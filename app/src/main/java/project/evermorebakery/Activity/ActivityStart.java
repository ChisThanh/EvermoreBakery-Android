package project.evermorebakery.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import project.evermorebakery.Fragment.FragmentLogin;

import project.evermorebakery.Handler.HandlerRestAPI;
import project.evermorebakery.Interface.InterfaceVolleyResponseListener;
import project.evermorebakery.R;

public class ActivityStart extends AppCompatActivity implements Runnable
{
    ProgressBar uProgress_aStart_ProgressBar;
    Button uButton_aStart_Continue;
    FrameLayout lFrame_aStart_Layout;
    final static int INTERVAL = 100;

    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        addControls();
        setProgressBar();
        addEvents();

        //addAPI();
    }

    @Override
    public void run()
    {
        int progress = uProgress_aStart_ProgressBar.getProgress() + 1;
        uProgress_aStart_ProgressBar.setProgress(progress);

        if (progress < uProgress_aStart_ProgressBar.getMax())
            new Handler().postDelayed(ActivityStart.this, INTERVAL);
        else
        {
            uProgress_aStart_ProgressBar.setVisibility(View.GONE);
            uButton_aStart_Continue.setVisibility(View.VISIBLE);
        }
    }

    void addControls()
    {
        uProgress_aStart_ProgressBar = findViewById(R.id.uProgress_aStart_ProgressBar);
        uButton_aStart_Continue = findViewById(R.id.uButton_aStart_Continue);
        lFrame_aStart_Layout = findViewById(R.id.lFrame_aStart_Layout);
    }

    void setProgressBar()
    {
        uProgress_aStart_ProgressBar.setMax(100);
        uProgress_aStart_ProgressBar.setProgress(0);

        new Handler().postDelayed(ActivityStart.this, INTERVAL);
    }

    void addEvents()
    {
        uButton_aStart_Continue.setOnClickListener(view ->
        {
            uButton_aStart_Continue.setVisibility(View.GONE);
            lFrame_aStart_Layout.setVisibility(View.VISIBLE);
            loadFragment(new FragmentLogin());
        });
    }

    void loadFragment(Fragment fragment)
    {
        FragmentTransaction fragment_transaction = getSupportFragmentManager().beginTransaction();
        fragment_transaction.replace(R.id.lFrame_aStart_Layout, fragment);
        fragment_transaction.commit();
    }

    void addAPI()
    {
        HandlerRestAPI handlerRESTAPI = new HandlerRestAPI(Volley.newRequestQueue(this));
        String select = "SELECT * FROM account";
        handlerRESTAPI.fetchData(select,
                new InterfaceVolleyResponseListener()
                {
                    @Override
                    public void onResponse(JSONArray response)
                    {
                        Log.e("Response", response.toString());
                    }

                    @Override
                    public void onError(String errorMessage)
                    {

                    }
                });

        String query = "UPDATE account SET USERNAME = 2711231 WHERE ACCOUNT_ID = 'TK0015'";
        handlerRESTAPI.updateData(query,
                new InterfaceVolleyResponseListener()
                {
                    @Override
                    public void onResponse(JSONArray response)
                    {
                        Log.e("Response", response.toString());
                    }

                    @Override
                    public void onError(String errorMessage)
                    {
                    }
                });
    }
}

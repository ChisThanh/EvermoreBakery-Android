//Activity Start:
//Is Use to Open the App when There Is No User Logged
//Has a Frame Layout to Open Fragment Login, Fragment Register, and Fragment Recover
//Has a Progress Bar to Stylize the App

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
import project.evermorebakery.Handler.HandlerRESTAPI;
import project.evermorebakery.Interface.VolleyResponseListener;
import project.evermorebakery.R;

public class ActivityStart extends AppCompatActivity implements Runnable
{
    ProgressBar uProgress_aStart_ProgressBar; //ProgressBar: Show Progress Bar
    Button uButton_aStart_Continue; //Button: Start Fragment Login
    FrameLayout lFrame_aStart_Layout; //FrameLayout: Store Fragments
    final static int INTERVAL = 100; //Static: Interval of the Progress Bar

    @Override
    protected void onCreate(Bundle savedInstanceState) //Extends: Extends from AppCompatActivity
    {
        //Code: Start Activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        addControls();
        setProgressBar();
        addEvents();

//        HandlerRESTAPI handlerRESTAPI = new HandlerRESTAPI(Volley.newRequestQueue(this));
//        String select = "SELECT * FROM account";
//        handlerRESTAPI.fetchData(select,
//                new VolleyResponseListener() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        Log.e("Response", response.toString());
//                    }
//
//                    @Override
//                    public void onError(String errorMessage) {
//                    }
//                });
//
//        String query = "UPDATE account SET USERNAME = 2711231 WHERE ACCOUNT_ID = 'TK0015'";
//        handlerRESTAPI.updateData(query,
//                new VolleyResponseListener() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        Log.e("Response", response.toString());
//                    }
//
//                    @Override
//                    public void onError(String errorMessage) {
//                    }
//                });

    }

    @Override
    public void run() //Interface: Override from Interface Runnable
    {
        //Code: Increase the Progress Bar and Set New Progress
        int progress = uProgress_aStart_ProgressBar.getProgress() + 1;
        uProgress_aStart_ProgressBar.setProgress(progress);

        //Code: Check If The Progress Bar is Max Out
        if (progress < uProgress_aStart_ProgressBar.getMax()) //Code: Continue The Loop
            //Code: Continue The Progress Bar with the Delay Correspond with the INTERVAL
            new Handler().postDelayed(ActivityStart.this, INTERVAL);
        else //Code: Stop The Loop
        {
            //Code: Hide Progress Bar and Show Button
            uProgress_aStart_ProgressBar.setVisibility(View.GONE);
            uButton_aStart_Continue.setVisibility(View.VISIBLE);
        }
    }

    void addControls() //Function: Add Controls for Easy Access
    {
        uProgress_aStart_ProgressBar = findViewById(R.id.uProgress_aStart_ProgressBar);
        uButton_aStart_Continue = findViewById(R.id.uButton_aStart_Continue);
        lFrame_aStart_Layout = findViewById(R.id.lFrame_aStart_Layout);
    }

    void setProgressBar() //Function: Create and Run Progress Bar
    {
        //Code: Initialize The Progress Bar
        uProgress_aStart_ProgressBar.setMax(100);
        uProgress_aStart_ProgressBar.setProgress(0);

        //Code: Start The Progress Bar and Set the Delay Correspond with the INTERVAL
        new Handler().postDelayed(ActivityStart.this, INTERVAL);
    }

    void addEvents() //Function: Add Events for User to Interact
    {
        //Code: Hide Button and Show Frame Layout
        uButton_aStart_Continue.setOnClickListener(view ->
        {
            uButton_aStart_Continue.setVisibility(View.GONE);
            lFrame_aStart_Layout.setVisibility(View.VISIBLE);
            loadFragment(new FragmentLogin());
        });
    }

    void loadFragment(Fragment fragment) //Code: Load Fragment from Input Fragment into Frame Layout
    {
        FragmentTransaction fragment_transaction = getSupportFragmentManager().beginTransaction();
        fragment_transaction.replace(R.id.lFrame_aStart_Layout, fragment);
        fragment_transaction.commit();
    }
}

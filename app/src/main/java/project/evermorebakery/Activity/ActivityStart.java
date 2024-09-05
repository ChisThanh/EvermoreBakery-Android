package project.evermorebakery.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import project.evermorebakery.Fragment.FragmentLogin;

import project.evermorebakery.Helper.HelperSharedPreferences;
import project.evermorebakery.Manager.ManagerAccount;
import project.evermorebakery.R;

public class ActivityStart extends AppCompatActivity implements Runnable
{
    ProgressBar uProgress_aStart_ProgressBar;
    AppCompatButton uButton_aStart_Continue;
    FrameLayout lFrame_aStart_Layout;
    final static int INTERVAL = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        HelperSharedPreferences shared_preferences = new HelperSharedPreferences(ActivityStart.this);
        if(shared_preferences.getSavedAccount() != null)
        {
            ManagerAccount.getInstance().setAccount(shared_preferences.getSavedAccount());
            Intent intent = new Intent(ActivityStart.this, ActivityMain.class);
            startActivity(intent);
        }

        setContentView(R.layout.activity_start);
        addControls();
        setProgressBar();
        addEvents();
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
}

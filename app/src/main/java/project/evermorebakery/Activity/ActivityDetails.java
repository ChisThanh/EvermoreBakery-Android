package project.evermorebakery.Activity;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import project.evermorebakery.Fragment.FragmentDetails;
import project.evermorebakery.R;

public class ActivityDetails extends AppCompatActivity
{
    RelativeLayout lRelative_aDetails_Background;
    ImageView vImage_aDetails_Return;
    ImageView vImage_aDetails_Share;
    FrameLayout lFrame_aDetails_Layout;

    protected void onCreate(Bundle saved_instance_state)
    {
        super.onCreate(saved_instance_state);
        setContentView(R.layout.activity_details);

        addControls();
        addEvents();

        loadFragment(new FragmentDetails());
    }

    void addControls()
    {
        lRelative_aDetails_Background = findViewById(R.id.lRelative_aDetails_Background);
        vImage_aDetails_Return = findViewById(R.id.vImage_aDetails_Return);
        vImage_aDetails_Share = findViewById(R.id.vImage_aDetails_Share);
        lFrame_aDetails_Layout = findViewById(R.id.lFrame_aDetails_Layout);
    }

    void addEvents()
    {
        vImage_aDetails_Return.setOnClickListener(view -> {});
        vImage_aDetails_Share.setOnClickListener(view -> {});
    }

    void loadFragment(Fragment fragment)
    {
        FragmentTransaction fragment_transaction = getSupportFragmentManager().beginTransaction();
        fragment_transaction.replace(R.id.lFrame_aDetails_Layout, fragment);
        fragment_transaction.commit();
    }
}

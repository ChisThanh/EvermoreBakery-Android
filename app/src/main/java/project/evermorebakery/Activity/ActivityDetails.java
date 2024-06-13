package project.evermorebakery.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.squareup.picasso.Picasso;

import project.evermorebakery.Fragment.FragmentDetails;
import project.evermorebakery.Model.ModelProduct;
import project.evermorebakery.R;

public class ActivityDetails extends AppCompatActivity
{
    ImageView vImage_aDetails_Background;
    ImageView vImage_aDetails_Return;
    ImageView vImage_aDetails_Share;
    FrameLayout lFrame_aDetails_Layout;
    ModelProduct product;

    protected void onCreate(Bundle saved_instance_state)
    {
        super.onCreate(saved_instance_state);
        setContentView(R.layout.activity_details);

        addControls();
        getData();
        addEvents();

        sendData();
    }

    void addControls()
    {
        vImage_aDetails_Background = findViewById(R.id.vImage_aDetails_Background);
        vImage_aDetails_Return = findViewById(R.id.vImage_aDetails_Return);
        vImage_aDetails_Share = findViewById(R.id.vImage_aDetails_Share);
        lFrame_aDetails_Layout = findViewById(R.id.lFrame_aDetails_Layout);
    }

    @SuppressLint("DiscouragedApi")
    void getData()
    {
        product = (ModelProduct) getIntent().getSerializableExtra("product");

         int drawable_id = getResources().getIdentifier(product.getImage(),
                "drawable", getPackageName());

        if(drawable_id != 0)
            Picasso.get()
                    .load(drawable_id)
                    .placeholder(R.drawable.square_placeholder)
                    .error(R.drawable.square_error).into(vImage_aDetails_Background);
        else vImage_aDetails_Background.setImageResource(R.drawable.square_placeholder);
    }

    void sendData()
    {
        FragmentDetails fragment = new FragmentDetails();
        Bundle bundle = new Bundle();
        bundle.putSerializable("product", product);
        fragment.setArguments(bundle);

        loadFragment(fragment);
    }

    void addEvents()
    {
        vImage_aDetails_Return.setOnClickListener(view ->
        {
            Intent intent = new Intent(ActivityDetails.this, ActivityMain.class);
            intent.putExtra("location", getIntent().getStringExtra("location"));
            startActivity(intent);
        });

        vImage_aDetails_Share.setOnClickListener(view -> {});
    }

    void loadFragment(Fragment fragment)
    {
        FragmentTransaction fragment_transaction = getSupportFragmentManager().beginTransaction();
        fragment_transaction.replace(R.id.lFrame_aDetails_Layout, fragment);
        fragment_transaction.commit();
    }
}

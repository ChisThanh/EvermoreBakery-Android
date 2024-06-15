package project.evermorebakery.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.text.SimpleDateFormat;
import java.util.Date;

import project.evermorebakery.Activity.ActivityStart;
import project.evermorebakery.Manager.ManagerDelivery;
import project.evermorebakery.R;

public class FragmentDelivery extends Fragment implements Runnable
{
    View view;
    TextView vText_fDelivery_Order;
    TextView vText_fDelivery_Total;
    TextView vText_fDelivery_Date;
    LinearLayout lLinear_fDelivery_Receive;
    LinearLayout lLinear_fDelivery_Bake;
    LinearLayout lLinear_fDelivery_Deliver;
    LinearLayout lLinear_fDelivery_Arrive;
    int time;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved_instance_state)
    {
        view = inflater.inflate(R.layout.fragment_delivery, container, false);
        time = 1;

        addControls();
        addData();

        return view;
    }

    private void addData() {

        String id = ManagerDelivery.getInstance().getId();

        if(id == null){
            loadFragment(new FragmentEmpty());
            return;
        }
        double total = ManagerDelivery.getInstance().getTotal();
        Date date = ManagerDelivery.getInstance().getDate();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String orderDate = sdf.format(date);

        vText_fDelivery_Order.setText(id);
        vText_fDelivery_Total.setText(String.valueOf(total));
        vText_fDelivery_Date.setText(orderDate);

        Handler handler = new Handler();
        handler.postDelayed(this, 5000);
    }
    void loadFragment(Fragment fragment)
    {
        FragmentTransaction fragment_transaction = getParentFragmentManager().beginTransaction();
        fragment_transaction.replace(R.id.lFrame_aMain_Layout, fragment);
        fragment_transaction.commit();
    }

    void addControls()
    {
        vText_fDelivery_Order = view.findViewById(R.id.vText_fDelivery_Order);
        vText_fDelivery_Total = view.findViewById(R.id.vText_fDelivery_Total);
        vText_fDelivery_Date = view.findViewById(R.id.vText_fDelivery_Date);
        lLinear_fDelivery_Receive = view.findViewById(R.id.lLinear_fDelivery_Receive);
        lLinear_fDelivery_Bake = view.findViewById(R.id.lLinear_fDelivery_Bake);
        lLinear_fDelivery_Deliver = view.findViewById(R.id.lLinear_fDelivery_Deliver);
        lLinear_fDelivery_Arrive = view.findViewById(R.id.lLinear_fDelivery_Arrive);

        lLinear_fDelivery_Bake.setVisibility(View.GONE);
        lLinear_fDelivery_Deliver.setVisibility(View.GONE);
        lLinear_fDelivery_Arrive.setVisibility(View.GONE);
    }

    @Override
    public void run()
    {
        time = time + 1;
        switch(time)
        {
            case 100:
                lLinear_fDelivery_Bake.setVisibility(View.VISIBLE);
                break;
            case 200:
                lLinear_fDelivery_Deliver.setVisibility(View.VISIBLE);
                break;
            case 300:
                lLinear_fDelivery_Arrive.setVisibility(View.VISIBLE);
                break;
        }
    }
}

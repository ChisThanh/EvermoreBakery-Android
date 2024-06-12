package project.evermorebakery.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import project.evermorebakery.Adapter.AdapterDisplay;
import project.evermorebakery.Model.ModelProduct;
import project.evermorebakery.R;

public class FragmentCheckout extends Fragment
{

    View view;
    TextView vText_fCheckout_Total;
    TextView vText_fCheckout_Offer;
    TextView vText_fCheckout_Order;
    TextView vText_fCheckout_Delivery;
    TextView vText_fCheckout_Checkout;
    TextView vText_fCheckout_Address;
    RecyclerView vRecycler_fCheckout_Item;
    LinearLayout lLinear_fPayment_Offer;
    LinearLayout lLinear_fPayment_Address;
    Button uButton_fCheckout_Payment;
    ArrayList<ModelProduct> product_list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle saved_instance_state)
    {

        view = inflater.inflate(R.layout.fragment_checkout, container, false);

        addControls();
        addData();
        addAdapter();
        addEvents();

        return view;
    }

    void addControls()
    {
        vText_fCheckout_Total = view.findViewById(R.id.vText_fCheckout_Total);
        vText_fCheckout_Offer = view.findViewById(R.id.vText_fCheckout_Offer);
        vText_fCheckout_Order = view.findViewById(R.id.vText_fCheckout_Order);
        vText_fCheckout_Delivery = view.findViewById(R.id.vText_fCheckout_Delivery);
        vText_fCheckout_Checkout = view.findViewById(R.id.vText_fCheckout_Checkout);
        vText_fCheckout_Address = view.findViewById(R.id.vText_fCheckout_Address);
        vRecycler_fCheckout_Item = view.findViewById(R.id.vRecycler_fCheckout_Item);
        lLinear_fPayment_Offer = view.findViewById(R.id.lLinear_fPayment_Offer);
        lLinear_fPayment_Address = view.findViewById(R.id.lLinear_fPayment_Address);
        uButton_fCheckout_Payment = view.findViewById(R.id.uButton_fCheckout_Payment);
    }

    void addData()
    {
        product_list = new ArrayList<>();

        product_list.add(new ModelProduct("square_placeholder", "Product 1", 10000, 5));
        product_list.add(new ModelProduct("square_placeholder", "Product 1", 10000, 3));
        product_list.add(new ModelProduct("square_placeholder", "Product 1", 10000, 4));
    }

    void addAdapter()
    {
        LinearLayoutManager layout_manager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        AdapterDisplay menu_adapter = new AdapterDisplay(requireContext(), product_list);
        vRecycler_fCheckout_Item.setLayoutManager(layout_manager);
        vRecycler_fCheckout_Item.setAdapter(menu_adapter);
    }

    void addEvents()
    {
        lLinear_fPayment_Offer.setOnClickListener(view -> {});
        lLinear_fPayment_Address.setOnClickListener(view -> {});
        uButton_fCheckout_Payment.setOnClickListener(view -> loadFragment(new FragmentPayment()));
    }

    void loadFragment(Fragment fragment)
    {
        FragmentTransaction fragment_transaction = getParentFragmentManager().beginTransaction();
        fragment_transaction.replace(R.id.lFrame_aMain_Layout, fragment);
        fragment_transaction.commit();
    }
}

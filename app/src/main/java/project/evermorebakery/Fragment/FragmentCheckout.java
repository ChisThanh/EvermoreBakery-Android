package project.evermorebakery.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import project.evermorebakery.Adapter.AdapterCheckout;
import project.evermorebakery.Manager.ManagerAccount;
import project.evermorebakery.Manager.ManagerCart;
import project.evermorebakery.Model.ModelAccount;
import project.evermorebakery.Model.ModelCart;
import project.evermorebakery.R;

public class FragmentCheckout extends Fragment
{

    View view;
    TextView vText_fCheckout_Order;
    TextView vText_fCheckout_Delivery;
    TextView vText_fCheckout_Total;
    TextView vText_fCheckout_Address;
    RecyclerView vRecycler_fCheckout_Item;
    AppCompatButton uButton_fCheckout_Payment;
    ArrayList<ModelCart> cart_list;
    NumberFormat vnd_currency;
    ModelAccount account;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved_instance_state)
    {
        cart_list = ManagerCart.getInstance().getCartList();
        view = inflater.inflate(R.layout.fragment_checkout, container, false);

        account = ManagerAccount.getInstance().getAccount();
        vnd_currency = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

        addControls();
        addAdapter();
        addData();
        addEvents();

        return view;
    }


    void addControls()
    {
        vText_fCheckout_Order = view.findViewById(R.id.vText_fCheckout_Order);
        vText_fCheckout_Delivery = view.findViewById(R.id.vText_fCheckout_Delivery);
        vText_fCheckout_Total = view.findViewById(R.id.vText_fCheckout_Total);
        vText_fCheckout_Address = view.findViewById(R.id.vText_fCheckout_Address);
        vRecycler_fCheckout_Item = view.findViewById(R.id.vRecycler_fCheckout_Item);
        uButton_fCheckout_Payment = view.findViewById(R.id.uButton_fCheckout_Payment);
    }

    void addAdapter()
    {
        LinearLayoutManager layout_manager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        AdapterCheckout checkout_adapter = new AdapterCheckout(requireContext(), cart_list);
        vRecycler_fCheckout_Item.setLayoutManager(layout_manager);
        vRecycler_fCheckout_Item.setAdapter(checkout_adapter);
    }

    void addData()
    {
        vText_fCheckout_Order.setText(vnd_currency.format(ManagerCart.getInstance().getTotal()));
        vText_fCheckout_Delivery.setText(vnd_currency.format(30000));
        vText_fCheckout_Total.setText(vnd_currency.format(ManagerCart.getInstance().getTotal() + 30000));
        vText_fCheckout_Address.setText(account.getAddress());
    }

    void addEvents()
    {
        uButton_fCheckout_Payment.setOnClickListener(view -> loadFragment(new FragmentPayment()));
    }

    void loadFragment(Fragment fragment)
    {
        FragmentTransaction fragment_transaction = getParentFragmentManager().beginTransaction();
        fragment_transaction.replace(R.id.lFrame_aMain_Layout, fragment);
        fragment_transaction.commit();
    }
}

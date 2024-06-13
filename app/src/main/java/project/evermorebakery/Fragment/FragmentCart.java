package project.evermorebakery.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import project.evermorebakery.Activity.ActivityDetails;
import project.evermorebakery.Activity.ActivityMain;
import project.evermorebakery.Adapter.AdapterCart;
import project.evermorebakery.Custom.CustomVerticalSpacingItemDecoration;
import project.evermorebakery.Manager.ManagerCart;
import project.evermorebakery.Model.ModelCart;
import project.evermorebakery.R;

public class FragmentCart extends Fragment
{
    View view;
    RecyclerView vRecycler_fCart_Cart;
    TextView vText_fCart_Total;
    AppCompatButton uButton_fCart_Checkout;
    ArrayList<ModelCart> cart_list;
    AdapterCart cart_adapter;
    NumberFormat vnd_currency;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved_instance_state)
    {
        cart_list = ManagerCart.getInstance().getCartList();
        if(cart_list.isEmpty())
        {
            loadFragment(new FragmentEmpty());
            return null;
        }
        else view = inflater.inflate(R.layout.fragment_cart, container, false);

        vnd_currency = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

        addControls();
        addAdapter();
        addEvents();

        updateTotal(ManagerCart.getInstance().getTotal());

        return view;
    }

    void addControls()
    {
        vRecycler_fCart_Cart = view.findViewById(R.id.vRecycler_fCart_Cart);
        vText_fCart_Total = view.findViewById(R.id.vText_fCart_Total);
        uButton_fCart_Checkout = view.findViewById(R.id.uButton_fCart_Checkout);
    }

    void addAdapter()
    {
        LinearLayoutManager layout_manager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        cart_adapter = new AdapterCart(requireContext(), cart_list, FragmentCart.this);
        vRecycler_fCart_Cart.setLayoutManager(layout_manager);
        vRecycler_fCart_Cart.addItemDecoration(new CustomVerticalSpacingItemDecoration(25));
        vRecycler_fCart_Cart.setAdapter(cart_adapter);

        ItemTouchHelper item_touch_helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT)
        {
            @Override
            public boolean onMove(@NonNull RecyclerView recycler_view, @NonNull RecyclerView.ViewHolder holder, @NonNull RecyclerView.ViewHolder target)
            {
                return false;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder holder, int direction)
            {
                int position = holder.getAdapterPosition();
                cart_adapter.removeItem(position);

                updateTotal(ManagerCart.getInstance().getTotal());
                if(ManagerCart.getInstance().isCartEmpty())
                {
                    loadFragment(new FragmentEmpty());
                }
            }
        });

        item_touch_helper.attachToRecyclerView(vRecycler_fCart_Cart);
    }

    void addEvents()
    {
        cart_adapter.setOnItemClickListener(product ->
        {
            Intent intent = new Intent(requireContext(), ActivityDetails.class);
            intent.putExtra("product", product);
            intent.putExtra("location", "cart");
            startActivity(intent);
        });

        uButton_fCart_Checkout.setOnClickListener(view -> loadFragment(new FragmentCheckout()));
    }

    public void updateTotal(int total)
    {
        vText_fCart_Total.setText(vnd_currency.format(total));
    }

    public void loadFragment(Fragment fragment)
    {
        FragmentTransaction fragment_transaction = getParentFragmentManager().beginTransaction();
        fragment_transaction.replace(R.id.lFrame_aMain_Layout, fragment);
        fragment_transaction.commit();
    }
}

package project.evermorebakery.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.text.NumberFormat;
import java.util.Locale;

import project.evermorebakery.Manager.ManagerCart;
import project.evermorebakery.Model.ModelCart;
import project.evermorebakery.Model.ModelProduct;
import project.evermorebakery.R;

public class FragmentDetails extends Fragment
{
    View view;
    TextView vText_fDetails_Name;
    TextView vText_fDetails_Rating;
    TextView vText_fDetails_Comment;
    TextView vText_fDetails_Price;
    TextView vText_fDetails_Description;
    TextView vText_fDetails_TotalPrice;
    TextView vText_fDetails_Quantity;
    ImageView vImage_fDetails_Comment;
    AppCompatButton uButton_fDetails_Decrease;
    AppCompatButton uButton_fDetails_Increase;
    AppCompatButton uButton_fDetails_Cart;
    ModelProduct product;
    NumberFormat vnd_currency;
    int price;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved_instance_state)
    {
        view = inflater.inflate(R.layout.fragment_details, container, false);
        vnd_currency = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

        addControls();
        getData();
        addEvents();

        return view;
    }

    void addControls()
    {
        vText_fDetails_Name = view.findViewById(R.id.vText_fDetails_Name);
        vText_fDetails_Rating = view.findViewById(R.id.vText_fDetails_Rating);
        vText_fDetails_Comment = view.findViewById(R.id.vText_fDetails_Comment);
        vText_fDetails_Price = view.findViewById(R.id.vText_fDetails_Price);
        vText_fDetails_Description = view.findViewById(R.id.vText_fDetails_Description);
        vText_fDetails_TotalPrice = view.findViewById(R.id.vText_fDetails_TotalPrice);
        vText_fDetails_Quantity = view.findViewById(R.id.vText_fDetails_Quantity);
        vImage_fDetails_Comment = view.findViewById(R.id.vImage_fDetails_Comment);
        uButton_fDetails_Decrease = view.findViewById(R.id.uButton_fDetails_Decrease);
        uButton_fDetails_Increase = view.findViewById(R.id.uButton_fDetails_Increase);
        uButton_fDetails_Cart = view.findViewById(R.id.uButton_fDetails_Cart);
    }

    @SuppressLint("SetTextI18n")
    void getData()
    {
        if(getArguments() != null) product = (ModelProduct) getArguments().getSerializable("product");

        if(product != null)
        {
            vText_fDetails_Name.setText(product.getName());
            vText_fDetails_Rating.setText(String.valueOf(product.getRating()));
            vText_fDetails_Description.setText(product.getDescription());
            price = product.getPrice();
            vText_fDetails_Price.setText("Price: " + vnd_currency.format(price));
            vText_fDetails_TotalPrice.setText(vnd_currency.format(product.getPrice()));
        }
    }

    void addEvents()
    {
        vImage_fDetails_Comment.setOnClickListener(view -> sendData());

        uButton_fDetails_Decrease.setOnClickListener(view ->
        {
            int quantity = Integer.parseInt(vText_fDetails_Quantity.getText().toString());
            if(quantity > 1) quantity = quantity - 1;
            vText_fDetails_Quantity.setText(String.valueOf(quantity));
            price = product.getPrice() * quantity;
            vText_fDetails_TotalPrice.setText(vnd_currency.format(price));
        });

        uButton_fDetails_Increase.setOnClickListener(view ->
        {
            int quantity = Integer.parseInt(vText_fDetails_Quantity.getText().toString());
            quantity = quantity + 1;
            vText_fDetails_Quantity.setText(String.valueOf(quantity));
            price = product.getPrice() * quantity;
            vText_fDetails_TotalPrice.setText(vnd_currency.format(price));
        });

        uButton_fDetails_Cart.setOnClickListener(view ->
        {
            ModelCart cart = new ModelCart();

            cart.setProduct(product);
            cart.setQuantity(Integer.parseInt(vText_fDetails_Quantity.getText().toString()));
            cart.setPrice(price);

            ManagerCart.getInstance().addCart(cart);
        });
    }

    void sendData()
    {
        FragmentComment fragment = new FragmentComment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("product", product);
        fragment.setArguments(bundle);
        loadFragment(fragment);
    }

    void loadFragment(Fragment fragment)
    {
        FragmentTransaction fragment_transaction = getParentFragmentManager().beginTransaction();
        fragment_transaction.replace(R.id.lFrame_aDetails_Layout, fragment);
        fragment_transaction.commit();
    }
}

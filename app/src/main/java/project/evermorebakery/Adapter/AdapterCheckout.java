package project.evermorebakery.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import project.evermorebakery.Model.ModelCart;
import project.evermorebakery.Model.ModelProduct;
import project.evermorebakery.R;

public class AdapterCheckout extends RecyclerView.Adapter<AdapterCheckout.ViewHolderCheckout>
{
    Context context;
    ArrayList<ModelCart> cart_list;

    public AdapterCheckout(Context context, ArrayList<ModelCart> cart_list)
    {
        this.context = context;
        this.cart_list = cart_list;
    }

    @NonNull
    @Override
    public AdapterCheckout.ViewHolderCheckout onCreateViewHolder(@NonNull ViewGroup parent, int view_type)
    {
        return new AdapterCheckout.ViewHolderCheckout(LayoutInflater
                .from(context).inflate(R.layout.adapter_checkout, parent, false));
    }

    @SuppressLint({"DiscouragedApi", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull AdapterCheckout.ViewHolderCheckout holder, int position)
    {
        ModelCart cart = cart_list.get(position);
        ModelProduct product = cart.getProduct();

        int drawable_id = context.getResources().getIdentifier(product.getImage(),
                "drawable", context.getPackageName());

        if(drawable_id != 0)
            Picasso.get()
                    .load(drawable_id)
                    .placeholder(R.drawable.square_placeholder)
                    .error(R.drawable.square_error).into(holder.vImage_dCheckout_Image);
        else holder.vImage_dCheckout_Image.setImageResource(R.drawable.square_placeholder);

        holder.vText_dCheckout_Name.setText(product.getName());

        NumberFormat vnd_currency = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        holder.vText_dCheckout_Price.setText("Price: " + vnd_currency.format(cart.getPrice()));

        holder.vText_dCheckout_Quantity.setText("Quantity: " + cart.getQuantity());

    }

    @Override
    public int getItemCount()
    {
        return cart_list.size();
    }

    public static class ViewHolderCheckout extends RecyclerView.ViewHolder
    {
        ImageView vImage_dCheckout_Image;
        TextView vText_dCheckout_Name;
        TextView vText_dCheckout_Price;
        TextView vText_dCheckout_Quantity;

        public ViewHolderCheckout(@NonNull View item_view)
        {
            super(item_view);

            vImage_dCheckout_Image = item_view.findViewById(R.id.vImage_dCheckout_Image);
            vText_dCheckout_Name = item_view.findViewById(R.id.vText_dCheckout_Name);
            vText_dCheckout_Price = item_view.findViewById(R.id.vText_dCheckout_Price);
            vText_dCheckout_Quantity = item_view.findViewById(R.id.vText_dCheckout_Quantity);
        }
    }
}
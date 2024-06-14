package project.evermorebakery.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import project.evermorebakery.Fragment.FragmentCart;
import project.evermorebakery.Fragment.FragmentEmpty;
import project.evermorebakery.Interface.InterfaceOnClickListener;
import project.evermorebakery.Manager.ManagerCart;
import project.evermorebakery.Model.ModelCart;
import project.evermorebakery.Model.ModelProduct;
import project.evermorebakery.R;

public class AdapterCart extends RecyclerView.Adapter<AdapterCart.ViewHolderCart>
{
    Context context;
    ArrayList<ModelCart> cart_list;
    FragmentCart fragment;
    InterfaceOnClickListener listener;

    public AdapterCart(Context context, ArrayList<ModelCart> cart_list, FragmentCart fragment)
    {
        this.context = context;
        this.cart_list = cart_list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public AdapterCart.ViewHolderCart onCreateViewHolder(@NonNull ViewGroup parent, int view_type)
    {
        return new AdapterCart.ViewHolderCart(LayoutInflater
                .from(context).inflate(R.layout.adapter_cart, parent, false));
    }

    @SuppressLint({"DiscouragedApi", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull AdapterCart.ViewHolderCart holder, int position)
    {
        ModelCart cart = cart_list.get(position);
        ModelProduct product = cart.getProduct();

        int drawable_id = context.getResources().getIdentifier(product.getImage(),
                "drawable", context.getPackageName());

        if(drawable_id != 0)
            Picasso.get()
                    .load(drawable_id)
                    .resize(300, 300)
                    .placeholder(R.drawable.square_placeholder)
                    .error(R.drawable.square_error).into(holder.vImage_dCart_Image);
        else holder.vImage_dCart_Image.setImageResource(R.drawable.square_placeholder);

        holder.vText_dCart_Name.setText(product.getShortName());

        NumberFormat vnd_currency = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        holder.vText_dCart_Price.setText("Price: " + vnd_currency.format(cart.getPrice()));

        holder.vText_dCart_Quantity.setText(String.valueOf(cart.getQuantity()));

        holder.uButton_dCart_Decrease.setOnClickListener(view ->
        {
            if(cart.getQuantity() > 1)
            {
                cart.setQuantity(cart.getQuantity() - 1);
                cart.setPrice(product.getPrice() * cart.getQuantity());
            }
            else cart_list.remove(cart);

            if(ManagerCart.getInstance().isCartEmpty())
            {
                fragment.loadFragment(new FragmentEmpty());
            }

            fragment.updateTotal(ManagerCart.getInstance().getTotal());
            notifyItemChanged(position);
        });

        holder.uButton_dCart_Increase.setOnClickListener(view ->
        {
            cart.setQuantity(cart.getQuantity() + 1);
            cart.setPrice(product.getPrice() * cart.getQuantity());

            fragment.updateTotal(ManagerCart.getInstance().getTotal());
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount()
    {
        return cart_list.size();
    }

    public void setOnItemClickListener(InterfaceOnClickListener listener)
    {
        this.listener = listener;
    }

    public void removeItem(int position)
    {
        cart_list.remove(position);
        notifyItemRemoved(position);
    }

    public class ViewHolderCart extends RecyclerView.ViewHolder
    {
        ImageView vImage_dCart_Image;
        TextView vText_dCart_Name;
        TextView vText_dCart_Price;
        TextView vText_dCart_Quantity;
        AppCompatButton uButton_dCart_Decrease;
        AppCompatButton uButton_dCart_Increase;

        public ViewHolderCart(@NonNull View item_view)
        {
            super(item_view);

            vImage_dCart_Image = item_view.findViewById(R.id.vImage_dCart_Image);
            vText_dCart_Name = item_view.findViewById(R.id.vText_dCart_Name);
            vText_dCart_Price = item_view.findViewById(R.id.vText_dCart_Price);
            vText_dCart_Quantity = item_view.findViewById(R.id.vText_dCart_Quantity);
            uButton_dCart_Decrease = item_view.findViewById(R.id.uButton_dCart_Decrease);
            uButton_dCart_Increase = item_view.findViewById(R.id.uButton_dCart_Increase);

            item_view.setOnClickListener(view ->
            {
                if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION)
                    listener.onItemClick(cart_list.get(getAdapterPosition()).getProduct());
            });
        }
    }
}

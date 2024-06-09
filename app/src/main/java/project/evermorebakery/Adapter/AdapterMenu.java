package project.evermorebakery.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import project.evermorebakery.Model.ModelProduct;
import project.evermorebakery.R;

public class AdapterMenu extends RecyclerView.Adapter<AdapterMenu.MenuViewHolder>
{
    Context context;
    ArrayList<ModelProduct> product_list;

    public AdapterMenu(Context context, ArrayList<ModelProduct> product_list)
    {
        this.context = context;
        this.product_list = product_list;
    }

    @NonNull
    @Override
    public AdapterMenu.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int view_type)
    {
        return new AdapterMenu.MenuViewHolder(LayoutInflater
                .from(context).inflate(R.layout.adapter_menu, parent, false));
    }

    @SuppressLint({"DiscouragedApi", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull AdapterMenu.MenuViewHolder holder, int position)
    {
        ModelProduct product = product_list.get(position);

        int drawable_id = context.getResources().getIdentifier(product.getImage(),
                "drawable", context.getPackageName());

        if(drawable_id != 0)
            Picasso.get()
                    .load(drawable_id)
                    .placeholder(R.drawable.square_placeholder)
                    .error(R.drawable.square_error).into(holder.vImage_aMenu_Image);
        else holder.vImage_aMenu_Image.setImageResource(R.drawable.square_placeholder);

        holder.vText_aMenu_Name.setText(product.getName());

        NumberFormat vnd_currency = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        holder.vText_aMenu_Price.setText("Price: " + vnd_currency.format(product_list.get(position).getPrice()));

        holder.uRating_aMenu_Rating.setRating(product.getRating());
    }

    @Override
    public int getItemCount()
    {
        return product_list.size();
    }

    public static class MenuViewHolder extends RecyclerView.ViewHolder
    {
        ImageView vImage_aMenu_Image;
        TextView vText_aMenu_Name;
        TextView vText_aMenu_Price;
        RatingBar uRating_aMenu_Rating;

        public MenuViewHolder(@NonNull View item_view)
        {
            super(item_view);
            vImage_aMenu_Image = item_view.findViewById(R.id.vImage_aMenu_Image);
            vText_aMenu_Name = item_view.findViewById(R.id.vText_aMenu_Name);
            vText_aMenu_Price = item_view.findViewById(R.id.vText_aMenu_Price);
            uRating_aMenu_Rating = item_view.findViewById(R.id.uRating_aMenu_Rating);
        }
    }
}

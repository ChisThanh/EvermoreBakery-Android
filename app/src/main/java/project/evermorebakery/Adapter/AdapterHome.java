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

import project.evermorebakery.Model.ModelProduct;
import project.evermorebakery.R;

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.HomeViewHolder>
{
    Context context;
    ArrayList<ModelProduct> product_list;

    public AdapterHome(Context context, ArrayList<ModelProduct> product_list)
    {
        this.context = context;
        this.product_list = product_list;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int view_type)
    {
        return new AdapterHome.HomeViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_home, parent, false));
    }

    @SuppressLint("DiscouragedApi, SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position)
    {
        int drawable_id = context.getResources().getIdentifier(product_list.get(position).getImage(),
                "drawable", context.getPackageName());

        if(drawable_id != 0)
            Picasso.get()
                    .load(drawable_id)
                    .placeholder(R.drawable.square_placeholder)
                    .error(R.drawable.square_error).into(holder.vImage_aHome_Image);
        else holder.vImage_aHome_Image.setImageResource(R.drawable.square_placeholder);

        holder.vText_aHome_Name.setText(product_list.get(position).getName());

        NumberFormat vnd_currency = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        holder.vText_aHome_Price.setText("Price: " + vnd_currency.format(product_list.get(position).getPrice()));
    }

    @Override
    public int getItemCount()
    {
        return product_list.size();
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder
    {
        ImageView vImage_aHome_Image;
        TextView vText_aHome_Name;
        TextView vText_aHome_Price;

        public HomeViewHolder(View item_view)
        {
            super(item_view);

            vImage_aHome_Image = item_view.findViewById(R.id.vImage_aHome_Image);
            vText_aHome_Name = item_view.findViewById(R.id.vText_aHome_Name);
            vText_aHome_Price = item_view.findViewById(R.id.vText_aHome_Price);
        }
    }
}

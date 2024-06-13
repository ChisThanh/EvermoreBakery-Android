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

import project.evermorebakery.Interface.InterfaceOnClickListener;
import project.evermorebakery.Model.ModelProduct;
import project.evermorebakery.R;

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.ViewHolderHome>
{
    Context context;
    ArrayList<ModelProduct> product_list;
    InterfaceOnClickListener listener;

    public AdapterHome(Context context, ArrayList<ModelProduct> product_list)
    {
        this.context = context;
        this.product_list = product_list;
    }

    @NonNull
    @Override
    public ViewHolderHome onCreateViewHolder(@NonNull ViewGroup parent, int view_type)
    {
        return new AdapterHome.ViewHolderHome(LayoutInflater.from(context).inflate(R.layout.adapter_home, parent, false));
    }

    @SuppressLint("DiscouragedApi, SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderHome holder, int position)
    {
        int drawable_id = context.getResources().getIdentifier(product_list.get(position).getImage(),
                "drawable", context.getPackageName());

        if(drawable_id != 0)
            Picasso.get()
                    .load(drawable_id)
                    .placeholder(R.drawable.square_placeholder)
                    .error(R.drawable.square_error).into(holder.vImage_dHome_Image);
        else holder.vImage_dHome_Image.setImageResource(R.drawable.square_placeholder);

        holder.vText_dHome_Name.setText(product_list.get(position).getName());

        NumberFormat vnd_currency = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        holder.vText_dHome_Price.setText("Price: " + vnd_currency.format(product_list.get(position).getPrice()));
    }

    @Override
    public int getItemCount()
    {
        return product_list.size();
    }

    public void setOnItemClickListener(InterfaceOnClickListener listener)
    {
        this.listener = listener;
    }

    public class ViewHolderHome extends RecyclerView.ViewHolder
    {
        ImageView vImage_dHome_Image;
        TextView vText_dHome_Name;
        TextView vText_dHome_Price;

        public ViewHolderHome(View item_view)
        {
            super(item_view);

            vImage_dHome_Image = item_view.findViewById(R.id.vImage_dHome_Image);
            vText_dHome_Name = item_view.findViewById(R.id.vText_dHome_Name);
            vText_dHome_Price = item_view.findViewById(R.id.vText_dHome_Price);

            item_view.setOnClickListener(view ->
            {
                if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION)
                    listener.onItemClick(product_list.get(getAdapterPosition()));
            });
        }
    }
}

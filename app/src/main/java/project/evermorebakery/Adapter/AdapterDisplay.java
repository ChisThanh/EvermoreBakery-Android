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

public class AdapterDisplay extends RecyclerView.Adapter<AdapterDisplay.ViewHolderDisplay>
{
    Context context;
    ArrayList<ModelProduct> product_list;
    InterfaceOnClickListener listener;

    public AdapterDisplay(Context context, ArrayList<ModelProduct> product_list)
    {
        this.context = context;
        this.product_list = product_list;
    }

    @NonNull
    @Override
    public AdapterDisplay.ViewHolderDisplay onCreateViewHolder(@NonNull ViewGroup parent, int view_type)
    {
        return new AdapterDisplay.ViewHolderDisplay(LayoutInflater
                .from(context).inflate(R.layout.adapter_display, parent, false));
    }

    @SuppressLint({"DiscouragedApi", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull AdapterDisplay.ViewHolderDisplay holder, int position)
    {
        ModelProduct product = product_list.get(position);

        int drawable_id = context.getResources().getIdentifier(product.getImage(),
                "drawable", context.getPackageName());

        if(drawable_id != 0)
            Picasso.get()
                    .load(drawable_id)
                    .placeholder(R.drawable.square_placeholder)
                    .error(R.drawable.square_error).into(holder.vImage_dDisplay_Image);
        else holder.vImage_dDisplay_Image.setImageResource(R.drawable.square_placeholder);

        holder.vText_dDisplay_Name.setText(product.getName());

        NumberFormat vnd_currency = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        holder.vText_dDisplay_Price.setText("Price: " + vnd_currency.format(product.getPrice()));
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

    public class ViewHolderDisplay extends RecyclerView.ViewHolder
    {
        ImageView vImage_dDisplay_Image;
        TextView vText_dDisplay_Name;
        TextView vText_dDisplay_Price;

        public ViewHolderDisplay(@NonNull View item_view)
        {
            super(item_view);
            vImage_dDisplay_Image = item_view.findViewById(R.id.vImage_dDisplay_Image);
            vText_dDisplay_Name = item_view.findViewById(R.id.vText_dDisplay_Name);
            vText_dDisplay_Price = item_view.findViewById(R.id.vText_dDisplay_Price);

            item_view.setOnClickListener(view ->
            {
                if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION)
                    listener.onItemClick(product_list.get(getAdapterPosition()));
            });
        }
    }
}

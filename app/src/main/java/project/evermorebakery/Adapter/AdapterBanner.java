package project.evermorebakery.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import project.evermorebakery.R;

public class AdapterBanner extends RecyclerView.Adapter<AdapterBanner.BannerViewHolder>
{
    Context context;
    int[] banner_list;

    public AdapterBanner(Context context, int[] banner_list)
    {
        this.context = context;
        this.banner_list = banner_list;
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int view_type)
    {
        return new BannerViewHolder(LayoutInflater
                .from(context).inflate(R.layout.adapter_banner, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position)
    {
        holder.vImage_dBanner_Banner.setImageResource(banner_list[position]);
    }

    @Override
    public int getItemCount()
    {
        return banner_list.length;
    }

    public static class BannerViewHolder extends RecyclerView.ViewHolder
    {
        ImageView vImage_dBanner_Banner;

        public BannerViewHolder(@NonNull View item_view)
        {
            super(item_view);
            vImage_dBanner_Banner = item_view.findViewById(R.id.vImage_dBanner_Banner);
        }
    }
}

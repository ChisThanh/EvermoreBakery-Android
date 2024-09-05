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

import java.util.ArrayList;

import project.evermorebakery.Model.ModelNotification;
import project.evermorebakery.R;

public class AdapterNotification extends RecyclerView.Adapter<AdapterNotification.ViewHolderNotification>
{
    Context context;
    ArrayList<ModelNotification> notification_list;

    public AdapterNotification(Context context, ArrayList<ModelNotification> notification_list)
    {
        this.context = context;
        this.notification_list = notification_list;
    }

    @NonNull
    @Override
    public AdapterNotification.ViewHolderNotification onCreateViewHolder(@NonNull ViewGroup parent, int view_type)
    {
        return new AdapterNotification.ViewHolderNotification(LayoutInflater
                .from(context).inflate(R.layout.adapter_notification, parent, false));
    }

    @SuppressLint({"DiscouragedApi", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull AdapterNotification.ViewHolderNotification holder, int position)
    {
        ModelNotification notification = notification_list.get(position);

        switch(notification.getType())
        {
            case "Order":
                holder.vImage_dNotification_Image.setImageResource(R.drawable.square_order);
                break;
            case "Delivery":
                holder.vImage_dNotification_Image.setImageResource(R.drawable.square_delivery);
                break;
            case "Event":
                holder.vImage_dNotification_Image.setImageResource(R.drawable.square_event);
                break;
            case "News":
                holder.vImage_dNotification_Image.setImageResource(R.drawable.square_news);
                break;
            case "Discount":
                holder.vImage_dNotification_Image.setImageResource(R.drawable.square_discount);
                break;
        }

        holder.vText_dNotification_Title.setText(notification.getTitle());
        holder.vText_dNotification_Content.setText(notification.getContent());
    }

    @Override
    public int getItemCount()
    {
        return notification_list.size();
    }

    public void removeItem(int position)
    {
        notification_list.remove(position);
        notifyItemRemoved(position);
    }

    public static class ViewHolderNotification extends RecyclerView.ViewHolder
    {
        ImageView vImage_dNotification_Image;
        TextView vText_dNotification_Title;
        TextView vText_dNotification_Content;
        TextView vText_dNotification_Time;

        public ViewHolderNotification(@NonNull View item_view)
        {
            super(item_view);

            vImage_dNotification_Image = item_view.findViewById(R.id.vImage_dNotification_Image);
            vText_dNotification_Title = item_view.findViewById(R.id.vText_dNotification_Title);
            vText_dNotification_Content = item_view.findViewById(R.id.vText_dNotification_Content);
            vText_dNotification_Time = item_view.findViewById(R.id.vText_dNotification_Time);
        }
    }
}
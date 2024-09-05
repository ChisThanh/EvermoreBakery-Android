package project.evermorebakery.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import project.evermorebakery.R;

public class AdapterSpinner extends BaseAdapter
{
    Context context;
    String[] string_list;

    public AdapterSpinner(Context context, String[] string_list)
    {
        this.context = context;
        this.string_list = string_list;
    }

    @Override
    public int getCount()
    {
        return string_list.length;
    }

    @Override
    public Object getItem(int position)
    {
        return string_list[position];
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup)
    {
        @SuppressLint({"ViewHolder", "InflateParams"})
        View item_view = LayoutInflater.from(context).inflate(R.layout.adapter_spinner, null, false);
        String string = string_list[position];

        TextView vText_dSpinner_Name = item_view.findViewById(R.id.vText_dSpinner_Name);
        vText_dSpinner_Name.setText(string);

        ImageView vImage_dSpinner_Icon = item_view.findViewById(R.id.vImage_dSpinner_Icon);
        switch (string)
        {
            case "Male":
                vImage_dSpinner_Icon.setImageResource(R.drawable.icon_male);
                break;
            case "Female":
                vImage_dSpinner_Icon.setImageResource(R.drawable.icon_female);
                break;
            case "Other":
                vImage_dSpinner_Icon.setImageResource(R.drawable.icon_gender);
                break;
            default:
                vImage_dSpinner_Icon.setImageResource(R.drawable.icon_card);
                break;
        }

        return item_view;
    }
}

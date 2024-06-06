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

public class AdapterGender extends BaseAdapter
{
    Context context;
    String[] gender_list;

    public AdapterGender(Context context, String[] gender_list)
    {
        this.context = context;
        this.gender_list = gender_list;
    }

    @Override
    public int getCount()
    {
        return gender_list.length;
    }

    @Override
    public Object getItem(int position)
    {
        return gender_list[position];
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
        View item_view = LayoutInflater.from(context).inflate(R.layout.adapter_gender, null, false);
        String gender = gender_list[position];

        TextView vText_aGender_Name = item_view.findViewById(R.id.vText_aGender_Name);
        vText_aGender_Name.setText(gender);

        ImageView vImage_aGender_Icon = item_view.findViewById(R.id.vImage_aGender_Icon);
        if(gender.equals("Male")) vImage_aGender_Icon.setImageResource(R.drawable.icon_male);
        else if(gender.equals("Female")) vImage_aGender_Icon.setImageResource(R.drawable.icon_female);
        else vImage_aGender_Icon.setImageResource(R.drawable.icon_gender);

        return item_view;
    }
}

package project.evermorebakery.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import project.evermorebakery.Activity.ActivityDetails;
import project.evermorebakery.Adapter.AdapterDisplay;
import project.evermorebakery.Model.ModelCategory;
import project.evermorebakery.Model.ModelProduct;
import project.evermorebakery.R;

public class FragmentMenu extends Fragment
{
    View view;
    TabLayout lTab_fMenu_Category;
    RecyclerView vRecycler_fMenu_Item;
    ArrayList<ModelCategory> category_list;
    ArrayList<ModelProduct> product_list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved_instance_state)
    {
        view = inflater.inflate(R.layout.fragment_menu, container, false);

        addControls();
        addCategory();
        addData();
        addAdapter();
        addEvents();

        return view;
    }

    void addControls()
    {
        lTab_fMenu_Category = view.findViewById(R.id.lTab_fMenu_Category);
        vRecycler_fMenu_Item = view.findViewById(R.id.vRecycler_fMenu_Item);
    }

    void addCategory()
    {
        category_list = new ArrayList<>();
        category_list.add(new ModelCategory("1", "Category 1", R.drawable.square_logo));
        category_list.add(new ModelCategory("1", "Category 1", R.drawable.square_logo));
        category_list.add(new ModelCategory("1", "Category 1", R.drawable.square_logo));
        category_list.add(new ModelCategory("1", "Category 1", R.drawable.square_logo));

        for (int index = 0; index < category_list.size(); index = index + 1)
        {
            ModelCategory category = category_list.get(index);
            TabLayout.Tab tab = lTab_fMenu_Category.newTab();
            tab.setCustomView(getTabView(category));
            lTab_fMenu_Category.addTab(tab);
        }
    }

    void addData()
    {
        product_list = new ArrayList<>();
    }

    void addAdapter()
    {
        LinearLayoutManager layout_manager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        AdapterDisplay menu_adapter = new AdapterDisplay(requireContext(), product_list);
        vRecycler_fMenu_Item.setLayoutManager(layout_manager);
        vRecycler_fMenu_Item.setAdapter(menu_adapter);

        menu_adapter.setOnItemClickListener(product ->
        {
            Intent intent = new Intent(requireContext(), ActivityDetails.class);
            intent.putExtra("product", product);
            intent.putExtra("location", "menu");
            startActivity(intent);
        });
    }

    @SuppressLint("InflateParams")
    private View getTabView(ModelCategory item)
    {
        View view = getLayoutInflater().inflate(R.layout.adapter_category, null, false);

        ImageView vImage_dCategory_Image = view.findViewById(R.id.vImage_dCategory_Image);
        vImage_dCategory_Image.setImageResource(item.getImage());

        TextView vText_dCategory_Name = view.findViewById(R.id.vText_dCategory_Name);
        vText_dCategory_Name.setText(item.getName());

        return view;
    }

    void addEvents()
    {
        lTab_fMenu_Category.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                View custom_view = tab.getCustomView();
                if (custom_view != null) custom_view.setSelected(true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {
                View custom_view = tab.getCustomView();
                if (custom_view != null) custom_view.setSelected(false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });
    }
}

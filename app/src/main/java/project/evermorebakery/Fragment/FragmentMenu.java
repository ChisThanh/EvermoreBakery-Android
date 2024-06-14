package project.evermorebakery.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import project.evermorebakery.Activity.ActivityDetails;
import project.evermorebakery.Adapter.AdapterDisplay;
import project.evermorebakery.Handler.HandlerAPI;
import project.evermorebakery.Interface.InterfaceVolleyResponseListener;
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
    HandlerAPI handler_api;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved_instance_state)
    {
        view = inflater.inflate(R.layout.fragment_menu, container, false);

        product_list = new ArrayList<>();

        addControls();
        addCategory();
        addEvents();

        fetchAPI("LM0001");

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
        category_list.add(new ModelCategory("LM0001", "Mousse", R.drawable.square_mousse));
        category_list.add(new ModelCategory("LM0002", "Cheesecake", R.drawable.square_cheesecake));
        category_list.add(new ModelCategory("LM0003", "Cupcake", R.drawable.square_cupcake));
        category_list.add(new ModelCategory("LM0004", "Tiramisu", R.drawable.square_tiramisu));
        category_list.add(new ModelCategory("LM0005", "Cake", R.drawable.square_cake));

        for (int index = 0; index < category_list.size(); index = index + 1)
        {
            ModelCategory category = category_list.get(index);
            TabLayout.Tab tab = lTab_fMenu_Category.newTab();
            tab.setCustomView(getTabView(category));
            lTab_fMenu_Category.addTab(tab);
        }
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

    /** @noinspection SpellCheckingInspection*/
    void fetchAPI(String id)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        handler_api = new HandlerAPI(requestQueue);
        String query =
                "select fm.item_id, fm.item_name, fm.price, fm.rating, fm.description, fi.image_filename " +
                "from foodmenu fm " +
                "inner join foodimage fi on fm.item_id = fi.item_id " +
                "where fm.category_id = '" + id + "' " +
                "group by fm.item_id";

        handler_api.fetchData(query, new InterfaceVolleyResponseListener()
        {
            @Override
            public void onResponse(JSONArray response)
            {
                try
                {
                    for (int index = 0; index < response.length(); index = index + 1)
                    {
                        JSONObject object = response.getJSONObject(index);
                        ModelProduct product = new ModelProduct();

                        product.setId(object.getString("item_id"));
                        product.setName(object.getString("item_name"));
                        product.setDescription(object.getString("description"));
                        product.setPrice((int) Double.parseDouble(object.getString("price")));
                        product.setRating(object.getInt("rating"));
                        product.setImage(object.getString("image_filename"));

                        product_list.add(product);
                    }

                    addAdapter();
                }
                catch (Exception exception)
                {
                    //noinspection DataFlowIssue
                    Log.e("ERROR", exception.getMessage());
                }
            }

            @Override
            public void onError(String errorMessage)
            {
                Log.e("ERROR", errorMessage);
            }
        });
    }

    void addEvents()
    {
        lTab_fMenu_Category.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                product_list.clear();
                addAdapter();

                View custom_view = tab.getCustomView();
                if (custom_view != null) custom_view.setSelected(true);

                switch(tab.getPosition())
                {
                    case 0:
                        fetchAPI("LM0001");
                        break;
                    case 1:
                        fetchAPI("LM0002");
                        break;
                    case 2:
                        fetchAPI("LM0003");
                        break;
                    case 3:
                        fetchAPI("LM0004");
                        break;
                    case 4:
                        fetchAPI("LM0005");
                        break;
                }
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

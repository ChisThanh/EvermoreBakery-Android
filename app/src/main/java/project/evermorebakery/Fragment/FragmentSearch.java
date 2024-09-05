package project.evermorebakery.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import project.evermorebakery.Activity.ActivityDetails;
import project.evermorebakery.Adapter.AdapterDisplay;
import project.evermorebakery.Handler.HandlerAPI;
import project.evermorebakery.Interface.InterfaceVolleyResponseListener;
import project.evermorebakery.Model.ModelProduct;
import project.evermorebakery.R;

public class FragmentSearch extends Fragment
{
    View view;
    RecyclerView vRecycler_fSearch_Item;
    ArrayList<ModelProduct> product_list;
    HandlerAPI handler_api;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved_instance_state)
    {
        view = inflater.inflate(R.layout.fragment_search, container, false);

        addControls();
        addData();
        addAdapter();

        return view;
    }

    void addControls()
    {
        vRecycler_fSearch_Item = view.findViewById(R.id.vRecycler_fSearch_Item);
    }

    void addData()
    {
        product_list = new ArrayList<>();

        String search = "empty";
        if(getArguments() != null) search = getArguments().getString("search");

        fetchAPI(search);
    }

    void addAdapter()
    {
        LinearLayoutManager layout_manager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        AdapterDisplay menu_adapter = new AdapterDisplay(requireContext(), product_list);
        vRecycler_fSearch_Item.setLayoutManager(layout_manager);
        vRecycler_fSearch_Item.setAdapter(menu_adapter);

        menu_adapter.setOnItemClickListener(product ->
        {
            Intent intent = new Intent(requireContext(), ActivityDetails.class);
            intent.putExtra("product", product);
            intent.putExtra("location", "search");
            startActivity(intent);
        });
    }

    /** @noinspection SpellCheckingInspection*/
    void fetchAPI(String name)
    {
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        handler_api = new HandlerAPI(requestQueue);
        String query =
                "select fm.item_id, fm.item_name, fm.price, fm.rating, fm.description, fi.image_filename " +
                "from foodmenu fm " +
                "inner join foodimage fi on fm.item_id = fi.item_id " +
                "where fm.item_name like '%" + name + "%' " +
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

                    if(product_list.isEmpty()) loadFragment(new FragmentEmpty());
                    else addAdapter();
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

    void loadFragment(Fragment fragment)
    {
        FragmentTransaction fragment_transaction = getParentFragmentManager().beginTransaction();
        fragment_transaction.replace(R.id.lFrame_aMain_Layout, fragment);
        fragment_transaction.commit();
    }
}

package project.evermorebakery.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import project.evermorebakery.Activity.ActivityDetails;
import project.evermorebakery.Adapter.AdapterBanner;
import project.evermorebakery.Adapter.AdapterHome;
import project.evermorebakery.Custom.CustomGridSpacingItemDecoration;
import project.evermorebakery.Handler.HandlerAPI;
import project.evermorebakery.Interface.InterfaceVolleyResponseListener;
import project.evermorebakery.Model.ModelProduct;
import project.evermorebakery.R;

public class FragmentHome extends Fragment
{
    View view;
    RecyclerView vRecycler_fHome_Banner;
    RecyclerView vRecycler_fHome_Revisit;
    RecyclerView vRecycler_fHome_Recommendation;
    RecyclerView vRecycler_fHome_Delights;
    static ArrayList<ModelProduct> revisit_list;
    static ArrayList<ModelProduct> recommendation_list;
    static ArrayList<ModelProduct> delight_list;
    HandlerAPI handler_api;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved_instance_state)
    {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        addControls();
        addData();
        addBanners();
        addEvents();

        return view;
    }

    void addControls()
    {
        vRecycler_fHome_Banner = view.findViewById(R.id.vRecycler_fHome_Banner);
        vRecycler_fHome_Revisit = view.findViewById(R.id.vRecycler_fHome_Revisit);
        vRecycler_fHome_Recommendation = view.findViewById(R.id.vRecycler_fHome_Recommendation);
        vRecycler_fHome_Delights = view.findViewById(R.id.vRecycler_fHome_Delights);
    }

    void addData()
    {
        revisit_list = new ArrayList<>();

        recommendation_list = new ArrayList<>();
        fetchRecommendation();

        delight_list = new ArrayList<>();
    }

    /** @noinspection SpellCheckingInspection*/
    void fetchRecommendation()
    {
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        handler_api = new HandlerAPI(requestQueue);
        String query =
                "select fm.item_id, fm.item_name, fm.price, fm.rating, fm.description, min(fi.image_filename) as image_filename " +
                "from foodmenu fm " +
                "left join foodimage fi on fm.item_id = fi.item_id " +
                "group by fm.item_id, fm.item_name, fm.price, fm.rating, fm.description " +
                "order by fm.rating desc, fm.item_id asc " +
                "limit 6";

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

                        recommendation_list.add(product);
                    }

                    addGridAdapter(vRecycler_fHome_Recommendation, recommendation_list);
                }
                catch (Exception exception)
                {
                    //noinspection DataFlowIssue
                    Log.e("ERROR", exception.getMessage());
                    failsafe();
                }
            }

            @Override
            public void onError(String errorMessage)
            {
                Log.e("ERROR", errorMessage);
            }
        });
    }

    void addBanners()
    {
        if(isAdded()){
            int[] banner_list = {R.drawable.landscape_banner_one, R.drawable.landscape_banner_two, R.drawable.landscape_banner_three};
            LinearLayoutManager layout_manager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
            LinearSnapHelper snap_helper = new LinearSnapHelper();
            AdapterBanner banner_adapter = new AdapterBanner(requireContext(), banner_list);
            vRecycler_fHome_Banner.setLayoutManager(layout_manager);
            vRecycler_fHome_Banner.setAdapter(banner_adapter);
            snap_helper.attachToRecyclerView(vRecycler_fHome_Banner);
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    void addLinearAdapter(RecyclerView recycler_view, ArrayList<ModelProduct> product_list)
    {
        if(isAdded()){
            LinearLayoutManager layout_manager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
            AdapterHome home_adapter = new AdapterHome(requireContext(), product_list);
            recycler_view.setLayoutManager(layout_manager);
            recycler_view.setAdapter(home_adapter);

            home_adapter.setOnItemClickListener(product ->
            {
                Intent intent = new Intent(requireContext(), ActivityDetails.class);
                intent.putExtra("product", product);
                intent.putExtra("location", "home");
                startActivity(intent);
            });
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    void addGridAdapter(RecyclerView recycler_view, ArrayList<ModelProduct> product_list)
    {
        if(isAdded()) {
            GridLayoutManager layout_manager = new GridLayoutManager(requireContext(), 2);
            AdapterHome home_adapter = new AdapterHome(requireContext(), product_list);
            recycler_view.setLayoutManager(layout_manager);
            recycler_view.addItemDecoration(new CustomGridSpacingItemDecoration(2, 0, true));
            recycler_view.setAdapter(home_adapter);

            home_adapter.setOnItemClickListener(product ->
            {
                Intent intent = new Intent(requireContext(), ActivityDetails.class);
                intent.putExtra("product", product);
                intent.putExtra("location", "home");
                startActivity(intent);
            });
        }
    }

    void addEvents()
    {

    }

    void failsafe()
    {
        ArrayList<ModelProduct> product_list = new ArrayList<>();

        product_list.add(new ModelProduct("1", "Testing #1", "HAHA", 10000, 3, "square_cake"));
        product_list.add(new ModelProduct("2", "Testing #2", "HAHA", 10000, 3, "square_cupcake"));
        product_list.add(new ModelProduct("3", "Testing #3", "HAHA", 10000, 3, "square_mousse"));
        product_list.add(new ModelProduct("4", "Testing #4", "HAHA", 10000, 3, "square_tiramisu"));
        product_list.add(new ModelProduct("5", "Testing #4", "HAHA", 10000, 3, "square_cheesecake"));

        addLinearAdapter(vRecycler_fHome_Revisit, product_list);
        addGridAdapter(vRecycler_fHome_Recommendation, product_list);
        addGridAdapter(vRecycler_fHome_Delights, product_list);
    }
}

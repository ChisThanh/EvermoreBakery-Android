package project.evermorebakery.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;

import project.evermorebakery.Activity.ActivityDetails;
import project.evermorebakery.Adapter.AdapterBanner;
import project.evermorebakery.Adapter.AdapterHome;
import project.evermorebakery.Custom.CustomGridSpacingItemDecoration;
import project.evermorebakery.Model.ModelProduct;
import project.evermorebakery.R;

public class FragmentHome extends Fragment
{
    View view;
    RecyclerView vRecycler_fHome_Banner;
    RecyclerView vRecycler_fHome_Revisit;
    RecyclerView vRecycler_fHome_Recommendation;
    RecyclerView vRecycler_fHome_Delights;
    ArrayList<ModelProduct> revisit_list;
    ArrayList<ModelProduct> recommendation_list;
    ArrayList<ModelProduct> delight_list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved_instance_state)
    {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        addControls();
        addData();
        addBanners();
        addLinearAdapter(vRecycler_fHome_Revisit, revisit_list);
        addGridAdapter(vRecycler_fHome_Recommendation, recommendation_list);
        addGridAdapter(vRecycler_fHome_Delights, delight_list);
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
        revisit_list.add(new ModelProduct("square_placeholder", "Placeholder", 10000, 5));
        revisit_list.add(new ModelProduct("square_error", "Error", 10000, 5));
        revisit_list.add(new ModelProduct("square_placeholder", "Placeholder", 10000, 5));
        revisit_list.add(new ModelProduct("square_error", "Error", 10000, 5));

        recommendation_list = new ArrayList<>();
        recommendation_list.add(new ModelProduct("square_placeholder", "Placeholder", 10000, 5));
        recommendation_list.add(new ModelProduct("square_error", "Error", 10000, 5));
        recommendation_list.add(new ModelProduct("square_placeholder", "Placeholder", 10000, 5));
        recommendation_list.add(new ModelProduct("square_error", "Error", 10000, 5));
        recommendation_list.add(new ModelProduct("square_placeholder", "Placeholder", 10000, 5));
        recommendation_list.add(new ModelProduct("square_error", "Error", 10000, 5));

        delight_list = new ArrayList<>();
        delight_list.add(new ModelProduct("square_placeholder", "Placeholder", 10000, 5));
    }

    void addBanners()
    {
        int[] banner_list = {R.drawable.landscape_banner_opening, R.drawable.landscape_banner_opening, R.drawable.landscape_banner_opening};
        LinearLayoutManager layout_manager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        LinearSnapHelper snap_helper = new LinearSnapHelper();
        AdapterBanner banner_adapter = new AdapterBanner(requireContext(), banner_list);
        vRecycler_fHome_Banner.setLayoutManager(layout_manager);
        vRecycler_fHome_Banner.setAdapter(banner_adapter);
        snap_helper.attachToRecyclerView(vRecycler_fHome_Banner);
    }

    @SuppressLint("ClickableViewAccessibility")
    void addLinearAdapter(RecyclerView recycler_view, ArrayList<ModelProduct> product_list)
    {
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

    @SuppressLint("ClickableViewAccessibility")
    void addGridAdapter(RecyclerView recycler_view, ArrayList<ModelProduct> product_list)
    {
        GridLayoutManager layout_manager = new GridLayoutManager(requireContext(), 2);
        AdapterHome home_adapter = new AdapterHome(requireContext(), product_list);
        recycler_view.setLayoutManager(layout_manager);
        recycler_view.addItemDecoration(new CustomGridSpacingItemDecoration(2, 10, true));
        recycler_view.setAdapter(home_adapter);

        home_adapter.setOnItemClickListener(product ->
        {
            Intent intent = new Intent(requireContext(), ActivityDetails.class);
            startActivity(intent);
        });
    }

    void addEvents()
    {

    }
}

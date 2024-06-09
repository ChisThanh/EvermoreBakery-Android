package project.evermorebakery.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import project.evermorebakery.Activity.ActivityDetails;
import project.evermorebakery.Adapter.AdapterDisplay;
import project.evermorebakery.Model.ModelProduct;
import project.evermorebakery.R;

public class FragmentSearch extends Fragment
{
    View view;
    RecyclerView vRecycler_fSearch_Item;
    ArrayList<ModelProduct> product_list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle saved_instance_state)
    {
        view = inflater.inflate(R.layout.fragment_search, container, false);

        addControls();
        addData();
        addAdapter();
        addEvents();

        return view;
    }

    void addControls()
    {
        vRecycler_fSearch_Item = view.findViewById(R.id.vRecycler_fSearch_Item);
    }

    void addData()
    {
        product_list = new ArrayList<>();
        product_list.add(new ModelProduct("square_error", "Product 1", 10000, 5));
        product_list.add(new ModelProduct("square_error", "Product 1", 10000, 3));
        product_list.add(new ModelProduct("square_error", "Product 1", 10000, 4));
        product_list.add(new ModelProduct("square_error", "Product 1", 10000, 3));
        product_list.add(new ModelProduct("square_error", "Product 1", 10000, 2));
        product_list.add(new ModelProduct("square_error", "Product 1", 10000, 1));
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
            startActivity(intent);
        });
    }

    void addEvents()
    {

    }
}

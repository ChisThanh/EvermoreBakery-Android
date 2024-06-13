package project.evermorebakery.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import project.evermorebakery.Adapter.AdapterComment;
import project.evermorebakery.Custom.CustomVerticalSpacingItemDecoration;
import project.evermorebakery.Model.ModelComment;
import project.evermorebakery.Model.ModelProduct;
import project.evermorebakery.R;

public class FragmentComment extends Fragment
{
    View view;
    TextView vText_fComment_Name;
    TextView vText_fComment_Flavor;
    TextView vText_fComment_Rating;
    TextView vText_fComment_Comment;
    ImageView vImage_fComment_Details;
    RatingBar uRating_dComment_New;
    AppCompatButton uButton_fComment_New;
    RecyclerView vRecycler_fComment_Comment;
    ArrayList<ModelComment> comment_list;
    ModelProduct product;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved_instance_state)
    {
        view = inflater.inflate(R.layout.fragment_comment, container, false);

        addControls();
        getData();
        addComment();
        addAdapter();
        addEvents();

        return view;
    }

    void addControls()
    {
        vText_fComment_Name = view.findViewById(R.id.vText_fComment_Name);
        vText_fComment_Flavor = view.findViewById(R.id.vText_fComment_Flavor);
        vText_fComment_Rating = view.findViewById(R.id.vText_fComment_Rating);
        vText_fComment_Comment = view.findViewById(R.id.vText_fComment_Comment);
        vImage_fComment_Details = view.findViewById(R.id.vImage_fComment_Details);
        uRating_dComment_New = view.findViewById(R.id.uRating_dComment_New);
        uButton_fComment_New = view.findViewById(R.id.uButton_fComment_New);
        vRecycler_fComment_Comment = view.findViewById(R.id.vRecycler_fComment_Comment);
    }

    void getData()
    {
        if(getArguments() != null) product = (ModelProduct) getArguments().getSerializable("product");

        if(product != null)
        {
            vText_fComment_Name.setText(product.getName());
            vText_fComment_Rating.setText(String.valueOf(product.getRating()));
        }
    }

    void addComment()
    {
        comment_list = new ArrayList<>();

        comment_list.add(new ModelComment("Account 1", "Very Good!", 4.5F));
        comment_list.add(new ModelComment("Account 2", "Delicious!", 2.5F));
        comment_list.add(new ModelComment("Account 3", "Very Bad!", 1F));
        comment_list.add(new ModelComment("Account 4", "Taste Like Heaven!", 5F));
    }

    void addAdapter()
    {
        LinearLayoutManager layout_manager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        AdapterComment comment_adapter = new AdapterComment(requireContext(), comment_list);
        vRecycler_fComment_Comment.setLayoutManager(layout_manager);
        vRecycler_fComment_Comment.addItemDecoration(new CustomVerticalSpacingItemDecoration(25));
        vRecycler_fComment_Comment.setAdapter(comment_adapter);
    }

    void addEvents()
    {
        vImage_fComment_Details.setOnClickListener(view -> sendData());
    }

    void sendData()
    {
        FragmentDetails fragment = new FragmentDetails();
        Bundle bundle = new Bundle();
        bundle.putSerializable("product", product);
        fragment.setArguments(bundle);
        loadFragment(fragment);
    }

    void loadFragment(Fragment fragment)
    {
        FragmentTransaction fragment_transaction = getParentFragmentManager().beginTransaction();
        fragment_transaction.replace(R.id.lFrame_aDetails_Layout, fragment);
        fragment_transaction.commit();
    }
}
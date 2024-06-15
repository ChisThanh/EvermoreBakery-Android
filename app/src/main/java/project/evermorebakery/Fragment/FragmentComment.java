package project.evermorebakery.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import project.evermorebakery.Adapter.AdapterComment;
import project.evermorebakery.Custom.CustomVerticalSpacingItemDecoration;
import project.evermorebakery.Handler.HandlerAPI;
import project.evermorebakery.Interface.InterfaceVolleyResponseListener;
import project.evermorebakery.Manager.ManagerProfile;
import project.evermorebakery.Model.ModelComment;
import project.evermorebakery.Model.ModelProduct;
import project.evermorebakery.R;

public class FragmentComment extends Fragment
{
    View view;
    TextView vText_fComment_Name;
    TextView vText_fComment_Rating;
    TextView vText_fComment_Comment;
    ImageView vImage_fComment_Details;
    RatingBar uRating_dComment_New;
    AppCompatButton uButton_fComment_New;
    RecyclerView vRecycler_fComment_Comment;
    ArrayList<ModelComment> comment_list;
    ModelProduct product;
    HandlerAPI handler_api;


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
    void fetchComment()
    {
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        handler_api = new HandlerAPI(requestQueue);
        String query =
                "select distinct " +
                        "    r.review_id," +
                        "    r.customer_id," +
                        "    c.customer_name, " +
                        "    r.item_id, " +
                        "    r.content, " +
                        "    r.rating as review_rating, " +
                        "    f.item_name " +
                        "from" +
                        "    review r " +
                        "join" +
                        "    foodmenu f on r.item_id = f.item_id "  +
                        "join " +
                        "    customer c on r.customer_id = c.customer_id where r.item_id = '" + product.getId() + "'";

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
                        ModelComment comment = new ModelComment(
                                object.getString("review_id"),
                                object.getString("customer_id"),
                                object.getString("customer_name"),
                                object.getString("item_id"),
                                object.getString("content"),
                                object.getInt("review_rating"),
                                object.getString("item_name")
                        );
                        comment_list.add(comment);
                    }

                    addAdapter();
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
    void addCommentNew()
    {
        String customerId = "KH0001";
        String itemId = product.getId();
        String content = vText_fComment_Name.getText().toString();
        int reviewRating = (int) uRating_dComment_New.getRating();
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        handler_api = new HandlerAPI(requestQueue);
        String query = "INSERT INTO REVIEW ( CUSTOMER_ID, ITEM_ID, CONTENT, RATING) VALUES ('"
                + customerId + "', '"
                + itemId + "', '"
                + content + "', "
                + reviewRating + ");";

        handler_api.updateData(query, new InterfaceVolleyResponseListener()
        {
            @Override
            public void onResponse(JSONArray response)
            {
                try {
                    JSONObject object = response.getJSONObject(0);
                    boolean success = object.getBoolean("success");
                    if (success) {
                        Toast.makeText(requireContext(), "Comment Successful", Toast.LENGTH_SHORT).show();
                        //loadFragment(); load fragment giao dịch thành công
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onError(String errorMessage)
            {
                Log.e("ERROR", errorMessage);
            }
        });
    }
    void addComment()
    {
        comment_list = new ArrayList<>();
        fetchComment();
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

        uButton_fComment_New.setOnClickListener(view -> {
            if (uRating_dComment_New.getRating() > 0) {

            }
        });
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
    void failsafe()
    {
        comment_list = new ArrayList<>();

        comment_list.add(new ModelComment("Account 1", "Very Good!", 4));
        comment_list.add(new ModelComment("Account 2", "Delicious!", 2));
        comment_list.add(new ModelComment("Account 3", "Very Bad!", 1));
        comment_list.add(new ModelComment("Account 4", "Taste Like Heaven!", 3));

       addAdapter();
    }
}
package project.evermorebakery.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import project.evermorebakery.R;

public class FragmentDetails extends Fragment
{
    View view;
    TextView vText_fDetails_Name;
    TextView vText_fDetails_Flavor;
    TextView vText_fDetails_Rating;
    TextView vText_fDetails_Comment;
    TextView vText_fDetails_Description;
    TextView vText_fDetails_TotalPrice;
    TextView vText_fDetails_Quantity;
    ImageView vImage_fDetails_Comment;
    Button uButton_fDetails_Decrease;
    Button uButton_fDetails_Increase;
    Button uButton_fDetails_Cart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle saved_instance_state)
    {
        view = inflater.inflate(R.layout.fragment_details, container, false);

        addControls();
        addEvents();

        return view;
    }

    void addControls()
    {
        vText_fDetails_Name = view.findViewById(R.id.vText_fDetails_Name);
        vText_fDetails_Flavor = view.findViewById(R.id.vText_fDetails_Flavor);
        vText_fDetails_Rating = view.findViewById(R.id.vText_fDetails_Rating);
        vText_fDetails_Comment = view.findViewById(R.id.vText_fDetails_Comment);
        vText_fDetails_Description = view.findViewById(R.id.vText_fDetails_Description);
        vText_fDetails_TotalPrice = view.findViewById(R.id.vText_fDetails_TotalPrice);
        vText_fDetails_Quantity = view.findViewById(R.id.vText_fDetails_Quantity);
        vImage_fDetails_Comment = view.findViewById(R.id.vImage_fDetails_Comment);
        uButton_fDetails_Decrease = view.findViewById(R.id.uButton_fDetails_Decrease);
        uButton_fDetails_Increase = view.findViewById(R.id.uButton_fDetails_Increase);
        uButton_fDetails_Cart = view.findViewById(R.id.uButton_fDetails_Cart);
    }

    void addEvents()
    {
        vImage_fDetails_Comment.setOnClickListener(view -> loadFragment(new FragmentComment()));
    }

    void loadFragment(Fragment fragment)
    {
        FragmentTransaction fragment_transaction = getParentFragmentManager().beginTransaction();
        fragment_transaction.replace(R.id.lFrame_aDetails_Layout, fragment);
        fragment_transaction.commit();
    }
}

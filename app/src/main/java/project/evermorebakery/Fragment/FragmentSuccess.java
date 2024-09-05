package project.evermorebakery.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import project.evermorebakery.R;

public class FragmentSuccess extends Fragment
{
    View view;
    AppCompatButton uButton_fSuccess_Track;
    AppCompatButton uButton_fSuccess_Continue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved_instance_state)
    {
        view = inflater.inflate(R.layout.fragment_success, container, false);

        uButton_fSuccess_Track = view.findViewById(R.id.uButton_fSuccess_Track);
        uButton_fSuccess_Continue = view.findViewById(R.id.uButton_fSuccess_Continue);

        uButton_fSuccess_Continue.setOnClickListener(view -> loadFragment(new FragmentHome()));
        uButton_fSuccess_Track.setOnClickListener(view -> loadFragment(new FragmentDelivery()));

        return view;
    }

    void loadFragment(Fragment fragment)
    {
        FragmentTransaction fragment_transaction = getParentFragmentManager().beginTransaction();
        fragment_transaction.replace(R.id.lFrame_aMain_Layout, fragment);
        fragment_transaction.commit();
    }
}

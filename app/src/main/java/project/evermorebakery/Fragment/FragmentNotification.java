package project.evermorebakery.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

import project.evermorebakery.Manager.ManagerNotification;
import project.evermorebakery.Model.ModelNotification;
import project.evermorebakery.R;

public class FragmentNotification extends Fragment
{
    View view;
    ArrayList<ModelNotification> notification_list;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saved_instance_state)
    {
        notification_list = ManagerNotification.getInstance().getNotificationList();
        if(notification_list.isEmpty())
        {
            loadFragment(new FragmentEmpty());
            return null;
        }
        //else view = inflater.inflate(R.layout.fragment_notification, container, false);

        return view;
    }

    public void loadFragment(Fragment fragment)
    {
        FragmentTransaction fragment_transaction = getParentFragmentManager().beginTransaction();
        fragment_transaction.replace(R.id.lFrame_aMain_Layout, fragment);
        fragment_transaction.commit();
    }
}

package project.evermorebakery.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import project.evermorebakery.Adapter.AdapterNotification;
import project.evermorebakery.Custom.CustomVerticalSpacingItemDecoration;
import project.evermorebakery.Manager.ManagerNotification;
import project.evermorebakery.Model.ModelNotification;
import project.evermorebakery.R;

public class FragmentNotification extends Fragment
{
    View view;
    RecyclerView vRecycler_fNotification_Notification;
    AppCompatButton uButton_fNotification_Clear;
    AppCompatButton uButton_fNotification_Delivery;
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
        else view = inflater.inflate(R.layout.fragment_notification, container, false);

        addControls();
        addAdapter();

        uButton_fNotification_Delivery.setOnClickListener(view -> loadFragment(new FragmentDelivery()));

        return view;
    }

    void addControls()
    {
        vRecycler_fNotification_Notification = view.findViewById(R.id.vRecycler_fNotification_Notification);
        uButton_fNotification_Delivery = view.findViewById(R.id.uButton_fNotification_Delivery);
        uButton_fNotification_Clear = view.findViewById(R.id.uButton_fNotification_Clear);
    }

    void addAdapter()
    {
        LinearLayoutManager layout_manager = new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false);
        AdapterNotification notification_adapter = new AdapterNotification(requireContext(), notification_list);
        vRecycler_fNotification_Notification.setLayoutManager(layout_manager);
        vRecycler_fNotification_Notification.addItemDecoration(new CustomVerticalSpacingItemDecoration(25));
        vRecycler_fNotification_Notification.setAdapter(notification_adapter);

        ItemTouchHelper item_touch_helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT)
        {
            @Override
            public boolean onMove(@NonNull RecyclerView recycler_view, @NonNull RecyclerView.ViewHolder holder, @NonNull RecyclerView.ViewHolder target)
            {
                return false;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder holder, int direction)
            {
                int position = holder.getAdapterPosition();
                notification_adapter.removeItem(position);

                if(ManagerNotification.getInstance().isNotificationEmpty()) loadFragment(new FragmentEmpty());
            }
        });

        item_touch_helper.attachToRecyclerView(vRecycler_fNotification_Notification);
    }

    void loadFragment(Fragment fragment)
    {
        FragmentTransaction fragment_transaction = getParentFragmentManager().beginTransaction();
        fragment_transaction.replace(R.id.lFrame_aMain_Layout, fragment);
        fragment_transaction.commit();
    }
}

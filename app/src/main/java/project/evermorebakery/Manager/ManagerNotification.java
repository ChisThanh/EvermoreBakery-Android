package project.evermorebakery.Manager;

import java.util.ArrayList;

import project.evermorebakery.Model.ModelCart;
import project.evermorebakery.Model.ModelNotification;

public class ManagerNotification
{
    private static ManagerNotification instance;
    private final ArrayList<ModelNotification> notification_list;
    private final ArrayList<ManagerNotification.ListenerNotification> listener_list;

    public ManagerNotification()
    {
        notification_list = new ArrayList<>();
        listener_list = new ArrayList<>();
    }

    public static synchronized ManagerNotification getInstance()
    {
        if(instance == null) instance = new ManagerNotification();
        return instance;
    }

    public ArrayList<ModelNotification> getNotificationList()
    {
        notifyListeners();
        return notification_list;
    }

    public void addNotification(ModelNotification notification)
    {
        notification_list.add(notification);
        notifyListeners();
    }

    public void clearNotification()
    {
        notification_list.clear();
        notifyListeners();
    }

    public int getQuantity()
    {
        return notification_list.size();
    }

    public boolean isNotificationEmpty()
    {
        notifyListeners();
        return notification_list.isEmpty();
    }

    public void addListener(ManagerNotification.ListenerNotification listener)
    {
        listener_list.add(listener);
    }

    private void notifyListeners()
    {
        for (ManagerNotification.ListenerNotification listener : listener_list)
            listener.onNotificationUpdated(getQuantity());
    }

    public interface ListenerNotification
    {
        void onNotificationUpdated(int count);
    }
}

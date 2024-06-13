package project.evermorebakery.Manager;

import java.util.ArrayList;

import project.evermorebakery.Model.ModelCart;
import project.evermorebakery.Model.ModelNotification;

public class ManagerNotification
{
    private static ManagerNotification instance;
    private final ArrayList<ModelNotification> notification_list;

    public ManagerNotification()
    {
        notification_list = new ArrayList<>();
    }

    public static synchronized ManagerNotification getInstance()
    {
        if(instance == null) instance = new ManagerNotification();
        return instance;
    }

    public void addNotification(ModelNotification notification)
    {
        notification_list.add(notification);
    }

    public void clearNotification()
    {
        notification_list.clear();
    }

    public int getQuantity()
    {
        return notification_list.size();
    }

    public boolean isNotificationEmpty()
    {
        return notification_list.isEmpty();
    }

    public ArrayList<ModelNotification> getNotificationList()
    {
        return notification_list;
    }
}

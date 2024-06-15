package project.evermorebakery.Manager;

import project.evermorebakery.Model.ModelDelivery;

public class ManagerDelivery
{
    private static ModelDelivery instance;

    public ManagerDelivery()
    {
        instance = new ModelDelivery();
    }

    public static synchronized ModelDelivery getInstance()
    {
        if(instance == null) instance = new ModelDelivery();
        return instance;
    }

    public ModelDelivery getDeliveryData()
    {
        return instance;
    }
    public static void setDeliveryData(ModelDelivery delivery)
    {
        instance = delivery;
    }

}

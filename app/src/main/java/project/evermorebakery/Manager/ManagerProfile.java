package project.evermorebakery.Manager;

import project.evermorebakery.Model.ModelProfile;

public class ManagerProfile
{
    private static ManagerProfile instance;
    private final ModelProfile account;

    private ManagerProfile()
    {
        account = new ModelProfile();
    }

    public static synchronized ManagerProfile getInstance()
    {
        if(instance == null) instance = new ManagerProfile();
        return instance;
    }

    public ModelProfile getAccount()
    {
        return account;
    }
}

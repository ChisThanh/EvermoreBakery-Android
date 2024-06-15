package project.evermorebakery.Manager;

import project.evermorebakery.Model.ModelAccount;

public class ManagerAccount
{
    private static ManagerAccount instance;
    private ModelAccount account;

    private ManagerAccount()
    {
        account = new ModelAccount();
    }

    public static synchronized ManagerAccount getInstance()
    {
        if(instance == null) instance = new ManagerAccount();
        return instance;
    }

    public ModelAccount getAccount()
    {
        return account;
    }

    public void setAccount(ModelAccount account)
    {
        this.account = account;
    }
}

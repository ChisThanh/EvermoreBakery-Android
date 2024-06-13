package project.evermorebakery.Manager;

import java.util.ArrayList;

import project.evermorebakery.Model.ModelCart;

public class ManagerCart
{
    private static ManagerCart instance;
    private final ArrayList<ModelCart> cart_list;

    public ManagerCart()
    {
        cart_list = new ArrayList<>();
    }

    public static synchronized ManagerCart getInstance()
    {
        if(instance == null) instance = new ManagerCart();
        return instance;
    }

    public void addCart(ModelCart cart)
    {
        cart_list.add(cart);
    }

    public int getTotal()
    {
        int total = 0;
        for(ModelCart cart : cart_list) total = total + cart.getPrice();
        return total;
    }

    public int getQuantity()
    {
        return cart_list.size();
    }

    public boolean isCartEmpty()
    {
        return cart_list.isEmpty();
    }

    public ArrayList<ModelCart> getCartList()
    {
        return cart_list;
    }
}

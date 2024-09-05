package project.evermorebakery.Manager;

import java.util.ArrayList;

import project.evermorebakery.Model.ModelCart;

public class ManagerCart
{
    private static ManagerCart instance;
    private final ArrayList<ModelCart> cart_list;
    private final ArrayList<ListenerCart> listener_list;

    public ManagerCart()
    {
        cart_list = new ArrayList<>();
        listener_list = new ArrayList<>();
    }

    public static synchronized ManagerCart getInstance()
    {
        if(instance == null) instance = new ManagerCart();
        return instance;
    }

    public ArrayList<ModelCart> getCartList()
    {
        notifyListeners();
        return cart_list;
    }

    public void addCart(ModelCart cart)
    {
        cart_list.add(cart);
        notifyListeners();
    }

    public void clearCart()
    {
        cart_list.clear();
        notifyListeners();
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
        notifyListeners();
        return cart_list.isEmpty();
    }

    public void addListener(ListenerCart listener)
    {
        listener_list.add(listener);
    }

    private void notifyListeners()
    {
        for (ListenerCart listener : listener_list) listener.onCartUpdated(getQuantity());
    }

    public interface ListenerCart
    {
        void onCartUpdated(int count);
    }
}

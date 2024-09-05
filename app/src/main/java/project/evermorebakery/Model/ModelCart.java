package project.evermorebakery.Model;

import java.io.Serializable;

public class ModelCart implements Serializable
{
    private ModelProduct product;
    private int quantity;
    private int price;

    public ModelProduct getProduct()
    {
        return product;
    }

    public void setProduct(ModelProduct product)
    {
        this.product = product;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public ModelCart()
    {

    }

    public ModelCart(ModelProduct product, int quantity, int price)
    {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

}

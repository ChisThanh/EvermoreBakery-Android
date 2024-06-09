package project.evermorebakery.Model;

public class ModelProduct
{
    private String id;
    private String name;
    private String image;
    private Integer price;
    private Integer rating;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public Integer getPrice()
    {
        return price;
    }

    public void setPrice(Integer price)
    {
        this.price = price;
    }

    public Integer getRating()
    {
        return rating;
    }

    public void setRating(Integer rating)
    {
        this.rating = rating;
    }


    public ModelProduct()
    {
        this.id = "";
        this.image = "";
        this.name = "";
        this.price = 0;
        this.rating = 0;
    }

    public ModelProduct(String id, String image, String name, Integer price, Integer rating)
    {
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public ModelProduct(String image, String name, Integer price, Integer rating)
    {
        this.image = image;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }


}

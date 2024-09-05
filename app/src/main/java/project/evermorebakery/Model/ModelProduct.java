package project.evermorebakery.Model;

import java.io.Serializable;

public class ModelProduct implements Serializable
{
    private String id;
    private String name;
    private String description;
    private Integer price;
    private Integer rating;
    private String image;

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

    public String getShortName()
    {
        if(name.contains("Mousse")) return "Mousse";
        else if(name.contains("Cheesecake")) return "Cheesecake";
        else if(name.contains("Tiramisu")) return "Tiramisu";
        else if(name.contains("Cupcake ")) return "Cupcake";
        else return "Cake";
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
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

    public String getImage()
    {
        String[] imageArray = image.split("\\.");
        return imageArray[0];
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public ModelProduct()
    {

    }

    public ModelProduct(String id, String name, String description, Integer price, Integer rating, String image)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.rating = rating;
        this.image = image;
    }
}

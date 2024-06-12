package project.evermorebakery.Model;

public class ModelComment
{
    private String account;
    private String comment;
    private Float rating;

    public ModelComment(String account, String comment, Float rating)
    {
        this.account = account;
        this.comment = comment;
        this.rating = rating;
    }

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public Float getRating()
    {
        return rating;
    }

    public void setRating(Float rating)
    {
        this.rating = rating;
    }
}

package project.evermorebakery.Model;

public class ModelComment
{
    private String account;
    private String comment;
    private Integer rating;

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

    public Integer getRating()
    {
        return rating;
    }

    public void setRating(Integer rating)
    {
        this.rating = rating;
    }

    public ModelComment(String account, String comment, Integer rating)
    {
        this.account = account;
        this.comment = comment;
        this.rating = rating;
    }
}

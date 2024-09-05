package project.evermorebakery.Model;

import java.io.Serializable;

public class ModelNotification implements Serializable
{
    private String type;
    private String title;
    private String content;

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public ModelNotification(String type, String title, String content)
    {
        this.type = type;
        this.title = title;
        this.content = content;
    }
}

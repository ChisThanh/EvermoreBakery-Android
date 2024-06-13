package project.evermorebakery.Model;

import java.io.Serializable;

public class ModelNotification implements Serializable
{
    private String type;
    private String content;

    public ModelNotification()
    {

    }

    public ModelNotification(String type, String content)
    {
        this.type = type;
        this.content = content;
    }

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
}

package project.evermorebakery.Model;

import java.util.Date;

public class ModelDelivery {
    String id;
    double total;
    Date date;

    public ModelDelivery() {
    }

    public ModelDelivery(String id, double total, Date date) {
        this.id = id;
        this.total = total;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

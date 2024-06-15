package project.evermorebakery.Manager;

import java.util.Date;

public class  ManagerDelivery
{
    static  String _id;
    static String _total;
    static String _status;
    static Date _date;

    public ManagerDelivery(String id, String total, String status, Date date) {
        _id = id;
        _total = total;
        _status = status;
        _date = date;
    }

    public static String get_id() {
        return _id;
    }

    public static void set_id(String _id) {
        ManagerDelivery._id = _id;
    }

    public static String get_total() {
        return _total;
    }

    public static void set_total(String _total) {
        ManagerDelivery._total = _total;
    }

    public static String get_status() {
        return _status;
    }

    public static void set_status(String _status) {
        ManagerDelivery._status = _status;
    }

    public static Date get_date() {
        return _date;
    }

    public static void set_date(Date _date) {
        ManagerDelivery._date = _date;
    }
}

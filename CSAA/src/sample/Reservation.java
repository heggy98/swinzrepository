package sample;

import javafx.fxml.Initializable;

import java.util.Date;

public class Reservation {
    int id;
    String name;
    String phone;
    String spz;
    String time;
    Date date;

    public Reservation(int id, String name, String phone, String spz, String time, Date date) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.spz = spz;
        this.time = time;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSpz() {
        return spz;
    }

    public void setSpz(String spz) {
        this.spz = spz;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
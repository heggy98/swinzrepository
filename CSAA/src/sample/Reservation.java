package sample;

import javafx.fxml.Initializable;

import java.util.Date;

public class Reservation {
    private int id;
    private String name;
    private String phone;
    private String spz;
    private Integer timeIndex;
    private Date date;

    @Override
    public String toString() {
        return "Reservation{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", spz='" + spz + '\'' +
                ", timeIndex=" + timeIndex +
                ", date=" + date +
                '}';
    }

    public Reservation(int id, String name, String phone, String spz, Date date, int timeIndex) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.spz = spz;
        this.timeIndex = timeIndex;
        this.date = date;
    }

    public Reservation(String name, String phone, String spz, Date date, int timeIndex) {
        this.name = name;
        this.phone = phone;
        this.spz = spz;
        this.timeIndex = timeIndex;
        this.date = date;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getSpz() {
        return spz;
    }

    public Integer getTimeIndex() {
        return timeIndex;
    }

    public Date getDate() {
        return date;
    }
}
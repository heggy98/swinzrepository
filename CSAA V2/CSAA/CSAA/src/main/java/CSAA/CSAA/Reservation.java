package CSAA.CSAA;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Reservation {
    private @Id @GeneratedValue Long id;
    public String name;
    public String phone;
    public String spz;
    public Integer timeIndex;
    public String time;
    public Date date;

    public Reservation() {
    }

    public Reservation(String name, String phone, String spz, Date date, int timeIndex) {
        this.name = name;
        this.phone = phone;
        this.spz = spz;
        this.timeIndex = timeIndex;
        this.date = date;
        this.time = TimeIndex.getByIndex(timeIndex);
    }

    public Reservation(long id, String name, String phone, String spz, Date date, int timeIndex) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.spz = spz;
        this.timeIndex = timeIndex;
        this.date = date;
        this.time = TimeIndex.getByIndex(timeIndex);
    }

    @Override
    public String toString() {

        var dateString = date.toString();
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            dateString = format.format(date);
        }
        catch (Exception ex){

        }

        return "Reservation{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", spz='" + spz + '\'' +
                ", timeIndex=" + time +
                ", date=" + dateString +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
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

    public Integer getTimeIndex() {
        return timeIndex;
    }

    public void setTimeIndex(Integer timeIndex) {
        this.timeIndex = timeIndex;
        this.time = TimeIndex.getByIndex(timeIndex);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
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
    public Date date;

    public Reservation() {
    }

    public Reservation(String name, String phone, String spz, Date date, int timeIndex) {
        this.name = name;
        this.phone = phone;
        this.spz = spz;
        this.timeIndex = timeIndex;
        this.date = date;
    }

    @Override
    public String toString() {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        String dateString = format.format(date);

        return "Reservation{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", spz='" + spz + '\'' +
                ", timeIndex=" + timeIndex +
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
}
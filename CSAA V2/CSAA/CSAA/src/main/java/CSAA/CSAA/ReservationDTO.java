package CSAA.CSAA;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ReservationDTO {
    private @Id
    @GeneratedValue
    Long id;
    public String name;
    public String phone;
    public String spz;
    public String time;
    public String date;

    public ReservationDTO() {
    }

    public ReservationDTO(Long id, String name, String phone, String spz, String date, String time) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.spz = spz;
        this.date = date;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", spz='" + spz + '\'' +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}

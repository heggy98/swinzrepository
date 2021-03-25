package sample;

public class ReservationDTO {
    private int id;
    private String name;
    private String phone;
    private String spz;
    private String time;
    private String date;

    public ReservationDTO(int id, String name, String phone, String spz, String date, String time) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.spz = spz;
        this.date = date;
        this.time = time;
    }

    public ReservationDTO(String name, String phone, String spz, String date, String time) {
        this.name = name;
        this.phone = phone;
        this.spz = spz;
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "ReservationDTO{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", spz='" + spz + '\'' +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                '}';
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

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }
}

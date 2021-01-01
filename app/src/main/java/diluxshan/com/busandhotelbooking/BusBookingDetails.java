package diluxshan.com.busandhotelbooking;

public class BusBookingDetails {
    private Integer id;
    private String Busid;
    private String User;
    private String bus_from;
    private String bus_to;
    private String bus_time;
    private String bus_Tprice;
    private String bus_Seats;
    private String bus_date;

    public BusBookingDetails(Integer id, String busid, String user, String bus_from, String bus_to, String bus_time, String bus_Tprice, String bus_Seats, String bus_date) {
        this.id = id;
        Busid = busid;
        User = user;
        this.bus_from = bus_from;
        this.bus_to = bus_to;
        this.bus_time = bus_time;
        this.bus_Tprice = bus_Tprice;
        this.bus_Seats = bus_Seats;
        this.bus_date = bus_date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBusid() {
        return Busid;
    }

    public void setBusid(String busid) {
        Busid = busid;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getBus_from() {
        return bus_from;
    }

    public void setBus_from(String bus_from) {
        this.bus_from = bus_from;
    }

    public String getBus_to() {
        return bus_to;
    }

    public void setBus_to(String bus_to) {
        this.bus_to = bus_to;
    }

    public String getBus_time() {
        return bus_time;
    }

    public void setBus_time(String bus_time) {
        this.bus_time = bus_time;
    }

    public String getBus_Tprice() {
        return bus_Tprice;
    }

    public void setBus_Tprice(String bus_Tprice) {
        this.bus_Tprice = bus_Tprice;
    }

    public String getBus_Seats() {
        return bus_Seats;
    }

    public void setBus_Seats(String bus_Seats) {
        this.bus_Seats = bus_Seats;
    }

    public String getBus_date() {
        return bus_date;
    }

    public void setBus_date(String bus_date) {
        this.bus_date = bus_date;
    }
}

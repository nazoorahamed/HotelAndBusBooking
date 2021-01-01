package diluxshan.com.busandhotelbooking;

public class BusDetails {
    private Integer id;
    private String bus_from;
    private String bus_to;
    private String bus_time;
    private String bus_Tprice;
    private String bus_Seats;

    public BusDetails(Integer id, String bus_from, String bus_to, String bus_time, String bus_Tprice, String bus_Seats) {
        this.id = id;
        this.bus_from = bus_from;
        this.bus_to = bus_to;
        this.bus_time = bus_time;
        this.bus_Tprice = bus_Tprice;
        this.bus_Seats = bus_Seats;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

}

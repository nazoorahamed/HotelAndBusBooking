package diluxshan.com.busandhotelbooking;

public class HotelDetails {

    private Integer id;
    private String hot_name;
    private String hot_address;
    private String hot_rooms;
    private String hot_price;
    private String hot_CheckIn;
    private String hot_CheckOut;

    public HotelDetails(Integer id, String hot_name, String hot_address, String hot_rooms, String hot_price, String hot_CheckIn, String hot_CheckOut) {
        this.id = id;
        this.hot_name = hot_name;
        this.hot_address = hot_address;
        this.hot_rooms = hot_rooms;
        this.hot_price = hot_price;
        this.hot_CheckIn = hot_CheckIn;
        this.hot_CheckOut = hot_CheckOut;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHot_name() {
        return hot_name;
    }

    public void setHot_name(String hot_name) {
        this.hot_name = hot_name;
    }

    public String getHot_address() {
        return hot_address;
    }

    public void setHot_address(String hot_address) {
        this.hot_address = hot_address;
    }

    public String getHot_rooms() {
        return hot_rooms;
    }

    public void setHot_rooms(String hot_rooms) {
        this.hot_rooms = hot_rooms;
    }

    public String getHot_price() {
        return hot_price;
    }

    public void setHot_price(String hot_price) {
        this.hot_price = hot_price;
    }

    public String getHot_CheckIn() {
        return hot_CheckIn;
    }

    public void setHot_CheckIn(String hot_CheckIn) {
        this.hot_CheckIn = hot_CheckIn;
    }

    public String getHot_CheckOut() {
        return hot_CheckOut;
    }

    public void setHot_CheckOut(String hot_CheckOut) {
        this.hot_CheckOut = hot_CheckOut;
    }
}

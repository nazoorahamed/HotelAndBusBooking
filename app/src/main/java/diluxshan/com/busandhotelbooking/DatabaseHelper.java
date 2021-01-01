package diluxshan.com.busandhotelbooking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;



public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "users.db";
    public static final String TABLE_NAME = "user_table";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";

    public static final String BUS_TABLE_NAME = "bus_table";
    public static final String HOTEL_TABLE_NAME = "hotel_table";
    public static final String HOTEL_DETAIL_TABLE_NAME = "hotel_detail_table";
    public static final String BUS_DETAIL_TABLE_NAME = "bus_detail_table";
    public static final String LOGGEDIN_USER_TABLE = "loggedin_table";


    String[] Buscolumns ={"ID","BUSFROM","BUSTO","BUSTIME","BUSTICKET","BUSSEATS"};
    String[] Hotelcolumns ={"ID","HOTELNAME","HOTELADDRESS","ROOMS","PRICE","CHECKIN","CHECKOUT"};
    String[] BusBookincolums ={"ID","BUSID","USER","BUSFROM","BUSTO","BUSTIME","BUSTICKET","BUSSEATS","DATE"};
    String[] HotelBookincolums ={"ID","HOTELID","USER","HOTELNAME","HOTELADDRESS","ROOMS","PRICE","CHECKIN","CHECKOUT"};


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT,PASSWORD TEXT)");
        sqLiteDatabase.execSQL("create table "+BUS_TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,BUSFROM TEXT,BUSTO TEXT,BUSTIME TEXT,BUSTICKET TEXT,BUSSEATS TEXT)");
        sqLiteDatabase.execSQL("create table "+HOTEL_TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,HOTELNAME TEXT,HOTELADDRESS TEXT,ROOMS TEXT,PRICE TEXT,CHECKIN TEXT,CHECKOUT TEXT)");
        sqLiteDatabase.execSQL("create table "+BUS_DETAIL_TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,BUSID TEXT,USER TEXT,BUSFROM TEXT,BUSTO TEXT,BUSTIME TEXT,PRICE TEXT,BUSSEATS TEXT,DATE TEXT)");
        sqLiteDatabase.execSQL("create table "+HOTEL_DETAIL_TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,HOTELID TEXT,USER TEXT,HOTELNAME TEXT,HOTELADDRESS TEXT,ROOMS TEXT,PRICE TEXT,CHECKIN TEXT,CHECKOUT TEXT)");
        sqLiteDatabase.execSQL("create table "+LOGGEDIN_USER_TABLE+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,LOGINUSER TEXT,USERNAME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+BUS_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+HOTEL_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+BUS_DETAIL_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+HOTEL_DETAIL_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+LOGGEDIN_USER_TABLE);
        onCreate(sqLiteDatabase);

    }

    public boolean insertData(String username,String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USERNAME,username);
        contentValues.put(PASSWORD,password);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public boolean LoggedInUser(String loggedIn,String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("LOGINUSER",loggedIn);
        contentValues.put("USERNAME",username);
        long result = db.insert(LOGGEDIN_USER_TABLE,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public String getLoggedUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM loggedin_table where LOGINUSER ='true'", null );

        res.moveToFirst();
        String user ="";


        while (res.isAfterLast() == false){
            user = res.getString(res.getColumnIndex("USERNAME"));
            res.moveToNext();
        }

        return user;
    }
    public void deleteLoggedUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + LOGGEDIN_USER_TABLE);
        db.execSQL("VACUUM");
    }

    public boolean CheckAccount(String user,String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM user_table where USERNAME ='"+user+"' and PASSWORD ='"+pass+"'", null );

        if(res.getCount()==0){
            return false;
        }else {
            return true;
        }
    }

    public  boolean AddBus(String busfrom, String busto, String busTime,String busTicket,String busSeats){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("BUSFROM",busfrom);
        contentValues.put("BUSTO",busto);
        contentValues.put("BUSTIME",busTime);
        contentValues.put("BUSTICKET",busTicket);
        contentValues.put("BUSSEATS",busSeats);
        long result = db.insert(BUS_TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public  boolean AddHotel(String hotelname, String address, String rooms,String price,String checkin,String checkout ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("HOTELNAME",hotelname);
        contentValues.put("HOTELADDRESS",address);
        contentValues.put("ROOMS",rooms);
        contentValues.put("PRICE",price);
        contentValues.put("CHECKIN",checkin);
        contentValues.put("CHECKOUT",checkout);
        long result = db.insert(HOTEL_TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }



    public  boolean AddBusBookDetails(String busid,String user,String busfrom, String busto, String busTime,String busTicket,String busSeats,String date ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("BUSID",busid);
        contentValues.put("USER",user);
        contentValues.put("BUSFROM",busfrom);
        contentValues.put("BUSTO",busto);
        contentValues.put("BUSTIME",busTime);
        contentValues.put("PRICE",busTicket);
        contentValues.put("BUSSEATS",busSeats);
        contentValues.put("DATE",date);
        long result = db.insert(BUS_DETAIL_TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public  boolean AddHotelBookDetails(String hotelid,String user,String hotelname, String address, String rooms,String price,String checkin,String checkout){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("HOTELID",hotelid);
        contentValues.put("USER",user);
        contentValues.put("HOTELNAME",hotelname);
        contentValues.put("HOTELADDRESS",address);
        contentValues.put("ROOMS",rooms);
        contentValues.put("PRICE",price);
        contentValues.put("CHECKIN",checkin);
        contentValues.put("CHECKOUT",checkout);
        long result = db.insert(HOTEL_DETAIL_TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }


    public Integer deleteBusData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(BUS_TABLE_NAME, "ID = ?",new String[] {id});

    }
    public Integer deleteHotelData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(HOTEL_TABLE_NAME, "ID = ?",new String[] {id});

    }
    public Integer deleteBusBooking (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(BUS_DETAIL_TABLE_NAME, "ID = ?",new String[] {id});

    }

    public Integer deleteHotelBooking (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(HOTEL_DETAIL_TABLE_NAME, "ID = ?",new String[] {id});

    }

    public boolean updateBus(String id,String busfrom, String busto, String busTime,String busTicket,String busSeats) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("BUSFROM",busfrom);
        contentValues.put("BUSTO",busto);
        contentValues.put("BUSTIME",busTime);
        contentValues.put("BUSTICKET",busTicket);
        contentValues.put("BUSSEATS",busSeats);
        db.update(BUS_TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public boolean updateHotel(String id,String hotelname, String address, String rooms,String price,String checkin,String checkout ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("HOTELNAME",hotelname);
        contentValues.put("HOTELADDRESS",address);
        contentValues.put("ROOMS",rooms);
        contentValues.put("PRICE",price);
        contentValues.put("CHECKIN",checkin);
        contentValues.put("CHECKOUT",checkout);
        db.update(HOTEL_TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public boolean updateBusBooking(String id,String busTicket,String busSeats) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("PRICE",busTicket);
        contentValues.put("BUSSEATS",busSeats);
        db.update(BUS_DETAIL_TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public boolean updateHotelBooking(String id, String checkout,String price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("CHECKOUT",checkout);
        contentValues.put("PRICE",price);
        db.update(HOTEL_DETAIL_TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }



    public Cursor AddBustext(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.query(BUS_TABLE_NAME,Buscolumns,"ID =?" ,new String[]{id},null,null,null);
        return  res;

    }

    public Cursor AddHoteltext(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.query(HOTEL_TABLE_NAME,Hotelcolumns,"ID =?" ,new String[]{id},null,null,null);
        return  res;

    }

    public Cursor AddBusBookingtext(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.query(BUS_DETAIL_TABLE_NAME,BusBookincolums,"ID =?" ,new String[]{id},null,null,null);
        return  res;

    }

    public Cursor AddHotelBookingtext(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.query(HOTEL_DETAIL_TABLE_NAME,HotelBookincolums,"ID =?" ,new String[]{id},null,null,null);
        return  res;

    }


    public ArrayList<BusDetails> getAllABusDetails() {
        ArrayList<BusDetails> array_list = new ArrayList<BusDetails>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM bus_table ", null );

        res.moveToFirst();

        while(res.isAfterLast() == false){
            Integer id = res.getInt(res.getColumnIndex("ID"));
            String bus_from = res.getString(res.getColumnIndex("BUSFROM"));
            String bus_to = res.getString(res.getColumnIndex("BUSTO"));
            String bus_time = res.getString(res.getColumnIndex("BUSTIME"));
            String bus_price = res.getString(res.getColumnIndex("BUSTICKET"));
            String bus_Seats = res.getString(res.getColumnIndex("BUSSEATS"));


            BusDetails busDetails = new BusDetails(id,bus_from,bus_to,bus_time,bus_price,bus_Seats);
            array_list.add(busDetails);
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<HotelDetails> getAllHotelDetails() {
        ArrayList<HotelDetails> array_list = new ArrayList<HotelDetails>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM hotel_table ", null );

        res.moveToFirst();

        while(res.isAfterLast() == false){
            Integer id = res.getInt(res.getColumnIndex("ID"));
            String hot_name = res.getString(res.getColumnIndex("HOTELNAME"));
            String hot_address = res.getString(res.getColumnIndex("HOTELADDRESS"));
            String hot_rooms = res.getString(res.getColumnIndex("ROOMS"));
            String hot_price = res.getString(res.getColumnIndex("PRICE"));
            String hot_checkIn = res.getString(res.getColumnIndex("CHECKIN"));
            String hot_checkOut = res.getString(res.getColumnIndex("CHECKOUT"));
            HotelDetails hotelDetails = new HotelDetails(id,hot_name,hot_address,hot_rooms,hot_price,hot_checkIn,hot_checkOut);
            array_list.add(hotelDetails);
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<BusBookingDetails> getAllBusBookingDetails(String username) {
        ArrayList<BusBookingDetails> array_list = new ArrayList<BusBookingDetails>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM bus_detail_table  where USER ='"+username+"'", null );

        res.moveToFirst();

        while(res.isAfterLast() == false){
            Integer id = res.getInt(res.getColumnIndex("ID"));
            String busId = res.getString(res.getColumnIndex("BUSID"));
            String user = res.getString(res.getColumnIndex("USER"));
            String bus_from = res.getString(res.getColumnIndex("BUSFROM"));
            String bus_to = res.getString(res.getColumnIndex("BUSTO"));
            String bus_time = res.getString(res.getColumnIndex("BUSTIME"));
            String bus_price = res.getString(res.getColumnIndex("PRICE"));
            String bus_Seats = res.getString(res.getColumnIndex("BUSSEATS"));
            String bus_date = res.getString(res.getColumnIndex("DATE"));
            BusBookingDetails busBookingDetails = new BusBookingDetails(id,busId,user,bus_from,bus_to,bus_time,bus_price,bus_Seats,bus_date);
            array_list.add(busBookingDetails);
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<HotelBookingDetails> getAllHotelBookingDetails(String username) {
        ArrayList<HotelBookingDetails> array_list = new ArrayList<HotelBookingDetails>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM hotel_detail_table  where USER ='"+username+"'", null );

        res.moveToFirst();

        while(res.isAfterLast() == false){
            Integer id = res.getInt(res.getColumnIndex("ID"));
            String hotelid = res.getString(res.getColumnIndex("HOTELID"));
            String user = res.getString(res.getColumnIndex("USER"));
            String hot_name = res.getString(res.getColumnIndex("HOTELNAME"));
            String hot_address = res.getString(res.getColumnIndex("HOTELADDRESS"));
            String hot_rooms = res.getString(res.getColumnIndex("ROOMS"));
            String hot_price = res.getString(res.getColumnIndex("PRICE"));
            String hot_checkIn = res.getString(res.getColumnIndex("CHECKIN"));
            String hot_checkOut = res.getString(res.getColumnIndex("CHECKOUT"));
            HotelBookingDetails hotelBookingDetails = new HotelBookingDetails(id,hotelid,user,hot_name,hot_address,hot_rooms,hot_price,hot_checkIn,hot_checkOut);
            array_list.add(hotelBookingDetails);
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<BusDetails> getBusdata(String id) {
        ArrayList<BusDetails> array_list = new ArrayList<BusDetails>();
        ArrayList<BusDetails> allABusDetails = getAllABusDetails();

        for (BusDetails busDetails: allABusDetails) {
            if(busDetails.getId().equals(Integer.valueOf(id))) {
                array_list.add(busDetails);
            }
        }
        return array_list;
    }

    public ArrayList<HotelDetails> getHoteldata(String id) {
        ArrayList<HotelDetails> array_list = new ArrayList<HotelDetails>();
        ArrayList<HotelDetails> allHotels = getAllHotelDetails();

        for (HotelDetails hotelDetails: allHotels) {
            if(hotelDetails.getId().equals(Integer.valueOf(id))) {
                array_list.add(hotelDetails);
            }
        }
        return array_list;
    }

}

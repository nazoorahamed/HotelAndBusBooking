package diluxshan.com.busandhotelbooking.BookingDetailsFragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import diluxshan.com.busandhotelbooking.DatabaseHelper;
import diluxshan.com.busandhotelbooking.HotelBookingDetails;
import diluxshan.com.busandhotelbooking.HotelDetails;
import diluxshan.com.busandhotelbooking.ListViewAdapters.HotelBookingAdapter;
import diluxshan.com.busandhotelbooking.R;


public class HotelBookingFragment extends Fragment {

    Button bt_done,bt_confirm;
    TextView ddg_total,ddg_seats,ddg_place;
    private DatabaseHelper myDb;
    Dialog Checkin_dialog,Checkout_dialog,confirm;
    SearchView searchView;
    java.util.List<HotelBookingDetails> List;
    java.util.List<HotelBookingDetails> resycleList;
    ListView listView;
    public static String SELECTID;
    public String checkinDate,checkoutdate,username;
    public String hotelname,hotelrooms,hotelprice,hotelAddress,checkintime,checkouttime,totalprice,bookingid,user;
    public Date checkin_date,checkOout_date;


    public HotelBookingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.list_fragment, container, false);

        listView = view.findViewById(R.id.ed_list_search);
        searchView = view.findViewById(R.id.ed_search_view);
        Checkin_dialog = new Dialog(getContext());
        Checkout_dialog = new Dialog(getContext());
        confirm = new Dialog(getContext());
        myDb = new DatabaseHelper(getContext());
        resycleList = new ArrayList<HotelBookingDetails>();
        username = myDb.getLoggedUser();
        List = myDb.getAllHotelBookingDetails(username);

        for (HotelBookingDetails app : List) {
            resycleList.add(app);
        }
        HotelBookingAdapter adapter = new HotelBookingAdapter(getContext(),
                R.layout.hotel_booking_list, resycleList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HotelBookingDetails hotelDetails = resycleList.get(i);
                SELECTID = hotelDetails.getId().toString();
                hotelname = hotelDetails.getHot_name();
                hotelrooms = hotelDetails.getHot_rooms();
                hotelAddress = hotelDetails.getHot_address();
                checkintime = hotelDetails.getHot_CheckIn();
                checkouttime = hotelDetails.getHot_CheckOut();
                hotelprice = hotelDetails.getHot_price();
                bookingid = hotelDetails.getHot_id();
                username = hotelDetails.getUser();


                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Do you want to Edit Or Delete "+hotelname+" ?");
                String[] action = {"Change Checkout Date","Delete Booking"};
                builder.setItems(action, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                EditView();
                                break;
                            case 1:
                                myDb.deleteHotelBooking(SELECTID);
                                viewAll();
                                break;
                        }

                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                getSearchResult(s);
                return false;
            }
        });

        return view;
    }

    private void getSearchResult(String search) {
        DatabaseHelper db = new DatabaseHelper(getContext());
        resycleList = new ArrayList<HotelBookingDetails>();
        List = db.getAllHotelBookingDetails(username);

        for (HotelBookingDetails app : List) {
            if (app.getHot_name().contains(search) || app.getHot_address().contains(search) || app.getHot_price().contains(search)) {
                resycleList.add(app);
            }
        }
        HotelBookingAdapter adapter = new HotelBookingAdapter(getContext(),
                R.layout.hotel_booking_list, resycleList);
        listView.setAdapter(adapter);

    }

    public void EditView() {

        Checkout_dialog.setContentView(R.layout.date_picker);
        TextView pickerHead = Checkout_dialog.findViewById(R.id.date_picker_head);
        TextView checkmsg = Checkout_dialog.findViewById(R.id.checkout_msg);
        bt_confirm = Checkout_dialog.findViewById(R.id.bt_CheckOut);
        bt_confirm.setVisibility(View.VISIBLE);
        checkmsg.setVisibility(View.VISIBLE);
        CalendarView OutdatePicker = Checkout_dialog.findViewById(R.id.calendarView);
        pickerHead.setText("Select Check Out Date");
        checkmsg.setText("*Check out Date Must me Greater than "+checkintime);

        OutdatePicker.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                checkoutdate = (i) + "/" + (i1 + 1) + "/" + (i2);
                checkOout_date = new Date(i - i1+1 - i2);

                bt_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (checkoutdate == null) {
                            Toast.makeText(getContext(), "Please Select a Check Out Date", Toast.LENGTH_SHORT);
                        } else {

                            Checkout_dialog.dismiss();
                            confirm.setContentView(R.layout.booking_dialog_view);

                            ddg_place = confirm.findViewById(R.id.book_place);
                            ddg_seats = confirm.findViewById(R.id.book_seats);
                            ddg_total = confirm.findViewById(R.id.book_total);
                            bt_done = confirm.findViewById(R.id.bt_Done);


                            ArrayList<HotelDetails> array_list = myDb.getHoteldata(bookingid);

                            String[] a = checkintime.split("/");

                            int year = Integer.valueOf(a[0]);
                            int month = Integer.valueOf(a[1]);
                            int day = Integer.valueOf(a[2]);
                            checkin_date = new Date(year-month-day+2);

                            long different = checkin_date.getTime() - checkOout_date.getTime();

                            Double total_price = Double.valueOf(different) * Double.valueOf(array_list.get(0).getHot_price());
                            totalprice = String.valueOf(total_price);
                            System.out.println(different);

                            ddg_place.setText(hotelname);
                            ddg_total.setText(totalprice + " /=");
                            ddg_seats.setText("Rooms : " + hotelrooms);
                            myDb.updateHotelBooking(SELECTID, checkoutdate,totalprice);
                            viewAll();

                            bt_done.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    confirm.dismiss();
                                }
                            });
                            confirm.show();
                        }
                    }
                });

            }
        });
        Checkout_dialog.show();

    }

    public void viewAll() {
        //view All the Appointment of that date
        listView =   getView().findViewById(R.id.ed_list_search);
        searchView = getView().findViewById(R.id.ed_search_view);

        final DatabaseHelper db = new DatabaseHelper(getContext());
        resycleList = new ArrayList<HotelBookingDetails>();
        List = db.getAllHotelBookingDetails(username);

        for (HotelBookingDetails app : List) {
            resycleList.add(app);
        }
        HotelBookingAdapter adapter = new HotelBookingAdapter(getContext(),
                R.layout.hotel_booking_list, resycleList);
        listView.setAdapter(adapter);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // Refresh your fragment here
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
            Log.i("IsRefresh", "Yes");
        }
    }
}

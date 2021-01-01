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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

import diluxshan.com.busandhotelbooking.BusBookingDetails;
import diluxshan.com.busandhotelbooking.BusDetails;
import diluxshan.com.busandhotelbooking.DatabaseHelper;
import diluxshan.com.busandhotelbooking.ListViewAdapters.BusBookingAdapter;
import diluxshan.com.busandhotelbooking.R;


public class BusBookingFragment extends Fragment {
    public static String date;
    TextView dnoSeats,dBusfrom,dBusTime,dBusprice,dtotalamount,ddg_total,ddg_seats,ddg_place;
    NumberPicker selectSeats=null;
    public Double Ticketprice,totalAmount;
    Button booknow,bt_done;
    Dialog dialogshow;
    String fromplace,toplace,seats,username;

    Button bt_addBus;
    EditText BusFrom,BusTo,BusTime,BusTicPrice,BusSeats;
    private DatabaseHelper myDb;
    Dialog dialog,Dg_datepick;
    String id,bus_from,bus_to,bus_time,bus_price,bus_seats,bus_id;
    SearchView searchView;
    java.util.List<BusBookingDetails> List;
    java.util.List<BusBookingDetails> resycleList;
    ListView listView;
    public static String SELECTID;

    public BusBookingFragment() {
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
        dialog = new Dialog(getContext());
        dialogshow = new Dialog(getContext());
        Dg_datepick = new Dialog(getContext());
        myDb = new DatabaseHelper(getContext());
        resycleList = new ArrayList<BusBookingDetails>();
        username = myDb.getLoggedUser();
        List = myDb.getAllBusBookingDetails(username);

        for (BusBookingDetails app : List) {
            resycleList.add(app);
        }
        BusBookingAdapter adapter = new BusBookingAdapter(getContext(),
                R.layout.bus_booking_view, resycleList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                BusBookingDetails busDetails = resycleList.get(i);
                SELECTID = busDetails.getId().toString();
                bus_from = busDetails.getBus_from().toString();
                bus_to = busDetails.getBus_to().toString();
                bus_time = busDetails.getBus_time();
                bus_price = busDetails.getBus_Tprice();
                bus_seats = busDetails.getBus_Seats();
                bus_id = busDetails.getBusid();
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Do you want to Edit Or Delete Booking "+SELECTID+" ?");
                String[] action = {"Change Seats","Delete Booking"};
                builder.setItems(action, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                EditView();
                                viewAll();
                                break;
                            case 1:
                                myDb.deleteBusBooking(SELECTID);
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
        resycleList = new ArrayList<BusBookingDetails>();
        List = db.getAllBusBookingDetails(username);

        for (BusBookingDetails app : List) {
            if (app.getBus_from().contains(search) || app.getBus_to().contains(search) || app.getBus_Tprice().contains(search)) {
                resycleList.add(app);
            }
        }
        BusBookingAdapter adapter = new BusBookingAdapter(getContext(),
                R.layout.bus_booking_view, resycleList);
        listView.setAdapter(adapter);

    }

    public void EditView(){
        dialogshow.setContentView(R.layout.view_book_bus);

        dnoSeats = dialogshow.findViewById(R.id.totbusSeats);
        dBusfrom = dialogshow.findViewById(R.id.tottxtfrom);
        dBusTime = dialogshow.findViewById(R.id.tottxtDate);
        dBusprice = dialogshow.findViewById(R.id.tottxtprice);
        selectSeats = dialogshow.findViewById(R.id.np_seats);
        dtotalamount = dialogshow.findViewById(R.id.totalamount);
        booknow = dialogshow.findViewById(R.id.bt_bookBus);


        ArrayList<BusDetails> array_list = myDb.getBusdata(bus_id);
        Ticketprice = Double.valueOf(array_list.get(0).getBus_Tprice());
        fromplace = array_list.get(0).getBus_from();
        toplace = array_list.get(0).getBus_to();
        seats = array_list.get(0).getBus_Seats();

        dnoSeats.setText(bus_seats);
        dBusfrom.setText(bus_from +" - "+bus_to);
        dBusprice.setText(bus_price+"/=");
        dBusTime.setText("Depature Time "+bus_time);

        selectSeats.setMaxValue(Integer.valueOf(seats));
        selectSeats.setMinValue(Integer.valueOf(bus_seats));
        selectSeats.setWrapSelectorWheel(false);
        totalAmount =selectSeats.getValue()*Ticketprice;
        dtotalamount.setText(String.valueOf("Total Amonut : "+totalAmount+"/="));

        selectSeats.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                totalAmount =selectSeats.getValue()*Ticketprice;
                dtotalamount.setText(String.valueOf("Total Amonut : "+totalAmount+"/="));
            }
        });

        booknow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogshow.dismiss();
                dialog.setContentView(R.layout.booking_dialog_view);

                ddg_place =dialog.findViewById(R.id.book_place);
                ddg_seats = dialog.findViewById(R.id.book_seats);
                ddg_total = dialog.findViewById(R.id.book_total);
                bt_done = dialog.findViewById(R.id.bt_Done);

                String username = myDb.getLoggedUser();
                ddg_place.setText(bus_from+"-"+bus_to);
                ddg_total.setText(String.valueOf("Total : "+totalAmount+"/="));
                ddg_seats.setText(selectSeats.getValue()+" Seats");
                myDb.updateBusBooking(SELECTID,totalAmount.toString(),String.valueOf(selectSeats.getValue()));

                viewAll();
                bt_done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }

        });
        dialogshow.show();

    }

    public void viewAll() {
        //view All the Appointment of that date
        listView =   getView().findViewById(R.id.ed_list_search);
        searchView = getView().findViewById(R.id.ed_search_view);

        final DatabaseHelper db = new DatabaseHelper(getContext());
        resycleList = new ArrayList<BusBookingDetails>();
        List = db.getAllBusBookingDetails(username);

        for (BusBookingDetails app : List) {
            resycleList.add(app);
        }
        BusBookingAdapter adapter = new BusBookingAdapter(getContext(),
                R.layout.bus_booking_view, resycleList);
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

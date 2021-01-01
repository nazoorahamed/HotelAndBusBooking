package diluxshan.com.busandhotelbooking.AddBusFragment;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
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
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import diluxshan.com.busandhotelbooking.BusDetails;
import diluxshan.com.busandhotelbooking.DatabaseHelper;
import diluxshan.com.busandhotelbooking.ListViewAdapters.BusDetailsAdapter;
import diluxshan.com.busandhotelbooking.R;


public class EditDeleteBusFragment extends Fragment {

    Button bt_addBus;
    EditText BusFrom,BusTo,BusTime,BusTicPrice,BusSeats;
    private DatabaseHelper myDb;
    Dialog dialog;
    String id,bus_from,bus_to,bus_time,bus_price,bus_seats;
    SearchView searchView;
    java.util.List<BusDetails> List;
    java.util.List<BusDetails> resycleList;
    ListView listView;
    public static String SELECTID;

    public EditDeleteBusFragment() {
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
        myDb = new DatabaseHelper(getContext());
        resycleList = new ArrayList<BusDetails>();
        List = myDb.getAllABusDetails();

        for (BusDetails app : List) {
            resycleList.add(app);
        }
        BusDetailsAdapter adapter = new BusDetailsAdapter(getContext(),
                R.layout.layout_bus_list, resycleList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BusDetails busDetails = resycleList.get(i);
                SELECTID = busDetails.getId().toString();
                bus_from = busDetails.getBus_from().toString();
                bus_to = busDetails.getBus_to().toString();
                bus_time = busDetails.getBus_time();
                bus_price = busDetails.getBus_Tprice();
                bus_seats = busDetails.getBus_Seats();

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Choose Action");
                String[] action = {"Edit", "Delete"};
                builder.setItems(action, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                EditView();
                                break;
                            case 1:
                                myDb.deleteBusData(SELECTID);
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
        resycleList = new ArrayList<BusDetails>();
        List = db.getAllABusDetails();

        for (BusDetails app : List) {
            if (app.getBus_from().contains(search) || app.getBus_to().contains(search) || app.getBus_Tprice().contains(search)) {
                resycleList.add(app);
            }
        }
        BusDetailsAdapter adapter = new BusDetailsAdapter(getContext(),
                R.layout.layout_bus_list, resycleList);
        listView.setAdapter(adapter);

    }

    public void EditView(){

        dialog.setContentView(R.layout.activity_add_bus_details);
        bt_addBus = dialog.findViewById(R.id.bt_addBus);
        BusFrom = dialog.findViewById(R.id.txt_busStart);
        BusTo = dialog.findViewById(R.id.txt_busEnd);
        BusTime = dialog.findViewById(R.id.txt_bustime);
        BusTicPrice = dialog.findViewById(R.id.txt_BusPrice);
        BusSeats = dialog.findViewById(R.id.txt_BusSeats);
        myDb = new DatabaseHelper(getContext());


        Cursor res = myDb.AddBustext(SELECTID);
        if (res.getCount() == 0) {
            // show message
            Toast.makeText(getContext(),"Databse is emphty",Toast.LENGTH_SHORT).show();
            return;
        }
        while (res.moveToNext()) {
            BusFrom.setText(res.getString(1));
            BusTo.setText(res.getString(2));
            BusTime.setText(res.getString(3));
            BusTicPrice.setText(res.getString(4));
            BusSeats.setText(res.getString(5));

        }



        bt_addBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String busfrom = String.valueOf(BusFrom.getText());
                String busto = String.valueOf(BusTo.getText());
                String bustime = String.valueOf(BusTime.getText());
                String ticketPrice  = String.valueOf(BusTicPrice.getText());
                String busSeats = String.valueOf(BusSeats.getText());

                if(!busfrom.equals("") && !busto.equals("")&& !bustime.equals("")&& !ticketPrice.equals("")&& !busSeats.equals("")){
                    boolean isInserted = myDb.updateBus(SELECTID,busfrom,busto,bustime,ticketPrice,busSeats);
                    if(isInserted == true) {
                        Toast.makeText(getContext(), "Bus Edited", Toast.LENGTH_LONG).show();
                        BusFrom.setText(null);
                        BusTo.setText(null);
                        BusTime.setText(null);
                        BusTicPrice.setText(null);
                        BusSeats.setText(null);
                        dialog.dismiss();
                        viewAll();

                    } else{
                        Toast.makeText(getContext(),"Bus Not Edited", Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(getContext(),"Fill to Edit Bus ", Toast.LENGTH_LONG).show();
                }
            }
        });
        dialog.show();

    }

    public void viewAll() {
        //view All the Appointment of that date
        listView =   getView().findViewById(R.id.ed_list_search);
        searchView = getView().findViewById(R.id.ed_search_view);

        final DatabaseHelper db = new DatabaseHelper(getContext());
        resycleList = new ArrayList<BusDetails>();
        List = db.getAllABusDetails();

        for (BusDetails app : List) {
            resycleList.add(app);
        }
        BusDetailsAdapter adapter = new BusDetailsAdapter(getContext(),
                R.layout.layout_bus_list, resycleList);
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

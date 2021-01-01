package diluxshan.com.busandhotelbooking.AddHotelFragment;


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

import diluxshan.com.busandhotelbooking.DatabaseHelper;
import diluxshan.com.busandhotelbooking.HotelDetails;
import diluxshan.com.busandhotelbooking.ListViewAdapters.HotelDetailsAdapter;
import diluxshan.com.busandhotelbooking.R;


public class EditDeleteHotelFragment extends Fragment {

    Button bt_addHotel;
    EditText HotelName,HotelAddress,HotelRooms,HotelPrice,HotelCheckIn,HotelCheckOut;
    private DatabaseHelper myDb;
    Dialog dialog;
    SearchView searchView;
    java.util.List<HotelDetails> List;
    java.util.List<HotelDetails> resycleList;
    ListView listView;
    public static String SELECTID;

    public EditDeleteHotelFragment() {
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
        resycleList = new ArrayList<HotelDetails>();
        List = myDb.getAllHotelDetails();

        for (HotelDetails app : List) {
            resycleList.add(app);
        }
        HotelDetailsAdapter adapter = new HotelDetailsAdapter(getContext(),
                R.layout.hotel_list, resycleList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HotelDetails hotelDetails = resycleList.get(i);
                SELECTID = hotelDetails.getId().toString();
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
                                myDb.deleteHotelData(SELECTID);
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
        resycleList = new ArrayList<HotelDetails>();
        List = db.getAllHotelDetails();

        for (HotelDetails app : List) {
            if (app.getHot_name().contains(search) || app.getHot_address().contains(search) || app.getHot_price().contains(search)) {
                resycleList.add(app);
            }
        }
        HotelDetailsAdapter adapter = new HotelDetailsAdapter(getContext(),
                R.layout.hotel_list, resycleList);
        listView.setAdapter(adapter);

    }

    public void EditView(){

        dialog.setContentView(R.layout.activity_add_hotel_details);
        bt_addHotel = dialog.findViewById(R.id.bt_addHotel);
        HotelName = dialog.findViewById(R.id.txt_Hotelname);
        HotelAddress = dialog.findViewById(R.id.txt_HotelAddress);
        HotelRooms = dialog.findViewById(R.id.txt_HotelRooms);
        HotelPrice = dialog.findViewById(R.id.txt_HotelPrice);
        HotelCheckIn = dialog.findViewById(R.id.txt_HotelCheckIn);
        HotelCheckOut = dialog.findViewById(R.id.txt_HotelCheckOut);
        myDb = new DatabaseHelper(getContext());


        Cursor res = myDb.AddHoteltext(SELECTID);
        if (res.getCount() == 0) {
            // show message
            Toast.makeText(getContext(),"Databse is emphty",Toast.LENGTH_SHORT).show();
            return;
        }
        while (res.moveToNext()) {
            HotelName.setText(res.getString(1));
            HotelAddress.setText(res.getString(2));
            HotelRooms.setText(res.getString(3));
            HotelPrice.setText(res.getString(4));
            HotelCheckIn.setText(res.getString(5));
            HotelCheckOut.setText(res.getString(6));

        }



        bt_addHotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String hotelName = String.valueOf(HotelName.getText());
                String hotelAddress = String.valueOf(HotelAddress.getText());
                String hotelRooms = String.valueOf(HotelRooms.getText());
                String hotelPrice  = String.valueOf(HotelPrice.getText());
                String hotelCheckIn = String.valueOf(HotelCheckIn.getText());
                String hotelCheckOut = String.valueOf(HotelCheckOut.getText());

                if(!hotelName.equals("") && !hotelAddress.equals("")&& !hotelPrice.equals("")&& !hotelRooms.equals("")&& !hotelCheckIn.equals("")&& !hotelCheckOut.equals("")){
                    boolean isInserted = myDb.updateHotel(SELECTID,hotelName,hotelAddress,hotelRooms,hotelPrice,hotelCheckIn,hotelCheckOut);
                    if(isInserted == true) {
                        Toast.makeText(getContext(), "Hotel Edited", Toast.LENGTH_LONG).show();
                        HotelName.setText(null);
                        HotelAddress.setText(null);
                        HotelRooms.setText(null);
                        HotelPrice.setText(null);
                        HotelCheckIn.setText(null);
                        HotelCheckOut.setText(null);
                        dialog.dismiss();
                        viewAll();

                    } else{
                        Toast.makeText(getContext(),"Hotel Not Edited", Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(getContext(),"Fill to Edit Hotel ", Toast.LENGTH_LONG).show();
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
        resycleList = new ArrayList<HotelDetails>();
        List = db.getAllHotelDetails();

        for (HotelDetails app : List) {
            resycleList.add(app);
        }
        HotelDetailsAdapter adapter = new HotelDetailsAdapter(getContext(),
                R.layout.hotel_list, resycleList);
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

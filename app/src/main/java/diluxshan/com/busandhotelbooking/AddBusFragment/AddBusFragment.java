package diluxshan.com.busandhotelbooking.AddBusFragment;


import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import diluxshan.com.busandhotelbooking.DatabaseHelper;
import diluxshan.com.busandhotelbooking.R;


public class AddBusFragment extends Fragment {

    Button bt_addBus;
    EditText BusFrom,BusTo,BusTime,BusTicPrice,BusSeats;

    private DatabaseHelper myDb;

    public AddBusFragment() {
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
        View view = inflater.inflate(R.layout.activity_add_bus_details, container, false);

        bt_addBus = view.findViewById(R.id.bt_addBus);
        BusFrom = view.findViewById(R.id.txt_busStart);
        BusTo = view.findViewById(R.id.txt_busEnd);
        BusTime = view.findViewById(R.id.txt_bustime);
        BusTicPrice = view.findViewById(R.id.txt_BusPrice);
        BusSeats = view.findViewById(R.id.txt_BusSeats);
        myDb = new DatabaseHelper(getContext());



        bt_addBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String busfrom = String.valueOf(BusFrom.getText());
                String busto = String.valueOf(BusTo.getText());
                String bustime = String.valueOf(BusTime.getText());
                String ticketPrice  = String.valueOf(BusTicPrice.getText());
                String busSeats = String.valueOf(BusSeats.getText());


                if(!busfrom.equals("") && !busto.equals("")&& !bustime.equals("")&& !ticketPrice.equals("")&& !busSeats.equals("")){
                    boolean isInserted = myDb.AddBus(busfrom,busto,bustime,ticketPrice,busSeats);
                    if(isInserted == true) {
                        Toast.makeText(getContext(), "Court Added", Toast.LENGTH_LONG).show();
                        BusFrom.setText(null);
                        BusTo.setText(null);
                        BusTime.setText(null);
                        BusTicPrice.setText(null);
                        BusSeats.setText(null);

                    } else{
                        Toast.makeText(getContext(),"Court Not Added", Toast.LENGTH_LONG).show();
                    }

                }else {
                    Toast.makeText(getContext(),"Fill Court number & Price ", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
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

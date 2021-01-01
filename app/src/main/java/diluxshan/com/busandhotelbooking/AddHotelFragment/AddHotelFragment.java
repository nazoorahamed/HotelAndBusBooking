package diluxshan.com.busandhotelbooking.AddHotelFragment;


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


public class AddHotelFragment extends Fragment {

    Button bt_addHotel;
    EditText HotelName,HotelAddress,HotelRooms,HotelPrice,HotelCheckIn,HotelCheckOut;

    private DatabaseHelper myDb;

    public AddHotelFragment() {
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
        View view = inflater.inflate(R.layout.activity_add_hotel_details, container, false);

        bt_addHotel = view.findViewById(R.id.bt_addHotel);
        HotelName = view.findViewById(R.id.txt_Hotelname);
        HotelAddress = view.findViewById(R.id.txt_HotelAddress);
        HotelRooms = view.findViewById(R.id.txt_HotelRooms);
        HotelPrice = view.findViewById(R.id.txt_HotelPrice);
        HotelCheckIn = view.findViewById(R.id.txt_HotelCheckIn);
        HotelCheckOut = view.findViewById(R.id.txt_HotelCheckOut);
        myDb = new DatabaseHelper(getContext());



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
                    boolean isInserted = myDb.AddHotel(hotelName,hotelAddress,hotelRooms,hotelPrice,hotelCheckIn,hotelCheckOut);
                    if(isInserted == true) {
                        Toast.makeText(getContext(), "Hotel Added", Toast.LENGTH_LONG).show();
                        HotelName.setText(null);
                        HotelAddress.setText(null);
                        HotelRooms.setText(null);
                        HotelPrice.setText(null);
                        HotelCheckIn.setText(null);
                        HotelCheckOut.setText(null);

                    } else{
                        Toast.makeText(getContext(),"Hotel Not Added", Toast.LENGTH_LONG).show();
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

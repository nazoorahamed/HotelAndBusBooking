package diluxshan.com.busandhotelbooking.ListViewAdapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import diluxshan.com.busandhotelbooking.HotelBookingDetails;
import diluxshan.com.busandhotelbooking.R;


public class HotelBookingAdapter extends ArrayAdapter<HotelBookingDetails> {
    List<HotelBookingDetails> list;
    Context context;
    int resource;


    public HotelBookingAdapter(@NonNull Context context, int resource, List<HotelBookingDetails> list) {
        super(context, resource,list);

        this.context  = context;
        this.resource = resource;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(resource, null);

        TextView lhotelname,ldhoteladdress,lhotelprice,lhotelcheckin,lhotelcheckout,lbookingid;

        lhotelname =   view.findViewById(R.id.hotel_b_name);
        ldhoteladdress = view.findViewById(R.id.hotel_b_address);
        lhotelprice = view.findViewById(R.id.hotel_b_amount);
        lhotelcheckin = view.findViewById(R.id.Hotel_b_checkin);
        lhotelcheckout = view.findViewById(R.id.Hotel_b_checkOut);
        lbookingid = view.findViewById(R.id.hotel_b_id);

        HotelBookingDetails hotelBookingDetails = list.get(position);
        lhotelname.setText(hotelBookingDetails.getHot_name());
        ldhoteladdress.setText("Address : "+hotelBookingDetails.getHot_address());
        lhotelprice.setText(hotelBookingDetails.getHot_price()+"/=");
        lhotelcheckin.setText(hotelBookingDetails.getHot_CheckIn());
        lhotelcheckout.setText(hotelBookingDetails.getHot_CheckOut());
        lbookingid.setText("Booking ID : "+hotelBookingDetails.getId());

        return view;
    }
}

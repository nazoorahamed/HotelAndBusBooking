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

import diluxshan.com.busandhotelbooking.HotelDetails;
import diluxshan.com.busandhotelbooking.R;

public class HotelDetailsAdapter extends ArrayAdapter<HotelDetails> {
    List<HotelDetails> list;
    Context context;
    int resource;


    public HotelDetailsAdapter(@NonNull Context context, int resource, List<HotelDetails> list) {
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

        TextView hot_name =   view.findViewById(R.id.ht_hotel_name);
        TextView hot_address = view.findViewById(R.id.ht_view_raddress);
        TextView hot_price = view.findViewById(R.id.ht_view_price);
        TextView hot_rooms = view.findViewById(R.id.ht_view_rooms);

        HotelDetails hotelDetails = list.get(position);
        hot_name.setText(hotelDetails.getHot_name());
        hot_address.setText("Address : "+hotelDetails.getHot_address());
        hot_price.setText("Per Day : "+hotelDetails.getHot_price()+" /=");
        hot_rooms.setText(hotelDetails.getHot_rooms()+" Rooms");

        return view;
    }
}

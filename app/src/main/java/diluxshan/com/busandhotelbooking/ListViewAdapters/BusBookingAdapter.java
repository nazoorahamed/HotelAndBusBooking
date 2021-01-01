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

import diluxshan.com.busandhotelbooking.BusBookingDetails;
import diluxshan.com.busandhotelbooking.R;


public class BusBookingAdapter extends ArrayAdapter<BusBookingDetails> {
    List<BusBookingDetails> list;
    Context context;
    int resource;


    public BusBookingAdapter(@NonNull Context context, int resource, List<BusBookingDetails> list) {
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

        TextView bus_place =   view.findViewById(R.id.bus_b_place);
        TextView busbookid = view.findViewById(R.id.bus_b_head);
        TextView bus_seats = view.findViewById(R.id.bus_b_seats);
        TextView bus_price = view.findViewById(R.id.bus_b_total);

        BusBookingDetails busBookingDetails = list.get(position);
        busbookid.setText("Booking ID : "+busBookingDetails.getId());
        bus_place.setText(busBookingDetails.getBus_from()+" - "+busBookingDetails.getBus_to());
        bus_seats.setText("Seats : "+busBookingDetails.getBus_Seats());
        bus_price.setText(" Total : "+busBookingDetails.getBus_Tprice()+" /=");

        return view;
    }
}

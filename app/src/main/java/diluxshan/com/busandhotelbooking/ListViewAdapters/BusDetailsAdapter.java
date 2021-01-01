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

import diluxshan.com.busandhotelbooking.BusDetails;
import diluxshan.com.busandhotelbooking.R;

public class BusDetailsAdapter extends ArrayAdapter<BusDetails> {
    List<BusDetails> list;
    Context context;
    int resource;


    public BusDetailsAdapter(@NonNull Context context, int resource, List<BusDetails> list) {
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

        TextView txt_from =  view.findViewById(R.id.txtfrom);
        TextView txt_to =   view.findViewById(R.id.txtto);
        TextView txt_date =   view.findViewById(R.id.txtDate);
        TextView txt_price = view.findViewById(R.id.txtprice);

        BusDetails busDetails = list.get(position);
        txt_from.setText(busDetails.getBus_from());
        txt_to.setText(busDetails.getBus_to());
        txt_date.setText("Depature Time  "+busDetails.getBus_time());
        txt_price.setText("Ticket Price :"+busDetails.getBus_Tprice()+"/=");

        return view;
    }
}

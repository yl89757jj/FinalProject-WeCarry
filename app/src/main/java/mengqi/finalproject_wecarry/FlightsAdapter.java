package mengqi.finalproject_wecarry;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Lu on 2/23/2016.
 */
public class FlightsAdapter extends RecyclerView.Adapter<FlightViewHolder> {
    private List<Flight> flights;
    private Context context;

    public FlightsAdapter(List<Flight> flights, Context context) {
        this.flights = flights;
        this.context = context;
    }

    @Override
    public FlightViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_flight, parent, false);
        return new FlightViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(FlightViewHolder holder, int position) {
        Flight flight = flights.get(position);
        holder.personName.setText(flight.name);
        holder.personInfo.setText(flight.info);
        holder.personPhoto.setImageResource(flight.photoId);
    }

    @Override
    public int getItemCount() {
        return flights.size();
    }
}

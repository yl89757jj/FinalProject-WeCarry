package mengqi.finalproject_wecarry;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lu on 2/23/2016.
 */
public class  FlightsAdapter extends RecyclerView.Adapter<FlightViewHolder> {
    public List<Flight> flights = new ArrayList<>();
    private Context context;


    public static String departure = "";
    public static String arrival = "";
    public static String departDate = "";



    //filter boolean
    private boolean filterDep(Flight flight){
        return departure.equals("") || flight.departure.equals(departure);
    }

    private boolean filterDate(Flight flight){
        return departDate.equals("") || flight.departDate.equals(departDate);
    }

    private boolean filterArv(Flight flight){
        return arrival.equals("") || flight.arrival.equals(arrival);
    }


    public FlightsAdapter(Firebase flightsRef, Context context, final int filter, final String userEmail) {
        this.context = context;
        flights = new ArrayList<>();

        flightsRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Flight flight = dataSnapshot.getValue(Flight.class);
                switch (filter) {
                    case 1:
                        flights.add(flight);
                        break;
                    case 2:
                        boolean show = filterArv(flight) && filterDep(flight) &&filterDate(flight);//&& filterSpc(flight);
                        if (show) {
                            flights.add(flight);
                        }
                        break;
                    case 3:
                        if(flight.userEmail.equals(userEmail)){
                            flights.add(flight);
                        }
                        break;
                }
                notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Flight flight = dataSnapshot.getValue(Flight.class);
                flights.remove(flight);
                notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


    @Override
    public FlightViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_flight, parent, false);
        return new FlightViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(FlightViewHolder holder, int position) {
        Flight flight = flights.get(position);
        holder.bind(flight);
    }

    @Override
    public int getItemCount() {
        return flights.size();
    }
}

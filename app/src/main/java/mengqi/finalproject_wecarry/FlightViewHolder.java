package mengqi.finalproject_wecarry;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Lu on 2/23/2016.
 */
public class FlightViewHolder extends RecyclerView.ViewHolder {
    public CardView flightCard;
    public TextView flightDep;
    public TextView flightNum;
    public TextView flightSpace;
    public TextView flightTime;
    public TextView flightArrival;


    // public ImageView personPhoto;
    private Context context;

    public FlightViewHolder(View itemView, final Context context) {
        super(itemView);
        flightCard = (CardView) itemView.findViewById(R.id.card_flight);
        flightDep = (TextView) itemView.findViewById(R.id.flight_departure);
        flightSpace = (TextView) itemView.findViewById(R.id.flight_space);
        flightTime = (TextView) itemView.findViewById(R.id.flight_time);
        flightArrival = (TextView) itemView.findViewById(R.id.flight_arrival);

        this.context = context;
    }


    public void bind(final Flight flight) {
        flightDep.setText(flight.departure);
        flightSpace.setText(flight.spaceAvailable);
        flightTime.setText(flight.departDate);
        flightArrival.setText(flight.arrival);
        flightCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(v.getContext(),FlightActivity.class);
                intent.putExtra("EDE",flight.departure);
                intent.putExtra("EAR",flight.arrival);
                intent.putExtra("EDA",flight.departDate);
                intent.putExtra("ESP",flight.spaceAvailable);
                intent.putExtra("ENO",flight.specialNote);
                intent.putExtra("ENU",flight.number);
                intent.putExtra("EEM",flight.userEmail);


                v.getContext().startActivity(intent);

            }
        });
    }
}

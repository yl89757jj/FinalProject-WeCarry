package mengqi.finalproject_wecarry;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
    public TextView flightNote;


    // public ImageView personPhoto;
    private Context context;

    public FlightViewHolder(View itemView, final Context context) {
        super(itemView);
        flightCard = (CardView) itemView.findViewById(R.id.card_flight);
        flightNum = (TextView) itemView.findViewById(R.id.flight_number);
        flightDep = (TextView) itemView.findViewById(R.id.flight_departure);
        flightSpace = (TextView) itemView.findViewById(R.id.flight_space);
        flightTime = (TextView) itemView.findViewById(R.id.flight_time);
        flightArrival = (TextView) itemView.findViewById(R.id.flight_arrival);
        flightNote = (TextView) itemView.findViewById(R.id.flight_note);

        // personPhoto = (ImageView) itemView.findViewById(R.id.person_photo);
        this.context = context;
    }


    public void bind(final Flight flight) {
        flightNum.setText(flight.number);
        flightDep.setText(flight.departure);
        flightSpace.setText(flight.spaceAvailable);
        flightTime.setText(flight.departDate);
        flightArrival.setText(flight.arrival);
        flightNote.setText(flight.specialNote);
        flightCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, flightNum.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}

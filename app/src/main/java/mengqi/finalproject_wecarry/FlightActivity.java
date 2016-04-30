package mengqi.finalproject_wecarry;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

public class FlightActivity extends AppCompatActivity {
    private TextView departureDate;
    private TextView departure;
    private TextView arrival;
    private TextView flightNo;
    private TextView spaceAvaible;
    private TextView specialNote;
    private String myEmail;
    private String toEmail;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight);
        departureDate = (TextView) findViewById(R.id.select_depart_date);
        arrival = (TextView) findViewById(R.id.select_arrival_city);
        departure = (TextView) findViewById(R.id.select_departure_city);
        flightNo = (TextView) findViewById(R.id.select_fight_no);
        spaceAvaible = (TextView) findViewById(R.id.select_space_available);
        specialNote = (TextView) findViewById(R.id.select_special_note);

    }

    private void SendEmail() {

        String[] TO = {toEmail};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Message From WeCarry");
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(FlightActivity.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}

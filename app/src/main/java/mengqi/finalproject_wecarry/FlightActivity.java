package mengqi.finalproject_wecarry;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class FlightActivity extends AppCompatActivity {
    private TextView departureDate;
    private TextView departure;
    private TextView arrival;
    private TextView flightNo;
    private TextView spaceAvaible;
    private TextView specialNote;
    private EditText edit_message;
    private String message;
    private String toEmail;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight);
        intent = getIntent();
        Bundle extras = intent.getExtras();
        departureDate = (TextView) findViewById(R.id.select_depart_date);
        arrival = (TextView) findViewById(R.id.select_arrival_city);
        departure = (TextView) findViewById(R.id.select_departure_city);
        flightNo = (TextView) findViewById(R.id.select_fight_no);
        spaceAvaible = (TextView) findViewById(R.id.select_space_available);
        specialNote = (TextView) findViewById(R.id.select_special_note);
        edit_message = (EditText) findViewById(R.id.message);

        toEmail = extras.getString("EEM");
        departure.setText(extras.getString("EDE"));
        arrival.setText(extras.getString("EAR"));
        departureDate.setText(extras.getString("EDA"));
        spaceAvaible.setText(extras.getString("ESP"));
        specialNote.setText(extras.getString("ENO"));
        flightNo.setText(extras.getString("ENU"));

    }

    public void sendEmail1(View view) {
        message = edit_message.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{toEmail});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Message From WeCarry");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.log_out:
                MainActivity.rootRef.unauth();
                return true;
            case R.id.user:
                Intent intent = new Intent(FlightActivity.this, UserActivity.class);
                startActivity(intent);
            case R.id.home:
                Intent intent2 = new Intent(FlightActivity.this, HomeActivity.class);
                startActivity(intent2);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

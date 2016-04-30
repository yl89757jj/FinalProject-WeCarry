package mengqi.finalproject_wecarry;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

import java.util.Calendar;

public class UserFlight extends AppCompatActivity {
    private Firebase.AuthStateListener authStateListener;
    private Spinner departure;
    private Spinner arrival;
    private EditText flightNo;
    private String departDate;
    private EditText spaceAvaible;
    private EditText specialNote;
    private Firebase userRef;
    private String userEmail;
    private Button button;
    private int year, month, day;
    private static final int DILOG_ID = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_flight);
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);


        authStateListener = new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData == null) {
                    Intent intent = new Intent(UserFlight.this, LogInActivity.class);
                    startActivity(intent);
                } else {
                    userRef = MainActivity.rootRef;

                    userEmail=authData.getProviderData().get("email").toString();


                }
            }
        };
    }

    public void datePick(View view) {
        button = (Button) findViewById(R.id.depart_date);
        showDialog(DILOG_ID);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DILOG_ID)
            return new DatePickerDialog(this, dpickListener, year, month, day);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int years, int monthOfYear, int dayOfMonth) {
            year = years;
            month = monthOfYear + 1;
            day = dayOfMonth;
            button.setText(month + "/" + day + "/" + year);
        }
    };

    public void submitFlight(View view) {
        departure = (Spinner) findViewById(R.id.departure_city);
        arrival = (Spinner) findViewById(R.id.arrival_city);
        flightNo = (EditText) findViewById(R.id.fight_no);
        departDate = month + "/" + day + "/" + year;
        spaceAvaible = (EditText) findViewById(R.id.space_available);
        specialNote = (EditText) findViewById(R.id.special_note);
        Flight flight = new Flight(departure.getSelectedItem().toString(), arrival.getSelectedItem().toString(), flightNo.getText().toString(), departDate,
                spaceAvaible.getText().toString(), specialNote.getText().toString(), userEmail);
        userRef.child("flights").push().setValue(flight);
        Intent intent = new Intent(UserFlight.this, HomeActivity.class);
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
                Intent intent = new Intent(UserFlight.this, UserActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MainActivity.rootRef.addAuthStateListener(authStateListener);
    }


    @Override
    protected void onPause() {
        super.onPause();
        MainActivity.rootRef.removeAuthStateListener(authStateListener);
    }


}

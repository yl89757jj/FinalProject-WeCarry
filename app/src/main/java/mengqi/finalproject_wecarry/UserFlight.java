package mengqi.finalproject_wecarry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

public class UserFlight extends AppCompatActivity {
    private Firebase.AuthStateListener authStateListener;
    private EditText departure;
    private EditText arrival;
    private EditText flightNo;
    private EditText departDate;
    private EditText spaceAvaible;
    private EditText specialNote;
    private Firebase userRef;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_flight);
        authStateListener = new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData == null) {
                    Intent intent = new Intent(UserFlight.this, LogInActivity.class);
                    startActivity(intent);
                } else
                    userRef = MainActivity.rootRef;
                userName = authData.getUid();
            }
        };
    }

    public void sumbitFlight(View view) {
        departure = (EditText) findViewById(R.id.departure_city);
        arrival = (EditText) findViewById(R.id.arrival_city);
        flightNo = (EditText) findViewById(R.id.fight_no);
        departDate = (EditText) findViewById(R.id.depart_date);
        spaceAvaible = (EditText) findViewById(R.id.space_available);
        specialNote = (EditText) findViewById(R.id.special_note);
        Flight flight = new Flight(departure.getText().toString(), arrival.getText().toString(), flightNo.getText().toString(), departDate.getText().toString(),
                spaceAvaible.getText().toString(), specialNote.getText().toString(), userName);
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

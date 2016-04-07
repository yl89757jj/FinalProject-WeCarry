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

public class UserCarry extends AppCompatActivity {
    private Firebase.AuthStateListener authStateListener;
    private EditText departureArea;
    private EditText arrivalArea;
    private EditText datePreferred;
    private EditText flexibility;
    private EditText whatToCarry;
    private Firebase userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_carry);
        authStateListener = new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData == null) {
                    Intent intent = new Intent(UserCarry.this, LogInActivity.class);
                    startActivity(intent);
                }
                userRef = MainActivity.rootRef.child("users/" + authData.getUid());
            }
        };
    }

    public void sumbitCarry(View view) {
        departureArea = (EditText) findViewById(R.id.departure_area);
        arrivalArea = (EditText) findViewById(R.id.arrival_area);
        datePreferred = (EditText) findViewById(R.id.date_preferred);
        flexibility = (EditText) findViewById(R.id.flexibility);
        whatToCarry = (EditText) findViewById(R.id.what_to_carry);
        Good goods = new Good(departureArea.getText().toString(), arrivalArea.getText().toString(), datePreferred.getText().toString(), flexibility.getText().toString(),
                whatToCarry.getText().toString());
        userRef.child("goods").push().setValue(goods);
        Intent intent = new Intent(UserCarry.this, HomeActivity.class);
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

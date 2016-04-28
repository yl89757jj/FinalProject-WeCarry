package mengqi.finalproject_wecarry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

public class UserActivity extends AppCompatActivity {
    private Firebase.AuthStateListener authStateListener;
    private Firebase userRef;
    private RecyclerView flightRecyclerView;
    private FlightsAdapter flightsAdapter;
    private GoodsAdapter goodsAdapter;
    private RecyclerView goodsRecyclerView;
    public String email;
    private String userName;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Firebase.setAndroidContext(this);

        authStateListener = new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData == null) {
                    Intent intent = new Intent(UserActivity.this, LogInActivity.class);
                    startActivity(intent);
                } else {
                    userRef = MainActivity.rootRef;
                    userName = authData.getUid().toString();
                    flightsAdapter = new FlightsAdapter(MainActivity.rootRef.child("flights"), UserActivity.this, 3, userName);
                    flightRecyclerView.setAdapter(flightsAdapter);

                    goodsAdapter = new GoodsAdapter(MainActivity.rootRef.child("goods"), UserActivity.this, 3, userName);
                    goodsRecyclerView.setAdapter(goodsAdapter);

                    flightRecyclerView.setHasFixedSize(true);
                    flightRecyclerView.setLayoutManager(new LinearLayoutManager(UserActivity.this));


                    goodsRecyclerView.setHasFixedSize(true);
                    goodsRecyclerView.setLayoutManager(new LinearLayoutManager(UserActivity.this));


                }
            }
        };

        flightRecyclerView = (RecyclerView) findViewById(R.id.flightRecycler_view);
        goodsRecyclerView = (RecyclerView) findViewById(R.id.goodsRecycler_view);




    }


    public void PostGoods(View view) {
        Intent intent = new Intent(UserActivity.this, UserCarry.class);
        startActivity(intent);
    }

    public void PostFlight(View view) {
        Intent intent = new Intent(UserActivity.this, UserFlight.class);
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
                Intent intent = new Intent(UserActivity.this, UserActivity.class);
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

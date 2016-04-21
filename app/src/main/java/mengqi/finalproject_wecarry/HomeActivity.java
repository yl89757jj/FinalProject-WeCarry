package mengqi.finalproject_wecarry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

public class HomeActivity extends AppCompatActivity {
    public static boolean fly;
    private RecyclerView flightRecyclerView;
    private FlightsAdapter flightsadapter;
    private GoodsAdapter goodsAdapter;
    private RecyclerView goodsRecyclerView;
    private Firebase.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Firebase.setAndroidContext(this);

        flightRecyclerView = (RecyclerView) findViewById(R.id.flightRecycler_view);
        goodsRecyclerView = (RecyclerView) findViewById(R.id.goodsRecycler_view);


        flightsadapter = new FlightsAdapter(MainActivity.rootRef.child("flights"), HomeActivity.this);
        flightRecyclerView.setAdapter(flightsadapter);
        goodsAdapter = new GoodsAdapter(MainActivity.rootRef.child("goods"), HomeActivity.this);
        goodsRecyclerView.setAdapter(goodsAdapter);


        flightRecyclerView.setHasFixedSize(true);
        flightRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        goodsRecyclerView.setHasFixedSize(true);
        goodsRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }


    public void SearchGoods(View view) {
        fly = true;
        Intent intent = new Intent(HomeActivity.this, SearchGoods.class);
        startActivity(intent);
    }

    public void SearchFlight(View view) {
        fly = false;
        Intent intent = new Intent(HomeActivity.this, SearchFlight.class);
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
}

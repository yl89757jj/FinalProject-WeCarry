package mengqi.finalproject_wecarry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firebase.client.Firebase;

public class HomeActivity extends AppCompatActivity {
    public static boolean fly;
    private RecyclerView flightRecyclerView;
    private FlightsAdapter flightsAdapter;
    private GoodsAdapter goodsAdapter;
    private RecyclerView goodsRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Firebase.setAndroidContext(this);


        flightRecyclerView = (RecyclerView) findViewById(R.id.flightRecycler_view);
        goodsRecyclerView = (RecyclerView) findViewById(R.id.goodsRecycler_view);


        flightsAdapter = new FlightsAdapter(MainActivity.rootRef.child("flights"), HomeActivity.this,1,"");
        flightRecyclerView.setAdapter(flightsAdapter);

        goodsAdapter = new GoodsAdapter(MainActivity.rootRef.child("goods"), HomeActivity.this,1,"");
        goodsRecyclerView.setAdapter(goodsAdapter);

        flightRecyclerView.setHasFixedSize(true);
        flightRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        goodsRecyclerView.setHasFixedSize(true);
        goodsRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }


    public void SearchGoods(View view) {
        fly = true;
        Intent intent = new Intent(HomeActivity.this, SearchResult.class);
        startActivity(intent);
    }

    public void SearchFlight(View view) {
        fly = false;
        Intent intent = new Intent(HomeActivity.this, SearchResult.class);
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
                Intent intent = new Intent(HomeActivity.this, UserActivity.class);
                startActivity(intent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

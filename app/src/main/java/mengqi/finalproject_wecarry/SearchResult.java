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

public class SearchResult extends AppCompatActivity {
    private RecyclerView RecyclerView;
    private FlightsAdapter flightsAdapter;
    private GoodsAdapter goodsAdapter;
    private TextView post;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        setTitle("Search Result");

        RecyclerView = (RecyclerView) findViewById(R.id.resultRecycler_view);

        flightsAdapter = new FlightsAdapter(MainActivity.rootRef.child("flights"), SearchResult.this, 2, "");
        goodsAdapter = new GoodsAdapter(MainActivity.rootRef.child("goods"), SearchResult.this, 2, "");
        if (HomeActivity.fly) {
            RecyclerView.setAdapter(goodsAdapter);
        } else {
            RecyclerView.setAdapter(flightsAdapter);
        }

        RecyclerView.setHasFixedSize(true);
        RecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }


    public void postMyFlight(View view) {
        Intent intent = new Intent(SearchResult.this, UserFlight.class);
        startActivity(intent);
    }

    public void postMyGoods(View view) {
        Intent intent = new Intent(SearchResult.this, UserCarry.class);
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
                Intent intent = new Intent(SearchResult.this, UserActivity.class);
                startActivity(intent);
            case R.id.home:
                Intent intent2 = new Intent(SearchResult.this, HomeActivity.class);
                startActivity(intent2);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

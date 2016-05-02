package mengqi.finalproject_wecarry;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.firebase.client.Firebase;

import java.util.Calendar;

public class HomeActivity extends AppCompatActivity {
    public static boolean fly;
    private Spinner departure;
    private Spinner arrival;
    private Button button;
    private int year, month, day;
    private String departDate;
    private static final int DILOG_ID = 0;
    private RecyclerView flightRecyclerView;
    private FlightsAdapter flightsAdapter;
    private GoodsAdapter goodsAdapter;
    private RecyclerView goodsRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Firebase.setAndroidContext(this);
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);



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


        departure = (Spinner) findViewById(R.id.departure_cityH);
        arrival = (Spinner) findViewById(R.id.arrival_cityH);


    }

    public void datePick(View view) {
        button = (Button) findViewById(R.id.depart_dateH);
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



    public void SearchGoods(View view) {
        fly = true;
        Intent intent = new Intent(HomeActivity.this, SearchResult.class);
        startActivity(intent);

        if(departure.getSelectedItem().toString().equals(null)){
            GoodsAdapter.departureArea ="";
        }else
            GoodsAdapter.departureArea = departure.getSelectedItem().toString();

        if(arrival.getSelectedItem().toString().equals(null)){
            GoodsAdapter.arrivalArea="";
        }else
            GoodsAdapter.arrivalArea = arrival.getSelectedItem().toString();

            departDate = month + "/" + day + "/" + year;
        if (departDate.equals(null)){
            GoodsAdapter.datePreferred = "";
        }else
            GoodsAdapter.datePreferred = departDate;

    }

    public void SearchFlight(View view) {
        fly = false;
        Intent intent = new Intent(HomeActivity.this, SearchResult.class);
        startActivity(intent);
        if(departure.getSelectedItem().toString().equals(null)){
            FlightsAdapter.departure="";
        }else
            FlightsAdapter.departure = departure.getSelectedItem().toString();

        if(arrival.getSelectedItem().toString().equals(null)){
            FlightsAdapter.arrival="";
        }else
            FlightsAdapter.arrival = arrival.getSelectedItem().toString();

        departDate = month + "/" + day + "/" + year;
        if(departDate.equals("0/0/0")){
            FlightsAdapter.departDate ="";
        }else
            FlightsAdapter.departDate = departDate;
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

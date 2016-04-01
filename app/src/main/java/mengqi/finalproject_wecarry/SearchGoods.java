package mengqi.finalproject_wecarry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class SearchGoods extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_goods);
        setTitle("I Will Fly");
    }

    public void SearchGoods(View view) {
        Intent intent = new Intent(SearchGoods.this, SearchResult.class);
        startActivity(intent);
    }
}

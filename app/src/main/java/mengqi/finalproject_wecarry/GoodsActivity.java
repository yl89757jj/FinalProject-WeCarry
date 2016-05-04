package mengqi.finalproject_wecarry;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class GoodsActivity extends AppCompatActivity {
    private TextView departureArea;
    private TextView arrivalArea;
    private TextView datePreferred;
    private TextView flexibility;
    private TextView whatToCarry;
    private EditText edit_message;
    private ImageView photo;
    private String toEmail;
    private String message;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        intent = getIntent();
        Bundle extras = intent.getExtras();
//        Good selectedGood= (Good) intent.getParcelableExtra("GOOD");

        edit_message = (EditText) findViewById(R.id.message1);
        departureArea = (TextView) findViewById(R.id.select_departure);
        arrivalArea = (TextView) findViewById(R.id.select_arrival);
        datePreferred = (TextView) findViewById(R.id.select_date);
        flexibility = (TextView) findViewById(R.id.select_flexibility);
        whatToCarry = (TextView) findViewById(R.id.select_what_to_carry);
        photo = (ImageView) findViewById(R.id.select_photo);

        toEmail = extras.getString("EXEM");
        departureArea.setText(extras.getString("EXDE"));
        arrivalArea.setText(extras.getString("EXAR"));
        datePreferred.setText(extras.getString("EXDA"));
        flexibility.setText(extras.getString("EXFL"));
        whatToCarry.setText(extras.getString("EXWH"));
        photo.setImageBitmap(byteStringToBitmap(extras.getString("EXPO")));

//        departureArea.setText(selectedGood.departureArea);
//        arrivalArea.setText(selectedGood.arrivalArea);
//        datePreferred.setText(selectedGood.datePreferred);
//        flexibility.setText(selectedGood.flexibility);
//        whatToCarry.setText(selectedGood.whatToCarry);
//        photo.setImageBitmap(byteStringToBitmap(selectedGood.photo));
//        toEmail=selectedGood.userEmail;
    }

    private Bitmap byteStringToBitmap(String byteString) {
            byte[] imageAsByte = Base64.decode(byteString.getBytes(), Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(imageAsByte, 0, imageAsByte.length);
    }


    public void sendEmail(View view) {
        message = edit_message.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
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
                Intent intent = new Intent(GoodsActivity.this, UserActivity.class);
                startActivity(intent);
            case R.id.home:
                Intent intent2 = new Intent(GoodsActivity.this, HomeActivity.class);
                startActivity(intent2);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

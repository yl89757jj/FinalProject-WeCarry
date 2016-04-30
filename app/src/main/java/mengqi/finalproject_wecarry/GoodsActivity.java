package mengqi.finalproject_wecarry;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GoodsActivity extends AppCompatActivity {
    private TextView departureArea;
    private TextView arrivalArea;
    private TextView datePreferred;
    private TextView flexibility;
    private TextView whatToCarry;
    private ImageView photo;
    private String myEmail;
    private String toEmail;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        departureArea = (TextView) findViewById(R.id.select_departure);
        arrivalArea = (TextView) findViewById(R.id.select_arrival);
        datePreferred = (TextView) findViewById(R.id.select_date);
        flexibility = (TextView) findViewById(R.id.select_flexibility);
        whatToCarry = (TextView) findViewById(R.id.select_what_to_carry);
        photo = (ImageView) findViewById(R.id.select_photo);
    }


    private void SendEmail() {
        String[] TO = {toEmail};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Message From WeCarry");
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(GoodsActivity.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }

}

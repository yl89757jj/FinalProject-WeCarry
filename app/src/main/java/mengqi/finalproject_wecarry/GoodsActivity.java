package mengqi.finalproject_wecarry;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
        intent=getIntent();
        Bundle extras = intent.getExtras();

        edit_message=(EditText)findViewById(R.id.message1) ;
        departureArea = (TextView) findViewById(R.id.select_departure);
        arrivalArea = (TextView) findViewById(R.id.select_arrival);
        datePreferred = (TextView) findViewById(R.id.select_date);
        flexibility = (TextView) findViewById(R.id.select_flexibility);
        whatToCarry = (TextView) findViewById(R.id.select_what_to_carry);
        photo = (ImageView) findViewById(R.id.select_photo);

        toEmail=extras.getString("EXEM");


        departureArea.setText(extras.getString("EXDE"));
        arrivalArea.setText(extras.getString("EXAR"));
        datePreferred.setText(extras.getString("EXDA"));
        flexibility.setText(extras.getString("EXFL"));
        whatToCarry.setText(extras.getString("EXWH"));


    }


    public void sendEmail(View view) {
        message = edit_message.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{toEmail});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Message From WeCarry");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(intent);

//        Intent emailIntent = new Intent(Intent.ACTION_SEND);
//        emailIntent.setData(Uri.parse("mailto:"+toEmail));
//        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Message From WeCarry");
//        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

//        try {
//            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
//            finish();
//        } catch (android.content.ActivityNotFoundException ex) {
//            Toast.makeText(GoodsActivity.this,
//                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
//        }
    }

}

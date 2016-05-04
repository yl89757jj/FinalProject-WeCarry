package mengqi.finalproject_wecarry;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;

public class UserCarry extends AppCompatActivity {
    private Firebase.AuthStateListener authStateListener;
    private static final int REQUEST_TAKE_PHOTO = 1;
    private static final int REQUEST_PICK_PHOTO = 2;
    private Spinner departureAreaSpinner;
    private Spinner arrivalAreaSpinner;
    private String datePreferred;
    private Spinner flexibility;
    private EditText whatToCarryEditText;
    private Firebase userRef;
    private String userEmail;
    private Button button;
    private int year, month, day;
    private static final int DILOG_ID = 0;
    private ImageView PhotoImageView;
    private File photoFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_carry);
        final Calendar calendar = Calendar.getInstance();
        button = (Button) findViewById(R.id.date_preferred);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        PhotoImageView = (ImageView) findViewById(R.id.image_photo);
        authStateListener = new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                if (authData == null) {
                    Intent intent = new Intent(UserCarry.this, LogInActivity.class);
                    startActivity(intent);
                } else {
                    userRef = MainActivity.rootRef;
                    userEmail=authData.getProviderData().get("email").toString();

                }
            }
        };
    }


    public void datePick(View view) {
        button = (Button) findViewById(R.id.date_preferred);
        showDialog(DILOG_ID);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DILOG_ID)
            return new DatePickerDialog(this, datePickListener, year, month, day);
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int years, int monthOfYear, int dayOfMonth) {
            year = years;
            month = monthOfYear + 1;
            day = dayOfMonth;
            button.setText(month + "/" + day + "/" + year);
        }
    };

    public void sumbitCarry(View view) {
        departureAreaSpinner = (Spinner) findViewById(R.id.departure_area);
        arrivalAreaSpinner = (Spinner) findViewById(R.id.arrival_area);
        datePreferred = button.getText().toString();
        flexibility = (Spinner) findViewById(R.id.flexibility);
        String byteString = bitmapToByteString(((BitmapDrawable) PhotoImageView.getDrawable()).getBitmap());
        whatToCarryEditText = (EditText) findViewById(R.id.what_to_carry);

        if (departureAreaSpinner.getSelectedItem().toString().equals("") || arrivalAreaSpinner.getSelectedItem().toString().equals("") || datePreferred.equals("") || whatToCarryEditText.getText().toString().equals("")) {
            Toast.makeText(UserCarry.this, "Please enter your required information.", Toast.LENGTH_LONG).show();
        }else {
            Good goods = new Good(departureAreaSpinner.getSelectedItem().toString(), arrivalAreaSpinner.getSelectedItem().toString(), datePreferred, flexibility.getSelectedItem().toString(),
                    whatToCarryEditText.getText().toString(), userEmail, byteString);
        userRef.child("goods").push().setValue(goods);
        Intent intent = new Intent(UserCarry.this, HomeActivity.class);
        startActivity(intent);}
    }

    public void takeImage(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        photoFile = createImageFile();//tell the camera where to save the image
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
        startActivityForResult(intent, REQUEST_TAKE_PHOTO);
    }

    private File createImageFile() {
        String imageFileName = "JPEG" + System.currentTimeMillis() + ".jpg";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return new File(storageDir.getAbsolutePath(), imageFileName);
    }

    public void addImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, REQUEST_PICK_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK)
            return;
        if (requestCode == REQUEST_TAKE_PHOTO) {
            setPic();
        } else if (requestCode == REQUEST_PICK_PHOTO) {
            try {
                decodeUri(data.getData());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void setPic() {
        int targetW = PhotoImageView.getWidth();
        int targetH = PhotoImageView.getHeight();

        BitmapFactory.Options bmpOptions = new BitmapFactory.Options();
        bmpOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photoFile.getAbsolutePath(), bmpOptions);

        int photoW = bmpOptions.outWidth;
        int photoH = bmpOptions.outHeight;
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        bmpOptions.inJustDecodeBounds = false;
        bmpOptions.inSampleSize = scaleFactor;
        Bitmap image = BitmapFactory.decodeFile(photoFile.getAbsolutePath(), bmpOptions);
        PhotoImageView.setImageBitmap(image);
    }


    private void decodeUri(Uri uri) throws FileNotFoundException {
        int targetW = PhotoImageView.getWidth();
        int targetH = PhotoImageView.getHeight();

        BitmapFactory.Options bmpOptions = new BitmapFactory.Options();
        bmpOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, bmpOptions);

        int photoW = bmpOptions.outWidth;
        int photoH = bmpOptions.outHeight;
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        bmpOptions.inJustDecodeBounds = false;
        bmpOptions.inSampleSize = scaleFactor;
        Bitmap image = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, bmpOptions);
        PhotoImageView.setImageBitmap(image);
    }

    private String bitmapToByteString(Bitmap bitmap) {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteStream);
        byte[] byteArray = byteStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
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
                Intent intent = new Intent(UserCarry.this, UserActivity.class);
                startActivity(intent);
            case R.id.home:
                Intent intent2 = new Intent(UserCarry.this, HomeActivity.class);
                startActivity(intent2);
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
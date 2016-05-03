package mengqi.finalproject_wecarry;

/**
 * Created by Mengqi on 3/23/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

public class LogInActivity extends AppCompatActivity {
    private Firebase firebase;
    private EditText emailEditText;
    private EditText passwordEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        setTitle("Log In");
        Firebase.setAndroidContext(this);
        firebase = new Firebase("https://wecarry.firebaseio.com");
        emailEditText = (EditText) findViewById(R.id.edit_text_email);
        passwordEditText = (EditText) findViewById(R.id.edit_text_password);
    }

    public void LogIn(View view) {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        firebase.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                Toast.makeText(LogInActivity.this, "You are logged in", Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                Toast.makeText(LogInActivity.this, "Unable to log in" + firebaseError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void SignUp(View view) {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (email.equals("")) {
            Toast.makeText(LogInActivity.this, "Please enter your email", Toast.LENGTH_LONG).show();
        } else if (email.substring(email.length() - 4, email.length()).equals(".edu")) {
            firebase.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {
                @Override
                public void onSuccess(Map<String, Object> result) {
                    Toast.makeText(LogInActivity.this, "Successfully created user account with uid: " + result.get("uid"), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onError(FirebaseError firebaseError) {
                    Toast.makeText(LogInActivity.this, "Unable to Sign Up: " + firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else if (password.equals("")) {
            Toast.makeText(LogInActivity.this, "Please enter your password", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(LogInActivity.this, "Please Sign up with a \".edu\" email", Toast.LENGTH_LONG).show();
        }

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
                Intent intent = new Intent(LogInActivity.this, UserActivity.class);
                startActivity(intent);
            case R.id.home:
                Intent intent2 = new Intent(LogInActivity.this, HomeActivity.class);
                startActivity(intent2);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


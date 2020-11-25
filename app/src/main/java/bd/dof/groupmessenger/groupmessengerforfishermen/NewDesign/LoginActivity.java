package bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import bd.dof.groupmessenger.groupmessengerforfishermen.R;

public class LoginActivity extends AppCompatActivity {
    private EditText mobileNumber, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mobileNumber = findViewById(R.id.mobile_number);
        password = findViewById(R.id.password);
    }

    public void submitNow(View view) {
        if (!TextUtils.isEmpty(mobileNumber.getText().toString()) && !TextUtils.isEmpty(password.getText().toString())) {

            startActivity(new Intent(LoginActivity.this, HomeScreenActivity.class));
            LoginActivity.this.overridePendingTransition(0, 0);
            LoginActivity.this.finish();
        } else {
            Toast.makeText(this, "Please Enter Mobile & Password Correctly", Toast.LENGTH_SHORT).show();

        }
    }
}
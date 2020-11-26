package bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import bd.dof.groupmessenger.groupmessengerforfishermen.R;

public class LoginActivity extends AppCompatActivity {
    private EditText mobileNumber, password;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences pref = LoginActivity.this.getApplicationContext().getSharedPreferences("MyPref", 0);
        editor = pref.edit();
        mobileNumber = findViewById(R.id.mobile_number);
        password = findViewById(R.id.password);
    }

    public void submitNow(View view) {
        if (!TextUtils.isEmpty(mobileNumber.getText().toString()) && !TextUtils.isEmpty(password.getText().toString())) {
            editor.putString("log", "true");
            editor.apply();
            startActivity(new Intent(LoginActivity.this, HomeScreenActivity.class));
            LoginActivity.this.overridePendingTransition(0, 0);
            LoginActivity.this.finish();
        } else {
            Toast.makeText(this, "Please Enter Mobile & Password Correctly", Toast.LENGTH_SHORT).show();

        }
    }
}
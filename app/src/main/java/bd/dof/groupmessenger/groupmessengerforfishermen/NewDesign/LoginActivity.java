package bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Objects;

import bd.dof.groupmessenger.groupmessengerforfishermen.API.OurClient;
import bd.dof.groupmessenger.groupmessengerforfishermen.API.OurServerClient;
import bd.dof.groupmessenger.groupmessengerforfishermen.R;
import bd.dof.groupmessenger.groupmessengerforfishermen.Response.defaultResponse;
import bd.dof.groupmessenger.groupmessengerforfishermen.Response.userResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText mobileNumber, password;
    private SharedPreferences.Editor editor;
    private OurClient ourClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ourClient = OurServerClient.cteateService(OurClient.class);
        SharedPreferences pref = LoginActivity.this.getApplicationContext().getSharedPreferences("MyPref", 0);
        editor = pref.edit();
        mobileNumber = findViewById(R.id.mobile_number);
        password = findViewById(R.id.password);
    }

    public void submitNow(View view) {
        if (!TextUtils.isEmpty(mobileNumber.getText().toString()) && !TextUtils.isEmpty(password.getText().toString())) {

            ourClient.getLogin(mobileNumber.getText().toString(),password.getText().toString()).enqueue(new Callback<ArrayList<userResponse>>() {
                @Override
                public void onResponse(Call<ArrayList<userResponse>> call, Response<ArrayList<userResponse>> response) {
                    if(!TextUtils.isEmpty(response.body().get(0).getId())){
                        editor.putString("log", "true");
                        editor.putString("division_id",response.body().get(0).getDivision_id() );
                        editor.putString("district_id", response.body().get(0).getDistrict_id());
                        editor.putString("upazilla_id", response.body().get(0).getUpazilla_id());
                        editor.putString("user_name", response.body().get(0).getUser_name());
                        editor.putString("user_phone", response.body().get(0).getUser_phone());
                        editor.putString("user_password", response.body().get(0).getUser_password());
                        editor.putString("status", response.body().get(0).getUser_password());
                        editor.putString("date", response.body().get(0).getDate());
                        editor.putString("time",response.body().get(0).getTime());
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this, HomeScreenActivity.class));
                        LoginActivity.this.overridePendingTransition(0, 0);
                        LoginActivity.this.finish();
                    }else {

                        Toast.makeText(LoginActivity.this, "Failed To Login !", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<userResponse>> call, Throwable t) {

                }
            });

        } else {
            Toast.makeText(this, "Please Enter Mobile & Password Correctly", Toast.LENGTH_SHORT).show();

        }
    }
}
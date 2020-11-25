package bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import bd.dof.groupmessenger.groupmessengerforfishermen.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                startActivity(new Intent(SplashScreenActivity.this,HomeScreenActivity.class));
            }
        }, 2000);
    }
}
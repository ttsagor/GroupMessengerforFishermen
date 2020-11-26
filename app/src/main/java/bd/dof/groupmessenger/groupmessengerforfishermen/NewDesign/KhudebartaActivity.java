package bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import bd.dof.groupmessenger.groupmessengerforfishermen.AddTemplate;
import bd.dof.groupmessenger.groupmessengerforfishermen.MainActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.R;
import bd.dof.groupmessenger.groupmessengerforfishermen.fishFarming;
import bd.dof.groupmessenger.groupmessengerforfishermen.groupsmsfilter;

public class KhudebartaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khudebarta);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KhudebartaActivity.this, HomeScreenActivity.class));
            }
        });

        EditText title = findViewById(R.id.title);
        title.setText("খুদেবার্তা  ");
        bottomNavigationHandler();
    }

    public void khudebartaperonSystem(View view) {

        startActivity(new Intent(KhudebartaActivity.this, groupsmsfilter.class));

    }

    public void khudebartsongjojonSystem(View view) {
    startActivity(new Intent(KhudebartaActivity.this , AddTemplate.class));
    }
    private void bottomNavigationHandler() {

        SharedPreferences pref = this.getApplicationContext().getSharedPreferences("MyPref", 0);
        final String  log = pref.getString("log", "");
        final SharedPreferences.Editor editor   = pref.edit();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.getMenu().clear();
        bottomNavigationView.inflateMenu(R.menu.new_bottom_menu_home);
        if (log.equals("true")) {

            bottomNavigationView.getMenu().removeItem(R.id.menu_sotorkota);

            bottomNavigationView.getMenu().getItem(3).setTitle("লগআউট");
        } else {
            bottomNavigationView.getMenu().removeItem(R.id.menu_profile);



        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.menu_login:
                        if (log.equals("true")) {
                            editor.putString("log", "false");
                            editor.apply();
                            startActivity(new Intent(KhudebartaActivity.this, SplashScreenActivity.class));

                        } else {
                            startActivity(new Intent(KhudebartaActivity.this, LoginActivity.class));
                        }
                        break;

                    case R.id.menu_sotorkota:
                        startActivity(new Intent(KhudebartaActivity.this, SotorkotaActivity.class));
                        break;
                    case R.id.menu_profile:
                        startActivity(new Intent(KhudebartaActivity.this, ProfileActivity.class));
                        break;
                    case R.id.menu_beboharbidi:
                        startActivity(new Intent(KhudebartaActivity.this, BaboharbidiActivity.class));
                        break;

                    case R.id.menu_somoshajomadin:
                        startActivity(new Intent(KhudebartaActivity.this, ComingSoonActivity.class));
                        break;
                }
                return false;
            }
        });
    }
}
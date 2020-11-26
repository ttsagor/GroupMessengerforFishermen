package bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import bd.dof.groupmessenger.groupmessengerforfishermen.AddFarmerPersonalInformation;
import bd.dof.groupmessenger.groupmessengerforfishermen.AddUpdateUser;
import bd.dof.groupmessenger.groupmessengerforfishermen.R;
import bd.dof.groupmessenger.groupmessengerforfishermen.farmerSearchActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.groupsmsfilter;

public class ChasiOnusandhanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chasi_onusandhan);
        bottomNavigationHandler();
    }

    public void chashionusandhanSystem(View view) {
        startActivity(new Intent(ChasiOnusandhanActivity.this, farmerSearchActivity.class));
    }

    public void chasisongjojonSystem(View view) {
        startActivity(new Intent(ChasiOnusandhanActivity.this, AddUpdateUser.class));
    }

    public  void chasiEditSystem(View view){

        startActivity(new Intent(ChasiOnusandhanActivity.this, EditUserActivity.class));
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
                            startActivity(new Intent(ChasiOnusandhanActivity.this, SplashScreenActivity.class));

                        } else {
                            startActivity(new Intent(ChasiOnusandhanActivity.this, LoginActivity.class));
                        }
                        break;

                    case R.id.menu_sotorkota:
                        startActivity(new Intent(ChasiOnusandhanActivity.this, SotorkotaActivity.class));
                        break;
                    case R.id.menu_profile:
                        startActivity(new Intent(ChasiOnusandhanActivity.this, ProfileActivity.class));
                        break;
                    case R.id.menu_beboharbidi:
                        startActivity(new Intent(ChasiOnusandhanActivity.this, BaboharbidiActivity.class));
                        break;

                    case R.id.menu_somoshajomadin:
                        startActivity(new Intent(ChasiOnusandhanActivity.this, ComingSoonActivity.class));
                        break;
                }
                return false;
            }
        });
    }
}
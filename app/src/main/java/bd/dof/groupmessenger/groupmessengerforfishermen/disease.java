package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.BaboharbidiActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.ComingSoonActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.EditUserActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.HomeScreenActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.LoginActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.ProfileActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.SotorkotaActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.SplashScreenActivity;

public class disease extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(disease.this, HomeScreenActivity.class));
            }
        });

        EditText title = findViewById(R.id.title);
        title.setText("রোগসমূহ");
        bottomNavigationHandler();
        final DbHandler db = new DbHandler(this, null, null, 1);

        GridView diseaseListListview = findViewById(R.id.diseaseListListview);

        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);


        ArrayList<DiseaseModel> diseaseList = db.getDiseases("1=1");

        for (DiseaseModel cDisease : diseaseList) {
            System.out.println(cDisease.getDiseaseID() + ": " + cDisease.getDiseaseName());
        }

        DiseaseListAdapter adapter = new DiseaseListAdapter(this, diseaseList);
        diseaseListListview.setAdapter(adapter);
    }

    private void bottomNavigationHandler() {

        SharedPreferences pref = this.getApplicationContext().getSharedPreferences("MyPref", 0);
        final String log = pref.getString("log", "");
        final SharedPreferences.Editor editor = pref.edit();
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
                            startActivity(new Intent(disease.this, SplashScreenActivity.class));

                        } else {
                            startActivity(new Intent(disease.this, LoginActivity.class));
                        }
                        break;

                    case R.id.menu_sotorkota:
                        startActivity(new Intent(disease.this, SotorkotaActivity.class));
                        break;
                    case R.id.menu_profile:
                        startActivity(new Intent(disease.this, ProfileActivity.class));
                        break;
                    case R.id.menu_beboharbidi:
                        startActivity(new Intent(disease.this, BaboharbidiActivity.class));
                        break;

                    case R.id.menu_somoshajomadin:
                        startActivity(new Intent(disease.this, ComingSoonActivity.class));
                        break;
                }
                return false;
            }
        });
    }
}

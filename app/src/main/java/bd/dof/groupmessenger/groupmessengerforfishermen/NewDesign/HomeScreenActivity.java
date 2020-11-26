package bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Objects;

import bd.dof.groupmessenger.groupmessengerforfishermen.DbHandler;
import bd.dof.groupmessenger.groupmessengerforfishermen.FarmerInfoModel;
import bd.dof.groupmessenger.groupmessengerforfishermen.InventorInformation;
import bd.dof.groupmessenger.groupmessengerforfishermen.MainActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.R;
import bd.dof.groupmessenger.groupmessengerforfishermen.SystemInformationModel;
import bd.dof.groupmessenger.groupmessengerforfishermen.UnionModel;
import bd.dof.groupmessenger.groupmessengerforfishermen.disease;
import bd.dof.groupmessenger.groupmessengerforfishermen.fishFarming;

public class HomeScreenActivity extends AppCompatActivity implements View.OnClickListener {
    public static String divisionID = "10";
    public static String districtID = "78";
    public static String upazillaID = "66";
    public static ArrayList<FarmerInfoModel> allFarmerInfo = new ArrayList<FarmerInfoModel>();
    public static ArrayList<UnionModel> allUnion = new ArrayList<UnionModel>();
    public static String log;
    private LinearLayout logged_id, loggedout_id;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        SharedPreferences pref = HomeScreenActivity.this.getApplicationContext().getSharedPreferences("MyPref", 0);
        log = pref.getString("log", "");
        editor = pref.edit();
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
        logged_id = findViewById(R.id.logged_id);
        loggedout_id = findViewById(R.id.logged_out);
        findViewById(R.id.ll_mosso_sutro).setOnClickListener(this);
        findViewById(R.id.ll_invention).setOnClickListener(this);
        findViewById(R.id.ll_desease).setOnClickListener(this);
        findViewById(R.id.ll_khude_barta_peron).setOnClickListener(this);
        findViewById(R.id.ll_chasi_onusandhan).setOnClickListener(this);
        findViewById(R.id.ll_mosso_chas).setOnClickListener(this);
        findViewById(R.id.ll_totto_kos).setOnClickListener(this);
        findViewById(R.id.ll_prosno_bank).setOnClickListener(this);
        DbHandler db = new DbHandler(this, null, null, 1);
        SystemInformationModel cSystemInfo = db.getSystemInfo();
        divisionID = cSystemInfo.getUserDivisionID();
        districtID = cSystemInfo.getUserDistrictID();
        upazillaID = cSystemInfo.getUserUpazillaID();
        allUnion = new ArrayList<UnionModel>();
        allFarmerInfo = new ArrayList<FarmerInfoModel>();
        allUnion = db.getAllUnion(HomeScreenActivity.divisionID, HomeScreenActivity.districtID, HomeScreenActivity.upazillaID);
        allFarmerInfo = db.getAllFarmerInfoBy();
        bottomNavigationHandler();

        if (log.equals("true")) {
            logged_id.setVisibility(View.VISIBLE);
            loggedout_id.setVisibility(View.GONE);
        } else {
            logged_id.setVisibility(View.GONE);
            loggedout_id.setVisibility(View.VISIBLE);
        }

    }

    private void bottomNavigationHandler() {
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
                            startActivity(new Intent(HomeScreenActivity.this, SplashScreenActivity.class));
                            HomeScreenActivity.this.finish();
                        } else {
                            startActivity(new Intent(HomeScreenActivity.this, LoginActivity.class));
                        }
                        break;

                    case R.id.menu_sotorkota:
                        startActivity(new Intent(HomeScreenActivity.this, SotorkotaActivity.class));
                        break;
                    case R.id.menu_profile:
                        startActivity(new Intent(HomeScreenActivity.this, ProfileActivity.class));
                        break;
                    case R.id.menu_beboharbidi:
                        startActivity(new Intent(HomeScreenActivity.this, BaboharbidiActivity.class));
                        break;

                    case R.id.menu_somoshajomadin:
                        startActivity(new Intent(HomeScreenActivity.this, ComingSoonActivity.class));
                        break;
                }
                return false;
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_mosso_sutro:
                startActivity(new Intent(HomeScreenActivity.this, fishFarming.class));

                break;
            case R.id.ll_invention:
                startActivity(new Intent(HomeScreenActivity.this, InventorInformation.class));
                break;
            case R.id.ll_desease:
                startActivity(new Intent(HomeScreenActivity.this, disease.class));
                break;
            case R.id.ll_khude_barta_peron:
                startActivity(new Intent(HomeScreenActivity.this, KhudebartaActivity.class));
                break;
            case R.id.ll_chasi_onusandhan:
                startActivity(new Intent(HomeScreenActivity.this, ChasiOnusandhanActivity.class));
                break;

            case R.id.ll_totto_kos:
                startActivity(new Intent(HomeScreenActivity.this, SotorkotaActivity.class));
                break;
            case R.id.ll_mosso_chas:
            case R.id.ll_prosno_bank:
                startActivity(new Intent(HomeScreenActivity.this, ComingSoonActivity.class));
                break;
        }
    }


    private void requestSmsPermission() {
        String permission = Manifest.permission.RECEIVE_SMS;
        int grant = ContextCompat.checkSelfPermission(this, permission);
        if (grant != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[1];
            permission_list[0] = permission;
            ActivityCompat.requestPermissions(this, permission_list, 1);
        }
    }
}
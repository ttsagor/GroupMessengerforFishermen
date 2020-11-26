package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.BaboharbidiActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.ComingSoonActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.EditUserActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.HomeScreenActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.LoginActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.ProfileActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.SotorkotaActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.SplashScreenActivity;

public class AreaFormula extends AppCompatActivity {

    TextView area_formula_length_text;
    TextView area_formula_height_text;
    TextView area_formula_width_text;
    TextView area_formula_sotangso_text;
    TextView area_formula_sotangso;
    TextView area_formula_hector_text;
    TextView area_formula_hector;

    EditText area_formula_length;
    EditText area_formula_height;
    EditText area_formula_width;

    AppCompatButton area_formula_agePonaSubmit;

    ImageView area_formula_SaveImg;
    ImageView area_formula_ShareImg;

    TextView top_head_fish_farming_area_formula;
    TextView top_head_app_fish_farming_area_formula;
    TextView top_farming_banner_area_formula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_area_formula);
        bottomNavigationHandler();
        area_formula_sotangso_text = (TextView) findViewById(R.id.area_formula_sotangso_text);
        area_formula_sotangso = (TextView) findViewById(R.id.area_formula_sotangso);
        area_formula_hector_text = (TextView) findViewById(R.id.area_formula_hector_text);
        area_formula_hector = (TextView) findViewById(R.id.area_formula_hector);

        area_formula_length = (EditText) findViewById(R.id.area_formula_length);
        area_formula_height = (EditText) findViewById(R.id.area_formula_height);
        area_formula_width = (EditText) findViewById(R.id.area_formula_width);

        area_formula_ShareImg = (ImageView) findViewById(R.id.area_formula_ShareImg);

        area_formula_agePonaSubmit = findViewById(R.id.area_formula_agePonaSubmit);

        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);


        area_formula_agePonaSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int area_formula_length_int = Integer.parseInt(area_formula_length.getText().toString());
                    int area_formula_height_int = Integer.parseInt(area_formula_height.getText().toString());
                    int area_formula_width_int = Integer.parseInt(area_formula_width.getText().toString());

                    double area_out = area_formula_length_int * area_formula_height_int * area_formula_width_int;
                    double area_out1 = area_out * 28.3;

                    area_formula_sotangso.setText(pona_mojud.engToBng(String.format("%.3f", area_out)));
                    area_formula_hector.setText(pona_mojud.engToBng(String.format("%.3f", area_out1)));

                    if (getCurrentFocus() != null) {
                        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    }

                } catch (Exception e) {
                }
            }
        });

        area_formula_ShareImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");

                    String des = "পুকুরের দৈর্ঘ্য: " + pona_mojud.engToBng(area_formula_length.getText().toString()) + "\n" +
                            "পুকুরের উচ্চতা: " + pona_mojud.engToBng(area_formula_height.getText().toString()) + "\n" +
                            "পুকুরের প্রস্থ: " + pona_mojud.engToBng(area_formula_width.getText().toString()) + "\n" +
                            "পুকুরের আয়তন (ঘনফুট): " + area_formula_sotangso.getText().toString() + "\n" +
                            "পুকুরের আয়তন (লিটার): " + area_formula_hector.getText();

                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "পুকুরের আয়তন নির্নয় ফরমুলা");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, des);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                } catch (Exception e) {
                    //Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                    //System.out.println( e.toString());
                }
            }
        });

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AreaFormula.this, HomeScreenActivity.class));
            }
        });

        EditText title = findViewById(R.id.title);
        title.setText("পুকুরের আয়তন নির্নয়");
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
                            startActivity(new Intent(AreaFormula.this, SplashScreenActivity.class));

                        } else {
                            startActivity(new Intent(AreaFormula.this, LoginActivity.class));
                        }
                        break;

                    case R.id.menu_sotorkota:
                        startActivity(new Intent(AreaFormula.this, SotorkotaActivity.class));
                        break;
                    case R.id.menu_profile:
                        startActivity(new Intent(AreaFormula.this, ProfileActivity.class));
                        break;
                    case R.id.menu_beboharbidi:
                        startActivity(new Intent(AreaFormula.this, BaboharbidiActivity.class));
                        break;

                    case R.id.menu_somoshajomadin:
                        startActivity(new Intent(AreaFormula.this, ComingSoonActivity.class));
                        break;
                }
                return false;
            }
        });
    }
}

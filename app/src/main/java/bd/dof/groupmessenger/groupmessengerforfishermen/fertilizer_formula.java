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
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.LoginActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.ProfileActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.SotorkotaActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.SplashScreenActivity;

public class fertilizer_formula extends AppCompatActivity {

    TextView fertilizer_length_text;
    TextView fertilizer_width_text;
    TextView fertilizer_qunatity_text;
    TextView fertilizer_area_text;
    TextView fertilizer_area;

    EditText fertilizer_length;
    EditText fertilizer_width;
    EditText fertilizer_qunatity;

    AppCompatButton fertilizer_agePonaSubmit;

    ImageView fertilizer_SaveImg;
    ImageView fertilizer_ShareImg;

    TextView top_farming_banner_fertilizer;
    TextView top_head_app_fish_farming_fertilizer;
    TextView top_head_fish_farming_fertilizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fertilizer_formula);
bottomNavigationHandler();
        fertilizer_area_text = (TextView) findViewById(R.id.fertilizer_area_text);
        fertilizer_area = (TextView) findViewById(R.id.fertilizer_area);

        fertilizer_length = (EditText) findViewById(R.id.fertilizer_length);
        fertilizer_width = (EditText) findViewById(R.id.fertilizer_width);
        fertilizer_qunatity = (EditText) findViewById(R.id.fertilizer_qunatity);

        fertilizer_agePonaSubmit =  findViewById(R.id.fertilizer_agePonaSubmit);

        fertilizer_SaveImg = (ImageView) findViewById(R.id.fertilizer_SaveImg);
        fertilizer_ShareImg = (ImageView) findViewById(R.id.fertilizer_ShareImg);



        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);




        fertilizer_agePonaSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int fertilizer_length_int = Integer.parseInt(fertilizer_length.getText().toString());
                    int fertilizer_width_int = Integer.parseInt(fertilizer_width.getText().toString());
                    int fertilizer_qunatity_int = Integer.parseInt(fertilizer_qunatity.getText().toString());

                    double area_out = (((fertilizer_length_int*fertilizer_width_int)/435.5)*fertilizer_qunatity_int)/1000;


                    fertilizer_area.setText(pona_mojud.engToBng(String.format("%.3f", area_out)));

                    if(getCurrentFocus()!=null) {
                        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    }

                }catch (Exception e){}
            }
        });

        fertilizer_ShareImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");

                    String des = "পুকুরের দৈর্ঘ্য: " +pona_mojud.engToBng(fertilizer_length.getText().toString())+"\n"+
                            "পুকুরের প্রস্থ: " + pona_mojud.engToBng(fertilizer_width.getText().toString())+"\n"+
                            "পরিমান(গ্রাম)/শতাংশ: " + pona_mojud.engToBng(fertilizer_qunatity.getText().toString())+"\n"+
                            "মোট চুন/সারের পরিমান (কেজি): " + fertilizer_area.getText();

                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"পুকুরের চুন/সার নির্নয় ফরমুলা");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,des);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                } catch (Exception e) {
                    //Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                    //System.out.println( e.toString());
                }
            }
        });

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
                            startActivity(new Intent(fertilizer_formula.this, SplashScreenActivity.class));

                        } else {
                            startActivity(new Intent(fertilizer_formula.this, LoginActivity.class));
                        }
                        break;

                    case R.id.menu_sotorkota:
                        startActivity(new Intent(fertilizer_formula.this, SotorkotaActivity.class));
                        break;
                    case R.id.menu_profile:
                        startActivity(new Intent(fertilizer_formula.this, ProfileActivity.class));
                        break;
                    case R.id.menu_beboharbidi:
                        startActivity(new Intent(fertilizer_formula.this, BaboharbidiActivity.class));
                        break;

                    case R.id.menu_somoshajomadin:
                        startActivity(new Intent(fertilizer_formula.this, ComingSoonActivity.class));
                        break;
                }
                return false;
            }
        });
    }
}

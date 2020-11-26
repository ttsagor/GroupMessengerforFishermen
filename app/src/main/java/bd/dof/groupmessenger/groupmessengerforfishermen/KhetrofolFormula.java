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

public class KhetrofolFormula extends AppCompatActivity {

    TextView khetrofol_length_text;
    TextView khetrofol_width_text;
    TextView khetrofol_sotangso_text;
    TextView khetrofol_sotangso;
    TextView khetrofol_hector_text;
    TextView khetrofol_hector;

    EditText khetrofol_length;
    EditText khetrofol_width;

    AppCompatButton khetrofol_agePonaSubmit;

    ImageView khetrofol_SaveImg;
    ImageView khetrofol_ShareImg;

    TextView top_head_fish_farming_khetrofol;
    TextView top_head_app_fish_farming_khetrofol;
    TextView top_farming_banner_khetrofol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_khetrofol_formula);


        khetrofol_sotangso_text = (TextView) findViewById(R.id.khetrofol_sotangso_text);
        khetrofol_sotangso = (TextView) findViewById(R.id.khetrofol_sotangso);
        khetrofol_hector_text = (TextView) findViewById(R.id.khetrofol_hector_text);
        khetrofol_hector = (TextView) findViewById(R.id.khetrofol_hector);
bottomNavigationHandler();


        khetrofol_agePonaSubmit =  findViewById(R.id.khetrofol_agePonaSubmit);

        khetrofol_length = (EditText) findViewById(R.id.khetrofol_length);
        khetrofol_width = (EditText) findViewById(R.id.khetrofol_width);

        khetrofol_ShareImg = (ImageView) findViewById(R.id.khetrofol_ShareImg);
        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);




        khetrofol_agePonaSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int fertilizer_length_int = Integer.parseInt(khetrofol_length.getText().toString());
                    int fertilizer_width_int = Integer.parseInt(khetrofol_width.getText().toString());

                    double area_out = (fertilizer_length_int*fertilizer_width_int)/435.5;
                    double hec_out = area_out/247;

                    khetrofol_sotangso.setText(pona_mojud.engToBng(String.format("%.3f", area_out)));
                    khetrofol_hector.setText(pona_mojud.engToBng(String.format("%.3f", hec_out)));

                    if(getCurrentFocus()!=null) {
                        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    }

                }catch (Exception e){}
            }
        });

        khetrofol_ShareImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");

                    String des = "পুকুরের দৈর্ঘ্য: " +pona_mojud.engToBng(khetrofol_length.getText().toString())+"\n"+
                            "পুকুরের প্রস্থ: " + pona_mojud.engToBng(khetrofol_width.getText().toString())+"\n"+
                            "পুকুরের শতাংশ: " + khetrofol_sotangso.getText().toString()+"\n"+
                            "পুকুরের হেক্টর: " + khetrofol_hector.getText();

                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"পুকুরের ক্ষেত্রফল নির্নয় ফরমুলা");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,des);
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
                startActivity(new Intent(KhetrofolFormula.this, HomeScreenActivity.class));
            }
        });

        EditText title = findViewById(R.id.title);
        title.setText("ক্ষেত্রফল নির্নয় ফরমুলা ");


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
                            startActivity(new Intent(KhetrofolFormula.this, SplashScreenActivity.class));

                        } else {
                            startActivity(new Intent(KhetrofolFormula.this, LoginActivity.class));
                        }
                        break;

                    case R.id.menu_sotorkota:
                        startActivity(new Intent(KhetrofolFormula.this, SotorkotaActivity.class));
                        break;
                    case R.id.menu_profile:
                        startActivity(new Intent(KhetrofolFormula.this, ProfileActivity.class));
                        break;
                    case R.id.menu_beboharbidi:
                        startActivity(new Intent(KhetrofolFormula.this, BaboharbidiActivity.class));
                        break;

                    case R.id.menu_somoshajomadin:
                        startActivity(new Intent(KhetrofolFormula.this, ComingSoonActivity.class));
                        break;
                }
                return false;
            }
        });
    }
}

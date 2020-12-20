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
import com.google.android.material.textfield.TextInputEditText;

import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.BaboharbidiActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.ComingSoonActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.EditUserActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.HomeScreenActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.LoginActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.ProfileActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.SotorkotaActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.SplashScreenActivity;

public class ppm_formula extends AppCompatActivity {

    TextView ppm_area_text;
    TextView ppm_area;
    TextView ppm_med_quantity_text;
    TextView ppm_med_quantity;
    TextInputEditText ppm_length, ppm_height, ppm_width, ppm_density;
    AppCompatButton ppm_agePonaSubmit;
    ImageView ppm_SaveImg;
    ImageView ppm_ShareImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ppm_formula);


        ppm_area_text = (TextView) findViewById(R.id.ppm_area_text);
        ppm_area = (TextView) findViewById(R.id.ppm_area);
        ppm_med_quantity_text = (TextView) findViewById(R.id.ppm_med_quantity_text);
        ppm_med_quantity = (TextView) findViewById(R.id.ppm_med_quantity);
        bottomNavigationHandler();
        ppm_length = findViewById(R.id.ppm_length);
        ppm_height = findViewById(R.id.ppm_height);
        ppm_width = findViewById(R.id.ppm_width);
        ppm_density = findViewById(R.id.ppm_density);

        ppm_agePonaSubmit = findViewById(R.id.ppm_agePonaSubmit);

        ppm_SaveImg = (ImageView) findViewById(R.id.ppm_SaveImg);
        ppm_ShareImg = (ImageView) findViewById(R.id.ppm_ShareImg);
        ppm_agePonaSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int ppm_length_int = Integer.parseInt(ppm_length.getText().toString());
                    int ppm_height_int = Integer.parseInt(ppm_height.getText().toString());
                    int ppm_width_int = Integer.parseInt(ppm_width.getText().toString());
                    int ppm_density_int = Integer.parseInt(ppm_density.getText().toString());

                    double area_out = (ppm_length_int * ppm_height_int * ppm_width_int) * 28.3;
                    double med_q_out = (area_out * ppm_density_int) / 1000;

                    ppm_area.setText(pona_mojud.engToBng(String.format("%.3f", area_out)));
                    ppm_med_quantity.setText(pona_mojud.engToBng(String.format("%.3f", med_q_out)));

                    if (getCurrentFocus() != null) {
                        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    }

                } catch (Exception e) {
                }
            }
        });


        ppm_ShareImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");

                    String des = "পুকুর/চৌবাচ্চার  দৈর্ঘ্য : " + pona_mojud.engToBng(ppm_length.getText().toString()) + "\n" +
                            "পুকুর/চৌবাচ্চার  উচ্চতা: " + pona_mojud.engToBng(ppm_height.getText().toString()) + "\n" +
                            "পুকুর/চৌবাচ্চার  প্রস্থ: " + pona_mojud.engToBng(ppm_width.getText().toString()) + "\n" +
                            "মাত্র (পিপিএম): " + pona_mojud.engToBng(ppm_density.getText().toString()) + "\n" +
                            "পুকুর/চৌবাচ্চার আয়তন (লিটার): " + ppm_area.getText() + "\n" +
                            "দ্রবীভুত পদার্থ বা ঔষধের পরিমান: " + ppm_med_quantity.getText();

                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "পিপিএম নির্নয় ফরমুলা");
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
                startActivity(new Intent(ppm_formula.this, HomeScreenActivity.class));
            }
        });

        EditText title = findViewById(R.id.title);
        title.setText("পিপিএম নির্নয় ফরমুলা ");

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
                            startActivity(new Intent(ppm_formula.this, SplashScreenActivity.class));

                        } else {
                            startActivity(new Intent(ppm_formula.this, LoginActivity.class));
                        }
                        break;

                    case R.id.menu_sotorkota:
                        startActivity(new Intent(ppm_formula.this, SotorkotaActivity.class));
                        break;
                    case R.id.menu_profile:
                        startActivity(new Intent(ppm_formula.this, ProfileActivity.class));
                        break;
                    case R.id.menu_beboharbidi:
                        startActivity(new Intent(ppm_formula.this, BaboharbidiActivity.class));
                        break;

                    case R.id.menu_somoshajomadin:
                        startActivity(new Intent(ppm_formula.this, ComingSoonActivity.class));
                        break;
                }
                return false;
            }
        });
    }
}

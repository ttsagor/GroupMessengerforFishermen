package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.BaboharbidiActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.ComingSoonActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.EditUserActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.HomeScreenActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.LoginActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.ProfileActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.SotorkotaActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.SplashScreenActivity;

public class InventorInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_inventor_information);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(InventorInformation.this, HomeScreenActivity.class));
            }
        });

        EditText title = findViewById(R.id.title);
        title.setText("সহযোগিতায়");
        bottomNavigationHandler();
        TextView invotech = (TextView) findViewById(R.id.invotech);
        TextView invoname = (TextView) findViewById(R.id.invoname);
        TextView invobsc = (TextView) findViewById(R.id.invobsc);
        TextView invoncad = (TextView) findViewById(R.id.invoncad);
        TextView invodeg = (TextView) findViewById(R.id.invodeg);
        TextView invonarea = (TextView) findViewById(R.id.invonarea);
        TextView invophone = (TextView) findViewById(R.id.invophone);
        TextView invoemail = (TextView) findViewById(R.id.invoemail);

        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);


        invotech.setTypeface(tf);
        invoname.setTypeface(tf);
        invobsc.setTypeface(tf);
        invoncad.setTypeface(tf);
        invodeg.setTypeface(tf);
        invonarea.setTypeface(tf);
        invophone.setTypeface(tf);
        invoemail.setTypeface(tf);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inventor_information, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
                            startActivity(new Intent(InventorInformation.this, SplashScreenActivity.class));

                        } else {
                            startActivity(new Intent(InventorInformation.this, LoginActivity.class));
                        }
                        break;

                    case R.id.menu_sotorkota:
                        startActivity(new Intent(InventorInformation.this, SotorkotaActivity.class));
                        break;
                    case R.id.menu_profile:
                        startActivity(new Intent(InventorInformation.this, ProfileActivity.class));
                        break;
                    case R.id.menu_beboharbidi:
                        startActivity(new Intent(InventorInformation.this, BaboharbidiActivity.class));
                        break;

                    case R.id.menu_somoshajomadin:
                        startActivity(new Intent(InventorInformation.this, ComingSoonActivity.class));
                        break;
                }
                return false;
            }
        });
    }
}

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.BaboharbidiActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.ComingSoonActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.EditUserActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.LoginActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.ProfileActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.SotorkotaActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.SplashScreenActivity;

public class GroupSmsNewMessage extends AppCompatActivity {
    String message="";
    String unionID="";
    String villageID="";
    String wardID="";
    DbHandler dbc;
    TextView GroupSmsNewMessageTotalFarme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_group_sms_new_message);

bottomNavigationHandler();
        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);

        TextView GroupSmsNewMessageTotalFarmer = (TextView) findViewById(R.id.GroupSmsNewMessageTotalFarmer);




        GroupSmsNewMessageTotalFarmer.setTypeface(tf);


        final DbHandler db = new DbHandler(this,null,null,1);
        dbc = db;
        GroupSmsNewMessageTotalFarme=(TextView)findViewById(R.id.GroupSmsNewMessageTotalFarmer);
        final AppCompatButton GroupSmsNewMessageSend =  findViewById(R.id.GroupSmsNewMessageSend);
        final AppCompatButton GroupSmsNewMessageSave = findViewById(R.id.GroupSmsNewMessageSave);
        final EditText GroupSmsNewMessageText = (EditText) findViewById(R.id.GroupSmsNewMessageText);
        GroupSmsNewMessageTotalFarme.setText(GroupSmsNewMessageTotalFarme.getText()+" "+pona_mojud.engToBng(String.valueOf(groupsmsfilter.finalRecipient.size())));
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        GroupSmsNewMessageSend.setTypeface(tf);
        GroupSmsNewMessageText.setTypeface(tf);
        if(b!=null)
        {
            message =(String) b.get("message");
        }
        GroupSmsNewMessageText.setText(message);

        GroupSmsNewMessageSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<FarmerInfoModel> farmerInfoList = groupsmsfilter.finalRecipient;

               /* if(GroupSmsNewMessageText.getText().length()>160)
                {
                    Toast.makeText(getBaseContext(), "Maximum Character Limit 160.Your Text has "+GroupSmsNewMessageText.getText().length()+" Character", Toast.LENGTH_LONG).show();
                }
                else*/
                if(farmerInfoList.size()>0 && GroupSmsNewMessageText.getText().length()>0){
                    Intent i = new Intent( GroupSmsNewMessage.this ,recpiant_limit.class);
                    i.putExtra("message", GroupSmsNewMessageText.getText().toString());
                    startActivity(i);
                }
            }
        });
        GroupSmsNewMessageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GroupSmsNewMessageText.getText().length()>0) {
                    SmsTemplateModel smsTemplateModel = new SmsTemplateModel();
                    smsTemplateModel.setSmsTemplateText(GroupSmsNewMessageText.getText().toString());
                    db.insertData(smsTemplateModel);
                    Toast.makeText(getBaseContext(), "টেম্পটে সেভ করা সম্পূর্ণ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_group_sms_new_message, menu);
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
                            startActivity(new Intent(GroupSmsNewMessage.this, SplashScreenActivity.class));

                        } else {
                            startActivity(new Intent(GroupSmsNewMessage.this, LoginActivity.class));
                        }
                        break;

                    case R.id.menu_sotorkota:
                        startActivity(new Intent(GroupSmsNewMessage.this, SotorkotaActivity.class));
                        break;
                    case R.id.menu_profile:
                        startActivity(new Intent(GroupSmsNewMessage.this, ProfileActivity.class));
                        break;
                    case R.id.menu_beboharbidi:
                        startActivity(new Intent(GroupSmsNewMessage.this, BaboharbidiActivity.class));
                        break;

                    case R.id.menu_somoshajomadin:
                        startActivity(new Intent(GroupSmsNewMessage.this, ComingSoonActivity.class));
                        break;
                }
                return false;
            }
        });
    }
}

package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

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

public class GroupSmsTemplateSelection extends AppCompatActivity {
    public static String unionID = "";
    public static String villageID = "";
    public static String wardID = "";
    public static DbHandler dbc;
    public static Context mContext;
    TextView GroupSmsTemplateSelectionTotalFarmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_group_sms_template_selection);
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GroupSmsTemplateSelection.this, HomeScreenActivity.class));
            }
        });

        EditText title = findViewById(R.id.title);
        title.setText("টেমপ্লেট");
        bottomNavigationHandler();
        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);


        final DbHandler db = new DbHandler(this, null, null, 1);
        //GroupSmsTemplateSelectionTotalFarmer=(TextView)findViewById(R.id.GroupSmsTemplateSelectionTotalFarmer);
        //final Button GroupSmsTemplateSelectionNext = (Button) findViewById(R.id.GroupSmsTemplateSelectionNext);
        mContext = this;
        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        if (b != null) {
            unionID = (String) b.get("unionID");
            villageID = (String) b.get("villageID");
            wardID = (String) b.get("wardID");
        }
        dbc = db;
        //totalUserCount();

        List<SmsTemplateModel> smsTemplateModels = db.getAllSmsTemplate();
        ArrayList<String> text = new ArrayList<String>();

        for (SmsTemplateModel cSmsTemplateModels : smsTemplateModels) {
            text.add(cSmsTemplateModels.getSmsTemplateText());
        }
        ListView GroupSmsTemplateSelectionListView = (ListView) findViewById(R.id.GroupSmsTemplateSelectionListView);
        SmsTemplateListAdaptar adapter = new SmsTemplateListAdaptar(text, this);
        GroupSmsTemplateSelectionListView.setAdapter(null);
        GroupSmsTemplateSelectionListView.setAdapter(adapter);

       /* GroupSmsTemplateSelectionNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GroupSmsTemplateSelection.this, GroupSmsNewMessage.class);
                i.putExtra("unionID", unionID);
                i.putExtra("villageID", villageID);
                i.putExtra("wardID", wardID);
                startActivity(i);
                //Toast.makeText(getActivity(), "hello", Toast.LENGTH_LONG).show();
            }
        });*/
    }

    public void totalUserCount() {

        GroupSmsTemplateSelectionTotalFarmer.setText("মোট চাষি সংখাঃ  " + dbc.getFarmerInfoByAreaFarmingCategoryMobileOperator(MainActivity.divisionID, MainActivity.districtID, MainActivity.upazillaID, unionID, villageID, wardID, GroupSmsFarmingCategory.farmingCategoryList, GroupSmsFarmingCategory.phoneCompanyList).size());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_group_sms_template_selection, menu);
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

    public static void nxtActivity(String smsTemplateText) {

        Intent i = new Intent(mContext, GroupSmsNewMessage.class);
        i.putExtra("message", smsTemplateText);
        mContext.startActivity(i);

       /* List<FarmerInfoModel> farmerInfoList = dbc.getFarmerInfoByAreaFarmingCategoryMobileOperator(MainActivity.divisionID, MainActivity.districtID, MainActivity.upazillaID, unionID, villageID, wardID, GroupSmsFarmingCategory.farmingCategoryList, GroupSmsFarmingCategory.phoneCompanyList);
           if(farmerInfoList.size()>0){
               String sep = "; ";
               String allNumber = "";
               if (android.os.Build.MANUFACTURER.equalsIgnoreCase("Samsung")) {
                   sep = ", ";
               }
             for (FarmerInfoModel cFarmerInfoList : farmerInfoList) {
                 allNumber +=cFarmerInfoList.getPhoneNumber()+sep;
             }

               Intent sendIntent = new Intent(Intent.ACTION_VIEW);
               sendIntent.putExtra("address", allNumber);
               sendIntent.putExtra( "sms_body", smsTemplateText );
               sendIntent.setType("vnd.android-dir/mms-sms");
               mContext.startActivity(sendIntent);
       }*/


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
                            startActivity(new Intent(GroupSmsTemplateSelection.this, SplashScreenActivity.class));

                        } else {
                            startActivity(new Intent(GroupSmsTemplateSelection.this, LoginActivity.class));
                        }
                        break;

                    case R.id.menu_sotorkota:
                        startActivity(new Intent(GroupSmsTemplateSelection.this, SotorkotaActivity.class));
                        break;
                    case R.id.menu_profile:
                        startActivity(new Intent(GroupSmsTemplateSelection.this, ProfileActivity.class));
                        break;
                    case R.id.menu_beboharbidi:
                        startActivity(new Intent(GroupSmsTemplateSelection.this, BaboharbidiActivity.class));
                        break;

                    case R.id.menu_somoshajomadin:
                        startActivity(new Intent(GroupSmsTemplateSelection.this, ComingSoonActivity.class));
                        break;
                }
                return false;
            }
        });
    }
}

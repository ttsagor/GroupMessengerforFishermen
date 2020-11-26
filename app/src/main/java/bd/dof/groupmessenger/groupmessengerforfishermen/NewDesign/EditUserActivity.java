package bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import bd.dof.groupmessenger.groupmessengerforfishermen.DbHandler;
import bd.dof.groupmessenger.groupmessengerforfishermen.FarmerInfoModel;
import bd.dof.groupmessenger.groupmessengerforfishermen.R;
import bd.dof.groupmessenger.groupmessengerforfishermen.UnionModel;

public class EditUserActivity extends AppCompatActivity {
    int userID = -1;
    Boolean updateFlag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_add_update_user);

        final Context mContext = this;
        final DbHandler db = new DbHandler(this, null, null, 1);
        ArrayList<String> list = new ArrayList<String>();

        for (UnionModel u : HomeScreenActivity.allUnion) {
            list.add(u.getUnionName());
        }

        final Spinner adduserunionlist = (Spinner) findViewById(R.id.adduserunionlist);

        final TextInputEditText addusername = findViewById(R.id.addusername);
        final com.google.android.material.textfield.TextInputEditText adduserfathersname = findViewById(R.id.adduserfathersname);
        final com.google.android.material.textfield.TextInputEditText adduserfphonenumber = findViewById(R.id.adduserfphonenumber);
        final com.google.android.material.textfield.TextInputEditText adduserfarmingcatagory = findViewById(R.id.adduserfarmingcatagory);
        final ImageView updateusereditbutton = (ImageView) findViewById(R.id.updateusereditbutton);
        final ImageView adduserimageview = (ImageView) findViewById(R.id.adduserimageview);
         bottomNavigationHandler();
        TextView top_head_app_fish_farming_add_farmer_union_name = (TextView) findViewById(R.id.top_head_app_fish_farming_add_farmer_union_name);


        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);


        top_head_app_fish_farming_add_farmer_union_name.setTypeface(tf);


        addusername.setTypeface(tf);
        adduserfathersname.setTypeface(tf);
        adduserfphonenumber.setTypeface(tf);
        adduserfarmingcatagory.setTypeface(tf);

        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adduserunionlist.setAdapter(adp1);

        Intent iin = getIntent();
        Bundle b = iin.getExtras();

        if (b != null) {
            try {
                userID = (Integer) b.get("farmerID");
            } catch (Exception e) {

            }

        }

        if (userID > -1) {
            updateusereditbutton.setVisibility(View.VISIBLE);
            adduserimageview.setVisibility(View.GONE);
            FarmerInfoModel cFarmer = getFarmerInfoByID(userID);
            addusername.setText(cFarmer.getFarmerName().toString());
            adduserfathersname.setText(cFarmer.getFarmerFatherHusbandName().toString());
            adduserfphonenumber.setText(cFarmer.getPhoneNumber().toString());
            adduserfarmingcatagory.setText(cFarmer.getFarmingCategory().toString());
            adduserunionlist.setSelection(adp1.getPosition(getUnionNameByID(cFarmer.getUnionID())));


            updateFlag = false;

            addusername.setEnabled(false);
            adduserfathersname.setEnabled(false);
            adduserfphonenumber.setEnabled(false);
            adduserfarmingcatagory.setEnabled(false);
            adduserunionlist.setEnabled(false);
        } else {
            updateusereditbutton.setVisibility(View.GONE);
            addusername.setEnabled(true);
            adduserfathersname.setEnabled(true);
            adduserfphonenumber.setEnabled(true);
            adduserfarmingcatagory.setEnabled(true);
            adduserunionlist.setEnabled(true);
        }

        updateusereditbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateFlag = true;
                addusername.setEnabled(true);
                adduserfathersname.setEnabled(true);
                adduserfphonenumber.setEnabled(true);
                adduserfarmingcatagory.setEnabled(true);
                adduserunionlist.setEnabled(true);
                updateusereditbutton.setVisibility(View.GONE);
                adduserimageview.setVisibility(View.VISIBLE);
            }
        });

        adduserimageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addusername.getText().toString().length() > 0 && adduserfphonenumber.getText().toString().length() == 11 && updateFlag) {
                    FarmerInfoModel cFarmer = new FarmerInfoModel();
                    if (userID > -1) {
                        cFarmer.setFarmerID(userID);
                    } else {
                        cFarmer.setFarmerID(HomeScreenActivity.allFarmerInfo.size() + 1);
                    }
                    cFarmer.setFarmerName(addusername.getText().toString());
                    cFarmer.setFarmerFatherHusbandName(adduserfathersname.getText().toString());
                    cFarmer.setFarmerAge("");
                    cFarmer.setFarmingCategory(adduserfarmingcatagory.getText().toString());
                    cFarmer.setNumberOfPonds("");
                    cFarmer.setAreaP1("");
                    cFarmer.setAreaP2("");
                    cFarmer.setAreaP3("");
                    cFarmer.setPhoneNumber(adduserfphonenumber.getText().toString());
                    cFarmer.setVillageID("");
                    cFarmer.setWardID("");
                    cFarmer.setUnionID(getUnionIDByName(adduserunionlist.getSelectedItem().toString()));
                    cFarmer.setUpazillaID(HomeScreenActivity.upazillaID);
                    cFarmer.setDistrictID(HomeScreenActivity.districtID);
                    cFarmer.setDivisionID(HomeScreenActivity.divisionID);
                    cFarmer.setActiveStatus(String.valueOf(1));
                    cFarmer.setUploadStatus(String.valueOf(0));
                    try {
                        if (userID > -1) {
                            db.updateData(cFarmer);
                            HomeScreenActivity.allFarmerInfo = db.getAllFarmerInfoBy();
                            sendToBack();
                        } else {
                            db.insertData(cFarmer);
                        }
                        Toast toast = Toast.makeText(mContext, "তথ্য সংরক্ষন সফল", Toast.LENGTH_SHORT);
                        TextView t = (TextView) toast.getView().findViewById(android.R.id.message);
                        if (t != null) {
                            t.setGravity(Gravity.CENTER);
                        }
                        toast.show();
                        HomeScreenActivity.allFarmerInfo.add(cFarmer);
                    } catch (Exception e) {
                        Toast toast = Toast.makeText(mContext, "তথ্য সংরক্ষন বার্থ", Toast.LENGTH_SHORT);
                        TextView t = (TextView) toast.getView().findViewById(android.R.id.message);
                        if (t != null) {
                            t.setGravity(Gravity.CENTER);
                        }
                        toast.show();
                    } finally {
                        addusername.setText("");
                        adduserfathersname.setText("");
                        adduserfphonenumber.setText("");
                        adduserfarmingcatagory.setText("");
                    }
                } else {
                    Toast toast = Toast.makeText(mContext, "অনুগ্রহক পূর্বক সকল তথ্য প্রদান করুন", Toast.LENGTH_SHORT);
                    TextView t = (TextView) toast.getView().findViewById(android.R.id.message);
                    if (t != null) {
                        t.setGravity(Gravity.CENTER);
                    }
                    toast.show();
                }
            }
        });

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditUserActivity.this, HomeScreenActivity.class));
            }
        });

        EditText title = findViewById(R.id.title);
        title.setText("চাষি আপডেট ");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_update_user, menu);
        return true;
    }

    String getUnionIDByName(String name) {
        for (UnionModel u : HomeScreenActivity.allUnion) {
            if (u.getUnionName() == name)
                return u.getUnionID();
        }
        return "";
    }

    FarmerInfoModel getFarmerInfoByID(int ID) {
        for (FarmerInfoModel u : HomeScreenActivity.allFarmerInfo) {
            if (u.getFarmerID() == ID)
                return u;
        }
        return new FarmerInfoModel();
    }

    void sendToBack() {
        super.onBackPressed();
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

    String getUnionNameByID(String unionID) {
        for (UnionModel u : HomeScreenActivity.allUnion) {
            if (u.getUnionID().contains(unionID) && (u.getUnionID().length() == unionID.length())) {
                return u.getUnionName();
            }
        }
        return "";
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
                            startActivity(new Intent(EditUserActivity.this, SplashScreenActivity.class));

                        } else {
                            startActivity(new Intent(EditUserActivity.this, LoginActivity.class));
                        }
                        break;

                    case R.id.menu_sotorkota:
                        startActivity(new Intent(EditUserActivity.this, SotorkotaActivity.class));
                        break;
                    case R.id.menu_profile:
                        startActivity(new Intent(EditUserActivity.this, ProfileActivity.class));
                        break;
                    case R.id.menu_beboharbidi:
                        startActivity(new Intent(EditUserActivity.this, BaboharbidiActivity.class));
                        break;

                    case R.id.menu_somoshajomadin:
                        startActivity(new Intent(EditUserActivity.this, ComingSoonActivity.class));
                        break;
                }
                return false;
            }
        });
    }
}

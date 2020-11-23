package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AddUpdateUser extends AppCompatActivity {

    int userID=-1;
    Boolean updateFlag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_add_update_user);

        final Context mContext = this;
        final DbHandler db = new DbHandler(this,null,null,1);
        ArrayList<String> list=new ArrayList<String>();

        for(UnionModel u : MainActivity.allUnion)
        {
            list.add(u.getUnionName());
        }

        final Spinner adduserunionlist= (Spinner) findViewById(R.id.adduserunionlist);

        final EditText addusername = (EditText) findViewById(R.id.addusername);
        final EditText adduserfathersname = (EditText) findViewById(R.id.adduserfathersname);
        final EditText adduserfphonenumber = (EditText) findViewById(R.id.adduserfphonenumber);
        final EditText adduserfarmingcatagory = (EditText) findViewById(R.id.adduserfarmingcatagory);
        final ImageView updateusereditbutton = (ImageView) findViewById(R.id.updateusereditbutton);
        final ImageView adduserimageview = (ImageView) findViewById(R.id.adduserimageview);

        TextView top_head_fish_farming_add_farmer = (TextView) findViewById(R.id.top_head_fish_farming_add_farmer);
        TextView top_head_app_fish_farming_add_farmer = (TextView) findViewById(R.id.top_head_app_fish_farming_add_farmer);
        TextView top_farming_banner_add_farmer = (TextView) findViewById(R.id.top_farming_banner_add_farmer);
        TextView top_head_app_fish_farming_add_farmer_name = (TextView) findViewById(R.id.top_head_app_fish_farming_add_farmer_name);
        TextView top_head_app_fish_farming_add_father_name = (TextView) findViewById(R.id.top_head_app_fish_farming_add_father_name);
        TextView top_head_app_fish_farming_add_farmer_mobile = (TextView) findViewById(R.id.top_head_app_fish_farming_add_farmer_mobile);
        TextView top_head_app_fish_farming_add_farmer_farming_category = (TextView) findViewById(R.id.top_head_app_fish_farming_add_farmer_farming_category);
        TextView top_head_app_fish_farming_add_farmer_union_name = (TextView) findViewById(R.id.top_head_app_fish_farming_add_farmer_union_name);


        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);

        top_head_fish_farming_add_farmer.setTypeface(tf);
        top_head_app_fish_farming_add_farmer.setTypeface(tf);
        top_farming_banner_add_farmer.setTypeface(tf);
        top_head_app_fish_farming_add_farmer_name.setTypeface(tf);
        top_head_app_fish_farming_add_father_name.setTypeface(tf);
        top_head_app_fish_farming_add_farmer_mobile.setTypeface(tf);
        top_head_app_fish_farming_add_farmer_farming_category.setTypeface(tf);
        top_head_app_fish_farming_add_farmer_union_name.setTypeface(tf);


        addusername.setTypeface(tf);
        adduserfathersname.setTypeface(tf);
        adduserfphonenumber.setTypeface(tf);
        adduserfarmingcatagory.setTypeface(tf);

        ArrayAdapter<String> adp1=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,list);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adduserunionlist.setAdapter(adp1);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            try
            {
                userID =(Integer) b.get("farmerID");
            }
            catch (Exception e){

            }

        }

        if(userID>-1)
        {
            updateusereditbutton.setVisibility(View.VISIBLE);
            adduserimageview.setVisibility(View.GONE);
            FarmerInfoModel cFarmer = getFarmerInfoByID(userID);
            addusername.setText(cFarmer.getFarmerName().toString());
            adduserfathersname.setText(cFarmer.getFarmerFatherHusbandName().toString());
            adduserfphonenumber.setText(cFarmer.getPhoneNumber().toString());
            adduserfarmingcatagory.setText(cFarmer.getFarmingCategory().toString());
            adduserunionlist.setSelection(adp1.getPosition(getUnionNameByID(cFarmer.getUnionID())));
            top_farming_banner_add_farmer.setText("চাষি তথ্য পরিবর্তন");

            updateFlag = false;

            addusername.setEnabled(false);
            adduserfathersname.setEnabled(false);
            adduserfphonenumber.setEnabled(false);
            adduserfarmingcatagory.setEnabled(false);
            adduserunionlist.setEnabled(false);
        }
        else
        {
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
                if(addusername.getText().toString().length()>0 && adduserfphonenumber.getText().toString().length()==11 && updateFlag)
                {
                    FarmerInfoModel cFarmer = new FarmerInfoModel();
                    if(userID>-1)
                    {
                        cFarmer.setFarmerID(userID);
                    }
                    else {
                        cFarmer.setFarmerID(MainActivity.allFarmerInfo.size() + 1);
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
                    cFarmer.setUpazillaID(MainActivity.upazillaID);
                    cFarmer.setDistrictID(MainActivity.districtID);
                    cFarmer.setDivisionID(MainActivity.divisionID);
                    cFarmer.setActiveStatus(String.valueOf(1));
                    cFarmer.setUploadStatus(String.valueOf(0));
                    try {
                        if(userID>-1)
                        {
                            db.updateData(cFarmer);
                            MainActivity.allFarmerInfo = db.getAllFarmerInfoBy();
                            sendToBack();
                        }
                        else
                        {
                            db.insertData(cFarmer);
                        }
                        Toast toast = Toast.makeText(mContext, "তথ্য সংরক্ষন সফল", Toast.LENGTH_SHORT);
                        TextView t = (TextView) toast.getView().findViewById(android.R.id.message);
                        if( t != null){
                            t.setGravity(Gravity.CENTER);
                        }
                        toast.show();
                        MainActivity.allFarmerInfo.add(cFarmer);
                    } catch (Exception e) {
                        Toast toast = Toast.makeText(mContext, "তথ্য সংরক্ষন বার্থ", Toast.LENGTH_SHORT);
                        TextView t = (TextView) toast.getView().findViewById(android.R.id.message);
                        if( t != null){
                            t.setGravity(Gravity.CENTER);
                        }
                        toast.show();
                    }
                    finally {
                        addusername.setText("");
                        adduserfathersname.setText("");
                        adduserfphonenumber.setText("");
                        adduserfarmingcatagory.setText("");
                    }
                }
                else
                {
                    Toast toast = Toast.makeText(mContext, "অনুগ্রহক পূর্বক সকল তথ্য প্রদান করুন", Toast.LENGTH_SHORT);
                    TextView t = (TextView) toast.getView().findViewById(android.R.id.message);
                    if( t != null){
                        t.setGravity(Gravity.CENTER);
                    }
                    toast.show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_update_user, menu);
        return true;
    }

    String getUnionIDByName(String name)
    {
        for(UnionModel u : MainActivity.allUnion)
        {
            if(u.getUnionName()==name)
                return u.getUnionID();
        }
        return "";
    }

    FarmerInfoModel getFarmerInfoByID(int ID)
    {
        for(FarmerInfoModel u : MainActivity.allFarmerInfo)
        {
            if(u.getFarmerID()==ID)
                return u;
        }
        return new FarmerInfoModel();
    }
    void sendToBack()
    {
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

    String getUnionNameByID(String unionID)
    {
        for(UnionModel u : MainActivity.allUnion)
        {
            if(u.getUnionID().contains(unionID) && (u.getUnionID().length()==unionID.length()))
            {
                return u.getUnionName();
            }
        }
        return "";
    }
}

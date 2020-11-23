package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.content.Intent;
import android.content.pm.ActivityInfo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class GroupSmsFarmingCategory extends AppCompatActivity {

    String unionID="";
    String villageID="";
    String wardID="";
    DbHandler dbc;
    public static ArrayList<String> farmingCategoryList = new ArrayList<String>();
    public static ArrayList<String> phoneCompanyList = new ArrayList<String>();
    TextView GroupSmsAreaFarmingCategoryTotalFarmer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_group_sms_farming_category);
        final DbHandler db = new DbHandler(this,null,null,1);
        dbc=db;
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null)
        {
            unionID =(String) b.get("unionID");
            villageID =(String) b.get("villageID");
            wardID =(String) b.get("wardID");
        }
        farmingCategoryList = new ArrayList<String>();
        phoneCompanyList = new ArrayList<String>();
        //System.out.println("union:"+unionID +" village:"+villageID+" ward:"+wardID);
        final List<String> farmingCategory =  db.getFarmingCategoryByArea(MainActivity.divisionID, MainActivity.districtID, MainActivity.upazillaID, unionID, wardID, villageID);
        LinearLayout GroupSmsFarmingCategoryLayout1=(LinearLayout)findViewById(R.id.GroupSmsFarmingCategoryLayout1);
        LinearLayout GroupSmsFarmingCategoryLayout2=(LinearLayout)findViewById(R.id.GroupSmsFarmingCategoryLayout2);
        LinearLayout GroupSmsFarmingCategoryLayout3=(LinearLayout)findViewById(R.id.GroupSmsFarmingCategoryLayout3);
        GroupSmsAreaFarmingCategoryTotalFarmer=(TextView)findViewById(R.id.GroupSmsAreaFarmingCategoryTotalFarmer);

        Button GroupSmsFarmingCategoryNext=(Button)findViewById(R.id.GroupSmsFarmingCategoryNext);

        CheckBox GroupSmsFarmingCategoryGrameen=(CheckBox)findViewById(R.id.GroupSmsFarmingCategoryGrameen);
        CheckBox GroupSmsFarmingCategoryBanglalink=(CheckBox)findViewById(R.id.GroupSmsFarmingCategoryBanglalink);
        CheckBox GroupSmsFarmingCategoryAirtel=(CheckBox)findViewById(R.id.GroupSmsFarmingCategoryAirtel);
        CheckBox GroupSmsFarmingCategoryRobi=(CheckBox)findViewById(R.id.GroupSmsFarmingCategoryRobi);
        CheckBox GroupSmsFarmingCategoryTeletalk=(CheckBox)findViewById(R.id.GroupSmsFarmingCategoryTeletalk);
        CheckBox GroupSmsFarmingCategoryCitycell=(CheckBox)findViewById(R.id.GroupSmsFarmingCategoryCitycell);

        GroupSmsFarmingCategoryGrameen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    phoneCompanyList.add("017");
                    totalUserCount();
                }
                else
                {
                    phoneCompanyList.remove("017");
                    totalUserCount();
                }

            }
        });

        GroupSmsFarmingCategoryBanglalink.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    phoneCompanyList.add("019");
                    totalUserCount();
                }
                else
                {
                    phoneCompanyList.remove("019");
                    totalUserCount();
                }

            }
        });

        GroupSmsFarmingCategoryAirtel.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    phoneCompanyList.add("016");
                    totalUserCount();
                }
                else
                {
                    phoneCompanyList.remove("016");
                    totalUserCount();
                }

            }
        });

        GroupSmsFarmingCategoryRobi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    phoneCompanyList.add("018");
                    totalUserCount();
                }
                else
                {
                    phoneCompanyList.remove("018");
                    totalUserCount();
                }

            }
        });

        GroupSmsFarmingCategoryTeletalk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    phoneCompanyList.add("015");
                    totalUserCount();
                }
                else
                {
                    phoneCompanyList.remove("015");
                    totalUserCount();
                }

            }
        });

        GroupSmsFarmingCategoryCitycell.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if ( isChecked )
                {
                    phoneCompanyList.add("011");
                    totalUserCount();
                }
                else
                {
                    phoneCompanyList.remove("011");
                    totalUserCount();
                }

            }
        });

        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int mapper=1;
        for(int i=0;i<farmingCategory.size();i++){
            CheckBox checkBox = new CheckBox(this);
            checkBox.setLayoutParams(lparams);
            checkBox.setText(farmingCategory.get(i));
            if(mapper==1)
            {
                GroupSmsFarmingCategoryLayout1.addView(checkBox);
                mapper=2;
            }
            else if(mapper==2)
            {
                GroupSmsFarmingCategoryLayout2.addView(checkBox);
                mapper=3;
            }
            else if(mapper==3)
            {
                GroupSmsFarmingCategoryLayout3.addView(checkBox);
                mapper=1;
            }
            final  int pos = i;
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
            {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
                {
                    if ( isChecked )
                    {
                        System.out.println(farmingCategory.get(pos) + "CHECKED");
                        farmingCategoryList.add(farmingCategory.get(pos));
                        totalUserCount();
                    }
                    else
                    {
                        System.out.println(farmingCategory.get(pos) + "UNCHECKED");
                        farmingCategoryList.remove(farmingCategory.get(pos));
                        totalUserCount();
                    }

                }
            });

        }

        GroupSmsFarmingCategoryNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GroupSmsFarmingCategory.this, GroupSmsTemplateSelection.class);
                i.putExtra("unionID",unionID);
                i.putExtra("villageID",villageID);
                i.putExtra("wardID",wardID);
                startActivity(i);
            }
        });
        totalUserCount();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_group_sms_farming_category, menu);
        return true;
    }

    public void totalUserCount()
    {

        GroupSmsAreaFarmingCategoryTotalFarmer.setText("মোট চাষি সংখাঃ  " + dbc.getFarmerInfoByAreaFarmingCategoryMobileOperator(MainActivity.divisionID, MainActivity.districtID, MainActivity.upazillaID, unionID, villageID, wardID, farmingCategoryList, phoneCompanyList).size());
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
}

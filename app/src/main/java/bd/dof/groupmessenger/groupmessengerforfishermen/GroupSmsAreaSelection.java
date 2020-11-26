package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
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
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.LoginActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.ProfileActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.SotorkotaActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.SplashScreenActivity;

public class GroupSmsAreaSelection extends AppCompatActivity {

    public String unionID;
    public String villageID;
    public String wardID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_group_sms_area_selection);
        final DbHandler db = new DbHandler(this,null,null,1);

        List<UnionModel> unionList = db.getUnionList(MainActivity.divisionID,MainActivity.districtID,MainActivity.upazillaID);

        ArrayList<String> unionNameList = new ArrayList<String>();
        unionNameList.add("সকল ইউনিয়ন");

        for (UnionModel cUion : unionList) {
                    unionNameList.add(cUion.getUnionName());
        }

        final Spinner unionspinner = (Spinner) findViewById(R.id.unionspinner);
        final Spinner villageSpinner = (Spinner) findViewById(R.id.villagespinner);
        final Spinner wardSpinner = (Spinner) findViewById(R.id.wardspinner);
        final TextView GroupSmsAreaSelectionTotalFarmer = (TextView) findViewById(R.id.GroupSmsAreaSelectionTotalFarmer);
        final Button GroupSmsAreaSelectionNext = (Button) findViewById(R.id.GroupSmsAreaSelectionNext);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, unionNameList);
        unionspinner.setAdapter(adapter);

        unionspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                UnionModel selectedUnion = db.getUnionIDByName(MainActivity.divisionID, MainActivity.districtID, MainActivity.upazillaID, unionspinner.getSelectedItem().toString());
                unionID = selectedUnion.getUnionID();
                ///-----village spinner set
                List<VillegeModel> villageList = db.getVillegenList(MainActivity.divisionID, MainActivity.districtID, MainActivity.upazillaID, selectedUnion.getUnionID());
                ArrayList<String> villageNameList = new ArrayList<String>();
                villageNameList.add("সকল গ্রাম");
                for (VillegeModel cVillage : villageList) {
                    villageNameList.add(cVillage.getVillageName());
                }

                final Spinner villagespinner = (Spinner) findViewById(R.id.villagespinner);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getBaseContext(),
                        android.R.layout.simple_spinner_dropdown_item, villageNameList);
                villagespinner.setAdapter(adapter);

                ///-----ward spinner set
                List<WardModel> wardList = db.getWardList(MainActivity.divisionID, MainActivity.districtID, MainActivity.upazillaID, selectedUnion.getUnionID());
                ArrayList<String> wardNameList = new ArrayList<String>();
                wardNameList.add("সকল ওয়াড");
                for (WardModel cWard : wardList) {
                    wardNameList.add(cWard.getWardName());
                }

                final Spinner wardspinner = (Spinner) findViewById(R.id.wardspinner);
                ArrayAdapter<String> adapterWard = new ArrayAdapter<String>(getBaseContext(),
                        android.R.layout.simple_spinner_dropdown_item, wardNameList);
                wardspinner.setAdapter(adapterWard);

                if (unionID == null) {
                    unionID = "%";
                }
                if (villageID == null) {
                    villageID = "%";
                }
                if (wardID == null) {
                    wardID = "%";
                }
                GroupSmsAreaSelectionTotalFarmer.setText("মোট চাষি সংখাঃ  " + db.getFarmerInfoByArea(MainActivity.divisionID, MainActivity.districtID, MainActivity.upazillaID, unionID, villageID, wardID).size());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        villageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                VillegeModel cVillage = db.getVillageIDByName(MainActivity.divisionID, MainActivity.districtID, MainActivity.upazillaID, unionID, villageSpinner.getSelectedItem().toString());
                villageID=cVillage.getVillageID();
                if(unionID==null)
                {
                    unionID="%";
                }
                if(villageID==null)
                {
                    villageID="%";
                }
                if(wardID==null)
                {
                    wardID="%";
                }
                GroupSmsAreaSelectionTotalFarmer.setText("মোট চাষি সংখাঃ  " + db.getFarmerInfoByArea(MainActivity.divisionID, MainActivity.districtID, MainActivity.upazillaID, unionID, villageID, wardID).size());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        wardSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                WardModel cWard = db.getWardIDByName(MainActivity.divisionID, MainActivity.districtID, MainActivity.upazillaID, unionID, wardSpinner.getSelectedItem().toString());
                wardID=cWard.getWardID();
                if(unionID==null)
                {
                    unionID="%";
                }
                if(villageID==null)
                {
                    villageID="%";
                }
                if(wardID==null)
                {
                    wardID="%";
                }
                GroupSmsAreaSelectionTotalFarmer.setText("মোট চাষি সংখাঃ  " + db.getFarmerInfoByArea(MainActivity.divisionID, MainActivity.districtID, MainActivity.upazillaID, unionID, villageID, wardID).size());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        GroupSmsAreaSelectionNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GroupSmsAreaSelection.this, GroupSmsFarmingCategory.class);
                i.putExtra("unionID",unionID);
                i.putExtra("villageID",villageID);
                i.putExtra("wardID",wardID);
                startActivity(i);
                //Toast.makeText(getActivity(), "hello", Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_group_sms_area_selection, menu);
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

}

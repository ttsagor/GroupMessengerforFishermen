package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class GroupSmsTemplateSelection extends AppCompatActivity {
    public static String unionID="";
    public static String villageID="";
    public static String wardID="";
    public static DbHandler dbc;
    public static Context mContext;
    TextView GroupSmsTemplateSelectionTotalFarmer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_group_sms_template_selection);


        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);


        final DbHandler db = new DbHandler(this,null,null,1);
        //GroupSmsTemplateSelectionTotalFarmer=(TextView)findViewById(R.id.GroupSmsTemplateSelectionTotalFarmer);
        //final Button GroupSmsTemplateSelectionNext = (Button) findViewById(R.id.GroupSmsTemplateSelectionNext);
        mContext=this;
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null)
        {
            unionID =(String) b.get("unionID");
            villageID =(String) b.get("villageID");
            wardID =(String) b.get("wardID");
        }
        dbc=db;
        //totalUserCount();

        List<SmsTemplateModel> smsTemplateModels=db.getAllSmsTemplate();
        ArrayList<String> text = new  ArrayList<String>();

        for (SmsTemplateModel cSmsTemplateModels : smsTemplateModels) {
            text.add(cSmsTemplateModels.getSmsTemplateText());
        }
        ListView GroupSmsTemplateSelectionListView=(ListView)findViewById(R.id.GroupSmsTemplateSelectionListView);
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

    public void totalUserCount()
    {

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
}

package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    public static String divisionID = "10";
    public static String districtID = "78";
    public static String upazillaID = "66";
    public static ArrayList<FarmerInfoModel> allFarmerInfo = new ArrayList<FarmerInfoModel>();
    public static ArrayList<UnionModel> allUnion = new ArrayList<UnionModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestSmsPermission();
        SimpleDateFormat df = new SimpleDateFormat("hh:ss MM/dd/yyyy");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        String result = df.format(System.currentTimeMillis());

        System.out.println("Current Time: " + result);
        System.out.println("Install Time: " + getInstallDate());


        DbHandler db = new DbHandler(this,null,null,1);
        SystemInformationModel cSystemInfo = db.getSystemInfo();
        divisionID = cSystemInfo.getUserDivisionID();
        districtID = cSystemInfo.getUserDistrictID();
        upazillaID = cSystemInfo.getUserUpazillaID();

                allUnion = new ArrayList<UnionModel>();
        allFarmerInfo = new ArrayList<FarmerInfoModel>();
        /*if(cSystemInfo.getDataLoaded()==1)
        {
            allUnion = db.getAllUnion(MainActivity.divisionID,MainActivity.districtID,MainActivity.upazillaID);
            allFarmerInfo = db.getAllFarmerInfoBy();
        }*/

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);

        allUnion = db.getAllUnion(MainActivity.divisionID,MainActivity.districtID,MainActivity.upazillaID);
        allFarmerInfo = db.getAllFarmerInfoBy();

        LinearLayout groupsms = (LinearLayout) findViewById(R.id.groupsms);
        LinearLayout search = (LinearLayout) findViewById(R.id.search);
        LinearLayout inventor = (LinearLayout) findViewById(R.id.inventor);
        LinearLayout adduser = (LinearLayout) findViewById(R.id.adduser);
        LinearLayout addtemplate = (LinearLayout) findViewById(R.id.addtemplate);
        LinearLayout farming = (LinearLayout) findViewById(R.id.farming);
        LinearLayout disease = (LinearLayout) findViewById(R.id.disease);
        LinearLayout settings = (LinearLayout) findViewById(R.id.settings);

        TextView smsMainText = (TextView) findViewById(R.id.smsMainText);
        TextView searchMainText = (TextView) findViewById(R.id.searchMainText);
        TextView farmingMainText = (TextView) findViewById(R.id.farmingMainText);
        TextView disMainText = (TextView) findViewById(R.id.disMainText);
        TextView invoMainText = (TextView) findViewById(R.id.invoMainText);
        TextView top_head = (TextView) findViewById(R.id.top_head);
        TextView top_head_app = (TextView) findViewById(R.id.top_head_app);
        TextView addtemplatetext = (TextView) findViewById(R.id.addtemplatetext);
        TextView addusertext = (TextView) findViewById(R.id.addusertext);
        TextView settingicontext = (TextView) findViewById(R.id.settingicontext);

        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);

        smsMainText.setTypeface(tf);
        searchMainText.setTypeface(tf);
        farmingMainText.setTypeface(tf);
        disMainText.setTypeface(tf);
        invoMainText.setTypeface(tf);
        top_head.setTypeface(tf);
        top_head_app.setTypeface(tf);
        addtemplatetext.setTypeface(tf);
        addusertext.setTypeface(tf);
        settingicontext.setTypeface(tf);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Welcome to চাষী বার্তা \n" +
                "App এর Development  চলমান\n" );
        builder.setMessage("ধন্যবাদন্তে- কামরুল ইসলাম");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {


                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();


        disease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( MainActivity.this ,disease.class);
                startActivity(i);
                //Toast.makeText(getActivity(), "hello", Toast.LENGTH_LONG).show();
            }
        });
        groupsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( MainActivity.this ,groupsmsfilter.class);
                startActivity(i);
                //Toast.makeText(getActivity(), "hello", Toast.LENGTH_LONG).show();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( MainActivity.this ,farmerSearchActivity.class);
                startActivity(i);
                //Toast.makeText(getActivity(), "hello", Toast.LENGTH_LONG).show();
            }
        });

        inventor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( MainActivity.this ,InventorInformation.class);
                startActivity(i);
                //Toast.makeText(getActivity(), "hello", Toast.LENGTH_LONG).show();
            }
        });

        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( MainActivity.this ,AddUpdateUser.class);
                i.putExtra("update","false");
                startActivity(i);
                //Toast.makeText(getActivity(), "hello", Toast.LENGTH_LONG).show();
            }
        });

        addtemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( MainActivity.this ,AddTemplate.class);
                startActivity(i);
                //Toast.makeText(getActivity(), "hello", Toast.LENGTH_LONG).show();
            }
        });

        farming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( MainActivity.this ,fishFarming.class);
                startActivity(i);
                //Toast.makeText(getActivity(), "hello", Toast.LENGTH_LONG).show();
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( MainActivity.this ,Setting.class);
                startActivity(i);
                //Toast.makeText(getActivity(), "hello", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private long getInstallDate() {
        // get app installation date
        long installed=0;
        try {
            installed = this.getPackageManager().getPackageInfo("package.name", 0).firstInstallTime;
        }
        catch (Exception e){}
        return installed;
    }

    private void requestSmsPermission() {
        String permission = Manifest.permission.RECEIVE_SMS;
        int grant = ContextCompat.checkSelfPermission(this, permission);
        if ( grant != PackageManager.PERMISSION_GRANTED) {
            String[] permission_list = new String[1];
            permission_list[0] = permission;
            ActivityCompat.requestPermissions(this, permission_list, 1);
        }
    }
}

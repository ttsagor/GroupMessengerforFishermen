package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.BaboharbidiActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.ComingSoonActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.EditUserActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.LoginActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.ProfileActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.SotorkotaActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.SplashScreenActivity;

public class diseaseDetails extends AppCompatActivity {

    DiseaseModel disease = new DiseaseModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
bottomNavigationHandler();
        setContentView(R.layout.activity_disease_details);
        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        final DbHandler db = new DbHandler(this, null, null, 1);
        final Context mContext = this;


        TextView disease_name = (TextView) findViewById(R.id.disease_name);
        TextView disease_reason_text = (TextView) findViewById(R.id.disease_reason_text);
        TextView disease_reason = (TextView) findViewById(R.id.disease_reason);
        TextView disease_sym_text = (TextView) findViewById(R.id.disease_sym_text);
        TextView disease_sym = (TextView) findViewById(R.id.disease_sym);
        TextView disease_cure_text = (TextView) findViewById(R.id.disease_cure_text);
        final TextView disease_cure = (TextView) findViewById(R.id.disease_cure);
        ImageView disease_details_img = (ImageView) findViewById(R.id.disease_details_img);
        ImageView disease_details_share = (ImageView) findViewById(R.id.disease_details_share);
        ImageView diseaseEditIcon = (ImageView) findViewById(R.id.diseaseEditIcon);

        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);



        disease_name.setTypeface(tf);
        disease_reason_text.setTypeface(tf);
        disease_reason.setTypeface(tf);
        disease_sym_text.setTypeface(tf);
        disease_sym.setTypeface(tf);
        disease_cure_text.setTypeface(tf);
        disease_cure.setTypeface(tf);


        if (b != null) {
            try {
                final int diseaseID = (Integer) b.get("diseaseID");


                ArrayList<DiseaseModel> diseaseList = db.getDiseases(db.COLUMN_diseaseID + "=" + diseaseID);

                for (DiseaseModel cDisease : diseaseList) {
                    disease.setDiseaseID(cDisease.getDiseaseID());
                    disease.setDiseaseName(cDisease.getDiseaseName());
                    disease.setDiseaseReason(cDisease.getDiseaseReason());
                    disease.setDiseaseSym(cDisease.getDiseaseSym());
                    disease.setDiseaseVac(cDisease.getDiseaseVac());
                    disease.setDiseasePic(cDisease.getDiseasePic());
                }

                disease_name.setText(disease_name.getText() + " " + disease.getDiseaseName());
                disease_reason.setText(disease.getDiseaseReason());
                disease_sym.setText(disease.getDiseaseSym());
                disease_cure.setText(disease.getDiseaseVac());

                int checkExistence = this.getResources().getIdentifier(disease.getDiseasePic(), "drawable", this.getPackageName());

                if (checkExistence != 0) {
                    String uri = "@drawable/" + disease.getDiseasePic();
                    int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                    Drawable res = getResources().getDrawable(imageResource);
                    disease_details_img.setImageDrawable(res);
                } else {

                    String uri = "@drawable/noimage";
                    int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                    Drawable res = getResources().getDrawable(imageResource);
                    disease_details_img.setImageDrawable(res);
                }

                disease_details_share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                            sharingIntent.setType("text/plain");
                            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, disease.getDiseaseName() + " এর " + "প্রতিকার");
                            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,disease.getDiseaseVac());
                            startActivity(Intent.createChooser(sharingIntent, "Share via"));
                        } catch (Exception e) {
                            //Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                            //System.out.println( e.toString());
                        }
                    }
                });

            } catch (Exception e) {
            }

        }


        diseaseEditIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    AlertDialog.Builder alert = new AlertDialog.Builder(mContext);

                    alert.setTitle("প্রতিকার পরিবর্তন");
                    alert.setMessage(disease.getDiseaseName());
                    final EditText input = new EditText(mContext);
                    input.setText(disease.getDiseaseVac());

                    alert.setView(input);

                    alert.setNegativeButton("পরিবর্তন", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            disease.setDiseaseVac(input.getText().toString());
                            db.updateData(disease);
                            disease_cure.setText(disease.getDiseaseVac());
                        }
                    });

                    alert.setPositiveButton("ক্যানসেল", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                            // Do something with value!
                        }
                    });



                    alert.show();
                }catch(Exception e){

                }
            }
        });

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
                            startActivity(new Intent(diseaseDetails.this, SplashScreenActivity.class));

                        } else {
                            startActivity(new Intent(diseaseDetails.this, LoginActivity.class));
                        }
                        break;

                    case R.id.menu_sotorkota:
                        startActivity(new Intent(diseaseDetails.this, SotorkotaActivity.class));
                        break;
                    case R.id.menu_profile:
                        startActivity(new Intent(diseaseDetails.this, ProfileActivity.class));
                        break;
                    case R.id.menu_beboharbidi:
                        startActivity(new Intent(diseaseDetails.this, BaboharbidiActivity.class));
                        break;

                    case R.id.menu_somoshajomadin:
                        startActivity(new Intent(diseaseDetails.this, ComingSoonActivity.class));
                        break;
                }
                return false;
            }
        });
    }
}




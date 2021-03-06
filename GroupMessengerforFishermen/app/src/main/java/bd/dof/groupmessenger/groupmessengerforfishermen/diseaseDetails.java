package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class diseaseDetails extends AppCompatActivity {

    DiseaseModel disease = new DiseaseModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_disease_details);
        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        final DbHandler db = new DbHandler(this, null, null, 1);
        final Context mContext = this;
        TextView top_head_fish_disease_details = (TextView) findViewById(R.id.top_head_fish_disease_details);
        TextView top_head_app_fish_farming_disease_details = (TextView) findViewById(R.id.top_head_app_fish_farming_disease_details);
        TextView top_disease_banner_details = (TextView) findViewById(R.id.top_disease_banner_details);

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

        top_head_fish_disease_details.setTypeface(tf);
        top_head_app_fish_farming_disease_details.setTypeface(tf);
        top_disease_banner_details.setTypeface(tf);

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

}




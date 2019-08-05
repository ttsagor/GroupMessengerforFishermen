package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class disease extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_disease);
        final DbHandler db = new DbHandler(this,null,null,1);
        TextView top_head_fish_disease = (TextView) findViewById(R.id.top_head_fish_disease);
        TextView top_head_app_fish_farming_disease = (TextView) findViewById(R.id.top_head_app_fish_farming_disease);
        TextView top_disease_banner = (TextView) findViewById(R.id.top_disease_banner);
        ListView diseaseListListview = (ListView) findViewById(R.id.diseaseListListview);

        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);

        top_head_fish_disease.setTypeface(tf);
        top_head_app_fish_farming_disease.setTypeface(tf);
        top_disease_banner.setTypeface(tf);


       ArrayList<DiseaseModel> diseaseList =  db.getDiseases("1=1");

        for (DiseaseModel cDisease : diseaseList) {
            System.out.println(cDisease.getDiseaseID()+": "+ cDisease.getDiseaseName());
        }

        DiseaseListAdapter adapter = new DiseaseListAdapter(this, diseaseList);
        diseaseListListview.setAdapter(adapter);
    }
}

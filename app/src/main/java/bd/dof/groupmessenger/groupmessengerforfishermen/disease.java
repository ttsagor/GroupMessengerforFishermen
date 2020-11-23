package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class disease extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease);
        final DbHandler db = new DbHandler(this,null,null,1);

        GridView diseaseListListview = findViewById(R.id.diseaseListListview);

        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);



       ArrayList<DiseaseModel> diseaseList =  db.getDiseases("1=1");

        for (DiseaseModel cDisease : diseaseList) {
            System.out.println(cDisease.getDiseaseID()+": "+ cDisease.getDiseaseName());
        }

        DiseaseListAdapter adapter = new DiseaseListAdapter(this, diseaseList);
        diseaseListListview.setAdapter(adapter);
    }
}

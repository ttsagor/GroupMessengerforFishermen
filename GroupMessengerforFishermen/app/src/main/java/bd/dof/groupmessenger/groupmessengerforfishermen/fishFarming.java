package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class fishFarming extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_fish_farming);

        Button pona_mojud_formula = (Button) findViewById(R.id.pona_mojud_formula);
        Button khaddo_prooig_formula = (Button) findViewById(R.id.khaddo_prooig_formula);
        Button age_wise_food_formula = (Button) findViewById(R.id.age_wise_food_formula);
        Button protin_wise_food = (Button) findViewById(R.id.protin_wise_food);
        Button ppm_formula= (Button) findViewById(R.id.ppm_formula);
        TextView top_head_app_fish_farming_main = (TextView) findViewById(R.id.top_head_app_fish_farming_main);
        TextView top_head_fish_farming_main = (TextView) findViewById(R.id.top_head_fish_farming_main);
        TextView top_farming_banner = (TextView) findViewById(R.id.top_farming_banner);
        TextView fertilizer_formula = (TextView) findViewById(R.id.fertilizer_formula);

        TextView khetrofol_formula = (TextView) findViewById(R.id.khetrofol_formula);
        TextView area_formula = (TextView) findViewById(R.id.area_formula);

        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);

        top_head_app_fish_farming_main.setTypeface(tf);
        top_head_fish_farming_main.setTypeface(tf);
        top_farming_banner.setTypeface(tf);

        pona_mojud_formula.setTypeface(tf);
        khaddo_prooig_formula.setTypeface(tf);
        age_wise_food_formula.setTypeface(tf);
        protin_wise_food.setTypeface(tf);
        ppm_formula.setTypeface(tf);
        fertilizer_formula.setTypeface(tf);
        khetrofol_formula.setTypeface(tf);
        area_formula.setTypeface(tf);

        pona_mojud_formula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( fishFarming.this ,pona_mojud.class);
                startActivity(i);
            }
        });
        khaddo_prooig_formula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( fishFarming.this ,khaddo_prooig.class);
                startActivity(i);
            }
        });
        age_wise_food_formula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( fishFarming.this ,AgeWiseFood.class);
                startActivity(i);
            }
        });
        protin_wise_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( fishFarming.this ,Protein_base_food.class);
                startActivity(i);
            }
        });
        ppm_formula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( fishFarming.this ,ppm_formula.class);
                startActivity(i);
            }
        });

        fertilizer_formula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( fishFarming.this ,fertilizer_formula.class);
                startActivity(i);
            }
        });

        khetrofol_formula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( fishFarming.this ,KhetrofolFormula.class);
                startActivity(i);
            }
        });

        area_formula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( fishFarming.this ,AreaFormula.class);
                startActivity(i);
            }
        });
    }
}

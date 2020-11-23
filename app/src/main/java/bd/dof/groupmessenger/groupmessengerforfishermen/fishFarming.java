package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class fishFarming extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_farming);

        CardView pona_mojud_formula =  findViewById(R.id.pona_mojud_formula);
        CardView khaddo_prooig_formula = findViewById(R.id.khaddo_prooig_formula);
        CardView age_wise_food_formula =  findViewById(R.id.age_wise_food_formula);
        CardView protin_wise_food = findViewById(R.id.protin_wise_food);
        CardView ppm_formula=  findViewById(R.id.ppm_formula);
        CardView fertilizer_formula =  findViewById(R.id.fertilizer_formula);

        CardView khetrofol_formula =  findViewById(R.id.khetrofol_formula);
        CardView area_formula =  findViewById(R.id.area_formula);

        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);





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

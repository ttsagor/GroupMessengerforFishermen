package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class AreaFormula extends AppCompatActivity {

    TextView area_formula_length_text;
    TextView area_formula_height_text;
    TextView area_formula_width_text;
    TextView area_formula_sotangso_text;
    TextView area_formula_sotangso;
    TextView area_formula_hector_text;
    TextView area_formula_hector;

    EditText area_formula_length;
    EditText area_formula_height;
    EditText area_formula_width;

    Button area_formula_agePonaSubmit;

    ImageView area_formula_SaveImg;
    ImageView area_formula_ShareImg;

    TextView top_head_fish_farming_area_formula;
    TextView top_head_app_fish_farming_area_formula;
    TextView top_farming_banner_area_formula;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_area_formula);

        area_formula_length_text = (TextView) findViewById(R.id.area_formula_length_text);
        area_formula_height_text = (TextView) findViewById(R.id.area_formula_height_text);
        area_formula_width_text = (TextView) findViewById(R.id.area_formula_width_text);
        area_formula_sotangso_text = (TextView) findViewById(R.id.area_formula_sotangso_text);
        area_formula_sotangso = (TextView) findViewById(R.id.area_formula_sotangso);
        area_formula_hector_text = (TextView) findViewById(R.id.area_formula_hector_text);
        area_formula_hector = (TextView) findViewById(R.id.area_formula_hector);

        top_head_fish_farming_area_formula = (TextView) findViewById(R.id.top_head_fish_farming_area_formula);
        top_head_app_fish_farming_area_formula = (TextView) findViewById(R.id.top_head_app_fish_farming_area_formula);
        top_farming_banner_area_formula = (TextView) findViewById(R.id.top_farming_banner_area_formula);

        area_formula_length = (EditText) findViewById(R.id.area_formula_length);
        area_formula_height = (EditText) findViewById(R.id.area_formula_height);
        area_formula_width = (EditText) findViewById(R.id.area_formula_width);

        area_formula_ShareImg = (ImageView) findViewById(R.id.area_formula_ShareImg);

        area_formula_agePonaSubmit = (Button) findViewById(R.id.area_formula_agePonaSubmit);

        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);
        area_formula_length_text.setTypeface(tf);
        area_formula_height_text.setTypeface(tf);
        area_formula_width_text.setTypeface(tf);
        area_formula_sotangso_text.setTypeface(tf);
        area_formula_sotangso.setTypeface(tf);
        area_formula_hector_text.setTypeface(tf);
        area_formula_hector.setTypeface(tf);
        area_formula_length.setTypeface(tf);
        area_formula_height.setTypeface(tf);
        area_formula_width.setTypeface(tf);
        top_head_fish_farming_area_formula.setTypeface(tf);
        top_head_app_fish_farming_area_formula.setTypeface(tf);
        top_farming_banner_area_formula.setTypeface(tf);
        area_formula_agePonaSubmit.setTypeface(tf);

        area_formula_agePonaSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int area_formula_length_int = Integer.parseInt(area_formula_length.getText().toString());
                    int area_formula_height_int = Integer.parseInt(area_formula_height.getText().toString());
                    int area_formula_width_int = Integer.parseInt(area_formula_width.getText().toString());

                    double area_out = area_formula_length_int*area_formula_height_int*area_formula_width_int;
                    double area_out1 = area_out*28.3;

                    area_formula_sotangso.setText(pona_mojud.engToBng(String.format("%.3f", area_out)));
                    area_formula_hector.setText(pona_mojud.engToBng(String.format("%.3f", area_out1)));

                    if(getCurrentFocus()!=null) {
                        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    }

                }catch (Exception e){}
            }
        });

        area_formula_ShareImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");

                    String des = "পুকুরের দৈর্ঘ্য: " +pona_mojud.engToBng(area_formula_length.getText().toString())+"\n"+
                            "পুকুরের উচ্চতা: " +pona_mojud.engToBng(area_formula_height.getText().toString())+"\n"+
                            "পুকুরের প্রস্থ: " + pona_mojud.engToBng(area_formula_width.getText().toString())+"\n"+
                            "পুকুরের আয়তন (ঘনফুট): " + area_formula_sotangso.getText().toString()+"\n"+
                            "পুকুরের আয়তন (লিটার): " + area_formula_hector.getText();

                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"পুকুরের আয়তন নির্নয় ফরমুলা");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,des);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                } catch (Exception e) {
                    //Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                    //System.out.println( e.toString());
                }
            }
        });
    }
}

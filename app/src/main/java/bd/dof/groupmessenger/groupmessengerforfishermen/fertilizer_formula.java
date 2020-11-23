package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class fertilizer_formula extends AppCompatActivity {

    TextView fertilizer_length_text;
    TextView fertilizer_width_text;
    TextView fertilizer_qunatity_text;
    TextView fertilizer_area_text;
    TextView fertilizer_area;

    EditText fertilizer_length;
    EditText fertilizer_width;
    EditText fertilizer_qunatity;

    Button fertilizer_agePonaSubmit;

    ImageView fertilizer_SaveImg;
    ImageView fertilizer_ShareImg;

    TextView top_farming_banner_fertilizer;
    TextView top_head_app_fish_farming_fertilizer;
    TextView top_head_fish_farming_fertilizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_fertilizer_formula);

        fertilizer_length_text = (TextView) findViewById(R.id.fertilizer_length_text);
        fertilizer_width_text = (TextView) findViewById(R.id.fertilizer_width_text);
        fertilizer_qunatity_text = (TextView) findViewById(R.id.fertilizer_qunatity_text);
        fertilizer_area_text = (TextView) findViewById(R.id.fertilizer_area_text);
        fertilizer_area = (TextView) findViewById(R.id.fertilizer_area);

        fertilizer_length = (EditText) findViewById(R.id.fertilizer_length);
        fertilizer_width = (EditText) findViewById(R.id.fertilizer_width);
        fertilizer_qunatity = (EditText) findViewById(R.id.fertilizer_qunatity);

        fertilizer_agePonaSubmit = (Button) findViewById(R.id.fertilizer_agePonaSubmit);

        fertilizer_SaveImg = (ImageView) findViewById(R.id.fertilizer_SaveImg);
        fertilizer_ShareImg = (ImageView) findViewById(R.id.fertilizer_ShareImg);

        top_farming_banner_fertilizer = (TextView) findViewById(R.id.top_farming_banner_fertilizer);
        top_head_app_fish_farming_fertilizer = (TextView) findViewById(R.id.top_head_app_fish_farming_fertilizer);
        top_head_fish_farming_fertilizer = (TextView) findViewById(R.id.top_head_fish_farming_fertilizer);

        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);
        fertilizer_length_text.setTypeface(tf);
        fertilizer_width_text.setTypeface(tf);
        fertilizer_qunatity_text.setTypeface(tf);
        fertilizer_area_text.setTypeface(tf);
        fertilizer_area.setTypeface(tf);
        fertilizer_length.setTypeface(tf);
        fertilizer_width.setTypeface(tf);
        fertilizer_qunatity.setTypeface(tf);
        fertilizer_agePonaSubmit.setTypeface(tf);
        top_farming_banner_fertilizer.setTypeface(tf);
        top_head_app_fish_farming_fertilizer .setTypeface(tf);
        top_head_fish_farming_fertilizer.setTypeface(tf);



        fertilizer_agePonaSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int fertilizer_length_int = Integer.parseInt(fertilizer_length.getText().toString());
                    int fertilizer_width_int = Integer.parseInt(fertilizer_width.getText().toString());
                    int fertilizer_qunatity_int = Integer.parseInt(fertilizer_qunatity.getText().toString());

                    double area_out = (((fertilizer_length_int*fertilizer_width_int)/435.5)*fertilizer_qunatity_int)/1000;


                    fertilizer_area.setText(pona_mojud.engToBng(String.format("%.3f", area_out)));

                    if(getCurrentFocus()!=null) {
                        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    }

                }catch (Exception e){}
            }
        });

        fertilizer_ShareImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");

                    String des = "পুকুরের দৈর্ঘ্য: " +pona_mojud.engToBng(fertilizer_length.getText().toString())+"\n"+
                            "পুকুরের প্রস্থ: " + pona_mojud.engToBng(fertilizer_width.getText().toString())+"\n"+
                            "পরিমান(গ্রাম)/শতাংশ: " + pona_mojud.engToBng(fertilizer_qunatity.getText().toString())+"\n"+
                            "মোট চুন/সারের পরিমান (কেজি): " + fertilizer_area.getText();

                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"পুকুরের চুন/সার নির্নয় ফরমুলা");
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

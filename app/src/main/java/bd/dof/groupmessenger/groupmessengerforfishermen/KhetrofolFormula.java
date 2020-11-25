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
import androidx.appcompat.widget.AppCompatButton;

public class KhetrofolFormula extends AppCompatActivity {

    TextView khetrofol_length_text;
    TextView khetrofol_width_text;
    TextView khetrofol_sotangso_text;
    TextView khetrofol_sotangso;
    TextView khetrofol_hector_text;
    TextView khetrofol_hector;

    EditText khetrofol_length;
    EditText khetrofol_width;

    AppCompatButton khetrofol_agePonaSubmit;

    ImageView khetrofol_SaveImg;
    ImageView khetrofol_ShareImg;

    TextView top_head_fish_farming_khetrofol;
    TextView top_head_app_fish_farming_khetrofol;
    TextView top_farming_banner_khetrofol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_khetrofol_formula);


        khetrofol_sotangso_text = (TextView) findViewById(R.id.khetrofol_sotangso_text);
        khetrofol_sotangso = (TextView) findViewById(R.id.khetrofol_sotangso);
        khetrofol_hector_text = (TextView) findViewById(R.id.khetrofol_hector_text);
        khetrofol_hector = (TextView) findViewById(R.id.khetrofol_hector);



        khetrofol_agePonaSubmit =  findViewById(R.id.khetrofol_agePonaSubmit);

        khetrofol_length = (EditText) findViewById(R.id.khetrofol_length);
        khetrofol_width = (EditText) findViewById(R.id.khetrofol_width);

        khetrofol_ShareImg = (ImageView) findViewById(R.id.khetrofol_ShareImg);
        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);




        khetrofol_agePonaSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int fertilizer_length_int = Integer.parseInt(khetrofol_length.getText().toString());
                    int fertilizer_width_int = Integer.parseInt(khetrofol_width.getText().toString());

                    double area_out = (fertilizer_length_int*fertilizer_width_int)/435.5;
                    double hec_out = area_out/247;

                    khetrofol_sotangso.setText(pona_mojud.engToBng(String.format("%.3f", area_out)));
                    khetrofol_hector.setText(pona_mojud.engToBng(String.format("%.3f", hec_out)));

                    if(getCurrentFocus()!=null) {
                        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    }

                }catch (Exception e){}
            }
        });

        khetrofol_ShareImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");

                    String des = "পুকুরের দৈর্ঘ্য: " +pona_mojud.engToBng(khetrofol_length.getText().toString())+"\n"+
                            "পুকুরের প্রস্থ: " + pona_mojud.engToBng(khetrofol_width.getText().toString())+"\n"+
                            "পুকুরের শতাংশ: " + khetrofol_sotangso.getText().toString()+"\n"+
                            "পুকুরের হেক্টর: " + khetrofol_hector.getText();

                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"পুকুরের ক্ষেত্রফল নির্নয় ফরমুলা");
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

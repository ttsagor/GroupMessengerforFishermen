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

public class ppm_formula extends AppCompatActivity {

    TextView ppm_length_text;
    TextView ppm_height_text;
    TextView ppm_width_text;
    TextView ppm_density_text;
    TextView ppm_area_text;
    TextView ppm_area;
    TextView ppm_med_quantity_text;
    TextView ppm_med_quantity;
    EditText ppm_length;
    EditText ppm_height;
    EditText ppm_width;
    EditText ppm_density;
    AppCompatButton ppm_agePonaSubmit;
    ImageView ppm_SaveImg;
    ImageView ppm_ShareImg;
    TextView top_farming_banner_ppm;
    TextView top_head_app_fish_farming_ppm;
    TextView top_head_fish_farming_ppm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ppm_formula);


        ppm_area_text = (TextView) findViewById(R.id.ppm_area_text);
        ppm_area = (TextView) findViewById(R.id.ppm_area);
        ppm_med_quantity_text = (TextView) findViewById(R.id.ppm_med_quantity_text);
        ppm_med_quantity = (TextView) findViewById(R.id.ppm_med_quantity);

        ppm_length = (EditText) findViewById(R.id.ppm_length);
        ppm_height = (EditText) findViewById(R.id.ppm_height);
        ppm_width = (EditText) findViewById(R.id.ppm_width);
        ppm_density = (EditText) findViewById(R.id.ppm_density);

        ppm_agePonaSubmit =  findViewById(R.id.ppm_agePonaSubmit);

        ppm_SaveImg = (ImageView) findViewById(R.id.ppm_SaveImg);
        ppm_ShareImg = (ImageView) findViewById(R.id.ppm_ShareImg);
        ppm_agePonaSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int ppm_length_int = Integer.parseInt(ppm_length.getText().toString());
                    int ppm_height_int = Integer.parseInt(ppm_height.getText().toString());
                    int ppm_width_int = Integer.parseInt(ppm_width.getText().toString());
                    int ppm_density_int = Integer.parseInt(ppm_density.getText().toString());

                    double area_out = (ppm_length_int*ppm_height_int*ppm_width_int)*28.3;
                    double med_q_out = (area_out*ppm_density_int)/1000;

                    ppm_area.setText(pona_mojud.engToBng(String.format("%.3f", area_out)));
                    ppm_med_quantity.setText(pona_mojud.engToBng(String.format("%.3f", med_q_out)));

                    if(getCurrentFocus()!=null) {
                        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    }

                }catch (Exception e){}
            }
        });


        ppm_ShareImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");

                    String des = "পুকুর/চৌবাচ্চার  দৈর্ঘ্য : " +pona_mojud.engToBng(ppm_length.getText().toString())+"\n"+
                            "পুকুর/চৌবাচ্চার  উচ্চতা: " + pona_mojud.engToBng(ppm_height.getText().toString())+"\n"+
                            "পুকুর/চৌবাচ্চার  প্রস্থ: " + pona_mojud.engToBng(ppm_width.getText().toString())+"\n"+
                            "মাত্র (পিপিএম): " + pona_mojud.engToBng(ppm_density.getText().toString())+"\n"+
                            "পুকুর/চৌবাচ্চার আয়তন (লিটার): " + ppm_area.getText()+"\n"+
                            "দ্রবীভুত পদার্থ বা ঔষধের পরিমান: " + ppm_med_quantity.getText();

                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"পিপিএম নির্নয় ফরমুলা");
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

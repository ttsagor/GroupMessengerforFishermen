package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static bd.dof.groupmessenger.groupmessengerforfishermen.R.id.view;

public class farmerSearchActivity extends AppCompatActivity {
    ListView farmerinfolistview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_farmer_search);
    }

    @Override
    protected void onResume() {
        super.onResume();
        farmerinfolistview = (ListView) findViewById(R.id.farmerinfolistview);
        EditText searcheditbox = (EditText) findViewById(R.id.searcheditbox);

        TextView top_head_farmersearch = (TextView) findViewById(R.id.top_head_farmersearch);
        TextView smsMainFarmerSearchText = (TextView) findViewById(R.id.smsMainFarmerSearchText);
        TextView top_head_app_farmersearch = (TextView) findViewById(R.id.top_head_app_farmersearch);
        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);

        top_head_farmersearch.setTypeface(tf);
        smsMainFarmerSearchText.setTypeface(tf);
        searcheditbox.setTypeface(tf);
        top_head_app_farmersearch.setTypeface(tf);
        System.out.println("size : "+MainActivity.allFarmerInfo.size());
        MyCustomAdapter adapter = new MyCustomAdapter(this, MainActivity.allFarmerInfo);
        farmerinfolistview.setAdapter(adapter);

        searcheditbox.addTextChangedListener(new TextWatcher() {

            boolean ignoreChange = false;

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                String text = s.toString();
                text = text.replace("'", "");
                change(text);
            }
        });


    }

    void change(String cond)
    {
        ArrayList<FarmerInfoModel> users = new ArrayList<FarmerInfoModel>();
        for(FarmerInfoModel u : MainActivity.allFarmerInfo)
        {
            if(u.getFarmerName().contains(cond) || u.getFarmerFatherHusbandName().contains(cond) || u.getPhoneNumber().contains(cond))
            {
                users.add(u);
            }
        }

        MyCustomAdapter adapter = new MyCustomAdapter(this, users);
        farmerinfolistview.setAdapter(adapter);
    }
}

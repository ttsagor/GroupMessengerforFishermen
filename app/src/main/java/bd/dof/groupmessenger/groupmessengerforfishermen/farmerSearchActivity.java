package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.content.Context;
import android.graphics.Typeface;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.HomeScreenActivity;

import static bd.dof.groupmessenger.groupmessengerforfishermen.R.id.view;

public class farmerSearchActivity extends AppCompatActivity {
    ListView farmerinfolistview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_farmer_search);
    }

    @Override
    protected void onResume() {
        super.onResume();
        farmerinfolistview = (ListView) findViewById(R.id.farmerinfolistview);
        EditText searcheditbox = (EditText) findViewById(R.id.searcheditbox);


        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);
        searcheditbox.setTypeface(tf);
        MyCustomAdapter adapter = new MyCustomAdapter(this, HomeScreenActivity.allFarmerInfo);
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
        for(FarmerInfoModel u : HomeScreenActivity.allFarmerInfo)
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

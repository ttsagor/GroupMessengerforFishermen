package bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import bd.dof.groupmessenger.groupmessengerforfishermen.InventorInformation;
import bd.dof.groupmessenger.groupmessengerforfishermen.MainActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.R;
import bd.dof.groupmessenger.groupmessengerforfishermen.disease;
import bd.dof.groupmessenger.groupmessengerforfishermen.fishFarming;

public class HomeScreenActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        findViewById(R.id.ll_mosso_sutro).setOnClickListener(this);
        findViewById(R.id.ll_invention).setOnClickListener(this);
        findViewById(R.id.ll_desease).setOnClickListener(this);
        findViewById(R.id.ll_khude_barta_peron).setOnClickListener(this);
        findViewById(R.id.ll_chasi_onusandhan).setOnClickListener(this);


    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_mosso_sutro:
                startActivity(new Intent(HomeScreenActivity.this,fishFarming.class));

                break;
            case R.id.ll_invention:
                startActivity(new Intent(HomeScreenActivity.this,InventorInformation.class));
                break;
            case R.id.ll_desease:
                startActivity(new Intent( HomeScreenActivity.this , disease.class));
                break;
            case R.id.ll_khude_barta_peron:
                startActivity(new Intent( HomeScreenActivity.this , KhudebartaActivity.class));
                break;
            case R.id.ll_chasi_onusandhan:
                startActivity(new Intent( HomeScreenActivity.this , ChasiOnusandhanActivity.class));
                break;
        }
    }



}
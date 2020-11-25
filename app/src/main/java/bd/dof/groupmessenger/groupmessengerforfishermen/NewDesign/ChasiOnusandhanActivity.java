package bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import bd.dof.groupmessenger.groupmessengerforfishermen.AddFarmerPersonalInformation;
import bd.dof.groupmessenger.groupmessengerforfishermen.AddUpdateUser;
import bd.dof.groupmessenger.groupmessengerforfishermen.R;
import bd.dof.groupmessenger.groupmessengerforfishermen.farmerSearchActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.groupsmsfilter;

public class ChasiOnusandhanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chasi_onusandhan);
    }

    public void chashionusandhanSystem(View view) {
        startActivity(new Intent(ChasiOnusandhanActivity.this, farmerSearchActivity.class));
    }

    public void chasisongjojonSystem(View view) {
        startActivity(new Intent(ChasiOnusandhanActivity.this, AddUpdateUser.class));
    }

    public  void chasiEditSystem(View view){

        startActivity(new Intent(ChasiOnusandhanActivity.this, EditUserActivity.class));
    }
}
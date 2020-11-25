package bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import bd.dof.groupmessenger.groupmessengerforfishermen.AddTemplate;
import bd.dof.groupmessenger.groupmessengerforfishermen.MainActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.R;
import bd.dof.groupmessenger.groupmessengerforfishermen.fishFarming;
import bd.dof.groupmessenger.groupmessengerforfishermen.groupsmsfilter;

public class KhudebartaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khudebarta);
    }

    public void khudebartaperonSystem(View view) {

        startActivity(new Intent(KhudebartaActivity.this, groupsmsfilter.class));

    }

    public void khudebartsongjojonSystem(View view) {
    startActivity(new Intent(KhudebartaActivity.this , AddTemplate.class));
    }

}
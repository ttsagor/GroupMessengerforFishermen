package bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.Pages.HomeFragment;
import bd.dof.groupmessenger.groupmessengerforfishermen.R;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        SetFragment(new HomeFragment());

    }
    //change the fragment
    private void SetFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack("home").commit();

    }
}
package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class InventorInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_inventor_information);

        TextView top_head_inova = (TextView) findViewById(R.id.top_head_inova);
        TextView smsMainInovationText = (TextView) findViewById(R.id.smsMainInovationText);
        TextView invotech = (TextView) findViewById(R.id.invotech);
        TextView invoname = (TextView) findViewById(R.id.invoname);
        TextView invobsc = (TextView) findViewById(R.id.invobsc);
        TextView invoncad = (TextView) findViewById(R.id.invoncad);
        TextView invodeg = (TextView) findViewById(R.id.invodeg);
        TextView invonarea = (TextView) findViewById(R.id.invonarea);
        TextView invophone = (TextView) findViewById(R.id.invophone);
        TextView invoemail = (TextView) findViewById(R.id.invoemail);
        TextView top_head_app_inova = (TextView) findViewById(R.id.top_head_app_inova);
        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);

        top_head_inova.setTypeface(tf);
        smsMainInovationText.setTypeface(tf);
        invotech.setTypeface(tf);
        invoname.setTypeface(tf);
        invobsc.setTypeface(tf);
        invoncad.setTypeface(tf);
        invodeg.setTypeface(tf);
        invonarea.setTypeface(tf);
        invophone.setTypeface(tf);
        invoemail.setTypeface(tf);
        top_head_app_inova.setTypeface(tf);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inventor_information, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

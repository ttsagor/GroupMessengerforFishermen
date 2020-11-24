package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class GroupSmsNewMessage extends AppCompatActivity {
    String message="";
    String unionID="";
    String villageID="";
    String wardID="";
    DbHandler dbc;
    TextView GroupSmsNewMessageTotalFarme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_group_sms_new_message);


        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);

        TextView GroupSmsNewMessageTotalFarmer = (TextView) findViewById(R.id.GroupSmsNewMessageTotalFarmer);




        GroupSmsNewMessageTotalFarmer.setTypeface(tf);


        final DbHandler db = new DbHandler(this,null,null,1);
        dbc = db;
        GroupSmsNewMessageTotalFarme=(TextView)findViewById(R.id.GroupSmsNewMessageTotalFarmer);
        final Button GroupSmsNewMessageSend = (Button) findViewById(R.id.GroupSmsNewMessageSend);
        final Button GroupSmsNewMessageSave = findViewById(R.id.GroupSmsNewMessageSave);
        final EditText GroupSmsNewMessageText = (EditText) findViewById(R.id.GroupSmsNewMessageText);
        GroupSmsNewMessageTotalFarme.setText(GroupSmsNewMessageTotalFarme.getText()+" "+pona_mojud.engToBng(String.valueOf(groupsmsfilter.finalRecipient.size())));
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        GroupSmsNewMessageSend.setTypeface(tf);
        GroupSmsNewMessageText.setTypeface(tf);
        if(b!=null)
        {
            message =(String) b.get("message");
        }
        GroupSmsNewMessageText.setText(message);

        GroupSmsNewMessageSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<FarmerInfoModel> farmerInfoList = groupsmsfilter.finalRecipient;

               /* if(GroupSmsNewMessageText.getText().length()>160)
                {
                    Toast.makeText(getBaseContext(), "Maximum Character Limit 160.Your Text has "+GroupSmsNewMessageText.getText().length()+" Character", Toast.LENGTH_LONG).show();
                }
                else*/
                if(farmerInfoList.size()>0 && GroupSmsNewMessageText.getText().length()>0){
                    Intent i = new Intent( GroupSmsNewMessage.this ,recpiant_limit.class);
                    i.putExtra("message", GroupSmsNewMessageText.getText().toString());
                    startActivity(i);
                }
            }
        });
        GroupSmsNewMessageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(GroupSmsNewMessageText.getText().length()>0) {
                    SmsTemplateModel smsTemplateModel = new SmsTemplateModel();
                    smsTemplateModel.setSmsTemplateText(GroupSmsNewMessageText.getText().toString());
                    db.insertData(smsTemplateModel);
                    Toast.makeText(getBaseContext(), "টেম্পটে সেভ করা সম্পূর্ণ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_group_sms_new_message, menu);
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

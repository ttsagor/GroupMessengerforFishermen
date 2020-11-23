package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.graphics.Typeface;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class AddTemplate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_template);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        final DbHandler db = new DbHandler(this,null,null,1);
        final ImageButton addtemplateMsgBoxMessageSave = (ImageButton) findViewById(R.id.addtemplateMsgBoxMessageSave);
        final EditText addtemplateMsgBox = (EditText) findViewById(R.id.addtemplateMsgBox);
        final TextView top_head_add_template = (TextView) findViewById(R.id.top_head_add_template);
        final TextView top_head_app_add_template = (TextView) findViewById(R.id.top_head_app_add_template);
        final TextView add_template_title_Text = (TextView) findViewById(R.id.add_template_title_Text);

        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);

        top_head_add_template.setTypeface(tf);
        top_head_app_add_template.setTypeface(tf);
        add_template_title_Text.setTypeface(tf);
        addtemplateMsgBoxMessageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addtemplateMsgBox.getText().length()>0) {
                    SmsTemplateModel smsTemplateModel = new SmsTemplateModel();
                    smsTemplateModel.setSmsTemplateText(addtemplateMsgBox.getText().toString());
                    db.insertData(smsTemplateModel);
                    Toast.makeText(getBaseContext(), "টেম্পটে সেভ করা সম্পূর্ণ", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getBaseContext(), "টেম্পটে তথ্য প্রদান করুন", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_template, menu);
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

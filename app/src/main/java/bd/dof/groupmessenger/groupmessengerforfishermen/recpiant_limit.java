package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.florescu.android.rangeseekbar.RangeSeekBar;

public class recpiant_limit extends AppCompatActivity {

    String message="";
    public static ArrayList<String> MobNumberForground = new ArrayList<String>();
    Context mContext;
    Intent service;
    int phnGp = 0;
    int phnRobi = 0;
    int phnTT = 0;
    int phnAT = 0;
    int phnBL = 0;
    int phnCS = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recpiant_limit);
        mContext = this;

        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null)
        {
            message =(String) b.get("message");
        }

        final TextView title = (TextView) findViewById(R.id.limit_title_total_count);
        final EditText limit_start_point = (EditText) findViewById(R.id.limit_start_point);
        final EditText limit_end_point = (EditText) findViewById(R.id.limit_end_point);
        final Button limit_button_start_activity = (Button) findViewById(R.id.limit_button_start_activity);
        final TextView limit_title_sms_text = (TextView) findViewById(R.id.limit_title_sms_text);
        final TextView smsMainReceptLimitText = (TextView) findViewById(R.id.smsMainReceptLimitText);
        final TextView limit_title_total_count = (TextView) findViewById(R.id.limit_title_total_count);
        final TextView top_head_recept_limit = (TextView) findViewById(R.id.top_head_recept_limit);
        final TextView top_head_app_recept_limit = (TextView) findViewById(R.id.top_head_app_recept_limit);
        final CheckBox smsreceptlimitaddremindercheck = (CheckBox) findViewById(R.id.smsreceptlimitaddremindercheck);
        final TextView recpiant_limit_to_txt = (TextView) findViewById(R.id.recpiant_limit_to_txt);
        final TextView recpiant_limit_from_txt = (TextView) findViewById(R.id.recpiant_limit_from_txt);
        final Context mContext = this;
        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);

        limit_title_sms_text.setTypeface(tf);
        smsMainReceptLimitText.setTypeface(tf);
        limit_title_total_count.setTypeface(tf);
        top_head_recept_limit.setTypeface(tf);
        smsMainReceptLimitText.setTypeface(tf);
        top_head_app_recept_limit.setTypeface(tf);
        limit_button_start_activity.setTypeface(tf);
        recpiant_limit_to_txt.setTypeface(tf);
        recpiant_limit_from_txt.setTypeface(tf);
        smsreceptlimitaddremindercheck.setTypeface(tf);
        limit_title_sms_text.setText(message);

        final List<String> MobNumber = new ArrayList<String>();
        for(int i=0;i<groupsmsfilter.finalRecipient.size();i++)
        {
            if(groupsmsfilter.finalRecipient.get(i).getPhoneNumber().length()==11)
            {
                if(!MobNumber.contains(groupsmsfilter.finalRecipient.get(i).getPhoneNumber())) {
                    MobNumber.add(groupsmsfilter.finalRecipient.get(i).getPhoneNumber());
                }
            }
        }
        final RangeSeekBar<Integer> seekBar = (RangeSeekBar<Integer>) findViewById(R.id.pricebar_with_label);
        seekBar.setRangeValues(1,MobNumber.size());

        seekBar.setNotifyWhileDragging(true);
        seekBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener<Integer>() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar<?> bar, Integer minValue, Integer maxValue) {
                limit_start_point.setText(String.valueOf(minValue));
                limit_end_point.setText(String.valueOf(maxValue));
            }
        });

        limit_start_point.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    seekBar.setSelectedMinValue(Integer.parseInt(limit_start_point.getText().toString()));
                    seekBar.setSelectedMaxValue( Integer.parseInt(limit_end_point.getText().toString()));
                }
                catch (Exception e)
                {

                }
            }
        });

        limit_end_point.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    seekBar.setSelectedMinValue(Integer.parseInt(limit_start_point.getText().toString()));
                    seekBar.setSelectedMaxValue( Integer.parseInt(limit_end_point.getText().toString()));
                }
                catch (Exception e)
                {

                }
            }
        });

        title.setText(title.getText().toString()+" "+pona_mojud.engToBng(String.valueOf(MobNumber.size())));
        limit_end_point.setText(String.valueOf(MobNumber.size()));

        limit_button_start_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(smsreceptlimitaddremindercheck.isChecked())
                {
                    int limit_start_point_val = Integer.parseInt(limit_start_point.getText().toString());
                    int limit_end_point_val = Integer.parseInt(limit_end_point.getText().toString());
                    int recpL = limit_end_point_val - limit_start_point_val+1;
                    Calendar cal = Calendar.getInstance();
                    Intent intent = new Intent(Intent.ACTION_EDIT);
                    intent.setType("vnd.android.cursor.item/event");
                    intent.putExtra("beginTime", cal.getTimeInMillis());
                    intent.putExtra("allDay", true);
                    intent.putExtra("rrule", "FREQ=YEARLY");
                    intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
                    intent.putExtra("title", "Group Messenger Reminder");
                    intent.putExtra("description", "Total Recipient: "+recpL+"\n"+message);
                    startActivity(intent);
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                try {
                    int limit_start_point_val = Integer.parseInt(limit_start_point.getText().toString());
                    int limit_end_point_val = Integer.parseInt(limit_end_point.getText().toString());
                    if(limit_start_point_val>limit_end_point_val || limit_start_point_val>MobNumber.size() || limit_end_point_val>MobNumber.size() || limit_end_point_val<1 || limit_start_point_val<1) {

                        Toast.makeText(getBaseContext(), "Invalid Input", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        service = new Intent(recpiant_limit.this, ForegroundService.class);
                        if (!isMyServiceRunning(ForegroundService.class)) {

                            MobNumberForground = new ArrayList<String>();
                            phnGp = 0;
                            phnRobi = 0;
                            phnTT = 0;
                            phnAT = 0;
                            phnBL = 0;
                            phnCS = 0;

                            for(int i=limit_start_point_val-1;i<limit_end_point_val;i++)
                            {
                                if(!MobNumberForground.contains(MobNumber.get(i)))
                                {
                                    String phn = MobNumber.get(i).substring(0,3);
                                    System.out.println(phn);
                                    if(phn.contains("011"))
                                    {
                                        phnCS++;
                                    }
                                    else if(phn.contains("015"))
                                    {
                                        phnTT++;
                                    }
                                    else if(phn.contains("016"))
                                    {
                                        phnAT++;
                                    }
                                    else if(phn.contains("017"))
                                    {
                                        phnGp++;
                                    }
                                    else if(phn.contains("018"))
                                    {
                                        phnRobi++;
                                    }
                                    else if(phn.contains("019"))
                                    {
                                        phnBL++;
                                    }
                                    MobNumberForground.add(MobNumber.get(i));
                                }
                            }
                            ForegroundService.sentMobNumber = new ArrayList<String>();
                            ForegroundService.failMobNumber = new ArrayList<String>();
                            ForegroundService.ii=0;
                            ForegroundService.isPausePresses =false;
                            ForegroundService.isSendingComplete =false;
                            ForegroundService.isSMSSend=false;
                            ForegroundService.message = message;
                            ForegroundService.currentTime = System.currentTimeMillis();
                            service.setAction(Constants.ACTION.PLAY_ACTION);


                        } else {
                            Toast.makeText(getBaseContext(), "Already Running", Toast.LENGTH_LONG).show();

                        }
                    }
                }
                catch (Exception e)
                {
                    Toast.makeText(getBaseContext(), "Invalid Input", Toast.LENGTH_LONG).show();
                }
                builder.setTitle("আপনি কি নিশ্চিত?");
                String CT = "";
                if(phnGp!=0)
                {
                    CT +="গ্রামীণফোন : "+pona_mojud.engToBng(String.valueOf(phnGp));
                }
                if(phnRobi!=0)
                {
                    CT +="\nরবি: "+pona_mojud.engToBng(String.valueOf(phnRobi));
                }
                if(phnTT!=0)
                {
                    CT +="\nটেলিটক: "+pona_mojud.engToBng(String.valueOf(phnTT));
                }
                if(phnAT!=0)
                {
                    CT +="\nএয়ারটেল: "+pona_mojud.engToBng(String.valueOf(phnAT));
                }
                if(phnBL!=0)
                {
                    CT +="\nবাংলালিংক: "+pona_mojud.engToBng(String.valueOf(phnBL));
                }
                if(phnCS!=0)
                {
                    CT +="\nসিটিসেল: "+pona_mojud.engToBng(String.valueOf(phnCS));
                }
                builder.setMessage(CT+"\nআপনি কি নিশ্চিত?");

                builder.setNegativeButton("হ্যাঁ", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        startService(service);
                        dialog.dismiss();
                    }
                });

                builder.setPositiveButton("না", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        // Do nothing
                        dialog.dismiss();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();



            }
        });
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}

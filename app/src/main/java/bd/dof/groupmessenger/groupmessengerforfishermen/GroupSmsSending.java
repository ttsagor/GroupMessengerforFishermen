package bd.dof.groupmessenger.groupmessengerforfishermen;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;

import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
public class GroupSmsSending extends AppCompatActivity {

    String messageInt="";
    List<String> MobNumber = new ArrayList<String>();
    TextView smsSendingtotalRecipnt;
    TextView smsSendingtotalSend;
    TextView smsSendingtotalPending;
    TextView smsSendingtotalfailed;
    TextView textView2;
    ProgressBar progressBar1;
    LinearLayout processBarHolderLayout;
    boolean smsSendFlag = false;
    List<String> sentMobNumber = new ArrayList<String>();
    List<String> failMobNumber = new ArrayList<String>();

    int smsSendingtotalSendCount=0;
    int smsSendingtotalPendingCount=0;
    int smsSendingtotalfailedCount=0;
    Context contex;
    long currentTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_group_sms_sending);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        smsSendingtotalSend=(TextView)findViewById(R.id.smsSendingtotalSend);
        smsSendingtotalPending=(TextView)findViewById(R.id.smsSendingtotalPending);
        smsSendingtotalRecipnt=(TextView)findViewById(R.id.smsSendingtotalRecipnt);
        smsSendingtotalfailed=(TextView)findViewById(R.id.smsSendingtotalfailed);
        progressBar1=(ProgressBar) findViewById(R.id.progressBar1);
        processBarHolderLayout = (LinearLayout) findViewById(R.id.processBarHolderLayout);
        textView2 = (TextView)findViewById(R.id.textView2);
        contex = this;

        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null)
        {
            messageInt =(String) b.get("message");
        }

        sentMobNumber = new ArrayList<String>();
        failMobNumber = new ArrayList<String>();

        for(int i=0;i<groupsmsfilter.finalRecipient.size();i++)
        {
            if(groupsmsfilter.finalRecipient.get(i).getPhoneNumber().length()==11)
            {
                if(!MobNumber.contains(groupsmsfilter.finalRecipient.get(i).getPhoneNumber())) {
                    MobNumber.add(groupsmsfilter.finalRecipient.get(i).getPhoneNumber());
                }
            }
        }
        smsSendingtotalRecipnt.setText(smsSendingtotalRecipnt.getText() + String.valueOf(MobNumber.size()));
        smsSendingtotalPendingCount = MobNumber.size();
        smsSendingtotalPending.setText(": " + String.valueOf(smsSendingtotalPendingCount));

        checkIfDone();
        currentTime = System.currentTimeMillis();
        new Thread(new Runnable() {
            public void run() {
                for(int i=0;i<MobNumber.size();i++)
                {

                    while (smsSendFlag)
                    {}
                    smsSendFlag = true;
                    try {
                        if (messageInt.length() >= 160) {
                            try {
                                sendMultiPartSmS(MobNumber.get(i), messageInt);
                            }catch (Exception e){}
                        } else {
                            try {
                                sendMultiPartSmS(MobNumber.get(i), messageInt);
                            }catch (Exception e){}
                        }
                    }catch (Exception e){Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_SHORT).show();}
                }
            }
        }).start();


        new Thread(new Runnable() {
            public void run() {
                try {
                    searchInbox();
                }catch (Exception e){}
            }
        }).start();
    }

    private void checkIfDone()
    {
        smsSendFlag = false;
        if(smsSendingtotalPendingCount<=0)
        {
            GroupSmsSending.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressBar1.setVisibility(View.GONE);
                    ImageView imageView = new ImageView(contex);
                    imageView.setImageResource(R.drawable.right_sign);
                    imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    processBarHolderLayout.addView(imageView);
                    textView2.setText("ক্ষুদেবার্তা প্রেরন সম্পূর্ণ");
                }//public void run() {
            });

        }

    }

    public static List<String> splitEqually(String text, int size) {
        // Give the list the right capacity to start with. You could use an array
        // instead if you wanted.
        List<String> ret = new ArrayList<String>((text.length() + size - 1) / size);

        for (int start = 0; start < text.length(); start += size) {
            ret.add(text.substring(start, Math.min(text.length(), start + size)));
        }
        return ret;
    }

    boolean canExit=false;
    @Override
    public void onBackPressed() {
        if(canExit)
            super.onBackPressed();
        else{
            canExit = true;
            Toast.makeText(getApplicationContext(), "Press again", Toast.LENGTH_SHORT).show();
        }
        mHandler.sendEmptyMessageDelayed(1, 2000/*time interval to next press in milli second*/);// if not pressed within 2seconds then will be setted(canExit) as false
    }


    public Handler mHandler = new Handler(){

        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {
                case 1:
                    canExit = false;
                    break;
                default:
                    break;
            }
        }
    };


    void sendMultiPartSmS(String ph_no, String str) {
        try {
            Context ctx = this;
            SmsManager sm = SmsManager.getDefault();
            final String SMS_SEND_ACTION = "CTS_SMS_SEND_ACTION";
            final String SMS_DELIVERY_ACTION = "CTS_SMS_DELIVERY_ACTION";

            IntentFilter sendIntentFilter = new IntentFilter(SMS_SEND_ACTION);
            IntentFilter receiveIntentFilter = new IntentFilter(SMS_DELIVERY_ACTION);

            PendingIntent sentPI = PendingIntent.getBroadcast(ctx, 0, new Intent(SMS_SEND_ACTION), 0);
            PendingIntent deliveredPI = PendingIntent.getBroadcast(ctx, 0, new Intent(SMS_DELIVERY_ACTION), 0);

            //final String phone_no = ph_no;

            ArrayList<String> parts = sm.divideMessage(str);

            ArrayList<PendingIntent> sentIntents = new ArrayList<PendingIntent>();
            ArrayList<PendingIntent> deliveryIntents = new ArrayList<PendingIntent>();

            for (int i = 0; i < parts.size(); i++) {
                sentIntents.add(PendingIntent.getBroadcast(ctx, 0, new Intent(SMS_SEND_ACTION), 0));
                deliveryIntents.add(PendingIntent.getBroadcast(ctx, 0, new Intent(SMS_DELIVERY_ACTION), 0));
            }

            sm.sendMultipartTextMessage(ph_no, null, parts, sentIntents, deliveryIntents);
            System.out.println("sendMultipartTextMessage :" + ph_no);
        }catch (Exception e)
        {
            Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    protected void onPause()
    {
        super.onPause();
    }

    private void searchInbox()
    {

        try {
            Cursor cursor = getContentResolver().query(Uri.parse("content://sms/sent"), null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    String msgDataNumber = cursor.getString(cursor.getColumnIndex("address"));
                    long msgDatadate = Long.parseLong(cursor.getString(cursor.getColumnIndex("date")));
                    if (!sentMobNumber.contains(msgDataNumber) && currentTime <= msgDatadate && MobNumber.contains(msgDataNumber)) {
                        sentMobNumber.add(msgDataNumber);
                        smsSendingtotalPendingCount--;
                        smsSendingtotalSendCount++;

                        GroupSmsSending.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                smsSendingtotalPending.setText(": " + smsSendingtotalPendingCount);
                                smsSendingtotalSend.setText(": " + smsSendingtotalSendCount);
                            }//public void run() {
                        });
                        System.out.println("sent number:" + msgDataNumber  + " time: " + msgDatadate);
                        checkIfDone();
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
        }catch (Exception e){}

        try {
            Cursor cursor = getContentResolver().query(Uri.parse("content://sms/failed"), null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    String msgDataNumber = cursor.getString(cursor.getColumnIndex("address"));
                    long msgDatadate = Long.parseLong(cursor.getString(cursor.getColumnIndex("date")));
                    if (!failMobNumber.contains(msgDataNumber) && currentTime <= msgDatadate && MobNumber.contains(msgDataNumber)) {
                        failMobNumber.add(msgDataNumber);
                        GroupSmsSending.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                smsSendingtotalPending.setText(": " + smsSendingtotalPendingCount);
                                smsSendingtotalfailed.setText(": " + smsSendingtotalfailedCount);
                            }//public void run() {
                        });
                        System.out.println("failed number:" + msgDataNumber  + " time: " + msgDatadate);
                        smsSendingtotalPendingCount--;
                        smsSendingtotalfailedCount++;
                        checkIfDone();
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
        }catch (Exception e){}
        try {
            Thread.sleep(2000);
        }catch (Exception e){}
        searchInbox();
    }

}

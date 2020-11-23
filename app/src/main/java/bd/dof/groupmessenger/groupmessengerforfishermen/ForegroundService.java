package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.IBinder;

import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.util.ArrayList;
import java.util.List;

public class ForegroundService extends Service {
    private static final String LOG_TAG = "ForegroundService";

    public static boolean IS_SERVICE_RUNNING = false;

    int flags;
    int startId;
    Thread thread;
    Thread threadInbox;

    @Override
    public void onCreate() {
        super.onCreate();
    }
    public static boolean isPausePresses =false;
    public static boolean isSendingComplete =false;
    public static boolean isSMSSend=false;
    public static int threadID = 0;
    public static long currentTime;
    public static String message;
    public static ArrayList<String> sentMobNumber = new ArrayList<String>();
    public static ArrayList<String> failMobNumber = new ArrayList<String>();

    public static ArrayList<String> forgroundFinalRecp = new ArrayList<String>();
    public static int ii=0;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        forgroundFinalRecp = recpiant_limit.MobNumberForground;
        this.flags = flags;
        this.startId = startId;
        if(!isSendingComplete)
        {
            threadID++;
            thread = new Thread(new Runnable() {
                public void run() {
                    beginSendingSMS(threadID);
                }});
            thread.start();

            threadInbox = new Thread(new Runnable() {
                public void run() {
                    try {
                        searchInbox();
                    }catch (Exception e){}
                }
            });
            threadInbox.start();
        }
        if (intent.getAction().equals(Constants.ACTION.STARTFOREGROUND_ACTION)) {

            isPausePresses = false;
            showNotification();
            Toast.makeText(this, "SMS Sending Started", Toast.LENGTH_SHORT).show();


        } else if (intent.getAction().equals(Constants.ACTION.PAUSE_ACTION)) {

            isPausePresses = true;


            threadInbox.interrupt();
            threadInbox = null;

            thread.interrupt();
            thread = null;

            showNotification();
            Toast.makeText(this, "SMS Sending Paused", Toast.LENGTH_SHORT).show();
        }
        else if (intent.getAction().equals(Constants.ACTION.PLAY_ACTION)) {

            isPausePresses = false;
            showNotification();
            Toast.makeText(this, "SMS Sending Resumed", Toast.LENGTH_SHORT).show();
        }
        else if (intent.getAction().equals(Constants.ACTION.STOP_ACTION)) {
            isPausePresses = true;

            threadInbox.interrupt();
            threadInbox = null;

            thread.interrupt();
            thread = null;

            onDestroy();
            stopForeground(true);
            stopSelf();
        }

        return START_STICKY;
    }

    private void showNotification() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setAction(Constants.ACTION.MAIN_ACTION);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        Intent stopIntent = new Intent(this, ForegroundService.class);
        stopIntent.setAction(Constants.ACTION.STOP_ACTION);
        PendingIntent pstopIntent = PendingIntent.getService(this, 0,
                stopIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent playIntent = new Intent(this, ForegroundService.class);
        playIntent.setAction(Constants.ACTION.PLAY_ACTION);
        PendingIntent pplayIntent = PendingIntent.getService(this, 0,
                playIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Intent pauseIntent = new Intent(this, ForegroundService.class);
        pauseIntent.setAction(Constants.ACTION.PAUSE_ACTION);
        PendingIntent ppauseIntent = PendingIntent.getService(this, 0,
                pauseIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                R.drawable.icon);

        if(isSendingComplete)
        {
            int send = sentMobNumber.size()+1;
            Notification notification = new NotificationCompat.Builder(this)
                    .setContentTitle("Group Messenger")
                    .setTicker("Group Messenger")
                    .setContentText("Message Sending Completed")
                    .setSubText("Sent: " + send +" || Failed: "+ failMobNumber.size() + " || Total: " + forgroundFinalRecp.size())
                    .setSmallIcon(R.drawable.icon)
                    .setLargeIcon(Bitmap.createScaledBitmap(icon, 128, 128, false))
                    .setContentIntent(pendingIntent)
                    .setOngoing(true)
                    .addAction(android.R.drawable.ic_media_previous, "Close",
                            pstopIntent).build();

            startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE,
                    notification);
        }
        else {
            if (isPausePresses) {
                Notification notification = new NotificationCompat.Builder(this)
                        .setContentTitle("Group Messenger")
                        .setTicker("Group Messenger")
                        .setContentText("Total Recipient : " + forgroundFinalRecp.size())
                        .setSubText("Sent: " + sentMobNumber.size() +" || Failed: "+ failMobNumber.size() + " || Pending: " + (forgroundFinalRecp.size()-sentMobNumber.size()-failMobNumber.size()))
                        .setSmallIcon(R.drawable.icon)
                        .setLargeIcon(Bitmap.createScaledBitmap(icon, 128, 128, false))
                        .setContentIntent(pendingIntent)
                        .setOngoing(true)
                        .addAction(android.R.drawable.ic_media_play, "Play",
                                pplayIntent)
                        .addAction(android.R.drawable.ic_media_previous, "Stop",
                                pstopIntent).build();

                startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE,
                        notification);
            } else {
                Notification notification = new NotificationCompat.Builder(this)
                        .setContentTitle("Group Messenger")
                        .setTicker("Group Messenger")
                        .setSubText("Sent: " + sentMobNumber.size() +" || Failed: "+ failMobNumber.size() + " || Pending: " + (forgroundFinalRecp.size()-sentMobNumber.size()-failMobNumber.size()))
                        .setContentText("Total Recipient : " + forgroundFinalRecp.size())
                        .setSmallIcon(R.drawable.icon)
                        .setLargeIcon(Bitmap.createScaledBitmap(icon, 128, 128, false))
                        .setContentIntent(pendingIntent)
                        .setOngoing(true)
                        .addAction(android.R.drawable.ic_media_pause, "Pause",
                                ppauseIntent)
                        .addAction(android.R.drawable.ic_media_previous, "Stop",
                                pstopIntent).build();

                startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE,
                        notification);

            }
        }

    }


    void beginSendingSMS(int threadID)
    {
        while(!isPausePresses && !isSendingComplete)
        {
            System.out.println("threadIDrunning: "+ii+" "+forgroundFinalRecp.size());
            if(ii>=forgroundFinalRecp.size())
            {
                System.out.println("threadIDrunningii: "+ii+" "+forgroundFinalRecp.size());
                isSendingComplete=true;
                showNotification();
                break;
            }
            else
            {
                System.out.println("threadIDrunningelse: "+ii+" "+forgroundFinalRecp.size()+" "+isSMSSend);
                while(isSMSSend){
                    System.out.println("threadIDrunningelse: in while");
                }
                System.out.println("threadIDrunningelsewhile: "+ii+" "+forgroundFinalRecp.size()+" "+isSMSSend);
                isSMSSend=true;
                sendMultiPartSmS(forgroundFinalRecp.get(ii), message);
                System.out.println("threadID: "+threadID+" ii : " + ii + " Mob: "+forgroundFinalRecp.get(ii)+" "+isPausePresses+" "+isSendingComplete);
                ii++;
                showNotification();
                System.out.println("threadIDrunningelseend: "+ii+" "+forgroundFinalRecp.size());
            }

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "In onDestroy");
        //Toast.makeText(this, "Service Detroyed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Used only in case if services are bound (Bound Services).
        return null;
    }

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
            //System.out.println("sendMultipartTextMessage :" + ph_no);
        }catch (Exception e)
        {
            Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void searchInbox()
    {

        try {
            Cursor cursor = getContentResolver().query(Uri.parse("content://sms/sent"), null, null, null, null);
            if (cursor.moveToFirst()) {
                do {
                    String msgDataNumber = cursor.getString(cursor.getColumnIndex("address"));
                    long msgDatadate = Long.parseLong(cursor.getString(cursor.getColumnIndex("date")));
                    if (!sentMobNumber.contains(msgDataNumber) && currentTime <= msgDatadate && forgroundFinalRecp.contains(msgDataNumber)) {
                        sentMobNumber.add(msgDataNumber);
                        System.out.println("sent number:" + msgDataNumber + " time: " + msgDatadate);
                        isSMSSend=false;
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
                    if (!failMobNumber.contains(msgDataNumber) && currentTime <= msgDatadate && forgroundFinalRecp.contains(msgDataNumber)) {
                        failMobNumber.add(msgDataNumber);
                        System.out.println("failed number:" + msgDataNumber + " time: " + msgDatadate);
                        isSMSSend=false;
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

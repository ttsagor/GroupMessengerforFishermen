package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sagor on 3/21/2016.
 */
public class MyCustomAdapter extends BaseAdapter implements ListAdapter {

    private Context context;
    ArrayList<Integer> id = new  ArrayList<Integer>();
    public static boolean animationFlag = true;
    LayoutInflater inflater;
    ArrayList<FarmerInfoModel> allFarmerInfo = new ArrayList<FarmerInfoModel>();
    //change made

    public MyCustomAdapter(Context context,ArrayList<FarmerInfoModel> allFarmerInfo) {
        this.allFarmerInfo = allFarmerInfo;
        this.context = context;
    }
    private static class rowHolder{
        public TextView farmerName;
        public TextView fatherName;
        public TextView phoneNumber;
        public TextView unionName;
        public LinearLayout holderLayout;
        public ImageView phone_call;
        public ImageView user_sms;
    }

    @Override
    public int getCount() {
        return allFarmerInfo.size();
    }

    @Override
    public Object getItem(int pos) {
        return allFarmerInfo.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
        //just return 0 if your list items do not have an Id variable.
    }

    public static List<String> splitString(String msg, int lineSize) {
        List<String> res = new ArrayList<String>();

        Pattern p = Pattern.compile("\\b.{1," + (lineSize-1) + "}\\b\\W?");
        Matcher m = p.matcher(msg);

        while(m.find()) {
            //System.out.println(m.group().trim());   // Debug
            res.add(m.group());
        }
        return res;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        rowHolder holder = new rowHolder();
        if (view == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.farmer_list_iteam, null);

            holder.holderLayout  = (LinearLayout) view.findViewById(R.id.search_list_layout);
            holder.farmerName  = (TextView) view.findViewById(R.id.list_iteam_name);
            //holder.fatherName  = (TextView) view.findViewById(R.id.list_iteam_fathername);
            holder.phoneNumber  = (TextView) view.findViewById(R.id.list_iteam_phone);
            holder.unionName  = (TextView) view.findViewById(R.id.list_iteam_union);
            holder.holderLayout  = (LinearLayout) view.findViewById(R.id.search_list_layout);

            holder.phone_call  = (ImageView) view.findViewById(R.id.search_farmer_call);
            holder.user_sms  = (ImageView) view.findViewById(R.id.search_farmer_sms);

            view.setTag(holder);
        }
        else
        {
            holder = (rowHolder) view.getTag();
        }

        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(context.getAssets(), fontPath);
        holder.farmerName.setTypeface(tf);
        holder.phoneNumber.setTypeface(tf);
        holder.unionName.setTypeface(tf);

        holder.farmerName.setText(allFarmerInfo.get(position).getFarmerName());
        //holder.fatherName.setText(allFarmerInfo.get(position).getFarmerFatherHusbandName());
        holder.phoneNumber.setText(allFarmerInfo.get(position).getPhoneNumber());
        holder.unionName.setText(getUnionNameByID(allFarmerInfo.get(position).getUnionID()));

        holder.holderLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( context ,AddUpdateUser.class);
                i.putExtra("update","true");
                i.putExtra("farmerID",allFarmerInfo.get(position).getFarmerID());
                context.startActivity(i);
            }
        });

        holder.phone_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(
                        context, android.Manifest.permission.CALL_PHONE) !=
                        PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions((Activity) context, new
                            String[]{android.Manifest.permission.CALL_PHONE}, 0);
                } else {
                    context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + allFarmerInfo.get(position).getPhoneNumber())));
                }
//
//                try {
//                    Intent intent = new Intent(Intent.ACTION_CALL);
//                    intent.setData(Uri.parse("tel:" +allFarmerInfo.get(position).getPhoneNumber() ));
//                    context.startActivity(intent);
//                }
//                catch (Exception e)
//                {
//                    Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
//                }
//


            }
        });

        holder.user_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                    smsIntent.setType("vnd.android-dir/mms-sms");
                    smsIntent.putExtra("address", allFarmerInfo.get(position).getPhoneNumber());
                    context.startActivity(smsIntent);
                }
                catch (Exception e)
                {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });



        return view;
    }

    String getUnionNameByID(String unionID)
    {

        for(UnionModel u : MainActivity.allUnion)
        {

            if(u.getUnionID().contains(unionID) && (u.getUnionID().length()==unionID.length()))
            {
                return u.getUnionName();
            }
        }

        return "";
    }

}
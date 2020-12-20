package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bd.dof.groupmessenger.groupmessengerforfishermen.API.OurSmsClient;
import bd.dof.groupmessenger.groupmessengerforfishermen.API.OurSmsConnection;
import bd.dof.groupmessenger.groupmessengerforfishermen.Response.smsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static bd.dof.groupmessenger.groupmessengerforfishermen.ForegroundService.message;

/**
 * Created by sagor on 3/21/2016.
 */
public class MyCustomAdapter extends BaseAdapter implements ListAdapter {

    private Context context;
    ArrayList<Integer> id = new ArrayList<Integer>();
    public static boolean animationFlag = true;
    LayoutInflater inflater;
    OurSmsClient ourSmsClient;
    ArrayList<FarmerInfoModel> allFarmerInfo = new ArrayList<FarmerInfoModel>();
    //change made

    public MyCustomAdapter(Context context, ArrayList<FarmerInfoModel> allFarmerInfo) {
        this.allFarmerInfo = allFarmerInfo;
        this.context = context;
    }

    private static class rowHolder {
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

        Pattern p = Pattern.compile("\\b.{1," + (lineSize - 1) + "}\\b\\W?");
        Matcher m = p.matcher(msg);

        while (m.find()) {
            //System.out.println(m.group().trim());   // Debug
            res.add(m.group());
        }
        return res;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ourSmsClient = OurSmsConnection.cteateService(OurSmsClient.class);
        rowHolder holder = new rowHolder();
        if (view == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.farmer_list_iteam, null);

            holder.holderLayout = (LinearLayout) view.findViewById(R.id.search_list_layout);
            holder.farmerName = (TextView) view.findViewById(R.id.list_iteam_name);
            //holder.fatherName  = (TextView) view.findViewById(R.id.list_iteam_fathername);
            holder.phoneNumber = (TextView) view.findViewById(R.id.list_iteam_phone);
            holder.unionName = (TextView) view.findViewById(R.id.list_iteam_union);
            holder.holderLayout = (LinearLayout) view.findViewById(R.id.search_list_layout);

            holder.phone_call = (ImageView) view.findViewById(R.id.search_farmer_call);
            holder.user_sms = (ImageView) view.findViewById(R.id.search_farmer_sms);

            view.setTag(holder);
        } else {
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
                Intent i = new Intent(context, AddUpdateUser.class);
                i.putExtra("update", "true");
                i.putExtra("farmerID", allFarmerInfo.get(position).getFarmerID());
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


                    final AlertDialog defaultAlert = new AlertDialog.Builder(context).create();
                    LayoutInflater layoutInflater = LayoutInflater.from(context);
                    View promptView = layoutInflater.inflate(R.layout.message_alert, null);
                    final TextView next = promptView.findViewById(R.id.submit);
                    final ImageButton cancel = promptView.findViewById(R.id.cancel);
                    final EditText edt_message = promptView.findViewById(R.id.edt_message);
                    defaultAlert.setCanceledOnTouchOutside(false);
                    final HashMap<String, String> smsMap = new HashMap<String, String>();
                    smsMap.put("api_key", "16857887899815422020/12/1101:31:11pmReza & Reza Solution");
                    smsMap.put("sender_id", "867");
                    smsMap.put("user_email", "minhaz.shatil@gmail.com");
                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            defaultAlert.dismiss();
                        }
                    });
                    next.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (TextUtils.isEmpty(edt_message.getText().toString())) {

                                Toast.makeText(context, "অনুগ্রহ করে ম্যাসেজ লিখুন", Toast.LENGTH_SHORT).show();
                            } else {

                                smsMap.put("message", edt_message.getText().toString());
                                smsMap.put("mobile_no", allFarmerInfo.get(position).getPhoneNumber());
                                ourSmsClient.sendSmsToFarmers(smsMap).enqueue(new Callback<smsResponse>() {
                                    @Override
                                    public void onResponse(Call<smsResponse> call, Response<smsResponse> response) {
                                        if (response.body().getMessage().equals("Successfull")) {
                                            Toast.makeText(context, "Message Send", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(context, "Please try Again !", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<smsResponse> call, Throwable t) {

                                    }
                                });
                            }
                        }
                    });
                    Objects.requireNonNull(defaultAlert.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                    defaultAlert.setCancelable(false);
                    defaultAlert.setView(promptView);
                    defaultAlert.show();
                }
//                    Intent smsIntent = new Intent(Intent.ACTION_VIEW);
//                    smsIntent.setType("vnd.android-dir/mms-sms");
//                    smsIntent.putExtra("address", allFarmerInfo.get(position).getPhoneNumber());
//                    context.startActivity(smsIntent);

                catch (Exception e) {
                    Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });


        return view;
    }

    String getUnionNameByID(String unionID) {

        for (UnionModel u : MainActivity.allUnion) {

            if (u.getUnionID().contains(unionID) && (u.getUnionID().length() == unionID.length())) {
                return u.getUnionName();
            }
        }

        return "";
    }

}
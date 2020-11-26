package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;

import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.BaboharbidiActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.ComingSoonActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.EditUserActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.LoginActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.ProfileActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.SotorkotaActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.SplashScreenActivity;

public class groupsmsfilter extends AppCompatActivity {

    boolean allAreaCheckFlag = false;
    boolean unionWiseCheckFlag = false;
    boolean fishingCatCheckFlag = false;
    boolean operatorWiseFlag = false;
    List<String> checkedUnion = new ArrayList<String>();
    List<String> checkedCatagory = new ArrayList<String>();
    List<String> checkedOperator = new ArrayList<String>();
    List<FarmerInfoModel> recipient = new ArrayList<>();
    public static List<FarmerInfoModel> finalRecipient = new ArrayList<>();

    List<String> allUnion = new ArrayList<String>();
    List<String> allCatagory = new ArrayList<String>();
    List<String> allOperator = new ArrayList<String>();

    RadioButton allarea;
    RadioButton areawise;
    RadioButton catwise;
    RadioButton operatorwise;

    ImageButton filtereset;
    TextView areaTotalcounTxt;
    TextView catagoryTotalcountTxt;
    TextView operatorTotalcountTxt;
    TextView sumTotalcountTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.smsreceiverfilter);
        bottomNavigationHandler();
        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);

        TextView smsMainText = (TextView) findViewById(R.id.smsMainText);
        TextView smsMainAllFarmerText = (TextView) findViewById(R.id.smsMainAllFarmerText);
        TextView smsMainAreaWiseText = (TextView) findViewById(R.id.smsMainAreaWiseText);
        TextView smsMainFarmingWiseText = (TextView) findViewById(R.id.smsMainFarmingWiseText);
        TextView smsMainOperatorWiseText = (TextView) findViewById(R.id.smsMainOperatorWiseText);
        TextView smsMainAreaInforamtionText = (TextView) findViewById(R.id.smsMainAreaInforamtionText);
        TextView smsMainFarmingCategoryText = (TextView) findViewById(R.id.smsMainFarmingCategoryText);
        TextView smsMainOperatorListText = (TextView) findViewById(R.id.smsMainOperatorListText);
        TextView smsMainTotalFarmerText = (TextView) findViewById(R.id.smsMainTotalFarmerText);

        areaTotalcounTxt = (TextView) findViewById(R.id.areaTotalcounTxt);
        catagoryTotalcountTxt = (TextView) findViewById(R.id.catagoryTotalcountTxt);
        operatorTotalcountTxt = (TextView) findViewById(R.id.operatorTotalcountTxt);
        sumTotalcountTxt = (TextView) findViewById(R.id.sumTotalcountTxt);


        areaTotalcounTxt.setTypeface(tf);
        catagoryTotalcountTxt.setTypeface(tf);
        operatorTotalcountTxt.setTypeface(tf);
        sumTotalcountTxt.setTypeface(tf);
        final DbHandler db = new DbHandler(this, null, null, 1);

        List<UnionModel> allUnionM = db.getUnionList(MainActivity.divisionID, MainActivity.districtID, MainActivity.upazillaID);
        allCatagory = db.getFarmingCategoryByArea(MainActivity.divisionID, MainActivity.districtID, MainActivity.upazillaID, "%", "%", "%");
        allOperator.add("গ্রামীন ফোন");
        allOperator.add("রবি");
        allOperator.add("টেলিটক");
        allOperator.add("এয়ারটেল");
        allOperator.add("বাংলালিংক");
        allOperator.add("সিটিসেল");

        for (int i = 0; i < allUnionM.size(); i++) {
            allUnion.add(allUnionM.get(i).getUnionName());
        }


        final LinearLayout allfarmerrdo = (LinearLayout) findViewById(R.id.allfarmerrdo);
        final LinearLayout areawiserdo = (LinearLayout) findViewById(R.id.areawiserdo);
        final LinearLayout catwiserdo = (LinearLayout) findViewById(R.id.catwiserdo);
        final LinearLayout operatorwiserdo = (LinearLayout) findViewById(R.id.operatorwiserdo);
        final LinearLayout countShowlayout = (LinearLayout) findViewById(R.id.countShowlayout);

        filtereset = (ImageButton) findViewById(R.id.filtereset);
        Button buttonsend = (Button) findViewById(R.id.buttonsend);
        Button buttontemplate = findViewById(R.id.buttontemplate);
        buttonsend.setTypeface(tf);


        filtereset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetAllFilter();
            }
        });

        buttontemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!allAreaCheckFlag && !unionWiseCheckFlag && !fishingCatCheckFlag && !operatorWiseFlag) {
                    Toast.makeText(getApplicationContext(), "অনুগ্রহক পূর্বক বার্তা প্রাপক নির্বাচন করুন", Toast.LENGTH_LONG).show();
                } else {
                    List<String> tempCheckedUnion = checkedUnion;
                    List<String> tempCheckedCatagory = checkedCatagory;
                    List<String> tempCheckedOperator = checkedOperator;

                    if (checkedUnion.size() == 0) {
                        checkedUnion = allUnion;
                    }
                    if (checkedCatagory.size() == 0) {
                        checkedCatagory = allCatagory;
                    }
                    if (checkedOperator.size() == 0) {
                        checkedOperator = allOperator;
                    }
                    List<FarmerInfoModel> Recipients = db.getFarmerInfoByAreaFarmingCategoryMobileOperator(MainActivity.divisionID, MainActivity.districtID, MainActivity.upazillaID, unionIDMask(checkedUnion), "%", "%", checkedCatagory, phoneNumberMask(checkedOperator));
                    if (Recipients.size() > 0) {
                        finalRecipient = Recipients;
                        Intent i = new Intent(groupsmsfilter.this, GroupSmsTemplateSelection.class);
                        startActivity(i);
                    } else {
                        checkedUnion = tempCheckedUnion;
                        checkedCatagory = tempCheckedCatagory;
                        checkedOperator = tempCheckedOperator;
                        Toast.makeText(getApplicationContext(), "অনুগ্রহক পূর্বক বার্তা প্রাপক নির্বাচন করুন", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        buttonsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!allAreaCheckFlag && !unionWiseCheckFlag && !fishingCatCheckFlag && !operatorWiseFlag) {
                    Toast.makeText(getApplicationContext(), "অনুগ্রহক পূর্বক বার্তা প্রাপক নির্বাচন করুন", Toast.LENGTH_LONG).show();
                } else {
                    List<String> tempCheckedUnion = checkedUnion;
                    List<String> tempCheckedCatagory = checkedCatagory;
                    List<String> tempCheckedOperator = checkedOperator;

                    if (checkedUnion.size() == 0) {
                        checkedUnion = allUnion;
                    }
                    if (checkedCatagory.size() == 0) {
                        checkedCatagory = allCatagory;
                    }
                    if (checkedOperator.size() == 0) {
                        checkedOperator = allOperator;
                    }
                    List<FarmerInfoModel> Recipients = db.getFarmerInfoByAreaFarmingCategoryMobileOperator(MainActivity.divisionID, MainActivity.districtID, MainActivity.upazillaID, unionIDMask(checkedUnion), "%", "%", checkedCatagory, phoneNumberMask(checkedOperator));
                    if (Recipients.size() > 0) {
                        finalRecipient = Recipients;
                        Intent i = new Intent(groupsmsfilter.this, GroupSmsNewMessage.class);
                        startActivity(i);
                    } else {
                        checkedUnion = tempCheckedUnion;
                        checkedCatagory = tempCheckedCatagory;
                        checkedOperator = tempCheckedOperator;
                        Toast.makeText(getApplicationContext(), "অনুগ্রহক পূর্বক বার্তা প্রাপক নির্বাচন করুন", Toast.LENGTH_LONG).show();

                    }
                }
            }
        });


        allarea = (RadioButton) findViewById(R.id.allarea);
        areawise = (RadioButton) findViewById(R.id.areawise);
        catwise = (RadioButton) findViewById(R.id.catwise);
        operatorwise = (RadioButton) findViewById(R.id.operatorwise);


        allarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!allAreaCheckFlag) {
                    allAreaCheckFlag = true;
                    allarea.setChecked(true);
                    countRecipients(allUnion, allCatagory, allOperator, "all");
                    checkedUnion = new ArrayList<String>();
                    checkedCatagory = new ArrayList<String>();
                    checkedOperator = new ArrayList<String>();

                    unionWiseCheckFlag = false;
                    fishingCatCheckFlag = false;
                    operatorWiseFlag = false;

                    areawise.setChecked(false);
                    catwise.setChecked(false);
                    operatorwise.setChecked(false);

                    areawise.setEnabled(false);
                    catwise.setEnabled(false);
                    operatorwise.setEnabled(false);
                } else {
                    checkedUnion = new ArrayList<String>();
                    checkedCatagory = new ArrayList<String>();
                    checkedOperator = new ArrayList<String>();
                    allAreaCheckFlag = false;
                    allarea.setChecked(false);

                    areawise.setEnabled(true);
                    catwise.setEnabled(true);
                    operatorwise.setEnabled(true);

                    resetAllFilter();
                }
            }
        });

        areawise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!unionWiseCheckFlag) {
                    unionWiseCheckFlag = true;
                    areawise.setChecked(true);
                    showRadioButtonDialogUnion();
                    if (checkedCatagory.size() == 0 && checkedOperator.size() == 0) {
                        countRecipients(allUnion, allCatagory, allOperator, "union");
                    } else if (checkedCatagory.size() == 0) {
                        countRecipients(allUnion, allCatagory, checkedOperator, "union");
                    } else if (checkedOperator.size() == 0) {
                        countRecipients(allUnion, checkedCatagory, allOperator, "union");
                    } else {
                        countRecipients(allUnion, checkedCatagory, checkedOperator, "union");
                    }
                    setCountTextLabel();
                } else {
                    checkedUnion = new ArrayList<String>();
                    unionWiseCheckFlag = false;
                    areawise.setChecked(false);

                    areaTotalcounTxt.setText(": 0");
                    if (checkedCatagory.size() == 0 && checkedOperator.size() == 0) {
                        countRecipients(allUnion, allCatagory, allOperator, "union");
                    } else if (checkedCatagory.size() == 0) {
                        countRecipients(allUnion, allCatagory, checkedOperator, "union");
                    } else if (checkedOperator.size() == 0) {
                        countRecipients(allUnion, checkedCatagory, allOperator, "union");
                    } else {
                        countRecipients(allUnion, checkedCatagory, checkedOperator, "union");
                    }
                    setCountTextLabel();
                }

                if (!allAreaCheckFlag && !unionWiseCheckFlag && !fishingCatCheckFlag && !operatorWiseFlag) {
                    resetAllFilter();
                }
            }
        });

        catwise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!fishingCatCheckFlag) {
                    fishingCatCheckFlag = true;
                    catwise.setChecked(true);
                    showRadioButtonDialogFarmingCat();

                    if (checkedUnion.size() == 0 && checkedOperator.size() == 0) {
                        countRecipients(allUnion, allCatagory, allOperator, "catagory");
                    } else if (checkedUnion.size() == 0) {
                        countRecipients(allUnion, allCatagory, checkedOperator, "catagory");
                    } else if (checkedOperator.size() == 0) {
                        countRecipients(checkedUnion, allCatagory, allOperator, "catagory");
                    } else {
                        countRecipients(checkedUnion, allCatagory, checkedOperator, "catagory");
                    }
                    setCountTextLabel();
                } else {
                    checkedCatagory = new ArrayList<String>();
                    fishingCatCheckFlag = false;
                    catwise.setChecked(false);
                    TextView catagoryTotalcountTxt = (TextView) findViewById(R.id.catagoryTotalcountTxt);
                    catagoryTotalcountTxt.setText(": 0");
                    if (checkedUnion.size() == 0 && checkedOperator.size() == 0) {
                        countRecipients(allUnion, allCatagory, allOperator, "catagory");
                    } else if (checkedUnion.size() == 0) {
                        countRecipients(allUnion, allCatagory, checkedOperator, "catagory");
                    } else if (checkedOperator.size() == 0) {
                        countRecipients(checkedUnion, allCatagory, allOperator, "catagory");
                    } else {
                        countRecipients(checkedUnion, allCatagory, checkedOperator, "catagory");
                    }
                    setCountTextLabel();
                }

                if (!allAreaCheckFlag && !unionWiseCheckFlag && !fishingCatCheckFlag && !operatorWiseFlag) {
                    resetAllFilter();
                }
            }
        });

        operatorwise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!operatorWiseFlag) {
                    operatorWiseFlag = true;
                    operatorwise.setChecked(true);
                    showRadioButtonDialogOperator();

                    if (checkedUnion.size() == 0 && checkedCatagory.size() == 0) {
                        countRecipients(allUnion, allCatagory, allOperator, "operator");
                    } else if (checkedUnion.size() == 0) {
                        countRecipients(allUnion, checkedCatagory, allOperator, "operator");
                    } else if (checkedCatagory.size() == 0) {
                        countRecipients(checkedUnion, allCatagory, allOperator, "operator");
                    } else {
                        countRecipients(checkedUnion, checkedCatagory, allOperator, "operator");
                    }
                    setCountTextLabel();
                } else {
                    checkedOperator = new ArrayList<String>();
                    operatorWiseFlag = false;
                    operatorwise.setChecked(false);
                    TextView operatorTotalcountTxt = (TextView) findViewById(R.id.operatorTotalcountTxt);
                    operatorTotalcountTxt.setText(": 0");
                    if (checkedUnion.size() == 0 && checkedCatagory.size() == 0) {
                        countRecipients(allUnion, allCatagory, allOperator, "operator");
                    } else if (checkedUnion.size() == 0) {
                        countRecipients(allUnion, checkedCatagory, allOperator, "operator");
                    } else if (checkedCatagory.size() == 0) {
                        countRecipients(checkedUnion, allCatagory, allOperator, "operator");
                    } else {
                        countRecipients(checkedUnion, checkedCatagory, allOperator, "operator");
                    }
                    setCountTextLabel();
                }

                if (!allAreaCheckFlag && !unionWiseCheckFlag && !fishingCatCheckFlag && !operatorWiseFlag) {
                    resetAllFilter();
                }
            }
        });

        allfarmerrdo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!allAreaCheckFlag) {
                    allarea.setChecked(true);
                    allAreaCheckFlag = true;
                    checkedUnion = new ArrayList<String>();
                    checkedCatagory = new ArrayList<String>();
                    checkedOperator = new ArrayList<String>();
                    countRecipients(allUnion, allCatagory, allOperator, "all");

                    areawise.setChecked(false);
                    catwise.setChecked(false);
                    operatorwise.setChecked(false);

                    unionWiseCheckFlag = false;
                    fishingCatCheckFlag = false;
                    operatorWiseFlag = false;

                    areawise.setEnabled(false);
                    catwise.setEnabled(false);
                    operatorwise.setEnabled(false);
                } else {
                    checkedUnion = new ArrayList<String>();
                    checkedCatagory = new ArrayList<String>();
                    checkedOperator = new ArrayList<String>();
                    areawise.setEnabled(true);
                    catwise.setEnabled(true);
                    operatorwise.setEnabled(true);

                    allAreaCheckFlag = false;
                    allarea.setChecked(false);
                    resetAllFilter();
                }
            }
        });
        areawiserdo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!allAreaCheckFlag) {
                    if (!unionWiseCheckFlag) {// countRecipients(checkedUnion,allCatagory,allOperator,"union");
                        //allarea.setChecked(false);
                        unionWiseCheckFlag = true;
                        areawise.setChecked(true);
                        showRadioButtonDialogUnion();
                        setCountTextLabel();
                    } else {
                        checkedUnion = new ArrayList<String>();
                        unionWiseCheckFlag = false;
                        areawise.setChecked(false);
                        //TextView areaTotalcounTxt = (TextView) findViewById(R.id.areaTotalcounTxt);
                        areaTotalcounTxt.setText(": 0");

                        if (checkedCatagory.size() == 0 && checkedOperator.size() == 0) {
                            countRecipients(allUnion, allCatagory, allOperator, "union");
                        } else if (checkedCatagory.size() == 0) {
                            countRecipients(allUnion, allCatagory, checkedOperator, "union");
                        } else if (checkedOperator.size() == 0) {
                            countRecipients(allUnion, checkedCatagory, allOperator, "union");
                        } else {
                            countRecipients(allUnion, checkedCatagory, checkedOperator, "union");
                        }
                        setCountTextLabel();
                    }
                }

                if (!allAreaCheckFlag && !unionWiseCheckFlag && !fishingCatCheckFlag && !operatorWiseFlag) {
                    resetAllFilter();
                }
            }
        });

        catwiserdo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!allAreaCheckFlag) {
                    if (!fishingCatCheckFlag) {
                        fishingCatCheckFlag = true;
                        catwise.setChecked(true);
                        showRadioButtonDialogFarmingCat();
                        setCountTextLabel();
                    } else {
                        checkedCatagory = new ArrayList<String>();
                        fishingCatCheckFlag = false;
                        catwise.setChecked(false);
                        TextView catagoryTotalcountTxt = (TextView) findViewById(R.id.catagoryTotalcountTxt);
                        catagoryTotalcountTxt.setText(": 0");
                        if (checkedUnion.size() == 0 && checkedOperator.size() == 0) {
                            countRecipients(allUnion, allCatagory, allOperator, "catagory");
                        } else if (checkedUnion.size() == 0) {
                            countRecipients(allUnion, allCatagory, checkedOperator, "catagory");
                        } else if (checkedOperator.size() == 0) {
                            countRecipients(checkedUnion, allCatagory, allOperator, "catagory");
                        } else {
                            countRecipients(checkedUnion, allCatagory, checkedOperator, "catagory");
                        }
                        setCountTextLabel();
                    }
                }

                if (!allAreaCheckFlag && !unionWiseCheckFlag && !fishingCatCheckFlag && !operatorWiseFlag) {
                    resetAllFilter();
                }
            }
        });

        operatorwiserdo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!allAreaCheckFlag) {
                    if (!operatorWiseFlag) {
                        operatorWiseFlag = true;
                        operatorwise.setChecked(true);
                        showRadioButtonDialogOperator();
                        setCountTextLabel();

                    } else {
                        checkedOperator = new ArrayList<String>();
                        operatorWiseFlag = false;
                        operatorwise.setChecked(false);
                        TextView operatorTotalcountTxt = (TextView) findViewById(R.id.operatorTotalcountTxt);
                        operatorTotalcountTxt.setText(": 0");
                        if (checkedUnion.size() == 0 && checkedCatagory.size() == 0) {
                            countRecipients(allUnion, allCatagory, allOperator, "operator");
                        } else if (checkedUnion.size() == 0) {
                            countRecipients(allUnion, checkedCatagory, allOperator, "operator");
                        } else if (checkedCatagory.size() == 0) {
                            countRecipients(checkedUnion, allCatagory, allOperator, "operator");
                        } else {
                            countRecipients(checkedUnion, checkedCatagory, allOperator, "operator");
                        }
                        setCountTextLabel();
                    }
                }

                if (!allAreaCheckFlag && !unionWiseCheckFlag && !fishingCatCheckFlag && !operatorWiseFlag) {
                    resetAllFilter();
                }
            }
        });
    }

    private void showRadioButtonDialogUnion() {
        // custom dialog
        DbHandler db = new DbHandler(this, null, null, 1);
        List<UnionModel> unionList = db.getUnionList(MainActivity.divisionID, MainActivity.districtID, MainActivity.upazillaID);
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.radiobutton_dialog);
        LinearLayout rg = (LinearLayout) dialog.findViewById(R.id.groupDynamicChckHolder);

        LinearLayout holder = (LinearLayout) dialog.findViewById(R.id.groupDynamicCButtonHolder);


        Button btnCancel = new Button(this);
        btnCancel.setText("ওকে");
        btnCancel.setLayoutParams(new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        holder.addView(btnCancel);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkedUnion.size() == 0) {
                    unionWiseCheckFlag = false;
                    areawise.setChecked(false);

                    if (!allAreaCheckFlag && !unionWiseCheckFlag && !fishingCatCheckFlag && !operatorWiseFlag) {
                        resetAllFilter();
                    }
                }
                dialog.dismiss();
            }
        });

        for (int i = 0; i < unionList.size(); i++) {
            CheckBox rb = new CheckBox(this); // dynamically creating RadioButton and adding to RadioGroup.
            final String unionName = unionList.get(i).getUnionName();
            rb.setText(unionList.get(i).getUnionName());
            if (checkedUnion.contains(unionName)) {
                rb.setChecked(true);
            }

            rg.addView(rb);

            final CheckBox rbFinal = rb;
            rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (rbFinal.isChecked() && !checkedUnion.contains(unionName)) {
                        checkedUnion.add(unionName);
                    } else if (!rbFinal.isChecked() && checkedUnion.contains(unionName)) {
                        checkedUnion.remove(unionName);
                    }

                    if (checkedCatagory.size() == 0 && checkedOperator.size() == 0) {
                        countRecipients(checkedUnion, allCatagory, allOperator, "union");
                    } else if (checkedCatagory.size() == 0) {
                        countRecipients(checkedUnion, allCatagory, checkedOperator, "union");
                    } else if (checkedOperator.size() == 0) {
                        countRecipients(checkedUnion, checkedCatagory, allOperator, "union");
                    } else {
                        countRecipients(checkedUnion, checkedCatagory, checkedOperator, "union");
                    }
                }
            });
        }

        dialog.show();
    }

    private void showRadioButtonDialogFarmingCat() {
        // custom dialog
        DbHandler db = new DbHandler(this, null, null, 1);
        List<String> farmingCat = db.getFarmingCategoryByArea(MainActivity.divisionID, MainActivity.districtID, MainActivity.upazillaID, "%", "%", "%");
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.radiobutton_dialog);
        LinearLayout rg = (LinearLayout) dialog.findViewById(R.id.groupDynamicChckHolder);

        LinearLayout holder = (LinearLayout) dialog.findViewById(R.id.groupDynamicCButtonHolder);

        Button btnok = new Button(this);
        btnok.setText("ওকে");
        btnok.setLayoutParams(new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));


        Button btnCancel = new Button(this);
        btnCancel.setText("ওকে");
        btnCancel.setLayoutParams(new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));


        holder.addView(btnCancel);

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkedCatagory.size() == 0) {
                    fishingCatCheckFlag = false;
                    catwise.setChecked(false);

                    if (!allAreaCheckFlag && !unionWiseCheckFlag && !fishingCatCheckFlag && !operatorWiseFlag) {
                        resetAllFilter();
                    }
                }
                dialog.dismiss();
            }
        });

        for (int i = 0; i < farmingCat.size(); i++) {
            CheckBox rb = new CheckBox(this);
            final String cataName = farmingCat.get(i);
            rb.setText(cataName);

            if (checkedCatagory.contains(cataName)) {
                rb.setChecked(true);
            }

            rg.addView(rb);
            final CheckBox rbFinal = rb;

            rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (rbFinal.isChecked() && !checkedCatagory.contains(cataName)) {
                        checkedCatagory.add(cataName);
                    } else if (!rbFinal.isChecked() && checkedCatagory.contains(cataName)) {
                        checkedCatagory.remove(cataName);
                    }

                    if (checkedUnion.size() == 0 && checkedOperator.size() == 0) {
                        countRecipients(allUnion, checkedCatagory, allOperator, "catagory");
                    } else if (checkedUnion.size() == 0) {
                        countRecipients(allUnion, checkedCatagory, checkedOperator, "catagory");
                    } else if (checkedOperator.size() == 0) {
                        countRecipients(checkedUnion, checkedCatagory, allOperator, "catagory");
                    } else {
                        countRecipients(checkedUnion, checkedCatagory, checkedOperator, "catagory");
                    }
                }
            });

        }

        dialog.show();
    }

    private void showRadioButtonDialogOperator() {
        // custom dialog
        DbHandler db = new DbHandler(this, null, null, 1);
        List<String> operatorList = new ArrayList<String>();

        operatorList.add("গ্রামীন ফোন");
        operatorList.add("রবি");
        operatorList.add("টেলিটক");
        operatorList.add("এয়ারটেল");
        operatorList.add("বাংলালিংক");
        operatorList.add("সিটিসেল");

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.radiobutton_dialog);
        dialog.setCancelable(false);
        LinearLayout rg = (LinearLayout) dialog.findViewById(R.id.groupDynamicChckHolder);
        LinearLayout holder = (LinearLayout) dialog.findViewById(R.id.groupDynamicCButtonHolder);

        Button btnok = new Button(this);
        btnok.setText("ওকে");
        btnok.setLayoutParams(new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));


        Button btnCancel = new Button(this);
        btnCancel.setText("ওকে");
        btnCancel.setLayoutParams(new LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));


        holder.addView(btnCancel);

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkedOperator.size() == 0) {
                    operatorWiseFlag = false;
                    operatorwise.setChecked(false);

                    if (!allAreaCheckFlag && !unionWiseCheckFlag && !fishingCatCheckFlag && !operatorWiseFlag) {
                        resetAllFilter();
                    }
                }

                dialog.dismiss();
            }
        });
        for (int i = 0; i < operatorList.size(); i++) {
            CheckBox rb = new CheckBox(this);
            final String operatorName = operatorList.get(i);
            rb.setText(operatorName);
            if (checkedOperator.contains(operatorName)) {
                rb.setChecked(true);
            }
            rg.addView(rb);
            final CheckBox rbFinal = rb;

            rb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (rbFinal.isChecked() && !checkedOperator.contains(operatorName)) {
                        checkedOperator.add(operatorName);
                    } else if (!rbFinal.isChecked() && checkedOperator.contains(operatorName)) {
                        checkedOperator.remove(operatorName);
                    }

                    if (checkedUnion.size() == 0 && checkedCatagory.size() == 0) {
                        countRecipients(allUnion, allCatagory, checkedOperator, "operator");
                    } else if (checkedUnion.size() == 0) {
                        countRecipients(allUnion, checkedCatagory, checkedOperator, "operator");
                    } else if (checkedCatagory.size() == 0) {
                        countRecipients(checkedUnion, allCatagory, checkedOperator, "operator");
                    } else {
                        countRecipients(checkedUnion, checkedCatagory, checkedOperator, "operator");
                    }
                }
            });
        }

        dialog.show();
    }

    private void countRecipients(List<String> union, List<String> catagory, List<String> operator, String para) {
        DbHandler db = new DbHandler(this, null, null, 1);
        List<FarmerInfoModel> Recipients = db.getFarmerInfoByAreaFarmingCategoryMobileOperator(MainActivity.divisionID, MainActivity.districtID, MainActivity.upazillaID, unionIDMask(union), "%", "%", catagory, phoneNumberMask(operator));

        if (para == "all") {

            //TextView areaTotalcounTxt = (TextView) findViewById(R.id.areaTotalcounTxt);
            areaTotalcounTxt.setText(": " + pona_mojud.engToBng(String.valueOf(allUnion.size())));

            TextView catagoryTotalcountTxt = (TextView) findViewById(R.id.catagoryTotalcountTxt);
            catagoryTotalcountTxt.setText(": " + pona_mojud.engToBng(String.valueOf(allCatagory.size())));

            TextView operatorTotalcountTxt = (TextView) findViewById(R.id.operatorTotalcountTxt);
            operatorTotalcountTxt.setText(": " + pona_mojud.engToBng(String.valueOf(allOperator.size())));
        }
        if (para == "union" && union.size() > 0) {
            //TextView areaTotalcounTxt = (TextView) findViewById(R.id.areaTotalcounTxt);
            areaTotalcounTxt.setText(": " + pona_mojud.engToBng(String.valueOf(union.size())));

        } else if (para == "union" && union.size() == 0) {

            checkedUnion = new ArrayList<String>();
            allAreaCheckFlag = false;
            allarea.setChecked(false);
        }


        if (para == "catagory" && catagory.size() > 0) {
            TextView catagoryTotalcountTxt = (TextView) findViewById(R.id.catagoryTotalcountTxt);
            catagoryTotalcountTxt.setText(": " + pona_mojud.engToBng(String.valueOf(catagory.size())));

        } else if (para == "catagory" && catagory.size() == 0) {
            checkedCatagory = new ArrayList<String>();
            fishingCatCheckFlag = false;
            catwise.setChecked(false);
        }


        if (para == "operator" && operator.size() > 0) {
            TextView operatorTotalcountTxt = (TextView) findViewById(R.id.operatorTotalcountTxt);
            operatorTotalcountTxt.setText(": " + pona_mojud.engToBng(String.valueOf(operator.size())));

        } else if (para == "operator" && operator.size() == 0) {
            checkedOperator = new ArrayList<String>();
            operatorWiseFlag = false;
            operatorwise.setChecked(false);
        }
        TextView sumTotalcountTxt = (TextView) findViewById(R.id.sumTotalcountTxt);
        sumTotalcountTxt.setText(": " + pona_mojud.engToBng(String.valueOf(Recipients.size())));
        recipient = Recipients;
        System.out.println(Recipients.size());
    }

    private List<String> phoneNumberMask(List<String> opList) {
        List<String> phoneNumber = new ArrayList<String>();
        for (int i = 0; i < opList.size(); i++) {
            if (opList.get(i) == "গ্রামীন ফোন") {
                phoneNumber.add("017");
            } else if (opList.get(i) == "রবি") {
                phoneNumber.add("018");
            } else if (opList.get(i) == "টেলিটক") {
                phoneNumber.add("015");
            } else if (opList.get(i) == "এয়ারটেল") {
                phoneNumber.add("016");
            } else if (opList.get(i) == "বাংলালিংক") {
                phoneNumber.add("019");
            } else if (opList.get(i) == "সিটিসেল") {
                phoneNumber.add("011");
            }
        }
        return phoneNumber;
    }

    private List<String> unionIDMask(List<String> unionBng) {
        DbHandler db = new DbHandler(this, null, null, 1);
        List<String> unionID = new ArrayList<String>();
        for (int i = 0; i < unionBng.size(); i++) {
            UnionModel union = db.getUnionIDByName(MainActivity.divisionID, MainActivity.districtID, MainActivity.upazillaID, unionBng.get(i));
            unionID.add(union.getUnionID());
        }
        return unionID;
    }

    private void resetAllFilter() {
        checkedUnion = new ArrayList<String>();
        checkedCatagory = new ArrayList<String>();
        checkedOperator = new ArrayList<String>();

        operatorWiseFlag = false;
        fishingCatCheckFlag = false;
        unionWiseCheckFlag = false;
        allAreaCheckFlag = false;

        allarea.setEnabled(true);
        areawise.setEnabled(true);
        catwise.setEnabled(true);
        operatorwise.setEnabled(true);

        allarea.setChecked(false);
        areawise.setChecked(false);
        catwise.setChecked(false);
        operatorwise.setChecked(false);

        //TextView areaTotalcounTxt = (TextView) findViewById(R.id.areaTotalcounTxt);
        areaTotalcounTxt.setText(": 0");

        TextView catagoryTotalcountTxt = (TextView) findViewById(R.id.catagoryTotalcountTxt);
        catagoryTotalcountTxt.setText(": 0");

        TextView operatorTotalcountTxt = (TextView) findViewById(R.id.operatorTotalcountTxt);
        operatorTotalcountTxt.setText(": 0");

        TextView sumTotalcountTxt = (TextView) findViewById(R.id.sumTotalcountTxt);
        sumTotalcountTxt.setText(": 0");
    }


    private void setCountTextLabel() {
        //TextView areaTotalcounTxt = (TextView) findViewById(R.id.areaTotalcounTxt);
        if (checkedUnion.size() > 0) {
            areaTotalcounTxt.setText(": " + pona_mojud.engToBng(String.valueOf(checkedUnion.size())));
        } else {
            areaTotalcounTxt.setText(": " + pona_mojud.engToBng(String.valueOf(allUnion.size())));
        }

        TextView catagoryTotalcountTxt = (TextView) findViewById(R.id.catagoryTotalcountTxt);
        if (checkedCatagory.size() > 0) {
            catagoryTotalcountTxt.setText(": " + pona_mojud.engToBng(String.valueOf(checkedCatagory.size())));
        } else {
            catagoryTotalcountTxt.setText(": " + pona_mojud.engToBng(String.valueOf(allCatagory.size())));
        }

        TextView operatorTotalcountTxt = (TextView) findViewById(R.id.operatorTotalcountTxt);
        if (checkedOperator.size() > 0) {
            operatorTotalcountTxt.setText(": " + pona_mojud.engToBng(String.valueOf(checkedOperator.size())));
        } else {
            operatorTotalcountTxt.setText(": " + pona_mojud.engToBng(String.valueOf(allOperator.size())));
        }
    }

    private void bottomNavigationHandler() {

        SharedPreferences pref = this.getApplicationContext().getSharedPreferences("MyPref", 0);
        final String log = pref.getString("log", "");
        final SharedPreferences.Editor editor = pref.edit();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.getMenu().clear();
        bottomNavigationView.inflateMenu(R.menu.new_bottom_menu_home);
        if (log.equals("true")) {

            bottomNavigationView.getMenu().removeItem(R.id.menu_sotorkota);

            bottomNavigationView.getMenu().getItem(3).setTitle("লগআউট");
        } else {
            bottomNavigationView.getMenu().removeItem(R.id.menu_profile);


        }
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.menu_login:
                        if (log.equals("true")) {
                            editor.putString("log", "false");
                            editor.apply();
                            startActivity(new Intent(groupsmsfilter.this, SplashScreenActivity.class));

                        } else {
                            startActivity(new Intent(groupsmsfilter.this, LoginActivity.class));
                        }
                        break;

                    case R.id.menu_sotorkota:
                        startActivity(new Intent(groupsmsfilter.this, SotorkotaActivity.class));
                        break;
                    case R.id.menu_profile:
                        startActivity(new Intent(groupsmsfilter.this, ProfileActivity.class));
                        break;
                    case R.id.menu_beboharbidi:
                        startActivity(new Intent(groupsmsfilter.this, BaboharbidiActivity.class));
                        break;

                    case R.id.menu_somoshajomadin:
                        startActivity(new Intent(groupsmsfilter.this, ComingSoonActivity.class));
                        break;
                }
                return false;
            }
        });
    }
}

package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.HomeScreenActivity;

public class PhoneNumberValidation extends AppCompatActivity {
DbHandler db;
  Context context;
    SystemInformationModel cSystemInfo;
SystemInformationModel nSystemInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number_validation);
        db = new DbHandler(this,null,null,1);
        cSystemInfo = db.getSystemInfo();
        String phonenumber;
        context = this;
         nSystemInfo = new SystemInformationModel();

        System.out.println(cSystemInfo.getUserID()+" "+cSystemInfo.getDataLoaded()+" "+cSystemInfo.getUserName());
        checkSystem();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do some thing after 100ms
                startActivity(new Intent(PhoneNumberValidation.this, HomeScreenActivity.class));
            }
        }, 5000);




    }

    private void checkSystem() {



        if (cSystemInfo.getDataLoaded()==1)
        {
            Intent intent = new Intent(PhoneNumberValidation.this,
                    HomeScreenActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
            PhoneNumberValidation.this.finish();
        }
        else
        {
            loadData(db, context);
            final EditText userphonenumber = (EditText) findViewById(R.id.userphonenumber);
            final Button phonenumberconfirm = (Button) findViewById(R.id.phonenumberconfirm);
            final Context mContext = this;
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 1);
            userphonenumber.requestFocus();
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);


        }



                    nSystemInfo.setUserID(1);
                    nSystemInfo.setUserName("Admin");
                    nSystemInfo.setUserPhoneNumber("111111111111");
                    nSystemInfo.setUserDivisionID(MainActivity.divisionID);
                    nSystemInfo.setUserDistrictID(MainActivity.districtID);
                    nSystemInfo.setUserUpazillaID(MainActivity.upazillaID);
                    nSystemInfo.setDataLoaded(1);
                    try {
                        db.insertData(nSystemInfo);
                    }catch (Exception e)
                    {
                        System.out.println(e.toString());
                    }








    }

    void loadData(DbHandler db, Context context) {
        ArrayList<String> divisions = loadAssetTextAsString(context, "division.csv");
        ArrayList<String> districts = loadAssetTextAsString(context, "district.csv");
        ArrayList<String> upazillas = loadAssetTextAsString(context, "upazilla.csv");
        ArrayList<String> unions = loadAssetTextAsString(context, "union.csv");
        ArrayList<String> villages = loadAssetTextAsString(context, "village.csv");
        ArrayList<String> wards = loadAssetTextAsString(context, "ward.csv");
        ArrayList<String> farmer_info = loadAssetTextAsString(context, "farmer_info.csv");


        for (String division : divisions) {

            String[] separated = division.split(";");
            DivisionModel cDivision = new DivisionModel();
            cDivision.setDivisionID(separated[0]);
            cDivision.setDivisionName(separated[1]);

            try {
                db.insertData(cDivision);
            } catch (Exception e) {
                System.out.println("ERROR IN:" + e.toString());
            }
        }

        for (String district : districts) {

            String[] separated = district.split(";");
            DistrictModel cDistrict = new DistrictModel();

            cDistrict.setDistrictID(separated[0]);
            cDistrict.setDivisionID(separated[1]);
            cDistrict.setDistrictName(separated[2]);

            try {
                db.insertData(cDistrict);
            } catch (Exception e) {
                System.out.println("ERROR IN:" + e.toString());
            }
        }

        for (String upazilla : upazillas) {

            String[] separated = upazilla.split(";");
            UpazillaModel cUpazilla = new UpazillaModel();

            cUpazilla.setUpazillaID(separated[0]);
            cUpazilla.setDistrictID(separated[1]);
            cUpazilla.setDivisionID(separated[2]);
            cUpazilla.setUpazillaName(separated[3]);

            try {
                db.insertData(cUpazilla);
            } catch (Exception e) {
                System.out.println("ERROR IN:" + e.toString());
            }
        }

        for (String union : unions) {

            String[] separated = union.split(";");
            UnionModel cUnion = new UnionModel();

            cUnion.setUnionID(separated[0]);
            cUnion.setUpazillaID(separated[1]);
            cUnion.setDistrictID(separated[2]);
            cUnion.setDivisionID(separated[3]);
            cUnion.setUnionName(separated[4]);

            try {
                db.insertData(cUnion);
            } catch (Exception e) {
                System.out.println("ERROR IN:UNION"+cUnion.getUnionName());
            }
        }

        for (String village : villages) {

            String[] separated = village.split(";");
            VillegeModel cVillage = new VillegeModel();

            cVillage.setVillageID(separated[0]);
            cVillage.setUnionID(separated[1]);
            cVillage.setUpazillaID(separated[2]);
            cVillage.setDistrictID(separated[3]);
            cVillage.setDivisionID(separated[4]);
            cVillage.setVillageName(separated[5]);

            try {
                db.insertData(cVillage);
            } catch (Exception e) {
                System.out.println("ERROR IN:" + e.toString());
            }
        }

        for (String ward : wards) {

            String[] separated = ward.split(";");
            WardModel cWard = new WardModel();

            cWard.setWardID(separated[4]);
            cWard.setUnionID(separated[3]);
            cWard.setUpazillaID(separated[2]);
            cWard.setDistrictID(separated[1]);
            cWard.setDivisionID(separated[0]);
            cWard.setWardName(separated[5]);

            try {
                db.insertData(cWard);
            } catch (Exception e) {
                System.out.println("ERROR IN:" + e.toString());
            }
        }

        for (String farmer : farmer_info) {

            String[] separated = farmer.split(";");
            FarmerInfoModel cFarmer = new FarmerInfoModel();

            cFarmer.setFarmerID(Integer.parseInt(separated[0]));
            cFarmer.setFarmerName(separated[1]);
            cFarmer.setFarmerFatherHusbandName(separated[2]);
            cFarmer.setFarmerAge(separated[3]);
            cFarmer.setFarmingCategory(separated[4]);
            cFarmer.setNumberOfPonds(separated[5]);
            cFarmer.setAreaP1(separated[6]);
            cFarmer.setAreaP2(separated[7]);
            cFarmer.setAreaP3(separated[8]);
            cFarmer.setPhoneNumber("0" + separated[9]);
            cFarmer.setVillageID(separated[10]);
            cFarmer.setWardID(separated[11]);
            cFarmer.setUnionID(separated[12]);
            cFarmer.setUpazillaID(separated[13]);
            cFarmer.setDistrictID(separated[14]);
            cFarmer.setDivisionID(separated[15]);
            cFarmer.setActiveStatus(String.valueOf(1));
            try {
                  db.insertData(cFarmer);
                System.out.println("inserting:" + cFarmer.getFarmerName());
            } catch (Exception e) {
                System.out.println("ERROR IN:" + e.toString());
            }
        }


        SmsTemplateModel smsTemplateModel = new SmsTemplateModel();
        smsTemplateModel.setSmsTemplateText("প্রিয় চাষি ভাইঃ মৎস্য দপ্তর কলাপাড়ার পক্ষ থেকে নববর্ষের শুভেচ্ছা। আসুন আমরা সকলে মিলে মাছ চাষ করি ও দেশের অর্থনীতি মজবুত করি।");
        db.insertData(smsTemplateModel);

        smsTemplateModel = new SmsTemplateModel();
        smsTemplateModel.setSmsTemplateText("প্রিয় চাষি ভাই, আকাশে দূযোগের আশংকা। আপনার পুকুরের চারিদিকে ঘনফাঁসের নেট দিয়ে ঘিরে রাখুন।");
        db.insertData(smsTemplateModel);

        smsTemplateModel = new SmsTemplateModel();
        smsTemplateModel.setSmsTemplateText("প্রিয়া চাষি, বৈশাখের আগমনী বার্তা। আপনার পুকুর প্রস্তুতির সময় হয়েছে। প্রতি শতাংশে ১ কেজি চুন, ২০০ গ্রাম ইউরিয়া, ১০০ গ্রাম টিএসপি দিয়ে পুকুর প্রস্তুত করে রাখুন। প্রচারে মৎস্য দপ্তর, কলাপাড়া।");
        db.insertData(smsTemplateModel);

        smsTemplateModel = new SmsTemplateModel();
        smsTemplateModel.setSmsTemplateText("প্রিয় চাষি, পুকুর প্রস্তুতির ৭ দিন পর কাংখিত মাছের পোনা ছাড়ুন। বড় আকৃতির সুস্থ্য সবল পোনা ছেড়ে বেশী লাভবান হোন।");
        db.insertData(smsTemplateModel);

        smsTemplateModel = new SmsTemplateModel();
        smsTemplateModel.setSmsTemplateText("প্রিয় মাছ চাষি, আপনার পুকুরে নিয়মিত খাবার দিন। পাংগাস ও তেলাপিয়া মাছের জন্য দেহের ওজনের ৫ভাগ হারে খাবার সরবরাহ করুন।");
        db.insertData(smsTemplateModel);

        smsTemplateModel = new SmsTemplateModel();
        smsTemplateModel.setSmsTemplateText("প্রিয় মাছ চাষি, আপানর পুকুরে শতাংশ প্রতি ১২টি কাতলা, ১০ টি সিলভার কার্প, ১০ টি রুই, ১০ টি মৃগেল মাছ ছাড়ুন। পাংগাস ও তেলাপিয়া ২০০ টি /শতাংশ।");
        db.insertData(smsTemplateModel);

        smsTemplateModel = new SmsTemplateModel();
        smsTemplateModel.setSmsTemplateText("প্রিয় চাষি, প্রতি মাসে অন্তত একবার ২৫০ গ্রাম/শতাংশে চুন প্রয়োগ করুন। নিয়মিত মাছের পরিচযা˝করুন এবং লাভবান হউন।");
        db.insertData(smsTemplateModel);

        smsTemplateModel = new SmsTemplateModel();
        smsTemplateModel.setSmsTemplateText("প্রিয় চাষি, আপানার পুকুরের কোন সমস্যা তাৎক্ষনিক সিনিয়র উপজেলা মৎস্য অফিসার (০১৭১৬৫৩৭৭৩০) কে অবহিত করুন। অপনার সেবায় আমরা নিয়োজিত।");
        db.insertData(smsTemplateModel);

        smsTemplateModel = new SmsTemplateModel();
        smsTemplateModel.setSmsTemplateText("প্রিয় চাষি, প্রতি কার্পের পুকুরে ৫০টি গলদা পিএল ছাড়ুন। চিংড়ির উৎপাদন বৃদ্ধি করুন ও লাভবান হউন।");
        db.insertData(smsTemplateModel);

        smsTemplateModel = new SmsTemplateModel();
        smsTemplateModel.setSmsTemplateText("প্রিয় চাষি, প্রাকৃতিক খাদ্য উৎপাদনের জন্য প্রতি সপ্তাহে শতাংশ প্রতি ৫০গ্রাম, ইউরিয়া, ৫০গ্রাম টিএসপি প্রয়োগ করুন।");
        db.insertData(smsTemplateModel);

        smsTemplateModel = new SmsTemplateModel();
        smsTemplateModel.setSmsTemplateText("প্রিয় চাষি, অধিক লাভবান হতে দ্রুত বর্ধনশীল জাতের পাংগাস, মনোসেক্স তেলাপিয়া ও থাই কই মাছের চাষ করুন। প্রচারে মৎস্য দপ্তর কলাপাড়া।");
        db.insertData(smsTemplateModel);

        smsTemplateModel = new SmsTemplateModel();
        smsTemplateModel.setSmsTemplateText("প্রিয় চাষি, শীতের শুরুতে আপানার পুকুরে শতাংশ প্রতি ১কেজি চুন প্রয়োগ করুন। আসছে শীতে আপানার পুকুর নিরাপদ থাকবে।");
        db.insertData(smsTemplateModel);

        smsTemplateModel = new SmsTemplateModel();
        smsTemplateModel.setSmsTemplateText("প্রিয় চাষি, আপানার পুকুরে ভাল জাতের মাছ ছাড়ুন। নিয়মিত খাদ্য দিন মনে রাখবেন ভাল পোনা ও ভাল খাবার আপনার লাভের হাতিয়ার।");
        db.insertData(smsTemplateModel);

        DiseaseModel disease;
        disease = new DiseaseModel(2,"লেজ ও পাখনা পচা রোগ", "প্রাথমিক ভাবে Aeromonas ব্যাক্টেরিয়া পরে ফাংগাল ইনফেকশন","লেজ ও পাখনায় সাদা দাগ, রং ফ্যাকাশে, চলাফেরায় ভারসাম্য হারায় এবং একসময় মারা যায়।","প্রতি কেজি খাবারে ১ গ্রাম হারে ক্লোরোমাইসিন বা পুকুরে ২৫-৪০ গ্রাম/দিন হারে পটাসিয়াম পারম্যাঙ্গানেট বা  চুন ১০ গ্রাম/দিন (৫ দিন পর পর ৩ ডোজ)।","disease2");
        db.insertData(disease);
        disease = new DiseaseModel(3,"পেটফুলা (ড্রপসি) রোগ", "প্রাথমিক ভাবে ভাইরাস ও পরে Aeromona ব্যাক্টেরিয়া এই রোগ ছড়ায়","পট ফুলে বেলুনের মত হয়, পায়ু ফুলে লাল বর্ণ হয় মাছ চলাফেরায় ভারসাম্য হারায় এবং কিনারায় জমা হয়ে একসময় মারা যায়।","প্রতি কেজি খাবারে ১ গ্রাম হারে ক্লোরোমাইসিন বা পুকুরে ২ ppm হারে পটাসিয়াম পারম্যাঙ্গানেট বা  চুন ১০০ গ্রাম/দিন (৫ দিন পর পর ৩ ডোজ)।প্রতি কেজি খাবারের সাথে ২০০ মিঃ গ্রাঃ ক্লোরেমফেনিকল পাউডার মিশিয়ে সরবরাহ করা।","disease3");
        db.insertData(disease);
        disease = new DiseaseModel(4,"সাদা দাগ রোগ", "ইকথায়োপথিরিয়াস মালটিফিরিস Ichthyophthirius multifiliis নামক পরজীবির কারনেই এই রোগ হয় ।","ত্বক ও পাখনায় সাদা দাগ হয় ও  দাগের স্থানে ক্ষত সৃষ্টি হয়ে মাছ মারা যায়।","মাছের সংখ্যা কমিয়ে পানির প্রবাহ বৃদ্ধি করা। জীবানু মুক্ত পানিতে দুই সপ্তাহের মধ্যে মাছ স্বাভাবিকভাবেই আরোগ্য লাভ করে।মাসে দুই বার চুন ২০০ গ্রাম/দিন হারে প্রয়োগ করতে হবে।","disease4");
        db.insertData(disease);
        disease = new DiseaseModel(5,"লার্নিয়া  সংক্রমণ", "","এইসব পরজীবি মাছের গায়ে লেগে থেকে বিরক্তির উদ্রেক করে। মাছ লাফালাফি করে। অনেক সময় দুর্বল ও অবশ হয়ে যায়। নীলাভ-ধূসর মিউকাস দ্বারা মাছের শরীর আবৃত হয়। ছোট মাছ বেশি আক্রান্ত হয়। ফুলকার টিস্যু ফুলে যায় ও তিগ্রস্ত হয় ফলে মাছের শ্বাস নিতে কষ্ট হয়।","২৫ পিপিএম ফরমালিন আক্রান্ত পুকুরে প্রয়োগ।০.৫-০.৭ পিপিএম CuSO4  আক্রান্ত পুকুরে প্রয়োগ। ২-৩% NaCl – ৫-১৫ মিানট  মাছকে ধৌতকরণ উন্নত পরিবেশ সংরণ ও সুষম খাবার প্রয়োগ। অতিরিক্ত মাছ মজুদ পরিহার। পুকুর জীবাণু মুক্তকরণ (চুন প্রয়োগের মাধ্যমে)।পোনা মজুদের পূর্বে চুন প্রয়োগ।","disease5");
        db.insertData(disease);
        disease = new DiseaseModel(6,"উকুন রোগ", "আরগুলাস","এর আক্রমণে ছোট মাছের েেত্র দৈহিক ভারসাম্যহীনতা পরিলতি হয় ।মাছকে বিভিন্ন কঠিন বস্তুর সাথে গা ঘষতে দেখা যায়। আক্রান্ত স্থানে একটি গোলাকার গর্ত পরিলতি হয় যা অনেক সময় গাঢ় লাল বর্ণ ধারণ করে।","০.২-০.৫ পিপিএম ডিপটারেক্স সপ্তাতে ১ বার হিসেবে পর পর ৩ বার প্রয়োগ করা যেতে পারে। ৮-১০ মিঃলিঃ /১ ফুট গভীরতার জন্য পরপর সাত দিন ব্যবহার করা যেতে পারে।","disease6");
        db.insertData(disease);
        disease = new DiseaseModel(7,"স্ট্রেপটোকক্কাল আক্রমন", "স্ট্রেপটোকক্কাস ব্যাকটেরিয়া","শারিরীক দুর্বলতা ও চলাফেরায় শৈথিল্য। ক্ষুধামন্দা।পায়ুপথ ফ্যাকাশে লাল হওয়া। লালচে চক্ষু, ফুলকা ও মাংশ পেশী ।মাছের কলিজা, বৃক্ক ও প্লীহা ফুলে যাওয়া। মাছ খাড়াভাবে বৃত্তাকারে সাঁতার কাটে। চোখ বাইরের দিকে বের হয়ে যায় ও কর্নিয়া অস্বচ্ছ হয়ে যায়।","ইরাইথ্রোমাইসিনঃ ৫০ মি.গ্রা./কেজি মাছের জন্য/দিন/৪-৭ দিন খাবারের সাথে মিশিয়ে খাওয়াতে হবে।","disease7");
        db.insertData(disease);
        disease = new DiseaseModel(8,"মিক্সো-বোলিয়াসিস", "মিক্সো-বোলাস প্রজাতির এককোষী প্রাণী","রুই জাতীয় মাছের বিশেষ করে কাতলা মাছের ফুলকার উপরে সাদা কিংবা হালকা বাদামী গোলাকার গুটি তৈরী করে বংশ বৃদ্ধি করতে থাকে। ক্রমান্বয়ে ঐ গুটির প্রভাবে ফুলকায় ঘা দেখা যায় এবং ফুলকা খসে পড়ে। শ্বাসপ্রশ্বাসের ব্যাঘাত সৃষ্টিতে মাছ অস্থিরভাবে ঘোরাফেরা করে এবং খাবি খায়। ","অদ্যাবধি এই রোগের সরাসরি কোন চিকিৎসা আবিষ্কৃত হয় নাই। তথাপিও প্রতি শতাংশ জলাশয়ে ১ কেজি হারে চুন প্রয়োগ করলে পানির গুনাগুন বৃদ্ধি পেয়ে অম্লত্ব দূর হয়। পরজীবিগুলো ক্রমান্বয়ে অদৃশ্য হয়ে যায় এবং মাছ নিষ্কিৃতি লাভ করে। পুকুর প্রস্তুতকালীন প্রতি শতাংশ জলাশয়ে ১ কেজি হারে চুন প্রয়োগ করে মাটি শোধন করা হলে আসন্ন মৌসুমে এ রোগের প্রকোপ থাকে না।","disease8");
        db.insertData(disease);
        disease= new DiseaseModel(9,"হোয়াইট স্পট বা চাইনা ভাইরাস রোগ", "হোয়াইট স্পট বা চাইনা ভাইরাস","ঘেরে চিংড়ি পোনা ছাড়ার ৩০-৭০ দিনের মধ্যে সাধারণত এ রোগ দেখা দিতে পারে। রোগাক্রমণের ৩/৪ দিন পর রোগের তীব্রতা বৃদ্ধি পায়। বাগদা চিংড়ি এ রোগে আক্রান্ত হয়ে থাকে","এ রোগ প্রতিরোধে পরিমাণমতো চুন সার দিয়ে ঘেরের জমি প্রস্তুতপূর্বক প্রয়োজনীয় সংখ্যক পোনা ছেড়ে নিয়মিত পরিচর্যা করতে হবে। বেশি উৎপাদনের লক্ষ্যে উন্নত প্রযুক্তি ব্যবহারের কোনো বিকল্প নেই।","disease9");
        db.insertData(disease);
        disease = new DiseaseModel(10,"চিংড়ির কালো ফুলকা রোগ", "পুকুরের তলদেশে মাত্রাতিরিক্ত হাইড্রোজেন সালফাইট ও জৈব পদার্থের কারণে সাধারণত এ রোগের আক্রান্ত হয়ে থাকে","এ রোগ চিংড়ির ফুলকায় কালো দাগ ও পচন দেখা যায়। আক্রান্ত চিংড়ি খাদ্য গ্রহণে অনীহা দেখায় এবং ক্রমশ মৃত্যুর কোলে ঢলে পড়ে। বাগদা ও গলদা দুই শ্রেণীর চিংড়িই এ রোগের শিকার হয়ে থাকে।","পুকুরের তলায় আঁচড় কেটে কিংবা হড়রা টেনে দ্রুত পানির পরিবর্তন করে এ রোগ থেকে নিষ্কৃতি পাওয়া যেতে পারে। Ascabic Acid 2000 mg/কেজি খাদ্যে মিশিয়ে আক্রান্ত চিংড়িকে খাওয়ালে সুফল পাওয়া যায়। এ রোগ প্রতিরোধে পুকুর প্রস্তুতির সময় তলার কাদামাটি তুলে তলদেশ শুকিয়ে নির্ধারিত পরিমাণে চুন/ডলমাইট/ব্লিচিং পাউডার প্রয়োগ করতে হবে। যেসব খামারে পানি সরবরাহ ব্যবস্থা নেই, সেখানেই এ রোগের পার্দুভাব বেশি দেখা যায়।","disease10");
        db.insertData(disease);
        disease  = new DiseaseModel(11,"মস্তক হলুদ রোগ:", "Yellow head নামক ভাইরাস","এ রোগের সংক্রমন হয়। যকৃৎ, অগ্নাশয় গ্রন্থি ফ্যাকাশে হওয়ার কারণে মস্তক হলুদ বর্ণ ধারণ করে।","পোনা মজুদের মাস খানিকের মধ্যে এ রোগ ধরা পড়ে। শুধু বাগদা চিংড়ি এ রোগে আক্রান্ত হয়ে থাকে। ঘেরে ফাইটো প্নাঙ্কটল চাষ করা হলে এ রোগে নিয়ন্ত্রণ সহজ হয়। সুষ্ঠু খামার ব্যবস্থাপনার মাধ্যমে এ রোগ প্রতিরোধ করা যায়। খামারের তলদেশে শুকিয়ে ব্লিচিং পাউডার চুন দিয়ে যথাযথভাবে মাটি শোধন করে নিলে সন্তোষজনক ফল পাওয়া যায়।","disease11");
        db.insertData(disease);
        disease  = new DiseaseModel(12,"হোয়াইট মাসল রোগ", "জৈব পদার্থ ও তাপমাত্রার আধিক্যের কারণে এ রোগের আক্রমণ হয়ে থাকে।","চিংড়ির লেজের দিক থেকে মাংস সাদা ও শক্ত হয়ে যাওয়া এই রোগের একটি গুরুত্বপূর্ণ লক্ষণ।","পানি পরিবর্তনসহ গভীরতা বৃদ্ধি করে এ রোগ থেকে মুক্তি সম্ভব। সুষম খাদ্য প্রয়োগ করেও এ রোগে ভালো ফল পাওয়া যায়। এ রোগের ক্ষেত্রে পানির গভীরতা ও পোনামজুদ হার সঠিক মাত্রায় রাখা অতীব জরুরি।","disease12");
        db.insertData(disease);
        disease  = new DiseaseModel(13,"চিংড়ি গায়ে শ্যাওলা সমস্যা:", "পুকুর অতিরিক্ত খাদ্য প্রয়োগের কারণে সবুজ শ্যাওলার আধিক্য হেতু এই সমস্যা দেখা দেয়।","সাধারণত গলদার খামারে শ্যাওলা রোগের আক্রমণ বেশি হয়ে থাকে। এতে চিংড়ির খোলস বদলাতে পারে না। দৈহিক বর্ধন প্রক্রিয়ায় ব্যাহত হওয়ায় চিংড়ি ক্রমান্বয়ে মৃত্যুর কবলে পড়ে। বাগদা, গলদা দুই শ্রেণীর  চিংড়িই এ রোগে আক্রান্ত হয়ে থাকে।","এ ক্ষেত্রে পুকুর থেকে দূষিত পানি বের করে দিয়ে নিয়মিত নতুন পানি সরবরাহ করতে হবে। পানির গভীরতা বৃদ্ধি করার পাশাপাশি পোনা মজুদ হার হ্রাস এবং এ পর্যায়ে চুন সার ও খাদ্য প্রয়োগের পরিমাণ সীমিত রাখা অত্যাবশ্যক। শীতকালে গলদা খামারে এ রোগের পার্দুভাব বেমি দেখা যায়। এ সময় খামারে সার প্রয়োগ বন্ধ রাখা উত্তম।","disease13");
        db.insertData(disease);
        disease = new DiseaseModel(14,"ছত্রাক রোগ", "খামারে দীর্ঘ দিন পানি না বদলালে সাপ্রোলেগ নিয়া নামক ছত্রাক দ্বারা চিংড়ি এ রোগে আক্রান্ত হয়।","এতে চিংড়ির ফুলকায় ক্ষুদ্র ক্ষুদ্র দাগ দেখা যায়। পরবর্তীতে চিংড়ির খোলস নষ্ট হয়ে যায়। বাগদা ও গলদা দুই শ্রেণীর চিংড়িই এ রোগে আক্রান্ত হয়ে থাকে।","এ রোগ প্রতিরোধে খামারের তলদেশে শুকিয়ে যথানিয়মে চুন প্রয়োগ করে জলাশয় চাষোপযোগী করে নিতে হবে। হ্যাচারির যন্ত্রপাতি ও আনুষঙ্গিক মালামাল ১০% ফরমালিন দ্বারা পরিশোধন করে নেয়া একান্তই বাঞ্চনীয়। চিংড়ির লার্ভা পিত্রল এ রোগে মূলত বেশি আক্রান্ত হয়।","disease14");
        db.insertData(disease);
        disease = new DiseaseModel(15,"খোলসে কালো দাগ", "সিডোমনাস ফাইটিনোভরাস ভিবরিও","অন্যতম। ব্যাকটেরিয়ার আক্রমণে চিংড়ির খোলসে কালো দাগ পড়ে। খোলসের রঙ পরিবর্তন হয়। লেজের অংশ বিশেষ ও অন্যান্য উপাঙ্ক খসে পড়ে। এতে চিংড়ির মৃত্যু ত্বরান্বিত হয়। বাগদা গলদা দুই শ্রেণীর চিংড়িই এ রোগের কবলে পড়ে","যথা নিয়মে চুন ও সার প্রয়েগের পাশাপাশি পুকুর দীঘিতে চিংড়ির খাদ্য বৃদ্ধির মাধ্যমে এ রোগের প্রতিকার সম্ভব। এ রোগ প্রতিরোধে পুকুর দীঘির তলদেশ শুকিয়ে প্রয়োজনীয় মাত্রায় চুন সার প্রয়োগ করে পুকুর দীঘি প্রস্তুত করে নিতে হবে। পাশাপাশি পানি সরবরাহ ব্যবস্থাপনায় কোনো ত্রুটি রাখা যাবে না।\n","disease14");
        db.insertData(disease);
        disease  = new DiseaseModel(1,"পাংগাসের লাল দাগ রোগ", "শীতকালে অপেক্ষাকৃত নিম্ন তাপমাত্রায় Trichodina এবং Apisomia নামক বহিঃ পরজীবী দ্বারা অথবা পানির গুণাগুণ সহনীয় মাত্রায় না থাকলে পাংগাস মাছ রোগাক্রান্ত হতে পারে।","পাংগাস মাছ লালচে দাগ রোগে আক্রান্ত হলে ত্বক ও পাখনার গোড়ায় লালচে দাগ স্পষ্ট দেখা দেয় এবং কখনও কখনও মুখে ঘা দেখা দেয়। এ রোগে শরীরের বিভিন্ন স্থানে ফোস্কা দেখা দেয়। এ অবস্থায় মাছ অস্থির ও এলোমেলোভাবে সাঁতার কাটে।","পুকুরে পাংগাস মাছ বহিঃ পরজীবী বা ব্যাকটেরিয়ার দ্বারা আক্রান্ত হলে আক্রান্ত মাছগুলোকে জাল টেনে উঠিয়ে ১ মিলি/লিটার পানিতে ফরমালডিহাইড দ্রবণে গোসল করিয়ে পুকুরে ছেড়ে দিলে ভাল ফল পাওয়া যায়। আক্রান্ত পুকুরে শতাংশে ০.৫-১.০ কেজি হারে কলিচুন প্রয়োগ করলে পরিবেশের উন্নয়ন হয়। শীতকালে সপ্তাহে ১-২ দিন পরিমিত পরিমাণে ডিপ টিউবয়েলের পানি পুকুরে সরবরাহ করলে পাংগাস মাছ এ ধরণের রোগাক্রান্ত হওয়ার সম্ভাবনা কম থাকে। অথাব প্রতি কেজি খাবারের সাথে ৫০ মিগ্রা. টেট্রাসাইকিন মিশিয়ে ৭ দিন খাওয়ালে লেজ ও পাখনা পচা রোগ ভাল হয়। আরগুলাস বা উকুন দ্বারা পাঙ্গাস মাছ আক্রান্ত হলে প্রতি শতাংশে ৪০-৫০ গ্রাম (৪-৫ ফুট পানি) করে ডিপটারেক্স সপ্তাহে অন্তর ২ বার পুকুরে প্রয়োগ করতে হবে।","disease1");
        db.insertData(disease);


    }

    private ArrayList<String> loadAssetTextAsString(Context context, String name) {
        BufferedReader in = null;
        ArrayList<String> rows = new ArrayList<String>();
        try {
            StringBuilder buf = new StringBuilder();

            InputStream is = null;
            try {
                is = context.getAssets().open(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
            in = new BufferedReader(new InputStreamReader(is));

            String str;
            boolean isFirst = true;
            while ( (str = in.readLine()) != null ) {
                if (isFirst)
                    isFirst = false;
                else
                    buf.append('\n');
                rows.add(str);
                buf.append(str);
            }
            return rows;
        } catch (IOException e) {
            // Log.e(TAG, "Error opening asset " + name);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    //Log.e(TAG, "Error closing asset " + name);
                }
            }
        }

        return null;
    }

    private String rendomNumberGenerator(String charSet)
    {
        char[] chars = charSet.toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }
}

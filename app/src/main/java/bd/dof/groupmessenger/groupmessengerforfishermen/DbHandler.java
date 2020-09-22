package bd.dof.groupmessenger.groupmessengerforfishermen;

/**
 * Created by sagor on 6/22/2016.
 */
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DbHandler extends SQLiteOpenHelper{
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME="groupmessenger.db";


    //---TABLE NAME
    public static final String TABLE_DIVISION = "division";
    public static final String TABLE_DISTRICT = "district";
    public static final String TABLE_UPAZILLA = "upazilla";
    public static final String TABLE_UNION = "unionDB";
    public static final String TABLE_WARD = "ward";
    public static final String TABLE_VILLAGE = "village";
    public static final String TABLE_FARMER_INFO="farmer_info";
    public static final String TABLE_SMS_TEMPLATE="sms_template";
    public static final String TABLE_SYSTEM_INFO="system_info";
    public static final String TABLE_DISEASE="disease";

    ///------division table columns
    public static final String COLUMN_divisionID = "divisionID";
    public static final String COLUMN_divisionName = "divisionName";

    ///------district table columns
    public static final String COLUMN_districtID = "districtID";
    public static final String COLUMN_districtName = "districtName";

    ///------upazilla table columns
    public static final String COLUMN_upazillaID = "upazillaID";
    public static final String COLUMN_upazillaName = "upazillaName";

    ///------union table columns
    public static final String COLUMN_unionID = "unionID";
    public static final String COLUMN_unionName = "unionName";


    ///------ward table columns
    public static final String COLUMN_wardID = "wardID";
    public static final String COLUMN_wardName = "wardName";

    ///------village table columns
    public static final String COLUMN_villageID = "villageID";
    public static final String COLUMN_villageName = "villageName";

    ///------farmer info table columns
    public static final String COLUMN_farmerID = "farmerID";
    public static final String COLUMN_farmerName = "farmerName";
    public static final String COLUMN_farmerNID = "farmerNID";
    public static final String COLUMN_farmerFatherHusbandName = "farmerFatherHusbandName";
    public static final String COLUMN_farmerAge = "farmerAge";
    public static final String COLUMN_farmingCategory = "farmingCategory";
    public static final String COLUMN_numberOfPonds = "numberOfPonds";
    public static final String COLUMN_areaP1 = "areaP1";
    public static final String COLUMN_areaP2 = "areaP2";
    public static final String COLUMN_areaP3 = "areaP3";
    public static final String COLUMN_phoneNumber = "phoneNumber";
    public static final String COLUMN_activeStatus = "activeStatus";
    public static final String COLUMN_onlineUpload = "onlineUpload";

    ///------sms_template table columns
    public static final String COLUMN_smsTemplateID = "smsTemplateID";
    public static final String COLUMN_smsTemplateText = "smsTemplateText";

    ///------system info table columns
    public static final String COLUMN_userID = "userID";
    public static final String COLUMN_userName = "userName";
    public static final String COLUMN_userPhoneNumber = "userPhoneNumber";
    public static final String COLUMN_dataLoaded = "dataLoaded";
    public static final String COLUMN_userDivisionID = "userDivisionID";
    public static final String COLUMN_userDistrictID = "userDistrictID";
    public static final String COLUMN_userUpazillaID = "userUpazillaID";

    ///------disease table columns
    public static final String COLUMN_diseaseID = "diseaseID";
    public static final String COLUMN_diseaseName = "diseaseName";
    public static final String COLUMN_diseaseReason = "diseaseReason";
    public static final String COLUMN_diseaseSym = "diseaseSym";
    public static final String COLUMN_diseaseVac = "diseaseVac";
    public static final String COLUMN_diseasePic = "diseasePic";


    public DbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //---creating table division
        String query = "CREATE TABLE " + TABLE_DIVISION+" ( "+
                        COLUMN_divisionID + " TEXT PRIMARY KEY, "+
                        COLUMN_divisionName + " TEXT );";
        try
        {
            db.execSQL(query);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }

        //---creating table district
        query = "CREATE TABLE " + TABLE_DISTRICT+" ( "+
                COLUMN_districtID + " TEXT PRIMARY KEY, "+
                COLUMN_divisionID + " TEXT , "+
                COLUMN_districtName + " TEXT );";
        try
        {
            db.execSQL(query);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }

        //---creating table upazilla
        query = "CREATE TABLE " + TABLE_UPAZILLA+" ( "+
                COLUMN_upazillaID + " TEXT , "+
                COLUMN_districtID + " TEXT , "+
                COLUMN_divisionID + " TEXT , "+
                COLUMN_upazillaName + " TEXT );";
        try
        {
            db.execSQL(query);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }


        //---creating table union
        query = "CREATE TABLE " + TABLE_UNION+" ( "+
                COLUMN_unionID + " TEXT , "+
                COLUMN_upazillaID + " TEXT , "+
                COLUMN_districtID + " TEXT , "+
                COLUMN_divisionID + " TEXT , "+
                COLUMN_unionName + " TEXT );";
        try
        {
            db.execSQL(query);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }

        //---creating table ward
        query = "CREATE TABLE " + TABLE_WARD+" ( "+
                COLUMN_wardID + " TEXT , "+
                COLUMN_unionID + " TEXT , "+
                COLUMN_upazillaID + " TEXT , "+
                COLUMN_districtID + " TEXT , "+
                COLUMN_divisionID + " TEXT , "+
                COLUMN_wardName + " TEXT );";
        try
        {
            db.execSQL(query);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }

        //---creating table villege
        query = "CREATE TABLE " + TABLE_VILLAGE+" ( "+
                COLUMN_villageID + " TEXT , "+
                COLUMN_unionID + " TEXT , "+
                COLUMN_upazillaID + " TEXT , "+
                COLUMN_districtID + " TEXT , "+
                COLUMN_divisionID + " TEXT , "+
                COLUMN_villageName + " TEXT );";
        try
        {
            db.execSQL(query);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }

        //---creating table farmer info
        query = "CREATE TABLE " + TABLE_FARMER_INFO+" ( "+
                COLUMN_farmerID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_farmerName + " TEXT , "+
                COLUMN_farmerNID + " TEXT , "+
                COLUMN_farmerFatherHusbandName + " TEXT , "+
                COLUMN_farmerAge + " TEXT , "+
                COLUMN_farmingCategory + " TEXT , "+
                COLUMN_numberOfPonds + " INTEGER , "+
                COLUMN_areaP1 + " TEXT , "+
                COLUMN_areaP2 + " TEXT , "+
                COLUMN_areaP3 + " TEXT , "+
                COLUMN_phoneNumber + " TEXT , "+
                COLUMN_divisionID + " TEXT , "+
                COLUMN_districtID + " TEXT , "+
                COLUMN_upazillaID + " TEXT , "+
                COLUMN_unionID + " TEXT , "+
                COLUMN_wardID + " TEXT , "+
                COLUMN_villageID + " TEXT , "+
                COLUMN_onlineUpload + " INTEGER , "+
                COLUMN_activeStatus + " INTEGER );";
        try
        {
            db.execSQL(query);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }

        //---creating table sms template
        query = "CREATE TABLE " + TABLE_SMS_TEMPLATE+" ( "+
                COLUMN_smsTemplateID + " INTEGER PRIMARY KEY AUTOINCREMENT , "+
                COLUMN_smsTemplateText + " TEXT NOT NULL UNIQUE);";
        try
        {
            db.execSQL(query);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }

        //---creating table system info
        query = "CREATE TABLE " + TABLE_SYSTEM_INFO+" ( "+
                COLUMN_userID + " INTEGER PRIMARY KEY, "+
                COLUMN_userName + " TEXT , "+
                COLUMN_userPhoneNumber + " TEXT , "+
                COLUMN_userDivisionID + " TEXT , "+
                COLUMN_userDistrictID + " TEXT , "+
                COLUMN_userUpazillaID + " TEXT , "+
                COLUMN_dataLoaded + " INTEGER);";
        try
        {
            db.execSQL(query);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }


        //---creating table disease
        query = "CREATE TABLE " + TABLE_DISEASE+" ( "+
                COLUMN_diseaseID + " INTEGER PRIMARY KEY , "+
                COLUMN_diseaseName + " TEXT,"+
                COLUMN_diseaseReason + " TEXT,"+
                COLUMN_diseaseSym + " TEXT,"+
                COLUMN_diseaseVac + " TEXT,"+
                COLUMN_diseasePic + " TEXT"
                +");";
        try
        {
            db.execSQL(query);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DIVISION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DISTRICT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UPAZILLA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_UNION);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_WARD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VILLAGE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FARMER_INFO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SMS_TEMPLATE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SYSTEM_INFO);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_DISEASE);
        onCreate(db);
    }


    //------------------------------------DATA INSERT[STARTED]
    public void insertData(DivisionModel cDivision)
    {
        ContentValues insertValues = new ContentValues();

        insertValues.put(COLUMN_divisionID, cDivision.getDivisionID());
        insertValues.put(COLUMN_divisionName, cDivision.getDivisionName());

        SQLiteDatabase db = getWritableDatabase();
        try
        {
            db.insert(TABLE_DIVISION, null, insertValues);

        }
        catch (Exception e) {
            Log.e("ERROR", e.toString());
        }
        db.close();
    }

    public void insertData(DistrictModel cDistrict)
    {
        ContentValues insertValues = new ContentValues();

        insertValues.put(COLUMN_districtID, cDistrict.getDistrictID());
        insertValues.put(COLUMN_divisionID, cDistrict.getDivisionID());
        insertValues.put(COLUMN_districtName, cDistrict.getDistrictName());

        SQLiteDatabase db = getWritableDatabase();
        try
        {
            db.insert(TABLE_DISTRICT, null, insertValues);

        }
        catch (Exception e) {
            Log.e("ERROR", e.toString());
        }
        db.close();
    }

    public void insertData(UpazillaModel cUpazilla)
    {
        ContentValues insertValues = new ContentValues();

        insertValues.put(COLUMN_upazillaID, cUpazilla.getUpazillaID());
        insertValues.put(COLUMN_districtID, cUpazilla.getDistrictID());
        insertValues.put(COLUMN_divisionID, cUpazilla.getDivisionID());
        insertValues.put(COLUMN_upazillaName, cUpazilla.getUpazillaName());

        SQLiteDatabase db = getWritableDatabase();
        try
        {
            db.insert(TABLE_UPAZILLA, null, insertValues);

        }
        catch (Exception e) {
            Log.e("ERROR", e.toString());
        }
        db.close();
    }

    public void insertData(UnionModel cUnion)
    {
        ContentValues insertValues = new ContentValues();

        insertValues.put(COLUMN_unionID, cUnion.getUnionID());
        insertValues.put(COLUMN_upazillaID, cUnion.getUpazillaID());
        insertValues.put(COLUMN_districtID, cUnion.getDistrictID());
        insertValues.put(COLUMN_divisionID, cUnion.getDivisionID());
        insertValues.put(COLUMN_unionName, cUnion.getUnionName());

        SQLiteDatabase db = getWritableDatabase();
        try
        {
            db.insert(TABLE_UNION, null, insertValues);

        }
        catch (Exception e) {
            Log.e("ERROR", e.toString());

        }
        db.close();
    }

    public void insertData(VillegeModel cVillage)
    {
        ContentValues insertValues = new ContentValues();

        insertValues.put(COLUMN_villageID, cVillage.getVillageID());
        insertValues.put(COLUMN_unionID, cVillage.getUnionID());
        insertValues.put(COLUMN_upazillaID, cVillage.getUpazillaID());
        insertValues.put(COLUMN_districtID, cVillage.getDistrictID());
        insertValues.put(COLUMN_divisionID, cVillage.getDivisionID());
        insertValues.put(COLUMN_villageName, cVillage.getVillageName());

        SQLiteDatabase db = getWritableDatabase();
        try
        {
            db.insert(TABLE_VILLAGE, null, insertValues);

        }
        catch (Exception e) {
            Log.e("ERROR", e.toString());
        }
        db.close();
    }

    public void insertData(WardModel cWard)
    {
        ContentValues insertValues = new ContentValues();

        insertValues.put(COLUMN_wardID, cWard.getWardID());
        insertValues.put(COLUMN_unionID, cWard.getUnionID());
        insertValues.put(COLUMN_upazillaID, cWard.getUpazillaID());
        insertValues.put(COLUMN_districtID, cWard.getDistrictID());
        insertValues.put(COLUMN_divisionID, cWard.getDivisionID());
        insertValues.put(COLUMN_wardName, cWard.getWardName());

        SQLiteDatabase db = getWritableDatabase();
        try
        {
            db.insert(TABLE_WARD, null, insertValues);

        }
        catch (Exception e) {
            Log.e("ERROR", e.toString());
        }
        db.close();
    }

    public void insertData(FarmerInfoModel cFarmer)
    {
        ContentValues insertValues = new ContentValues();
        //insertValues.put(COLUMN_farmerID, cFarmer.getFarmerID());
        insertValues.put(COLUMN_farmerName, cFarmer.getFarmerName());
        insertValues.put(COLUMN_farmerFatherHusbandName, cFarmer.getFarmerFatherHusbandName());
        insertValues.put(COLUMN_farmerAge, cFarmer.getFarmerAge());
        insertValues.put(COLUMN_farmingCategory, cFarmer.getFarmingCategory());
        insertValues.put(COLUMN_numberOfPonds, cFarmer.getNumberOfPonds());
        insertValues.put(COLUMN_areaP1, cFarmer.getAreaP1());
        insertValues.put(COLUMN_areaP2, cFarmer.getAreaP2());
        insertValues.put(COLUMN_areaP2, cFarmer.getAreaP2());
        insertValues.put(COLUMN_phoneNumber, cFarmer.getPhoneNumber());
        insertValues.put(COLUMN_villageID, cFarmer.getVillageID());
        insertValues.put(COLUMN_wardID, cFarmer.getWardID());
        insertValues.put(COLUMN_unionID, cFarmer.getUnionID());
        insertValues.put(COLUMN_upazillaID, cFarmer.getUpazillaID());
        insertValues.put(COLUMN_districtID, cFarmer.getDistrictID());
        insertValues.put(COLUMN_divisionID, cFarmer.getDivisionID());
        insertValues.put(COLUMN_activeStatus, cFarmer.getActiveStatus());
        insertValues.put(COLUMN_onlineUpload, cFarmer.getUploadStatus());

        SQLiteDatabase db = getWritableDatabase();
        try
        {
            db.insert(TABLE_FARMER_INFO, null, insertValues);

        }
        catch (Exception e) {
            Log.e("ERROR", e.toString());
        }
        db.close();
    }


    public void insertData(SystemInformationModel systemInformationModel)
    {
        ContentValues insertValues = new ContentValues();

        insertValues.put(COLUMN_userID, systemInformationModel.getUserID());
        insertValues.put(COLUMN_userName, systemInformationModel.getUserName());
        insertValues.put(COLUMN_userPhoneNumber, systemInformationModel.getUserPhoneNumber());
        insertValues.put(COLUMN_userDivisionID, systemInformationModel.getUserDivisionID());
        insertValues.put(COLUMN_userDistrictID, systemInformationModel.getUserDistrictID());
        insertValues.put(COLUMN_userUpazillaID, systemInformationModel.getUserUpazillaID());
        insertValues.put(COLUMN_dataLoaded, systemInformationModel.getDataLoaded());

        SQLiteDatabase db = getWritableDatabase();
        try
        {
            db.insert(TABLE_SYSTEM_INFO, null, insertValues);

        }
        catch (Exception e) {
            Log.e("ERROR", e.toString());
        }
        db.close();
    }


    public void insertData(SmsTemplateModel smsTemplateModel)
    {
        ContentValues insertValues = new ContentValues();
        insertValues.put(COLUMN_smsTemplateText, smsTemplateModel.getSmsTemplateText());
        SQLiteDatabase db = getWritableDatabase();
        try
        {
            db.insert(TABLE_SMS_TEMPLATE, null, insertValues);

        }
        catch (Exception e) {
            Log.e("ERROR", e.toString());
        }
        db.close();
    }

    public void insertData(DiseaseModel disease)
    {
        ContentValues insertValues = new ContentValues();
        insertValues.put(COLUMN_diseaseID, disease.getDiseaseID());
        insertValues.put(COLUMN_diseaseName, disease.getDiseaseName());
        insertValues.put(COLUMN_diseaseReason, disease.getDiseaseReason());
        insertValues.put(COLUMN_diseaseSym, disease.getDiseaseSym());
        insertValues.put(COLUMN_diseaseVac, disease.getDiseaseVac());
        insertValues.put(COLUMN_diseasePic, disease.getDiseasePic());
        SQLiteDatabase db = getWritableDatabase();
        try
        {
            db.insert(TABLE_DISEASE, null, insertValues);

        }
        catch (Exception e) {
            Log.e("ERROR", e.toString());
        }
        db.close();
    }
    //------------------------------------DATA INSERT[END]

    //-----------------------------------DATA UPDATE[START]
    public void updateData(FarmerInfoModel cFarmer)
    {
        ContentValues insertValues = new ContentValues();
        insertValues.put(COLUMN_farmerID, cFarmer.getFarmerID());
        insertValues.put(COLUMN_farmerName, cFarmer.getFarmerName());
        insertValues.put(COLUMN_farmerFatherHusbandName, cFarmer.getFarmerFatherHusbandName());
        insertValues.put(COLUMN_farmerAge, cFarmer.getFarmerAge());
        insertValues.put(COLUMN_farmingCategory, cFarmer.getFarmingCategory());
        insertValues.put(COLUMN_numberOfPonds, cFarmer.getNumberOfPonds());
        insertValues.put(COLUMN_areaP1, cFarmer.getAreaP1());
        insertValues.put(COLUMN_areaP2, cFarmer.getAreaP2());
        insertValues.put(COLUMN_areaP2, cFarmer.getAreaP2());
        insertValues.put(COLUMN_phoneNumber, cFarmer.getPhoneNumber());
        insertValues.put(COLUMN_villageID, cFarmer.getVillageID());
        insertValues.put(COLUMN_wardID, cFarmer.getWardID());
        insertValues.put(COLUMN_unionID, cFarmer.getUnionID());
        insertValues.put(COLUMN_upazillaID, cFarmer.getUpazillaID());
        insertValues.put(COLUMN_districtID, cFarmer.getDistrictID());
        insertValues.put(COLUMN_divisionID, cFarmer.getDivisionID());
        insertValues.put(COLUMN_activeStatus, cFarmer.getActiveStatus());
        insertValues.put(COLUMN_onlineUpload, cFarmer.getUploadStatus());

        SQLiteDatabase db = getWritableDatabase();
        try
        {
            db.update(TABLE_FARMER_INFO, insertValues, COLUMN_farmerID + "=" + cFarmer.getFarmerID(), null);
        }
        catch (Exception e) {
            Log.e("ERROR", e.toString());
        }
        db.close();
    }

    public void updateData(DiseaseModel cDisease)
    {
        ContentValues insertValues = new ContentValues();
        insertValues.put(COLUMN_diseaseID, cDisease.getDiseaseID());
        insertValues.put(COLUMN_diseaseName, cDisease.getDiseaseName());
        insertValues.put(COLUMN_diseasePic, cDisease.getDiseasePic());
        insertValues.put(COLUMN_diseaseReason, cDisease.getDiseaseReason());
        insertValues.put(COLUMN_diseaseSym, cDisease.getDiseaseSym());
        insertValues.put(COLUMN_diseaseVac, cDisease.getDiseaseVac());

        SQLiteDatabase db = getWritableDatabase();
        try
        {
            db.update(TABLE_DISEASE, insertValues, COLUMN_diseaseID + "=" + cDisease.getDiseaseID(), null);
        }
        catch (Exception e) {
            Log.e("ERROR", e.toString());
        }
        db.close();
    }
    //-----------------------------------DATA UPDATE[END]

    //-----------------------------------DATA SELECTION[STARTED]
    public SystemInformationModel getSystemInfo()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_SYSTEM_INFO+" WHERE 1";
        Cursor c=null;
        try
        {
            c =db.rawQuery(query,null);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }
        SystemInformationModel cSystemInfo = new SystemInformationModel();
        if(c.getCount()>0)
        {
            c.moveToFirst();

            do
            {
                cSystemInfo.setUserID(c.getInt(0));
                cSystemInfo.setUserName(c.getString(1));
                cSystemInfo.setUserPhoneNumber(c.getString(2));
                cSystemInfo.setUserDivisionID(c.getString(3));
                cSystemInfo.setUserDistrictID(c.getString(4));
                cSystemInfo.setUserUpazillaID(c.getString(5));
                cSystemInfo.setDataLoaded(c.getInt(6));

            }while(c.moveToNext());
        }
        db.close();

        return  cSystemInfo;
    }

    public List<FarmerInfoModel> getAllFarmerInfo()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_FARMER_INFO;
        Cursor c=null;
        try
        {
            c =db.rawQuery(query,null);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }

        if(c.getCount()>0){
            c.moveToFirst();
            List<FarmerInfoModel> farmerList=new ArrayList<FarmerInfoModel>();
            String resultString="";
            do
            {
                FarmerInfoModel cFarmer = new FarmerInfoModel();
               /* cFarmer.setAreaID(c.getInt(0));
                cFarmer.setAreaName(c.getString(c.getColumnIndex("area_name")));
                cFarmer.setParentID(c.getColumnIndex("parent_id"));
                cFarmer.setActive_stat(c.getColumnIndex("active_stat"));
                cFarmer.setLast_update(c.getString(c.getColumnIndex("last_update")));*/
                farmerList.add(cFarmer);
            }while(c.moveToNext());
            db.close();
        }
        return  null;
    }

    public List<UnionModel> getUnionList(String divisionID,String districtID,String upazillaID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_UNION+" WHERE "+COLUMN_divisionID+"='"+divisionID+"' AND "+COLUMN_districtID+"='"+districtID+"' AND "+COLUMN_upazillaID+"='"+upazillaID+"'";
        Cursor c=null;
        try
        {
            c =db.rawQuery(query,null);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }
        List<UnionModel> unionList=new ArrayList<UnionModel>();
        if(c.getCount()>0){
            c.moveToFirst();

            String resultString="";
            do
            {
                UnionModel cUnion = new UnionModel();
                cUnion.setUnionID(c.getString(0));
                cUnion.setUpazillaID(c.getString(1));
                cUnion.setDistrictID(c.getString(2));
                cUnion.setDivisionID(c.getString(3));
                cUnion.setUnionName(c.getString(4));
                unionList.add(cUnion);
            }while(c.moveToNext());
            db.close();
        }
        return unionList;
    }

    public List<VillegeModel> getVillegenList(String divisionID,String districtID,String upazillaID,String unionID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_VILLAGE+" WHERE "+COLUMN_divisionID+"='"+divisionID+"' AND "+COLUMN_districtID+"='"+districtID+"' AND "+COLUMN_upazillaID+"='"+upazillaID+"' AND "+COLUMN_unionID+"='"+unionID+"'";
        Cursor c=null;
        try
        {
            c =db.rawQuery(query,null);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }
        List<VillegeModel> villageList=new ArrayList<VillegeModel>();
        if(c.getCount()>0){
            c.moveToFirst();

            String resultString="";
            do
            {
                VillegeModel cVillage = new VillegeModel();
                cVillage.setVillageID(c.getString(0));
                cVillage.setUnionID(c.getString(1));
                cVillage.setUpazillaID(c.getString(2));
                cVillage.setDistrictID(c.getString(3));
                cVillage.setDivisionID(c.getString(4));
                cVillage.setVillageName(c.getString(5));
                villageList.add(cVillage);
            }while(c.moveToNext());
            db.close();
        }
        return villageList;
    }

    public List<WardModel> getWardList(String divisionID,String districtID,String upazillaID,String unionID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_WARD+" WHERE "+COLUMN_divisionID+"='"+divisionID+"' AND "+COLUMN_districtID+"='"+districtID+"' AND "+COLUMN_upazillaID+"='"+upazillaID+"' AND "+COLUMN_unionID+"='"+unionID+"'";
        Cursor c=null;
        try
        {
            c =db.rawQuery(query,null);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }
        List<WardModel> WardList=new ArrayList<WardModel>();
        if(c.getCount()>0){
            c.moveToFirst();
            do
            {
                WardModel cWard = new WardModel();
                cWard.setWardID(c.getString(0));
                cWard.setUnionID(c.getString(1));
                cWard.setUpazillaID(c.getString(2));
                cWard.setDistrictID(c.getString(3));
                cWard.setDivisionID(c.getString(4));
                cWard.setWardName(c.getString(5));
                WardList.add(cWard);
            }while(c.moveToNext());
            db.close();
        }
        return WardList;
    }

    public UnionModel getUnionIDByName(String divisionID,String districtID,String upazillaID,String unionName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_UNION+" WHERE "+COLUMN_divisionID+"='"+divisionID+"' AND "+COLUMN_districtID+"='"+districtID+"' AND "+COLUMN_upazillaID+"='"+upazillaID+"' AND "+COLUMN_unionName+"='"+unionName+"'";
         Cursor c=null;
        try
        {
            c =db.rawQuery(query,null);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }
        UnionModel cUnion = new UnionModel();
        if(c.getCount()>0){
            c.moveToFirst();
            do
            {
                cUnion.setUnionID(c.getString(0));
                cUnion.setUpazillaID(c.getString(1));
                cUnion.setDistrictID(c.getString(2));
                cUnion.setDivisionID(c.getString(3));
                cUnion.setUnionName(c.getString(4));

            }while(c.moveToNext());
            db.close();
        }
        return cUnion;
    }

    public VillegeModel getVillageIDByName(String divisionID,String districtID,String upazillaID,String unionID,String villageName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_VILLAGE+" WHERE "+COLUMN_divisionID+"='"+divisionID+"' AND "+COLUMN_districtID+"='"+districtID+"' AND "+COLUMN_upazillaID+"='"+upazillaID+"' AND "+COLUMN_unionID+"='"+unionID+"' AND "+COLUMN_villageName+"='"+villageName+"'";
        Cursor c=null;
        try
        {
            c =db.rawQuery(query,null);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }
        VillegeModel cVillage = new VillegeModel();
        if(c.getCount()>0){
            c.moveToFirst();
            do
            {
                cVillage.setVillageID(c.getString(0));
                cVillage.setUnionID(c.getString(1));
                cVillage.setUpazillaID(c.getString(2));
                cVillage.setDistrictID(c.getString(3));
                cVillage.setDivisionID(c.getString(4));
                cVillage.setVillageName(c.getString(5));
            }while(c.moveToNext());
            db.close();
        }
        return cVillage;
    }

    public WardModel getWardIDByName(String divisionID,String districtID,String upazillaID,String unionID,String wardName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_WARD+" WHERE "+COLUMN_divisionID+"='"+divisionID+"' AND "+COLUMN_districtID+"='"+districtID+"' AND "+COLUMN_upazillaID+"='"+upazillaID+"' AND "+COLUMN_unionID+"='"+unionID+"' AND "+COLUMN_wardName+"='"+wardName+"'";
        Cursor c=null;
        try
        {
            c =db.rawQuery(query,null);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }
        WardModel cWard = new WardModel();
        if(c.getCount()>0){
            c.moveToFirst();
            do
            {
                cWard.setWardID(c.getString(0));
                cWard.setUnionID(c.getString(1));
                cWard.setUpazillaID(c.getString(2));
                cWard.setDistrictID(c.getString(3));
                cWard.setDivisionID(c.getString(4));
                cWard.setWardName(c.getString(5));
            }while(c.moveToNext());
            db.close();
        }
        return cWard;
    }

    public List<FarmerInfoModel> getFarmerInfoByArea(String divisionID,String districtID,String upazillaID,String unionID,String villageID,String wardID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_FARMER_INFO+" WHERE "+COLUMN_divisionID+"='"+divisionID+"' AND "+COLUMN_districtID+"='"+districtID+"' AND "+COLUMN_upazillaID+"='"+upazillaID+"' AND " +COLUMN_unionID+" LIKE '"+unionID+"' AND "+COLUMN_villageID + " LIKE '"+villageID+"' AND "+COLUMN_wardID+" LIKE '"+wardID+"'";
        Cursor c=null;
        try
        {
            c =db.rawQuery(query,null);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }
        List<FarmerInfoModel> farmerList=new ArrayList<FarmerInfoModel>();
        if(c.getCount()>0){
            c.moveToFirst();
            do
            {
                FarmerInfoModel cFarmer = new FarmerInfoModel();
                cFarmer.setFarmerID(c.getInt(0));
                cFarmer.setFarmerName(c.getString(1));
                cFarmer.setFarmerNID(c.getString(2));
                cFarmer.setFarmerFatherHusbandName(c.getString(3));
                cFarmer.setFarmerAge(c.getString(4));
                cFarmer.setFarmingCategory(c.getString(5));
                cFarmer.setNumberOfPonds(c.getString(6));
                cFarmer.setAreaP1(c.getString(7));
                cFarmer.setAreaP2(c.getString(8));
                cFarmer.setAreaP3(c.getString(9));
                cFarmer.setPhoneNumber(c.getString(10));
                cFarmer.setDivisionID(c.getString(11));
                cFarmer.setDistrictID(c.getString(12));
                cFarmer.setUpazillaID(c.getString(13));
                cFarmer.setUnionID(c.getString(14));
                cFarmer.setWardID(c.getString(15));
                cFarmer.setVillageID(c.getString(16));
                cFarmer.setActiveStatus(c.getString(17));
                farmerList.add(cFarmer);
            }while(c.moveToNext());
            db.close();
        }
        return  farmerList;
    }

    public ArrayList<FarmerInfoModel> getFarmerInfoPendingUpdate()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_FARMER_INFO+" WHERE "+COLUMN_onlineUpload+"=0";
        Cursor c=null;
        try
        {
            c =db.rawQuery(query,null);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }
        ArrayList<FarmerInfoModel> farmerList=new ArrayList<FarmerInfoModel>();
        if(c.getCount()>0){
            c.moveToFirst();
            do
            {
                FarmerInfoModel cFarmer = new FarmerInfoModel();
                cFarmer.setFarmerID(c.getInt(0));
                cFarmer.setFarmerName(c.getString(1));
                cFarmer.setFarmerNID(c.getString(2));
                cFarmer.setFarmerFatherHusbandName(c.getString(3));
                cFarmer.setFarmerAge(c.getString(4));
                cFarmer.setFarmingCategory(c.getString(5));
                cFarmer.setNumberOfPonds(c.getString(6));
                cFarmer.setAreaP1(c.getString(7));
                cFarmer.setAreaP2(c.getString(8));
                cFarmer.setAreaP3(c.getString(9));
                cFarmer.setPhoneNumber(c.getString(10));
                cFarmer.setDivisionID(c.getString(11));
                cFarmer.setDistrictID(c.getString(12));
                cFarmer.setUpazillaID(c.getString(13));
                cFarmer.setUnionID(c.getString(14));
                cFarmer.setWardID(c.getString(15));
                cFarmer.setVillageID(c.getString(16));
                cFarmer.setActiveStatus(c.getString(17));
                farmerList.add(cFarmer);
            }while(c.moveToNext());
            db.close();
        }
        return  farmerList;
    }


    public List<String> getFarmingCategoryByArea(String divisionID,String districtID,String upazillaID,String unionID,String wardID,String villageID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+COLUMN_farmingCategory+" FROM "+ TABLE_FARMER_INFO+" WHERE "+COLUMN_divisionID+"='"+divisionID+"' AND "+COLUMN_districtID+"='"+districtID+"' AND "+COLUMN_upazillaID+"='"+upazillaID+"' GROUP BY "+COLUMN_farmingCategory;
        System.out.println(query);
        Cursor c=null;
        try
        {
            c =db.rawQuery(query,null);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }
        List<String> farmingCatagory = new ArrayList<String>();
        if(c.getCount()>0){
            c.moveToFirst();
            do
            {
                farmingCatagory.add(c.getString(0));
            }while(c.moveToNext());
            db.close();
        }
        return farmingCatagory;
    }


    public List<FarmerInfoModel> getFarmerInfoByAreaFarmingCategoryMobileOperator(String divisionID,String districtID,String upazillaID,List<String> unionID,String villageID,String wardID,List<String> farmingCategoryList,List<String> phoneCompanyList)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String extendedConditionFarmingCategory = " ( ";
        String extendedConditionMobileOperator = " ( ";
        String extendedConditionUnionID = " ( ";

        if(unionID.size()>0) {
            for (int i = 0; i < unionID.size() - 1; i++) {
                extendedConditionUnionID += COLUMN_unionID + "='" + unionID.get(i) + "' OR ";
            }
            extendedConditionUnionID += COLUMN_unionID + "='" + unionID.get(unionID.size()- 1) + "' " + " ) ";
        }
        else
        {
            extendedConditionUnionID="1";
        }


        if(farmingCategoryList.size()>0) {
            for (int i = 0; i < farmingCategoryList.size() - 1; i++) {
                extendedConditionFarmingCategory += COLUMN_farmingCategory + "='" + farmingCategoryList.get(i) + "' OR ";
            }
            extendedConditionFarmingCategory += COLUMN_farmingCategory + "='" + farmingCategoryList.get(farmingCategoryList.size()- 1) + "' " + " ) ";
        }
        else
        {
            extendedConditionFarmingCategory="1";
        }

        if(phoneCompanyList.size()>0) {
            for (int i = 0; i < phoneCompanyList.size() - 1; i++) {
                extendedConditionMobileOperator += COLUMN_phoneNumber + " LIKE '" + phoneCompanyList.get(i) + "%' OR ";
            }
            extendedConditionMobileOperator += COLUMN_phoneNumber + " LIKE '" + phoneCompanyList.get(phoneCompanyList.size()- 1) + "%' " + " ) ";
        }
        else{extendedConditionMobileOperator="1";}

        //String query = "SELECT * FROM "+ TABLE_FARMER_INFO+" WHERE "+COLUMN_divisionID+"='"+divisionID+"' AND "+COLUMN_districtID+"='"+districtID+"' AND "+COLUMN_upazillaID+"='"+upazillaID+"' AND " + COLUMN_villageID + " LIKE '"+villageID+"' AND "+COLUMN_wardID+" LIKE '"+wardID+"' AND "+extendedConditionFarmingCategory+" AND "+extendedConditionMobileOperator + " AND "+ extendedConditionUnionID;
        String query = "SELECT * FROM "+ TABLE_FARMER_INFO+" WHERE "+COLUMN_divisionID+"='"+divisionID+"' AND "+COLUMN_districtID+"='"+districtID+"' AND "+COLUMN_upazillaID+"='"+upazillaID+"' AND "+extendedConditionFarmingCategory+" AND "+extendedConditionMobileOperator  + " AND "+ extendedConditionUnionID;
        System.out.println(query);
        Cursor c=null;
        try
        {
            c =db.rawQuery(query,null);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }
        List<FarmerInfoModel> farmerList=new ArrayList<FarmerInfoModel>();
        List<String> tempNum = new ArrayList<>();
        if(c.getCount()>0){
            c.moveToFirst();
            do
            {
                if(!tempNum.contains(c.getString(10)) && c.getString(10).length()==11) {
                    tempNum.add(c.getString(10));
                    FarmerInfoModel cFarmer = new FarmerInfoModel();
                    cFarmer.setFarmerID(c.getInt(0));
                    cFarmer.setFarmerName(c.getString(1));
                    cFarmer.setFarmerNID(c.getString(2));
                    cFarmer.setFarmerFatherHusbandName(c.getString(3));
                    cFarmer.setFarmerAge(c.getString(4));
                    cFarmer.setFarmingCategory(c.getString(5));
                    cFarmer.setNumberOfPonds(c.getString(6));
                    cFarmer.setAreaP1(c.getString(7));
                    cFarmer.setAreaP2(c.getString(8));
                    cFarmer.setAreaP3(c.getString(9));
                    cFarmer.setPhoneNumber(c.getString(10));
                    cFarmer.setDivisionID(c.getString(11));
                    cFarmer.setDistrictID(c.getString(12));
                    cFarmer.setUpazillaID(c.getString(13));
                    cFarmer.setUnionID(c.getString(14));
                    cFarmer.setWardID(c.getString(15));
                    cFarmer.setVillageID(c.getString(16));
                    cFarmer.setActiveStatus(c.getString(17));
                    farmerList.add(cFarmer);
                    //System.out.println(cFarmer.getFarmerName()+" "+cFarmer.getFarmerFatherHusbandName()+" "+cFarmer.getPhoneNumber()+" "+cFarmer.getDivisionID()+cFarmer.getDistrictID()+cFarmer.getUpazillaID()+cFarmer.getUnionID()+" "+cFarmer.getFarmingCategory()+" "+cFarmer.getActiveStatus());
                }
            }while(c.moveToNext());
            db.close();
        }
        return  farmerList;
    }

    public List<FarmerInfoModel> getFarmerInfoByAreaFarmingCategoryMobileOperator(String divisionID,String districtID,String upazillaID,String unionID,String villageID,String wardID,ArrayList<String> farmingCategoryList,ArrayList<String> phoneCompanyList)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String extendedConditionFarmingCategory = " ( ";
        String extendedConditionMobileOperator = " ( ";
        if(farmingCategoryList.size()>0) {
            for (int i = 0; i < farmingCategoryList.size() - 1; i++) {
                extendedConditionFarmingCategory += COLUMN_farmingCategory + "='" + farmingCategoryList.get(i) + "' OR ";
            }
            extendedConditionFarmingCategory += COLUMN_farmingCategory + "='" + farmingCategoryList.get(farmingCategoryList.size()- 1) + "' " + " ) ";
        }
        else
        {
            extendedConditionFarmingCategory="1";
        }

        if(phoneCompanyList.size()>0) {
            for (int i = 0; i < phoneCompanyList.size() - 1; i++) {
                extendedConditionMobileOperator += COLUMN_phoneNumber + " LIKE '" + phoneCompanyList.get(i) + "%' OR ";
            }
            extendedConditionMobileOperator += COLUMN_phoneNumber + " LIKE '" + phoneCompanyList.get(phoneCompanyList.size()- 1) + "%' " + " ) ";
        }
        else{extendedConditionMobileOperator="1";}

        String query = "SELECT * FROM "+ TABLE_FARMER_INFO+" WHERE "+COLUMN_divisionID+"='"+divisionID+"' AND "+COLUMN_districtID+"='"+districtID+"' AND "+COLUMN_upazillaID+"='"+upazillaID+"' AND " +COLUMN_unionID+" LIKE '"+unionID+"' AND "+COLUMN_villageID + " LIKE '"+villageID+"' AND "+COLUMN_wardID+" LIKE '"+wardID+"' AND "+extendedConditionFarmingCategory+" AND "+extendedConditionMobileOperator;
        System.out.println(query);
        Cursor c=null;
        try
        {
            c =db.rawQuery(query,null);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }
        List<FarmerInfoModel> farmerList=new ArrayList<FarmerInfoModel>();
        if(c.getCount()>0){
            c.moveToFirst();
            do
            {
                FarmerInfoModel cFarmer = new FarmerInfoModel();
                cFarmer.setFarmerID(c.getInt(0));
                cFarmer.setFarmerName(c.getString(1));
                cFarmer.setFarmerNID(c.getString(2));
                cFarmer.setFarmerFatherHusbandName(c.getString(3));
                cFarmer.setFarmerAge(c.getString(4));
                cFarmer.setFarmingCategory(c.getString(5));
                cFarmer.setNumberOfPonds(c.getString(6));
                cFarmer.setAreaP1(c.getString(7));
                cFarmer.setAreaP2(c.getString(8));
                cFarmer.setAreaP3(c.getString(9));
                cFarmer.setPhoneNumber(c.getString(10));
                cFarmer.setDivisionID(c.getString(11));
                cFarmer.setDistrictID(c.getString(12));
                cFarmer.setUpazillaID(c.getString(13));
                cFarmer.setUnionID(c.getString(14));
                cFarmer.setWardID(c.getString(15));
                cFarmer.setVillageID(c.getString(16));
                cFarmer.setActiveStatus(c.getString(17));
                farmerList.add(cFarmer);
            }while(c.moveToNext());
            db.close();
        }
        return  farmerList;
    }


    public List<SmsTemplateModel> getAllSmsTemplate()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_SMS_TEMPLATE;
        Cursor c=null;
        try
        {
            c =db.rawQuery(query,null);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }
        List<SmsTemplateModel> smsTemplateModel=new ArrayList<SmsTemplateModel>();
        if(c.getCount()>0){
            c.moveToFirst();
            do
            {
                SmsTemplateModel cSmsTemplate = new SmsTemplateModel();
                cSmsTemplate.setSmsTemplateID(Integer.parseInt(c.getString(0)));
                cSmsTemplate.setSmsTemplateText(c.getString(1));
                smsTemplateModel.add(cSmsTemplate);
            }while(c.moveToNext());
            db.close();
        }
        return smsTemplateModel;
    }


    public ArrayList<FarmerInfoModel> getAllFarmerInfoBy()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_FARMER_INFO;
        Cursor c=null;
        try
        {
            c =db.rawQuery(query,null);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }
        ArrayList<FarmerInfoModel> farmerList=new ArrayList<FarmerInfoModel>();
        if(c.getCount()>0){
            c.moveToFirst();
            do
            {
                FarmerInfoModel cFarmer = new FarmerInfoModel();
                cFarmer.setFarmerID(c.getInt(0));
                cFarmer.setFarmerName(c.getString(1));
                cFarmer.setFarmerNID(c.getString(2));
                cFarmer.setFarmerFatherHusbandName(c.getString(3));
                cFarmer.setFarmerAge(c.getString(4));
                cFarmer.setFarmingCategory(c.getString(5));
                cFarmer.setNumberOfPonds(c.getString(6));
                cFarmer.setAreaP1(c.getString(7));
                cFarmer.setAreaP2(c.getString(8));
                cFarmer.setAreaP3(c.getString(9));
                cFarmer.setPhoneNumber(c.getString(10));
                cFarmer.setDivisionID(c.getString(11));
                cFarmer.setDistrictID(c.getString(12));
                cFarmer.setUpazillaID(c.getString(13));
                cFarmer.setUnionID(c.getString(14));
                cFarmer.setWardID(c.getString(15));
                cFarmer.setVillageID(c.getString(16));
                cFarmer.setActiveStatus(c.getString(17));
                farmerList.add(cFarmer);
            }while(c.moveToNext());
            db.close();
        }
        return farmerList;
    }

    public boolean getFarmerInfoByExists(String phoneNumber)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_FARMER_INFO+" WHERE "+COLUMN_phoneNumber+" LIKE '"+phoneNumber+"'";
        Cursor c=null;
        try
        {
            c =db.rawQuery(query,null);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }

        if(c.getCount()>0){
           return true;
        }
        return false;
    }


    public ArrayList<UnionModel> getAllUnion(String divisionID,String districtID,String upazillaID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_UNION+" WHERE "+COLUMN_divisionID+"='"+divisionID+"' AND "+COLUMN_districtID+"='"+districtID+"' AND "+COLUMN_upazillaID+"='"+upazillaID+"'";
        Cursor c=null;
        try
        {
            c =db.rawQuery(query,null);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }

        ArrayList<UnionModel> allUnion = new ArrayList<UnionModel>();
        if(c.getCount()>0){
            c.moveToFirst();
            do
            {
                UnionModel cUnion = new UnionModel();
                cUnion.setUnionID(c.getString(0));
                cUnion.setUpazillaID(c.getString(1));
                cUnion.setDistrictID(c.getString(2));
                cUnion.setDivisionID(c.getString(3));
                cUnion.setUnionName(c.getString(4));
                allUnion.add(cUnion);

            }while(c.moveToNext());
            db.close();
        }
        return allUnion;
    }


    public ArrayList<DiseaseModel> getDiseases(String condition)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM "+ TABLE_DISEASE+" WHERE "+condition;
        Cursor c=null;
        try
        {
            c =db.rawQuery(query,null);

        }
        catch (Exception e)
        {
            Log.e("ERROR", e.toString());
        }
        ArrayList<DiseaseModel> diseaseList = new ArrayList<DiseaseModel>();
        if(c.getCount()>0){
            c.moveToFirst();
            do
            {
                DiseaseModel cDisease = new DiseaseModel();
                cDisease.setDiseaseID(c.getInt(0));
                cDisease.setDiseaseName(c.getString(1));
                cDisease.setDiseaseReason(c.getString(2));
                cDisease.setDiseaseSym(c.getString(3));
                cDisease.setDiseaseVac(c.getString(4));
                cDisease.setDiseasePic(c.getString(5));
                diseaseList.add(cDisease);
            }while(c.moveToNext());
            db.close();
        }
        return diseaseList;
    }
}
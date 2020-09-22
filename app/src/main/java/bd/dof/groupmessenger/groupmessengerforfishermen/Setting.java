package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.angads25.filepicker.controller.DialogSelectionListener;
import com.github.angads25.filepicker.model.DialogConfigs;
import com.github.angads25.filepicker.model.DialogProperties;
import com.github.angads25.filepicker.view.FilePickerDialog;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static bd.dof.groupmessenger.groupmessengerforfishermen.GroupSmsTemplateSelection.mContext;
import static bd.dof.groupmessenger.groupmessengerforfishermen.R.styleable.AlertDialog;


public class Setting extends AppCompatActivity {

    TextView top_head_setting;
    TextView top_head_app_setting;
    TextView top_farming_banner_setting;

    Button upload_setting;
    Button download_setting;
    Button upload_excel;
    Context mContex;
    SystemInformationModel nSystemInfo = new SystemInformationModel();
    DbHandler db = new DbHandler(this,null,null,1);
    int uploadCount=0;
    ArrayList<FarmerInfoModel> cAllFarmer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_setting);
        mContex = this;
        final DbHandler db = new DbHandler(this,null,null,1);
        nSystemInfo = db.getSystemInfo();
        cAllFarmer = db.getFarmerInfoPendingUpdate();
        top_head_setting = (TextView) findViewById(R.id.top_head_setting);
        top_head_app_setting = (TextView) findViewById(R.id.top_head_app_setting);
        top_farming_banner_setting = (TextView) findViewById(R.id.top_farming_banner_setting);

        upload_setting = (Button) findViewById(R.id.upload_setting);
        download_setting = (Button) findViewById(R.id.download_setting);
        upload_excel = (Button) findViewById(R.id.upload_excel);
        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);
        top_head_setting.setTypeface(tf);
        top_head_app_setting.setTypeface(tf);
        top_farming_banner_setting.setTypeface(tf);
        upload_setting.setTypeface(tf);
        download_setting.setTypeface(tf);
        upload_excel.setTypeface(tf);

        System.out.println(nSystemInfo.getUserID());
        System.out.println(nSystemInfo.getUserName());
        System.out.println(nSystemInfo.getUserPhoneNumber());
        System.out.println(nSystemInfo.getUserDivisionID());
        System.out.println(nSystemInfo.getUserDistrictID());
        System.out.println(nSystemInfo.getUserUpazillaID());



        download_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        upload_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContex, "Upload Started", Toast.LENGTH_LONG).show();


                System.out.println("size: "+cAllFarmer.size());
                uploadData(cAllFarmer.get(uploadCount));


                Toast.makeText(mContex, "Upload Complete", Toast.LENGTH_LONG).show();
            }
        });


        upload_excel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogProperties properties = new DialogProperties();
                properties.selection_mode = DialogConfigs.SINGLE_MODE;
                properties.selection_type = DialogConfigs.FILE_SELECT;
                properties.root = new File(DialogConfigs.DEFAULT_DIR);
                properties.error_dir = new File(DialogConfigs.DEFAULT_DIR);
                properties.offset = new File(DialogConfigs.DEFAULT_DIR);
                properties.extensions = null;
                FilePickerDialog dialog = new FilePickerDialog(mContex,properties);
                dialog.setTitle("Select Your Excel File");

                dialog.setDialogSelectionListener(new DialogSelectionListener() {
                    @Override
                    public void onSelectedFilePaths(String[] files) {
                       for(int i=0;i<files.length;i++)
                       {
                           System.out.println("sagor "+files[i]);
                           readExcelData(files[i]);

                       }
                    }
                });

                dialog.show();

            }
        });

    }

    private void readExcelData(String filePath) {
       // Log.d(TAG, "readExcelData: Reading Excel File.");

        //decarle input file
        File inputFile = new File(filePath);

        try {
            InputStream inputStream = new FileInputStream(inputFile);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowsCount = sheet.getPhysicalNumberOfRows();
            FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
            StringBuilder sb = new StringBuilder();

            //outter loop, loops through rows
            for (int r = 1; r < rowsCount; r++) {
                Row row = sheet.getRow(r);
                int cellsCount = row.getPhysicalNumberOfCells();
                //inner loop, loops through columns
                FarmerInfoModel cFarmer = new FarmerInfoModel();

                cFarmer.setDivisionID(nSystemInfo.getUserDivisionID());
                cFarmer.setDistrictID(nSystemInfo.getUserDistrictID());
                cFarmer.setUpazillaID(nSystemInfo.getUserUpazillaID());
                cFarmer.setUpazillaID(nSystemInfo.getUserUpazillaID());
                cFarmer.setActiveStatus("1");
                cFarmer.setUploadStatus("0");
                String phoneNumber="";
                for (int c = 0; c < cellsCount; c++) {
                        String value = getCellAsString(row, c, formulaEvaluator);
                        if(c==1)
                        {
                            cFarmer.setFarmerName(value);
                        }
                        else if(c==2)
                        {
                            cFarmer.setFarmerFatherHusbandName(value);
                        }
                        else if(c==3)
                        {
                            cFarmer.setFarmingCategory(value);
                        }
                        else if(c==4)
                        {
                            if(value.length()==10 && value.substring(0,1)!="0")
                            {
                                cFarmer.setPhoneNumber("0"+value);
                            }
                            else
                            {
                                cFarmer.setPhoneNumber(value);
                            }

                        }
                        else if(c==5)
                        {
                            cFarmer.setUnionID(value);
                        }
                        //System.out.println(cellInfo);


                }

                if(cFarmer.getPhoneNumber().length()==11 && !db.getFarmerInfoByExists(cFarmer.getPhoneNumber()))
                {
                    System.out.println(cFarmer.getFarmerName()+" "+ cFarmer.getFarmingCategory()+" "+cFarmer.getDivisionID()+cFarmer.getDistrictID()+cFarmer.getUpazillaID()+cFarmer.getUnionID()+" "+cFarmer.getPhoneNumber());
                    ArrayList<UnionModel> allUnionExcel = db.getAllUnion(nSystemInfo.getUserDivisionID(),nSystemInfo.getUserDistrictID(),nSystemInfo.getUserUpazillaID());
                    boolean unionPresentFlag = false;
                    int maxUnionId=0;
                    for(UnionModel u : allUnionExcel)
                    {

                        if(Integer.parseInt(u.getUnionID())>maxUnionId)
                        {
                            maxUnionId = Integer.parseInt(u.getUnionID());
                        }
                        if(u.getUnionName().contains(cFarmer.getUnionID()) && (u.getUnionName().length()==cFarmer.getUnionID().length()))
                        {
                            unionPresentFlag = true;
                            cFarmer.setUnionID(u.getUnionID());
                            break;
                        }
                    }

                    if(!unionPresentFlag)
                    {
                        maxUnionId++;
                        UnionModel u = new UnionModel();
                        u.setDivisionID(nSystemInfo.getUserDivisionID());
                        u.setDistrictID(nSystemInfo.getUserDistrictID());
                        u.setUpazillaID(nSystemInfo.getUserUpazillaID());
                        u.setUnionID(String.valueOf(maxUnionId));
                        u.setUnionName(cFarmer.getUnionID());
                        db.insertData(u);
                        cFarmer.setUnionID(String.valueOf(maxUnionId));
                    }

                    try {
                        db.insertData(cFarmer);
                    } catch (Exception e) {
                        System.out.println("ERROR IN:" + e.toString());
                    }
                }
            }

            MainActivity.allUnion = db.getAllUnion(nSystemInfo.getUserDivisionID(),nSystemInfo.getUserDistrictID(),nSystemInfo.getUserUpazillaID());
            MainActivity.allFarmerInfo = db.getAllFarmerInfoBy();
            //Log.d(TAG, "readExcelData: STRINGBUILDER: " + sb.toString());

           // parseStringBuilder(sb);

        }catch (FileNotFoundException e) {
            //Log.e(TAG, "readExcelData: FileNotFoundException. " + e.getMessage() );
        } catch (IOException e) {
           // Log.e(TAG, "readExcelData: Error reading inputstream. " + e.getMessage() );
        }
    }

    private String getCellAsString(Row row, int c, FormulaEvaluator formulaEvaluator) {
        String value = "";
        try {
            Cell cell = row.getCell(c);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            CellValue cellValue = formulaEvaluator.evaluate(cell);

            switch (cellValue.getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    value = ""+cellValue.getBooleanValue();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    double numericValue = cellValue.getNumberValue();
                        value = ""+numericValue;
                    break;
                case Cell.CELL_TYPE_STRING:
                    value = ""+cellValue.getStringValue();
                    break;

                default:
            }


        } catch (NullPointerException e) {

            //Log.e(TAG, "getCellAsString: NullPointerException: " + e.getMessage() );
        }
        if (!(value != null && !value.isEmpty()))
        {
            value="";
        }
        return value;
    }


    String getUnionName(String unionID)
    {
        ArrayList<UnionModel> allUnionExcel = db.getAllUnion(nSystemInfo.getUserDivisionID(),nSystemInfo.getUserDistrictID(),nSystemInfo.getUserUpazillaID());
        for(UnionModel u : allUnionExcel)
        {
            if(u.getUnionID().contains(unionID) && (u.getUnionID().length()==unionID.length()))
            {
                return u.getUnionName();
            }
        }
        return "";
    }
    boolean requestFlag = true;
    void uploadData(final FarmerInfoModel cFarmer)
    {
        requestFlag = true;
        String para = "name="+Uri.encode(cFarmer.getFarmerName())+"&&";
        para += "fathers_name="+Uri.encode(cFarmer.getFarmerFatherHusbandName())+"&&";
        para += "phone_number="+(cFarmer.getPhoneNumber())+"&&";
        para += "farming_category="+Uri.encode(cFarmer.getFarmingCategory())+"&&";
        para += "division_id="+(cFarmer.getDivisionID())+"&&";
        para += "district_id="+(cFarmer.getDistrictID())+"&&";
        para += "upazilla_id="+cFarmer.getUpazillaID()+"&&";
        para += "union_id="+(cFarmer.getUnionID())+"&&";
        para += "union_name="+Uri.encode(getUnionName(cFarmer.getUnionID()))+"&&";
        para += "user_id="+nSystemInfo.getUserID();

        RequestQueue queue = Volley.newRequestQueue(this);
        final String url ="http://digital-phonebook.com/groupmessenger/insert_farmer_info.php?"+para;
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println("done");
                try {
                    JSONArray jr = new JSONArray("["+response.toString()+"]");
                    JSONObject jb = (JSONObject)jr.getJSONObject(0);
                    String status = jb.get("status").toString();
                    if(Integer.parseInt(status)==1 || Integer.parseInt(status)==2)
                    {
                        cFarmer.setUploadStatus("1");
                        db.updateData(cFarmer);
                    }

                }
                catch (Exception e) {
                    System.out.println("eroor done");
                    Toast.makeText(mContex, e.toString(), Toast.LENGTH_LONG).show();
                }
                requestFlag = false;
                            System.out.println("Response => "+response.toString());
                            if(cAllFarmer.size()>uploadCount)
                            {
                                uploadCount++;
                                uploadData(cAllFarmer.get(uploadCount));
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    });
        queue.add(jsObjRequest);

    }

}

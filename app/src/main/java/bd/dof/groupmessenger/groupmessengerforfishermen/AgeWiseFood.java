package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class AgeWiseFood extends AppCompatActivity {
    private String[] arraySpinner;
    EditText agePona;
    EditText ageWeight;
    EditText agePer;
    ListView list;
    Spinner s;
    ArrayList<String> week = new ArrayList<String>();
    ArrayList<String> fishweight = new ArrayList<String>();
    ArrayList<String> foodweight = new ArrayList<String>();
    ArrayList<String> fishQuatity = new ArrayList<String>();
    ArrayList<String> avgWeight = new ArrayList<String>();
    ArrayList<String> foodPrecent = new ArrayList<String>();
    boolean flag= true;
    ImageView ageWiseFoodShareImg;
    ImageView ageWiseFoodSaveImg;
    private File pdfFile;
    String[] allWeek = new String[] {"১ সপ্তাহ", "২য়-৪র্থ সপ্তাহ", "৫-৬ষ্ঠ সপ্তাহ", "৭-৯ম সপ্তাহ", "১০-১২ তম সপ্তাহ","১৩-১৫তম সপ্তাহ","১৬-১৯তম সপ্তাহ","২০-২৩তম সপ্তাহ","২৪-২৭ তম সপ্তাহ","২৮-৩০তম সপ্তাহ"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_age_wise_food);

        this.arraySpinner = allWeek;

        s = (Spinner) findViewById(R.id.ageWeek);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);
        agePona = (EditText) findViewById(R.id.agePona);
        ageWeight = (EditText) findViewById(R.id.ageWeight);
        agePer = (EditText) findViewById(R.id.agePer);
        list = (ListView) findViewById(R.id.scrolAgeList);
        Button agePonaSubmit = (Button) findViewById(R.id.agePonaSubmit);
        week.add("মাছের বয়স");
        fishweight.add("মাছের মোট ওজন (কেজি)");
        foodweight.add("খাদ্যের পরিমান (কেজি)");
        fishQuatity.add("");
        avgWeight.add("");
        foodPrecent.add("");


        TextView age_wise_week = (TextView) findViewById(R.id.age_wise_week);


        ageWiseFoodShareImg = (ImageView) findViewById(R.id.ageWiseFoodShareImg);
        ageWiseFoodSaveImg = (ImageView) findViewById(R.id.ageWiseFoodSaveImg);

        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);



        agePonaSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                    try {
                        if(s.getSelectedItemPosition()<10 && flag) {
                            if(s.getSelectedItemPosition()==9)
                            {
                                flag =false;
                            }
                            int ponaCount = Integer.parseInt(agePona.getText().toString());
                            int ponaWeight = Integer.parseInt(ageWeight.getText().toString());
                            int totalWeight = (ponaCount * ponaWeight) / 1000;
                            int foodPer = Integer.parseInt(agePer.getText().toString());
                            int foodWeight = (totalWeight * foodPer) / 100;

                            fishQuatity.add(pona_mojud.engToBng(agePona.getText().toString()));
                            avgWeight.add(pona_mojud.engToBng(ageWeight.getText().toString()));
                            foodPrecent.add(pona_mojud.engToBng(agePer.getText().toString()));
                            week.add(pona_mojud.engToBng(s.getSelectedItem().toString()));
                            fishweight.add(pona_mojud.engToBng(String.valueOf(totalWeight)));
                            foodweight.add(pona_mojud.engToBng(String.valueOf(foodWeight)));
                            getList();
                            if (flag)
                            {
                                s.setSelection(s.getSelectedItemPosition() + 1);
                                ageWeight.setText("");
                                agePer.setText("");
                                ageWeight.requestFocus();

                            }
                            else
                            {
                                ageWeight.setText("");
                                agePer.setText("");

                                if(getCurrentFocus()!=null) {
                                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                                    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                                }
                            }

                        }
                        else
                        {
                            if(getCurrentFocus()!=null) {
                                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                            }
                        }
                    } catch (Exception e) {
                    }

            }
        });

        ageWiseFoodSaveImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    createPdf();
                }
                catch (Exception e)
                {}
            }
        });
        ageWiseFoodShareImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");

                    String des = "";
                    for(int i=1;i<fishQuatity.size();i++)
                    {
                        des +=  "মাছের বয়স " + week.get(i)+"\n"+
                                "মাছের সংখ্যা " + fishQuatity.get(i)+"\n"+
                                "গড় ওজন (গ্রাম) " + avgWeight.get(i)+"\n"+
                                "খাদ্য হার (%) " + foodPrecent.get(i)+"\n"+
                                "মাছের মোট ওজন (কেজি) " + fishweight.get(i)+"\n"+
                                "খাদ্যের পরিমান (কেজি) " + foodweight.get(i)+"\n\n";
                    }

                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"বয়স অনুযায়ী মাছের খাদ্য প্রয়োগ");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,des);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                } catch (Exception e) {
                    //Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                    //System.out.println( e.toString());
                }
            }
        });


    }

    private void createPdf() throws FileNotFoundException {

        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
            //Log.i(TAG, "Created a new directory for PDF");
        }

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
        String date = df.format(Calendar.getInstance().getTime());
        pdfFile = new File(docsFolder.getAbsolutePath(),date+"_week_wise_food.pdf");

        OutputStream output = new FileOutputStream(pdfFile);
        Document document = new Document();
        String FONT="assets/fonts/SolaimanLipi.ttf";

        try {
            BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font f = new Font(bf, 30);

            PdfWriter.getInstance(document, output);
            document.open();

            PdfPTable table = new PdfPTable(1);
            table.setWidthPercentage(100);
            table.setHorizontalAlignment(1);
            InputStream ims = getAssets().open("3/1.png");
            Bitmap bmp = BitmapFactory.decodeStream(ims);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            Image image = Image.getInstance(stream.toByteArray());
            PdfPCell cellOne = new PdfPCell(image);
            cellOne.setBorder(Rectangle.NO_BORDER);
            table.addCell(cellOne);
            document.add(table);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            table = new PdfPTable(1);
            table.setWidthPercentage(100);
            table.setHorizontalAlignment(1);
            ims = getAssets().open("3/2.png");
            bmp = BitmapFactory.decodeStream(ims);
            stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            image = Image.getInstance(stream.toByteArray());
            cellOne = new PdfPCell(image);
            cellOne.setBorder(Rectangle.NO_BORDER);
            table.addCell(cellOne);
            document.add(table);
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            table = new PdfPTable(3);
            table.setWidthPercentage(100);
            //table.setHorizontalAlignment(1);
            ims = getAssets().open("3/6.png");
            bmp = BitmapFactory.decodeStream(ims);
            stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            image = Image.getInstance(stream.toByteArray());
            cellOne = new PdfPCell(image);
            cellOne.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
            cellOne.setPaddingTop(5);
            cellOne.setPaddingBottom(5);
            table.addCell(cellOne);


            ims = getAssets().open("3/7.png");
            bmp = BitmapFactory.decodeStream(ims);
            stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            image = Image.getInstance(stream.toByteArray());
            cellOne = new PdfPCell(image);
            cellOne.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
            cellOne.setPaddingTop(5);
            cellOne.setPaddingBottom(5);
            table.addCell(cellOne);

            ims = getAssets().open("3/8.png");
            bmp = BitmapFactory.decodeStream(ims);
            stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            image = Image.getInstance(stream.toByteArray());
            cellOne = new PdfPCell(image);
            cellOne.setPaddingTop(5);
            cellOne.setPaddingBottom(5);
            cellOne.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
            table.addCell(cellOne);

            for(int i=1;i<fishQuatity.size();i++)
            {
                int index = Arrays.asList(allWeek).indexOf(week.get(i))+9;
                String path = "3/"+index+".png";

                ims = getAssets().open(path);
                bmp = BitmapFactory.decodeStream(ims);
                stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                image = Image.getInstance(stream.toByteArray());
                cellOne = new PdfPCell(image);
                cellOne.setPaddingTop(5);
                cellOne.setPaddingBottom(5);
                cellOne.setBorder(Rectangle.BOTTOM);
                table.addCell(cellOne);

                cellOne = new PdfPCell(new Paragraph(fishweight.get(i),f));
                cellOne.setBorder(Rectangle.BOTTOM);
                cellOne.setPaddingLeft(40);
                table.addCell(cellOne);

                cellOne = new PdfPCell(new Paragraph(foodweight.get(i),f));
                cellOne.setBorder(Rectangle.BOTTOM);
                cellOne.setPaddingLeft(40);
                table.addCell(cellOne);
            }

            document.add(table);
        }catch (Exception e)
        {}


        document.close();
        previewPdf();

    }

    private void previewPdf() {

        PackageManager packageManager = getPackageManager();
        Intent testIntent = new Intent(Intent.ACTION_VIEW);
        testIntent.setType("application/pdf");
        List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size() > 0) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(pdfFile);
            intent.setDataAndType(uri, "application/pdf");

            startActivity(intent);
        }else{
            Toast.makeText(this,"Download a PDF Viewer to see the generated PDF",Toast.LENGTH_SHORT).show();
        }
    }

    void getList()
    {
        AgeRowAdapter listadapter = new AgeRowAdapter(this,week,fishweight,foodweight);
        list.setAdapter(listadapter);
    }
}

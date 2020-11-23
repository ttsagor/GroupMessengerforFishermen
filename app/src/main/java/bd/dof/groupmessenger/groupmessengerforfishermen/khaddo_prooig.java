package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Environment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.CFFFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class khaddo_prooig extends AppCompatActivity {

    TextView foodAmount;
    TextView totalWeigt;

    EditText totalFish;
    EditText avgWeight;
    EditText foodPercent;
    ImageView khaddo_prooig_share_img;
    ImageView khaddo_prooig_save_img;
    int totalFishAmount = 0;
    int avgWeightAmount = 0;
    int foodPercentAmount = 0;
    private File pdfFile;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_khaddo_prooig);


        totalFish = (EditText) findViewById(R.id.totalFish);
        avgWeight = (EditText) findViewById(R.id.avgWeight);
        foodPercent = (EditText) findViewById(R.id.foodPercent);

        foodAmount = (TextView) findViewById(R.id.foodAmount);
        totalWeigt = (TextView) findViewById(R.id.totalWeigt);


        TextView khaddo_prooig_result_food = (TextView) findViewById(R.id.khaddo_prooig_result_food);
        TextView khaddo_prooig_result_fish_weight = (TextView) findViewById(R.id.khaddo_prooig_result_fish_weight);
        Button foodPercentButton = (Button) findViewById(R.id.foodPercentButton);
        khaddo_prooig_share_img = (ImageView) findViewById(R.id.khaddo_prooig_share_img);
        khaddo_prooig_save_img = (ImageView) findViewById(R.id.khaddo_prooig_save_img);
        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);

        khaddo_prooig_result_food.setTypeface(tf);
        khaddo_prooig_result_fish_weight.setTypeface(tf);
        foodPercentButton.setTypeface(tf);
        totalWeigt.setTypeface(tf);
        foodAmount.setTypeface(tf);

        foodPercentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    totalFishAmount = Integer.parseInt(totalFish.getText().toString());
                    avgWeightAmount = Integer.parseInt(avgWeight.getText().toString());
                    foodPercentAmount = Integer.parseInt(foodPercent.getText().toString());
                    if (getCurrentFocus() != null) {
                        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    }
                    calculate();
                } catch (Exception e) {
                    totalFishAmount = 0;
                    avgWeightAmount = 0;
                    foodPercentAmount = 0;
                    calculate();
                }
            }
        });

        totalFish.addTextChangedListener(new TextWatcher() {
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
                /*try {
                    totalFishAmount = Integer.parseInt(totalFish.getText().toString());
                    calculate();
                }catch (Exception e){
                    totalFishAmount = 0;
                    calculate();
                }*/
            }
        });

        avgWeight.addTextChangedListener(new TextWatcher() {
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
               /* try {
                    avgWeightAmount = Integer.parseInt(avgWeight.getText().toString());
                    calculate();
                }catch (Exception e){
                    avgWeightAmount = 0;
                    calculate();
                }*/
            }
        });

        foodPercent.addTextChangedListener(new TextWatcher() {
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
                /*try {
                    foodPercentAmount = Integer.parseInt(foodPercent.getText().toString());
                    calculate();
                }catch (Exception e){
                    foodPercentAmount = 0;
                    calculate();
                }*/
            }
        });


        khaddo_prooig_share_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");

                    String des = "মাছের সংখ্যা : " + pona_mojud.engToBng(totalFish.getText().toString()) + "\n" +
                            "গড় ওজন (গ্রাম): " + pona_mojud.engToBng(avgWeight.getText().toString()) + "\n" +
                            "খাদ্য হার(%) : " + pona_mojud.engToBng(foodPercent.getText().toString()) + "\n" +
                            "খাদ্যের পরিমান (কেজি): " + foodAmount.getText() + "\n" +
                            "মাছের মোট ওজন (কেজি): " + totalWeigt.getText();

                    sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "মাছের খাদ্য প্রয়োগের সূত্র");
                    sharingIntent.putExtra(Intent.EXTRA_TEXT, des);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                } catch (Exception e) {
                }
            }
        });

        khaddo_prooig_save_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                        createPdf();
                    }
                    catch (Exception e) {
                    }
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    void calculate() {
        float totalWKG = (totalFishAmount * avgWeightAmount) / 1000;

        foodAmount.setText(pona_mojud.engToBng(String.valueOf((totalWKG * foodPercentAmount) / 100)));
        totalWeigt.setText(pona_mojud.engToBng(String.valueOf(totalWKG)));
    }

    private void createPdf() throws FileNotFoundException {

        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
            //Log.i(TAG, "Created a new directory for PDF");
        }

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
        String date = df.format(Calendar.getInstance().getTime());
        pdfFile = new File(docsFolder.getAbsolutePath(),date+"_khaddo_prooig.pdf");

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
            InputStream ims = getAssets().open("2/1.png");
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
            ims = getAssets().open("2/2.png");
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

            table = new PdfPTable(2);
            table.setWidthPercentage(100);
            //table.setHorizontalAlignment(1);
            ims = getAssets().open("2/3.png");
            bmp = BitmapFactory.decodeStream(ims);
            stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            image = Image.getInstance(stream.toByteArray());
            cellOne = new PdfPCell(image);
            cellOne.setBorder(Rectangle.NO_BORDER);
            table.addCell(cellOne);

            cellOne = new PdfPCell(new Paragraph(pona_mojud.engToBng(totalFish.getText().toString()),f));
            cellOne.setBorder(Rectangle.NO_BORDER);
            cellOne.setPaddingTop(-5);
            table.addCell(cellOne);

            ims = getAssets().open("2/4.png");
            bmp = BitmapFactory.decodeStream(ims);
            stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            image = Image.getInstance(stream.toByteArray());
            cellOne = new PdfPCell(image);
            cellOne.setBorder(Rectangle.NO_BORDER);
            table.addCell(cellOne);

            cellOne = new PdfPCell(new Paragraph(pona_mojud.engToBng(avgWeight.getText().toString()),f));
            cellOne.setBorder(Rectangle.NO_BORDER);
            cellOne.setPaddingTop(-5);
            table.addCell(cellOne);

            ims = getAssets().open("2/5.png");
            bmp = BitmapFactory.decodeStream(ims);
            stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            image = Image.getInstance(stream.toByteArray());
            cellOne = new PdfPCell(image);
            cellOne.setBorder(Rectangle.NO_BORDER);
            table.addCell(cellOne);
            cellOne = new PdfPCell(new Paragraph(pona_mojud.engToBng(foodPercent.getText().toString()),f));
            cellOne.setBorder(Rectangle.NO_BORDER);
            cellOne.setPaddingTop(-5);
            table.addCell(cellOne);
            document.add(table);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));


            table = new PdfPTable(2);
            table.setWidthPercentage(100);
            //table.setHorizontalAlignment(1);
            ims = getAssets().open("2/6.png");
            bmp = BitmapFactory.decodeStream(ims);
            stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            image = Image.getInstance(stream.toByteArray());
            cellOne = new PdfPCell(image);
            cellOne.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
            cellOne.setPaddingTop(5);
            cellOne.setPaddingBottom(5);
            table.addCell(cellOne);
            cellOne = new PdfPCell(new Paragraph(pona_mojud.engToBng(foodAmount.getText().toString()),f));
            cellOne.setBorder(Rectangle.BOTTOM| Rectangle.TOP);
            cellOne.setPaddingLeft(90);
            //cellOne.setPaddingTop(-10);
            table.addCell(cellOne);

            ims = getAssets().open("2/7.png");
            bmp = BitmapFactory.decodeStream(ims);
            stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            image = Image.getInstance(stream.toByteArray());
            cellOne = new PdfPCell(image);
            cellOne.setPaddingTop(5);
            cellOne.setPaddingBottom(5);
            cellOne.setBorder(Rectangle.BOTTOM);
            table.addCell(cellOne);
            cellOne = new PdfPCell(new Paragraph(pona_mojud.engToBng(totalWeigt.getText().toString()),f));

            cellOne.setBorder(Rectangle.BOTTOM );
            //cellOne.setPaddingTop(-10);
            cellOne.setPaddingLeft(90);

            table.addCell(cellOne);

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

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("khaddo_prooig Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
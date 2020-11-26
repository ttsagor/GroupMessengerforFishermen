package bd.dof.groupmessenger.groupmessengerforfishermen;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
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
import java.util.Calendar;
import java.util.List;

import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.BaboharbidiActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.ComingSoonActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.EditUserActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.LoginActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.ProfileActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.SotorkotaActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.SplashScreenActivity;

public class pona_mojud extends AppCompatActivity {

    int katlaPer = 16;
    int silverPer = 14;
    int ruiPer = 30;
    int mrigelPer = 26;
    int kalibausPer = 8;
    int putiPer = 2;
    int grasscupPer = 4;

    int ponaAreaText = 0;
    int ponaAmountText = 0;

    TextView katla;
    TextView silver;
    TextView rui;
    TextView mrigel;
    TextView kalibaus;
    TextView puti;
    TextView grasscup;
    TextView totalPona;

    EditText ponaArea;
    EditText ponaAmount;
    ImageView pona_mojud_share;
    ImageView pona_mojud_save;
    private File pdfFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pona_mojud);

        ponaArea = (EditText) findViewById(R.id.ponaArea);
        ponaAmount = (EditText) findViewById(R.id.ponaAmount);

        katla = (TextView) findViewById(R.id.katla);
        silver = (TextView) findViewById(R.id.silver);
        rui = (TextView) findViewById(R.id.rui);
        mrigel = (TextView) findViewById(R.id.mrigel);
        kalibaus = (TextView) findViewById(R.id.kalibaus);
        puti = (TextView) findViewById(R.id.puti);
        grasscup = (TextView) findViewById(R.id.grasscup);
        totalPona = (TextView) findViewById(R.id.totalPona);

        pona_mojud_share = (ImageView) findViewById(R.id.pona_mojud_share);
        pona_mojud_save = (ImageView) findViewById(R.id.pona_mojud_save);

        bottomNavigationHandler();
        AppCompatButton ponaAreaSubmit = findViewById(R.id.ponaAreaSubmit);

        TextView pona_mojud_katla = (TextView) findViewById(R.id.pona_mojud_katla);
        TextView pona_mojud_silva = (TextView) findViewById(R.id.pona_mojud_silva);
        TextView pona_mojud_rui = (TextView) findViewById(R.id.pona_mojud_rui);
        TextView pona_mojud_mrigel = (TextView) findViewById(R.id.pona_mojud_mrigel);
        TextView pona_mojug_kalibaus = (TextView) findViewById(R.id.pona_mojug_kalibaus);
        TextView pona_mojud_puti = (TextView) findViewById(R.id.pona_mojud_puti);
        TextView pona_mojud_panggas = (TextView) findViewById(R.id.pona_mojud_panggas);
        TextView pona_mojud_total = (TextView) findViewById(R.id.pona_mojud_total);


        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);


        ponaAreaSubmit.setTypeface(tf);

        pona_mojud_katla.setTypeface(tf);
        pona_mojud_silva.setTypeface(tf);
        pona_mojud_rui.setTypeface(tf);
        pona_mojud_mrigel.setTypeface(tf);
        pona_mojug_kalibaus.setTypeface(tf);
        pona_mojud_puti.setTypeface(tf);
        pona_mojud_panggas.setTypeface(tf);
        pona_mojud_total.setTypeface(tf);

        katla.setTypeface(tf);
        silver.setTypeface(tf);
        rui.setTypeface(tf);
        mrigel.setTypeface(tf);
        kalibaus.setTypeface(tf);
        puti.setTypeface(tf);
        grasscup.setTypeface(tf);
        totalPona.setTypeface(tf);
        ponaArea.setTypeface(tf);
        ponaAmount.setTypeface(tf);

        ponaAreaSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ponaAreaText = Integer.parseInt(ponaArea.getText().toString());
                    ponaAmountText = Integer.parseInt(ponaAmount.getText().toString());
                    if (getCurrentFocus() != null) {
                        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    }
                    calculate();
                } catch (Exception e) {
                    ponaAreaText = 0;
                    ponaAmountText = 0;
                    calculate();
                }
            }
        });

        pona_mojud_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    try {
                        createPdf();
                    } catch (Exception e) {
                    }
                } catch (Exception e) {
                    //Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                    //System.out.println( e.toString());
                }
            }
        });


        pona_mojud_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");

                    String des = "আয়তন(শতাংশ): " + engToBng(ponaArea.getText().toString()) + "\n" +
                            "পোনা/শতাংশ: " + engToBng(ponaAmount.getText().toString()) + "\n" +
                            "কাতলা (১৬%): " + katla.getText() + "\n" +
                            "সিলভার(১৪%): " + silver.getText() + "\n" +
                            "রুই(৩০%): " + rui.getText() + "\n" +
                            "মৃগেল(২৬%): " + mrigel.getText() + "\n" +
                            "কালিবাউস(৮%): " + kalibaus.getText() + "\n" +
                            "পুটি (২%): " + puti.getText() + "\n" +
                            "গ্রাসকার্প (৪%): " + grasscup.getText() + "\n" +
                            "মোট পোনা: " + totalPona.getText();

                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "পোনা মজুদের সূত্র");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, des);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                } catch (Exception e) {
                    //Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                    //System.out.println( e.toString());
                }
            }
        });

        ponaArea.addTextChangedListener(new TextWatcher() {
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
                    ponaAreaText = Integer.parseInt(ponaArea.getText().toString());
                    calculate();
                }catch (Exception e){
                    ponaAreaText = 0;
                    calculate();
                }*/
            }
        });

        ponaAmount.addTextChangedListener(new TextWatcher() {
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
               /* try{
                ponaAmountText = Integer.parseInt(ponaAmount.getText().toString());
                calculate();
                }catch (Exception e){
                    ponaAmountText = 0;
                    calculate();
                }*/
            }
        });

    }

    void calculate() {

        katla.setText(engToBng(String.valueOf((ponaAreaText * ponaAmountText * katlaPer) / 100)));
        silver.setText(engToBng(String.valueOf((ponaAreaText * ponaAmountText * silverPer) / 100)));
        rui.setText(engToBng(String.valueOf((ponaAreaText * ponaAmountText * ruiPer) / 100)));
        mrigel.setText(engToBng(String.valueOf((ponaAreaText * ponaAmountText * mrigelPer) / 100)));
        kalibaus.setText(engToBng(String.valueOf((ponaAreaText * ponaAmountText * kalibausPer) / 100)));
        puti.setText(engToBng(String.valueOf((ponaAreaText * ponaAmountText * putiPer) / 100)));
        grasscup.setText(engToBng(String.valueOf((ponaAreaText * ponaAmountText * grasscupPer) / 100)));
        totalPona.setText(engToBng(String.valueOf((ponaAreaText * ponaAmountText))));

    }

    private void createPdf() throws FileNotFoundException {

        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
            //Log.i(TAG, "Created a new directory for PDF");
        }

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
        String date = df.format(Calendar.getInstance().getTime());
        pdfFile = new File(docsFolder.getAbsolutePath(), date + "_pona_mojad.pdf");

        OutputStream output = new FileOutputStream(pdfFile);
        Document document = new Document();
        String FONT = "assets/fonts/SolaimanLipi.ttf";

        try {
            BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font f = new Font(bf, 30);

            PdfWriter.getInstance(document, output);
            document.open();

            PdfPTable table = new PdfPTable(1);
            table.setWidthPercentage(100);
            table.setHorizontalAlignment(1);
            InputStream ims = getAssets().open("1/1.png");
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
            ims = getAssets().open("1/2.png");
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
            ims = getAssets().open("1/3.png");
            bmp = BitmapFactory.decodeStream(ims);
            stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            image = Image.getInstance(stream.toByteArray());
            cellOne = new PdfPCell(image);
            cellOne.setBorder(Rectangle.NO_BORDER);
            table.addCell(cellOne);

            cellOne = new PdfPCell(new Paragraph(pona_mojud.engToBng(ponaArea.getText().toString()), f));
            cellOne.setBorder(Rectangle.NO_BORDER);
            cellOne.setPaddingTop(-5);
            table.addCell(cellOne);

            ims = getAssets().open("1/4.png");
            bmp = BitmapFactory.decodeStream(ims);
            stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            image = Image.getInstance(stream.toByteArray());
            cellOne = new PdfPCell(image);
            cellOne.setBorder(Rectangle.NO_BORDER);
            table.addCell(cellOne);

            cellOne = new PdfPCell(new Paragraph(pona_mojud.engToBng(ponaAmount.getText().toString()), f));
            cellOne.setBorder(Rectangle.NO_BORDER);
            cellOne.setPaddingTop(-5);
            table.addCell(cellOne);
            document.add(table);
            document.add(new Paragraph(" "));

            table = new PdfPTable(2);
            table.setWidthPercentage(100);
            //table.setHorizontalAlignment(1);
            ims = getAssets().open("1/5.png");
            bmp = BitmapFactory.decodeStream(ims);
            stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            image = Image.getInstance(stream.toByteArray());
            cellOne = new PdfPCell(image);
            cellOne.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
            cellOne.setPaddingTop(5);
            cellOne.setPaddingBottom(5);
            table.addCell(cellOne);
            cellOne = new PdfPCell(new Paragraph(pona_mojud.engToBng(katla.getText().toString()), f));
            cellOne.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
            cellOne.setPaddingLeft(-20);
            //cellOne.setPaddingTop(-10);
            table.addCell(cellOne);


            ims = getAssets().open("1/6.png");
            bmp = BitmapFactory.decodeStream(ims);
            stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            image = Image.getInstance(stream.toByteArray());
            cellOne = new PdfPCell(image);
            cellOne.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
            cellOne.setPaddingTop(5);
            cellOne.setPaddingBottom(5);
            table.addCell(cellOne);
            cellOne = new PdfPCell(new Paragraph(pona_mojud.engToBng(silver.getText().toString()), f));
            cellOne.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
            cellOne.setPaddingLeft(-20);
            //cellOne.setPaddingTop(-10);
            table.addCell(cellOne);

            ims = getAssets().open("1/7.png");
            bmp = BitmapFactory.decodeStream(ims);
            stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            image = Image.getInstance(stream.toByteArray());
            cellOne = new PdfPCell(image);
            cellOne.setPaddingTop(5);
            cellOne.setPaddingBottom(5);
            cellOne.setBorder(Rectangle.BOTTOM);
            table.addCell(cellOne);
            cellOne = new PdfPCell(new Paragraph(pona_mojud.engToBng(rui.getText().toString()), f));
            cellOne.setBorder(Rectangle.BOTTOM);
            cellOne.setPaddingLeft(-20);
            table.addCell(cellOne);

            ims = getAssets().open("1/8.png");
            bmp = BitmapFactory.decodeStream(ims);
            stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            image = Image.getInstance(stream.toByteArray());
            cellOne = new PdfPCell(image);
            cellOne.setPaddingTop(5);
            cellOne.setPaddingBottom(5);
            cellOne.setBorder(Rectangle.BOTTOM);
            table.addCell(cellOne);
            cellOne = new PdfPCell(new Paragraph(pona_mojud.engToBng(mrigel.getText().toString()), f));
            cellOne.setBorder(Rectangle.BOTTOM);
            cellOne.setPaddingLeft(-20);
            table.addCell(cellOne);

            ims = getAssets().open("1/9.png");
            bmp = BitmapFactory.decodeStream(ims);
            stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            image = Image.getInstance(stream.toByteArray());
            cellOne = new PdfPCell(image);
            cellOne.setPaddingTop(5);
            cellOne.setPaddingBottom(5);
            cellOne.setBorder(Rectangle.BOTTOM);
            table.addCell(cellOne);
            cellOne = new PdfPCell(new Paragraph(pona_mojud.engToBng(kalibaus.getText().toString()), f));
            cellOne.setBorder(Rectangle.BOTTOM);
            cellOne.setPaddingLeft(-20);
            table.addCell(cellOne);

            ims = getAssets().open("1/10.png");
            bmp = BitmapFactory.decodeStream(ims);
            stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            image = Image.getInstance(stream.toByteArray());
            cellOne = new PdfPCell(image);
            cellOne.setPaddingTop(5);
            cellOne.setPaddingBottom(5);
            cellOne.setBorder(Rectangle.BOTTOM);
            table.addCell(cellOne);
            cellOne = new PdfPCell(new Paragraph(pona_mojud.engToBng(puti.getText().toString()), f));
            cellOne.setBorder(Rectangle.BOTTOM);
            cellOne.setPaddingLeft(-20);
            table.addCell(cellOne);

            ims = getAssets().open("1/11.png");
            bmp = BitmapFactory.decodeStream(ims);
            stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            image = Image.getInstance(stream.toByteArray());
            cellOne = new PdfPCell(image);
            cellOne.setPaddingTop(5);
            cellOne.setPaddingBottom(5);
            cellOne.setBorder(Rectangle.BOTTOM);
            table.addCell(cellOne);
            cellOne = new PdfPCell(new Paragraph(pona_mojud.engToBng(grasscup.getText().toString()), f));
            cellOne.setBorder(Rectangle.BOTTOM);
            cellOne.setPaddingLeft(-20);
            table.addCell(cellOne);

            ims = getAssets().open("1/12.png");
            bmp = BitmapFactory.decodeStream(ims);
            stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            image = Image.getInstance(stream.toByteArray());
            cellOne = new PdfPCell(image);
            cellOne.setPaddingTop(5);
            cellOne.setPaddingBottom(5);
            cellOne.setBorder(Rectangle.BOTTOM);
            table.addCell(cellOne);
            cellOne = new PdfPCell(new Paragraph(pona_mojud.engToBng(totalPona.getText().toString()), f));
            cellOne.setBorder(Rectangle.BOTTOM);
            cellOne.setPaddingLeft(-20);
            table.addCell(cellOne);

            document.add(table);
        } catch (Exception e) {
        }


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
        } else {
            Toast.makeText(this, "Download a PDF Viewer to see the generated PDF", Toast.LENGTH_SHORT).show();
        }
    }

    public static String engToBng(String num) {
        num = num.replace('1', '১');
        num = num.replace('2', '২');
        num = num.replace('3', '৩');
        num = num.replace('4', '৪');
        num = num.replace('5', '৫');
        num = num.replace('6', '৬');
        num = num.replace('7', '৭');
        num = num.replace('8', '৮');
        num = num.replace('9', '৯');
        num = num.replace('0', '০');
        return num;
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
                            startActivity(new Intent(pona_mojud.this, SplashScreenActivity.class));

                        } else {
                            startActivity(new Intent(pona_mojud.this, LoginActivity.class));
                        }
                        break;

                    case R.id.menu_sotorkota:
                        startActivity(new Intent(pona_mojud.this, SotorkotaActivity.class));
                        break;
                    case R.id.menu_profile:
                        startActivity(new Intent(pona_mojud.this, ProfileActivity.class));
                        break;
                    case R.id.menu_beboharbidi:
                        startActivity(new Intent(pona_mojud.this, BaboharbidiActivity.class));
                        break;

                    case R.id.menu_somoshajomadin:
                        startActivity(new Intent(pona_mojud.this, ComingSoonActivity.class));
                        break;
                }
                return false;
            }
        });
    }
}

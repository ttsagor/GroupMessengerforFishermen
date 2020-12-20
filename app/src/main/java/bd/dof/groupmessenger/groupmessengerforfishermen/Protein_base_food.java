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
import com.google.android.material.textfield.TextInputEditText;
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
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.HomeScreenActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.LoginActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.ProfileActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.SotorkotaActivity;
import bd.dof.groupmessenger.groupmessengerforfishermen.NewDesign.SplashScreenActivity;

public class Protein_base_food extends AppCompatActivity {

    float proteinAmountText= 0;
    float totalfoodText= 0;

    TextView fishmeal;
    TextView khoil;
    TextView maze;
    TextView rice;
    TextView totalfoodSum;

    TextInputEditText proteinAmount;
    TextInputEditText totalfood;
    ImageView protein_base_share;
    ImageView protein_base_save;
    private File pdfFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_protein_base_food2);

        proteinAmount = findViewById(R.id.proteinAmount);
        totalfood =  findViewById(R.id.totalfood);

        fishmeal = (TextView) findViewById(R.id.fishmeal);
        khoil = (TextView) findViewById(R.id.khoil);
        maze = (TextView) findViewById(R.id.maze);
        rice = (TextView) findViewById(R.id.rice);
        totalfoodSum = (TextView) findViewById(R.id.totalfoodSum);

        TextView protein_base_food_fishmeal = (TextView) findViewById(R.id.protein_base_food_fishmeal);
bottomNavigationHandler();
        TextView protein_base_food_khoil = (TextView) findViewById(R.id.protein_base_food_khoil);
        TextView protein_base_food_maze = (TextView) findViewById(R.id.protein_base_food_maze);
        TextView protein_base_food_rice = (TextView) findViewById(R.id.protein_base_food_rice);
        TextView protein_base_food_total = (TextView) findViewById(R.id.protein_base_food_total);
        AppCompatButton proteinAmountButton =  findViewById(R.id.proteinAmountButton);
        protein_base_share = (ImageView) findViewById(R.id.protein_base_share);
        protein_base_save = (ImageView) findViewById(R.id.protein_base_save);
        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(this.getAssets(), fontPath);

        protein_base_food_fishmeal.setTypeface(tf);
        protein_base_food_khoil.setTypeface(tf);
        protein_base_food_maze.setTypeface(tf);
        protein_base_food_rice.setTypeface(tf);
        protein_base_food_total.setTypeface(tf);
        proteinAmountButton.setTypeface(tf);

        fishmeal.setTypeface(tf);
        khoil.setTypeface(tf);
        maze.setTypeface(tf);
        rice.setTypeface(tf);
        totalfoodSum.setTypeface(tf);

        proteinAmountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    proteinAmountText = Integer.parseInt(proteinAmount.getText().toString());
                    totalfoodText = Integer.parseInt(totalfood.getText().toString());
                    if(getCurrentFocus()!=null) {
                        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    }
                    calculate();
                }catch (Exception e){
                    proteinAmountText = 0;
                    totalfoodText = 0;
                    calculate();
                }
            }
        });
        protein_base_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    createPdf();
                }
                catch (Exception e){
                }
            }
        });

        protein_base_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");

                    String des = "প্রোটিনের কাঙ্ক্ষিত পরিমাণ (শতাংশে): " +pona_mojud.engToBng(proteinAmount.getText().toString())+"\n"+
                            "মোট খাদ্যের পরিমাণ (কেজি): "+ pona_mojud.engToBng(totalfood.getText().toString())+"\n"+
                            "ফিসমিল (কেজি): "+ fishmeal.getText().toString()+"\n"+
                            "সরিষার খইল (কেজি): " + khoil.getText()+"\n"+
                            "মেইজ (কেজি): " + maze.getText()+"\n"+
                            "রাইস ব্রান (কেজি): " + rice.getText()+"\n"+
                            "সর্বমোট খাদ্য (কেজি): " + totalfoodSum.getText();

                    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"প্রোটিনের জন্য খাদ্য প্রস্তুত");
                    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT,des);
                    startActivity(Intent.createChooser(sharingIntent, "Share via"));
                }
                catch (Exception e){
                }
            }
        });
        proteinAmount.addTextChangedListener(new TextWatcher() {
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
                    proteinAmountText = Integer.parseInt(proteinAmount.getText().toString());
                    calculate();
                }catch (Exception e){
                    proteinAmountText = 0;
                    calculate();
                }*/
            }
        });

        totalfood.addTextChangedListener(new TextWatcher() {
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
                    totalfoodText = Integer.parseInt(totalfood.getText().toString());
                    calculate();
                }catch (Exception e){
                    totalfoodText = 0;
                    calculate();
                }*/
            }
        });

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Protein_base_food.this, HomeScreenActivity.class));
            }
        });

        EditText title = findViewById(R.id.title);
        title.setText("প্রোটিনের প্রস্তুত");
    }

    void calculate()
    {
        float avgProteinSupplement = 48;
        float avgBojolSupplement = 10;
        float pearsonSquare = avgProteinSupplement - proteinAmountText;
        float diffBetweenExpProteinAvgBojol = proteinAmountText - avgBojolSupplement;
        float totalPearsonSquare = pearsonSquare + diffBetweenExpProteinAvgBojol;

        float fishmealText = totalfoodText*diffBetweenExpProteinAvgBojol/totalPearsonSquare/2;
        float khoilText = totalfoodText*diffBetweenExpProteinAvgBojol/totalPearsonSquare/2;
        float mazeText = totalfoodText*pearsonSquare/totalPearsonSquare/2;
        float riceText = totalfoodText*pearsonSquare/totalPearsonSquare/2;


        fishmeal.setText(pona_mojud.engToBng(String.valueOf(fishmealText)));
        khoil.setText(pona_mojud.engToBng(String.valueOf(khoilText)));
        maze.setText(pona_mojud.engToBng(String.valueOf(mazeText)));
        rice.setText(pona_mojud.engToBng(String.valueOf(riceText)));
        totalfoodSum.setText(pona_mojud.engToBng(String.valueOf(totalfoodText)));
    }

    private void createPdf() throws FileNotFoundException {

        File docsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
        if (!docsFolder.exists()) {
            docsFolder.mkdir();
            //Log.i(TAG, "Created a new directory for PDF");
        }

        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
        String date = df.format(Calendar.getInstance().getTime());
        pdfFile = new File(docsFolder.getAbsolutePath(),date+"_protein_base_food.pdf");

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
            InputStream ims = getAssets().open("4/1.png");
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
            ims = getAssets().open("4/2.png");
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
            ims = getAssets().open("4/3.png");
            bmp = BitmapFactory.decodeStream(ims);
            stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            image = Image.getInstance(stream.toByteArray());
            cellOne = new PdfPCell(image);
            cellOne.setBorder(Rectangle.NO_BORDER);
            table.addCell(cellOne);

            cellOne = new PdfPCell(new Paragraph(pona_mojud.engToBng(proteinAmount.getText().toString()),f));
            cellOne.setBorder(Rectangle.NO_BORDER);
            cellOne.setPaddingLeft(120);
            cellOne.setPaddingTop(-5);
            table.addCell(cellOne);

            ims = getAssets().open("4/4.png");
            bmp = BitmapFactory.decodeStream(ims);
            stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            image = Image.getInstance(stream.toByteArray());
            cellOne = new PdfPCell(image);
            cellOne.setBorder(Rectangle.NO_BORDER);
            table.addCell(cellOne);

            cellOne = new PdfPCell(new Paragraph(pona_mojud.engToBng(totalfood.getText().toString()),f));
            cellOne.setBorder(Rectangle.NO_BORDER);
            cellOne.setPaddingTop(-5);
            cellOne.setPaddingLeft(120);
            table.addCell(cellOne);
            document.add(table);
            document.add(new Paragraph(" "));

            table = new PdfPTable(2);
            table.setWidthPercentage(100);
            //table.setHorizontalAlignment(1);
            ims = getAssets().open("4/5.png");
            bmp = BitmapFactory.decodeStream(ims);
            stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            image = Image.getInstance(stream.toByteArray());
            cellOne = new PdfPCell(image);
            cellOne.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
            cellOne.setPaddingTop(5);
            cellOne.setPaddingBottom(5);
            table.addCell(cellOne);
            cellOne = new PdfPCell(new Paragraph(pona_mojud.engToBng(fishmeal.getText().toString()),f));
            cellOne.setBorder(Rectangle.BOTTOM| Rectangle.TOP);
            cellOne.setPaddingLeft(0);
            //cellOne.setPaddingTop(-10);
            table.addCell(cellOne);


            ims = getAssets().open("4/6.png");
            bmp = BitmapFactory.decodeStream(ims);
            stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            image = Image.getInstance(stream.toByteArray());
            cellOne = new PdfPCell(image);
            cellOne.setBorder(Rectangle.BOTTOM | Rectangle.TOP);
            cellOne.setPaddingTop(5);
            cellOne.setPaddingBottom(5);
            table.addCell(cellOne);
            cellOne = new PdfPCell(new Paragraph(pona_mojud.engToBng(khoil.getText().toString()),f));
            cellOne.setBorder(Rectangle.BOTTOM| Rectangle.TOP);
            cellOne.setPaddingLeft(0);
            //cellOne.setPaddingTop(-10);
            table.addCell(cellOne);

            ims = getAssets().open("4/7.png");
            bmp = BitmapFactory.decodeStream(ims);
            stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            image = Image.getInstance(stream.toByteArray());
            cellOne = new PdfPCell(image);
            cellOne.setPaddingTop(5);
            cellOne.setPaddingBottom(5);
            cellOne.setBorder(Rectangle.BOTTOM);
            table.addCell(cellOne);
            cellOne = new PdfPCell(new Paragraph(pona_mojud.engToBng(maze.getText().toString()),f));
            cellOne.setBorder(Rectangle.BOTTOM );
            cellOne.setPaddingLeft(0);
            table.addCell(cellOne);

            ims = getAssets().open("4/8.png");
            bmp = BitmapFactory.decodeStream(ims);
            stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            image = Image.getInstance(stream.toByteArray());
            cellOne = new PdfPCell(image);
            cellOne.setPaddingTop(5);
            cellOne.setPaddingBottom(5);
            cellOne.setBorder(Rectangle.BOTTOM);
            table.addCell(cellOne);
            cellOne = new PdfPCell(new Paragraph(pona_mojud.engToBng(rice.getText().toString()),f));
            cellOne.setBorder(Rectangle.BOTTOM );
            cellOne.setPaddingLeft(0);
            table.addCell(cellOne);

            ims = getAssets().open("4/9.png");
            bmp = BitmapFactory.decodeStream(ims);
            stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            image = Image.getInstance(stream.toByteArray());
            cellOne = new PdfPCell(image);
            cellOne.setPaddingTop(5);
            cellOne.setPaddingBottom(5);
            cellOne.setBorder(Rectangle.BOTTOM);
            table.addCell(cellOne);
            cellOne = new PdfPCell(new Paragraph(pona_mojud.engToBng(totalfoodSum.getText().toString()),f));
            cellOne.setBorder(Rectangle.BOTTOM );
            cellOne.setPaddingLeft(0);
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
    private void bottomNavigationHandler() {

        SharedPreferences pref = this.getApplicationContext().getSharedPreferences("MyPref", 0);
        final String  log = pref.getString("log", "");
        final SharedPreferences.Editor editor   = pref.edit();
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
                            startActivity(new Intent(Protein_base_food.this, SplashScreenActivity.class));

                        } else {
                            startActivity(new Intent(Protein_base_food.this, LoginActivity.class));
                        }
                        break;

                    case R.id.menu_sotorkota:
                        startActivity(new Intent(Protein_base_food.this, SotorkotaActivity.class));
                        break;
                    case R.id.menu_profile:
                        startActivity(new Intent(Protein_base_food.this, ProfileActivity.class));
                        break;
                    case R.id.menu_beboharbidi:
                        startActivity(new Intent(Protein_base_food.this, BaboharbidiActivity.class));
                        break;

                    case R.id.menu_somoshajomadin:
                        startActivity(new Intent(Protein_base_food.this, ComingSoonActivity.class));
                        break;
                }
                return false;
            }
        });
    }
}

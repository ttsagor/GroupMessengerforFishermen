package bd.dof.groupmessenger.groupmessengerforfishermen;
import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sagor on 3/21/2016.
 */
public class DiseaseListAdapter extends BaseAdapter implements ListAdapter {

    private Context context;
    ArrayList<Integer> id = new  ArrayList<Integer>();
    public static boolean animationFlag = true;
    LayoutInflater inflater;
    ArrayList<DiseaseModel> allDisease = new ArrayList<DiseaseModel>();
    //change made

    public DiseaseListAdapter(Context context,ArrayList<DiseaseModel> allDisease) {
        this.allDisease = allDisease;
        this.context = context;
    }
    private static class rowHolder{
        public Button diseaseName;
    }

    @Override
    public int getCount() {
        return allDisease.size();
    }

    @Override
    public Object getItem(int pos) {
        return allDisease.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;
        //just return 0 if your list items do not have an Id variable.
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        rowHolder holder = new rowHolder();
        if (view == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(R.layout.diseaselist, null);
            holder.diseaseName  = (Button) view.findViewById(R.id.diseaseName);
            view.setTag(holder);
        }
        else
        {
            holder = (rowHolder) view.getTag();
        }

        String fontPath = "fonts/SolaimanLipi.ttf";
        Typeface tf;
        tf = Typeface.createFromAsset(context.getAssets(), fontPath);
        holder.diseaseName.setTypeface(tf);

        holder.diseaseName.setText(allDisease.get(position).getDiseaseName());

        holder.diseaseName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( context ,diseaseDetails.class);
                i.putExtra("diseaseID",allDisease.get(position).getDiseaseID());
                context.startActivity(i);
            }
        });

        return view;
    }

}
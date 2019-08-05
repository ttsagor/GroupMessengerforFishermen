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
public class AgeRowAdapter extends BaseAdapter implements ListAdapter {

    private Context context;
    LayoutInflater inflater;
    ArrayList<String> allagerowweek = new ArrayList<String>();
    ArrayList<String> allagerowweight = new ArrayList<String>();
    ArrayList<String> allFagerowfoodweight = new ArrayList<String>();


    public AgeRowAdapter(Context context,ArrayList<String> allagerowweek,ArrayList<String> allagerowweight,ArrayList<String> allFagerowfoodweight) {
        this.allagerowweek = allagerowweek;
        this.allagerowweight = allagerowweight;
        this.allFagerowfoodweight = allFagerowfoodweight;
        this.context = context;
    }
    private static class rowHolder{
        public TextView agerowweek;
        public TextView agerowweight;
        public TextView agerowfoodweight;
    }

    @Override
    public int getCount() {
        return allagerowweek.size();
    }

    @Override
    public Object getItem(int pos) {
        return allagerowweek.get(pos);
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
            view = inflater.inflate(R.layout.age_wise_row, null);

            holder.agerowweek  = (TextView) view.findViewById(R.id.agerowweek);
            holder.agerowweight  = (TextView) view.findViewById(R.id.agerowweight);
            holder.agerowfoodweight  = (TextView) view.findViewById(R.id.agerowfoodweight);
            String fontPath = "fonts/SolaimanLipi.ttf";
            Typeface tf;
            tf = Typeface.createFromAsset(context.getAssets(), fontPath);

            holder.agerowweek.setTypeface(tf);
            holder.agerowweight.setTypeface(tf);
            holder.agerowfoodweight.setTypeface(tf);
            view.setTag(holder);
        }
        else
        {
            holder = (rowHolder) view.getTag();
        }

        holder.agerowweek.setText(allagerowweek.get(position));
        holder.agerowweight.setText(allagerowweight.get(position));
        holder.agerowfoodweight.setText(allFagerowfoodweight.get(position));
        return view;
    }
}
package bd.dof.groupmessenger.groupmessengerforfishermen;

/**
 * Created by Sagor on 7/1/2016.
 */



       import android.content.Context;
        import android.content.Intent;
       import android.content.res.ColorStateList;
       import android.graphics.Color;
       import android.graphics.Typeface;
       import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.BaseAdapter;
        import android.widget.Button;
        import android.widget.CheckBox;
       import android.widget.ImageButton;
       import android.widget.LinearLayout;
        import android.widget.ListAdapter;
        import android.widget.RelativeLayout;
        import android.widget.TextView;


        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.Map;

/**
 * Created by sagor on 3/21/2016.
 */
public class SmsTemplateListAdaptar extends BaseAdapter implements ListAdapter {
    private Context context;
    ArrayList<String> text = new  ArrayList<String>();
    LayoutInflater inflater;
    //change made

    public SmsTemplateListAdaptar(ArrayList<String> text,Context context) {
        this.text = text;
        this.context = context;
    }
    private static class rowHolder{
        public TextView SmsText;
        public LinearLayout textHolderLayout;
        public Button sendButton;
        //public ImageButton detailsButton;
    }

    @Override
    public int getCount() {
        return text.size();
    }

    @Override
    public Object getItem(int pos) {
        return text.get(pos);
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
            view = inflater.inflate(R.layout.sms_template_list_item, null);
            holder.SmsText  = (TextView) view.findViewById(R.id.GroupSmsTemplateSelectionSignleItem);

            String fontPath = "fonts/SolaimanLipi.ttf";
            Typeface tf;
            tf = Typeface.createFromAsset(context.getAssets(), fontPath);
            holder.SmsText.setTypeface(tf);

            holder.textHolderLayout  = (LinearLayout) view.findViewById(R.id.groupsmstextviewholderlayout);
            holder.sendButton  = (Button) view.findViewById(R.id.templateselectionsend);
           // holder.detailsButton  = (ImageButton) view.findViewById(R.id.templateselectiondetails);
            holder.sendButton.setTypeface(tf);
            view.setTag(holder);
        }
        else
        {
            holder = (rowHolder) view.getTag();
        }

        if(text.get(position).length()>70)
        {
            holder.SmsText.setText(text.get(position).substring(0,70)+"....");
        }
        else
        {
            holder.SmsText.setText(text.get(position));
        }

        final TextView tv = holder.SmsText;
        final LinearLayout ll = holder.textHolderLayout;
        ll.setMinimumHeight(0);
        holder.SmsText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(text.get(position).length()>70) {
                    ll.setMinimumHeight((text.get(position).length() / 30) * 80);
                    tv.setText(text.get(position));
                }
                //tv.setTextColor(Color.RED);
                //GroupSmsTemplateSelection.nxtActivity(text.get(position));
            }
        });

       /* holder.detailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text.get(position).length()>70) {
                    ll.setMinimumHeight((text.get(position).length() / 30) * 80);
                    tv.setText(text.get(position));
                }
                //tv.setTextColor(Color.RED);
                //GroupSmsTemplateSelection.nxtActivity(text.get(position));
            }
        });*/

        holder.sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //ll.setMinimumHeight((text.get(position).length()/30)*80);
                //tv.setText(text.get(position));
                //tv.setTextColor(Color.RED);
                GroupSmsTemplateSelection.nxtActivity(text.get(position));
            }
        });
        return view;
    }


}

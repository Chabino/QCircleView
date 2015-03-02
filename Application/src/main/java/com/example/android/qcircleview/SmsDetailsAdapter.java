package com.example.android.qcircleview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Chabino on 16/02/2015.
 */
public class SmsDetailsAdapter extends ArrayAdapter<String> {
    private List<String> detailsList;
    private Context context;

    public SmsDetailsAdapter(List<String> detailsList, Context ctx) {
        super(ctx, R.layout.row_layout, detailsList);
        this.detailsList = detailsList;
        this.context = ctx;
    }

    public int getCount() {
        return detailsList.size();
    }

    public String getItem(int position) {
        return detailsList.get(position);
    }

    public long getItemId(int position) {
        return detailsList.get(position).hashCode();
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        StringHolder holder = new StringHolder();

        // First let's verify the convertView is not null
        if (convertView == null) {
            // This a new view we inflate the new layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.row_layout, null);
            // Now we can fill the layout with the right values
            TextView tv = (TextView) v.findViewById(R.id.name);

            holder.stringNameView = tv;

            v.setTag(holder);
            /*boolean trouve =false;
            int val=0;
            for(val=0;val<SmsDetails.listClone.size();val++){
                if(SmsDetails.listClone.get(val).equals(String.valueOf(position))){
                    v.setBackgroundResource(R.drawable.listview_selector_even);
                    trouve=true;
                }
            }
            if(!trouve){
                v.setBackgroundResource(R.drawable.listview_selector_odd);
            }


            if ( position % 2 == 0)
                v.setBackgroundResource(R.drawable.listview_selector_even);
            else
                v.setBackgroundResource(R.drawable.listview_selector_odd);*/
        } else
            holder = (StringHolder) v.getTag();

        String p = detailsList.get(position);
        if (p.contains("====you====")) {
            v.setBackgroundResource(R.drawable.listview_selector_even);
        } else {
            v.setBackgroundResource(R.drawable.listview_selector_odd);
        }
        holder.stringNameView.setText(p);

        return v;
    }

    private static class StringHolder {
        public TextView stringNameView;
    }
}

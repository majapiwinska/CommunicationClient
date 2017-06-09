package com.example.snapchat.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.maja.snapchat.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maja on 04.06.17.
 */

public class CheckboxAdapter extends ArrayAdapter<String> {
    private LayoutInflater mInflater;


    private List<String>  mStrings;
    private TypedArray mIcons;
    private int mViewResourceId;
    private ArrayList<String> selectedStrings = new ArrayList<String>();

    public CheckboxAdapter(Context ctx, int viewResourceId, List<String> strings){
        super(ctx,viewResourceId,strings);

        mInflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mStrings = strings;

        mViewResourceId = viewResourceId;
    }

    public int getCount(){
        return mStrings.size();
    }

    public String getItem(int position){
        return mStrings.get(position);
    }

    public long getItemId(int position){
        return 0;
    }

    public ArrayList<String> getSelectedString(){
        return selectedStrings;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        convertView = mInflater.inflate(mViewResourceId, null);

        final CheckBox tv = (CheckBox)convertView.findViewById(R.id.checkBox_sendSnapTo);
        tv.setText(mStrings.get(position));
        tv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    selectedStrings.add(tv.getText().toString());
                }else{
                    selectedStrings.remove(tv.getText().toString());
                }

            }
        });
        return convertView;
    }
}
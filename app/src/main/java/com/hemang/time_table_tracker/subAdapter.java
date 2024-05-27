package com.hemang.time_table_tracker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class subAdapter extends ArrayAdapter
{
    ArrayList<subModel> subAdapterArrayList = new ArrayList<subModel>();

    Context myContext;


    public subAdapter(Context myContext,int resource,ArrayList<subModel> objects)
    {
        super(myContext,resource,objects);
        this.subAdapterArrayList = objects;
        this.myContext = myContext;
    }

    @Override
    public int getCount() { return super.getCount(); }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
//        return super.getView(position, convertView, parent);
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.sub_list,null);

        subModel sm = subAdapterArrayList.get(position);

        TextView txt1 = convertView.findViewById(R.id.sub_name);

        txt1.setText("Name :"+sm.getSubName());

        return convertView;
    }
}

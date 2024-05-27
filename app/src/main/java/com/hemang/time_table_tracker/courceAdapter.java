package com.hemang.time_table_tracker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class courceAdapter extends ArrayAdapter
{
    ArrayList<courceModel> courceModelArrayList = new ArrayList<>();

    Context myContext;

    public courceAdapter(Context myContext,int resource,ArrayList<courceModel> objects)
    {
        super(myContext,resource,objects);
        this.courceModelArrayList = objects;
        this.myContext =  myContext;
    }

    @Override
    public int getCount() { return super.getCount(); }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
//        return super.getView(position, convertView, parent);
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.cource_list,null);

        courceModel cm = courceModelArrayList.get(position);

        TextView txt1 = convertView.findViewById(R.id.cource);
        TextView txt2 = convertView.findViewById(R.id.department);
        TextView txt3 = convertView.findViewById(R.id.semester);

        txt1.setText("Cource : "+cm.cource_name);
        txt2.setText("Department : "+cm.department);
        txt3.setText("Semester : "+cm.semester);

        return convertView;
    }
}

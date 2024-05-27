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

public class studentAdapter extends ArrayAdapter
{

    ArrayList<studentModel> studentListArrayList = new ArrayList<>();

    Context myContext;

    public studentAdapter(Context mycontext,int resource,ArrayList<studentModel> objects)
    {
        super(mycontext,resource,objects);
        this.studentListArrayList = objects;
        this.myContext = mycontext;
    }

    @Override
    public int getCount() { return super.getCount(); }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.student_list,null);
//        TextView txt1 = convertView.findViewById(R.id.txtStdId);
        TextView txt1 = convertView.findViewById(R.id.std_name);
        TextView txt2 = convertView.findViewById(R.id.std_en);
        TextView txt3 = convertView.findViewById(R.id.std_course);

        studentModel sm = studentListArrayList.get(position);

//        txt1.setText("ID :"+studentModel.getStdId());
        txt1.setText("Name : "+sm.getStd_Name());
        txt2.setText("En no : "+sm.getRoll_no());
        txt3.setText("Course : "+sm.getCourse());

        return  convertView;

    }
}

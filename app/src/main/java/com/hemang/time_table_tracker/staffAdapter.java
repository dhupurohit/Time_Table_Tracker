package com.hemang.time_table_tracker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class staffAdapter extends ArrayAdapter
{
    ArrayList<String> staffListArrayListS = new ArrayList<>();
    ArrayList<staffModel> staffListArrayList = new ArrayList<>();

    Context myContext;

    public staffAdapter(Context myContext,int resource,ArrayList<staffModel> objects)
    {
        super(myContext,resource,objects);
        this.staffListArrayList = objects;
        this.myContext =  myContext;
    }

//    public staffAdapter(Context myContext,int resource,ArrayList<String> objects)
//    {
//        super(myContext,resource,objects);
//        this.staffListArrayListS = objects;
//        this.myContext =  myContext;
//    }

    @Override
    public int getCount() { return super.getCount(); }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
//        return super.getView(position, convertView, parent);
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.staff_list,null);
        TextView txt1=convertView.findViewById(R.id.staff_name);
        TextView txt2=convertView.findViewById(R.id.staff_email);

        staffModel sm = staffListArrayList.get(position);
//        txt1.setText("ID: " + sm.getId());
        txt1.setText("Name: " + sm.getUsername());
        txt2.setText("Email: " + sm.getEmail());
//        txt4.setText("First Name: " + sm.getFirstName());
//        txt5.setText("Last Name: " + sm.getLastName());


        return  convertView;


    }
}

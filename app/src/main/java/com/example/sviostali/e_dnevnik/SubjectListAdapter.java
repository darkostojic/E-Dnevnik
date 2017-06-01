package com.example.sviostali.e_dnevnik;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;


public class SubjectListAdapter extends BaseAdapter {

    public ArrayList<SubjectList> list;
    public Context context;

    public SubjectListAdapter(Context c){
        context = c;
        Resources res = context.getResources();
        String[] subjects = res.getStringArray(R.array.subjects);

        list = new ArrayList<SubjectList>();


        for(int i = 0;i < subjects.length;i++){
            list.add(new SubjectList(subjects[i]));
        }

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = layoutInflater.inflate(R.layout.subject_list, viewGroup, false);

        TextView subject = (TextView) row.findViewById(R.id.tvSubject);
        final CheckBox isMarked = (CheckBox) row.findViewById(R.id.cbIsMarked);

        SubjectList temp = list.get(position);

        subject.setText(temp.subject);
        return row;
    }




}

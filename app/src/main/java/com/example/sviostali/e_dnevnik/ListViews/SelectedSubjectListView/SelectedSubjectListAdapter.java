package com.example.sviostali.e_dnevnik.ListViews.SelectedSubjectListView;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sviostali.e_dnevnik.R;
import com.example.sviostali.e_dnevnik.sugarclasses.subjects;

import java.util.List;

public class SelectedSubjectListAdapter extends BaseAdapter {


    public subjects sub;
    public List<subjects> list;
    public Context context;




    public SelectedSubjectListAdapter(Context c) {
        this.context = c;
        list = subjects.listAll(subjects.class);
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
        View row = layoutInflater.inflate(R.layout.selected_subject_list, viewGroup, false);

        TextView subject = (TextView) row.findViewById(R.id.tvSelectedSubject);

        sub = list.get(position);
        subject.setText(sub.getName());


        return row;
    }
}

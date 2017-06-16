package com.example.sviostali.e_dnevnik.ListViews.SelectedStudentsListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sviostali.e_dnevnik.R;
import com.example.sviostali.e_dnevnik.sugarclasses.studentsubject;
import com.example.sviostali.e_dnevnik.sugarclasses.subjects;
import com.example.sviostali.e_dnevnik.sugarclasses.usersugar;

import java.util.List;


public class SelectedStudentsAdapter extends BaseAdapter {
    public usersugar student;
    public subjects subject;
    public studentsubject stud_sub;
    public List<studentsubject> list;

    Context context;
    public long id;

    public SelectedStudentsAdapter(Context c, long id) {
        this.context = c;
        this.id = id;
        subject = subjects.findById(subjects.class,id);
        list = subject.getStudentSubjects();
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

        TextView tvStudent = (TextView) row.findViewById(R.id.tvSelectedSubject);
        stud_sub = list.get(position);
        student = stud_sub.getUser();
        tvStudent.setText(student.getLogin());


        return row;
    }
}

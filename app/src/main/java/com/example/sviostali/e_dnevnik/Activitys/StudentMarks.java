package com.example.sviostali.e_dnevnik.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sviostali.e_dnevnik.ListViews.MarksListView.MarksAdapter2;
import com.example.sviostali.e_dnevnik.R;
import com.example.sviostali.e_dnevnik.sugarclasses.marks;
import com.example.sviostali.e_dnevnik.sugarclasses.studentsubject;

import java.util.List;

public class StudentMarks extends AppCompatActivity {

    public TextView tvFinalMark, tvAbsence;
    public ListView lvMarks;
    public studentsubject stud_sub;
    public long id;
    public MarksAdapter2 Madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_marks);

        Bundle s = getIntent().getExtras();

        id = s.getLong("id");

        stud_sub = studentsubject.findById(studentsubject.class, id);


        tvFinalMark = (TextView) findViewById(R.id.tvSMfinalmark);
        tvAbsence = (TextView) findViewById(R.id.tvSMabsence);
        lvMarks = (ListView) findViewById(R.id.lvSMmarks);
        Madapter = new MarksAdapter2(this, id);
        lvMarks.setAdapter(Madapter);

        if(stud_sub.getFinalmark()!=0) {
            tvFinalMark.setText("Zaključna ocjena: "+ stud_sub.getFinalmark());
        }else{
            tvFinalMark.setText("Nije zaljučeno");
        }
        tvAbsence.setText("Broj izostanaka: "+ stud_sub.getAbsence());




    }


}

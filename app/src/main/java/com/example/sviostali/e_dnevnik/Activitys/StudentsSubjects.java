package com.example.sviostali.e_dnevnik.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sviostali.e_dnevnik.ListViews.StudentsSubjectList.StudentsSubjectAdapter;
import com.example.sviostali.e_dnevnik.R;

public class StudentsSubjects extends AppCompatActivity {

    public ListView lvStudentsSubjets;
    public int id;
    public StudentsSubjectAdapter SSadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students_subjects);

        Bundle s = getIntent().getExtras();
        id = s.getInt("id");
        lvStudentsSubjets = (ListView) findViewById(R.id.lvStudentsSubjects);
        SSadapter = new StudentsSubjectAdapter(this,id);
        lvStudentsSubjets.setAdapter(SSadapter);




    }
}

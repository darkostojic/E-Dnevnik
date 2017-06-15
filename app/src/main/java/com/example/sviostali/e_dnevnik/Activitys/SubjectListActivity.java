package com.example.sviostali.e_dnevnik.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.sviostali.e_dnevnik.R;
import com.example.sviostali.e_dnevnik.ListViews.SubjectListView.SubjectList;
import com.example.sviostali.e_dnevnik.ListViews.SubjectListView.SubjectListAdapter;
import com.orm.SugarContext;
import com.example.sviostali.e_dnevnik.sugarclasses.subjects;
import com.example.sviostali.e_dnevnik.sugarclasses.usersugar;

public class SubjectListActivity extends AppCompatActivity {

    public ListView lvSubjects;
    public Button bAccept;
    public SubjectListAdapter subjectListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_subject_list);

        subjectListAdapter = new SubjectListAdapter(getApplicationContext());

        bAccept = (Button) findViewById(R.id.bAccept);
        lvSubjects = (ListView) findViewById(R.id.lvSubjects);
        lvSubjects.setAdapter(subjectListAdapter);



        bAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle s = getIntent().getExtras();

                int id = s.getInt("id");
                subjects sub;
                usersugar user = usersugar.findById(usersugar.class, id);
                for (SubjectList sl : subjectListAdapter.getSubjectList()){
                    if(sl.isMarked()){

                        sub = new subjects(sl.getSubject(), user);
                        sub.save();
                    }

                }

                finish();

            }
        });



    }
}

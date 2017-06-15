package com.example.sviostali.e_dnevnik.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sviostali.e_dnevnik.ListViews.SelectedSubjectListView.SelectedSubjectListAdapter;
import com.example.sviostali.e_dnevnik.R;
import com.example.sviostali.e_dnevnik.sugarclasses.subjects;
import com.orm.SugarContext;

public class SelectedSubjectsActivity extends AppCompatActivity {

    public ListView lvSSubjects;
    public SelectedSubjectListAdapter SSAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_selected_subjects);

        SSAdapter = new SelectedSubjectListAdapter(getApplicationContext());

        lvSSubjects = (ListView) findViewById(R.id.lvSelectedSubjects);
        lvSSubjects.setAdapter(SSAdapter);

        

    }
}

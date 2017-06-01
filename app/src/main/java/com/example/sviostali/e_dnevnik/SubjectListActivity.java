package com.example.sviostali.e_dnevnik;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

public class SubjectListActivity extends AppCompatActivity {

    public ListView lvSubjects;
    public Button bAccept;
    public SubjectListAdapter subjectListAdapter;
    public DBHelper dbMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);

        subjectListAdapter = new SubjectListAdapter(getApplicationContext());

        bAccept = (Button) findViewById(R.id.bAccept);
        lvSubjects = (ListView) findViewById(R.id.lvSubjects);
        lvSubjects.setAdapter(subjectListAdapter);
        dbMain = new DBHelper(getApplicationContext());
        bAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0;i < subjectListAdapter.getCount();i++){

                    View view = lvSubjects.getAdapter().getView(i,null,lvSubjects);
                    String s = ((TextView) view.findViewById(R.id.tvSubject)).getText().toString();
                    Boolean b = ((CheckBox) view.findViewById(R.id.cbIsMarked)).isChecked();

                }
            }
        });



    }
}

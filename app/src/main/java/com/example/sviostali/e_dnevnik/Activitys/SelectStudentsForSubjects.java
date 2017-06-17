package com.example.sviostali.e_dnevnik.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sviostali.e_dnevnik.ListViews.UsersListAdapter.UserList;
import com.example.sviostali.e_dnevnik.ListViews.UsersListAdapter.UserListAdapter;
import com.example.sviostali.e_dnevnik.R;
import com.example.sviostali.e_dnevnik.sugarclasses.studentsubject;
import com.example.sviostali.e_dnevnik.sugarclasses.subjects;
import com.example.sviostali.e_dnevnik.sugarclasses.usersugar;
import com.orm.SugarContext;

public class SelectStudentsForSubjects extends AppCompatActivity {

    public Button bAccept;
    public ListView lvStudents;
    public UserListAdapter userListAdapter;
    long id;
    subjects sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_subject_list); // Koristim isti layout za ovo jer nema potrebe za novim

        userListAdapter = new UserListAdapter(getApplicationContext());

        bAccept = (Button) findViewById(R.id.bAccept);
        lvStudents = (ListView) findViewById(R.id.lvSubjects); // zove se lvStudents radi olaksanja pri pisanju
        lvStudents.setAdapter(userListAdapter);

        Bundle s = getIntent().getExtras();
        id = s.getLong("id");
        sub = subjects.findById(subjects.class,id);

        bAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                usersugar user = usersugar.findById(usersugar.class, id);

                studentsubject stsub;
                for(int i=0;i<userListAdapter.getUserList().size();i++){

                    if(userListAdapter.getUserList().get(i).isMarked()){
                        stsub = new studentsubject(userListAdapter.getSUser(i), sub, 0);
                        stsub.save();
                    }
                }


            finish();
            }
        });
    }
}

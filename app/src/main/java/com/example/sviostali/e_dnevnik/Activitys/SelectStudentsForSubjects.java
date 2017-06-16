package com.example.sviostali.e_dnevnik.Activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_subject_list); // Koristim isti layout za ovo jer nema potrebe za novim

        userListAdapter = new UserListAdapter(getApplicationContext());

        bAccept = (Button) findViewById(R.id.bAccept);
        lvStudents = (ListView) findViewById(R.id.lvSubjects); // zove se lvStudents radi olaksanja pri pisanju
        lvStudents.setAdapter(userListAdapter);


/*
        bAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle s = getIntent().getExtras();
                int id = s.getInt("id");
                usersugar user = usersugar.findById(usersugar.class, id);
                subjects sub;
                studentsubject stsub;
                for (UserList u1 : userListAdapter.getUserList()){
                    if (u1.isMarked()){
                        stsub = new studentsubject(u1.getUser(), )); //korisnik i u drugi dio nakon zareza trebalo bi proslijedit predmet
                        stsub.save();
                    }

                }

            }
        });*/
    }
}

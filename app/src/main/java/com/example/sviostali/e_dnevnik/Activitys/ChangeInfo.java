package com.example.sviostali.e_dnevnik.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sviostali.e_dnevnik.R;
import com.example.sviostali.e_dnevnik.sugarclasses.usersugar;
import com.orm.SugarContext;

public class ChangeInfo extends AppCompatActivity {

    public Button change;
    public EditText etCIname, etCIlastname, etCIDate, etCIAvatar;
    public int id;
    public usersugar user;
    public String FName, LName, Date, Avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_change_info);

        Bundle s = getIntent().getExtras();
        id = s.getInt("id");

        etCIname = (EditText) findViewById(R.id.etCIname);
        etCIlastname = (EditText) findViewById(R.id.etCILastname);
        etCIDate = (EditText) findViewById(R.id.etCIDate);
        etCIAvatar = (EditText) findViewById(R.id.etCIAvatar);

        user=usersugar.findById(usersugar.class, id);

        change = (Button) findViewById(R.id.btnCIchange);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FName = etCIname.getText().toString();
                LName = etCIlastname.getText().toString();
                Date = etCIDate.getText().toString();
                Avatar = etCIAvatar.getText().toString();
                if(FName.equals("")&&LName.equals("")&&Date.equals("")){
                    Toast.makeText(ChangeInfo.this, "Unesite sve podatke", Toast.LENGTH_SHORT).show();
                }else {
                    user.setFirstname(FName);
                    user.setLastname(LName);
                    user.setBirthdate(Date);
                    if(!Avatar.equals(""))
                        user.setAvatar(Avatar);
                    user.save();
                    Intent i = new Intent(ChangeInfo.this, UserInfo.class);
                    i.putExtra("id",id);
                    startActivity(i);
                    finish();

                }
            }
        });





    }
}

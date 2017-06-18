package com.example.sviostali.e_dnevnik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sviostali.e_dnevnik.Activitys.Login;
import com.example.sviostali.e_dnevnik.Activitys.UserInfo;
import com.example.sviostali.e_dnevnik.sugarclasses.usersugar;
import com.orm.SugarContext;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_login);

        Intent i = new Intent(MainActivity.this, Login.class);
        startActivity(i);
        finish();


    }


}

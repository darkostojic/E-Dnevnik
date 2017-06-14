package com.example.sviostali.e_dnevnik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sviostali.e_dnevnik.sugarclasses.usersugar;
import com.orm.SugarContext;


public class MainActivity extends AppCompatActivity {


    public TextView tvData;
    public Button but, button2;
    public GetUsersFromJSON g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_main);
        but = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        tvData = (TextView) findViewById(R.id.tvData);
        g = new GetUsersFromJSON(this);


        but.setText("Test");

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                g.getData();
            }
        });
        but.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                usersugar user2 = usersugar.findById(usersugar.class, 1);
                Toast.makeText(MainActivity.this, user2.getLogin(), Toast.LENGTH_SHORT).show();


                return false;
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);
            }
        });


    }

    /*
    private void showData(){
        Cursor getDataNow = dbMain.getUsers();
        if(getDataNow.getCount() == 0){
            Toast.makeText(getApplicationContext(), "No data so far.", Toast.LENGTH_SHORT).show();
            return;
        }else{
            getDataNow.moveToFirst();
            String a = getDataNow.getString(getDataNow.getColumnIndex("login"));
            tvData.setText(a);
        }
    }
    */
}

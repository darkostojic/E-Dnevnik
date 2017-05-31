package com.example.sviostali.e_dnevnik;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    public DBHelper dbMain;
    public TextView tvData;
    public Button but, button2;
    public GetUsersFromJSON g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        tvData = (TextView) findViewById(R.id.tvData);
        dbMain = new DBHelper(this);
        but.setText("Test");
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                g = new GetUsersFromJSON(MainActivity.this);

            }
        });

        but.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showData();
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

}

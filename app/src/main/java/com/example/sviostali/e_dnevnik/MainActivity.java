package com.example.sviostali.e_dnevnik;

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
    public Button but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        but = (Button) findViewById(R.id.button);
        tvData = (TextView) findViewById(R.id.tvData);
        dbMain = new DBHelper(this);


        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertUserData();
            }
        });

        but.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showData();
                return false;
            }
        });


        GetStudentsFromURL gsfu = new GetStudentsFromURL();
        gsfu.execute();



    }

    private void insertUserData(){
        boolean insert = dbMain.insertUserData("test1", "test1", "test1", 1);
        if(insert){
            Toast.makeText(getApplicationContext(), "uspjeh", Toast.LENGTH_SHORT).show();
            Log.i("insertData", "Success!");
        }else{
            Log.i("insertData", "Failed!");
        }
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

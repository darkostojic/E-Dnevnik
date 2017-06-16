package com.example.sviostali.e_dnevnik.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sviostali.e_dnevnik.R;

// Jedan predmet, 2 buttona a drugi button otvara odabir ucenika


public class SelectedSubject extends AppCompatActivity {
    public TextView tvSSImePredmeta;
    public Button btnSS1, btnSS2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_subject);

        tvSSImePredmeta = (TextView) findViewById(R.id.tvSSImePredmeta);
        btnSS1 = (Button) findViewById(R.id.btnSS1);
        btnSS2 = (Button) findViewById(R.id.btnSS2);
        btnSS2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SelectedSubject.this, SelectStudentsForSubjects.class);
                startActivity(i);
            }
        });
    }
}

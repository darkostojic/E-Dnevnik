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

public class Register extends AppCompatActivity {

    EditText etRFName, etRLName, etRUsername, etRPassword1, etRPassword2, etRDateOfBirth, etRAdminKod;
    Button btnRRegister, btnRReset;

    public String firstName, lastName, username, password, dateofbirth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_register);



        etRFName = (EditText) findViewById(R.id.etRFName);
        etRLName = (EditText) findViewById(R.id.etRLName);
        etRUsername = (EditText) findViewById(R.id.etRUsername);
        etRPassword1 = (EditText) findViewById(R.id.etRPassword1);
        etRPassword2 = (EditText) findViewById(R.id.etRPassword2);
        etRDateOfBirth = (EditText) findViewById(R.id.etRDateOfBirth);
        etRAdminKod = (EditText) findViewById(R.id.etRAdminKod);
        btnRRegister = (Button) findViewById(R.id.btnRRegister);
        btnRReset = (Button) findViewById(R.id.btnRReset);

        /**
         * Trenutno sam dodao boolean da provjeri ako ima ijedno polje prazno
         * Ako skontam neki drugi nacin unutar iducih 5 min promjenim al za sad radi
         * takodjer sam dodao admin kod - 1234 koji stoji na dnu zaslona kad se registriras
         * -Edo
         * **/
        btnRRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkIfEmpty()==false) {
                    if(passwordsMatch() == true) {
                        if(etRAdminKod.getText().toString().equals("1234")) {
                            insertData();
                        }else {
                            Toast.makeText(Register.this, "Admin code wrong, cannot register!", Toast.LENGTH_SHORT).show();
                            clearETs();
                        }
                    }
                }
                else{
                    Toast.makeText(Register.this, "Nepotpuna registracijska forma!", Toast.LENGTH_LONG).show();
                }
            }
        });
        // Ocistiti polja za unos
        btnRReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearETs();
            }
        });
    }

    private void clearETs() {
        etRFName.setText("");
        etRLName.setText("");
        etRUsername.setText("");
        etRPassword1.setText("");
        etRPassword2.setText("");
        etRDateOfBirth.setText("");
    }

    //Provjera da li su obe lozinke jednake
    private boolean passwordsMatch() {
        if(etRPassword1.getText().toString().equals(etRPassword2.getText().toString())) {
            return true;
        }
        else{
            Toast.makeText(Register.this, "Lozinke nisu jednake.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    //Unos u bazu podataka
    private void insertData() {
        firstName = etRFName.getText().toString();
        lastName = etRLName.getText().toString();
        username = etRUsername.getText().toString();
        password = etRPassword1.getText().toString();
        dateofbirth = etRDateOfBirth.getText().toString();

        //Random slika sa interneta koja izgleda kao user "https://www.iconfinder.com/data/icons/rcons-user-action/32/boy-512.png"
        String avatar = "https://www.iconfinder.com/data/icons/rcons-user-action/32/boy-512.png";

        usersugar user = new usersugar(username,password,avatar,firstName,lastName,dateofbirth,1);
        user.save();

        Toast.makeText(this, "Uspješno ste se registrirali! Sada se možete prijaviti.", Toast.LENGTH_LONG).show();
        Intent i = new Intent(Register.this, Login.class);
        startActivity(i);
        finish();


    }

    //Boolean za provjeru spomenut iznad, cie = checkIfEmpty, vraca false normalno, ali ako bilo koji uvjet nije tocan vrati true
    private boolean checkIfEmpty() {
        boolean cie = false;
        if(etRFName.getText().toString().equals(""))
            cie=true;
        if(etRLName.getText().toString().equals(""))
            cie=true;
        if(etRUsername.getText().toString().equals(""))
            cie=true;
        if(etRDateOfBirth.getText().toString().equals(""))
            cie=true;
        if(etRPassword1.getText().toString().equals(""))
            cie=true;
        if(etRPassword2.getText().toString().equals(""))
            cie=true;

        return cie;
    }

    private boolean validPass() {
        if(passwordsMatch() == true){
            password = etRPassword1.getText().toString();
            if(password.length()>=4){
                return true;
            }
            else{
                Toast.makeText(this, "Lozinka bi trebala imati najmanje 4 znaka.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            return false;
        }
    }
}

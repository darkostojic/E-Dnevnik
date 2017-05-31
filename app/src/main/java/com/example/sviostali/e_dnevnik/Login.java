package com.example.sviostali.e_dnevnik;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class Login extends AppCompatActivity {

    EditText etLUsername, etLPassword;
    Button btnLBack, btnLLogin, btnLRegister;
    public String username, password;
    public DBHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myDb = new DBHelper(this);

        etLUsername = (EditText) findViewById(R.id.etLUsername);
        etLPassword = (EditText) findViewById(R.id.etLPassword);
        btnLBack = (Button) findViewById(R.id.btnLBack);
        btnLLogin = (Button) findViewById(R.id.btnLLogin);
        btnLRegister = (Button) findViewById(R.id.btnLRegister);

        /**
         * 3 onclicklistenera, login posalje samo username pa sam mislio napravit da userinfo prihvati username,
         * pretrazi bazu i zatim ispise podatke ovisno o tome da li se radi o profesoru ili uceniku
         * -Edo
         * **/
        btnLBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, MainActivity.class);
                startActivity(i);
            }
        });

        btnLLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( !etLPassword.getText().toString().equals("") && !etLUsername.getText().toString().equals("")){ //Lozinka i korisnicko moraju biti popunjeni
                    if(checkLogin()==true){
                        Intent i = new Intent(Login.this, UserInfo.class);
                        i.putExtra("username", username);
                        startActivity(i);
                        finish();
                    }else{
                        Toast.makeText(Login.this, "Korisničko ime/lozinka ne postoji/netocno.", Toast.LENGTH_SHORT).show();
                        etLPassword.setText("");
                        etLUsername.setText("");
                    }
                }else{
                    Toast.makeText(Login.this, "Niste unijeli korsnicko ime/lozinku.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });

    }

    public boolean checkLogin(){
        Cursor showDataNow = myDb.getData();
        boolean rtrn = false;
        if(showDataNow.getCount() == 0){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();

        }else{

            while (showDataNow.moveToNext()) {

                String a = showDataNow.getString(showDataNow.getColumnIndex("login"));
                String b = showDataNow.getString(showDataNow.getColumnIndex("password"));
                if((a.equals(etLUsername.getText().toString()))&&(b.equals(etLPassword.getText().toString()))){
                    username = a;
                    password = b;
                    rtrn = true;
                    Toast.makeText(this, "Uspjesno ulogirani!", Toast.LENGTH_SHORT).show();
                }

            }
        }
        return rtrn;
    }

}

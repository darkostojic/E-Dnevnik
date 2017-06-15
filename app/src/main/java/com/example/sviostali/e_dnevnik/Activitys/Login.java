package com.example.sviostali.e_dnevnik.Activitys;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sviostali.e_dnevnik.MainActivity;
import com.example.sviostali.e_dnevnik.R;
import com.example.sviostali.e_dnevnik.sugarclasses.usersugar;
import com.orm.SugarContext;

import java.util.List;


public class Login extends AppCompatActivity {
    EditText etLUsername, etLPassword;
    Button btnLBack, btnLLogin, btnLRegister;
    public String username, password;
    public int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_login);

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
                        i.putExtra("id", id);
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
                finish();
            }
        });

    }

    public boolean checkLogin(){

        List<usersugar> allUsers = usersugar.listAll(usersugar.class);

        boolean rtrn = false;
        if(allUsers.isEmpty()){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();

        }else{

            for(int i = 0;i<allUsers.size();i++) {

                String a = allUsers.get(i).getLogin();
                String b = allUsers.get(i).getPassword();

                if ((a.equals(etLUsername.getText().toString())) && (b.equals(etLPassword.getText().toString()))) {
                    username = a;
                    password = b;
                    id = Integer.parseInt(String.valueOf(allUsers.get(i).getId()));
                    rtrn = true;
                    Toast.makeText(this, "Uspjesno ulogirani!", Toast.LENGTH_SHORT).show();

                }
            }

        }
        return rtrn;
    }

}

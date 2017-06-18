package com.example.sviostali.e_dnevnik.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sviostali.e_dnevnik.R;
import com.example.sviostali.e_dnevnik.sugarclasses.usersugar;
import com.orm.SugarContext;

public class UserInfo extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // tvui1,itd radi lakseg gledanja, avatar isto tako
    Button btnUI1, btnUI2;
    TextView tvUI1, tvUI2, tvUI3, tvUI4;
    ImageView avatar;
    public String username, avatarurl;
    public int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SugarContext.init(this);
        setContentView(R.layout.activity_user_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvUI1 = (TextView) findViewById(R.id.tvUIUsername);
        tvUI2 = (TextView) findViewById(R.id.tvUIProsjek);
        tvUI3 = (TextView) findViewById(R.id.tvUIRedovitost);
        tvUI4 = (TextView) findViewById(R.id.tvUIRodjendan);
        avatar = (ImageView) findViewById(R.id.iv_uiavatar);
        btnUI1 = (Button) findViewById(R.id.btnUI1);
        btnUI2 = (Button) findViewById(R.id.btnUI2);



        /** Iz bundlea uzme username, i u showdata provjeri username i pokupi ostale podatke da ih moze koristiti*/
        Bundle s = getIntent().getExtras();

        id = s.getInt("id");
        showData(id);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.change_info) {
            Intent i = new Intent(UserInfo.this, ChangeInfo.class);
            i.putExtra("id", this.id);
            startActivity(i);
        } else if (id == R.id.change_pass) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void showData(final int id){

        usersugar user = usersugar.findById(usersugar.class, id);

        tvUI1.setText("Username: "+user.getLogin());
        tvUI2.setText("Ime: "+user.getFirstname());
        tvUI3.setText("Prezime: "+user.getLastname());
        avatarurl = user.getAvatar();
        Glide.with(this)
                .load(avatarurl)
                .into(avatar);
        avatarurl = user.getAvatar();
        tvUI4.setText(user.getBirthdate());
        if(user.getProfessor()==1) { //Gornje za profesore, donje za studente

            btnUI1.setText("Vaši predmeti");// dodat onclicklistenere za sva 4 kad napravimo predmete i ocjene itd
            btnUI2.setText("Dodavanje predmeta");

            btnUI1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(UserInfo.this, SelectedSubjectsActivity.class);
                    i.putExtra("id", id);
                    startActivity(i);
                }
            });

            btnUI2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(UserInfo.this, SubjectListActivity.class);
                    i.putExtra("id", id);
                    startActivity(i);
                }
            });

        }else{
            btnUI1.setText("Popis predmeta");
            btnUI2.setText("Ocjene");

            btnUI1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(UserInfo.this, StudentsSubjects.class);
                    i.putExtra("id", id);
                    startActivity(i);
                }
            });
        }




    }

}

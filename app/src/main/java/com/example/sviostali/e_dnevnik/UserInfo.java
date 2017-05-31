package com.example.sviostali.e_dnevnik;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class UserInfo extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // tvui1,itd radi lakseg gledanja, avatar isto tako
    Button btnUI1, btnUI2;
    TextView tvUI1, tvUI2, tvUI3, tvUI4;
    ImageView avatar;
    public String username, avatarurl;
    public DBHelper myDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        myDb = new DBHelper(this);

        /** Iz bundlea uzme username, i u showdata provjeri username i pokupi ostale podatke da ih moze koristiti*/
        Bundle s = getIntent().getExtras();
        username = s.getString("username");
        showData();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void showData(){
        Cursor showDataNow = myDb.getData();
        if(showDataNow.getCount() == 0){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }else{

            while (showDataNow.moveToNext()) {

                String a = showDataNow.getString(showDataNow.getColumnIndex("login"));
                if((a.equals(username))){
                    if(showDataNow.getInt(showDataNow.getColumnIndex("professor"))==1) { //Gornje za profesore, donje za studente
                        tvUI1.setText("Username: "+showDataNow.getString(showDataNow.getColumnIndex("login")));
                        tvUI2.setText("Ime: "+showDataNow.getString(showDataNow.getColumnIndex("first_name")));
                        tvUI3.setText("Prezime: "+showDataNow.getString(showDataNow.getColumnIndex("last_name")));
                        avatarurl = showDataNow.getString(showDataNow.getColumnIndex("avatar"));

                        tvUI4.setText(showDataNow.getString(showDataNow.getColumnIndex("birth_date")));
                        btnUI1.setText("Vaši predmeti");        // dodat onclicklistenere za sva 4 kad napravimo predmete i ocjene itd
                        btnUI2.setText("Dodavanje predmeta");
                    }else{
                        tvUI1.setText("Username: "+showDataNow.getString(showDataNow.getColumnIndex("login")));
                        tvUI2.setText("Ime: "+showDataNow.getString(showDataNow.getColumnIndex("first_name")));
                        tvUI3.setText("Prezime: "+showDataNow.getString(showDataNow.getColumnIndex("last_name")));
                        tvUI4.setText(showDataNow.getString(showDataNow.getColumnIndex("birth_date")));
                        btnUI1.setText("Popis profesora");
                        btnUI2.setText("Ocjene");
                    }
                }
            }
        }

    }

}

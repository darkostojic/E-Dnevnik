package com.example.sviostali.e_dnevnik;


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetUsersFromJSON extends AppCompatActivity {

    public String url = "https://api.github.com/users";
    public RequestQueue requestQueue;
    public Context context;
    public DBHelper dbMain;
    public GetUsersFromJSON(Context context) {
        this.context = context;
    }

    public void getData(){
        dbMain = new DBHelper(context);
        requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    String login;
                    String avatar;
                    for(int i = 0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        login = jsonObject.getString("login");
                        avatar = jsonObject.getString("avatar_url");
                        dbMain.insertUserData(login,"1234", avatar,"john","doe","01.01.2000",0);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

}
package com.example.sviostali.e_dnevnik;

import android.app.Application;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.AppLaunchChecker;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

public class GetStudentsFromURL extends AsyncTask<Void, Void, Void> {

    private ProgressDialog pDialog;
    private static String url = "https://api.github.com/users";
    public DBHelper dbMain;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Showing progress dialog
        /*
        pDialog = new ProgressDialog();
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        pDialog.show();
        */

    }

    @Override
    protected Void doInBackground(Void... arg0) {
        HttpHandler sh = new HttpHandler();

        // Making a request to url and getting response
        String jsonStr = sh.makeServiceCall(url);

        Log.e(TAG, "Response from url: " + jsonStr);
        dbMain = new DBHelper(MyApplication.getAppContext());
        if (jsonStr != null) {
            try {

                JSONArray students = new JSONArray(jsonStr);

                for (int i = 0; i < students.length(); i++) {

                    JSONObject c = students.getJSONObject(i);

                    String login = c.getString("login");
                    String avatar = c.getString("avatar_url");
                    //dbMain.insertUserData(login, "1234", avatar, 0);


                }
            } catch (final JSONException e) {
                Log.e(TAG, "Json parsing error: " + e.getMessage());

            }
        } else {
            Log.e(TAG, "Couldn't get json from server.");

        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        // Dismiss the progress dialog
        /*if (pDialog.isShowing())
            pDialog.dismiss();
        */

    }

}
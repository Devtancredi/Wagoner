package com.example.wagoner;

import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.GridView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.net.URL;

public class ScrollingActivity extends AppCompatActivity {

    private ArrayList<Wheel> userWheels = new ArrayList<Wheel>();

    /*String[] images = {
            "@drawable/notes_math.jpg",
            "@drawable/notes_math2.jpg"
    };*/
    String json_string;
    Wheel wheel;
    Wheel[] wheels;



    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //createTestWheel();

        gridView = (GridView) findViewById(R.id.wheelsGridView);
        ImageAdapter imageAdapter = new ImageAdapter(this);
        gridView.setAdapter(imageAdapter);

        //Create new wheel fab
        FloatingActionButton fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Submit Wheel", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                openCreateWheelActivity();
            }
        });

        //search wheels fab
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Search", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                openSearchActivity();
            }
        });
    }

    public void openCreateWheelActivity() {
        Intent intent = new Intent(this, CreateWheelActivity.class);
        startActivity(intent);
    }

    public void openSearchActivity() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void openWheelActivity(/*wheel id*/) {
        Intent intent = new Intent(this, WheelActivity.class);
        startActivity(intent);
    }

    public void openWheelActivity(/*wheel id*/ View v) {
        Intent intent = new Intent(this, WheelActivity.class);
        startActivity(intent);
    }

    public void createTestWheel() {
        for (int x = 0; x < images.length; x++) {
            userWheels.add(new Wheel(BitmapFactory.decodeFile(images[x])));
        }
    }

    public void displayTestWheel() {

    }

    public void getWheels() {

    }

    public void getWheel(int x) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
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





    public void getJson(View view) {
        new BackgroundTask().execute();
    }

    class BackgroundTask extends AsyncTask<Void,Void,String> {

        String json_url;

        @Override
        protected void onPreExecute() {
            json_url= "tancredbs.cspqiztnfuiy.us-east-1.rds.amazonaws.com";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader( inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((json_string = bufferedReader.readLine()) != null) {
                    stringBuilder.append(json_string+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate();
        }

        @Override
        protected void onPostExecute(String result) {
            //Gson.
        }



    }
}

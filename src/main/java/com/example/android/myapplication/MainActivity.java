package com.example.android.myapplication;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;

import data.Citypreferances;
import data.JSONWeatherparser;
import data.Weatherhttpclient;
import models.Weather;
import utils.utils;

public class MainActivity extends AppCompatActivity {


    private TextView cityname;
    private TextView temp;
    private ImageView iconview;
    private TextView description;
    private TextView humidity;
    private TextView pressure;
    private TextView wind;
    private TextView sunrise;
    private TextView sunset;
    private TextView updated;


    Weather weather = new Weather();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iconview = (ImageView) findViewById(R.id.thumbnailicon);
        cityname = (TextView) findViewById(R.id.citytext);
        temp = (TextView) findViewById(R.id.temptext);
        description = (TextView) findViewById(R.id.cloudtext);
        humidity = (TextView) findViewById(R.id.humidiytext);
        pressure = (TextView) findViewById(R.id.pressuretext);
        wind = (TextView) findViewById(R.id.windtext);
        sunrise = (TextView) findViewById(R.id.Sunrisetext);
        sunset = (TextView) findViewById(R.id.sunsettext);
        updated = (TextView) findViewById(R.id.lastupdated);






        Citypreferances citypreferances= new Citypreferances(MainActivity.this);

        renderweatherdata(citypreferances.getcity());



        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2 = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void renderweatherdata(String place) {

        Weathertask weathertask = new Weathertask();
        weathertask.execute(place + "&units=metric");

    }



    private void Showinputdialog(){
        AlertDialog.Builder builder= new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Set City");

        final EditText cityinput= new EditText(MainActivity.this);
        cityinput.setInputType(InputType.TYPE_CLASS_TEXT);
        cityinput.setHint("potland,US");
        builder.setView(cityinput);

        builder.setPositiveButton("Submite", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Citypreferances citypreferances= new Citypreferances(MainActivity.this);
                citypreferances.Setcity(cityinput.getText().toString());

                String newcity= citypreferances.getcity();
                renderweatherdata(newcity);




            }

        }

        );
        builder.show();
    }

   private class Downloadimage extends AsyncTask<String,Void,Bitmap>{


       @Override
       protected Bitmap doInBackground(String... params) {
           return DownloadImageFromPath(params[0]);
       }

       @Override
       protected void onPostExecute(Bitmap bitmap) {
           iconview.setImageBitmap(bitmap);
       }
   }

    private Bitmap DownloadImageFromPath(String Code){
        InputStream in =null;
        Bitmap bmp=null;
        int responseCode = -1;
        try{

            URL url = new URL(utils.ICON_URL+Code+".png");//"http://192.xx.xx.xx/mypath/img1.jpg
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setDoInput(true);
            con.connect();
            responseCode = con.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK)
            {
                //download
                in = con.getInputStream();
                bmp = BitmapFactory.decodeStream(in);
                in.close();

            }

        }
        catch(Exception ex){
            Log.e("Exception",ex.toString());
        }
        return bmp;
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2.connect();
        AppIndex.AppIndexApi.start(client2, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client2, getIndexApiAction());
        client2.disconnect();
    }

    private class Weathertask extends AsyncTask<String, Void, Weather> {


        @Override
        protected Weather doInBackground(String[] strings) {


            try {
                String data = new Weatherhttpclient().getweatherdata(strings[0]);
                weather = JSONWeatherparser.getweather(data);
                weather.icondata = weather.currentcondition.getIcon();
                new Downloadimage().execute(weather.icondata);


                Log.v("Data:", weather.currentcondition.getDes());



            } catch (IOException e) {
                e.printStackTrace();
            }
            return weather;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            DateFormat df = DateFormat.getTimeInstance();

            String Sunrisedate = df.format(new Date(weather.place.getSunrise()));
            String Sunsetdate = df.format(new Date(weather.place.getSunset()));
            String updatedate = df.format(new Date(weather.place.getLastupdated()));


            DecimalFormat de = new DecimalFormat("#.#");

            String temperatures = de.format(weather.currentcondition.getTemperature());

            cityname.setText(weather.place.getCity() + "," + weather.place.getCountry());
            temp.setText("" + temperatures + "C");
            humidity.setText("Humidity:" + weather.currentcondition.getHumidity() + "%");
            pressure.setText("Pressure:" + weather.currentcondition.getPressure() + "HPA");
            wind.setText("Wind:" + weather.wind.getSpeed() + "mps");
            sunrise.setText("Sunrise:" + Sunrisedate);
            sunset.setText("Sunset:" + Sunsetdate);
            updated.setText("Last Updated:" + updatedate);
            description.setText("Condition:" + weather.currentcondition.getCondition() + "(" + weather.currentcondition.getDes() + ")");

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.changecity) Showinputdialog();
        return super.onOptionsItemSelected(item);

    }
}

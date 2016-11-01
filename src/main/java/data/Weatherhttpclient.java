package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import utils.utils;

/**
 * Created by Sam on 10/23/2016.
 */
public class Weatherhttpclient {

    public String getweatherdata(String place) throws IOException {

        HttpURLConnection connection=null;
        InputStream inputStream=null;

        try {
            connection=(HttpURLConnection)(new URL(utils.BASE_URL + place+ "&appid=d7b900681c37193223281142bd919019")).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoInput(true);
            connection.connect();



            StringBuffer stringBuffer= new StringBuffer();
            inputStream = connection.getInputStream();

            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));

            String line= null;

            while ((line=bufferedReader.readLine())!=null){

                stringBuffer.append(line+"\r\n");
            }

            inputStream.close();
            connection.disconnect();

            return stringBuffer.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    };
}

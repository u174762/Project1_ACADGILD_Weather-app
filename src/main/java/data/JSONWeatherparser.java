package data;

import android.support.annotation.Nullable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import models.Weather;
import models.place;

/**
 * Created by Sam on 10/24/2016.
 */
public class JSONWeatherparser {

    @Nullable
    public static Weather getweather(String data) {

        Weather weather= new Weather();

        try {
            JSONObject jsonObject= new JSONObject(data);

            place place= new place();

            //get cordinate objectsect
            JSONObject coordobj= utils.utils.getObject("coord",jsonObject);
            place.setLat(utils.utils.getfloat("lat",coordobj));
            place.setLon(utils.utils.getfloat("lon",coordobj));

            //get sys obj

            JSONObject sysobj=utils.utils.getObject("sys",jsonObject);
            place.setCountry(utils.utils.getsstring("country",sysobj));
            place.setLastupdated(utils.utils.getin("dt",jsonObject));
            place.setSunrise(utils.utils.getin("sunrise",sysobj));
            place.setSunset(utils.utils.getin("sunset",sysobj));
            place.setCity(utils.utils.getsstring("name",jsonObject));

            Weather.place =place;

            JSONArray jsonArray = jsonObject.getJSONArray("weather");

            JSONObject jsonweather=jsonArray.getJSONObject(0);

            //get weather info

            weather.currentcondition.setWeatherid(utils.utils.getin("id",jsonweather));
            weather.currentcondition.setDes(utils.utils.getsstring("description",jsonweather));
            weather.currentcondition.setCondition(utils.utils.getsstring("main",jsonweather));
            weather.currentcondition.setIcon(utils.utils.getsstring("icon",jsonweather));

            JSONObject mainobj=utils.utils.getObject("main",jsonObject);
            weather.currentcondition.setHumidity(utils.utils.getin("humidity",mainobj));
            weather.currentcondition.setPressure(utils.utils.getin("pressure",mainobj));
            weather.currentcondition.setMintemp(utils.utils.getfloat("temp_min",mainobj));
            weather.currentcondition.setMaxtemp(utils.utils.getfloat("temp_max",mainobj));
            weather.currentcondition.setTemperature(utils.utils.getdouble("temp",mainobj));

            //get clud info

            JSONObject cloudobj=utils.utils.getObject("clouds",jsonObject);
            weather.clouds.setPercipitation(utils.utils.getin("all",cloudobj));

            //get wind info

            JSONObject windobj=utils.utils.getObject("wind",jsonObject);
            weather.wind.setDegree(utils.utils.getfloat("deg",windobj));
            weather.wind.setSpeed(utils.utils.getfloat("speed",windobj));

            return weather;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}

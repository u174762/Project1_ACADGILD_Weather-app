package utils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sam on 10/23/2016.
 */
public class utils {

    public static final String BASE_URL="http://api.openweathermap.org/data/2.5/weather?q=";
    public static final String ICON_URL="https://openweathermap.org/img/w/";

    public static JSONObject getObject(String Tagname,JSONObject jsonObject) throws JSONException{
        JSONObject jbj=jsonObject.getJSONObject(Tagname);
        return  jbj;
    }
    public static String getsstring(String Tagname,JSONObject jsonObject)throws  JSONException{

        return jsonObject.getString(Tagname);
    }
    public static float getfloat(String Tagname, JSONObject jsonObject)throws  JSONException{

        return (float)jsonObject.getDouble(Tagname);
    }
    public static double getdouble(String Tagname, JSONObject jsonObject)throws  JSONException{

        return (float)jsonObject.getDouble(Tagname);
    }
    public static int getin(String Tagname,JSONObject jsonObject)throws  JSONException{

        return jsonObject.getInt(Tagname);
    }
}

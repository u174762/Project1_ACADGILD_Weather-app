package data;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Created by Sam on 11/1/2016.
 */

public class Citypreferances {

    SharedPreferences presfs;
    public Citypreferances(Activity activity){
        presfs=activity.getPreferences(activity.MODE_PRIVATE);

    }
    public String getcity(){

        return presfs.getString("city","Spokane,US");

    }
    public String Setcity(String city){

        return String.valueOf(presfs.edit().putString("city",city).commit());
    }
}

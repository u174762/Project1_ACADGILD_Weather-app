package models;

/**
 * Created by Sam on 10/23/2016.
 */
public class Currentcondition {

    public int getWeatherid() {
        return Weatherid;
    }

    public void setWeatherid(int weatherid) {
        Weatherid = weatherid;
    }

    public double getTemperature() {
        return Temperature;
    }

    public void setTemperature(double temperature) {
        Temperature = temperature;
    }

    public float getMintemp() {
        return Mintemp;
    }

    public void setMintemp(float mintemp) {
        Mintemp = mintemp;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public float getMaxtemp() {
        return Maxtemp;
    }

    public void setMaxtemp(float maxtemp) {
        Maxtemp = maxtemp;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }


    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
    private int Weatherid;
    private String condition;
    private String icon;
    private String des;
    private float pressure;
    private float humidity;
    private float Maxtemp;
    private float Mintemp;
    private double Temperature;


}

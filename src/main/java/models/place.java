package models;

/**
 * Created by Sam on 10/23/2016.
 */
public class place {
    private float lon;
    private float lat;
    private long sunrise;
    private long sunset;
    private String country;
    private String city;
    private long lastupdated;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public long getLastupdated() {
        return lastupdated;
    }

    public void setLastupdated(long lastupdated) {
        this.lastupdated = lastupdated;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

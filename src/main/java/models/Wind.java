package models;

/**
 * Created by Sam on 10/23/2016.
 */
public class Wind {
    public float getDegree() {
        return degree;
    }

    public void setDegree(float degree) {
        this.degree = degree;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    private float speed;
    private float degree;
}

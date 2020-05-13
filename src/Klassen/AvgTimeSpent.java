package Klassen;

import Interfaces.AvgTimeSpentIF;

public class AvgTimeSpent implements AvgTimeSpentIF {

    float avgtime;

    public AvgTimeSpent(){
        this.avgtime = 0.0f;
    }

    @Override
    public float getAvgtime() {
        return this.avgtime;
    }

    @Override
    public void setAvgtime(float avgtime) {
        this.avgtime = avgtime;
    }
}

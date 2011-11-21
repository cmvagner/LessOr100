package com.appspot.lessor100.df;

public class Threshold {

    private String mount;
    private double threshold;

    public Threshold(String mount, double threshold) {
        this.mount = mount;
        this.threshold = threshold;
    }

    public String getMount() {
        return mount;
    }

    public void setMount(String mount) {
        this.mount = mount;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }
}

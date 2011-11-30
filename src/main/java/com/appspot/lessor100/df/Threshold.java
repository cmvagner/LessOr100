package com.appspot.lessor100.df;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.google.appengine.api.datastore.Key;

@Model
public class Threshold {

    @Attribute(primaryKey = true)
    private Key key;
    private String mount;
    private double threshold;
    private ModelRef<Server> serverRef = new ModelRef<Server>(Server.class);

    public Threshold() {
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
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

    public ModelRef<Server> getServerRef() {
        return serverRef;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Threshold");
        sb.append("{key=").append(key);
        sb.append(", mount='").append(mount).append('\'');
        sb.append(", threshold=").append(threshold);
        sb.append(", serverRef=").append(serverRef);
        sb.append('}');
        return sb.toString();
    }
}

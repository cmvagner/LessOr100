package com.appspot.lessor100.df;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.google.appengine.api.datastore.Key;

@Model
public class Mount {

    @Attribute(primaryKey = true)
    private Key key;
    private double usageInPercent;
    private String mountedOn;
    private ModelRef<MountGroup> mountGroupRef = new ModelRef<MountGroup>(MountGroup.class);

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public double getUsageInPercent() {
        return usageInPercent;
    }

    public void setUsageInPercent(double usageInPercent) {
        this.usageInPercent = usageInPercent;
    }

    public String getMountedOn() {
        return mountedOn;
    }

    public void setMountedOn(String mountedOn) {
        this.mountedOn = mountedOn;
    }

    public ModelRef<MountGroup> getMountGroupRef() {
        return mountGroupRef;
    }
}

package com.appspot.lessor100.df;

import java.util.List;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;
import org.slim3.datastore.Sort;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;

@Model
public class Server {

    @Attribute(primaryKey = true)
    private Key key;
    private String name;
    @Attribute(persistent = false)
    private InverseModelListRef<MountGroup, Server> mountListRef = new InverseModelListRef<MountGroup, Server>(MountGroup.class, MountGroupMeta.get().serverRef.getName(), this, new Sort(MountGroupMeta.get().createdAt.getName(), Query.SortDirection.DESCENDING));
    @Attribute(persistent = false)
    private InverseModelListRef<Threshold, Server> thresholdListRef = new InverseModelListRef<Threshold, Server>(Threshold.class, ThresholdMeta.get().serverRef.getName(), this);

    public Server() {
    }

    public Server(String serverName) {
        this.name = serverName;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InverseModelListRef<MountGroup, Server> getMountListRef() {
        return mountListRef;
    }

    public InverseModelListRef<Threshold, Server> getThresholdListRef() {
        return thresholdListRef;
    }

    public MountGroup getLatestMountGroup() {
        List<MountGroup> mountGroups = getMountListRef().getModelList();
        if (!mountGroups.isEmpty()) {
            return mountGroups.get(0);
        }
        return null;
    }
}

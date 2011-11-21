package com.appspot.lessor100.df;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;

import com.google.appengine.api.datastore.Key;

@Model
public class Server {

    @Attribute(primaryKey = true)
    private Key key;
    private String name;
    @Attribute(persistent = false)
    private InverseModelListRef<MountGroup, Server> mountListRef = new InverseModelListRef<MountGroup, Server>(MountGroup.class, MountGroupMeta.get().serverRef.getName(), this);

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
}

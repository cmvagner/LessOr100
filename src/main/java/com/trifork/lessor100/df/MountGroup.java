package com.trifork.lessor100.df;

import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.CreationDate;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.google.appengine.api.datastore.Key;

@Model
public class MountGroup {

    @Attribute(primaryKey = true)
    private Key key;
    @Attribute(listener = CreationDate.class)
    private Date createdAt;
    @Attribute(persistent = false)
    private InverseModelListRef<Mount, MountGroup> mountListRef = new InverseModelListRef<Mount, MountGroup>(Mount.class, MountMeta.get().mountGroupRef.getName(), this);
    private ModelRef<Server> serverRef = new ModelRef<Server>(Server.class);

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public InverseModelListRef<Mount, MountGroup> getMountListRef() {
        return mountListRef;
    }

    public ModelRef<Server> getServerRef() {
        return serverRef;
    }
}

package com.appspot.lessor100.df;

import java.util.List;

import org.slim3.datastore.Datastore;
import org.springframework.stereotype.Repository;

import com.appspot.lessor100.datastore.AbstractDataStoreRepository;
import com.appspot.lessor100.datastore.TransactionCallback;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Query;

@Repository
public class ServerRepositoryImpl extends AbstractDataStoreRepository implements ServerRepository {

    @Override
    public Server findByKey(Key key) {
        return Datastore.get(Server.class, key);
    }

    @Override
    public Server findByName(String serverName) {
        return Datastore.query(Server.class).filter("name", Query.FilterOperator.EQUAL, serverName).asSingle();
    }

    @Override
    public void saveServer(final String serverName) {
        doDataStoreOperation(new TransactionCallback() {
            @Override
            public void doInTransaction() {
                Datastore.put(new Server(serverName));
            }
        });
    }

    @Override
    public void saveMounts(final List<Mount> mounts, final String serverName) {
        doDataStoreOperation(new TransactionCallback() {
            @Override
            public void doInTransaction() {
                Server server = findByName(serverName);
                Key serverKey;
                if (server == null) {
                    server = new Server(serverName);
                    serverKey = Datastore.put(server);
                } else {
                    serverKey = server.getKey();
                }
                MountGroup mountGroup = new MountGroup();
                Key mountKey = Datastore.allocateId(serverKey, MountGroup.class);
                mountGroup.setKey(mountKey);
                mountGroup.getServerRef().setModel(server);
                Datastore.put(mountGroup);
                for (Mount mount : mounts) {
                    mount.setKey(Datastore.allocateId(mountKey, Mount.class));
                    mount.getMountGroupRef().setModel(mountGroup);
                }
                Datastore.put(mounts);
            }
        });
    }

    @Override
    public void saveThreshold(final Threshold threshold, final Server server) {
        doDataStoreOperation(new TransactionCallback() {
            @Override
            public void doInTransaction() {
                threshold.setKey(Datastore.allocateId(server.getKey(), Threshold.class));
                threshold.getServerRef().setModel(server);
                Datastore.put(threshold);
            }
        });
    }

    @Override
    public List<Server> getAllServers() {
        return Datastore.query(Server.class).asList();
    }
}

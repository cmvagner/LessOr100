package com.appspot.lessor100.df;

import java.util.List;

import com.google.appengine.api.datastore.Key;

public interface ServerRepository {

    Server findByKey(Key key);

    Server findByName(String serverName);

    void saveServer(String serverName);

    void saveMounts(List<Mount> mounts, String serverName);

    void saveThreshold(Threshold threshold, Server server);

    List<Server> getAllServers();


}

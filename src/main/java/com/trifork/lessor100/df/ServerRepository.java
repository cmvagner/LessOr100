package com.trifork.lessor100.df;

import java.util.List;

public interface ServerRepository {

    Server findByName(String serverName);

    void saveMounts(List<Mount> mounts, String serverName);

    List<Server> getAllServers();
}

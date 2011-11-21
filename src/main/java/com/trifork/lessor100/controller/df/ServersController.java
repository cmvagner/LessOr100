package com.trifork.lessor100.controller.df;

import org.slim3.controller.Navigation;

import com.trifork.lessor100.controller.AbstractBaseController;

public class ServersController extends AbstractBaseController {

    @Override
    protected Navigation run() throws Exception {
        requestScope("servers", getServerRepository().getAllServers());
        return forward("servers.jsp");
    }
}

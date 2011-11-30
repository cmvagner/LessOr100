package com.appspot.lessor100.controller.df.server;

import org.slim3.controller.Navigation;

import com.appspot.lessor100.controller.AbstractBaseController;

public class IndexController extends AbstractBaseController {

    @Override
    protected Navigation run() throws Exception {
        requestScope("servers", getServerRepository().getAllServers());
        return forward("index.jsp");
    }
}

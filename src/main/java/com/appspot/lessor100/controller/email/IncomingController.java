package com.appspot.lessor100.controller.email;

import org.slim3.controller.Navigation;

import com.appspot.lessor100.controller.AbstractBaseController;

public class IncomingController extends AbstractBaseController {

    @Override
    protected Navigation run() throws Exception {
        requestScope("incomingEmails", getEmailRepository().getAllIncomingEmails());
        return forward("incoming.jsp");
    }

}

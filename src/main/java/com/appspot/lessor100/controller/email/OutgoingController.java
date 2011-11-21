package com.appspot.lessor100.controller.email;

import org.slim3.controller.Navigation;

import com.appspot.lessor100.controller.AbstractBaseController;

public class OutgoingController extends AbstractBaseController {

    @Override
    protected Navigation run() throws Exception {
        requestScope("outgoingEmails", getEmailRepository().getAllOutgoingEmails());
        return forward("outgoing.jsp");
    }
}

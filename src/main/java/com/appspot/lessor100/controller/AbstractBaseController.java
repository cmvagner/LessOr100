package com.appspot.lessor100.controller;

import org.slim3.controller.Controller;
import org.slim3.util.ServletContextLocator;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.appspot.lessor100.df.ServerRepository;
import com.appspot.lessor100.email.EmailRepository;

public abstract class AbstractBaseController extends Controller {

    protected EmailRepository getEmailRepository() {
        return getApplicationContext().getBean(EmailRepository.class);
    }

    protected ServerRepository getServerRepository() {
        return getApplicationContext().getBean(ServerRepository.class);
    }

    private WebApplicationContext getApplicationContext() {
        return WebApplicationContextUtils.getRequiredWebApplicationContext(ServletContextLocator.get());
    }
}

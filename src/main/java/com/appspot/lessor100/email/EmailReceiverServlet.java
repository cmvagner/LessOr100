package com.appspot.lessor100.email;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class EmailReceiverServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(EmailReceiverServlet.class.getName());

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            EmailReceiver emailReceiver = getEmailReceiver();
            Email email = emailReceiver.receive(request.getInputStream());
            EmailDispatcher emailDispatcher = getEmailDispatcher();
            emailDispatcher.dispatch(email);
            logger.info(String.format("successfully processing of email from %s", email.getFrom()));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "exception during incoming email processing", e);
            EmailSender emailSender = getEmailSender();
            emailSender.sendErrorReportEmail(e, "admin");
        }
    }

    private EmailSender getEmailSender() {
        return getApplicationContext().getBean(EmailSender.class);
    }

    private EmailDispatcher getEmailDispatcher() {
        return getApplicationContext().getBean(EmailDispatcher.class);
    }

    private EmailReceiver getEmailReceiver() {
        return getApplicationContext().getBean(EmailReceiver.class);
    }

    private WebApplicationContext getApplicationContext() {
        return WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    }
}

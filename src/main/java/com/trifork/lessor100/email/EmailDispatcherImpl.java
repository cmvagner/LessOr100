package com.trifork.lessor100.email;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trifork.lessor100.df.DiskFreeEmailHandler;

@Service
public class EmailDispatcherImpl implements EmailDispatcher {

    private static final Logger logger = Logger.getLogger(EmailDispatcherImpl.class.getName());

    @Autowired
    private DiskFreeEmailHandler diskFreeEmailHandler;

    @Override
    public void dispatch(Email email) {
        AppMail appMail = AppMail.valueOf(email);
        if (appMail == null) {
            logger.warning(String.format("recipient %s for email from %s with subject %s could not be mapped to any AppMail", email.getTo(), email.getFrom(), email.getSubject()));
            return;
        }
        switch (appMail) {
            case DF:
                diskFreeEmailHandler.process(email);
                break;
            default:
                throw new RuntimeException(String.format("no mapped support for AppMail %s", appMail));
        }
    }
}

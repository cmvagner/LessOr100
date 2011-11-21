package com.appspot.lessor100.email;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

@Service
public class EmailSenderImpl implements EmailSender {

    private static final Logger logger = Logger.getLogger(EmailSenderImpl.class.getName());

    @Override
    public void sendErrorReportEmail(Exception exception, String email) {
        logger.log(Level.SEVERE, "sendErrorReportEmail = " + email, email);
    }

    public void send(String email, String subject, String msgBody) {
        /*log.debug("Processing send for " + subject + " at " + new Date());
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(ADMIN_EMAIL));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
                    email));
            msg.setSubject(subject);
            msg.setContent(msgBody, "text/html");
            Transport.send(msg);
            log.debug("sending mail to " + email);
        } catch (Exception e) {
            log.error("sender is " + ADMIN_EMAIL, e);
        }*/
    }
}

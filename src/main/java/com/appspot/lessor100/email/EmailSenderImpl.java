package com.appspot.lessor100.email;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

import com.appspot.lessor100.Constants;

@Service
public class EmailSenderImpl implements EmailSender {

    private static final Logger logger = Logger.getLogger(EmailSenderImpl.class.getName());

    @Override
    public void sendErrorReportEmail(Exception exception, String email) {
        logger.log(Level.SEVERE, "sendErrorReportEmail = " + email, email);
    }

    @Override
    public void sendEmail(String receiver, String subject, String body) {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("notification@" + Constants.APP_MAIL_DOMAIN));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            msg.setSubject(subject);
            msg.setText(body);
            Transport.send(msg);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "could not send email to " + receiver, e);
        }
    }
}

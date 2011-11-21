package com.appspot.lessor100.email;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.Text;

@Service
public class EmailReceiverImpl implements EmailReceiver {

    private static final Logger logger = Logger.getLogger(EmailReceiverImpl.class.getName());

    private EmailRepository emailRepository;

    @Autowired
    public EmailReceiverImpl(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    @Override
    public Email receive(InputStream inputStream) {
        try {
            MimeMessage mimeMessage = getMimeMessage(inputStream);
            String mailSubject = mimeMessage.getSubject();
            String fromMail = ((InternetAddress) mimeMessage.getFrom()[0]).getAddress();
            String toMail = ((InternetAddress) mimeMessage.getRecipients(Message.RecipientType.TO)[0]).getAddress();
            Email email = new Email(toMail, fromMail, mailSubject, EmailType.INCOMING);

            logger.info("received email from " + fromMail + " subject " + mailSubject);

            Object content = mimeMessage.getContent();
            String body = "";
            List<Attachment> attachments = null;
            if (content instanceof String) {
                body = (String) content;
            } else if (content instanceof Multipart) {
                Multipart multipart = (Multipart) content;
                Part part = multipart.getBodyPart(0);
                Object partContent = part.getContent();
                if (partContent instanceof String) {
                    body = (String) partContent;
                }
                attachments = getAttachments(multipart);
            }
            email.setBody(new Text(body));
            emailRepository.save(email, attachments);
            return email;
        } catch (Exception e) {
            throw new RuntimeException("error receiving email", e);
        }
    }

    /**
     * From http://java.sun.com/developer/onlineTraining/JavaMail/contents.html#GettingAttachments
     *
     * @param multipart the multipart message
     * @return a list of attachments to the message
     * @throws MessagingException if an error occurs
     * @throws IOException        if an error occurs
     */
    private List<Attachment> getAttachments(Multipart multipart) throws MessagingException, IOException {
        List<Attachment> attachments = new ArrayList<Attachment>();
        for (int i = 0; i < multipart.getCount(); i++) {
            BodyPart bodyPart = multipart.getBodyPart(i);
            String disposition = bodyPart.getDisposition();
            if (disposition == null) {
                continue;
            }
            if ((disposition.equals(Part.ATTACHMENT) || (disposition.equals(Part.INLINE)))) {
                String fileName = bodyPart.getFileName();

                logger.info(String.format("received attachment named %s", fileName));

                byte[] attachmentContent;
                InputStream attachmentsInputStream = bodyPart.getInputStream();
                try {
                    attachmentContent = getAttachmentContent(attachmentsInputStream);
                } finally {
                    attachmentsInputStream.close();
                }
                attachments.add(new Attachment(fileName, attachmentContent));
            }
        }
        return attachments;
    }

    private byte[] getAttachmentContent(InputStream inputStream) throws IOException {
        return IOUtils.toByteArray(inputStream);
    }

    /**
     * From http://code.google.com/appengine/docs/java/mail/receiving.html
     *
     * @param inputStream the input stream
     * @return the mime message based on the input stream
     * @throws javax.mail.MessagingException if the mime message could not be created
     */
    private MimeMessage getMimeMessage(InputStream inputStream) throws MessagingException {
        Session session = Session.getDefaultInstance(new Properties(), null);
        return new MimeMessage(session, inputStream);
    }
}

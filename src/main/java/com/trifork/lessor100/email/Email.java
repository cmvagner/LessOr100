package com.trifork.lessor100.email;

import java.util.Date;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.CreationDate;
import org.slim3.datastore.InverseModelListRef;
import org.slim3.datastore.Model;


import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Text;

@Model
public class Email {

    @Attribute(primaryKey = true)
    private Key key;
    @Attribute(listener = CreationDate.class)
    Date createdAt;
    @Attribute
    private EmailType emailType;
    @Attribute
    private String to;
    @Attribute
    private String from;
    @Attribute
    private String subject;
    @Attribute
    private Text body;
    @Attribute(persistent = false)
    private InverseModelListRef<Attachment, Email> attachmentListRef = new InverseModelListRef<Attachment, Email>(Attachment.class, AttachmentMeta.get().emailRef.getName(), this);

    public Email() {
    }

    public Email(String to, String from, String subject, EmailType emailType) {
        this.emailType = emailType;
        this.to = to;
        this.from = from;
        this.subject = subject;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public EmailType getEmailType() {
        return emailType;
    }

    public void setEmailType(EmailType emailType) {
        this.emailType = emailType;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Text getBody() {
        return body;
    }

    public void setBody(Text body) {
        this.body = body;
    }

    public InverseModelListRef<Attachment, Email> getAttachmentListRef() {
        return attachmentListRef;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Email");
        sb.append("{key=").append(key);
        sb.append(", to='").append(to).append('\'');
        sb.append(", from='").append(from).append('\'');
        sb.append(", subject='").append(subject).append('\'');
        sb.append(", body='").append(body).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /*public boolean hasAttachments() {
        return getAttachments() != null && !getAttachments().isEmpty();
    }*/

}

package com.appspot.lessor100.email;

import org.slim3.datastore.Attribute;
import org.slim3.datastore.Model;
import org.slim3.datastore.ModelRef;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Key;

@Model
public class Attachment {

    @Attribute(primaryKey = true)
    private Key key;
    @Attribute
    private String fileName;
    @Attribute(lob = true)
    private byte[] content;
    @Attribute
    private ModelRef<Email> emailRef = new ModelRef<Email>(Email.class);

    public Attachment() {
    }

    public Attachment(String fileName, byte[] content) {
        this.fileName = fileName;
        this.content = content;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public ModelRef<Email> getEmailRef() {
        return emailRef;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Attachment");
        sb.append("{content=").append(new String(content));
        sb.append(", fileName='").append(fileName).append('\'');
        sb.append(", key=").append(key);
        sb.append('}');
        return sb.toString();
    }
}

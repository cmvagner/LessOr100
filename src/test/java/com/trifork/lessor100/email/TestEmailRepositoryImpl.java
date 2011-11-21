package com.trifork.lessor100.email;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slim3.tester.AppEngineTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestEmailRepositoryImpl extends AppEngineTestCase {

    @Autowired
    private EmailRepository emailRepository;

    @Test
    public void testThatAEmailWithAnAttachmentCanBeStored() {
        Email email = new Email("test", "tester", "blabla", EmailType.INCOMING);
        Attachment attachment = new Attachment("fileName", "file content".getBytes());
        List<Attachment> attachments = new ArrayList<Attachment>();
        attachments.add(attachment);
        emailRepository.save(email, attachments);
        email = new Email("test", "tester", "blabla", EmailType.INCOMING);
        emailRepository.save(email);
        email = new Email("test", "tester", "blabla", EmailType.OUTGOING);
        emailRepository.save(email);
        List<Email> emails = emailRepository.getAllIncomingEmails();
        Assert.assertEquals(2, emails.size());
        email = emails.get(0);
        Assert.assertNotNull(email);
        attachments = email.getAttachmentListRef().getModelList();
        Assert.assertEquals(1, attachments.size());
        attachment = attachments.get(0);
        Assert.assertNotNull(attachment);
        emails = emailRepository.getAllOutgoingEmails();
        Assert.assertEquals(1, emails.size());
        email = emails.get(0);
        Assert.assertNotNull(email);

    }
}

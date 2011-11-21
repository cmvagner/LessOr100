package com.appspot.lessor100.email;

import java.util.List;

import org.slim3.datastore.Datastore;
import org.springframework.stereotype.Repository;

import com.google.appengine.api.datastore.Key;
import com.appspot.lessor100.datastore.AbstractDataStoreRepository;
import com.appspot.lessor100.datastore.TransactionCallback;

@Repository
public class EmailRepositoryImpl extends AbstractDataStoreRepository implements EmailRepository {

    @Override
    public void save(final Email email, final List<Attachment> attachments) {
        doDataStoreOperation(new TransactionCallback() {
            @Override
            public void doInTransaction() {
                Key emailKey = Datastore.put(email);
                if (attachments != null && !attachments.isEmpty()) {
                    for (Attachment attachment : attachments) {
                        attachment.setKey(Datastore.allocateId(emailKey, Attachment.class));
                        attachment.getEmailRef().setModel(email);
                    }
                    Datastore.put(attachments);
                }
            }
        });
    }

    @Override
    public void save(final Email email) {
        doDataStoreOperation(new TransactionCallback() {
            @Override
            public void doInTransaction() {
                Datastore.put(email);
            }
        });
    }

    @Override
    public List<Email> getAllIncomingEmails() {
        return queryForEmails(EmailType.INCOMING);
    }

    @Override
    public List<Email> getAllOutgoingEmails() {
        return queryForEmails(EmailType.OUTGOING);
    }

    private List<Email> queryForEmails(EmailType emailType) {
        EmailMeta emailMeta = EmailMeta.get();
        return Datastore.query(emailMeta).filter(emailMeta.emailType.equal(emailType)).asList();
    }
}

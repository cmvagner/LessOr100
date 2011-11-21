package com.appspot.lessor100.email;

import java.util.List;

public interface EmailRepository {

    void save(Email email, List<Attachment> attachments);

    void save(Email email);

    List<Email> getAllIncomingEmails();

    List<Email> getAllOutgoingEmails();
}

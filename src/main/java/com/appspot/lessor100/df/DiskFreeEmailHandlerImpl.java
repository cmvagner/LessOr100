package com.appspot.lessor100.df;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appspot.lessor100.email.Attachment;
import com.appspot.lessor100.email.Email;

@Service
public class DiskFreeEmailHandlerImpl implements DiskFreeEmailHandler {

    @Autowired
    private DiskFreeParser diskFreeParser;

    @Autowired
    private ServerRepository serverRepository;

    @Override
    public void process(Email email) {
        String contentToParse = getContentToParse(email);
        List<Mount> mounts = diskFreeParser.parse(contentToParse);
        serverRepository.saveMounts(mounts, getServerName(email));
    }

    private String getContentToParse(Email email) {
        List<Attachment> attachments = email.getAttachmentListRef().getModelList();
        String contentToParse;
        //if the email has attachments then parse the first attachment otherwise use the email body
        if (!attachments.isEmpty()) {
            //we only process the first attachment whether or not the mail has more then one attachment.
            Attachment attachment = attachments.get(0);
            contentToParse = new String(attachment.getContent());
        } else {
            contentToParse = email.getBody().getValue();
        }
        return contentToParse;
    }

    private String getServerName(Email email) {
        //here we use the address before the at sign. Another alternative to be to include the server name in the to address like df-ewpback20@lessor100...
        return email.getFrom().substring(email.getFrom().indexOf("@") + 1);
    }
}

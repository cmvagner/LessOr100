package com.appspot.lessor100.email;

import java.io.InputStream;

public interface EmailReceiver {

    /**
     * Will process the inputStream, create the email including attachments and
     * store it all.
     *
     * @param inputStream the email stream
     * @return the stored email
     */
    Email receive(InputStream inputStream);
}

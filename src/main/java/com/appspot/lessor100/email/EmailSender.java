package com.appspot.lessor100.email;

public interface EmailSender {

    void sendErrorReportEmail(Exception exception, String email);

    void sendEmail(String receiver, String subject, String body);
}

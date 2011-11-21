package com.appspot.lessor100.email;

public interface EmailSender {

    void sendErrorReportEmail(Exception exception, String email);
}

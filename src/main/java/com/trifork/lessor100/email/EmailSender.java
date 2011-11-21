package com.trifork.lessor100.email;

public interface EmailSender {

    void sendErrorReportEmail(Exception exception, String email);
}

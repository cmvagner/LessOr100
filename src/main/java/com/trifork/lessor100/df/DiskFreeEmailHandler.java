package com.trifork.lessor100.df;

import com.trifork.lessor100.email.Email;

public interface DiskFreeEmailHandler {

    void process(Email email);
}

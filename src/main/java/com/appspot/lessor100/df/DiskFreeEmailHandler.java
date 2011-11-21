package com.appspot.lessor100.df;

import com.appspot.lessor100.email.Email;

public interface DiskFreeEmailHandler {

    void process(Email email);
}

package com.appspot.lessor100.email;

import com.appspot.lessor100.Constants;

public enum AppMail {
    /**
     * DF corresponds to unix command df (disk free)
     */
    DF {
        @Override
        String getAddress() {
            return String.format("df@%s", Constants.APP_MAIL_DOMAIN);
        }
    };

    abstract String getAddress();

    public static AppMail valueOf(Email email) {
        for (AppMail appMail : values()) {
            if (appMail.getAddress().equals(email.getTo().trim())) {
                return appMail;
            }
        }
        return null;
    }
}

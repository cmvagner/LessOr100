package com.appspot.lessor100.controller.email;

import org.junit.Ignore;
import org.junit.Test;
import org.slim3.tester.ControllerTestCase;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class TestIndexController extends ControllerTestCase {

    @Test
    @Ignore
    public void run() throws Exception {
        tester.start("/email/");
        IncomingController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertFalse(tester.isRedirect());
        assertThat(tester.getDestinationPath(), is("/email/incoming.jsp"));
    }
}

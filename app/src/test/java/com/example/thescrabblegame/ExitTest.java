/*package com.example.thescrabblegame;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;

/**
 * Exit unit test to test System.exit(0).
 *
 *

public class ExitTest {
    public static String message;

    public static void doSomethingAndExit() {
        message = "exit ...";
        System.exit(1);
    }

    public static void doNothing() {
    }
}
public class AppWithExitTest {
    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void exits() {
        exit.expectSystemExit();
        AppWithExit.doSomethingAndExit();
    }

    @Test
    public void exitsWithStatusCode1() {
        exit.expectSystemExitWithStatus(1);
        AppWithExit.doSomethingAndExit();
    }

    @Test
    public void writesMessage() {
        exit.expectSystemExitWithStatus(1);
        exit.checkAssertionAfterwards(new Assertion() {
            public void checkAssertion() {
                assertEquals("exit ...", AppWithExit.message);
            }
        });
        AppWithExit.doSomethingAndExit();
    }

    @Test
    public void systemExitWithStatusCode1() {
        exit.expectSystemExitWithStatus(1);
        AppWithExit.doSomethingAndExit();
    }

    @Test
    public void noSystemExit() {
        AppWithExit.doNothing();
        //passes
    }
}
*/
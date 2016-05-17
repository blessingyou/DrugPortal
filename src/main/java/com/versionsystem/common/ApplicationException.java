package com.versionsystem.common;

/**
 * Simulated business-logic exception indicating a desired business entity or record cannot be found.
 */
public class ApplicationException extends RuntimeException {

    public ApplicationException(String msg) {
        super(msg);
    }
}

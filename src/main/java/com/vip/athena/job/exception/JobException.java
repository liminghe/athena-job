package com.vip.athena.job.exception;

/**
 * @author waying.he
 */
public class JobException extends RuntimeException {

    public JobException(Throwable cause) {
        super(cause);
    }

    public JobException(String message) {
        super(message);
    }
}

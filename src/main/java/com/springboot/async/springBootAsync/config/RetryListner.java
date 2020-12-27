package com.springboot.async.springBootAsync.config;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.listener.RetryListenerSupport;

import java.util.logging.Logger;

public class RetryListner extends RetryListenerSupport {
    Logger logger = Logger.getLogger(RetryListner.class.toString());

    //This will occur after a successful request or max errors
    @Override
    public <T, E extends Throwable> void close(RetryContext context,
                                               RetryCallback<T, E> callback, Throwable throwable) {
        logger.info("After Req");
        super.close(context, callback, throwable);
    }

    //When there is a runtime exception thrown by the code
    @Override
    public <T, E extends Throwable> void onError(RetryContext context,
                                                 RetryCallback<T, E> callback, Throwable throwable) {
        logger.info("Service Error");
        super.onError(context, callback, throwable);
    }

    @Override
    public <T, E extends Throwable> boolean open(RetryContext context,
                                                 RetryCallback<T, E> callback) {
        logger.info("Before Req");
        return super.open(context, callback);
    }
}

package com.mogsev.simpleprojects.urils;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * Created by Eugene Sikaylo (mogsev@gmail.com).
 */

public class ResponseWrapper {

    private volatile AuthHeader mAuthHeader;

    private static final AtomicReferenceFieldUpdater<ResponseWrapper, AuthHeader> updater =
            AtomicReferenceFieldUpdater.newUpdater(
                    ResponseWrapper.class, AuthHeader.class, "mAuthHeader");

    public ResponseWrapper() {

    }

    public AuthHeader getAuthHeader() {
        return mAuthHeader;
    }

    public void setAuthHeader(AuthHeader authHeader) {
        updater.compareAndSet(this, this.mAuthHeader, authHeader);
    }

    @Override
    public String toString() {
        return "ResponseWrapper{" +
                "mAuthHeader=" + mAuthHeader +
                '}';
    }
}

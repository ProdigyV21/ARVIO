package com.google.common.util.concurrent;

import com.google.common.collect.w1;
import j$.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public final class CycleDetectingLockFactory$PotentialDeadlockException extends IllegalStateException {
    static {
        w1.k(3, r0.class.getName(), "com.google.common.util.concurrent.CycleDetectingLockFactory$PotentialDeadlockException", p0.class.getName());
    }

    @Override // java.lang.Throwable
    public final String getMessage() {
        String message = super.getMessage();
        Objects.requireNonNull(message);
        StringBuilder sb2 = new StringBuilder(message);
        for (Throwable cause = null; cause != null; cause = cause.getCause()) {
            sb2.append(", ");
            sb2.append(cause.getMessage());
        }
        return sb2.toString();
    }
}

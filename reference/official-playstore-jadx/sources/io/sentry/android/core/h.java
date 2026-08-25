package io.sentry.android.core;

import io.sentry.s3;

/* JADX INFO: loaded from: classes5.dex */
public abstract /* synthetic */ class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f16495a;

    static {
        int[] iArr = new int[s3.values().length];
        f16495a = iArr;
        try {
            iArr[s3.TRACE.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f16495a[s3.MANUAL.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
    }
}

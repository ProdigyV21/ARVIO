package io.sentry.android.core;

import io.sentry.o0;

/* JADX INFO: loaded from: classes5.dex */
public abstract /* synthetic */ class t0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f16748a;

    static {
        int[] iArr = new int[o0.a.values().length];
        f16748a = iArr;
        try {
            iArr[o0.a.DISCONNECTED.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f16748a[o0.a.CONNECTED.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
    }
}

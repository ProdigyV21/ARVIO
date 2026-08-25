package io.sentry.android.core;

import io.sentry.o0;

/* JADX INFO: loaded from: classes5.dex */
public abstract /* synthetic */ class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f16827a;

    static {
        int[] iArr = new int[o0.a.values().length];
        f16827a = iArr;
        try {
            iArr[o0.a.CONNECTED.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f16827a[o0.a.UNKNOWN.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f16827a[o0.a.NO_PERMISSION.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
    }
}

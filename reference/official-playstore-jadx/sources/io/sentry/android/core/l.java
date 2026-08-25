package io.sentry.android.core;

import io.sentry.w5;

/* JADX INFO: loaded from: classes5.dex */
public abstract /* synthetic */ class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f16664a;

    static {
        int[] iArr = new int[w5.values().length];
        f16664a = iArr;
        try {
            iArr[w5.INFO.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f16664a[w5.WARNING.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f16664a[w5.ERROR.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            f16664a[w5.FATAL.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            f16664a[w5.DEBUG.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
    }
}

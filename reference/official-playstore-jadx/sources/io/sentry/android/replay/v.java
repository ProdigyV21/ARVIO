package io.sentry.android.replay;

import io.sentry.k4;

/* JADX INFO: loaded from: classes5.dex */
public abstract /* synthetic */ class v {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f17055a;

    static {
        int[] iArr = new int[k4.values().length];
        try {
            iArr[k4.CANVAS.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[k4.PIXEL_COPY.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        f17055a = iArr;
    }
}

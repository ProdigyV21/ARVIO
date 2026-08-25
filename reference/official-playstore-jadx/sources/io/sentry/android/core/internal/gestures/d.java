package io.sentry.android.core.internal.gestures;

/* JADX INFO: loaded from: classes5.dex */
public abstract /* synthetic */ class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f16536a;

    static {
        int[] iArr = new int[e.values().length];
        f16536a = iArr;
        try {
            iArr[e.Click.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f16536a[e.Scroll.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f16536a[e.Swipe.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            f16536a[e.Unknown.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
    }
}

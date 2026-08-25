package io.sentry.android.replay.util;

/* JADX INFO: loaded from: classes5.dex */
public abstract /* synthetic */ class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f17054a;

    static {
        int[] iArr = new int[h.values().length];
        try {
            iArr[h.SOC_MODEL.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[h.SOC_MANUFACTURER.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        f17054a = iArr;
    }
}

package io.sentry.android.replay;

/* JADX INFO: loaded from: classes5.dex */
public abstract /* synthetic */ class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f16995a;

    static {
        int[] iArr = new int[s.values().length];
        try {
            iArr[s.INITIAL.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr[s.STARTED.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr[s.RESUMED.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr[s.PAUSED.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr[s.STOPPED.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
        try {
            iArr[s.CLOSED.ordinal()] = 6;
        } catch (NoSuchFieldError unused6) {
        }
        f16995a = iArr;
    }
}

package io.sentry;

/* JADX INFO: loaded from: classes4.dex */
public abstract /* synthetic */ class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f17343a;

    static {
        int[] iArr = new int[g4.values().length];
        f17343a = iArr;
        try {
            iArr[g4.CURRENT.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f17343a[g4.ISOLATION.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f17343a[g4.GLOBAL.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
    }
}

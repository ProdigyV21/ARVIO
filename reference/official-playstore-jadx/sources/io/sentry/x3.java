package io.sentry;

/* JADX INFO: loaded from: classes4.dex */
public abstract /* synthetic */ class x3 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int[] f18052a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int[] f18053b;

    static {
        int[] iArr = new int[io.sentry.rrweb.c.values().length];
        f18053b = iArr;
        try {
            iArr[io.sentry.rrweb.c.IncrementalSnapshot.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            f18053b[io.sentry.rrweb.c.Meta.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            f18053b[io.sentry.rrweb.c.Custom.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        int[] iArr2 = new int[io.sentry.rrweb.d.values().length];
        f18052a = iArr2;
        try {
            iArr2[io.sentry.rrweb.d.MouseInteraction.ordinal()] = 1;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            f18052a[io.sentry.rrweb.d.TouchMove.ordinal()] = 2;
        } catch (NoSuchFieldError unused5) {
        }
    }
}

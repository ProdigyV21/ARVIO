package io.sentry.util;

/* JADX INFO: loaded from: classes5.dex */
public abstract class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final boolean f17967a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final boolean f17968b;

    static {
        try {
            f17967a = "The Android Project".equals(System.getProperty("java.vendor"));
        } catch (Throwable unused) {
            f17967a = false;
        }
        try {
            String property = System.getProperty("java.specification.version");
            if (property != null) {
                f17968b = Double.valueOf(property).doubleValue() >= 9.0d;
            } else {
                f17968b = false;
            }
        } catch (Throwable unused2) {
            f17968b = false;
        }
    }
}

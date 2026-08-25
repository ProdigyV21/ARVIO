package androidx.datastore.preferences.protobuf;

/* JADX INFO: loaded from: classes3.dex */
public abstract class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Class f2419a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final boolean f2420b;

    static {
        Class<?> cls;
        Class<?> cls2 = null;
        try {
            cls = Class.forName("libcore.io.Memory");
        } catch (Throwable unused) {
            cls = null;
        }
        f2419a = cls;
        try {
            cls2 = Class.forName("org.robolectric.Robolectric");
        } catch (Throwable unused2) {
        }
        f2420b = cls2 != null;
    }

    public static boolean a() {
        return (f2419a == null || f2420b) ? false : true;
    }
}

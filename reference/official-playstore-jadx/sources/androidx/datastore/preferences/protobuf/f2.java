package androidx.datastore.preferences.protobuf;

/* JADX INFO: loaded from: classes3.dex */
public abstract class f2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final d2 f2423a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final e2 f2424b;

    static {
        d2 d2Var = null;
        try {
            d2Var = (d2) Class.forName("androidx.datastore.preferences.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(null).newInstance(null);
        } catch (Exception unused) {
        }
        f2423a = d2Var;
        f2424b = new e2();
    }
}

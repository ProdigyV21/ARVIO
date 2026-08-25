package androidx.datastore.preferences.protobuf;

/* JADX INFO: loaded from: classes3.dex */
public abstract class g0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f0 f2425a = new f0();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final e0 f2426b;

    static {
        e0 e0Var = null;
        try {
            e0Var = (e0) Class.forName("androidx.datastore.preferences.protobuf.ExtensionSchemaFull").getDeclaredConstructor(null).newInstance(null);
        } catch (Exception unused) {
        }
        f2426b = e0Var;
    }
}

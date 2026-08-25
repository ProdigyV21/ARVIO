package androidx.datastore.preferences.protobuf;

/* JADX INFO: loaded from: classes3.dex */
public abstract class s1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final q1 f2526a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final r1 f2527b;

    static {
        q1 q1Var = null;
        try {
            q1Var = (q1) Class.forName("androidx.datastore.preferences.protobuf.MapFieldSchemaFull").getDeclaredConstructor(null).newInstance(null);
        } catch (Exception unused) {
        }
        f2526a = q1Var;
        f2527b = new r1();
    }
}

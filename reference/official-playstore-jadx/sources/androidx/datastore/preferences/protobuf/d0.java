package androidx.datastore.preferences.protobuf;

import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class d0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile d0 f2416b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final d0 f2417c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Map f2418a = Collections.EMPTY_MAP;

    static {
        try {
            Class.forName("androidx.datastore.preferences.protobuf.Extension");
        } catch (ClassNotFoundException unused) {
        }
        f2417c = new d0();
    }

    public static d0 a() {
        d0 d0Var;
        d0 d0Var2 = f2416b;
        if (d0Var2 != null) {
            return d0Var2;
        }
        synchronized (d0.class) {
            try {
                d0Var = f2416b;
                if (d0Var == null) {
                    Class cls = c0.f2414a;
                    if (cls != null) {
                        try {
                            d0Var = (d0) cls.getDeclaredMethod("getEmptyRegistry", null).invoke(null, null);
                        } catch (Exception unused) {
                            d0Var = f2417c;
                        }
                        f2416b = d0Var;
                    } else {
                        d0Var = f2417c;
                        f2416b = d0Var;
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return d0Var;
    }
}

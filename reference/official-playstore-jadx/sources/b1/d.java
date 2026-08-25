package b1;

import androidx.datastore.preferences.protobuf.h2;
import androidx.datastore.preferences.protobuf.m2;
import androidx.datastore.preferences.protobuf.o0;
import androidx.datastore.preferences.protobuf.p0;
import androidx.datastore.preferences.protobuf.p1;
import androidx.datastore.preferences.protobuf.r0;
import h.f0;
import java.io.FileInputStream;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class d extends r0 implements e {
    private static final d DEFAULT_INSTANCE;
    private static volatile h2<d> PARSER = null;
    public static final int PREFERENCES_FIELD_NUMBER = 1;
    private p1 preferences_ = p1.f2495l;

    static {
        d dVar = new d();
        DEFAULT_INSTANCE = dVar;
        r0.m(d.class, dVar);
    }

    public static p1 n(d dVar) {
        p1 p1Var = dVar.preferences_;
        if (!p1Var.f2496i) {
            dVar.preferences_ = p1Var.e();
        }
        return dVar.preferences_;
    }

    public static b p() {
        return (b) ((o0) DEFAULT_INSTANCE.h(5));
    }

    public static d q(FileInputStream fileInputStream) {
        return (d) r0.l(DEFAULT_INSTANCE, fileInputStream);
    }

    @Override // androidx.datastore.preferences.protobuf.r0
    public final Object h(int i10) {
        h2 p0Var;
        switch (f0.c(i10)) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return new m2(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u00012", new Object[]{"preferences_", c.f7110a});
            case 3:
                return new d();
            case 4:
                return new b(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                h2<d> h2Var = PARSER;
                if (h2Var != null) {
                    return h2Var;
                }
                synchronized (d.class) {
                    try {
                        p0Var = PARSER;
                        if (p0Var == null) {
                            p0Var = new p0();
                            PARSER = p0Var;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                    break;
                }
                return p0Var;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public final Map o() {
        return Collections.unmodifiableMap(this.preferences_);
    }
}

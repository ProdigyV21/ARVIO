package b1;

import androidx.datastore.preferences.protobuf.e1;
import androidx.datastore.preferences.protobuf.h2;
import androidx.datastore.preferences.protobuf.k2;
import androidx.datastore.preferences.protobuf.m2;
import androidx.datastore.preferences.protobuf.o0;
import androidx.datastore.preferences.protobuf.p0;
import androidx.datastore.preferences.protobuf.r0;
import h.f0;

/* JADX INFO: loaded from: classes3.dex */
public final class g extends r0 implements h {
    private static final g DEFAULT_INSTANCE;
    private static volatile h2<g> PARSER = null;
    public static final int STRINGS_FIELD_NUMBER = 1;
    private e1<String> strings_ = k2.f2458n;

    static {
        g gVar = new g();
        DEFAULT_INSTANCE = gVar;
        r0.m(g.class, gVar);
    }

    public static void n(g gVar, Iterable iterable) {
        if (!gVar.strings_.T()) {
            e1<String> e1Var = gVar.strings_;
            int size = e1Var.size();
            gVar.strings_ = e1Var.V(size == 0 ? 10 : size * 2);
        }
        androidx.datastore.preferences.protobuf.c.f(iterable, gVar.strings_);
    }

    public static g o() {
        return DEFAULT_INSTANCE;
    }

    public static f q() {
        return (f) ((o0) DEFAULT_INSTANCE.h(5));
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
                return new m2(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001a", new Object[]{"strings_"});
            case 3:
                return new g();
            case 4:
                return new f(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                h2<g> h2Var = PARSER;
                if (h2Var != null) {
                    return h2Var;
                }
                synchronized (g.class) {
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

    public final e1 p() {
        return this.strings_;
    }
}

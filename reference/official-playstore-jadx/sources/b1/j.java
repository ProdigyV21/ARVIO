package b1;

import androidx.datastore.preferences.protobuf.h2;
import androidx.datastore.preferences.protobuf.m2;
import androidx.datastore.preferences.protobuf.o0;
import androidx.datastore.preferences.protobuf.p0;
import androidx.datastore.preferences.protobuf.r0;
import h.f0;

/* JADX INFO: loaded from: classes3.dex */
public final class j extends r0 implements k {
    public static final int BOOLEAN_FIELD_NUMBER = 1;
    private static final j DEFAULT_INSTANCE;
    public static final int DOUBLE_FIELD_NUMBER = 7;
    public static final int FLOAT_FIELD_NUMBER = 2;
    public static final int INTEGER_FIELD_NUMBER = 3;
    public static final int LONG_FIELD_NUMBER = 4;
    private static volatile h2<j> PARSER = null;
    public static final int STRING_FIELD_NUMBER = 5;
    public static final int STRING_SET_FIELD_NUMBER = 6;
    private int bitField0_;
    private int valueCase_ = 0;
    private Object value_;

    static {
        j jVar = new j();
        DEFAULT_INSTANCE = jVar;
        r0.m(j.class, jVar);
    }

    public static i D() {
        return (i) ((o0) DEFAULT_INSTANCE.h(5));
    }

    public static void n(j jVar, long j10) {
        jVar.valueCase_ = 4;
        jVar.value_ = Long.valueOf(j10);
    }

    public static void o(j jVar, String str) {
        jVar.getClass();
        jVar.valueCase_ = 5;
        jVar.value_ = str;
    }

    public static void p(j jVar, f fVar) {
        jVar.getClass();
        jVar.value_ = fVar.f();
        jVar.valueCase_ = 6;
    }

    public static void q(j jVar, double d4) {
        jVar.valueCase_ = 7;
        jVar.value_ = Double.valueOf(d4);
    }

    public static void r(j jVar, boolean z) {
        jVar.valueCase_ = 1;
        jVar.value_ = Boolean.valueOf(z);
    }

    public static void s(j jVar, float f10) {
        jVar.valueCase_ = 2;
        jVar.value_ = Float.valueOf(f10);
    }

    public static void t(j jVar, int i10) {
        jVar.valueCase_ = 3;
        jVar.value_ = Integer.valueOf(i10);
    }

    public static j v() {
        return DEFAULT_INSTANCE;
    }

    public final String A() {
        return this.valueCase_ == 5 ? (String) this.value_ : "";
    }

    public final g B() {
        return this.valueCase_ == 6 ? (g) this.value_ : g.o();
    }

    public final int C() {
        switch (this.valueCase_) {
            case 0:
                return 8;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            case 4:
                return 4;
            case 5:
                return 5;
            case 6:
                return 6;
            case 7:
                return 7;
            default:
                return 0;
        }
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
                return new m2(DEFAULT_INSTANCE, "\u0001\u0007\u0001\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001:\u0000\u00024\u0000\u00037\u0000\u00045\u0000\u0005;\u0000\u0006<\u0000\u00073\u0000", new Object[]{"value_", "valueCase_", "bitField0_", g.class});
            case 3:
                return new j();
            case 4:
                return new i(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                h2<j> h2Var = PARSER;
                if (h2Var != null) {
                    return h2Var;
                }
                synchronized (j.class) {
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

    public final boolean u() {
        if (this.valueCase_ == 1) {
            return ((Boolean) this.value_).booleanValue();
        }
        return false;
    }

    public final double w() {
        if (this.valueCase_ == 7) {
            return ((Double) this.value_).doubleValue();
        }
        return 0.0d;
    }

    public final float x() {
        if (this.valueCase_ == 2) {
            return ((Float) this.value_).floatValue();
        }
        return 0.0f;
    }

    public final int y() {
        if (this.valueCase_ == 3) {
            return ((Integer) this.value_).intValue();
        }
        return 0;
    }

    public final long z() {
        if (this.valueCase_ == 4) {
            return ((Long) this.value_).longValue();
        }
        return 0L;
    }
}

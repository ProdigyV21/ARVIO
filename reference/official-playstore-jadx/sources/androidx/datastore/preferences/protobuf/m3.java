package androidx.datastore.preferences.protobuf;

import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes3.dex */
public final class m3 extends o3 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f2478b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ m3(Unsafe unsafe, int i10) {
        super(unsafe);
        this.f2478b = i10;
    }

    @Override // androidx.datastore.preferences.protobuf.o3
    public final boolean c(long j10, Object obj) {
        switch (this.f2478b) {
            case 0:
                if (p3.f2504h) {
                    if (p3.g(j10, obj) == 0) {
                    }
                } else if (p3.h(j10, obj) == 0) {
                }
                break;
            default:
                if (p3.f2504h) {
                    if (p3.g(j10, obj) == 0) {
                    }
                } else if (p3.h(j10, obj) == 0) {
                }
                break;
        }
        return false;
    }

    @Override // androidx.datastore.preferences.protobuf.o3
    public final byte d(long j10, Object obj) {
        switch (this.f2478b) {
            case 0:
                if (!p3.f2504h) {
                }
                break;
            default:
                if (!p3.f2504h) {
                }
                break;
        }
        return p3.h(j10, obj);
    }

    @Override // androidx.datastore.preferences.protobuf.o3
    public final double e(long j10, Object obj) {
        switch (this.f2478b) {
        }
        return Double.longBitsToDouble(h(j10, obj));
    }

    @Override // androidx.datastore.preferences.protobuf.o3
    public final float f(long j10, Object obj) {
        switch (this.f2478b) {
        }
        return Float.intBitsToFloat(g(j10, obj));
    }

    @Override // androidx.datastore.preferences.protobuf.o3
    public final void k(Object obj, long j10, boolean z) {
        switch (this.f2478b) {
            case 0:
                if (!p3.f2504h) {
                    p3.l(obj, j10, z ? (byte) 1 : (byte) 0);
                } else {
                    p3.k(obj, j10, z ? (byte) 1 : (byte) 0);
                }
                break;
            default:
                if (!p3.f2504h) {
                    p3.l(obj, j10, z ? (byte) 1 : (byte) 0);
                } else {
                    p3.k(obj, j10, z ? (byte) 1 : (byte) 0);
                }
                break;
        }
    }

    @Override // androidx.datastore.preferences.protobuf.o3
    public final void l(Object obj, long j10, byte b10) {
        switch (this.f2478b) {
            case 0:
                if (!p3.f2504h) {
                    p3.l(obj, j10, b10);
                } else {
                    p3.k(obj, j10, b10);
                }
                break;
            default:
                if (!p3.f2504h) {
                    p3.l(obj, j10, b10);
                } else {
                    p3.k(obj, j10, b10);
                }
                break;
        }
    }

    @Override // androidx.datastore.preferences.protobuf.o3
    public final void m(Object obj, long j10, double d4) {
        switch (this.f2478b) {
            case 0:
                p(obj, j10, Double.doubleToLongBits(d4));
                break;
            default:
                p(obj, j10, Double.doubleToLongBits(d4));
                break;
        }
    }

    @Override // androidx.datastore.preferences.protobuf.o3
    public final void n(Object obj, long j10, float f10) {
        switch (this.f2478b) {
            case 0:
                o(Float.floatToIntBits(f10), j10, obj);
                break;
            default:
                o(Float.floatToIntBits(f10), j10, obj);
                break;
        }
    }
}

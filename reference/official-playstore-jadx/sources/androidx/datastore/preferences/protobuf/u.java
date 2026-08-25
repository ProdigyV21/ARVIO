package androidx.datastore.preferences.protobuf;

import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes3.dex */
public abstract class u extends x1 {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final Logger f2533d = Logger.getLogger(u.class.getName());

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final boolean f2534e = p3.f2502f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public v f2535c;

    public static int m(int i10, m mVar) {
        return n(mVar) + t(i10);
    }

    public static int n(m mVar) {
        int size = mVar.size();
        return v(size) + size;
    }

    public static int o(int i10) {
        return t(i10) + 4;
    }

    public static int p(int i10) {
        return t(i10) + 8;
    }

    public static int q(int i10, v1 v1Var, o2 o2Var) {
        return ((c) v1Var).g(o2Var) + (t(i10) * 2);
    }

    public static int r(int i10) {
        if (i10 >= 0) {
            return v(i10);
        }
        return 10;
    }

    public static int s(String str) {
        int length;
        try {
            length = s3.b(str);
        } catch (r3 unused) {
            length = str.getBytes(f1.f2421a).length;
        }
        return v(length) + length;
    }

    public static int t(int i10) {
        return v(i10 << 3);
    }

    public static int u(int i10, int i11) {
        return v(i11) + t(i10);
    }

    public static int v(int i10) {
        if ((i10 & (-128)) == 0) {
            return 1;
        }
        if ((i10 & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i10) == 0) {
            return 3;
        }
        return (i10 & (-268435456)) == 0 ? 4 : 5;
    }

    public static int w(long j10) {
        int i10;
        if (((-128) & j10) == 0) {
            return 1;
        }
        if (j10 < 0) {
            return 10;
        }
        if (((-34359738368L) & j10) != 0) {
            j10 >>>= 28;
            i10 = 6;
        } else {
            i10 = 2;
        }
        if (((-2097152) & j10) != 0) {
            i10 += 2;
            j10 >>>= 14;
        }
        return (j10 & (-16384)) != 0 ? i10 + 1 : i10;
    }

    public abstract void A(int i10, byte[] bArr);

    public abstract void B(int i10, m mVar);

    public abstract void C(m mVar);

    public abstract void D(int i10, int i11);

    public abstract void E(int i10);

    public abstract void F(int i10, long j10);

    public abstract void G(long j10);

    public abstract void H(int i10, int i11);

    public abstract void I(int i10);

    public abstract void J(int i10, v1 v1Var, o2 o2Var);

    public abstract void K(v1 v1Var);

    public abstract void L(int i10, String str);

    public abstract void M(String str);

    public abstract void N(int i10, int i11);

    public abstract void O(int i10, int i11);

    public abstract void P(int i10);

    public abstract void Q(int i10, long j10);

    public abstract void R(long j10);

    public final void x(String str, r3 r3Var) throws CodedOutputStream$OutOfSpaceException {
        f2533d.log(Level.WARNING, "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) r3Var);
        byte[] bytes = str.getBytes(f1.f2421a);
        try {
            P(bytes.length);
            l(0, bytes.length, bytes);
        } catch (CodedOutputStream$OutOfSpaceException e5) {
            throw e5;
        } catch (IndexOutOfBoundsException e6) {
            throw new CodedOutputStream$OutOfSpaceException(e6);
        }
    }

    public abstract void y(byte b10);

    public abstract void z(int i10, boolean z);
}

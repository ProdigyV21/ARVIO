package androidx.datastore.preferences.protobuf;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes3.dex */
public final class k0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final r2 f2456a = new r2(16);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f2457b;

    static {
        new k0(0);
    }

    public k0() {
    }

    public static int a(y3 y3Var, int i10, Object obj) {
        int size;
        int iV;
        int iT = u.t(i10);
        if (y3Var == y3.f2566n) {
            iT *= 2;
        }
        int iW = 1;
        switch (y3Var.ordinal()) {
            case 0:
                ((Double) obj).getClass();
                Logger logger = u.f2533d;
                iW = 8;
                return iW + iT;
            case 1:
                ((Float) obj).getClass();
                Logger logger2 = u.f2533d;
                iW = 4;
                return iW + iT;
            case 2:
                iW = u.w(((Long) obj).longValue());
                return iW + iT;
            case 3:
                iW = u.w(((Long) obj).longValue());
                return iW + iT;
            case 4:
                iW = u.r(((Integer) obj).intValue());
                return iW + iT;
            case 5:
                ((Long) obj).getClass();
                Logger logger3 = u.f2533d;
                iW = 8;
                return iW + iT;
            case 6:
                ((Integer) obj).getClass();
                Logger logger4 = u.f2533d;
                iW = 4;
                return iW + iT;
            case 7:
                ((Boolean) obj).getClass();
                Logger logger5 = u.f2533d;
                return iW + iT;
            case 8:
                if (!(obj instanceof m)) {
                    iW = u.s((String) obj);
                    return iW + iT;
                }
                Logger logger6 = u.f2533d;
                size = ((m) obj).size();
                iV = u.v(size);
                iW = iV + size;
                return iW + iT;
            case 9:
                Logger logger7 = u.f2533d;
                iW = ((v1) obj).a();
                return iW + iT;
            case 10:
                Logger logger8 = u.f2533d;
                size = ((v1) obj).a();
                iV = u.v(size);
                iW = iV + size;
                return iW + iT;
            case 11:
                if (obj instanceof m) {
                    Logger logger9 = u.f2533d;
                    size = ((m) obj).size();
                    iV = u.v(size);
                } else {
                    Logger logger10 = u.f2533d;
                    size = ((byte[]) obj).length;
                    iV = u.v(size);
                }
                iW = iV + size;
                return iW + iT;
            case 12:
                iW = u.v(((Integer) obj).intValue());
                return iW + iT;
            case 13:
                iW = obj instanceof w0 ? u.r(((w0) obj).getNumber()) : u.r(((Integer) obj).intValue());
                return iW + iT;
            case 14:
                ((Integer) obj).getClass();
                Logger logger11 = u.f2533d;
                iW = 4;
                return iW + iT;
            case 15:
                ((Long) obj).getClass();
                Logger logger12 = u.f2533d;
                iW = 8;
                return iW + iT;
            case 16:
                int iIntValue = ((Integer) obj).intValue();
                iW = u.v((iIntValue >> 31) ^ (iIntValue << 1));
                return iW + iT;
            case 17:
                long jLongValue = ((Long) obj).longValue();
                iW = u.w((jLongValue >> 63) ^ (jLongValue << 1));
                return iW + iT;
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static void d(y3 y3Var, Object obj) {
        Charset charset = f1.f2421a;
        obj.getClass();
        boolean z = true;
        switch (y3Var.f2569i.ordinal()) {
            case 0:
                z = obj instanceof Integer;
                break;
            case 1:
                z = obj instanceof Long;
                break;
            case 2:
                z = obj instanceof Float;
                break;
            case 3:
                z = obj instanceof Double;
                break;
            case 4:
                z = obj instanceof Boolean;
                break;
            case 5:
                z = obj instanceof String;
                break;
            case 6:
                if (!(obj instanceof m) && !(obj instanceof byte[])) {
                    z = false;
                }
                break;
            case 7:
                if (!(obj instanceof Integer) && !(obj instanceof w0)) {
                    z = false;
                }
                break;
            case 8:
                if (!(obj instanceof v1)) {
                    z = false;
                }
                break;
            default:
                z = false;
                break;
        }
        if (!z) {
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
    }

    public static void e(u uVar, y3 y3Var, int i10, Object obj) {
        if (y3Var == y3.f2566n) {
            uVar.N(i10, 3);
            ((v1) obj).d(uVar);
            uVar.N(i10, 4);
        }
        uVar.N(i10, y3Var.f2570l);
        switch (y3Var.ordinal()) {
            case 0:
                uVar.G(Double.doubleToRawLongBits(((Double) obj).doubleValue()));
                break;
            case 1:
                uVar.E(Float.floatToRawIntBits(((Float) obj).floatValue()));
                break;
            case 2:
                uVar.R(((Long) obj).longValue());
                break;
            case 3:
                uVar.R(((Long) obj).longValue());
                break;
            case 4:
                uVar.I(((Integer) obj).intValue());
                break;
            case 5:
                uVar.G(((Long) obj).longValue());
                break;
            case 6:
                uVar.E(((Integer) obj).intValue());
                break;
            case 7:
                uVar.y(((Boolean) obj).booleanValue() ? (byte) 1 : (byte) 0);
                break;
            case 8:
                if (!(obj instanceof m)) {
                    uVar.M((String) obj);
                } else {
                    uVar.C((m) obj);
                }
                break;
            case 9:
                ((v1) obj).d(uVar);
                break;
            case 10:
                uVar.K((v1) obj);
                break;
            case 11:
                if (!(obj instanceof m)) {
                    byte[] bArr = (byte[]) obj;
                    uVar.A(bArr.length, bArr);
                } else {
                    uVar.C((m) obj);
                }
                break;
            case 12:
                uVar.P(((Integer) obj).intValue());
                break;
            case 13:
                if (!(obj instanceof w0)) {
                    uVar.I(((Integer) obj).intValue());
                } else {
                    uVar.I(((w0) obj).getNumber());
                }
                break;
            case 14:
                uVar.E(((Integer) obj).intValue());
                break;
            case 15:
                uVar.G(((Long) obj).longValue());
                break;
            case 16:
                int iIntValue = ((Integer) obj).intValue();
                uVar.P((iIntValue >> 31) ^ (iIntValue << 1));
                break;
            case 17:
                long jLongValue = ((Long) obj).longValue();
                uVar.R((jLongValue >> 63) ^ (jLongValue << 1));
                break;
        }
    }

    public final void b() {
        if (this.f2457b) {
            return;
        }
        r2 r2Var = this.f2456a;
        if (!r2Var.f2520n) {
            for (int i10 = 0; i10 < r2Var.f2518l.size(); i10++) {
                Map.Entry entryE = r2Var.e(i10);
                if (((j0) entryE.getKey()).b()) {
                    entryE.setValue(Collections.unmodifiableList((List) entryE.getValue()));
                }
            }
            for (Map.Entry entry : r2Var.g()) {
                if (((j0) entry.getKey()).b()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        if (!r2Var.f2520n) {
            r2Var.f2519m = r2Var.f2519m.isEmpty() ? Collections.EMPTY_MAP : Collections.unmodifiableMap(r2Var.f2519m);
            r2Var.f2522p = r2Var.f2522p.isEmpty() ? Collections.EMPTY_MAP : Collections.unmodifiableMap(r2Var.f2522p);
            r2Var.f2520n = true;
        }
        this.f2457b = true;
    }

    public final void c(j0 j0Var, Object obj) {
        if (!j0Var.b()) {
            d(j0Var.c(), obj);
        } else {
            if (!(obj instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                d(j0Var.c(), it.next());
            }
            obj = arrayList;
        }
        this.f2456a.put(j0Var, obj);
    }

    public final Object clone() {
        r2 r2Var;
        k0 k0Var = new k0();
        int i10 = 0;
        while (true) {
            r2Var = this.f2456a;
            if (i10 >= r2Var.f2518l.size()) {
                break;
            }
            Map.Entry entryE = r2Var.e(i10);
            k0Var.c((j0) entryE.getKey(), entryE.getValue());
            i10++;
        }
        for (Map.Entry entry : r2Var.g()) {
            k0Var.c((j0) entry.getKey(), entry.getValue());
        }
        return k0Var;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof k0) {
            return this.f2456a.equals(((k0) obj).f2456a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f2456a.hashCode();
    }

    public k0(int i10) {
        b();
        b();
    }
}

package kotlin.reflect.jvm.internal.impl.protobuf;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public final class n {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final n f19811c = new n(0);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i0 f19812a = new i0(16);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f19813b;

    public n() {
    }

    public static int c(u0 u0Var, Object obj) {
        switch (u0Var.ordinal()) {
            case 0:
                ((Double) obj).getClass();
                return 8;
            case 1:
                ((Float) obj).getClass();
                return 4;
            case 2:
                return j.g(((Long) obj).longValue());
            case 3:
                return j.g(((Long) obj).longValue());
            case 4:
                return j.c(((Integer) obj).intValue());
            case 5:
                ((Long) obj).getClass();
                return 8;
            case 6:
                ((Integer) obj).getClass();
                return 4;
            case 7:
                ((Boolean) obj).getClass();
                return 1;
            case 8:
                try {
                    byte[] bytes = ((String) obj).getBytes("UTF-8");
                    return j.f(bytes.length) + bytes.length;
                } catch (UnsupportedEncodingException e5) {
                    throw new RuntimeException("UTF-8 not supported.", e5);
                }
            case 9:
                return ((c0) obj).a();
            case 10:
                return j.e((c0) obj);
            case 11:
                if (obj instanceof g) {
                    g gVar = (g) obj;
                    return gVar.size() + j.f(gVar.size());
                }
                byte[] bArr = (byte[]) obj;
                return j.f(bArr.length) + bArr.length;
            case 12:
                return j.f(((Integer) obj).intValue());
            case 13:
                return obj instanceof v ? j.c(((v) obj).getNumber()) : j.c(((Integer) obj).intValue());
            case 14:
                ((Integer) obj).getClass();
                return 4;
            case 15:
                ((Long) obj).getClass();
                return 8;
            case 16:
                int iIntValue = ((Integer) obj).intValue();
                return j.f((iIntValue >> 31) ^ (iIntValue << 1));
            case 17:
                long jLongValue = ((Long) obj).longValue();
                return j.g((jLongValue >> 63) ^ (jLongValue << 1));
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    public static int d(m mVar, Object obj) {
        int iH;
        int iC;
        u0 u0VarC = mVar.c();
        int number = mVar.getNumber();
        if (mVar.b()) {
            int iC2 = 0;
            if (!mVar.h()) {
                for (Object obj2 : (List) obj) {
                    int iH2 = j.h(number);
                    if (u0VarC == u0.f19831o) {
                        iH2 *= 2;
                    }
                    iC2 += c(u0VarC, obj2) + iH2;
                }
                return iC2;
            }
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                iC2 += c(u0VarC, it.next());
            }
            iH = j.h(number) + iC2;
            iC = j.f(iC2);
        } else {
            iH = j.h(number);
            if (u0VarC == u0.f19831o) {
                iH *= 2;
            }
            iC = c(u0VarC, obj);
        }
        return iC + iH;
    }

    public static boolean e(Map.Entry entry) {
        m mVar = (m) entry.getKey();
        if (mVar.g() != v0.f19845t) {
            return true;
        }
        if (!mVar.b()) {
            Object value = entry.getValue();
            if (value instanceof c0) {
                return ((c0) value).isInitialized();
            }
            throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
        }
        Iterator it = ((List) entry.getValue()).iterator();
        while (it.hasNext()) {
            if (!((c0) it.next()).isInitialized()) {
                return false;
            }
        }
        return true;
    }

    public static Object h(i iVar, u0 u0Var) {
        switch (u0Var.ordinal()) {
            case 0:
                return Double.valueOf(Double.longBitsToDouble(iVar.j()));
            case 1:
                return Float.valueOf(Float.intBitsToFloat(iVar.i()));
            case 2:
                return Long.valueOf(iVar.l());
            case 3:
                return Long.valueOf(iVar.l());
            case 4:
                return Integer.valueOf(iVar.k());
            case 5:
                return Long.valueOf(iVar.j());
            case 6:
                return Integer.valueOf(iVar.i());
            case 7:
                return Boolean.valueOf(iVar.l() != 0);
            case 8:
                int iK = iVar.k();
                int i10 = iVar.f19784b;
                int i11 = iVar.f19786d;
                if (iK > i10 - i11 || iK <= 0) {
                    return iK == 0 ? "" : new String(iVar.h(iK), "UTF-8");
                }
                String str = new String(iVar.f19783a, i11, iK, "UTF-8");
                iVar.f19786d += iK;
                return str;
            case 9:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle nested groups.");
            case 10:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle embedded messages.");
            case 11:
                return iVar.e();
            case 12:
                return Integer.valueOf(iVar.k());
            case 13:
                throw new IllegalArgumentException("readPrimitiveField() cannot handle enums.");
            case 14:
                return Integer.valueOf(iVar.i());
            case 15:
                return Long.valueOf(iVar.j());
            case 16:
                int iK2 = iVar.k();
                return Integer.valueOf((-(iK2 & 1)) ^ (iK2 >>> 1));
            case 17:
                long jL = iVar.l();
                return Long.valueOf((-(jL & 1)) ^ (jL >>> 1));
            default:
                throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void j(kotlin.reflect.jvm.internal.impl.protobuf.u0 r2, java.lang.Object r3) {
        /*
            r3.getClass()
            kotlin.reflect.jvm.internal.impl.protobuf.v0 r2 = r2.f19835i
            int r2 = r2.ordinal()
            r0 = 1
            r1 = 0
            switch(r2) {
                case 0: goto L36;
                case 1: goto L33;
                case 2: goto L30;
                case 3: goto L2d;
                case 4: goto L2a;
                case 5: goto L27;
                case 6: goto L1e;
                case 7: goto L12;
                case 8: goto Lf;
                default: goto Le;
            }
        Le:
            goto L38
        Lf:
            boolean r1 = r3 instanceof kotlin.reflect.jvm.internal.impl.protobuf.c0
            goto L38
        L12:
            boolean r2 = r3 instanceof java.lang.Integer
            if (r2 != 0) goto L1c
            boolean r2 = r3 instanceof kotlin.reflect.jvm.internal.impl.protobuf.v
            if (r2 == 0) goto L1b
            goto L1c
        L1b:
            r0 = r1
        L1c:
            r1 = r0
            goto L38
        L1e:
            boolean r2 = r3 instanceof kotlin.reflect.jvm.internal.impl.protobuf.g
            if (r2 != 0) goto L1c
            boolean r2 = r3 instanceof byte[]
            if (r2 == 0) goto L1b
            goto L1c
        L27:
            boolean r1 = r3 instanceof java.lang.String
            goto L38
        L2a:
            boolean r1 = r3 instanceof java.lang.Boolean
            goto L38
        L2d:
            boolean r1 = r3 instanceof java.lang.Double
            goto L38
        L30:
            boolean r1 = r3 instanceof java.lang.Float
            goto L38
        L33:
            boolean r1 = r3 instanceof java.lang.Long
            goto L38
        L36:
            boolean r1 = r3 instanceof java.lang.Integer
        L38:
            if (r1 == 0) goto L3b
            return
        L3b:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r3 = "Wrong object type used with protocol message reflection."
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.reflect.jvm.internal.impl.protobuf.n.j(kotlin.reflect.jvm.internal.impl.protobuf.u0, java.lang.Object):void");
    }

    public static void k(j jVar, u0 u0Var, Object obj) {
        switch (u0Var.ordinal()) {
            case 0:
                double dDoubleValue = ((Double) obj).doubleValue();
                jVar.getClass();
                jVar.u(Double.doubleToRawLongBits(dDoubleValue));
                break;
            case 1:
                float fFloatValue = ((Float) obj).floatValue();
                jVar.getClass();
                jVar.t(Float.floatToRawIntBits(fFloatValue));
                break;
            case 2:
                jVar.w(((Long) obj).longValue());
                break;
            case 3:
                jVar.w(((Long) obj).longValue());
                break;
            case 4:
                jVar.n(((Integer) obj).intValue());
                break;
            case 5:
                jVar.u(((Long) obj).longValue());
                break;
            case 6:
                jVar.t(((Integer) obj).intValue());
                break;
            case 7:
                jVar.q(((Boolean) obj).booleanValue() ? 1 : 0);
                break;
            case 8:
                jVar.getClass();
                byte[] bytes = ((String) obj).getBytes("UTF-8");
                jVar.v(bytes.length);
                jVar.s(bytes);
                break;
            case 9:
                jVar.getClass();
                ((c0) obj).e(jVar);
                break;
            case 10:
                jVar.p((c0) obj);
                break;
            case 11:
                if (!(obj instanceof g)) {
                    byte[] bArr = (byte[]) obj;
                    jVar.getClass();
                    jVar.v(bArr.length);
                    jVar.s(bArr);
                } else {
                    g gVar = (g) obj;
                    jVar.getClass();
                    jVar.v(gVar.size());
                    jVar.r(gVar);
                }
                break;
            case 12:
                jVar.v(((Integer) obj).intValue());
                break;
            case 13:
                if (!(obj instanceof v)) {
                    jVar.n(((Integer) obj).intValue());
                } else {
                    jVar.n(((v) obj).getNumber());
                }
                break;
            case 14:
                jVar.t(((Integer) obj).intValue());
                break;
            case 15:
                jVar.u(((Long) obj).longValue());
                break;
            case 16:
                int iIntValue = ((Integer) obj).intValue();
                jVar.v((iIntValue >> 31) ^ (iIntValue << 1));
                break;
            case 17:
                long jLongValue = ((Long) obj).longValue();
                jVar.w((jLongValue >> 63) ^ (jLongValue << 1));
                break;
        }
    }

    public final void a(s sVar, Object obj) {
        List arrayList;
        if (!sVar.f19823m) {
            throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields.");
        }
        j(sVar.f19822l, obj);
        i0 i0Var = this.f19812a;
        Object obj2 = i0Var.get(sVar);
        if (obj2 == null) {
            arrayList = new ArrayList();
            i0Var.put(sVar, arrayList);
        } else {
            arrayList = (List) obj2;
        }
        arrayList.add(obj);
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final n clone() {
        i0 i0Var;
        n nVar = new n();
        int i10 = 0;
        while (true) {
            i0Var = this.f19812a;
            if (i10 >= i0Var.f19794l.size()) {
                break;
            }
            Map.Entry entry = (Map.Entry) i0Var.f19794l.get(i10);
            nVar.i((m) entry.getKey(), entry.getValue());
            i10++;
        }
        for (Map.Entry entry2 : i0Var.e()) {
            nVar.i((m) entry2.getKey(), entry2.getValue());
        }
        return nVar;
    }

    public final void f() {
        if (this.f19813b) {
            return;
        }
        i0 i0Var = this.f19812a;
        if (!i0Var.f19796n) {
            for (int i10 = 0; i10 < i0Var.f19794l.size(); i10++) {
                Map.Entry entry = (Map.Entry) i0Var.f19794l.get(i10);
                if (((m) entry.getKey()).b()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
            for (Map.Entry entry2 : i0Var.e()) {
                if (((m) entry2.getKey()).b()) {
                    entry2.setValue(Collections.unmodifiableList((List) entry2.getValue()));
                }
            }
        }
        if (!i0Var.f19796n) {
            i0Var.f19795m = i0Var.f19795m.isEmpty() ? Collections.EMPTY_MAP : Collections.unmodifiableMap(i0Var.f19795m);
            i0Var.f19796n = true;
        }
        this.f19813b = true;
    }

    public final void g(Map.Entry entry) {
        m mVar = (m) entry.getKey();
        Object value = entry.getValue();
        boolean zB = mVar.b();
        i0 i0Var = this.f19812a;
        if (zB) {
            Object arrayList = i0Var.get(mVar);
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            for (Object obj : (List) value) {
                List list = (List) arrayList;
                if (obj instanceof byte[]) {
                    byte[] bArr = (byte[]) obj;
                    byte[] bArr2 = new byte[bArr.length];
                    System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                    obj = bArr2;
                }
                list.add(obj);
            }
            i0Var.put(mVar, arrayList);
            return;
        }
        if (mVar.g() != v0.f19845t) {
            if (value instanceof byte[]) {
                byte[] bArr3 = (byte[]) value;
                byte[] bArr4 = new byte[bArr3.length];
                System.arraycopy(bArr3, 0, bArr4, 0, bArr3.length);
                value = bArr4;
            }
            i0Var.put(mVar, value);
            return;
        }
        Object obj2 = i0Var.get(mVar);
        if (obj2 != null) {
            i0Var.put(mVar, mVar.e(((c0) obj2).b(), (c0) value).build());
            return;
        }
        if (value instanceof byte[]) {
            byte[] bArr5 = (byte[]) value;
            byte[] bArr6 = new byte[bArr5.length];
            System.arraycopy(bArr5, 0, bArr6, 0, bArr5.length);
            value = bArr6;
        }
        i0Var.put(mVar, value);
    }

    public final void i(m mVar, Object obj) {
        if (!mVar.b()) {
            j(mVar.c(), obj);
        } else {
            if (!(obj instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                j(mVar.c(), it.next());
            }
            obj = arrayList;
        }
        this.f19812a.put(mVar, obj);
    }

    public n(int i10) {
        f();
    }
}

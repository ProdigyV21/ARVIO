package com.google.common.collect;

import j$.util.Objects;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class t3 extends l1 {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final t3 f14114q = new t3(new Object[0], 0, null);

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final transient Object f14115n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final transient Object[] f14116o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final transient int f14117p;

    public t3(Object[] objArr, int i10, Object obj) {
        this.f14115n = obj;
        this.f14116o = objArr;
        this.f14117p = i10;
    }

    public static t3 j(int i10, Object[] objArr, k1 k1Var) {
        if (i10 == 0) {
            return f14114q;
        }
        if (i10 == 1) {
            Objects.requireNonNull(objArr[0]);
            Objects.requireNonNull(objArr[1]);
            return new t3(objArr, 1, null);
        }
        ac.b.q(i10, objArr.length >> 1);
        Object objK = k(objArr, i10, w1.h(i10), 0);
        if (objK instanceof Object[]) {
            Object[] objArr2 = (Object[]) objK;
            j1 j1Var = (j1) objArr2[2];
            if (k1Var == null) {
                throw j1Var.a();
            }
            k1Var.f14042c = j1Var;
            Object obj = objArr2[0];
            int iIntValue = ((Integer) objArr2[1]).intValue();
            objArr = Arrays.copyOf(objArr, iIntValue * 2);
            objK = obj;
            i10 = iIntValue;
        }
        return new t3(objArr, i10, objK);
    }

    public static Object k(Object[] objArr, int i10, int i11, int i12) {
        int i13;
        j1 j1Var = null;
        int i14 = 1;
        if (i10 == 1) {
            Objects.requireNonNull(objArr[i12]);
            Objects.requireNonNull(objArr[i12 ^ 1]);
            return null;
        }
        int i15 = i11 - 1;
        if (i11 <= 128) {
            byte[] bArr = new byte[i11];
            Arrays.fill(bArr, (byte) -1);
            int i16 = 0;
            for (int i17 = 0; i17 < i10; i17++) {
                int i18 = (i17 * 2) + i12;
                int i19 = (i16 * 2) + i12;
                Object obj = objArr[i18];
                Objects.requireNonNull(obj);
                Object obj2 = objArr[i18 ^ 1];
                Objects.requireNonNull(obj2);
                int iT = a0.t(obj.hashCode());
                while (true) {
                    int i20 = iT & i15;
                    int i21 = bArr[i20] & 255;
                    if (i21 == 255) {
                        bArr[i20] = (byte) i19;
                        if (i16 < i17) {
                            objArr[i19] = obj;
                            objArr[i19 ^ 1] = obj2;
                        }
                        i16++;
                    } else {
                        if (obj.equals(objArr[i21])) {
                            int i22 = i21 ^ 1;
                            Object obj3 = objArr[i22];
                            Objects.requireNonNull(obj3);
                            j1Var = new j1(obj, obj2, obj3);
                            objArr[i22] = obj2;
                            break;
                        }
                        iT = i20 + 1;
                    }
                }
            }
            return i16 == i10 ? bArr : new Object[]{bArr, Integer.valueOf(i16), j1Var};
        }
        if (i11 <= 32768) {
            short[] sArr = new short[i11];
            Arrays.fill(sArr, (short) -1);
            int i23 = 0;
            for (int i24 = 0; i24 < i10; i24++) {
                int i25 = (i24 * 2) + i12;
                int i26 = (i23 * 2) + i12;
                Object obj4 = objArr[i25];
                Objects.requireNonNull(obj4);
                Object obj5 = objArr[i25 ^ 1];
                Objects.requireNonNull(obj5);
                int iT2 = a0.t(obj4.hashCode());
                while (true) {
                    int i27 = iT2 & i15;
                    int i28 = sArr[i27] & 65535;
                    if (i28 == 65535) {
                        sArr[i27] = (short) i26;
                        if (i23 < i24) {
                            objArr[i26] = obj4;
                            objArr[i26 ^ 1] = obj5;
                        }
                        i23++;
                    } else {
                        if (obj4.equals(objArr[i28])) {
                            int i29 = i28 ^ 1;
                            Object obj6 = objArr[i29];
                            Objects.requireNonNull(obj6);
                            j1Var = new j1(obj4, obj5, obj6);
                            objArr[i29] = obj5;
                            break;
                        }
                        iT2 = i27 + 1;
                    }
                }
            }
            return i23 == i10 ? sArr : new Object[]{sArr, Integer.valueOf(i23), j1Var};
        }
        int[] iArr = new int[i11];
        Arrays.fill(iArr, -1);
        int i30 = 0;
        int i31 = 0;
        while (i30 < i10) {
            int i32 = (i30 * 2) + i12;
            int i33 = (i31 * 2) + i12;
            Object obj7 = objArr[i32];
            Objects.requireNonNull(obj7);
            Object obj8 = objArr[i32 ^ i14];
            Objects.requireNonNull(obj8);
            int iT3 = a0.t(obj7.hashCode());
            while (true) {
                int i34 = iT3 & i15;
                int i35 = iArr[i34];
                if (i35 == -1) {
                    iArr[i34] = i33;
                    if (i31 < i30) {
                        objArr[i33] = obj7;
                        objArr[i33 ^ 1] = obj8;
                    }
                    i31++;
                    i13 = i14;
                } else {
                    i13 = i14;
                    if (obj7.equals(objArr[i35])) {
                        int i36 = i35 ^ 1;
                        Object obj9 = objArr[i36];
                        Objects.requireNonNull(obj9);
                        j1Var = new j1(obj7, obj8, obj9);
                        objArr[i36] = obj8;
                        break;
                    }
                    iT3 = i34 + 1;
                    i14 = i13;
                }
            }
            i30++;
            i14 = i13;
        }
        int i37 = i14;
        if (i31 == i10) {
            return iArr;
        }
        Integer numValueOf = Integer.valueOf(i31);
        Object[] objArr2 = new Object[3];
        objArr2[0] = iArr;
        objArr2[i37] = numValueOf;
        objArr2[2] = j1Var;
        return objArr2;
    }

    public static Object l(Object[] objArr, int i10, int i11, Object obj, Object obj2) {
        if (obj2 == null) {
            return null;
        }
        if (i10 == 1) {
            Object obj3 = objArr[i11];
            Objects.requireNonNull(obj3);
            if (!obj3.equals(obj2)) {
                return null;
            }
            Object obj4 = objArr[i11 ^ 1];
            Objects.requireNonNull(obj4);
            return obj4;
        }
        if (obj == null) {
            return null;
        }
        if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            int length = bArr.length - 1;
            int iT = a0.t(obj2.hashCode());
            while (true) {
                int i12 = iT & length;
                int i13 = bArr[i12] & 255;
                if (i13 == 255) {
                    return null;
                }
                if (obj2.equals(objArr[i13])) {
                    return objArr[i13 ^ 1];
                }
                iT = i12 + 1;
            }
        } else if (obj instanceof short[]) {
            short[] sArr = (short[]) obj;
            int length2 = sArr.length - 1;
            int iT2 = a0.t(obj2.hashCode());
            while (true) {
                int i14 = iT2 & length2;
                int i15 = sArr[i14] & 65535;
                if (i15 == 65535) {
                    return null;
                }
                if (obj2.equals(objArr[i15])) {
                    return objArr[i15 ^ 1];
                }
                iT2 = i14 + 1;
            }
        } else {
            int[] iArr = (int[]) obj;
            int length3 = iArr.length - 1;
            int iT3 = a0.t(obj2.hashCode());
            while (true) {
                int i16 = iT3 & length3;
                int i17 = iArr[i16];
                if (i17 == -1) {
                    return null;
                }
                if (obj2.equals(objArr[i17])) {
                    return objArr[i17 ^ 1];
                }
                iT3 = i16 + 1;
            }
        }
    }

    @Override // com.google.common.collect.l1
    public final q3 c() {
        return new q3(this, this.f14116o, 0, this.f14117p);
    }

    @Override // com.google.common.collect.l1
    public final r3 e() {
        return new r3(this, new s3(this.f14116o, 0, this.f14117p));
    }

    @Override // com.google.common.collect.l1
    public final c1 g() {
        return new s3(this.f14116o, 1, this.f14117p);
    }

    @Override // com.google.common.collect.l1, java.util.Map
    public final Object get(Object obj) {
        Object objL = l(this.f14116o, this.f14117p, 0, this.f14115n, obj);
        if (objL == null) {
            return null;
        }
        return objL;
    }

    @Override // java.util.Map
    public final int size() {
        return this.f14117p;
    }
}

package com.google.common.collect;

import j$.util.Objects;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class f0 extends AbstractMap implements Serializable {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final Object f13987t = new Object();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public transient Object f13988i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public transient int[] f13989l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public transient Object[] f13990m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public transient Object[] f13991n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public transient int f13992o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public transient int f13993p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public transient d0 f13994q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public transient d0 f13995r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public transient s f13996s;

    public static f0 b() {
        f0 f0Var = new f0();
        f0Var.f13992o = m2.f0.h(3, 1);
        return f0Var;
    }

    public static f0 c(int i10) {
        f0 f0Var = new f0();
        ac.b.k(i10 >= 0, "Expected size must be >= 0");
        f0Var.f13992o = m2.f0.h(i10, 1);
        return f0Var;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        if (j()) {
            return;
        }
        this.f13992o += 32;
        Map mapE = e();
        if (mapE != null) {
            this.f13992o = m2.f0.h(size(), 3);
            mapE.clear();
            this.f13988i = null;
            this.f13993p = 0;
            return;
        }
        Arrays.fill(m(), 0, this.f13993p, (Object) null);
        Arrays.fill(o(), 0, this.f13993p, (Object) null);
        Object obj = this.f13988i;
        Objects.requireNonNull(obj);
        if (obj instanceof byte[]) {
            Arrays.fill((byte[]) obj, (byte) 0);
        } else if (obj instanceof short[]) {
            Arrays.fill((short[]) obj, (short) 0);
        } else {
            Arrays.fill((int[]) obj, 0);
        }
        Arrays.fill(l(), 0, this.f13993p, 0);
        this.f13993p = 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        Map mapE = e();
        return mapE != null ? mapE.containsKey(obj) : h(obj) != -1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsValue(Object obj) {
        Map mapE = e();
        if (mapE != null) {
            return mapE.containsValue(obj);
        }
        for (int i10 = 0; i10 < this.f13993p; i10++) {
            if (a.a.L(obj, o()[i10])) {
                return true;
            }
        }
        return false;
    }

    public final Map e() {
        Object obj = this.f13988i;
        if (obj instanceof Map) {
            return (Map) obj;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        d0 d0Var = this.f13995r;
        if (d0Var != null) {
            return d0Var;
        }
        d0 d0Var2 = new d0(this, 0);
        this.f13995r = d0Var2;
        return d0Var2;
    }

    public final int g() {
        return (1 << (this.f13992o & 31)) - 1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        Map mapE = e();
        if (mapE != null) {
            return mapE.get(obj);
        }
        int iH = h(obj);
        if (iH == -1) {
            return null;
        }
        return o()[iH];
    }

    public final int h(Object obj) {
        if (j()) {
            return -1;
        }
        int iU = a0.u(obj);
        int iG = g();
        Object obj2 = this.f13988i;
        Objects.requireNonNull(obj2);
        int iV = a0.v(iU & iG, obj2);
        if (iV == 0) {
            return -1;
        }
        int i10 = ~iG;
        int i11 = iU & i10;
        do {
            int i12 = iV - 1;
            int i13 = l()[i12];
            if ((i13 & i10) == i11 && a.a.L(obj, m()[i12])) {
                return i12;
            }
            iV = i13 & iG;
        } while (iV != 0);
        return -1;
    }

    public final void i(int i10, int i11) {
        Object obj = this.f13988i;
        Objects.requireNonNull(obj);
        int[] iArrL = l();
        Object[] objArrM = m();
        Object[] objArrO = o();
        int size = size();
        int i12 = size - 1;
        if (i10 >= i12) {
            objArrM[i10] = null;
            objArrO[i10] = null;
            iArrL[i10] = 0;
            return;
        }
        Object obj2 = objArrM[i12];
        objArrM[i10] = obj2;
        objArrO[i10] = objArrO[i12];
        objArrM[i12] = null;
        objArrO[i12] = null;
        iArrL[i10] = iArrL[i12];
        iArrL[i12] = 0;
        int iU = a0.u(obj2) & i11;
        int iV = a0.v(iU, obj);
        if (iV == size) {
            a0.w(iU, i10 + 1, obj);
            return;
        }
        while (true) {
            int i13 = iV - 1;
            int i14 = iArrL[i13];
            int i15 = i14 & i11;
            if (i15 == size) {
                iArrL[i13] = a0.n(i14, i10 + 1, i11);
                return;
            }
            iV = i15;
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean isEmpty() {
        return size() == 0;
    }

    public final boolean j() {
        return this.f13988i == null;
    }

    public final Object k(Object obj) {
        if (!j()) {
            int iG = g();
            Object obj2 = this.f13988i;
            Objects.requireNonNull(obj2);
            int iQ = a0.q(obj, null, iG, obj2, l(), m(), null);
            if (iQ != -1) {
                Object obj3 = o()[iQ];
                i(iQ, iG);
                this.f13993p--;
                this.f13992o += 32;
                return obj3;
            }
        }
        return f13987t;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        d0 d0Var = this.f13994q;
        if (d0Var != null) {
            return d0Var;
        }
        d0 d0Var2 = new d0(this, 1);
        this.f13994q = d0Var2;
        return d0Var2;
    }

    public final int[] l() {
        int[] iArr = this.f13989l;
        Objects.requireNonNull(iArr);
        return iArr;
    }

    public final Object[] m() {
        Object[] objArr = this.f13990m;
        Objects.requireNonNull(objArr);
        return objArr;
    }

    public final Object[] o() {
        Object[] objArr = this.f13991n;
        Objects.requireNonNull(objArr);
        return objArr;
    }

    public final int p(int i10, int i11, int i12, int i13) {
        Object objF = a0.f(i11);
        int i14 = i11 - 1;
        if (i13 != 0) {
            a0.w(i12 & i14, i13 + 1, objF);
        }
        Object obj = this.f13988i;
        Objects.requireNonNull(obj);
        int[] iArrL = l();
        for (int i15 = 0; i15 <= i10; i15++) {
            int iV = a0.v(i15, obj);
            while (iV != 0) {
                int i16 = iV - 1;
                int i17 = iArrL[i16];
                int i18 = ((~i10) & i17) | i15;
                int i19 = i18 & i14;
                int iV2 = a0.v(i19, objF);
                a0.w(i19, iV, objF);
                iArrL[i16] = a0.n(i18, iV2, i14);
                iV = i17 & i10;
            }
        }
        this.f13988i = objF;
        this.f13992o = a0.n(this.f13992o, 32 - Integer.numberOfLeadingZeros(i14), 31);
        return i14;
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00de  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:38:0x00f1 -> B:34:0x00d9). Please report as a decompilation issue!!! */
    @Override // java.util.AbstractMap, java.util.Map
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object put(java.lang.Object r20, java.lang.Object r21) {
        /*
            Method dump skipped, instruction units count: 380
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.f0.put(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        Map mapE = e();
        if (mapE != null) {
            return mapE.remove(obj);
        }
        Object objK = k(obj);
        if (objK == f13987t) {
            return null;
        }
        return objK;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        Map mapE = e();
        return mapE != null ? mapE.size() : this.f13993p;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        s sVar = this.f13996s;
        if (sVar != null) {
            return sVar;
        }
        s sVar2 = new s(this, 2);
        this.f13996s = sVar2;
        return sVar2;
    }
}

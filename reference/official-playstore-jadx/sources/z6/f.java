package z6;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.jvm.internal.p;
import qb.l;

/* JADX INFO: loaded from: classes5.dex */
public final class f implements Map, Serializable, s7.g {
    public static final f x;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object[] f23224i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object[] f23225l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int[] f23226m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int[] f23227n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public int f23228o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f23229p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f23230q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public int f23231r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public int f23232s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public g f23233t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public h f23234u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public g f23235v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public boolean f23236w;

    static {
        f fVar = new f(0);
        fVar.f23236w = true;
        x = fVar;
    }

    public f() {
        this(8);
    }

    public final int b(Object obj) {
        e();
        while (true) {
            int iK = k(obj);
            int i10 = this.f23228o * 2;
            int length = this.f23227n.length / 2;
            if (i10 > length) {
                i10 = length;
            }
            int i11 = 0;
            while (true) {
                int[] iArr = this.f23227n;
                int i12 = iArr[iK];
                if (i12 <= 0) {
                    int i13 = this.f23229p;
                    Object[] objArr = this.f23224i;
                    if (i13 < objArr.length) {
                        int i14 = i13 + 1;
                        this.f23229p = i14;
                        objArr[i13] = obj;
                        this.f23226m[i13] = iK;
                        iArr[iK] = i14;
                        this.f23232s++;
                        this.f23231r++;
                        if (i11 > this.f23228o) {
                            this.f23228o = i11;
                        }
                        return i13;
                    }
                    h(1);
                } else {
                    if (p.a(this.f23224i[i12 - 1], obj)) {
                        return -i12;
                    }
                    i11++;
                    if (i11 > i10) {
                        l(this.f23227n.length * 2);
                        break;
                    }
                    iK = iK == 0 ? this.f23227n.length - 1 : iK - 1;
                }
            }
        }
    }

    public final f c() {
        e();
        this.f23236w = true;
        return this.f23232s > 0 ? this : x;
    }

    @Override // java.util.Map
    public final void clear() {
        e();
        int i10 = this.f23229p - 1;
        if (i10 >= 0) {
            int i11 = 0;
            while (true) {
                int[] iArr = this.f23226m;
                int i12 = iArr[i11];
                if (i12 >= 0) {
                    this.f23227n[i12] = 0;
                    iArr[i11] = -1;
                }
                if (i11 == i10) {
                    break;
                } else {
                    i11++;
                }
            }
        }
        l.y(this.f23224i, 0, this.f23229p);
        Object[] objArr = this.f23225l;
        if (objArr != null) {
            l.y(objArr, 0, this.f23229p);
        }
        this.f23232s = 0;
        this.f23229p = 0;
        this.f23231r++;
    }

    @Override // java.util.Map
    public final boolean containsKey(Object obj) {
        return i(obj) >= 0;
    }

    @Override // java.util.Map
    public final boolean containsValue(Object obj) {
        int i10;
        int i11 = this.f23229p;
        while (true) {
            i10 = -1;
            i11--;
            if (i11 >= 0) {
                if (this.f23226m[i11] >= 0 && p.a(this.f23225l[i11], obj)) {
                    i10 = i11;
                    break;
                }
            } else {
                break;
            }
        }
        return i10 >= 0;
    }

    public final void e() {
        if (this.f23236w) {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.Map
    public final Set entrySet() {
        g gVar = this.f23235v;
        if (gVar != null) {
            return gVar;
        }
        g gVar2 = new g(this, 0);
        this.f23235v = gVar2;
        return gVar2;
    }

    @Override // java.util.Map
    public final boolean equals(Object obj) {
        boolean z;
        if (obj != this) {
            if (obj instanceof Map) {
                Map map = (Map) obj;
                if (this.f23232s == map.size()) {
                    for (Object obj2 : map.entrySet()) {
                        if (obj2 != null) {
                            try {
                                Map.Entry entry = (Map.Entry) obj2;
                                int i10 = i(entry.getKey());
                                if (!(i10 < 0 ? false : p.a(this.f23225l[i10], entry.getValue()))) {
                                }
                            } catch (ClassCastException unused) {
                            }
                        }
                        z = false;
                    }
                    z = true;
                    if (z) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    public final void g(boolean z) {
        int i10;
        Object[] objArr = this.f23225l;
        int i11 = 0;
        int i12 = 0;
        while (true) {
            i10 = this.f23229p;
            if (i11 >= i10) {
                break;
            }
            int[] iArr = this.f23226m;
            int i13 = iArr[i11];
            if (i13 >= 0) {
                Object[] objArr2 = this.f23224i;
                objArr2[i12] = objArr2[i11];
                if (objArr != null) {
                    objArr[i12] = objArr[i11];
                }
                if (z) {
                    iArr[i12] = i13;
                    this.f23227n[i13] = i12 + 1;
                }
                i12++;
            }
            i11++;
        }
        l.y(this.f23224i, i12, i10);
        if (objArr != null) {
            l.y(objArr, i12, this.f23229p);
        }
        this.f23229p = i12;
    }

    @Override // java.util.Map
    public final Object get(Object obj) {
        int i10 = i(obj);
        if (i10 < 0) {
            return null;
        }
        return this.f23225l[i10];
    }

    public final void h(int i10) {
        Object[] objArr = this.f23224i;
        int length = objArr.length;
        int i11 = this.f23229p;
        int i12 = length - i11;
        int i13 = i11 - this.f23232s;
        if (i12 < i10 && i12 + i13 >= i10 && i13 >= objArr.length / 4) {
            g(true);
            return;
        }
        int i14 = i11 + i10;
        if (i14 < 0) {
            throw new OutOfMemoryError();
        }
        if (i14 > objArr.length) {
            kotlin.collections.c cVar = kotlin.collections.f.Companion;
            int length2 = objArr.length;
            cVar.getClass();
            int iE = kotlin.collections.c.e(length2, i14);
            this.f23224i = Arrays.copyOf(this.f23224i, iE);
            Object[] objArr2 = this.f23225l;
            this.f23225l = objArr2 != null ? Arrays.copyOf(objArr2, iE) : null;
            this.f23226m = Arrays.copyOf(this.f23226m, iE);
            int iHighestOneBit = Integer.highestOneBit((iE >= 1 ? iE : 1) * 3);
            if (iHighestOneBit > this.f23227n.length) {
                l(iHighestOneBit);
            }
        }
    }

    @Override // java.util.Map
    public final int hashCode() {
        d dVar = new d(this, 0);
        int i10 = 0;
        while (dVar.hasNext()) {
            int i11 = dVar.f2238i;
            f fVar = (f) dVar.f2241n;
            if (i11 >= fVar.f23229p) {
                throw new NoSuchElementException();
            }
            dVar.f2238i = i11 + 1;
            dVar.f2239l = i11;
            Object obj = fVar.f23224i[i11];
            int iHashCode = obj != null ? obj.hashCode() : 0;
            Object obj2 = fVar.f23225l[dVar.f2239l];
            int iHashCode2 = obj2 != null ? obj2.hashCode() : 0;
            dVar.e();
            i10 += iHashCode ^ iHashCode2;
        }
        return i10;
    }

    public final int i(Object obj) {
        int iK = k(obj);
        int i10 = this.f23228o;
        while (true) {
            int i11 = this.f23227n[iK];
            if (i11 == 0) {
                return -1;
            }
            if (i11 > 0) {
                int i12 = i11 - 1;
                if (p.a(this.f23224i[i12], obj)) {
                    return i12;
                }
            }
            i10--;
            if (i10 < 0) {
                return -1;
            }
            iK = iK == 0 ? this.f23227n.length - 1 : iK - 1;
        }
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.f23232s == 0;
    }

    public final int k(Object obj) {
        return ((obj != null ? obj.hashCode() : 0) * (-1640531527)) >>> this.f23230q;
    }

    @Override // java.util.Map
    public final Set keySet() {
        g gVar = this.f23233t;
        if (gVar != null) {
            return gVar;
        }
        g gVar2 = new g(this, 1);
        this.f23233t = gVar2;
        return gVar2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0032, code lost:
    
        r3[r0] = r6;
        r5.f23226m[r2] = r0;
        r2 = r6;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void l(int r6) {
        /*
            r5 = this;
            int r0 = r5.f23231r
            int r0 = r0 + 1
            r5.f23231r = r0
            int r0 = r5.f23229p
            int r1 = r5.f23232s
            r2 = 0
            if (r0 <= r1) goto L10
            r5.g(r2)
        L10:
            int[] r0 = new int[r6]
            r5.f23227n = r0
            int r6 = java.lang.Integer.numberOfLeadingZeros(r6)
            int r6 = r6 + 1
            r5.f23230q = r6
        L1c:
            int r6 = r5.f23229p
            if (r2 >= r6) goto L50
            int r6 = r2 + 1
            java.lang.Object[] r0 = r5.f23224i
            r0 = r0[r2]
            int r0 = r5.k(r0)
            int r1 = r5.f23228o
        L2c:
            int[] r3 = r5.f23227n
            r4 = r3[r0]
            if (r4 != 0) goto L3a
            r3[r0] = r6
            int[] r1 = r5.f23226m
            r1[r2] = r0
            r2 = r6
            goto L1c
        L3a:
            int r1 = r1 + (-1)
            if (r1 < 0) goto L48
            int r4 = r0 + (-1)
            if (r0 != 0) goto L46
            int r0 = r3.length
            int r0 = r0 + (-1)
            goto L2c
        L46:
            r0 = r4
            goto L2c
        L48:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "This cannot happen with fixed magic multiplier and grow-only hash array. Have object hashCodes changed?"
            r6.<init>(r0)
            throw r6
        L50:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: z6.f.l(int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0063 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:33:? A[LOOP:0: B:9:0x001f->B:33:?, LOOP_END, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m(int r12) {
        /*
            r11 = this;
            java.lang.Object[] r0 = r11.f23224i
            r1 = 0
            r0[r12] = r1
            java.lang.Object[] r0 = r11.f23225l
            if (r0 == 0) goto Lb
            r0[r12] = r1
        Lb:
            int[] r0 = r11.f23226m
            r0 = r0[r12]
            int r1 = r11.f23228o
            int r1 = r1 * 2
            int[] r2 = r11.f23227n
            int r2 = r2.length
            int r2 = r2 / 2
            if (r1 <= r2) goto L1b
            r1 = r2
        L1b:
            r2 = 0
            r3 = r1
            r4 = r2
            r1 = r0
        L1f:
            int r5 = r0 + (-1)
            if (r0 != 0) goto L29
            int[] r0 = r11.f23227n
            int r0 = r0.length
            int r0 = r0 + (-1)
            goto L2a
        L29:
            r0 = r5
        L2a:
            int r4 = r4 + 1
            int r5 = r11.f23228o
            r6 = -1
            if (r4 <= r5) goto L36
            int[] r0 = r11.f23227n
            r0[r1] = r2
            goto L67
        L36:
            int[] r5 = r11.f23227n
            r7 = r5[r0]
            if (r7 != 0) goto L3f
            r5[r1] = r2
            goto L67
        L3f:
            if (r7 >= 0) goto L46
            r5[r1] = r6
        L43:
            r1 = r0
            r4 = r2
            goto L60
        L46:
            java.lang.Object[] r5 = r11.f23224i
            int r8 = r7 + (-1)
            r5 = r5[r8]
            int r5 = r11.k(r5)
            int r5 = r5 - r0
            int[] r9 = r11.f23227n
            int r10 = r9.length
            int r10 = r10 + (-1)
            r5 = r5 & r10
            if (r5 < r4) goto L60
            r9[r1] = r7
            int[] r4 = r11.f23226m
            r4[r8] = r1
            goto L43
        L60:
            int r3 = r3 + r6
            if (r3 >= 0) goto L1f
            int[] r0 = r11.f23227n
            r0[r1] = r6
        L67:
            int[] r0 = r11.f23226m
            r0[r12] = r6
            int r12 = r11.f23232s
            int r12 = r12 + r6
            r11.f23232s = r12
            int r12 = r11.f23231r
            int r12 = r12 + 1
            r11.f23231r = r12
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: z6.f.m(int):void");
    }

    @Override // java.util.Map
    public final Object put(Object obj, Object obj2) {
        e();
        int iB = b(obj);
        Object[] objArr = this.f23225l;
        if (objArr == null) {
            int length = this.f23224i.length;
            if (length < 0) {
                throw new IllegalArgumentException("capacity must be non-negative.");
            }
            objArr = new Object[length];
            this.f23225l = objArr;
        }
        if (iB >= 0) {
            objArr[iB] = obj2;
            return null;
        }
        int i10 = (-iB) - 1;
        Object obj3 = objArr[i10];
        objArr[i10] = obj2;
        return obj3;
    }

    @Override // java.util.Map
    public final void putAll(Map map) {
        e();
        Set<Map.Entry> setEntrySet = map.entrySet();
        if (setEntrySet.isEmpty()) {
            return;
        }
        h(setEntrySet.size());
        for (Map.Entry entry : setEntrySet) {
            int iB = b(entry.getKey());
            Object[] objArr = this.f23225l;
            if (objArr == null) {
                int length = this.f23224i.length;
                if (length < 0) {
                    throw new IllegalArgumentException("capacity must be non-negative.");
                }
                objArr = new Object[length];
                this.f23225l = objArr;
            }
            if (iB >= 0) {
                objArr[iB] = entry.getValue();
            } else {
                int i10 = (-iB) - 1;
                if (!p.a(entry.getValue(), objArr[i10])) {
                    objArr[i10] = entry.getValue();
                }
            }
        }
    }

    @Override // java.util.Map
    public final Object remove(Object obj) {
        e();
        int i10 = i(obj);
        if (i10 < 0) {
            return null;
        }
        Object obj2 = this.f23225l[i10];
        m(i10);
        return obj2;
    }

    @Override // java.util.Map
    public final int size() {
        return this.f23232s;
    }

    public final String toString() {
        StringBuilder sb2 = new StringBuilder((this.f23232s * 3) + 2);
        sb2.append("{");
        int i10 = 0;
        d dVar = new d(this, 0);
        while (dVar.hasNext()) {
            if (i10 > 0) {
                sb2.append(", ");
            }
            int i11 = dVar.f2238i;
            f fVar = (f) dVar.f2241n;
            if (i11 >= fVar.f23229p) {
                throw new NoSuchElementException();
            }
            dVar.f2238i = i11 + 1;
            dVar.f2239l = i11;
            Object obj = fVar.f23224i[i11];
            if (obj == fVar) {
                sb2.append("(this Map)");
            } else {
                sb2.append(obj);
            }
            sb2.append('=');
            Object obj2 = fVar.f23225l[dVar.f2239l];
            if (obj2 == fVar) {
                sb2.append("(this Map)");
            } else {
                sb2.append(obj2);
            }
            dVar.e();
            i10++;
        }
        sb2.append("}");
        return sb2.toString();
    }

    @Override // java.util.Map
    public final Collection values() {
        h hVar = this.f23234u;
        if (hVar != null) {
            return hVar;
        }
        h hVar2 = new h(this);
        this.f23234u = hVar2;
        return hVar2;
    }

    public f(int i10) {
        if (i10 < 0) {
            throw new IllegalArgumentException("capacity must be non-negative.");
        }
        Object[] objArr = new Object[i10];
        int[] iArr = new int[i10];
        int iHighestOneBit = Integer.highestOneBit((i10 < 1 ? 1 : i10) * 3);
        this.f23224i = objArr;
        this.f23225l = null;
        this.f23226m = iArr;
        this.f23227n = new int[iHighestOneBit];
        this.f23228o = 2;
        this.f23229p = 0;
        this.f23230q = Integer.numberOfLeadingZeros(iHighestOneBit) + 1;
    }
}

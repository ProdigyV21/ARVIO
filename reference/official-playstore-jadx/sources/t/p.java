package t;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class p implements Cloneable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public /* synthetic */ boolean f21857i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public /* synthetic */ long[] f21858l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object[] f21859m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ int f21860n;

    public p(int i10) {
        if (i10 == 0) {
            this.f21858l = u.a.f22009b;
            this.f21859m = u.a.f22010c;
            return;
        }
        int i11 = i10 * 8;
        int i12 = 4;
        while (true) {
            if (i12 >= 32) {
                break;
            }
            int i13 = (1 << i12) - 12;
            if (i11 <= i13) {
                i11 = i13;
                break;
            }
            i12++;
        }
        int i14 = i11 / 8;
        this.f21858l = new long[i14];
        this.f21859m = new Object[i14];
    }

    public final void a(long j10, Long l10) {
        int i10 = this.f21860n;
        if (i10 != 0 && j10 <= this.f21858l[i10 - 1]) {
            g(j10, l10);
            return;
        }
        if (this.f21857i) {
            long[] jArr = this.f21858l;
            if (i10 >= jArr.length) {
                Object[] objArr = this.f21859m;
                int i11 = 0;
                for (int i12 = 0; i12 < i10; i12++) {
                    Object obj = objArr[i12];
                    if (obj != q.f21861a) {
                        if (i12 != i11) {
                            jArr[i11] = jArr[i12];
                            objArr[i11] = obj;
                            objArr[i12] = null;
                        }
                        i11++;
                    }
                }
                this.f21857i = false;
                this.f21860n = i11;
            }
        }
        int i13 = this.f21860n;
        if (i13 >= this.f21858l.length) {
            int i14 = (i13 + 1) * 8;
            int i15 = 4;
            while (true) {
                if (i15 >= 32) {
                    break;
                }
                int i16 = (1 << i15) - 12;
                if (i14 <= i16) {
                    i14 = i16;
                    break;
                }
                i15++;
            }
            int i17 = i14 / 8;
            this.f21858l = Arrays.copyOf(this.f21858l, i17);
            this.f21859m = Arrays.copyOf(this.f21859m, i17);
        }
        this.f21858l[i13] = j10;
        this.f21859m[i13] = l10;
        this.f21860n = i13 + 1;
    }

    public final void b() {
        int i10 = this.f21860n;
        Object[] objArr = this.f21859m;
        for (int i11 = 0; i11 < i10; i11++) {
            objArr[i11] = null;
        }
        this.f21860n = 0;
        this.f21857i = false;
    }

    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final p clone() {
        p pVar = (p) super.clone();
        pVar.f21858l = (long[]) this.f21858l.clone();
        pVar.f21859m = (Object[]) this.f21859m.clone();
        return pVar;
    }

    public final Object d(long j10) {
        Object obj;
        int iB = u.a.b(this.f21858l, this.f21860n, j10);
        if (iB < 0 || (obj = this.f21859m[iB]) == q.f21861a) {
            return null;
        }
        return obj;
    }

    public final Object e(long j10) {
        Object obj;
        int iB = u.a.b(this.f21858l, this.f21860n, j10);
        if (iB < 0 || (obj = this.f21859m[iB]) == q.f21861a) {
            return -1L;
        }
        return obj;
    }

    public final long f(int i10) {
        int i11;
        if (i10 < 0 || i10 >= (i11 = this.f21860n)) {
            throw new IllegalArgumentException(a0.c.i(i10, "Expected index to be within 0..size()-1, but was ").toString());
        }
        if (this.f21857i) {
            long[] jArr = this.f21858l;
            Object[] objArr = this.f21859m;
            int i12 = 0;
            for (int i13 = 0; i13 < i11; i13++) {
                Object obj = objArr[i13];
                if (obj != q.f21861a) {
                    if (i13 != i12) {
                        jArr[i12] = jArr[i13];
                        objArr[i12] = obj;
                        objArr[i13] = null;
                    }
                    i12++;
                }
            }
            this.f21857i = false;
            this.f21860n = i12;
        }
        return this.f21858l[i10];
    }

    public final void g(long j10, Object obj) {
        Object obj2 = q.f21861a;
        int iB = u.a.b(this.f21858l, this.f21860n, j10);
        if (iB >= 0) {
            this.f21859m[iB] = obj;
            return;
        }
        int i10 = ~iB;
        int i11 = this.f21860n;
        if (i10 < i11) {
            Object[] objArr = this.f21859m;
            if (objArr[i10] == obj2) {
                this.f21858l[i10] = j10;
                objArr[i10] = obj;
                return;
            }
        }
        if (this.f21857i) {
            long[] jArr = this.f21858l;
            if (i11 >= jArr.length) {
                Object[] objArr2 = this.f21859m;
                int i12 = 0;
                for (int i13 = 0; i13 < i11; i13++) {
                    Object obj3 = objArr2[i13];
                    if (obj3 != obj2) {
                        if (i13 != i12) {
                            jArr[i12] = jArr[i13];
                            objArr2[i12] = obj3;
                            objArr2[i13] = null;
                        }
                        i12++;
                    }
                }
                this.f21857i = false;
                this.f21860n = i12;
                i10 = ~u.a.b(this.f21858l, i12, j10);
            }
        }
        int i14 = this.f21860n;
        if (i14 >= this.f21858l.length) {
            int i15 = (i14 + 1) * 8;
            int i16 = 4;
            while (true) {
                if (i16 >= 32) {
                    break;
                }
                int i17 = (1 << i16) - 12;
                if (i15 <= i17) {
                    i15 = i17;
                    break;
                }
                i16++;
            }
            int i18 = i15 / 8;
            this.f21858l = Arrays.copyOf(this.f21858l, i18);
            this.f21859m = Arrays.copyOf(this.f21859m, i18);
        }
        int i19 = this.f21860n - i10;
        if (i19 != 0) {
            long[] jArr2 = this.f21858l;
            int i20 = i10 + 1;
            System.arraycopy(jArr2, i10, jArr2, i20, i19);
            Object[] objArr3 = this.f21859m;
            kotlin.collections.r.N(objArr3, objArr3, i20, i10, this.f21860n);
        }
        this.f21858l[i10] = j10;
        this.f21859m[i10] = obj;
        this.f21860n++;
    }

    public final int h() {
        if (this.f21857i) {
            int i10 = this.f21860n;
            long[] jArr = this.f21858l;
            Object[] objArr = this.f21859m;
            int i11 = 0;
            for (int i12 = 0; i12 < i10; i12++) {
                Object obj = objArr[i12];
                if (obj != q.f21861a) {
                    if (i12 != i11) {
                        jArr[i11] = jArr[i12];
                        objArr[i11] = obj;
                        objArr[i12] = null;
                    }
                    i11++;
                }
            }
            this.f21857i = false;
            this.f21860n = i11;
        }
        return this.f21860n;
    }

    public final Object i(int i10) {
        int i11;
        if (i10 < 0 || i10 >= (i11 = this.f21860n)) {
            throw new IllegalArgumentException(a0.c.i(i10, "Expected index to be within 0..size()-1, but was ").toString());
        }
        if (this.f21857i) {
            long[] jArr = this.f21858l;
            Object[] objArr = this.f21859m;
            int i12 = 0;
            for (int i13 = 0; i13 < i11; i13++) {
                Object obj = objArr[i13];
                if (obj != q.f21861a) {
                    if (i13 != i12) {
                        jArr[i12] = jArr[i13];
                        objArr[i12] = obj;
                        objArr[i13] = null;
                    }
                    i12++;
                }
            }
            this.f21857i = false;
            this.f21860n = i12;
        }
        return this.f21859m[i10];
    }

    public final String toString() {
        if (h() <= 0) {
            return "{}";
        }
        StringBuilder sb2 = new StringBuilder(this.f21860n * 28);
        sb2.append('{');
        int i10 = this.f21860n;
        for (int i11 = 0; i11 < i10; i11++) {
            if (i11 > 0) {
                sb2.append(", ");
            }
            sb2.append(f(i11));
            sb2.append('=');
            Object objI = i(i11);
            if (objI != sb2) {
                sb2.append(objI);
            } else {
                sb2.append("(this Map)");
            }
        }
        sb2.append('}');
        return sb2.toString();
    }

    public /* synthetic */ p(Object obj) {
        this(10);
    }
}

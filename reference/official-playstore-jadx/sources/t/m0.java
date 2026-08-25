package t;

import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class m0 implements Cloneable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public /* synthetic */ boolean f21851i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public /* synthetic */ int[] f21852l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object[] f21853m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ int f21854n;

    public m0(int i10) {
        int i11;
        int i12 = 4;
        while (true) {
            i11 = 40;
            if (i12 >= 32) {
                break;
            }
            int i13 = (1 << i12) - 12;
            if (40 <= i13) {
                i11 = i13;
                break;
            }
            i12++;
        }
        int i14 = i11 / 4;
        this.f21852l = new int[i14];
        this.f21853m = new Object[i14];
    }

    public final void a(int i10, Object obj) {
        int i11 = this.f21854n;
        if (i11 != 0 && i10 <= this.f21852l[i11 - 1]) {
            e(i10, obj);
            return;
        }
        if (this.f21851i && i11 >= this.f21852l.length) {
            q.a(this);
        }
        int i12 = this.f21854n;
        if (i12 >= this.f21852l.length) {
            int i13 = (i12 + 1) * 4;
            int i14 = 4;
            while (true) {
                if (i14 >= 32) {
                    break;
                }
                int i15 = (1 << i14) - 12;
                if (i13 <= i15) {
                    i13 = i15;
                    break;
                }
                i14++;
            }
            int i16 = i13 / 4;
            this.f21852l = Arrays.copyOf(this.f21852l, i16);
            this.f21853m = Arrays.copyOf(this.f21853m, i16);
        }
        this.f21852l[i12] = i10;
        this.f21853m[i12] = obj;
        this.f21854n = i12 + 1;
    }

    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public final m0 clone() {
        m0 m0Var = (m0) super.clone();
        m0Var.f21852l = (int[]) this.f21852l.clone();
        m0Var.f21853m = (Object[]) this.f21853m.clone();
        return m0Var;
    }

    public final Object c(int i10) {
        Object obj;
        int iA = u.a.a(this.f21854n, i10, this.f21852l);
        if (iA < 0 || (obj = this.f21853m[iA]) == q.f21862b) {
            return null;
        }
        return obj;
    }

    public final int d(int i10) {
        if (this.f21851i) {
            q.a(this);
        }
        return this.f21852l[i10];
    }

    public final void e(int i10, Object obj) {
        int iA = u.a.a(this.f21854n, i10, this.f21852l);
        if (iA >= 0) {
            this.f21853m[iA] = obj;
            return;
        }
        int i11 = ~iA;
        int i12 = this.f21854n;
        if (i11 < i12) {
            Object[] objArr = this.f21853m;
            if (objArr[i11] == q.f21862b) {
                this.f21852l[i11] = i10;
                objArr[i11] = obj;
                return;
            }
        }
        if (this.f21851i && i12 >= this.f21852l.length) {
            q.a(this);
            i11 = ~u.a.a(this.f21854n, i10, this.f21852l);
        }
        int i13 = this.f21854n;
        if (i13 >= this.f21852l.length) {
            int i14 = (i13 + 1) * 4;
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
            int i17 = i14 / 4;
            this.f21852l = Arrays.copyOf(this.f21852l, i17);
            this.f21853m = Arrays.copyOf(this.f21853m, i17);
        }
        int i18 = this.f21854n;
        if (i18 - i11 != 0) {
            int[] iArr = this.f21852l;
            int i19 = i11 + 1;
            kotlin.collections.r.K(i19, i11, i18, iArr, iArr);
            Object[] objArr2 = this.f21853m;
            kotlin.collections.r.N(objArr2, objArr2, i19, i11, this.f21854n);
        }
        this.f21852l[i11] = i10;
        this.f21853m[i11] = obj;
        this.f21854n++;
    }

    public final int f() {
        if (this.f21851i) {
            q.a(this);
        }
        return this.f21854n;
    }

    public final Object g(int i10) {
        if (this.f21851i) {
            q.a(this);
        }
        return this.f21853m[i10];
    }

    public final String toString() {
        if (f() <= 0) {
            return "{}";
        }
        StringBuilder sb2 = new StringBuilder(this.f21854n * 28);
        sb2.append('{');
        int i10 = this.f21854n;
        for (int i11 = 0; i11 < i10; i11++) {
            if (i11 > 0) {
                sb2.append(", ");
            }
            sb2.append(d(i11));
            sb2.append('=');
            Object objG = g(i11);
            if (objG != this) {
                sb2.append(objG);
            } else {
                sb2.append("(this Map)");
            }
        }
        sb2.append('}');
        return sb2.toString();
    }
}

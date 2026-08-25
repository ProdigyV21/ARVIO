package androidx.recyclerview.widget;

import android.util.SparseIntArray;
import android.view.View;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public final class c2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f4903a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f4904b;

    public c2(a2 a2Var) {
        this.f4903a = a2Var;
        z1 z1Var = new z1();
        z1Var.f5127a = 0;
        this.f4904b = z1Var;
    }

    public static int e(int i10, int i11) {
        int i12 = 0;
        int i13 = 0;
        for (int i14 = 0; i14 < i10; i14++) {
            i12++;
            if (i12 == i11) {
                i13++;
                i12 = 0;
            } else if (i12 > i11) {
                i13++;
                i12 = 1;
            }
        }
        return i12 + 1 > i11 ? i13 + 1 : i13;
    }

    public void a(p1 p1Var, t0 t0Var) {
        t.l0 l0Var = (t.l0) this.f4903a;
        d2 d2VarA = (d2) l0Var.get(p1Var);
        if (d2VarA == null) {
            d2VarA = d2.a();
            l0Var.put(p1Var, d2VarA);
        }
        d2VarA.f4912c = t0Var;
        d2VarA.f4910a |= 8;
    }

    public void b() {
        int[] iArr = (int[]) this.f4903a;
        if (iArr != null) {
            Arrays.fill(iArr, -1);
        }
        this.f4904b = null;
    }

    public void c(int i10) {
        int[] iArr = (int[]) this.f4903a;
        if (iArr == null) {
            int[] iArr2 = new int[Math.max(i10, 10) + 1];
            this.f4903a = iArr2;
            Arrays.fill(iArr2, -1);
        } else if (i10 >= iArr.length) {
            int length = iArr.length;
            while (length <= i10) {
                length *= 2;
            }
            int[] iArr3 = new int[length];
            this.f4903a = iArr3;
            System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            int[] iArr4 = (int[]) this.f4903a;
            Arrays.fill(iArr4, iArr.length, iArr4.length, -1);
        }
    }

    public View d(int i10, int i11, int i12, int i13) {
        z1 z1Var = (z1) this.f4904b;
        a2 a2Var = (a2) this.f4903a;
        int iB = a2Var.b();
        int iC = a2Var.c();
        int i14 = i11 > i10 ? 1 : -1;
        View view = null;
        while (i10 != i11) {
            View viewD = a2Var.d(i10);
            int iA = a2Var.a(viewD);
            int iE = a2Var.e(viewD);
            z1Var.f5128b = iB;
            z1Var.f5129c = iC;
            z1Var.f5130d = iA;
            z1Var.f5131e = iE;
            if (i12 != 0) {
                z1Var.f5127a = i12;
                if (z1Var.a()) {
                    return viewD;
                }
            }
            if (i13 != 0) {
                z1Var.f5127a = i13;
                if (z1Var.a()) {
                    view = viewD;
                }
            }
            i10 += i14;
        }
        return view;
    }

    public void f() {
        ((SparseIntArray) this.f4903a).clear();
    }

    public boolean g(View view) {
        z1 z1Var = (z1) this.f4904b;
        a2 a2Var = (a2) this.f4903a;
        int iB = a2Var.b();
        int iC = a2Var.c();
        int iA = a2Var.a(view);
        int iE = a2Var.e(view);
        z1Var.f5128b = iB;
        z1Var.f5129c = iC;
        z1Var.f5130d = iA;
        z1Var.f5131e = iE;
        z1Var.f5127a = 24579;
        return z1Var.a();
    }

    public void h(int i10, int i11) {
        int[] iArr = (int[]) this.f4903a;
        if (iArr == null || i10 >= iArr.length) {
            return;
        }
        int i12 = i10 + i11;
        c(i12);
        int[] iArr2 = (int[]) this.f4903a;
        System.arraycopy(iArr2, i10, iArr2, i12, (iArr2.length - i10) - i11);
        Arrays.fill((int[]) this.f4903a, i10, i12, -1);
        ArrayList arrayList = (ArrayList) this.f4904b;
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            v1 v1Var = (v1) ((ArrayList) this.f4904b).get(size);
            int i13 = v1Var.f5073i;
            if (i13 >= i10) {
                v1Var.f5073i = i13 + i11;
            }
        }
    }

    public void i(int i10, int i11) {
        int[] iArr = (int[]) this.f4903a;
        if (iArr == null || i10 >= iArr.length) {
            return;
        }
        int i12 = i10 + i11;
        c(i12);
        int[] iArr2 = (int[]) this.f4903a;
        System.arraycopy(iArr2, i12, iArr2, i10, (iArr2.length - i10) - i11);
        int[] iArr3 = (int[]) this.f4903a;
        Arrays.fill(iArr3, iArr3.length - i11, iArr3.length, -1);
        ArrayList arrayList = (ArrayList) this.f4904b;
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            v1 v1Var = (v1) ((ArrayList) this.f4904b).get(size);
            int i13 = v1Var.f5073i;
            if (i13 >= i10) {
                if (i13 < i12) {
                    ((ArrayList) this.f4904b).remove(size);
                } else {
                    v1Var.f5073i = i13 - i11;
                }
            }
        }
    }

    public t0 j(p1 p1Var, int i10) {
        d2 d2Var;
        t0 t0Var;
        t.l0 l0Var = (t.l0) this.f4903a;
        int iE = l0Var.e(p1Var);
        if (iE >= 0 && (d2Var = (d2) l0Var.k(iE)) != null) {
            int i11 = d2Var.f4910a;
            if ((i11 & i10) != 0) {
                int i12 = i11 & (~i10);
                d2Var.f4910a = i12;
                if (i10 == 4) {
                    t0Var = d2Var.f4911b;
                } else {
                    if (i10 != 8) {
                        throw new IllegalArgumentException("Must provide flag PRE or POST");
                    }
                    t0Var = d2Var.f4912c;
                }
                if ((i12 & 12) == 0) {
                    l0Var.i(iE);
                    d2Var.f4910a = 0;
                    d2Var.f4911b = null;
                    d2Var.f4912c = null;
                    d2.f4909d.f(d2Var);
                }
                return t0Var;
            }
        }
        return null;
    }

    public void k(p1 p1Var) {
        d2 d2Var = (d2) ((t.l0) this.f4903a).get(p1Var);
        if (d2Var == null) {
            return;
        }
        d2Var.f4910a &= -2;
    }

    public void l(p1 p1Var) {
        t.p pVar = (t.p) this.f4904b;
        int iH = pVar.h() - 1;
        while (true) {
            if (iH < 0) {
                break;
            }
            if (p1Var == pVar.i(iH)) {
                Object[] objArr = pVar.f21859m;
                Object obj = objArr[iH];
                Object obj2 = t.q.f21861a;
                if (obj != obj2) {
                    objArr[iH] = obj2;
                    pVar.f21857i = true;
                }
            } else {
                iH--;
            }
        }
        d2 d2Var = (d2) ((t.l0) this.f4903a).remove(p1Var);
        if (d2Var != null) {
            d2Var.f4910a = 0;
            d2Var.f4911b = null;
            d2Var.f4912c = null;
            d2.f4909d.f(d2Var);
        }
    }

    public c2(int i10) {
        switch (i10) {
            case 3:
                this.f4903a = new t.l0(0);
                this.f4904b = new t.p((Object) null);
                break;
            default:
                this.f4903a = new SparseIntArray();
                this.f4904b = new SparseIntArray();
                break;
        }
    }
}

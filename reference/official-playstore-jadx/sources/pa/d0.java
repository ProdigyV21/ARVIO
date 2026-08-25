package pa;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import ka.f1;
import ka.g1;

/* JADX INFO: loaded from: classes5.dex */
public class d0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f21260b = AtomicIntegerFieldUpdater.newUpdater(d0.class, "_size");

    @q7.w
    private volatile int _size;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public e0[] f21261a;

    public final void a(f1 f1Var) {
        f1Var.a((g1) this);
        e0[] e0VarArr = this.f21261a;
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f21260b;
        if (e0VarArr == null) {
            e0VarArr = new e0[4];
            this.f21261a = e0VarArr;
        } else if (atomicIntegerFieldUpdater.get(this) >= e0VarArr.length) {
            e0VarArr = (e0[]) Arrays.copyOf(e0VarArr, atomicIntegerFieldUpdater.get(this) * 2);
            this.f21261a = e0VarArr;
        }
        int i10 = atomicIntegerFieldUpdater.get(this);
        atomicIntegerFieldUpdater.set(this, i10 + 1);
        e0VarArr[i10] = f1Var;
        f1Var.f19586l = i10;
        while (i10 > 0) {
            e0[] e0VarArr2 = this.f21261a;
            int i11 = (i10 - 1) / 2;
            if (((Comparable) e0VarArr2[i11]).compareTo(e0VarArr2[i10]) <= 0) {
                return;
            }
            e(i10, i11);
            i10 = i11;
        }
    }

    public final e0 b() {
        e0 e0Var;
        synchronized (this) {
            e0[] e0VarArr = this.f21261a;
            e0Var = e0VarArr != null ? e0VarArr[0] : null;
        }
        return e0Var;
    }

    public final void c(f1 f1Var) {
        synchronized (this) {
            if (f1Var.d() != null) {
                d(f1Var.f19586l);
            }
        }
    }

    public final e0 d(int i10) {
        e0[] e0VarArr = this.f21261a;
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f21260b;
        atomicIntegerFieldUpdater.set(this, atomicIntegerFieldUpdater.get(this) - 1);
        if (i10 < atomicIntegerFieldUpdater.get(this)) {
            e(i10, atomicIntegerFieldUpdater.get(this));
            int i11 = (i10 - 1) / 2;
            if (i10 <= 0 || ((Comparable) e0VarArr[i10]).compareTo(e0VarArr[i11]) >= 0) {
                while (true) {
                    int i12 = i10 * 2;
                    int i13 = i12 + 1;
                    if (i13 >= atomicIntegerFieldUpdater.get(this)) {
                        break;
                    }
                    e0[] e0VarArr2 = this.f21261a;
                    int i14 = i12 + 2;
                    if (i14 >= atomicIntegerFieldUpdater.get(this) || ((Comparable) e0VarArr2[i14]).compareTo(e0VarArr2[i13]) >= 0) {
                        i14 = i13;
                    }
                    if (((Comparable) e0VarArr2[i10]).compareTo(e0VarArr2[i14]) <= 0) {
                        break;
                    }
                    e(i10, i14);
                    i10 = i14;
                }
            } else {
                e(i10, i11);
                while (i11 > 0) {
                    e0[] e0VarArr3 = this.f21261a;
                    int i15 = (i11 - 1) / 2;
                    if (((Comparable) e0VarArr3[i15]).compareTo(e0VarArr3[i11]) <= 0) {
                        break;
                    }
                    e(i11, i15);
                    i11 = i15;
                }
            }
        }
        e0 e0Var = e0VarArr[atomicIntegerFieldUpdater.get(this)];
        e0Var.a(null);
        e0Var.setIndex(-1);
        e0VarArr[atomicIntegerFieldUpdater.get(this)] = null;
        return e0Var;
    }

    public final void e(int i10, int i11) {
        e0[] e0VarArr = this.f21261a;
        e0 e0Var = e0VarArr[i11];
        e0 e0Var2 = e0VarArr[i10];
        e0VarArr[i10] = e0Var;
        e0VarArr[i11] = e0Var2;
        e0Var.setIndex(i10);
        e0Var2.setIndex(i11);
    }
}

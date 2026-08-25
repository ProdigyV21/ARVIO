package androidx.recyclerview.widget;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public final class s implements x0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f5040a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5041b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5042c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f5043d;

    public void a(com.squareup.moshi.u uVar) {
        uVar.f14473m = null;
        uVar.f14471i = null;
        uVar.f14472l = null;
        uVar.f14479s = 1;
        int i10 = this.f5040a;
        if (i10 > 0) {
            int i11 = this.f5042c;
            if ((i11 & 1) == 0) {
                this.f5042c = i11 + 1;
                this.f5040a = i10 - 1;
                this.f5041b++;
            }
        }
        uVar.f14471i = (com.squareup.moshi.u) this.f5043d;
        this.f5043d = uVar;
        int i12 = this.f5042c;
        int i13 = i12 + 1;
        this.f5042c = i13;
        int i14 = this.f5040a;
        if (i14 > 0 && (i13 & 1) == 0) {
            this.f5042c = i12 + 2;
            this.f5040a = i14 - 1;
            this.f5041b++;
        }
        int i15 = 4;
        while (true) {
            int i16 = i15 - 1;
            if ((this.f5042c & i16) != i16) {
                return;
            }
            int i17 = this.f5041b;
            if (i17 == 0) {
                com.squareup.moshi.u uVar2 = (com.squareup.moshi.u) this.f5043d;
                com.squareup.moshi.u uVar3 = uVar2.f14471i;
                com.squareup.moshi.u uVar4 = uVar3.f14471i;
                uVar3.f14471i = uVar4.f14471i;
                this.f5043d = uVar3;
                uVar3.f14472l = uVar4;
                uVar3.f14473m = uVar2;
                uVar3.f14479s = uVar2.f14479s + 1;
                uVar4.f14471i = uVar3;
                uVar2.f14471i = uVar3;
            } else if (i17 == 1) {
                com.squareup.moshi.u uVar5 = (com.squareup.moshi.u) this.f5043d;
                com.squareup.moshi.u uVar6 = uVar5.f14471i;
                this.f5043d = uVar6;
                uVar6.f14473m = uVar5;
                uVar6.f14479s = uVar5.f14479s + 1;
                uVar5.f14471i = uVar6;
                this.f5041b = 0;
            } else if (i17 == 2) {
                this.f5041b = 0;
            }
            i15 *= 2;
        }
    }

    public void b(int i10, int i11) {
        if (i10 < 0) {
            throw new IllegalArgumentException("Layout positions must be non-negative");
        }
        if (i11 < 0) {
            throw new IllegalArgumentException("Pixel distance must be non-negative");
        }
        int i12 = this.f5042c;
        int i13 = i12 * 2;
        int[] iArr = (int[]) this.f5043d;
        if (iArr == null) {
            int[] iArr2 = new int[4];
            this.f5043d = iArr2;
            Arrays.fill(iArr2, -1);
        } else if (i13 >= iArr.length) {
            int[] iArr3 = new int[i12 * 4];
            this.f5043d = iArr3;
            System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
        }
        int[] iArr4 = (int[]) this.f5043d;
        iArr4[i13] = i10;
        iArr4[i13 + 1] = i11;
        this.f5042c++;
    }

    public void c(RecyclerView recyclerView, boolean z) {
        this.f5042c = 0;
        int[] iArr = (int[]) this.f5043d;
        if (iArr != null) {
            Arrays.fill(iArr, -1);
        }
        z0 z0Var = recyclerView.x;
        if (recyclerView.f4856w == null || z0Var == null || !z0Var.f5121i) {
            return;
        }
        if (z) {
            if (!recyclerView.f4841o.g()) {
                z0Var.i(recyclerView.f4856w.getItemCount(), this);
            }
        } else if (!recyclerView.I()) {
            z0Var.h(this.f5040a, this.f5041b, recyclerView.f4846q0, this);
        }
        int i10 = this.f5042c;
        if (i10 > z0Var.f5122j) {
            z0Var.f5122j = i10;
            z0Var.k = z;
            recyclerView.f4837m.m();
        }
    }

    public void d() {
        Object[] objArr = (Object[]) this.f5043d;
        int length = objArr.length;
        int i10 = this.f5040a;
        int i11 = length - i10;
        int i12 = length << 1;
        if (i12 < 0) {
            throw new RuntimeException("Max array capacity exceeded");
        }
        Object[] objArr2 = new Object[i12];
        kotlin.collections.r.N(objArr, objArr2, 0, i10, length);
        kotlin.collections.r.N((Object[]) this.f5043d, objArr2, i11, 0, this.f5040a);
        this.f5043d = objArr2;
        this.f5040a = 0;
        this.f5041b = length;
        this.f5042c = i12 - 1;
    }

    public void e(int i10) {
        if (i10 <= 0) {
            return;
        }
        if (i10 > g()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int i11 = this.f5041b;
        int i12 = i10 < i11 ? i11 - i10 : 0;
        for (int i13 = i12; i13 < i11; i13++) {
            ((Object[]) this.f5043d)[i13] = null;
        }
        int i14 = this.f5041b;
        int i15 = i14 - i12;
        int i16 = i10 - i15;
        this.f5041b = i14 - i15;
        if (i16 > 0) {
            int length = ((Object[]) this.f5043d).length;
            this.f5041b = length;
            int i17 = length - i16;
            for (int i18 = i17; i18 < length; i18++) {
                ((Object[]) this.f5043d)[i18] = null;
            }
            this.f5041b = i17;
        }
    }

    public void f(int i10) {
        if (i10 <= 0) {
            return;
        }
        if (i10 > g()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int length = ((Object[]) this.f5043d).length;
        int i11 = this.f5040a;
        if (i10 < length - i11) {
            length = i11 + i10;
        }
        while (i11 < length) {
            ((Object[]) this.f5043d)[i11] = null;
            i11++;
        }
        int i12 = this.f5040a;
        int i13 = length - i12;
        int i14 = i10 - i13;
        this.f5040a = this.f5042c & (i12 + i13);
        if (i14 > 0) {
            for (int i15 = 0; i15 < i14; i15++) {
                ((Object[]) this.f5043d)[i15] = null;
            }
            this.f5040a = i14;
        }
    }

    public int g() {
        return (this.f5041b - this.f5040a) & this.f5042c;
    }
}

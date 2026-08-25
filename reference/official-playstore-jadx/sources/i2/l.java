package i2;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;

/* JADX INFO: loaded from: classes3.dex */
public final class l implements State {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f16054i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final int f16055l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final MutableState f16056m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f16057n;

    public l(int i10, int i11, int i12) {
        this.f16054i = i11;
        this.f16055l = i12;
        int i13 = (i10 / i11) * i11;
        this.f16056m = SnapshotStateKt.mutableStateOf(qb.d.X(Math.max(i13 - i12, 0), i13 + i11 + i12), SnapshotStateKt.structuralEqualityPolicy());
        this.f16057n = i10;
    }

    public final void a(int i10) {
        if (i10 != this.f16057n) {
            this.f16057n = i10;
            int i11 = this.f16054i;
            int i12 = (i10 / i11) * i11;
            int i13 = this.f16055l;
            this.f16056m.setValue(qb.d.X(Math.max(i12 - i13, 0), i12 + i11 + i13));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.compose.runtime.State
    public final Object getValue() {
        return (x7.i) this.f16056m.getValue();
    }
}

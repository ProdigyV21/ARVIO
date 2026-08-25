package androidx.recyclerview.widget;

/* JADX INFO: loaded from: classes3.dex */
public final class z1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f5127a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5128b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5129c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5130d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5131e;

    public final boolean a() {
        int i10 = this.f5127a;
        int i11 = 2;
        if ((i10 & 7) != 0) {
            int i12 = this.f5130d;
            int i13 = this.f5128b;
            if (((i12 > i13 ? 1 : i12 == i13 ? 2 : 4) & i10) == 0) {
                return false;
            }
        }
        if ((i10 & 112) != 0) {
            int i14 = this.f5130d;
            int i15 = this.f5129c;
            if ((((i14 > i15 ? 1 : i14 == i15 ? 2 : 4) << 4) & i10) == 0) {
                return false;
            }
        }
        if ((i10 & 1792) != 0) {
            int i16 = this.f5131e;
            int i17 = this.f5128b;
            if ((((i16 > i17 ? 1 : i16 == i17 ? 2 : 4) << 8) & i10) == 0) {
                return false;
            }
        }
        if ((i10 & 28672) != 0) {
            int i18 = this.f5131e;
            int i19 = this.f5129c;
            if (i18 > i19) {
                i11 = 1;
            } else if (i18 != i19) {
                i11 = 4;
            }
            if ((i10 & (i11 << 12)) == 0) {
                return false;
            }
        }
        return true;
    }
}

package androidx.navigation;

import android.os.Bundle;

/* JADX INFO: loaded from: classes3.dex */
public final class c0 implements Comparable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final d0 f4531i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Bundle f4532l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f4533m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f4534n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final boolean f4535o;

    public c0(d0 d0Var, Bundle bundle, boolean z, int i10, boolean z5) {
        this.f4531i = d0Var;
        this.f4532l = bundle;
        this.f4533m = z;
        this.f4534n = i10;
        this.f4535o = z5;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public final int compareTo(c0 c0Var) {
        boolean z = c0Var.f4535o;
        boolean z5 = c0Var.f4533m;
        Bundle bundle = c0Var.f4532l;
        boolean z10 = this.f4533m;
        if (z10 && !z5) {
            return 1;
        }
        if (!z10 && z5) {
            return -1;
        }
        int i10 = this.f4534n - c0Var.f4534n;
        if (i10 > 0) {
            return 1;
        }
        if (i10 < 0) {
            return -1;
        }
        Bundle bundle2 = this.f4532l;
        if (bundle2 != null && bundle == null) {
            return 1;
        }
        if (bundle2 == null && bundle != null) {
            return -1;
        }
        if (bundle2 != null) {
            int size = bundle2.size() - bundle.size();
            if (size > 0) {
                return 1;
            }
            if (size < 0) {
                return -1;
            }
        }
        boolean z11 = this.f4535o;
        if (!z11 || z) {
            return (z11 || !z) ? 0 : -1;
        }
        return 1;
    }
}

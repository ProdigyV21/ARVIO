package androidx.lifecycle;

import android.os.Handler;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0004B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Landroidx/lifecycle/ProcessLifecycleOwner;", "Landroidx/lifecycle/y;", "<init>", "()V", "androidx/lifecycle/m0", "lifecycle-process_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ProcessLifecycleOwner implements y {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final ProcessLifecycleOwner f3185s = new ProcessLifecycleOwner();

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f3186i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f3187l;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Handler f3190o;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f3188m = true;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f3189n = true;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final a0 f3191p = new a0(this);

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final androidx.activity.n f3192q = new androidx.activity.n(this, 8);

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final o0 f3193r = new o0(this);

    private ProcessLifecycleOwner() {
    }

    public final void a() {
        int i10 = this.f3187l + 1;
        this.f3187l = i10;
        if (i10 == 1) {
            if (!this.f3188m) {
                this.f3190o.removeCallbacks(this.f3192q);
            } else {
                this.f3191p.c(p.ON_RESUME);
                this.f3188m = false;
            }
        }
    }

    @Override // androidx.lifecycle.y
    public final r getLifecycle() {
        return this.f3191p;
    }
}

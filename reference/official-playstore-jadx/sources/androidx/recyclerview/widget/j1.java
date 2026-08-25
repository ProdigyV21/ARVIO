package androidx.recyclerview.widget;

import android.util.Log;
import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes3.dex */
public final class j1 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f4953a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4954b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4955c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4956d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Interpolator f4957e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f4958f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4959g;

    public final void a(RecyclerView recyclerView) {
        int i10 = this.f4956d;
        if (i10 >= 0) {
            this.f4956d = -1;
            recyclerView.L(i10);
            this.f4958f = false;
            return;
        }
        if (!this.f4958f) {
            this.f4959g = 0;
            return;
        }
        Interpolator interpolator = this.f4957e;
        if (interpolator != null && this.f4955c < 1) {
            throw new IllegalStateException("If you provide an interpolator, you must set a positive duration");
        }
        int i11 = this.f4955c;
        if (i11 < 1) {
            throw new IllegalStateException("Scroll duration must be a positive number");
        }
        recyclerView.f4840n0.c(this.f4953a, this.f4954b, i11, interpolator);
        int i12 = this.f4959g + 1;
        this.f4959g = i12;
        if (i12 > 10) {
            Log.e("RecyclerView", "Smooth Scroll action is being updated too frequently. Make sure you are not changing it unless necessary");
        }
        this.f4958f = false;
    }
}

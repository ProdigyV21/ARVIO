package androidx.fragment.app;

import android.os.Handler;

/* JADX INFO: loaded from: classes3.dex */
public abstract class m0 extends j0 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final h0 f2779i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final h0 f2780l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Handler f2781m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final c1 f2782n;

    public m0(h0 h0Var) {
        Handler handler = new Handler();
        this.f2782n = new c1();
        this.f2779i = h0Var;
        this.f2780l = h0Var;
        this.f2781m = handler;
    }
}

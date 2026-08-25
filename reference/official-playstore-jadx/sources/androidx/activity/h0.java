package androidx.activity;

import android.window.BackEvent;
import android.window.OnBackAnimationCallback;

/* JADX INFO: loaded from: classes.dex */
public final class h0 implements OnBackAnimationCallback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ e0 f947a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ e0 f948b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ f0 f949c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ f0 f950d;

    public h0(e0 e0Var, e0 e0Var2, f0 f0Var, f0 f0Var2) {
        this.f947a = e0Var;
        this.f948b = e0Var2;
        this.f949c = f0Var;
        this.f950d = f0Var2;
    }

    public final void onBackCancelled() {
        this.f950d.invoke();
    }

    public final void onBackInvoked() {
        this.f949c.invoke();
    }

    public final void onBackProgressed(BackEvent backEvent) {
        this.f948b.invoke(new c(backEvent));
    }

    public final void onBackStarted(BackEvent backEvent) {
        this.f947a.invoke(new c(backEvent));
    }
}

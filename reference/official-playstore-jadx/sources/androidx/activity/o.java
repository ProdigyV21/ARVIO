package androidx.activity;

import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes.dex */
public final class o implements m, ViewTreeObserver.OnDrawListener, Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f974i = SystemClock.uptimeMillis() + ((long) 10000);

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Runnable f975l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public boolean f976m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ t f977n;

    public o(t tVar) {
        this.f977n = tVar;
    }

    @Override // androidx.activity.m
    public final void O(View view) {
        if (this.f976m) {
            return;
        }
        this.f976m = true;
        view.getViewTreeObserver().addOnDrawListener(this);
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        this.f975l = runnable;
        View decorView = this.f977n.getWindow().getDecorView();
        if (!this.f976m) {
            decorView.postOnAnimation(new n(this, 0));
        } else if (kotlin.jvm.internal.p.a(Looper.myLooper(), Looper.getMainLooper())) {
            decorView.invalidate();
        } else {
            decorView.postInvalidate();
        }
    }

    @Override // androidx.activity.m
    public final void j() {
        t tVar = this.f977n;
        tVar.getWindow().getDecorView().removeCallbacks(this);
        tVar.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(this);
    }

    @Override // android.view.ViewTreeObserver.OnDrawListener
    public final void onDraw() {
        Runnable runnable = this.f975l;
        t tVar = this.f977n;
        if (runnable == null) {
            if (SystemClock.uptimeMillis() > this.f974i) {
                this.f976m = false;
                tVar.getWindow().getDecorView().post(this);
                return;
            }
            return;
        }
        runnable.run();
        this.f975l = null;
        if (tVar.getFullyDrawnReporter().c()) {
            this.f976m = false;
            tVar.getWindow().getDecorView().post(this);
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f977n.getWindow().getDecorView().getViewTreeObserver().removeOnDrawListener(this);
    }
}

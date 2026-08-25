package androidx.core.view;

import android.view.View;
import android.view.Window;
import android.view.WindowInsetsController;

/* JADX INFO: loaded from: classes3.dex */
public final class t3 extends k2.c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final WindowInsetsController f2313b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Window f2314c;

    public t3(Window window, x0 x0Var) {
        this.f2313b = window.getInsetsController();
        this.f2314c = window;
    }

    @Override // k2.c
    public final void B() {
        Window window = this.f2314c;
        if (window != null) {
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & (-17));
        }
        this.f2313b.setSystemBarsAppearance(0, 16);
    }

    @Override // k2.c
    public final void C() {
        Window window = this.f2314c;
        if (window != null) {
            View decorView = window.getDecorView();
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & (-8193));
        }
        this.f2313b.setSystemBarsAppearance(0, 8);
    }

    @Override // k2.c
    public final void D() {
        this.f2313b.setSystemBarsBehavior(2);
    }

    @Override // k2.c
    public final void E() {
        this.f2313b.show(7);
    }

    @Override // k2.c
    public final void u() {
        this.f2313b.hide(7);
    }
}

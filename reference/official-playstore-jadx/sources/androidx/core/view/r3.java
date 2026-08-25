package androidx.core.view;

import android.view.View;
import android.view.Window;

/* JADX INFO: loaded from: classes3.dex */
public class r3 extends k2.c {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Window f2311b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final x0 f2312c;

    public r3(Window window, x0 x0Var) {
        this.f2311b = window;
        this.f2312c = x0Var;
    }

    @Override // k2.c
    public final void C() {
        M(8192);
    }

    @Override // k2.c
    public final void D() {
        M(2048);
        L(4096);
    }

    @Override // k2.c
    public final void E() {
        for (int i10 = 1; i10 <= 256; i10 <<= 1) {
            if ((7 & i10) != 0) {
                if (i10 == 1) {
                    M(4);
                    this.f2311b.clearFlags(1024);
                } else if (i10 == 2) {
                    M(2);
                } else if (i10 == 8) {
                    this.f2312c.f2333a.f();
                }
            }
        }
    }

    public final void L(int i10) {
        View decorView = this.f2311b.getDecorView();
        decorView.setSystemUiVisibility(i10 | decorView.getSystemUiVisibility());
    }

    public final void M(int i10) {
        View decorView = this.f2311b.getDecorView();
        decorView.setSystemUiVisibility((~i10) & decorView.getSystemUiVisibility());
    }

    @Override // k2.c
    public final void u() {
        for (int i10 = 1; i10 <= 256; i10 <<= 1) {
            if ((7 & i10) != 0) {
                if (i10 == 1) {
                    L(4);
                } else if (i10 == 2) {
                    L(2);
                } else if (i10 == 8) {
                    this.f2312c.f2333a.b();
                }
            }
        }
    }
}

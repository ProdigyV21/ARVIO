package androidx.appcompat.widget;

import android.view.ViewGroup;

/* JADX INFO: loaded from: classes.dex */
public final class f2 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1569i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ ViewGroup f1570l;

    public /* synthetic */ f2(ViewGroup viewGroup, int i10) {
        this.f1569i = i10;
        this.f1570l = viewGroup;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f1569i) {
            case 0:
                g2 g2Var = (g2) this.f1570l;
                g2Var.f1587v = null;
                g2Var.drawableStateChanged();
                break;
            default:
                ((Toolbar) this.f1570l).t();
                break;
        }
    }
}

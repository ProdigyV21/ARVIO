package androidx.appcompat.widget;

import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class s2 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1738i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ w2 f1739l;

    public /* synthetic */ s2(w2 w2Var, int i10) {
        this.f1738i = i10;
        this.f1739l = w2Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i10 = this.f1738i;
        w2 w2Var = this.f1739l;
        switch (i10) {
            case 0:
                g2 g2Var = w2Var.f1813m;
                if (g2Var != null) {
                    g2Var.setListSelectionHidden(true);
                    g2Var.requestLayout();
                }
                break;
            default:
                g2 g2Var2 = w2Var.f1813m;
                if (g2Var2 != null) {
                    WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
                    if (g2Var2.isAttachedToWindow() && w2Var.f1813m.getCount() > w2Var.f1813m.getChildCount() && w2Var.f1813m.getChildCount() <= w2Var.f1823w) {
                        w2Var.I.setInputMethodMode(2);
                        w2Var.show();
                        break;
                    }
                }
                break;
        }
    }
}

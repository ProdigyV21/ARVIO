package androidx.appcompat.widget;

import android.view.ViewTreeObserver;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class q0 implements ViewTreeObserver.OnGlobalLayoutListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1720i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f1721l;

    public /* synthetic */ q0(Object obj, int i10) {
        this.f1720i = i10;
        this.f1721l = obj;
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        int i10 = this.f1720i;
        Object obj = this.f1721l;
        switch (i10) {
            case 0:
                c1 c1Var = (c1) obj;
                if (!c1Var.getInternalPopup().a()) {
                    c1Var.f1533p.j(s0.b(c1Var), s0.a(c1Var));
                }
                ViewTreeObserver viewTreeObserver = c1Var.getViewTreeObserver();
                if (viewTreeObserver != null) {
                    r0.a(viewTreeObserver, this);
                }
                break;
            default:
                y0 y0Var = (y0) obj;
                c1 c1Var2 = y0Var.Q;
                y0Var.getClass();
                WeakHashMap weakHashMap = androidx.core.view.b2.f2200a;
                if (c1Var2.isAttachedToWindow() && c1Var2.getGlobalVisibleRect(y0Var.O)) {
                    y0Var.p();
                    y0Var.show();
                } else {
                    y0Var.dismiss();
                }
                break;
        }
    }
}

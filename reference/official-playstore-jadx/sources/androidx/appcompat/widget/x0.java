package androidx.appcompat.widget;

import android.view.ViewTreeObserver;
import android.widget.PopupWindow;

/* JADX INFO: loaded from: classes.dex */
public final class x0 implements PopupWindow.OnDismissListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ q0 f1832i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ y0 f1833l;

    public x0(y0 y0Var, q0 q0Var) {
        this.f1833l = y0Var;
        this.f1832i = q0Var;
    }

    @Override // android.widget.PopupWindow.OnDismissListener
    public final void onDismiss() {
        ViewTreeObserver viewTreeObserver = this.f1833l.Q.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.removeGlobalOnLayoutListener(this.f1832i);
        }
    }
}

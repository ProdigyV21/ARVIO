package androidx.appcompat.view.menu;

import android.view.View;
import android.view.ViewTreeObserver;

/* JADX INFO: loaded from: classes.dex */
public final class g implements View.OnAttachStateChangeListener {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f1290i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f1291l;

    public /* synthetic */ g(Object obj, int i10) {
        this.f1290i = i10;
        this.f1291l = obj;
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewAttachedToWindow(View view) {
        switch (this.f1290i) {
            case 0:
            case 1:
                break;
            default:
                view.getViewTreeObserver().addOnDrawListener((io.sentry.android.core.internal.util.i) this.f1291l);
                view.removeOnAttachStateChangeListener(this);
                break;
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public final void onViewDetachedFromWindow(View view) {
        switch (this.f1290i) {
            case 0:
                k kVar = (k) this.f1291l;
                ViewTreeObserver viewTreeObserver = kVar.H;
                if (viewTreeObserver != null) {
                    if (!viewTreeObserver.isAlive()) {
                        kVar.H = view.getViewTreeObserver();
                    }
                    kVar.H.removeGlobalOnLayoutListener(kVar.f1321s);
                }
                view.removeOnAttachStateChangeListener(this);
                break;
            case 1:
                h0 h0Var = (h0) this.f1291l;
                ViewTreeObserver viewTreeObserver2 = h0Var.f1309y;
                if (viewTreeObserver2 != null) {
                    if (!viewTreeObserver2.isAlive()) {
                        h0Var.f1309y = view.getViewTreeObserver();
                    }
                    h0Var.f1309y.removeGlobalOnLayoutListener(h0Var.f1304s);
                }
                view.removeOnAttachStateChangeListener(this);
                break;
            default:
                view.removeOnAttachStateChangeListener(this);
                break;
        }
    }

    private final void a(View view) {
    }

    private final void b(View view) {
    }
}

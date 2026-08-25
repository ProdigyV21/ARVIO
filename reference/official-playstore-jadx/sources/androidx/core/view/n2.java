package androidx.core.view;

import android.view.View;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes3.dex */
public final class n2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WeakReference f2294a;

    public n2(View view) {
        this.f2294a = new WeakReference(view);
    }

    public final void a(float f10) {
        View view = (View) this.f2294a.get();
        if (view != null) {
            view.animate().alpha(f10);
        }
    }

    public final void b() {
        View view = (View) this.f2294a.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public final void c(long j10) {
        View view = (View) this.f2294a.get();
        if (view != null) {
            view.animate().setDuration(j10);
        }
    }

    public final void d(o2 o2Var) {
        View view = (View) this.f2294a.get();
        if (view != null) {
            if (o2Var != null) {
                view.animate().setListener(new m2(o2Var, view, 0));
            } else {
                view.animate().setListener(null);
            }
        }
    }

    public final void e(float f10) {
        View view = (View) this.f2294a.get();
        if (view != null) {
            view.animate().translationY(f10);
        }
    }
}

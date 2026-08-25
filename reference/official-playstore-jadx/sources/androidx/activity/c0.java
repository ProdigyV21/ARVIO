package androidx.activity;

import android.view.View;
import android.view.inputmethod.InputMethodManager;

/* JADX INFO: loaded from: classes.dex */
public final class c0 implements androidx.lifecycle.w {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final x6.i0 f923l = new x6.i0(z.f1027i);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final t f924i;

    public c0(t tVar) {
        this.f924i = tVar;
    }

    @Override // androidx.lifecycle.w
    public final void onStateChanged(androidx.lifecycle.y yVar, androidx.lifecycle.p pVar) {
        InputMethodManager inputMethodManager;
        y yVar2;
        Object objB;
        if (pVar == androidx.lifecycle.p.ON_DESTROY && (objB = (yVar2 = (y) f923l.getValue()).b((inputMethodManager = (InputMethodManager) this.f924i.getSystemService("input_method")))) != null) {
            synchronized (objB) {
                View viewC = yVar2.c(inputMethodManager);
                if (viewC == null) {
                    return;
                }
                if (viewC.isAttachedToWindow()) {
                    return;
                }
                boolean zA = yVar2.a(inputMethodManager);
                if (zA) {
                    inputMethodManager.isActive();
                }
            }
        }
    }
}

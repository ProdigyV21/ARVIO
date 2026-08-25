package androidx.activity;

import android.os.Build;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import java.util.Iterator;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes.dex */
public final class l0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Runnable f963a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final kotlin.collections.p f964b = new kotlin.collections.p();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public d0 f965c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final OnBackInvokedCallback f966d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public OnBackInvokedDispatcher f967e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f968f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f969g;

    public l0(Runnable runnable) {
        this.f963a = runnable;
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 33) {
            this.f966d = i10 >= 34 ? new h0(new e0(this, 0), new e0(this, 1), new f0(this, 0), new f0(this, 1)) : new g0(new f0(this, 2), 0);
        }
    }

    public final void a(androidx.lifecycle.y yVar, d0 d0Var) {
        androidx.lifecycle.r lifecycle = yVar.getLifecycle();
        if (lifecycle.getCurrentState() == androidx.lifecycle.q.f3266i) {
            return;
        }
        d0Var.f932b.add(new i0(this, lifecycle, d0Var));
        e();
        d0Var.f933c = new k0(0, this, l0.class, "updateEnabledCallbacks", "updateEnabledCallbacks()V", 0, 0);
    }

    public final void b() {
        Object objPrevious;
        d0 d0Var = this.f965c;
        if (d0Var == null) {
            kotlin.collections.p pVar = this.f964b;
            ListIterator<E> listIterator = pVar.listIterator(pVar.size());
            while (true) {
                if (!listIterator.hasPrevious()) {
                    objPrevious = null;
                    break;
                } else {
                    objPrevious = listIterator.previous();
                    if (((d0) objPrevious).f931a) {
                        break;
                    }
                }
            }
            d0Var = (d0) objPrevious;
        }
        this.f965c = null;
        if (d0Var != null) {
            d0Var.a();
        }
    }

    public final void c() {
        Object objPrevious;
        d0 d0Var = this.f965c;
        if (d0Var == null) {
            kotlin.collections.p pVar = this.f964b;
            ListIterator listIterator = pVar.listIterator(pVar.size());
            while (true) {
                if (!listIterator.hasPrevious()) {
                    objPrevious = null;
                    break;
                } else {
                    objPrevious = listIterator.previous();
                    if (((d0) objPrevious).f931a) {
                        break;
                    }
                }
            }
            d0Var = (d0) objPrevious;
        }
        this.f965c = null;
        if (d0Var != null) {
            d0Var.b();
        } else {
            this.f963a.run();
        }
    }

    public final void d(boolean z) {
        OnBackInvokedCallback onBackInvokedCallback;
        OnBackInvokedDispatcher onBackInvokedDispatcher = this.f967e;
        if (onBackInvokedDispatcher == null || (onBackInvokedCallback = this.f966d) == null) {
            return;
        }
        if (z && !this.f968f) {
            j.c(onBackInvokedDispatcher, onBackInvokedCallback);
            this.f968f = true;
        } else {
            if (z || !this.f968f) {
                return;
            }
            j.d(onBackInvokedDispatcher, onBackInvokedCallback);
            this.f968f = false;
        }
    }

    public final void e() {
        boolean z = this.f969g;
        boolean z5 = false;
        kotlin.collections.p pVar = this.f964b;
        if (pVar == null || !pVar.isEmpty()) {
            Iterator it = pVar.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                } else if (((d0) it.next()).f931a) {
                    z5 = true;
                    break;
                }
            }
        }
        this.f969g = z5;
        if (z5 == z || Build.VERSION.SDK_INT < 33) {
            return;
        }
        d(z5);
    }
}

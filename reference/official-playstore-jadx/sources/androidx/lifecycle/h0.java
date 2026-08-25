package androidx.lifecycle;

import android.os.Looper;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public abstract class h0 {
    public static final Object k = new Object();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f3239a = new Object();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final p.f f3240b = new p.f();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3241c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f3242d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public volatile Object f3243e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile Object f3244f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f3245g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f3246h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f3247i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final androidx.leanback.widget.u f3248j;

    public h0() {
        Object obj = k;
        this.f3244f = obj;
        this.f3248j = new androidx.leanback.widget.u(this, 1);
        this.f3243e = obj;
        this.f3245g = -1;
    }

    public static void b(String str) {
        o.a.Z().f20717d.getClass();
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException(a0.c.l("Cannot invoke ", str, " on a background thread"));
        }
    }

    public final void c(g0 g0Var) {
        if (g0Var.f3236l) {
            if (!g0Var.d()) {
                g0Var.a(false);
                return;
            }
            int i10 = g0Var.f3237m;
            int i11 = this.f3245g;
            if (i10 >= i11) {
                return;
            }
            g0Var.f3237m = i11;
            g0Var.f3235i.c(this.f3243e);
        }
    }

    public final void d(g0 g0Var) {
        if (this.f3246h) {
            this.f3247i = true;
            return;
        }
        this.f3246h = true;
        do {
            this.f3247i = false;
            if (g0Var != null) {
                c(g0Var);
                g0Var = null;
            } else {
                p.f fVar = this.f3240b;
                fVar.getClass();
                p.d dVar = new p.d(fVar);
                fVar.f21034m.put(dVar, Boolean.FALSE);
                while (dVar.hasNext()) {
                    c((g0) ((Map.Entry) dVar.next()).getValue());
                    if (this.f3247i) {
                        break;
                    }
                }
            }
        } while (this.f3247i);
        this.f3246h = false;
    }

    public final void e(y yVar, k0 k0Var) {
        Object obj;
        b("observe");
        if (yVar.getLifecycle().getCurrentState() == q.f3266i) {
            return;
        }
        f0 f0Var = new f0(this, yVar, k0Var);
        p.f fVar = this.f3240b;
        p.c cVarA = fVar.a(k0Var);
        if (cVarA != null) {
            obj = cVarA.f21026l;
        } else {
            p.c cVar = new p.c(k0Var, f0Var);
            fVar.f21035n++;
            p.c cVar2 = fVar.f21033l;
            if (cVar2 == null) {
                fVar.f21032i = cVar;
                fVar.f21033l = cVar;
            } else {
                cVar2.f21027m = cVar;
                cVar.f21028n = cVar2;
                fVar.f21033l = cVar;
            }
            obj = null;
        }
        g0 g0Var = (g0) obj;
        if (g0Var != null && !g0Var.c(yVar)) {
            throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
        }
        if (g0Var != null) {
            return;
        }
        yVar.getLifecycle().addObserver(f0Var);
    }

    public void f() {
    }

    public void g() {
    }

    public void h(k0 k0Var) {
        b("removeObserver");
        g0 g0Var = (g0) this.f3240b.b(k0Var);
        if (g0Var == null) {
            return;
        }
        g0Var.b();
        g0Var.a(false);
    }

    public abstract void i(Object obj);
}

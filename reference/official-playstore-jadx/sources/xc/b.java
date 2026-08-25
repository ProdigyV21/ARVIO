package xc;

import java.lang.reflect.Method;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/* JADX INFO: loaded from: classes5.dex */
public final class b implements vc.b {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final String f22812i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public volatile vc.b f22813l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Boolean f22814m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Method f22815n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public wc.a f22816o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final Queue f22817p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final boolean f22818q;

    public b(String str, LinkedBlockingQueue linkedBlockingQueue, boolean z) {
        this.f22812i = str;
        this.f22817p = linkedBlockingQueue;
        this.f22818q = z;
    }

    @Override // vc.b
    public final void a(String str) {
        i().a(str);
    }

    @Override // vc.b
    public final boolean b() {
        return i().b();
    }

    @Override // vc.b
    public final void c(String str, Throwable th) {
        i().c(str, th);
    }

    @Override // vc.b
    public final void d(String str) {
        i().d(str);
    }

    @Override // vc.b
    public final void e(String str, Throwable th) {
        i().e(str, th);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && b.class == obj.getClass() && this.f22812i.equals(((b) obj).f22812i);
    }

    @Override // vc.b
    public final void f(String str) {
        i().f(str);
    }

    @Override // vc.b
    public final void g(String str) {
        i().g(str);
    }

    @Override // vc.b
    public final String getName() {
        return this.f22812i;
    }

    @Override // vc.b
    public final void h(String str) {
        i().h(str);
    }

    public final int hashCode() {
        return this.f22812i.hashCode();
    }

    public final vc.b i() {
        if (this.f22813l != null) {
            return this.f22813l;
        }
        if (this.f22818q) {
            return a.f22811i;
        }
        if (this.f22816o == null) {
            Queue queue = this.f22817p;
            wc.a aVar = new wc.a();
            aVar.f22542l = this;
            aVar.f22541i = this.f22812i;
            aVar.f22543m = queue;
            this.f22816o = aVar;
        }
        return this.f22816o;
    }

    public final boolean j() {
        Boolean bool = this.f22814m;
        if (bool != null) {
            return bool.booleanValue();
        }
        try {
            this.f22815n = this.f22813l.getClass().getMethod("log", wc.c.class);
            this.f22814m = Boolean.TRUE;
        } catch (NoSuchMethodException unused) {
            this.f22814m = Boolean.FALSE;
        }
        return this.f22814m.booleanValue();
    }
}

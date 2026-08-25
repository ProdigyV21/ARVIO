package wc;

import java.util.Queue;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements vc.b {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f22541i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public xc.b f22542l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Queue f22543m;

    @Override // vc.b
    public final void a(String str) {
        i();
    }

    @Override // vc.b
    public final boolean b() {
        return true;
    }

    @Override // vc.b
    public final void c(String str, Throwable th) {
        i();
    }

    @Override // vc.b
    public final void d(String str) {
        i();
    }

    @Override // vc.b
    public final void e(String str, Throwable th) {
        i();
    }

    @Override // vc.b
    public final void f(String str) {
        i();
    }

    @Override // vc.b
    public final void g(String str) {
        i();
    }

    @Override // vc.b
    public final String getName() {
        return this.f22541i;
    }

    @Override // vc.b
    public final void h(String str) {
        i();
    }

    public final void i() {
        d dVar = new d();
        System.currentTimeMillis();
        dVar.f22547a = this.f22542l;
        Thread.currentThread().getName();
        this.f22543m.add(dVar);
    }
}

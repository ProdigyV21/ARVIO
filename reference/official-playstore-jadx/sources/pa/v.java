package pa;

import ka.m0;

/* JADX INFO: loaded from: classes5.dex */
public class v extends ka.a implements f7.d {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final d7.d f21296n;

    public v(d7.j jVar, d7.d dVar) {
        super(jVar, true);
        this.f21296n = dVar;
    }

    @Override // ka.f2
    public final boolean C() {
        return true;
    }

    @Override // ka.f2
    public void g(Object obj) {
        a.g(t7.a.A(this.f21296n), m0.q(obj));
    }

    @Override // f7.d
    public final f7.d getCallerFrame() {
        d7.d dVar = this.f21296n;
        if (dVar instanceof f7.d) {
            return (f7.d) dVar;
        }
        return null;
    }

    @Override // ka.f2
    public void h(Object obj) {
        this.f21296n.resumeWith(m0.q(obj));
    }
}

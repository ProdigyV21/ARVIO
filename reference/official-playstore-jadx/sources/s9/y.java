package s9;

import g8.v0;

/* JADX INFO: loaded from: classes5.dex */
public final class y extends androidx.appcompat.view.menu.e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final a9.n f21766e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final y f21767f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final f9.b f21768g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final a9.m f21769h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final boolean f21770i;

    public y(a9.n nVar, c9.f fVar, androidx.work.impl.constraints.j jVar, v0 v0Var, y yVar) {
        super(fVar, jVar, v0Var);
        this.f21766e = nVar;
        this.f21767f = yVar;
        this.f21768g = t7.a.r(fVar, nVar.f584o);
        a9.m mVar = (a9.m) c9.e.f7437f.c(nVar.f583n);
        this.f21769h = mVar == null ? a9.m.CLASS : mVar;
        this.f21770i = c9.e.f7438g.c(nVar.f583n).booleanValue();
    }

    @Override // androidx.appcompat.view.menu.e
    public final f9.c e() {
        return this.f21768g.b();
    }
}

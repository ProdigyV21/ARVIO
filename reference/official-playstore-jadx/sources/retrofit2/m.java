package retrofit2;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes5.dex */
public final class m implements c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Executor f21521i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final c f21522l;

    public m(Executor executor, c cVar) {
        this.f21521i = executor;
        this.f21522l = cVar;
    }

    @Override // retrofit2.c
    public final void G(f fVar) {
        this.f21522l.G(new fi.iki.elonen.f((Object) this, (Object) fVar, false));
    }

    @Override // retrofit2.c
    public final void cancel() {
        this.f21522l.cancel();
    }

    @Override // retrofit2.c
    public final boolean k() {
        return this.f21522l.k();
    }

    @Override // retrofit2.c
    public final gb.k0 request() {
        return this.f21522l.request();
    }

    @Override // retrofit2.c
    public final c clone() {
        return new m(this.f21521i, this.f21522l.clone());
    }
}

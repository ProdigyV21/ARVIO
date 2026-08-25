package p;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class d extends e implements Iterator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public c f21029i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public boolean f21030l = true;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ f f21031m;

    public d(f fVar) {
        this.f21031m = fVar;
    }

    @Override // p.e
    public final void a(c cVar) {
        c cVar2 = this.f21029i;
        if (cVar == cVar2) {
            c cVar3 = cVar2.f21028n;
            this.f21029i = cVar3;
            this.f21030l = cVar3 == null;
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.f21030l) {
            return this.f21031m.f21032i != null;
        }
        c cVar = this.f21029i;
        return (cVar == null || cVar.f21027m == null) ? false : true;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (this.f21030l) {
            this.f21030l = false;
            this.f21029i = this.f21031m.f21032i;
        } else {
            c cVar = this.f21029i;
            this.f21029i = cVar != null ? cVar.f21027m : null;
        }
        return this.f21029i;
    }
}

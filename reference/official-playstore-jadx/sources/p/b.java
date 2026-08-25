package p;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class b extends e implements Iterator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public c f21022i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public c f21023l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f21024m;

    public b(c cVar, c cVar2, int i10) {
        this.f21024m = i10;
        this.f21022i = cVar2;
        this.f21023l = cVar;
    }

    @Override // p.e
    public final void a(c cVar) {
        c cVar2;
        c cVarB = null;
        if (this.f21022i == cVar && cVar == this.f21023l) {
            this.f21023l = null;
            this.f21022i = null;
        }
        c cVar3 = this.f21022i;
        if (cVar3 == cVar) {
            switch (this.f21024m) {
                case 0:
                    cVar2 = cVar3.f21028n;
                    break;
                default:
                    cVar2 = cVar3.f21027m;
                    break;
            }
            this.f21022i = cVar2;
        }
        c cVar4 = this.f21023l;
        if (cVar4 == cVar) {
            c cVar5 = this.f21022i;
            if (cVar4 != cVar5 && cVar5 != null) {
                cVarB = b(cVar4);
            }
            this.f21023l = cVarB;
        }
    }

    public final c b(c cVar) {
        switch (this.f21024m) {
            case 0:
                return cVar.f21027m;
            default:
                return cVar.f21028n;
        }
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        return this.f21023l != null;
    }

    @Override // java.util.Iterator
    public final Object next() {
        c cVar = this.f21023l;
        c cVar2 = this.f21022i;
        this.f21023l = (cVar == cVar2 || cVar2 == null) ? null : b(cVar);
        return cVar;
    }
}

package p9;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes5.dex */
public final class g extends i9.q {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ ArrayList f21221c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ h f21222d;

    public g(ArrayList arrayList, h hVar) {
        this.f21221c = arrayList;
        this.f21222d = hVar;
    }

    @Override // i9.q
    public final void b(g8.c cVar) {
        i9.n.r(cVar, null);
        this.f21221c.add(cVar);
    }

    @Override // i9.q
    public final void d(g8.c cVar, g8.c cVar2) {
        throw new IllegalStateException(("Conflict in scope of " + this.f21222d.f21224b + ": " + cVar + " vs " + cVar2).toString());
    }
}

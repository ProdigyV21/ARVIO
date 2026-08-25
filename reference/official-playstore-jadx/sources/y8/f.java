package y8;

import java.util.ArrayList;
import y8.y;

/* JADX INFO: loaded from: classes5.dex */
public final class f implements y.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ g f22933a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ArrayList f22934b;

    public f(g gVar, ArrayList arrayList) {
        this.f22933a = gVar;
        this.f22934b = arrayList;
    }

    @Override // y8.y.c
    public final y.a b(f9.b bVar, l8.a aVar) {
        if (c8.b.f7422a.contains(bVar)) {
            return null;
        }
        n nVar = (n) this.f22933a;
        return new m(nVar, g8.y.f(nVar.f22959m, bVar, nVar.f22960n), bVar, this.f22934b, aVar);
    }

    @Override // y8.y.c
    public final void a() {
    }
}

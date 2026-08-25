package y8;

import java.util.ArrayList;
import y8.y;

/* JADX INFO: loaded from: classes5.dex */
public abstract class c implements y.c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a0 f22915a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f22916b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ d f22917c;

    public c(d dVar, a0 a0Var) {
        this.f22917c = dVar;
        this.f22915a = a0Var;
    }

    @Override // y8.y.c
    public final void a() {
        ArrayList arrayList = this.f22916b;
        if (arrayList.isEmpty()) {
            return;
        }
        this.f22917c.f22920b.put(this.f22915a, arrayList);
    }

    @Override // y8.y.c
    public final y.a b(f9.b bVar, l8.a aVar) {
        e eVar = this.f22917c.f22919a;
        if (c8.b.f7422a.contains(bVar)) {
            return null;
        }
        n nVar = (n) eVar;
        return new m(nVar, g8.y.f(nVar.f22959m, bVar, nVar.f22960n), bVar, this.f22916b, aVar);
    }
}

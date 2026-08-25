package y8;

import g8.v0;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class m extends l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final HashMap f22953b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ n f22954c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final /* synthetic */ g8.f f22955d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ f9.b f22956e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ List f22957f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ v0 f22958g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(n nVar, g8.f fVar, f9.b bVar, List list, v0 v0Var) {
        super(nVar);
        this.f22954c = nVar;
        this.f22955d = fVar;
        this.f22956e = bVar;
        this.f22957f = list;
        this.f22958g = v0Var;
        this.f22953b = new HashMap();
    }

    @Override // y8.y.a
    public final void a() {
        f9.b bVar = c8.b.f7423b;
        f9.b bVar2 = this.f22956e;
        boolean zEquals = bVar2.equals(bVar);
        n nVar = this.f22954c;
        HashMap map = this.f22953b;
        boolean zF = false;
        if (zEquals) {
            Object obj = map.get(f9.f.j("value"));
            k9.s sVar = obj instanceof k9.s ? (k9.s) obj : null;
            if (sVar != null) {
                Object obj2 = sVar.f19530a;
                k9.q qVar = obj2 instanceof k9.q ? (k9.q) obj2 : null;
                if (qVar != null) {
                    zF = nVar.f(qVar.f19538a.f19528a);
                }
            }
        }
        if (zF || nVar.f(bVar2)) {
            return;
        }
        this.f22957f.add(new h8.c(this.f22955d.m(), map, this.f22958g));
    }
}

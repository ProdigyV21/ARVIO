package z8;

import y8.y;

/* JADX INFO: loaded from: classes5.dex */
public final class j implements y.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ k f23261a;

    public j(k kVar) {
        this.f23261a = kVar;
    }

    @Override // y8.y.a
    public final y.b b(f9.f fVar) {
        String strD = fVar.d();
        if ("data".equals(strD) || "filePartClassNames".equals(strD)) {
            return new h(this);
        }
        if ("strings".equals(strD)) {
            return new i(this);
        }
        return null;
    }

    @Override // y8.y.a
    public final y.a c(f9.b bVar, f9.f fVar) {
        return null;
    }

    @Override // y8.y.a
    public final void f(f9.f fVar, Object obj) {
        String strD = fVar.d();
        boolean zEquals = "version".equals(strD);
        k kVar = this.f23261a;
        if (zEquals) {
            if (obj instanceof int[]) {
                kVar.f23264a = (int[]) obj;
            }
        } else if ("multifileClassName".equals(strD)) {
            kVar.f23265b = obj instanceof String ? (String) obj : null;
        }
    }

    @Override // y8.y.a
    public final void a() {
    }

    @Override // y8.y.a
    public final void e(f9.f fVar, k9.f fVar2) {
    }

    @Override // y8.y.a
    public final void d(f9.f fVar, f9.b bVar, f9.f fVar2) {
    }
}

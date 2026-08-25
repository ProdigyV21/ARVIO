package z8;

import y8.y;

/* JADX INFO: loaded from: classes5.dex */
public final class e implements y.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ k f23256a;

    public e(k kVar) {
        this.f23256a = kVar;
    }

    @Override // y8.y.a
    public final y.b b(f9.f fVar) {
        String strD = fVar.d();
        if ("d1".equals(strD)) {
            return new c(this);
        }
        if ("d2".equals(strD)) {
            return new d(this);
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
        boolean zEquals = "k".equals(strD);
        k kVar = this.f23256a;
        if (zEquals) {
            if (obj instanceof Integer) {
                a aVar = (a) a.f23244l.get((Integer) obj);
                if (aVar == null) {
                    aVar = a.UNKNOWN;
                }
                kVar.f23270g = aVar;
                return;
            }
            return;
        }
        if ("mv".equals(strD)) {
            if (obj instanceof int[]) {
                kVar.f23264a = (int[]) obj;
            }
        } else {
            if ("xs".equals(strD)) {
                if (obj instanceof String) {
                    String str = (String) obj;
                    if (str.isEmpty()) {
                        return;
                    }
                    kVar.f23265b = str;
                    return;
                }
                return;
            }
            if (!"xi".equals(strD)) {
                "pn".equals(strD);
            } else if (obj instanceof Integer) {
                kVar.f23266c = ((Integer) obj).intValue();
            }
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

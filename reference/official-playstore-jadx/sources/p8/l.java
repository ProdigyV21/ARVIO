package p8;

import g8.p0;

/* JADX INFO: loaded from: classes5.dex */
public final class l implements i9.g {
    @Override // i9.g
    public final int a(g8.a aVar, g8.a aVar2, g8.f fVar) {
        if ((aVar2 instanceof p0) && (aVar instanceof p0)) {
            p0 p0Var = (p0) aVar2;
            p0 p0Var2 = (p0) aVar;
            if (kotlin.jvm.internal.p.a(p0Var.getName(), p0Var2.getName())) {
                if (p0Var.getGetter() == null && p0Var2.getGetter() == null) {
                    return 1;
                }
                if (p0Var.getGetter() == null || p0Var2.getGetter() == null) {
                    return 3;
                }
            }
        }
        return 4;
    }

    @Override // i9.g
    public final int b() {
        return 3;
    }
}

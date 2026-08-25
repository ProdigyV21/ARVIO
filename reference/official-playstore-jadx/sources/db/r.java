package db;

import java.util.List;
import x6.i0;

/* JADX INFO: loaded from: classes5.dex */
public final class r implements ab.g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final i0 f14923a;

    public r(r7.a aVar) {
        this.f14923a = new i0(aVar);
    }

    public final ab.g a() {
        return (ab.g) this.f14923a.getValue();
    }

    @Override // ab.g
    public final boolean b() {
        return false;
    }

    @Override // ab.g
    public final int c(String str) {
        return a().c(str);
    }

    @Override // ab.g
    public final int d() {
        return a().d();
    }

    @Override // ab.g
    public final String e(int i10) {
        return a().e(i10);
    }

    @Override // ab.g
    public final List f(int i10) {
        return a().f(i10);
    }

    @Override // ab.g
    public final ab.g g(int i10) {
        return a().g(i10);
    }

    @Override // ab.g
    public final List getAnnotations() {
        return kotlin.collections.z.f19728i;
    }

    @Override // ab.g
    public final a.a getKind() {
        return a().getKind();
    }

    @Override // ab.g
    public final String h() {
        return a().h();
    }

    @Override // ab.g
    public final boolean i(int i10) {
        return a().i(i10);
    }

    @Override // ab.g
    public final boolean isInline() {
        return false;
    }
}

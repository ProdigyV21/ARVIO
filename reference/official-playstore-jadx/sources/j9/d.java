package j9;

import g8.b1;
import h8.h;
import v9.r0;
import v9.v0;
import v9.w;

/* JADX INFO: loaded from: classes5.dex */
public final class d extends v0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f19408b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final v0 f19409c;

    public /* synthetic */ d(v0 v0Var, int i10) {
        this.f19408b = i10;
        this.f19409c = v0Var;
    }

    @Override // v9.v0
    public boolean a() {
        switch (this.f19408b) {
            case 0:
                return this.f19409c.a();
            default:
                return super.a();
        }
    }

    @Override // v9.v0
    public boolean b() {
        switch (this.f19408b) {
            case 0:
                return true;
            default:
                return super.b();
        }
    }

    @Override // v9.v0
    public final h c(h hVar) {
        switch (this.f19408b) {
        }
        return this.f19409c.c(hVar);
    }

    @Override // v9.v0
    public final r0 d(w wVar) {
        switch (this.f19408b) {
            case 0:
                r0 r0VarD = this.f19409c.d(wVar);
                if (r0VarD == null) {
                    return null;
                }
                g8.h hVarE = wVar.I0().e();
                return k2.c.g(r0VarD, hVarE instanceof b1 ? (b1) hVarE : null);
            default:
                return this.f19409c.d(wVar);
        }
    }

    @Override // v9.v0
    public final boolean e() {
        switch (this.f19408b) {
        }
        return this.f19409c.e();
    }

    @Override // v9.v0
    public final w f(int i10, w wVar) {
        switch (this.f19408b) {
        }
        return this.f19409c.f(i10, wVar);
    }
}

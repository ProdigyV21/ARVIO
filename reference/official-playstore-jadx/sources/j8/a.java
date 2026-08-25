package j8;

import java.util.List;
import v9.b1;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f19240i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ b f19241l;

    public /* synthetic */ a(b bVar, int i10) {
        this.f19240i = i10;
        this.f19241l = bVar;
    }

    @Override // r7.a
    public final Object invoke() {
        int i10 = this.f19240i;
        b bVar = this.f19241l;
        switch (i10) {
            case 0:
                p9.n nVarU = bVar.U();
                d8.i iVar = new d8.i(this, 1);
                x9.h hVar = b1.f22188a;
                if (x9.k.e(bVar)) {
                    return x9.k.b(x9.j.f22700u, bVar.toString());
                }
                v9.o0 o0VarG = bVar.g();
                if (o0VarG == null) {
                    b1.a(12);
                    throw null;
                }
                if (nVarU == null) {
                    b1.a(13);
                    throw null;
                }
                List listD = b1.d(o0VarG.getParameters());
                v9.k0.f22220l.getClass();
                return v9.e.t(v9.k0.f22221m, o0VarG, listD, false, nVarU, iVar);
            case 1:
                return new p9.i(bVar.U());
            default:
                return new x(bVar);
        }
    }
}

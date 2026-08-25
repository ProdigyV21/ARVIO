package k9;

import g8.b0;
import java.util.Collections;
import kotlin.NoWhenBranchMatchedException;
import v9.d1;
import v9.k0;
import v9.t0;

/* JADX INFO: loaded from: classes5.dex */
public final class s extends g {
    public s(f fVar) {
        super(new q(fVar));
    }

    @Override // k9.g
    public final v9.w a(b0 b0Var) {
        v9.w wVarB;
        k0.f22220l.getClass();
        k0 k0Var = k0.f22221m;
        d8.k kVarJ = b0Var.j();
        kVarJ.getClass();
        g8.f fVarI = kVarJ.i(d8.p.P.g());
        Object obj = this.f19530a;
        r rVar = (r) obj;
        if (rVar instanceof p) {
            wVarB = ((p) obj).f19537a;
        } else {
            if (!(rVar instanceof q)) {
                throw new NoWhenBranchMatchedException();
            }
            f fVar = ((q) obj).f19538a;
            f9.b bVar = fVar.f19528a;
            int i10 = fVar.f19529b;
            g8.f fVarD = g8.y.d(b0Var, bVar);
            if (fVarD == null) {
                wVarB = x9.k.b(x9.j.f22693n, bVar.toString(), String.valueOf(i10));
            } else {
                d1 d1VarG0 = xc.d.G0(fVarD.m());
                for (int i11 = 0; i11 < i10; i11++) {
                    d1VarG0 = b0Var.j().h(d1VarG0);
                }
                wVarB = d1VarG0;
            }
        }
        return v9.e.r(Collections.singletonList(new t0(wVarB)), k0Var, fVarI.g(), false);
    }

    public s(f9.b bVar, int i10) {
        this(new f(bVar, i10));
    }
}

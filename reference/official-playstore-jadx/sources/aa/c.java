package aa;

import v9.o0;
import v9.p0;
import v9.r0;
import v9.t0;

/* JADX INFO: loaded from: classes5.dex */
public final class c extends p0 {
    @Override // v9.p0
    public final r0 g(o0 o0Var) {
        j9.b bVar = o0Var instanceof j9.b ? (j9.b) o0Var : null;
        if (bVar == null) {
            return null;
        }
        return bVar.b().b() ? new t0(3, bVar.b().getType()) : bVar.b();
    }
}

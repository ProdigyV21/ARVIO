package e0;

import m2.f0;

/* JADX INFO: loaded from: classes3.dex */
public final class e extends f0 {
    @Override // m2.f0
    public final void H(f fVar, f fVar2) {
        fVar.f14985b = fVar2;
    }

    @Override // m2.f0
    public final void I(f fVar, Thread thread) {
        fVar.f14984a = thread;
    }

    @Override // m2.f0
    public final boolean c(g gVar, c cVar, c cVar2) {
        synchronized (gVar) {
            try {
                if (gVar.f14991l != cVar) {
                    return false;
                }
                gVar.f14991l = cVar2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // m2.f0
    public final boolean d(g gVar, Object obj, Object obj2) {
        synchronized (gVar) {
            try {
                if (gVar.f14990i != obj) {
                    return false;
                }
                gVar.f14990i = obj2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // m2.f0
    public final boolean e(g gVar, f fVar, f fVar2) {
        synchronized (gVar) {
            try {
                if (gVar.f14992m != fVar) {
                    return false;
                }
                gVar.f14992m = fVar2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

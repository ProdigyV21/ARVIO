package androidx.work.impl.utils.futures;

import com.google.common.util.concurrent.p0;

/* JADX INFO: loaded from: classes3.dex */
public final class g extends p0 {
    @Override // com.google.common.util.concurrent.p0
    public final boolean d(i iVar, d dVar, d dVar2) {
        synchronized (iVar) {
            try {
                if (iVar.f7038l != dVar) {
                    return false;
                }
                iVar.f7038l = dVar2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.common.util.concurrent.p0
    public final boolean e(i iVar, Object obj, Object obj2) {
        synchronized (iVar) {
            try {
                if (iVar.f7037i != obj) {
                    return false;
                }
                iVar.f7037i = obj2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.common.util.concurrent.p0
    public final boolean f(i iVar, h hVar, h hVar2) {
        synchronized (iVar) {
            try {
                if (iVar.f7039m != hVar) {
                    return false;
                }
                iVar.f7039m = hVar2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.common.util.concurrent.p0
    public final void x(h hVar, h hVar2) {
        hVar.f7032b = hVar2;
    }

    @Override // com.google.common.util.concurrent.p0
    public final void y(h hVar, Thread thread) {
        hVar.f7031a = thread;
    }
}

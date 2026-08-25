package com.google.common.util.concurrent;

/* JADX INFO: loaded from: classes4.dex */
public final class g extends a {
    @Override // com.google.common.util.concurrent.a
    public final boolean a(p pVar, d dVar, d dVar2) {
        synchronized (pVar) {
            try {
                if (pVar.listeners != dVar) {
                    return false;
                }
                pVar.listeners = dVar2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.common.util.concurrent.a
    public final boolean b(p pVar, Object obj, Object obj2) {
        synchronized (pVar) {
            try {
                if (pVar.value != obj) {
                    return false;
                }
                pVar.value = obj2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.common.util.concurrent.a
    public final boolean c(p pVar, o oVar, o oVar2) {
        synchronized (pVar) {
            try {
                if (pVar.waiters != oVar) {
                    return false;
                }
                pVar.waiters = oVar2;
                return true;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.google.common.util.concurrent.a
    public final d d(p pVar) {
        d dVar;
        d dVar2 = d.f14196d;
        synchronized (pVar) {
            try {
                dVar = pVar.listeners;
                if (dVar != dVar2) {
                    pVar.listeners = dVar2;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return dVar;
    }

    @Override // com.google.common.util.concurrent.a
    public final o e(p pVar) {
        o oVar;
        o oVar2 = o.f14225c;
        synchronized (pVar) {
            try {
                oVar = pVar.waiters;
                if (oVar != oVar2) {
                    pVar.waiters = oVar2;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return oVar;
    }

    @Override // com.google.common.util.concurrent.a
    public final void f(o oVar, o oVar2) {
        oVar.f14227b = oVar2;
    }

    @Override // com.google.common.util.concurrent.a
    public final void g(o oVar, Thread thread) {
        oVar.f14226a = thread;
    }
}

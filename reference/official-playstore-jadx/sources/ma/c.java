package ma;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import ka.m0;
import ka.y2;
import x6.c0;

/* JADX INFO: loaded from: classes5.dex */
public final class c implements l, y2 {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object f20303i = h.f20337p;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public ka.l f20304l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ f f20305m;

    public c(f fVar) {
        this.f20305m = fVar;
    }

    @Override // ma.l
    public final Object a(f7.c cVar) throws Throwable {
        p pVarK;
        p pVarK2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f.f20317q;
        f fVar = this.f20305m;
        p pVar = (p) atomicReferenceFieldUpdater.get(fVar);
        while (!fVar.r()) {
            long andIncrement = f.f20313m.getAndIncrement(fVar);
            long j10 = h.f20324b;
            long j11 = andIncrement / j10;
            int i10 = (int) (andIncrement % j10);
            if (pVar.f21298m != j11) {
                pVarK = fVar.k(j11, pVar);
                if (pVarK == null) {
                    continue;
                }
            } else {
                pVarK = pVar;
            }
            Object objB = fVar.B(pVarK, i10, andIncrement, null);
            c2.a aVar = h.f20334m;
            if (objB == aVar) {
                throw new IllegalStateException("unreachable");
            }
            c2.a aVar2 = h.f20336o;
            if (objB == aVar2) {
                if (andIncrement < fVar.o()) {
                    pVarK.a();
                }
                pVar = pVarK;
            } else {
                if (objB != h.f20335n) {
                    pVarK.a();
                    this.f20303i = objB;
                    return Boolean.TRUE;
                }
                ka.l lVarL = m0.l(t7.a.A(cVar));
                try {
                    this.f20304l = lVarL;
                    try {
                        Object objB2 = fVar.B(pVarK, i10, andIncrement, this);
                        if (objB2 == aVar) {
                            e(pVarK, i10);
                        } else {
                            if (objB2 == aVar2) {
                                if (andIncrement < fVar.o()) {
                                    pVarK.a();
                                }
                                p pVar2 = (p) f.f20317q.get(fVar);
                                while (true) {
                                    if (fVar.r()) {
                                        ka.l lVar = this.f20304l;
                                        this.f20304l = null;
                                        this.f20303i = h.f20333l;
                                        Throwable thL = fVar.l();
                                        if (thL == null) {
                                            lVar.resumeWith(Boolean.FALSE);
                                        } else {
                                            lVar.resumeWith(new c0(thL));
                                        }
                                    } else {
                                        long andIncrement2 = f.f20313m.getAndIncrement(fVar);
                                        long j12 = h.f20324b;
                                        long j13 = andIncrement2 / j12;
                                        int i11 = (int) (andIncrement2 % j12);
                                        if (pVar2.f21298m != j13) {
                                            pVarK2 = fVar.k(j13, pVar2);
                                            if (pVarK2 == null) {
                                            }
                                        } else {
                                            pVarK2 = pVar2;
                                        }
                                        Object objB3 = fVar.B(pVarK2, i11, andIncrement2, this);
                                        if (objB3 == h.f20334m) {
                                            e(pVarK2, i11);
                                            break;
                                        }
                                        if (objB3 == h.f20336o) {
                                            if (andIncrement2 < fVar.o()) {
                                                pVarK2.a();
                                            }
                                            pVar2 = pVarK2;
                                        } else {
                                            if (objB3 == h.f20335n) {
                                                throw new IllegalStateException("unexpected");
                                            }
                                            pVarK2.a();
                                            this.f20303i = objB3;
                                            this.f20304l = null;
                                        }
                                    }
                                }
                            } else {
                                pVarK.a();
                                this.f20303i = objB2;
                                this.f20304l = null;
                            }
                            lVarL.d(Boolean.TRUE, null);
                        }
                        return lVarL.r();
                    } catch (Throwable th) {
                        th = th;
                        lVarL.y();
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
        }
        this.f20303i = h.f20333l;
        Throwable thL2 = fVar.l();
        if (thL2 == null) {
            return Boolean.FALSE;
        }
        int i12 = pa.x.f21299a;
        throw thL2;
    }

    @Override // ka.y2
    public final void e(pa.w wVar, int i10) {
        ka.l lVar = this.f20304l;
        if (lVar != null) {
            lVar.e(wVar, i10);
        }
    }

    @Override // ma.l
    public final Object next() throws Throwable {
        Object obj = this.f20303i;
        c2.a aVar = h.f20337p;
        if (obj == aVar) {
            throw new IllegalStateException("`hasNext()` has not been invoked");
        }
        this.f20303i = aVar;
        if (obj != h.f20333l) {
            return obj;
        }
        Throwable thM = this.f20305m.m();
        int i10 = pa.x.f21299a;
        throw thM;
    }
}

package j8;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import v9.y0;

/* JADX INFO: loaded from: classes5.dex */
public final class h implements r7.a, ob.s {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f19280i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f19281l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f19282m;

    public /* synthetic */ h(Object obj, Object obj2, int i10) {
        this.f19280i = i10;
        this.f19282m = obj;
        this.f19281l = obj2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:53:0x00e0, code lost:
    
        if (r18 == false) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00e2, code lost:
    
        r2.j(ib.c.f16269b, true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00e7, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:?, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(boolean r18, int r19, xb.l r20, int r21) throws java.io.EOFException {
        /*
            Method dump skipped, instruction units count: 232
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: j8.h.a(boolean, int, xb.l, int):void");
    }

    public void b(int i10, xb.m mVar) {
        int i11;
        Object[] array;
        mVar.f();
        ob.o oVar = (ob.o) this.f19282m;
        synchronized (oVar) {
            array = oVar.f20923l.values().toArray(new ob.x[0]);
            oVar.f20927p = true;
        }
        for (ob.x xVar : (ob.x[]) array) {
            if (xVar.f20965a > i10 && xVar.h()) {
                xVar.k(8);
                ((ob.o) this.f19282m).l(xVar.f20965a);
            }
        }
    }

    public void c(boolean z, List list, int i10) {
        ob.o oVar = (ob.o) this.f19282m;
        if (i10 != 0 && (i10 & 1) == 0) {
            oVar.f20930s.c(new ob.k(oVar.f20924m + '[' + i10 + "] onHeaders", oVar, i10, list, z), 0L);
            return;
        }
        synchronized (oVar) {
            ob.x xVarJ = oVar.j(i10);
            if (xVarJ != null) {
                xVarJ.j(ib.c.v(list), z);
                return;
            }
            if (oVar.f20927p) {
                return;
            }
            if (i10 <= oVar.f20925n) {
                return;
            }
            if (i10 % 2 == oVar.f20926o % 2) {
                return;
            }
            ob.x xVar = new ob.x(i10, oVar, false, z, ib.c.v(list));
            oVar.f20925n = i10;
            oVar.f20923l.put(Integer.valueOf(i10), xVar);
            oVar.f20928q.e().c(new ob.h(1, oVar, xVar, oVar.f20924m + '[' + i10 + "] onStream"), 0L);
        }
    }

    public void d(int i10, int i11, boolean z) {
        if (!z) {
            ((ob.o) this.f19282m).f20929r.c(new ob.i(a0.c.p(new StringBuilder(), ((ob.o) this.f19282m).f20924m, " ping"), (ob.o) this.f19282m, i10, i11, 0), 0L);
            return;
        }
        ob.o oVar = (ob.o) this.f19282m;
        synchronized (oVar) {
            try {
                if (i10 == 1) {
                    oVar.f20934w++;
                } else if (i10 == 2) {
                    oVar.f20935y++;
                } else if (i10 == 3) {
                    oVar.notifyAll();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public void e(int i10, List list) {
        ob.o oVar = (ob.o) this.f19282m;
        synchronized (oVar) {
            if (oVar.J.contains(Integer.valueOf(i10))) {
                oVar.G(i10, 2);
                return;
            }
            oVar.J.add(Integer.valueOf(i10));
            oVar.f20930s.c(new ob.k(oVar.f20924m + '[' + i10 + "] onRequest", oVar, i10, list), 0L);
        }
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f19280i) {
            case 0:
                v9.k0.f22220l.getClass();
                v9.k0 k0Var = v9.k0.f22221m;
                v9.o0 o0VarG = ((j) this.f19282m).g();
                return v9.e.s(Collections.EMPTY_LIST, new p9.j(u9.p.f22089e, new d8.h(this, 1)), k0Var, o0VarG, false);
            case 1:
                ea.i iVar = new ea.i();
                Iterator it = ((w) this.f19282m).k().iterator();
                while (it.hasNext()) {
                    iVar.add(((g8.w) it.next()).b((y0) this.f19281l));
                }
                return iVar;
            default:
                ob.o oVar = (ob.o) this.f19282m;
                ob.t tVar = (ob.t) this.f19281l;
                try {
                } catch (IOException e5) {
                    oVar.i(2, 2, e5);
                } catch (Throwable th) {
                    oVar.i(3, 3, null);
                    ib.c.d(tVar);
                    throw th;
                }
                if (!tVar.i(true, this)) {
                    throw new IOException("Required SETTINGS preface not received");
                }
                while (tVar.i(false, this)) {
                }
                oVar.i(1, 9, null);
                ib.c.d(tVar);
                return x6.t0.f22605a;
        }
    }
}

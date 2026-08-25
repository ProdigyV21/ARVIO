package androidx.lifecycle;

import android.os.Looper;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class a0 extends r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f3196a = true;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public p.a f3197b = new p.a();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public q f3198c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final WeakReference f3199d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3200e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f3201f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f3202g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ArrayList f3203h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final na.j1 f3204i;

    public a0(y yVar) {
        q qVar = q.f3267l;
        this.f3198c = qVar;
        this.f3203h = new ArrayList();
        this.f3199d = new WeakReference(yVar);
        this.f3204i = na.y0.b(qVar);
    }

    public final q a(x xVar) {
        z zVar;
        HashMap map = this.f3197b.f21021o;
        p.c cVar = map.containsKey(xVar) ? ((p.c) map.get(xVar)).f21028n : null;
        q qVar = (cVar == null || (zVar = (z) cVar.f21026l) == null) ? null : zVar.f3314a;
        ArrayList arrayList = this.f3203h;
        q qVar2 = arrayList.isEmpty() ? null : (q) a0.c.h(1, arrayList);
        q qVar3 = this.f3198c;
        if (qVar == null || qVar.compareTo(qVar3) >= 0) {
            qVar = qVar3;
        }
        return (qVar2 == null || qVar2.compareTo(qVar) >= 0) ? qVar : qVar2;
    }

    @Override // androidx.lifecycle.r
    public final void addObserver(x xVar) {
        w gVar;
        Object obj;
        y yVar;
        b("addObserver");
        q qVar = this.f3198c;
        q qVar2 = q.f3266i;
        if (qVar != qVar2) {
            qVar2 = q.f3267l;
        }
        z zVar = new z();
        HashMap map = d0.f3223a;
        boolean z = xVar instanceof w;
        boolean z5 = xVar instanceof DefaultLifecycleObserver;
        int i10 = 2;
        Object obj2 = null;
        if (z && z5) {
            gVar = new g((DefaultLifecycleObserver) xVar, (w) xVar, i);
        } else if (z5) {
            gVar = new g((DefaultLifecycleObserver) xVar, obj2, i);
        } else if (z) {
            gVar = (w) xVar;
        } else {
            Class<?> cls = xVar.getClass();
            if (d0.b(cls) == 2) {
                List list = (List) d0.f3224b.get(cls);
                if (list.size() == 1) {
                    gVar = new b2.b(d0.a((Constructor) list.get(0), xVar), 4);
                } else {
                    int size = list.size();
                    i[] iVarArr = new i[size];
                    for (int i11 = 0; i11 < size; i11++) {
                        iVarArr[i11] = d0.a((Constructor) list.get(i11), xVar);
                    }
                    gVar = new b2.b(iVarArr, i10);
                }
            } else {
                gVar = new g(xVar);
            }
        }
        zVar.f3315b = gVar;
        zVar.f3314a = qVar2;
        p.a aVar = this.f3197b;
        p.c cVarA = aVar.a(xVar);
        if (cVarA != null) {
            obj = cVarA.f21026l;
        } else {
            HashMap map2 = aVar.f21021o;
            p.c cVar = new p.c(xVar, zVar);
            aVar.f21035n++;
            p.c cVar2 = aVar.f21033l;
            if (cVar2 == null) {
                aVar.f21032i = cVar;
                aVar.f21033l = cVar;
            } else {
                cVar2.f21027m = cVar;
                cVar.f21028n = cVar2;
                aVar.f21033l = cVar;
            }
            map2.put(xVar, cVar);
            obj = null;
        }
        if (((z) obj) == null && (yVar = (y) this.f3199d.get()) != null) {
            i = (this.f3200e != 0 || this.f3201f) ? 1 : 0;
            q qVarA = a(xVar);
            this.f3200e++;
            while (zVar.f3314a.compareTo(qVarA) < 0 && this.f3197b.f21021o.containsKey(xVar)) {
                q qVar3 = zVar.f3314a;
                ArrayList arrayList = this.f3203h;
                arrayList.add(qVar3);
                n nVar = p.Companion;
                q qVar4 = zVar.f3314a;
                nVar.getClass();
                int iOrdinal = qVar4.ordinal();
                p pVar = iOrdinal != 1 ? iOrdinal != 2 ? iOrdinal != 3 ? null : p.ON_RESUME : p.ON_START : p.ON_CREATE;
                if (pVar == null) {
                    throw new IllegalStateException("no event up from " + zVar.f3314a);
                }
                zVar.a(yVar, pVar);
                arrayList.remove(arrayList.size() - 1);
                qVarA = a(xVar);
            }
            if (i == 0) {
                f();
            }
            this.f3200e--;
        }
    }

    public final void b(String str) {
        if (this.f3196a) {
            o.a.Z().f20717d.getClass();
            if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
                throw new IllegalStateException(a0.c.l("Method ", str, " must be called on the main thread").toString());
            }
        }
    }

    public final void c(p pVar) {
        b("handleLifecycleEvent");
        d(pVar.a());
    }

    public final void d(q qVar) {
        q qVar2 = this.f3198c;
        if (qVar2 == qVar) {
            return;
        }
        q qVar3 = q.f3267l;
        q qVar4 = q.f3266i;
        if (qVar2 == qVar3 && qVar == qVar4) {
            throw new IllegalStateException(("no event down from " + this.f3198c + " in component " + this.f3199d.get()).toString());
        }
        this.f3198c = qVar;
        if (this.f3201f || this.f3200e != 0) {
            this.f3202g = true;
            return;
        }
        this.f3201f = true;
        f();
        this.f3201f = false;
        if (this.f3198c == qVar4) {
            this.f3197b = new p.a();
        }
    }

    public final void e(q qVar) {
        b("setCurrentState");
        d(qVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0028, code lost:
    
        r8.f3202g = false;
        r8.f3204i.setValue(r8.f3198c);
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0031, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void f() {
        /*
            Method dump skipped, instruction units count: 359
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.lifecycle.a0.f():void");
    }

    @Override // androidx.lifecycle.r
    public final q getCurrentState() {
        return this.f3198c;
    }

    @Override // androidx.lifecycle.r
    public final na.h1 getCurrentStateFlow() {
        return na.y0.e(this.f3204i);
    }

    @Override // androidx.lifecycle.r
    public final void removeObserver(x xVar) {
        b("removeObserver");
        this.f3197b.b(xVar);
    }
}

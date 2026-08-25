package androidx.lifecycle;

import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class g implements w {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3232i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f3233l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f3234m;

    public /* synthetic */ g(Object obj, Object obj2, int i10) {
        this.f3232i = i10;
        this.f3233l = obj;
        this.f3234m = obj2;
    }

    @Override // androidx.lifecycle.w
    public final void onStateChanged(y yVar, p pVar) {
        switch (this.f3232i) {
            case 0:
                DefaultLifecycleObserver defaultLifecycleObserver = (DefaultLifecycleObserver) this.f3233l;
                switch (f.f3227a[pVar.ordinal()]) {
                    case 1:
                        defaultLifecycleObserver.onCreate(yVar);
                        break;
                    case 2:
                        defaultLifecycleObserver.onStart(yVar);
                        break;
                    case 3:
                        defaultLifecycleObserver.onResume(yVar);
                        break;
                    case 4:
                        defaultLifecycleObserver.onPause(yVar);
                        break;
                    case 5:
                        defaultLifecycleObserver.onStop(yVar);
                        break;
                    case 6:
                        defaultLifecycleObserver.onDestroy(yVar);
                        break;
                    case 7:
                        throw new IllegalArgumentException("ON_ANY must not been send by anybody");
                }
                w wVar = (w) this.f3234m;
                if (wVar != null) {
                    wVar.onStateChanged(yVar, pVar);
                    return;
                }
                return;
            case 1:
                if (pVar == p.ON_START) {
                    ((r) this.f3233l).removeObserver(this);
                    ((b2.e) this.f3234m).d();
                    return;
                }
                return;
            default:
                HashMap map = ((a) this.f3234m).f3194a;
                List list = (List) map.get(pVar);
                Object obj = this.f3233l;
                a.a(list, yVar, pVar, obj);
                a.a((List) map.get(p.ON_ANY), yVar, pVar, obj);
                return;
        }
    }

    public g(x xVar) {
        this.f3232i = 2;
        this.f3233l = xVar;
        c cVar = c.f3216c;
        Class<?> cls = xVar.getClass();
        a aVar = (a) cVar.f3217a.get(cls);
        this.f3234m = aVar == null ? cVar.a(cls, null) : aVar;
    }
}

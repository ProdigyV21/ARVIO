package u9;

import com.google.android.gms.internal.auth.d1;
import j$.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes5.dex */
public class m implements r {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final p f22081i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final ConcurrentHashMap f22082l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final r7.l f22083m;

    public m(p pVar, ConcurrentHashMap concurrentHashMap, r7.l lVar) {
        this.f22081i = pVar;
        this.f22082l = concurrentHashMap;
        this.f22083m = lVar;
    }

    public static /* synthetic */ void b(int i10) {
        String str = (i10 == 3 || i10 == 4) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i10 == 3 || i10 == 4) ? 2 : 3];
        if (i10 == 1) {
            objArr[0] = "map";
        } else if (i10 == 2) {
            objArr[0] = "compute";
        } else if (i10 == 3 || i10 == 4) {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$MapBasedMemoizedFunction";
        } else {
            objArr[0] = "storageManager";
        }
        if (i10 == 3) {
            objArr[1] = "recursionDetected";
        } else if (i10 != 4) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/storage/LockBasedStorageManager$MapBasedMemoizedFunction";
        } else {
            objArr[1] = "raceCondition";
        }
        if (i10 != 3 && i10 != 4) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i10 != 3 && i10 != 4) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public final AssertionError c(Object obj, Object obj2) {
        AssertionError assertionError = new AssertionError("Inconsistent key detected. " + o.f22085l + " is expected, was: " + obj2 + ", most probably race condition detected on input " + obj + " under " + this.f22081i);
        p.i(assertionError);
        return assertionError;
    }

    public final AssertionError d(Object obj, Object obj2) {
        AssertionError assertionError = new AssertionError("Race condition detected on input " + obj + ". Old value is " + obj2 + " under " + this.f22081i);
        p.i(assertionError);
        return assertionError;
    }

    public final AssertionError e(Object obj, Throwable th) {
        AssertionError assertionError = new AssertionError("Unable to remove " + obj + " under " + this.f22081i, th);
        p.i(assertionError);
        return assertionError;
    }

    @Override // r7.l
    public Object invoke(Object obj) throws Throwable {
        Object obj2;
        AssertionError assertionErrorE;
        p pVar = this.f22081i;
        c cVar = pVar.f22091b;
        u uVar = pVar.f22090a;
        ConcurrentHashMap concurrentHashMap = this.f22082l;
        Object obj3 = concurrentHashMap.get(obj);
        Object obj4 = ea.o.f15099a;
        o oVar = o.f22085l;
        if (obj3 != null && obj3 != oVar) {
            ea.o.j(obj3);
            if (obj3 == obj4) {
                return null;
            }
            return obj3;
        }
        uVar.lock();
        try {
            obj2 = concurrentHashMap.get(obj);
            o oVar2 = o.f22086m;
            if (obj2 == oVar) {
                d1 d1VarH = pVar.h(obj, "");
                if (d1VarH == null) {
                    b(3);
                    throw null;
                }
                if (!d1VarH.f13017b) {
                    return d1VarH.f13018c;
                }
                obj2 = oVar2;
            }
            if (obj2 == oVar2) {
                d1 d1VarH2 = pVar.h(obj, "");
                if (d1VarH2 == null) {
                    b(3);
                    throw null;
                }
                if (!d1VarH2.f13017b) {
                    return d1VarH2.f13018c;
                }
            }
        } finally {
        }
        if (obj2 != null) {
            ea.o.j(obj2);
            return obj2 != obj4 ? obj2 : null;
        }
        try {
            concurrentHashMap.put(obj, oVar);
            Object objInvoke = this.f22083m.invoke(obj);
            if (objInvoke != null) {
                obj4 = objInvoke;
            }
            Object objPut = concurrentHashMap.put(obj, obj4);
            if (objPut == oVar) {
                return objInvoke;
            }
            assertionErrorD = d(obj, objPut);
            throw assertionErrorD;
        } catch (Throwable th) {
            if (ea.o.i(th)) {
                try {
                    Object objRemove = concurrentHashMap.remove(obj);
                    if (objRemove != oVar) {
                        throw c(obj, objRemove);
                    }
                    throw th;
                } finally {
                }
            }
            if (th == assertionErrorD) {
                try {
                    concurrentHashMap.remove(obj);
                    cVar.getClass();
                    throw th;
                } finally {
                }
            }
            Object objPut2 = concurrentHashMap.put(obj, new ea.n(th));
            if (objPut2 != oVar) {
                throw d(obj, objPut2);
            }
            cVar.getClass();
            throw th;
        }
        uVar.unlock();
    }

    @Override // u9.r
    public final boolean m(f9.c cVar) {
        Object obj = this.f22082l.get(cVar);
        return (obj == null || obj == o.f22085l) ? false : true;
    }
}

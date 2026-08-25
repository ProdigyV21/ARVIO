package pa;

import ka.q2;

/* JADX INFO: loaded from: classes5.dex */
public abstract class c0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final c2.a f21257a = new c2.a("NO_THREAD_ELEMENTS", 5);

    public static final void a(d7.j jVar, Object obj) {
        if (obj == f21257a) {
            return;
        }
        if (!(obj instanceof f0)) {
            ((q2) jVar.fold(null, a0.f21253i)).restoreThreadContext(jVar, obj);
            return;
        }
        f0 f0Var = (f0) obj;
        q2[] q2VarArr = f0Var.f21266c;
        int length = q2VarArr.length - 1;
        if (length < 0) {
            return;
        }
        while (true) {
            int i10 = length - 1;
            q2VarArr[length].restoreThreadContext(jVar, f0Var.f21265b[length]);
            if (i10 < 0) {
                return;
            } else {
                length = i10;
            }
        }
    }

    public static final Object b(d7.j jVar, Object obj) {
        if (obj == null) {
            obj = jVar.fold(0, z.f21301i);
        }
        return obj == 0 ? f21257a : obj instanceof Integer ? jVar.fold(new f0(((Number) obj).intValue(), jVar), b0.f21255i) : ((q2) obj).updateThreadContext(jVar);
    }
}

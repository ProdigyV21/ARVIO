package pa;

import d7.j;
import ka.q2;

/* JADX INFO: loaded from: classes5.dex */
public final class b0 extends kotlin.jvm.internal.r implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final b0 f21255i = new b0(2);

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        f0 f0Var = (f0) obj;
        j.a aVar = (j.a) obj2;
        if (aVar instanceof q2) {
            q2 q2Var = (q2) aVar;
            Object objUpdateThreadContext = q2Var.updateThreadContext(f0Var.f21264a);
            Object[] objArr = f0Var.f21265b;
            int i10 = f0Var.f21267d;
            objArr[i10] = objUpdateThreadContext;
            q2[] q2VarArr = f0Var.f21266c;
            f0Var.f21267d = i10 + 1;
            q2VarArr[i10] = q2Var;
        }
        return f0Var;
    }
}

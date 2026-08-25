package ka;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class e2 extends kotlin.jvm.internal.m implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final e2 f19581i = new e2(3, f2.class, "registerSelectForOnJoin", "registerSelectForOnJoin(Lkotlinx/coroutines/selects/SelectInstance;Ljava/lang/Object;)V", 0);

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        Object objY;
        x6.t0 t0Var;
        f2 f2Var = (f2) obj;
        sa.f fVar = (sa.f) obj2;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2.f19587i;
        do {
            objY = f2Var.y();
            boolean z = objY instanceof p1;
            t0Var = x6.t0.f22605a;
            if (!z) {
                fVar.a();
                return t0Var;
            }
        } while (f2Var.L(objY) < 0);
        f2Var.invokeOnCompletion(false, true, new a1(f2Var, fVar));
        fVar.c();
        return t0Var;
    }
}

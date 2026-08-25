package ka;

import java.util.concurrent.CancellationException;

/* JADX INFO: loaded from: classes5.dex */
public abstract class l0 {
    public static final pa.e a(d7.j jVar) {
        if (jVar.get(u1.f19642i) == null) {
            jVar = jVar.plus(m0.c());
        }
        return new pa.e(jVar);
    }

    public static final void b(k0 k0Var, CancellationException cancellationException) {
        v1 v1Var = (v1) k0Var.getCoroutineContext().get(u1.f19642i);
        if (v1Var != null) {
            v1Var.cancel(cancellationException);
        } else {
            throw new IllegalStateException(("Scope cannot be cancelled because it does not have a job: " + k0Var).toString());
        }
    }

    public static final Object c(r7.p pVar, d7.d dVar) {
        pa.v vVar = new pa.v(dVar.getContext(), dVar);
        return kotlin.reflect.b0.F(vVar, vVar, pVar);
    }

    public static final boolean d(k0 k0Var) {
        v1 v1Var = (v1) k0Var.getCoroutineContext().get(u1.f19642i);
        if (v1Var != null) {
            return v1Var.isActive();
        }
        return true;
    }
}

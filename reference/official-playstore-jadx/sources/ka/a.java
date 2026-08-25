package ka;

import kotlin.NoWhenBranchMatchedException;
import kotlinx.coroutines.CompletionHandlerException;

/* JADX INFO: loaded from: classes5.dex */
public abstract class a extends f2 implements d7.d, k0 {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final d7.j f19541m;

    public a(d7.j jVar, boolean z) {
        super(z);
        B((v1) jVar.get(u1.f19642i));
        this.f19541m = jVar.plus(this);
    }

    @Override // ka.f2
    public final void A(CompletionHandlerException completionHandlerException) {
        h0.a(this.f19541m, completionHandlerException);
    }

    @Override // ka.f2
    public final void J(Object obj) {
        if (!(obj instanceof v)) {
            Q(obj);
        } else {
            v vVar = (v) obj;
            P(vVar.f19647a, v.f19646b.get(vVar) != 0);
        }
    }

    public final void S(int i10, a aVar, r7.p pVar) {
        int iC = h.f0.c(i10);
        if (iC == 0) {
            k2.c.F(pVar, aVar, this);
            return;
        }
        if (iC != 1) {
            if (iC == 2) {
                t7.a.A(t7.a.m(aVar, this, pVar)).resumeWith(x6.t0.f22605a);
                return;
            }
            if (iC != 3) {
                throw new NoWhenBranchMatchedException();
            }
            try {
                d7.j jVar = this.f19541m;
                Object objB = pa.c0.b(jVar, null);
                try {
                    kotlin.jvm.internal.q0.e(2, pVar);
                    Object objInvoke = pVar.invoke(aVar, this);
                    if (objInvoke != e7.a.f15033i) {
                        resumeWith(objInvoke);
                    }
                } finally {
                    pa.c0.a(jVar, objB);
                }
            } catch (Throwable th) {
                resumeWith(new x6.c0(th));
            }
        }
    }

    @Override // d7.d
    public final d7.j getContext() {
        return this.f19541m;
    }

    @Override // ka.k0
    public final d7.j getCoroutineContext() {
        return this.f19541m;
    }

    @Override // ka.f2
    public final String p() {
        return getClass().getSimpleName().concat(" was cancelled");
    }

    @Override // d7.d
    public final void resumeWith(Object obj) {
        Throwable thA = x6.d0.a(obj);
        if (thA != null) {
            obj = new v(thA, false);
        }
        Object objE = E(obj);
        if (objE == m0.f19615e) {
            return;
        }
        h(objE);
    }

    public void Q(Object obj) {
    }

    public void P(Throwable th, boolean z) {
    }
}

package androidx.tv.foundation.lazy.list;

import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.ui.layout.Remeasurement;
import java.util.LinkedHashSet;
import x6.t0;

/* JADX INFO: loaded from: classes3.dex */
public final class k0 extends f7.j implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f5416i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f5417l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f5418m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f5419n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k0(m0 m0Var, int i10, int i11, d7.d dVar) {
        super(2, dVar);
        this.f5419n = m0Var;
        this.f5417l = i10;
        this.f5418m = i11;
    }

    @Override // f7.a
    public final d7.d create(Object obj, d7.d dVar) {
        switch (this.f5416i) {
            case 0:
                return new k0((m0) this.f5419n, this.f5417l, this.f5418m, dVar);
            default:
                return new k0((i2.m) this.f5419n, this.f5418m, dVar);
        }
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f5416i) {
            case 0:
                k0 k0Var = (k0) create((ScrollScope) obj, (d7.d) obj2);
                t0 t0Var = t0.f22605a;
                k0Var.invokeSuspend(t0Var);
                return t0Var;
            default:
                return ((k0) create((ka.k0) obj, (d7.d) obj2)).invokeSuspend(t0.f22605a);
        }
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        switch (this.f5416i) {
            case 0:
                k2.c.G(obj);
                m0 m0Var = (m0) this.f5419n;
                int i10 = this.f5417l;
                androidx.tv.foundation.lazy.grid.f0 f0Var = m0Var.f5429c;
                f0Var.b(i10, this.f5418m);
                f0Var.f5235e = null;
                k kVar = m0Var.f5442q;
                ((LinkedHashSet) kVar.f5411e).clear();
                kVar.f5412f = i2.j.f16053a;
                kVar.f5408b = -1;
                Remeasurement remeasurement = m0Var.f5439n;
                if (remeasurement != null) {
                    remeasurement.forceRemeasure();
                }
                return t0.f22605a;
            default:
                int i11 = this.f5417l;
                if (i11 == 0) {
                    k2.c.G(obj);
                    i2.m mVar = (i2.m) this.f5419n;
                    this.f5417l = 1;
                    Object objA = mVar.a(this.f5418m, this);
                    e7.a aVar = e7.a.f15033i;
                    if (objA == aVar) {
                        return aVar;
                    }
                } else {
                    if (i11 != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    k2.c.G(obj);
                }
                return t0.f22605a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k0(i2.m mVar, int i10, d7.d dVar) {
        super(2, dVar);
        this.f5419n = mVar;
        this.f5418m = i10;
    }
}

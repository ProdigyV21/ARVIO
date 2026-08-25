package na;

import kotlinx.coroutines.flow.internal.AbortFlowException;

/* JADX INFO: loaded from: classes5.dex */
public final class d0 implements k {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f20543i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f20544l;

    public /* synthetic */ d0(Object obj, int i10) {
        this.f20543i = i10;
        this.f20544l = obj;
    }

    @Override // na.k
    public final Object emit(Object obj, d7.d dVar) {
        switch (this.f20543i) {
            case 0:
                ((kotlin.jvm.internal.k0) this.f20544l).f19746i = obj;
                throw new AbortFlowException(this);
            default:
                ((w5.x) this.f20544l).f22496c.set((w5.n) obj);
                return x6.t0.f22605a;
        }
    }
}

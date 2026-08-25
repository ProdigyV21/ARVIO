package t8;

import kotlin.jvm.internal.k0;

/* JADX INFO: loaded from: classes5.dex */
public final class y extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f21984i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ z f21985l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ w8.n f21986m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ k0 f21987n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ y(z zVar, w8.n nVar, k0 k0Var, int i10) {
        super(0);
        this.f21984i = i10;
        this.f21985l = zVar;
        this.f21986m = nVar;
        this.f21987n = k0Var;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f21984i) {
            case 0:
                q8.i iVar = ((s8.a) this.f21985l.f21989b.f2140l).f21652h;
                iVar.getClass();
                return null;
            default:
                z zVar = this.f21985l;
                return ((s8.a) zVar.f21989b.f2140l).f21645a.c(new y(zVar, this.f21986m, this.f21987n, 0));
        }
    }
}

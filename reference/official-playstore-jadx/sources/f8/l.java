package f8;

import kotlin.jvm.internal.r;

/* JADX INFO: loaded from: classes5.dex */
public final class l extends r implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f15331i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ f9.f f15332l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ l(f9.f fVar, int i10) {
        super(1);
        this.f15331i = i10;
        this.f15332l = fVar;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f15331i) {
            case 0:
                return ((p9.n) obj).c(this.f15332l, o8.e.f20793i);
            default:
                return ((p9.n) obj).g(this.f15332l, o8.e.f20797o);
        }
    }
}

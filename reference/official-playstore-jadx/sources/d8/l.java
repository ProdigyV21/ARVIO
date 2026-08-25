package d8;

/* JADX INFO: loaded from: classes5.dex */
public final class l extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f14710i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ m f14711l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ l(m mVar, int i10) {
        super(0);
        this.f14710i = i10;
        this.f14711l = mVar;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f14710i) {
            case 0:
                return q.f14766l.c(this.f14711l.f14722l);
            default:
                return q.f14766l.c(this.f14711l.f14721i);
        }
    }
}

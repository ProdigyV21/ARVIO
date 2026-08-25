package a8;

/* JADX INFO: loaded from: classes5.dex */
public final class t extends kotlin.jvm.internal.r implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ g8.c f266i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f267l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(g8.c cVar, int i10) {
        super(0);
        this.f266i = cVar;
        this.f267l = i10;
    }

    @Override // r7.a
    public final Object invoke() {
        return (g8.n0) this.f266i.e().get(this.f267l);
    }
}

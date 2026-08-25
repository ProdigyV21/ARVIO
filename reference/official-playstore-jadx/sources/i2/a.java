package i2;

/* JADX INFO: loaded from: classes3.dex */
public final class a extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public d7.m f16018i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public /* synthetic */ Object f16019l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ b f16020m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f16021n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public a(b bVar, f7.c cVar) {
        super(cVar);
        this.f16020m = bVar;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f16019l = obj;
        this.f16021n |= Integer.MIN_VALUE;
        return this.f16020m.a(this);
    }
}

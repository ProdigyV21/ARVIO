package oa;

/* JADX INFO: loaded from: classes5.dex */
public final class j extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public /* synthetic */ Object f20829i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ k f20830l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f20831m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(k kVar, d7.d dVar) {
        super(dVar);
        this.f20830l = kVar;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f20829i = obj;
        this.f20831m |= Integer.MIN_VALUE;
        return this.f20830l.emit(null, this);
    }
}

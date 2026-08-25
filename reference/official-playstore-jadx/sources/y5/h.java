package y5;

/* JADX INFO: loaded from: classes4.dex */
public final class h extends f7.c {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public /* synthetic */ Object f22899i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ i f22900l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f22901m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(i iVar, f7.c cVar) {
        super(cVar);
        this.f22900l = iVar;
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        this.f22899i = obj;
        this.f22901m |= Integer.MIN_VALUE;
        return this.f22900l.c(null, null, this);
    }
}

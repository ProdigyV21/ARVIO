package ob;

/* JADX INFO: loaded from: classes5.dex */
public final class l extends kb.a {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ o f20914e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f20915f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public l(String str, o oVar, int i10, int i11) {
        super(str, true);
        this.f20914e = oVar;
        this.f20915f = i10;
    }

    @Override // kb.a
    public final long a() {
        this.f20914e.f20932u.getClass();
        synchronized (this.f20914e) {
            this.f20914e.J.remove(Integer.valueOf(this.f20915f));
        }
        return -1L;
    }
}

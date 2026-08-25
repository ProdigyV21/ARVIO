package retrofit2;

/* JADX INFO: loaded from: classes5.dex */
public final class g implements f {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f21501i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final i f21502l;

    public /* synthetic */ g(i iVar, int i10) {
        this.f21501i = i10;
        this.f21502l = iVar;
    }

    @Override // retrofit2.f
    public final void C(c cVar, r0 r0Var) {
        switch (this.f21501i) {
            case 0:
                boolean zK = r0Var.f21578a.k();
                i iVar = this.f21502l;
                if (!zK) {
                    iVar.completeExceptionally(new HttpException(r0Var));
                } else {
                    iVar.complete(r0Var.f21579b);
                }
                break;
            default:
                this.f21502l.complete(r0Var);
                break;
        }
    }

    @Override // retrofit2.f
    public final void onFailure(Throwable th) {
        switch (this.f21501i) {
            case 0:
                this.f21502l.completeExceptionally(th);
                break;
            default:
                this.f21502l.completeExceptionally(th);
                break;
        }
    }
}

package v9;

/* JADX INFO: loaded from: classes5.dex */
public final class y extends o {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f22261m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ y(b0 b0Var, int i10) {
        super(b0Var);
        this.f22261m = i10;
    }

    @Override // v9.n, v9.w
    public final boolean J0() {
        switch (this.f22261m) {
            case 0:
                return false;
            default:
                return true;
        }
    }

    @Override // v9.n
    public final n T0(b0 b0Var) {
        switch (this.f22261m) {
            case 0:
                return new y(b0Var, 0);
            default:
                return new y(b0Var, 1);
        }
    }
}

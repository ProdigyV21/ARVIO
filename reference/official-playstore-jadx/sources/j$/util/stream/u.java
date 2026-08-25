package j$.util.stream;

/* JADX INFO: loaded from: classes2.dex */
public final class u extends i1 {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ int f18900s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ u(a aVar, int i10, int i11) {
        super(aVar, i10);
        this.f18900s = i11;
    }

    @Override // j$.util.stream.a
    public final l5 P0(int i10, l5 l5Var) {
        switch (this.f18900s) {
            case 0:
                return new q(this, l5Var, 3);
            case 1:
                return new v0(0, l5Var);
            case 2:
                return new t0(this, l5Var, 3);
            case 3:
                return new c1(this, l5Var, 1);
            case 4:
                return l5Var;
            default:
                return new c1(this, l5Var, 4);
        }
    }
}

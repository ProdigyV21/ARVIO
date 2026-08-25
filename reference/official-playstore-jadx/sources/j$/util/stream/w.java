package j$.util.stream;

/* JADX INFO: loaded from: classes2.dex */
public final class w extends z {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ int f18935s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ w(a aVar, int i10, int i11) {
        super(aVar, i10);
        this.f18935s = i11;
    }

    @Override // j$.util.stream.a
    public final l5 P0(int i10, l5 l5Var) {
        switch (this.f18935s) {
            case 0:
                return l5Var;
            case 1:
                return new q(this, l5Var, 4);
            case 2:
                return new v0(1, l5Var);
            case 3:
                return new t0(this, l5Var, 4);
            case 4:
                return new d1(l5Var);
            default:
                return new c1(this, l5Var, 3);
        }
    }
}

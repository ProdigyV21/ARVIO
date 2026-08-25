package j$.util.stream;

/* JADX INFO: loaded from: classes2.dex */
public abstract class i2 implements g2 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final g2 f18722a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g2 f18723b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final long f18724c;

    @Override // j$.util.stream.g2
    public final int o() {
        return 2;
    }

    public i2(g2 g2Var, g2 g2Var2) {
        this.f18722a = g2Var;
        this.f18723b = g2Var2;
        this.f18724c = g2Var2.count() + g2Var.count();
    }

    @Override // j$.util.stream.g2
    public final g2 a(int i10) {
        if (i10 == 0) {
            return this.f18722a;
        }
        if (i10 == 1) {
            return this.f18723b;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // j$.util.stream.g2
    public final long count() {
        return this.f18724c;
    }

    @Override // j$.util.stream.g2
    public /* bridge */ /* synthetic */ f2 a(int i10) {
        return (f2) a(i10);
    }
}

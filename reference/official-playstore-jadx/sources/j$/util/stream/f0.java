package j$.util.stream;

import java.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class f0 extends j0 implements i5 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final e0 f18684c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final e0 f18685d;

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return j$.com.android.tools.r8.a.c(this, doubleConsumer);
    }

    @Override // j$.util.stream.j0, j$.util.stream.l5
    public final void accept(double d4) {
        n(Double.valueOf(d4));
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        if (this.f18737a) {
            return new j$.util.l(((Double) this.f18738b).doubleValue());
        }
        return null;
    }

    static {
        z6 z6Var = z6.DOUBLE_VALUE;
        o oVar = new o(10);
        o oVar2 = new o(11);
        j$.util.l lVar = j$.util.l.f18568c;
        f18684c = new e0(true, z6Var, lVar, oVar, oVar2);
        f18685d = new e0(false, z6Var, lVar, new o(10), new o(11));
    }
}

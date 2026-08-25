package j$.util.stream;

import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class h0 extends j0 implements k5 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final e0 f18705c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final e0 f18706d;

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return j$.com.android.tools.r8.a.d(this, longConsumer);
    }

    @Override // j$.util.stream.j0, j$.util.stream.l5
    public final void accept(long j10) {
        n(Long.valueOf(j10));
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        if (this.f18737a) {
            return new j$.util.n(((Long) this.f18738b).longValue());
        }
        return null;
    }

    static {
        z6 z6Var = z6.LONG_VALUE;
        o oVar = new o(14);
        o oVar2 = new o(15);
        j$.util.n nVar = j$.util.n.f18576c;
        f18705c = new e0(true, z6Var, nVar, oVar, oVar2);
        f18706d = new e0(false, z6Var, nVar, new o(14), new o(15));
    }
}

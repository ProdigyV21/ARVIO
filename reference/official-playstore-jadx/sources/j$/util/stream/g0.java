package j$.util.stream;

import j$.util.function.IntConsumer$CC;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class g0 extends j0 implements j5 {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final e0 f18694c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final e0 f18695d;

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return IntConsumer$CC.$default$andThen(this, intConsumer);
    }

    @Override // j$.util.stream.j0, j$.util.stream.l5
    public final void accept(int i10) {
        n(Integer.valueOf(i10));
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        if (this.f18737a) {
            return new j$.util.m(((Integer) this.f18738b).intValue());
        }
        return null;
    }

    static {
        z6 z6Var = z6.INT_VALUE;
        o oVar = new o(12);
        o oVar2 = new o(13);
        j$.util.m mVar = j$.util.m.f18572c;
        f18694c = new e0(true, z6Var, mVar, oVar, oVar2);
        f18695d = new e0(false, z6Var, mVar, new o(12), new o(13));
    }
}

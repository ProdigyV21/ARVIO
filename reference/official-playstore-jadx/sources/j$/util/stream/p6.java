package j$.util.stream;

import j$.util.Objects;
import j$.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class p6 extends t6 implements j$.util.h0 {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ q6 f18831g;

    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.com.android.tools.r8.a.j(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return j$.com.android.tools.r8.a.A(this, consumer);
    }

    @Override // j$.util.stream.t6
    public final void a(int i10, Object obj, Object obj2) {
        ((IntConsumer) obj2).accept(((int[]) obj)[i10]);
    }

    @Override // j$.util.stream.t6
    public final j$.util.n0 b(Object obj, int i10, int i11) {
        int[] iArr = (int[]) obj;
        int i12 = i11 + i10;
        Spliterators.a(((int[]) Objects.requireNonNull(iArr)).length, i10, i12);
        return new j$.util.z0(iArr, i10, i12, 1040);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p6(q6 q6Var, int i10, int i11, int i12, int i13) {
        super(q6Var, i10, i11, i12, i13);
        this.f18831g = q6Var;
    }

    @Override // j$.util.stream.t6
    public final j$.util.n0 c(int i10, int i11, int i12, int i13) {
        return new p6(this.f18831g, i10, i11, i12, i13);
    }
}

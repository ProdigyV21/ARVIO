package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class o0 extends q0 implements k5 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final LongConsumer f18811b;

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final /* bridge */ /* synthetic */ void n(Object obj) {
        l((Long) obj);
    }

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return j$.com.android.tools.r8.a.d(this, longConsumer);
    }

    @Override // java.util.function.Supplier
    public final /* bridge */ /* synthetic */ Object get() {
        return null;
    }

    @Override // j$.util.stream.k5
    public final /* synthetic */ void l(Long l10) {
        v3.H(this, l10);
    }

    @Override // j$.util.stream.e8
    public final Object e(a aVar, Spliterator spliterator) {
        aVar.E0(spliterator, this);
        return null;
    }

    @Override // j$.util.stream.e8
    public final /* bridge */ /* synthetic */ Object h(v3 v3Var, Spliterator spliterator) {
        a(v3Var, spliterator);
        return null;
    }

    public o0(LongConsumer longConsumer, boolean z) {
        super(z);
        this.f18811b = longConsumer;
    }

    @Override // j$.util.stream.q0, j$.util.stream.l5
    public final void accept(long j10) {
        this.f18811b.accept(j10);
    }
}

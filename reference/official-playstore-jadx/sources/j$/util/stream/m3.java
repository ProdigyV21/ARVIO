package j$.util.stream;

import j$.util.Spliterator;
import j$.util.function.Consumer$CC;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.LongConsumer;

/* JADX INFO: loaded from: classes2.dex */
public final class m3 extends s6 implements e2, x1 {
    @Override // j$.util.stream.l5
    public final /* synthetic */ void accept(double d4) {
        v3.C();
        throw null;
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ void accept(int i10) {
        v3.J();
        throw null;
    }

    @Override // java.util.function.Consumer
    /* JADX INFO: renamed from: accept */
    public final /* bridge */ /* synthetic */ void n(Object obj) {
        l((Long) obj);
    }

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    @Override // j$.util.stream.x1, j$.util.stream.y1
    public final e2 build() {
        return this;
    }

    @Override // j$.util.stream.y1
    public final g2 build() {
        return this;
    }

    @Override // j$.util.stream.l5
    public final void end() {
    }

    @Override // j$.util.stream.l5
    public final /* synthetic */ boolean f() {
        return false;
    }

    @Override // j$.util.stream.g2
    public final /* synthetic */ g2 h(long j10, long j11, IntFunction intFunction) {
        return v3.U(this, j10, j11);
    }

    @Override // j$.util.stream.k5
    public final /* synthetic */ void l(Long l10) {
        v3.H(this, l10);
    }

    @Override // j$.util.stream.g2
    public final /* synthetic */ Object[] m(IntFunction intFunction) {
        return v3.L(this, intFunction);
    }

    @Override // j$.util.stream.g2
    public final /* synthetic */ int o() {
        return 0;
    }

    @Override // j$.util.stream.g2
    public final /* bridge */ /* synthetic */ g2 a(int i10) {
        a(i10);
        throw null;
    }

    @Override // j$.util.stream.f2, j$.util.stream.g2
    public final f2 a(int i10) {
        throw new IndexOutOfBoundsException();
    }

    @Override // j$.util.stream.g2
    public final /* synthetic */ void k(Object[] objArr, int i10) {
        v3.O(this, (Long[]) objArr, i10);
    }

    @Override // j$.util.stream.u6, j$.util.stream.f2
    public final void e(Object obj, int i10) {
        super.e((long[]) obj, i10);
    }

    @Override // j$.util.stream.u6, j$.util.stream.f2
    public final void g(Object obj) {
        super.g((LongConsumer) obj);
    }

    @Override // j$.util.stream.s6, j$.util.stream.u6, java.lang.Iterable, j$.util.stream.g2
    public final Spliterator spliterator() {
        return super.spliterator();
    }

    @Override // j$.util.stream.s6, j$.util.stream.u6, java.lang.Iterable, j$.util.stream.g2
    public final j$.util.n0 spliterator() {
        return super.spliterator();
    }

    @Override // j$.util.stream.l5
    public final void c(long j10) {
        clear();
        s(j10);
    }

    @Override // j$.util.stream.u6, j$.util.stream.f2
    public final Object b() {
        return (long[]) super.b();
    }
}

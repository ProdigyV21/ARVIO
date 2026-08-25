package j$.util.stream;

import j$.util.Spliterator;
import j$.util.function.IntConsumer$CC;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

/* JADX INFO: loaded from: classes2.dex */
public final class v8 extends y8 implements IntConsumer, j$.util.h0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f18933e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f18934f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ v8(Spliterator spliterator, int i10) {
        super(spliterator);
        this.f18934f = i10;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ v8(Spliterator spliterator, y8 y8Var, int i10) {
        super(spliterator, y8Var);
        this.f18934f = i10;
    }

    public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer) {
        return IntConsumer$CC.$default$andThen(this, intConsumer);
    }

    @Override // j$.util.stream.y8, j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.com.android.tools.r8.a.j(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return j$.com.android.tools.r8.a.A(this, consumer);
    }

    @Override // j$.util.n0
    public final void forEachRemaining(IntConsumer intConsumer) {
        while (tryAdvance(intConsumer)) {
        }
    }

    @Override // java.util.function.IntConsumer
    public final void accept(int i10) {
        this.f18988d = (this.f18988d + 1) & 63;
        this.f18933e = i10;
    }

    @Override // j$.util.stream.y8
    public final Spliterator b(Spliterator spliterator) {
        switch (this.f18934f) {
            case 0:
                return new v8((j$.util.h0) spliterator, this, 0);
            default:
                return new v8((j$.util.h0) spliterator, this, 1);
        }
    }

    @Override // j$.util.stream.y8, j$.util.Spliterator
    public /* bridge */ /* synthetic */ Spliterator trySplit() {
        switch (this.f18934f) {
            case 1:
                return trySplit();
            default:
                return super.trySplit();
        }
    }

    @Override // j$.util.stream.y8, j$.util.Spliterator
    public /* bridge */ /* synthetic */ j$.util.n0 trySplit() {
        switch (this.f18934f) {
            case 1:
                return trySplit();
            default:
                return super.trySplit();
        }
    }

    @Override // j$.util.h0
    public final boolean tryAdvance(IntConsumer intConsumer) {
        switch (this.f18934f) {
            case 0:
                boolean z = this.f18987c;
                Spliterator spliterator = this.f18985a;
                if (z) {
                    this.f18987c = false;
                    boolean zTryAdvance = ((j$.util.h0) spliterator).tryAdvance((IntConsumer) this);
                    if (zTryAdvance && a()) {
                        IntPredicate intPredicate = null;
                        intPredicate.test(this.f18933e);
                        throw null;
                    }
                    if (!zTryAdvance) {
                        return zTryAdvance;
                    }
                    intConsumer.accept(this.f18933e);
                    return zTryAdvance;
                }
                return ((j$.util.h0) spliterator).tryAdvance(intConsumer);
            default:
                if (this.f18987c && a() && ((j$.util.h0) this.f18985a).tryAdvance((IntConsumer) this)) {
                    IntPredicate intPredicate2 = null;
                    intPredicate2.test(this.f18933e);
                    throw null;
                }
                this.f18987c = false;
                return false;
        }
    }

    @Override // j$.util.stream.y8, j$.util.Spliterator
    public j$.util.h0 trySplit() {
        switch (this.f18934f) {
            case 1:
                if (this.f18986b.get()) {
                    return null;
                }
                return (j$.util.h0) super.trySplit();
            default:
                return super.trySplit();
        }
    }

    @Override // j$.util.n0
    public /* bridge */ /* synthetic */ boolean tryAdvance(Object obj) {
        switch (this.f18934f) {
            case 1:
                tryAdvance((IntConsumer) obj);
                return false;
            default:
                return tryAdvance((IntConsumer) obj);
        }
    }
}

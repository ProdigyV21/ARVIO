package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.DoublePredicate;

/* JADX INFO: loaded from: classes2.dex */
public final class u8 extends y8 implements DoubleConsumer, j$.util.e0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public double f18912e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f18913f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ u8(Spliterator spliterator, int i10) {
        super(spliterator);
        this.f18913f = i10;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ u8(Spliterator spliterator, y8 y8Var, int i10) {
        super(spliterator, y8Var);
        this.f18913f = i10;
    }

    public final /* synthetic */ DoubleConsumer andThen(DoubleConsumer doubleConsumer) {
        return j$.com.android.tools.r8.a.c(this, doubleConsumer);
    }

    @Override // j$.util.stream.y8, j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.com.android.tools.r8.a.i(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return j$.com.android.tools.r8.a.z(this, consumer);
    }

    @Override // j$.util.n0
    public final void forEachRemaining(DoubleConsumer doubleConsumer) {
        while (tryAdvance(doubleConsumer)) {
        }
    }

    @Override // java.util.function.DoubleConsumer
    public final void accept(double d4) {
        this.f18988d = (this.f18988d + 1) & 63;
        this.f18912e = d4;
    }

    @Override // j$.util.stream.y8
    public final Spliterator b(Spliterator spliterator) {
        switch (this.f18913f) {
            case 0:
                return new u8((j$.util.e0) spliterator, this, 0);
            default:
                return new u8((j$.util.e0) spliterator, this, 1);
        }
    }

    @Override // j$.util.stream.y8, j$.util.Spliterator
    public /* bridge */ /* synthetic */ Spliterator trySplit() {
        switch (this.f18913f) {
            case 1:
                return trySplit();
            default:
                return super.trySplit();
        }
    }

    @Override // j$.util.stream.y8, j$.util.Spliterator
    public /* bridge */ /* synthetic */ j$.util.n0 trySplit() {
        switch (this.f18913f) {
            case 1:
                return trySplit();
            default:
                return super.trySplit();
        }
    }

    @Override // j$.util.e0
    public final boolean tryAdvance(DoubleConsumer doubleConsumer) {
        switch (this.f18913f) {
            case 0:
                boolean z = this.f18987c;
                Spliterator spliterator = this.f18985a;
                if (z) {
                    this.f18987c = false;
                    boolean zTryAdvance = ((j$.util.e0) spliterator).tryAdvance((DoubleConsumer) this);
                    if (zTryAdvance && a()) {
                        DoublePredicate doublePredicate = null;
                        doublePredicate.test(this.f18912e);
                        throw null;
                    }
                    if (!zTryAdvance) {
                        return zTryAdvance;
                    }
                    doubleConsumer.accept(this.f18912e);
                    return zTryAdvance;
                }
                return ((j$.util.e0) spliterator).tryAdvance(doubleConsumer);
            default:
                if (this.f18987c && a() && ((j$.util.e0) this.f18985a).tryAdvance((DoubleConsumer) this)) {
                    DoublePredicate doublePredicate2 = null;
                    doublePredicate2.test(this.f18912e);
                    throw null;
                }
                this.f18987c = false;
                return false;
        }
    }

    @Override // j$.util.stream.y8, j$.util.Spliterator
    public j$.util.e0 trySplit() {
        switch (this.f18913f) {
            case 1:
                if (this.f18986b.get()) {
                    return null;
                }
                return (j$.util.e0) super.trySplit();
            default:
                return super.trySplit();
        }
    }

    @Override // j$.util.n0
    public /* bridge */ /* synthetic */ boolean tryAdvance(Object obj) {
        switch (this.f18913f) {
            case 1:
                tryAdvance((DoubleConsumer) obj);
                return false;
            default:
                return tryAdvance((DoubleConsumer) obj);
        }
    }
}

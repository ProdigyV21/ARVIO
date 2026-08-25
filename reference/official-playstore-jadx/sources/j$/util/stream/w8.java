package j$.util.stream;

import j$.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.LongConsumer;
import java.util.function.LongPredicate;

/* JADX INFO: loaded from: classes2.dex */
public final class w8 extends y8 implements LongConsumer, j$.util.k0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f18945e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f18946f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ w8(Spliterator spliterator, int i10) {
        super(spliterator);
        this.f18946f = i10;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ w8(Spliterator spliterator, y8 y8Var, int i10) {
        super(spliterator, y8Var);
        this.f18946f = i10;
    }

    public final /* synthetic */ LongConsumer andThen(LongConsumer longConsumer) {
        return j$.com.android.tools.r8.a.d(this, longConsumer);
    }

    @Override // j$.util.stream.y8, j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.com.android.tools.r8.a.k(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return j$.com.android.tools.r8.a.B(this, consumer);
    }

    @Override // j$.util.n0
    public final void forEachRemaining(LongConsumer longConsumer) {
        while (tryAdvance(longConsumer)) {
        }
    }

    @Override // java.util.function.LongConsumer
    public final void accept(long j10) {
        this.f18988d = (this.f18988d + 1) & 63;
        this.f18945e = j10;
    }

    @Override // j$.util.stream.y8
    public final Spliterator b(Spliterator spliterator) {
        switch (this.f18946f) {
            case 0:
                return new w8((j$.util.k0) spliterator, this, 0);
            default:
                return new w8((j$.util.k0) spliterator, this, 1);
        }
    }

    @Override // j$.util.stream.y8, j$.util.Spliterator
    public /* bridge */ /* synthetic */ Spliterator trySplit() {
        switch (this.f18946f) {
            case 1:
                return trySplit();
            default:
                return super.trySplit();
        }
    }

    @Override // j$.util.stream.y8, j$.util.Spliterator
    public /* bridge */ /* synthetic */ j$.util.n0 trySplit() {
        switch (this.f18946f) {
            case 1:
                return trySplit();
            default:
                return super.trySplit();
        }
    }

    @Override // j$.util.k0
    public final boolean tryAdvance(LongConsumer longConsumer) {
        switch (this.f18946f) {
            case 0:
                boolean z = this.f18987c;
                Spliterator spliterator = this.f18985a;
                if (z) {
                    this.f18987c = false;
                    boolean zTryAdvance = ((j$.util.k0) spliterator).tryAdvance((LongConsumer) this);
                    if (zTryAdvance && a()) {
                        LongPredicate longPredicate = null;
                        longPredicate.test(this.f18945e);
                        throw null;
                    }
                    if (!zTryAdvance) {
                        return zTryAdvance;
                    }
                    longConsumer.accept(this.f18945e);
                    return zTryAdvance;
                }
                return ((j$.util.k0) spliterator).tryAdvance(longConsumer);
            default:
                if (this.f18987c && a() && ((j$.util.k0) this.f18985a).tryAdvance((LongConsumer) this)) {
                    LongPredicate longPredicate2 = null;
                    longPredicate2.test(this.f18945e);
                    throw null;
                }
                this.f18987c = false;
                return false;
        }
    }

    @Override // j$.util.stream.y8, j$.util.Spliterator
    public j$.util.k0 trySplit() {
        switch (this.f18946f) {
            case 1:
                if (this.f18986b.get()) {
                    return null;
                }
                return (j$.util.k0) super.trySplit();
            default:
                return super.trySplit();
        }
    }

    @Override // j$.util.n0
    public /* bridge */ /* synthetic */ boolean tryAdvance(Object obj) {
        switch (this.f18946f) {
            case 1:
                tryAdvance((LongConsumer) obj);
                return false;
            default:
                return tryAdvance((LongConsumer) obj);
        }
    }
}

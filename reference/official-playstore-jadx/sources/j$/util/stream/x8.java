package j$.util.stream;

import j$.util.Spliterator;
import j$.util.function.Consumer$CC;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: classes2.dex */
public final class x8 extends y8 implements Consumer {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Predicate f18953e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public Object f18954f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ int f18955g;

    public final /* synthetic */ Consumer andThen(Consumer consumer) {
        return Consumer$CC.$default$andThen(this, consumer);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x8(Spliterator spliterator, Predicate predicate, int i10) {
        super(spliterator);
        this.f18955g = i10;
        this.f18953e = predicate;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public x8(Spliterator spliterator, x8 x8Var, int i10) {
        super(spliterator, x8Var);
        this.f18955g = i10;
        this.f18953e = x8Var.f18953e;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        this.f18988d = (this.f18988d + 1) & 63;
        this.f18954f = obj;
    }

    @Override // j$.util.Spliterator
    public final boolean tryAdvance(Consumer consumer) {
        boolean zTryAdvance;
        boolean zTest;
        switch (this.f18955g) {
            case 0:
                boolean z = this.f18987c;
                Spliterator spliterator = this.f18985a;
                if (z) {
                    boolean z5 = false;
                    this.f18987c = false;
                    while (true) {
                        zTryAdvance = spliterator.tryAdvance(this);
                        if (zTryAdvance && a() && this.f18953e.test(this.f18954f)) {
                            z5 = true;
                        }
                    }
                    if (!zTryAdvance) {
                        return zTryAdvance;
                    }
                    if (z5) {
                        this.f18986b.set(true);
                    }
                    consumer.accept(this.f18954f);
                    return zTryAdvance;
                }
                return spliterator.tryAdvance(consumer);
            default:
                if (this.f18987c && a() && this.f18985a.tryAdvance(this)) {
                    zTest = this.f18953e.test(this.f18954f);
                    if (zTest) {
                        consumer.accept(this.f18954f);
                        return true;
                    }
                } else {
                    zTest = true;
                }
                this.f18987c = false;
                if (!zTest) {
                    this.f18986b.set(true);
                }
                return false;
        }
    }

    @Override // j$.util.stream.y8, j$.util.Spliterator
    public Spliterator trySplit() {
        switch (this.f18955g) {
            case 1:
                if (!this.f18986b.get()) {
                    break;
                }
                break;
        }
        return super.trySplit();
    }

    @Override // j$.util.stream.y8
    public final Spliterator b(Spliterator spliterator) {
        switch (this.f18955g) {
            case 0:
                return new x8(spliterator, this, 0);
            default:
                return new x8(spliterator, this, 1);
        }
    }
}

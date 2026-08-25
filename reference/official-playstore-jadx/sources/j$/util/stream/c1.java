package j$.util.stream;

import java.util.function.LongConsumer;
import java.util.function.LongFunction;
import java.util.function.LongPredicate;
import java.util.function.LongToDoubleFunction;
import java.util.function.LongToIntFunction;
import java.util.function.LongUnaryOperator;

/* JADX INFO: loaded from: classes2.dex */
public final class c1 extends g5 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f18650b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ a f18651c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ c1(a aVar, l5 l5Var, int i10) {
        super(l5Var);
        this.f18650b = i10;
        this.f18651c = aVar;
    }

    @Override // j$.util.stream.g5, j$.util.stream.l5
    public void c(long j10) {
        switch (this.f18650b) {
            case 4:
                this.f18699a.c(-1L);
                break;
            default:
                super.c(j10);
                break;
        }
    }

    @Override // j$.util.stream.k5, j$.util.stream.l5
    public final void accept(long j10) {
        switch (this.f18650b) {
            case 0:
                this.f18699a.accept(((LongFunction) ((r) this.f18651c).f18840t).apply(j10));
                return;
            case 1:
                ((u) this.f18651c).getClass();
                LongUnaryOperator longUnaryOperator = null;
                longUnaryOperator.applyAsLong(j10);
                throw null;
            case 2:
                ((t) this.f18651c).getClass();
                LongToIntFunction longToIntFunction = null;
                longToIntFunction.applyAsInt(j10);
                throw null;
            case 3:
                ((w) this.f18651c).getClass();
                LongToDoubleFunction longToDoubleFunction = null;
                longToDoubleFunction.applyAsDouble(j10);
                throw null;
            case 4:
                ((u) this.f18651c).getClass();
                LongPredicate longPredicate = null;
                longPredicate.test(j10);
                throw null;
            default:
                ((LongConsumer) ((f1) this.f18651c).f18687t).accept(j10);
                this.f18699a.accept(j10);
                return;
        }
    }
}

package j$.util.stream;

import java.util.function.DoubleConsumer;
import java.util.function.DoubleFunction;
import java.util.function.DoublePredicate;
import java.util.function.DoubleToIntFunction;
import java.util.function.DoubleToLongFunction;
import java.util.function.DoubleUnaryOperator;

/* JADX INFO: loaded from: classes2.dex */
public final class q extends e5 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ int f18832b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ a f18833c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ q(a aVar, l5 l5Var, int i10) {
        super(l5Var);
        this.f18832b = i10;
        this.f18833c = aVar;
    }

    @Override // j$.util.stream.e5, j$.util.stream.l5
    public void c(long j10) {
        switch (this.f18832b) {
            case 4:
                this.f18680a.c(-1L);
                break;
            default:
                super.c(j10);
                break;
        }
    }

    @Override // j$.util.stream.i5, j$.util.stream.l5
    public final void accept(double d4) {
        switch (this.f18832b) {
            case 0:
                this.f18680a.accept(((DoubleFunction) ((r) this.f18833c).f18840t).apply(d4));
                return;
            case 1:
                this.f18680a.accept(((DoubleUnaryOperator) ((s) this.f18833c).f18860t).applyAsDouble(d4));
                return;
            case 2:
                ((t) this.f18833c).getClass();
                DoubleToIntFunction doubleToIntFunction = null;
                doubleToIntFunction.applyAsInt(d4);
                throw null;
            case 3:
                ((u) this.f18833c).getClass();
                DoubleToLongFunction doubleToLongFunction = null;
                doubleToLongFunction.applyAsLong(d4);
                throw null;
            case 4:
                ((w) this.f18833c).getClass();
                DoublePredicate doublePredicate = null;
                doublePredicate.test(d4);
                throw null;
            default:
                ((DoubleConsumer) ((s) this.f18833c).f18860t).accept(d4);
                this.f18680a.accept(d4);
                return;
        }
    }
}

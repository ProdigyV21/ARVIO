package j$.util.function;

import java.util.function.DoubleUnaryOperator;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class c implements DoubleUnaryOperator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f18540a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ DoubleUnaryOperator f18541b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ DoubleUnaryOperator f18542c;

    public /* synthetic */ c(DoubleUnaryOperator doubleUnaryOperator, DoubleUnaryOperator doubleUnaryOperator2, int i10) {
        this.f18540a = i10;
        this.f18541b = doubleUnaryOperator;
        this.f18542c = doubleUnaryOperator2;
    }

    public final /* synthetic */ DoubleUnaryOperator andThen(DoubleUnaryOperator doubleUnaryOperator) {
        switch (this.f18540a) {
        }
        return DoubleUnaryOperator$CC.$default$andThen(this, doubleUnaryOperator);
    }

    public final /* synthetic */ DoubleUnaryOperator compose(DoubleUnaryOperator doubleUnaryOperator) {
        switch (this.f18540a) {
        }
        return DoubleUnaryOperator$CC.$default$compose(this, doubleUnaryOperator);
    }

    @Override // java.util.function.DoubleUnaryOperator
    public final double applyAsDouble(double d4) {
        switch (this.f18540a) {
            case 0:
                return this.f18541b.applyAsDouble(this.f18542c.applyAsDouble(d4));
            default:
                return this.f18542c.applyAsDouble(this.f18541b.applyAsDouble(d4));
        }
    }
}

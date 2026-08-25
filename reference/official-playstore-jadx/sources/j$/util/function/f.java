package j$.util.function;

import java.util.function.IntUnaryOperator;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class f implements IntUnaryOperator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f18548a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ IntUnaryOperator f18549b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ IntUnaryOperator f18550c;

    public /* synthetic */ f(IntUnaryOperator intUnaryOperator, IntUnaryOperator intUnaryOperator2, int i10) {
        this.f18548a = i10;
        this.f18549b = intUnaryOperator;
        this.f18550c = intUnaryOperator2;
    }

    public final /* synthetic */ IntUnaryOperator andThen(IntUnaryOperator intUnaryOperator) {
        switch (this.f18548a) {
        }
        return IntUnaryOperator$CC.$default$andThen(this, intUnaryOperator);
    }

    public final /* synthetic */ IntUnaryOperator compose(IntUnaryOperator intUnaryOperator) {
        switch (this.f18548a) {
        }
        return IntUnaryOperator$CC.$default$compose(this, intUnaryOperator);
    }

    @Override // java.util.function.IntUnaryOperator
    public final int applyAsInt(int i10) {
        switch (this.f18548a) {
            case 0:
                return this.f18550c.applyAsInt(this.f18549b.applyAsInt(i10));
            default:
                return this.f18549b.applyAsInt(this.f18550c.applyAsInt(i10));
        }
    }
}

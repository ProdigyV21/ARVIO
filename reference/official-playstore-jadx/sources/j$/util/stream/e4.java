package j$.util.stream;

import java.util.function.DoubleBinaryOperator;

/* JADX INFO: loaded from: classes2.dex */
public final class e4 extends v3 {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ DoubleBinaryOperator f18678h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ double f18679i;

    @Override // j$.util.stream.v3
    public final q4 D0() {
        return new z3(this.f18679i, this.f18678h);
    }

    public e4(z6 z6Var, DoubleBinaryOperator doubleBinaryOperator, double d4) {
        this.f18678h = doubleBinaryOperator;
        this.f18679i = d4;
    }
}

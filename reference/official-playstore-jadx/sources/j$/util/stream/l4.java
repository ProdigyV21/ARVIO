package j$.util.stream;

import java.util.function.IntBinaryOperator;

/* JADX INFO: loaded from: classes2.dex */
public final class l4 extends v3 {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ IntBinaryOperator f18774h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f18775i;

    @Override // j$.util.stream.v3
    public final q4 D0() {
        return new k4(this.f18775i, this.f18774h);
    }

    public l4(z6 z6Var, IntBinaryOperator intBinaryOperator, int i10) {
        this.f18774h = intBinaryOperator;
        this.f18775i = i10;
    }
}

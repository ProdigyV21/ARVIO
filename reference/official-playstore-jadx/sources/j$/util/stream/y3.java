package j$.util.stream;

import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.LongBinaryOperator;

/* JADX INFO: loaded from: classes2.dex */
public final class y3 extends v3 {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f18956h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Object f18957i;

    public /* synthetic */ y3(z6 z6Var, Object obj, int i10) {
        this.f18956h = i10;
        this.f18957i = obj;
    }

    @Override // j$.util.stream.v3
    public final q4 D0() {
        switch (this.f18956h) {
            case 0:
                return new p4((LongBinaryOperator) this.f18957i);
            case 1:
                return new b4((DoubleBinaryOperator) this.f18957i);
            case 2:
                return new g4((BinaryOperator) this.f18957i);
            default:
                return new m4((IntBinaryOperator) this.f18957i);
        }
    }
}

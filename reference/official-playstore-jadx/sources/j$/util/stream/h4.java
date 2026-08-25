package j$.util.stream;

import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public final class h4 extends v3 {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ BinaryOperator f18708h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ BiConsumer f18709i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ Supplier f18710j;
    public final /* synthetic */ Collector k;

    @Override // j$.util.stream.v3
    public final q4 D0() {
        return new i4(this.f18710j, this.f18709i, this.f18708h);
    }

    @Override // j$.util.stream.v3, j$.util.stream.e8
    public final int t() {
        if (this.k.characteristics().contains(h.UNORDERED)) {
            return y6.f18974r;
        }
        return 0;
    }

    public h4(z6 z6Var, BinaryOperator binaryOperator, BiConsumer biConsumer, Supplier supplier, Collector collector) {
        this.f18708h = binaryOperator;
        this.f18709i = biConsumer;
        this.f18710j = supplier;
        this.k = collector;
    }
}

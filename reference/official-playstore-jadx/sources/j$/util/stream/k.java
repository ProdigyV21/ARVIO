package j$.util.stream;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public final class k implements Collector {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Supplier f18752a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final BiConsumer f18753b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final BinaryOperator f18754c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Function f18755d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Set f18756e;

    public k(Supplier supplier, BiConsumer biConsumer, BinaryOperator binaryOperator, Function function, Set set) {
        this.f18752a = supplier;
        this.f18753b = biConsumer;
        this.f18754c = binaryOperator;
        this.f18755d = function;
        this.f18756e = set;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public k(Supplier supplier, BiConsumer biConsumer, BinaryOperator binaryOperator, Set set) {
        this(supplier, biConsumer, binaryOperator, new j$.time.c(23), set);
        Set set2 = Collectors.f18598a;
    }

    @Override // j$.util.stream.Collector
    public final BiConsumer accumulator() {
        return this.f18753b;
    }

    @Override // j$.util.stream.Collector
    public final Supplier supplier() {
        return this.f18752a;
    }

    @Override // j$.util.stream.Collector
    public final BinaryOperator combiner() {
        return this.f18754c;
    }

    @Override // j$.util.stream.Collector
    public final Function finisher() {
        return this.f18755d;
    }

    @Override // j$.util.stream.Collector
    public final Set characteristics() {
        return this.f18756e;
    }
}

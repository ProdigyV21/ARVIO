package j$.util.stream;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class j implements java.util.stream.Collector {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Collector f18736a;

    public /* synthetic */ j(Collector collector) {
        this.f18736a = collector;
    }

    @Override // java.util.stream.Collector
    public final /* synthetic */ BiConsumer accumulator() {
        return this.f18736a.accumulator();
    }

    @Override // java.util.stream.Collector
    public final /* synthetic */ Set characteristics() {
        return v3.q0(this.f18736a.characteristics());
    }

    @Override // java.util.stream.Collector
    public final /* synthetic */ BinaryOperator combiner() {
        return this.f18736a.combiner();
    }

    public final /* synthetic */ boolean equals(Object obj) {
        Collector collector = this.f18736a;
        if (obj instanceof j) {
            obj = ((j) obj).f18736a;
        }
        return collector.equals(obj);
    }

    @Override // java.util.stream.Collector
    public final /* synthetic */ Function finisher() {
        return this.f18736a.finisher();
    }

    public final /* synthetic */ int hashCode() {
        return this.f18736a.hashCode();
    }

    @Override // java.util.stream.Collector
    public final /* synthetic */ Supplier supplier() {
        return this.f18736a.supplier();
    }
}

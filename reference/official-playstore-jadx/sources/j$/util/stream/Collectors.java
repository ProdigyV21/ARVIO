package j$.util.stream;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes2.dex */
public final class Collectors {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Set f18598a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Set f18599b;

    static {
        h hVar = h.CONCURRENT;
        h hVar2 = h.UNORDERED;
        h hVar3 = h.IDENTITY_FINISH;
        Collections.unmodifiableSet(EnumSet.of(hVar, hVar2, hVar3));
        Collections.unmodifiableSet(EnumSet.of(hVar, hVar2));
        f18598a = Collections.unmodifiableSet(EnumSet.of(hVar3));
        Collections.unmodifiableSet(EnumSet.of(hVar2, hVar3));
        f18599b = Collections.EMPTY_SET;
        Collections.unmodifiableSet(EnumSet.of(hVar2));
    }

    public static <T, C extends Collection<T>> Collector<T, ?, C> toCollection(Supplier<C> supplier) {
        return new k(supplier, new j$.time.c(16), new j$.time.c(17), f18598a);
    }

    public static <T> Collector<T, ?, List<T>> toList() {
        return new k(new j$.time.c(18), new j$.time.c(19), new j$.time.c(22), f18598a);
    }

    public static <T, A, R, RR> Collector<T, A, RR> collectingAndThen(Collector<T, A, R> collector, Function<R, RR> function) {
        Set setCharacteristics = collector.characteristics();
        h hVar = h.IDENTITY_FINISH;
        if (setCharacteristics.contains(hVar)) {
            if (setCharacteristics.size() == 1) {
                setCharacteristics = f18599b;
            } else {
                EnumSet enumSetCopyOf = EnumSet.copyOf((Collection) setCharacteristics);
                enumSetCopyOf.remove(hVar);
                setCharacteristics = Collections.unmodifiableSet(enumSetCopyOf);
            }
        }
        return new k(collector.supplier(), collector.accumulator(), collector.combiner(), collector.finisher().mo7027andThen(function), setCharacteristics);
    }

    public static void a(double[] dArr, double d4) {
        double d10 = d4 - dArr[1];
        double d11 = dArr[0];
        double d12 = d11 + d10;
        dArr[1] = (d12 - d11) - d10;
        dArr[0] = d12;
    }
}

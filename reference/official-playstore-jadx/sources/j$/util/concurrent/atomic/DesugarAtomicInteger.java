package j$.util.concurrent.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

/* JADX INFO: loaded from: classes2.dex */
public class DesugarAtomicInteger {
    public static int updateAndGet(AtomicInteger atomicInteger, IntUnaryOperator intUnaryOperator) {
        int i10;
        int iApplyAsInt;
        do {
            i10 = atomicInteger.get();
            iApplyAsInt = intUnaryOperator.applyAsInt(i10);
        } while (!atomicInteger.compareAndSet(i10, iApplyAsInt));
        return iApplyAsInt;
    }
}

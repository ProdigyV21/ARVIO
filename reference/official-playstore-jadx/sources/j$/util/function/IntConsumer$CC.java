package j$.util.function;

import j$.util.Objects;
import java.util.function.IntConsumer;

/* JADX INFO: renamed from: j$.util.function.IntConsumer$-CC, reason: invalid class name */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class IntConsumer$CC {
    public static IntConsumer $default$andThen(final IntConsumer intConsumer, final IntConsumer intConsumer2) {
        Objects.requireNonNull(intConsumer2);
        return new IntConsumer() { // from class: j$.util.function.e
            public final /* synthetic */ IntConsumer andThen(IntConsumer intConsumer3) {
                return IntConsumer$CC.$default$andThen(this, intConsumer3);
            }

            @Override // java.util.function.IntConsumer
            public final void accept(int i10) {
                intConsumer.accept(i10);
                intConsumer2.accept(i10);
            }
        };
    }
}

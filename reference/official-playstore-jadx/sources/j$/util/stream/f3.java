package j$.util.stream;

import java.util.function.Consumer;

/* JADX INFO: loaded from: classes2.dex */
public final class f3 extends h3 implements j$.util.h0 {
    @Override // j$.util.Spliterator
    public final /* synthetic */ void forEachRemaining(Consumer consumer) {
        j$.com.android.tools.r8.a.j(this, consumer);
    }

    @Override // j$.util.Spliterator
    public final /* synthetic */ boolean tryAdvance(Consumer consumer) {
        return j$.com.android.tools.r8.a.A(this, consumer);
    }
}

package w7;

import j$.util.concurrent.ThreadLocalRandom;
import java.util.Random;

/* JADX INFO: loaded from: classes5.dex */
public final class a extends v7.a {
    @Override // v7.d
    public final int e(int i10, int i11) {
        return ThreadLocalRandom.current().nextInt(i10, i11);
    }

    @Override // v7.d
    public final long g(long j10) {
        return ThreadLocalRandom.current().nextLong(j10);
    }

    @Override // v7.d
    public final long h(long j10) {
        return ThreadLocalRandom.current().nextLong(0L, j10);
    }

    @Override // v7.a
    public final Random i() {
        return ThreadLocalRandom.current();
    }
}

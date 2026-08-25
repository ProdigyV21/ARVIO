package androidx.media3.exoplayer.source;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class q {
    public static ShuffleOrder b(ShuffleOrder shuffleOrder, int i10, int i11) {
        return shuffleOrder.cloneAndClear().cloneAndInsert(0, i10);
    }

    public static ShuffleOrder a(ShuffleOrder shuffleOrder, int i10, int i11, int i12) {
        return shuffleOrder;
    }
}

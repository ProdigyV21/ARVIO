package j$.util.concurrent;

/* JADX INFO: loaded from: classes2.dex */
public final class v extends ThreadLocal {
    @Override // java.lang.ThreadLocal
    public final Object initialValue() {
        return new ThreadLocalRandom(0);
    }
}

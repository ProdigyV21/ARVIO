package j$.util.concurrent;

/* JADX INFO: loaded from: classes2.dex */
public abstract class a extends p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ConcurrentHashMap f18477i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public l f18478j;

    public a(l[] lVarArr, int i10, int i11, ConcurrentHashMap concurrentHashMap) {
        super(lVarArr, i10, 0, i11);
        this.f18477i = concurrentHashMap;
        a();
    }

    public final boolean hasNext() {
        return this.f18497b != null;
    }

    public final boolean hasMoreElements() {
        return this.f18497b != null;
    }

    public final void remove() {
        l lVar = this.f18478j;
        if (lVar == null) {
            throw new IllegalStateException();
        }
        this.f18478j = null;
        this.f18477i.g(lVar.f18489b, null, null);
    }
}

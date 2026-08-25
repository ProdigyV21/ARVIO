package ra;

import ka.f0;

/* JADX INFO: loaded from: classes5.dex */
public final class d extends g {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final d f21426l;

    static {
        int i10 = k.f21434c;
        int i11 = k.f21435d;
        long j10 = k.f21436e;
        String str = k.f21432a;
        d dVar = new d();
        dVar.f21428i = new b(j10, i10, str, i11);
        f21426l = dVar;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        throw new UnsupportedOperationException("Dispatchers.Default cannot be closed");
    }

    @Override // ka.f0
    public final f0 limitedParallelism(int i10) {
        pa.a.a(i10);
        return i10 >= k.f21434c ? this : super.limitedParallelism(i10);
    }

    @Override // ka.f0
    public final String toString() {
        return "Dispatchers.Default";
    }
}

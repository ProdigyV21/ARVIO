package f7;

/* JADX INFO: loaded from: classes5.dex */
public final class b implements d7.d {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final b f15289i = new b();

    @Override // d7.d
    public final d7.j getContext() {
        throw new IllegalStateException("This continuation is already complete");
    }

    @Override // d7.d
    public final void resumeWith(Object obj) {
        throw new IllegalStateException("This continuation is already complete");
    }

    public final String toString() {
        return "This continuation is already complete";
    }
}

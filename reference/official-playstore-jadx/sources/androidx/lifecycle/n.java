package androidx.lifecycle;

/* JADX INFO: loaded from: classes3.dex */
public final class n {
    public static p a(q qVar) {
        int iOrdinal = qVar.ordinal();
        if (iOrdinal == 2) {
            return p.ON_DESTROY;
        }
        if (iOrdinal == 3) {
            return p.ON_STOP;
        }
        if (iOrdinal != 4) {
            return null;
        }
        return p.ON_PAUSE;
    }
}

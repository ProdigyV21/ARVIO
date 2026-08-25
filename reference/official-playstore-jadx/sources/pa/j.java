package pa;

/* JADX INFO: loaded from: classes5.dex */
public class j extends m {
    @Override // pa.m
    public final boolean isRemoved() {
        return false;
    }

    @Override // pa.m
    public final m nextIfRemoved() {
        return null;
    }

    @Override // pa.m
    public final boolean remove() {
        throw new IllegalStateException("head cannot be removed");
    }
}

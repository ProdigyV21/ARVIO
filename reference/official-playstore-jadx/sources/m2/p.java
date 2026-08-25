package m2;

/* JADX INFO: loaded from: classes3.dex */
public final class p extends r {
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && p.class == obj.getClass();
    }

    public final int hashCode() {
        return p.class.getName().hashCode();
    }

    public final String toString() {
        return "Retry";
    }
}

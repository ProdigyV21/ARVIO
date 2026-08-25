package androidx.navigation;

/* JADX INFO: loaded from: classes3.dex */
public final class a extends d0 {
    @Override // androidx.navigation.d0
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof a) || !super.equals(obj)) {
            return false;
        }
        return true;
    }

    @Override // androidx.navigation.d0
    public final int hashCode() {
        return super.hashCode() * 961;
    }
}

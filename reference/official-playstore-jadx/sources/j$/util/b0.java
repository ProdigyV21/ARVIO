package j$.util;

/* JADX INFO: loaded from: classes2.dex */
public final class b0 extends a1 {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ java.util.SortedSet f18447f;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b0(java.util.SortedSet sortedSet, java.util.Collection collection) {
        super(collection, 21);
        this.f18447f = sortedSet;
    }

    @Override // j$.util.a1, j$.util.Spliterator
    public final java.util.Comparator getComparator() {
        return this.f18447f.comparator();
    }
}

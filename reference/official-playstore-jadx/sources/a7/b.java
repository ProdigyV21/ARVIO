package a7;

import java.util.Comparator;

/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class b implements Comparator {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ Comparator f128i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Comparator f129l;

    public /* synthetic */ b(Comparator comparator, Comparator comparator2) {
        this.f128i = comparator;
        this.f129l = comparator2;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        int iCompare = this.f128i.compare(obj, obj2);
        return iCompare != 0 ? iCompare : this.f129l.compare(obj, obj2);
    }
}

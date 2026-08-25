package i9;

import com.google.common.util.concurrent.r0;
import g8.a1;
import g8.p0;
import g8.w;
import java.util.Comparator;

/* JADX INFO: loaded from: classes5.dex */
public final class i implements Comparator {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final i f16251l = new i(0);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f16252i;

    public /* synthetic */ i(int i10) {
        this.f16252i = i10;
    }

    public static int a(g8.k kVar) {
        if (f.m(kVar)) {
            return 8;
        }
        if (kVar instanceof g8.j) {
            return 7;
        }
        if (kVar instanceof p0) {
            return ((p0) kVar).M() == null ? 6 : 5;
        }
        if (kVar instanceof w) {
            return ((w) kVar).M() == null ? 4 : 3;
        }
        if (kVar instanceof g8.f) {
            return 2;
        }
        return kVar instanceof a1 ? 1 : 0;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        Integer numValueOf;
        switch (this.f16252i) {
            case 0:
                g8.k kVar = (g8.k) obj;
                g8.k kVar2 = (g8.k) obj2;
                int iA = a(kVar2) - a(kVar);
                if (iA != 0) {
                    numValueOf = Integer.valueOf(iA);
                } else if (f.n(kVar, 4) && f.n(kVar2, 4)) {
                    numValueOf = 0;
                } else {
                    int iCompareTo = kVar.getName().f15368i.compareTo(kVar2.getName().f15368i);
                    numValueOf = iCompareTo != 0 ? Integer.valueOf(iCompareTo) : null;
                }
                if (numValueOf != null) {
                    return numValueOf.intValue();
                }
                return 0;
            default:
                return r0.e(m9.d.g((g8.f) obj).b(), m9.d.g((g8.f) obj2).b());
        }
    }
}

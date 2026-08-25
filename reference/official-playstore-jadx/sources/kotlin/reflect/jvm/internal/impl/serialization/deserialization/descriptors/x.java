package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import a9.k1;
import a9.r1;
import g8.y0;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import s9.g0;

/* JADX INFO: loaded from: classes5.dex */
public final class x extends j8.c {

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final s9.m f19906u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final r1 f19907v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final b f19908w;

    /* JADX WARN: Illegal instructions before constructor call */
    public x(s9.m mVar, r1 r1Var, int i10) {
        int i11;
        u9.p pVar = mVar.f21731a.f21706a;
        g8.k kVar = mVar.f21733c;
        f9.f fVarX = t7.a.x(mVar.f21732b, r1Var.f649o);
        int iOrdinal = r1Var.f651q.ordinal();
        int i12 = 2;
        if (iOrdinal == 0) {
            i11 = i12;
        } else if (iOrdinal == 1) {
            i12 = 3;
            i11 = i12;
        } else {
            if (iOrdinal != 2) {
                throw new NoWhenBranchMatchedException();
            }
            i11 = 1;
        }
        super(pVar, kVar, h8.g.f15863a, fVarX, i11, r1Var.f650p, i10, y0.f15559l);
        this.f19906u = mVar;
        this.f19907v = r1Var;
        this.f19908w = new b(pVar, new r(this, 4));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.lang.Iterable, java.util.List] */
    /* JADX WARN: Type inference failed for: r3v7, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    @Override // j8.j
    public final List H0() {
        s9.m mVar = this.f19906u;
        androidx.work.impl.constraints.j jVar = mVar.f21734d;
        r1 r1Var = this.f19907v;
        List list = r1Var.f652r;
        boolean zIsEmpty = list.isEmpty();
        ?? arrayList = list;
        if (zIsEmpty) {
            arrayList = 0;
        }
        if (arrayList == 0) {
            List list2 = r1Var.f653s;
            arrayList = new ArrayList(kotlin.collections.s.U(list2, 10));
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                arrayList.add(jVar.b(((Integer) it.next()).intValue()));
            }
        }
        if (arrayList.isEmpty()) {
            return Collections.singletonList(m9.d.e(this).m());
        }
        g0 g0Var = mVar.f21738h;
        ArrayList arrayList2 = new ArrayList(kotlin.collections.s.U(arrayList, 10));
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList2.add(g0Var.g((k1) it2.next()));
        }
        return arrayList2;
    }

    @Override // androidx.tvprovider.media.tv.h, h8.a
    public final h8.h getAnnotations() {
        return this.f19908w;
    }
}

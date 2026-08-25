package kotlin.reflect.jvm.internal.impl.serialization.deserialization.descriptors;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes5.dex */
public final class f extends i9.q {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ ArrayList f19855c;

    public f(ArrayList arrayList) {
        this.f19855c = arrayList;
    }

    @Override // i9.q
    public final void b(g8.c cVar) {
        i9.n.r(cVar, null);
        this.f19855c.add(cVar);
    }

    @Override // i9.q
    public final void d(g8.c cVar, g8.c cVar2) {
        if (cVar2 instanceof j8.w) {
            ((j8.w) cVar2).N0(g8.r.f15540a, cVar);
        }
    }
}

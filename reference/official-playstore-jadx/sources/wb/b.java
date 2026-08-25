package wb;

import gb.c0;
import gb.p0;
import java.util.TreeSet;
import kotlin.collections.x;
import mb.f;

/* JADX INFO: loaded from: classes5.dex */
public final class b implements c0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile Object f22540a;

    public final void a(String str) {
        TreeSet treeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
        x.b0(treeSet, (Iterable) this.f22540a);
        treeSet.add(str);
        this.f22540a = treeSet;
    }

    @Override // gb.c0
    public final p0 intercept(c0.a aVar) {
        f fVar = (f) aVar;
        return fVar.a(fVar.f20366e);
    }
}

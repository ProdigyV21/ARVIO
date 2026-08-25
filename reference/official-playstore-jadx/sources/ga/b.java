package ga;

import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
public final class b extends kotlin.collections.b {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Iterator f15562m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final r7.l f15563n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final HashSet f15564o = new HashSet();

    public b(Iterator it, r7.l lVar) {
        this.f15562m = it;
        this.f15563n = lVar;
    }

    @Override // kotlin.collections.b
    public final void a() {
        Object next;
        do {
            Iterator it = this.f15562m;
            if (!it.hasNext()) {
                this.f19684i = 2;
                return;
            } else {
                next = it.next();
            }
        } while (!this.f15564o.add(this.f15563n.invoke(next)));
        this.f19685l = next;
        this.f19684i = 1;
    }
}

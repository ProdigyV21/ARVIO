package ga;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes5.dex */
public final class a implements m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicReference f15561a;

    public a(m mVar) {
        this.f15561a = new AtomicReference(mVar);
    }

    @Override // ga.m
    public final Iterator iterator() {
        m mVar = (m) this.f15561a.getAndSet(null);
        if (mVar != null) {
            return mVar.iterator();
        }
        throw new IllegalStateException("This sequence can be consumed only once.");
    }
}

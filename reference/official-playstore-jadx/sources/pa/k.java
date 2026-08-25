package pa;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes5.dex */
public abstract class k extends b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final m f21282b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public m f21283c;

    public k(m mVar) {
        this.f21282b = mVar;
    }

    @Override // pa.b
    public final void b(Object obj, Object obj2) {
        m mVar = (m) obj;
        boolean z = obj2 == null;
        m mVar2 = this.f21282b;
        m mVar3 = z ? mVar2 : this.f21283c;
        if (mVar3 != null) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = m._next$FU;
            while (!atomicReferenceFieldUpdater.compareAndSet(mVar, this, mVar3)) {
                if (atomicReferenceFieldUpdater.get(mVar) != this) {
                    return;
                }
            }
            if (z) {
                mVar2.b(this.f21283c);
            }
        }
    }
}

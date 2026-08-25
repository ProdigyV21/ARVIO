package pa;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import ka.m0;

/* JADX INFO: loaded from: classes5.dex */
public class m {
    private static final AtomicReferenceFieldUpdater _next$FU = AtomicReferenceFieldUpdater.newUpdater(m.class, Object.class, "_next");
    private static final AtomicReferenceFieldUpdater _prev$FU = AtomicReferenceFieldUpdater.newUpdater(m.class, Object.class, "_prev");
    private static final AtomicReferenceFieldUpdater _removedRef$FU = AtomicReferenceFieldUpdater.newUpdater(m.class, Object.class, "_removedRef");

    @q7.w
    private volatile Object _next = this;

    @q7.w
    private volatile Object _prev = this;

    @q7.w
    private volatile Object _removedRef;

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0040, code lost:
    
        r5 = ((pa.t) r5).f21295a;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0048, code lost:
    
        if (r4.compareAndSet(r3, r2, r5) == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0050, code lost:
    
        if (r4.get(r3) == r2) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final pa.m a() {
        /*
            r8 = this;
        L0:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r0 = pa.m._prev$FU
            java.lang.Object r0 = r0.get(r8)
            pa.m r0 = (pa.m) r0
            r1 = 0
            r2 = r0
        La:
            r3 = r1
        Lb:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r4 = pa.m._next$FU
            java.lang.Object r5 = r4.get(r2)
            if (r5 != r8) goto L26
            if (r0 != r2) goto L16
            goto L2f
        L16:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r6 = pa.m._prev$FU
        L18:
            boolean r1 = r6.compareAndSet(r8, r0, r2)
            if (r1 == 0) goto L1f
            goto L2f
        L1f:
            java.lang.Object r1 = r6.get(r8)
            if (r1 == r0) goto L18
            goto L0
        L26:
            boolean r6 = r8.isRemoved()
            if (r6 == 0) goto L2d
            return r1
        L2d:
            if (r5 != 0) goto L30
        L2f:
            return r2
        L30:
            boolean r6 = r5 instanceof pa.s
            if (r6 == 0) goto L3a
            pa.s r5 = (pa.s) r5
            r5.a(r2)
            goto L0
        L3a:
            boolean r6 = r5 instanceof pa.t
            if (r6 == 0) goto L5c
            if (r3 == 0) goto L53
            pa.t r5 = (pa.t) r5
            pa.m r5 = r5.f21295a
        L44:
            boolean r6 = r4.compareAndSet(r3, r2, r5)
            if (r6 == 0) goto L4c
            r2 = r3
            goto La
        L4c:
            java.lang.Object r6 = r4.get(r3)
            if (r6 == r2) goto L44
            goto L0
        L53:
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r4 = pa.m._prev$FU
            java.lang.Object r2 = r4.get(r2)
            pa.m r2 = (pa.m) r2
            goto Lb
        L5c:
            r3 = r5
            pa.m r3 = (pa.m) r3
            r7 = r3
            r3 = r2
            r2 = r7
            goto Lb
        */
        throw new UnsupportedOperationException("Method not decompiled: pa.m.a():pa.m");
    }

    public final void addLast(m mVar) {
        while (!getPrevNode().addNext(mVar, this)) {
        }
    }

    public final boolean addLastIf(m mVar, r7.a<Boolean> aVar) {
        int iTryCondAddNext;
        l lVar = new l(mVar, aVar);
        do {
            iTryCondAddNext = getPrevNode().tryCondAddNext(mVar, this, lVar);
            if (iTryCondAddNext == 1) {
                return true;
            }
        } while (iTryCondAddNext != 2);
        return false;
    }

    public final boolean addNext(m mVar, m mVar2) {
        _prev$FU.lazySet(mVar, this);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _next$FU;
        atomicReferenceFieldUpdater.lazySet(mVar, mVar2);
        while (!atomicReferenceFieldUpdater.compareAndSet(this, mVar2, mVar)) {
            if (atomicReferenceFieldUpdater.get(this) != mVar2) {
                return false;
            }
        }
        mVar.b(mVar2);
        return true;
    }

    public final boolean addOneIfEmpty(m mVar) {
        _prev$FU.lazySet(mVar, this);
        _next$FU.lazySet(mVar, this);
        while (getNext() == this) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _next$FU;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, this, mVar)) {
                if (atomicReferenceFieldUpdater.get(this) != this) {
                    break;
                }
            }
            mVar.b(this);
            return true;
        }
        return false;
    }

    public final void b(m mVar) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _prev$FU;
        while (true) {
            m mVar2 = (m) atomicReferenceFieldUpdater.get(mVar);
            if (getNext() != mVar) {
                return;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = _prev$FU;
            while (!atomicReferenceFieldUpdater2.compareAndSet(mVar, mVar2, this)) {
                if (atomicReferenceFieldUpdater2.get(mVar) != mVar2) {
                    break;
                }
            }
            if (isRemoved()) {
                mVar.a();
                return;
            }
            return;
        }
    }

    public final Object getNext() {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _next$FU;
        while (true) {
            Object obj = atomicReferenceFieldUpdater.get(this);
            if (!(obj instanceof s)) {
                return obj;
            }
            ((s) obj).a(this);
        }
    }

    public final m getNextNode() {
        m mVar;
        Object next = getNext();
        t tVar = next instanceof t ? (t) next : null;
        return (tVar == null || (mVar = tVar.f21295a) == null) ? (m) next : mVar;
    }

    public final m getPrevNode() {
        m mVarA = a();
        if (mVarA != null) {
            return mVarA;
        }
        Object obj = _prev$FU.get(this);
        while (true) {
            m mVar = (m) obj;
            if (!mVar.isRemoved()) {
                return mVar;
            }
            obj = _prev$FU.get(mVar);
        }
    }

    public boolean isRemoved() {
        return getNext() instanceof t;
    }

    public final k makeCondAddOp(m mVar, r7.a<Boolean> aVar) {
        return new l(mVar, aVar);
    }

    public m nextIfRemoved() {
        Object next = getNext();
        t tVar = next instanceof t ? (t) next : null;
        if (tVar != null) {
            return tVar.f21295a;
        }
        return null;
    }

    public boolean remove() {
        return removeOrNext() == null;
    }

    public final m removeOrNext() {
        while (true) {
            Object next = getNext();
            if (next instanceof t) {
                return ((t) next).f21295a;
            }
            if (next == this) {
                return (m) next;
            }
            m mVar = (m) next;
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _removedRef$FU;
            t tVar = (t) atomicReferenceFieldUpdater.get(mVar);
            if (tVar == null) {
                tVar = new t(mVar);
                atomicReferenceFieldUpdater.lazySet(mVar, tVar);
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = _next$FU;
            while (!atomicReferenceFieldUpdater2.compareAndSet(this, next, tVar)) {
                if (atomicReferenceFieldUpdater2.get(this) != next) {
                    break;
                }
            }
            mVar.a();
            return null;
        }
    }

    public String toString() {
        return new androidx.tv.foundation.lazy.grid.r(1, 2, m0.class, this, "classSimpleName", "getClassSimpleName(Ljava/lang/Object;)Ljava/lang/String;") + '@' + m0.j(this);
    }

    public final int tryCondAddNext(m mVar, m mVar2, k kVar) {
        _prev$FU.lazySet(mVar, this);
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = _next$FU;
        atomicReferenceFieldUpdater.lazySet(mVar, mVar2);
        kVar.f21283c = mVar2;
        while (!atomicReferenceFieldUpdater.compareAndSet(this, mVar2, kVar)) {
            if (atomicReferenceFieldUpdater.get(this) != mVar2) {
                return 0;
            }
        }
        return kVar.a(this) == null ? 1 : 2;
    }

    public final void validateNode$kotlinx_coroutines_core(m mVar, m mVar2) {
    }
}

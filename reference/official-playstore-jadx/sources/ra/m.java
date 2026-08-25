package ra;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import q7.w;

/* JADX INFO: loaded from: classes5.dex */
public final class m {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f21441b = AtomicReferenceFieldUpdater.newUpdater(m.class, Object.class, "lastScheduledTask");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f21442c = AtomicIntegerFieldUpdater.newUpdater(m.class, "producerIndex");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f21443d = AtomicIntegerFieldUpdater.newUpdater(m.class, "consumerIndex");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f21444e = AtomicIntegerFieldUpdater.newUpdater(m.class, "blockingTasksInBuffer");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicReferenceArray f21445a = new AtomicReferenceArray(128);

    @w
    private volatile int blockingTasksInBuffer;

    @w
    private volatile int consumerIndex;

    @w
    private volatile Object lastScheduledTask;

    @w
    private volatile int producerIndex;

    public final h a(h hVar) {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f21442c;
        if (atomicIntegerFieldUpdater.get(this) - f21443d.get(this) == 127) {
            return hVar;
        }
        if (hVar.f21430l.a() == 1) {
            f21444e.incrementAndGet(this);
        }
        int i10 = atomicIntegerFieldUpdater.get(this) & 127;
        while (true) {
            AtomicReferenceArray atomicReferenceArray = this.f21445a;
            if (atomicReferenceArray.get(i10) == null) {
                atomicReferenceArray.lazySet(i10, hVar);
                atomicIntegerFieldUpdater.incrementAndGet(this);
                return null;
            }
            Thread.yield();
        }
    }

    public final h b() {
        h hVar;
        while (true) {
            AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = f21443d;
            int i10 = atomicIntegerFieldUpdater.get(this);
            if (i10 - f21442c.get(this) == 0) {
                return null;
            }
            int i11 = i10 & 127;
            if (atomicIntegerFieldUpdater.compareAndSet(this, i10, i10 + 1) && (hVar = (h) this.f21445a.getAndSet(i11, null)) != null) {
                if (hVar.f21430l.a() == 1) {
                    f21444e.decrementAndGet(this);
                }
                return hVar;
            }
        }
    }

    public final h c(int i10, boolean z) {
        int i11 = i10 & 127;
        AtomicReferenceArray atomicReferenceArray = this.f21445a;
        h hVar = (h) atomicReferenceArray.get(i11);
        if (hVar != null) {
            if ((hVar.f21430l.a() == 1) == z) {
                while (!atomicReferenceArray.compareAndSet(i11, hVar, null)) {
                    if (atomicReferenceArray.get(i11) != hVar) {
                    }
                }
                if (z) {
                    f21444e.decrementAndGet(this);
                }
                return hVar;
            }
        }
        return null;
    }
}

package ka;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes5.dex */
public final class b2 implements p1 {

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public static final AtomicIntegerFieldUpdater f19555l = AtomicIntegerFieldUpdater.newUpdater(b2.class, "_isCompleting");

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f19556m = AtomicReferenceFieldUpdater.newUpdater(b2.class, Object.class, "_rootCause");

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f19557n = AtomicReferenceFieldUpdater.newUpdater(b2.class, Object.class, "_exceptionsHolder");

    @q7.w
    private volatile Object _exceptionsHolder;

    @q7.w
    private volatile int _isCompleting = 0;

    @q7.w
    private volatile Object _rootCause;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final i2 f19558i;

    public b2(i2 i2Var, Throwable th) {
        this.f19558i = i2Var;
        this._rootCause = th;
    }

    public final void a(Throwable th) {
        Throwable thB = b();
        if (thB == null) {
            f19556m.set(this, th);
            return;
        }
        if (th == thB) {
            return;
        }
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f19557n;
        Object obj = atomicReferenceFieldUpdater.get(this);
        if (obj == null) {
            atomicReferenceFieldUpdater.set(this, th);
            return;
        }
        if (!(obj instanceof Throwable)) {
            if (obj instanceof ArrayList) {
                ((ArrayList) obj).add(th);
                return;
            } else {
                throw new IllegalStateException(("State is " + obj).toString());
            }
        }
        if (th == obj) {
            return;
        }
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(obj);
        arrayList.add(th);
        atomicReferenceFieldUpdater.set(this, arrayList);
    }

    public final Throwable b() {
        return (Throwable) f19556m.get(this);
    }

    public final boolean c() {
        return b() != null;
    }

    public final boolean d() {
        return f19555l.get(this) != 0;
    }

    public final ArrayList e(Throwable th) {
        ArrayList arrayList;
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f19557n;
        Object obj = atomicReferenceFieldUpdater.get(this);
        if (obj == null) {
            arrayList = new ArrayList(4);
        } else if (obj instanceof Throwable) {
            ArrayList arrayList2 = new ArrayList(4);
            arrayList2.add(obj);
            arrayList = arrayList2;
        } else {
            if (!(obj instanceof ArrayList)) {
                throw new IllegalStateException(("State is " + obj).toString());
            }
            arrayList = (ArrayList) obj;
        }
        Throwable thB = b();
        if (thB != null) {
            arrayList.add(0, thB);
        }
        if (th != null && !th.equals(thB)) {
            arrayList.add(th);
        }
        atomicReferenceFieldUpdater.set(this, m0.f19618h);
        return arrayList;
    }

    @Override // ka.p1
    public final i2 getList() {
        return this.f19558i;
    }

    @Override // ka.p1
    public final boolean isActive() {
        return b() == null;
    }

    public final String toString() {
        return "Finishing[cancelling=" + c() + ", completing=" + d() + ", rootCause=" + b() + ", exceptions=" + f19557n.get(this) + ", list=" + this.f19558i + ']';
    }
}

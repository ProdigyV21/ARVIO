package ka;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes5.dex */
public abstract class y1 extends pa.m implements z0, p1, r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public f2 f19659i;

    public final f2 c() {
        f2 f2Var = this.f19659i;
        if (f2Var != null) {
            return f2Var;
        }
        kotlin.jvm.internal.p.i("job");
        throw null;
    }

    @Override // ka.z0
    public final void dispose() {
        f2 f2VarC = c();
        while (true) {
            Object objY = f2VarC.y();
            if (!(objY instanceof y1)) {
                if (!(objY instanceof p1) || ((p1) objY).getList() == null) {
                    return;
                }
                remove();
                return;
            }
            if (objY != this) {
                return;
            }
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f2.f19587i;
            b1 b1Var = m0.f19620j;
            while (!atomicReferenceFieldUpdater.compareAndSet(f2VarC, objY, b1Var)) {
                if (atomicReferenceFieldUpdater.get(f2VarC) != objY) {
                    break;
                }
            }
            return;
        }
    }

    @Override // ka.p1
    public final i2 getList() {
        return null;
    }

    public v1 getParent() {
        return c();
    }

    public abstract void invoke(Throwable th);

    @Override // ka.p1
    public final boolean isActive() {
        return true;
    }

    @Override // pa.m
    public final String toString() {
        return getClass().getSimpleName() + '@' + m0.j(this) + "[job@" + m0.j(c()) + ']';
    }
}

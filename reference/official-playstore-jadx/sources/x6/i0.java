package x6;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public final class i0 implements s, Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public r7.a f22584i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public volatile Object f22585l = q0.f22597a;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f22586m = this;

    public i0(r7.a aVar) {
        this.f22584i = aVar;
    }

    @Override // x6.s
    public final Object getValue() {
        Object objInvoke;
        Object obj = this.f22585l;
        q0 q0Var = q0.f22597a;
        if (obj != q0Var) {
            return obj;
        }
        synchronized (this.f22586m) {
            objInvoke = this.f22585l;
            if (objInvoke == q0Var) {
                objInvoke = this.f22584i.invoke();
                this.f22585l = objInvoke;
                this.f22584i = null;
            }
        }
        return objInvoke;
    }

    @Override // x6.s
    public final boolean isInitialized() {
        return this.f22585l != q0.f22597a;
    }

    public final String toString() {
        return isInitialized() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }
}

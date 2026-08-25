package x6;

import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public final class u0 implements s, Serializable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public r7.a f22606i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public Object f22607l;

    @Override // x6.s
    public final Object getValue() {
        if (this.f22607l == q0.f22597a) {
            this.f22607l = this.f22606i.invoke();
            this.f22606i = null;
        }
        return this.f22607l;
    }

    @Override // x6.s
    public final boolean isInitialized() {
        return this.f22607l != q0.f22597a;
    }

    public final String toString() {
        return isInitialized() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }
}

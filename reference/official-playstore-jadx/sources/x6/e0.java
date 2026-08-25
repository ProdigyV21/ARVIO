package x6;

import androidx.media3.exoplayer.upstream.CmcdData;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes5.dex */
public final class e0 implements s, Serializable {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final AtomicReferenceFieldUpdater f22581m = AtomicReferenceFieldUpdater.newUpdater(e0.class, Object.class, CmcdData.STREAM_TYPE_LIVE);

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public volatile r7.a f22582i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public volatile Object f22583l;

    @Override // x6.s
    public final Object getValue() {
        Object obj = this.f22583l;
        q0 q0Var = q0.f22597a;
        if (obj != q0Var) {
            return obj;
        }
        r7.a aVar = this.f22582i;
        if (aVar != null) {
            Object objInvoke = aVar.invoke();
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = f22581m;
            while (!atomicReferenceFieldUpdater.compareAndSet(this, q0Var, objInvoke)) {
                if (atomicReferenceFieldUpdater.get(this) != q0Var) {
                }
            }
            this.f22582i = null;
            return objInvoke;
        }
        return this.f22583l;
    }

    @Override // x6.s
    public final boolean isInitialized() {
        return this.f22583l != q0.f22597a;
    }

    public final String toString() {
        return isInitialized() ? String.valueOf(getValue()) : "Lazy value not initialized yet.";
    }
}

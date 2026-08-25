package ua;

import java.util.concurrent.atomic.AtomicReferenceArray;
import pa.w;

/* JADX INFO: loaded from: classes5.dex */
public final class k extends w {

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final AtomicReferenceArray f22114o;

    public k(long j10, k kVar, int i10) {
        super(j10, kVar, i10);
        this.f22114o = new AtomicReferenceArray(j.f22113f);
    }

    @Override // pa.w
    public final int f() {
        return j.f22113f;
    }

    @Override // pa.w
    public final void g(int i10, d7.j jVar) {
        this.f22114o.set(i10, j.f22112e);
        h();
    }

    public final String toString() {
        return "SemaphoreSegment[id=" + this.f21298m + ", hashCode=" + hashCode() + ']';
    }
}

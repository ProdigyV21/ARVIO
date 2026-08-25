package androidx.work.impl.utils;

/* JADX INFO: loaded from: classes3.dex */
public final class m implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final androidx.work.impl.g f7048i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final androidx.work.impl.l f7049l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f7050m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final int f7051n;

    public m(androidx.work.impl.g gVar, androidx.work.impl.l lVar, boolean z, int i10) {
        this.f7048i = gVar;
        this.f7049l = lVar;
        this.f7050m = z;
        this.f7051n = i10;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean zK;
        androidx.work.impl.v vVarC;
        if (this.f7050m) {
            androidx.work.impl.g gVar = this.f7048i;
            androidx.work.impl.l lVar = this.f7049l;
            int i10 = this.f7051n;
            gVar.getClass();
            String str = lVar.f6896a.f6914a;
            synchronized (gVar.k) {
                vVarC = gVar.c(str);
            }
            zK = androidx.work.impl.g.f(str, vVarC, i10);
        } else {
            zK = this.f7048i.k(this.f7049l, this.f7051n);
        }
        m2.t.d().a(m2.t.f("StopWorkRunnable"), "StopWorkRunnable for " + this.f7049l.f6896a.f6914a + "; Processor.stopWork = " + zK);
    }
}

package androidx.emoji2.text;

import java.util.concurrent.ThreadPoolExecutor;

/* JADX INFO: loaded from: classes3.dex */
public final class r extends m2.f0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ m2.f0 f2641a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ ThreadPoolExecutor f2642b;

    public r(m2.f0 f0Var, ThreadPoolExecutor threadPoolExecutor) {
        this.f2641a = f0Var;
        this.f2642b = threadPoolExecutor;
    }

    @Override // m2.f0
    public final void D(Throwable th) {
        ThreadPoolExecutor threadPoolExecutor = this.f2642b;
        try {
            this.f2641a.D(th);
        } finally {
            threadPoolExecutor.shutdown();
        }
    }

    @Override // m2.f0
    public final void E(e0 e0Var) {
        ThreadPoolExecutor threadPoolExecutor = this.f2642b;
        try {
            this.f2641a.E(e0Var);
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}

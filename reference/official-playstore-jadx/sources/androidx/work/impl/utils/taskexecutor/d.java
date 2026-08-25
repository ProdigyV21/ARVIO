package androidx.work.impl.utils.taskexecutor;

import android.os.Handler;
import android.os.Looper;
import androidx.work.impl.utils.l;
import java.util.concurrent.ExecutorService;
import ka.k1;

/* JADX INFO: loaded from: classes3.dex */
public final class d implements b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final l f7076a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final k1 f7077b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Handler f7078c = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final c f7079d = new c(this);

    public d(ExecutorService executorService) {
        l lVar = new l(executorService);
        this.f7076a = lVar;
        this.f7077b = new k1(lVar);
    }

    @Override // androidx.work.impl.utils.taskexecutor.b
    public final c a() {
        return this.f7079d;
    }

    @Override // androidx.work.impl.utils.taskexecutor.b
    public final k1 b() {
        return this.f7077b;
    }

    @Override // androidx.work.impl.utils.taskexecutor.b
    public final l c() {
        return this.f7076a;
    }

    @Override // androidx.work.impl.utils.taskexecutor.b
    public final void d(Runnable runnable) {
        this.f7076a.execute(runnable);
    }
}

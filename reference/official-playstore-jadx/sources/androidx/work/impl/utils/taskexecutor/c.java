package androidx.work.impl.utils.taskexecutor;

import android.os.Looper;
import androidx.loader.content.j;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public final class c implements Executor {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7074i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final Object f7075l;

    public c(Looper looper) {
        this.f7075l = new j(looper, 4);
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        switch (this.f7074i) {
            case 0:
                ((d) this.f7075l).f7078c.post(runnable);
                break;
            default:
                ((j) this.f7075l).post(runnable);
                break;
        }
    }

    public c(d dVar) {
        this.f7075l = dVar;
    }
}

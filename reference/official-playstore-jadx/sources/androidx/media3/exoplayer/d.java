package androidx.media3.exoplayer;

import androidx.media3.common.util.BackgroundThreadStateHandler;
import androidx.media3.common.util.HandlerWrapper;
import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class d implements Executor {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3590i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3591l;

    public /* synthetic */ d(Object obj, int i10) {
        this.f3590i = i10;
        this.f3591l = obj;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        switch (this.f3590i) {
            case 0:
                ((BackgroundThreadStateHandler) this.f3591l).runInBackground(runnable);
                break;
            default:
                ((HandlerWrapper) this.f3591l).post(runnable);
                break;
        }
    }
}

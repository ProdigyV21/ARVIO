package androidx.media3.session;

import java.util.concurrent.Executor;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class o implements Executor {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4089i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f4090l;

    public /* synthetic */ o(Object obj, int i10) {
        this.f4089i = i10;
        this.f4090l = obj;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        switch (this.f4089i) {
            case 0:
                ((MediaControllerHolder) this.f4090l).lambda$setController$1(runnable);
                break;
            case 1:
                ((MediaLibrarySessionImpl) this.f4090l).postOrRunOnApplicationHandler(runnable);
                break;
            case 2:
                ((MediaNotificationManager) this.f4090l).lambda$new$0(runnable);
                break;
            default:
                ((MediaSessionImpl) this.f4090l).postOrRunOnApplicationHandler(runnable);
                break;
        }
    }
}

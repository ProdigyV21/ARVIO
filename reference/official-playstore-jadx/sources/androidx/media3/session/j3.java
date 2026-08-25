package androidx.media3.session;

import android.os.HandlerThread;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class j3 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4044i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f4045l;

    public /* synthetic */ j3(Object obj, int i10) {
        this.f4044i = i10;
        this.f4045l = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f4044i) {
            case 0:
                ((SequencedFutureManager) this.f4045l).release();
                break;
            case 1:
                ((MediaController) this.f4045l).release();
                break;
            case 2:
                ((MediaSessionService) this.f4045l).lambda$onForegroundServiceStartNotAllowedException$4();
                break;
            default:
                ((HandlerThread) this.f4045l).quit();
                break;
        }
    }
}

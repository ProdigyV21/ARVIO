package androidx.media3.session;

import androidx.media3.session.MediaSession;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class y1 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4214i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f4215l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f4216m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f4217n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f4218o;

    public /* synthetic */ y1(MediaNotificationManager mediaNotificationManager, MediaSession mediaSession, MediaNotification mediaNotification, boolean z) {
        this.f4216m = mediaNotificationManager;
        this.f4217n = mediaSession;
        this.f4218o = mediaNotification;
        this.f4215l = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f4214i) {
            case 0:
                ((MediaNotificationManager) this.f4216m).lambda$updateNotification$6((MediaSession) this.f4217n, (MediaNotification) this.f4218o, this.f4215l);
                break;
            default:
                ((MediaSessionImpl) this.f4216m).lambda$applyMediaButtonKeyEvent$34(this.f4215l, (MediaSession.ControllerInfo) this.f4217n, (Runnable) this.f4218o);
                break;
        }
    }

    public /* synthetic */ y1(MediaSessionImpl mediaSessionImpl, boolean z, MediaSession.ControllerInfo controllerInfo, Runnable runnable) {
        this.f4216m = mediaSessionImpl;
        this.f4215l = z;
        this.f4217n = controllerInfo;
        this.f4218o = runnable;
    }
}

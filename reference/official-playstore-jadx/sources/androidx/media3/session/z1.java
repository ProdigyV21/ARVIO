package androidx.media3.session;

import androidx.media3.common.Player;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;
import androidx.media3.session.MediaSessionService;
import androidx.media3.session.legacy.MediaSessionManager;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class z1 implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4224i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f4225l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f4226m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f4227n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f4228o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f4229p;

    public /* synthetic */ z1(int i10, Object obj, Object obj2, Object obj3, Object obj4, boolean z) {
        this.f4224i = i10;
        this.f4226m = obj;
        this.f4227n = obj2;
        this.f4228o = obj3;
        this.f4229p = obj4;
        this.f4225l = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f4224i) {
            case 0:
                ((MediaNotificationManager) this.f4226m).lambda$updateNotification$7((MediaSession) this.f4227n, (com.google.common.collect.h1) this.f4228o, (x0) this.f4229p, this.f4225l);
                break;
            case 1:
                ((MediaSessionImpl.AnonymousClass1) this.f4226m).lambda$onSuccess$0((MediaSession.MediaItemsWithStartPosition) this.f4227n, this.f4225l, (MediaSession.ControllerInfo) this.f4228o, (Player.Commands) this.f4229p);
                break;
            default:
                ((MediaSessionService.MediaSessionServiceStub) this.f4226m).lambda$connect$0((IMediaController) this.f4227n, (MediaSessionManager.RemoteUserInfo) this.f4228o, (ConnectionRequest) this.f4229p, this.f4225l);
                break;
        }
    }

    public /* synthetic */ z1(MediaSessionImpl.AnonymousClass1 anonymousClass1, MediaSession.MediaItemsWithStartPosition mediaItemsWithStartPosition, boolean z, MediaSession.ControllerInfo controllerInfo, Player.Commands commands) {
        this.f4224i = 1;
        this.f4226m = anonymousClass1;
        this.f4227n = mediaItemsWithStartPosition;
        this.f4225l = z;
        this.f4228o = controllerInfo;
        this.f4229p = commands;
    }
}

package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class g3 implements MediaSessionStub.SessionTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4000i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MediaSessionStub.SessionTask f4001l;

    public /* synthetic */ g3(MediaSessionStub.SessionTask sessionTask, int i10) {
        this.f4000i = i10;
        this.f4001l = sessionTask;
    }

    @Override // androidx.media3.session.MediaSessionStub.SessionTask
    public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        switch (this.f4000i) {
            case 0:
                return MediaSessionStub.lambda$sendLibraryResultWhenReady$11(this.f4001l, (MediaLibrarySessionImpl) mediaSessionImpl, controllerInfo, i10);
            default:
                return MediaSessionStub.lambda$sendSessionResultWhenReady$3(this.f4001l, mediaSessionImpl, controllerInfo, i10);
        }
    }
}

package androidx.media3.session;

import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class v2 implements MediaSessionStub.SessionTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4179i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f4180l;

    public /* synthetic */ v2(String str, int i10) {
        this.f4179i = i10;
        this.f4180l = str;
    }

    @Override // androidx.media3.session.MediaSessionStub.SessionTask
    public final Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        switch (this.f4179i) {
            case 0:
                return MediaSessionStub.lambda$unsubscribe$77(this.f4180l, (MediaLibrarySessionImpl) mediaSessionImpl, controllerInfo, i10);
            default:
                return MediaSessionStub.lambda$getItem$72(this.f4180l, (MediaLibrarySessionImpl) mediaSessionImpl, controllerInfo, i10);
        }
    }
}

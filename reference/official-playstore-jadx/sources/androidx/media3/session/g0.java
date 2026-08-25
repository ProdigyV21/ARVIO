package androidx.media3.session;

import android.os.RemoteException;
import androidx.media3.common.MediaItem;
import androidx.media3.session.MediaControllerImplBase;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class g0 implements MediaControllerImplBase.RemoteSessionTask, MediaSessionStub.SessionTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ long f3989i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3990l;

    public /* synthetic */ g0(Object obj, long j10) {
        this.f3990l = obj;
        this.f3989i = j10;
    }

    @Override // androidx.media3.session.MediaSessionStub.SessionTask
    public Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        return MediaSessionStub.lambda$setMediaItemWithStartPosition$32((MediaItem) this.f3990l, this.f3989i, mediaSessionImpl, controllerInfo, i10);
    }

    @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
    public void run(IMediaSession iMediaSession, int i10) throws RemoteException {
        ((MediaControllerImplBase) this.f3990l).lambda$seekTo$10(this.f3989i, iMediaSession, i10);
    }
}

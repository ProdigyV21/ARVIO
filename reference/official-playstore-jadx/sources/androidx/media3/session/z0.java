package androidx.media3.session;

import android.os.RemoteException;
import androidx.media3.session.MediaControllerImplBase;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class z0 implements MediaControllerImplBase.RemoteSessionTask, MediaSessionStub.ControllerPlayerTask, MediaSessionStub.SessionTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4221i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ long f4222l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f4223m;

    public /* synthetic */ z0(int i10, long j10, Object obj) {
        this.f4223m = obj;
        this.f4221i = i10;
        this.f4222l = j10;
    }

    @Override // androidx.media3.session.MediaSessionStub.SessionTask
    public Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        return MediaSessionStub.lambda$setMediaItemsWithStartIndex$35((List) this.f4223m, this.f4221i, this.f4222l, mediaSessionImpl, controllerInfo, i10);
    }

    @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
    public void run(IMediaSession iMediaSession, int i10) throws RemoteException {
        ((MediaControllerImplBase) this.f4223m).lambda$seekTo$11(this.f4221i, this.f4222l, iMediaSession, i10);
    }

    @Override // androidx.media3.session.MediaSessionStub.ControllerPlayerTask
    public void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
        ((MediaSessionStub) this.f4223m).lambda$seekToWithMediaItemIndex$23(this.f4221i, this.f4222l, playerWrapper, controllerInfo);
    }
}

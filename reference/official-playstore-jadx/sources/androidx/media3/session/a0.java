package androidx.media3.session;

import android.os.Bundle;
import android.os.RemoteException;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.MediaItem;
import androidx.media3.session.MediaControllerImplBase;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionStub;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class a0 implements MediaControllerImplBase.RemoteSessionTask, MediaSessionStub.SessionTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3914i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3915l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f3916m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f3917n;

    public /* synthetic */ a0(MediaControllerImplBase mediaControllerImplBase, Object obj, boolean z, int i10) {
        this.f3914i = i10;
        this.f3915l = mediaControllerImplBase;
        this.f3917n = obj;
        this.f3916m = z;
    }

    @Override // androidx.media3.session.MediaSessionStub.SessionTask
    public Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        return MediaSessionStub.lambda$onCustomCommandWithProgressUpdate$24(this.f3916m, (SessionCommand) this.f3915l, (Bundle) this.f3917n, mediaSessionImpl, controllerInfo, i10);
    }

    public /* synthetic */ a0(boolean z, SessionCommand sessionCommand, Bundle bundle) {
        this.f3914i = 3;
        this.f3916m = z;
        this.f3915l = sessionCommand;
        this.f3917n = bundle;
    }

    @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
    public void run(IMediaSession iMediaSession, int i10) throws RemoteException {
        switch (this.f3914i) {
            case 0:
                ((MediaControllerImplBase) this.f3915l).lambda$setMediaItem$25((MediaItem) this.f3917n, this.f3916m, iMediaSession, i10);
                break;
            case 1:
                ((MediaControllerImplBase) this.f3915l).lambda$setAudioAttributes$72((AudioAttributes) this.f3917n, this.f3916m, iMediaSession, i10);
                break;
            default:
                ((MediaControllerImplBase) this.f3915l).lambda$setMediaItems$27((List) this.f3917n, this.f3916m, iMediaSession, i10);
                break;
        }
    }
}

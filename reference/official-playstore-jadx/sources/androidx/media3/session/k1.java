package androidx.media3.session;

import android.os.RemoteException;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.Rating;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaController;
import androidx.media3.session.MediaControllerStub;
import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;
import androidx.media3.session.MediaSessionStub;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class k1 implements MediaControllerStub.ControllerTask, MediaSessionImpl.RemoteControllerTask, MediaSessionStub.SessionTask, MediaSessionStub.ControllerPlayerTask, Consumer {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4052i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f4053l;

    public /* synthetic */ k1(Object obj, int i10) {
        this.f4052i = i10;
        this.f4053l = obj;
    }

    @Override // androidx.media3.common.util.Consumer
    public void accept(Object obj) {
        ((MediaController) this.f4053l).lambda$release$0((MediaController.Listener) obj);
    }

    @Override // androidx.media3.session.MediaSessionStub.SessionTask
    public Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        switch (this.f4052i) {
            case 6:
                return MediaSessionStub.lambda$setRating$29((Rating) this.f4053l, mediaSessionImpl, controllerInfo, i10);
            case 7:
                return MediaSessionStub.lambda$getLibraryRoot$71((MediaLibraryService.LibraryParams) this.f4053l, (MediaLibrarySessionImpl) mediaSessionImpl, controllerInfo, i10);
            default:
                return MediaSessionStub.lambda$sendSessionResultSuccess$1((MediaSessionStub.ControllerPlayerTask) this.f4053l, mediaSessionImpl, controllerInfo, i10);
        }
    }

    @Override // androidx.media3.session.MediaControllerStub.ControllerTask
    public void run(MediaControllerImplBase mediaControllerImplBase) {
        switch (this.f4052i) {
            case 0:
                mediaControllerImplBase.onConnected((ConnectionState) this.f4053l);
                break;
            default:
                mediaControllerImplBase.notifyPeriodicSessionPositionInfoChanged((SessionPositionInfo) this.f4053l);
                break;
        }
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public void run(MediaSession.ControllerCb controllerCb, int i10) throws RemoteException {
        switch (this.f4052i) {
            case 2:
                ((MediaSessionImpl) this.f4053l).lambda$handleAvailablePlayerCommandsChanged$24(controllerCb, i10);
                break;
            case 3:
                controllerCb.onDeviceInfoChanged(i10, (DeviceInfo) this.f4053l);
                break;
            case 4:
                controllerCb.onTracksChanged(i10, (Tracks) this.f4053l);
                break;
            default:
                controllerCb.onVideoSizeChanged(i10, (VideoSize) this.f4053l);
                break;
        }
    }

    @Override // androidx.media3.session.MediaSessionStub.ControllerPlayerTask
    public void run(PlayerWrapper playerWrapper, MediaSession.ControllerInfo controllerInfo) {
        ((Consumer) this.f4053l).accept(playerWrapper);
    }
}

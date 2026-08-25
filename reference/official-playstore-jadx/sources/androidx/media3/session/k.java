package androidx.media3.session;

import android.app.PendingIntent;
import android.os.RemoteException;
import android.view.Surface;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Rating;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.session.MediaBrowser;
import androidx.media3.session.MediaBrowserImplLegacy;
import androidx.media3.session.MediaController;
import androidx.media3.session.MediaControllerImplBase;
import androidx.media3.session.MediaControllerImplLegacy;
import androidx.media3.session.MediaControllerStub;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;
import androidx.media3.session.MediaSessionLegacyStub;
import androidx.media3.session.MediaSessionStub;
import androidx.media3.session.PlayerInfo;
import androidx.media3.session.legacy.MediaDescriptionCompat;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class k implements Consumer, MediaControllerImplBase.RemoteSessionTask, ListenerSet.Event, MediaControllerStub.ControllerTask, MediaSessionImpl.RemoteControllerTask, MediaSessionLegacyStub.SessionTask, MediaSessionStub.SessionTask {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4046i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f4047l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f4048m;

    public /* synthetic */ k(Object obj, Object obj2, int i10) {
        this.f4046i = i10;
        this.f4047l = obj;
        this.f4048m = obj2;
    }

    @Override // androidx.media3.common.util.Consumer
    public void accept(Object obj) {
        switch (this.f4046i) {
            case 0:
                ((MediaBrowserImplLegacy.AnonymousClass2) this.f4047l).lambda$onError$1((String) this.f4048m, (MediaBrowser.Listener) obj);
                break;
            case 7:
                ((MediaControllerImplBase) this.f4047l).lambda$onSetSessionActivity$120((PendingIntent) this.f4048m, (MediaController.Listener) obj);
                break;
            case 8:
                ((MediaControllerImplBase) this.f4047l).lambda$onError$121((SessionError) this.f4048m, (MediaController.Listener) obj);
                break;
            case 9:
                ((MediaControllerImplBase) this.f4047l).lambda$onAvailableCommandsChangedFromSession$111((SessionCommands) this.f4048m, (MediaController.Listener) obj);
                break;
            case 11:
                ((MediaControllerImplLegacy) this.f4047l).lambda$handleNewLegacyParameters$5((MediaControllerImplLegacy.LegacyPlayerInfo) this.f4048m, (MediaController.Listener) obj);
                break;
            case 17:
                ((MediaSessionStub) this.f4047l).lambda$setTrackSelectionParameters$70((TrackSelectionParameters) this.f4048m, (PlayerWrapper) obj);
                break;
            case 18:
                ((MediaSessionStub) this.f4047l).lambda$playForControllerInfo$19((MediaSession.ControllerInfo) this.f4048m, (PlayerWrapper) obj);
                break;
            default:
                ((MediaSessionStub) this.f4047l).lambda$setVideoSurface$55((Surface) this.f4048m, (PlayerWrapper) obj);
                break;
        }
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public void invoke(Object obj) {
        switch (this.f4046i) {
            case 6:
                MediaControllerImplBase.lambda$notifyPlayerInfoListenersWithReasons$80((MediaItem) this.f4047l, (Integer) this.f4048m, (Player.Listener) obj);
                break;
            default:
                MediaControllerImplLegacy.lambda$updateControllerInfo$11((MediaControllerImplLegacy.ControllerInfo) this.f4047l, (Integer) this.f4048m, (Player.Listener) obj);
                break;
        }
    }

    @Override // androidx.media3.session.MediaSessionStub.SessionTask
    public Object run(MediaSessionImpl mediaSessionImpl, MediaSession.ControllerInfo controllerInfo, int i10) {
        switch (this.f4046i) {
            case 19:
                return MediaSessionStub.lambda$setRatingWithMediaId$28((String) this.f4048m, (Rating) this.f4047l, mediaSessionImpl, controllerInfo, i10);
            case 20:
                return MediaSessionStub.lambda$handleMediaItemsWithStartPositionWhenReady$9((MediaSessionStub.SessionTask) this.f4047l, (MediaSessionStub.MediaItemsWithStartPositionPlayerTask) this.f4048m, mediaSessionImpl, controllerInfo, i10);
            default:
                return MediaSessionStub.lambda$handleMediaItemsWhenReady$6((MediaSessionStub.SessionTask) this.f4047l, (MediaSessionStub.MediaItemPlayerTask) this.f4048m, mediaSessionImpl, controllerInfo, i10);
        }
    }

    public /* synthetic */ k(String str, Rating rating) {
        this.f4046i = 19;
        this.f4048m = str;
        this.f4047l = rating;
    }

    @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
    public void run(IMediaSession iMediaSession, int i10) throws RemoteException {
        switch (this.f4046i) {
            case 1:
                ((MediaControllerImplBase) this.f4047l).lambda$setTrackSelectionParameters$76((TrackSelectionParameters) this.f4048m, iMediaSession, i10);
                break;
            case 2:
                ((MediaControllerImplBase) this.f4047l).lambda$setRating$20((Rating) this.f4048m, iMediaSession, i10);
                break;
            case 3:
                ((MediaControllerImplBase) this.f4047l).lambda$setPlaybackParameters$15((PlaybackParameters) this.f4048m, iMediaSession, i10);
                break;
            case 4:
                ((MediaControllerImplBase) this.f4047l).lambda$setVideoSurfaceWithSize$75((Surface) this.f4048m, iMediaSession, i10);
                break;
            default:
                ((MediaControllerImplBase) this.f4047l).lambda$setPlaylistMetadata$29((MediaMetadata) this.f4048m, iMediaSession, i10);
                break;
        }
    }

    @Override // androidx.media3.session.MediaControllerStub.ControllerTask
    public void run(MediaControllerImplBase mediaControllerImplBase) {
        mediaControllerImplBase.onPlayerInfoChanged((PlayerInfo) this.f4047l, (PlayerInfo.BundlingExclusions) this.f4048m);
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public void run(MediaSession.ControllerCb controllerCb, int i10) throws RemoteException {
        controllerCb.onPlayerChanged(i10, (PlayerWrapper) this.f4047l, (PlayerWrapper) this.f4048m);
    }

    @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
    public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        switch (this.f4046i) {
            case 14:
                ((MediaSessionLegacyStub) this.f4047l).lambda$dispatchSessionTaskWithSetRatingSessionCommand$21((Rating) this.f4048m, controllerInfo);
                break;
            case 15:
                ((MediaSessionLegacyStub) this.f4047l).lambda$onRemoveQueueItem$17((MediaDescriptionCompat) this.f4048m, controllerInfo);
                break;
            default:
                ((MediaSessionLegacyStub) this.f4047l).lambda$dispatchCustomCommandAsPredefinedCommand$23((CommandButton) this.f4048m, controllerInfo);
                break;
        }
    }
}

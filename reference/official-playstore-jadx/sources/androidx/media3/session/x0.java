package androidx.media3.session;

import android.os.Bundle;
import android.os.RemoteException;
import androidx.media3.common.Player;
import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaControllerImplBase;
import androidx.media3.session.MediaControllerStub;
import androidx.media3.session.MediaNotification;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;
import androidx.media3.session.MediaSessionLegacyStub;
import androidx.media3.session.legacy.MediaDescriptionCompat;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class x0 implements MediaControllerImplBase.RemoteSessionTask, MediaControllerStub.ControllerTask, MediaNotification.Provider.Callback, MediaSessionImpl.RemoteControllerTask, MediaSessionLegacyStub.SessionTask, Consumer {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4197i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f4198l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f4199m;

    public /* synthetic */ x0(int i10, SessionCommand sessionCommand, Bundle bundle) {
        this.f4197i = i10;
        this.f4198l = sessionCommand;
        this.f4199m = bundle;
    }

    @Override // androidx.media3.common.util.Consumer
    public void accept(Object obj) {
        MediaSessionStub.lambda$sendSessionResultWhenReady$2((MediaSessionImpl) this.f4198l, (MediaSession.ControllerInfo) this.f4199m, this.f4197i, (com.google.common.util.concurrent.d1) obj);
    }

    @Override // androidx.media3.session.MediaNotification.Provider.Callback
    public void onNotificationChanged(MediaNotification mediaNotification) {
        ((MediaNotificationManager) this.f4198l).lambda$updateNotification$5(this.f4197i, (MediaSession) this.f4199m, mediaNotification);
    }

    @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
    public void run(IMediaSession iMediaSession, int i10) throws RemoteException {
        ((MediaControllerImplBase) this.f4198l).lambda$addMediaItems$34(this.f4197i, (List) this.f4199m, iMediaSession, i10);
    }

    public /* synthetic */ x0(Object obj, int i10, Object obj2) {
        this.f4198l = obj;
        this.f4197i = i10;
        this.f4199m = obj2;
    }

    @Override // androidx.media3.session.MediaControllerStub.ControllerTask
    public void run(MediaControllerImplBase mediaControllerImplBase) {
        mediaControllerImplBase.onCustomCommand(this.f4197i, (SessionCommand) this.f4198l, (Bundle) this.f4199m);
    }

    public /* synthetic */ x0(Object obj, Object obj2, int i10) {
        this.f4198l = obj;
        this.f4199m = obj2;
        this.f4197i = i10;
    }

    @Override // androidx.media3.session.MediaSessionImpl.RemoteControllerTask
    public void run(MediaSession.ControllerCb controllerCb, int i10) throws RemoteException {
        controllerCb.onPositionDiscontinuity(i10, (Player.PositionInfo) this.f4198l, (Player.PositionInfo) this.f4199m, this.f4197i);
    }

    @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
    public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        ((MediaSessionLegacyStub) this.f4198l).lambda$handleOnAddQueueItem$27((MediaDescriptionCompat) this.f4199m, this.f4197i, controllerInfo);
    }
}

package androidx.media3.session;

import android.os.Bundle;
import android.os.RemoteException;
import androidx.media3.common.Player;
import androidx.media3.common.Rating;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.session.MediaBrowser;
import androidx.media3.session.MediaBrowserImplLegacy;
import androidx.media3.session.MediaController;
import androidx.media3.session.MediaControllerImplBase;
import androidx.media3.session.MediaControllerImplLegacy;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionLegacyStub;
import androidx.media3.session.MediaSessionStub;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class j implements Consumer, MediaControllerImplBase.RemoteSessionTask, ListenerSet.Event, MediaSessionLegacyStub.SessionTask, com.google.common.util.concurrent.w {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f4031i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f4032l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f4033m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f4034n;

    public /* synthetic */ j(Object obj, Object obj2, Object obj3, int i10) {
        this.f4031i = i10;
        this.f4033m = obj;
        this.f4032l = obj2;
        this.f4034n = obj3;
    }

    @Override // androidx.media3.common.util.Consumer
    public void accept(Object obj) {
        switch (this.f4031i) {
            case 0:
                ((MediaBrowserImplLegacy.AnonymousClass2) this.f4033m).lambda$onSearchResult$0((String) this.f4032l, (List) this.f4034n, (MediaBrowser.Listener) obj);
                break;
            case 1:
                ((MediaBrowserImplLegacy.SubscribeCallback) this.f4033m).lambda$onErrorInternal$0((String) this.f4032l, (Bundle) this.f4034n, (MediaBrowser.Listener) obj);
                break;
            default:
                ((MediaControllerImplLegacy.ControllerCompatCallback) this.f4033m).lambda$onSessionEvent$1((String) this.f4032l, (Bundle) this.f4034n, (MediaController.Listener) obj);
                break;
        }
    }

    @Override // com.google.common.util.concurrent.w
    public com.google.common.util.concurrent.d1 apply(Object obj) {
        switch (this.f4031i) {
            case 7:
                return MediaSessionStub.lambda$handleMediaItemsWithStartPositionWhenReady$8((MediaSessionImpl) this.f4033m, (MediaSession.ControllerInfo) this.f4032l, (MediaSessionStub.MediaItemsWithStartPositionPlayerTask) this.f4034n, (MediaSession.MediaItemsWithStartPosition) obj);
            default:
                return MediaSessionStub.lambda$handleMediaItemsWhenReady$5((MediaSessionImpl) this.f4033m, (MediaSession.ControllerInfo) this.f4032l, (MediaSessionStub.MediaItemPlayerTask) this.f4034n, (List) obj);
        }
    }

    @Override // androidx.media3.common.util.ListenerSet.Event
    public void invoke(Object obj) {
        MediaControllerImplLegacy.lambda$updateControllerInfo$10((MediaControllerImplLegacy.ControllerInfo) this.f4033m, (MediaControllerImplLegacy.ControllerInfo) this.f4032l, (Integer) this.f4034n, (Player.Listener) obj);
    }

    @Override // androidx.media3.session.MediaControllerImplBase.RemoteSessionTask
    public void run(IMediaSession iMediaSession, int i10) throws RemoteException {
        switch (this.f4031i) {
            case 2:
                ((MediaControllerImplBase) this.f4033m).lambda$setRating$19((String) this.f4032l, (Rating) this.f4034n, iMediaSession, i10);
                break;
            default:
                ((MediaControllerImplBase) this.f4033m).lambda$sendCustomCommand$21((SessionCommand) this.f4032l, (Bundle) this.f4034n, iMediaSession, i10);
                break;
        }
    }

    @Override // androidx.media3.session.MediaSessionLegacyStub.SessionTask
    public void run(MediaSession.ControllerInfo controllerInfo) throws RemoteException {
        ((MediaSessionLegacyStub) this.f4033m).lambda$onCustomAction$1((SessionCommand) this.f4032l, (Bundle) this.f4034n, controllerInfo);
    }
}

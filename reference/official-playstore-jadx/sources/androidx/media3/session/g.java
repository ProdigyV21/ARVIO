package androidx.media3.session;

import android.content.Intent;
import android.os.ResultReceiver;
import androidx.media3.common.util.Consumer;
import androidx.media3.session.MediaBrowser;
import androidx.media3.session.MediaControllerImplLegacy;
import androidx.media3.session.MediaControllerStub;
import androidx.media3.session.MediaLibraryServiceLegacyStub;
import androidx.media3.session.MediaNotification;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionLegacyStub;
import androidx.media3.session.legacy.MediaSessionCompat;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class g implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f3986i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f3987l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3988m;

    public /* synthetic */ g(Object obj, Object obj2, int i10) {
        this.f3986i = i10;
        this.f3987l = obj;
        this.f3988m = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f3986i) {
            case 0:
                ((MediaControllerHolder) this.f3987l).setController((MediaBrowser) this.f3988m);
                break;
            case 1:
                ((MediaControllerImplLegacy) this.f3987l).lambda$connectToSession$1((MediaSessionCompat.Token) this.f3988m);
                break;
            case 2:
                ((MediaControllerImplLegacy) this.f3987l).lambda$updateControllerInfo$7((MediaControllerImplLegacy.ControllerInfo) this.f3988m);
                break;
            case 3:
                MediaControllerStub.lambda$dispatchControllerTaskOnHandler$18((MediaControllerImplBase) this.f3987l, (MediaControllerStub.ControllerTask) this.f3988m);
                break;
            case 4:
                MediaLibraryServiceLegacyStub.lambda$createMediaItemsToBrowserItemsAsyncFunction$10((com.google.common.util.concurrent.q1) this.f3987l, (ArrayList) this.f3988m);
                break;
            case 5:
                MediaLibraryServiceLegacyStub.lambda$createMediaItemToBrowserItemAsyncFunction$13((com.google.common.util.concurrent.q1) this.f3987l, (com.google.common.util.concurrent.d1) this.f3988m);
                break;
            case 6:
                ((MediaLibraryServiceLegacyStub.BrowserLegacyCb) this.f3987l).lambda$onSearchResultChanged$0((ArrayList) this.f3988m);
                break;
            case 7:
                ((MediaSessionImpl) this.f3987l).setPlayerInternal(null, (PlayerWrapper) this.f3988m);
                break;
            case 8:
                ((MediaSessionImpl) this.f3987l).lambda$onPlayRequested$21((com.google.common.util.concurrent.q1) this.f3988m);
                break;
            case 9:
                MediaSessionLegacyStub.lambda$sendCustomCommandResultWhenReady$28((com.google.common.util.concurrent.d1) this.f3987l, (ResultReceiver) this.f3988m);
                break;
            case 10:
                MediaSessionLegacyStub.lambda$dispatchSessionTaskWithPlayerCommand$19((MediaSessionLegacyStub.SessionTask) this.f3987l, (MediaSession.ControllerInfo) this.f3988m);
                break;
            case 11:
                MediaSessionService.lambda$onStartCommand$2((MediaSessionImpl) this.f3987l, (Intent) this.f3988m);
                break;
            case 12:
                ((MediaSessionStub) this.f3987l).lambda$release$18((IMediaController) this.f3988m);
                break;
            case 13:
                ((MediaSessionStub) this.f3987l).lambda$flushCommandQueue$69((MediaSession.ControllerInfo) this.f3988m);
                break;
            case 14:
                ((Consumer) this.f3987l).accept((MediaBrowser.Listener) this.f3988m);
                break;
            default:
                ((MediaSessionService) this.f3987l).lambda$setMediaNotificationProvider$3((MediaNotification.Provider) this.f3988m);
                break;
        }
    }
}

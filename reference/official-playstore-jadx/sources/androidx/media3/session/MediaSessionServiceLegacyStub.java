package androidx.media3.session;

import android.os.Bundle;
import androidx.media3.common.util.ConditionVariable;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.MediaSession;
import androidx.media3.session.legacy.MediaBrowserCompat;
import androidx.media3.session.legacy.MediaBrowserServiceCompat;
import androidx.media3.session.legacy.MediaSessionCompat;
import androidx.media3.session.legacy.MediaSessionManager;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes3.dex */
class MediaSessionServiceLegacyStub extends MediaBrowserServiceCompat {
    private static final String TAG = "MSSLegacyStub";
    private final ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> connectedControllersManager;
    private final MediaSessionManager manager;
    private final MediaSessionImpl sessionImpl;

    public MediaSessionServiceLegacyStub(MediaSessionImpl mediaSessionImpl) {
        this.manager = MediaSessionManager.getSessionManager(mediaSessionImpl.getContext());
        this.sessionImpl = mediaSessionImpl;
        this.connectedControllersManager = new ConnectedControllersManager<>(mediaSessionImpl);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onGetRoot$0(AtomicReference atomicReference, MediaSession.ControllerInfo controllerInfo, ConditionVariable conditionVariable) {
        atomicReference.set(this.sessionImpl.onConnectOnHandler(controllerInfo));
        conditionVariable.open();
    }

    public MediaSession.ControllerInfo createControllerInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo, Bundle bundle) {
        return new MediaSession.ControllerInfo(remoteUserInfo, 0, 0, this.manager.isTrustedForMediaControl(remoteUserInfo), null, bundle, LegacyConversions.extractMaxCommandsForMediaItemFromRootHints(bundle), true);
    }

    public final ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> getConnectedControllersManager() {
        return this.connectedControllersManager;
    }

    public final MediaSessionManager getMediaSessionManager() {
        return this.manager;
    }

    public void initialize(MediaSessionCompat.Token token) {
        attachToBaseContext(this.sessionImpl.getContext());
        onCreate();
        setSessionToken(token);
    }

    @Override // androidx.media3.session.legacy.MediaBrowserServiceCompat
    public MediaBrowserServiceCompat.BrowserRoot onGetRoot(String str, int i10, Bundle bundle) {
        MediaSessionManager.RemoteUserInfo currentBrowserInfo = getCurrentBrowserInfo();
        if (bundle == null) {
            bundle = Bundle.EMPTY;
        }
        MediaSession.ControllerInfo controllerInfoCreateControllerInfo = createControllerInfo(currentBrowserInfo, bundle);
        AtomicReference atomicReference = new AtomicReference();
        ConditionVariable conditionVariable = new ConditionVariable();
        Util.postOrRun(this.sessionImpl.getApplicationHandler(), new c(this, atomicReference, controllerInfoCreateControllerInfo, conditionVariable, 7));
        try {
            conditionVariable.block();
            MediaSession.ConnectionResult connectionResult = (MediaSession.ConnectionResult) atomicReference.get();
            if (!connectionResult.isAccepted) {
                return null;
            }
            this.connectedControllersManager.addController(currentBrowserInfo, controllerInfoCreateControllerInfo, connectionResult.availableSessionCommands, connectionResult.availablePlayerCommands);
            return MediaUtils.defaultBrowserRoot;
        } catch (InterruptedException e5) {
            Log.e(TAG, "Couldn't get a result from onConnect", e5);
            return null;
        }
    }

    @Override // androidx.media3.session.legacy.MediaBrowserServiceCompat
    public void onLoadChildren(String str, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
        result.sendResult(null);
    }
}

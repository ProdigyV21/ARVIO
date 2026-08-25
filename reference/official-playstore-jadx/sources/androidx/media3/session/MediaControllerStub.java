package androidx.media3.session;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.media3.common.Player;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.IMediaController;
import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.PlayerInfo;
import j$.util.Objects;
import java.lang.ref.WeakReference;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
class MediaControllerStub extends IMediaController.Stub {
    private static final String TAG = "MediaControllerStub";
    public static final int VERSION_INT = 8;
    private final WeakReference<MediaControllerImplBase> controller;

    public interface ControllerTask<T extends MediaControllerImplBase> {
        void run(T t2);
    }

    public MediaControllerStub(MediaControllerImplBase mediaControllerImplBase) {
        this.controller = new WeakReference<>(mediaControllerImplBase);
    }

    private <T extends MediaControllerImplBase> void dispatchControllerTaskOnHandler(ControllerTask<T> controllerTask) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            MediaControllerImplBase mediaControllerImplBase = this.controller.get();
            if (mediaControllerImplBase == null) {
                return;
            }
            Util.postOrRun(mediaControllerImplBase.getInstance().applicationHandler, new g(mediaControllerImplBase, controllerTask, 3));
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    private int getSessionInterfaceVersion() {
        SessionToken connectedToken;
        MediaControllerImplBase mediaControllerImplBase = this.controller.get();
        if (mediaControllerImplBase == null || (connectedToken = mediaControllerImplBase.getConnectedToken()) == null) {
            return -1;
        }
        return connectedToken.getInterfaceVersion();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$dispatchControllerTaskOnHandler$18(MediaControllerImplBase mediaControllerImplBase, ControllerTask controllerTask) {
        if (mediaControllerImplBase.isReleased()) {
            return;
        }
        controllerTask.run(mediaControllerImplBase);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onDisconnected$1(MediaControllerImplBase mediaControllerImplBase) {
        MediaController mediaControllerImplBase2 = mediaControllerImplBase.getInstance();
        MediaController mediaControllerImplBase3 = mediaControllerImplBase.getInstance();
        Objects.requireNonNull(mediaControllerImplBase3);
        mediaControllerImplBase2.runOnApplicationLooper(new j3(mediaControllerImplBase3, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ CommandButton lambda$onSetCustomLayout$2(int i10, Bundle bundle) {
        return CommandButton.fromBundle(bundle, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ CommandButton lambda$onSetMediaButtonPreferences$4(int i10, Bundle bundle) {
        return CommandButton.fromBundle(bundle, i10);
    }

    private <T> void setControllerFutureResult(int i10, T t2) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            MediaControllerImplBase mediaControllerImplBase = this.controller.get();
            if (mediaControllerImplBase == null) {
                return;
            }
            mediaControllerImplBase.setFutureResult(i10, t2);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public void destroy() {
        this.controller.clear();
    }

    @Override // androidx.media3.session.IMediaController
    public void onAvailableCommandsChangedFromPlayer(int i10, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        try {
            dispatchControllerTaskOnHandler(new n1(Player.Commands.fromBundle(bundle)));
        } catch (RuntimeException e5) {
            Log.w(TAG, "Ignoring malformed Bundle for Commands", e5);
        }
    }

    @Override // androidx.media3.session.IMediaController
    public void onAvailableCommandsChangedFromSession(int i10, Bundle bundle, Bundle bundle2) {
        if (bundle == null || bundle2 == null) {
            return;
        }
        try {
            try {
                dispatchControllerTaskOnHandler(new o1(SessionCommands.fromBundle(bundle), Player.Commands.fromBundle(bundle2)));
            } catch (RuntimeException e5) {
                Log.w(TAG, "Ignoring malformed Bundle for Commands", e5);
            }
        } catch (RuntimeException e6) {
            Log.w(TAG, "Ignoring malformed Bundle for SessionCommands", e6);
        }
    }

    @Override // androidx.media3.session.IMediaController
    public void onChildrenChanged(int i10, String str, int i11, Bundle bundle) {
        MediaLibraryService.LibraryParams libraryParamsFromBundle;
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "onChildrenChanged(): Ignoring empty parentId");
            return;
        }
        if (i11 < 0) {
            androidx.fragment.app.a2.s(i11, "onChildrenChanged(): Ignoring negative itemCount: ", TAG);
            return;
        }
        if (bundle == null) {
            libraryParamsFromBundle = null;
        } else {
            try {
                libraryParamsFromBundle = MediaLibraryService.LibraryParams.fromBundle(bundle);
            } catch (RuntimeException e5) {
                Log.w(TAG, "Ignoring malformed Bundle for LibraryParams", e5);
                return;
            }
        }
        dispatchControllerTaskOnHandler(new m1(i11, str, 1, libraryParamsFromBundle));
    }

    @Override // androidx.media3.session.IMediaController
    public void onConnected(int i10, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        try {
            dispatchControllerTaskOnHandler(new k1(ConnectionState.fromBundle(bundle), 0));
        } catch (RuntimeException e5) {
            Log.w(TAG, "Malformed Bundle for ConnectionResult. Disconnected from the session.", e5);
            onDisconnected(i10);
        }
    }

    @Override // androidx.media3.session.IMediaController
    public void onCustomCommand(int i10, Bundle bundle, Bundle bundle2) {
        if (bundle == null || bundle2 == null) {
            Log.w(TAG, "Ignoring custom command with null args.");
            return;
        }
        try {
            dispatchControllerTaskOnHandler(new x0(i10, SessionCommand.fromBundle(bundle), bundle2));
        } catch (RuntimeException e5) {
            Log.w(TAG, "Ignoring malformed Bundle for SessionCommand", e5);
        }
    }

    @Override // androidx.media3.session.IMediaController
    public void onCustomCommandProgressUpdate(int i10, Bundle bundle, Bundle bundle2, Bundle bundle3) throws RemoteException {
        if (bundle == null || bundle2 == null) {
            Log.w(TAG, "Ignoring custom command progress update with null args.");
            return;
        }
        try {
            dispatchControllerTaskOnHandler(new j1(i10, SessionCommand.fromBundle(bundle), bundle2, bundle3));
        } catch (RuntimeException e5) {
            Log.w(TAG, "Ignoring malformed Bundle for SessionCommand", e5);
        }
    }

    @Override // androidx.media3.session.IMediaController
    public void onDisconnected(int i10) {
        dispatchControllerTaskOnHandler(new v(3));
    }

    @Override // androidx.media3.session.IMediaController
    public void onError(int i10, Bundle bundle) throws RemoteException {
        try {
            dispatchControllerTaskOnHandler(new l1(i10, SessionError.fromBundle(bundle), 2));
        } catch (RuntimeException e5) {
            Log.w(TAG, "Ignoring malformed Bundle for SessionError", e5);
        }
    }

    @Override // androidx.media3.session.IMediaController
    public void onExtrasChanged(int i10, Bundle bundle) {
        if (bundle == null) {
            Log.w(TAG, "Ignoring null Bundle for extras");
        } else {
            dispatchControllerTaskOnHandler(new i1(0, bundle));
        }
    }

    @Override // androidx.media3.session.IMediaController
    public void onLibraryResult(int i10, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        try {
            setControllerFutureResult(i10, LibraryResult.fromUnknownBundle(bundle));
        } catch (RuntimeException e5) {
            Log.w(TAG, "Ignoring malformed Bundle for LibraryResult", e5);
        }
    }

    @Override // androidx.media3.session.IMediaController
    public void onPeriodicSessionPositionInfoChanged(int i10, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        try {
            dispatchControllerTaskOnHandler(new k1(SessionPositionInfo.fromBundle(bundle), 1));
        } catch (RuntimeException e5) {
            Log.w(TAG, "Ignoring malformed Bundle for SessionPositionInfo", e5);
        }
    }

    @Override // androidx.media3.session.IMediaController
    @Deprecated
    public void onPlayerInfoChanged(int i10, Bundle bundle, boolean z) {
        onPlayerInfoChangedWithExclusions(i10, bundle, new PlayerInfo.BundlingExclusions(z, true).toBundle());
    }

    @Override // androidx.media3.session.IMediaController
    public void onPlayerInfoChangedWithExclusions(int i10, Bundle bundle, Bundle bundle2) {
        if (bundle == null || bundle2 == null) {
            return;
        }
        try {
            int sessionInterfaceVersion = getSessionInterfaceVersion();
            if (sessionInterfaceVersion == -1) {
                return;
            }
            try {
                dispatchControllerTaskOnHandler(new k(PlayerInfo.fromBundle(bundle, sessionInterfaceVersion), PlayerInfo.BundlingExclusions.fromBundle(bundle2), 12));
            } catch (RuntimeException e5) {
                Log.w(TAG, "Ignoring malformed Bundle for BundlingExclusions", e5);
            }
        } catch (RuntimeException e6) {
            Log.w(TAG, "Ignoring malformed Bundle for PlayerInfo", e6);
        }
    }

    @Override // androidx.media3.session.IMediaController
    public void onRenderedFirstFrame(int i10) {
        dispatchControllerTaskOnHandler(new v(2));
    }

    @Override // androidx.media3.session.IMediaController
    public void onSearchResultChanged(int i10, String str, int i11, Bundle bundle) throws RuntimeException {
        MediaLibraryService.LibraryParams libraryParamsFromBundle;
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "onSearchResultChanged(): Ignoring empty query");
            return;
        }
        if (i11 < 0) {
            androidx.fragment.app.a2.s(i11, "onSearchResultChanged(): Ignoring negative itemCount: ", TAG);
            return;
        }
        if (bundle == null) {
            libraryParamsFromBundle = null;
        } else {
            try {
                libraryParamsFromBundle = MediaLibraryService.LibraryParams.fromBundle(bundle);
            } catch (RuntimeException e5) {
                Log.w(TAG, "Ignoring malformed Bundle for LibraryParams", e5);
                return;
            }
        }
        dispatchControllerTaskOnHandler(new m1(i11, str, 0, libraryParamsFromBundle));
    }

    @Override // androidx.media3.session.IMediaController
    public void onSessionActivityChanged(int i10, PendingIntent pendingIntent) throws RemoteException {
        dispatchControllerTaskOnHandler(new l1(i10, pendingIntent, 3));
    }

    @Override // androidx.media3.session.IMediaController
    public void onSessionResult(int i10, Bundle bundle) {
        if (bundle == null) {
            return;
        }
        try {
            setControllerFutureResult(i10, SessionResult.fromBundle(bundle));
        } catch (RuntimeException e5) {
            Log.w(TAG, "Ignoring malformed Bundle for SessionResult", e5);
        }
    }

    @Override // androidx.media3.session.IMediaController
    public void onSetCustomLayout(int i10, List<Bundle> list) {
        if (list == null) {
            return;
        }
        try {
            int sessionInterfaceVersion = getSessionInterfaceVersion();
            if (sessionInterfaceVersion == -1) {
                return;
            }
            dispatchControllerTaskOnHandler(new l1(i10, BundleCollectionUtil.fromBundleList(new e(sessionInterfaceVersion, 4), list), 0));
        } catch (RuntimeException e5) {
            Log.w(TAG, "Ignoring malformed Bundle for CommandButton", e5);
        }
    }

    @Override // androidx.media3.session.IMediaController
    public void onSetMediaButtonPreferences(int i10, List<Bundle> list) {
        if (list == null) {
            return;
        }
        try {
            int sessionInterfaceVersion = getSessionInterfaceVersion();
            if (sessionInterfaceVersion == -1) {
                return;
            }
            dispatchControllerTaskOnHandler(new l1(i10, BundleCollectionUtil.fromBundleList(new e(sessionInterfaceVersion, 3), list), 1));
        } catch (RuntimeException e5) {
            Log.w(TAG, "Ignoring malformed Bundle for CommandButton", e5);
        }
    }

    @Override // androidx.media3.session.IMediaController
    public void onSurfaceSizeChanged(int i10, int i11, int i12) {
        dispatchControllerTaskOnHandler(new u(i11, i12, 1));
    }
}

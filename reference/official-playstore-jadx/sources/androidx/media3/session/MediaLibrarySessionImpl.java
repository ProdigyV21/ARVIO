package androidx.media3.session;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Player;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionImpl;
import androidx.media3.session.legacy.MediaSessionCompat;
import com.google.common.collect.o4;
import j$.util.Objects;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes3.dex */
class MediaLibrarySessionImpl extends MediaSessionImpl {
    private static final String RECENT_LIBRARY_ROOT_MEDIA_ID = "androidx.media3.session.recent.root";
    private final MediaLibraryService.MediaLibrarySession.Callback callback;
    private final com.google.common.collect.w0 controllerToSubscribedParentIds;
    private final MediaLibraryService.MediaLibrarySession instance;
    private final int libraryErrorReplicationMode;
    private final com.google.common.collect.w0 parentIdToSubscribedControllers;

    public MediaLibrarySessionImpl(MediaLibraryService.MediaLibrarySession mediaLibrarySession, Context context, String str, Player player, PendingIntent pendingIntent, com.google.common.collect.h1 h1Var, com.google.common.collect.h1 h1Var2, com.google.common.collect.h1 h1Var3, MediaLibraryService.MediaLibrarySession.Callback callback, Bundle bundle, Bundle bundle2, androidx.media3.common.util.BitmapLoader bitmapLoader, boolean z, boolean z5, int i10) {
        super(mediaLibrarySession, context, str, player, pendingIntent, h1Var, h1Var2, h1Var3, callback, bundle, bundle2, bitmapLoader, z, z5, false);
        this.instance = mediaLibrarySession;
        this.callback = callback;
        this.libraryErrorReplicationMode = i10;
        this.parentIdToSubscribedControllers = new com.google.common.collect.w0();
        this.controllerToSubscribedParentIds = new com.google.common.collect.w0();
    }

    private com.google.common.util.concurrent.d1<LibraryResult<com.google.common.collect.h1>> getRecentMediaItemAtDeviceBootTime(MediaSession.ControllerInfo controllerInfo, final MediaLibraryService.LibraryParams libraryParams) {
        final com.google.common.util.concurrent.q1 q1Var = new com.google.common.util.concurrent.q1();
        if (isMediaNotificationControllerConnected()) {
            controllerInfo = getMediaNotificationControllerInfo();
            controllerInfo.getClass();
        }
        xc.d.G(this.callback.onPlaybackResumption(this.instance, controllerInfo, false), new com.google.common.util.concurrent.v0<MediaSession.MediaItemsWithStartPosition>() { // from class: androidx.media3.session.MediaLibrarySessionImpl.1
            @Override // com.google.common.util.concurrent.v0
            public void onFailure(Throwable th) {
                q1Var.set(LibraryResult.ofError(-1, libraryParams));
                Log.e(MediaSessionImpl.TAG, "Failed fetching recent media item at boot time: " + th.getMessage(), th);
            }

            @Override // com.google.common.util.concurrent.v0
            public void onSuccess(MediaSession.MediaItemsWithStartPosition mediaItemsWithStartPosition) {
                if (mediaItemsWithStartPosition.mediaItems.isEmpty()) {
                    q1Var.set(LibraryResult.ofError(-2, libraryParams));
                } else {
                    q1Var.set(LibraryResult.ofItemList(com.google.common.collect.h1.s((MediaItem) mediaItemsWithStartPosition.mediaItems.get(Math.max(0, Math.min(mediaItemsWithStartPosition.startIndex, mediaItemsWithStartPosition.mediaItems.size() - 1)))), libraryParams));
                }
            }
        }, com.google.common.util.concurrent.s0.f14233i);
        return q1Var;
    }

    private boolean isReplicationErrorCode(int i10) {
        return i10 == -102 || i10 == -105;
    }

    private boolean isSubscribed(MediaSession.ControllerCb controllerCb, String str) {
        return this.controllerToSubscribedParentIds.b(controllerCb, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$notifyChildrenChanged$4(String str, int i10, MediaLibraryService.LibraryParams libraryParams, MediaSession.ControllerCb controllerCb, int i11) throws RemoteException {
        if (isSubscribed(controllerCb, str)) {
            controllerCb.onChildrenChanged(i11, str, i10, libraryParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onGetChildrenOnHandler$0(com.google.common.util.concurrent.d1 d1Var, MediaSession.ControllerInfo controllerInfo, int i10) {
        LibraryResult<?> libraryResult = (LibraryResult) tryGetFutureResult(d1Var);
        if (libraryResult != null) {
            maybeUpdateLegacyErrorState(controllerInfo, libraryResult);
            verifyResultItems(libraryResult, i10);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onGetItemOnHandler$1(com.google.common.util.concurrent.d1 d1Var, MediaSession.ControllerInfo controllerInfo) {
        LibraryResult<?> libraryResult = (LibraryResult) tryGetFutureResult(d1Var);
        if (libraryResult != null) {
            maybeUpdateLegacyErrorState(controllerInfo, libraryResult);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onGetSearchResultOnHandler$6(com.google.common.util.concurrent.d1 d1Var, MediaSession.ControllerInfo controllerInfo, int i10) {
        LibraryResult<?> libraryResult = (LibraryResult) tryGetFutureResult(d1Var);
        if (libraryResult != null) {
            maybeUpdateLegacyErrorState(controllerInfo, libraryResult);
            verifyResultItems(libraryResult, i10);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onSearchOnHandler$5(com.google.common.util.concurrent.d1 d1Var, MediaSession.ControllerInfo controllerInfo) {
        LibraryResult<?> libraryResult = (LibraryResult) tryGetFutureResult(d1Var);
        if (libraryResult != null) {
            maybeUpdateLegacyErrorState(controllerInfo, libraryResult);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onSubscribeOnHandler$2(com.google.common.util.concurrent.d1 d1Var, MediaSession.ControllerInfo controllerInfo, String str) {
        LibraryResult libraryResult = (LibraryResult) tryGetFutureResult(d1Var);
        if (libraryResult == null || libraryResult.resultCode != 0) {
            lambda$onUnsubscribeOnHandler$3(controllerInfo, str);
        }
    }

    private void maybeUpdateLegacyErrorState(MediaSession.ControllerInfo controllerInfo, LibraryResult<?> libraryResult) {
        if (this.libraryErrorReplicationMode == 0 || controllerInfo.getControllerVersion() != 0) {
            return;
        }
        if (isReplicationErrorCode(libraryResult.resultCode)) {
            getMediaSessionLegacyStub().setLegacyError(libraryResult, this.libraryErrorReplicationMode == 1);
        }
        if (libraryResult.resultCode == 0) {
            getMediaSessionLegacyStub().clearLegacyErrorStatus();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postOrRunOnApplicationHandler(Runnable runnable) {
        Util.postOrRun(getApplicationHandler(), runnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: removeSubscription, reason: merged with bridge method [inline-methods] */
    public void lambda$onUnsubscribeOnHandler$3(MediaSession.ControllerInfo controllerInfo, String str) {
        MediaSession.ControllerCb controllerCb = controllerInfo.getControllerCb();
        controllerCb.getClass();
        this.parentIdToSubscribedControllers.remove(str, controllerInfo);
        this.controllerToSubscribedParentIds.remove(controllerCb, str);
    }

    private static <T> T tryGetFutureResult(Future<T> future) {
        ac.b.s(future.isDone());
        try {
            return future.get();
        } catch (InterruptedException | CancellationException | ExecutionException e5) {
            Log.w(MediaSessionImpl.TAG, "Library operation failed", e5);
            return null;
        }
    }

    private static void verifyResultItems(LibraryResult<com.google.common.collect.h1> libraryResult, int i10) {
        if (libraryResult.resultCode == 0) {
            com.google.common.collect.h1 h1Var = libraryResult.value;
            h1Var.getClass();
            if (h1Var.size() <= i10) {
                return;
            }
            throw new IllegalStateException("Invalid size=" + h1Var.size() + ", pageSize=" + i10);
        }
    }

    public void clearReplicatedLibraryError() {
        getMediaSessionLegacyStub().clearLegacyErrorStatus();
    }

    @Override // androidx.media3.session.MediaSessionImpl
    public MediaSessionServiceLegacyStub createLegacyBrowserService(MediaSessionCompat.Token token) {
        MediaLibraryServiceLegacyStub mediaLibraryServiceLegacyStub = new MediaLibraryServiceLegacyStub(this);
        mediaLibraryServiceLegacyStub.initialize(token);
        return mediaLibraryServiceLegacyStub;
    }

    @Override // androidx.media3.session.MediaSessionImpl
    public void dispatchRemoteControllerTaskWithoutReturn(MediaSessionImpl.RemoteControllerTask remoteControllerTask) {
        super.dispatchRemoteControllerTaskWithoutReturn(remoteControllerTask);
        MediaLibraryServiceLegacyStub legacyBrowserService = getLegacyBrowserService();
        if (legacyBrowserService != null) {
            try {
                remoteControllerTask.run(legacyBrowserService.getBrowserLegacyCbForBroadcast(), 0);
            } catch (RemoteException e5) {
                Log.e(MediaSessionImpl.TAG, "Exception in using media1 API", e5);
            }
        }
    }

    @Override // androidx.media3.session.MediaSessionImpl
    public List<MediaSession.ControllerInfo> getConnectedControllers() {
        List<MediaSession.ControllerInfo> connectedControllers = super.getConnectedControllers();
        MediaLibraryServiceLegacyStub legacyBrowserService = getLegacyBrowserService();
        if (legacyBrowserService == null) {
            return connectedControllers;
        }
        com.google.common.collect.h1 connectedControllers2 = legacyBrowserService.getConnectedControllersManager().getConnectedControllers();
        com.google.common.collect.e1 e1VarM = com.google.common.collect.h1.m(connectedControllers2.size() + connectedControllers.size());
        e1VarM.d(connectedControllers);
        e1VarM.d(connectedControllers2);
        return e1VarM.f();
    }

    public com.google.common.collect.h1 getSubscribedControllers(String str) {
        return com.google.common.collect.h1.n(this.parentIdToSubscribedControllers.l(str));
    }

    @Override // androidx.media3.session.MediaSessionImpl
    public boolean isConnected(MediaSession.ControllerInfo controllerInfo) {
        if (super.isConnected(controllerInfo)) {
            return true;
        }
        MediaLibraryServiceLegacyStub legacyBrowserService = getLegacyBrowserService();
        return legacyBrowserService != null && legacyBrowserService.getConnectedControllersManager().isConnected(controllerInfo);
    }

    public void notifyChildrenChanged(String str, int i10, MediaLibraryService.LibraryParams libraryParams) {
        List<MediaSession.ControllerInfo> connectedControllers = this.instance.getConnectedControllers();
        for (int i11 = 0; i11 < connectedControllers.size(); i11++) {
            notifyChildrenChanged(connectedControllers.get(i11), str, i10, libraryParams);
        }
    }

    public void notifySearchResultChanged(MediaSession.ControllerInfo controllerInfo, String str, int i10, MediaLibraryService.LibraryParams libraryParams) {
        if (isMediaNotificationControllerConnected() && isMediaNotificationController(controllerInfo) && (controllerInfo = getSystemUiControllerInfo()) == null) {
            return;
        }
        dispatchRemoteControllerTaskWithoutReturn(controllerInfo, new m1(i10, str, 2, libraryParams));
    }

    @Override // androidx.media3.session.MediaSessionImpl
    public void onDisconnectedOnHandler(MediaSession.ControllerInfo controllerInfo) {
        MediaSession.ControllerCb controllerCb = controllerInfo.getControllerCb();
        controllerCb.getClass();
        o4 o4VarG = com.google.common.collect.w1.m(this.controllerToSubscribedParentIds.l(controllerCb)).iterator();
        while (o4VarG.hasNext()) {
            lambda$onUnsubscribeOnHandler$3(controllerInfo, (String) o4VarG.next());
        }
        super.onDisconnectedOnHandler(controllerInfo);
    }

    public com.google.common.util.concurrent.d1<LibraryResult<com.google.common.collect.h1>> onGetChildrenOnHandler(MediaSession.ControllerInfo controllerInfo, String str, int i10, int i11, MediaLibraryService.LibraryParams libraryParams) {
        if (Objects.equals(str, RECENT_LIBRARY_ROOT_MEDIA_ID)) {
            return !canResumePlaybackOnStart() ? xc.d.s0(LibraryResult.ofError(-6)) : getPlayerWrapper().getPlaybackState() == 1 ? getRecentMediaItemAtDeviceBootTime(controllerInfo, libraryParams) : xc.d.s0(LibraryResult.ofItemList(com.google.common.collect.h1.s(new MediaItem.Builder().setMediaId("androidx.media3.session.recent.item").setMediaMetadata(new MediaMetadata.Builder().setIsBrowsable(Boolean.FALSE).setIsPlayable(Boolean.TRUE).build()).build()), libraryParams));
        }
        com.google.common.util.concurrent.d1<LibraryResult<com.google.common.collect.h1>> d1VarOnGetChildren = this.callback.onGetChildren(this.instance, resolveControllerInfoForCallback(controllerInfo), str, i10, i11, libraryParams);
        d1VarOnGetChildren.addListener(new u1(this, d1VarOnGetChildren, controllerInfo, i11, 0), new o(this, 1));
        return d1VarOnGetChildren;
    }

    public com.google.common.util.concurrent.d1<LibraryResult<MediaItem>> onGetItemOnHandler(MediaSession.ControllerInfo controllerInfo, String str) {
        com.google.common.util.concurrent.d1<LibraryResult<MediaItem>> d1VarOnGetItem = this.callback.onGetItem(this.instance, resolveControllerInfoForCallback(controllerInfo), str);
        d1VarOnGetItem.addListener(new v1(this, d1VarOnGetItem, controllerInfo, 0), new o(this, 1));
        return d1VarOnGetItem;
    }

    public com.google.common.util.concurrent.d1<LibraryResult<MediaItem>> onGetLibraryRootOnHandler(MediaSession.ControllerInfo controllerInfo, MediaLibraryService.LibraryParams libraryParams) {
        return (libraryParams != null && libraryParams.isRecent && isSystemUiController(controllerInfo)) ? !canResumePlaybackOnStart() ? xc.d.s0(LibraryResult.ofError(-6)) : xc.d.s0(LibraryResult.ofItem(new MediaItem.Builder().setMediaId(RECENT_LIBRARY_ROOT_MEDIA_ID).setMediaMetadata(new MediaMetadata.Builder().setIsBrowsable(Boolean.TRUE).setIsPlayable(Boolean.FALSE).build()).build(), libraryParams)) : this.callback.onGetLibraryRoot(this.instance, resolveControllerInfoForCallback(controllerInfo), libraryParams);
    }

    public com.google.common.util.concurrent.d1<LibraryResult<com.google.common.collect.h1>> onGetSearchResultOnHandler(MediaSession.ControllerInfo controllerInfo, String str, int i10, int i11, MediaLibraryService.LibraryParams libraryParams) {
        com.google.common.util.concurrent.d1<LibraryResult<com.google.common.collect.h1>> d1VarOnGetSearchResult = this.callback.onGetSearchResult(this.instance, resolveControllerInfoForCallback(controllerInfo), str, i10, i11, libraryParams);
        d1VarOnGetSearchResult.addListener(new u1(this, d1VarOnGetSearchResult, controllerInfo, i11, 1), new o(this, 1));
        return d1VarOnGetSearchResult;
    }

    public com.google.common.util.concurrent.d1<LibraryResult<Void>> onSearchOnHandler(MediaSession.ControllerInfo controllerInfo, String str, MediaLibraryService.LibraryParams libraryParams) {
        com.google.common.util.concurrent.d1<LibraryResult<Void>> d1VarOnSearch = this.callback.onSearch(this.instance, resolveControllerInfoForCallback(controllerInfo), str, libraryParams);
        int i10 = 1;
        d1VarOnSearch.addListener(new v1(this, d1VarOnSearch, controllerInfo, i10), new o(this, i10));
        return d1VarOnSearch;
    }

    public com.google.common.util.concurrent.d1<LibraryResult<Void>> onSubscribeOnHandler(MediaSession.ControllerInfo controllerInfo, String str, MediaLibraryService.LibraryParams libraryParams) {
        MediaSession.ControllerCb controllerCb = controllerInfo.getControllerCb();
        controllerCb.getClass();
        this.controllerToSubscribedParentIds.put(controllerCb, str);
        this.parentIdToSubscribedControllers.put(str, controllerInfo);
        com.google.common.util.concurrent.d1<LibraryResult<Void>> d1VarOnSubscribe = this.callback.onSubscribe(this.instance, resolveControllerInfoForCallback(controllerInfo), str, libraryParams);
        ac.b.p(d1VarOnSubscribe, "onSubscribe must return non-null future");
        d1VarOnSubscribe.addListener(new c(this, d1VarOnSubscribe, controllerInfo, str, 3), new o(this, 1));
        return d1VarOnSubscribe;
    }

    public com.google.common.util.concurrent.d1<LibraryResult<Void>> onUnsubscribeOnHandler(MediaSession.ControllerInfo controllerInfo, String str) {
        com.google.common.util.concurrent.d1<LibraryResult<Void>> d1VarOnUnsubscribe = this.callback.onUnsubscribe(this.instance, resolveControllerInfoForCallback(controllerInfo), str);
        d1VarOnUnsubscribe.addListener(new s1(this, controllerInfo, str, 2), new o(this, 1));
        return d1VarOnUnsubscribe;
    }

    @Override // androidx.media3.session.MediaSessionImpl
    public MediaLibraryServiceLegacyStub getLegacyBrowserService() {
        return (MediaLibraryServiceLegacyStub) super.getLegacyBrowserService();
    }

    public void notifyChildrenChanged(MediaSession.ControllerInfo controllerInfo, String str, int i10, MediaLibraryService.LibraryParams libraryParams) {
        if (isMediaNotificationControllerConnected() && isMediaNotificationController(controllerInfo) && (controllerInfo = getSystemUiControllerInfo()) == null) {
            return;
        }
        dispatchRemoteControllerTaskWithoutReturn(controllerInfo, new w0(this, str, i10, libraryParams));
    }
}

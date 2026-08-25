package androidx.media3.session;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.RemoteException;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Log;
import androidx.media3.session.MediaBrowser;
import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.SequencedFutureManager;

/* JADX INFO: loaded from: classes3.dex */
class MediaBrowserImplBase extends MediaControllerImplBase implements MediaBrowser.MediaBrowserImpl {
    private final MediaBrowser instance;

    public interface RemoteLibrarySessionTask {
        void run(IMediaSession iMediaSession, int i10) throws RemoteException;
    }

    public MediaBrowserImplBase(Context context, MediaBrowser mediaBrowser, SessionToken sessionToken, Bundle bundle, Looper looper) {
        super(context, mediaBrowser, sessionToken, bundle, looper);
        this.instance = mediaBrowser;
    }

    private <V> com.google.common.util.concurrent.d1<LibraryResult<V>> dispatchRemoteLibrarySessionTask(int i10, RemoteLibrarySessionTask remoteLibrarySessionTask) {
        IMediaSession sessionInterfaceWithSessionCommandIfAble = getSessionInterfaceWithSessionCommandIfAble(i10);
        if (sessionInterfaceWithSessionCommandIfAble == null) {
            return xc.d.s0(LibraryResult.ofError(-4));
        }
        notifyPlatformControllerAboutMedia3ChangeRequest();
        SequencedFutureManager.SequencedFuture sequencedFutureCreateSequencedFuture = this.sequencedFutureManager.createSequencedFuture(LibraryResult.ofError(1));
        try {
            remoteLibrarySessionTask.run(sessionInterfaceWithSessionCommandIfAble, sequencedFutureCreateSequencedFuture.getSequenceNumber());
            return sequencedFutureCreateSequencedFuture;
        } catch (RemoteException e5) {
            Log.w(MediaControllerImplBase.TAG, "Cannot connect to the service or the session is gone", e5);
            this.sequencedFutureManager.setFutureResult(sequencedFutureCreateSequencedFuture.getSequenceNumber(), LibraryResult.ofError(-100));
            return sequencedFutureCreateSequencedFuture;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$notifyChildrenChanged$1(String str, int i10, MediaLibraryService.LibraryParams libraryParams, MediaBrowser.Listener listener) {
        listener.onChildrenChanged(getInstance(), str, i10, libraryParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$notifySearchResultChanged$0(String str, int i10, MediaLibraryService.LibraryParams libraryParams, MediaBrowser.Listener listener) {
        listener.onSearchResultChanged(getInstance(), str, i10, libraryParams);
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public com.google.common.util.concurrent.d1<LibraryResult<com.google.common.collect.h1>> getChildren(final String str, final int i10, final int i11, final MediaLibraryService.LibraryParams libraryParams) {
        return dispatchRemoteLibrarySessionTask(SessionCommand.COMMAND_CODE_LIBRARY_GET_CHILDREN, new RemoteLibrarySessionTask() { // from class: androidx.media3.session.MediaBrowserImplBase.4
            @Override // androidx.media3.session.MediaBrowserImplBase.RemoteLibrarySessionTask
            public void run(IMediaSession iMediaSession, int i12) throws RemoteException {
                MediaControllerStub mediaControllerStub = MediaBrowserImplBase.this.controllerStub;
                String str2 = str;
                int i13 = i10;
                int i14 = i11;
                MediaLibraryService.LibraryParams libraryParams2 = libraryParams;
                iMediaSession.getChildren(mediaControllerStub, i12, str2, i13, i14, libraryParams2 == null ? null : libraryParams2.toBundle());
            }
        });
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public com.google.common.util.concurrent.d1<LibraryResult<MediaItem>> getItem(final String str) {
        return dispatchRemoteLibrarySessionTask(SessionCommand.COMMAND_CODE_LIBRARY_GET_ITEM, new RemoteLibrarySessionTask() { // from class: androidx.media3.session.MediaBrowserImplBase.5
            @Override // androidx.media3.session.MediaBrowserImplBase.RemoteLibrarySessionTask
            public void run(IMediaSession iMediaSession, int i10) throws RemoteException {
                iMediaSession.getItem(MediaBrowserImplBase.this.controllerStub, i10, str);
            }
        });
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public com.google.common.util.concurrent.d1<LibraryResult<MediaItem>> getLibraryRoot(final MediaLibraryService.LibraryParams libraryParams) {
        return dispatchRemoteLibrarySessionTask(50000, new RemoteLibrarySessionTask() { // from class: androidx.media3.session.MediaBrowserImplBase.1
            @Override // androidx.media3.session.MediaBrowserImplBase.RemoteLibrarySessionTask
            public void run(IMediaSession iMediaSession, int i10) throws RemoteException {
                MediaControllerStub mediaControllerStub = MediaBrowserImplBase.this.controllerStub;
                MediaLibraryService.LibraryParams libraryParams2 = libraryParams;
                iMediaSession.getLibraryRoot(mediaControllerStub, i10, libraryParams2 == null ? null : libraryParams2.toBundle());
            }
        });
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public com.google.common.util.concurrent.d1<LibraryResult<com.google.common.collect.h1>> getSearchResult(final String str, final int i10, final int i11, final MediaLibraryService.LibraryParams libraryParams) {
        return dispatchRemoteLibrarySessionTask(SessionCommand.COMMAND_CODE_LIBRARY_GET_SEARCH_RESULT, new RemoteLibrarySessionTask() { // from class: androidx.media3.session.MediaBrowserImplBase.7
            @Override // androidx.media3.session.MediaBrowserImplBase.RemoteLibrarySessionTask
            public void run(IMediaSession iMediaSession, int i12) throws RemoteException {
                MediaControllerStub mediaControllerStub = MediaBrowserImplBase.this.controllerStub;
                String str2 = str;
                int i13 = i10;
                int i14 = i11;
                MediaLibraryService.LibraryParams libraryParams2 = libraryParams;
                iMediaSession.getSearchResult(mediaControllerStub, i12, str2, i13, i14, libraryParams2 == null ? null : libraryParams2.toBundle());
            }
        });
    }

    public void notifyChildrenChanged(String str, int i10, MediaLibraryService.LibraryParams libraryParams) {
        if (isConnected()) {
            getInstance().notifyBrowserListener(new i(this, str, i10, 1, libraryParams));
        }
    }

    public void notifySearchResultChanged(String str, int i10, MediaLibraryService.LibraryParams libraryParams) {
        if (isConnected()) {
            getInstance().notifyBrowserListener(new i(this, str, i10, 0, libraryParams));
        }
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public com.google.common.util.concurrent.d1<LibraryResult<Void>> search(final String str, final MediaLibraryService.LibraryParams libraryParams) {
        return dispatchRemoteLibrarySessionTask(SessionCommand.COMMAND_CODE_LIBRARY_SEARCH, new RemoteLibrarySessionTask() { // from class: androidx.media3.session.MediaBrowserImplBase.6
            @Override // androidx.media3.session.MediaBrowserImplBase.RemoteLibrarySessionTask
            public void run(IMediaSession iMediaSession, int i10) throws RemoteException {
                MediaControllerStub mediaControllerStub = MediaBrowserImplBase.this.controllerStub;
                String str2 = str;
                MediaLibraryService.LibraryParams libraryParams2 = libraryParams;
                iMediaSession.search(mediaControllerStub, i10, str2, libraryParams2 == null ? null : libraryParams2.toBundle());
            }
        });
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public com.google.common.util.concurrent.d1<LibraryResult<Void>> subscribe(final String str, final MediaLibraryService.LibraryParams libraryParams) {
        return dispatchRemoteLibrarySessionTask(SessionCommand.COMMAND_CODE_LIBRARY_SUBSCRIBE, new RemoteLibrarySessionTask() { // from class: androidx.media3.session.MediaBrowserImplBase.2
            @Override // androidx.media3.session.MediaBrowserImplBase.RemoteLibrarySessionTask
            public void run(IMediaSession iMediaSession, int i10) throws RemoteException {
                MediaControllerStub mediaControllerStub = MediaBrowserImplBase.this.controllerStub;
                String str2 = str;
                MediaLibraryService.LibraryParams libraryParams2 = libraryParams;
                iMediaSession.subscribe(mediaControllerStub, i10, str2, libraryParams2 == null ? null : libraryParams2.toBundle());
            }
        });
    }

    @Override // androidx.media3.session.MediaBrowser.MediaBrowserImpl
    public com.google.common.util.concurrent.d1<LibraryResult<Void>> unsubscribe(final String str) {
        return dispatchRemoteLibrarySessionTask(SessionCommand.COMMAND_CODE_LIBRARY_UNSUBSCRIBE, new RemoteLibrarySessionTask() { // from class: androidx.media3.session.MediaBrowserImplBase.3
            @Override // androidx.media3.session.MediaBrowserImplBase.RemoteLibrarySessionTask
            public void run(IMediaSession iMediaSession, int i10) throws RemoteException {
                iMediaSession.unsubscribe(MediaBrowserImplBase.this.controllerStub, i10, str);
            }
        });
    }

    @Override // androidx.media3.session.MediaControllerImplBase
    public MediaBrowser getInstance() {
        return this.instance;
    }
}

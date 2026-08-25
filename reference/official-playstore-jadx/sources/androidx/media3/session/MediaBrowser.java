package androidx.media3.session;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.Consumer;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSourceBitmapLoader;
import androidx.media3.session.MediaController;
import androidx.media3.session.MediaLibraryService;
import java.util.List;
import org.checkerframework.checker.initialization.qual.NotOnlyInitialized;

/* JADX INFO: loaded from: classes3.dex */
public final class MediaBrowser extends MediaController {
    private static final String WRONG_THREAD_ERROR_MESSAGE = "MediaBrowser method is called from a wrong thread. See javadoc of MediaController for details.";

    @NotOnlyInitialized
    private MediaBrowserImpl impl;

    public static final class Builder {
        private Looper applicationLooper;
        private androidx.media3.common.util.BitmapLoader bitmapLoader;
        private Bundle connectionHints;
        private final Context context;
        private Listener listener;
        private int maxCommandsForMediaItems;
        private long platformSessionCallbackAggregationTimeoutMs;
        private final SessionToken token;

        public Builder(Context context, SessionToken sessionToken) {
            context.getClass();
            this.context = context;
            sessionToken.getClass();
            this.token = sessionToken;
            this.connectionHints = Bundle.EMPTY;
            this.listener = new Listener() { // from class: androidx.media3.session.MediaBrowser.Builder.1
                @Override // androidx.media3.session.MediaController.Listener
                public final /* synthetic */ void onAvailableSessionCommandsChanged(MediaController mediaController, SessionCommands sessionCommands) {
                    n.a(this, mediaController, sessionCommands);
                }

                @Override // androidx.media3.session.MediaBrowser.Listener
                public final /* synthetic */ void onChildrenChanged(MediaBrowser mediaBrowser, String str, int i10, MediaLibraryService.LibraryParams libraryParams) {
                    h.a(this, mediaBrowser, str, i10, libraryParams);
                }

                @Override // androidx.media3.session.MediaController.Listener
                public final /* synthetic */ com.google.common.util.concurrent.d1 onCustomCommand(MediaController mediaController, SessionCommand sessionCommand, Bundle bundle) {
                    return n.b(this, mediaController, sessionCommand, bundle);
                }

                @Override // androidx.media3.session.MediaController.Listener
                public final /* synthetic */ void onCustomLayoutChanged(MediaController mediaController, List list) {
                    n.c(this, mediaController, list);
                }

                @Override // androidx.media3.session.MediaController.Listener
                public final /* synthetic */ void onDisconnected(MediaController mediaController) {
                    n.d(this, mediaController);
                }

                @Override // androidx.media3.session.MediaController.Listener
                public final /* synthetic */ void onError(MediaController mediaController, SessionError sessionError) {
                    n.e(this, mediaController, sessionError);
                }

                @Override // androidx.media3.session.MediaController.Listener
                public final /* synthetic */ void onExtrasChanged(MediaController mediaController, Bundle bundle) {
                    n.f(this, mediaController, bundle);
                }

                @Override // androidx.media3.session.MediaController.Listener
                public final /* synthetic */ void onMediaButtonPreferencesChanged(MediaController mediaController, List list) {
                    n.g(this, mediaController, list);
                }

                @Override // androidx.media3.session.MediaBrowser.Listener
                public final /* synthetic */ void onSearchResultChanged(MediaBrowser mediaBrowser, String str, int i10, MediaLibraryService.LibraryParams libraryParams) {
                    h.b(this, mediaBrowser, str, i10, libraryParams);
                }

                @Override // androidx.media3.session.MediaController.Listener
                public final /* synthetic */ void onSessionActivityChanged(MediaController mediaController, PendingIntent pendingIntent) {
                    n.h(this, mediaController, pendingIntent);
                }

                @Override // androidx.media3.session.MediaController.Listener
                public final /* synthetic */ com.google.common.util.concurrent.d1 onSetCustomLayout(MediaController mediaController, List list) {
                    return n.i(this, mediaController, list);
                }
            };
            this.applicationLooper = Util.getCurrentOrMainLooper();
            this.platformSessionCallbackAggregationTimeoutMs = 100L;
        }

        public com.google.common.util.concurrent.d1<MediaBrowser> buildAsync() {
            MediaControllerHolder mediaControllerHolder = new MediaControllerHolder(this.applicationLooper);
            if (this.token.isLegacySession() && this.bitmapLoader == null) {
                this.bitmapLoader = new CacheBitmapLoader(new DataSourceBitmapLoader.Builder(this.context).build());
            }
            Util.postOrRun(new Handler(this.applicationLooper), new g(mediaControllerHolder, new MediaBrowser(this.context, this.token, this.connectionHints, this.listener, this.applicationLooper, mediaControllerHolder, this.bitmapLoader, this.maxCommandsForMediaItems, this.platformSessionCallbackAggregationTimeoutMs), 0));
            return mediaControllerHolder;
        }

        public Builder experimentalSetPlatformSessionCallbackAggregationTimeoutMs(long j10) {
            this.platformSessionCallbackAggregationTimeoutMs = j10;
            return this;
        }

        public Builder setApplicationLooper(Looper looper) {
            looper.getClass();
            this.applicationLooper = looper;
            return this;
        }

        public Builder setBitmapLoader(androidx.media3.common.util.BitmapLoader bitmapLoader) {
            bitmapLoader.getClass();
            this.bitmapLoader = bitmapLoader;
            return this;
        }

        public Builder setConnectionHints(Bundle bundle) {
            bundle.getClass();
            this.connectionHints = new Bundle(bundle);
            return this;
        }

        public Builder setListener(Listener listener) {
            listener.getClass();
            this.listener = listener;
            return this;
        }

        public Builder setMaxCommandsForMediaItems(int i10) {
            ac.b.j(i10 >= 0);
            this.maxCommandsForMediaItems = i10;
            return this;
        }
    }

    public interface Listener extends MediaController.Listener {
        void onChildrenChanged(MediaBrowser mediaBrowser, String str, int i10, MediaLibraryService.LibraryParams libraryParams);

        void onSearchResultChanged(MediaBrowser mediaBrowser, String str, int i10, MediaLibraryService.LibraryParams libraryParams);
    }

    public interface MediaBrowserImpl extends MediaController.MediaControllerImpl {
        com.google.common.util.concurrent.d1<LibraryResult<com.google.common.collect.h1>> getChildren(String str, int i10, int i11, MediaLibraryService.LibraryParams libraryParams);

        com.google.common.util.concurrent.d1<LibraryResult<MediaItem>> getItem(String str);

        com.google.common.util.concurrent.d1<LibraryResult<MediaItem>> getLibraryRoot(MediaLibraryService.LibraryParams libraryParams);

        com.google.common.util.concurrent.d1<LibraryResult<com.google.common.collect.h1>> getSearchResult(String str, int i10, int i11, MediaLibraryService.LibraryParams libraryParams);

        com.google.common.util.concurrent.d1<LibraryResult<Void>> search(String str, MediaLibraryService.LibraryParams libraryParams);

        com.google.common.util.concurrent.d1<LibraryResult<Void>> subscribe(String str, MediaLibraryService.LibraryParams libraryParams);

        com.google.common.util.concurrent.d1<LibraryResult<Void>> unsubscribe(String str);
    }

    public MediaBrowser(Context context, SessionToken sessionToken, Bundle bundle, Listener listener, Looper looper, MediaController.ConnectionCallback connectionCallback, androidx.media3.common.util.BitmapLoader bitmapLoader, int i10, long j10) {
        super(context, sessionToken, bundle, listener, looper, connectionCallback, bitmapLoader, i10, j10);
    }

    private static <V> com.google.common.util.concurrent.d1<LibraryResult<V>> createDisconnectedFuture() {
        return xc.d.s0(LibraryResult.ofError(-100));
    }

    private void verifyApplicationThread() {
        ac.b.t(Looper.myLooper() == getApplicationLooper(), WRONG_THREAD_ERROR_MESSAGE);
    }

    public com.google.common.util.concurrent.d1<LibraryResult<com.google.common.collect.h1>> getChildren(String str, int i10, int i11, MediaLibraryService.LibraryParams libraryParams) {
        verifyApplicationThread();
        ac.b.k(!TextUtils.isEmpty(str), "parentId must not be empty");
        ac.b.k(i10 >= 0, "page must not be negative");
        ac.b.k(i11 >= 1, "pageSize must not be less than 1");
        if (!isConnected()) {
            return createDisconnectedFuture();
        }
        MediaBrowserImpl mediaBrowserImpl = this.impl;
        mediaBrowserImpl.getClass();
        return mediaBrowserImpl.getChildren(str, i10, i11, libraryParams);
    }

    public com.google.common.util.concurrent.d1<LibraryResult<MediaItem>> getItem(String str) {
        verifyApplicationThread();
        ac.b.k(!TextUtils.isEmpty(str), "mediaId must not be empty");
        if (!isConnected()) {
            return createDisconnectedFuture();
        }
        MediaBrowserImpl mediaBrowserImpl = this.impl;
        mediaBrowserImpl.getClass();
        return mediaBrowserImpl.getItem(str);
    }

    public com.google.common.util.concurrent.d1<LibraryResult<MediaItem>> getLibraryRoot(MediaLibraryService.LibraryParams libraryParams) {
        verifyApplicationThread();
        if (!isConnected()) {
            return createDisconnectedFuture();
        }
        MediaBrowserImpl mediaBrowserImpl = this.impl;
        mediaBrowserImpl.getClass();
        return mediaBrowserImpl.getLibraryRoot(libraryParams);
    }

    public com.google.common.util.concurrent.d1<LibraryResult<com.google.common.collect.h1>> getSearchResult(String str, int i10, int i11, MediaLibraryService.LibraryParams libraryParams) {
        verifyApplicationThread();
        ac.b.k(!TextUtils.isEmpty(str), "query must not be empty");
        ac.b.k(i10 >= 0, "page must not be negative");
        ac.b.k(i11 >= 1, "pageSize must not be less than 1");
        if (!isConnected()) {
            return createDisconnectedFuture();
        }
        MediaBrowserImpl mediaBrowserImpl = this.impl;
        mediaBrowserImpl.getClass();
        return mediaBrowserImpl.getSearchResult(str, i10, i11, libraryParams);
    }

    public void notifyBrowserListener(Consumer<Listener> consumer) {
        Listener listener = (Listener) this.listener;
        if (listener != null) {
            Util.postOrRun(this.applicationHandler, new g(consumer, listener, 14));
        }
    }

    public com.google.common.util.concurrent.d1<LibraryResult<Void>> search(String str, MediaLibraryService.LibraryParams libraryParams) {
        verifyApplicationThread();
        ac.b.k(!TextUtils.isEmpty(str), "query must not be empty");
        if (!isConnected()) {
            return createDisconnectedFuture();
        }
        MediaBrowserImpl mediaBrowserImpl = this.impl;
        mediaBrowserImpl.getClass();
        return mediaBrowserImpl.search(str, libraryParams);
    }

    public com.google.common.util.concurrent.d1<LibraryResult<Void>> subscribe(String str, MediaLibraryService.LibraryParams libraryParams) {
        verifyApplicationThread();
        ac.b.k(!TextUtils.isEmpty(str), "parentId must not be empty");
        if (!isConnected()) {
            return createDisconnectedFuture();
        }
        MediaBrowserImpl mediaBrowserImpl = this.impl;
        mediaBrowserImpl.getClass();
        return mediaBrowserImpl.subscribe(str, libraryParams);
    }

    public com.google.common.util.concurrent.d1<LibraryResult<Void>> unsubscribe(String str) {
        verifyApplicationThread();
        ac.b.k(!TextUtils.isEmpty(str), "parentId must not be empty");
        if (!isConnected()) {
            return createDisconnectedFuture();
        }
        MediaBrowserImpl mediaBrowserImpl = this.impl;
        mediaBrowserImpl.getClass();
        return mediaBrowserImpl.unsubscribe(str);
    }

    @Override // androidx.media3.session.MediaController
    public MediaBrowserImpl createImpl(Context context, SessionToken sessionToken, Bundle bundle, Looper looper, androidx.media3.common.util.BitmapLoader bitmapLoader, long j10) {
        MediaBrowserImpl mediaBrowserImplBase;
        if (sessionToken.isLegacySession()) {
            bitmapLoader.getClass();
            mediaBrowserImplBase = new MediaBrowserImplLegacy(context, this, sessionToken, bundle, looper, bitmapLoader, j10);
        } else {
            mediaBrowserImplBase = new MediaBrowserImplBase(context, this, sessionToken, bundle, looper);
        }
        this.impl = mediaBrowserImplBase;
        return mediaBrowserImplBase;
    }
}

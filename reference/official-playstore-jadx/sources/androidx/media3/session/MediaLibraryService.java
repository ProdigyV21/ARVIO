package androidx.media3.session;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.common.util.Util;
import androidx.media3.session.MediaSession;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public abstract class MediaLibraryService extends MediaSessionService {
    public static final String SERVICE_INTERFACE = "androidx.media3.session.MediaLibraryService";

    public static final class LibraryParams {
        public final Bundle extras;
        public final boolean isOffline;
        public final boolean isRecent;
        public final boolean isSuggested;
        private static final String FIELD_EXTRAS = Util.intToStringMaxRadix(0);
        private static final String FIELD_RECENT = Util.intToStringMaxRadix(1);
        private static final String FIELD_OFFLINE = Util.intToStringMaxRadix(2);
        private static final String FIELD_SUGGESTED = Util.intToStringMaxRadix(3);

        public static final class Builder {
            private Bundle extras = Bundle.EMPTY;
            private boolean offline;
            private boolean recent;
            private boolean suggested;

            public LibraryParams build() {
                return new LibraryParams(this.extras, this.recent, this.offline, this.suggested);
            }

            public Builder setExtras(Bundle bundle) {
                bundle.getClass();
                this.extras = bundle;
                return this;
            }

            public Builder setOffline(boolean z) {
                this.offline = z;
                return this;
            }

            public Builder setRecent(boolean z) {
                this.recent = z;
                return this;
            }

            public Builder setSuggested(boolean z) {
                this.suggested = z;
                return this;
            }
        }

        public static LibraryParams fromBundle(Bundle bundle) {
            Bundle bundle2 = bundle.getBundle(FIELD_EXTRAS);
            boolean z = bundle.getBoolean(FIELD_RECENT, false);
            boolean z5 = bundle.getBoolean(FIELD_OFFLINE, false);
            boolean z10 = bundle.getBoolean(FIELD_SUGGESTED, false);
            if (bundle2 == null) {
                bundle2 = Bundle.EMPTY;
            }
            return new LibraryParams(bundle2, z, z5, z10);
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putBundle(FIELD_EXTRAS, this.extras);
            bundle.putBoolean(FIELD_RECENT, this.isRecent);
            bundle.putBoolean(FIELD_OFFLINE, this.isOffline);
            bundle.putBoolean(FIELD_SUGGESTED, this.isSuggested);
            return bundle;
        }

        private LibraryParams(Bundle bundle, boolean z, boolean z5, boolean z10) {
            this.extras = new Bundle(bundle);
            this.isRecent = z;
            this.isOffline = z5;
            this.isSuggested = z10;
        }
    }

    public static final class MediaLibrarySession extends MediaSession {
        public static final int LIBRARY_ERROR_REPLICATION_MODE_FATAL = 1;
        public static final int LIBRARY_ERROR_REPLICATION_MODE_NONE = 0;
        public static final int LIBRARY_ERROR_REPLICATION_MODE_NON_FATAL = 2;

        public static final class Builder extends MediaSession.BuilderBase<MediaLibrarySession, Builder, Callback> {
            private int libraryErrorReplicationMode;

            public Builder(MediaLibraryService mediaLibraryService, Player player, Callback callback) {
                this((Context) mediaLibraryService, player, callback);
            }

            @Override // androidx.media3.session.MediaSession.BuilderBase
            public /* bridge */ /* synthetic */ MediaSession.BuilderBase setCommandButtonsForMediaItems(List list) {
                return setCommandButtonsForMediaItems((List<CommandButton>) list);
            }

            @Override // androidx.media3.session.MediaSession.BuilderBase
            public /* bridge */ /* synthetic */ MediaSession.BuilderBase setCustomLayout(List list) {
                return setCustomLayout((List<CommandButton>) list);
            }

            public Builder setLibraryErrorReplicationMode(int i10) {
                this.libraryErrorReplicationMode = i10;
                return this;
            }

            @Override // androidx.media3.session.MediaSession.BuilderBase
            public /* bridge */ /* synthetic */ MediaSession.BuilderBase setMediaButtonPreferences(List list) {
                return setMediaButtonPreferences((List<CommandButton>) list);
            }

            public Builder(Context context, Player player, Callback callback) {
                super(context, player, callback);
                this.libraryErrorReplicationMode = 2;
            }

            @Override // androidx.media3.session.MediaSession.BuilderBase
            public MediaLibrarySession build() {
                ensureBitmapLoaderIsSizeLimited();
                return new MediaLibrarySession(this.context, this.id, this.player, this.sessionActivity, this.customLayout, this.mediaButtonPreferences, this.commandButtonsForMediaItems, this.callback, this.tokenExtras, this.sessionExtras, this.bitmapLoader, this.playIfSuppressed, this.isPeriodicPositionUpdateEnabled, this.libraryErrorReplicationMode);
            }

            @Override // androidx.media3.session.MediaSession.BuilderBase
            public Builder setBitmapLoader(androidx.media3.common.util.BitmapLoader bitmapLoader) {
                return (Builder) super.setBitmapLoader(bitmapLoader);
            }

            @Override // androidx.media3.session.MediaSession.BuilderBase
            public Builder setCommandButtonsForMediaItems(List<CommandButton> list) {
                return (Builder) super.setCommandButtonsForMediaItems(list);
            }

            @Override // androidx.media3.session.MediaSession.BuilderBase
            public Builder setCustomLayout(List<CommandButton> list) {
                return (Builder) super.setCustomLayout(list);
            }

            @Override // androidx.media3.session.MediaSession.BuilderBase
            public Builder setExtras(Bundle bundle) {
                return (Builder) super.setExtras(bundle);
            }

            @Override // androidx.media3.session.MediaSession.BuilderBase
            public Builder setId(String str) {
                return (Builder) super.setId(str);
            }

            @Override // androidx.media3.session.MediaSession.BuilderBase
            public Builder setMediaButtonPreferences(List<CommandButton> list) {
                return (Builder) super.setMediaButtonPreferences(list);
            }

            @Override // androidx.media3.session.MediaSession.BuilderBase
            public Builder setPeriodicPositionUpdateEnabled(boolean z) {
                return (Builder) super.setPeriodicPositionUpdateEnabled(z);
            }

            @Override // androidx.media3.session.MediaSession.BuilderBase
            public Builder setSessionActivity(PendingIntent pendingIntent) {
                return (Builder) super.setSessionActivity(pendingIntent);
            }

            @Override // androidx.media3.session.MediaSession.BuilderBase
            public Builder setSessionExtras(Bundle bundle) {
                return (Builder) super.setSessionExtras(bundle);
            }

            @Override // androidx.media3.session.MediaSession.BuilderBase
            public Builder setShowPlayButtonIfPlaybackIsSuppressed(boolean z) {
                return (Builder) super.setShowPlayButtonIfPlaybackIsSuppressed(z);
            }
        }

        public interface Callback extends MediaSession.Callback {
            com.google.common.util.concurrent.d1<LibraryResult<com.google.common.collect.h1>> onGetChildren(MediaLibrarySession mediaLibrarySession, MediaSession.ControllerInfo controllerInfo, String str, int i10, int i11, LibraryParams libraryParams);

            com.google.common.util.concurrent.d1<LibraryResult<MediaItem>> onGetItem(MediaLibrarySession mediaLibrarySession, MediaSession.ControllerInfo controllerInfo, String str);

            com.google.common.util.concurrent.d1<LibraryResult<MediaItem>> onGetLibraryRoot(MediaLibrarySession mediaLibrarySession, MediaSession.ControllerInfo controllerInfo, LibraryParams libraryParams);

            com.google.common.util.concurrent.d1<LibraryResult<com.google.common.collect.h1>> onGetSearchResult(MediaLibrarySession mediaLibrarySession, MediaSession.ControllerInfo controllerInfo, String str, int i10, int i11, LibraryParams libraryParams);

            com.google.common.util.concurrent.d1<LibraryResult<Void>> onSearch(MediaLibrarySession mediaLibrarySession, MediaSession.ControllerInfo controllerInfo, String str, LibraryParams libraryParams);

            com.google.common.util.concurrent.d1<LibraryResult<Void>> onSubscribe(MediaLibrarySession mediaLibrarySession, MediaSession.ControllerInfo controllerInfo, String str, LibraryParams libraryParams);

            com.google.common.util.concurrent.d1<LibraryResult<Void>> onUnsubscribe(MediaLibrarySession mediaLibrarySession, MediaSession.ControllerInfo controllerInfo, String str);
        }

        public MediaLibrarySession(Context context, String str, Player player, PendingIntent pendingIntent, com.google.common.collect.h1 h1Var, com.google.common.collect.h1 h1Var2, com.google.common.collect.h1 h1Var3, MediaSession.Callback callback, Bundle bundle, Bundle bundle2, androidx.media3.common.util.BitmapLoader bitmapLoader, boolean z, boolean z5, int i10) {
            super(context, str, player, pendingIntent, h1Var, h1Var2, h1Var3, callback, bundle, bundle2, bitmapLoader, z, z5, i10, false);
        }

        public void clearReplicatedLibraryError() {
            getImpl().clearReplicatedLibraryError();
        }

        public com.google.common.collect.h1 getSubscribedControllers(String str) {
            return getImpl().getSubscribedControllers(str);
        }

        public void notifyChildrenChanged(MediaSession.ControllerInfo controllerInfo, String str, int i10, LibraryParams libraryParams) {
            ac.b.j(i10 >= 0);
            ac.b.j(true ^ TextUtils.isEmpty(str));
            MediaLibrarySessionImpl impl = getImpl();
            controllerInfo.getClass();
            impl.notifyChildrenChanged(controllerInfo, str, i10, libraryParams);
        }

        public void notifySearchResultChanged(MediaSession.ControllerInfo controllerInfo, String str, int i10, LibraryParams libraryParams) {
            ac.b.j(!TextUtils.isEmpty(str));
            ac.b.j(i10 >= 0);
            MediaLibrarySessionImpl impl = getImpl();
            controllerInfo.getClass();
            impl.notifySearchResultChanged(controllerInfo, str, i10, libraryParams);
        }

        @Override // androidx.media3.session.MediaSession
        public MediaLibrarySessionImpl createImpl(Context context, String str, Player player, PendingIntent pendingIntent, com.google.common.collect.h1 h1Var, com.google.common.collect.h1 h1Var2, com.google.common.collect.h1 h1Var3, MediaSession.Callback callback, Bundle bundle, Bundle bundle2, androidx.media3.common.util.BitmapLoader bitmapLoader, boolean z, boolean z5, int i10, boolean z10) {
            return new MediaLibrarySessionImpl(this, context, str, player, pendingIntent, h1Var, h1Var2, h1Var3, (Callback) callback, bundle, bundle2, bitmapLoader, z, z5, i10);
        }

        @Override // androidx.media3.session.MediaSession
        public MediaLibrarySessionImpl getImpl() {
            return (MediaLibrarySessionImpl) super.getImpl();
        }

        public void notifyChildrenChanged(String str, int i10, LibraryParams libraryParams) {
            ac.b.j(!TextUtils.isEmpty(str));
            ac.b.j(i10 >= 0);
            getImpl().notifyChildrenChanged(str, i10, libraryParams);
        }
    }

    @Override // androidx.media3.session.MediaSessionService, android.app.Service
    public IBinder onBind(Intent intent) {
        if (intent == null) {
            return null;
        }
        return SERVICE_INTERFACE.equals(intent.getAction()) ? getServiceBinder() : super.onBind(intent);
    }

    @Override // androidx.media3.session.MediaSessionService
    public abstract MediaLibrarySession onGetSession(MediaSession.ControllerInfo controllerInfo);
}

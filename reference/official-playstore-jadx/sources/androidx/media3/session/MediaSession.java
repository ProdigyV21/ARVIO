package androidx.media3.session;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Rating;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.datasource.DataSourceBitmapLoader;
import androidx.media3.session.MediaLibraryService;
import androidx.media3.session.SessionCommands;
import androidx.media3.session.legacy.MediaSessionManager;
import com.google.common.collect.o3;
import j$.util.Objects;
import java.util.HashMap;
import java.util.List;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;

/* JADX INFO: loaded from: classes3.dex */
@x4.f
public class MediaSession {
    private final MediaSessionImpl impl;
    private static final Object STATIC_LOCK = new Object();
    private static final HashMap<String, MediaSession> SESSION_ID_TO_SESSION_MAP = new HashMap<>();

    public static final class Api31 {
        private Api31() {
        }

        public static boolean isActivity(PendingIntent pendingIntent) {
            return pendingIntent.isActivity();
        }
    }

    public static final class Builder extends BuilderBase<MediaSession, Builder, Callback> {
        private boolean useLegacySurfaceHandling;

        public Builder(Context context, Player player) {
            super(context, player, new Callback() { // from class: androidx.media3.session.MediaSession.Builder.1
                @Override // androidx.media3.session.MediaSession.Callback
                public final /* synthetic */ com.google.common.util.concurrent.d1 onAddMediaItems(MediaSession mediaSession, ControllerInfo controllerInfo, List list) {
                    return b2.a(this, mediaSession, controllerInfo, list);
                }

                @Override // androidx.media3.session.MediaSession.Callback
                public final /* synthetic */ ConnectionResult onConnect(MediaSession mediaSession, ControllerInfo controllerInfo) {
                    return b2.b(this, mediaSession, controllerInfo);
                }

                @Override // androidx.media3.session.MediaSession.Callback
                public final /* synthetic */ com.google.common.util.concurrent.d1 onCustomCommand(MediaSession mediaSession, ControllerInfo controllerInfo, SessionCommand sessionCommand, Bundle bundle) {
                    return b2.c(this, mediaSession, controllerInfo, sessionCommand, bundle);
                }

                @Override // androidx.media3.session.MediaSession.Callback
                public final /* synthetic */ void onDisconnected(MediaSession mediaSession, ControllerInfo controllerInfo) {
                    b2.e(this, mediaSession, controllerInfo);
                }

                @Override // androidx.media3.session.MediaSession.Callback
                public final /* synthetic */ boolean onMediaButtonEvent(MediaSession mediaSession, ControllerInfo controllerInfo, Intent intent) {
                    return b2.f(this, mediaSession, controllerInfo, intent);
                }

                @Override // androidx.media3.session.MediaSession.Callback
                public final /* synthetic */ com.google.common.util.concurrent.d1 onPlaybackResumption(MediaSession mediaSession, ControllerInfo controllerInfo) {
                    return b2.g(this, mediaSession, controllerInfo);
                }

                @Override // androidx.media3.session.MediaSession.Callback
                public final /* synthetic */ int onPlayerCommandRequest(MediaSession mediaSession, ControllerInfo controllerInfo, int i10) {
                    return b2.i(this, mediaSession, controllerInfo, i10);
                }

                @Override // androidx.media3.session.MediaSession.Callback
                public final /* synthetic */ void onPlayerInteractionFinished(MediaSession mediaSession, ControllerInfo controllerInfo, Player.Commands commands) {
                    b2.j(this, mediaSession, controllerInfo, commands);
                }

                @Override // androidx.media3.session.MediaSession.Callback
                public final /* synthetic */ void onPostConnect(MediaSession mediaSession, ControllerInfo controllerInfo) {
                    b2.k(this, mediaSession, controllerInfo);
                }

                @Override // androidx.media3.session.MediaSession.Callback
                public final /* synthetic */ com.google.common.util.concurrent.d1 onSetMediaItems(MediaSession mediaSession, ControllerInfo controllerInfo, List list, int i10, long j10) {
                    return b2.l(this, mediaSession, controllerInfo, list, i10, j10);
                }

                @Override // androidx.media3.session.MediaSession.Callback
                public final /* synthetic */ com.google.common.util.concurrent.d1 onSetRating(MediaSession mediaSession, ControllerInfo controllerInfo, Rating rating) {
                    return b2.m(this, mediaSession, controllerInfo, rating);
                }

                @Override // androidx.media3.session.MediaSession.Callback
                public final /* synthetic */ com.google.common.util.concurrent.d1 onCustomCommand(MediaSession mediaSession, ControllerInfo controllerInfo, SessionCommand sessionCommand, Bundle bundle, ProgressReporter progressReporter) {
                    return b2.d(this, mediaSession, controllerInfo, sessionCommand, bundle, progressReporter);
                }

                @Override // androidx.media3.session.MediaSession.Callback
                public final /* synthetic */ com.google.common.util.concurrent.d1 onPlaybackResumption(MediaSession mediaSession, ControllerInfo controllerInfo, boolean z) {
                    return b2.h(this, mediaSession, controllerInfo, z);
                }

                @Override // androidx.media3.session.MediaSession.Callback
                public final /* synthetic */ com.google.common.util.concurrent.d1 onSetRating(MediaSession mediaSession, ControllerInfo controllerInfo, String str, Rating rating) {
                    return b2.n(this, mediaSession, controllerInfo, str, rating);
                }
            });
        }

        @Override // androidx.media3.session.MediaSession.BuilderBase
        public MediaSession build() {
            ensureBitmapLoaderIsSizeLimited();
            return new MediaSession(this.context, this.id, this.player, this.sessionActivity, this.customLayout, this.mediaButtonPreferences, this.commandButtonsForMediaItems, this.callback, this.tokenExtras, this.sessionExtras, this.bitmapLoader, this.playIfSuppressed, this.isPeriodicPositionUpdateEnabled, 0, this.useLegacySurfaceHandling);
        }

        @Override // androidx.media3.session.MediaSession.BuilderBase
        public /* bridge */ /* synthetic */ BuilderBase setCommandButtonsForMediaItems(List list) {
            return setCommandButtonsForMediaItems((List<CommandButton>) list);
        }

        @Override // androidx.media3.session.MediaSession.BuilderBase
        public /* bridge */ /* synthetic */ BuilderBase setCustomLayout(List list) {
            return setCustomLayout((List<CommandButton>) list);
        }

        public Builder setExperimentalSetUseLegacySurfaceHandling(boolean z) {
            this.useLegacySurfaceHandling = z;
            return this;
        }

        @Override // androidx.media3.session.MediaSession.BuilderBase
        public /* bridge */ /* synthetic */ BuilderBase setMediaButtonPreferences(List list) {
            return setMediaButtonPreferences((List<CommandButton>) list);
        }

        @Override // androidx.media3.session.MediaSession.BuilderBase
        public Builder setBitmapLoader(androidx.media3.common.util.BitmapLoader bitmapLoader) {
            return (Builder) super.setBitmapLoader(bitmapLoader);
        }

        @Override // androidx.media3.session.MediaSession.BuilderBase
        public Builder setCallback(Callback callback) {
            return (Builder) super.setCallback(callback);
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

    public static abstract class BuilderBase<SessionT extends MediaSession, BuilderT extends BuilderBase<SessionT, BuilderT, CallbackT>, CallbackT extends Callback> {
        androidx.media3.common.util.BitmapLoader bitmapLoader;
        CallbackT callback;
        com.google.common.collect.h1 commandButtonsForMediaItems;
        final Context context;
        com.google.common.collect.h1 customLayout;
        String id;
        boolean isPeriodicPositionUpdateEnabled;
        com.google.common.collect.h1 mediaButtonPreferences;
        boolean playIfSuppressed;
        final Player player;
        PendingIntent sessionActivity;
        Bundle sessionExtras;
        Bundle tokenExtras;

        public BuilderBase(Context context, Player player, CallbackT callbackt) {
            context.getClass();
            this.context = context;
            player.getClass();
            this.player = player;
            ac.b.j(player.canAdvertiseSession());
            this.id = "";
            this.callback = callbackt;
            this.tokenExtras = new Bundle();
            this.sessionExtras = new Bundle();
            com.google.common.collect.f1 f1Var = com.google.common.collect.h1.f14020l;
            o3 o3Var = o3.f14078o;
            this.customLayout = o3Var;
            this.mediaButtonPreferences = o3Var;
            this.playIfSuppressed = true;
            this.isPeriodicPositionUpdateEnabled = true;
            this.commandButtonsForMediaItems = o3Var;
        }

        public abstract SessionT build();

        @EnsuresNonNull({"bitmapLoader"})
        public final void ensureBitmapLoaderIsSizeLimited() {
            int bitmapDimensionLimit = MediaSession.getBitmapDimensionLimit(this.context);
            androidx.media3.common.util.BitmapLoader bitmapLoader = this.bitmapLoader;
            if (bitmapLoader == null) {
                this.bitmapLoader = new CacheBitmapLoader(new DataSourceBitmapLoader.Builder(this.context).setMaximumOutputDimension(bitmapDimensionLimit).setMakeShared(true).build());
            } else {
                this.bitmapLoader = new SizeLimitedBitmapLoader(bitmapLoader, bitmapDimensionLimit, true);
            }
        }

        public BuilderT setBitmapLoader(androidx.media3.common.util.BitmapLoader bitmapLoader) {
            bitmapLoader.getClass();
            this.bitmapLoader = bitmapLoader;
            return this;
        }

        public BuilderT setCallback(CallbackT callbackt) {
            callbackt.getClass();
            this.callback = callbackt;
            return this;
        }

        public BuilderT setCommandButtonsForMediaItems(List<CommandButton> list) {
            this.commandButtonsForMediaItems = com.google.common.collect.h1.n(list);
            return this;
        }

        public BuilderT setCustomLayout(List<CommandButton> list) {
            this.customLayout = com.google.common.collect.h1.n(list);
            return this;
        }

        public BuilderT setExtras(Bundle bundle) {
            bundle.getClass();
            this.tokenExtras = new Bundle(bundle);
            return this;
        }

        public BuilderT setId(String str) {
            str.getClass();
            this.id = str;
            return this;
        }

        public BuilderT setMediaButtonPreferences(List<CommandButton> list) {
            this.mediaButtonPreferences = com.google.common.collect.h1.n(list);
            return this;
        }

        public BuilderT setPeriodicPositionUpdateEnabled(boolean z) {
            this.isPeriodicPositionUpdateEnabled = z;
            return this;
        }

        public BuilderT setSessionActivity(PendingIntent pendingIntent) {
            if (Build.VERSION.SDK_INT >= 31) {
                ac.b.j(Api31.isActivity(pendingIntent));
            }
            pendingIntent.getClass();
            this.sessionActivity = pendingIntent;
            return this;
        }

        public BuilderT setSessionExtras(Bundle bundle) {
            bundle.getClass();
            this.sessionExtras = new Bundle(bundle);
            return this;
        }

        public BuilderT setShowPlayButtonIfPlaybackIsSuppressed(boolean z) {
            this.playIfSuppressed = z;
            return this;
        }
    }

    public interface Callback {
        com.google.common.util.concurrent.d1<List<MediaItem>> onAddMediaItems(MediaSession mediaSession, ControllerInfo controllerInfo, List<MediaItem> list);

        ConnectionResult onConnect(MediaSession mediaSession, ControllerInfo controllerInfo);

        com.google.common.util.concurrent.d1<SessionResult> onCustomCommand(MediaSession mediaSession, ControllerInfo controllerInfo, SessionCommand sessionCommand, Bundle bundle);

        com.google.common.util.concurrent.d1<SessionResult> onCustomCommand(MediaSession mediaSession, ControllerInfo controllerInfo, SessionCommand sessionCommand, Bundle bundle, ProgressReporter progressReporter);

        void onDisconnected(MediaSession mediaSession, ControllerInfo controllerInfo);

        boolean onMediaButtonEvent(MediaSession mediaSession, ControllerInfo controllerInfo, Intent intent);

        @Deprecated
        com.google.common.util.concurrent.d1<MediaItemsWithStartPosition> onPlaybackResumption(MediaSession mediaSession, ControllerInfo controllerInfo);

        com.google.common.util.concurrent.d1<MediaItemsWithStartPosition> onPlaybackResumption(MediaSession mediaSession, ControllerInfo controllerInfo, boolean z);

        @Deprecated
        int onPlayerCommandRequest(MediaSession mediaSession, ControllerInfo controllerInfo, int i10);

        void onPlayerInteractionFinished(MediaSession mediaSession, ControllerInfo controllerInfo, Player.Commands commands);

        void onPostConnect(MediaSession mediaSession, ControllerInfo controllerInfo);

        com.google.common.util.concurrent.d1<MediaItemsWithStartPosition> onSetMediaItems(MediaSession mediaSession, ControllerInfo controllerInfo, List<MediaItem> list, int i10, long j10);

        com.google.common.util.concurrent.d1<SessionResult> onSetRating(MediaSession mediaSession, ControllerInfo controllerInfo, Rating rating);

        com.google.common.util.concurrent.d1<SessionResult> onSetRating(MediaSession mediaSession, ControllerInfo controllerInfo, String str, Rating rating);
    }

    public static final class ConnectionResult {
        public final Player.Commands availablePlayerCommands;
        public final SessionCommands availableSessionCommands;
        public final com.google.common.collect.h1 customLayout;
        public final boolean isAccepted;
        public final com.google.common.collect.h1 mediaButtonPreferences;
        public final PendingIntent sessionActivity;
        public final Bundle sessionExtras;
        public static final SessionCommands DEFAULT_SESSION_COMMANDS = new SessionCommands.Builder().addAllSessionCommands().build();
        public static final SessionCommands DEFAULT_SESSION_AND_LIBRARY_COMMANDS = new SessionCommands.Builder().addAllLibraryCommands().addAllSessionCommands().build();
        public static final Player.Commands DEFAULT_PLAYER_COMMANDS = new Player.Commands.Builder().addAllCommands().build();

        public static class AcceptedResultBuilder {
            private Player.Commands availablePlayerCommands = ConnectionResult.DEFAULT_PLAYER_COMMANDS;
            private SessionCommands availableSessionCommands;
            private com.google.common.collect.h1 customLayout;
            private com.google.common.collect.h1 mediaButtonPreferences;
            private PendingIntent sessionActivity;
            private Bundle sessionExtras;

            public AcceptedResultBuilder(MediaSession mediaSession) {
                this.availableSessionCommands = mediaSession instanceof MediaLibraryService.MediaLibrarySession ? ConnectionResult.DEFAULT_SESSION_AND_LIBRARY_COMMANDS : ConnectionResult.DEFAULT_SESSION_COMMANDS;
            }

            public ConnectionResult build() {
                return new ConnectionResult(true, this.availableSessionCommands, this.availablePlayerCommands, this.customLayout, this.mediaButtonPreferences, this.sessionExtras, this.sessionActivity);
            }

            public AcceptedResultBuilder setAvailablePlayerCommands(Player.Commands commands) {
                commands.getClass();
                this.availablePlayerCommands = commands;
                return this;
            }

            public AcceptedResultBuilder setAvailableSessionCommands(SessionCommands sessionCommands) {
                sessionCommands.getClass();
                this.availableSessionCommands = sessionCommands;
                return this;
            }

            public AcceptedResultBuilder setCustomLayout(List<CommandButton> list) {
                this.customLayout = list == null ? null : com.google.common.collect.h1.n(list);
                return this;
            }

            public AcceptedResultBuilder setMediaButtonPreferences(List<CommandButton> list) {
                this.mediaButtonPreferences = list == null ? null : com.google.common.collect.h1.n(list);
                return this;
            }

            public AcceptedResultBuilder setSessionActivity(PendingIntent pendingIntent) {
                this.sessionActivity = pendingIntent;
                return this;
            }

            public AcceptedResultBuilder setSessionExtras(Bundle bundle) {
                this.sessionExtras = bundle;
                return this;
            }
        }

        public static ConnectionResult accept(SessionCommands sessionCommands, Player.Commands commands) {
            return new ConnectionResult(true, sessionCommands, commands, null, null, null, null);
        }

        public static ConnectionResult reject() {
            SessionCommands sessionCommands = SessionCommands.EMPTY;
            Player.Commands commands = Player.Commands.EMPTY;
            com.google.common.collect.f1 f1Var = com.google.common.collect.h1.f14020l;
            o3 o3Var = o3.f14078o;
            return new ConnectionResult(false, sessionCommands, commands, o3Var, o3Var, Bundle.EMPTY, null);
        }

        private ConnectionResult(boolean z, SessionCommands sessionCommands, Player.Commands commands, com.google.common.collect.h1 h1Var, com.google.common.collect.h1 h1Var2, Bundle bundle, PendingIntent pendingIntent) {
            this.isAccepted = z;
            this.availableSessionCommands = sessionCommands;
            this.availablePlayerCommands = commands;
            this.customLayout = h1Var;
            this.mediaButtonPreferences = h1Var2;
            this.sessionExtras = bundle;
            this.sessionActivity = pendingIntent;
        }
    }

    public interface ControllerCb {
        void onAudioAttributesChanged(int i10, AudioAttributes audioAttributes) throws RemoteException;

        void onAudioSessionIdChanged(int i10, int i11) throws RemoteException;

        void onAvailableCommandsChangedFromPlayer(int i10, Player.Commands commands) throws RemoteException;

        void onAvailableCommandsChangedFromSession(int i10, SessionCommands sessionCommands, Player.Commands commands) throws RemoteException;

        void onChildrenChanged(int i10, String str, int i11, MediaLibraryService.LibraryParams libraryParams) throws RemoteException;

        void onDeviceInfoChanged(int i10, DeviceInfo deviceInfo) throws RemoteException;

        void onDeviceVolumeChanged(int i10, int i11, boolean z) throws RemoteException;

        void onDisconnected(int i10);

        void onError(int i10, SessionError sessionError) throws RemoteException;

        void onIsLoadingChanged(int i10, boolean z) throws RemoteException;

        void onIsPlayingChanged(int i10, boolean z) throws RemoteException;

        void onLibraryResult(int i10, LibraryResult<?> libraryResult) throws RemoteException;

        void onMediaItemTransition(int i10, MediaItem mediaItem, int i11) throws RemoteException;

        void onMediaMetadataChanged(int i10, MediaMetadata mediaMetadata) throws RemoteException;

        void onPeriodicSessionPositionInfoChanged(int i10, SessionPositionInfo sessionPositionInfo, boolean z, boolean z5, int i11) throws RemoteException;

        void onPlayWhenReadyChanged(int i10, boolean z, int i11) throws RemoteException;

        void onPlaybackParametersChanged(int i10, PlaybackParameters playbackParameters) throws RemoteException;

        void onPlaybackStateChanged(int i10, int i11, PlaybackException playbackException) throws RemoteException;

        void onPlaybackSuppressionReasonChanged(int i10, int i11) throws RemoteException;

        void onPlayerChanged(int i10, PlayerWrapper playerWrapper, PlayerWrapper playerWrapper2) throws RemoteException;

        void onPlayerError(int i10, PlaybackException playbackException) throws RemoteException;

        void onPlayerInfoChanged(int i10, PlayerInfo playerInfo, Player.Commands commands, boolean z, boolean z5) throws RemoteException;

        void onPlaylistMetadataChanged(int i10, MediaMetadata mediaMetadata) throws RemoteException;

        void onPositionDiscontinuity(int i10, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i11) throws RemoteException;

        void onRenderedFirstFrame(int i10) throws RemoteException;

        void onRepeatModeChanged(int i10, int i11) throws RemoteException;

        void onSearchResultChanged(int i10, String str, int i11, MediaLibraryService.LibraryParams libraryParams) throws RemoteException;

        void onSeekBackIncrementChanged(int i10, long j10) throws RemoteException;

        void onSeekForwardIncrementChanged(int i10, long j10) throws RemoteException;

        void onSessionActivityChanged(int i10, PendingIntent pendingIntent) throws RemoteException;

        void onSessionExtrasChanged(int i10, Bundle bundle) throws RemoteException;

        void onSessionResult(int i10, SessionResult sessionResult) throws RemoteException;

        void onShuffleModeEnabledChanged(int i10, boolean z) throws RemoteException;

        void onSurfaceSizeChanged(int i10, int i11, int i12) throws RemoteException;

        void onTimelineChanged(int i10, Timeline timeline, int i11) throws RemoteException;

        void onTrackSelectionParametersChanged(int i10, TrackSelectionParameters trackSelectionParameters) throws RemoteException;

        void onTracksChanged(int i10, Tracks tracks) throws RemoteException;

        void onVideoSizeChanged(int i10, VideoSize videoSize) throws RemoteException;

        void onVolumeChanged(int i10, float f10) throws RemoteException;

        void sendCustomCommand(int i10, SessionCommand sessionCommand, Bundle bundle) throws RemoteException;

        void sendCustomCommandProgressUpdate(int i10, SessionCommand sessionCommand, Bundle bundle, Bundle bundle2) throws RemoteException;

        void setCustomLayout(int i10, List<CommandButton> list) throws RemoteException;

        void setMediaButtonPreferences(int i10, List<CommandButton> list) throws RemoteException;
    }

    public static final class ControllerInfo {
        public static final int LEGACY_CONTROLLER_INTERFACE_VERSION = 0;
        public static final String LEGACY_CONTROLLER_PACKAGE_NAME = "android.media.session.MediaController";
        public static final int LEGACY_CONTROLLER_VERSION = 0;
        private final Bundle connectionHints;
        private final ControllerCb controllerCb;
        private final int interfaceVersion;
        private final boolean isPackageNameVerified;
        private final boolean isTrusted;
        private final int libraryVersion;
        private final int maxCommandsForMediaItems;
        private final MediaSessionManager.RemoteUserInfo remoteUserInfo;

        public ControllerInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo, int i10, int i11, boolean z, ControllerCb controllerCb, Bundle bundle, int i12, boolean z5) {
            this.remoteUserInfo = remoteUserInfo;
            this.libraryVersion = i10;
            this.interfaceVersion = i11;
            this.isTrusted = z;
            this.controllerCb = controllerCb;
            this.connectionHints = bundle;
            this.maxCommandsForMediaItems = i12;
            this.isPackageNameVerified = z5;
        }

        public static ControllerInfo createLegacyControllerInfo() {
            return new ControllerInfo(new MediaSessionManager.RemoteUserInfo("android.media.session.MediaController", -1, -1), 0, 0, false, null, Bundle.EMPTY, 0, false);
        }

        public static ControllerInfo createTestOnlyControllerInfo(String str, int i10, int i11, int i12, int i13, boolean z, Bundle bundle, boolean z5) {
            return new ControllerInfo(new MediaSessionManager.RemoteUserInfo(str, i10, i11), i12, i13, z, null, bundle, 0, z5);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ControllerInfo)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            ControllerInfo controllerInfo = (ControllerInfo) obj;
            ControllerCb controllerCb = this.controllerCb;
            return (controllerCb == null && controllerInfo.controllerCb == null) ? this.remoteUserInfo.equals(controllerInfo.remoteUserInfo) : Objects.equals(controllerCb, controllerInfo.controllerCb);
        }

        public Bundle getConnectionHints() {
            return new Bundle(this.connectionHints);
        }

        public ControllerCb getControllerCb() {
            return this.controllerCb;
        }

        public int getControllerVersion() {
            return this.libraryVersion;
        }

        public int getInterfaceVersion() {
            return this.interfaceVersion;
        }

        public int getMaxCommandsForMediaItems() {
            return this.maxCommandsForMediaItems;
        }

        public String getPackageName() {
            return this.remoteUserInfo.getPackageName();
        }

        public MediaSessionManager.RemoteUserInfo getRemoteUserInfo() {
            return this.remoteUserInfo;
        }

        public int getUid() {
            return this.remoteUserInfo.getUid();
        }

        public int hashCode() {
            return Objects.hash(this.controllerCb, this.remoteUserInfo);
        }

        public boolean isPackageNameVerified() {
            return this.isPackageNameVerified;
        }

        public boolean isTrusted() {
            return this.isTrusted;
        }

        public String toString() {
            return "ControllerInfo {pkg=" + this.remoteUserInfo.getPackageName() + ", uid=" + this.remoteUserInfo.getUid() + "}";
        }
    }

    public interface Listener {
        void onNotificationRefreshRequired(MediaSession mediaSession);

        boolean onPlayRequested(MediaSession mediaSession);
    }

    public static final class MediaItemsWithStartPosition {
        public final com.google.common.collect.h1 mediaItems;
        public final int startIndex;
        public final long startPositionMs;

        public MediaItemsWithStartPosition(List<MediaItem> list, int i10, long j10) {
            this.mediaItems = com.google.common.collect.h1.n(list);
            this.startIndex = i10;
            this.startPositionMs = j10;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof MediaItemsWithStartPosition)) {
                return false;
            }
            MediaItemsWithStartPosition mediaItemsWithStartPosition = (MediaItemsWithStartPosition) obj;
            return this.mediaItems.equals(mediaItemsWithStartPosition.mediaItems) && this.startIndex == mediaItemsWithStartPosition.startIndex && this.startPositionMs == mediaItemsWithStartPosition.startPositionMs;
        }

        public int hashCode() {
            return qb.d.F(this.startPositionMs) + (((this.mediaItems.hashCode() * 31) + this.startIndex) * 31);
        }
    }

    public interface ProgressReporter {
        void sendProgressUpdate(Bundle bundle);
    }

    public MediaSession(Context context, String str, Player player, PendingIntent pendingIntent, com.google.common.collect.h1 h1Var, com.google.common.collect.h1 h1Var2, com.google.common.collect.h1 h1Var3, Callback callback, Bundle bundle, Bundle bundle2, androidx.media3.common.util.BitmapLoader bitmapLoader, boolean z, boolean z5, int i10, boolean z10) {
        synchronized (STATIC_LOCK) {
            HashMap<String, MediaSession> map = SESSION_ID_TO_SESSION_MAP;
            if (map.containsKey(str)) {
                throw new IllegalStateException("Session ID must be unique. ID=" + str);
            }
            map.put(str, this);
        }
        this.impl = createImpl(context, str, player, pendingIntent, h1Var, h1Var2, h1Var3, callback, bundle, bundle2, bitmapLoader, z, z5, i10, z10);
    }

    public static int getBitmapDimensionLimit(Context context) {
        return MediaSessionImpl.getBitmapDimensionLimit(context);
    }

    public static MediaSession getSession(Uri uri) {
        synchronized (STATIC_LOCK) {
            try {
                for (MediaSession mediaSession : SESSION_ID_TO_SESSION_MAP.values()) {
                    if (Objects.equals(mediaSession.getUri(), uri)) {
                        return mediaSession;
                    }
                }
                return null;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void broadcastCustomCommand(SessionCommand sessionCommand, Bundle bundle) {
        sessionCommand.getClass();
        bundle.getClass();
        ac.b.k(sessionCommand.commandCode == 0, "command must be a custom command");
        this.impl.broadcastCustomCommand(sessionCommand, bundle);
    }

    public final void clearListener() {
        this.impl.clearMediaSessionListener();
    }

    public MediaSessionImpl createImpl(Context context, String str, Player player, PendingIntent pendingIntent, com.google.common.collect.h1 h1Var, com.google.common.collect.h1 h1Var2, com.google.common.collect.h1 h1Var3, Callback callback, Bundle bundle, Bundle bundle2, androidx.media3.common.util.BitmapLoader bitmapLoader, boolean z, boolean z5, int i10, boolean z10) {
        return new MediaSessionImpl(this, context, str, player, pendingIntent, h1Var, h1Var2, h1Var3, callback, bundle, bundle2, bitmapLoader, z, z5, z10);
    }

    public final androidx.media3.common.util.BitmapLoader getBitmapLoader() {
        return this.impl.getBitmapLoader();
    }

    public final List<ControllerInfo> getConnectedControllers() {
        return this.impl.getConnectedControllers();
    }

    public final ControllerInfo getControllerForCurrentRequest() {
        return this.impl.getControllerForCurrentRequest();
    }

    public com.google.common.collect.h1 getCustomLayout() {
        return this.impl.getCustomLayout();
    }

    public final String getId() {
        return this.impl.getId();
    }

    public MediaSessionImpl getImpl() {
        return this.impl;
    }

    public final IBinder getLegacyBrowserServiceBinder() {
        return this.impl.getLegacyBrowserServiceBinder();
    }

    public com.google.common.collect.h1 getMediaButtonPreferences() {
        return this.impl.getMediaButtonPreferences();
    }

    public ControllerInfo getMediaNotificationControllerInfo() {
        return this.impl.getMediaNotificationControllerInfo();
    }

    public final MediaSession.Token getPlatformToken() {
        return this.impl.getPlatformToken();
    }

    public final Player getPlayer() {
        return this.impl.getPlayerWrapper().getWrappedPlayer();
    }

    public final PendingIntent getSessionActivity() {
        return this.impl.getSessionActivity();
    }

    public Bundle getSessionExtras() {
        return this.impl.getSessionExtras();
    }

    public final boolean getShowPlayButtonIfPlaybackIsSuppressed() {
        return this.impl.shouldPlayIfSuppressed();
    }

    public final SessionToken getToken() {
        return this.impl.getToken();
    }

    public final Uri getUri() {
        return this.impl.getUri();
    }

    public final void handleControllerConnectionFromService(IMediaController iMediaController, ControllerInfo controllerInfo) {
        this.impl.connectFromService(iMediaController, controllerInfo);
    }

    public final boolean isAutoCompanionController(ControllerInfo controllerInfo) {
        return this.impl.isAutoCompanionController(controllerInfo);
    }

    public final boolean isAutomotiveController(ControllerInfo controllerInfo) {
        return this.impl.isAutomotiveController(controllerInfo);
    }

    public boolean isMediaNotificationController(ControllerInfo controllerInfo) {
        return this.impl.isMediaNotificationController(controllerInfo);
    }

    public final boolean isReleased() {
        return this.impl.isReleased();
    }

    public final void release() {
        try {
            synchronized (STATIC_LOCK) {
                SESSION_ID_TO_SESSION_MAP.remove(this.impl.getId());
            }
            this.impl.release();
        } catch (Exception unused) {
        }
    }

    public final com.google.common.util.concurrent.d1<SessionResult> sendCustomCommand(ControllerInfo controllerInfo, SessionCommand sessionCommand, Bundle bundle) {
        controllerInfo.getClass();
        sessionCommand.getClass();
        bundle.getClass();
        ac.b.k(sessionCommand.commandCode == 0, "command must be a custom command");
        return this.impl.sendCustomCommand(controllerInfo, sessionCommand, bundle);
    }

    public final void sendError(ControllerInfo controllerInfo, SessionError sessionError) {
        this.impl.sendError(controllerInfo, sessionError);
    }

    public final void setAvailableCommands(ControllerInfo controllerInfo, SessionCommands sessionCommands, Player.Commands commands) {
        ac.b.p(controllerInfo, "controller must not be null");
        ac.b.p(sessionCommands, "sessionCommands must not be null");
        ac.b.p(commands, "playerCommands must not be null");
        this.impl.setAvailableCommands(controllerInfo, sessionCommands, commands);
    }

    public final com.google.common.util.concurrent.d1<SessionResult> setCustomLayout(ControllerInfo controllerInfo, List<CommandButton> list) {
        ac.b.p(controllerInfo, "controller must not be null");
        ac.b.p(list, "layout must not be null");
        return this.impl.setCustomLayout(controllerInfo, com.google.common.collect.h1.n(list));
    }

    public final void setLegacyControllerConnectionTimeoutMs(long j10) {
        this.impl.setLegacyControllerConnectionTimeoutMs(j10);
    }

    public final void setListener(Listener listener) {
        this.impl.setMediaSessionListener(listener);
    }

    public final com.google.common.util.concurrent.d1<SessionResult> setMediaButtonPreferences(ControllerInfo controllerInfo, List<CommandButton> list) {
        ac.b.p(controllerInfo, "controller must not be null");
        ac.b.p(list, "media button preferences must not be null");
        return this.impl.setMediaButtonPreferences(controllerInfo, com.google.common.collect.h1.n(list));
    }

    public final void setPlaybackException(ControllerInfo controllerInfo, PlaybackException playbackException) {
        this.impl.setPlaybackException(controllerInfo, playbackException);
    }

    public final void setPlayer(Player player) {
        player.getClass();
        ac.b.j(player.canAdvertiseSession());
        ac.b.j(player.getApplicationLooper() == getPlayer().getApplicationLooper());
        ac.b.s(player.getApplicationLooper() == Looper.myLooper());
        this.impl.setPlayer(player);
    }

    public final void setSessionActivity(PendingIntent pendingIntent) {
        if (Build.VERSION.SDK_INT >= 31 && pendingIntent != null) {
            ac.b.j(Api31.isActivity(pendingIntent));
        }
        this.impl.setSessionActivity(pendingIntent);
    }

    public final void setSessionExtras(Bundle bundle) {
        this.impl.setSessionExtras(new Bundle(bundle));
    }

    public final void setSessionPositionUpdateDelayMs(long j10) {
        this.impl.setSessionPositionUpdateDelayMsOnHandler(j10);
    }

    public final void sendError(SessionError sessionError) {
        this.impl.sendError(sessionError);
    }

    public final void setPlaybackException(PlaybackException playbackException) {
        this.impl.setPlaybackException(playbackException);
    }

    public final void setSessionExtras(ControllerInfo controllerInfo, Bundle bundle) {
        ac.b.p(controllerInfo, "controller must not be null");
        this.impl.setSessionExtras(controllerInfo, new Bundle(bundle));
    }

    public final void setCustomLayout(List<CommandButton> list) {
        ac.b.p(list, "layout must not be null");
        this.impl.setCustomLayout(com.google.common.collect.h1.n(list));
    }

    public final void setMediaButtonPreferences(List<CommandButton> list) {
        ac.b.p(list, "media button preferences must not be null");
        this.impl.setMediaButtonPreferences(com.google.common.collect.h1.n(list));
    }

    public final void setSessionActivity(ControllerInfo controllerInfo, PendingIntent pendingIntent) {
        if (Build.VERSION.SDK_INT >= 31 && pendingIntent != null) {
            ac.b.j(Api31.isActivity(pendingIntent));
        }
        this.impl.setSessionActivity(controllerInfo, pendingIntent);
    }
}

package androidx.media3.common;

import android.os.Bundle;
import android.os.Looper;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import androidx.fragment.app.a2;
import androidx.media3.common.FlagSet;
import androidx.media3.common.text.Cue;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Size;
import androidx.media3.common.util.Util;
import j$.util.Objects;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public interface Player {

    @Deprecated
    public static final int COMMAND_ADJUST_DEVICE_VOLUME = 26;
    public static final int COMMAND_ADJUST_DEVICE_VOLUME_WITH_FLAGS = 34;
    public static final int COMMAND_CHANGE_MEDIA_ITEMS = 20;
    public static final int COMMAND_GET_AUDIO_ATTRIBUTES = 21;
    public static final int COMMAND_GET_CURRENT_MEDIA_ITEM = 16;
    public static final int COMMAND_GET_DEVICE_VOLUME = 23;

    @Deprecated
    public static final int COMMAND_GET_MEDIA_ITEMS_METADATA = 18;
    public static final int COMMAND_GET_METADATA = 18;
    public static final int COMMAND_GET_TEXT = 28;
    public static final int COMMAND_GET_TIMELINE = 17;
    public static final int COMMAND_GET_TRACKS = 30;
    public static final int COMMAND_GET_VOLUME = 22;
    public static final int COMMAND_INVALID = -1;
    public static final int COMMAND_PLAY_PAUSE = 1;
    public static final int COMMAND_PREPARE = 2;
    public static final int COMMAND_RELEASE = 32;
    public static final int COMMAND_SEEK_BACK = 11;
    public static final int COMMAND_SEEK_FORWARD = 12;
    public static final int COMMAND_SEEK_IN_CURRENT_MEDIA_ITEM = 5;

    @Deprecated
    public static final int COMMAND_SEEK_IN_CURRENT_WINDOW = 5;
    public static final int COMMAND_SEEK_TO_DEFAULT_POSITION = 4;
    public static final int COMMAND_SEEK_TO_MEDIA_ITEM = 10;
    public static final int COMMAND_SEEK_TO_NEXT = 9;
    public static final int COMMAND_SEEK_TO_NEXT_MEDIA_ITEM = 8;

    @Deprecated
    public static final int COMMAND_SEEK_TO_NEXT_WINDOW = 8;
    public static final int COMMAND_SEEK_TO_PREVIOUS = 7;
    public static final int COMMAND_SEEK_TO_PREVIOUS_MEDIA_ITEM = 6;

    @Deprecated
    public static final int COMMAND_SEEK_TO_PREVIOUS_WINDOW = 6;

    @Deprecated
    public static final int COMMAND_SEEK_TO_WINDOW = 10;
    public static final int COMMAND_SET_AUDIO_ATTRIBUTES = 35;

    @Deprecated
    public static final int COMMAND_SET_DEVICE_VOLUME = 25;
    public static final int COMMAND_SET_DEVICE_VOLUME_WITH_FLAGS = 33;
    public static final int COMMAND_SET_MEDIA_ITEM = 31;

    @Deprecated
    public static final int COMMAND_SET_MEDIA_ITEMS_METADATA = 19;
    public static final int COMMAND_SET_PLAYLIST_METADATA = 19;
    public static final int COMMAND_SET_REPEAT_MODE = 15;
    public static final int COMMAND_SET_SHUFFLE_MODE = 14;
    public static final int COMMAND_SET_SPEED_AND_PITCH = 13;
    public static final int COMMAND_SET_TRACK_SELECTION_PARAMETERS = 29;
    public static final int COMMAND_SET_VIDEO_SURFACE = 27;
    public static final int COMMAND_SET_VOLUME = 24;
    public static final int COMMAND_STOP = 3;
    public static final int DISCONTINUITY_REASON_AUTO_TRANSITION = 0;
    public static final int DISCONTINUITY_REASON_INTERNAL = 5;
    public static final int DISCONTINUITY_REASON_REMOVE = 4;
    public static final int DISCONTINUITY_REASON_SEEK = 1;
    public static final int DISCONTINUITY_REASON_SEEK_ADJUSTMENT = 2;
    public static final int DISCONTINUITY_REASON_SILENCE_SKIP = 6;
    public static final int DISCONTINUITY_REASON_SKIP = 3;
    public static final int EVENT_AUDIO_ATTRIBUTES_CHANGED = 20;
    public static final int EVENT_AUDIO_SESSION_ID = 21;
    public static final int EVENT_AVAILABLE_COMMANDS_CHANGED = 13;
    public static final int EVENT_CUES = 27;
    public static final int EVENT_DEVICE_INFO_CHANGED = 29;
    public static final int EVENT_DEVICE_VOLUME_CHANGED = 30;
    public static final int EVENT_IS_LOADING_CHANGED = 3;
    public static final int EVENT_IS_PLAYING_CHANGED = 7;
    public static final int EVENT_MAX_SEEK_TO_PREVIOUS_POSITION_CHANGED = 18;
    public static final int EVENT_MEDIA_ITEM_TRANSITION = 1;
    public static final int EVENT_MEDIA_METADATA_CHANGED = 14;
    public static final int EVENT_METADATA = 28;
    public static final int EVENT_PLAYBACK_PARAMETERS_CHANGED = 12;
    public static final int EVENT_PLAYBACK_STATE_CHANGED = 4;
    public static final int EVENT_PLAYBACK_SUPPRESSION_REASON_CHANGED = 6;
    public static final int EVENT_PLAYER_ERROR = 10;
    public static final int EVENT_PLAYLIST_METADATA_CHANGED = 15;
    public static final int EVENT_PLAY_WHEN_READY_CHANGED = 5;
    public static final int EVENT_POSITION_DISCONTINUITY = 11;
    public static final int EVENT_RENDERED_FIRST_FRAME = 26;
    public static final int EVENT_REPEAT_MODE_CHANGED = 8;
    public static final int EVENT_SEEK_BACK_INCREMENT_CHANGED = 16;
    public static final int EVENT_SEEK_FORWARD_INCREMENT_CHANGED = 17;
    public static final int EVENT_SHUFFLE_MODE_ENABLED_CHANGED = 9;
    public static final int EVENT_SKIP_SILENCE_ENABLED_CHANGED = 23;
    public static final int EVENT_SURFACE_SIZE_CHANGED = 24;
    public static final int EVENT_TIMELINE_CHANGED = 0;
    public static final int EVENT_TRACKS_CHANGED = 2;
    public static final int EVENT_TRACK_SELECTION_PARAMETERS_CHANGED = 19;
    public static final int EVENT_VIDEO_SIZE_CHANGED = 25;
    public static final int EVENT_VOLUME_CHANGED = 22;
    public static final int MEDIA_ITEM_TRANSITION_REASON_AUTO = 1;
    public static final int MEDIA_ITEM_TRANSITION_REASON_PLAYLIST_CHANGED = 3;
    public static final int MEDIA_ITEM_TRANSITION_REASON_REPEAT = 0;
    public static final int MEDIA_ITEM_TRANSITION_REASON_SEEK = 2;
    public static final int PLAYBACK_SUPPRESSION_REASON_NONE = 0;
    public static final int PLAYBACK_SUPPRESSION_REASON_SCRUBBING = 4;
    public static final int PLAYBACK_SUPPRESSION_REASON_TRANSIENT_AUDIO_FOCUS_LOSS = 1;
    public static final int PLAYBACK_SUPPRESSION_REASON_UNSUITABLE_AUDIO_OUTPUT = 3;

    @Deprecated
    public static final int PLAYBACK_SUPPRESSION_REASON_UNSUITABLE_AUDIO_ROUTE = 2;
    public static final int PLAY_WHEN_READY_CHANGE_REASON_AUDIO_BECOMING_NOISY = 3;
    public static final int PLAY_WHEN_READY_CHANGE_REASON_AUDIO_FOCUS_LOSS = 2;
    public static final int PLAY_WHEN_READY_CHANGE_REASON_END_OF_MEDIA_ITEM = 5;
    public static final int PLAY_WHEN_READY_CHANGE_REASON_REMOTE = 4;
    public static final int PLAY_WHEN_READY_CHANGE_REASON_SUPPRESSED_TOO_LONG = 6;
    public static final int PLAY_WHEN_READY_CHANGE_REASON_USER_REQUEST = 1;
    public static final int REPEAT_MODE_ALL = 2;
    public static final int REPEAT_MODE_OFF = 0;
    public static final int REPEAT_MODE_ONE = 1;
    public static final int STATE_BUFFERING = 2;
    public static final int STATE_ENDED = 4;
    public static final int STATE_IDLE = 1;
    public static final int STATE_READY = 3;
    public static final int TIMELINE_CHANGE_REASON_PLAYLIST_CHANGED = 0;
    public static final int TIMELINE_CHANGE_REASON_SOURCE_UPDATE = 1;

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Command {
    }

    public static final class Commands {
        public static final Commands EMPTY = new Builder().build();
        private static final String FIELD_COMMANDS = Util.intToStringMaxRadix(0);
        private final FlagSet flags;

        public static final class Builder {
            private static final int[] SUPPORTED_COMMANDS = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 31, 20, 21, 22, 23, 24, 25, 33, 26, 34, 35, 27, 28, 29, 30, 32};
            private final FlagSet.Builder flagsBuilder;

            public Builder add(int i10) {
                this.flagsBuilder.add(i10);
                return this;
            }

            public Builder addAll(int... iArr) {
                this.flagsBuilder.addAll(iArr);
                return this;
            }

            public Builder addAllCommands() {
                this.flagsBuilder.addAll(SUPPORTED_COMMANDS);
                return this;
            }

            public Builder addIf(int i10, boolean z) {
                this.flagsBuilder.addIf(i10, z);
                return this;
            }

            public Commands build() {
                return new Commands(this.flagsBuilder.build());
            }

            public Builder remove(int i10) {
                this.flagsBuilder.remove(i10);
                return this;
            }

            public Builder removeAll(int... iArr) {
                this.flagsBuilder.removeAll(iArr);
                return this;
            }

            public Builder removeIf(int i10, boolean z) {
                this.flagsBuilder.removeIf(i10, z);
                return this;
            }

            public Builder() {
                this.flagsBuilder = new FlagSet.Builder();
            }

            public Builder addAll(Commands commands) {
                this.flagsBuilder.addAll(commands.flags);
                return this;
            }

            private Builder(Commands commands) {
                FlagSet.Builder builder = new FlagSet.Builder();
                this.flagsBuilder = builder;
                builder.addAll(commands.flags);
            }
        }

        public static Commands fromBundle(Bundle bundle) {
            ArrayList<Integer> integerArrayList = bundle.getIntegerArrayList(FIELD_COMMANDS);
            if (integerArrayList == null) {
                return EMPTY;
            }
            Builder builder = new Builder();
            for (int i10 = 0; i10 < integerArrayList.size(); i10++) {
                builder.add(integerArrayList.get(i10).intValue());
            }
            return builder.build();
        }

        public Builder buildUpon() {
            return new Builder();
        }

        public boolean contains(int i10) {
            return this.flags.contains(i10);
        }

        public boolean containsAny(int... iArr) {
            return this.flags.containsAny(iArr);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Commands) {
                return this.flags.equals(((Commands) obj).flags);
            }
            return false;
        }

        public int get(int i10) {
            return this.flags.get(i10);
        }

        public int hashCode() {
            return this.flags.hashCode();
        }

        public int size() {
            return this.flags.size();
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            ArrayList<Integer> arrayList = new ArrayList<>();
            for (int i10 = 0; i10 < this.flags.size(); i10++) {
                arrayList.add(Integer.valueOf(this.flags.get(i10)));
            }
            bundle.putIntegerArrayList(FIELD_COMMANDS, arrayList);
            return bundle;
        }

        private Commands(FlagSet flagSet) {
            this.flags = flagSet;
        }
    }

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface DiscontinuityReason {
    }

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Event {
    }

    public static final class Events {
        private final FlagSet flags;

        public Events(FlagSet flagSet) {
            this.flags = flagSet;
        }

        public boolean contains(int i10) {
            return this.flags.contains(i10);
        }

        public boolean containsAny(int... iArr) {
            return this.flags.containsAny(iArr);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Events) {
                return this.flags.equals(((Events) obj).flags);
            }
            return false;
        }

        public int get(int i10) {
            return this.flags.get(i10);
        }

        public int hashCode() {
            return this.flags.hashCode();
        }

        public int size() {
            return this.flags.size();
        }

        public boolean containsAny(Events events) {
            return this.flags.containsAny(events.flags);
        }
    }

    public interface Listener {
        void onAudioAttributesChanged(AudioAttributes audioAttributes);

        void onAudioSessionIdChanged(int i10);

        void onAvailableCommandsChanged(Commands commands);

        void onCues(CueGroup cueGroup);

        @Deprecated
        void onCues(List<Cue> list);

        void onDeviceInfoChanged(DeviceInfo deviceInfo);

        void onDeviceVolumeChanged(int i10, boolean z);

        void onEvents(Player player, Events events);

        void onIsLoadingChanged(boolean z);

        void onIsPlayingChanged(boolean z);

        @Deprecated
        void onLoadingChanged(boolean z);

        void onMaxSeekToPreviousPositionChanged(long j10);

        void onMediaItemTransition(MediaItem mediaItem, int i10);

        void onMediaMetadataChanged(MediaMetadata mediaMetadata);

        void onMetadata(Metadata metadata);

        void onPlayWhenReadyChanged(boolean z, int i10);

        void onPlaybackParametersChanged(PlaybackParameters playbackParameters);

        void onPlaybackStateChanged(int i10);

        void onPlaybackSuppressionReasonChanged(int i10);

        void onPlayerError(PlaybackException playbackException);

        void onPlayerErrorChanged(PlaybackException playbackException);

        @Deprecated
        void onPlayerStateChanged(boolean z, int i10);

        void onPlaylistMetadataChanged(MediaMetadata mediaMetadata);

        @Deprecated
        void onPositionDiscontinuity(int i10);

        void onPositionDiscontinuity(PositionInfo positionInfo, PositionInfo positionInfo2, int i10);

        void onRenderedFirstFrame();

        void onRepeatModeChanged(int i10);

        void onSeekBackIncrementChanged(long j10);

        void onSeekForwardIncrementChanged(long j10);

        void onShuffleModeEnabledChanged(boolean z);

        void onSkipSilenceEnabledChanged(boolean z);

        void onSurfaceSizeChanged(int i10, int i11);

        void onTimelineChanged(Timeline timeline, int i10);

        void onTrackSelectionParametersChanged(TrackSelectionParameters trackSelectionParameters);

        void onTracksChanged(Tracks tracks);

        void onVideoSizeChanged(VideoSize videoSize);

        void onVolumeChanged(float f10);
    }

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface MediaItemTransitionReason {
    }

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface PlayWhenReadyChangeReason {
    }

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface PlaybackSuppressionReason {
    }

    public static final class PositionInfo {
        public final int adGroupIndex;
        public final int adIndexInAdGroup;
        public final long contentPositionMs;
        public final MediaItem mediaItem;
        public final int mediaItemIndex;
        public final int periodIndex;
        public final Object periodUid;
        public final long positionMs;

        @Deprecated
        public final int windowIndex;
        public final Object windowUid;
        static final String FIELD_MEDIA_ITEM_INDEX = Util.intToStringMaxRadix(0);
        private static final String FIELD_MEDIA_ITEM = Util.intToStringMaxRadix(1);
        static final String FIELD_PERIOD_INDEX = Util.intToStringMaxRadix(2);
        static final String FIELD_POSITION_MS = Util.intToStringMaxRadix(3);
        static final String FIELD_CONTENT_POSITION_MS = Util.intToStringMaxRadix(4);
        private static final String FIELD_AD_GROUP_INDEX = Util.intToStringMaxRadix(5);
        private static final String FIELD_AD_INDEX_IN_AD_GROUP = Util.intToStringMaxRadix(6);

        @Deprecated
        public PositionInfo(Object obj, int i10, Object obj2, int i11, long j10, long j11, int i12, int i13) {
            this(obj, i10, MediaItem.EMPTY, obj2, i11, j10, j11, i12, i13);
        }

        public static PositionInfo fromBundle(Bundle bundle) {
            int iMax = Math.max(0, bundle.getInt(FIELD_MEDIA_ITEM_INDEX, 0));
            Bundle bundle2 = bundle.getBundle(FIELD_MEDIA_ITEM);
            return new PositionInfo(null, iMax, bundle2 == null ? null : MediaItem.fromBundle(bundle2), null, Math.max(0, bundle.getInt(FIELD_PERIOD_INDEX, 0)), bundle.getLong(FIELD_POSITION_MS, 0L), bundle.getLong(FIELD_CONTENT_POSITION_MS, 0L), bundle.getInt(FIELD_AD_GROUP_INDEX, -1), bundle.getInt(FIELD_AD_INDEX_IN_AD_GROUP, -1));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && PositionInfo.class == obj.getClass()) {
                PositionInfo positionInfo = (PositionInfo) obj;
                if (equalsForBundling(positionInfo) && Objects.equals(this.windowUid, positionInfo.windowUid) && Objects.equals(this.periodUid, positionInfo.periodUid)) {
                    return true;
                }
            }
            return false;
        }

        public boolean equalsForBundling(PositionInfo positionInfo) {
            return this.mediaItemIndex == positionInfo.mediaItemIndex && this.periodIndex == positionInfo.periodIndex && this.positionMs == positionInfo.positionMs && this.contentPositionMs == positionInfo.contentPositionMs && this.adGroupIndex == positionInfo.adGroupIndex && this.adIndexInAdGroup == positionInfo.adIndexInAdGroup && Objects.equals(this.mediaItem, positionInfo.mediaItem);
        }

        public PositionInfo filterByAvailableCommands(boolean z, boolean z5) {
            if (z && z5) {
                return this;
            }
            return new PositionInfo(this.windowUid, z5 ? this.mediaItemIndex : 0, z ? this.mediaItem : null, this.periodUid, z5 ? this.periodIndex : 0, z ? this.positionMs : 0L, z ? this.contentPositionMs : 0L, z ? this.adGroupIndex : -1, z ? this.adIndexInAdGroup : -1);
        }

        public int hashCode() {
            return Objects.hash(this.windowUid, Integer.valueOf(this.mediaItemIndex), this.mediaItem, this.periodUid, Integer.valueOf(this.periodIndex), Long.valueOf(this.positionMs), Long.valueOf(this.contentPositionMs), Integer.valueOf(this.adGroupIndex), Integer.valueOf(this.adIndexInAdGroup));
        }

        public Bundle toBundle(int i10) {
            Bundle bundle = new Bundle();
            if (i10 < 3 || this.mediaItemIndex != 0) {
                bundle.putInt(FIELD_MEDIA_ITEM_INDEX, this.mediaItemIndex);
            }
            MediaItem mediaItem = this.mediaItem;
            if (mediaItem != null) {
                bundle.putBundle(FIELD_MEDIA_ITEM, mediaItem.toBundle());
            }
            if (i10 < 3 || this.periodIndex != 0) {
                bundle.putInt(FIELD_PERIOD_INDEX, this.periodIndex);
            }
            if (i10 < 3 || this.positionMs != 0) {
                bundle.putLong(FIELD_POSITION_MS, this.positionMs);
            }
            if (i10 < 3 || this.contentPositionMs != 0) {
                bundle.putLong(FIELD_CONTENT_POSITION_MS, this.contentPositionMs);
            }
            int i11 = this.adGroupIndex;
            if (i11 != -1) {
                bundle.putInt(FIELD_AD_GROUP_INDEX, i11);
            }
            int i12 = this.adIndexInAdGroup;
            if (i12 != -1) {
                bundle.putInt(FIELD_AD_INDEX_IN_AD_GROUP, i12);
            }
            return bundle;
        }

        public String toString() {
            String str = "mediaItem=" + this.mediaItemIndex + ", period=" + this.periodIndex + ", pos=" + this.positionMs;
            if (this.adGroupIndex == -1) {
                return str;
            }
            StringBuilder sbP = a2.p(str, ", contentPos=");
            sbP.append(this.contentPositionMs);
            sbP.append(", adGroup=");
            sbP.append(this.adGroupIndex);
            sbP.append(", ad=");
            sbP.append(this.adIndexInAdGroup);
            return sbP.toString();
        }

        public PositionInfo(Object obj, int i10, MediaItem mediaItem, Object obj2, int i11, long j10, long j11, int i12, int i13) {
            ac.b.j(i10 >= 0);
            ac.b.j(i11 >= 0);
            this.windowUid = obj;
            this.windowIndex = i10;
            this.mediaItemIndex = i10;
            this.mediaItem = mediaItem;
            this.periodUid = obj2;
            this.periodIndex = i11;
            this.positionMs = j10;
            this.contentPositionMs = j11;
            this.adGroupIndex = i12;
            this.adIndexInAdGroup = i13;
        }

        @Deprecated
        public Bundle toBundle() {
            return toBundle(Integer.MAX_VALUE);
        }
    }

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface RepeatMode {
    }

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface State {
    }

    @Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface TimelineChangeReason {
    }

    void addListener(Listener listener);

    void addMediaItem(int i10, MediaItem mediaItem);

    void addMediaItem(MediaItem mediaItem);

    void addMediaItems(int i10, List<MediaItem> list);

    void addMediaItems(List<MediaItem> list);

    boolean canAdvertiseSession();

    void clearMediaItems();

    void clearVideoSurface();

    void clearVideoSurface(Surface surface);

    void clearVideoSurfaceHolder(SurfaceHolder surfaceHolder);

    void clearVideoSurfaceView(SurfaceView surfaceView);

    void clearVideoTextureView(TextureView textureView);

    @Deprecated
    void decreaseDeviceVolume();

    void decreaseDeviceVolume(int i10);

    Looper getApplicationLooper();

    AudioAttributes getAudioAttributes();

    int getAudioSessionId();

    Commands getAvailableCommands();

    int getBufferedPercentage();

    long getBufferedPosition();

    long getContentBufferedPosition();

    long getContentDuration();

    long getContentPosition();

    int getCurrentAdGroupIndex();

    int getCurrentAdIndexInAdGroup();

    CueGroup getCurrentCues();

    long getCurrentLiveOffset();

    Object getCurrentManifest();

    MediaItem getCurrentMediaItem();

    int getCurrentMediaItemIndex();

    int getCurrentPeriodIndex();

    long getCurrentPosition();

    Timeline getCurrentTimeline();

    Tracks getCurrentTracks();

    @Deprecated
    int getCurrentWindowIndex();

    DeviceInfo getDeviceInfo();

    int getDeviceVolume();

    long getDuration();

    long getMaxSeekToPreviousPosition();

    MediaItem getMediaItemAt(int i10);

    int getMediaItemCount();

    MediaMetadata getMediaMetadata();

    int getNextMediaItemIndex();

    @Deprecated
    int getNextWindowIndex();

    boolean getPlayWhenReady();

    PlaybackParameters getPlaybackParameters();

    int getPlaybackState();

    int getPlaybackSuppressionReason();

    PlaybackException getPlayerError();

    MediaMetadata getPlaylistMetadata();

    int getPreviousMediaItemIndex();

    @Deprecated
    int getPreviousWindowIndex();

    int getRepeatMode();

    long getSeekBackIncrement();

    long getSeekForwardIncrement();

    boolean getShuffleModeEnabled();

    Size getSurfaceSize();

    long getTotalBufferedDuration();

    TrackSelectionParameters getTrackSelectionParameters();

    VideoSize getVideoSize();

    float getVolume();

    boolean hasNextMediaItem();

    boolean hasPreviousMediaItem();

    @Deprecated
    void increaseDeviceVolume();

    void increaseDeviceVolume(int i10);

    boolean isCommandAvailable(int i10);

    boolean isCurrentMediaItemDynamic();

    boolean isCurrentMediaItemLive();

    boolean isCurrentMediaItemSeekable();

    @Deprecated
    boolean isCurrentWindowDynamic();

    @Deprecated
    boolean isCurrentWindowLive();

    @Deprecated
    boolean isCurrentWindowSeekable();

    boolean isDeviceMuted();

    boolean isLoading();

    boolean isPlaying();

    boolean isPlayingAd();

    void moveMediaItem(int i10, int i11);

    void moveMediaItems(int i10, int i11, int i12);

    void mute();

    void pause();

    void play();

    void prepare();

    void release();

    void removeListener(Listener listener);

    void removeMediaItem(int i10);

    void removeMediaItems(int i10, int i11);

    void replaceMediaItem(int i10, MediaItem mediaItem);

    void replaceMediaItems(int i10, int i11, List<MediaItem> list);

    void seekBack();

    void seekForward();

    void seekTo(int i10, long j10);

    void seekTo(long j10);

    void seekToDefaultPosition();

    void seekToDefaultPosition(int i10);

    void seekToNext();

    void seekToNextMediaItem();

    void seekToPrevious();

    void seekToPreviousMediaItem();

    void setAudioAttributes(AudioAttributes audioAttributes, boolean z);

    @Deprecated
    void setDeviceMuted(boolean z);

    void setDeviceMuted(boolean z, int i10);

    @Deprecated
    void setDeviceVolume(int i10);

    void setDeviceVolume(int i10, int i11);

    void setMediaItem(MediaItem mediaItem);

    void setMediaItem(MediaItem mediaItem, long j10);

    void setMediaItem(MediaItem mediaItem, boolean z);

    void setMediaItems(List<MediaItem> list);

    void setMediaItems(List<MediaItem> list, int i10, long j10);

    void setMediaItems(List<MediaItem> list, boolean z);

    void setPlayWhenReady(boolean z);

    void setPlaybackParameters(PlaybackParameters playbackParameters);

    void setPlaybackSpeed(float f10);

    void setPlaylistMetadata(MediaMetadata mediaMetadata);

    void setRepeatMode(int i10);

    void setShuffleModeEnabled(boolean z);

    void setTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters);

    void setVideoSurface(Surface surface);

    void setVideoSurfaceHolder(SurfaceHolder surfaceHolder);

    void setVideoSurfaceView(SurfaceView surfaceView);

    void setVideoTextureView(TextureView textureView);

    void setVolume(float f10);

    void stop();

    void unmute();
}

package androidx.media3.session;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.C;
import androidx.media3.common.DeviceInfo;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.PlaybackException;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.Player;
import androidx.media3.common.Timeline;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.common.VideoSize;
import androidx.media3.common.text.CueGroup;
import androidx.media3.common.util.Util;
import j$.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
class PlayerInfo {
    public static final PlayerInfo DEFAULT;
    public static final int DISCONTINUITY_REASON_DEFAULT = 0;
    private static final String FIELD_AUDIO_ATTRIBUTES;
    private static final String FIELD_AUDIO_SESSION_ID;
    private static final String FIELD_CUE_GROUP;
    private static final String FIELD_CURRENT_TRACKS;
    private static final String FIELD_DEVICE_INFO;
    private static final String FIELD_DEVICE_MUTED;
    private static final String FIELD_DEVICE_VOLUME;
    private static final String FIELD_DISCONTINUITY_REASON;
    private static final String FIELD_IN_PROCESS_BINDER;
    private static final String FIELD_IS_LOADING;
    private static final String FIELD_IS_PLAYING;
    static final String FIELD_MAX_SEEK_TO_PREVIOUS_POSITION_MS;
    private static final String FIELD_MEDIA_ITEM_TRANSITION_REASON;
    private static final String FIELD_MEDIA_METADATA;
    static final String FIELD_NEW_POSITION_INFO;
    static final String FIELD_OLD_POSITION_INFO;
    private static final String FIELD_PLAYBACK_ERROR;
    private static final String FIELD_PLAYBACK_PARAMETERS;
    private static final String FIELD_PLAYBACK_STATE;
    private static final String FIELD_PLAYBACK_SUPPRESSION_REASON;
    private static final String FIELD_PLAYLIST_METADATA;
    private static final String FIELD_PLAY_WHEN_READY;
    private static final String FIELD_PLAY_WHEN_READY_CHANGE_REASON;
    private static final String FIELD_REPEAT_MODE;
    static final String FIELD_SEEK_BACK_INCREMENT_MS;
    static final String FIELD_SEEK_FORWARD_INCREMENT_MS;
    static final String FIELD_SESSION_POSITION_INFO;
    private static final String FIELD_SHUFFLE_MODE_ENABLED;
    private static final String FIELD_TIMELINE;
    private static final String FIELD_TIMELINE_CHANGE_REASON;
    private static final String FIELD_TRACK_SELECTION_PARAMETERS;
    private static final String FIELD_UNMUTE_VOLUME;
    private static final String FIELD_VIDEO_SIZE;
    private static final String FIELD_VOLUME;
    public static final int MEDIA_ITEM_TRANSITION_REASON_DEFAULT = 0;
    public static final int PLAY_WHEN_READY_CHANGE_REASON_DEFAULT = 1;
    public static final int TIMELINE_CHANGE_REASON_DEFAULT = 0;
    public final AudioAttributes audioAttributes;
    public final int audioSessionId;
    public final CueGroup cueGroup;
    public final Tracks currentTracks;
    public final DeviceInfo deviceInfo;
    public final boolean deviceMuted;
    public final int deviceVolume;
    public final int discontinuityReason;
    public final boolean isLoading;
    public final boolean isPlaying;
    public final long maxSeekToPreviousPositionMs;
    public final int mediaItemTransitionReason;
    public final MediaMetadata mediaMetadata;
    public final Player.PositionInfo newPositionInfo;
    public final Player.PositionInfo oldPositionInfo;
    public final boolean playWhenReady;
    public final int playWhenReadyChangeReason;
    public final PlaybackParameters playbackParameters;
    public final int playbackState;
    public final int playbackSuppressionReason;
    public final PlaybackException playerError;
    public final MediaMetadata playlistMetadata;
    public final int repeatMode;
    public final long seekBackIncrementMs;
    public final long seekForwardIncrementMs;
    public final SessionPositionInfo sessionPositionInfo;
    public final boolean shuffleModeEnabled;
    public final Timeline timeline;
    public final int timelineChangeReason;
    public final TrackSelectionParameters trackSelectionParameters;
    public final float unmuteVolume;
    public final VideoSize videoSize;
    public final float volume;

    public static class Builder {
        private AudioAttributes audioAttributes;
        private int audioSessionId;
        private CueGroup cueGroup;
        private Tracks currentTracks;
        private DeviceInfo deviceInfo;
        private boolean deviceMuted;
        private int deviceVolume;
        private int discontinuityReason;
        private boolean isLoading;
        private boolean isPlaying;
        private long maxSeekToPreviousPositionMs;
        private int mediaItemTransitionReason;
        private MediaMetadata mediaMetadata;
        private Player.PositionInfo newPositionInfo;
        private Player.PositionInfo oldPositionInfo;
        private boolean playWhenReady;
        private int playWhenReadyChangeReason;
        private PlaybackParameters playbackParameters;
        private int playbackState;
        private int playbackSuppressionReason;
        private PlaybackException playerError;
        private MediaMetadata playlistMetadata;
        private int repeatMode;
        private long seekBackIncrementMs;
        private long seekForwardIncrementMs;
        private SessionPositionInfo sessionPositionInfo;
        private boolean shuffleModeEnabled;
        private Timeline timeline;
        private int timelineChangeReason;
        private TrackSelectionParameters trackSelectionParameters;
        private float unmuteVolume;
        private VideoSize videoSize;
        private float volume;

        public Builder(PlayerInfo playerInfo) {
            this.playerError = playerInfo.playerError;
            this.mediaItemTransitionReason = playerInfo.mediaItemTransitionReason;
            this.sessionPositionInfo = playerInfo.sessionPositionInfo;
            this.oldPositionInfo = playerInfo.oldPositionInfo;
            this.newPositionInfo = playerInfo.newPositionInfo;
            this.discontinuityReason = playerInfo.discontinuityReason;
            this.playbackParameters = playerInfo.playbackParameters;
            this.repeatMode = playerInfo.repeatMode;
            this.shuffleModeEnabled = playerInfo.shuffleModeEnabled;
            this.timeline = playerInfo.timeline;
            this.timelineChangeReason = playerInfo.timelineChangeReason;
            this.videoSize = playerInfo.videoSize;
            this.playlistMetadata = playerInfo.playlistMetadata;
            this.volume = playerInfo.volume;
            this.unmuteVolume = playerInfo.unmuteVolume;
            this.audioSessionId = playerInfo.audioSessionId;
            this.audioAttributes = playerInfo.audioAttributes;
            this.cueGroup = playerInfo.cueGroup;
            this.deviceInfo = playerInfo.deviceInfo;
            this.deviceVolume = playerInfo.deviceVolume;
            this.deviceMuted = playerInfo.deviceMuted;
            this.playWhenReady = playerInfo.playWhenReady;
            this.playWhenReadyChangeReason = playerInfo.playWhenReadyChangeReason;
            this.isPlaying = playerInfo.isPlaying;
            this.isLoading = playerInfo.isLoading;
            this.playbackSuppressionReason = playerInfo.playbackSuppressionReason;
            this.playbackState = playerInfo.playbackState;
            this.mediaMetadata = playerInfo.mediaMetadata;
            this.seekBackIncrementMs = playerInfo.seekBackIncrementMs;
            this.seekForwardIncrementMs = playerInfo.seekForwardIncrementMs;
            this.maxSeekToPreviousPositionMs = playerInfo.maxSeekToPreviousPositionMs;
            this.currentTracks = playerInfo.currentTracks;
            this.trackSelectionParameters = playerInfo.trackSelectionParameters;
        }

        public PlayerInfo build() {
            ac.b.s(this.timeline.isEmpty() || this.sessionPositionInfo.positionInfo.mediaItemIndex < this.timeline.getWindowCount());
            return new PlayerInfo(this.playerError, this.mediaItemTransitionReason, this.sessionPositionInfo, this.oldPositionInfo, this.newPositionInfo, this.discontinuityReason, this.playbackParameters, this.repeatMode, this.shuffleModeEnabled, this.videoSize, this.timeline, this.timelineChangeReason, this.playlistMetadata, this.volume, this.unmuteVolume, this.audioAttributes, this.audioSessionId, this.cueGroup, this.deviceInfo, this.deviceVolume, this.deviceMuted, this.playWhenReady, this.playWhenReadyChangeReason, this.playbackSuppressionReason, this.playbackState, this.isPlaying, this.isLoading, this.mediaMetadata, this.seekBackIncrementMs, this.seekForwardIncrementMs, this.maxSeekToPreviousPositionMs, this.currentTracks, this.trackSelectionParameters);
        }

        public Builder setAudioAttributes(AudioAttributes audioAttributes) {
            this.audioAttributes = audioAttributes;
            return this;
        }

        public Builder setAudioSessionId(int i10) {
            this.audioSessionId = i10;
            return this;
        }

        public Builder setCues(CueGroup cueGroup) {
            this.cueGroup = cueGroup;
            return this;
        }

        public Builder setCurrentTracks(Tracks tracks) {
            this.currentTracks = tracks;
            return this;
        }

        public Builder setDeviceInfo(DeviceInfo deviceInfo) {
            this.deviceInfo = deviceInfo;
            return this;
        }

        public Builder setDeviceMuted(boolean z) {
            this.deviceMuted = z;
            return this;
        }

        public Builder setDeviceVolume(int i10) {
            this.deviceVolume = i10;
            return this;
        }

        public Builder setDiscontinuityReason(int i10) {
            this.discontinuityReason = i10;
            return this;
        }

        public Builder setIsLoading(boolean z) {
            this.isLoading = z;
            return this;
        }

        public Builder setIsPlaying(boolean z) {
            this.isPlaying = z;
            return this;
        }

        public Builder setMaxSeekToPreviousPositionMs(long j10) {
            this.maxSeekToPreviousPositionMs = j10;
            return this;
        }

        public Builder setMediaItemTransitionReason(int i10) {
            this.mediaItemTransitionReason = i10;
            return this;
        }

        public Builder setMediaMetadata(MediaMetadata mediaMetadata) {
            this.mediaMetadata = mediaMetadata;
            return this;
        }

        public Builder setNewPositionInfo(Player.PositionInfo positionInfo) {
            this.newPositionInfo = positionInfo;
            return this;
        }

        public Builder setOldPositionInfo(Player.PositionInfo positionInfo) {
            this.oldPositionInfo = positionInfo;
            return this;
        }

        public Builder setPlayWhenReady(boolean z) {
            this.playWhenReady = z;
            return this;
        }

        public Builder setPlayWhenReadyChangeReason(int i10) {
            this.playWhenReadyChangeReason = i10;
            return this;
        }

        public Builder setPlaybackParameters(PlaybackParameters playbackParameters) {
            this.playbackParameters = playbackParameters;
            return this;
        }

        public Builder setPlaybackState(int i10) {
            this.playbackState = i10;
            return this;
        }

        public Builder setPlaybackSuppressionReason(int i10) {
            this.playbackSuppressionReason = i10;
            return this;
        }

        public Builder setPlayerError(PlaybackException playbackException) {
            this.playerError = playbackException;
            return this;
        }

        public Builder setPlaylistMetadata(MediaMetadata mediaMetadata) {
            this.playlistMetadata = mediaMetadata;
            return this;
        }

        public Builder setRepeatMode(int i10) {
            this.repeatMode = i10;
            return this;
        }

        public Builder setSeekBackIncrement(long j10) {
            this.seekBackIncrementMs = j10;
            return this;
        }

        public Builder setSeekForwardIncrement(long j10) {
            this.seekForwardIncrementMs = j10;
            return this;
        }

        public Builder setSessionPositionInfo(SessionPositionInfo sessionPositionInfo) {
            this.sessionPositionInfo = sessionPositionInfo;
            return this;
        }

        public Builder setShuffleModeEnabled(boolean z) {
            this.shuffleModeEnabled = z;
            return this;
        }

        public Builder setTimeline(Timeline timeline) {
            this.timeline = timeline;
            return this;
        }

        public Builder setTimelineChangeReason(int i10) {
            this.timelineChangeReason = i10;
            return this;
        }

        public Builder setTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
            this.trackSelectionParameters = trackSelectionParameters;
            return this;
        }

        public Builder setUnmuteVolume(float f10) {
            this.unmuteVolume = f10;
            return this;
        }

        public Builder setVideoSize(VideoSize videoSize) {
            this.videoSize = videoSize;
            return this;
        }

        public Builder setVolume(float f10) {
            this.unmuteVolume = f10 != 0.0f ? f10 : this.volume;
            this.volume = f10;
            return this;
        }
    }

    public static class BundlingExclusions {
        public final boolean areCurrentTracksExcluded;
        public final boolean isTimelineExcluded;
        public static final BundlingExclusions NONE = new BundlingExclusions(false, false);
        private static final String FIELD_IS_TIMELINE_EXCLUDED = Util.intToStringMaxRadix(0);
        private static final String FIELD_ARE_CURRENT_TRACKS_EXCLUDED = Util.intToStringMaxRadix(1);

        public BundlingExclusions(boolean z, boolean z5) {
            this.isTimelineExcluded = z;
            this.areCurrentTracksExcluded = z5;
        }

        public static BundlingExclusions fromBundle(Bundle bundle) {
            return new BundlingExclusions(bundle.getBoolean(FIELD_IS_TIMELINE_EXCLUDED, false), bundle.getBoolean(FIELD_ARE_CURRENT_TRACKS_EXCLUDED, false));
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BundlingExclusions)) {
                return false;
            }
            BundlingExclusions bundlingExclusions = (BundlingExclusions) obj;
            return this.isTimelineExcluded == bundlingExclusions.isTimelineExcluded && this.areCurrentTracksExcluded == bundlingExclusions.areCurrentTracksExcluded;
        }

        public int hashCode() {
            return Objects.hash(Boolean.valueOf(this.isTimelineExcluded), Boolean.valueOf(this.areCurrentTracksExcluded));
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putBoolean(FIELD_IS_TIMELINE_EXCLUDED, this.isTimelineExcluded);
            bundle.putBoolean(FIELD_ARE_CURRENT_TRACKS_EXCLUDED, this.areCurrentTracksExcluded);
            return bundle;
        }
    }

    public final class InProcessBinder extends Binder {
        private InProcessBinder() {
        }

        public PlayerInfo getPlayerInfo() {
            return PlayerInfo.this;
        }
    }

    static {
        SessionPositionInfo sessionPositionInfo = SessionPositionInfo.DEFAULT;
        Player.PositionInfo positionInfo = SessionPositionInfo.DEFAULT_POSITION_INFO;
        PlaybackParameters playbackParameters = PlaybackParameters.DEFAULT;
        VideoSize videoSize = VideoSize.UNKNOWN;
        Timeline timeline = Timeline.EMPTY;
        MediaMetadata mediaMetadata = MediaMetadata.EMPTY;
        DEFAULT = new PlayerInfo(null, 0, sessionPositionInfo, positionInfo, positionInfo, 0, playbackParameters, 0, false, videoSize, timeline, 0, mediaMetadata, 1.0f, 1.0f, AudioAttributes.DEFAULT, 0, CueGroup.EMPTY_TIME_ZERO, DeviceInfo.UNKNOWN, 0, false, false, 1, 0, 1, false, false, mediaMetadata, 5000L, 15000L, C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS, Tracks.EMPTY, TrackSelectionParameters.DEFAULT);
        FIELD_PLAYBACK_PARAMETERS = Util.intToStringMaxRadix(1);
        FIELD_REPEAT_MODE = Util.intToStringMaxRadix(2);
        FIELD_SHUFFLE_MODE_ENABLED = Util.intToStringMaxRadix(3);
        FIELD_TIMELINE = Util.intToStringMaxRadix(4);
        FIELD_VIDEO_SIZE = Util.intToStringMaxRadix(5);
        FIELD_PLAYLIST_METADATA = Util.intToStringMaxRadix(6);
        FIELD_VOLUME = Util.intToStringMaxRadix(7);
        FIELD_UNMUTE_VOLUME = Util.intToStringMaxRadix(33);
        FIELD_AUDIO_ATTRIBUTES = Util.intToStringMaxRadix(8);
        FIELD_DEVICE_INFO = Util.intToStringMaxRadix(9);
        FIELD_DEVICE_VOLUME = Util.intToStringMaxRadix(10);
        FIELD_DEVICE_MUTED = Util.intToStringMaxRadix(11);
        FIELD_PLAY_WHEN_READY = Util.intToStringMaxRadix(12);
        FIELD_PLAY_WHEN_READY_CHANGE_REASON = Util.intToStringMaxRadix(13);
        FIELD_PLAYBACK_SUPPRESSION_REASON = Util.intToStringMaxRadix(14);
        FIELD_PLAYBACK_STATE = Util.intToStringMaxRadix(15);
        FIELD_IS_PLAYING = Util.intToStringMaxRadix(16);
        FIELD_IS_LOADING = Util.intToStringMaxRadix(17);
        FIELD_PLAYBACK_ERROR = Util.intToStringMaxRadix(18);
        FIELD_SESSION_POSITION_INFO = Util.intToStringMaxRadix(19);
        FIELD_MEDIA_ITEM_TRANSITION_REASON = Util.intToStringMaxRadix(20);
        FIELD_OLD_POSITION_INFO = Util.intToStringMaxRadix(21);
        FIELD_NEW_POSITION_INFO = Util.intToStringMaxRadix(22);
        FIELD_DISCONTINUITY_REASON = Util.intToStringMaxRadix(23);
        FIELD_CUE_GROUP = Util.intToStringMaxRadix(24);
        FIELD_MEDIA_METADATA = Util.intToStringMaxRadix(25);
        FIELD_SEEK_BACK_INCREMENT_MS = Util.intToStringMaxRadix(26);
        FIELD_SEEK_FORWARD_INCREMENT_MS = Util.intToStringMaxRadix(27);
        FIELD_MAX_SEEK_TO_PREVIOUS_POSITION_MS = Util.intToStringMaxRadix(28);
        FIELD_TRACK_SELECTION_PARAMETERS = Util.intToStringMaxRadix(29);
        FIELD_CURRENT_TRACKS = Util.intToStringMaxRadix(30);
        FIELD_TIMELINE_CHANGE_REASON = Util.intToStringMaxRadix(31);
        FIELD_IN_PROCESS_BINDER = Util.intToStringMaxRadix(32);
        FIELD_AUDIO_SESSION_ID = Util.intToStringMaxRadix(34);
    }

    public PlayerInfo(PlaybackException playbackException, int i10, SessionPositionInfo sessionPositionInfo, Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i11, PlaybackParameters playbackParameters, int i12, boolean z, VideoSize videoSize, Timeline timeline, int i13, MediaMetadata mediaMetadata, float f10, float f11, AudioAttributes audioAttributes, int i14, CueGroup cueGroup, DeviceInfo deviceInfo, int i15, boolean z5, boolean z10, int i16, int i17, int i18, boolean z11, boolean z12, MediaMetadata mediaMetadata2, long j10, long j11, long j12, Tracks tracks, TrackSelectionParameters trackSelectionParameters) {
        this.playerError = playbackException;
        this.mediaItemTransitionReason = i10;
        this.sessionPositionInfo = sessionPositionInfo;
        this.oldPositionInfo = positionInfo;
        this.newPositionInfo = positionInfo2;
        this.discontinuityReason = i11;
        this.playbackParameters = playbackParameters;
        this.repeatMode = i12;
        this.shuffleModeEnabled = z;
        this.videoSize = videoSize;
        this.timeline = timeline;
        this.timelineChangeReason = i13;
        this.playlistMetadata = mediaMetadata;
        this.volume = f10;
        this.unmuteVolume = f11;
        this.audioSessionId = i14;
        this.audioAttributes = audioAttributes;
        this.cueGroup = cueGroup;
        this.deviceInfo = deviceInfo;
        this.deviceVolume = i15;
        this.deviceMuted = z5;
        this.playWhenReady = z10;
        this.playWhenReadyChangeReason = i16;
        this.playbackSuppressionReason = i17;
        this.playbackState = i18;
        this.isPlaying = z11;
        this.isLoading = z12;
        this.mediaMetadata = mediaMetadata2;
        this.seekBackIncrementMs = j10;
        this.seekForwardIncrementMs = j11;
        this.maxSeekToPreviousPositionMs = j12;
        this.currentTracks = tracks;
        this.trackSelectionParameters = trackSelectionParameters;
    }

    public static PlayerInfo fromBundle(Bundle bundle, int i10) {
        PlaybackException playbackException;
        long j10;
        IBinder binder = bundle.getBinder(FIELD_IN_PROCESS_BINDER);
        if (binder instanceof InProcessBinder) {
            return ((InProcessBinder) binder).getPlayerInfo();
        }
        Bundle bundle2 = bundle.getBundle(FIELD_PLAYBACK_ERROR);
        PlaybackException playbackExceptionFromBundle = bundle2 == null ? null : PlaybackException.fromBundle(bundle2);
        int i11 = bundle.getInt(FIELD_MEDIA_ITEM_TRANSITION_REASON, 0);
        Bundle bundle3 = bundle.getBundle(FIELD_SESSION_POSITION_INFO);
        SessionPositionInfo sessionPositionInfoFromBundle = bundle3 == null ? SessionPositionInfo.DEFAULT : SessionPositionInfo.fromBundle(bundle3);
        Bundle bundle4 = bundle.getBundle(FIELD_OLD_POSITION_INFO);
        Player.PositionInfo positionInfoFromBundle = bundle4 == null ? SessionPositionInfo.DEFAULT_POSITION_INFO : Player.PositionInfo.fromBundle(bundle4);
        Bundle bundle5 = bundle.getBundle(FIELD_NEW_POSITION_INFO);
        Player.PositionInfo positionInfoFromBundle2 = bundle5 == null ? SessionPositionInfo.DEFAULT_POSITION_INFO : Player.PositionInfo.fromBundle(bundle5);
        int i12 = bundle.getInt(FIELD_DISCONTINUITY_REASON, 0);
        Bundle bundle6 = bundle.getBundle(FIELD_PLAYBACK_PARAMETERS);
        PlaybackParameters playbackParametersFromBundle = bundle6 == null ? PlaybackParameters.DEFAULT : PlaybackParameters.fromBundle(bundle6);
        int i13 = bundle.getInt(FIELD_REPEAT_MODE, 0);
        boolean z = bundle.getBoolean(FIELD_SHUFFLE_MODE_ENABLED, false);
        Bundle bundle7 = bundle.getBundle(FIELD_TIMELINE);
        Timeline timelineFromBundle = bundle7 == null ? Timeline.EMPTY : Timeline.fromBundle(bundle7);
        int i14 = bundle.getInt(FIELD_TIMELINE_CHANGE_REASON, 0);
        Bundle bundle8 = bundle.getBundle(FIELD_VIDEO_SIZE);
        VideoSize videoSizeFromBundle = bundle8 == null ? VideoSize.UNKNOWN : VideoSize.fromBundle(bundle8);
        Bundle bundle9 = bundle.getBundle(FIELD_PLAYLIST_METADATA);
        MediaMetadata mediaMetadataFromBundle = bundle9 == null ? MediaMetadata.EMPTY : MediaMetadata.fromBundle(bundle9);
        float f10 = bundle.getFloat(FIELD_VOLUME, 1.0f);
        float f11 = bundle.getFloat(FIELD_UNMUTE_VOLUME, 1.0f);
        int i15 = bundle.getInt(FIELD_AUDIO_SESSION_ID, 0);
        Bundle bundle10 = bundle.getBundle(FIELD_AUDIO_ATTRIBUTES);
        AudioAttributes audioAttributesFromBundle = bundle10 == null ? AudioAttributes.DEFAULT : AudioAttributes.fromBundle(bundle10);
        Bundle bundle11 = bundle.getBundle(FIELD_CUE_GROUP);
        CueGroup cueGroupFromBundle = bundle11 == null ? CueGroup.EMPTY_TIME_ZERO : CueGroup.fromBundle(bundle11);
        Bundle bundle12 = bundle.getBundle(FIELD_DEVICE_INFO);
        DeviceInfo deviceInfoFromBundle = bundle12 == null ? DeviceInfo.UNKNOWN : DeviceInfo.fromBundle(bundle12);
        AudioAttributes audioAttributes = audioAttributesFromBundle;
        int i16 = bundle.getInt(FIELD_DEVICE_VOLUME, 0);
        boolean z5 = bundle.getBoolean(FIELD_DEVICE_MUTED, false);
        boolean z10 = bundle.getBoolean(FIELD_PLAY_WHEN_READY, false);
        int i17 = bundle.getInt(FIELD_PLAY_WHEN_READY_CHANGE_REASON, 1);
        int i18 = bundle.getInt(FIELD_PLAYBACK_SUPPRESSION_REASON, 0);
        int i19 = bundle.getInt(FIELD_PLAYBACK_STATE, 1);
        boolean z11 = bundle.getBoolean(FIELD_IS_PLAYING, false);
        boolean z12 = bundle.getBoolean(FIELD_IS_LOADING, false);
        Bundle bundle13 = bundle.getBundle(FIELD_MEDIA_METADATA);
        MediaMetadata mediaMetadataFromBundle2 = bundle13 == null ? MediaMetadata.EMPTY : MediaMetadata.fromBundle(bundle13);
        String str = FIELD_SEEK_BACK_INCREMENT_MS;
        if (i10 < 4) {
            playbackException = playbackExceptionFromBundle;
            j10 = 0;
        } else {
            playbackException = playbackExceptionFromBundle;
            j10 = 5000;
        }
        long j11 = bundle.getLong(str, j10);
        long j12 = bundle.getLong(FIELD_SEEK_FORWARD_INCREMENT_MS, i10 < 4 ? 0L : 15000L);
        long j13 = bundle.getLong(FIELD_MAX_SEEK_TO_PREVIOUS_POSITION_MS, i10 >= 4 ? C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS : 0L);
        Bundle bundle14 = bundle.getBundle(FIELD_CURRENT_TRACKS);
        Tracks tracksFromBundle = bundle14 == null ? Tracks.EMPTY : Tracks.fromBundle(bundle14);
        Bundle bundle15 = bundle.getBundle(FIELD_TRACK_SELECTION_PARAMETERS);
        return new PlayerInfo(playbackException, i11, sessionPositionInfoFromBundle, positionInfoFromBundle, positionInfoFromBundle2, i12, playbackParametersFromBundle, i13, z, videoSizeFromBundle, timelineFromBundle, i14, mediaMetadataFromBundle, f10, f11, audioAttributes, i15, cueGroupFromBundle, deviceInfoFromBundle, i16, z5, z10, i17, i18, i19, z11, z12, mediaMetadataFromBundle2, j11, j12, j13, tracksFromBundle, bundle15 == null ? TrackSelectionParameters.DEFAULT : TrackSelectionParameters.fromBundle(bundle15));
    }

    private boolean isPlaying(int i10, boolean z, int i11) {
        return i10 == 3 && z && i11 == 0;
    }

    public PlayerInfo copyWithAudioAttributes(AudioAttributes audioAttributes) {
        return new Builder(this).setAudioAttributes(audioAttributes).build();
    }

    public PlayerInfo copyWithAudioSessionId(int i10) {
        return new Builder(this).setAudioSessionId(i10).build();
    }

    public PlayerInfo copyWithCurrentTracks(Tracks tracks) {
        return new Builder(this).setCurrentTracks(tracks).build();
    }

    public PlayerInfo copyWithDeviceInfo(DeviceInfo deviceInfo) {
        return new Builder(this).setDeviceInfo(deviceInfo).build();
    }

    public PlayerInfo copyWithDeviceVolume(int i10, boolean z) {
        return new Builder(this).setDeviceVolume(i10).setDeviceMuted(z).build();
    }

    public PlayerInfo copyWithIsLoading(boolean z) {
        return new Builder(this).setIsLoading(z).build();
    }

    public PlayerInfo copyWithIsPlaying(boolean z) {
        return new Builder(this).setIsPlaying(z).build();
    }

    public PlayerInfo copyWithMaxSeekToPreviousPositionMs(long j10) {
        return new Builder(this).setMaxSeekToPreviousPositionMs(j10).build();
    }

    public PlayerInfo copyWithMediaItemTransitionReason(int i10) {
        return new Builder(this).setMediaItemTransitionReason(i10).build();
    }

    public PlayerInfo copyWithMediaMetadata(MediaMetadata mediaMetadata) {
        return new Builder(this).setMediaMetadata(mediaMetadata).build();
    }

    public PlayerInfo copyWithPlayWhenReady(boolean z, int i10, int i11) {
        return new Builder(this).setPlayWhenReady(z).setPlayWhenReadyChangeReason(i10).setPlaybackSuppressionReason(i11).setIsPlaying(isPlaying(this.playbackState, z, i11)).build();
    }

    public PlayerInfo copyWithPlaybackParameters(PlaybackParameters playbackParameters) {
        return new Builder(this).setPlaybackParameters(playbackParameters).build();
    }

    public PlayerInfo copyWithPlaybackState(int i10, PlaybackException playbackException) {
        return new Builder(this).setPlayerError(playbackException).setPlaybackState(i10).setIsPlaying(isPlaying(i10, this.playWhenReady, this.playbackSuppressionReason)).build();
    }

    public PlayerInfo copyWithPlayerError(PlaybackException playbackException) {
        return new Builder(this).setPlayerError(playbackException).build();
    }

    public PlayerInfo copyWithPlaylistMetadata(MediaMetadata mediaMetadata) {
        return new Builder(this).setPlaylistMetadata(mediaMetadata).build();
    }

    public PlayerInfo copyWithPositionInfos(Player.PositionInfo positionInfo, Player.PositionInfo positionInfo2, int i10) {
        return new Builder(this).setOldPositionInfo(positionInfo).setNewPositionInfo(positionInfo2).setDiscontinuityReason(i10).build();
    }

    public PlayerInfo copyWithRepeatMode(int i10) {
        return new Builder(this).setRepeatMode(i10).build();
    }

    public PlayerInfo copyWithSeekBackIncrement(long j10) {
        return new Builder(this).setSeekBackIncrement(j10).build();
    }

    public PlayerInfo copyWithSeekForwardIncrement(long j10) {
        return new Builder(this).setSeekForwardIncrement(j10).build();
    }

    public PlayerInfo copyWithSessionPositionInfo(SessionPositionInfo sessionPositionInfo) {
        return new Builder(this).setSessionPositionInfo(sessionPositionInfo).build();
    }

    public PlayerInfo copyWithShuffleModeEnabled(boolean z) {
        return new Builder(this).setShuffleModeEnabled(z).build();
    }

    public PlayerInfo copyWithTimeline(Timeline timeline) {
        return new Builder(this).setTimeline(timeline).build();
    }

    public PlayerInfo copyWithTimelineAndMediaItemIndex(Timeline timeline, int i10, int i11) {
        Builder timelineChangeReason = new Builder(this).setTimeline(timeline).setTimelineChangeReason(i11);
        Player.PositionInfo positionInfo = this.sessionPositionInfo.positionInfo;
        Player.PositionInfo positionInfo2 = new Player.PositionInfo(positionInfo.windowUid, i10, positionInfo.mediaItem, positionInfo.periodUid, positionInfo.periodIndex, positionInfo.positionMs, positionInfo.contentPositionMs, positionInfo.adGroupIndex, positionInfo.adIndexInAdGroup);
        SessionPositionInfo sessionPositionInfo = this.sessionPositionInfo;
        return timelineChangeReason.setSessionPositionInfo(new SessionPositionInfo(positionInfo2, sessionPositionInfo.isPlayingAd, sessionPositionInfo.eventTimeMs, sessionPositionInfo.durationMs, sessionPositionInfo.bufferedPositionMs, sessionPositionInfo.bufferedPercentage, sessionPositionInfo.totalBufferedDurationMs, sessionPositionInfo.currentLiveOffsetMs, sessionPositionInfo.contentDurationMs, sessionPositionInfo.contentBufferedPositionMs)).build();
    }

    public PlayerInfo copyWithTimelineAndSessionPositionInfo(Timeline timeline, SessionPositionInfo sessionPositionInfo, int i10) {
        return new Builder(this).setTimeline(timeline).setSessionPositionInfo(sessionPositionInfo).setTimelineChangeReason(i10).build();
    }

    public PlayerInfo copyWithTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
        return new Builder(this).setTrackSelectionParameters(trackSelectionParameters).build();
    }

    public PlayerInfo copyWithUnmuteVolume(float f10) {
        return new Builder(this).setUnmuteVolume(f10).build();
    }

    public PlayerInfo copyWithVideoSize(VideoSize videoSize) {
        return new Builder(this).setVideoSize(videoSize).build();
    }

    public PlayerInfo copyWithVolume(float f10) {
        return new Builder(this).setVolume(f10).build();
    }

    public PlayerInfo filterByAvailableCommands(Player.Commands commands, boolean z, boolean z5) {
        Builder builder = new Builder(this);
        boolean zContains = commands.contains(16);
        boolean zContains2 = commands.contains(17);
        builder.setSessionPositionInfo(this.sessionPositionInfo.filterByAvailableCommands(zContains, zContains2));
        builder.setOldPositionInfo(this.oldPositionInfo.filterByAvailableCommands(zContains, zContains2));
        builder.setNewPositionInfo(this.newPositionInfo.filterByAvailableCommands(zContains, zContains2));
        if (!zContains2 && zContains && !this.timeline.isEmpty()) {
            builder.setTimeline(this.timeline.copyWithSingleWindow(this.sessionPositionInfo.positionInfo.mediaItemIndex));
        } else if (z || !zContains2) {
            builder.setTimeline(Timeline.EMPTY);
        }
        if (!commands.contains(18)) {
            builder.setPlaylistMetadata(MediaMetadata.EMPTY);
        }
        if (!commands.contains(22)) {
            builder.setVolume(1.0f);
        }
        if (!commands.contains(21)) {
            builder.setAudioAttributes(AudioAttributes.DEFAULT);
        }
        if (!commands.contains(28)) {
            builder.setCues(CueGroup.EMPTY_TIME_ZERO);
        }
        if (!commands.contains(23)) {
            builder.setDeviceVolume(0).setDeviceMuted(false);
        }
        if (!commands.contains(18)) {
            builder.setMediaMetadata(MediaMetadata.EMPTY);
        }
        if (z5 || !commands.contains(30)) {
            builder.setCurrentTracks(Tracks.EMPTY);
        }
        return builder.build();
    }

    public MediaItem getCurrentMediaItem() {
        if (this.timeline.isEmpty()) {
            return null;
        }
        return this.timeline.getWindow(this.sessionPositionInfo.positionInfo.mediaItemIndex, new Timeline.Window()).mediaItem;
    }

    public Bundle toBundleForRemoteProcess(int i10) {
        Bundle bundle = new Bundle();
        PlaybackException playbackException = this.playerError;
        if (playbackException != null) {
            bundle.putBundle(FIELD_PLAYBACK_ERROR, playbackException.toBundle());
        }
        int i11 = this.mediaItemTransitionReason;
        if (i11 != 0) {
            bundle.putInt(FIELD_MEDIA_ITEM_TRANSITION_REASON, i11);
        }
        if (i10 < 3 || !this.sessionPositionInfo.equals(SessionPositionInfo.DEFAULT)) {
            bundle.putBundle(FIELD_SESSION_POSITION_INFO, this.sessionPositionInfo.toBundle(i10));
        }
        if (i10 < 3 || !SessionPositionInfo.DEFAULT_POSITION_INFO.equalsForBundling(this.oldPositionInfo)) {
            bundle.putBundle(FIELD_OLD_POSITION_INFO, this.oldPositionInfo.toBundle(i10));
        }
        if (i10 < 3 || !SessionPositionInfo.DEFAULT_POSITION_INFO.equalsForBundling(this.newPositionInfo)) {
            bundle.putBundle(FIELD_NEW_POSITION_INFO, this.newPositionInfo.toBundle(i10));
        }
        int i12 = this.discontinuityReason;
        if (i12 != 0) {
            bundle.putInt(FIELD_DISCONTINUITY_REASON, i12);
        }
        if (!this.playbackParameters.equals(PlaybackParameters.DEFAULT)) {
            bundle.putBundle(FIELD_PLAYBACK_PARAMETERS, this.playbackParameters.toBundle());
        }
        int i13 = this.repeatMode;
        if (i13 != 0) {
            bundle.putInt(FIELD_REPEAT_MODE, i13);
        }
        boolean z = this.shuffleModeEnabled;
        if (z) {
            bundle.putBoolean(FIELD_SHUFFLE_MODE_ENABLED, z);
        }
        if (!this.timeline.equals(Timeline.EMPTY)) {
            bundle.putBundle(FIELD_TIMELINE, this.timeline.toBundle());
        }
        int i14 = this.timelineChangeReason;
        if (i14 != 0) {
            bundle.putInt(FIELD_TIMELINE_CHANGE_REASON, i14);
        }
        if (!this.videoSize.equals(VideoSize.UNKNOWN)) {
            bundle.putBundle(FIELD_VIDEO_SIZE, this.videoSize.toBundle());
        }
        MediaMetadata mediaMetadata = this.playlistMetadata;
        MediaMetadata mediaMetadata2 = MediaMetadata.EMPTY;
        if (!mediaMetadata.equals(mediaMetadata2)) {
            bundle.putBundle(FIELD_PLAYLIST_METADATA, this.playlistMetadata.toBundle());
        }
        float f10 = this.volume;
        if (f10 != 1.0f) {
            bundle.putFloat(FIELD_VOLUME, f10);
        }
        float f11 = this.unmuteVolume;
        if (f11 != 1.0f) {
            bundle.putFloat(FIELD_UNMUTE_VOLUME, f11);
        }
        int i15 = this.audioSessionId;
        if (i15 != 0) {
            bundle.putInt(FIELD_AUDIO_SESSION_ID, i15);
        }
        if (!this.audioAttributes.equals(AudioAttributes.DEFAULT)) {
            bundle.putBundle(FIELD_AUDIO_ATTRIBUTES, this.audioAttributes.toBundle());
        }
        if (!this.cueGroup.equals(CueGroup.EMPTY_TIME_ZERO)) {
            bundle.putBundle(FIELD_CUE_GROUP, this.cueGroup.toBundle());
        }
        if (!this.deviceInfo.equals(DeviceInfo.UNKNOWN)) {
            bundle.putBundle(FIELD_DEVICE_INFO, this.deviceInfo.toBundle());
        }
        int i16 = this.deviceVolume;
        if (i16 != 0) {
            bundle.putInt(FIELD_DEVICE_VOLUME, i16);
        }
        boolean z5 = this.deviceMuted;
        if (z5) {
            bundle.putBoolean(FIELD_DEVICE_MUTED, z5);
        }
        boolean z10 = this.playWhenReady;
        if (z10) {
            bundle.putBoolean(FIELD_PLAY_WHEN_READY, z10);
        }
        int i17 = this.playWhenReadyChangeReason;
        if (i17 != 1) {
            bundle.putInt(FIELD_PLAY_WHEN_READY_CHANGE_REASON, i17);
        }
        int i18 = this.playbackSuppressionReason;
        if (i18 != 0) {
            bundle.putInt(FIELD_PLAYBACK_SUPPRESSION_REASON, i18);
        }
        int i19 = this.playbackState;
        if (i19 != 1) {
            bundle.putInt(FIELD_PLAYBACK_STATE, i19);
        }
        boolean z11 = this.isPlaying;
        if (z11) {
            bundle.putBoolean(FIELD_IS_PLAYING, z11);
        }
        boolean z12 = this.isLoading;
        if (z12) {
            bundle.putBoolean(FIELD_IS_LOADING, z12);
        }
        if (!this.mediaMetadata.equals(mediaMetadata2)) {
            bundle.putBundle(FIELD_MEDIA_METADATA, this.mediaMetadata.toBundle());
        }
        long j10 = i10 < 6 ? 0L : 5000L;
        long j11 = this.seekBackIncrementMs;
        if (j11 != j10) {
            bundle.putLong(FIELD_SEEK_BACK_INCREMENT_MS, j11);
        }
        long j12 = i10 < 6 ? 0L : 15000L;
        long j13 = this.seekForwardIncrementMs;
        if (j13 != j12) {
            bundle.putLong(FIELD_SEEK_FORWARD_INCREMENT_MS, j13);
        }
        long j14 = i10 >= 6 ? C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS : 0L;
        long j15 = this.maxSeekToPreviousPositionMs;
        if (j15 != j14) {
            bundle.putLong(FIELD_MAX_SEEK_TO_PREVIOUS_POSITION_MS, j15);
        }
        if (!this.currentTracks.equals(Tracks.EMPTY)) {
            bundle.putBundle(FIELD_CURRENT_TRACKS, this.currentTracks.toBundle());
        }
        if (!this.trackSelectionParameters.equals(TrackSelectionParameters.DEFAULT)) {
            bundle.putBundle(FIELD_TRACK_SELECTION_PARAMETERS, this.trackSelectionParameters.toBundle());
        }
        return bundle;
    }

    public Bundle toBundleInProcess() {
        Bundle bundle = new Bundle();
        bundle.putBinder(FIELD_IN_PROCESS_BINDER, new InProcessBinder());
        return bundle;
    }
}

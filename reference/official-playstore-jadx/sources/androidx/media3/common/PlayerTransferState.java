package androidx.media3.common;

import com.google.common.collect.f1;
import com.google.common.collect.h1;
import com.google.common.collect.o3;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class PlayerTransferState {
    private final int currentMediaItemIndex;
    private final long currentPosition;
    private final h1 mediaItems;
    private final boolean playWhenReady;
    private final PlaybackParameters playbackParameters;
    private final int repeatMode;
    private final boolean shuffleModeEnabled;
    private final TrackSelectionParameters trackSelectionParameters;

    public static final class Builder {
        private int currentMediaItemIndex;
        private long currentPosition;
        private h1 mediaItems;
        private boolean playWhenReady;
        private PlaybackParameters playbackParameters;
        private int repeatMode;
        private boolean shuffleModeEnabled;
        private TrackSelectionParameters trackSelectionParameters;

        public PlayerTransferState build() {
            return new PlayerTransferState(this);
        }

        public Builder setCurrentMediaItemIndex(int i10) {
            this.currentMediaItemIndex = i10;
            return this;
        }

        public Builder setCurrentPosition(long j10) {
            this.currentPosition = j10;
            return this;
        }

        public Builder setMediaItems(List<MediaItem> list) {
            this.mediaItems = h1.n(list);
            return this;
        }

        public Builder setPlayWhenReady(boolean z) {
            this.playWhenReady = z;
            return this;
        }

        public Builder setPlaybackParameters(PlaybackParameters playbackParameters) {
            Objects.requireNonNull(playbackParameters);
            this.playbackParameters = playbackParameters;
            return this;
        }

        public Builder setRepeatMode(int i10) {
            this.repeatMode = i10;
            return this;
        }

        public Builder setShuffleModeEnabled(boolean z) {
            this.shuffleModeEnabled = z;
            return this;
        }

        public Builder setTrackSelectionParameters(TrackSelectionParameters trackSelectionParameters) {
            Objects.requireNonNull(trackSelectionParameters);
            this.trackSelectionParameters = trackSelectionParameters;
            return this;
        }

        public Builder() {
            this.playWhenReady = false;
            this.repeatMode = 0;
            this.shuffleModeEnabled = false;
            this.currentMediaItemIndex = 0;
            this.currentPosition = 0L;
            f1 f1Var = h1.f14020l;
            this.mediaItems = o3.f14078o;
            this.playbackParameters = PlaybackParameters.DEFAULT;
            this.trackSelectionParameters = TrackSelectionParameters.DEFAULT;
        }

        private Builder(PlayerTransferState playerTransferState) {
            this.playWhenReady = playerTransferState.playWhenReady;
            this.repeatMode = playerTransferState.repeatMode;
            this.shuffleModeEnabled = playerTransferState.shuffleModeEnabled;
            this.currentMediaItemIndex = playerTransferState.currentMediaItemIndex;
            this.currentPosition = playerTransferState.currentPosition;
            this.mediaItems = playerTransferState.mediaItems;
            this.playbackParameters = playerTransferState.playbackParameters;
            this.trackSelectionParameters = playerTransferState.trackSelectionParameters;
        }
    }

    public static Builder builderFromPlayer(Player player) {
        Objects.requireNonNull(player);
        ArrayList arrayList = new ArrayList();
        for (int i10 = 0; i10 < player.getMediaItemCount(); i10++) {
            arrayList.add(player.getMediaItemAt(i10));
        }
        return new Builder().setPlayWhenReady(player.getPlayWhenReady()).setRepeatMode(player.getRepeatMode()).setShuffleModeEnabled(player.getShuffleModeEnabled()).setCurrentMediaItemIndex(player.getCurrentMediaItemIndex()).setCurrentPosition(player.getCurrentPosition()).setMediaItems(arrayList).setPlaybackParameters(player.getPlaybackParameters()).setTrackSelectionParameters(player.getTrackSelectionParameters());
    }

    public static PlayerTransferState fromPlayer(Player player) {
        return builderFromPlayer(player).build();
    }

    public Builder buildUpon() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (obj != null && PlayerTransferState.class == obj.getClass()) {
            PlayerTransferState playerTransferState = (PlayerTransferState) obj;
            if (this.playWhenReady == playerTransferState.playWhenReady && this.repeatMode == playerTransferState.repeatMode && this.shuffleModeEnabled == playerTransferState.shuffleModeEnabled && this.currentMediaItemIndex == playerTransferState.currentMediaItemIndex && this.currentPosition == playerTransferState.currentPosition && Objects.equals(this.mediaItems, playerTransferState.mediaItems) && Objects.equals(this.playbackParameters, playerTransferState.playbackParameters) && Objects.equals(this.trackSelectionParameters, playerTransferState.trackSelectionParameters)) {
                return true;
            }
        }
        return false;
    }

    public int getCurrentMediaItemIndex() {
        return this.currentMediaItemIndex;
    }

    public long getCurrentPosition() {
        return this.currentPosition;
    }

    public h1 getMediaItems() {
        return this.mediaItems;
    }

    public boolean getPlayWhenReady() {
        return this.playWhenReady;
    }

    public PlaybackParameters getPlaybackParameters() {
        return this.playbackParameters;
    }

    public int getRepeatMode() {
        return this.repeatMode;
    }

    public boolean getShuffleModeEnabled() {
        return this.shuffleModeEnabled;
    }

    public TrackSelectionParameters getTrackSelectionParameters() {
        return this.trackSelectionParameters;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.playWhenReady), Integer.valueOf(this.repeatMode), Boolean.valueOf(this.shuffleModeEnabled), Integer.valueOf(this.currentMediaItemIndex), Long.valueOf(this.currentPosition), this.mediaItems, this.playbackParameters, this.trackSelectionParameters);
    }

    public void setToPlayer(Player player) {
        Objects.requireNonNull(player);
        if (player.getAvailableCommands().contains(1)) {
            player.setPlayWhenReady(this.playWhenReady);
        }
        if (player.getAvailableCommands().contains(15)) {
            player.setRepeatMode(this.repeatMode);
        }
        if (player.getAvailableCommands().contains(14)) {
            player.setShuffleModeEnabled(this.shuffleModeEnabled);
        }
        if (player.getAvailableCommands().contains(31)) {
            player.setMediaItems(this.mediaItems, this.currentMediaItemIndex, this.currentPosition);
        }
        if (player.getAvailableCommands().contains(13)) {
            player.setPlaybackParameters(this.playbackParameters);
        }
        if (player.getAvailableCommands().contains(29)) {
            player.setTrackSelectionParameters(this.trackSelectionParameters);
        }
    }

    private PlayerTransferState(Builder builder) {
        this.playWhenReady = builder.playWhenReady;
        this.repeatMode = builder.repeatMode;
        this.shuffleModeEnabled = builder.shuffleModeEnabled;
        this.currentMediaItemIndex = builder.currentMediaItemIndex;
        this.currentPosition = builder.currentPosition;
        this.mediaItems = builder.mediaItems;
        this.playbackParameters = builder.playbackParameters;
        this.trackSelectionParameters = builder.trackSelectionParameters;
    }
}

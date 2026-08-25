package androidx.media3.exoplayer;

import androidx.media3.common.PlaybackParameters;

/* JADX INFO: loaded from: classes3.dex */
public interface MediaClock {
    PlaybackParameters getPlaybackParameters();

    long getPositionUs();

    boolean hasSkippedSilenceSinceLastCall();

    void setPlaybackParameters(PlaybackParameters playbackParameters);
}

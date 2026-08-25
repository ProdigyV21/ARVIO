package com.discord.org.webrtc.audio;

import android.media.AudioTrack;
import android.os.Build;
import com.discord.org.webrtc.Logging;

/* JADX INFO: loaded from: classes4.dex */
class LowLatencyAudioBufferManager {
    private static final String TAG = "LowLatencyAudioBufferManager";
    private int prevUnderrunCount = 0;
    private int ticksUntilNextDecrease = 10;
    private boolean keepLoweringBufferSize = true;
    private int bufferIncreaseCounter = 0;

    public void maybeAdjustBufferSize(AudioTrack audioTrack) {
        if (Build.VERSION.SDK_INT >= 26) {
            int underrunCount = audioTrack.getUnderrunCount();
            if (underrunCount > this.prevUnderrunCount) {
                if (this.bufferIncreaseCounter < 5) {
                    int bufferSizeInFrames = audioTrack.getBufferSizeInFrames();
                    int playbackRate = (audioTrack.getPlaybackRate() / 100) + bufferSizeInFrames;
                    Logging.d(TAG, "Underrun detected! Increasing AudioTrack buffer size from " + bufferSizeInFrames + " to " + playbackRate);
                    audioTrack.setBufferSizeInFrames(playbackRate);
                    this.bufferIncreaseCounter = this.bufferIncreaseCounter + 1;
                }
                this.keepLoweringBufferSize = false;
                this.prevUnderrunCount = underrunCount;
                this.ticksUntilNextDecrease = 10;
                return;
            }
            if (this.keepLoweringBufferSize) {
                int i10 = this.ticksUntilNextDecrease - 1;
                this.ticksUntilNextDecrease = i10;
                if (i10 <= 0) {
                    int playbackRate2 = audioTrack.getPlaybackRate() / 100;
                    int bufferSizeInFrames2 = audioTrack.getBufferSizeInFrames();
                    int iMax = Math.max(playbackRate2, bufferSizeInFrames2 - playbackRate2);
                    if (iMax != bufferSizeInFrames2) {
                        Logging.d(TAG, "Lowering AudioTrack buffer size from " + bufferSizeInFrames2 + " to " + iMax);
                        audioTrack.setBufferSizeInFrames(iMax);
                    }
                    this.ticksUntilNextDecrease = 10;
                }
            }
        }
    }
}

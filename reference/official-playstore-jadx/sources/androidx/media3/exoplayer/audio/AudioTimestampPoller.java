package androidx.media3.exoplayer.audio;

import android.media.AudioTimestamp;
import android.media.AudioTrack;
import androidx.media3.common.C;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.audio.AudioTrackPositionTracker;

/* JADX INFO: loaded from: classes3.dex */
final class AudioTimestampPoller {
    private static final int ERROR_POLL_INTERVAL_US = 500000;
    private static final int FAST_POLL_INTERVAL_US = 10000;
    private static final int INITIALIZING_DURATION_US = 500000;
    private static final long MAX_AUDIO_TIMESTAMP_OFFSET_US = 5000000;
    private static final long MAX_POSITION_DRIFT_ADVANCING_TIMESTAMP_US = 1000;
    private static final int SLOW_POLL_INTERVAL_US = 10000000;
    private static final int STATE_ERROR = 4;
    private static final int STATE_INITIALIZING = 0;
    private static final int STATE_NO_TIMESTAMP = 3;
    private static final int STATE_TIMESTAMP = 1;
    private static final int STATE_TIMESTAMP_ADVANCING = 2;
    private static final int WAIT_FOR_ADVANCE_DURATION_US = 2000000;
    private final AudioTimestampWrapper audioTimestamp;
    private final AudioTrackPositionTracker.Listener errorListener;
    private long initialTimestampPositionFrames;
    private long initialTimestampSystemTimeUs;
    private long initializeSystemTimeUs;
    private long lastTimestampSampleTimeUs;
    private long sampleIntervalUs;
    private final int sampleRate;
    private int state;

    public static final class AudioTimestampWrapper {
        private long accumulatedRawTimestampFramePosition;
        private final AudioTimestamp audioTimestamp = new AudioTimestamp();
        private final AudioTrack audioTrack;
        private boolean expectTimestampFramePositionReset;
        private long lastTimestampPositionFrames;
        private long lastTimestampRawPositionFrames;
        private long rawTimestampFramePositionWrapCount;

        public AudioTimestampWrapper(AudioTrack audioTrack) {
            this.audioTrack = audioTrack;
        }

        public void expectTimestampFramePositionReset() {
            this.expectTimestampFramePositionReset = true;
        }

        public long getTimestampPositionFrames() {
            return this.lastTimestampPositionFrames;
        }

        public long getTimestampSystemTimeUs() {
            return this.audioTimestamp.nanoTime / 1000;
        }

        public boolean maybeUpdateTimestamp() {
            boolean timestamp = this.audioTrack.getTimestamp(this.audioTimestamp);
            if (timestamp) {
                long j10 = this.audioTimestamp.framePosition;
                long j11 = this.lastTimestampRawPositionFrames;
                if (j11 > j10) {
                    if (this.expectTimestampFramePositionReset) {
                        this.accumulatedRawTimestampFramePosition += j11;
                        this.expectTimestampFramePositionReset = false;
                    } else {
                        this.rawTimestampFramePositionWrapCount++;
                    }
                }
                this.lastTimestampRawPositionFrames = j10;
                this.lastTimestampPositionFrames = j10 + this.accumulatedRawTimestampFramePosition + (this.rawTimestampFramePositionWrapCount << 32);
            }
            return timestamp;
        }
    }

    public AudioTimestampPoller(AudioTrack audioTrack, AudioTrackPositionTracker.Listener listener) {
        this.audioTimestamp = new AudioTimestampWrapper(audioTrack);
        this.sampleRate = audioTrack.getSampleRate();
        this.errorListener = listener;
        reset();
    }

    private void checkTimestampIsPlausibleAndUpdateErrorState(long j10, float f10, long j11) {
        long timestampSystemTimeUs = this.audioTimestamp.getTimestampSystemTimeUs();
        long jComputeTimestampPositionUs = computeTimestampPositionUs(j10, f10);
        if (Math.abs(timestampSystemTimeUs - j10) > 5000000) {
            this.errorListener.onSystemTimeUsMismatch(this.audioTimestamp.getTimestampPositionFrames(), timestampSystemTimeUs, j10, j11);
            updateState(4);
        } else if (Math.abs(jComputeTimestampPositionUs - j11) > 5000000) {
            this.errorListener.onPositionFramesMismatch(this.audioTimestamp.getTimestampPositionFrames(), timestampSystemTimeUs, j10, j11);
            updateState(4);
        } else if (this.state == 4) {
            reset();
        }
    }

    private long computeTimestampPositionUs(long j10, float f10) {
        return computeTimestampPositionUs(this.audioTimestamp.getTimestampPositionFrames(), this.audioTimestamp.getTimestampSystemTimeUs(), j10, f10);
    }

    private boolean isTimestampAdvancingFromInitialTimestamp(long j10, float f10) {
        long timestampPositionFrames = this.audioTimestamp.getTimestampPositionFrames();
        long j11 = this.initialTimestampPositionFrames;
        if (timestampPositionFrames <= j11) {
            return false;
        }
        return Math.abs(computeTimestampPositionUs(j10, f10) - computeTimestampPositionUs(j11, this.initialTimestampSystemTimeUs, j10, f10)) < 1000;
    }

    private void updateState(int i10) {
        this.state = i10;
        if (i10 == 0) {
            this.lastTimestampSampleTimeUs = 0L;
            this.initialTimestampPositionFrames = -1L;
            this.initialTimestampSystemTimeUs = C.TIME_UNSET;
            this.initializeSystemTimeUs = System.nanoTime() / 1000;
            this.sampleIntervalUs = 10000L;
            return;
        }
        if (i10 == 1) {
            this.sampleIntervalUs = 10000L;
            return;
        }
        if (i10 == 2 || i10 == 3) {
            this.sampleIntervalUs = 10000000L;
        } else {
            if (i10 != 4) {
                throw new IllegalStateException();
            }
            this.sampleIntervalUs = 500000L;
        }
    }

    public void expectTimestampFramePositionReset() {
        this.audioTimestamp.expectTimestampFramePositionReset();
    }

    public long getTimestampPositionUs(long j10, float f10) {
        return computeTimestampPositionUs(j10, f10);
    }

    public boolean hasAdvancingTimestamp() {
        return this.state == 2;
    }

    public boolean isWaitingForAdvancingTimestamp() {
        int i10 = this.state;
        return i10 == 0 || i10 == 1;
    }

    public void maybePollTimestamp(long j10, float f10, long j11) {
        if (j10 - this.lastTimestampSampleTimeUs < this.sampleIntervalUs) {
            return;
        }
        this.lastTimestampSampleTimeUs = j10;
        boolean zMaybeUpdateTimestamp = this.audioTimestamp.maybeUpdateTimestamp();
        if (zMaybeUpdateTimestamp) {
            checkTimestampIsPlausibleAndUpdateErrorState(j10, f10, j11);
        }
        int i10 = this.state;
        if (i10 == 0) {
            if (!zMaybeUpdateTimestamp) {
                if (j10 - this.initializeSystemTimeUs > 500000) {
                    updateState(3);
                    return;
                }
                return;
            } else {
                if (this.audioTimestamp.getTimestampSystemTimeUs() >= this.initializeSystemTimeUs) {
                    this.initialTimestampPositionFrames = this.audioTimestamp.getTimestampPositionFrames();
                    this.initialTimestampSystemTimeUs = this.audioTimestamp.getTimestampSystemTimeUs();
                    updateState(1);
                    return;
                }
                return;
            }
        }
        if (i10 == 1) {
            if (!zMaybeUpdateTimestamp) {
                reset();
                return;
            }
            if (isTimestampAdvancingFromInitialTimestamp(j10, f10)) {
                updateState(2);
                return;
            } else if (j10 - this.initializeSystemTimeUs > SilenceSkippingAudioProcessor.DEFAULT_MAX_SILENCE_TO_KEEP_DURATION_US) {
                updateState(3);
                return;
            } else {
                this.initialTimestampPositionFrames = this.audioTimestamp.getTimestampPositionFrames();
                this.initialTimestampSystemTimeUs = this.audioTimestamp.getTimestampSystemTimeUs();
                return;
            }
        }
        if (i10 == 2) {
            if (zMaybeUpdateTimestamp) {
                return;
            }
            reset();
        } else if (i10 != 3) {
            if (i10 != 4) {
                throw new IllegalStateException();
            }
        } else if (zMaybeUpdateTimestamp) {
            reset();
        }
    }

    public void reset() {
        updateState(0);
    }

    private long computeTimestampPositionUs(long j10, long j11, long j12, float f10) {
        return Util.getMediaDurationForPlayoutDuration(j12 - j11, f10) + Util.sampleCountToDurationUs(j10, this.sampleRate);
    }
}

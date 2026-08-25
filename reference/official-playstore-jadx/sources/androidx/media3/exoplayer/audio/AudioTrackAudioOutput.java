package androidx.media3.exoplayer.audio;

import android.media.AudioDeviceInfo;
import android.media.AudioRouting;
import android.media.AudioTrack;
import android.media.AudioTrack$StreamEventCallback;
import android.media.PlaybackParams;
import android.media.metrics.LogSessionId;
import android.os.Build;
import android.os.Handler;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.util.BackgroundExecutor;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.audio.AudioOutput;
import androidx.media3.exoplayer.audio.AudioOutputProvider;
import androidx.media3.exoplayer.audio.AudioTrackPositionTracker;
import j$.util.Objects;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public final class AudioTrackAudioOutput implements AudioOutput {
    private static final int AUDIO_TRACK_VOLUME_RAMP_TIME_MS = 20;
    private static final int ERROR_NATIVE_DEAD_OBJECT = -32;
    private static final String TAG = "AudioTrackAudioOutput";
    private static int pendingReleaseCount;
    private static ScheduledExecutorService releaseExecutor;
    private static final Object releaseExecutorLock = new Object();
    private final AudioTrack audioTrack;
    private final AudioTrackPositionTracker audioTrackPositionTracker;
    private ByteBuffer avSyncHeader;
    private int bytesUntilNextAvSync;
    private final CapabilityChangeListener capabilityChangeListener;
    private final AudioOutputProvider.OutputConfig config;
    private int framesPerEncodedSample;
    private boolean hasBeenStopped;
    private boolean hasData;
    private final boolean isOutputPcm;
    private long lastTunnelingAvSyncPresentationTimeUs;
    private int lastUnderrunCount;
    private final ListenerSet<AudioOutput.Listener> listeners;
    private final StreamEventCallbackV29 offloadStreamEventCallbackV29;
    private OnRoutingChangedListenerApi24 onRoutingChangedListener;
    private final int pcmFrameSize;
    private long writtenEncodedFrames;
    private long writtenPcmBytes;

    public interface CapabilityChangeListener {
        void onRecoverableWriteError();

        void onRoutedDeviceChanged(AudioDeviceInfo audioDeviceInfo);
    }

    public static final class InvalidAudioTrackTimestampException extends RuntimeException {
        private InvalidAudioTrackTimestampException(String str) {
            super(str);
        }
    }

    public static final class OnRoutingChangedListenerApi24 {
        private final AudioTrack audioTrack;
        private final CapabilityChangeListener capabilityChangeListener;
        private AudioRouting.OnRoutingChangedListener listener;
        private final Handler playbackThreadHandler;

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onRoutingChanged$0(AudioDeviceInfo audioDeviceInfo) {
            if (this.listener == null) {
                return;
            }
            this.capabilityChangeListener.onRoutedDeviceChanged(audioDeviceInfo);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onRoutingChanged$1(AudioRouting audioRouting) {
            AudioDeviceInfo routedDevice = audioRouting.getRoutedDevice();
            if (routedDevice != null) {
                this.playbackThreadHandler.post(new p(this, routedDevice, 0));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onRoutingChanged(AudioRouting audioRouting) {
            if (this.listener == null) {
                return;
            }
            BackgroundExecutor.get().execute(new p(this, audioRouting, 1));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void release() {
            AudioTrack audioTrack = this.audioTrack;
            AudioRouting.OnRoutingChangedListener onRoutingChangedListener = this.listener;
            onRoutingChangedListener.getClass();
            audioTrack.removeOnRoutingChangedListener(onRoutingChangedListener);
            this.listener = null;
        }

        private OnRoutingChangedListenerApi24(AudioTrack audioTrack, CapabilityChangeListener capabilityChangeListener) {
            this.audioTrack = audioTrack;
            this.capabilityChangeListener = capabilityChangeListener;
            Handler handlerCreateHandlerForCurrentLooper = Util.createHandlerForCurrentLooper();
            this.playbackThreadHandler = handlerCreateHandlerForCurrentLooper;
            AudioRouting.OnRoutingChangedListener onRoutingChangedListener = new AudioRouting.OnRoutingChangedListener() { // from class: androidx.media3.exoplayer.audio.o
                @Override // android.media.AudioRouting.OnRoutingChangedListener
                public final void onRoutingChanged(AudioRouting audioRouting) {
                    this.f3572a.onRoutingChanged(audioRouting);
                }
            };
            this.listener = onRoutingChangedListener;
            audioTrack.addOnRoutingChangedListener(onRoutingChangedListener, handlerCreateHandlerForCurrentLooper);
        }
    }

    public final class PositionTrackerListener implements AudioTrackPositionTracker.Listener {
        private PositionTrackerListener() {
        }

        @Override // androidx.media3.exoplayer.audio.AudioTrackPositionTracker.Listener
        public void onInvalidLatency(long j10) {
            Log.w(AudioTrackAudioOutput.TAG, "Ignoring impossibly large audio latency: " + j10);
        }

        @Override // androidx.media3.exoplayer.audio.AudioTrackPositionTracker.Listener
        public void onPositionAdvancing(final long j10) {
            AudioTrackAudioOutput.this.listeners.sendEvent(new ListenerSet.Event() { // from class: androidx.media3.exoplayer.audio.q
                @Override // androidx.media3.common.util.ListenerSet.Event
                public final void invoke(Object obj) {
                    ((AudioOutput.Listener) obj).onPositionAdvancing(j10);
                }
            });
        }

        @Override // androidx.media3.exoplayer.audio.AudioTrackPositionTracker.Listener
        public void onPositionFramesMismatch(long j10, long j11, long j12, long j13) {
            StringBuilder sbR = androidx.compose.material3.d.r(j10, "Spurious audio timestamp (frame position mismatch): ", ", ");
            sbR.append(j11);
            androidx.compose.foundation.c.z(sbR, ", ", j12, ", ");
            sbR.append(j13);
            sbR.append(", ");
            sbR.append(AudioTrackAudioOutput.this.getWrittenFrames());
            String string = sbR.toString();
            if (AudioTrackAudioOutputProvider.failOnSpuriousAudioTimestamp) {
                throw new InvalidAudioTrackTimestampException(string);
            }
            Log.w(AudioTrackAudioOutput.TAG, string);
        }

        @Override // androidx.media3.exoplayer.audio.AudioTrackPositionTracker.Listener
        public void onSystemTimeUsMismatch(long j10, long j11, long j12, long j13) {
            StringBuilder sbR = androidx.compose.material3.d.r(j10, "Spurious audio timestamp (system clock mismatch): ", ", ");
            sbR.append(j11);
            androidx.compose.foundation.c.z(sbR, ", ", j12, ", ");
            sbR.append(j13);
            sbR.append(", ");
            sbR.append(AudioTrackAudioOutput.this.getWrittenFrames());
            String string = sbR.toString();
            if (AudioTrackAudioOutputProvider.failOnSpuriousAudioTimestamp) {
                throw new InvalidAudioTrackTimestampException(string);
            }
            Log.w(AudioTrackAudioOutput.TAG, string);
        }
    }

    public final class StreamEventCallbackV29 {
        private final AudioTrack$StreamEventCallback callback;
        private final Handler handler;

        /* JADX INFO: Access modifiers changed from: private */
        public void unregister() {
            AudioTrackAudioOutput.this.audioTrack.unregisterStreamEventCallback(this.callback);
            this.handler.removeCallbacksAndMessages(null);
        }

        private StreamEventCallbackV29() {
            Handler handlerCreateHandlerForCurrentLooper = Util.createHandlerForCurrentLooper();
            this.handler = handlerCreateHandlerForCurrentLooper;
            AudioTrack$StreamEventCallback audioTrack$StreamEventCallback = new AudioTrack$StreamEventCallback() { // from class: androidx.media3.exoplayer.audio.AudioTrackAudioOutput.StreamEventCallbackV29.1
                public void onDataRequest(AudioTrack audioTrack, int i10) {
                    AudioTrackAudioOutput.this.listeners.sendEvent(new androidx.media3.common.util.h(7));
                }

                public void onPresentationEnded(AudioTrack audioTrack) {
                    AudioTrackAudioOutput.this.listeners.sendEvent(new androidx.media3.common.util.h(8));
                }

                public void onTearDown(AudioTrack audioTrack) {
                    AudioTrackAudioOutput.this.listeners.sendEvent(new androidx.media3.common.util.h(7));
                }
            };
            this.callback = audioTrack$StreamEventCallback;
            AudioTrack audioTrack = AudioTrackAudioOutput.this.audioTrack;
            Objects.requireNonNull(handlerCreateHandlerForCurrentLooper);
            audioTrack.registerStreamEventCallback(new androidx.media3.common.util.d(handlerCreateHandlerForCurrentLooper), audioTrack$StreamEventCallback);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AudioTrackAudioOutput(AudioTrack audioTrack, AudioOutputProvider.OutputConfig outputConfig, CapabilityChangeListener capabilityChangeListener, Clock clock) {
        this.audioTrack = audioTrack;
        this.config = outputConfig;
        this.capabilityChangeListener = capabilityChangeListener;
        ListenerSet<AudioOutput.Listener> listenerSet = new ListenerSet<>(Thread.currentThread());
        this.listeners = listenerSet;
        listenerSet.setThrowsWhenUsingWrongThread(false);
        boolean zIsEncodingLinearPcm = Util.isEncodingLinearPcm(outputConfig.encoding);
        this.isOutputPcm = zIsEncodingLinearPcm;
        if (zIsEncodingLinearPcm) {
            this.pcmFrameSize = Util.getPcmFrameSize(outputConfig.encoding, Integer.bitCount(outputConfig.channelMask));
        } else {
            this.pcmFrameSize = -1;
        }
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        this.audioTrackPositionTracker = new AudioTrackPositionTracker(new PositionTrackerListener(), clock, audioTrack, outputConfig.encoding, this.pcmFrameSize, outputConfig.bufferSize);
        if (Build.VERSION.SDK_INT >= 24 && capabilityChangeListener != null) {
            this.onRoutingChangedListener = new OnRoutingChangedListenerApi24(audioTrack, capabilityChangeListener);
        }
        this.offloadStreamEventCallbackV29 = isOffloadedPlayback() ? new StreamEventCallbackV29() : null;
    }

    private int getAudioOutputUnderrunCount(long j10) {
        if (Build.VERSION.SDK_INT >= 24) {
            return this.audioTrack.getUnderrunCount();
        }
        boolean z = this.hasData;
        boolean z5 = j10 > Util.durationUsToSampleCount(getPositionUs(), getSampleRate());
        this.hasData = z5;
        return (!z || z5 || this.audioTrack.getPlayState() == 1) ? this.lastUnderrunCount : this.lastUnderrunCount + 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getWrittenFrames() {
        return this.isOutputPcm ? Util.ceilDivide(this.writtenPcmBytes, this.pcmFrameSize) : this.writtenEncodedFrames;
    }

    private boolean hasPendingAudioTrackUnderruns(long j10) {
        int audioOutputUnderrunCount = getAudioOutputUnderrunCount(j10);
        boolean z = audioOutputUnderrunCount > this.lastUnderrunCount;
        this.lastUnderrunCount = audioOutputUnderrunCount;
        return z;
    }

    private static boolean isAudioTrackDeadObject(int i10) {
        return (Build.VERSION.SDK_INT >= 24 && i10 == -6) || i10 == ERROR_NATIVE_DEAD_OBJECT;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$releaseAudioTrackAsync$0(ListenerSet listenerSet) {
        listenerSet.sendEvent(new androidx.media3.common.util.h(6));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void lambda$releaseAudioTrackAsync$1(AudioTrack audioTrack, Handler handler, ListenerSet listenerSet) {
        try {
            audioTrack.flush();
            audioTrack.release();
            if (handler.getLooper().getThread().isAlive()) {
                handler.post(new n(listenerSet, 0));
            }
            synchronized (releaseExecutorLock) {
                try {
                    int i10 = pendingReleaseCount - 1;
                    pendingReleaseCount = i10;
                    if (i10 == 0) {
                        ScheduledExecutorService scheduledExecutorService = releaseExecutor;
                        scheduledExecutorService.getClass();
                        scheduledExecutorService.shutdown();
                        releaseExecutor = null;
                    }
                } finally {
                }
            }
        } catch (Throwable th) {
            if (handler.getLooper().getThread().isAlive()) {
                handler.post(new n(listenerSet, 0));
            }
            synchronized (releaseExecutorLock) {
                try {
                    int i11 = pendingReleaseCount - 1;
                    pendingReleaseCount = i11;
                    if (i11 == 0) {
                        ScheduledExecutorService scheduledExecutorService2 = releaseExecutor;
                        scheduledExecutorService2.getClass();
                        scheduledExecutorService2.shutdown();
                        releaseExecutor = null;
                    }
                    throw th;
                } finally {
                }
            }
        }
    }

    private void maybeReportUnderrun() {
        if (hasPendingAudioTrackUnderruns(getWrittenFrames())) {
            this.listeners.sendEvent(new androidx.media3.common.util.h(5));
        }
    }

    private static void releaseAudioTrackAsync(AudioTrack audioTrack, ListenerSet<AudioOutput.Listener> listenerSet) {
        Handler handlerCreateHandlerForCurrentLooper = Util.createHandlerForCurrentLooper();
        synchronized (releaseExecutorLock) {
            try {
                if (releaseExecutor == null) {
                    releaseExecutor = Util.newSingleThreadScheduledExecutor("ExoPlayer:AudioTrackReleaseThread");
                }
                pendingReleaseCount++;
                releaseExecutor.schedule(new androidx.emoji2.text.q(audioTrack, handlerCreateHandlerForCurrentLooper, listenerSet, 4), 20L, TimeUnit.MILLISECONDS);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private int writeWithAvSync(AudioTrack audioTrack, ByteBuffer byteBuffer, long j10) {
        int iRemaining = byteBuffer.remaining();
        if (Build.VERSION.SDK_INT >= 26) {
            return audioTrack.write(byteBuffer, iRemaining, 1, j10 * 1000);
        }
        if (this.avSyncHeader == null) {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(16);
            this.avSyncHeader = byteBufferAllocate;
            byteBufferAllocate.order(ByteOrder.BIG_ENDIAN);
            this.avSyncHeader.putInt(1431633921);
        }
        if (this.bytesUntilNextAvSync == 0) {
            this.avSyncHeader.putInt(4, iRemaining);
            this.avSyncHeader.putLong(8, j10 * 1000);
            this.avSyncHeader.position(0);
            this.bytesUntilNextAvSync = iRemaining;
        }
        int iRemaining2 = this.avSyncHeader.remaining();
        if (iRemaining2 > 0) {
            int iWrite = audioTrack.write(this.avSyncHeader, iRemaining2, 1);
            if (iWrite < 0) {
                this.bytesUntilNextAvSync = 0;
                return iWrite;
            }
            if (iWrite < iRemaining2) {
                return 0;
            }
        }
        int iWrite2 = audioTrack.write(byteBuffer, iRemaining, 1);
        if (iWrite2 < 0) {
            this.bytesUntilNextAvSync = 0;
            return iWrite2;
        }
        this.bytesUntilNextAvSync -= iWrite2;
        return iWrite2;
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutput
    public void addListener(AudioOutput.Listener listener) {
        this.listeners.add(listener);
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutput
    public void attachAuxEffect(int i10) {
        this.audioTrack.attachAuxEffect(i10);
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutput
    public void flush() {
        this.avSyncHeader = null;
        this.bytesUntilNextAvSync = 0;
        this.writtenPcmBytes = 0L;
        this.writtenEncodedFrames = 0L;
        this.hasBeenStopped = false;
        this.framesPerEncodedSample = 0;
        this.audioTrack.flush();
        this.audioTrackPositionTracker.reset();
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutput
    public int getAudioSessionId() {
        return this.audioTrack.getAudioSessionId();
    }

    public AudioTrack getAudioTrack() {
        return this.audioTrack;
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutput
    public long getBufferSizeInFrames() {
        return this.audioTrack.getBufferSizeInFrames();
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutput
    public PlaybackParameters getPlaybackParameters() {
        PlaybackParams playbackParams = this.audioTrack.getPlaybackParams();
        return new PlaybackParameters(playbackParams.getSpeed(), playbackParams.getPitch());
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutput
    public long getPositionUs() {
        return this.audioTrackPositionTracker.getCurrentPositionUs();
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutput
    public int getSampleRate() {
        return this.audioTrack.getSampleRate();
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutput
    public boolean isOffloadedPlayback() {
        return Build.VERSION.SDK_INT >= 29 && this.audioTrack.isOffloadedPlayback();
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutput
    public boolean isStalled() {
        return this.audioTrackPositionTracker.isStalled(getWrittenFrames());
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutput
    public void pause() {
        this.audioTrackPositionTracker.pause();
        if (!this.hasBeenStopped || isOffloadedPlayback()) {
            this.audioTrack.pause();
        }
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutput
    public void play() {
        this.audioTrackPositionTracker.start();
        if (!this.hasBeenStopped || isOffloadedPlayback()) {
            this.audioTrack.play();
        }
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutput
    public void release() {
        OnRoutingChangedListenerApi24 onRoutingChangedListenerApi24;
        if (this.audioTrackPositionTracker.isPlaying()) {
            this.audioTrack.pause();
        }
        int i10 = Build.VERSION.SDK_INT;
        if (i10 >= 29 && isOffloadedPlayback()) {
            StreamEventCallbackV29 streamEventCallbackV29 = this.offloadStreamEventCallbackV29;
            streamEventCallbackV29.getClass();
            streamEventCallbackV29.unregister();
        }
        if (i10 >= 24 && (onRoutingChangedListenerApi24 = this.onRoutingChangedListener) != null) {
            onRoutingChangedListenerApi24.release();
            this.onRoutingChangedListener = null;
        }
        releaseAudioTrackAsync(this.audioTrack, this.listeners);
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutput
    public void removeListener(AudioOutput.Listener listener) {
        this.listeners.remove(listener);
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutput
    public void setAuxEffectSendLevel(float f10) {
        this.audioTrack.setAuxEffectSendLevel(f10);
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutput
    public void setOffloadDelayPadding(int i10, int i11) {
        if (Build.VERSION.SDK_INT < 29) {
            return;
        }
        this.audioTrack.setOffloadDelayPadding(i10, i11);
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutput
    public void setOffloadEndOfStream() {
        if (Build.VERSION.SDK_INT >= 29 && this.audioTrack.getPlayState() == 3) {
            this.audioTrack.setOffloadEndOfStream();
            this.audioTrackPositionTracker.expectRawPlaybackHeadReset();
        }
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutput
    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        try {
            this.audioTrack.setPlaybackParams(new PlaybackParams().allowDefaults().setSpeed(playbackParameters.speed).setPitch(playbackParameters.pitch).setAudioFallbackMode(2));
        } catch (IllegalArgumentException e5) {
            Log.w(TAG, "Failed to set playback params", e5);
        }
        this.audioTrackPositionTracker.setAudioTrackPlaybackSpeed(this.audioTrack.getPlaybackParams().getSpeed());
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutput
    public void setPlayerId(PlayerId playerId) {
        if (Build.VERSION.SDK_INT < 31) {
            return;
        }
        LogSessionId logSessionId = playerId.getLogSessionId();
        if (logSessionId.equals(LogSessionId.LOG_SESSION_ID_NONE)) {
            return;
        }
        this.audioTrack.setLogSessionId(logSessionId);
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutput
    public void setPreferredDevice(AudioDeviceInfo audioDeviceInfo) {
        this.audioTrack.setPreferredDevice(audioDeviceInfo);
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutput
    public void setVolume(float f10) {
        this.audioTrack.setVolume(f10);
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutput
    public void stop() {
        if (this.hasBeenStopped) {
            return;
        }
        this.hasBeenStopped = true;
        this.audioTrackPositionTracker.handleEndOfStream(getWrittenFrames());
        this.audioTrack.stop();
        this.bytesUntilNextAvSync = 0;
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutput
    public boolean write(ByteBuffer byteBuffer, int i10, long j10) throws AudioOutput.WriteException {
        int iWrite;
        CapabilityChangeListener capabilityChangeListener;
        if (!this.isOutputPcm && this.framesPerEncodedSample == 0) {
            this.framesPerEncodedSample = DefaultAudioSink.getFramesPerEncodedSample(this.config.encoding, byteBuffer);
        }
        maybeReportUnderrun();
        int iRemaining = byteBuffer.remaining();
        if (this.config.isTunneling) {
            if (j10 == Long.MIN_VALUE) {
                j10 = this.lastTunnelingAvSyncPresentationTimeUs;
            } else {
                this.lastTunnelingAvSyncPresentationTimeUs = j10;
            }
            iWrite = writeWithAvSync(this.audioTrack, byteBuffer, j10);
        } else {
            iWrite = this.audioTrack.write(byteBuffer, byteBuffer.remaining(), 1);
        }
        if (iWrite < 0) {
            boolean zIsAudioTrackDeadObject = isAudioTrackDeadObject(iWrite);
            if (zIsAudioTrackDeadObject && (capabilityChangeListener = this.capabilityChangeListener) != null) {
                capabilityChangeListener.onRecoverableWriteError();
            }
            throw new AudioOutput.WriteException(iWrite, zIsAudioTrackDeadObject);
        }
        boolean z = iWrite == iRemaining;
        if (this.isOutputPcm) {
            this.writtenPcmBytes += (long) iWrite;
            return z;
        }
        if (z) {
            this.writtenEncodedFrames = (((long) this.framesPerEncodedSample) * ((long) i10)) + this.writtenEncodedFrames;
        }
        return z;
    }
}

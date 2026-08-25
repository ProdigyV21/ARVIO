package androidx.media3.exoplayer.audio;

import android.media.AudioDeviceInfo;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.exoplayer.analytics.PlayerId;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public interface AudioOutput {

    public interface Listener {
        void onOffloadDataRequest();

        void onOffloadPresentationEnded();

        void onPositionAdvancing(long j10);

        void onReleased();

        void onUnderrun();
    }

    public static final class WriteException extends Exception {
        public final int errorCode;
        public final boolean isRecoverable;

        public WriteException(int i10, boolean z) {
            super(a0.c.i(i10, "AudioOutput write failed: "));
            this.isRecoverable = z;
            this.errorCode = i10;
        }
    }

    void addListener(Listener listener);

    void attachAuxEffect(int i10);

    void flush();

    int getAudioSessionId();

    long getBufferSizeInFrames();

    PlaybackParameters getPlaybackParameters();

    long getPositionUs();

    int getSampleRate();

    boolean isOffloadedPlayback();

    boolean isStalled();

    void pause();

    void play();

    void release();

    void removeListener(Listener listener);

    void setAuxEffectSendLevel(float f10);

    void setOffloadDelayPadding(int i10, int i11);

    void setOffloadEndOfStream();

    void setPlaybackParameters(PlaybackParameters playbackParameters);

    void setPlayerId(PlayerId playerId);

    void setPreferredDevice(AudioDeviceInfo audioDeviceInfo);

    void setVolume(float f10);

    void stop();

    boolean write(ByteBuffer byteBuffer, int i10, long j10) throws WriteException;
}

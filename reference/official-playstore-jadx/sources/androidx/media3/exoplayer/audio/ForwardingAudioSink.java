package androidx.media3.exoplayer.audio;

import android.media.AudioDeviceInfo;
import androidx.media3.common.AudioAttributes;
import androidx.media3.common.AuxEffectInfo;
import androidx.media3.common.Format;
import androidx.media3.common.PlaybackParameters;
import androidx.media3.common.util.Clock;
import androidx.media3.exoplayer.analytics.PlayerId;
import androidx.media3.exoplayer.audio.AudioSink;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public class ForwardingAudioSink implements AudioSink {
    private final AudioSink sink;

    public ForwardingAudioSink(AudioSink audioSink) {
        this.sink = audioSink;
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void configure(Format format, int i10, int[] iArr) throws AudioSink.ConfigurationException {
        this.sink.configure(format, i10, iArr);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void disableTunneling() {
        this.sink.disableTunneling();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void enableTunnelingV21() {
        this.sink.enableTunnelingV21();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void flush() {
        this.sink.flush();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public AudioAttributes getAudioAttributes() {
        return this.sink.getAudioAttributes();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public long getAudioTrackBufferSizeUs() {
        return this.sink.getAudioTrackBufferSizeUs();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public long getCurrentPositionUs(boolean z) {
        return this.sink.getCurrentPositionUs(z);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public AudioOffloadSupport getFormatOffloadSupport(Format format) {
        return this.sink.getFormatOffloadSupport(format);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public int getFormatSupport(Format format) {
        return this.sink.getFormatSupport(format);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public PlaybackParameters getPlaybackParameters() {
        return this.sink.getPlaybackParameters();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public boolean getSkipSilenceEnabled() {
        return this.sink.getSkipSilenceEnabled();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public boolean handleBuffer(ByteBuffer byteBuffer, long j10, int i10) throws AudioSink.WriteException, AudioSink.InitializationException {
        return this.sink.handleBuffer(byteBuffer, j10, i10);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void handleDiscontinuity() {
        this.sink.handleDiscontinuity();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public boolean hasPendingData() {
        return this.sink.hasPendingData();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public boolean isEnded() {
        return this.sink.isEnded();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void pause() {
        this.sink.pause();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void play() {
        this.sink.play();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void playToEndOfStream() throws AudioSink.WriteException {
        this.sink.playToEndOfStream();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void release() {
        this.sink.release();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void reset() {
        this.sink.reset();
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setAudioAttributes(AudioAttributes audioAttributes) {
        this.sink.setAudioAttributes(audioAttributes);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setAudioOutputProvider(AudioOutputProvider audioOutputProvider) {
        this.sink.setAudioOutputProvider(audioOutputProvider);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setAudioSessionId(int i10) {
        this.sink.setAudioSessionId(i10);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setAuxEffectInfo(AuxEffectInfo auxEffectInfo) {
        this.sink.setAuxEffectInfo(auxEffectInfo);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setClock(Clock clock) {
        this.sink.setClock(clock);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setListener(AudioSink.Listener listener) {
        this.sink.setListener(listener);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setOffloadDelayPadding(int i10, int i11) {
        this.sink.setOffloadDelayPadding(i10, i11);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setOffloadMode(int i10) {
        this.sink.setOffloadMode(i10);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setOutputStreamOffsetUs(long j10) {
        this.sink.setOutputStreamOffsetUs(j10);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setPlaybackParameters(PlaybackParameters playbackParameters) {
        this.sink.setPlaybackParameters(playbackParameters);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setPlayerId(PlayerId playerId) {
        this.sink.setPlayerId(playerId);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setPreferredDevice(AudioDeviceInfo audioDeviceInfo) {
        this.sink.setPreferredDevice(audioDeviceInfo);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setSkipSilenceEnabled(boolean z) {
        this.sink.setSkipSilenceEnabled(z);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setVirtualDeviceId(int i10) {
        this.sink.setVirtualDeviceId(i10);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public void setVolume(float f10) {
        this.sink.setVolume(f10);
    }

    @Override // androidx.media3.exoplayer.audio.AudioSink
    public boolean supportsFormat(Format format) {
        return this.sink.supportsFormat(format);
    }
}

package androidx.media3.exoplayer.audio;

import androidx.media3.common.util.Clock;
import androidx.media3.exoplayer.audio.AudioOutputProvider;

/* JADX INFO: loaded from: classes3.dex */
public class ForwardingAudioOutputProvider implements AudioOutputProvider {
    private final AudioOutputProvider audioOutputProvider;

    public ForwardingAudioOutputProvider(AudioOutputProvider audioOutputProvider) {
        this.audioOutputProvider = audioOutputProvider;
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutputProvider
    public void addListener(AudioOutputProvider.Listener listener) {
        this.audioOutputProvider.addListener(listener);
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutputProvider
    public AudioOutput getAudioOutput(AudioOutputProvider.OutputConfig outputConfig) throws AudioOutputProvider.InitializationException {
        return this.audioOutputProvider.getAudioOutput(outputConfig);
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutputProvider
    public AudioOutputProvider.FormatSupport getFormatSupport(AudioOutputProvider.FormatConfig formatConfig) {
        return this.audioOutputProvider.getFormatSupport(formatConfig);
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutputProvider
    public AudioOutputProvider.OutputConfig getOutputConfig(AudioOutputProvider.FormatConfig formatConfig) throws AudioOutputProvider.ConfigurationException {
        return this.audioOutputProvider.getOutputConfig(formatConfig);
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutputProvider
    public void release() {
        this.audioOutputProvider.release();
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutputProvider
    public void removeListener(AudioOutputProvider.Listener listener) {
        this.audioOutputProvider.removeListener(listener);
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutputProvider
    public void setClock(Clock clock) {
        this.audioOutputProvider.setClock(clock);
    }
}

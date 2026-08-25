package androidx.media3.exoplayer.audio;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioDeviceInfo;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.os.Build;
import android.os.Looper;
import android.util.Pair;
import androidx.media3.common.Format;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.Clock;
import androidx.media3.common.util.ListenerSet;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.audio.AudioCapabilitiesReceiver;
import androidx.media3.exoplayer.audio.AudioOutputProvider;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.audio.AudioTrackAudioOutput;
import androidx.media3.exoplayer.audio.DefaultAudioSink;
import j$.util.Objects;
import java.util.function.BiConsumer;
import org.checkerframework.checker.nullness.qual.EnsuresNonNull;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: loaded from: classes3.dex */
public final class AudioTrackAudioOutputProvider implements AudioOutputProvider {
    private static final String TAG = "ATAudioOutputProvider";
    public static boolean failOnSpuriousAudioTimestamp = false;
    private AudioCapabilities audioCapabilities;
    private AudioCapabilitiesReceiver audioCapabilitiesReceiver;
    private final DefaultAudioSink.AudioOffloadSupportProvider audioOffloadSupportProvider;
    private final DefaultAudioSink.AudioTrackBufferSizeProvider audioTrackBufferSizeProvider;
    private final DefaultAudioSink.AudioTrackProvider audioTrackProvider;
    private final BiConsumer<AudioTrack.Builder, AudioOutputProvider.OutputConfig> builderModifier;
    private final CapabilityChangeListener capabilityChangeListener;
    private Clock clock;
    private final Context context;
    private Context contextWithDeviceId;
    private ListenerSet<AudioOutputProvider.Listener> listeners;
    private Looper playbackLooper;

    public static final class Builder {
        private AudioCapabilities audioCapabilities;
        private DefaultAudioSink.AudioOffloadSupportProvider audioOffloadSupportProvider;
        private BiConsumer<AudioTrack.Builder, AudioOutputProvider.OutputConfig> audioTrackBuilderModifier;
        private DefaultAudioSink.AudioTrackProvider audioTrackProvider;
        private DefaultAudioSink.AudioTrackBufferSizeProvider bufferSizeProvider;
        private final Context context;

        public Builder(Context context) {
            this.context = context != null ? context.getApplicationContext() : null;
            this.bufferSizeProvider = DefaultAudioSink.AudioTrackBufferSizeProvider.DEFAULT;
            if (context == null) {
                this.audioCapabilities = AudioCapabilities.DEFAULT_AUDIO_CAPABILITIES;
            }
        }

        public AudioTrackAudioOutputProvider build() {
            if (this.audioOffloadSupportProvider == null) {
                this.audioOffloadSupportProvider = new DefaultAudioOffloadSupportProvider(this.context);
            }
            return new AudioTrackAudioOutputProvider(this);
        }

        public Builder setAudioCapabilities(AudioCapabilities audioCapabilities) {
            if (this.context == null) {
                this.audioCapabilities = audioCapabilities;
            }
            return this;
        }

        public Builder setAudioOffloadSupportProvider(DefaultAudioSink.AudioOffloadSupportProvider audioOffloadSupportProvider) {
            this.audioOffloadSupportProvider = audioOffloadSupportProvider;
            return this;
        }

        public Builder setAudioTrackBufferSizeProvider(DefaultAudioSink.AudioTrackBufferSizeProvider audioTrackBufferSizeProvider) {
            this.bufferSizeProvider = audioTrackBufferSizeProvider;
            return this;
        }

        public Builder setAudioTrackBuilderModifier(BiConsumer<AudioTrack.Builder, AudioOutputProvider.OutputConfig> biConsumer) {
            this.audioTrackBuilderModifier = biConsumer;
            return this;
        }

        public Builder setAudioTrackProvider(DefaultAudioSink.AudioTrackProvider audioTrackProvider) {
            this.audioTrackProvider = audioTrackProvider;
            return this;
        }
    }

    public final class CapabilityChangeListener implements AudioTrackAudioOutput.CapabilityChangeListener {
        private CapabilityChangeListener() {
        }

        @Override // androidx.media3.exoplayer.audio.AudioTrackAudioOutput.CapabilityChangeListener
        public void onRecoverableWriteError() {
            if (AudioTrackAudioOutputProvider.this.audioCapabilitiesReceiver != null) {
                AudioTrackAudioOutputProvider audioTrackAudioOutputProvider = AudioTrackAudioOutputProvider.this;
                AudioCapabilities audioCapabilities = AudioCapabilities.DEFAULT_AUDIO_CAPABILITIES;
                audioTrackAudioOutputProvider.audioCapabilities = audioCapabilities;
                AudioTrackAudioOutputProvider.this.audioCapabilitiesReceiver.overrideCapabilities(audioCapabilities);
            }
        }

        @Override // androidx.media3.exoplayer.audio.AudioTrackAudioOutput.CapabilityChangeListener
        public void onRoutedDeviceChanged(AudioDeviceInfo audioDeviceInfo) {
            if (AudioTrackAudioOutputProvider.this.audioCapabilitiesReceiver != null) {
                AudioTrackAudioOutputProvider.this.audioCapabilitiesReceiver.setRoutedDevice(audioDeviceInfo);
            }
        }
    }

    private int getAudioOutputChannelConfig(int i10) {
        DefaultAudioSink.AudioTrackProvider audioTrackProvider = this.audioTrackProvider;
        return audioTrackProvider != null ? audioTrackProvider.getAudioTrackChannelConfig(i10) : Util.getAudioTrackChannelConfig(i10);
    }

    private AudioAttributes getAudioTrackAttributes(androidx.media3.common.AudioAttributes audioAttributes, boolean z) {
        return z ? getAudioTrackTunnelingAttributes() : audioAttributes.getPlatformAudioAttributes();
    }

    private AudioSink.AudioTrackConfig getAudioTrackConfig(AudioOutputProvider.OutputConfig outputConfig) {
        return new AudioSink.AudioTrackConfig(outputConfig.encoding, outputConfig.sampleRate, outputConfig.channelMask, outputConfig.isTunneling, outputConfig.isOffload, outputConfig.bufferSize);
    }

    private int getAudioTrackMinBufferSize(int i10, int i11, int i12) {
        int minBufferSize = AudioTrack.getMinBufferSize(i10, i11, i12);
        ac.b.s(minBufferSize != -2);
        return minBufferSize;
    }

    private AudioAttributes getAudioTrackTunnelingAttributes() {
        return new AudioAttributes.Builder().setContentType(3).setFlags(16).setUsage(1).build();
    }

    @RequiresNonNull({"audioCapabilities"})
    private int getFormatSupportLevel(AudioOutputProvider.FormatConfig formatConfig) {
        Format format = formatConfig.format;
        if (!Objects.equals(format.sampleMimeType, MimeTypes.AUDIO_RAW)) {
            return this.audioCapabilities.isPassthroughPlaybackSupported(format, formatConfig.audioAttributes) ? 2 : 0;
        }
        if (Util.isEncodingLinearPcm(format.pcmEncoding)) {
            int i10 = format.pcmEncoding;
            return (i10 == 2 || (formatConfig.enableHighResolutionPcmOutput && i10 == 4)) ? 2 : 1;
        }
        Log.w(TAG, "Invalid PCM encoding: " + format.pcmEncoding);
        return 0;
    }

    private static String getLooperThreadName(Looper looper) {
        return looper == null ? "null" : looper.getThread().getName();
    }

    @EnsuresNonNull({"audioCapabilities"})
    private void updateAudioCapabilitiesReceiver(AudioOutputProvider.FormatConfig formatConfig) {
        Context context;
        verifySinglePlaybackLooper();
        AudioCapabilitiesReceiver audioCapabilitiesReceiver = this.audioCapabilitiesReceiver;
        if (audioCapabilitiesReceiver == null && (context = this.context) != null) {
            AudioCapabilitiesReceiver audioCapabilitiesReceiver2 = new AudioCapabilitiesReceiver(context, new AudioCapabilitiesReceiver.Listener() { // from class: androidx.media3.exoplayer.audio.r
                @Override // androidx.media3.exoplayer.audio.AudioCapabilitiesReceiver.Listener
                public final void onAudioCapabilitiesChanged(AudioCapabilities audioCapabilities) {
                    this.f3577a.onAudioCapabilitiesChanged(audioCapabilities);
                }
            }, formatConfig.audioAttributes, formatConfig.preferredDevice);
            this.audioCapabilitiesReceiver = audioCapabilitiesReceiver2;
            this.audioCapabilities = audioCapabilitiesReceiver2.register();
        } else if (audioCapabilitiesReceiver != null) {
            AudioDeviceInfo audioDeviceInfo = formatConfig.preferredDevice;
            if (audioDeviceInfo != null) {
                audioCapabilitiesReceiver.setRoutedDevice(audioDeviceInfo);
            }
            this.audioCapabilitiesReceiver.setAudioAttributes(formatConfig.audioAttributes);
        }
        this.audioCapabilities.getClass();
    }

    private void verifySinglePlaybackLooper() {
        if (this.context == null) {
            return;
        }
        Looper looperMyLooper = Looper.myLooper();
        Looper looper = this.playbackLooper;
        boolean z = looper == null || looper == looperMyLooper;
        String looperThreadName = getLooperThreadName(looper);
        String looperThreadName2 = getLooperThreadName(looperMyLooper);
        if (!z) {
            throw new IllegalStateException(androidx.work.impl.t.y("AudioTrackAudioOutputProvider accessed on multiple threads: %s and %s", looperThreadName, looperThreadName2));
        }
        this.playbackLooper = looperMyLooper;
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutputProvider
    public void addListener(AudioOutputProvider.Listener listener) {
        verifySinglePlaybackLooper();
        if (this.listeners == null) {
            ListenerSet<AudioOutputProvider.Listener> listenerSet = new ListenerSet<>(Thread.currentThread());
            this.listeners = listenerSet;
            listenerSet.setThrowsWhenUsingWrongThread(false);
        }
        this.listeners.add(listener);
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutputProvider
    public AudioOutputProvider.FormatSupport getFormatSupport(AudioOutputProvider.FormatConfig formatConfig) {
        updateAudioCapabilitiesReceiver(formatConfig);
        AudioOffloadSupport audioOffloadSupport = this.audioOffloadSupportProvider.getAudioOffloadSupport(formatConfig.format, formatConfig.audioAttributes);
        return new AudioOutputProvider.FormatSupport.Builder().setFormatSupportLevel(getFormatSupportLevel(formatConfig)).setIsFormatSupportedForOffload(audioOffloadSupport.isFormatSupported).setIsGaplessSupportedForOffload(audioOffloadSupport.isGaplessSupported).setIsSpeedChangeSupportedForOffload(audioOffloadSupport.isSpeedChangeSupported).build();
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutputProvider
    public AudioOutputProvider.OutputConfig getOutputConfig(AudioOutputProvider.FormatConfig formatConfig) throws AudioOutputProvider.ConfigurationException {
        int i10;
        boolean z;
        int i11;
        int i12;
        int audioOutputChannelConfig;
        int pcmFrameSize;
        boolean z5;
        Format format = formatConfig.format;
        updateAudioCapabilitiesReceiver(formatConfig);
        if (Objects.equals(format.sampleMimeType, MimeTypes.AUDIO_RAW)) {
            ac.b.j(Util.isEncodingLinearPcm(format.pcmEncoding));
            int i13 = format.pcmEncoding;
            i10 = format.sampleRate;
            audioOutputChannelConfig = getAudioOutputChannelConfig(format.channelCount);
            pcmFrameSize = Util.getPcmFrameSize(i13, format.channelCount);
            z = formatConfig.enablePlaybackParameters;
            i11 = i13;
            z5 = false;
            i12 = 0;
        } else {
            i10 = format.sampleRate;
            AudioOffloadSupport audioOffloadSupport = formatConfig.enableOffload ? this.audioOffloadSupportProvider.getAudioOffloadSupport(format, formatConfig.audioAttributes) : AudioOffloadSupport.DEFAULT_UNSUPPORTED;
            if (formatConfig.enableOffload && audioOffloadSupport.isFormatSupported) {
                String str = format.sampleMimeType;
                str.getClass();
                int encoding = MimeTypes.getEncoding(str, format.codecs);
                int audioOutputChannelConfig2 = getAudioOutputChannelConfig(format.channelCount);
                z5 = audioOffloadSupport.isGaplessSupported;
                z = true;
                i12 = 1;
                i11 = encoding;
                audioOutputChannelConfig = audioOutputChannelConfig2;
                pcmFrameSize = -1;
            } else {
                Pair<Integer, Integer> encodingAndChannelConfigForPassthrough = this.audioCapabilities.getEncodingAndChannelConfigForPassthrough(format, formatConfig.audioAttributes);
                if (encodingAndChannelConfigForPassthrough == null) {
                    throw new AudioOutputProvider.ConfigurationException("Unable to configure passthrough for: " + format);
                }
                int iIntValue = ((Integer) encodingAndChannelConfigForPassthrough.first).intValue();
                int iIntValue2 = ((Integer) encodingAndChannelConfigForPassthrough.second).intValue();
                z = formatConfig.enablePlaybackParameters;
                i11 = iIntValue;
                i12 = 2;
                audioOutputChannelConfig = iIntValue2;
                pcmFrameSize = -1;
                z5 = false;
            }
        }
        int i14 = format.bitrate;
        if (Objects.equals(format.sampleMimeType, MimeTypes.AUDIO_DTS_EXPRESS) && i14 == -1) {
            i14 = 768000;
        }
        int i15 = i14;
        int bufferSizeInBytes = formatConfig.preferredBufferSize;
        if (bufferSizeInBytes == -1) {
            int i16 = i10;
            bufferSizeInBytes = this.audioTrackBufferSizeProvider.getBufferSizeInBytes(getAudioTrackMinBufferSize(i10, audioOutputChannelConfig, i11), i11, i12, pcmFrameSize != -1 ? pcmFrameSize : 1, i16, i15, z ? 8.0d : 1.0d);
            i10 = i16;
        }
        return new AudioOutputProvider.OutputConfig.Builder().setSampleRate(i10).setChannelMask(audioOutputChannelConfig).setEncoding(i11).setBufferSize(bufferSizeInBytes).setAudioSessionId(formatConfig.audioSessionId).setAudioAttributes(formatConfig.audioAttributes).setIsOffload(i12 == 1).setIsTunneling(formatConfig.enableTunneling).setUsePlaybackParameters(z).setUseOffloadGapless(z5).setVirtualDeviceId(formatConfig.virtualDeviceId).build();
    }

    public void onAudioCapabilitiesChanged(AudioCapabilities audioCapabilities) {
        verifySinglePlaybackLooper();
        AudioCapabilities audioCapabilities2 = this.audioCapabilities;
        if (audioCapabilities2 == null || audioCapabilities.equals(audioCapabilities2)) {
            return;
        }
        this.audioCapabilities = audioCapabilities;
        ListenerSet<AudioOutputProvider.Listener> listenerSet = this.listeners;
        if (listenerSet != null) {
            listenerSet.sendEvent(new androidx.media3.common.util.h(9));
        }
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutputProvider
    public void release() {
        ListenerSet<AudioOutputProvider.Listener> listenerSet = this.listeners;
        if (listenerSet != null) {
            listenerSet.release();
        }
        AudioCapabilitiesReceiver audioCapabilitiesReceiver = this.audioCapabilitiesReceiver;
        if (audioCapabilitiesReceiver != null) {
            audioCapabilitiesReceiver.unregister();
        }
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutputProvider
    public void removeListener(AudioOutputProvider.Listener listener) {
        ListenerSet<AudioOutputProvider.Listener> listenerSet = this.listeners;
        if (listenerSet != null) {
            listenerSet.remove(listener);
        }
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutputProvider
    public void setClock(Clock clock) {
        this.clock = clock;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private AudioTrackAudioOutputProvider(Builder builder) {
        this.context = builder.context;
        this.builderModifier = builder.audioTrackBuilderModifier;
        DefaultAudioSink.AudioOffloadSupportProvider audioOffloadSupportProvider = builder.audioOffloadSupportProvider;
        audioOffloadSupportProvider.getClass();
        this.audioOffloadSupportProvider = audioOffloadSupportProvider;
        this.audioTrackBufferSizeProvider = builder.bufferSizeProvider;
        this.audioCapabilities = builder.audioCapabilities;
        this.audioTrackProvider = builder.audioTrackProvider;
        this.capabilityChangeListener = builder.context != null ? new CapabilityChangeListener() : null;
        this.clock = Clock.DEFAULT;
    }

    @Override // androidx.media3.exoplayer.audio.AudioOutputProvider
    public AudioTrackAudioOutput getAudioOutput(AudioOutputProvider.OutputConfig outputConfig) throws AudioOutputProvider.InitializationException {
        Context context;
        AudioTrack audioTrackBuild;
        try {
            int i10 = outputConfig.audioSessionId;
            if (outputConfig.virtualDeviceId == -1 || this.context == null || Build.VERSION.SDK_INT < 34) {
                context = null;
            } else {
                Context context2 = this.contextWithDeviceId;
                if (context2 == null || context2.getDeviceId() != outputConfig.virtualDeviceId) {
                    this.contextWithDeviceId = this.context.createDeviceContext(outputConfig.virtualDeviceId);
                }
                context = this.contextWithDeviceId;
                i10 = 0;
            }
            if (this.audioTrackProvider != null) {
                audioTrackBuild = this.audioTrackProvider.getAudioTrack(getAudioTrackConfig(outputConfig), outputConfig.audioAttributes, i10, context);
            } else {
                AudioTrack.Builder sessionId = new AudioTrack.Builder().setAudioAttributes(getAudioTrackAttributes(outputConfig.audioAttributes, outputConfig.isTunneling)).setAudioFormat(new AudioFormat.Builder().setSampleRate(outputConfig.sampleRate).setChannelMask(outputConfig.channelMask).setEncoding(outputConfig.encoding).build()).setTransferMode(1).setBufferSizeInBytes(outputConfig.bufferSize).setSessionId(i10);
                int i11 = Build.VERSION.SDK_INT;
                if (i11 >= 29) {
                    sessionId.setOffloadedPlayback(outputConfig.isOffload);
                }
                if (i11 >= 34 && context != null) {
                    sessionId.setContext(context);
                }
                BiConsumer<AudioTrack.Builder, AudioOutputProvider.OutputConfig> biConsumer = this.builderModifier;
                if (biConsumer != null && i11 >= 24) {
                    biConsumer.accept(sessionId, outputConfig);
                }
                audioTrackBuild = sessionId.build();
            }
            if (audioTrackBuild.getState() == 1) {
                return new AudioTrackAudioOutput(audioTrackBuild, outputConfig, this.capabilityChangeListener, this.clock);
            }
            try {
                audioTrackBuild.release();
            } catch (Exception unused) {
            }
            throw new AudioOutputProvider.InitializationException();
        } catch (IllegalArgumentException e5) {
            e = e5;
            throw new AudioOutputProvider.InitializationException(e);
        } catch (UnsupportedOperationException e6) {
            e = e6;
            throw new AudioOutputProvider.InitializationException(e);
        }
    }
}

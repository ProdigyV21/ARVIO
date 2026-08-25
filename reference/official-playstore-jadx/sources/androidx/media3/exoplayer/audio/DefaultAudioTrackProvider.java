package androidx.media3.exoplayer.audio;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioTrack;
import android.os.Build;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.audio.AudioSink;
import androidx.media3.exoplayer.audio.DefaultAudioSink;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class DefaultAudioTrackProvider implements DefaultAudioSink.AudioTrackProvider {
    private AudioAttributes getAudioTrackAttributes(androidx.media3.common.AudioAttributes audioAttributes, boolean z) {
        return z ? getAudioTrackTunnelingAttributes() : audioAttributes.getPlatformAudioAttributes();
    }

    private AudioAttributes getAudioTrackTunnelingAttributes() {
        return new AudioAttributes.Builder().setContentType(3).setFlags(16).setUsage(1).build();
    }

    private void setOffloadedPlaybackV29(AudioTrack.Builder builder, boolean z) {
        builder.setOffloadedPlayback(z);
    }

    public AudioTrack.Builder customizeAudioTrackBuilder(AudioTrack.Builder builder) {
        return builder;
    }

    @Override // androidx.media3.exoplayer.audio.DefaultAudioSink.AudioTrackProvider
    public final AudioTrack getAudioTrack(AudioSink.AudioTrackConfig audioTrackConfig, androidx.media3.common.AudioAttributes audioAttributes, int i10, Context context) {
        AudioTrack.Builder sessionId = new AudioTrack.Builder().setAudioAttributes(getAudioTrackAttributes(audioAttributes, audioTrackConfig.tunneling)).setAudioFormat(Util.getAudioFormat(audioTrackConfig.sampleRate, audioTrackConfig.channelConfig, audioTrackConfig.encoding)).setTransferMode(1).setBufferSizeInBytes(audioTrackConfig.bufferSize).setSessionId(i10);
        int i11 = Build.VERSION.SDK_INT;
        if (i11 >= 29) {
            setOffloadedPlaybackV29(sessionId, audioTrackConfig.offload);
        }
        if (i11 >= 34 && context != null) {
            sessionId.setContext(context);
        }
        return customizeAudioTrackBuilder(sessionId).build();
    }

    @Override // androidx.media3.exoplayer.audio.DefaultAudioSink.AudioTrackProvider
    public final /* synthetic */ int getAudioTrackChannelConfig(int i10) {
        return t.a(this, i10);
    }
}

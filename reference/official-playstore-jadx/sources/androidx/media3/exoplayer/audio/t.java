package androidx.media3.exoplayer.audio;

import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.audio.DefaultAudioSink;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class t {
    public static int a(DefaultAudioSink.AudioTrackProvider audioTrackProvider, int i10) {
        return Util.getAudioTrackChannelConfig(i10);
    }
}

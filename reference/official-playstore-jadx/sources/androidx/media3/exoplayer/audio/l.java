package androidx.media3.exoplayer.audio;

import android.media.AudioDeviceInfo;
import androidx.media3.common.Format;
import androidx.media3.common.util.Clock;
import androidx.media3.exoplayer.analytics.PlayerId;

/* JADX INFO: loaded from: classes3.dex */
public abstract /* synthetic */ class l {
    public static AudioOffloadSupport a(AudioSink audioSink, Format format) {
        return AudioOffloadSupport.DEFAULT_UNSUPPORTED;
    }

    public static void c(AudioSink audioSink, AudioOutputProvider audioOutputProvider) {
        throw new UnsupportedOperationException("AudioSink doesn't support setAudioOutputProvider");
    }

    public static void b(AudioSink audioSink) {
    }

    public static void d(AudioSink audioSink, Clock clock) {
    }

    public static void f(AudioSink audioSink, int i10) {
    }

    public static void g(AudioSink audioSink, long j10) {
    }

    public static void h(AudioSink audioSink, PlayerId playerId) {
    }

    public static void i(AudioSink audioSink, AudioDeviceInfo audioDeviceInfo) {
    }

    public static void j(AudioSink audioSink, int i10) {
    }

    public static void e(AudioSink audioSink, int i10, int i11) {
    }
}

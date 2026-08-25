package com.discord.org.webrtc;

/* JADX INFO: loaded from: classes4.dex */
public class AudioTrack extends MediaStreamTrack {
    public AudioTrack(long j10) {
        super(j10);
    }

    private static native void nativeSetVolume(long j10, double d4);

    public long getNativeAudioTrack() {
        return getNativeMediaStreamTrack();
    }

    public void setVolume(double d4) {
        nativeSetVolume(getNativeAudioTrack(), d4);
    }
}

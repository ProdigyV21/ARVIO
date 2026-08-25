package com.discord.org.webrtc;

/* JADX INFO: loaded from: classes4.dex */
public class AudioSource extends MediaSource {
    public AudioSource(long j10) {
        super(j10);
    }

    public long getNativeAudioSource() {
        return getNativeMediaSource();
    }
}

package com.discord.org.webrtc;

/* JADX INFO: loaded from: classes4.dex */
public interface CapturerObserver {
    void onCapturerStarted(boolean z);

    void onCapturerStopped();

    void onFrameCaptured(VideoFrame videoFrame);
}

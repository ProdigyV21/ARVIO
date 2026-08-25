package com.discord.org.webrtc;

import android.content.Context;

/* JADX INFO: loaded from: classes4.dex */
public interface VideoCapturer {
    void changeCaptureFormat(int i10, int i11, int i12);

    void dispose();

    void initialize(SurfaceTextureHelper surfaceTextureHelper, Context context, CapturerObserver capturerObserver);

    boolean isScreencast();

    void startCapture(int i10, int i11, int i12);

    void stopCapture() throws InterruptedException;
}

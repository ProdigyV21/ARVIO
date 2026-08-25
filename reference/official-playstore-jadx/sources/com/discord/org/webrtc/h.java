package com.discord.org.webrtc;

import com.discord.org.webrtc.EglBase;
import com.discord.org.webrtc.EglBase10Impl;
import com.discord.org.webrtc.EglBase14Impl;
import com.discord.org.webrtc.VideoFrame;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class h implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f12513i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f12514l;

    public /* synthetic */ h(Object obj, int i10) {
        this.f12513i = i10;
        this.f12514l = obj;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f12513i) {
            case 0:
                ((EglBase10Impl.EglConnection) this.f12514l).lambda$new$0();
                break;
            case 1:
                ((EglBase14Impl.EglConnection) this.f12514l).lambda$new$0();
                break;
            case 2:
                ((EglRenderer) this.f12514l).renderFrameOnRenderThread();
                break;
            case 3:
                ((EglBase.EglConnection) this.f12514l).release();
                break;
            case 4:
                ((VideoFrame.I420Buffer) this.f12514l).release();
                break;
            case 5:
                ((ScreenCapturerAndroid) this.f12514l).updateVirtualDisplay();
                break;
            case 6:
                ((CountDownLatch) this.f12514l).countDown();
                break;
            default:
                ((VideoFileRenderer) this.f12514l).lambda$release$3();
                break;
        }
    }
}

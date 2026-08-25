package com.discord.org.webrtc;

import com.discord.org.webrtc.TextureBufferImpl;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class y implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f12550i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f12551l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f12552m;

    public /* synthetic */ y(Object obj, Object obj2, int i10) {
        this.f12550i = i10;
        this.f12551l = obj;
        this.f12552m = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f12550i) {
            case 0:
                ((TextureBufferImpl) this.f12551l).lambda$new$0((TextureBufferImpl.RefCountMonitor) this.f12552m);
                break;
            case 1:
                ((EglRenderer) this.f12551l).lambda$release$0((CountDownLatch) this.f12552m);
                break;
            case 2:
                ((EglRenderer) this.f12551l).lambda$releaseEglSurface$4((Runnable) this.f12552m);
                break;
            case 3:
                ((VideoFileRenderer) this.f12551l).lambda$onFrame$0((VideoFrame) this.f12552m);
                break;
            case 4:
                ((VideoFileRenderer) this.f12551l).lambda$release$2((CountDownLatch) this.f12552m);
                break;
            default:
                ((VideoSource) this.f12551l).lambda$setVideoProcessor$0((VideoFrame) this.f12552m);
                break;
        }
    }
}

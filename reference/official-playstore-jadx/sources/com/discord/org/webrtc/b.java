package com.discord.org.webrtc;

import com.discord.org.webrtc.Camera2Session;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class b implements VideoSink {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f12507i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f12508l;

    public /* synthetic */ b(Object obj, int i10) {
        this.f12507i = i10;
        this.f12508l = obj;
    }

    @Override // com.discord.org.webrtc.VideoSink
    public final void onFrame(VideoFrame videoFrame) {
        switch (this.f12507i) {
            case 0:
                ((Camera1Session) this.f12508l).lambda$listenForTextureFrames$0(videoFrame);
                break;
            case 1:
                ((Camera2Session.CaptureSessionCallback) this.f12508l).lambda$onConfigured$0(videoFrame);
                break;
            default:
                ((VideoSource) this.f12508l).lambda$setVideoProcessor$1(videoFrame);
                break;
        }
    }
}

package com.discord.org.webrtc;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class v implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f12543i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ SurfaceTextureHelper f12544l;

    public /* synthetic */ v(SurfaceTextureHelper surfaceTextureHelper, int i10) {
        this.f12543i = i10;
        this.f12544l = surfaceTextureHelper;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f12543i) {
            case 0:
                this.f12544l.lambda$forceFrame$3();
                break;
            case 1:
                this.f12544l.lambda$dispose$6();
                break;
            case 2:
                this.f12544l.lambda$stopListening$1();
                break;
            default:
                this.f12544l.lambda$returnTextureFrame$5();
                break;
        }
    }
}

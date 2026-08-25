package com.discord.org.webrtc;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class w implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f12545i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f12546l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f12547m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f12548n;

    public /* synthetic */ w(int i10, int i11, int i12, Object obj) {
        this.f12545i = i12;
        this.f12548n = obj;
        this.f12546l = i10;
        this.f12547m = i11;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f12545i) {
            case 0:
                ((SurfaceTextureHelper) this.f12548n).lambda$setTextureSize$2(this.f12546l, this.f12547m);
                break;
            default:
                ((SurfaceViewRenderer) this.f12548n).lambda$onFrameResolutionChanged$0(this.f12546l, this.f12547m);
                break;
        }
    }
}

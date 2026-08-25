package com.discord.org.webrtc;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class o implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f12533i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f12534l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f12535m;

    public /* synthetic */ o(Object obj, int i10, int i11) {
        this.f12533i = i11;
        this.f12535m = obj;
        this.f12534l = i10;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f12533i) {
            case 0:
                ((HardwareVideoEncoder) this.f12535m).lambda$deliverEncodedImage$0(this.f12534l);
                break;
            default:
                ((SurfaceTextureHelper) this.f12535m).lambda$setFrameRotation$4(this.f12534l);
                break;
        }
    }
}

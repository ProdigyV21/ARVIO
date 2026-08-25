package com.discord.org.webrtc;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class m implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f12529i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ EglThread f12530l;

    public /* synthetic */ m(EglThread eglThread, int i10) {
        this.f12529i = i10;
        this.f12530l = eglThread;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f12529i) {
            case 0:
                this.f12530l.lambda$onRenderWindowClose$3();
                break;
            default:
                this.f12530l.lambda$onRenderWindowOpen$2();
                break;
        }
    }
}

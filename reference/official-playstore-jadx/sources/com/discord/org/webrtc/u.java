package com.discord.org.webrtc;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class u implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f12541i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ RenderSynchronizer f12542l;

    public /* synthetic */ u(RenderSynchronizer renderSynchronizer, int i10) {
        this.f12541i = i10;
        this.f12542l = renderSynchronizer;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f12541i) {
            case 0:
                this.f12542l.lambda$new$0();
                break;
            default:
                this.f12542l.lambda$registerListener$1();
                break;
        }
    }
}

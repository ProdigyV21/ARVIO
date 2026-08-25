package com.discord.org.webrtc;

import com.discord.org.webrtc.Camera1Session;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class c implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f12509i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Camera1Session.AnonymousClass2 f12510l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ byte[] f12511m;

    public /* synthetic */ c(Camera1Session.AnonymousClass2 anonymousClass2, byte[] bArr, int i10) {
        this.f12509i = i10;
        this.f12510l = anonymousClass2;
        this.f12511m = bArr;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f12509i) {
            case 0:
                this.f12510l.lambda$onPreviewFrame$0(this.f12511m);
                break;
            default:
                this.f12510l.lambda$onPreviewFrame$1(this.f12511m);
                break;
        }
    }
}

package com.discord.org.webrtc;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class p implements Runnable {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f12536i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ ByteBuffer f12537l;

    public /* synthetic */ p(ByteBuffer byteBuffer, int i10) {
        this.f12536i = i10;
        this.f12537l = byteBuffer;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.f12536i) {
            case 0:
                JniCommon.nativeFreeByteBuffer(this.f12537l);
                break;
            default:
                JniCommon.nativeFreeByteBuffer(this.f12537l);
                break;
        }
    }
}

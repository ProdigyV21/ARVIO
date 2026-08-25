package com.discord.org.webrtc;

import android.view.Choreographer;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class t implements Choreographer.FrameCallback {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ RenderSynchronizer f12540i;

    public /* synthetic */ t(RenderSynchronizer renderSynchronizer) {
        this.f12540i = renderSynchronizer;
    }

    @Override // android.view.Choreographer.FrameCallback
    public final void doFrame(long j10) {
        this.f12540i.onDisplayRefreshCycleBegin(j10);
    }
}

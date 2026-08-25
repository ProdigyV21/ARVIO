package com.arflix.tv.ui.components;

import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import com.arflix.tv.data.model.IptvProgram;
import com.arflix.tv.ui.screens.tv.live.FullscreenGuideOverlayKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class t1 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8399i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ long f8400l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ State f8401m;

    public /* synthetic */ t1(long j10, State state, int i10) {
        this.f8399i = i10;
        this.f8400l = j10;
        this.f8401m = state;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f8399i) {
            case 0:
                return PremiumEffectsKt.RingPulseEffect_FNF3uiM$lambda$0$0$2$0(this.f8400l, this.f8401m, (DrawScope) obj);
            default:
                return Boolean.valueOf(FullscreenGuideOverlayKt.FullscreenGuideOverlay$lambda$6$0(this.f8400l, (MutableLongState) this.f8401m, (IptvProgram) obj));
        }
    }
}

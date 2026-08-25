package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.GraphicsLayerScope;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class w implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11856i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ State f11857l;

    public /* synthetic */ w(State state, int i10) {
        this.f11856i = i10;
        this.f11857l = state;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f11856i) {
            case 0:
                return ChannelRowKt.ChannelRow_ZSrRDQQ$lambda$11$0(this.f11857l, (GraphicsLayerScope) obj);
            default:
                return LiveTvScreenKt.LiveTvScreen$lambda$251$12$0(this.f11857l, (GraphicsLayerScope) obj);
        }
    }
}

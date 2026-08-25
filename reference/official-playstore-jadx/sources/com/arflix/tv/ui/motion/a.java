package com.arflix.tv.ui.motion;

import androidx.compose.ui.graphics.GraphicsLayerScope;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class a implements l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8581i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ ArvioBackMotion f8582l;

    public /* synthetic */ a(ArvioBackMotion arvioBackMotion, int i10) {
        this.f8581i = i10;
        this.f8582l = arvioBackMotion;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f8581i) {
            case 0:
                return ArvioPredictiveBackKt.arvioBackModal$lambda$0(this.f8582l, (GraphicsLayerScope) obj);
            default:
                return ArvioPredictiveBackKt.arvioBackSurface$lambda$0(this.f8582l, (GraphicsLayerScope) obj);
        }
    }
}

package com.arflix.tv.ui.screens.details;

import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.GraphicsLayerScope;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class s implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8973i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ State f8974l;

    public /* synthetic */ s(State state, int i10) {
        this.f8973i = i10;
        this.f8974l = state;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f8973i) {
            case 0:
                return DetailsScreenKt.PremiumActionButton$lambda$9$0(this.f8974l, (GraphicsLayerScope) obj);
            case 1:
                return DetailsScreenKt.EpisodeCard_EUb7tLY$lambda$7$0(this.f8974l, (GraphicsLayerScope) obj);
            case 2:
                return DetailsScreenKt.ReviewCard$lambda$2$0(this.f8974l, (GraphicsLayerScope) obj);
            case 3:
                return DetailsScreenKt.CircularCastCard$lambda$2$0$0(this.f8974l, (GraphicsLayerScope) obj);
            default:
                return DetailsScreenKt.CastCard$lambda$1$0$0(this.f8974l, (GraphicsLayerScope) obj);
        }
    }
}

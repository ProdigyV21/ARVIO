package com.arflix.tv.ui.screens.collections;

import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.ui.PlayerView;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class d implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8600i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f8601l;

    public /* synthetic */ d(Object obj, int i10) {
        this.f8600i = i10;
        this.f8601l = obj;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f8600i) {
            case 0:
                return CollectionDetailsScreenKt.CollectionDetailsScreen$lambda$31$4$0((CollectionDetailsViewModel) this.f8601l, (List) obj);
            default:
                return VideoHeroKt.VideoHero$lambda$5$0((ExoPlayer) this.f8601l, (PlayerView) obj);
        }
    }
}

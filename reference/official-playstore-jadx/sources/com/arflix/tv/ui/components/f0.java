package com.arflix.tv.ui.components;

import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.runtime.Composer;
import com.arflix.tv.data.model.MediaItem;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class f0 implements r7.r {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7952i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f7953l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MediaItem f7954m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f7955n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ float f7956o;

    public /* synthetic */ f0(boolean z, MediaItem mediaItem, float f10, String str) {
        this.f7953l = z;
        this.f7954m = mediaItem;
        this.f7956o = f10;
        this.f7955n = str;
    }

    @Override // r7.r
    public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
        switch (this.f7952i) {
            case 0:
                int iIntValue = ((Integer) obj4).intValue();
                return ContinueWatchingCardKt.ContinueWatchingCardCompact$lambda$1(this.f7953l, this.f7954m, this.f7956o, this.f7955n, (BoxScope) obj, ((Boolean) obj2).booleanValue(), (Composer) obj3, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj4).intValue();
                boolean z = this.f7953l;
                MediaItem mediaItem = this.f7954m;
                float f10 = this.f7956o;
                return ContinueWatchingCardKt.ContinueWatchingCard_iHT_50w$lambda$1$0(z, mediaItem, this.f7955n, f10, (BoxScope) obj, ((Boolean) obj2).booleanValue(), (Composer) obj3, iIntValue2);
        }
    }

    public /* synthetic */ f0(boolean z, MediaItem mediaItem, String str, float f10) {
        this.f7953l = z;
        this.f7954m = mediaItem;
        this.f7955n = str;
        this.f7956o = f10;
    }
}

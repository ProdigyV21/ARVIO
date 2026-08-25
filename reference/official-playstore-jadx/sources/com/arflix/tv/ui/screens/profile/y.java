package com.arflix.tv.ui.screens.profile;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import com.arflix.tv.ui.screens.tv.live.ChannelLogoKt;
import com.arflix.tv.ui.screens.tv.live.EnrichedChannel;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class y implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10083i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ float f10084l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Modifier f10085m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f10086n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f10087o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f10088p;

    public /* synthetic */ y(float f10, Modifier modifier, r7.a aVar, int i10, int i11) {
        this.f10084l = f10;
        this.f10085m = modifier;
        this.f10088p = aVar;
        this.f10086n = i10;
        this.f10087o = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f10083i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return ProfileSelectionScreenKt.AddProfileButton_Kz89ssw$lambda$5(this.f10084l, this.f10085m, (r7.a) this.f10088p, this.f10086n, this.f10087o, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return ChannelLogoKt.ChannelLogo_uFdPcIQ$lambda$4((EnrichedChannel) this.f10088p, this.f10084l, this.f10085m, this.f10086n, this.f10087o, (Composer) obj, iIntValue2);
        }
    }

    public /* synthetic */ y(EnrichedChannel enrichedChannel, float f10, Modifier modifier, int i10, int i11) {
        this.f10088p = enrichedChannel;
        this.f10084l = f10;
        this.f10085m = modifier;
        this.f10086n = i10;
        this.f10087o = i11;
    }
}

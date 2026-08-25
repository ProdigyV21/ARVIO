package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import androidx.compose.ui.Modifier;
import com.arflix.tv.data.model.IptvNowNext;
import com.arflix.tv.data.model.StreamSource;
import com.arflix.tv.ui.screens.tv.live.EnrichedChannel;
import com.arflix.tv.ui.screens.tv.live.QuickZapOverlayKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class c2 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7865i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f7866l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f7867m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f7868n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ int f7869o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f7870p;

    public /* synthetic */ c2(int i10, EnrichedChannel enrichedChannel, IptvNowNext iptvNowNext, boolean z, int i11) {
        this.f7865i = 2;
        this.f7868n = i10;
        this.f7870p = enrichedChannel;
        this.f7867m = iptvNowNext;
        this.f7866l = z;
        this.f7869o = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f7865i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return ScreensaverKt.Screensaver$lambda$15(this.f7866l, (r7.a) this.f7870p, (Modifier) this.f7867m, this.f7868n, this.f7869o, (Composer) obj, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj2).intValue();
                return SourceInfoOverlayKt.SourceInfoOverlay$lambda$1(this.f7866l, (StreamSource) this.f7870p, (Modifier) this.f7867m, this.f7868n, this.f7869o, (Composer) obj, iIntValue2);
            default:
                int iIntValue3 = ((Integer) obj2).intValue();
                return QuickZapOverlayKt.FocusedChannelSlot$lambda$1(this.f7868n, (EnrichedChannel) this.f7870p, (IptvNowNext) this.f7867m, this.f7866l, this.f7869o, (Composer) obj, iIntValue3);
        }
    }

    public /* synthetic */ c2(boolean z, Object obj, Modifier modifier, int i10, int i11, int i12) {
        this.f7865i = i12;
        this.f7866l = z;
        this.f7870p = obj;
        this.f7867m = modifier;
        this.f7868n = i10;
        this.f7869o = i11;
    }
}

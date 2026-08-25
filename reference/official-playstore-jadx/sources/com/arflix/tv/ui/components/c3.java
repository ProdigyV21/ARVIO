package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import com.arflix.tv.data.model.StreamSource;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class c3 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7871i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f7872l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f7873m;

    public /* synthetic */ c3(Object obj, int i10, int i11) {
        this.f7871i = i11;
        this.f7873m = obj;
        this.f7872l = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f7871i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return StreamSelectorKt.PremiumQualityPill$lambda$1((SourcePresentation) this.f7873m, this.f7872l, (Composer) obj, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj2).intValue();
                return PlaybackQualityBadgesKt.PlaybackBadgeItem$lambda$0((PlaybackBadge) this.f7873m, this.f7872l, (Composer) obj, iIntValue2);
            default:
                int iIntValue3 = ((Integer) obj2).intValue();
                return StreamSelectorKt.CompactQualityBadge$lambda$2((StreamSource) this.f7873m, this.f7872l, (Composer) obj, iIntValue3);
        }
    }
}

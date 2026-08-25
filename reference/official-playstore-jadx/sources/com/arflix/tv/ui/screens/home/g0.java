package com.arflix.tv.ui.screens.home;

import androidx.compose.runtime.Composer;
import com.arflix.tv.data.model.IptvNowNext;
import com.arflix.tv.ui.screens.tv.live.EnrichedChannel;
import com.arflix.tv.ui.screens.tv.live.MiniPlayerKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class g0 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9139i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ long f9140l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f9141m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f9142n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f9143o;

    public /* synthetic */ g0(long j10, int i10, String str, String str2) {
        this.f9142n = str;
        this.f9143o = str2;
        this.f9140l = j10;
        this.f9141m = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f9139i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return HomeScreenKt.ArcticFuseRatingBadge_XO_JAsU$lambda$1((String) this.f9142n, (String) this.f9143o, this.f9140l, this.f9141m, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return MiniPlayerKt.NowCard$lambda$1((EnrichedChannel) this.f9142n, this.f9140l, (IptvNowNext) this.f9143o, this.f9141m, (Composer) obj, iIntValue2);
        }
    }

    public /* synthetic */ g0(EnrichedChannel enrichedChannel, long j10, IptvNowNext iptvNowNext, int i10) {
        this.f9142n = enrichedChannel;
        this.f9140l = j10;
        this.f9143o = iptvNowNext;
        this.f9141m = i10;
    }
}

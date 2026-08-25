package com.arflix.tv.ui.screens.settings.telegram;

import androidx.compose.runtime.Composer;
import com.arflix.tv.data.repository.HomeServerCatalogCandidate;
import com.arflix.tv.ui.screens.tv.live.LivePanesKt;
import com.arflix.tv.ui.screens.tv.live.MiniPlayerKt;
import com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt;
import r7.l;
import r7.p;
import x6.o;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class f implements p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10978i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ int f10979l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f10980m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f10981n;

    public /* synthetic */ f(int i10, int i11, int i12, Object obj) {
        this.f10978i = i12;
        this.f10981n = obj;
        this.f10979l = i10;
        this.f10980m = i11;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f10978i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return TelegramSettingsScreenKt.CodeContent$lambda$4(this.f10979l, (l) this.f10981n, this.f10980m, (Composer) obj, iIntValue);
            case 1:
                int iIntValue2 = ((Integer) obj2).intValue();
                return LivePanesKt.LoadingPane$lambda$1((String) this.f10981n, this.f10979l, this.f10980m, (Composer) obj, iIntValue2);
            case 2:
                int iIntValue3 = ((Integer) obj2).intValue();
                return MiniPlayerKt.SourceBadge$lambda$2(this.f10979l, (r7.a) this.f10981n, this.f10980m, (Composer) obj, iIntValue3);
            default:
                int iIntValue4 = ((Integer) obj2).intValue();
                return WatchlistScreenKt.MobileLibrarySelector$lambda$3$3$0$0((HomeServerCatalogCandidate) this.f10981n, this.f10979l, this.f10980m, (Composer) obj, iIntValue4);
        }
    }

    public /* synthetic */ f(int i10, o oVar, int i11, int i12) {
        this.f10978i = i12;
        this.f10979l = i10;
        this.f10981n = oVar;
        this.f10980m = i11;
    }
}

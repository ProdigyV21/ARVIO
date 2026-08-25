package com.arflix.tv.ui.screens.settings;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableIntState;
import com.arflix.tv.data.repository.HomeServerCatalogCandidate;
import com.arflix.tv.ui.screens.player.SubtitleAiModel;
import com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt;
import com.arflix.tv.ui.screens.watchlist.WatchlistViewModel;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class r1 implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10876i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ boolean f10877l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f10878m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f10879n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f10880o;

    public /* synthetic */ r1(boolean z, Object obj, Object obj2, Object obj3, int i10) {
        this.f10876i = i10;
        this.f10877l = z;
        this.f10878m = obj;
        this.f10879n = obj2;
        this.f10880o = obj3;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f10876i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return SettingsScreenKt.AiModelDialog$lambda$1(this.f10877l, (List) this.f10878m, (SubtitleAiModel) this.f10879n, (r7.l) this.f10880o, (Composer) obj, iIntValue);
            default:
                return WatchlistScreenKt.WatchlistScreen$lambda$74$0$2$0(this.f10877l, (WatchlistViewModel) this.f10878m, (MutableIntState) this.f10879n, (MutableIntState) this.f10880o, ((Integer) obj).intValue(), (HomeServerCatalogCandidate) obj2);
        }
    }
}

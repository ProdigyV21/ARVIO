package com.arflix.tv.ui.screens.collections;

import androidx.compose.runtime.Composer;
import com.arflix.tv.ui.screens.home.HomeScreenKt;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsScreenKt;
import com.arflix.tv.ui.screens.tv.live.ChannelRowKt;
import com.arflix.tv.ui.screens.tv.live.MiniPlayerKt;
import com.arflix.tv.ui.screens.tv.live.QuickZapOverlayKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class b implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8596i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ String f8597l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f8598m;

    public /* synthetic */ b(String str, int i10, int i11) {
        this.f8596i = i11;
        this.f8597l = str;
        this.f8598m = i10;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        int i10 = this.f8596i;
        Composer composer = (Composer) obj;
        int iIntValue = ((Integer) obj2).intValue();
        switch (i10) {
            case 0:
                return CollectionDetailsScreenKt.CollectionEmptyState$lambda$1(this.f8597l, this.f8598m, composer, iIntValue);
            case 1:
                return HomeScreenKt.ImdbBadge$lambda$1(this.f8597l, this.f8598m, composer, iIntValue);
            case 2:
                return HomeScreenKt.MetaPill$lambda$1(this.f8597l, this.f8598m, composer, iIntValue);
            case 3:
                return SettingsScreenKt.SettingsSectionGroupLabel$lambda$0(this.f8597l, this.f8598m, composer, iIntValue);
            case 4:
                return SettingsScreenKt.TvSettingsStatusPill$lambda$1(this.f8597l, this.f8598m, composer, iIntValue);
            case 5:
                return TelegramSettingsScreenKt.LoadingContent$lambda$1(this.f8597l, this.f8598m, composer, iIntValue);
            case 6:
                return TelegramSettingsScreenKt.QrContent$lambda$2(this.f8597l, this.f8598m, composer, iIntValue);
            case 7:
                return ChannelRowKt.SmallPillBadge$lambda$1(this.f8597l, this.f8598m, composer, iIntValue);
            case 8:
                return MiniPlayerKt.LangBadge$lambda$1(this.f8597l, this.f8598m, composer, iIntValue);
            default:
                return QuickZapOverlayKt.NonFocusedCategoryRow$lambda$0(this.f8597l, this.f8598m, composer, iIntValue);
        }
    }
}

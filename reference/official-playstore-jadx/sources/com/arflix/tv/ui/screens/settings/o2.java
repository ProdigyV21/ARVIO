package com.arflix.tv.ui.screens.settings;

import androidx.compose.ui.graphics.drawscope.DrawScope;
import com.arflix.tv.data.repository.IptvPlaylistEntry;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class o2 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10773i;

    public /* synthetic */ o2(int i10) {
        this.f10773i = i10;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f10773i) {
            case 0:
                return SettingsScreenKt.TvGeneralSettingsRows$lambda$3$0(((Boolean) obj).booleanValue());
            case 1:
                return SettingsScreenKt.TvGeneralSettingsRows$lambda$4$0(((Boolean) obj).booleanValue());
            case 2:
                return SettingsScreenKt.TvGeneralSettingsRows$lambda$6$0(((Boolean) obj).booleanValue());
            case 3:
                return SettingsScreenKt.TvGeneralSettingsRows$lambda$7$0(((Boolean) obj).booleanValue());
            case 4:
                return SettingsScreenKt.TvGeneralSettingsRows$lambda$8$0(((Boolean) obj).booleanValue());
            case 5:
                return SettingsScreenKt.TvGeneralSettingsRows$lambda$9$0(((Boolean) obj).booleanValue());
            case 6:
                return Integer.valueOf(SettingsScreenKt.MobileSettingsLayout$lambda$10$1$0(((Integer) obj).intValue()));
            case 7:
                return Integer.valueOf(SettingsScreenKt.MobileSettingsLayout$lambda$10$2$0(((Integer) obj).intValue()));
            case 8:
                return SettingsScreenKt.IptvSettings$lambda$0$0((String) obj);
            case 9:
                return SettingsScreenKt.IptvSettings$lambda$1$0((String) obj);
            case 10:
                return SettingsScreenKt.DiscordWebViewModal$lambda$18$1$0$2$2$0$0((DrawScope) obj);
            case 11:
                return SettingsScreenKt.StremioAddonsSettings$lambda$0$0((String) obj);
            case 12:
                return SettingsScreenKt.StremioAddonsSettings$lambda$1$0((String) obj);
            case 13:
                return SettingsScreenKt.StremioAddonsSettings$lambda$2$0((String) obj);
            case 14:
                return SettingsScreenKt.StremioAddonsSettings$lambda$3$0((String) obj);
            case 15:
                return SettingsScreenKt.QualityFiltersModal$lambda$0$0(((Integer) obj).intValue());
            case 16:
                return SettingsScreenKt.QualityFiltersModal$lambda$1$0(((Integer) obj).intValue());
            case 17:
                return SettingsScreenKt.SettingsScreen$lambda$241$111$0((String) obj);
            default:
                return SettingsViewModelKt.syncSignature$lambda$0((IptvPlaylistEntry) obj);
        }
    }
}

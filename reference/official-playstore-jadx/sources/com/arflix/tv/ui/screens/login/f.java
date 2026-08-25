package com.arflix.tv.ui.screens.login;

import androidx.compose.runtime.MutableState;
import com.arflix.tv.ui.screens.plugin.PluginScreenKt;
import com.arflix.tv.ui.screens.search.SearchScreenKt;
import com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsScreenKt;
import com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class f implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9374i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableState f9375l;

    public /* synthetic */ f(MutableState mutableState, int i10) {
        this.f9374i = i10;
        this.f9375l = mutableState;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f9374i) {
            case 0:
                return LoginScreenKt.LoginScreen$lambda$23$0$1$13$0(this.f9375l);
            case 1:
                return PluginScreenKt.PluginScreen$lambda$18$4$0$0(this.f9375l);
            case 2:
                return PluginScreenKt.PluginScreen$lambda$20$2$0(this.f9375l);
            case 3:
                return PluginScreenKt.PluginScreen$lambda$20$7$0(this.f9375l);
            case 4:
                return PluginScreenKt.PluginScreen$lambda$22$0(this.f9375l);
            case 5:
                return PluginScreenKt.PluginScreen$lambda$24$0(this.f9375l);
            case 6:
                return PluginScreenKt.PluginScreen$lambda$25$1$0(this.f9375l);
            case 7:
                return PluginScreenKt.PluginScreen$lambda$18$1$0$0(this.f9375l);
            case 8:
                return SearchScreenKt.SearchScreen$lambda$69$0$0$3$0(this.f9375l);
            case 9:
                return TelegramSettingsScreenKt.TelegramSettingsScreen$lambda$5$2$0(this.f9375l);
            case 10:
                return TelegramSettingsScreenKt.TelegramSettingsScreen$lambda$5$0$0$6$0(this.f9375l);
            case 11:
                return WatchlistScreenKt.MobileLibrarySelector$lambda$3$0$0(this.f9375l);
            default:
                return WatchlistScreenKt.MobileLibrarySelector$lambda$3$2$0(this.f9375l);
        }
    }
}

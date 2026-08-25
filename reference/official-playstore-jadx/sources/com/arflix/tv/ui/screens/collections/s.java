package com.arflix.tv.ui.screens.collections;

import androidx.compose.foundation.text.KeyboardActionScope;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusState;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import androidx.lifecycle.y;
import androidx.media3.exoplayer.ExoPlayer;
import com.arflix.tv.domain.model.ScraperInfo;
import com.arflix.tv.ui.screens.login.LoginScreenKt;
import com.arflix.tv.ui.screens.plugin.PluginScreenKt;
import com.arflix.tv.ui.screens.plugin.PluginViewModel;
import com.arflix.tv.ui.screens.profile.ProfileSelectionScreenKt;
import com.arflix.tv.ui.screens.search.SearchScreenKt;
import com.arflix.tv.ui.screens.watchlist.WatchlistScreenKt;
import com.arflix.tv.ui.screens.watchlist.WatchlistViewModel;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class s implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8675i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f8676l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f8677m;

    public /* synthetic */ s(Object obj, Object obj2, int i10) {
        this.f8675i = i10;
        this.f8676l = obj;
        this.f8677m = obj2;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f8675i) {
            case 0:
                return VideoHeroKt.VideoHero$lambda$2$0((ExoPlayer) this.f8676l, (State) this.f8677m, (DisposableEffectScope) obj);
            case 1:
                return LoginScreenKt.LoginScreen$lambda$23$0$1$5$0((SoftwareKeyboardController) this.f8676l, (FocusRequester) this.f8677m, (KeyboardActionScope) obj);
            case 2:
                return PluginScreenKt.PluginScreen$lambda$20$5$0$0((PluginViewModel) this.f8676l, (ScraperInfo) this.f8677m, ((Boolean) obj).booleanValue());
            case 3:
                return PluginScreenKt.PluginScreen$lambda$21$0((PluginViewModel) this.f8676l, (MutableState) this.f8677m, (String) obj);
            case 4:
                return ProfileSelectionScreenKt.ProfileAvatar_aA_HZ9I$lambda$5$0$3$0((r7.a) this.f8676l, (MutableIntState) this.f8677m, (FocusState) obj);
            case 5:
                return SearchScreenKt.SearchScreen$lambda$69$0$1$0((MutableState) this.f8676l, (MutableIntState) this.f8677m, ((Integer) obj).intValue());
            case 6:
                return SearchScreenKt.GlowChip$lambda$5$0((r7.a) this.f8676l, (MutableState) this.f8677m, (FocusState) obj);
            default:
                return WatchlistScreenKt.WatchlistScreen$lambda$62$0((y) this.f8676l, (WatchlistViewModel) this.f8677m, (DisposableEffectScope) obj);
        }
    }
}

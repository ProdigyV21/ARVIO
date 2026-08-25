package com.arflix.tv.ui.screens.plugin;

import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.platform.SoftwareKeyboardController;
import com.arflix.tv.data.model.CatalogConfig;
import com.arflix.tv.domain.model.PluginRepository;
import com.arflix.tv.ui.screens.profile.ProfileSelectionScreenKt;
import com.arflix.tv.ui.screens.profile.ProfileViewModel;
import com.arflix.tv.ui.screens.search.SearchScreenKt;
import com.arflix.tv.ui.screens.search.SearchViewModel;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import com.arflix.tv.ui.screens.settings.SettingsUiState;
import com.arflix.tv.updater.UpdateStatus;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class e implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9866i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f9867l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f9868m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f9869n;

    public /* synthetic */ e(int i10, MutableState mutableState, Object obj, Object obj2) {
        this.f9866i = i10;
        this.f9867l = obj;
        this.f9868m = mutableState;
        this.f9869n = obj2;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f9866i) {
            case 0:
                return PluginScreenKt.PluginScreen$lambda$23$0((PluginViewModel) this.f9867l, (l) this.f9869n, (MutableState) this.f9868m);
            case 1:
                return PluginScreenKt.PluginScreen$lambda$25$0$0((PluginViewModel) this.f9867l, (PluginRepository) this.f9869n, (MutableState) this.f9868m);
            case 2:
                return ProfileSelectionScreenKt.ProfileSelectionScreen$lambda$13$0$2$1$0((ProfileViewModel) this.f9867l, (MutableState) this.f9868m, (State) this.f9869n);
            case 3:
                return SearchScreenKt.SearchScreen$lambda$69$0$0$1$0((SearchViewModel) this.f9867l, (SoftwareKeyboardController) this.f9869n, (MutableState) this.f9868m);
            case 4:
                return SearchScreenKt.SearchScreen$lambda$69$0$0$4$0((MutableState) this.f9868m, (MutableState) this.f9867l, (MutableIntState) this.f9869n);
            case 5:
                return SearchScreenKt.SearchScreen$lambda$69$0$0$5$0((SoftwareKeyboardController) this.f9867l, (MutableState) this.f9868m, (MutableState) this.f9869n);
            case 6:
                return SearchScreenKt.SearchScreen$lambda$69$0$2$0((ColumnScope) this.f9867l, (MutableState) this.f9868m, (FocusRequester) this.f9869n);
            case 7:
                return SettingsScreenKt.CatalogsSettings$lambda$6$2$0$2$1$0((CatalogConfig) this.f9867l, (MutableState) this.f9868m, (MutableState) this.f9869n);
            case 8:
                return SettingsScreenKt.AccountsSettings$lambda$4$14$0((UpdateStatus) this.f9867l, (r7.a) this.f9869n, (r7.a) this.f9868m);
            case 9:
                return SettingsScreenKt.IptvSettings$lambda$8$0$0$0((MutableState) this.f9868m, (l) this.f9869n, (MutableState) this.f9867l);
            case 10:
                return SettingsScreenKt.TrackingIntegrationsPage$lambda$20$1$3$0((SettingsUiState) this.f9867l, (r7.a) this.f9869n, (r7.a) this.f9868m);
            case 11:
                return SettingsScreenKt.SettingsScreen$lambda$208$0((State) this.f9867l, (MutableState) this.f9868m, (MutableState) this.f9869n);
            default:
                return SettingsScreenKt.SettingsScreen$lambda$239$0((r7.a) this.f9867l, (MutableState) this.f9868m, (MutableState) this.f9869n);
        }
    }

    public /* synthetic */ e(MutableState mutableState, MutableState mutableState2, MutableIntState mutableIntState) {
        this.f9866i = 4;
        this.f9868m = mutableState;
        this.f9867l = mutableState2;
        this.f9869n = mutableIntState;
    }

    public /* synthetic */ e(MutableState mutableState, l lVar, MutableState mutableState2) {
        this.f9866i = 9;
        this.f9868m = mutableState;
        this.f9869n = lVar;
        this.f9867l = mutableState2;
    }

    public /* synthetic */ e(Object obj, Object obj2, Object obj3, int i10) {
        this.f9866i = i10;
        this.f9867l = obj;
        this.f9869n = obj2;
        this.f9868m = obj3;
    }
}

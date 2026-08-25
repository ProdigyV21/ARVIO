package com.arflix.tv.ui.screens.settings;

import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class y1 implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11126i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f11127l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f11128m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f11129n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f11130o;

    public /* synthetic */ y1(MutableIntState mutableIntState, FocusRequester focusRequester, r7.a aVar, r7.a aVar2) {
        this.f11126i = 4;
        this.f11127l = focusRequester;
        this.f11129n = aVar;
        this.f11130o = aVar2;
        this.f11128m = mutableIntState;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        switch (this.f11126i) {
            case 0:
                return SettingsScreenKt.MobileSettingsSubPage$lambda$6$1((SettingsUiState) this.f11127l, (SettingsViewModel) this.f11128m, (r7.a) this.f11129n, (r7.a) this.f11130o, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            case 1:
                return SettingsScreenKt.IptvCategoriesSettings$lambda$2$0((List) this.f11127l, (String) this.f11128m, (List) this.f11129n, (r7.l) this.f11130o, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            case 2:
                return SettingsScreenKt.MobileCloudAccountSubPage$lambda$11((SettingsUiState) this.f11127l, (r7.a) this.f11129n, (SettingsViewModel) this.f11128m, (MutableState) this.f11130o, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            case 3:
                return SettingsScreenKt.MobileCloudAccountSubPage$lambda$13((SettingsUiState) this.f11127l, (r7.l) this.f11129n, (SettingsViewModel) this.f11128m, (MutableState) this.f11130o, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            default:
                return SettingsScreenKt.UnknownSourcesModal$lambda$5$1((FocusRequester) this.f11127l, (r7.a) this.f11129n, (r7.a) this.f11130o, (MutableIntState) this.f11128m, (BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    }

    public /* synthetic */ y1(SettingsUiState settingsUiState, x6.o oVar, SettingsViewModel settingsViewModel, MutableState mutableState, int i10) {
        this.f11126i = i10;
        this.f11127l = settingsUiState;
        this.f11129n = oVar;
        this.f11128m = settingsViewModel;
        this.f11130o = mutableState;
    }

    public /* synthetic */ y1(Object obj, Object obj2, Object obj3, x6.o oVar, int i10) {
        this.f11126i = i10;
        this.f11127l = obj;
        this.f11128m = obj2;
        this.f11129n = obj3;
        this.f11130o = oVar;
    }
}

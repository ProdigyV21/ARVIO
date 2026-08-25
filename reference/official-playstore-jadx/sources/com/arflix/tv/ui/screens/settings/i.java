package com.arflix.tv.ui.screens.settings;

import androidx.compose.runtime.MutableState;
import com.arflix.tv.data.model.CatalogConfig;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class i implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10550i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableState f10551l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MutableState f10552m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ MutableState f10553n;

    public /* synthetic */ i(MutableState mutableState, MutableState mutableState2, MutableState mutableState3, int i10) {
        this.f10550i = i10;
        this.f10551l = mutableState;
        this.f10552m = mutableState2;
        this.f10553n = mutableState3;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f10550i) {
            case 0:
                return SettingsScreenKt.SettingsScreen$lambda$241$9$0(this.f10551l, this.f10552m, this.f10553n, (CatalogConfig) obj);
            default:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$72$0(this.f10551l, this.f10552m, this.f10553n, (CatalogConfig) obj);
        }
    }
}

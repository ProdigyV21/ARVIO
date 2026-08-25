package com.arflix.tv.ui.screens.settings;

import androidx.compose.runtime.MutableState;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class s implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10908i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.a f10909l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MutableState f10910m;

    public /* synthetic */ s(r7.a aVar, MutableState mutableState, int i10) {
        this.f10908i = i10;
        this.f10909l = aVar;
        this.f10910m = mutableState;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f10908i) {
            case 0:
                return SettingsScreenKt.TrackingIntegrationsPage$lambda$15$0(this.f10909l, this.f10910m);
            case 1:
                return SettingsScreenKt.TrackingIntegrationsPage$lambda$17$0(this.f10909l, this.f10910m);
            case 2:
                return SettingsScreenKt.DiscordWebViewModal$lambda$17$0(this.f10909l, this.f10910m);
            default:
                return SettingsScreenKt.DiscordWebViewModal$lambda$18$1$0$0$0$0$0(this.f10909l, this.f10910m);
        }
    }
}

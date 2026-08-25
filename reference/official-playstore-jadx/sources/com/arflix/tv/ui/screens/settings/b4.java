package com.arflix.tv.ui.screens.settings;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.geometry.Offset;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class b4 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10342i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.l f10343l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MutableState f10344m;

    public /* synthetic */ b4(int i10, MutableState mutableState, r7.l lVar) {
        this.f10342i = i10;
        this.f10343l = lVar;
        this.f10344m = mutableState;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f10342i) {
            case 0:
                return SettingsScreenKt$AiApiKeyDialog$3$1$1$3$2$1.invokeSuspend$lambda$0(this.f10343l, this.f10344m, (Offset) obj);
            case 1:
                return SettingsScreenKt$CustomUserAgentDialog$3$1$1$2$2$1.invokeSuspend$lambda$0(this.f10343l, this.f10344m, (Offset) obj);
            default:
                return SettingsScreenKt.TrackingIntegrationsPage$lambda$13$0(this.f10343l, this.f10344m, (String) obj);
        }
    }
}

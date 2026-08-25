package com.arflix.tv.ui.screens.plugin;

import androidx.compose.runtime.MutableState;
import com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsScreenKt;
import r7.l;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class a implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9842i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ l f9843l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MutableState f9844m;

    public /* synthetic */ a(int i10, MutableState mutableState, l lVar) {
        this.f9842i = i10;
        this.f9843l = lVar;
        this.f9844m = mutableState;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f9842i) {
            case 0:
                return PluginScreenKt.AddRepoDialog$lambda$7$1$0$0$1$10$0(this.f9843l, this.f9844m);
            case 1:
                return TelegramSettingsScreenKt.PasswordContent$lambda$3$2$0(this.f9843l, this.f9844m);
            default:
                return TelegramSettingsScreenKt.CodeContent$lambda$3$3$0(this.f9843l, this.f9844m);
        }
    }
}

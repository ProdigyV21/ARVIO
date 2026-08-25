package com.arflix.tv.ui.screens.settings;

import androidx.compose.ui.geometry.Offset;
import com.arflix.tv.ui.screens.player.SubtitleAiModel;
import com.arflix.tv.ui.screens.settings.SettingsViewModel;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class c4 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10377i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f10378l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f10379m;

    public /* synthetic */ c4(Object obj, Object obj2, int i10) {
        this.f10377i = i10;
        this.f10378l = obj;
        this.f10379m = obj2;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f10377i) {
            case 0:
                return SettingsScreenKt$AiModelDialog$2$1$1$1$1$1.invokeSuspend$lambda$0((r7.l) this.f10378l, (SubtitleAiModel) this.f10379m, (Offset) obj);
            case 1:
                return SettingsViewModel.C15271.invokeSuspend$lambda$1$0((SettingsViewModel) this.f10378l, (ka.o2) this.f10379m, (Throwable) obj);
            default:
                return SettingsScreenKt.MobileSettingsSubPage$lambda$6$26$0((SettingsViewModel) this.f10379m, (r7.l) this.f10378l, (String) obj);
        }
    }

    public /* synthetic */ c4(r7.l lVar, SettingsViewModel settingsViewModel) {
        this.f10377i = 2;
        this.f10379m = settingsViewModel;
        this.f10378l = lVar;
    }
}

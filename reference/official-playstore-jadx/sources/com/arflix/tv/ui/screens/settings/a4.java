package com.arflix.tv.ui.screens.settings;

import androidx.compose.ui.geometry.Offset;
import com.arflix.tv.data.repository.HomeServerConnection;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class a4 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10285i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.a f10286l;

    public /* synthetic */ a4(int i10, r7.a aVar) {
        this.f10285i = i10;
        this.f10286l = aVar;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f10285i) {
            case 0:
                return SettingsScreenKt$AiApiKeyDialog$3$1$1$3$1$1.invokeSuspend$lambda$0(this.f10286l, (Offset) obj);
            case 1:
                return SettingsScreenKt$AiKeyQrOverlay$3$2$1$1.invokeSuspend$lambda$0(this.f10286l, (Offset) obj);
            case 2:
                return SettingsScreenKt$CustomUserAgentDialog$3$1$1$2$1$1.invokeSuspend$lambda$0(this.f10286l, (Offset) obj);
            case 3:
                return SettingsScreenKt.MobileSettingsSubPage$lambda$6$32$0(this.f10286l, (HomeServerConnection) obj);
            case 4:
                return SettingsScreenKt.GeneralSettings$lambda$29$1$0(this.f10286l, ((Boolean) obj).booleanValue());
            default:
                return SettingsScreenKt.TvGeneralSettingsRows$lambda$34$0$1$0(this.f10286l, ((Boolean) obj).booleanValue());
        }
    }
}

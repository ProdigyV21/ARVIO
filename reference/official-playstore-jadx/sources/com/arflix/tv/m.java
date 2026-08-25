package com.arflix.tv;

import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusState;
import com.arflix.tv.ui.components.AppBottomBarKt;
import com.arflix.tv.ui.screens.crash.CrashReportActivityKt;
import com.arflix.tv.ui.screens.login.LoginScreenKt;
import com.arflix.tv.ui.screens.plugin.PluginScreenKt;
import com.arflix.tv.ui.screens.settings.telegram.TelegramSettingsScreenKt;

/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class m implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7765i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableState f7766l;

    public /* synthetic */ m(MutableState mutableState, int i10) {
        this.f7765i = i10;
        this.f7766l = mutableState;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f7765i) {
            case 0:
                return MainActivityKt.ArflixApp$lambda$20$0$1$0(this.f7766l, ((Boolean) obj).booleanValue());
            case 1:
                return AppBottomBarKt.AppBottomBar$lambda$0$0$0$3$0(this.f7766l, (FocusState) obj);
            case 2:
                return CrashReportActivityKt.CrashReportScreen$lambda$9$0$2$1$0(this.f7766l, (FocusState) obj);
            case 3:
                return LoginScreenKt.LoginScreen$lambda$23$0$1$1$0(this.f7766l, (String) obj);
            case 4:
                return LoginScreenKt.LoginScreen$lambda$23$0$1$4$0(this.f7766l, (FocusState) obj);
            case 5:
                return LoginScreenKt.LoginScreen$lambda$23$0$1$6$0(this.f7766l, (FocusState) obj);
            case 6:
                return LoginScreenKt.LoginScreen$lambda$23$0$1$7$0(this.f7766l, (String) obj);
            case 7:
                return LoginScreenKt.LoginScreen$lambda$23$0$1$10$0(this.f7766l, (FocusState) obj);
            case 8:
                return LoginScreenKt.LoginScreen$lambda$23$0$1$12$0(this.f7766l, (FocusState) obj);
            case 9:
                return LoginScreenKt.LoginScreen$lambda$23$0$1$14$0(this.f7766l, (FocusState) obj);
            case 10:
                return PluginScreenKt.WarningDialog$lambda$3$1$0$0$0$6$0(this.f7766l, (FocusState) obj);
            case 11:
                return PluginScreenKt.AddRepoDialog$lambda$7$1$0$0$0$0(this.f7766l, (String) obj);
            case 12:
                return PluginScreenKt.AddRepoDialog$lambda$7$1$0$0$1$6$0(this.f7766l, (FocusState) obj);
            case 13:
                return PluginScreenKt.AddRepoDialog$lambda$7$1$0$0$1$9$0(this.f7766l, (FocusState) obj);
            case 14:
                return PluginScreenKt.WarningDialog$lambda$3$1$0$0$0$9$0(this.f7766l, (FocusState) obj);
            case 15:
                return TelegramSettingsScreenKt.PasswordContent$lambda$3$1$0(this.f7766l, (String) obj);
            case 16:
                return TelegramSettingsScreenKt.ConnectedContent$lambda$0$5$0(this.f7766l, (FocusState) obj);
            default:
                return TelegramSettingsScreenKt.PhoneContent$lambda$10$1$0(this.f7766l, (String) obj);
        }
    }
}

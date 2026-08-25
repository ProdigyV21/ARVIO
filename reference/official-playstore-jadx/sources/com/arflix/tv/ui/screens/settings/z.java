package com.arflix.tv.ui.screens.settings;

import android.content.Context;
import androidx.compose.runtime.State;
import androidx.compose.ui.platform.ClipboardManager;
import com.arflix.tv.data.model.CatalogPackManifest;
import com.arflix.tv.data.repository.PlexPinAuthSession;
import com.arflix.tv.ui.screens.player.SubtitleAiModel;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class z implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11138i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f11139l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f11140m;

    public /* synthetic */ z(Object obj, Object obj2, int i10) {
        this.f11138i = i10;
        this.f11140m = obj;
        this.f11139l = obj2;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f11138i) {
            case 0:
                return SettingsScreenKt.MobileSettingsMainPage$lambda$2$0$2$0$4$0((Context) this.f11140m, (State) this.f11139l);
            case 1:
                return SettingsScreenKt.SettingsScreen$lambda$241$17$1$0$46$0((SettingsViewModel) this.f11140m, (State) this.f11139l);
            case 2:
                return SettingsScreenKt.AiModelDialog$lambda$1$0$0$0$2$0((r7.l) this.f11140m, (SubtitleAiModel) this.f11139l);
            case 3:
                return SettingsScreenKt.TraktActivationModal$lambda$2$0$1$3$0((ClipboardManager) this.f11140m, (String) this.f11139l);
            case 4:
                return SettingsScreenKt.CatalogPackImportDialog$lambda$5$1$1$2$1$0$0((r7.l) this.f11140m, (CatalogPackManifest) this.f11139l);
            default:
                return SettingsScreenKt.SettingsScreen$lambda$241$106$1$0((Context) this.f11140m, (PlexPinAuthSession) this.f11139l);
        }
    }
}

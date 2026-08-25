package com.arflix.tv.ui.components;

import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.foundation.layout.BoxScope;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.ui.focus.FocusRequester;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import com.arflix.tv.ui.screens.settings.SettingsUiState;
import com.arflix.tv.ui.screens.settings.SettingsViewModel;
import com.arflix.tv.updater.UpdateStatus;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class j implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8096i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ x6.o f8097l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f8098m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f8099n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f8100o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f8101p;

    public /* synthetic */ j(MutableIntState mutableIntState, FocusRequester focusRequester, UpdateStatus updateStatus, List list, r7.a aVar) {
        this.f8100o = focusRequester;
        this.f8097l = aVar;
        this.f8098m = mutableIntState;
        this.f8099n = list;
        this.f8101p = updateStatus;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        switch (this.f8096i) {
            case 0:
                return AppUpdateModalKt.AppUpdateModal$lambda$7$0((FocusRequester) this.f8100o, (r7.a) this.f8097l, (MutableIntState) this.f8098m, (List) this.f8099n, (UpdateStatus) this.f8101p, (BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            case 1:
                return AudioTrackSelectorKt.AudioTrackSelector$lambda$4((r7.a) this.f8097l, (MutableIntState) this.f8098m, (List) this.f8099n, (r7.l) this.f8100o, (String) this.f8101p, (AnimatedVisibilityScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            case 2:
                return MediaContextMenuKt.MediaContextMenu$lambda$7((FocusRequester) this.f8100o, (List) this.f8099n, (r7.a) this.f8097l, (MutableIntState) this.f8098m, (String) this.f8101p, (AnimatedVisibilityScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            case 3:
                return SettingsScreenKt.UiModeWarningDialog$lambda$5$1((FocusRequester) this.f8100o, (r7.a) this.f8097l, (r7.a) this.f8099n, (MutableIntState) this.f8098m, (String) this.f8101p, (BoxScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            case 4:
                return SettingsScreenKt.MobileSettingsSubPage$lambda$6$3((SettingsUiState) this.f8100o, (SettingsViewModel) this.f8098m, (r7.a) this.f8097l, (r7.a) this.f8099n, (r7.a) this.f8101p, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            default:
                return SettingsScreenKt.StremioAddonsSettings$lambda$6$1((List) this.f8099n, (r7.l) this.f8100o, (r7.l) this.f8097l, (r7.l) this.f8098m, (r7.l) this.f8101p, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    }

    public /* synthetic */ j(MutableIntState mutableIntState, FocusRequester focusRequester, String str, r7.a aVar, r7.a aVar2) {
        this.f8100o = focusRequester;
        this.f8097l = aVar;
        this.f8099n = aVar2;
        this.f8098m = mutableIntState;
        this.f8101p = str;
    }

    public /* synthetic */ j(FocusRequester focusRequester, z6.c cVar, r7.a aVar, MutableIntState mutableIntState, String str) {
        this.f8100o = focusRequester;
        this.f8099n = cVar;
        this.f8097l = aVar;
        this.f8098m = mutableIntState;
        this.f8101p = str;
    }

    public /* synthetic */ j(SettingsUiState settingsUiState, SettingsViewModel settingsViewModel, r7.a aVar, r7.a aVar2, r7.a aVar3) {
        this.f8100o = settingsUiState;
        this.f8098m = settingsViewModel;
        this.f8097l = aVar;
        this.f8099n = aVar2;
        this.f8101p = aVar3;
    }

    public /* synthetic */ j(List list, r7.l lVar, r7.l lVar2, r7.l lVar3, r7.l lVar4) {
        this.f8099n = list;
        this.f8100o = lVar;
        this.f8097l = lVar2;
        this.f8098m = lVar3;
        this.f8101p = lVar4;
    }

    public /* synthetic */ j(r7.a aVar, MutableIntState mutableIntState, List list, r7.l lVar, String str) {
        this.f8097l = aVar;
        this.f8098m = mutableIntState;
        this.f8099n = list;
        this.f8100o = lVar;
        this.f8101p = str;
    }
}

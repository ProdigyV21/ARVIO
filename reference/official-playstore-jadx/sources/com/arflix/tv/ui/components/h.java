package com.arflix.tv.ui.components;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.ui.focus.FocusRequester;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import com.arflix.tv.updater.UpdateStatus;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class h implements r7.p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8018i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ r7.a f8019l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ FocusRequester f8020m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ MutableIntState f8021n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f8022o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f8023p;

    public /* synthetic */ h(MutableIntState mutableIntState, FocusRequester focusRequester, UpdateStatus updateStatus, List list, r7.a aVar) {
        this.f8019l = aVar;
        this.f8020m = focusRequester;
        this.f8021n = mutableIntState;
        this.f8022o = list;
        this.f8023p = updateStatus;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f8018i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return AppUpdateModalKt.AppUpdateModal$lambda$7(this.f8019l, this.f8020m, this.f8021n, (List) this.f8022o, (UpdateStatus) this.f8023p, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return SettingsScreenKt.UiModeWarningDialog$lambda$5(this.f8019l, this.f8020m, (r7.a) this.f8022o, this.f8021n, (String) this.f8023p, (Composer) obj, iIntValue2);
        }
    }

    public /* synthetic */ h(MutableIntState mutableIntState, FocusRequester focusRequester, String str, r7.a aVar, r7.a aVar2) {
        this.f8019l = aVar;
        this.f8020m = focusRequester;
        this.f8022o = aVar2;
        this.f8021n = mutableIntState;
        this.f8023p = str;
    }
}

package com.arflix.tv.ui.screens.plugin;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import r7.l;
import r7.p;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class c implements p {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9855i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableState f9856l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ l f9857m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ r7.a f9858n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f9859o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f9860p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f9861q;

    public /* synthetic */ c(String str, MutableState mutableState, String str2, String str3, l lVar, r7.a aVar) {
        this.f9859o = str;
        this.f9856l = mutableState;
        this.f9860p = str2;
        this.f9861q = str3;
        this.f9857m = lVar;
        this.f9858n = aVar;
    }

    @Override // r7.p
    public final Object invoke(Object obj, Object obj2) {
        switch (this.f9855i) {
            case 0:
                int iIntValue = ((Integer) obj2).intValue();
                return PluginScreenKt.AddRepoDialog$lambda$7(this.f9858n, (FocusRequester) this.f9859o, this.f9856l, (FocusRequester) this.f9860p, (FocusRequester) this.f9861q, this.f9857m, (Composer) obj, iIntValue);
            default:
                int iIntValue2 = ((Integer) obj2).intValue();
                return SettingsScreenKt.CatalogDiscoveryTextInputDialog$lambda$3((String) this.f9859o, this.f9856l, (String) this.f9860p, (String) this.f9861q, this.f9857m, this.f9858n, (Composer) obj, iIntValue2);
        }
    }

    public /* synthetic */ c(r7.a aVar, FocusRequester focusRequester, MutableState mutableState, FocusRequester focusRequester2, FocusRequester focusRequester3, l lVar) {
        this.f9858n = aVar;
        this.f9859o = focusRequester;
        this.f9856l = mutableState;
        this.f9860p = focusRequester2;
        this.f9861q = focusRequester3;
        this.f9857m = lVar;
    }
}

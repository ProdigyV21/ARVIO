package com.arflix.tv.ui.screens.settings;

import android.content.Context;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.arflix.tv.data.model.QualityFilterConfig;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class c1 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10353i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableState f10354l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f10355m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f10356n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f10357o;

    public /* synthetic */ c1(MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4) {
        this.f10354l = mutableState;
        this.f10355m = mutableState2;
        this.f10356n = mutableState3;
        this.f10357o = mutableState4;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f10353i) {
            case 0:
                return SettingsScreenKt.CatalogDiscoveryModal$lambda$8$0$0((CatalogDiscoveryInputTarget) this.f10355m, (r7.l) this.f10356n, (r7.l) this.f10357o, this.f10354l, (String) obj);
            case 1:
                return SettingsScreenKt.SettingsScreen$lambda$241$57$0(this.f10354l, (MutableState) this.f10355m, (MutableState) this.f10356n, (MutableState) this.f10357o, (QualityFilterConfig) obj);
            case 2:
                return Integer.valueOf(SettingsScreenKt.SettingsScreen$lambda$199$0((List) this.f10355m, this.f10354l, (State) this.f10356n, (MutableIntState) this.f10357o, (String) obj));
            default:
                return SettingsScreenKt.DiscordWebViewModal$lambda$18$1$0$2$0$0((String) this.f10355m, this.f10354l, (r7.a) this.f10357o, (r7.l) this.f10356n, (Context) obj);
        }
    }

    public /* synthetic */ c1(CatalogDiscoveryInputTarget catalogDiscoveryInputTarget, r7.l lVar, r7.l lVar2, MutableState mutableState) {
        this.f10355m = catalogDiscoveryInputTarget;
        this.f10356n = lVar;
        this.f10357o = lVar2;
        this.f10354l = mutableState;
    }

    public /* synthetic */ c1(String str, MutableState mutableState, r7.a aVar, r7.l lVar) {
        this.f10355m = str;
        this.f10354l = mutableState;
        this.f10357o = aVar;
        this.f10356n = lVar;
    }

    public /* synthetic */ c1(List list, MutableState mutableState, State state, MutableIntState mutableIntState) {
        this.f10355m = list;
        this.f10354l = mutableState;
        this.f10356n = state;
        this.f10357o = mutableIntState;
    }
}

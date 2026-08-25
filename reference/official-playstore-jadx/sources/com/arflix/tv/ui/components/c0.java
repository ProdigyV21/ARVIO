package com.arflix.tv.ui.components;

import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;
import com.arflix.tv.ui.screens.settings.SettingsScreenKt;
import com.arflix.tv.ui.screens.settings.SettingsUiState;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class c0 implements r7.q {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f7853i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f7854l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ x6.o f7855m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f7856n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f7857o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f7858p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f7859q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f7860r;

    public /* synthetic */ c0(Object obj, r7.a aVar, Object obj2, x6.o oVar, MutableState mutableState, Object obj3, Object obj4, int i10) {
        this.f7853i = i10;
        this.f7856n = obj;
        this.f7857o = aVar;
        this.f7854l = obj2;
        this.f7855m = oVar;
        this.f7858p = mutableState;
        this.f7859q = obj3;
        this.f7860r = obj4;
    }

    @Override // r7.q
    public final Object invoke(Object obj, Object obj2, Object obj3) {
        switch (this.f7853i) {
            case 0:
                return ContextMenuKt.ContextMenu$lambda$8((FocusRequester) this.f7856n, (r7.a) this.f7857o, (List) this.f7854l, (r7.l) this.f7855m, (MutableIntState) this.f7858p, (String) this.f7859q, (String) this.f7860r, (AnimatedVisibilityScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            case 1:
                return SettingsScreenKt.CatalogsSettings$lambda$6$2((List) this.f7854l, (MutableState) this.f7856n, (r7.l) this.f7855m, (MutableState) this.f7857o, (r7.l) this.f7858p, (r7.l) this.f7859q, (r7.l) this.f7860r, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            default:
                return SettingsScreenKt.TrackingIntegrationsPage$lambda$20$1((SettingsUiState) this.f7856n, (r7.a) this.f7857o, (r7.a) this.f7854l, (r7.a) this.f7855m, (MutableState) this.f7858p, (MutableState) this.f7859q, (MutableState) this.f7860r, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    }

    public /* synthetic */ c0(List list, MutableState mutableState, r7.l lVar, MutableState mutableState2, r7.l lVar2, r7.l lVar3, r7.l lVar4) {
        this.f7853i = 1;
        this.f7854l = list;
        this.f7856n = mutableState;
        this.f7855m = lVar;
        this.f7857o = mutableState2;
        this.f7858p = lVar2;
        this.f7859q = lVar3;
        this.f7860r = lVar4;
    }
}

package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.focus.FocusRequester;
import com.arflix.tv.ui.screens.collections.CollectionDetailsScreenKt;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class a2 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11287i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableState f11288l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MutableState f11289m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f11290n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f11291o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f11292p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f11293q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ MutableState f11294r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ MutableState f11295s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ State f11296t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ State f11297u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ Object f11298v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ State f11299w;
    public final /* synthetic */ FocusRequester x;

    public /* synthetic */ a2(MutableState mutableState, MutableState mutableState2, MutableLongState mutableLongState, Map map, Map map2, List list, MutableState mutableState3, MutableState mutableState4, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableState mutableState5, MutableIntState mutableIntState3, FocusRequester focusRequester, int i10) {
        this.f11287i = i10;
        this.f11288l = mutableState;
        this.f11289m = mutableState2;
        this.f11290n = mutableLongState;
        this.f11291o = map;
        this.f11292p = map2;
        this.f11293q = list;
        this.f11294r = mutableState3;
        this.f11295s = mutableState4;
        this.f11296t = mutableIntState;
        this.f11297u = mutableIntState2;
        this.f11298v = mutableState5;
        this.f11299w = mutableIntState3;
        this.x = focusRequester;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f11287i) {
            case 0:
                return LiveTvScreenKt.LiveTvScreen$lambda$251$1$15$0(this.f11288l, this.f11289m, (MutableLongState) this.f11290n, (Map) this.f11291o, (Map) this.f11292p, (List) this.f11293q, this.f11294r, this.f11295s, (MutableIntState) this.f11296t, (MutableIntState) this.f11297u, (MutableState) this.f11298v, (MutableIntState) this.f11299w, this.x, (EnrichedChannel) obj);
            case 1:
                return LiveTvScreenKt.LiveTvScreen$lambda$251$2$10$15$0(this.f11288l, this.f11289m, (MutableLongState) this.f11290n, (Map) this.f11291o, (Map) this.f11292p, (List) this.f11293q, this.f11294r, this.f11295s, (MutableIntState) this.f11296t, (MutableIntState) this.f11297u, (MutableState) this.f11298v, (MutableIntState) this.f11299w, this.x, (EnrichedChannel) obj);
            default:
                return CollectionDetailsScreenKt.CollectionDetailsScreen$lambda$28$0((androidx.lifecycle.y) this.f11298v, (ka.k0) this.f11290n, (androidx.tv.foundation.lazy.grid.b1) this.f11291o, (androidx.tv.foundation.lazy.grid.b1) this.f11292p, this.f11288l, this.x, (FocusRequester) this.f11293q, this.f11296t, this.f11297u, this.f11299w, this.f11289m, this.f11294r, this.f11295s, (DisposableEffectScope) obj);
        }
    }

    public /* synthetic */ a2(androidx.lifecycle.y yVar, ka.k0 k0Var, androidx.tv.foundation.lazy.grid.b1 b1Var, androidx.tv.foundation.lazy.grid.b1 b1Var2, MutableState mutableState, FocusRequester focusRequester, FocusRequester focusRequester2, State state, State state2, State state3, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4) {
        this.f11287i = 2;
        this.f11298v = yVar;
        this.f11290n = k0Var;
        this.f11291o = b1Var;
        this.f11292p = b1Var2;
        this.f11288l = mutableState;
        this.x = focusRequester;
        this.f11293q = focusRequester2;
        this.f11296t = state;
        this.f11297u = state2;
        this.f11299w = state3;
        this.f11289m = mutableState2;
        this.f11294r = mutableState3;
        this.f11295s = mutableState4;
    }
}

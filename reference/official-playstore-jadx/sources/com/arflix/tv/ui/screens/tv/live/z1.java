package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;
import com.arflix.tv.data.model.IptvProgram;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class z1 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f11948i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f11949l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f11950m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ List f11951n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ MutableLongState f11952o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ MutableState f11953p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ MutableState f11954q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ MutableState f11955r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ MutableState f11956s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ MutableState f11957t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ MutableState f11958u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ MutableState f11959v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ Object f11960w;

    public /* synthetic */ z1(EnrichedChannel enrichedChannel, MutableState mutableState, MutableState mutableState2, List list, MutableState mutableState3, MutableLongState mutableLongState, MutableState mutableState4, MutableState mutableState5, MutableState mutableState6, MutableState mutableState7, MutableState mutableState8, MutableState mutableState9) {
        this.f11948i = 2;
        this.f11949l = enrichedChannel;
        this.f11953p = mutableState;
        this.f11954q = mutableState2;
        this.f11951n = list;
        this.f11955r = mutableState3;
        this.f11952o = mutableLongState;
        this.f11958u = mutableState4;
        this.f11950m = mutableState5;
        this.f11956s = mutableState6;
        this.f11957t = mutableState7;
        this.f11959v = mutableState8;
        this.f11960w = mutableState9;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f11948i) {
            case 0:
                return LiveTvScreenKt.LiveTvScreen$lambda$251$1$14$0((Map) this.f11949l, (Map) this.f11950m, this.f11951n, this.f11952o, this.f11953p, this.f11954q, this.f11955r, (MutableIntState) this.f11956s, (MutableIntState) this.f11957t, this.f11958u, (MutableIntState) this.f11959v, (FocusRequester) this.f11960w, (EnrichedChannel) obj);
            case 1:
                return LiveTvScreenKt.LiveTvScreen$lambda$251$2$10$14$0((Map) this.f11949l, (Map) this.f11950m, this.f11951n, this.f11952o, this.f11953p, this.f11954q, this.f11955r, (MutableIntState) this.f11956s, (MutableIntState) this.f11957t, this.f11958u, (MutableIntState) this.f11959v, (FocusRequester) this.f11960w, (EnrichedChannel) obj);
            default:
                return LiveTvScreenKt.LiveTvScreen$lambda$251$10$16$0((EnrichedChannel) this.f11949l, this.f11953p, this.f11954q, this.f11951n, this.f11955r, this.f11952o, this.f11958u, (MutableState) this.f11950m, this.f11956s, this.f11957t, this.f11959v, (MutableState) this.f11960w, (IptvProgram) obj);
        }
    }

    public /* synthetic */ z1(Map map, Map map2, List list, MutableLongState mutableLongState, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableState mutableState4, MutableIntState mutableIntState3, FocusRequester focusRequester, int i10) {
        this.f11948i = i10;
        this.f11949l = map;
        this.f11950m = map2;
        this.f11951n = list;
        this.f11952o = mutableLongState;
        this.f11953p = mutableState;
        this.f11954q = mutableState2;
        this.f11955r = mutableState3;
        this.f11956s = mutableIntState;
        this.f11957t = mutableIntState2;
        this.f11958u = mutableState4;
        this.f11959v = mutableIntState3;
        this.f11960w = focusRequester;
    }
}

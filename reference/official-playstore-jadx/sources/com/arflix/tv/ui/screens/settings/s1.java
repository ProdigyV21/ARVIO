package com.arflix.tv.ui.screens.settings;

import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import com.arflix.tv.data.model.IptvChannel;
import com.arflix.tv.ui.screens.tv.TvScreenKt;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class s1 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f10913i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableState f10914l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MutableIntState f10915m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f10916n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f10917o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f10918p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f10919q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ MutableState f10920r;

    public /* synthetic */ s1(MutableState mutableState, MutableState mutableState2, State state, MutableIntState mutableIntState, IptvChannel iptvChannel, MutableState mutableState3, MutableState mutableState4) {
        this.f10914l = mutableState;
        this.f10916n = mutableState2;
        this.f10917o = state;
        this.f10915m = mutableIntState;
        this.f10918p = iptvChannel;
        this.f10919q = mutableState3;
        this.f10920r = mutableState4;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f10913i) {
            case 0:
                return SettingsScreenKt.QualityFiltersModal$lambda$17$0$1$0$0((List) this.f10916n, (r7.l) this.f10917o, (r7.l) this.f10918p, (r7.l) this.f10919q, this.f10914l, this.f10915m, (MutableIntState) this.f10920r, (LazyListScope) obj);
            case 1:
                return TvScreenKt.TvScreen$lambda$115$0(this.f10914l, (MutableState) this.f10916n, (State) this.f10917o, this.f10915m, (IptvChannel) this.f10918p, (MutableState) this.f10919q, this.f10920r, (DisposableEffectScope) obj);
            default:
                return TvScreenKt.TvScreen$lambda$118$2$0$0$0((List) this.f10916n, this.f10915m, this.f10914l, (State) this.f10917o, (MutableState) this.f10918p, (MutableState) this.f10919q, (MutableIntState) this.f10920r, ((Integer) obj).intValue());
        }
    }

    public /* synthetic */ s1(List list, MutableIntState mutableIntState, MutableState mutableState, State state, MutableState mutableState2, MutableState mutableState3, MutableIntState mutableIntState2) {
        this.f10916n = list;
        this.f10915m = mutableIntState;
        this.f10914l = mutableState;
        this.f10917o = state;
        this.f10918p = mutableState2;
        this.f10919q = mutableState3;
        this.f10920r = mutableIntState2;
    }

    public /* synthetic */ s1(List list, r7.l lVar, r7.l lVar2, r7.l lVar3, MutableState mutableState, MutableIntState mutableIntState, MutableIntState mutableIntState2) {
        this.f10916n = list;
        this.f10917o = lVar;
        this.f10918p = lVar2;
        this.f10919q = lVar3;
        this.f10914l = mutableState;
        this.f10915m = mutableIntState;
        this.f10920r = mutableIntState2;
    }
}

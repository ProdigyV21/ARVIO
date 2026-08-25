package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.focus.FocusRequester;
import androidx.media3.exoplayer.ExoPlayer;
import com.arflix.tv.data.model.StreamSource;
import com.arflix.tv.ui.screens.tv.live.EnrichedChannel;
import com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class n0 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9650i = 1;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableState f9651l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ MutableState f9652m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ MutableState f9653n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ MutableState f9654o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ MutableState f9655p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ MutableState f9656q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ MutableIntState f9657r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ MutableIntState f9658s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ MutableState f9659t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ FocusRequester f9660u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ Object f9661v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ Object f9662w;
    public final /* synthetic */ Object x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final /* synthetic */ Object f9663y;
    public final /* synthetic */ MutableState z;

    public /* synthetic */ n0(MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, MutableState mutableState6, MutableLongState mutableLongState, Map map, Map map2, List list, MutableIntState mutableIntState, MutableIntState mutableIntState2, MutableState mutableState7, MutableIntState mutableIntState3, FocusRequester focusRequester) {
        this.f9651l = mutableState;
        this.f9652m = mutableState2;
        this.f9653n = mutableState3;
        this.f9654o = mutableState4;
        this.f9655p = mutableState5;
        this.f9656q = mutableState6;
        this.f9661v = mutableLongState;
        this.f9662w = map;
        this.x = map2;
        this.f9663y = list;
        this.f9657r = mutableIntState;
        this.f9658s = mutableIntState2;
        this.f9659t = mutableState7;
        this.z = mutableIntState3;
        this.f9660u = focusRequester;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f9650i) {
            case 0:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$13$0((PlayerViewModel) this.f9661v, (ExoPlayer) this.f9662w, (ka.k0) this.x, this.f9651l, this.f9652m, this.f9653n, this.f9654o, this.f9657r, this.f9655p, this.f9656q, this.f9659t, this.f9658s, (MutableState) this.f9663y, this.z, this.f9660u, (StreamSource) obj);
            default:
                return LiveTvScreenKt.LiveTvScreen$lambda$251$14$2$0(this.f9651l, this.f9652m, this.f9653n, this.f9654o, this.f9655p, this.f9656q, (MutableLongState) this.f9661v, (Map) this.f9662w, (Map) this.x, (List) this.f9663y, this.f9657r, this.f9658s, this.f9659t, (MutableIntState) this.z, this.f9660u, (EnrichedChannel) obj);
        }
    }

    public /* synthetic */ n0(PlayerViewModel playerViewModel, ExoPlayer exoPlayer, ka.k0 k0Var, MutableState mutableState, MutableState mutableState2, MutableState mutableState3, MutableState mutableState4, MutableIntState mutableIntState, MutableState mutableState5, MutableState mutableState6, MutableState mutableState7, MutableIntState mutableIntState2, MutableState mutableState8, MutableState mutableState9, FocusRequester focusRequester) {
        this.f9661v = playerViewModel;
        this.f9662w = exoPlayer;
        this.x = k0Var;
        this.f9651l = mutableState;
        this.f9652m = mutableState2;
        this.f9653n = mutableState3;
        this.f9654o = mutableState4;
        this.f9657r = mutableIntState;
        this.f9655p = mutableState5;
        this.f9656q = mutableState6;
        this.f9659t = mutableState7;
        this.f9658s = mutableIntState2;
        this.f9663y = mutableState8;
        this.z = mutableState9;
        this.f9660u = focusRequester;
    }
}

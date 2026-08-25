package com.arflix.tv.ui.screens.details;

import android.content.Context;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.media3.exoplayer.ExoPlayer;
import com.arflix.tv.ui.screens.player.PlayerScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class n implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f8908i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableIntState f8909l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f8910m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ MutableState f8911n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f8912o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f8913p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f8914q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f8915r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Object f8916s;

    public /* synthetic */ n(State state, boolean z, DetailsViewModel detailsViewModel, MutableIntState mutableIntState, MutableState mutableState, State state2, MutableIntState mutableIntState2, MutableState mutableState2) {
        this.f8912o = state;
        this.f8910m = z;
        this.f8914q = detailsViewModel;
        this.f8909l = mutableIntState;
        this.f8911n = mutableState;
        this.f8913p = state2;
        this.f8915r = mutableIntState2;
        this.f8916s = mutableState2;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f8908i) {
            case 0:
                return DetailsScreenKt.DetailsScreen$lambda$91$0((State) this.f8912o, this.f8910m, (DetailsViewModel) this.f8914q, this.f8909l, this.f8911n, (State) this.f8913p, (MutableIntState) this.f8915r, (MutableState) this.f8916s, ((Integer) obj).intValue());
            default:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$0$0((ExoPlayer) this.f8912o, this.f8909l, (String) this.f8913p, (String) this.f8914q, (String) this.f8915r, (String) this.f8916s, this.f8910m, this.f8911n, (Context) obj);
        }
    }

    public /* synthetic */ n(ExoPlayer exoPlayer, MutableIntState mutableIntState, String str, String str2, String str3, String str4, boolean z, MutableState mutableState) {
        this.f8912o = exoPlayer;
        this.f8909l = mutableIntState;
        this.f8913p = str;
        this.f8914q = str2;
        this.f8915r = str3;
        this.f8916s = str4;
        this.f8910m = z;
        this.f8911n = mutableState;
    }
}

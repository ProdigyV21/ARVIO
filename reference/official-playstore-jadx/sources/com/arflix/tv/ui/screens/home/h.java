package com.arflix.tv.ui.screens.home;

import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.focus.FocusState;
import androidx.media3.exoplayer.ExoPlayer;
import com.arflix.tv.data.model.MediaItem;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class h implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9144i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f9145l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f9146m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f9147n;

    public /* synthetic */ h(int i10, MutableState mutableState, Object obj, Object obj2) {
        this.f9144i = i10;
        this.f9146m = obj;
        this.f9147n = obj2;
        this.f9145l = mutableState;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f9144i) {
            case 0:
                return HomeScreenKt.HomeScreen$lambda$15$0((androidx.lifecycle.y) this.f9146m, (HomeViewModel) this.f9147n, (MutableLongState) this.f9145l, (DisposableEffectScope) obj);
            case 1:
                return HomeScreenKt.HomeInputLayer_0DYc4_I$lambda$33$0((MutableState) this.f9146m, (MutableState) this.f9147n, (MutableLongState) this.f9145l, (FocusState) obj);
            case 2:
                return HomeScreenKt.HomeScreen$lambda$21$0((HomeViewModel) this.f9147n, (r7.a) this.f9146m, (r7.s) this.f9145l, (MediaItem) obj);
            default:
                return HomeScreenKt.HomeScreen$lambda$75$0((ExoPlayer) this.f9146m, (State) this.f9147n, (MutableState) this.f9145l, (DisposableEffectScope) obj);
        }
    }

    public /* synthetic */ h(HomeViewModel homeViewModel, r7.a aVar, r7.s sVar) {
        this.f9144i = 2;
        this.f9147n = homeViewModel;
        this.f9146m = aVar;
        this.f9145l = sVar;
    }
}

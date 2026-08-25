package com.arflix.tv.ui.screens.player;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.media3.exoplayer.ExoPlayer;
import com.arflix.tv.data.repository.SkipInterval;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class j0 implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9575i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ Object f9576l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f9577m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f9578n;

    public /* synthetic */ j0(Object obj, Object obj2, Object obj3, int i10) {
        this.f9575i = i10;
        this.f9576l = obj;
        this.f9577m = obj2;
        this.f9578n = obj3;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f9575i) {
            case 0:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$4$0((SkipInterval) this.f9576l, (ExoPlayer) this.f9577m, (PlayerViewModel) this.f9578n);
            default:
                return PlayerScreenKt.PlayerScreen$lambda$362$2$9$0$1$0$36$0((r7.r) this.f9576l, (MutableState) this.f9577m, (State) this.f9578n);
        }
    }
}

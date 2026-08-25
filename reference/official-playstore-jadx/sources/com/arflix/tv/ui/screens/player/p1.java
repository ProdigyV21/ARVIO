package com.arflix.tv.ui.screens.player;

import android.content.Context;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.State;
import androidx.compose.ui.geometry.Offset;
import androidx.media3.exoplayer.ExoPlayer;
import com.arflix.tv.util.DeviceType;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class p1 implements r7.l {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9697i;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableState f9698l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f9699m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f9700n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f9701o;

    public /* synthetic */ p1(int i10, MutableState mutableState, Object obj, Object obj2, Object obj3) {
        this.f9697i = i10;
        this.f9699m = obj;
        this.f9700n = obj2;
        this.f9701o = obj3;
        this.f9698l = mutableState;
    }

    @Override // r7.l
    public final Object invoke(Object obj) {
        switch (this.f9697i) {
            case 0:
                return PlayerScreenKt$PlayerScreen$49$1$1.invokeSuspend$lambda$1((State) this.f9699m, this.f9698l, (MutableState) this.f9700n, (MutableState) this.f9701o, (Offset) obj);
            case 1:
                return PlayerScreenKt$PlayerScreen$49$3$9$1$2$2$3$1.invokeSuspend$lambda$0((MutableLongState) this.f9699m, (MutableIntState) this.f9700n, (MutableLongState) this.f9701o, this.f9698l, (Offset) obj);
            default:
                return PlayerScreenKt.PlayerScreen$lambda$318$0((DeviceType) this.f9699m, (Context) this.f9700n, (ExoPlayer) this.f9701o, this.f9698l, (DisposableEffectScope) obj);
        }
    }

    public /* synthetic */ p1(State state, MutableState mutableState, MutableState mutableState2, MutableState mutableState3) {
        this.f9697i = 0;
        this.f9699m = state;
        this.f9698l = mutableState;
        this.f9700n = mutableState2;
        this.f9701o = mutableState3;
    }
}

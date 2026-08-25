package com.arflix.tv.ui.screens.player;

import android.app.Activity;
import android.content.Context;
import androidx.compose.runtime.MutableLongState;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.graphics.vector.VectorPainter;
import androidx.compose.ui.unit.Density;
import androidx.media3.exoplayer.ExoPlayer;
import com.arflix.tv.ui.screens.tv.live.LiveTvScreenKt;

/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class g1 implements r7.a {

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final /* synthetic */ int f9520i = 0;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public final /* synthetic */ MutableState f9521l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f9522m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f9523n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public final /* synthetic */ Object f9524o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f9525p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f9526q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f9527r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Object f9528s;

    public /* synthetic */ g1(Context context, VectorPainter vectorPainter, VectorPainter vectorPainter2, VectorPainter vectorPainter3, VectorPainter vectorPainter4, MutableState mutableState, Density density, Activity activity) {
        this.f9522m = context;
        this.f9523n = vectorPainter;
        this.f9524o = vectorPainter2;
        this.f9525p = vectorPainter3;
        this.f9526q = vectorPainter4;
        this.f9521l = mutableState;
        this.f9527r = density;
        this.f9528s = activity;
    }

    @Override // r7.a
    public final Object invoke() {
        switch (this.f9520i) {
            case 0:
                return PlayerScreenKt.PlayerScreen$lambda$282$0((Context) this.f9522m, (VectorPainter) this.f9523n, (VectorPainter) this.f9524o, (VectorPainter) this.f9525p, (VectorPainter) this.f9526q, this.f9521l, (Density) this.f9527r, (Activity) this.f9528s);
            default:
                return LiveTvScreenKt.LiveTvScreen$lambda$251$10$11$0((ExoPlayer) this.f9522m, this.f9521l, (MutableState) this.f9523n, (MutableLongState) this.f9524o, (MutableState) this.f9525p, (MutableState) this.f9526q, (MutableState) this.f9527r, (MutableState) this.f9528s);
        }
    }

    public /* synthetic */ g1(ExoPlayer exoPlayer, MutableState mutableState, MutableState mutableState2, MutableLongState mutableLongState, MutableState mutableState3, MutableState mutableState4, MutableState mutableState5, MutableState mutableState6) {
        this.f9522m = exoPlayer;
        this.f9521l = mutableState;
        this.f9523n = mutableState2;
        this.f9524o = mutableLongState;
        this.f9525p = mutableState3;
        this.f9526q = mutableState4;
        this.f9527r = mutableState5;
        this.f9528s = mutableState6;
    }
}

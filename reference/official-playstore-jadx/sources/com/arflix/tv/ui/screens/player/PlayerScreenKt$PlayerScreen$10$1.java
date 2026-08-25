package com.arflix.tv.ui.screens.player;

import android.app.Activity;
import android.app.PictureInPictureParams;
import android.content.Context;
import android.os.Build;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.graphics.vector.VectorPainter;
import androidx.compose.ui.unit.Density;
import com.arflix.tv.util.DeviceType;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.player.PlayerScreenKt$PlayerScreen$10$1", f = "PlayerScreen.kt", l = {}, m = "invokeSuspend", v = 2)
public final class PlayerScreenKt$PlayerScreen$10$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ Context $context;
    final /* synthetic */ DeviceType $deviceType;
    final /* synthetic */ MutableState<Boolean> $isInPipMode$delegate;
    final /* synthetic */ MutableState<Boolean> $isPlaying$delegate;
    final /* synthetic */ Density $pipDensity;
    final /* synthetic */ VectorPainter $pipForwardPainter;
    final /* synthetic */ VectorPainter $pipPausePainter;
    final /* synthetic */ VectorPainter $pipPlayPainter;
    final /* synthetic */ VectorPainter $pipRewindPainter;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayerScreenKt$PlayerScreen$10$1(DeviceType deviceType, MutableState<Boolean> mutableState, Context context, VectorPainter vectorPainter, VectorPainter vectorPainter2, VectorPainter vectorPainter3, VectorPainter vectorPainter4, MutableState<Boolean> mutableState2, Density density, Activity activity, d7.d<? super PlayerScreenKt$PlayerScreen$10$1> dVar) {
        super(2, dVar);
        this.$deviceType = deviceType;
        this.$isInPipMode$delegate = mutableState;
        this.$context = context;
        this.$pipRewindPainter = vectorPainter;
        this.$pipPausePainter = vectorPainter2;
        this.$pipPlayPainter = vectorPainter3;
        this.$pipForwardPainter = vectorPainter4;
        this.$isPlaying$delegate = mutableState2;
        this.$pipDensity = density;
        this.$activity = activity;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new PlayerScreenKt$PlayerScreen$10$1(this.$deviceType, this.$isInPipMode$delegate, this.$context, this.$pipRewindPainter, this.$pipPausePainter, this.$pipPlayPainter, this.$pipForwardPainter, this.$isPlaying$delegate, this.$pipDensity, this.$activity, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        PictureInPictureParams pictureInPictureParamsPlayerScreen$buildPipParams;
        Activity activity;
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        k2.c.G(obj);
        if (PlayerScreenKt.PlayerScreen$lambda$277(this.$isInPipMode$delegate) && this.$deviceType.isTouchDevice() && Build.VERSION.SDK_INT >= 26 && (pictureInPictureParamsPlayerScreen$buildPipParams = PlayerScreenKt.PlayerScreen$buildPipParams(this.$context, this.$pipRewindPainter, this.$pipPausePainter, this.$pipPlayPainter, this.$pipForwardPainter, this.$isPlaying$delegate, this.$pipDensity)) != null && (activity = this.$activity) != null) {
            activity.setPictureInPictureParams(pictureInPictureParamsPlayerScreen$buildPipParams);
        }
        return x6.t0.f22605a;
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((PlayerScreenKt$PlayerScreen$10$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}

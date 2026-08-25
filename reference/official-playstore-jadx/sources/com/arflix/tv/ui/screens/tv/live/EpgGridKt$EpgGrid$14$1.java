package com.arflix.tv.ui.screens.tv.live;

import androidx.compose.foundation.ScrollState;
import androidx.compose.ui.unit.Density;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.tv.live.EpgGridKt$EpgGrid$14$1", f = "EpgGrid.kt", l = {317, 320}, m = "invokeSuspend", v = 2)
public final class EpgGridKt$EpgGrid$14$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ long $clockTickMillis;
    final /* synthetic */ Density $density;
    final /* synthetic */ ScrollState $hScroll;
    final /* synthetic */ float $pxPerMin;
    final /* synthetic */ long $windowStartMillis;
    float F$0;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    int I$4;
    int I$5;
    int I$6;
    long J$0;
    long J$1;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EpgGridKt$EpgGrid$14$1(Density density, ScrollState scrollState, long j10, long j11, float f10, d7.d<? super EpgGridKt$EpgGrid$14$1> dVar) {
        super(2, dVar);
        this.$density = density;
        this.$hScroll = scrollState;
        this.$clockTickMillis = j10;
        this.$windowStartMillis = j11;
        this.$pxPerMin = f10;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new EpgGridKt$EpgGrid$14$1(this.$density, this.$hScroll, this.$clockTickMillis, this.$windowStartMillis, this.$pxPerMin, dVar);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x00d7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0111 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x00fd -> B:28:0x00fe). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x0106 -> B:29:0x0102). Please report as a decompilation issue!!! */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r23) {
        /*
            Method dump skipped, instruction units count: 274
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.screens.tv.live.EpgGridKt$EpgGrid$14$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((EpgGridKt$EpgGrid$14$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}

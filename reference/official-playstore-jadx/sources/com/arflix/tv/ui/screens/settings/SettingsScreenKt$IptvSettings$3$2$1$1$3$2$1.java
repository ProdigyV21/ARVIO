package com.arflix.tv.ui.screens.settings;

import androidx.compose.foundation.gestures.DragGestureDetectorKt;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/input/pointer/PointerInputScope;", "Lx6/t0;", "<anonymous>", "(Landroidx/compose/ui/input/pointer/PointerInputScope;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.settings.SettingsScreenKt$IptvSettings$3$2$1$1$3$2$1", f = "SettingsScreen.kt", l = {7156}, m = "invokeSuspend", v = 2)
public final class SettingsScreenKt$IptvSettings$3$2$1$1$3$2$1 extends f7.j implements r7.p<PointerInputScope, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ int $index;
    final /* synthetic */ r7.l<Integer, x6.t0> $onMovePlaylistDown;
    final /* synthetic */ r7.l<Integer, x6.t0> $onMovePlaylistUp;
    float F$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public SettingsScreenKt$IptvSettings$3$2$1$1$3$2$1(r7.l<? super Integer, x6.t0> lVar, int i10, r7.l<? super Integer, x6.t0> lVar2, d7.d<? super SettingsScreenKt$IptvSettings$3$2$1$1$3$2$1> dVar) {
        super(2, dVar);
        this.$onMovePlaylistDown = lVar;
        this.$index = i10;
        this.$onMovePlaylistUp = lVar2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 invokeSuspend$lambda$0(kotlin.jvm.internal.h0 h0Var) {
        h0Var.f19742i = 0.0f;
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 invokeSuspend$lambda$1(kotlin.jvm.internal.h0 h0Var) {
        h0Var.f19742i = 0.0f;
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 invokeSuspend$lambda$2(kotlin.jvm.internal.h0 h0Var, float f10, r7.l lVar, int i10, r7.l lVar2, PointerInputChange pointerInputChange, float f11) {
        float f12;
        pointerInputChange.consume();
        float f13 = h0Var.f19742i + f11;
        h0Var.f19742i = f13;
        if (f13 <= f10) {
            if (f13 < (-f10)) {
                lVar2.invoke(Integer.valueOf(i10));
                f12 = h0Var.f19742i + f10;
            }
            return x6.t0.f22605a;
        }
        lVar.invoke(Integer.valueOf(i10));
        f12 = h0Var.f19742i - f10;
        h0Var.f19742i = f12;
        return x6.t0.f22605a;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        SettingsScreenKt$IptvSettings$3$2$1$1$3$2$1 settingsScreenKt$IptvSettings$3$2$1$1$3$2$1 = new SettingsScreenKt$IptvSettings$3$2$1$1$3$2$1(this.$onMovePlaylistDown, this.$index, this.$onMovePlaylistUp, dVar);
        settingsScreenKt$IptvSettings$3$2$1$1$3$2$1.L$0 = obj;
        return settingsScreenKt$IptvSettings$3$2$1$1$3$2$1;
    }

    @Override // r7.p
    public final Object invoke(PointerInputScope pointerInputScope, d7.d<? super x6.t0> dVar) {
        return ((SettingsScreenKt$IptvSettings$3$2$1$1$3$2$1) create(pointerInputScope, dVar)).invokeSuspend(x6.t0.f22605a);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
        int i10 = this.label;
        if (i10 == 0) {
            k2.c.G(obj);
            final kotlin.jvm.internal.h0 h0Var = new kotlin.jvm.internal.h0();
            final float fMo285toPx0680j_4 = pointerInputScope.mo285toPx0680j_4(Dp.m5678constructorimpl(64));
            d4 d4Var = new d4(h0Var, 2);
            d4 d4Var2 = new d4(h0Var, 3);
            final r7.l<Integer, x6.t0> lVar = this.$onMovePlaylistDown;
            final int i11 = this.$index;
            final r7.l<Integer, x6.t0> lVar2 = this.$onMovePlaylistUp;
            r7.p pVar = new r7.p() { // from class: com.arflix.tv.ui.screens.settings.f4
                @Override // r7.p
                public final Object invoke(Object obj2, Object obj3) {
                    float fFloatValue = ((Float) obj3).floatValue();
                    return SettingsScreenKt$IptvSettings$3$2$1$1$3$2$1.invokeSuspend$lambda$2(h0Var, fMo285toPx0680j_4, lVar, i11, lVar2, (PointerInputChange) obj2, fFloatValue);
                }
            };
            this.L$0 = null;
            this.L$1 = null;
            this.F$0 = fMo285toPx0680j_4;
            this.label = 1;
            Object objDetectVerticalDragGestures$default = DragGestureDetectorKt.detectVerticalDragGestures$default(pointerInputScope, null, d4Var, d4Var2, pVar, this, 1, null);
            e7.a aVar = e7.a.f15033i;
            if (objDetectVerticalDragGestures$default == aVar) {
                return aVar;
            }
        } else {
            if (i10 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            k2.c.G(obj);
        }
        return x6.t0.f22605a;
    }
}

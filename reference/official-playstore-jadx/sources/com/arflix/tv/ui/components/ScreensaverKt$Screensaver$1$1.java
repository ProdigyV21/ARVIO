package com.arflix.tv.ui.components;

import androidx.compose.runtime.MutableState;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.components.ScreensaverKt$Screensaver$1$1", f = "Screensaver.kt", l = {68}, m = "invokeSuspend", v = 2)
public final class ScreensaverKt$Screensaver$1$1 extends f7.j implements r7.p<ka.k0, d7.d<? super x6.t0>, Object> {
    final /* synthetic */ MutableState<Float> $directionX$delegate;
    final /* synthetic */ MutableState<Float> $directionY$delegate;
    final /* synthetic */ boolean $isVisible;
    final /* synthetic */ MutableState<Float> $positionX$delegate;
    final /* synthetic */ MutableState<Float> $positionY$delegate;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScreensaverKt$Screensaver$1$1(boolean z, MutableState<Float> mutableState, MutableState<Float> mutableState2, MutableState<Float> mutableState3, MutableState<Float> mutableState4, d7.d<? super ScreensaverKt$Screensaver$1$1> dVar) {
        super(2, dVar);
        this.$isVisible = z;
        this.$positionX$delegate = mutableState;
        this.$directionX$delegate = mutableState2;
        this.$positionY$delegate = mutableState3;
        this.$directionY$delegate = mutableState4;
    }

    @Override // f7.a
    public final d7.d<x6.t0> create(Object obj, d7.d<?> dVar) {
        return new ScreensaverKt$Screensaver$1$1(this.$isVisible, this.$positionX$delegate, this.$directionX$delegate, this.$positionY$delegate, this.$directionY$delegate, dVar);
    }

    /* JADX WARN: Path cross not found for [B:15:0x005e, B:17:0x0068], limit reached: 25 */
    /* JADX WARN: Path cross not found for [B:17:0x0068, B:15:0x005e], limit reached: 25 */
    /* JADX WARN: Path cross not found for [B:20:0x0089, B:22:0x0093], limit reached: 25 */
    /* JADX WARN: Path cross not found for [B:22:0x0093, B:20:0x0089], limit reached: 25 */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0026 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0089  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x0024 -> B:13:0x0027). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // f7.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            int r0 = r5.label
            r1 = 1
            if (r0 == 0) goto L13
            if (r0 != r1) goto Lb
            k2.c.G(r6)
            goto L27
        Lb:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L13:
            k2.c.G(r6)
            boolean r6 = r5.$isVisible
            if (r6 == 0) goto Lac
        L1a:
            r5.label = r1
            r2 = 50
            java.lang.Object r6 = ka.s0.a(r2, r5)
            e7.a r0 = e7.a.f15033i
            if (r6 != r0) goto L27
            return r0
        L27:
            androidx.compose.runtime.MutableState<java.lang.Float> r6 = r5.$positionX$delegate
            float r0 = com.arflix.tv.ui.components.ScreensaverKt.access$Screensaver$lambda$1(r6)
            androidx.compose.runtime.MutableState<java.lang.Float> r2 = r5.$directionX$delegate
            float r2 = com.arflix.tv.ui.components.ScreensaverKt.access$Screensaver$lambda$7(r2)
            r3 = 994352038(0x3b449ba6, float:0.003)
            float r2 = r2 * r3
            float r2 = r2 + r0
            com.arflix.tv.ui.components.ScreensaverKt.access$Screensaver$lambda$2(r6, r2)
            androidx.compose.runtime.MutableState<java.lang.Float> r6 = r5.$positionY$delegate
            float r0 = com.arflix.tv.ui.components.ScreensaverKt.access$Screensaver$lambda$4(r6)
            androidx.compose.runtime.MutableState<java.lang.Float> r2 = r5.$directionY$delegate
            float r2 = com.arflix.tv.ui.components.ScreensaverKt.access$Screensaver$lambda$10(r2)
            r3 = 990057071(0x3b03126f, float:0.002)
            float r2 = r2 * r3
            float r2 = r2 + r0
            com.arflix.tv.ui.components.ScreensaverKt.access$Screensaver$lambda$5(r6, r2)
            androidx.compose.runtime.MutableState<java.lang.Float> r6 = r5.$positionX$delegate
            float r6 = com.arflix.tv.ui.components.ScreensaverKt.access$Screensaver$lambda$1(r6)
            r0 = 0
            int r6 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            r2 = -1082130432(0xffffffffbf800000, float:-1.0)
            r3 = 1065353216(0x3f800000, float:1.0)
            if (r6 <= 0) goto L68
            androidx.compose.runtime.MutableState<java.lang.Float> r6 = r5.$positionX$delegate
            float r6 = com.arflix.tv.ui.components.ScreensaverKt.access$Screensaver$lambda$1(r6)
            int r6 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r6 < 0) goto L7f
        L68:
            androidx.compose.runtime.MutableState<java.lang.Float> r6 = r5.$directionX$delegate
            float r4 = com.arflix.tv.ui.components.ScreensaverKt.access$Screensaver$lambda$7(r6)
            float r4 = r4 * r2
            com.arflix.tv.ui.components.ScreensaverKt.access$Screensaver$lambda$8(r6, r4)
            androidx.compose.runtime.MutableState<java.lang.Float> r6 = r5.$positionX$delegate
            float r4 = com.arflix.tv.ui.components.ScreensaverKt.access$Screensaver$lambda$1(r6)
            float r4 = qb.d.m(r4, r0, r3)
            com.arflix.tv.ui.components.ScreensaverKt.access$Screensaver$lambda$2(r6, r4)
        L7f:
            androidx.compose.runtime.MutableState<java.lang.Float> r6 = r5.$positionY$delegate
            float r6 = com.arflix.tv.ui.components.ScreensaverKt.access$Screensaver$lambda$4(r6)
            int r6 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r6 <= 0) goto L93
            androidx.compose.runtime.MutableState<java.lang.Float> r6 = r5.$positionY$delegate
            float r6 = com.arflix.tv.ui.components.ScreensaverKt.access$Screensaver$lambda$4(r6)
            int r6 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r6 < 0) goto L1a
        L93:
            androidx.compose.runtime.MutableState<java.lang.Float> r6 = r5.$directionY$delegate
            float r4 = com.arflix.tv.ui.components.ScreensaverKt.access$Screensaver$lambda$10(r6)
            float r4 = r4 * r2
            com.arflix.tv.ui.components.ScreensaverKt.access$Screensaver$lambda$11(r6, r4)
            androidx.compose.runtime.MutableState<java.lang.Float> r6 = r5.$positionY$delegate
            float r2 = com.arflix.tv.ui.components.ScreensaverKt.access$Screensaver$lambda$4(r6)
            float r0 = qb.d.m(r2, r0, r3)
            com.arflix.tv.ui.components.ScreensaverKt.access$Screensaver$lambda$5(r6, r0)
            goto L1a
        Lac:
            x6.t0 r6 = x6.t0.f22605a
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.ScreensaverKt$Screensaver$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super x6.t0> dVar) {
        return ((ScreensaverKt$Screensaver$1$1) create(k0Var, dVar)).invokeSuspend(x6.t0.f22605a);
    }
}

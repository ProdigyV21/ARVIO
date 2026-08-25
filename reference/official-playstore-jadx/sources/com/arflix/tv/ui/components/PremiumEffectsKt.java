package com.arflix.tv.ui.components;

import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.InfiniteRepeatableSpec;
import androidx.compose.animation.core.InfiniteTransition;
import androidx.compose.animation.core.InfiniteTransitionKt;
import androidx.compose.animation.core.RepeatMode;
import androidx.compose.material3.MenuKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.unit.Dp;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.arflix.tv.ui.theme.ColorKt;
import com.google.android.gms.cast.MediaError;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\f\u001a9\u0010\b\u001a\u00020\u00042\b\b\u0002\u0010\u0001\u001a\u00020\u00002\u001e\b\u0002\u0010\u0007\u001a\u0018\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0002\b\u0005¢\u0006\u0002\b\u0006H\u0007¢\u0006\u0004\b\b\u0010\t\u001a3\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u000b\u001a\u00020\n2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0007¢\u0006\u0004\b\u000f\u0010\u0010\u001aK\u0010\u0016\u001a\u00020\u00042\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u0013\u001a\u00020\u00122\u001c\u0010\u0007\u001a\u0018\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0002\b\u0005¢\u0006\u0002\b\u0006H\u0007¢\u0006\u0004\b\u0014\u0010\u0015\u001aK\u0010\u001e\u001a\u00020\u00042\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0017\u001a\u00020\n2\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u00182\b\b\u0002\u0010\u0011\u001a\u00020\r2\b\b\u0002\u0010\u001b\u001a\u00020\rH\u0007¢\u0006\u0004\b\u001c\u0010\u001d\u001a#\u0010\"\u001a\u00020\u00042\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u001f\u001a\u00020\u0018H\u0007¢\u0006\u0004\b \u0010!\u001aK\u0010&\u001a\u00020\u00042\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010$\u001a\u00020#2\b\b\u0002\u0010%\u001a\u00020#2\u001c\u0010\u0007\u001a\u0018\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002¢\u0006\u0002\b\u0005¢\u0006\u0002\b\u0006H\u0007¢\u0006\u0004\b&\u0010'\u001a\u000f\u0010)\u001a\u00020(H\u0007¢\u0006\u0004\b)\u0010*\u001a-\u0010.\u001a\u00020\u00042\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010+\u001a\u00020\n2\b\b\u0002\u0010\u0011\u001a\u00020\rH\u0007¢\u0006\u0004\b,\u0010-\u001a\u0019\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0001\u001a\u00020\u0000H\u0007¢\u0006\u0004\b/\u00100\"\u0014\u00102\u001a\u0002018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00103\"\u0014\u00104\u001a\u0002018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00103¨\u0006=²\u0006\f\u00105\u001a\u00020\u00128\nX\u008a\u0084\u0002²\u0006\f\u00106\u001a\u00020\u00128\nX\u008a\u0084\u0002²\u0006\f\u00107\u001a\u00020\u00128\nX\u008a\u0084\u0002²\u0006\f\u00108\u001a\u00020\u00128\nX\u008a\u0084\u0002²\u0006\f\u00109\u001a\u00020\u00128\nX\u008a\u0084\u0002²\u0006\f\u0010:\u001a\u00020\u00128\nX\u008a\u0084\u0002²\u0006\f\u0010;\u001a\u00020\u00128\nX\u008a\u0084\u0002²\u0006\f\u00107\u001a\u00020\u00128\nX\u008a\u0084\u0002²\u0006\f\u00108\u001a\u00020\u00128\nX\u008a\u0084\u0002²\u0006\f\u0010<\u001a\u00020\u00128\nX\u008a\u0084\u0002"}, d2 = {"Landroidx/compose/ui/Modifier;", "modifier", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/BoxScope;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "content", "AnimatedGradientBackground", "(Landroidx/compose/ui/Modifier;Lr7/q;Landroidx/compose/runtime/Composer;II)V", "", "particleCount", "", "Landroidx/compose/ui/graphics/Color;", "colors", "FloatingParticles", "(Landroidx/compose/ui/Modifier;ILjava/util/List;Landroidx/compose/runtime/Composer;II)V", TtmlNode.ATTR_TTS_COLOR, "", "pulseScale", "PulsingGlow-sW7UJKQ", "(Landroidx/compose/ui/Modifier;JFLr7/q;Landroidx/compose/runtime/Composer;II)V", "PulsingGlow", "dotCount", "Landroidx/compose/ui/unit/Dp;", "dotSize", "dotSpacing", "secondaryColor", "WaveLoadingDots-AxOVJ2Y", "(Landroidx/compose/ui/Modifier;IFFJJLandroidx/compose/runtime/Composer;II)V", "WaveLoadingDots", "height", "GradientSweepLine-rAjV9yQ", "(Landroidx/compose/ui/Modifier;FLandroidx/compose/runtime/Composer;II)V", "GradientSweepLine", "", "hasBorder", "borderGradient", "GlassmorphicCard", "(Landroidx/compose/ui/Modifier;ZZLr7/q;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/ui/graphics/Brush;", "gradientTextBrush", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Brush;", "ringCount", "RingPulseEffect-FNF3uiM", "(Landroidx/compose/ui/Modifier;IJLandroidx/compose/runtime/Composer;II)V", "RingPulseEffect", "ShimmerEffect", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)Landroidx/compose/ui/Modifier;", "Landroidx/compose/animation/core/CubicBezierEasing;", "EaseInOutCubic", "Landroidx/compose/animation/core/CubicBezierEasing;", "EaseOut", "animatedOffset", "time", "scale", "alpha", "offsetY", "dotAlpha", "sweepProgress", "shimmerOffset", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class PremiumEffectsKt {
    private static final CubicBezierEasing EaseInOutCubic = new CubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f);
    private static final CubicBezierEasing EaseOut = new CubicBezierEasing(0.0f, 0.0f, 0.2f, 1.0f);

    /* JADX WARN: Removed duplicated region for block: B:31:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x005e  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0233  */
    /* JADX WARN: Removed duplicated region for block: B:61:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void AnimatedGradientBackground(androidx.compose.ui.Modifier r26, r7.q<? super androidx.compose.foundation.layout.BoxScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r27, androidx.compose.runtime.Composer r28, int r29, int r30) {
        /*
            Method dump skipped, instruction units count: 578
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.PremiumEffectsKt.AnimatedGradientBackground(androidx.compose.ui.Modifier, r7.q, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final float AnimatedGradientBackground$lambda$0(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 AnimatedGradientBackground$lambda$2(Modifier modifier, r7.q qVar, int i10, int i11, Composer composer, int i12) {
        AnimatedGradientBackground(modifier, qVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:74:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void FloatingParticles(androidx.compose.ui.Modifier r27, int r28, java.util.List<androidx.compose.ui.graphics.Color> r29, androidx.compose.runtime.Composer r30, int r31, int r32) {
        /*
            Method dump skipped, instruction units count: 399
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.PremiumEffectsKt.FloatingParticles(androidx.compose.ui.Modifier, int, java.util.List, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final float FloatingParticles$lambda$1(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 FloatingParticles$lambda$2$0(List list, State state, DrawScope drawScope) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Particle particle = (Particle) it.next();
            float speed = (((particle.getSpeed() * ((float) Math.cos(particle.getAngle()))) * FloatingParticles$lambda$1(state)) + particle.getX()) % 1.0f;
            float speed2 = ((((particle.getSpeed() * ((float) Math.sin(particle.getAngle()))) * FloatingParticles$lambda$1(state)) * 0.5f) + particle.getY()) % 1.0f;
            androidx.compose.ui.graphics.drawscope.c.x(drawScope, Color.m3471copywmQWz5c$default(particle.m6174getColor0d7_KjU(), 0.3f, 0.0f, 0.0f, 0.0f, 14, null), particle.getRadius() * 3, OffsetKt.Offset(speed < 0.0f ? speed + 1.0f : Size.m3305getWidthimpl(drawScope.mo3916getSizeNHjbRc()) * speed, speed2 < 0.0f ? speed2 + 1.0f : Size.m3302getHeightimpl(drawScope.mo3916getSizeNHjbRc()) * speed2), 0.0f, null, null, 0, MenuKt.InTransitionDuration, null);
            long jM6174getColor0d7_KjU = particle.m6174getColor0d7_KjU();
            float radius = particle.getRadius();
            if (speed < 0.0f) {
                speed += 1.0f;
            }
            float fM3305getWidthimpl = Size.m3305getWidthimpl(drawScope.mo3916getSizeNHjbRc()) * speed;
            if (speed2 < 0.0f) {
                speed2 += 1.0f;
            }
            androidx.compose.ui.graphics.drawscope.c.x(drawScope, jM6174getColor0d7_KjU, radius, OffsetKt.Offset(fM3305getWidthimpl, Size.m3302getHeightimpl(drawScope.mo3916getSizeNHjbRc()) * speed2), 0.0f, null, null, 0, MenuKt.InTransitionDuration, null);
        }
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 FloatingParticles$lambda$3(Modifier modifier, int i10, List list, int i11, int i12, Composer composer, int i13) {
        FloatingParticles(modifier, i10, list, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1), i12);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0194  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01b7  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:86:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void GlassmorphicCard(androidx.compose.ui.Modifier r24, boolean r25, boolean r26, r7.q<? super androidx.compose.foundation.layout.BoxScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r27, androidx.compose.runtime.Composer r28, int r29, int r30) {
        /*
            Method dump skipped, instruction units count: 594
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.PremiumEffectsKt.GlassmorphicCard(androidx.compose.ui.Modifier, boolean, boolean, r7.q, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 GlassmorphicCard$lambda$1(Modifier modifier, boolean z, boolean z5, r7.q qVar, int i10, int i11, Composer composer, int i12) {
        GlassmorphicCard(modifier, z, z5, qVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x006b  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:59:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: GradientSweepLine-rAjV9yQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6177GradientSweepLinerAjV9yQ(androidx.compose.ui.Modifier r23, float r24, androidx.compose.runtime.Composer r25, int r26, int r27) {
        /*
            Method dump skipped, instruction units count: 480
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.PremiumEffectsKt.m6177GradientSweepLinerAjV9yQ(androidx.compose.ui.Modifier, float, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final float GradientSweepLine_rAjV9yQ$lambda$0(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 GradientSweepLine_rAjV9yQ$lambda$2(Modifier modifier, float f10, int i10, int i11, Composer composer, int i12) {
        m6177GradientSweepLinerAjV9yQ(modifier, f10, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0050  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0151  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0174  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:81:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: PulsingGlow-sW7UJKQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6178PulsingGlowsW7UJKQ(androidx.compose.ui.Modifier r35, long r36, float r38, r7.q<? super androidx.compose.foundation.layout.BoxScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r39, androidx.compose.runtime.Composer r40, int r41, int r42) {
        /*
            Method dump skipped, instruction units count: 552
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.PremiumEffectsKt.m6178PulsingGlowsW7UJKQ(androidx.compose.ui.Modifier, long, float, r7.q, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final float PulsingGlow_sW7UJKQ$lambda$0(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final float PulsingGlow_sW7UJKQ$lambda$1(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 PulsingGlow_sW7UJKQ$lambda$3(Modifier modifier, long j10, float f10, r7.q qVar, int i10, int i11, Composer composer, int i12) {
        m6178PulsingGlowsW7UJKQ(modifier, j10, f10, qVar, composer, RecomposeScopeImplKt.updateChangedFlags(i10 | 1), i11);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01e7  */
    /* JADX WARN: Removed duplicated region for block: B:91:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: RingPulseEffect-FNF3uiM, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6179RingPulseEffectFNF3uiM(androidx.compose.ui.Modifier r25, int r26, long r27, androidx.compose.runtime.Composer r29, final int r30, final int r31) {
        /*
            Method dump skipped, instruction units count: 498
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.PremiumEffectsKt.m6179RingPulseEffectFNF3uiM(androidx.compose.ui.Modifier, int, long, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final float RingPulseEffect_FNF3uiM$lambda$0$0$0(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final float RingPulseEffect_FNF3uiM$lambda$0$0$1(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 RingPulseEffect_FNF3uiM$lambda$0$0$2$0(long j10, State state, DrawScope drawScope) {
        androidx.compose.ui.graphics.drawscope.c.x(drawScope, Color.m3471copywmQWz5c$default(j10, RingPulseEffect_FNF3uiM$lambda$0$0$1(state), 0.0f, 0.0f, 0.0f, 14, null), 0.0f, 0L, 0.0f, new Stroke(drawScope.mo285toPx0680j_4(Dp.m5678constructorimpl(2)), 0.0f, 0, 0, null, 30, null), null, 0, MediaError.DetailedErrorCode.SOURCE_BUFFER_FAILURE, null);
        return x6.t0.f22605a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 RingPulseEffect_FNF3uiM$lambda$1(Modifier modifier, int i10, long j10, int i11, int i12, Composer composer, int i13) {
        m6179RingPulseEffectFNF3uiM(modifier, i10, j10, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1), i12);
        return x6.t0.f22605a;
    }

    public static final Modifier ShimmerEffect(Modifier modifier, Composer composer, int i10, int i11) {
        if ((i11 & 1) != 0) {
            modifier = Modifier.INSTANCE;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1144412714, i10, -1, "com.arflix.tv.ui.components.ShimmerEffect (PremiumEffects.kt:473)");
        }
        State<Float> stateAnimateFloat = InfiniteTransitionKt.animateFloat(InfiniteTransitionKt.rememberInfiniteTransition("shimmer", composer, 6, 0), -1.0f, 2.0f, AnimationSpecKt.m93infiniteRepeatable9IiC70o$default(AnimationSpecKt.tween$default(1500, 0, EasingKt.getLinearEasing(), 2, null), RepeatMode.Restart, 0L, 4, null), "shimmerOffset", composer, InfiniteTransition.$stable | 24960 | (InfiniteRepeatableSpec.$stable << 9), 0);
        boolean zChanged = composer.changed(stateAnimateFloat);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new c(stateAnimateFloat, 4);
            composer.updateRememberedValue(objRememberedValue);
        }
        Modifier modifierDrawBehind = DrawModifierKt.drawBehind(modifier, (r7.l) objRememberedValue);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return modifierDrawBehind;
    }

    private static final float ShimmerEffect$lambda$0(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 ShimmerEffect$lambda$1$0(State state, DrawScope drawScope) {
        Brush.Companion companion = Brush.INSTANCE;
        Color.Companion companion2 = Color.INSTANCE;
        androidx.compose.ui.graphics.drawscope.c.J(drawScope, Brush.Companion.m3429linearGradientmHitzGk$default(companion, t7.a.E(Color.m3462boximpl(companion2.m3507getTransparent0d7_KjU()), Color.m3462boximpl(Color.m3471copywmQWz5c$default(companion2.m3509getWhite0d7_KjU(), 0.1f, 0.0f, 0.0f, 0.0f, 14, null)), Color.m3462boximpl(companion2.m3507getTransparent0d7_KjU())), OffsetKt.Offset(Size.m3305getWidthimpl(drawScope.mo3916getSizeNHjbRc()) * ShimmerEffect$lambda$0(state), 0.0f), OffsetKt.Offset((ShimmerEffect$lambda$0(state) + 0.5f) * Size.m3305getWidthimpl(drawScope.mo3916getSizeNHjbRc()), Size.m3302getHeightimpl(drawScope.mo3916getSizeNHjbRc())), 0, 8, (Object) null), 0L, 0L, 0.0f, null, null, 0, 126, null);
        return x6.t0.f22605a;
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x01a1 A[LOOP:0: B:105:0x019f->B:106:0x01a1, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x02ec  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x02ff  */
    /* JADX WARN: Removed duplicated region for block: B:116:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004d  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00c0  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0103  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0165  */
    /* JADX INFO: renamed from: WaveLoadingDots-AxOVJ2Y, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m6180WaveLoadingDotsAxOVJ2Y(androidx.compose.ui.Modifier r25, int r26, float r27, float r28, long r29, long r31, androidx.compose.runtime.Composer r33, final int r34, final int r35) {
        /*
            Method dump skipped, instruction units count: 778
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.arflix.tv.ui.components.PremiumEffectsKt.m6180WaveLoadingDotsAxOVJ2Y(androidx.compose.ui.Modifier, int, float, float, long, long, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final float WaveLoadingDots_AxOVJ2Y$lambda$0$0$0(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final float WaveLoadingDots_AxOVJ2Y$lambda$0$0$1(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final x6.t0 WaveLoadingDots_AxOVJ2Y$lambda$1(Modifier modifier, int i10, float f10, float f11, long j10, long j11, int i11, int i12, Composer composer, int i13) {
        m6180WaveLoadingDotsAxOVJ2Y(modifier, i10, f10, f11, j10, j11, composer, RecomposeScopeImplKt.updateChangedFlags(i11 | 1), i12);
        return x6.t0.f22605a;
    }

    public static final Brush gradientTextBrush(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1902041295, i10, -1, "com.arflix.tv.ui.components.gradientTextBrush (PremiumEffects.kt:401)");
        }
        Brush brushM3429linearGradientmHitzGk$default = Brush.Companion.m3429linearGradientmHitzGk$default(Brush.INSTANCE, t7.a.E(Color.m3462boximpl(ColorKt.getPurpleDark()), Color.m3462boximpl(ColorKt.getPurple()), Color.m3462boximpl(ColorKt.getPurpleLight())), 0L, 0L, 0, 14, (Object) null);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return brushM3429linearGradientmHitzGk$default;
    }
}

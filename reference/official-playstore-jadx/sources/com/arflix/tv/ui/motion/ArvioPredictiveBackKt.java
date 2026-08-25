package com.arflix.tv.ui.motion;

import androidx.activity.compose.PredictiveBackHandlerKt;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.animation.core.Easing;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.ts.PsExtractor;
import com.arflix.tv.data.repository.c0;
import com.arflix.tv.data.repository.g;
import d7.k;
import ka.k0;
import kotlin.Metadata;
import qb.d;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a9\u0010\t\u001a\u00020\b2\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0007¢\u0006\u0004\b\t\u0010\n\u001a\u0019\u0010\r\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\f\u001a\u00020\b¢\u0006\u0004\b\r\u0010\u000e\u001a!\u0010\u0010\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u0019\u0010\u0012\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\f\u001a\u00020\b¢\u0006\u0004\b\u0012\u0010\u000e\"\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0019\u0010\u001a\"\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001a¨\u0006\u001e²\u0006\u0012\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\nX\u008a\u0084\u0002"}, d2 = {"", "enabled", "", "commitDurationMs", "cancelDurationMs", "Lkotlin/Function0;", "Lx6/t0;", "onCommit", "Lcom/arflix/tv/ui/motion/ArvioBackMotion;", "rememberArvioPredictiveBack", "(ZIILr7/a;Landroidx/compose/runtime/Composer;II)Lcom/arflix/tv/ui/motion/ArvioBackMotion;", "Landroidx/compose/ui/Modifier;", "motion", "arvioBackSurface", "(Landroidx/compose/ui/Modifier;Lcom/arflix/tv/ui/motion/ArvioBackMotion;)Landroidx/compose/ui/Modifier;", "active", "arvioBackPeek", "(Landroidx/compose/ui/Modifier;Lcom/arflix/tv/ui/motion/ArvioBackMotion;Z)Landroidx/compose/ui/Modifier;", "arvioBackModal", "Landroidx/compose/animation/core/Easing;", "ArvioStandardDecelerate", "Landroidx/compose/animation/core/Easing;", "getArvioStandardDecelerate", "()Landroidx/compose/animation/core/Easing;", "", "MinSurfaceScale", "F", "Landroidx/compose/ui/unit/Dp;", "EdgeMargin", "commit", "app_playRelease"}, k = 2, mv = {2, 3, 0}, xi = 48)
public final class ArvioPredictiveBackKt {
    private static final Easing ArvioStandardDecelerate = new CubicBezierEasing(0.05f, 0.7f, 0.1f, 1.0f);
    private static final float EdgeMargin = Dp.m5678constructorimpl(8);
    private static final float MinSurfaceScale = 0.9f;

    public static final Modifier arvioBackModal(Modifier modifier, ArvioBackMotion arvioBackMotion) {
        return GraphicsLayerModifierKt.graphicsLayer(modifier, new a(arvioBackMotion, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 arvioBackModal$lambda$0(ArvioBackMotion arvioBackMotion, GraphicsLayerScope graphicsLayerScope) {
        float eased = arvioBackMotion.getEased();
        t0 t0Var = t0.f22605a;
        if (eased <= 0.0f) {
            return t0Var;
        }
        float f10 = 1.0f - (0.100000024f * eased);
        graphicsLayerScope.setScaleX(f10);
        graphicsLayerScope.setScaleY(f10);
        graphicsLayerScope.setAlpha(1.0f - (eased * 0.3f));
        return t0Var;
    }

    public static final Modifier arvioBackPeek(Modifier modifier, ArvioBackMotion arvioBackMotion, boolean z) {
        return GraphicsLayerModifierKt.graphicsLayer(modifier, new c0(2, arvioBackMotion, z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 arvioBackPeek$lambda$0(boolean z, ArvioBackMotion arvioBackMotion, GraphicsLayerScope graphicsLayerScope) {
        t0 t0Var = t0.f22605a;
        if (!z) {
            return t0Var;
        }
        float eased = arvioBackMotion.getEased();
        if (eased <= 0.0f) {
            return t0Var;
        }
        float f10 = (0.04f * eased) + 0.96f;
        graphicsLayerScope.setScaleX(f10);
        graphicsLayerScope.setScaleY(f10);
        graphicsLayerScope.setAlpha((eased * 0.4f) + 0.6f);
        return t0Var;
    }

    public static final Modifier arvioBackSurface(Modifier modifier, ArvioBackMotion arvioBackMotion) {
        return GraphicsLayerModifierKt.graphicsLayer(modifier, new a(arvioBackMotion, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final t0 arvioBackSurface$lambda$0(ArvioBackMotion arvioBackMotion, GraphicsLayerScope graphicsLayerScope) {
        float eased = arvioBackMotion.getEased();
        t0 t0Var = t0.f22605a;
        if (eased <= 0.0f) {
            return t0Var;
        }
        float f10 = 1.0f - (0.100000024f * eased);
        graphicsLayerScope.setScaleX(f10);
        graphicsLayerScope.setScaleY(f10);
        float fMo285toPx0680j_4 = graphicsLayerScope.mo285toPx0680j_4(EdgeMargin);
        float fM3305getWidthimpl = (Size.m3305getWidthimpl(graphicsLayerScope.getSize()) / 20.0f) - fMo285toPx0680j_4;
        if (fM3305getWidthimpl < 0.0f) {
            fM3305getWidthimpl = 0.0f;
        }
        graphicsLayerScope.setTranslationX((arvioBackMotion.getSwipeEdge() == 1 ? -1.0f : 1.0f) * eased * fM3305getWidthimpl);
        float fM3302getHeightimpl = (Size.m3302getHeightimpl(graphicsLayerScope.getSize()) / 20.0f) - fMo285toPx0680j_4;
        if (fM3302getHeightimpl < 0.0f) {
            fM3302getHeightimpl = 0.0f;
        }
        graphicsLayerScope.setTranslationY(eased * (Float.isNaN(arvioBackMotion.getTouchY()) ? 0.0f : d.m((arvioBackMotion.getTouchY() - (Size.m3302getHeightimpl(graphicsLayerScope.getSize()) / 2.0f)) / (Size.m3302getHeightimpl(graphicsLayerScope.getSize()) / 2.0f), -1.0f, 1.0f)) * fM3302getHeightimpl);
        return t0Var;
    }

    public static final Easing getArvioStandardDecelerate() {
        return ArvioStandardDecelerate;
    }

    public static final ArvioBackMotion rememberArvioPredictiveBack(boolean z, int i10, int i11, r7.a<t0> aVar, Composer composer, int i12, int i13) {
        if ((i13 & 2) != 0) {
            i10 = 280;
        }
        int i14 = i10;
        if ((i13 & 4) != 0) {
            i11 = PsExtractor.VIDEO_STREAM_MASK;
        }
        int i15 = i11;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1712091138, i12, -1, "com.arflix.tv.ui.motion.rememberArvioPredictiveBack (ArvioPredictiveBack.kt:61)");
        }
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = new ArvioBackMotion();
            composer.updateRememberedValue(objRememberedValue);
        }
        ArvioBackMotion arvioBackMotion = (ArvioBackMotion) objRememberedValue;
        Object objRememberedValue2 = composer.rememberedValue();
        if (objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = AnimatableKt.Animatable$default(0.0f, 0.0f, 2, null);
            composer.updateRememberedValue(objRememberedValue2);
        }
        Animatable animatable = (Animatable) objRememberedValue2;
        Object objRememberedValue3 = composer.rememberedValue();
        if (objRememberedValue3 == companion.getEmpty()) {
            objRememberedValue3 = g.h(EffectsKt.createCompositionCoroutineScope(k.f14688i, composer), composer);
        }
        k0 coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue3).getCoroutineScope();
        State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(aVar, composer, (i12 >> 9) & 14);
        boolean zChangedInstance = composer.changedInstance(animatable);
        Object objRememberedValue4 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue4 == companion.getEmpty()) {
            objRememberedValue4 = new ArvioPredictiveBackKt$rememberArvioPredictiveBack$1$1(animatable, arvioBackMotion, null);
            composer.updateRememberedValue(objRememberedValue4);
        }
        EffectsKt.LaunchedEffect(animatable, (p<? super k0, ? super d7.d<? super t0>, ? extends Object>) objRememberedValue4, composer, Animatable.$stable);
        boolean z5 = true;
        boolean zChangedInstance2 = composer.changedInstance(animatable) | composer.changedInstance(coroutineScope) | ((((i12 & 112) ^ 48) > 32 && composer.changed(i14)) || (i12 & 48) == 32) | composer.changed(stateRememberUpdatedState);
        if ((((i12 & 896) ^ RendererCapabilities.DECODER_SUPPORT_MASK) <= 256 || !composer.changed(i15)) && (i12 & RendererCapabilities.DECODER_SUPPORT_MASK) != 256) {
            z5 = false;
        }
        boolean z10 = zChangedInstance2 | z5;
        Object objRememberedValue5 = composer.rememberedValue();
        if (z10 || objRememberedValue5 == companion.getEmpty()) {
            Object arvioPredictiveBackKt$rememberArvioPredictiveBack$2$1 = new ArvioPredictiveBackKt$rememberArvioPredictiveBack$2$1(coroutineScope, arvioBackMotion, animatable, i14, stateRememberUpdatedState, i15, null);
            composer.updateRememberedValue(arvioPredictiveBackKt$rememberArvioPredictiveBack$2$1);
            objRememberedValue5 = arvioPredictiveBackKt$rememberArvioPredictiveBack$2$1;
        }
        PredictiveBackHandlerKt.PredictiveBackHandler(z, (p) objRememberedValue5, composer, i12 & 14, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return arvioBackMotion;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final r7.a<t0> rememberArvioPredictiveBack$lambda$2(State<? extends r7.a<t0>> state) {
        return state.getValue();
    }
}

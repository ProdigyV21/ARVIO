package androidx.tv.material3;

import androidx.compose.animation.AnimatedVisibilityKt;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.exoplayer.source.ProgressiveMediaSource;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u001aÂ\u0001\u0010\u0018\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00002\u0015\b\u0002\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\b2\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\b2\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\u0011\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\bH\u0007¢\u0006\u0004\b\u0018\u0010\u0019\u001aÊ\u0001\u0010 \u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u00052\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00002\u0015\b\u0002\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\b2\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\b2\b\b\u0002\u0010\f\u001a\u00020\u001b2\b\b\u0002\u0010\u000e\u001a\u00020\u001c2\b\b\u0002\u0010\u0010\u001a\u00020\u001d2\b\b\u0002\u0010\u0012\u001a\u00020\u001e2\b\b\u0002\u0010\u0014\u001a\u00020\u001f2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\u0011\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\bH\u0007¢\u0006\u0004\b \u0010!\u001aá\u0001\u0010#\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u00052\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00002\u0015\b\u0002\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\b2\u0015\b\u0002\u0010\"\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\b2\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\b2\b\b\u0002\u0010\f\u001a\u00020\u001b2\b\b\u0002\u0010\u000e\u001a\u00020\u001c2\b\b\u0002\u0010\u0010\u001a\u00020\u001d2\b\b\u0002\u0010\u0012\u001a\u00020\u001e2\b\b\u0002\u0010\u0014\u001a\u00020\u001f2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\u0011\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\bH\u0007¢\u0006\u0004\b#\u0010$\u001a\u0094\u0001\u0010%\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00052\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00002\b\b\u0002\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0014\u001a\u00020\u00132\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00152\u0011\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\bH\u0007¢\u0006\u0004\b%\u0010&\u001aÇ\u0001\u00100\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00002\u0006\u0010\u0006\u001a\u00020\u00052\u0011\u0010'\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\b2\u0006\u0010)\u001a\u00020(2\u0013\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\b2\u0013\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010+\u001a\u00020*2\u0006\u0010-\u001a\u00020,2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0003ø\u0001\u0000¢\u0006\u0004\b.\u0010/\u001aä\u0001\u00103\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00032\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00002\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00002\u0006\u0010\u0006\u001a\u00020\u00052\u0011\u0010'\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\b2\u0006\u0010)\u001a\u00020(2\u0013\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\b2\u0013\u0010\"\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\b2\u0013\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\b2\u0006\u0010\f\u001a\u00020\u001b2\u0006\u0010\u000e\u001a\u00020\u001c2\u0006\u0010\u0010\u001a\u00020\u001d2\u0006\u0010\u0012\u001a\u00020\u001e2\u0006\u0010\u0014\u001a\u00020\u001f2\u0006\u0010+\u001a\u00020*2\u0006\u0010-\u001a\u00020,2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0003ø\u0001\u0000¢\u0006\u0004\b1\u00102\u001a|\u00106\u001a\u00020\u00012\u0011\u0010'\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\b2\u0006\u0010)\u001a\u00020(2\u0013\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\b2\u0013\u0010\"\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\b2\u0013\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0000¢\u0006\u0002\b\b2\u0006\u0010+\u001a\u00020*2\u0006\u0010-\u001a\u00020,H\u0003ø\u0001\u0000¢\u0006\u0004\b4\u00105\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00067"}, d2 = {"Lkotlin/Function0;", "Lx6/t0;", "onClick", "Landroidx/compose/ui/Modifier;", "modifier", "", "enabled", "onLongClick", "Landroidx/compose/runtime/Composable;", "leadingIcon", "trailingIcon", "Landroidx/tv/material3/b1;", "shape", "Landroidx/tv/material3/y0;", "colors", "Landroidx/tv/material3/a1;", "scale", "Landroidx/tv/material3/x0;", "border", "Landroidx/tv/material3/z0;", "glow", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "content", "AssistChip", "(Lr7/a;Landroidx/compose/ui/Modifier;ZLr7/a;Lr7/p;Lr7/p;Landroidx/tv/material3/b1;Landroidx/tv/material3/y0;Landroidx/tv/material3/a1;Landroidx/tv/material3/x0;Landroidx/tv/material3/z0;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/p;Landroidx/compose/runtime/Composer;III)V", "selected", "Landroidx/tv/material3/w2;", "Landroidx/tv/material3/t2;", "Landroidx/tv/material3/v2;", "Landroidx/tv/material3/s2;", "Landroidx/tv/material3/u2;", "FilterChip", "(ZLr7/a;Landroidx/compose/ui/Modifier;ZLr7/a;Lr7/p;Lr7/p;Landroidx/tv/material3/w2;Landroidx/tv/material3/t2;Landroidx/tv/material3/v2;Landroidx/tv/material3/s2;Landroidx/tv/material3/u2;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/p;Landroidx/compose/runtime/Composer;III)V", "avatar", "InputChip", "(ZLr7/a;Landroidx/compose/ui/Modifier;ZLr7/a;Lr7/p;Lr7/p;Lr7/p;Landroidx/tv/material3/w2;Landroidx/tv/material3/t2;Landroidx/tv/material3/v2;Landroidx/tv/material3/s2;Landroidx/tv/material3/u2;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/p;Landroidx/compose/runtime/Composer;III)V", "SuggestionChip", "(Lr7/a;Landroidx/compose/ui/Modifier;ZLr7/a;Landroidx/tv/material3/b1;Landroidx/tv/material3/y0;Landroidx/tv/material3/a1;Landroidx/tv/material3/x0;Landroidx/tv/material3/z0;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/p;Landroidx/compose/runtime/Composer;III)V", "label", "Landroidx/compose/ui/text/TextStyle;", "labelTextStyle", "Landroidx/compose/ui/unit/Dp;", "minHeight", "Landroidx/compose/foundation/layout/PaddingValues;", "paddingValues", "ClickableChip-u0RnIRE", "(Landroidx/compose/ui/Modifier;Lr7/a;Lr7/a;ZLr7/p;Landroidx/compose/ui/text/TextStyle;Lr7/p;Lr7/p;Landroidx/tv/material3/b1;Landroidx/tv/material3/y0;Landroidx/tv/material3/a1;Landroidx/tv/material3/x0;Landroidx/tv/material3/z0;FLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "ClickableChip", "SelectableChip-lguZlCk", "(ZLandroidx/compose/ui/Modifier;Lr7/a;Lr7/a;ZLr7/p;Landroidx/compose/ui/text/TextStyle;Lr7/p;Lr7/p;Lr7/p;Landroidx/tv/material3/w2;Landroidx/tv/material3/t2;Landroidx/tv/material3/v2;Landroidx/tv/material3/s2;Landroidx/tv/material3/u2;FLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "SelectableChip", "ChipContent-HYR8e34", "(Lr7/p;Landroidx/compose/ui/text/TextStyle;Lr7/p;Lr7/p;Lr7/p;FLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;I)V", "ChipContent", "tv-material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ChipKt {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final float f5592a = Dp.m5678constructorimpl(8);

    public static final void AssistChip(r7.a<x6.t0> aVar, Modifier modifier, boolean z, r7.a<x6.t0> aVar2, r7.p<? super Composer, ? super Integer, x6.t0> pVar, r7.p<? super Composer, ? super Integer, x6.t0> pVar2, b1 b1Var, y0 y0Var, a1 a1Var, x0 x0Var, z0 z0Var, MutableInteractionSource mutableInteractionSource, r7.p<? super Composer, ? super Integer, x6.t0> pVar3, Composer composer, int i10, int i11, int i12) {
        b1 b1Var2;
        int i13;
        y0 y0VarM5972colorsoq7We08;
        a1 a1Var2;
        Composer composer2;
        x0 x0VarBorder;
        z0 z0Var2;
        int i14;
        int i15;
        composer.startReplaceableGroup(-1160189982);
        Modifier modifier2 = (i12 & 2) != 0 ? Modifier.INSTANCE : modifier;
        boolean z5 = (i12 & 4) != 0 ? true : z;
        r7.a<x6.t0> aVar3 = (i12 & 8) != 0 ? null : aVar2;
        r7.p<? super Composer, ? super Integer, x6.t0> pVar4 = (i12 & 16) != 0 ? null : pVar;
        r7.p<? super Composer, ? super Integer, x6.t0> pVar5 = (i12 & 32) != 0 ? null : pVar2;
        if ((i12 & 64) != 0) {
            RoundedCornerShape roundedCornerShape = AssistChipDefaults.f5529c;
            b1Var2 = new b1(roundedCornerShape, roundedCornerShape, roundedCornerShape, roundedCornerShape, roundedCornerShape);
        } else {
            b1Var2 = b1Var;
        }
        if ((i12 & 128) != 0) {
            i13 = i12;
            y0VarM5972colorsoq7We08 = AssistChipDefaults.f5527a.m5972colorsoq7We08(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composer, 100663296, 255);
        } else {
            i13 = i12;
            y0VarM5972colorsoq7We08 = y0Var;
        }
        if ((i13 & 256) != 0) {
            AssistChipDefaults assistChipDefaults = AssistChipDefaults.f5527a;
            a1Var2 = new a1();
        } else {
            a1Var2 = a1Var;
        }
        if ((i13 & 512) != 0) {
            composer2 = composer;
            x0VarBorder = AssistChipDefaults.f5527a.border(null, null, null, null, null, composer, 196608, 31);
        } else {
            composer2 = composer;
            x0VarBorder = x0Var;
        }
        if ((i13 & 1024) != 0) {
            AssistChipDefaults assistChipDefaults2 = AssistChipDefaults.f5527a;
            s1 s1Var = s1.f6376c;
            z0Var2 = new z0(s1Var, s1Var, s1Var);
        } else {
            z0Var2 = z0Var;
        }
        MutableInteractionSource mutableInteractionSource2 = (i13 & 2048) != 0 ? null : mutableInteractionSource;
        if (ComposerKt.isTraceInProgress()) {
            i14 = i10;
            i15 = i11;
            ComposerKt.traceEventStart(-1160189982, i14, i15, "androidx.tv.material3.AssistChip (Chip.kt:92)");
        } else {
            i14 = i10;
            i15 = i11;
        }
        TextStyle textStyle = MaterialTheme.f5670a.getTypography(composer2, 6).f6310m;
        float f10 = AssistChipDefaults.f5528b;
        int i16 = i14 >> 3;
        int i17 = i14 << 3;
        int i18 = (i16 & 896) | (i16 & 14) | (i17 & 112) | (i17 & 7168);
        int i19 = i15 << 6;
        int i20 = i14 << 6;
        int i21 = i18 | (57344 & i19) | (3670016 & i20) | (29360128 & i20) | (234881024 & i20) | (i20 & 1879048192);
        int i22 = i14 >> 24;
        Composer composer3 = composer2;
        r7.a<x6.t0> aVar4 = aVar3;
        m5984ClickableChipu0RnIRE(modifier2, aVar, aVar4, z5, pVar3, textStyle, pVar4, pVar5, b1Var2, y0VarM5972colorsoq7We08, a1Var2, x0VarBorder, z0Var2, f10, b(false, pVar4 != null, pVar5 != null), mutableInteractionSource2, composer3, i21, (i22 & 112) | (i22 & 14) | 3072 | (i19 & 896) | ((i15 << 12) & 458752));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ChipContent-HYR8e34, reason: not valid java name */
    public static final void m5983ChipContentHYR8e34(r7.p<? super Composer, ? super Integer, x6.t0> pVar, TextStyle textStyle, r7.p<? super Composer, ? super Integer, x6.t0> pVar2, r7.p<? super Composer, ? super Integer, x6.t0> pVar3, r7.p<? super Composer, ? super Integer, x6.t0> pVar4, float f10, PaddingValues paddingValues, Composer composer, int i10) {
        int i11;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1778933019);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changedInstance(pVar) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changed(textStyle) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(pVar2) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(pVar3) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(pVar4) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            i11 |= composerStartRestartGroup.changed(f10) ? 131072 : 65536;
        }
        if ((1572864 & i10) == 0) {
            i11 |= composerStartRestartGroup.changed(paddingValues) ? ProgressiveMediaSource.DEFAULT_LOADING_CHECK_INTERVAL_BYTES : 524288;
        }
        if ((599187 & i11) == 599186 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1778933019, i11, -1, "androidx.tv.material3.ChipContent (Chip.kt:442)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierPadding = PaddingKt.padding(SizeKt.m563defaultMinSizeVpY3zN4$default(companion, 0.0f, f10, 1, null), paddingValues);
            Arrangement.Horizontal start = Arrangement.INSTANCE.getStart();
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            composerStartRestartGroup.startReplaceableGroup(693286680);
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(start, centerVertically, composerStartRestartGroup, 54);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            r7.q<SkippableUpdater<ComposeUiNode>, Composer, Integer, x6.t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierPadding);
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composerStartRestartGroup);
            r7.p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicyRowMeasurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i12 = i11;
            AnimatedVisibilityKt.AnimatedVisibility(pVar3 != null, (Modifier) null, (EnterTransition) null, (ExitTransition) null, (String) null, ComposableLambdaKt.composableLambda(composerStartRestartGroup, -775567521, true, new ChipKt$ChipContent$1$1(pVar3)), composerStartRestartGroup, 196608, 30);
            composer2 = composerStartRestartGroup;
            AnimatedVisibilityKt.AnimatedVisibility(pVar2 != null, (Modifier) null, (EnterTransition) null, (ExitTransition) null, (String) null, ComposableLambdaKt.composableLambda(composer2, 1201881928, true, new ChipKt$ChipContent$1$2(pVar2)), composer2, 196608, 30);
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>) TextKt.f5779a.provides(textStyle), pVar, composer2, ((i12 << 3) & 112) | ProvidedValue.$stable);
            composer2.startReplaceableGroup(1568571999);
            if (pVar4 != null) {
                SpacerKt.Spacer(SizeKt.m583width3ABfNKs(companion, f5592a), composer2, 6);
                pVar4.invoke(composer2, 0);
            }
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            composer2.endNode();
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new u0(pVar, textStyle, pVar2, pVar3, pVar4, f10, paddingValues, i10));
        }
    }

    /* JADX INFO: renamed from: ClickableChip-u0RnIRE, reason: not valid java name */
    private static final void m5984ClickableChipu0RnIRE(Modifier modifier, r7.a<x6.t0> aVar, r7.a<x6.t0> aVar2, boolean z, r7.p<? super Composer, ? super Integer, x6.t0> pVar, TextStyle textStyle, r7.p<? super Composer, ? super Integer, x6.t0> pVar2, r7.p<? super Composer, ? super Integer, x6.t0> pVar3, b1 b1Var, y0 y0Var, a1 a1Var, x0 x0Var, z0 z0Var, float f10, PaddingValues paddingValues, MutableInteractionSource mutableInteractionSource, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(1820475454);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1820475454, i10, i11, "androidx.tv.material3.ClickableChip (Chip.kt:359)");
        }
        Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifier, false, v0.f6465i, 1, null);
        g1 g1Var = new g1(b1Var.f5820a, b1Var.f5821b, b1Var.f5822c, b1Var.f5823d, b1Var.f5824e);
        d1 d1Var = new d1(y0Var.f6543a, y0Var.f6544b, y0Var.f6545c, y0Var.f6546d, y0Var.f6547e, y0Var.f6548f, y0Var.f6549g, y0Var.f6550h);
        a1Var.getClass();
        SurfaceKt.m6007Surface05tvjtU(aVar, modifierSemantics$default, aVar2, z, 0.0f, g1Var, d1Var, new f1(1.1f), new c1(x0Var.f6503a, x0Var.f6504b, x0Var.f6505c, x0Var.f6506d, x0Var.f6507e), new e1(z0Var.f6581a, z0Var.f6582b, z0Var.f6583c), mutableInteractionSource, ComposableLambdaKt.composableLambda(composer, 278594973, true, new ChipKt$ClickableChip$2(pVar, textStyle, pVar2, pVar3, f10, paddingValues)), composer, ((i10 >> 3) & 14) | (i10 & 896) | (i10 & 7168), ((i11 >> 15) & 14) | 48, 16);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
    }

    public static final void FilterChip(boolean z, r7.a<x6.t0> aVar, Modifier modifier, boolean z5, r7.a<x6.t0> aVar2, r7.p<? super Composer, ? super Integer, x6.t0> pVar, r7.p<? super Composer, ? super Integer, x6.t0> pVar2, w2 w2Var, t2 t2Var, v2 v2Var, s2 s2Var, u2 u2Var, MutableInteractionSource mutableInteractionSource, r7.p<? super Composer, ? super Integer, x6.t0> pVar3, Composer composer, int i10, int i11, int i12) {
        w2 w2Var2;
        v2 v2Var2;
        Composer composer2;
        t2 t2Var2;
        int i13;
        s2 s2VarBorder;
        u2 u2Var2;
        int i14;
        int i15;
        composer.startReplaceableGroup(1280878755);
        Modifier modifier2 = (i12 & 4) != 0 ? Modifier.INSTANCE : modifier;
        boolean z10 = (i12 & 8) != 0 ? true : z5;
        r7.a<x6.t0> aVar3 = (i12 & 16) != 0 ? null : aVar2;
        r7.p<? super Composer, ? super Integer, x6.t0> pVar4 = (i12 & 32) != 0 ? null : pVar;
        r7.p<? super Composer, ? super Integer, x6.t0> pVar5 = (i12 & 64) != 0 ? null : pVar2;
        if ((i12 & 128) != 0) {
            RoundedCornerShape roundedCornerShape = FilterChipDefaults.f5628c;
            w2Var2 = new w2(roundedCornerShape, roundedCornerShape, roundedCornerShape, roundedCornerShape, roundedCornerShape, roundedCornerShape, roundedCornerShape, roundedCornerShape, roundedCornerShape, roundedCornerShape);
        } else {
            w2Var2 = w2Var;
        }
        t2 t2VarM5988colorsu3YEpmA = (i12 & 256) != 0 ? FilterChipDefaults.f5626a.m5988colorsu3YEpmA(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composer, 0, 24576, 16383) : t2Var;
        if ((i12 & 512) != 0) {
            FilterChipDefaults filterChipDefaults = FilterChipDefaults.f5626a;
            v2Var2 = new v2();
        } else {
            v2Var2 = v2Var;
        }
        if ((i12 & 1024) != 0) {
            composer2 = composer;
            t2Var2 = t2VarM5988colorsu3YEpmA;
            i13 = i12;
            s2VarBorder = FilterChipDefaults.f5626a.border(null, null, null, null, null, null, null, null, null, null, composer2, 0, 6, AnalyticsListener.EVENT_DRM_KEYS_LOADED);
        } else {
            composer2 = composer;
            t2Var2 = t2VarM5988colorsu3YEpmA;
            i13 = i12;
            s2VarBorder = s2Var;
        }
        if ((i13 & 2048) != 0) {
            FilterChipDefaults filterChipDefaults2 = FilterChipDefaults.f5626a;
            s1 s1Var = s1.f6376c;
            u2Var2 = new u2(s1Var, s1Var, s1Var, s1Var, s1Var, s1Var);
        } else {
            u2Var2 = u2Var;
        }
        MutableInteractionSource mutableInteractionSource2 = (i13 & 4096) != 0 ? null : mutableInteractionSource;
        if (ComposerKt.isTraceInProgress()) {
            i14 = i10;
            i15 = i11;
            ComposerKt.traceEventStart(1280878755, i14, i15, "androidx.tv.material3.FilterChip (Chip.kt:169)");
        } else {
            i14 = i10;
            i15 = i11;
        }
        TextStyle textStyle = MaterialTheme.f5670a.getTypography(composer2, 6).f6310m;
        float f10 = FilterChipDefaults.f5627b;
        int i16 = i14 >> 3;
        int i17 = i14 << 3;
        int i18 = (i14 & 14) | 100663296 | (i16 & 112) | (i17 & 896) | (i16 & 7168) | (i17 & 57344) | ((i15 << 6) & 458752) | ((i14 << 6) & 29360128) | ((i14 << 9) & 1879048192);
        int i19 = i14 >> 21;
        int i20 = i15 << 9;
        int i21 = (i19 & 896) | (i19 & 14) | 196608 | (i19 & 112) | (i20 & 7168) | (i20 & 57344) | ((i15 << 15) & 29360128);
        r7.a<x6.t0> aVar4 = aVar3;
        m5985SelectableChiplguZlCk(z, modifier2, aVar, aVar4, z10, pVar3, textStyle, pVar4, null, pVar5, w2Var2, t2Var2, v2Var2, s2VarBorder, u2Var2, f10, b(false, pVar4 != null, pVar5 != null), mutableInteractionSource2, composer2, i18, i21);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
    }

    public static final void InputChip(boolean z, r7.a<x6.t0> aVar, Modifier modifier, boolean z5, r7.a<x6.t0> aVar2, r7.p<? super Composer, ? super Integer, x6.t0> pVar, r7.p<? super Composer, ? super Integer, x6.t0> pVar2, r7.p<? super Composer, ? super Integer, x6.t0> pVar3, w2 w2Var, t2 t2Var, v2 v2Var, s2 s2Var, u2 u2Var, MutableInteractionSource mutableInteractionSource, r7.p<? super Composer, ? super Integer, x6.t0> pVar4, Composer composer, int i10, int i11, int i12) {
        w2 w2Var2;
        int i13;
        t2 t2VarM5993colorsu3YEpmA;
        v2 v2Var2;
        Composer composer2;
        s2 s2VarBorder;
        u2 u2Var2;
        int i14;
        int i15;
        composer.startReplaceableGroup(-663200007);
        Modifier modifier2 = (i12 & 4) != 0 ? Modifier.INSTANCE : modifier;
        boolean z10 = (i12 & 8) != 0 ? true : z5;
        r7.a<x6.t0> aVar3 = (i12 & 16) != 0 ? null : aVar2;
        r7.p<? super Composer, ? super Integer, x6.t0> pVar5 = (i12 & 32) != 0 ? null : pVar;
        r7.p<? super Composer, ? super Integer, x6.t0> pVar6 = (i12 & 64) != 0 ? null : pVar2;
        r7.p<? super Composer, ? super Integer, x6.t0> pVar7 = (i12 & 128) != 0 ? null : pVar3;
        if ((i12 & 256) != 0) {
            InputChipDefaults inputChipDefaults = InputChipDefaults.f5636a;
            RoundedCornerShape roundedCornerShape = pVar6 != null ? InputChipDefaults.f5639d : InputChipDefaults.f5638c;
            w2Var2 = new w2(roundedCornerShape, roundedCornerShape, roundedCornerShape, roundedCornerShape, roundedCornerShape, roundedCornerShape, roundedCornerShape, roundedCornerShape, roundedCornerShape, roundedCornerShape);
        } else {
            w2Var2 = w2Var;
        }
        if ((i12 & 512) != 0) {
            i13 = i12;
            t2VarM5993colorsu3YEpmA = InputChipDefaults.f5636a.m5993colorsu3YEpmA(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composer, 0, 24576, 16383);
        } else {
            i13 = i12;
            t2VarM5993colorsu3YEpmA = t2Var;
        }
        if ((i13 & 1024) != 0) {
            InputChipDefaults inputChipDefaults2 = InputChipDefaults.f5636a;
            v2Var2 = new v2();
        } else {
            v2Var2 = v2Var;
        }
        if ((i13 & 2048) != 0) {
            composer2 = composer;
            s2VarBorder = InputChipDefaults.f5636a.border(pVar6 != null, null, null, null, null, null, null, null, null, null, null, composer2, 0, 48, 2046);
        } else {
            composer2 = composer;
            s2VarBorder = s2Var;
        }
        if ((i13 & 4096) != 0) {
            InputChipDefaults inputChipDefaults3 = InputChipDefaults.f5636a;
            s1 s1Var = s1.f6376c;
            u2Var2 = new u2(s1Var, s1Var, s1Var, s1Var, s1Var, s1Var);
        } else {
            u2Var2 = u2Var;
        }
        MutableInteractionSource mutableInteractionSource2 = (i13 & 8192) == 0 ? mutableInteractionSource : null;
        if (ComposerKt.isTraceInProgress()) {
            i14 = i10;
            i15 = i11;
            ComposerKt.traceEventStart(-663200007, i14, i15, "androidx.tv.material3.InputChip (Chip.kt:247)");
        } else {
            i14 = i10;
            i15 = i11;
        }
        TextStyle textStyle = MaterialTheme.f5670a.getTypography(composer2, 6).f6310m;
        float f10 = InputChipDefaults.f5637b;
        int i16 = i14 >> 3;
        int i17 = i14 << 3;
        int i18 = (i14 & 14) | (i16 & 112) | (i17 & 896) | (i16 & 7168) | (i17 & 57344) | (458752 & (i15 << 3));
        int i19 = i14 << 6;
        int i20 = i18 | (i19 & 29360128) | (234881024 & i19) | (i19 & 1879048192);
        int i21 = i14 >> 24;
        int i22 = i15 << 6;
        Composer composer3 = composer2;
        s2 s2Var2 = s2VarBorder;
        Modifier modifier3 = modifier2;
        m5985SelectableChiplguZlCk(z, modifier3, aVar, aVar3, z10, pVar4, textStyle, pVar5, pVar6, pVar7, w2Var2, t2VarM5993colorsu3YEpmA, v2Var2, s2Var2, u2Var2, f10, b(pVar6 != null, pVar5 != null, pVar7 != null), mutableInteractionSource2, composer3, i20, (i21 & 112) | (i21 & 14) | 196608 | (i22 & 896) | (i22 & 7168) | (i22 & 57344) | ((i15 << 12) & 29360128));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
    }

    /* JADX INFO: renamed from: SelectableChip-lguZlCk, reason: not valid java name */
    private static final void m5985SelectableChiplguZlCk(boolean z, Modifier modifier, r7.a<x6.t0> aVar, r7.a<x6.t0> aVar2, boolean z5, r7.p<? super Composer, ? super Integer, x6.t0> pVar, TextStyle textStyle, r7.p<? super Composer, ? super Integer, x6.t0> pVar2, r7.p<? super Composer, ? super Integer, x6.t0> pVar3, r7.p<? super Composer, ? super Integer, x6.t0> pVar4, w2 w2Var, t2 t2Var, v2 v2Var, s2 s2Var, u2 u2Var, float f10, PaddingValues paddingValues, MutableInteractionSource mutableInteractionSource, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(732077748);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(732077748, i10, i11, "androidx.tv.material3.SelectableChip (Chip.kt:406)");
        }
        Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifier, false, w0.f6472i, 1, null);
        b3 b3Var = new b3(w2Var.f6481a, w2Var.f6482b, w2Var.f6483c, w2Var.f6484d, w2Var.f6485e, w2Var.f6486f, w2Var.f6487g, w2Var.f6488h, w2Var.f6489i, w2Var.f6490j);
        y2 y2Var = new y2(t2Var.f6411a, t2Var.f6412b, t2Var.f6413c, t2Var.f6414d, t2Var.f6415e, t2Var.f6416f, t2Var.f6417g, t2Var.f6418h, t2Var.f6419i, t2Var.f6420j, t2Var.k, t2Var.f6421l, t2Var.f6422m, t2Var.f6423n);
        v2Var.getClass();
        int i12 = i10 >> 3;
        SurfaceKt.m6009SurfacexYaah8o(z, aVar, modifierSemantics$default, z5, aVar2, 0.0f, b3Var, y2Var, new a3(1.1f, 1.1f), new x2(s2Var.f6379a, s2Var.f6380b, s2Var.f6381c, s2Var.f6382d, s2Var.f6383e, s2Var.f6384f, s2Var.f6385g, s2Var.f6386h, s2Var.f6387i, s2Var.f6388j), new z2(u2Var.f6440a, u2Var.f6441b, u2Var.f6442c, u2Var.f6443d, u2Var.f6444e, u2Var.f6445f), mutableInteractionSource, ComposableLambdaKt.composableLambda(composer, 1191486420, true, new ChipKt$SelectableChip$2(pVar, textStyle, pVar2, pVar3, pVar4, f10, paddingValues)), composer, (i10 & 14) | (i12 & 112) | (i12 & 7168) | (57344 & (i10 << 3)), ((i11 >> 18) & 112) | RendererCapabilities.DECODER_SUPPORT_MASK, 32);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
    }

    public static final void SuggestionChip(r7.a<x6.t0> aVar, Modifier modifier, boolean z, r7.a<x6.t0> aVar2, b1 b1Var, y0 y0Var, a1 a1Var, x0 x0Var, z0 z0Var, MutableInteractionSource mutableInteractionSource, r7.p<? super Composer, ? super Integer, x6.t0> pVar, Composer composer, int i10, int i11, int i12) {
        b1 b1Var2;
        a1 a1Var2;
        Composer composer2;
        int i13;
        int i14;
        int i15;
        x0 x0VarBorder;
        z0 z0Var2;
        composer.startReplaceableGroup(-552548203);
        Modifier modifier2 = (i12 & 2) != 0 ? Modifier.INSTANCE : modifier;
        boolean z5 = (i12 & 4) != 0 ? true : z;
        r7.a<x6.t0> aVar3 = (i12 & 8) != 0 ? null : aVar2;
        if ((i12 & 16) != 0) {
            RoundedCornerShape roundedCornerShape = SuggestionChipDefaults.f5713c;
            b1Var2 = new b1(roundedCornerShape, roundedCornerShape, roundedCornerShape, roundedCornerShape, roundedCornerShape);
        } else {
            b1Var2 = b1Var;
        }
        y0 y0VarM6005colorsoq7We08 = (i12 & 32) != 0 ? SuggestionChipDefaults.f5711a.m6005colorsoq7We08(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, composer, 100663296, 255) : y0Var;
        if ((i12 & 64) != 0) {
            SuggestionChipDefaults suggestionChipDefaults = SuggestionChipDefaults.f5711a;
            a1Var2 = new a1();
        } else {
            a1Var2 = a1Var;
        }
        if ((i12 & 128) != 0) {
            composer2 = composer;
            i13 = i10;
            i14 = i11;
            i15 = i12;
            x0VarBorder = SuggestionChipDefaults.f5711a.border(null, null, null, null, null, composer2, 196608, 31);
        } else {
            composer2 = composer;
            i13 = i10;
            i14 = i11;
            i15 = i12;
            x0VarBorder = x0Var;
        }
        if ((i15 & 256) != 0) {
            SuggestionChipDefaults suggestionChipDefaults2 = SuggestionChipDefaults.f5711a;
            s1 s1Var = s1.f6376c;
            z0Var2 = new z0(s1Var, s1Var, s1Var);
        } else {
            z0Var2 = z0Var;
        }
        MutableInteractionSource mutableInteractionSource2 = (i15 & 512) != 0 ? null : mutableInteractionSource;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-552548203, i13, i14, "androidx.tv.material3.SuggestionChip (Chip.kt:317)");
        }
        int i16 = i13 >> 3;
        int i17 = i13 << 3;
        int i18 = i13 << 12;
        int i19 = (i16 & 896) | (i16 & 14) | 14155776 | (i17 & 112) | (i17 & 7168) | (57344 & (i14 << 12)) | (234881024 & i18) | (i18 & 1879048192);
        int i20 = i13 >> 18;
        int i21 = (i20 & 896) | (i20 & 14) | 3072 | (i20 & 112) | (458752 & (i13 >> 12));
        z0 z0Var3 = z0Var2;
        r7.a<x6.t0> aVar4 = aVar3;
        m5984ClickableChipu0RnIRE(modifier2, aVar, aVar4, z5, pVar, MaterialTheme.f5670a.getTypography(composer2, 6).f6310m, null, null, b1Var2, y0VarM6005colorsoq7We08, a1Var2, x0VarBorder, z0Var3, SuggestionChipDefaults.f5712b, b(false, false, false), mutableInteractionSource2, composer, i19, i21);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
    }

    public static final PaddingValues b(boolean z, boolean z5, boolean z10) {
        float fM5678constructorimpl = Dp.m5678constructorimpl(z ? 4 : z5 ? 12 : 16);
        float fM5678constructorimpl2 = Dp.m5678constructorimpl(z10 ? 12 : 16);
        float fM5678constructorimpl3 = Dp.m5678constructorimpl(z ? 4 : 8);
        return PaddingKt.m525PaddingValuesa9UjIt4(fM5678constructorimpl, fM5678constructorimpl3, fM5678constructorimpl2, fM5678constructorimpl3);
    }
}

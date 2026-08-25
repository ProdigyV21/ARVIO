package androidx.compose.material3;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.analytics.AnalyticsListener;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import r7.l;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\u001am\u0010\u0012\u001a\u00020\r2\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0011\u0010\u000f\u001a\r\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\b\u000eH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u008f\u0001\u0010\u0012\u001a\u00020\r2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\f2\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u0017\u001a\u00020\u00162\u0011\u0010\u000f\u001a\r\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\b\u000eH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u0097\u0001\u0010\u0012\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u00142\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\f2\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u0017\u001a\u00020\u00162\u0011\u0010\u000f\u001a\r\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\b\u000eH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001a\u009d\u0001\u0010\u0012\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u00142\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\r0\u001e2\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u0017\u001a\u00020\u00162\u0011\u0010\u000f\u001a\r\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\b\u000eH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010 \u001a8\u0010%\u001a\u00020\u0000*\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010!\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\t\u001a\u00020\"H\u0003ø\u0001\u0000¢\u0006\u0004\b#\u0010$\u001a\"\u0010)\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010&\u001a\u00020\u0007H\u0003ø\u0001\u0000¢\u0006\u0004\b'\u0010(\"\u001d\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00070*8\u0006¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006/"}, d2 = {"Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/ui/graphics/Shape;", "shape", "Landroidx/compose/ui/graphics/Color;", TtmlNode.ATTR_TTS_COLOR, "contentColor", "Landroidx/compose/ui/unit/Dp;", "tonalElevation", "shadowElevation", "Landroidx/compose/foundation/BorderStroke;", "border", "Lkotlin/Function0;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "content", "Surface-T9BRK9s", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJFFLandroidx/compose/foundation/BorderStroke;Lr7/p;Landroidx/compose/runtime/Composer;II)V", "Surface", "onClick", "", "enabled", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "Surface-o_FOJdg", "(Lr7/a;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;JJFFLandroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/p;Landroidx/compose/runtime/Composer;III)V", "selected", "Surface-d85dljk", "(ZLr7/a;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;JJFFLandroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/p;Landroidx/compose/runtime/Composer;III)V", "checked", "Lkotlin/Function1;", "onCheckedChange", "(ZLr7/l;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/graphics/Shape;JJFFLandroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lr7/p;Landroidx/compose/runtime/Composer;III)V", TtmlNode.ATTR_TTS_BACKGROUND_COLOR, "", "surface-XO-JAsU", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JLandroidx/compose/foundation/BorderStroke;F)Landroidx/compose/ui/Modifier;", "surface", "elevation", "surfaceColorAtElevation-CLU3JFs", "(JFLandroidx/compose/runtime/Composer;I)J", "surfaceColorAtElevation", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "LocalAbsoluteTonalElevation", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "getLocalAbsoluteTonalElevation", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SurfaceKt {
    private static final ProvidableCompositionLocal<Dp> LocalAbsoluteTonalElevation = CompositionLocalKt.compositionLocalOf$default(null, SurfaceKt$LocalAbsoluteTonalElevation$1.INSTANCE, 1, null);

    /* JADX INFO: renamed from: Surface-T9BRK9s, reason: not valid java name */
    public static final void m2004SurfaceT9BRK9s(Modifier modifier, Shape shape, long j10, long j11, float f10, float f11, BorderStroke borderStroke, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10, int i11) {
        composer.startReplaceableGroup(-513881741);
        if ((i11 & 1) != 0) {
            modifier = Modifier.INSTANCE;
        }
        if ((i11 & 2) != 0) {
            shape = RectangleShapeKt.getRectangleShape();
        }
        if ((i11 & 4) != 0) {
            j10 = MaterialTheme.INSTANCE.getColorScheme(composer, 6).getSurface();
        }
        if ((i11 & 8) != 0) {
            j11 = ColorSchemeKt.m1394contentColorForek8zF_U(j10, composer, (i10 >> 6) & 14);
        }
        if ((i11 & 16) != 0) {
            f10 = Dp.m5678constructorimpl(0);
        }
        if ((i11 & 32) != 0) {
            f11 = Dp.m5678constructorimpl(0);
        }
        if ((i11 & 64) != 0) {
            borderStroke = null;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-513881741, i10, -1, "androidx.compose.material3.Surface (Surface.kt:109)");
        }
        ProvidableCompositionLocal<Dp> providableCompositionLocal = LocalAbsoluteTonalElevation;
        float fM5678constructorimpl = Dp.m5678constructorimpl(((Dp) composer.consume(providableCompositionLocal)).m5692unboximpl() + f10);
        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m3462boximpl(j11)), providableCompositionLocal.provides(Dp.m5676boximpl(fM5678constructorimpl))}, ComposableLambdaKt.composableLambda(composer, -70914509, true, new SurfaceKt$Surface$1(modifier, shape, j10, fM5678constructorimpl, borderStroke, f11, pVar)), composer, 48);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: Surface-d85dljk, reason: not valid java name */
    public static final void m2005Surfaced85dljk(boolean z, r7.a<t0> aVar, Modifier modifier, boolean z5, Shape shape, long j10, long j11, float f10, float f11, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10, int i11, int i12) {
        MutableInteractionSource mutableInteractionSource2;
        char c10;
        composer.startReplaceableGroup(540296512);
        Modifier modifier2 = (i12 & 4) != 0 ? Modifier.INSTANCE : modifier;
        boolean z10 = (i12 & 8) != 0 ? true : z5;
        Shape rectangleShape = (i12 & 16) != 0 ? RectangleShapeKt.getRectangleShape() : shape;
        long surface = (i12 & 32) != 0 ? MaterialTheme.INSTANCE.getColorScheme(composer, 6).getSurface() : j10;
        long jM1394contentColorForek8zF_U = (i12 & 64) != 0 ? ColorSchemeKt.m1394contentColorForek8zF_U(surface, composer, (i10 >> 15) & 14) : j11;
        float fM5678constructorimpl = (i12 & 128) != 0 ? Dp.m5678constructorimpl(0) : f10;
        float fM5678constructorimpl2 = (i12 & 256) != 0 ? Dp.m5678constructorimpl(0) : f11;
        BorderStroke borderStroke2 = (i12 & 512) != 0 ? null : borderStroke;
        if ((i12 & 1024) != 0) {
            composer.startReplaceableGroup(-746935250);
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                composer.updateRememberedValue(objRememberedValue);
            }
            mutableInteractionSource2 = (MutableInteractionSource) objRememberedValue;
            composer.endReplaceableGroup();
        } else {
            mutableInteractionSource2 = mutableInteractionSource;
        }
        if (ComposerKt.isTraceInProgress()) {
            c10 = 1;
            ComposerKt.traceEventStart(540296512, i10, i11, "androidx.compose.material3.Surface (Surface.kt:325)");
        } else {
            c10 = 1;
        }
        ProvidableCompositionLocal<Dp> providableCompositionLocal = LocalAbsoluteTonalElevation;
        float fM5678constructorimpl3 = Dp.m5678constructorimpl(((Dp) composer.consume(providableCompositionLocal)).m5692unboximpl() + fM5678constructorimpl);
        ProvidedValue<Color> providedValueProvides = ContentColorKt.getLocalContentColor().provides(Color.m3462boximpl(jM1394contentColorForek8zF_U));
        ProvidedValue<Dp> providedValueProvides2 = providableCompositionLocal.provides(Dp.m5676boximpl(fM5678constructorimpl3));
        ProvidedValue[] providedValueArr = new ProvidedValue[2];
        providedValueArr[0] = providedValueProvides;
        providedValueArr[c10] = providedValueProvides2;
        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) providedValueArr, ComposableLambdaKt.composableLambda(composer, -1164547968, c10, new SurfaceKt$Surface$5(modifier2, rectangleShape, surface, fM5678constructorimpl3, borderStroke2, z, mutableInteractionSource2, z10, aVar, fM5678constructorimpl2, pVar)), composer, 48);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: Surface-o_FOJdg, reason: not valid java name */
    public static final void m2007Surfaceo_FOJdg(r7.a<t0> aVar, Modifier modifier, boolean z, Shape shape, long j10, long j11, float f10, float f11, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10, int i11, int i12) {
        char c10;
        MutableInteractionSource mutableInteractionSource2;
        char c11;
        composer.startReplaceableGroup(-789752804);
        Modifier modifier2 = (i12 & 2) != 0 ? Modifier.INSTANCE : modifier;
        boolean z5 = (i12 & 4) != 0 ? true : z;
        Shape rectangleShape = (i12 & 8) != 0 ? RectangleShapeKt.getRectangleShape() : shape;
        long surface = (i12 & 16) != 0 ? MaterialTheme.INSTANCE.getColorScheme(composer, 6).getSurface() : j10;
        long jM1394contentColorForek8zF_U = (i12 & 32) != 0 ? ColorSchemeKt.m1394contentColorForek8zF_U(surface, composer, (i10 >> 12) & 14) : j11;
        float fM5678constructorimpl = (i12 & 64) != 0 ? Dp.m5678constructorimpl(0) : f10;
        float fM5678constructorimpl2 = (i12 & 128) != 0 ? Dp.m5678constructorimpl(0) : f11;
        BorderStroke borderStroke2 = (i12 & 256) != 0 ? null : borderStroke;
        if ((i12 & 512) != 0) {
            composer.startReplaceableGroup(-746940902);
            Object objRememberedValue = composer.rememberedValue();
            c10 = 1;
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                composer.updateRememberedValue(objRememberedValue);
            }
            mutableInteractionSource2 = (MutableInteractionSource) objRememberedValue;
            composer.endReplaceableGroup();
        } else {
            c10 = 1;
            mutableInteractionSource2 = mutableInteractionSource;
        }
        if (ComposerKt.isTraceInProgress()) {
            c11 = 0;
            ComposerKt.traceEventStart(-789752804, i10, i11, "androidx.compose.material3.Surface (Surface.kt:215)");
        } else {
            c11 = 0;
        }
        ProvidableCompositionLocal<Dp> providableCompositionLocal = LocalAbsoluteTonalElevation;
        float fM5678constructorimpl3 = Dp.m5678constructorimpl(((Dp) composer.consume(providableCompositionLocal)).m5692unboximpl() + fM5678constructorimpl);
        ProvidedValue<Color> providedValueProvides = ContentColorKt.getLocalContentColor().provides(Color.m3462boximpl(jM1394contentColorForek8zF_U));
        ProvidedValue<Dp> providedValueProvides2 = providableCompositionLocal.provides(Dp.m5676boximpl(fM5678constructorimpl3));
        ProvidedValue[] providedValueArr = new ProvidedValue[2];
        providedValueArr[c11] = providedValueProvides;
        providedValueArr[c10] = providedValueProvides2;
        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) providedValueArr, ComposableLambdaKt.composableLambda(composer, 1279702876, c10, new SurfaceKt$Surface$3(modifier2, rectangleShape, surface, fM5678constructorimpl3, borderStroke2, mutableInteractionSource2, z5, aVar, fM5678constructorimpl2, pVar)), composer, 48);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
    }

    public static final ProvidableCompositionLocal<Dp> getLocalAbsoluteTonalElevation() {
        return LocalAbsoluteTonalElevation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: surface-XO-JAsU, reason: not valid java name */
    public static final Modifier m2010surfaceXOJAsU(Modifier modifier, Shape shape, long j10, BorderStroke borderStroke, float f10) {
        return ClipKt.clip(BackgroundKt.m178backgroundbw27NRU(GraphicsLayerModifierKt.m3633graphicsLayerAp8cVGQ$default(modifier, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, f10, 0.0f, 0.0f, 0.0f, 0.0f, 0L, shape, false, null, 0L, 0L, 0, 124895, null).then(borderStroke != null ? BorderKt.border(Modifier.INSTANCE, borderStroke, shape) : Modifier.INSTANCE), j10, shape), shape);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: surfaceColorAtElevation-CLU3JFs, reason: not valid java name */
    public static final long m2011surfaceColorAtElevationCLU3JFs(long j10, float f10, Composer composer, int i10) {
        composer.startReplaceableGroup(-2079918090);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2079918090, i10, -1, "androidx.compose.material3.surfaceColorAtElevation (Surface.kt:483)");
        }
        long jM1392applyTonalElevationRFCenO8 = ColorSchemeKt.m1392applyTonalElevationRFCenO8(MaterialTheme.INSTANCE.getColorScheme(composer, 6), j10, f10, composer, (i10 << 3) & AnalyticsListener.EVENT_AUDIO_DECODER_INITIALIZED);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return jM1392applyTonalElevationRFCenO8;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: Surface-d85dljk, reason: not valid java name */
    public static final void m2006Surfaced85dljk(boolean z, l<? super Boolean, t0> lVar, Modifier modifier, boolean z5, Shape shape, long j10, long j11, float f10, float f11, BorderStroke borderStroke, MutableInteractionSource mutableInteractionSource, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10, int i11, int i12) {
        MutableInteractionSource mutableInteractionSource2;
        char c10;
        composer.startReplaceableGroup(-1877401889);
        Modifier modifier2 = (i12 & 4) != 0 ? Modifier.INSTANCE : modifier;
        boolean z10 = (i12 & 8) != 0 ? true : z5;
        Shape rectangleShape = (i12 & 16) != 0 ? RectangleShapeKt.getRectangleShape() : shape;
        long surface = (i12 & 32) != 0 ? MaterialTheme.INSTANCE.getColorScheme(composer, 6).getSurface() : j10;
        long jM1394contentColorForek8zF_U = (i12 & 64) != 0 ? ColorSchemeKt.m1394contentColorForek8zF_U(surface, composer, (i10 >> 15) & 14) : j11;
        float fM5678constructorimpl = (i12 & 128) != 0 ? Dp.m5678constructorimpl(0) : f10;
        float fM5678constructorimpl2 = (i12 & 256) != 0 ? Dp.m5678constructorimpl(0) : f11;
        BorderStroke borderStroke2 = (i12 & 512) != 0 ? null : borderStroke;
        if ((i12 & 1024) != 0) {
            composer.startReplaceableGroup(-746929488);
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                composer.updateRememberedValue(objRememberedValue);
            }
            mutableInteractionSource2 = (MutableInteractionSource) objRememberedValue;
            composer.endReplaceableGroup();
        } else {
            mutableInteractionSource2 = mutableInteractionSource;
        }
        if (ComposerKt.isTraceInProgress()) {
            c10 = 1;
            ComposerKt.traceEventStart(-1877401889, i10, i11, "androidx.compose.material3.Surface (Surface.kt:436)");
        } else {
            c10 = 1;
        }
        ProvidableCompositionLocal<Dp> providableCompositionLocal = LocalAbsoluteTonalElevation;
        float fM5678constructorimpl3 = Dp.m5678constructorimpl(((Dp) composer.consume(providableCompositionLocal)).m5692unboximpl() + fM5678constructorimpl);
        ProvidedValue<Color> providedValueProvides = ContentColorKt.getLocalContentColor().provides(Color.m3462boximpl(jM1394contentColorForek8zF_U));
        ProvidedValue<Dp> providedValueProvides2 = providableCompositionLocal.provides(Dp.m5676boximpl(fM5678constructorimpl3));
        ProvidedValue[] providedValueArr = new ProvidedValue[2];
        providedValueArr[0] = providedValueProvides;
        providedValueArr[c10] = providedValueProvides2;
        CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) providedValueArr, ComposableLambdaKt.composableLambda(composer, 712720927, c10, new SurfaceKt$Surface$7(modifier2, rectangleShape, surface, fM5678constructorimpl3, borderStroke2, z, mutableInteractionSource2, z10, lVar, fM5678constructorimpl2, pVar)), composer, 48);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
    }
}

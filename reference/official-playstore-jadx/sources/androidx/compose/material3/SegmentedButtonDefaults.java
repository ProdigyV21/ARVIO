package androidx.compose.material3;

import androidx.compose.animation.AnimatedVisibilityScope;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.CheckKt;
import androidx.compose.material3.tokens.OutlinedSegmentedButtonTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u008a\u0001\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\u00072\b\b\u0002\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\r\u001a\u00020\u00072\b\b\u0002\u0010\u000e\u001a\u00020\u00072\b\b\u0002\u0010\u000f\u001a\u00020\u00072\b\b\u0002\u0010\u0010\u001a\u00020\u00072\b\b\u0002\u0010\u0011\u001a\u00020\u00072\b\b\u0002\u0010\u0012\u001a\u00020\u00072\b\b\u0002\u0010\u0013\u001a\u00020\u0007H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J)\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\b\b\u0002\u0010\u001a\u001a\u00020\u0019H\u0007¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001f\u001a\u00020\u001eH\u0007¢\u0006\u0004\b\u001f\u0010 JC\u0010'\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020!2\u0013\b\u0002\u0010%\u001a\r\u0012\u0004\u0012\u00020\u001e0#¢\u0006\u0002\b$2\u0015\b\u0002\u0010&\u001a\u000f\u0012\u0004\u0012\u00020\u001e\u0018\u00010#¢\u0006\u0002\b$H\u0007¢\u0006\u0004\b'\u0010(J\"\u0010/\u001a\u00020,2\u0006\u0010)\u001a\u00020\u00072\b\b\u0002\u0010+\u001a\u00020*ø\u0001\u0000¢\u0006\u0004\b-\u0010.R\u001d\u00100\u001a\u00020*8\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b0\u00101\u001a\u0004\b2\u00103R\u001d\u00104\u001a\u00020*8\u0006ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\b4\u00101\u001a\u0004\b5\u00103R\u0018\u00109\u001a\u00020\u0004*\u0002068@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b7\u00108R\u0011\u0010\u001a\u001a\u00020\u00198G¢\u0006\u0006\u001a\u0004\b:\u0010;\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006<"}, d2 = {"Landroidx/compose/material3/SegmentedButtonDefaults;", "", "<init>", "()V", "Landroidx/compose/material3/SegmentedButtonColors;", "colors", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/SegmentedButtonColors;", "Landroidx/compose/ui/graphics/Color;", "activeContainerColor", "activeContentColor", "activeBorderColor", "inactiveContainerColor", "inactiveContentColor", "inactiveBorderColor", "disabledActiveContainerColor", "disabledActiveContentColor", "disabledActiveBorderColor", "disabledInactiveContainerColor", "disabledInactiveContentColor", "disabledInactiveBorderColor", "colors-XqyqHi0", "(JJJJJJJJJJJJLandroidx/compose/runtime/Composer;III)Landroidx/compose/material3/SegmentedButtonColors;", "", "index", "count", "Landroidx/compose/foundation/shape/CornerBasedShape;", "baseShape", "Landroidx/compose/ui/graphics/Shape;", "itemShape", "(IILandroidx/compose/foundation/shape/CornerBasedShape;Landroidx/compose/runtime/Composer;II)Landroidx/compose/ui/graphics/Shape;", "Lx6/t0;", "ActiveIcon", "(Landroidx/compose/runtime/Composer;I)V", "", "active", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "activeContent", "inactiveContent", "Icon", "(ZLr7/p;Lr7/p;Landroidx/compose/runtime/Composer;II)V", TtmlNode.ATTR_TTS_COLOR, "Landroidx/compose/ui/unit/Dp;", "width", "Landroidx/compose/foundation/BorderStroke;", "borderStroke-l07J4OM", "(JF)Landroidx/compose/foundation/BorderStroke;", "borderStroke", "BorderWidth", "F", "getBorderWidth-D9Ej5fM", "()F", "IconSize", "getIconSize-D9Ej5fM", "Landroidx/compose/material3/ColorScheme;", "getDefaultSegmentedButtonColors$material3_release", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/SegmentedButtonColors;", "defaultSegmentedButtonColors", "getBaseShape", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/shape/CornerBasedShape;", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class SegmentedButtonDefaults {
    public static final int $stable = 0;
    private static final float BorderWidth;
    public static final SegmentedButtonDefaults INSTANCE = new SegmentedButtonDefaults();
    private static final float IconSize;

    /* JADX INFO: renamed from: androidx.compose.material3.SegmentedButtonDefaults$ActiveIcon$1, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(int i10) {
            super(2);
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            SegmentedButtonDefaults.this.ActiveIcon(composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SegmentedButtonDefaults$Icon$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/animation/AnimatedVisibilityScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/animation/AnimatedVisibilityScope;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class C05351 extends r implements q<AnimatedVisibilityScope, Composer, Integer, t0> {
        final /* synthetic */ p<Composer, Integer, t0> $activeContent;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C05351(p<? super Composer, ? super Integer, t0> pVar) {
            super(3);
            this.$activeContent = pVar;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((AnimatedVisibilityScope) obj, (Composer) obj2, ((Number) obj3).intValue());
            return t0.f22605a;
        }

        public final void invoke(AnimatedVisibilityScope animatedVisibilityScope, Composer composer, int i10) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-750750819, i10, -1, "androidx.compose.material3.SegmentedButtonDefaults.Icon.<anonymous> (SegmentedButton.kt:585)");
            }
            if (a0.c.A(composer, 0, this.$activeContent)) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SegmentedButtonDefaults$Icon$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"", "it", "Lx6/t0;", "invoke", "(ZLandroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements q<Boolean, Composer, Integer, t0> {
        final /* synthetic */ p<Composer, Integer, t0> $activeContent;
        final /* synthetic */ p<Composer, Integer, t0> $inactiveContent;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2) {
            super(3);
            this.$activeContent = pVar;
            this.$inactiveContent = pVar2;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke(((Boolean) obj).booleanValue(), (Composer) obj2, ((Number) obj3).intValue());
            return t0.f22605a;
        }

        public final void invoke(boolean z, Composer composer, int i10) {
            p<Composer, Integer, t0> pVar;
            if ((i10 & 6) == 0) {
                i10 |= composer.changed(z) ? 4 : 2;
            }
            if ((i10 & 19) == 18 && composer.getSkipping()) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1364873619, i10, -1, "androidx.compose.material3.SegmentedButtonDefaults.Icon.<anonymous> (SegmentedButton.kt:589)");
            }
            if (z) {
                composer.startReplaceableGroup(-412602680);
                pVar = this.$activeContent;
            } else {
                composer.startReplaceableGroup(-412602659);
                pVar = this.$inactiveContent;
            }
            pVar.invoke(composer, 0);
            composer.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.SegmentedButtonDefaults$Icon$3, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ boolean $active;
        final /* synthetic */ p<Composer, Integer, t0> $activeContent;
        final /* synthetic */ p<Composer, Integer, t0> $inactiveContent;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(boolean z, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, int i10, int i11) {
            super(2);
            this.$active = z;
            this.$activeContent = pVar;
            this.$inactiveContent = pVar2;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            SegmentedButtonDefaults.this.Icon(this.$active, this.$activeContent, this.$inactiveContent, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    static {
        OutlinedSegmentedButtonTokens outlinedSegmentedButtonTokens = OutlinedSegmentedButtonTokens.INSTANCE;
        BorderWidth = outlinedSegmentedButtonTokens.m2698getOutlineWidthD9Ej5fM();
        IconSize = outlinedSegmentedButtonTokens.m2697getIconSizeD9Ej5fM();
    }

    private SegmentedButtonDefaults() {
    }

    /* JADX INFO: renamed from: borderStroke-l07J4OM$default, reason: not valid java name */
    public static /* synthetic */ BorderStroke m1840borderStrokel07J4OM$default(SegmentedButtonDefaults segmentedButtonDefaults, long j10, float f10, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            f10 = BorderWidth;
        }
        return segmentedButtonDefaults.m1841borderStrokel07J4OM(j10, f10);
    }

    public final void ActiveIcon(Composer composer, int i10) {
        int i11;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1273041460);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changed(this) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i11 & 3) == 2 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1273041460, i11, -1, "androidx.compose.material3.SegmentedButtonDefaults.ActiveIcon (SegmentedButton.kt:553)");
            }
            IconKt.m1610Iconww6aTOc(CheckKt.getCheck(Icons.Filled.INSTANCE), (String) null, SizeKt.m578size3ABfNKs(Modifier.INSTANCE, IconSize), 0L, composerStartRestartGroup, 48, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass1(i10));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:62:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void Icon(boolean r17, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r18, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r19, androidx.compose.runtime.Composer r20, int r21, int r22) {
        /*
            Method dump skipped, instruction units count: 290
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SegmentedButtonDefaults.Icon(boolean, r7.p, r7.p, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: renamed from: borderStroke-l07J4OM, reason: not valid java name */
    public final BorderStroke m1841borderStrokel07J4OM(long color, float width) {
        return BorderStrokeKt.m207BorderStrokecXLIe8U(width, color);
    }

    public final SegmentedButtonColors colors(Composer composer, int i10) {
        composer.startReplaceableGroup(679457321);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(679457321, i10, -1, "androidx.compose.material3.SegmentedButtonDefaults.colors (SegmentedButton.kt:438)");
        }
        SegmentedButtonColors defaultSegmentedButtonColors$material3_release = getDefaultSegmentedButtonColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return defaultSegmentedButtonColors$material3_release;
    }

    /* JADX INFO: renamed from: colors-XqyqHi0, reason: not valid java name */
    public final SegmentedButtonColors m1842colorsXqyqHi0(long j10, long j11, long j12, long j13, long j14, long j15, long j16, long j17, long j18, long j19, long j20, long j21, Composer composer, int i10, int i11, int i12) {
        composer.startReplaceableGroup(132526205);
        long jM3508getUnspecified0d7_KjU = (i12 & 1) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j10;
        long jM3508getUnspecified0d7_KjU2 = (i12 & 2) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j11;
        long jM3508getUnspecified0d7_KjU3 = (i12 & 4) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j12;
        long jM3508getUnspecified0d7_KjU4 = (i12 & 8) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j13;
        long jM3508getUnspecified0d7_KjU5 = (i12 & 16) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j14;
        long jM3508getUnspecified0d7_KjU6 = (i12 & 32) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j15;
        long jM3508getUnspecified0d7_KjU7 = (i12 & 64) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j16;
        long jM3508getUnspecified0d7_KjU8 = (i12 & 128) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j17;
        long jM3508getUnspecified0d7_KjU9 = (i12 & 256) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j18;
        long jM3508getUnspecified0d7_KjU10 = (i12 & 512) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j19;
        long jM3508getUnspecified0d7_KjU11 = (i12 & 1024) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j20;
        long jM3508getUnspecified0d7_KjU12 = (i12 & 2048) != 0 ? Color.INSTANCE.m3508getUnspecified0d7_KjU() : j21;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(132526205, i10, i11, "androidx.compose.material3.SegmentedButtonDefaults.colors (SegmentedButton.kt:474)");
        }
        SegmentedButtonColors segmentedButtonColorsM1827copy2qZNXz8 = getDefaultSegmentedButtonColors$material3_release(MaterialTheme.INSTANCE.getColorScheme(composer, 6)).m1827copy2qZNXz8(jM3508getUnspecified0d7_KjU, jM3508getUnspecified0d7_KjU2, jM3508getUnspecified0d7_KjU3, jM3508getUnspecified0d7_KjU4, jM3508getUnspecified0d7_KjU5, jM3508getUnspecified0d7_KjU6, jM3508getUnspecified0d7_KjU7, jM3508getUnspecified0d7_KjU8, jM3508getUnspecified0d7_KjU9, jM3508getUnspecified0d7_KjU10, jM3508getUnspecified0d7_KjU11, jM3508getUnspecified0d7_KjU12);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return segmentedButtonColorsM1827copy2qZNXz8;
    }

    public final CornerBasedShape getBaseShape(Composer composer, int i10) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1264240381, i10, -1, "androidx.compose.material3.SegmentedButtonDefaults.<get-baseShape> (SegmentedButton.kt:518)");
        }
        CornerBasedShape cornerBasedShape = (CornerBasedShape) ShapesKt.getValue(OutlinedSegmentedButtonTokens.INSTANCE.getShape(), composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return cornerBasedShape;
    }

    /* JADX INFO: renamed from: getBorderWidth-D9Ej5fM, reason: not valid java name */
    public final float m1843getBorderWidthD9Ej5fM() {
        return BorderWidth;
    }

    public final SegmentedButtonColors getDefaultSegmentedButtonColors$material3_release(ColorScheme colorScheme) {
        SegmentedButtonColors defaultSegmentedButtonColorsCached = colorScheme.getDefaultSegmentedButtonColorsCached();
        if (defaultSegmentedButtonColorsCached != null) {
            return defaultSegmentedButtonColorsCached;
        }
        OutlinedSegmentedButtonTokens outlinedSegmentedButtonTokens = OutlinedSegmentedButtonTokens.INSTANCE;
        SegmentedButtonColors segmentedButtonColors = new SegmentedButtonColors(ColorSchemeKt.fromToken(colorScheme, outlinedSegmentedButtonTokens.getSelectedContainerColor()), ColorSchemeKt.fromToken(colorScheme, outlinedSegmentedButtonTokens.getSelectedLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, outlinedSegmentedButtonTokens.getOutlineColor()), colorScheme.getSurface(), ColorSchemeKt.fromToken(colorScheme, outlinedSegmentedButtonTokens.getUnselectedLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, outlinedSegmentedButtonTokens.getOutlineColor()), ColorSchemeKt.fromToken(colorScheme, outlinedSegmentedButtonTokens.getSelectedContainerColor()), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, outlinedSegmentedButtonTokens.getDisabledLabelTextColor()), outlinedSegmentedButtonTokens.getDisabledLabelTextOpacity(), 0.0f, 0.0f, 0.0f, 14, null), Color.m3471copywmQWz5c$default(ColorSchemeKt.fromToken(colorScheme, outlinedSegmentedButtonTokens.getOutlineColor()), outlinedSegmentedButtonTokens.getDisabledOutlineOpacity(), 0.0f, 0.0f, 0.0f, 14, null), colorScheme.getSurface(), ColorSchemeKt.fromToken(colorScheme, outlinedSegmentedButtonTokens.getDisabledLabelTextColor()), ColorSchemeKt.fromToken(colorScheme, outlinedSegmentedButtonTokens.getOutlineColor()), null);
        colorScheme.setDefaultSegmentedButtonColorsCached$material3_release(segmentedButtonColors);
        return segmentedButtonColors;
    }

    /* JADX INFO: renamed from: getIconSize-D9Ej5fM, reason: not valid java name */
    public final float m1844getIconSizeD9Ej5fM() {
        return IconSize;
    }

    public final Shape itemShape(int i10, int i11, CornerBasedShape cornerBasedShape, Composer composer, int i12, int i13) {
        if ((i13 & 4) != 0) {
            cornerBasedShape = getBaseShape(composer, (i12 >> 9) & 14);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-942072063, i12, -1, "androidx.compose.material3.SegmentedButtonDefaults.itemShape (SegmentedButton.kt:534)");
        }
        if (i11 == 1) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            return cornerBasedShape;
        }
        Shape shapeStart = i10 == 0 ? ShapesKt.start(cornerBasedShape) : i10 == i11 - 1 ? ShapesKt.end(cornerBasedShape) : RectangleShapeKt.getRectangleShape();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return shapeStart;
    }
}

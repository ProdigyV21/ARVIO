package androidx.compose.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.material3.tokens.NavigationBarTokens;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.media3.exoplayer.RendererCapabilities;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u000f\u001ab\u0010\u0011\u001a\u00020\u000b2\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00072\u001c\u0010\u000e\u001a\u0018\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\f¢\u0006\u0002\b\rH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a\u0085\u0001\u0010\u001e\u001a\u00020\u000b*\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00122\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00142\u0011\u0010\u0016\u001a\r\u0012\u0004\u0012\u00020\u000b0\u0014¢\u0006\u0002\b\f2\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0017\u001a\u00020\u00122\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0014¢\u0006\u0002\b\f2\b\b\u0002\u0010\u0019\u001a\u00020\u00122\b\b\u0002\u0010\u001b\u001a\u00020\u001a2\b\b\u0002\u0010\u001d\u001a\u00020\u001cH\u0007¢\u0006\u0004\b\u001e\u0010\u001f\u001as\u0010$\u001a\u00020\u000b2\u0011\u0010 \u001a\r\u0012\u0004\u0012\u00020\u000b0\u0014¢\u0006\u0002\b\f2\u0011\u0010!\u001a\r\u0012\u0004\u0012\u00020\u000b0\u0014¢\u0006\u0002\b\f2\u0011\u0010\u0016\u001a\r\u0012\u0004\u0012\u00020\u000b0\u0014¢\u0006\u0002\b\f2\u0013\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0014¢\u0006\u0002\b\f2\u0006\u0010\u0019\u001a\u00020\u00122\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0\u0014H\u0003¢\u0006\u0004\b$\u0010%\u001a8\u00100\u001a\u00020-*\u00020&2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020'2\b\u0010*\u001a\u0004\u0018\u00010'2\u0006\u0010,\u001a\u00020+H\u0002ø\u0001\u0000¢\u0006\u0004\b.\u0010/\u001aP\u00104\u001a\u00020-*\u00020&2\u0006\u00101\u001a\u00020'2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00020'2\b\u0010*\u001a\u0004\u0018\u00010'2\u0006\u0010,\u001a\u00020+2\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\"H\u0002ø\u0001\u0000¢\u0006\u0004\b2\u00103\"\u0014\u00106\u001a\u0002058\u0002X\u0082T¢\u0006\u0006\n\u0004\b6\u00107\"\u0014\u00108\u001a\u0002058\u0002X\u0082T¢\u0006\u0006\n\u0004\b8\u00107\"\u0014\u00109\u001a\u0002058\u0002X\u0082T¢\u0006\u0006\n\u0004\b9\u00107\"\u0014\u0010:\u001a\u0002058\u0002X\u0082T¢\u0006\u0006\n\u0004\b:\u00107\"\u0014\u0010;\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b;\u0010<\"\u0014\u0010>\u001a\u00020=8\u0002X\u0082T¢\u0006\u0006\n\u0004\b>\u0010?\"\u001a\u0010@\u001a\u00020\u00058\u0000X\u0080\u0004¢\u0006\f\n\u0004\b@\u0010<\u001a\u0004\bA\u0010B\"\u001a\u0010C\u001a\u00020\u00058\u0000X\u0080\u0004¢\u0006\f\n\u0004\bC\u0010<\u001a\u0004\bD\u0010B\"\u0014\u0010E\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bE\u0010<\"\u001a\u0010F\u001a\u00020\u00058\u0000X\u0080\u0004¢\u0006\f\n\u0004\bF\u0010<\u001a\u0004\bG\u0010B\"\u0014\u0010H\u001a\u00020\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bH\u0010<\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006L²\u0006\f\u0010I\u001a\u00020\u00028\nX\u008a\u0084\u0002²\u0006\f\u0010J\u001a\u00020\u00028\nX\u008a\u0084\u0002²\u0006\u000e\u0010K\u001a\u00020=8\n@\nX\u008a\u008e\u0002"}, d2 = {"Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/ui/graphics/Color;", "containerColor", "contentColor", "Landroidx/compose/ui/unit/Dp;", "tonalElevation", "Landroidx/compose/foundation/layout/WindowInsets;", "windowInsets", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "content", "NavigationBar-HsRjFd4", "(Landroidx/compose/ui/Modifier;JJFLandroidx/compose/foundation/layout/WindowInsets;Lr7/q;Landroidx/compose/runtime/Composer;II)V", "NavigationBar", "", "selected", "Lkotlin/Function0;", "onClick", NavigationBarKt.IconLayoutIdTag, "enabled", NavigationBarKt.LabelLayoutIdTag, "alwaysShowLabel", "Landroidx/compose/material3/NavigationBarItemColors;", "colors", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "NavigationBarItem", "(Landroidx/compose/foundation/layout/RowScope;ZLr7/a;Lr7/p;Landroidx/compose/ui/Modifier;ZLr7/p;ZLandroidx/compose/material3/NavigationBarItemColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", NavigationBarKt.IndicatorRippleLayoutIdTag, NavigationBarKt.IndicatorLayoutIdTag, "", "animationProgress", "NavigationBarItemLayout", "(Lr7/p;Lr7/p;Lr7/p;Lr7/p;ZLr7/a;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Placeable;", "iconPlaceable", "indicatorRipplePlaceable", "indicatorPlaceable", "Landroidx/compose/ui/unit/Constraints;", "constraints", "Landroidx/compose/ui/layout/MeasureResult;", "placeIcon-X9ElhV4", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;J)Landroidx/compose/ui/layout/MeasureResult;", "placeIcon", "labelPlaceable", "placeLabelAndIcon-zUg2_y0", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;JZF)Landroidx/compose/ui/layout/MeasureResult;", "placeLabelAndIcon", "", "IndicatorRippleLayoutIdTag", "Ljava/lang/String;", "IndicatorLayoutIdTag", "IconLayoutIdTag", "LabelLayoutIdTag", "NavigationBarHeight", "F", "", "ItemAnimationDurationMillis", "I", "NavigationBarItemHorizontalPadding", "getNavigationBarItemHorizontalPadding", "()F", "NavigationBarIndicatorToLabelPadding", "getNavigationBarIndicatorToLabelPadding", "IndicatorHorizontalPadding", "IndicatorVerticalPadding", "getIndicatorVerticalPadding", "IndicatorVerticalOffset", "iconColor", "textColor", "itemWidth", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NavigationBarKt {
    private static final String IconLayoutIdTag = "icon";
    private static final float IndicatorHorizontalPadding;
    private static final String IndicatorLayoutIdTag = "indicator";
    private static final String IndicatorRippleLayoutIdTag = "indicatorRipple";
    private static final float IndicatorVerticalOffset;
    private static final float IndicatorVerticalPadding;
    private static final int ItemAnimationDurationMillis = 100;
    private static final String LabelLayoutIdTag = "label";
    private static final float NavigationBarHeight;
    private static final float NavigationBarIndicatorToLabelPadding;
    private static final float NavigationBarItemHorizontalPadding;

    /* JADX INFO: renamed from: androidx.compose.material3.NavigationBarKt$NavigationBarItem$4, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ boolean $alwaysShowLabel;
        final /* synthetic */ NavigationBarItemColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ p<Composer, Integer, t0> $icon;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ p<Composer, Integer, t0> $label;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ r7.a<t0> $onClick;
        final /* synthetic */ boolean $selected;
        final /* synthetic */ RowScope $this_NavigationBarItem;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass4(RowScope rowScope, boolean z, r7.a<t0> aVar, p<? super Composer, ? super Integer, t0> pVar, Modifier modifier, boolean z5, p<? super Composer, ? super Integer, t0> pVar2, boolean z10, NavigationBarItemColors navigationBarItemColors, MutableInteractionSource mutableInteractionSource, int i10, int i11) {
            super(2);
            this.$this_NavigationBarItem = rowScope;
            this.$selected = z;
            this.$onClick = aVar;
            this.$icon = pVar;
            this.$modifier = modifier;
            this.$enabled = z5;
            this.$label = pVar2;
            this.$alwaysShowLabel = z10;
            this.$colors = navigationBarItemColors;
            this.$interactionSource = mutableInteractionSource;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            NavigationBarKt.NavigationBarItem(this.$this_NavigationBarItem, this.$selected, this.$onClick, this.$icon, this.$modifier, this.$enabled, this.$label, this.$alwaysShowLabel, this.$colors, this.$interactionSource, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.NavigationBarKt$NavigationBarItemLayout$3, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ boolean $alwaysShowLabel;
        final /* synthetic */ r7.a<Float> $animationProgress;
        final /* synthetic */ p<Composer, Integer, t0> $icon;
        final /* synthetic */ p<Composer, Integer, t0> $indicator;
        final /* synthetic */ p<Composer, Integer, t0> $indicatorRipple;
        final /* synthetic */ p<Composer, Integer, t0> $label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, boolean z, r7.a<Float> aVar, int i10) {
            super(2);
            this.$indicatorRipple = pVar;
            this.$indicator = pVar2;
            this.$icon = pVar3;
            this.$label = pVar4;
            this.$alwaysShowLabel = z;
            this.$animationProgress = aVar;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            NavigationBarKt.NavigationBarItemLayout(this.$indicatorRipple, this.$indicator, this.$icon, this.$label, this.$alwaysShowLabel, this.$animationProgress, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    static {
        NavigationBarTokens navigationBarTokens = NavigationBarTokens.INSTANCE;
        NavigationBarHeight = navigationBarTokens.m2657getContainerHeightD9Ej5fM();
        NavigationBarItemHorizontalPadding = Dp.m5678constructorimpl(8);
        NavigationBarIndicatorToLabelPadding = Dp.m5678constructorimpl(4);
        float f10 = 2;
        IndicatorHorizontalPadding = Dp.m5678constructorimpl(Dp.m5678constructorimpl(navigationBarTokens.m2655getActiveIndicatorWidthD9Ej5fM() - navigationBarTokens.m2658getIconSizeD9Ej5fM()) / f10);
        IndicatorVerticalPadding = Dp.m5678constructorimpl(Dp.m5678constructorimpl(navigationBarTokens.m2654getActiveIndicatorHeightD9Ej5fM() - navigationBarTokens.m2658getIconSizeD9Ej5fM()) / f10);
        IndicatorVerticalOffset = Dp.m5678constructorimpl(12);
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0132  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0140  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0187  */
    /* JADX WARN: Removed duplicated region for block: B:115:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0100  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x011b  */
    /* JADX INFO: renamed from: NavigationBar-HsRjFd4, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1706NavigationBarHsRjFd4(androidx.compose.ui.Modifier r23, long r24, long r26, float r28, androidx.compose.foundation.layout.WindowInsets r29, r7.q<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r30, androidx.compose.runtime.Composer r31, int r32, int r33) {
        /*
            Method dump skipped, instruction units count: 402
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationBarKt.m1706NavigationBarHsRjFd4(androidx.compose.ui.Modifier, long, long, float, androidx.compose.foundation.layout.WindowInsets, r7.q, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0183  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x025b  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x02a0  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x02c1  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0341  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0343  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0356  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0393  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x03bb  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x03cc  */
    /* JADX WARN: Removed duplicated region for block: B:196:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0069  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00bb  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00f1  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0107  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void NavigationBarItem(androidx.compose.foundation.layout.RowScope r25, boolean r26, r7.a<x6.t0> r27, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r28, androidx.compose.ui.Modifier r29, boolean r30, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r31, boolean r32, androidx.compose.material3.NavigationBarItemColors r33, androidx.compose.foundation.interaction.MutableInteractionSource r34, androidx.compose.runtime.Composer r35, int r36, int r37) {
        /*
            Method dump skipped, instruction units count: 989
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationBarKt.NavigationBarItem(androidx.compose.foundation.layout.RowScope, boolean, r7.a, r7.p, androidx.compose.ui.Modifier, boolean, r7.p, boolean, androidx.compose.material3.NavigationBarItemColors, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void NavigationBarItemLayout(p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, boolean z, final r7.a<Float> aVar, Composer composer, int i10) {
        int i11;
        final p<? super Composer, ? super Integer, t0> pVar5 = pVar4;
        final boolean z5 = z;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1427075886);
        if ((i10 & 6) == 0) {
            i11 = (composerStartRestartGroup.changedInstance(pVar) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(pVar2) ? 32 : 16;
        }
        if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(pVar3) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(pVar5) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= composerStartRestartGroup.changed(z5) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            i11 |= composerStartRestartGroup.changedInstance(aVar) ? 131072 : 65536;
        }
        if ((74899 & i11) == 74898 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1427075886, i11, -1, "androidx.compose.material3.NavigationBarItemLayout (NavigationBar.kt:505)");
            }
            composerStartRestartGroup.startReplaceableGroup(-1250032035);
            int i12 = 458752 & i11;
            int i13 = 57344 & i11;
            boolean z10 = ((i11 & 7168) == 2048) | (i12 == 131072) | (i13 == 16384);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z10 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new MeasurePolicy() { // from class: androidx.compose.material3.NavigationBarKt$NavigationBarItemLayout$2$1
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i14) {
                        return androidx.compose.ui.layout.g.a(this, intrinsicMeasureScope, list, i14);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i14) {
                        return androidx.compose.ui.layout.g.b(this, intrinsicMeasureScope, list, i14);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo5measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j10) {
                        Measurable measurable;
                        Placeable placeableMo4631measureBRTryo0;
                        MeasureScope measureScope2 = measureScope;
                        float fFloatValue = ((Number) aVar.invoke()).floatValue();
                        long jM5637copyZbe2FdA$default = Constraints.m5637copyZbe2FdA$default(j10, 0, 0, 0, 0, 10, null);
                        int size = list.size();
                        int i14 = 0;
                        while (i14 < size) {
                            Measurable measurable2 = list.get(i14);
                            if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable2), "icon")) {
                                Placeable placeableMo4631measureBRTryo02 = measurable2.mo4631measureBRTryo0(jM5637copyZbe2FdA$default);
                                float f10 = 2;
                                int iMo279roundToPx0680j_4 = measureScope2.mo279roundToPx0680j_4(Dp.m5678constructorimpl(NavigationBarKt.IndicatorHorizontalPadding * f10)) + placeableMo4631measureBRTryo02.getWidth();
                                int iM = t7.a.M(iMo279roundToPx0680j_4 * fFloatValue);
                                int iMo279roundToPx0680j_42 = measureScope2.mo279roundToPx0680j_4(Dp.m5678constructorimpl(NavigationBarKt.getIndicatorVerticalPadding() * f10)) + placeableMo4631measureBRTryo02.getHeight();
                                int size2 = list.size();
                                int i15 = 0;
                                while (i15 < size2) {
                                    Measurable measurable3 = list.get(i15);
                                    if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable3), "indicatorRipple")) {
                                        Placeable placeableMo4631measureBRTryo03 = measurable3.mo4631measureBRTryo0(Constraints.INSTANCE.m5654fixedJhjzzOo(iMo279roundToPx0680j_4, iMo279roundToPx0680j_42));
                                        int size3 = list.size();
                                        int i16 = 0;
                                        while (true) {
                                            if (i16 >= size3) {
                                                measurable = null;
                                                break;
                                            }
                                            measurable = list.get(i16);
                                            if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable), "indicator")) {
                                                break;
                                            }
                                            i16++;
                                        }
                                        Measurable measurable4 = measurable;
                                        Placeable placeableMo4631measureBRTryo04 = measurable4 != null ? measurable4.mo4631measureBRTryo0(Constraints.INSTANCE.m5654fixedJhjzzOo(iM, iMo279roundToPx0680j_42)) : null;
                                        if (pVar5 != null) {
                                            int size4 = list.size();
                                            for (int i17 = 0; i17 < size4; i17++) {
                                                Measurable measurable5 = list.get(i17);
                                                if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable5), "label")) {
                                                    placeableMo4631measureBRTryo0 = measurable5.mo4631measureBRTryo0(jM5637copyZbe2FdA$default);
                                                }
                                            }
                                            throw new NoSuchElementException("Collection contains no element matching the predicate.");
                                        }
                                        placeableMo4631measureBRTryo0 = null;
                                        if (pVar5 == null) {
                                            return NavigationBarKt.m1709placeIconX9ElhV4(measureScope2, placeableMo4631measureBRTryo02, placeableMo4631measureBRTryo03, placeableMo4631measureBRTryo04, j10);
                                        }
                                        return NavigationBarKt.m1710placeLabelAndIconzUg2_y0(measureScope, placeableMo4631measureBRTryo0, placeableMo4631measureBRTryo02, placeableMo4631measureBRTryo03, placeableMo4631measureBRTryo04, j10, z5, fFloatValue);
                                    }
                                    i15++;
                                    measureScope2 = measureScope;
                                }
                                throw new NoSuchElementException("Collection contains no element matching the predicate.");
                            }
                            i14++;
                            measureScope2 = measureScope;
                        }
                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i14) {
                        return androidx.compose.ui.layout.g.c(this, intrinsicMeasureScope, list, i14);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i14) {
                        return androidx.compose.ui.layout.g.d(this, intrinsicMeasureScope, list, i14);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue;
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            Modifier.Companion companion = Modifier.INSTANCE;
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            int i14 = i11;
            r7.a<ComposeUiNode> constructor = companion2.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(companion);
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
            p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf, composerStartRestartGroup, 0, 2058660585);
            pVar.invoke(composerStartRestartGroup, Integer.valueOf(i14 & 14));
            pVar2.invoke(composerStartRestartGroup, Integer.valueOf((i14 >> 3) & 14));
            Modifier modifierLayoutId = LayoutIdKt.layoutId(companion, IconLayoutIdTag);
            composerStartRestartGroup.startReplaceableGroup(733328855);
            Alignment.Companion companion3 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyD = a0.c.d(companion3, false, composerStartRestartGroup, 0, -1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion2.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierLayoutId);
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor2);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2991constructorimpl2 = Updater.m2991constructorimpl(composerStartRestartGroup);
            p pVarU2 = a0.c.u(companion2, composerM2991constructorimpl2, measurePolicyD, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU2);
            }
            a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf2, composerStartRestartGroup, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            pVar3.invoke(composerStartRestartGroup, Integer.valueOf((i14 >> 6) & 14));
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.startReplaceableGroup(-1250032367);
            if (pVar4 != null) {
                Modifier modifierLayoutId2 = LayoutIdKt.layoutId(companion, LabelLayoutIdTag);
                composerStartRestartGroup.startReplaceableGroup(1836184900);
                boolean z11 = (i13 == 16384) | (i12 == 131072);
                Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z11 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    z5 = z;
                    objRememberedValue2 = new NavigationBarKt$NavigationBarItemLayout$1$2$1(z5, aVar);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    z5 = z;
                }
                composerStartRestartGroup.endReplaceableGroup();
                Modifier modifierM531paddingVpY3zN4$default = PaddingKt.m531paddingVpY3zN4$default(GraphicsLayerModifierKt.graphicsLayer(modifierLayoutId2, (l) objRememberedValue2), Dp.m5678constructorimpl(NavigationBarItemHorizontalPadding / 2), 0.0f, 2, null);
                composerStartRestartGroup.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyD2 = a0.c.d(companion3, false, composerStartRestartGroup, 0, -1323940314);
                int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor3 = companion2.getConstructor();
                q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierM531paddingVpY3zN4$default);
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor3);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM2991constructorimpl3 = Updater.m2991constructorimpl(composerStartRestartGroup);
                p pVarU3 = a0.c.u(companion2, composerM2991constructorimpl3, measurePolicyD2, composerM2991constructorimpl3, currentCompositionLocalMap3);
                if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                    a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU3);
                }
                a0.c.y(composerStartRestartGroup, qVarModifierMaterializerOf3, composerStartRestartGroup, 0, 2058660585);
                pVar5 = pVar4;
                pVar5.invoke(composerStartRestartGroup, Integer.valueOf((i14 >> 9) & 14));
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endNode();
                composerStartRestartGroup.endReplaceableGroup();
                composerStartRestartGroup.endReplaceableGroup();
            } else {
                pVar5 = pVar4;
                z5 = z;
            }
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass3(pVar, pVar2, pVar3, pVar5, z5, aVar, i10));
        }
    }

    public static final float getIndicatorVerticalPadding() {
        return IndicatorVerticalPadding;
    }

    public static final float getNavigationBarIndicatorToLabelPadding() {
        return NavigationBarIndicatorToLabelPadding;
    }

    public static final float getNavigationBarItemHorizontalPadding() {
        return NavigationBarItemHorizontalPadding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: placeIcon-X9ElhV4, reason: not valid java name */
    public static final MeasureResult m1709placeIconX9ElhV4(MeasureScope measureScope, Placeable placeable, Placeable placeable2, Placeable placeable3, long j10) {
        int iM5646getMaxWidthimpl = Constraints.m5646getMaxWidthimpl(j10);
        int iM5659constrainHeightK40F9xA = ConstraintsKt.m5659constrainHeightK40F9xA(j10, measureScope.mo279roundToPx0680j_4(NavigationBarHeight));
        return MeasureScope.CC.q(measureScope, iM5646getMaxWidthimpl, iM5659constrainHeightK40F9xA, null, new NavigationBarKt$placeIcon$1(placeable3, placeable, androidx.compose.foundation.c.B(placeable, iM5646getMaxWidthimpl, 2), androidx.compose.foundation.c.f(placeable, iM5659constrainHeightK40F9xA, 2), placeable2, androidx.compose.foundation.c.B(placeable2, iM5646getMaxWidthimpl, 2), androidx.compose.foundation.c.f(placeable2, iM5659constrainHeightK40F9xA, 2), iM5646getMaxWidthimpl, iM5659constrainHeightK40F9xA), 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: placeLabelAndIcon-zUg2_y0, reason: not valid java name */
    public static final MeasureResult m1710placeLabelAndIconzUg2_y0(MeasureScope measureScope, Placeable placeable, Placeable placeable2, Placeable placeable3, Placeable placeable4, long j10, boolean z, float f10) {
        float height = placeable2.getHeight();
        float f11 = IndicatorVerticalPadding;
        float fMo285toPx0680j_4 = measureScope.mo285toPx0680j_4(f11) + height;
        float f12 = NavigationBarIndicatorToLabelPadding;
        float fMo285toPx0680j_42 = measureScope.mo285toPx0680j_4(f12) + fMo285toPx0680j_4 + placeable.getHeight();
        float f13 = 2;
        float fM5647getMinHeightimpl = (Constraints.m5647getMinHeightimpl(j10) - fMo285toPx0680j_42) / f13;
        float fMo285toPx0680j_43 = measureScope.mo285toPx0680j_4(f11);
        float f14 = fM5647getMinHeightimpl < fMo285toPx0680j_43 ? fMo285toPx0680j_43 : fM5647getMinHeightimpl;
        float f15 = (f14 * f13) + fMo285toPx0680j_42;
        float height2 = (1 - f10) * ((z ? f14 : (f15 - placeable2.getHeight()) / f13) - f14);
        float fMo285toPx0680j_44 = measureScope.mo285toPx0680j_4(f12) + measureScope.mo285toPx0680j_4(f11) + placeable2.getHeight() + f14;
        int iM5646getMaxWidthimpl = Constraints.m5646getMaxWidthimpl(j10);
        return MeasureScope.CC.q(measureScope, iM5646getMaxWidthimpl, t7.a.M(f15), null, new NavigationBarKt$placeLabelAndIcon$1(placeable4, z, f10, placeable, androidx.compose.foundation.c.B(placeable, iM5646getMaxWidthimpl, 2), fMo285toPx0680j_44, height2, placeable2, androidx.compose.foundation.c.B(placeable2, iM5646getMaxWidthimpl, 2), f14, placeable3, androidx.compose.foundation.c.B(placeable3, iM5646getMaxWidthimpl, 2), f14 - measureScope.mo285toPx0680j_4(f11), iM5646getMaxWidthimpl, measureScope), 4, null);
    }
}

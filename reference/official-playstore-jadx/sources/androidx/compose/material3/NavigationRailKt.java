package androidx.compose.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.material3.tokens.NavigationRailTokens;
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
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000e\u001az\u0010\u0010\u001a\u00020\u00072\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0004\u001a\u00020\u00022 \b\u0002\u0010\n\u001a\u001a\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005¢\u0006\u0002\b\b¢\u0006\u0002\b\t2\b\b\u0002\u0010\f\u001a\u00020\u000b2\u001c\u0010\r\u001a\u0018\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\b¢\u0006\u0002\b\tH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0081\u0001\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00112\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u00132\u0011\u0010\u0015\u001a\r\u0012\u0004\u0012\u00020\u00070\u0013¢\u0006\u0002\b\b2\b\b\u0002\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0016\u001a\u00020\u00112\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0013¢\u0006\u0002\b\b2\b\b\u0002\u0010\u0018\u001a\u00020\u00112\b\b\u0002\u0010\u001a\u001a\u00020\u00192\b\b\u0002\u0010\u001c\u001a\u00020\u001bH\u0007¢\u0006\u0004\b\u001d\u0010\u001e\u001as\u0010#\u001a\u00020\u00072\u0011\u0010\u001f\u001a\r\u0012\u0004\u0012\u00020\u00070\u0013¢\u0006\u0002\b\b2\u0011\u0010 \u001a\r\u0012\u0004\u0012\u00020\u00070\u0013¢\u0006\u0002\b\b2\u0011\u0010\u0015\u001a\r\u0012\u0004\u0012\u00020\u00070\u0013¢\u0006\u0002\b\b2\u0013\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0013¢\u0006\u0002\b\b2\u0006\u0010\u0018\u001a\u00020\u00112\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020!0\u0013H\u0003¢\u0006\u0004\b#\u0010$\u001a8\u0010/\u001a\u00020,*\u00020%2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020&2\b\u0010)\u001a\u0004\u0018\u00010&2\u0006\u0010+\u001a\u00020*H\u0002ø\u0001\u0000¢\u0006\u0004\b-\u0010.\u001aP\u00103\u001a\u00020,*\u00020%2\u0006\u00100\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020&2\b\u0010)\u001a\u0004\u0018\u00010&2\u0006\u0010+\u001a\u00020*2\u0006\u0010\u0018\u001a\u00020\u00112\u0006\u0010\"\u001a\u00020!H\u0002ø\u0001\u0000¢\u0006\u0004\b1\u00102\"\u0014\u00105\u001a\u0002048\u0002X\u0082T¢\u0006\u0006\n\u0004\b5\u00106\"\u0014\u00107\u001a\u0002048\u0002X\u0082T¢\u0006\u0006\n\u0004\b7\u00106\"\u0014\u00108\u001a\u0002048\u0002X\u0082T¢\u0006\u0006\n\u0004\b8\u00106\"\u0014\u00109\u001a\u0002048\u0002X\u0082T¢\u0006\u0006\n\u0004\b9\u00106\"\u001a\u0010;\u001a\u00020:8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>\"\u0014\u0010?\u001a\u00020:8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u0010<\"\u0014\u0010A\u001a\u00020@8\u0002X\u0082T¢\u0006\u0006\n\u0004\bA\u0010B\"\u001a\u0010C\u001a\u00020:8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bC\u0010<\u001a\u0004\bD\u0010>\"\u001a\u0010E\u001a\u00020:8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bE\u0010<\u001a\u0004\bF\u0010>\"\u001a\u0010G\u001a\u00020:8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bG\u0010<\u001a\u0004\bH\u0010>\"\u0014\u0010I\u001a\u00020:8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bI\u0010<\"\u0014\u0010J\u001a\u00020:8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bJ\u0010<\"\u0014\u0010K\u001a\u00020:8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bK\u0010<\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006N²\u0006\f\u0010L\u001a\u00020\u00028\nX\u008a\u0084\u0002²\u0006\f\u0010M\u001a\u00020\u00028\nX\u008a\u0084\u0002"}, d2 = {"Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/ui/graphics/Color;", "containerColor", "contentColor", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Lx6/t0;", "Landroidx/compose/runtime/Composable;", "Lx6/n;", "header", "Landroidx/compose/foundation/layout/WindowInsets;", "windowInsets", "content", "NavigationRail-qi6gXK8", "(Landroidx/compose/ui/Modifier;JJLr7/q;Landroidx/compose/foundation/layout/WindowInsets;Lr7/q;Landroidx/compose/runtime/Composer;II)V", "NavigationRail", "", "selected", "Lkotlin/Function0;", "onClick", NavigationRailKt.IconLayoutIdTag, "enabled", NavigationRailKt.LabelLayoutIdTag, "alwaysShowLabel", "Landroidx/compose/material3/NavigationRailItemColors;", "colors", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "interactionSource", "NavigationRailItem", "(ZLr7/a;Lr7/p;Landroidx/compose/ui/Modifier;ZLr7/p;ZLandroidx/compose/material3/NavigationRailItemColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", NavigationRailKt.IndicatorRippleLayoutIdTag, NavigationRailKt.IndicatorLayoutIdTag, "", "animationProgress", "NavigationRailItemLayout", "(Lr7/p;Lr7/p;Lr7/p;Lr7/p;ZLr7/a;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Placeable;", "iconPlaceable", "indicatorRipplePlaceable", "indicatorPlaceable", "Landroidx/compose/ui/unit/Constraints;", "constraints", "Landroidx/compose/ui/layout/MeasureResult;", "placeIcon-X9ElhV4", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;J)Landroidx/compose/ui/layout/MeasureResult;", "placeIcon", "labelPlaceable", "placeLabelAndIcon-zUg2_y0", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;JZF)Landroidx/compose/ui/layout/MeasureResult;", "placeLabelAndIcon", "", "IndicatorRippleLayoutIdTag", "Ljava/lang/String;", "IndicatorLayoutIdTag", "IconLayoutIdTag", "LabelLayoutIdTag", "Landroidx/compose/ui/unit/Dp;", "NavigationRailVerticalPadding", "F", "getNavigationRailVerticalPadding", "()F", "NavigationRailHeaderPadding", "", "ItemAnimationDurationMillis", "I", "NavigationRailItemWidth", "getNavigationRailItemWidth", "NavigationRailItemHeight", "getNavigationRailItemHeight", "NavigationRailItemVerticalPadding", "getNavigationRailItemVerticalPadding", "IndicatorHorizontalPadding", "IndicatorVerticalPaddingWithLabel", "IndicatorVerticalPaddingNoLabel", "iconColor", "textColor", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class NavigationRailKt {
    private static final String IconLayoutIdTag = "icon";
    private static final float IndicatorHorizontalPadding;
    private static final String IndicatorLayoutIdTag = "indicator";
    private static final String IndicatorRippleLayoutIdTag = "indicatorRipple";
    private static final float IndicatorVerticalPaddingNoLabel;
    private static final float IndicatorVerticalPaddingWithLabel;
    private static final int ItemAnimationDurationMillis = 150;
    private static final String LabelLayoutIdTag = "label";
    private static final float NavigationRailHeaderPadding = Dp.m5678constructorimpl(8);
    private static final float NavigationRailItemHeight;
    private static final float NavigationRailItemVerticalPadding;
    private static final float NavigationRailItemWidth;
    private static final float NavigationRailVerticalPadding;

    /* JADX INFO: renamed from: androidx.compose.material3.NavigationRailKt$NavigationRailItem$3, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass3 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ boolean $alwaysShowLabel;
        final /* synthetic */ NavigationRailItemColors $colors;
        final /* synthetic */ boolean $enabled;
        final /* synthetic */ p<Composer, Integer, t0> $icon;
        final /* synthetic */ MutableInteractionSource $interactionSource;
        final /* synthetic */ p<Composer, Integer, t0> $label;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ r7.a<t0> $onClick;
        final /* synthetic */ boolean $selected;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(boolean z, r7.a<t0> aVar, p<? super Composer, ? super Integer, t0> pVar, Modifier modifier, boolean z5, p<? super Composer, ? super Integer, t0> pVar2, boolean z10, NavigationRailItemColors navigationRailItemColors, MutableInteractionSource mutableInteractionSource, int i10, int i11) {
            super(2);
            this.$selected = z;
            this.$onClick = aVar;
            this.$icon = pVar;
            this.$modifier = modifier;
            this.$enabled = z5;
            this.$label = pVar2;
            this.$alwaysShowLabel = z10;
            this.$colors = navigationRailItemColors;
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
            NavigationRailKt.NavigationRailItem(this.$selected, this.$onClick, this.$icon, this.$modifier, this.$enabled, this.$label, this.$alwaysShowLabel, this.$colors, this.$interactionSource, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.NavigationRailKt$NavigationRailItemLayout$3, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class C05303 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ boolean $alwaysShowLabel;
        final /* synthetic */ r7.a<Float> $animationProgress;
        final /* synthetic */ p<Composer, Integer, t0> $icon;
        final /* synthetic */ p<Composer, Integer, t0> $indicator;
        final /* synthetic */ p<Composer, Integer, t0> $indicatorRipple;
        final /* synthetic */ p<Composer, Integer, t0> $label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public C05303(p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, boolean z, r7.a<Float> aVar, int i10) {
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
            NavigationRailKt.NavigationRailItemLayout(this.$indicatorRipple, this.$indicator, this.$icon, this.$label, this.$alwaysShowLabel, this.$animationProgress, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    static {
        float f10 = 4;
        NavigationRailVerticalPadding = Dp.m5678constructorimpl(f10);
        NavigationRailTokens navigationRailTokens = NavigationRailTokens.INSTANCE;
        NavigationRailItemWidth = navigationRailTokens.m2668getContainerWidthD9Ej5fM();
        NavigationRailItemHeight = navigationRailTokens.m2671getNoLabelActiveIndicatorHeightD9Ej5fM();
        NavigationRailItemVerticalPadding = Dp.m5678constructorimpl(f10);
        float f11 = 2;
        IndicatorHorizontalPadding = Dp.m5678constructorimpl(Dp.m5678constructorimpl(navigationRailTokens.m2666getActiveIndicatorWidthD9Ej5fM() - navigationRailTokens.m2669getIconSizeD9Ej5fM()) / f11);
        IndicatorVerticalPaddingWithLabel = Dp.m5678constructorimpl(Dp.m5678constructorimpl(navigationRailTokens.m2665getActiveIndicatorHeightD9Ej5fM() - navigationRailTokens.m2669getIconSizeD9Ej5fM()) / f11);
        IndicatorVerticalPaddingNoLabel = Dp.m5678constructorimpl(Dp.m5678constructorimpl(navigationRailTokens.m2671getNoLabelActiveIndicatorHeightD9Ej5fM() - navigationRailTokens.m2669getIconSizeD9Ej5fM()) / f11);
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0129  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:115:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0118  */
    /* JADX INFO: renamed from: NavigationRail-qi6gXK8, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1738NavigationRailqi6gXK8(androidx.compose.ui.Modifier r23, long r24, long r26, r7.q<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r28, androidx.compose.foundation.layout.WindowInsets r29, r7.q<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r30, androidx.compose.runtime.Composer r31, int r32, int r33) {
        /*
            Method dump skipped, instruction units count: 387
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationRailKt.m1738NavigationRailqi6gXK8(androidx.compose.ui.Modifier, long, long, r7.q, androidx.compose.foundation.layout.WindowInsets, r7.q, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0113  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0191  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x01ba  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0236  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x023a  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0259  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x02c9  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x02de  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x02ee  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0301  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x0344  */
    /* JADX WARN: Removed duplicated region for block: B:178:0x036d  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x037d  */
    /* JADX WARN: Removed duplicated region for block: B:184:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0045  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x00f4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void NavigationRailItem(boolean r25, r7.a<x6.t0> r26, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r27, androidx.compose.ui.Modifier r28, boolean r29, r7.p<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, x6.t0> r30, boolean r31, androidx.compose.material3.NavigationRailItemColors r32, androidx.compose.foundation.interaction.MutableInteractionSource r33, androidx.compose.runtime.Composer r34, int r35, int r36) {
        /*
            Method dump skipped, instruction units count: 910
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationRailKt.NavigationRailItem(boolean, r7.a, r7.p, androidx.compose.ui.Modifier, boolean, r7.p, boolean, androidx.compose.material3.NavigationRailItemColors, androidx.compose.foundation.interaction.MutableInteractionSource, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void NavigationRailItemLayout(p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, p<? super Composer, ? super Integer, t0> pVar4, boolean z, final r7.a<Float> aVar, Composer composer, int i10) {
        int i11;
        final p<? super Composer, ? super Integer, t0> pVar5 = pVar4;
        final boolean z5 = z;
        Composer composerStartRestartGroup = composer.startRestartGroup(1498399348);
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
                ComposerKt.traceEventStart(1498399348, i11, -1, "androidx.compose.material3.NavigationRailItemLayout (NavigationRail.kt:502)");
            }
            composerStartRestartGroup.startReplaceableGroup(-753441910);
            int i12 = 458752 & i11;
            int i13 = 57344 & i11;
            boolean z10 = ((i11 & 7168) == 2048) | (i12 == 131072) | (i13 == 16384);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z10 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new MeasurePolicy() { // from class: androidx.compose.material3.NavigationRailKt$NavigationRailItemLayout$2$1
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
                                int iMo279roundToPx0680j_4 = measureScope2.mo279roundToPx0680j_4(Dp.m5678constructorimpl(NavigationRailKt.IndicatorHorizontalPadding * f10)) + placeableMo4631measureBRTryo02.getWidth();
                                int iM = t7.a.M(iMo279roundToPx0680j_4 * fFloatValue);
                                int iMo279roundToPx0680j_42 = measureScope2.mo279roundToPx0680j_4(Dp.m5678constructorimpl((pVar5 == null ? NavigationRailKt.IndicatorVerticalPaddingNoLabel : NavigationRailKt.IndicatorVerticalPaddingWithLabel) * f10)) + placeableMo4631measureBRTryo02.getHeight();
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
                                            return NavigationRailKt.m1741placeIconX9ElhV4(measureScope2, placeableMo4631measureBRTryo02, placeableMo4631measureBRTryo03, placeableMo4631measureBRTryo04, j10);
                                        }
                                        return NavigationRailKt.m1742placeLabelAndIconzUg2_y0(measureScope, placeableMo4631measureBRTryo0, placeableMo4631measureBRTryo02, placeableMo4631measureBRTryo03, placeableMo4631measureBRTryo04, j10, z5, fFloatValue);
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
            composerStartRestartGroup.startReplaceableGroup(-753442160);
            if (pVar4 != null) {
                Modifier modifierLayoutId2 = LayoutIdKt.layoutId(companion, LabelLayoutIdTag);
                composerStartRestartGroup.startReplaceableGroup(484847171);
                boolean z11 = (i13 == 16384) | (i12 == 131072);
                Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z11 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                    z5 = z;
                    objRememberedValue2 = new NavigationRailKt$NavigationRailItemLayout$1$2$1(z5, aVar);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    z5 = z;
                }
                composerStartRestartGroup.endReplaceableGroup();
                Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(modifierLayoutId2, (l) objRememberedValue2);
                composerStartRestartGroup.startReplaceableGroup(733328855);
                MeasurePolicy measurePolicyD2 = a0.c.d(companion3, false, composerStartRestartGroup, 0, -1323940314);
                int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                r7.a<ComposeUiNode> constructor3 = companion2.getConstructor();
                q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierGraphicsLayer);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new C05303(pVar, pVar2, pVar3, pVar5, z5, aVar, i10));
        }
    }

    public static final float getNavigationRailItemHeight() {
        return NavigationRailItemHeight;
    }

    public static final float getNavigationRailItemVerticalPadding() {
        return NavigationRailItemVerticalPadding;
    }

    public static final float getNavigationRailItemWidth() {
        return NavigationRailItemWidth;
    }

    public static final float getNavigationRailVerticalPadding() {
        return NavigationRailVerticalPadding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: placeIcon-X9ElhV4, reason: not valid java name */
    public static final MeasureResult m1741placeIconX9ElhV4(MeasureScope measureScope, Placeable placeable, Placeable placeable2, Placeable placeable3, long j10) {
        int iM5660constrainWidthK40F9xA = ConstraintsKt.m5660constrainWidthK40F9xA(j10, Math.max(placeable.getWidth(), Math.max(placeable2.getWidth(), placeable3 != null ? placeable3.getWidth() : 0)));
        int iM5659constrainHeightK40F9xA = ConstraintsKt.m5659constrainHeightK40F9xA(j10, measureScope.mo279roundToPx0680j_4(NavigationRailItemHeight));
        return MeasureScope.CC.q(measureScope, iM5660constrainWidthK40F9xA, iM5659constrainHeightK40F9xA, null, new NavigationRailKt$placeIcon$1(placeable3, placeable, androidx.compose.foundation.c.B(placeable, iM5660constrainWidthK40F9xA, 2), androidx.compose.foundation.c.f(placeable, iM5659constrainHeightK40F9xA, 2), placeable2, androidx.compose.foundation.c.B(placeable2, iM5660constrainWidthK40F9xA, 2), androidx.compose.foundation.c.f(placeable2, iM5659constrainHeightK40F9xA, 2), iM5660constrainWidthK40F9xA, iM5659constrainHeightK40F9xA), 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: placeLabelAndIcon-zUg2_y0, reason: not valid java name */
    public static final MeasureResult m1742placeLabelAndIconzUg2_y0(MeasureScope measureScope, Placeable placeable, Placeable placeable2, Placeable placeable3, Placeable placeable4, long j10, boolean z, float f10) {
        float height = placeable2.getHeight();
        float f11 = IndicatorVerticalPaddingWithLabel;
        float fMo285toPx0680j_4 = measureScope.mo285toPx0680j_4(f11) + height;
        float f12 = NavigationRailItemVerticalPadding;
        float fMo285toPx0680j_42 = measureScope.mo285toPx0680j_4(f12) + fMo285toPx0680j_4 + placeable.getHeight();
        float f13 = 2;
        float fM5647getMinHeightimpl = (Constraints.m5647getMinHeightimpl(j10) - fMo285toPx0680j_42) / f13;
        float fMo285toPx0680j_43 = measureScope.mo285toPx0680j_4(f11);
        float f14 = fM5647getMinHeightimpl < fMo285toPx0680j_43 ? fMo285toPx0680j_43 : fM5647getMinHeightimpl;
        float f15 = (f14 * f13) + fMo285toPx0680j_42;
        float height2 = (1 - f10) * ((z ? f14 : (f15 - placeable2.getHeight()) / f13) - f14);
        float fMo285toPx0680j_44 = measureScope.mo285toPx0680j_4(f12) + measureScope.mo285toPx0680j_4(f11) + placeable2.getHeight() + f14;
        int iM5660constrainWidthK40F9xA = ConstraintsKt.m5660constrainWidthK40F9xA(j10, Math.max(placeable2.getWidth(), Math.max(placeable.getWidth(), placeable4 != null ? placeable4.getWidth() : 0)));
        return MeasureScope.CC.q(measureScope, iM5660constrainWidthK40F9xA, t7.a.M(f15), null, new NavigationRailKt$placeLabelAndIcon$1(placeable4, z, f10, placeable, androidx.compose.foundation.c.B(placeable, iM5660constrainWidthK40F9xA, 2), fMo285toPx0680j_44, height2, placeable2, androidx.compose.foundation.c.B(placeable2, iM5660constrainWidthK40F9xA, 2), f14, placeable3, androidx.compose.foundation.c.B(placeable3, iM5660constrainWidthK40F9xA, 2), f14 - measureScope.mo285toPx0680j_4(f11), iM5660constrainWidthK40F9xA, measureScope), 4, null);
    }
}

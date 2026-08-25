package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.media3.extractor.text.ttml.TtmlNode;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\n\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a&\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a:\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a>\u0010\u0010\u001a\u00020\u0011*\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u0019\u0010\u0016\u001a\u00020\u0003*\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0018H\u0007¢\u0006\u0002\u0010\u0019\u001a\u0019\u0010\u001a\u001a\u00020\u0003*\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u0018H\u0007¢\u0006\u0002\u0010\u0019\u001a\u0014\u0010\u001b\u001a\u00020\u0011*\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u0001H\u0007\u001a\u001e\u0010\u001b\u001a\u00020\u0011*\u00020\u00112\u0006\u0010\u0002\u001a\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001a*\u0010\u001b\u001a\u00020\u0011*\u00020\u00112\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 \u001a>\u0010\u001b\u001a\u00020\u0011*\u00020\u00112\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u0003H\u0007ø\u0001\u0000¢\u0006\u0004\b!\u0010\u0015\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\""}, d2 = {"PaddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", TtmlNode.COMBINE_ALL, "Landroidx/compose/ui/unit/Dp;", "PaddingValues-0680j_4", "(F)Landroidx/compose/foundation/layout/PaddingValues;", "horizontal", "vertical", "PaddingValues-YgX7TsA", "(FF)Landroidx/compose/foundation/layout/PaddingValues;", TtmlNode.START, "top", TtmlNode.END, "bottom", "PaddingValues-a9UjIt4", "(FFFF)Landroidx/compose/foundation/layout/PaddingValues;", "absolutePadding", "Landroidx/compose/ui/Modifier;", TtmlNode.LEFT, TtmlNode.RIGHT, "absolutePadding-qDBjuR0", "(Landroidx/compose/ui/Modifier;FFFF)Landroidx/compose/ui/Modifier;", "calculateEndPadding", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "(Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/ui/unit/LayoutDirection;)F", "calculateStartPadding", "padding", "paddingValues", "padding-3ABfNKs", "(Landroidx/compose/ui/Modifier;F)Landroidx/compose/ui/Modifier;", "padding-VpY3zN4", "(Landroidx/compose/ui/Modifier;FF)Landroidx/compose/ui/Modifier;", "padding-qDBjuR0", "foundation-layout_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PaddingKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.layout.PaddingKt$padding$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/platform/InspectorInfo;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<InspectorInfo, t0> {
        final /* synthetic */ float $bottom;
        final /* synthetic */ float $end;
        final /* synthetic */ float $start;
        final /* synthetic */ float $top;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(float f10, float f11, float f12, float f13) {
            super(1);
            this.$start = f10;
            this.$top = f11;
            this.$end = f12;
            this.$bottom = f13;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((InspectorInfo) obj);
            return t0.f22605a;
        }

        public final void invoke(InspectorInfo inspectorInfo) {
            inspectorInfo.setName("padding");
            a0.c.e(this.$end, a0.c.e(this.$top, a0.c.e(this.$start, inspectorInfo.getProperties(), TtmlNode.START, inspectorInfo), "top", inspectorInfo), TtmlNode.END, inspectorInfo).set("bottom", Dp.m5676boximpl(this.$bottom));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.layout.PaddingKt$padding$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/platform/InspectorInfo;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<InspectorInfo, t0> {
        final /* synthetic */ float $horizontal;
        final /* synthetic */ float $vertical;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(float f10, float f11) {
            super(1);
            this.$horizontal = f10;
            this.$vertical = f11;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((InspectorInfo) obj);
            return t0.f22605a;
        }

        public final void invoke(InspectorInfo inspectorInfo) {
            inspectorInfo.setName("padding");
            a0.c.e(this.$horizontal, inspectorInfo.getProperties(), "horizontal", inspectorInfo).set("vertical", Dp.m5676boximpl(this.$vertical));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.layout.PaddingKt$padding$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/platform/InspectorInfo;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3 extends r implements l<InspectorInfo, t0> {
        final /* synthetic */ float $all;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass3(float f10) {
            super(1);
            this.$all = f10;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((InspectorInfo) obj);
            return t0.f22605a;
        }

        public final void invoke(InspectorInfo inspectorInfo) {
            inspectorInfo.setName("padding");
            inspectorInfo.setValue(Dp.m5676boximpl(this.$all));
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.layout.PaddingKt$padding$4, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/platform/InspectorInfo;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass4 extends r implements l<InspectorInfo, t0> {
        final /* synthetic */ PaddingValues $paddingValues;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass4(PaddingValues paddingValues) {
            super(1);
            this.$paddingValues = paddingValues;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((InspectorInfo) obj);
            return t0.f22605a;
        }

        public final void invoke(InspectorInfo inspectorInfo) {
            inspectorInfo.setName("padding");
            inspectorInfo.getProperties().set("paddingValues", this.$paddingValues);
        }
    }

    /* JADX INFO: renamed from: PaddingValues-0680j_4, reason: not valid java name */
    public static final PaddingValues m522PaddingValues0680j_4(float f10) {
        return new PaddingValuesImpl(f10, f10, f10, f10, null);
    }

    /* JADX INFO: renamed from: PaddingValues-YgX7TsA, reason: not valid java name */
    public static final PaddingValues m523PaddingValuesYgX7TsA(float f10, float f11) {
        return new PaddingValuesImpl(f10, f11, f10, f11, null);
    }

    /* JADX INFO: renamed from: PaddingValues-YgX7TsA$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m524PaddingValuesYgX7TsA$default(float f10, float f11, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = Dp.m5678constructorimpl(0);
        }
        if ((i10 & 2) != 0) {
            f11 = Dp.m5678constructorimpl(0);
        }
        return m523PaddingValuesYgX7TsA(f10, f11);
    }

    /* JADX INFO: renamed from: PaddingValues-a9UjIt4, reason: not valid java name */
    public static final PaddingValues m525PaddingValuesa9UjIt4(float f10, float f11, float f12, float f13) {
        return new PaddingValuesImpl(f10, f11, f12, f13, null);
    }

    /* JADX INFO: renamed from: PaddingValues-a9UjIt4$default, reason: not valid java name */
    public static /* synthetic */ PaddingValues m526PaddingValuesa9UjIt4$default(float f10, float f11, float f12, float f13, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = Dp.m5678constructorimpl(0);
        }
        if ((i10 & 2) != 0) {
            f11 = Dp.m5678constructorimpl(0);
        }
        if ((i10 & 4) != 0) {
            f12 = Dp.m5678constructorimpl(0);
        }
        if ((i10 & 8) != 0) {
            f13 = Dp.m5678constructorimpl(0);
        }
        return m525PaddingValuesa9UjIt4(f10, f11, f12, f13);
    }

    /* JADX INFO: renamed from: absolutePadding-qDBjuR0, reason: not valid java name */
    public static final Modifier m527absolutePaddingqDBjuR0(Modifier modifier, float f10, float f11, float f12, float f13) {
        return modifier.then(new PaddingElement(f10, f11, f12, f13, false, new PaddingKt$absolutePadding$1(f10, f11, f12, f13), null));
    }

    /* JADX INFO: renamed from: absolutePadding-qDBjuR0$default, reason: not valid java name */
    public static /* synthetic */ Modifier m528absolutePaddingqDBjuR0$default(Modifier modifier, float f10, float f11, float f12, float f13, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = Dp.m5678constructorimpl(0);
        }
        if ((i10 & 2) != 0) {
            f11 = Dp.m5678constructorimpl(0);
        }
        if ((i10 & 4) != 0) {
            f12 = Dp.m5678constructorimpl(0);
        }
        if ((i10 & 8) != 0) {
            f13 = Dp.m5678constructorimpl(0);
        }
        return m527absolutePaddingqDBjuR0(modifier, f10, f11, f12, f13);
    }

    public static final float calculateEndPadding(PaddingValues paddingValues, LayoutDirection layoutDirection) {
        return layoutDirection == LayoutDirection.Ltr ? paddingValues.mo481calculateRightPaddingu2uoSUM(layoutDirection) : paddingValues.mo480calculateLeftPaddingu2uoSUM(layoutDirection);
    }

    public static final float calculateStartPadding(PaddingValues paddingValues, LayoutDirection layoutDirection) {
        return layoutDirection == LayoutDirection.Ltr ? paddingValues.mo480calculateLeftPaddingu2uoSUM(layoutDirection) : paddingValues.mo481calculateRightPaddingu2uoSUM(layoutDirection);
    }

    public static final Modifier padding(Modifier modifier, PaddingValues paddingValues) {
        return modifier.then(new PaddingValuesElement(paddingValues, new AnonymousClass4(paddingValues)));
    }

    /* JADX INFO: renamed from: padding-3ABfNKs, reason: not valid java name */
    public static final Modifier m529padding3ABfNKs(Modifier modifier, float f10) {
        return modifier.then(new PaddingElement(f10, f10, f10, f10, true, new AnonymousClass3(f10), null));
    }

    /* JADX INFO: renamed from: padding-VpY3zN4, reason: not valid java name */
    public static final Modifier m530paddingVpY3zN4(Modifier modifier, float f10, float f11) {
        return modifier.then(new PaddingElement(f10, f11, f10, f11, true, new AnonymousClass2(f10, f11), null));
    }

    /* JADX INFO: renamed from: padding-VpY3zN4$default, reason: not valid java name */
    public static /* synthetic */ Modifier m531paddingVpY3zN4$default(Modifier modifier, float f10, float f11, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = Dp.m5678constructorimpl(0);
        }
        if ((i10 & 2) != 0) {
            f11 = Dp.m5678constructorimpl(0);
        }
        return m530paddingVpY3zN4(modifier, f10, f11);
    }

    /* JADX INFO: renamed from: padding-qDBjuR0, reason: not valid java name */
    public static final Modifier m532paddingqDBjuR0(Modifier modifier, float f10, float f11, float f12, float f13) {
        return modifier.then(new PaddingElement(f10, f11, f12, f13, true, new AnonymousClass1(f10, f11, f12, f13), null));
    }

    /* JADX INFO: renamed from: padding-qDBjuR0$default, reason: not valid java name */
    public static /* synthetic */ Modifier m533paddingqDBjuR0$default(Modifier modifier, float f10, float f11, float f12, float f13, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = Dp.m5678constructorimpl(0);
        }
        if ((i10 & 2) != 0) {
            f11 = Dp.m5678constructorimpl(0);
        }
        if ((i10 & 4) != 0) {
            f12 = Dp.m5678constructorimpl(0);
        }
        if ((i10 & 8) != 0) {
            f13 = Dp.m5678constructorimpl(0);
        }
        return m532paddingqDBjuR0(modifier, f10, f11, f12, f13);
    }
}

package androidx.compose.material3;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/SubcomposeMeasureScope;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "invoke-0kLqBqw", "(Landroidx/compose/ui/layout/SubcomposeMeasureScope;J)Landroidx/compose/ui/layout/MeasureResult;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class TabRowKt$ScrollableTabRowImp$1$1$1 extends r implements p<SubcomposeMeasureScope, Constraints, MeasureResult> {
    final /* synthetic */ p<Composer, Integer, t0> $divider;
    final /* synthetic */ float $edgePadding;
    final /* synthetic */ q<List<TabPosition>, Composer, Integer, t0> $indicator;
    final /* synthetic */ ScrollableTabData $scrollableTabData;
    final /* synthetic */ int $selectedTabIndex;
    final /* synthetic */ p<Composer, Integer, t0> $tabs;

    /* JADX INFO: renamed from: androidx.compose.material3.TabRowKt$ScrollableTabRowImp$1$1$1$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass2 extends r implements l<Placeable.PlacementScope, t0> {
        final /* synthetic */ long $constraints;
        final /* synthetic */ p<Composer, Integer, t0> $divider;
        final /* synthetic */ q<List<TabPosition>, Composer, Integer, t0> $indicator;
        final /* synthetic */ int $layoutHeight;
        final /* synthetic */ int $layoutWidth;
        final /* synthetic */ int $padding;
        final /* synthetic */ ScrollableTabData $scrollableTabData;
        final /* synthetic */ int $selectedTabIndex;
        final /* synthetic */ List<Dp> $tabContentWidths;
        final /* synthetic */ List<Placeable> $tabPlaceables;
        final /* synthetic */ SubcomposeMeasureScope $this_SubcomposeLayout;

        /* JADX INFO: renamed from: androidx.compose.material3.TabRowKt$ScrollableTabRowImp$1$1$1$2$3, reason: invalid class name */
        @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass3 extends r implements p<Composer, Integer, t0> {
            final /* synthetic */ q<List<TabPosition>, Composer, Integer, t0> $indicator;
            final /* synthetic */ List<TabPosition> $tabPositions;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass3(q<? super List<TabPosition>, ? super Composer, ? super Integer, t0> qVar, List<TabPosition> list) {
                super(2);
                this.$indicator = qVar;
                this.$tabPositions = list;
            }

            @Override // r7.p
            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                invoke((Composer) obj, ((Number) obj2).intValue());
                return t0.f22605a;
            }

            public final void invoke(Composer composer, int i10) {
                if ((i10 & 3) == 2 && composer.getSkipping()) {
                    composer.skipToGroupEnd();
                    return;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(358596038, i10, -1, "androidx.compose.material3.ScrollableTabRowImp.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TabRow.kt:942)");
                }
                this.$indicator.invoke(this.$tabPositions, composer, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(int i10, List<Placeable> list, SubcomposeMeasureScope subcomposeMeasureScope, p<? super Composer, ? super Integer, t0> pVar, ScrollableTabData scrollableTabData, int i11, List<Dp> list2, long j10, int i12, int i13, q<? super List<TabPosition>, ? super Composer, ? super Integer, t0> qVar) {
            super(1);
            this.$padding = i10;
            this.$tabPlaceables = list;
            this.$this_SubcomposeLayout = subcomposeMeasureScope;
            this.$divider = pVar;
            this.$scrollableTabData = scrollableTabData;
            this.$selectedTabIndex = i11;
            this.$tabContentWidths = list2;
            this.$constraints = j10;
            this.$layoutWidth = i12;
            this.$layoutHeight = i13;
            this.$indicator = qVar;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Placeable.PlacementScope) obj);
            return t0.f22605a;
        }

        public final void invoke(Placeable.PlacementScope placementScope) {
            ArrayList arrayList = new ArrayList();
            int i10 = this.$padding;
            List<Placeable> list = this.$tabPlaceables;
            SubcomposeMeasureScope subcomposeMeasureScope = this.$this_SubcomposeLayout;
            List<Dp> list2 = this.$tabContentWidths;
            int size = list.size();
            int width = i10;
            for (int i11 = 0; i11 < size; i11++) {
                Placeable placeable = list.get(i11);
                Placeable.PlacementScope.placeRelative$default(placementScope, placeable, width, 0, 0.0f, 4, null);
                arrayList.add(new TabPosition(subcomposeMeasureScope.mo282toDpu2uoSUM(width), subcomposeMeasureScope.mo282toDpu2uoSUM(placeable.getWidth()), list2.get(i11).m5692unboximpl(), null));
                width += placeable.getWidth();
            }
            List<Measurable> listSubcompose = this.$this_SubcomposeLayout.subcompose(TabSlots.Divider, this.$divider);
            long j10 = this.$constraints;
            int i12 = this.$layoutWidth;
            int i13 = this.$layoutHeight;
            int size2 = listSubcompose.size();
            for (int i14 = 0; i14 < size2; i14++) {
                Placeable placeableMo4631measureBRTryo0 = listSubcompose.get(i14).mo4631measureBRTryo0(Constraints.m5637copyZbe2FdA$default(j10, i12, i12, 0, 0, 8, null));
                Placeable.PlacementScope.placeRelative$default(placementScope, placeableMo4631measureBRTryo0, 0, i13 - placeableMo4631measureBRTryo0.getHeight(), 0.0f, 4, null);
            }
            List<Measurable> listSubcompose2 = this.$this_SubcomposeLayout.subcompose(TabSlots.Indicator, ComposableLambdaKt.composableLambdaInstance(358596038, true, new AnonymousClass3(this.$indicator, arrayList)));
            int i15 = this.$layoutWidth;
            int i16 = this.$layoutHeight;
            int size3 = listSubcompose2.size();
            for (int i17 = 0; i17 < size3; i17++) {
                Placeable.PlacementScope.placeRelative$default(placementScope, listSubcompose2.get(i17).mo4631measureBRTryo0(Constraints.INSTANCE.m5654fixedJhjzzOo(i15, i16)), 0, 0, 0.0f, 4, null);
            }
            this.$scrollableTabData.onLaidOut(this.$this_SubcomposeLayout, this.$padding, arrayList, this.$selectedTabIndex);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public TabRowKt$ScrollableTabRowImp$1$1$1(float f10, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, ScrollableTabData scrollableTabData, int i10, q<? super List<TabPosition>, ? super Composer, ? super Integer, t0> qVar) {
        super(2);
        this.$edgePadding = f10;
        this.$tabs = pVar;
        this.$divider = pVar2;
        this.$scrollableTabData = scrollableTabData;
        this.$selectedTabIndex = i10;
        this.$indicator = qVar;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return m2066invoke0kLqBqw((SubcomposeMeasureScope) obj, ((Constraints) obj2).getValue());
    }

    /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
    public final MeasureResult m2066invoke0kLqBqw(SubcomposeMeasureScope subcomposeMeasureScope, long j10) {
        int iMo279roundToPx0680j_4 = subcomposeMeasureScope.mo279roundToPx0680j_4(TabRowKt.ScrollableTabRowMinimumTabWidth);
        int iMo279roundToPx0680j_42 = subcomposeMeasureScope.mo279roundToPx0680j_4(this.$edgePadding);
        List<Measurable> listSubcompose = subcomposeMeasureScope.subcompose(TabSlots.Tabs, this.$tabs);
        Integer numValueOf = 0;
        int size = listSubcompose.size();
        for (int i10 = 0; i10 < size; i10++) {
            numValueOf = Integer.valueOf(Math.max(numValueOf.intValue(), listSubcompose.get(i10).maxIntrinsicHeight(Integer.MAX_VALUE)));
        }
        int iIntValue = numValueOf.intValue();
        long jM5637copyZbe2FdA$default = Constraints.m5637copyZbe2FdA$default(j10, iMo279roundToPx0680j_4, 0, iIntValue, iIntValue, 2, null);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int size2 = listSubcompose.size();
        for (int i11 = 0; i11 < size2; i11++) {
            Measurable measurable = listSubcompose.get(i11);
            Placeable placeableMo4631measureBRTryo0 = measurable.mo4631measureBRTryo0(jM5637copyZbe2FdA$default);
            float fM5678constructorimpl = Dp.m5678constructorimpl(subcomposeMeasureScope.mo282toDpu2uoSUM(Math.min(measurable.maxIntrinsicWidth(placeableMo4631measureBRTryo0.getHeight()), placeableMo4631measureBRTryo0.getWidth())) - Dp.m5678constructorimpl(TabKt.getHorizontalTextPadding() * 2));
            arrayList.add(placeableMo4631measureBRTryo0);
            arrayList2.add(Dp.m5676boximpl(fM5678constructorimpl));
        }
        Integer numValueOf2 = Integer.valueOf(iMo279roundToPx0680j_42 * 2);
        int size3 = arrayList.size();
        for (int i12 = 0; i12 < size3; i12++) {
            numValueOf2 = Integer.valueOf(((Placeable) arrayList.get(i12)).getWidth() + numValueOf2.intValue());
        }
        int iIntValue2 = numValueOf2.intValue();
        return MeasureScope.CC.q(subcomposeMeasureScope, iIntValue2, iIntValue, null, new AnonymousClass2(iMo279roundToPx0680j_42, arrayList, subcomposeMeasureScope, this.$divider, this.$scrollableTabData, this.$selectedTabIndex, arrayList2, j10, iIntValue2, iIntValue, this.$indicator), 4, null);
    }
}

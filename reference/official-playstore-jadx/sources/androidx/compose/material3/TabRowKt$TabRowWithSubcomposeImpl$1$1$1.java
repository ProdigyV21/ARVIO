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
import kotlin.jvm.internal.i0;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/SubcomposeMeasureScope;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "invoke-0kLqBqw", "(Landroidx/compose/ui/layout/SubcomposeMeasureScope;J)Landroidx/compose/ui/layout/MeasureResult;"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class TabRowKt$TabRowWithSubcomposeImpl$1$1$1 extends r implements p<SubcomposeMeasureScope, Constraints, MeasureResult> {
    final /* synthetic */ p<Composer, Integer, t0> $divider;
    final /* synthetic */ q<List<TabPosition>, Composer, Integer, t0> $indicator;
    final /* synthetic */ p<Composer, Integer, t0> $tabs;

    /* JADX INFO: renamed from: androidx.compose.material3.TabRowKt$TabRowWithSubcomposeImpl$1$1$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<Placeable.PlacementScope, t0> {
        final /* synthetic */ long $constraints;
        final /* synthetic */ p<Composer, Integer, t0> $divider;
        final /* synthetic */ q<List<TabPosition>, Composer, Integer, t0> $indicator;
        final /* synthetic */ List<Placeable> $tabPlaceables;
        final /* synthetic */ List<TabPosition> $tabPositions;
        final /* synthetic */ int $tabRowHeight;
        final /* synthetic */ int $tabRowWidth;
        final /* synthetic */ i0 $tabWidth;
        final /* synthetic */ SubcomposeMeasureScope $this_SubcomposeLayout;

        /* JADX INFO: renamed from: androidx.compose.material3.TabRowKt$TabRowWithSubcomposeImpl$1$1$1$1$3, reason: invalid class name */
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
                    ComposerKt.traceEventStart(1621992604, i10, -1, "androidx.compose.material3.TabRowWithSubcomposeImpl.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TabRow.kt:631)");
                }
                this.$indicator.invoke(this.$tabPositions, composer, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(List<? extends Placeable> list, SubcomposeMeasureScope subcomposeMeasureScope, p<? super Composer, ? super Integer, t0> pVar, i0 i0Var, long j10, int i10, q<? super List<TabPosition>, ? super Composer, ? super Integer, t0> qVar, List<TabPosition> list2, int i11) {
            super(1);
            this.$tabPlaceables = list;
            this.$this_SubcomposeLayout = subcomposeMeasureScope;
            this.$divider = pVar;
            this.$tabWidth = i0Var;
            this.$constraints = j10;
            this.$tabRowHeight = i10;
            this.$indicator = qVar;
            this.$tabPositions = list2;
            this.$tabRowWidth = i11;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Placeable.PlacementScope) obj);
            return t0.f22605a;
        }

        public final void invoke(Placeable.PlacementScope placementScope) {
            List<Placeable> list = this.$tabPlaceables;
            i0 i0Var = this.$tabWidth;
            int size = list.size();
            for (int i10 = 0; i10 < size; i10++) {
                Placeable.PlacementScope.placeRelative$default(placementScope, list.get(i10), i10 * i0Var.f19744i, 0, 0.0f, 4, null);
            }
            List<Measurable> listSubcompose = this.$this_SubcomposeLayout.subcompose(TabSlots.Divider, this.$divider);
            long j10 = this.$constraints;
            int i11 = this.$tabRowHeight;
            int size2 = listSubcompose.size();
            for (int i12 = 0; i12 < size2; i12++) {
                Placeable placeableMo4631measureBRTryo0 = listSubcompose.get(i12).mo4631measureBRTryo0(Constraints.m5637copyZbe2FdA$default(j10, 0, 0, 0, 0, 11, null));
                Placeable.PlacementScope.placeRelative$default(placementScope, placeableMo4631measureBRTryo0, 0, i11 - placeableMo4631measureBRTryo0.getHeight(), 0.0f, 4, null);
            }
            List<Measurable> listSubcompose2 = this.$this_SubcomposeLayout.subcompose(TabSlots.Indicator, ComposableLambdaKt.composableLambdaInstance(1621992604, true, new AnonymousClass3(this.$indicator, this.$tabPositions)));
            int i13 = this.$tabRowWidth;
            int i14 = this.$tabRowHeight;
            int size3 = listSubcompose2.size();
            for (int i15 = 0; i15 < size3; i15++) {
                Placeable.PlacementScope.placeRelative$default(placementScope, listSubcompose2.get(i15).mo4631measureBRTryo0(Constraints.INSTANCE.m5654fixedJhjzzOo(i13, i14)), 0, 0, 0.0f, 4, null);
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public TabRowKt$TabRowWithSubcomposeImpl$1$1$1(p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, q<? super List<TabPosition>, ? super Composer, ? super Integer, t0> qVar) {
        super(2);
        this.$tabs = pVar;
        this.$divider = pVar2;
        this.$indicator = qVar;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return m2068invoke0kLqBqw((SubcomposeMeasureScope) obj, ((Constraints) obj2).getValue());
    }

    /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
    public final MeasureResult m2068invoke0kLqBqw(SubcomposeMeasureScope subcomposeMeasureScope, long j10) {
        int iM5646getMaxWidthimpl = Constraints.m5646getMaxWidthimpl(j10);
        List<Measurable> listSubcompose = subcomposeMeasureScope.subcompose(TabSlots.Tabs, this.$tabs);
        int size = listSubcompose.size();
        i0 i0Var = new i0();
        if (size > 0) {
            i0Var.f19744i = iM5646getMaxWidthimpl / size;
        }
        Integer numValueOf = 0;
        int size2 = listSubcompose.size();
        for (int i10 = 0; i10 < size2; i10++) {
            numValueOf = Integer.valueOf(Math.max(listSubcompose.get(i10).maxIntrinsicHeight(i0Var.f19744i), numValueOf.intValue()));
        }
        int iIntValue = numValueOf.intValue();
        ArrayList arrayList = new ArrayList(listSubcompose.size());
        int size3 = listSubcompose.size();
        for (int i11 = 0; i11 < size3; i11++) {
            Measurable measurable = listSubcompose.get(i11);
            int i12 = i0Var.f19744i;
            arrayList.add(measurable.mo4631measureBRTryo0(Constraints.m5636copyZbe2FdA(j10, i12, i12, iIntValue, iIntValue)));
        }
        ArrayList arrayList2 = new ArrayList(size);
        for (int i13 = 0; i13 < size; i13++) {
            Dp dpM5676boximpl = Dp.m5676boximpl(Dp.m5678constructorimpl(subcomposeMeasureScope.mo282toDpu2uoSUM(Math.min(listSubcompose.get(i13).maxIntrinsicWidth(iIntValue), i0Var.f19744i)) - Dp.m5678constructorimpl(TabKt.getHorizontalTextPadding() * 2)));
            Dp dpM5676boximpl2 = Dp.m5676boximpl(Dp.m5678constructorimpl(24));
            if (dpM5676boximpl.compareTo(dpM5676boximpl2) < 0) {
                dpM5676boximpl = dpM5676boximpl2;
            }
            arrayList2.add(new TabPosition(Dp.m5678constructorimpl(subcomposeMeasureScope.mo282toDpu2uoSUM(i0Var.f19744i) * i13), subcomposeMeasureScope.mo282toDpu2uoSUM(i0Var.f19744i), dpM5676boximpl.m5692unboximpl(), null));
        }
        return MeasureScope.CC.q(subcomposeMeasureScope, iM5646getMaxWidthimpl, iIntValue, null, new AnonymousClass1(arrayList, subcomposeMeasureScope, this.$divider, i0Var, j10, iIntValue, this.$indicator, arrayList2, iM5646getMaxWidthimpl), 4, null);
    }
}

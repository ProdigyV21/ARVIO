package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.material3.FabPosition;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.LayoutDirection;
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
public final class ScaffoldKt$ScaffoldLayoutWithMeasureFix$1$1 extends r implements p<SubcomposeMeasureScope, Constraints, MeasureResult> {
    final /* synthetic */ p<Composer, Integer, t0> $bottomBar;
    final /* synthetic */ q<PaddingValues, Composer, Integer, t0> $content;
    final /* synthetic */ WindowInsets $contentWindowInsets;
    final /* synthetic */ p<Composer, Integer, t0> $fab;
    final /* synthetic */ int $fabPosition;
    final /* synthetic */ p<Composer, Integer, t0> $snackbar;
    final /* synthetic */ p<Composer, Integer, t0> $topBar;

    /* JADX INFO: renamed from: androidx.compose.material3.ScaffoldKt$ScaffoldLayoutWithMeasureFix$1$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<Placeable.PlacementScope, t0> {
        final /* synthetic */ List<Placeable> $bodyContentPlaceables;
        final /* synthetic */ Integer $bottomBarHeight;
        final /* synthetic */ List<Placeable> $bottomBarPlaceables;
        final /* synthetic */ WindowInsets $contentWindowInsets;
        final /* synthetic */ Integer $fabOffsetFromBottom;
        final /* synthetic */ List<Placeable> $fabPlaceables;
        final /* synthetic */ FabPlacement $fabPlacement;
        final /* synthetic */ int $layoutHeight;
        final /* synthetic */ int $layoutWidth;
        final /* synthetic */ int $snackbarOffsetFromBottom;
        final /* synthetic */ List<Placeable> $snackbarPlaceables;
        final /* synthetic */ int $snackbarWidth;
        final /* synthetic */ SubcomposeMeasureScope $this_SubcomposeLayout;
        final /* synthetic */ List<Placeable> $topBarPlaceables;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(List<? extends Placeable> list, List<? extends Placeable> list2, List<? extends Placeable> list3, List<? extends Placeable> list4, FabPlacement fabPlacement, int i10, int i11, WindowInsets windowInsets, SubcomposeMeasureScope subcomposeMeasureScope, int i12, int i13, Integer num, List<? extends Placeable> list5, Integer num2) {
            super(1);
            this.$bodyContentPlaceables = list;
            this.$topBarPlaceables = list2;
            this.$snackbarPlaceables = list3;
            this.$bottomBarPlaceables = list4;
            this.$fabPlacement = fabPlacement;
            this.$layoutWidth = i10;
            this.$snackbarWidth = i11;
            this.$contentWindowInsets = windowInsets;
            this.$this_SubcomposeLayout = subcomposeMeasureScope;
            this.$layoutHeight = i12;
            this.$snackbarOffsetFromBottom = i13;
            this.$bottomBarHeight = num;
            this.$fabPlaceables = list5;
            this.$fabOffsetFromBottom = num2;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Placeable.PlacementScope) obj);
            return t0.f22605a;
        }

        public final void invoke(Placeable.PlacementScope placementScope) {
            List<Placeable> list = this.$bodyContentPlaceables;
            int size = list.size();
            for (int i10 = 0; i10 < size; i10++) {
                Placeable.PlacementScope.place$default(placementScope, list.get(i10), 0, 0, 0.0f, 4, null);
            }
            List<Placeable> list2 = this.$topBarPlaceables;
            int size2 = list2.size();
            for (int i11 = 0; i11 < size2; i11++) {
                Placeable.PlacementScope.place$default(placementScope, list2.get(i11), 0, 0, 0.0f, 4, null);
            }
            List<Placeable> list3 = this.$snackbarPlaceables;
            int i12 = this.$layoutWidth;
            int i13 = this.$snackbarWidth;
            WindowInsets windowInsets = this.$contentWindowInsets;
            SubcomposeMeasureScope subcomposeMeasureScope = this.$this_SubcomposeLayout;
            int i14 = this.$layoutHeight;
            int i15 = this.$snackbarOffsetFromBottom;
            int size3 = list3.size();
            for (int i16 = 0; i16 < size3; i16++) {
                Placeable.PlacementScope.place$default(placementScope, list3.get(i16), windowInsets.getLeft(subcomposeMeasureScope, subcomposeMeasureScope.getLayoutDirection()) + ((i12 - i13) / 2), i14 - i15, 0.0f, 4, null);
            }
            List<Placeable> list4 = this.$bottomBarPlaceables;
            int i17 = this.$layoutHeight;
            Integer num = this.$bottomBarHeight;
            int size4 = list4.size();
            for (int i18 = 0; i18 < size4; i18++) {
                Placeable.PlacementScope.place$default(placementScope, list4.get(i18), 0, i17 - (num != null ? num.intValue() : 0), 0.0f, 4, null);
            }
            FabPlacement fabPlacement = this.$fabPlacement;
            if (fabPlacement != null) {
                List<Placeable> list5 = this.$fabPlaceables;
                int i19 = this.$layoutHeight;
                Integer num2 = this.$fabOffsetFromBottom;
                int size5 = list5.size();
                for (int i20 = 0; i20 < size5; i20++) {
                    Placeable.PlacementScope.place$default(placementScope, list5.get(i20), fabPlacement.getLeft(), i19 - num2.intValue(), 0.0f, 4, null);
                }
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ScaffoldKt$ScaffoldLayoutWithMeasureFix$1$1(p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, int i10, WindowInsets windowInsets, p<? super Composer, ? super Integer, t0> pVar4, q<? super PaddingValues, ? super Composer, ? super Integer, t0> qVar) {
        super(2);
        this.$topBar = pVar;
        this.$snackbar = pVar2;
        this.$fab = pVar3;
        this.$fabPosition = i10;
        this.$contentWindowInsets = windowInsets;
        this.$bottomBar = pVar4;
        this.$content = qVar;
    }

    @Override // r7.p
    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        return m1805invoke0kLqBqw((SubcomposeMeasureScope) obj, ((Constraints) obj2).getValue());
    }

    /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
    public final MeasureResult m1805invoke0kLqBqw(SubcomposeMeasureScope subcomposeMeasureScope, long j10) {
        Object obj;
        Object obj2;
        Object obj3;
        int i10;
        FabPlacement fabPlacement;
        Object obj4;
        Object obj5;
        Object obj6;
        int iMo279roundToPx0680j_4;
        int iMo279roundToPx0680j_42;
        int iM5646getMaxWidthimpl = Constraints.m5646getMaxWidthimpl(j10);
        int iM5645getMaxHeightimpl = Constraints.m5645getMaxHeightimpl(j10);
        long jM5637copyZbe2FdA$default = Constraints.m5637copyZbe2FdA$default(j10, 0, 0, 0, 0, 10, null);
        List<Measurable> listSubcompose = subcomposeMeasureScope.subcompose(ScaffoldLayoutContent.TopBar, this.$topBar);
        ArrayList arrayList = new ArrayList(listSubcompose.size());
        int size = listSubcompose.size();
        for (int i11 = 0; i11 < size; i11++) {
            arrayList.add(listSubcompose.get(i11).mo4631measureBRTryo0(jM5637copyZbe2FdA$default));
        }
        if (arrayList.isEmpty()) {
            obj = null;
        } else {
            obj = arrayList.get(0);
            int height = ((Placeable) obj).getHeight();
            int iW = t7.a.w(arrayList);
            if (1 <= iW) {
                int i12 = 1;
                while (true) {
                    Object obj7 = arrayList.get(i12);
                    int height2 = ((Placeable) obj7).getHeight();
                    if (height < height2) {
                        obj = obj7;
                        height = height2;
                    }
                    if (i12 == iW) {
                        break;
                    }
                    i12++;
                }
            }
        }
        Placeable placeable = (Placeable) obj;
        int height3 = placeable != null ? placeable.getHeight() : 0;
        List<Measurable> listSubcompose2 = subcomposeMeasureScope.subcompose(ScaffoldLayoutContent.Snackbar, this.$snackbar);
        WindowInsets windowInsets = this.$contentWindowInsets;
        ArrayList arrayList2 = new ArrayList(listSubcompose2.size());
        int size2 = listSubcompose2.size();
        int i13 = 0;
        while (i13 < size2) {
            arrayList2.add(listSubcompose2.get(i13).mo4631measureBRTryo0(ConstraintsKt.m5662offsetNN6EwU(jM5637copyZbe2FdA$default, (-windowInsets.getLeft(subcomposeMeasureScope, subcomposeMeasureScope.getLayoutDirection())) - windowInsets.getRight(subcomposeMeasureScope, subcomposeMeasureScope.getLayoutDirection()), -windowInsets.getBottom(subcomposeMeasureScope))));
            i13++;
            arrayList = arrayList;
            listSubcompose2 = listSubcompose2;
        }
        ArrayList arrayList3 = arrayList;
        if (arrayList2.isEmpty()) {
            obj2 = null;
        } else {
            obj2 = arrayList2.get(0);
            int height4 = ((Placeable) obj2).getHeight();
            int iW2 = t7.a.w(arrayList2);
            if (1 <= iW2) {
                Object obj8 = obj2;
                int i14 = height4;
                int i15 = 1;
                while (true) {
                    Object obj9 = arrayList2.get(i15);
                    int height5 = ((Placeable) obj9).getHeight();
                    if (i14 < height5) {
                        obj8 = obj9;
                        i14 = height5;
                    }
                    if (i15 == iW2) {
                        break;
                    }
                    i15++;
                }
                obj2 = obj8;
            }
        }
        Placeable placeable2 = (Placeable) obj2;
        int height6 = placeable2 != null ? placeable2.getHeight() : 0;
        if (arrayList2.isEmpty()) {
            obj3 = null;
        } else {
            obj3 = arrayList2.get(0);
            int width = ((Placeable) obj3).getWidth();
            int iW3 = t7.a.w(arrayList2);
            if (1 <= iW3) {
                Object obj10 = obj3;
                int i16 = width;
                int i17 = 1;
                while (true) {
                    Object obj11 = arrayList2.get(i17);
                    int width2 = ((Placeable) obj11).getWidth();
                    if (i16 < width2) {
                        obj10 = obj11;
                        i16 = width2;
                    }
                    if (i17 == iW3) {
                        break;
                    }
                    i17++;
                }
                obj3 = obj10;
            }
        }
        Placeable placeable3 = (Placeable) obj3;
        int width3 = placeable3 != null ? placeable3.getWidth() : 0;
        List<Measurable> listSubcompose3 = subcomposeMeasureScope.subcompose(ScaffoldLayoutContent.Fab, this.$fab);
        WindowInsets windowInsets2 = this.$contentWindowInsets;
        int i18 = width3;
        ArrayList arrayList4 = new ArrayList(listSubcompose3.size());
        int size3 = listSubcompose3.size();
        int i19 = 0;
        while (i19 < size3) {
            int i20 = height6;
            List<Measurable> list = listSubcompose3;
            Placeable placeableMo4631measureBRTryo0 = listSubcompose3.get(i19).mo4631measureBRTryo0(ConstraintsKt.m5662offsetNN6EwU(jM5637copyZbe2FdA$default, (-windowInsets2.getLeft(subcomposeMeasureScope, subcomposeMeasureScope.getLayoutDirection())) - windowInsets2.getRight(subcomposeMeasureScope, subcomposeMeasureScope.getLayoutDirection()), -windowInsets2.getBottom(subcomposeMeasureScope)));
            if (placeableMo4631measureBRTryo0.getHeight() == 0 || placeableMo4631measureBRTryo0.getWidth() == 0) {
                placeableMo4631measureBRTryo0 = null;
            }
            if (placeableMo4631measureBRTryo0 != null) {
                arrayList4.add(placeableMo4631measureBRTryo0);
            }
            i19++;
            height6 = i20;
            listSubcompose3 = list;
        }
        int i21 = height6;
        if (arrayList4.isEmpty()) {
            i10 = height3;
            fabPlacement = null;
        } else {
            if (arrayList4.isEmpty()) {
                obj5 = null;
            } else {
                obj5 = arrayList4.get(0);
                int width4 = ((Placeable) obj5).getWidth();
                int iW4 = t7.a.w(arrayList4);
                if (1 <= iW4) {
                    Object obj12 = obj5;
                    int i22 = width4;
                    int i23 = 1;
                    while (true) {
                        Object obj13 = arrayList4.get(i23);
                        int width5 = ((Placeable) obj13).getWidth();
                        if (i22 < width5) {
                            obj12 = obj13;
                            i22 = width5;
                        }
                        if (i23 == iW4) {
                            break;
                        }
                        i23++;
                    }
                    obj5 = obj12;
                }
            }
            int width6 = ((Placeable) obj5).getWidth();
            if (arrayList4.isEmpty()) {
                i10 = height3;
                obj6 = null;
            } else {
                obj6 = arrayList4.get(0);
                int height7 = ((Placeable) obj6).getHeight();
                int iW5 = t7.a.w(arrayList4);
                if (1 <= iW5) {
                    Object obj14 = obj6;
                    int i24 = height7;
                    int i25 = 1;
                    while (true) {
                        Object obj15 = arrayList4.get(i25);
                        i10 = height3;
                        int height8 = ((Placeable) obj15).getHeight();
                        if (i24 < height8) {
                            i24 = height8;
                            obj14 = obj15;
                        }
                        if (i25 == iW5) {
                            break;
                        }
                        i25++;
                        height3 = i10;
                    }
                    obj6 = obj14;
                } else {
                    i10 = height3;
                }
            }
            int height9 = ((Placeable) obj6).getHeight();
            int i26 = this.$fabPosition;
            FabPosition.Companion companion = FabPosition.INSTANCE;
            if (!FabPosition.m1564equalsimpl0(i26, companion.m1571getStartERTFSPs())) {
                if (!(FabPosition.m1564equalsimpl0(i26, companion.m1569getEndERTFSPs()) ? true : FabPosition.m1564equalsimpl0(i26, companion.m1570getEndOverlayERTFSPs()))) {
                    iMo279roundToPx0680j_4 = (iM5646getMaxWidthimpl - width6) / 2;
                } else if (subcomposeMeasureScope.getLayoutDirection() == LayoutDirection.Ltr) {
                    iMo279roundToPx0680j_42 = subcomposeMeasureScope.mo279roundToPx0680j_4(ScaffoldKt.FabSpacing);
                    iMo279roundToPx0680j_4 = (iM5646getMaxWidthimpl - iMo279roundToPx0680j_42) - width6;
                } else {
                    iMo279roundToPx0680j_4 = subcomposeMeasureScope.mo279roundToPx0680j_4(ScaffoldKt.FabSpacing);
                }
                fabPlacement = new FabPlacement(iMo279roundToPx0680j_4, width6, height9);
            } else if (subcomposeMeasureScope.getLayoutDirection() == LayoutDirection.Ltr) {
                iMo279roundToPx0680j_4 = subcomposeMeasureScope.mo279roundToPx0680j_4(ScaffoldKt.FabSpacing);
                fabPlacement = new FabPlacement(iMo279roundToPx0680j_4, width6, height9);
            } else {
                iMo279roundToPx0680j_42 = subcomposeMeasureScope.mo279roundToPx0680j_4(ScaffoldKt.FabSpacing);
                iMo279roundToPx0680j_4 = (iM5646getMaxWidthimpl - iMo279roundToPx0680j_42) - width6;
                fabPlacement = new FabPlacement(iMo279roundToPx0680j_4, width6, height9);
            }
        }
        List<Measurable> listSubcompose4 = subcomposeMeasureScope.subcompose(ScaffoldLayoutContent.BottomBar, ComposableLambdaKt.composableLambdaInstance(1843374446, true, new ScaffoldKt$ScaffoldLayoutWithMeasureFix$1$1$bottomBarPlaceables$1(fabPlacement, this.$bottomBar)));
        ArrayList arrayList5 = new ArrayList(listSubcompose4.size());
        int size4 = listSubcompose4.size();
        for (int i27 = 0; i27 < size4; i27++) {
            arrayList5.add(listSubcompose4.get(i27).mo4631measureBRTryo0(jM5637copyZbe2FdA$default));
        }
        if (arrayList5.isEmpty()) {
            obj4 = null;
        } else {
            obj4 = arrayList5.get(0);
            int height10 = ((Placeable) obj4).getHeight();
            int iW6 = t7.a.w(arrayList5);
            if (1 <= iW6) {
                int i28 = height10;
                int i29 = 1;
                while (true) {
                    Object obj16 = arrayList5.get(i29);
                    int height11 = ((Placeable) obj16).getHeight();
                    if (i28 < height11) {
                        i28 = height11;
                        obj4 = obj16;
                    }
                    if (i29 == iW6) {
                        break;
                    }
                    i29++;
                }
            }
        }
        Placeable placeable4 = (Placeable) obj4;
        Integer numValueOf = placeable4 != null ? Integer.valueOf(placeable4.getHeight()) : null;
        Integer numValueOf2 = fabPlacement != null ? Integer.valueOf((numValueOf == null || FabPosition.m1564equalsimpl0(this.$fabPosition, FabPosition.INSTANCE.m1570getEndOverlayERTFSPs())) ? this.$contentWindowInsets.getBottom(subcomposeMeasureScope) + subcomposeMeasureScope.mo279roundToPx0680j_4(ScaffoldKt.FabSpacing) + fabPlacement.getHeight() : subcomposeMeasureScope.mo279roundToPx0680j_4(ScaffoldKt.FabSpacing) + fabPlacement.getHeight() + numValueOf.intValue()) : null;
        int iIntValue = i21 != 0 ? i21 + (numValueOf2 != null ? numValueOf2.intValue() : numValueOf != null ? numValueOf.intValue() : this.$contentWindowInsets.getBottom(subcomposeMeasureScope)) : 0;
        FabPlacement fabPlacement2 = fabPlacement;
        List<Measurable> listSubcompose5 = subcomposeMeasureScope.subcompose(ScaffoldLayoutContent.MainContent, ComposableLambdaKt.composableLambdaInstance(1655277373, true, new ScaffoldKt$ScaffoldLayoutWithMeasureFix$1$1$bodyContentPlaceables$1(this.$contentWindowInsets, subcomposeMeasureScope, arrayList3, i10, arrayList5, numValueOf, this.$content)));
        ArrayList arrayList6 = new ArrayList(listSubcompose5.size());
        int size5 = listSubcompose5.size();
        for (int i30 = 0; i30 < size5; i30++) {
            arrayList6.add(listSubcompose5.get(i30).mo4631measureBRTryo0(jM5637copyZbe2FdA$default));
        }
        return MeasureScope.CC.q(subcomposeMeasureScope, iM5646getMaxWidthimpl, iM5645getMaxHeightimpl, null, new AnonymousClass1(arrayList6, arrayList3, arrayList2, arrayList5, fabPlacement2, iM5646getMaxWidthimpl, i18, this.$contentWindowInsets, subcomposeMeasureScope, iM5645getMaxHeightimpl, iIntValue, numValueOf, arrayList4, numValueOf2), 4, null);
    }
}

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
public final class ScaffoldKt$LegacyScaffoldLayout$1$1 extends r implements p<SubcomposeMeasureScope, Constraints, MeasureResult> {
    final /* synthetic */ p<Composer, Integer, t0> $bottomBar;
    final /* synthetic */ q<PaddingValues, Composer, Integer, t0> $content;
    final /* synthetic */ WindowInsets $contentWindowInsets;
    final /* synthetic */ p<Composer, Integer, t0> $fab;
    final /* synthetic */ int $fabPosition;
    final /* synthetic */ p<Composer, Integer, t0> $snackbar;
    final /* synthetic */ p<Composer, Integer, t0> $topBar;

    /* JADX INFO: renamed from: androidx.compose.material3.ScaffoldKt$LegacyScaffoldLayout$1$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements l<Placeable.PlacementScope, t0> {
        final /* synthetic */ p<Composer, Integer, t0> $bottomBar;
        final /* synthetic */ q<PaddingValues, Composer, Integer, t0> $content;
        final /* synthetic */ WindowInsets $contentWindowInsets;
        final /* synthetic */ p<Composer, Integer, t0> $fab;
        final /* synthetic */ int $fabPosition;
        final /* synthetic */ int $layoutHeight;
        final /* synthetic */ int $layoutWidth;
        final /* synthetic */ long $looseConstraints;
        final /* synthetic */ p<Composer, Integer, t0> $snackbar;
        final /* synthetic */ SubcomposeMeasureScope $this_SubcomposeLayout;
        final /* synthetic */ p<Composer, Integer, t0> $topBar;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(SubcomposeMeasureScope subcomposeMeasureScope, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, int i10, int i11, WindowInsets windowInsets, long j10, p<? super Composer, ? super Integer, t0> pVar4, q<? super PaddingValues, ? super Composer, ? super Integer, t0> qVar, int i12) {
            super(1);
            this.$this_SubcomposeLayout = subcomposeMeasureScope;
            this.$topBar = pVar;
            this.$snackbar = pVar2;
            this.$fab = pVar3;
            this.$fabPosition = i10;
            this.$layoutWidth = i11;
            this.$contentWindowInsets = windowInsets;
            this.$looseConstraints = j10;
            this.$bottomBar = pVar4;
            this.$content = qVar;
            this.$layoutHeight = i12;
        }

        @Override // r7.l
        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            invoke((Placeable.PlacementScope) obj);
            return t0.f22605a;
        }

        public final void invoke(Placeable.PlacementScope placementScope) {
            Object obj;
            Object obj2;
            Object obj3;
            FabPlacement fabPlacement;
            int i10;
            Object obj4;
            Integer numValueOf;
            Object obj5;
            Object obj6;
            int iMo279roundToPx0680j_4;
            int i11;
            int iMo279roundToPx0680j_42;
            List<Measurable> listSubcompose = this.$this_SubcomposeLayout.subcompose(ScaffoldLayoutContent.TopBar, this.$topBar);
            long j10 = this.$looseConstraints;
            ArrayList arrayList = new ArrayList(listSubcompose.size());
            int size = listSubcompose.size();
            for (int i12 = 0; i12 < size; i12++) {
                arrayList.add(listSubcompose.get(i12).mo4631measureBRTryo0(j10));
            }
            if (arrayList.isEmpty()) {
                obj = null;
            } else {
                obj = arrayList.get(0);
                int height = ((Placeable) obj).getHeight();
                int iW = t7.a.w(arrayList);
                if (1 <= iW) {
                    int i13 = 1;
                    while (true) {
                        Object obj7 = arrayList.get(i13);
                        int height2 = ((Placeable) obj7).getHeight();
                        if (height < height2) {
                            obj = obj7;
                            height = height2;
                        }
                        if (i13 == iW) {
                            break;
                        } else {
                            i13++;
                        }
                    }
                }
            }
            Placeable placeable = (Placeable) obj;
            int height3 = placeable != null ? placeable.getHeight() : 0;
            List<Measurable> listSubcompose2 = this.$this_SubcomposeLayout.subcompose(ScaffoldLayoutContent.Snackbar, this.$snackbar);
            WindowInsets windowInsets = this.$contentWindowInsets;
            SubcomposeMeasureScope subcomposeMeasureScope = this.$this_SubcomposeLayout;
            long j11 = this.$looseConstraints;
            ArrayList arrayList2 = new ArrayList(listSubcompose2.size());
            int size2 = listSubcompose2.size();
            for (int i14 = 0; i14 < size2; i14++) {
                arrayList2.add(listSubcompose2.get(i14).mo4631measureBRTryo0(ConstraintsKt.m5662offsetNN6EwU(j11, (-windowInsets.getLeft(subcomposeMeasureScope, subcomposeMeasureScope.getLayoutDirection())) - windowInsets.getRight(subcomposeMeasureScope, subcomposeMeasureScope.getLayoutDirection()), -windowInsets.getBottom(subcomposeMeasureScope))));
            }
            if (arrayList2.isEmpty()) {
                obj2 = null;
            } else {
                obj2 = arrayList2.get(0);
                int height4 = ((Placeable) obj2).getHeight();
                int iW2 = t7.a.w(arrayList2);
                if (1 <= iW2) {
                    int i15 = 1;
                    while (true) {
                        Object obj8 = arrayList2.get(i15);
                        int height5 = ((Placeable) obj8).getHeight();
                        if (height4 < height5) {
                            obj2 = obj8;
                            height4 = height5;
                        }
                        if (i15 == iW2) {
                            break;
                        } else {
                            i15++;
                        }
                    }
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
                    int i16 = 1;
                    while (true) {
                        Object obj9 = arrayList2.get(i16);
                        int width2 = ((Placeable) obj9).getWidth();
                        if (width < width2) {
                            obj3 = obj9;
                            width = width2;
                        }
                        if (i16 == iW3) {
                            break;
                        } else {
                            i16++;
                        }
                    }
                }
            }
            Placeable placeable3 = (Placeable) obj3;
            int width3 = placeable3 != null ? placeable3.getWidth() : 0;
            List<Measurable> listSubcompose3 = this.$this_SubcomposeLayout.subcompose(ScaffoldLayoutContent.Fab, this.$fab);
            WindowInsets windowInsets2 = this.$contentWindowInsets;
            SubcomposeMeasureScope subcomposeMeasureScope2 = this.$this_SubcomposeLayout;
            long j12 = this.$looseConstraints;
            ArrayList arrayList3 = new ArrayList(listSubcompose3.size());
            int size3 = listSubcompose3.size();
            int i17 = 0;
            while (i17 < size3) {
                int i18 = height6;
                int i19 = width3;
                Placeable placeableMo4631measureBRTryo0 = listSubcompose3.get(i17).mo4631measureBRTryo0(ConstraintsKt.m5662offsetNN6EwU(j12, (-windowInsets2.getLeft(subcomposeMeasureScope2, subcomposeMeasureScope2.getLayoutDirection())) - windowInsets2.getRight(subcomposeMeasureScope2, subcomposeMeasureScope2.getLayoutDirection()), -windowInsets2.getBottom(subcomposeMeasureScope2)));
                if (placeableMo4631measureBRTryo0.getHeight() == 0 || placeableMo4631measureBRTryo0.getWidth() == 0) {
                    placeableMo4631measureBRTryo0 = null;
                }
                if (placeableMo4631measureBRTryo0 != null) {
                    arrayList3.add(placeableMo4631measureBRTryo0);
                }
                i17++;
                height6 = i18;
                width3 = i19;
            }
            int i20 = height6;
            int i21 = width3;
            if (arrayList3.isEmpty()) {
                fabPlacement = null;
            } else {
                if (arrayList3.isEmpty()) {
                    obj5 = null;
                } else {
                    obj5 = arrayList3.get(0);
                    int width4 = ((Placeable) obj5).getWidth();
                    int iW4 = t7.a.w(arrayList3);
                    if (1 <= iW4) {
                        int i22 = 1;
                        while (true) {
                            Object obj10 = arrayList3.get(i22);
                            int width5 = ((Placeable) obj10).getWidth();
                            if (width4 < width5) {
                                obj5 = obj10;
                                width4 = width5;
                            }
                            if (i22 == iW4) {
                                break;
                            } else {
                                i22++;
                            }
                        }
                    }
                }
                int width6 = ((Placeable) obj5).getWidth();
                if (arrayList3.isEmpty()) {
                    obj6 = null;
                } else {
                    obj6 = arrayList3.get(0);
                    int height7 = ((Placeable) obj6).getHeight();
                    int iW5 = t7.a.w(arrayList3);
                    if (1 <= iW5) {
                        int i23 = 1;
                        while (true) {
                            Object obj11 = arrayList3.get(i23);
                            int height8 = ((Placeable) obj11).getHeight();
                            if (height7 < height8) {
                                obj6 = obj11;
                                height7 = height8;
                            }
                            if (i23 == iW5) {
                                break;
                            } else {
                                i23++;
                            }
                        }
                    }
                }
                int height9 = ((Placeable) obj6).getHeight();
                int i24 = this.$fabPosition;
                FabPosition.Companion companion = FabPosition.INSTANCE;
                if (!FabPosition.m1564equalsimpl0(i24, companion.m1571getStartERTFSPs())) {
                    if (!FabPosition.m1564equalsimpl0(i24, companion.m1569getEndERTFSPs())) {
                        iMo279roundToPx0680j_4 = (this.$layoutWidth - width6) / 2;
                    } else if (this.$this_SubcomposeLayout.getLayoutDirection() == LayoutDirection.Ltr) {
                        i11 = this.$layoutWidth;
                        iMo279roundToPx0680j_42 = this.$this_SubcomposeLayout.mo279roundToPx0680j_4(ScaffoldKt.FabSpacing);
                        iMo279roundToPx0680j_4 = (i11 - iMo279roundToPx0680j_42) - width6;
                    } else {
                        iMo279roundToPx0680j_4 = this.$this_SubcomposeLayout.mo279roundToPx0680j_4(ScaffoldKt.FabSpacing);
                    }
                    fabPlacement = new FabPlacement(iMo279roundToPx0680j_4, width6, height9);
                } else if (this.$this_SubcomposeLayout.getLayoutDirection() == LayoutDirection.Ltr) {
                    iMo279roundToPx0680j_4 = this.$this_SubcomposeLayout.mo279roundToPx0680j_4(ScaffoldKt.FabSpacing);
                    fabPlacement = new FabPlacement(iMo279roundToPx0680j_4, width6, height9);
                } else {
                    i11 = this.$layoutWidth;
                    iMo279roundToPx0680j_42 = this.$this_SubcomposeLayout.mo279roundToPx0680j_4(ScaffoldKt.FabSpacing);
                    iMo279roundToPx0680j_4 = (i11 - iMo279roundToPx0680j_42) - width6;
                    fabPlacement = new FabPlacement(iMo279roundToPx0680j_4, width6, height9);
                }
            }
            List<Measurable> listSubcompose4 = this.$this_SubcomposeLayout.subcompose(ScaffoldLayoutContent.BottomBar, ComposableLambdaKt.composableLambdaInstance(-791102355, true, new ScaffoldKt$LegacyScaffoldLayout$1$1$1$bottomBarPlaceables$1(fabPlacement, this.$bottomBar)));
            long j13 = this.$looseConstraints;
            ArrayList arrayList4 = new ArrayList(listSubcompose4.size());
            int size4 = listSubcompose4.size();
            for (int i25 = 0; i25 < size4; i25++) {
                arrayList4.add(listSubcompose4.get(i25).mo4631measureBRTryo0(j13));
            }
            if (arrayList4.isEmpty()) {
                i10 = 0;
                obj4 = null;
            } else {
                i10 = 0;
                obj4 = arrayList4.get(0);
                int height10 = ((Placeable) obj4).getHeight();
                int iW6 = t7.a.w(arrayList4);
                if (1 <= iW6) {
                    int i26 = height10;
                    int i27 = 1;
                    while (true) {
                        Object obj12 = arrayList4.get(i27);
                        int height11 = ((Placeable) obj12).getHeight();
                        if (i26 < height11) {
                            obj4 = obj12;
                            i26 = height11;
                        }
                        if (i27 == iW6) {
                            break;
                        } else {
                            i27++;
                        }
                    }
                }
            }
            Placeable placeable4 = (Placeable) obj4;
            Integer numValueOf2 = placeable4 != null ? Integer.valueOf(placeable4.getHeight()) : null;
            if (fabPlacement != null) {
                SubcomposeMeasureScope subcomposeMeasureScope3 = this.$this_SubcomposeLayout;
                numValueOf = Integer.valueOf(numValueOf2 == null ? this.$contentWindowInsets.getBottom(subcomposeMeasureScope3) + subcomposeMeasureScope3.mo279roundToPx0680j_4(ScaffoldKt.FabSpacing) + fabPlacement.getHeight() : subcomposeMeasureScope3.mo279roundToPx0680j_4(ScaffoldKt.FabSpacing) + fabPlacement.getHeight() + numValueOf2.intValue());
            } else {
                numValueOf = null;
            }
            int iIntValue = i20 != 0 ? i20 + (numValueOf != null ? numValueOf.intValue() : numValueOf2 != null ? numValueOf2.intValue() : this.$contentWindowInsets.getBottom(this.$this_SubcomposeLayout)) : i10;
            SubcomposeMeasureScope subcomposeMeasureScope4 = this.$this_SubcomposeLayout;
            List<Measurable> listSubcompose5 = subcomposeMeasureScope4.subcompose(ScaffoldLayoutContent.MainContent, ComposableLambdaKt.composableLambdaInstance(495329982, true, new ScaffoldKt$LegacyScaffoldLayout$1$1$1$bodyContentPlaceables$1(this.$contentWindowInsets, subcomposeMeasureScope4, arrayList, height3, arrayList4, numValueOf2, this.$content)));
            long j14 = this.$looseConstraints;
            ArrayList arrayList5 = new ArrayList(listSubcompose5.size());
            int size5 = listSubcompose5.size();
            for (int i28 = i10; i28 < size5; i28++) {
                arrayList5.add(listSubcompose5.get(i28).mo4631measureBRTryo0(j14));
            }
            int size6 = arrayList5.size();
            for (int i29 = i10; i29 < size6; i29++) {
                Placeable.PlacementScope.place$default(placementScope, (Placeable) arrayList5.get(i29), 0, 0, 0.0f, 4, null);
            }
            int size7 = arrayList.size();
            for (int i30 = i10; i30 < size7; i30++) {
                Placeable.PlacementScope.place$default(placementScope, (Placeable) arrayList.get(i30), 0, 0, 0.0f, 4, null);
            }
            int i31 = this.$layoutWidth;
            WindowInsets windowInsets3 = this.$contentWindowInsets;
            SubcomposeMeasureScope subcomposeMeasureScope5 = this.$this_SubcomposeLayout;
            int i32 = this.$layoutHeight;
            int size8 = arrayList2.size();
            for (int i33 = i10; i33 < size8; i33++) {
                Placeable.PlacementScope.place$default(placementScope, (Placeable) arrayList2.get(i33), windowInsets3.getLeft(subcomposeMeasureScope5, subcomposeMeasureScope5.getLayoutDirection()) + ((i31 - i21) / 2), i32 - iIntValue, 0.0f, 4, null);
            }
            int i34 = this.$layoutHeight;
            int size9 = arrayList4.size();
            for (int i35 = i10; i35 < size9; i35++) {
                Placeable.PlacementScope.place$default(placementScope, (Placeable) arrayList4.get(i35), 0, i34 - (numValueOf2 != null ? numValueOf2.intValue() : i10), 0.0f, 4, null);
            }
            if (fabPlacement != null) {
                int i36 = this.$layoutHeight;
                int size10 = arrayList3.size();
                for (int i37 = i10; i37 < size10; i37++) {
                    Placeable.PlacementScope.place$default(placementScope, (Placeable) arrayList3.get(i37), fabPlacement.getLeft(), i36 - numValueOf.intValue(), 0.0f, 4, null);
                }
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ScaffoldKt$LegacyScaffoldLayout$1$1(p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, int i10, WindowInsets windowInsets, p<? super Composer, ? super Integer, t0> pVar4, q<? super PaddingValues, ? super Composer, ? super Integer, t0> qVar) {
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
        return m1804invoke0kLqBqw((SubcomposeMeasureScope) obj, ((Constraints) obj2).getValue());
    }

    /* JADX INFO: renamed from: invoke-0kLqBqw, reason: not valid java name */
    public final MeasureResult m1804invoke0kLqBqw(SubcomposeMeasureScope subcomposeMeasureScope, long j10) {
        int iM5646getMaxWidthimpl = Constraints.m5646getMaxWidthimpl(j10);
        int iM5645getMaxHeightimpl = Constraints.m5645getMaxHeightimpl(j10);
        return MeasureScope.CC.q(subcomposeMeasureScope, iM5646getMaxWidthimpl, iM5645getMaxHeightimpl, null, new AnonymousClass1(subcomposeMeasureScope, this.$topBar, this.$snackbar, this.$fab, this.$fabPosition, iM5646getMaxWidthimpl, this.$contentWindowInsets, Constraints.m5637copyZbe2FdA$default(j10, 0, 0, 0, 0, 10, null), this.$bottomBar, this.$content, iM5645getMaxHeightimpl), 4, null);
    }
}

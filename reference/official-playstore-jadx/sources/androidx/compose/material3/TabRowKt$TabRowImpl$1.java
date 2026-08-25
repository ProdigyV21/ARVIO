package androidx.compose.material3;

import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.MultiContentMeasurePolicyKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.i;
import androidx.compose.ui.node.ComposeUiNode;
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
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class TabRowKt$TabRowImpl$1 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ p<Composer, Integer, t0> $divider;
    final /* synthetic */ q<TabIndicatorScope, Composer, Integer, t0> $indicator;
    final /* synthetic */ p<Composer, Integer, t0> $tabs;

    /* JADX INFO: renamed from: androidx.compose.material3.TabRowKt$TabRowImpl$1$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass1 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ q<TabIndicatorScope, Composer, Integer, t0> $indicator;
        final /* synthetic */ TabRowKt$TabRowImpl$1$scope$1$1 $scope;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(q<? super TabIndicatorScope, ? super Composer, ? super Integer, t0> qVar, TabRowKt$TabRowImpl$1$scope$1$1 tabRowKt$TabRowImpl$1$scope$1$1) {
            super(2);
            this.$indicator = qVar;
            this.$scope = tabRowKt$TabRowImpl$1$scope$1$1;
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
                ComposerKt.traceEventStart(1236693605, i10, -1, "androidx.compose.material3.TabRowImpl.<anonymous>.<anonymous> (TabRow.kt:414)");
            }
            this.$indicator.invoke(this.$scope, composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public TabRowKt$TabRowImpl$1(p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, q<? super TabIndicatorScope, ? super Composer, ? super Integer, t0> qVar) {
        super(2);
        this.$tabs = pVar;
        this.$divider = pVar2;
        this.$indicator = qVar;
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
            ComposerKt.traceEventStart(-65106680, i10, -1, "androidx.compose.material3.TabRowImpl.<anonymous> (TabRow.kt:371)");
        }
        composer.startReplaceableGroup(474062752);
        Object objRememberedValue = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        if (objRememberedValue == companion.getEmpty()) {
            objRememberedValue = new TabRowKt$TabRowImpl$1$scope$1$1();
            composer.updateRememberedValue(objRememberedValue);
        }
        final TabRowKt$TabRowImpl$1$scope$1$1 tabRowKt$TabRowImpl$1$scope$1$1 = (TabRowKt$TabRowImpl$1$scope$1$1) objRememberedValue;
        composer.endReplaceableGroup();
        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
        List listE = t7.a.E(this.$tabs, this.$divider, ComposableLambdaKt.composableLambda(composer, 1236693605, true, new AnonymousClass1(this.$indicator, tabRowKt$TabRowImpl$1$scope$1$1)));
        composer.startReplaceableGroup(474064303);
        Object objRememberedValue2 = composer.rememberedValue();
        if (objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = new MultiContentMeasurePolicy() { // from class: androidx.compose.material3.TabRowKt$TabRowImpl$1$2$1

                /* JADX INFO: renamed from: androidx.compose.material3.TabRowKt$TabRowImpl$1$2$1$2, reason: invalid class name */
                @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                public static final class AnonymousClass2 extends r implements l<Placeable.PlacementScope, t0> {
                    final /* synthetic */ List<Placeable> $dividerPlaceables;
                    final /* synthetic */ List<Placeable> $indicatorPlaceables;
                    final /* synthetic */ List<Placeable> $tabPlaceables;
                    final /* synthetic */ int $tabRowHeight;
                    final /* synthetic */ i0 $tabWidth;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    public AnonymousClass2(List<? extends Placeable> list, List<? extends Placeable> list2, List<? extends Placeable> list3, i0 i0Var, int i10) {
                        super(1);
                        this.$tabPlaceables = list;
                        this.$dividerPlaceables = list2;
                        this.$indicatorPlaceables = list3;
                        this.$tabWidth = i0Var;
                        this.$tabRowHeight = i10;
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
                        List<Placeable> list2 = this.$dividerPlaceables;
                        int i11 = this.$tabRowHeight;
                        int size2 = list2.size();
                        for (int i12 = 0; i12 < size2; i12++) {
                            Placeable placeable = list2.get(i12);
                            Placeable.PlacementScope.placeRelative$default(placementScope, placeable, 0, i11 - placeable.getHeight(), 0.0f, 4, null);
                        }
                        List<Placeable> list3 = this.$indicatorPlaceables;
                        int i13 = this.$tabRowHeight;
                        int size3 = list3.size();
                        for (int i14 = 0; i14 < size3; i14++) {
                            Placeable placeable2 = list3.get(i14);
                            Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, 0, i13 - placeable2.getHeight(), 0.0f, 4, null);
                        }
                    }
                }

                @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
                public final /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i11) {
                    return i.a(this, intrinsicMeasureScope, list, i11);
                }

                @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
                public final /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i11) {
                    return i.b(this, intrinsicMeasureScope, list, i11);
                }

                @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
                /* JADX INFO: renamed from: measure-3p2s80s */
                public final MeasureResult mo1658measure3p2s80s(MeasureScope measureScope, List<? extends List<? extends Measurable>> list, long j10) {
                    MeasureScope measureScope2 = measureScope;
                    List<? extends Measurable> list2 = list.get(0);
                    List<? extends Measurable> list3 = list.get(1);
                    int i11 = 2;
                    List<? extends Measurable> list4 = list.get(2);
                    int iM5646getMaxWidthimpl = Constraints.m5646getMaxWidthimpl(j10);
                    int size = list2.size();
                    i0 i0Var = new i0();
                    if (size > 0) {
                        i0Var.f19744i = iM5646getMaxWidthimpl / size;
                    }
                    Integer numValueOf = 0;
                    int size2 = list2.size();
                    for (int i12 = 0; i12 < size2; i12++) {
                        numValueOf = Integer.valueOf(Math.max(list2.get(i12).maxIntrinsicHeight(i0Var.f19744i), numValueOf.intValue()));
                    }
                    int iIntValue = numValueOf.intValue();
                    TabRowKt$TabRowImpl$1$scope$1$1 tabRowKt$TabRowImpl$1$scope$1$12 = tabRowKt$TabRowImpl$1$scope$1$1;
                    ArrayList arrayList = new ArrayList(size);
                    int i13 = 0;
                    while (i13 < size) {
                        Dp dpM5676boximpl = Dp.m5676boximpl(Dp.m5678constructorimpl(measureScope2.mo282toDpu2uoSUM(Math.min(list2.get(i13).maxIntrinsicWidth(iIntValue), i0Var.f19744i)) - Dp.m5678constructorimpl(TabKt.getHorizontalTextPadding() * i11)));
                        Dp dpM5676boximpl2 = Dp.m5676boximpl(Dp.m5678constructorimpl(24));
                        if (dpM5676boximpl.compareTo(dpM5676boximpl2) < 0) {
                            dpM5676boximpl = dpM5676boximpl2;
                        }
                        arrayList.add(new TabPosition(Dp.m5678constructorimpl(measureScope2.mo282toDpu2uoSUM(i0Var.f19744i) * i13), measureScope2.mo282toDpu2uoSUM(i0Var.f19744i), dpM5676boximpl.m5692unboximpl(), null));
                        i13++;
                        measureScope2 = measureScope;
                        i11 = 2;
                    }
                    tabRowKt$TabRowImpl$1$scope$1$12.setTabPositions(arrayList);
                    ArrayList arrayList2 = new ArrayList(list2.size());
                    int size3 = list2.size();
                    for (int i14 = 0; i14 < size3; i14++) {
                        Measurable measurable = list2.get(i14);
                        int i15 = i0Var.f19744i;
                        int i16 = iIntValue;
                        long jM5636copyZbe2FdA = Constraints.m5636copyZbe2FdA(j10, i15, i15, i16, iIntValue);
                        iIntValue = i16;
                        arrayList2.add(measurable.mo4631measureBRTryo0(jM5636copyZbe2FdA));
                    }
                    ArrayList arrayList3 = new ArrayList(list3.size());
                    int size4 = list3.size();
                    for (int i17 = 0; i17 < size4; i17++) {
                        arrayList3.add(list3.get(i17).mo4631measureBRTryo0(Constraints.m5637copyZbe2FdA$default(j10, 0, 0, 0, 0, 11, null)));
                    }
                    ArrayList arrayList4 = new ArrayList(list4.size());
                    int size5 = list4.size();
                    for (int i18 = 0; i18 < size5; i18++) {
                        Measurable measurable2 = list4.get(i18);
                        int i19 = i0Var.f19744i;
                        int i20 = iIntValue;
                        iIntValue = i20;
                        arrayList4.add(measurable2.mo4631measureBRTryo0(Constraints.m5637copyZbe2FdA$default(j10, i19, i19, 0, i20, 4, null)));
                    }
                    return MeasureScope.CC.q(measureScope, iM5646getMaxWidthimpl, iIntValue, null, new AnonymousClass2(arrayList2, arrayList3, arrayList4, i0Var, iIntValue), 4, null);
                }

                @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
                public final /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i11) {
                    return i.c(this, intrinsicMeasureScope, list, i11);
                }

                @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
                public final /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i11) {
                    return i.d(this, intrinsicMeasureScope, list, i11);
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        MultiContentMeasurePolicy multiContentMeasurePolicy = (MultiContentMeasurePolicy) objRememberedValue2;
        composer.endReplaceableGroup();
        composer.startReplaceableGroup(1399185516);
        p<Composer, Integer, t0> pVarCombineAsVirtualLayouts = LayoutKt.combineAsVirtualLayouts(listE);
        composer.startReplaceableGroup(1157296644);
        boolean zChanged = composer.changed(multiContentMeasurePolicy);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChanged || objRememberedValue3 == companion.getEmpty()) {
            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicy);
            composer.updateRememberedValue(objRememberedValue3);
        }
        composer.endReplaceableGroup();
        MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue3;
        composer.startReplaceableGroup(-1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor = companion2.getConstructor();
        q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierFillMaxWidth$default);
        if (composer.getApplier() == null) {
            ComposablesKt.invalidApplier();
        }
        composer.startReusableNode();
        if (composer.getInserting()) {
            composer.createNode(constructor);
        } else {
            composer.useNode();
        }
        Composer composerM2991constructorimpl = Updater.m2991constructorimpl(composer);
        p pVarU = a0.c.u(companion2, composerM2991constructorimpl, measurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
        }
        a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
        if (a0.c.B(pVarCombineAsVirtualLayouts, composer, 0)) {
            ComposerKt.traceEventEnd();
        }
    }
}

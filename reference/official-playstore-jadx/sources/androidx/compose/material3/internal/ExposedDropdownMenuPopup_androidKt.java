package androidx.compose.material3.internal;

import a0.c;
import android.view.View;
import androidx.compose.material3.TouchExplorationStateProvider_androidKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.OnGloballyPositionedModifierKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.g;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.PopupPositionProvider;
import androidx.media3.exoplayer.RendererCapabilities;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.a;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a<\u0010\u0007\u001a\u00020\u00012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u0005H\u0001¢\u0006\u0004\b\u0007\u0010\b\u001a-\u0010\u000b\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\t2\u0013\b\b\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u0005H\u0083\b¢\u0006\u0004\b\u000b\u0010\f¨\u0006\u0010²\u0006\f\u0010\u000e\u001a\u00020\r8\nX\u008a\u0084\u0002²\u0006\u0017\u0010\u000f\u001a\r\u0012\u0004\u0012\u00020\u00010\u0000¢\u0006\u0002\b\u00058\nX\u008a\u0084\u0002"}, d2 = {"Lkotlin/Function0;", "Lx6/t0;", "onDismissRequest", "Landroidx/compose/ui/window/PopupPositionProvider;", "popupPositionProvider", "Landroidx/compose/runtime/Composable;", "content", "ExposedDropdownMenuPopup", "(Lr7/a;Landroidx/compose/ui/window/PopupPositionProvider;Lr7/p;Landroidx/compose/runtime/Composer;II)V", "Landroidx/compose/ui/Modifier;", "modifier", "SimpleStack", "(Landroidx/compose/ui/Modifier;Lr7/p;Landroidx/compose/runtime/Composer;I)V", "", "isTouchExplorationEnabled", "currentContent", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ExposedDropdownMenuPopup_androidKt {

    /* JADX INFO: renamed from: androidx.compose.material3.internal.ExposedDropdownMenuPopup_androidKt$ExposedDropdownMenuPopup$6, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass6 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ p<Composer, Integer, t0> $content;
        final /* synthetic */ a<t0> $onDismissRequest;
        final /* synthetic */ PopupPositionProvider $popupPositionProvider;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass6(a<t0> aVar, PopupPositionProvider popupPositionProvider, p<? super Composer, ? super Integer, t0> pVar, int i10, int i11) {
            super(2);
            this.$onDismissRequest = aVar;
            this.$popupPositionProvider = popupPositionProvider;
            this.$content = pVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            ExposedDropdownMenuPopup_androidKt.ExposedDropdownMenuPopup(this.$onDismissRequest, this.$popupPositionProvider, this.$content, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.internal.ExposedDropdownMenuPopup_androidKt$SimpleStack$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0004\b\b\u0010\t"}, d2 = {"<anonymous>", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1 implements MeasurePolicy {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        /* JADX INFO: renamed from: androidx.compose.material3.internal.ExposedDropdownMenuPopup_androidKt$SimpleStack$1$1, reason: invalid class name and collision with other inner class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class C01171 extends r implements l<Placeable.PlacementScope, t0> {
            public static final C01171 INSTANCE = new C01171();

            public C01171() {
                super(1);
            }

            public final void invoke(Placeable.PlacementScope placementScope) {
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Placeable.PlacementScope) obj);
                return t0.f22605a;
            }
        }

        /* JADX INFO: renamed from: androidx.compose.material3.internal.ExposedDropdownMenuPopup_androidKt$SimpleStack$1$2, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass2 extends r implements l<Placeable.PlacementScope, t0> {
            final /* synthetic */ Placeable $p;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AnonymousClass2(Placeable placeable) {
                super(1);
                this.$p = placeable;
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Placeable.PlacementScope) obj);
                return t0.f22605a;
            }

            public final void invoke(Placeable.PlacementScope placementScope) {
                Placeable.PlacementScope.placeRelative$default(placementScope, this.$p, 0, 0, 0.0f, 4, null);
            }
        }

        /* JADX INFO: renamed from: androidx.compose.material3.internal.ExposedDropdownMenuPopup_androidKt$SimpleStack$1$3, reason: invalid class name */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
        public static final class AnonymousClass3 extends r implements l<Placeable.PlacementScope, t0> {
            final /* synthetic */ List<Placeable> $placeables;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass3(List<? extends Placeable> list) {
                super(1);
                this.$placeables = list;
            }

            @Override // r7.l
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Placeable.PlacementScope) obj);
                return t0.f22605a;
            }

            public final void invoke(Placeable.PlacementScope placementScope) {
                int iW = t7.a.w(this.$placeables);
                if (iW < 0) {
                    return;
                }
                int i10 = 0;
                while (true) {
                    Placeable.PlacementScope placementScope2 = placementScope;
                    Placeable.PlacementScope.placeRelative$default(placementScope2, this.$placeables.get(i10), 0, 0, 0.0f, 4, null);
                    if (i10 == iW) {
                        return;
                    }
                    i10++;
                    placementScope = placementScope2;
                }
            }
        }

        @Override // androidx.compose.ui.layout.MeasurePolicy
        public final /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i10) {
            return g.a(this, intrinsicMeasureScope, list, i10);
        }

        @Override // androidx.compose.ui.layout.MeasurePolicy
        public final /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i10) {
            return g.b(this, intrinsicMeasureScope, list, i10);
        }

        @Override // androidx.compose.ui.layout.MeasurePolicy
        /* JADX INFO: renamed from: measure-3p2s80s */
        public final MeasureResult mo5measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j10) {
            int i10;
            int i11;
            int size = list.size();
            if (size == 0) {
                return MeasureScope.CC.q(measureScope, 0, 0, null, C01171.INSTANCE, 4, null);
            }
            int i12 = 0;
            if (size == 1) {
                Placeable placeableMo4631measureBRTryo0 = list.get(0).mo4631measureBRTryo0(j10);
                return MeasureScope.CC.q(measureScope, placeableMo4631measureBRTryo0.getWidth(), placeableMo4631measureBRTryo0.getHeight(), null, new AnonymousClass2(placeableMo4631measureBRTryo0), 4, null);
            }
            ArrayList arrayList = new ArrayList(list.size());
            int size2 = list.size();
            for (int i13 = 0; i13 < size2; i13++) {
                arrayList.add(list.get(i13).mo4631measureBRTryo0(j10));
            }
            int iW = t7.a.w(arrayList);
            if (iW >= 0) {
                int iMax = 0;
                int iMax2 = 0;
                while (true) {
                    Placeable placeable = (Placeable) arrayList.get(i12);
                    iMax = Math.max(iMax, placeable.getWidth());
                    iMax2 = Math.max(iMax2, placeable.getHeight());
                    if (i12 == iW) {
                        break;
                    }
                    i12++;
                }
                i10 = iMax;
                i11 = iMax2;
            } else {
                i10 = 0;
                i11 = 0;
            }
            return MeasureScope.CC.q(measureScope, i10, i11, null, new AnonymousClass3(arrayList), 4, null);
        }

        @Override // androidx.compose.ui.layout.MeasurePolicy
        public final /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i10) {
            return g.c(this, intrinsicMeasureScope, list, i10);
        }

        @Override // androidx.compose.ui.layout.MeasurePolicy
        public final /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i10) {
            return g.d(this, intrinsicMeasureScope, list, i10);
        }
    }

    public static final void ExposedDropdownMenuPopup(a<t0> aVar, PopupPositionProvider popupPositionProvider, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10, int i11) {
        a<t0> aVar2;
        int i12;
        PopupPositionProvider popupPositionProvider2;
        a<t0> aVar3;
        final LayoutDirection layoutDirection;
        int i13;
        a<t0> aVar4;
        Composer composerStartRestartGroup = composer.startRestartGroup(-727958629);
        int i14 = i11 & 1;
        if (i14 != 0) {
            i12 = i10 | 6;
            aVar2 = aVar;
        } else if ((i10 & 6) == 0) {
            aVar2 = aVar;
            i12 = (composerStartRestartGroup.changedInstance(aVar2) ? 4 : 2) | i10;
        } else {
            aVar2 = aVar;
            i12 = i10;
        }
        if ((i11 & 2) != 0) {
            i12 |= 48;
            popupPositionProvider2 = popupPositionProvider;
        } else {
            popupPositionProvider2 = popupPositionProvider;
            if ((i10 & 48) == 0) {
                i12 |= composerStartRestartGroup.changed(popupPositionProvider2) ? 32 : 16;
            }
        }
        if ((i11 & 4) != 0) {
            i12 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if ((i10 & RendererCapabilities.DECODER_SUPPORT_MASK) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar) ? 256 : 128;
        }
        int i15 = i12;
        if ((i15 & 147) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            aVar4 = aVar2;
        } else {
            a<t0> aVar5 = i14 != 0 ? null : aVar2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-727958629, i15, -1, "androidx.compose.material3.internal.ExposedDropdownMenuPopup (ExposedDropdownMenuPopup.android.kt:84)");
            }
            View view = (View) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalView());
            Density density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
            LayoutDirection layoutDirection2 = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
            State<Boolean> state = TouchExplorationStateProvider_androidKt.touchExplorationState(composerStartRestartGroup, 0);
            CompositionContext compositionContextRememberCompositionContext = ComposablesKt.rememberCompositionContext(composerStartRestartGroup, 0);
            State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(pVar, composerStartRestartGroup, (i15 >> 6) & 14);
            UUID uuid = (UUID) RememberSaveableKt.m3084rememberSaveable(new Object[0], (Saver) null, (String) null, (a) ExposedDropdownMenuPopup_androidKt$ExposedDropdownMenuPopup$popupId$1.INSTANCE, composerStartRestartGroup, 3072, 6);
            boolean zExposedDropdownMenuPopup$lambda$0 = ExposedDropdownMenuPopup$lambda$0(state);
            composerStartRestartGroup.startReplaceableGroup(-246571242);
            boolean zChanged = composerStartRestartGroup.changed(zExposedDropdownMenuPopup$lambda$0);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                aVar3 = aVar5;
                layoutDirection = layoutDirection2;
                i13 = i15;
                PopupLayout popupLayout = new PopupLayout(aVar3, view, popupPositionProvider2, ExposedDropdownMenuPopup$lambda$0(state), density, uuid);
                popupLayout.setContent(compositionContextRememberCompositionContext, ComposableLambdaKt.composableLambdaInstance(-493861435, true, new ExposedDropdownMenuPopup_androidKt$ExposedDropdownMenuPopup$popupLayout$1$1$1(popupLayout, stateRememberUpdatedState)));
                composerStartRestartGroup.updateRememberedValue(popupLayout);
                objRememberedValue = popupLayout;
            } else {
                aVar3 = aVar5;
                i13 = i15;
                layoutDirection = layoutDirection2;
            }
            final PopupLayout popupLayout2 = (PopupLayout) objRememberedValue;
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.startReplaceableGroup(-246570242);
            int i16 = i13 & 14;
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(popupLayout2) | (i16 == 4) | composerStartRestartGroup.changed(layoutDirection);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new ExposedDropdownMenuPopup_androidKt$ExposedDropdownMenuPopup$1$1(popupLayout2, aVar3, layoutDirection);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            composerStartRestartGroup.endReplaceableGroup();
            EffectsKt.DisposableEffect(popupLayout2, (l<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue2, composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(-246569906);
            boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(popupLayout2) | (i16 == 4) | composerStartRestartGroup.changed(layoutDirection);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new ExposedDropdownMenuPopup_androidKt$ExposedDropdownMenuPopup$2$1(popupLayout2, aVar3, layoutDirection);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            composerStartRestartGroup.endReplaceableGroup();
            EffectsKt.SideEffect((a) objRememberedValue3, composerStartRestartGroup, 0);
            Modifier.Companion companion = Modifier.INSTANCE;
            composerStartRestartGroup.startReplaceableGroup(-246569499);
            boolean zChangedInstance3 = composerStartRestartGroup.changedInstance(popupLayout2);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = new ExposedDropdownMenuPopup_androidKt$ExposedDropdownMenuPopup$4$1(popupLayout2);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            composerStartRestartGroup.endReplaceableGroup();
            Modifier modifierOnGloballyPositioned = OnGloballyPositionedModifierKt.onGloballyPositioned(companion, (l) objRememberedValue4);
            composerStartRestartGroup.startReplaceableGroup(-246569030);
            boolean zChangedInstance4 = composerStartRestartGroup.changedInstance(popupLayout2) | composerStartRestartGroup.changed(layoutDirection);
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance4 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue5 = new MeasurePolicy() { // from class: androidx.compose.material3.internal.ExposedDropdownMenuPopup_androidKt$ExposedDropdownMenuPopup$5$1

                    /* JADX INFO: renamed from: androidx.compose.material3.internal.ExposedDropdownMenuPopup_androidKt$ExposedDropdownMenuPopup$5$1$1, reason: invalid class name */
                    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
                    public static final class AnonymousClass1 extends r implements l<Placeable.PlacementScope, t0> {
                        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

                        public AnonymousClass1() {
                            super(1);
                        }

                        public final void invoke(Placeable.PlacementScope placementScope) {
                        }

                        @Override // r7.l
                        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                            invoke((Placeable.PlacementScope) obj);
                            return t0.f22605a;
                        }
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i17) {
                        return g.a(this, intrinsicMeasureScope, list, i17);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i17) {
                        return g.b(this, intrinsicMeasureScope, list, i17);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s */
                    public final MeasureResult mo5measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j10) {
                        popupLayout2.setParentLayoutDirection(layoutDirection);
                        return MeasureScope.CC.q(measureScope, 0, 0, null, AnonymousClass1.INSTANCE, 4, null);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i17) {
                        return g.c(this, intrinsicMeasureScope, list, i17);
                    }

                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    public final /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i17) {
                        return g.d(this, intrinsicMeasureScope, list, i17);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue5;
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            a<ComposeUiNode> constructor = companion2.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierOnGloballyPositioned);
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
            p pVarU = c.u(companion2, composerM2991constructorimpl, measurePolicy, composerM2991constructorimpl, currentCompositionLocalMap);
            if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, pVarU);
            }
            qVarModifierMaterializerOf.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(2058660585);
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            aVar4 = aVar3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass6(aVar4, popupPositionProvider, pVar, i10, i11));
        }
    }

    private static final boolean ExposedDropdownMenuPopup$lambda$0(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final p<Composer, Integer, t0> ExposedDropdownMenuPopup$lambda$1(State<? extends p<? super Composer, ? super Integer, t0>> state) {
        return (p) state.getValue();
    }

    private static final void SimpleStack(Modifier modifier, p<? super Composer, ? super Integer, t0> pVar, Composer composer, int i10) {
        composer.startReplaceableGroup(437877758);
        AnonymousClass1 anonymousClass1 = AnonymousClass1.INSTANCE;
        composer.startReplaceableGroup(-1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
        a<ComposeUiNode> constructor = companion.getConstructor();
        q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifier);
        int i11 = (((((i10 << 3) & 112) | ((i10 >> 3) & 14)) << 9) & 7168) | 6;
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
        Updater.m2998setimpl(composerM2991constructorimpl, anonymousClass1, companion.getSetMeasurePolicy());
        Updater.m2998setimpl(composerM2991constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
        p<ComposeUiNode, Integer, t0> setCompositeKeyHash = companion.getSetCompositeKeyHash();
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, setCompositeKeyHash);
        }
        c.x(0, qVarModifierMaterializerOf, SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 2058660585);
        pVar.invoke(composer, Integer.valueOf((i11 >> 9) & 14));
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
    }
}

package androidx.compose.material3;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
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
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.l;
import r7.p;
import r7.q;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0003\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"Lx6/t0;", "invoke", "(Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
public final class ChipKt$ChipContent$1 extends r implements p<Composer, Integer, t0> {
    final /* synthetic */ p<Composer, Integer, t0> $avatar;
    final /* synthetic */ p<Composer, Integer, t0> $label;
    final /* synthetic */ p<Composer, Integer, t0> $leadingIcon;
    final /* synthetic */ long $leadingIconColor;
    final /* synthetic */ float $minHeight;
    final /* synthetic */ PaddingValues $paddingValues;
    final /* synthetic */ p<Composer, Integer, t0> $trailingIcon;
    final /* synthetic */ long $trailingIconColor;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ChipKt$ChipContent$1(float f10, PaddingValues paddingValues, p<? super Composer, ? super Integer, t0> pVar, p<? super Composer, ? super Integer, t0> pVar2, p<? super Composer, ? super Integer, t0> pVar3, long j10, p<? super Composer, ? super Integer, t0> pVar4, long j11) {
        super(2);
        this.$minHeight = f10;
        this.$paddingValues = paddingValues;
        this.$avatar = pVar;
        this.$leadingIcon = pVar2;
        this.$trailingIcon = pVar3;
        this.$leadingIconColor = j10;
        this.$label = pVar4;
        this.$trailingIconColor = j11;
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
            ComposerKt.traceEventStart(1748799148, i10, -1, "androidx.compose.material3.ChipContent.<anonymous> (Chip.kt:1952)");
        }
        Modifier.Companion companion = Modifier.INSTANCE;
        Modifier modifierPadding = PaddingKt.padding(SizeKt.m563defaultMinSizeVpY3zN4$default(companion, 0.0f, this.$minHeight, 1, null), this.$paddingValues);
        AnonymousClass1 anonymousClass1 = new MeasurePolicy() { // from class: androidx.compose.material3.ChipKt$ChipContent$1.1

            /* JADX INFO: renamed from: androidx.compose.material3.ChipKt$ChipContent$1$1$1, reason: invalid class name and collision with other inner class name */
            @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0004\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lx6/t0;", "invoke", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
            public static final class C00931 extends r implements l<Placeable.PlacementScope, t0> {
                final /* synthetic */ int $height;
                final /* synthetic */ Placeable $labelPlaceable;
                final /* synthetic */ int $leadingIconHeight;
                final /* synthetic */ Placeable $leadingIconPlaceable;
                final /* synthetic */ int $leadingIconWidth;
                final /* synthetic */ int $trailingIconHeight;
                final /* synthetic */ Placeable $trailingIconPlaceable;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public C00931(Placeable placeable, int i10, int i11, Placeable placeable2, int i12, Placeable placeable3, int i13) {
                    super(1);
                    this.$leadingIconPlaceable = placeable;
                    this.$leadingIconHeight = i10;
                    this.$height = i11;
                    this.$labelPlaceable = placeable2;
                    this.$leadingIconWidth = i12;
                    this.$trailingIconPlaceable = placeable3;
                    this.$trailingIconHeight = i13;
                }

                @Override // r7.l
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((Placeable.PlacementScope) obj);
                    return t0.f22605a;
                }

                public final void invoke(Placeable.PlacementScope placementScope) {
                    Placeable.PlacementScope placementScope2;
                    Placeable placeable = this.$leadingIconPlaceable;
                    if (placeable != null) {
                        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, 0, Alignment.INSTANCE.getCenterVertically().align(this.$leadingIconHeight, this.$height), 0.0f, 4, null);
                        placementScope2 = placementScope;
                    } else {
                        placementScope2 = placementScope;
                    }
                    Placeable.PlacementScope.placeRelative$default(placementScope2, this.$labelPlaceable, this.$leadingIconWidth, 0, 0.0f, 4, null);
                    Placeable placeable2 = this.$trailingIconPlaceable;
                    if (placeable2 != null) {
                        Placeable.PlacementScope.placeRelative$default(placementScope2, placeable2, this.$labelPlaceable.getWidth() + this.$leadingIconWidth, Alignment.INSTANCE.getCenterVertically().align(this.$trailingIconHeight, this.$height), 0.0f, 4, null);
                    }
                }
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public final /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i11) {
                return androidx.compose.ui.layout.g.a(this, intrinsicMeasureScope, list, i11);
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public final /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i11) {
                return androidx.compose.ui.layout.g.b(this, intrinsicMeasureScope, list, i11);
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            /* JADX INFO: renamed from: measure-3p2s80s */
            public final MeasureResult mo5measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j10) {
                Measurable measurable;
                Measurable measurable2;
                int size = list.size();
                int i11 = 0;
                while (true) {
                    if (i11 >= size) {
                        measurable = null;
                        break;
                    }
                    measurable = list.get(i11);
                    if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable), "leadingIcon")) {
                        break;
                    }
                    i11++;
                }
                Measurable measurable3 = measurable;
                Placeable placeableMo4631measureBRTryo0 = measurable3 != null ? measurable3.mo4631measureBRTryo0(Constraints.m5637copyZbe2FdA$default(j10, 0, 0, 0, 0, 10, null)) : null;
                int iWidthOrZero = TextFieldImplKt.widthOrZero(placeableMo4631measureBRTryo0);
                int iHeightOrZero = TextFieldImplKt.heightOrZero(placeableMo4631measureBRTryo0);
                int size2 = list.size();
                int i12 = 0;
                while (true) {
                    if (i12 >= size2) {
                        measurable2 = null;
                        break;
                    }
                    measurable2 = list.get(i12);
                    if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable2), "trailingIcon")) {
                        break;
                    }
                    i12++;
                }
                Measurable measurable4 = measurable2;
                Placeable placeableMo4631measureBRTryo02 = measurable4 != null ? measurable4.mo4631measureBRTryo0(Constraints.m5637copyZbe2FdA$default(j10, 0, 0, 0, 0, 10, null)) : null;
                int iWidthOrZero2 = TextFieldImplKt.widthOrZero(placeableMo4631measureBRTryo02);
                int iHeightOrZero2 = TextFieldImplKt.heightOrZero(placeableMo4631measureBRTryo02);
                int size3 = list.size();
                for (int i13 = 0; i13 < size3; i13++) {
                    Measurable measurable5 = list.get(i13);
                    if (kotlin.jvm.internal.p.a(LayoutIdKt.getLayoutId(measurable5), "label")) {
                        Placeable placeableMo4631measureBRTryo03 = measurable5.mo4631measureBRTryo0(ConstraintsKt.m5663offsetNN6EwU$default(j10, -(iWidthOrZero + iWidthOrZero2), 0, 2, null));
                        int width = placeableMo4631measureBRTryo03.getWidth() + iWidthOrZero + iWidthOrZero2;
                        int iMax = Math.max(iHeightOrZero, Math.max(placeableMo4631measureBRTryo03.getHeight(), iHeightOrZero2));
                        return MeasureScope.CC.q(measureScope, width, iMax, null, new C00931(placeableMo4631measureBRTryo0, iHeightOrZero, iMax, placeableMo4631measureBRTryo03, iWidthOrZero, placeableMo4631measureBRTryo02, iHeightOrZero2), 4, null);
                    }
                }
                throw new NoSuchElementException("Collection contains no element matching the predicate.");
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public final /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i11) {
                return androidx.compose.ui.layout.g.c(this, intrinsicMeasureScope, list, i11);
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public final /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i11) {
                return androidx.compose.ui.layout.g.d(this, intrinsicMeasureScope, list, i11);
            }
        };
        p<Composer, Integer, t0> pVar = this.$avatar;
        p<Composer, Integer, t0> pVar2 = this.$leadingIcon;
        p<Composer, Integer, t0> pVar3 = this.$trailingIcon;
        long j10 = this.$leadingIconColor;
        p<Composer, Integer, t0> pVar4 = this.$label;
        long j11 = this.$trailingIconColor;
        composer.startReplaceableGroup(-1323940314);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
        r7.a<ComposeUiNode> constructor = companion2.getConstructor();
        q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierPadding);
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
        Updater.m2998setimpl(composerM2991constructorimpl, anonymousClass1, companion2.getSetMeasurePolicy());
        Updater.m2998setimpl(composerM2991constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
        p<ComposeUiNode, Integer, t0> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
        if (composerM2991constructorimpl.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            a0.c.w(currentCompositeKeyHash, composerM2991constructorimpl, currentCompositeKeyHash, setCompositeKeyHash);
        }
        a0.c.y(composer, qVarModifierMaterializerOf, composer, 0, 2058660585);
        composer.startReplaceableGroup(651014582);
        if (pVar != null || pVar2 != null) {
            Modifier modifierLayoutId = LayoutIdKt.layoutId(companion, "leadingIcon");
            Alignment center = Alignment.INSTANCE.getCenter();
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composer, 6);
            composer.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor2 = companion2.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierLayoutId);
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor2);
            } else {
                composer.useNode();
            }
            Composer composerM2991constructorimpl2 = Updater.m2991constructorimpl(composer);
            p pVarU = a0.c.u(companion2, composerM2991constructorimpl2, measurePolicyRememberBoxMeasurePolicy, composerM2991constructorimpl2, currentCompositionLocalMap2);
            if (composerM2991constructorimpl2.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                a0.c.w(currentCompositeKeyHash2, composerM2991constructorimpl2, currentCompositeKeyHash2, pVarU);
            }
            a0.c.y(composer, qVarModifierMaterializerOf2, composer, 0, 2058660585);
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            if (pVar != null) {
                composer.startReplaceableGroup(1725997334);
                pVar.invoke(composer, 0);
                composer.endReplaceableGroup();
            } else if (pVar2 != null) {
                composer.startReplaceableGroup(1725997437);
                CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3462boximpl(j10)), pVar2, composer, ProvidedValue.$stable);
                composer.endReplaceableGroup();
            } else {
                composer.startReplaceableGroup(1725997699);
                composer.endReplaceableGroup();
            }
            androidx.compose.foundation.c.y(composer);
        }
        composer.endReplaceableGroup();
        Modifier modifierM530paddingVpY3zN4 = PaddingKt.m530paddingVpY3zN4(LayoutIdKt.layoutId(companion, "label"), ChipKt.HorizontalElementsPadding, Dp.m5678constructorimpl(0));
        Arrangement.Horizontal start = Arrangement.INSTANCE.getStart();
        Alignment.Companion companion3 = Alignment.INSTANCE;
        Alignment.Vertical centerVertically = companion3.getCenterVertically();
        composer.startReplaceableGroup(693286680);
        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(start, centerVertically, composer, 54);
        composer.startReplaceableGroup(-1323940314);
        int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap3 = composer.getCurrentCompositionLocalMap();
        r7.a<ComposeUiNode> constructor3 = companion2.getConstructor();
        q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierM530paddingVpY3zN4);
        if (composer.getApplier() == null) {
            ComposablesKt.invalidApplier();
        }
        composer.startReusableNode();
        if (composer.getInserting()) {
            composer.createNode(constructor3);
        } else {
            composer.useNode();
        }
        Composer composerM2991constructorimpl3 = Updater.m2991constructorimpl(composer);
        p pVarU2 = a0.c.u(companion2, composerM2991constructorimpl3, measurePolicyRowMeasurePolicy, composerM2991constructorimpl3, currentCompositionLocalMap3);
        if (composerM2991constructorimpl3.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
            a0.c.w(currentCompositeKeyHash3, composerM2991constructorimpl3, currentCompositeKeyHash3, pVarU2);
        }
        a0.c.y(composer, qVarModifierMaterializerOf3, composer, 0, 2058660585);
        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
        pVar4.invoke(composer, 0);
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        composer.startReplaceableGroup(-313041276);
        if (pVar3 != null) {
            Modifier modifierLayoutId2 = LayoutIdKt.layoutId(companion, "trailingIcon");
            Alignment center2 = companion3.getCenter();
            composer.startReplaceableGroup(733328855);
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy2 = BoxKt.rememberBoxMeasurePolicy(center2, false, composer, 6);
            composer.startReplaceableGroup(-1323940314);
            int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap4 = composer.getCurrentCompositionLocalMap();
            r7.a<ComposeUiNode> constructor4 = companion2.getConstructor();
            q<SkippableUpdater<ComposeUiNode>, Composer, Integer, t0> qVarModifierMaterializerOf4 = LayoutKt.modifierMaterializerOf(modifierLayoutId2);
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor4);
            } else {
                composer.useNode();
            }
            Composer composerM2991constructorimpl4 = Updater.m2991constructorimpl(composer);
            p pVarU3 = a0.c.u(companion2, composerM2991constructorimpl4, measurePolicyRememberBoxMeasurePolicy2, composerM2991constructorimpl4, currentCompositionLocalMap4);
            if (composerM2991constructorimpl4.getInserting() || !kotlin.jvm.internal.p.a(composerM2991constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                a0.c.w(currentCompositeKeyHash4, composerM2991constructorimpl4, currentCompositeKeyHash4, pVarU3);
            }
            a0.c.y(composer, qVarModifierMaterializerOf4, composer, 0, 2058660585);
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3462boximpl(j11)), pVar3, composer, ProvidedValue.$stable);
            composer.endReplaceableGroup();
            composer.endNode();
            composer.endReplaceableGroup();
            composer.endReplaceableGroup();
        }
        composer.endReplaceableGroup();
        composer.endReplaceableGroup();
        composer.endNode();
        composer.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }
}

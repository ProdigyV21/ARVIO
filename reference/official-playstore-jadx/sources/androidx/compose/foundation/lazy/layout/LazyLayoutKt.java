package androidx.compose.foundation.lazy.layout;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.SaveableStateHolder;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import androidx.compose.ui.layout.SubcomposeLayoutState;
import androidx.compose.ui.unit.Constraints;
import androidx.media3.exoplayer.RendererCapabilities;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import r7.q;
import x6.e;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001aL\u0010\r\u001a\u00020\f2\u0006\u0010\u0001\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u001d\u0010\u000b\u001a\u0019\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0006¢\u0006\u0002\b\nH\u0007¢\u0006\u0004\b\r\u0010\u000e\u001aR\u0010\r\u001a\u00020\f2\f\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020\u00000\u000f2\b\b\u0002\u0010\u0003\u001a\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u001d\u0010\u000b\u001a\u0019\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0006¢\u0006\u0002\b\nH\u0007¢\u0006\u0004\b\r\u0010\u0010\"\u0014\u0010\u0012\u001a\u00020\u00118\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutItemProvider;", "itemProvider", "Landroidx/compose/ui/Modifier;", "modifier", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;", "prefetchState", "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/layout/MeasureResult;", "Lx6/n;", "measurePolicy", "Lx6/t0;", "LazyLayout", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutItemProvider;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;Lr7/p;Landroidx/compose/runtime/Composer;II)V", "Lkotlin/Function0;", "(Lr7/a;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;Lr7/p;Landroidx/compose/runtime/Composer;II)V", "", "MaxItemsToRetainForReuse", "I", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyLayoutKt {
    private static final int MaxItemsToRetainForReuse = 7;

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazyLayoutKt$LazyLayout$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ LazyLayoutItemProvider $itemProvider;
        final /* synthetic */ p<LazyLayoutMeasureScope, Constraints, MeasureResult> $measurePolicy;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ LazyLayoutPrefetchState $prefetchState;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(LazyLayoutItemProvider lazyLayoutItemProvider, Modifier modifier, LazyLayoutPrefetchState lazyLayoutPrefetchState, p<? super LazyLayoutMeasureScope, ? super Constraints, ? extends MeasureResult> pVar, int i10, int i11) {
            super(2);
            this.$itemProvider = lazyLayoutItemProvider;
            this.$modifier = modifier;
            this.$prefetchState = lazyLayoutPrefetchState;
            this.$measurePolicy = pVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            LazyLayoutKt.LazyLayout(this.$itemProvider, this.$modifier, this.$prefetchState, this.$measurePolicy, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazyLayoutKt$LazyLayout$3, reason: invalid class name */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000H\u000b¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"Landroidx/compose/runtime/saveable/SaveableStateHolder;", "saveableStateHolder", "Lx6/t0;", "invoke", "(Landroidx/compose/runtime/saveable/SaveableStateHolder;Landroidx/compose/runtime/Composer;I)V", "<anonymous>"}, k = 3, mv = {1, 8, 0})
    public static final class AnonymousClass3 extends r implements q<SaveableStateHolder, Composer, Integer, t0> {
        final /* synthetic */ State<r7.a<LazyLayoutItemProvider>> $currentItemProvider;
        final /* synthetic */ p<LazyLayoutMeasureScope, Constraints, MeasureResult> $measurePolicy;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ LazyLayoutPrefetchState $prefetchState;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass3(LazyLayoutPrefetchState lazyLayoutPrefetchState, Modifier modifier, p<? super LazyLayoutMeasureScope, ? super Constraints, ? extends MeasureResult> pVar, State<? extends r7.a<? extends LazyLayoutItemProvider>> state) {
            super(3);
            this.$prefetchState = lazyLayoutPrefetchState;
            this.$modifier = modifier;
            this.$measurePolicy = pVar;
            this.$currentItemProvider = state;
        }

        @Override // r7.q
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
            invoke((SaveableStateHolder) obj, (Composer) obj2, ((Number) obj3).intValue());
            return t0.f22605a;
        }

        public final void invoke(SaveableStateHolder saveableStateHolder, Composer composer, int i10) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1488997347, i10, -1, "androidx.compose.foundation.lazy.layout.LazyLayout.<anonymous> (LazyLayout.kt:81)");
            }
            State<r7.a<LazyLayoutItemProvider>> state = this.$currentItemProvider;
            composer.startReplaceableGroup(-492369756);
            Object objRememberedValue = composer.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = new LazyLayoutItemContentFactory(saveableStateHolder, new LazyLayoutKt$LazyLayout$3$itemContentFactory$1$1(state));
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            LazyLayoutItemContentFactory lazyLayoutItemContentFactory = (LazyLayoutItemContentFactory) objRememberedValue;
            composer.startReplaceableGroup(-492369756);
            Object objRememberedValue2 = composer.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new SubcomposeLayoutState(new LazyLayoutItemReusePolicy(lazyLayoutItemContentFactory));
                composer.updateRememberedValue(objRememberedValue2);
            }
            composer.endReplaceableGroup();
            SubcomposeLayoutState subcomposeLayoutState = (SubcomposeLayoutState) objRememberedValue2;
            LazyLayoutPrefetchState lazyLayoutPrefetchState = this.$prefetchState;
            composer.startReplaceableGroup(-1523807258);
            if (lazyLayoutPrefetchState != null) {
                LazyLayoutPrefetcher_androidKt.LazyLayoutPrefetcher(this.$prefetchState, lazyLayoutItemContentFactory, subcomposeLayoutState, composer, (SubcomposeLayoutState.$stable << 6) | 64);
            }
            composer.endReplaceableGroup();
            Modifier modifier = this.$modifier;
            p<LazyLayoutMeasureScope, Constraints, MeasureResult> pVar = this.$measurePolicy;
            composer.startReplaceableGroup(511388516);
            boolean zChanged = composer.changed(lazyLayoutItemContentFactory) | composer.changed(pVar);
            Object objRememberedValue3 = composer.rememberedValue();
            if (zChanged || objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = new LazyLayoutKt$LazyLayout$3$2$1(lazyLayoutItemContentFactory, pVar);
                composer.updateRememberedValue(objRememberedValue3);
            }
            composer.endReplaceableGroup();
            SubcomposeLayoutKt.SubcomposeLayout(subcomposeLayoutState, modifier, (p) objRememberedValue3, composer, SubcomposeLayoutState.$stable, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazyLayoutKt$LazyLayout$4, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass4 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ int $$default;
        final /* synthetic */ r7.a<LazyLayoutItemProvider> $itemProvider;
        final /* synthetic */ p<LazyLayoutMeasureScope, Constraints, MeasureResult> $measurePolicy;
        final /* synthetic */ Modifier $modifier;
        final /* synthetic */ LazyLayoutPrefetchState $prefetchState;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass4(r7.a<? extends LazyLayoutItemProvider> aVar, Modifier modifier, LazyLayoutPrefetchState lazyLayoutPrefetchState, p<? super LazyLayoutMeasureScope, ? super Constraints, ? extends MeasureResult> pVar, int i10, int i11) {
            super(2);
            this.$itemProvider = aVar;
            this.$modifier = modifier;
            this.$prefetchState = lazyLayoutPrefetchState;
            this.$measurePolicy = pVar;
            this.$$changed = i10;
            this.$$default = i11;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            LazyLayoutKt.LazyLayout(this.$itemProvider, this.$modifier, this.$prefetchState, this.$measurePolicy, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1), this.$$default);
        }
    }

    @e
    public static final void LazyLayout(LazyLayoutItemProvider lazyLayoutItemProvider, Modifier modifier, LazyLayoutPrefetchState lazyLayoutPrefetchState, p<? super LazyLayoutMeasureScope, ? super Constraints, ? extends MeasureResult> pVar, Composer composer, int i10, int i11) {
        int i12;
        p<? super LazyLayoutMeasureScope, ? super Constraints, ? extends MeasureResult> pVar2;
        Modifier modifier2;
        LazyLayoutPrefetchState lazyLayoutPrefetchState2;
        Composer composerStartRestartGroup = composer.startRestartGroup(852831187);
        if ((i11 & 1) != 0) {
            i12 = i10 | 6;
        } else if ((i10 & 14) == 0) {
            i12 = (composerStartRestartGroup.changed(lazyLayoutItemProvider) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        int i13 = i11 & 2;
        if (i13 != 0) {
            i12 |= 48;
        } else if ((i10 & 112) == 0) {
            i12 |= composerStartRestartGroup.changed(modifier) ? 32 : 16;
        }
        int i14 = i11 & 4;
        if (i14 != 0) {
            i12 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if ((i10 & 896) == 0) {
            i12 |= composerStartRestartGroup.changed(lazyLayoutPrefetchState) ? 256 : 128;
        }
        if ((i11 & 8) != 0) {
            i12 |= 3072;
            pVar2 = pVar;
        } else {
            pVar2 = pVar;
            if ((i10 & 7168) == 0) {
                i12 |= composerStartRestartGroup.changedInstance(pVar2) ? 2048 : 1024;
            }
        }
        if ((i12 & 5851) == 1170 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
            modifier2 = modifier;
            lazyLayoutPrefetchState2 = lazyLayoutPrefetchState;
        } else {
            if (i13 != 0) {
                modifier = Modifier.INSTANCE;
            }
            Modifier modifier3 = modifier;
            if (i14 != 0) {
                lazyLayoutPrefetchState = null;
            }
            LazyLayoutPrefetchState lazyLayoutPrefetchState3 = lazyLayoutPrefetchState;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(852831187, i12, -1, "androidx.compose.foundation.lazy.layout.LazyLayout (LazyLayout.kt:52)");
            }
            composerStartRestartGroup.startReplaceableGroup(-1428817084);
            boolean zChanged = composerStartRestartGroup.changed(lazyLayoutItemProvider);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new LazyLayoutKt$LazyLayout$1$1(lazyLayoutItemProvider);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            LazyLayout((r7.a<? extends LazyLayoutItemProvider>) objRememberedValue, modifier3, lazyLayoutPrefetchState3, pVar2, composerStartRestartGroup, i12 & 8176, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            lazyLayoutPrefetchState2 = lazyLayoutPrefetchState3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass2(lazyLayoutItemProvider, modifier2, lazyLayoutPrefetchState2, pVar, i10, i11));
        }
    }

    public static final void LazyLayout(r7.a<? extends LazyLayoutItemProvider> aVar, Modifier modifier, LazyLayoutPrefetchState lazyLayoutPrefetchState, p<? super LazyLayoutMeasureScope, ? super Constraints, ? extends MeasureResult> pVar, Composer composer, int i10, int i11) {
        int i12;
        Composer composerStartRestartGroup = composer.startRestartGroup(2002163445);
        if ((i11 & 1) != 0) {
            i12 = i10 | 6;
        } else if ((i10 & 14) == 0) {
            i12 = (composerStartRestartGroup.changedInstance(aVar) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        int i13 = i11 & 2;
        if (i13 != 0) {
            i12 |= 48;
        } else if ((i10 & 112) == 0) {
            i12 |= composerStartRestartGroup.changed(modifier) ? 32 : 16;
        }
        int i14 = i11 & 4;
        if (i14 != 0) {
            i12 |= RendererCapabilities.DECODER_SUPPORT_MASK;
        } else if ((i10 & 896) == 0) {
            i12 |= composerStartRestartGroup.changed(lazyLayoutPrefetchState) ? 256 : 128;
        }
        if ((i11 & 8) != 0) {
            i12 |= 3072;
        } else if ((i10 & 7168) == 0) {
            i12 |= composerStartRestartGroup.changedInstance(pVar) ? 2048 : 1024;
        }
        if ((i12 & 5851) == 1170 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (i13 != 0) {
                modifier = Modifier.INSTANCE;
            }
            if (i14 != 0) {
                lazyLayoutPrefetchState = null;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2002163445, i12, -1, "androidx.compose.foundation.lazy.layout.LazyLayout (LazyLayout.kt:77)");
            }
            LazySaveableStateHolderKt.LazySaveableStateHolderProvider(ComposableLambdaKt.composableLambda(composerStartRestartGroup, -1488997347, true, new AnonymousClass3(lazyLayoutPrefetchState, modifier, pVar, SnapshotStateKt.rememberUpdatedState(aVar, composerStartRestartGroup, i12 & 14))), composerStartRestartGroup, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        Modifier modifier2 = modifier;
        LazyLayoutPrefetchState lazyLayoutPrefetchState2 = lazyLayoutPrefetchState;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass4(aVar, modifier2, lazyLayoutPrefetchState2, pVar, i10, i11));
        }
    }
}

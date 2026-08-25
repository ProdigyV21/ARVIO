package androidx.compose.foundation.lazy.layout;

import android.view.View;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.layout.SubcomposeLayoutState;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import kotlin.Metadata;
import kotlin.jvm.internal.r;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a'\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0001¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;", "prefetchState", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemContentFactory;", "itemContentFactory", "Landroidx/compose/ui/layout/SubcomposeLayoutState;", "subcomposeLayoutState", "Lx6/t0;", "LazyLayoutPrefetcher", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;Landroidx/compose/foundation/lazy/layout/LazyLayoutItemContentFactory;Landroidx/compose/ui/layout/SubcomposeLayoutState;Landroidx/compose/runtime/Composer;I)V", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyLayoutPrefetcher_androidKt {

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazyLayoutPrefetcher_androidKt$LazyLayoutPrefetcher$2, reason: invalid class name */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2 extends r implements p<Composer, Integer, t0> {
        final /* synthetic */ int $$changed;
        final /* synthetic */ LazyLayoutItemContentFactory $itemContentFactory;
        final /* synthetic */ LazyLayoutPrefetchState $prefetchState;
        final /* synthetic */ SubcomposeLayoutState $subcomposeLayoutState;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass2(LazyLayoutPrefetchState lazyLayoutPrefetchState, LazyLayoutItemContentFactory lazyLayoutItemContentFactory, SubcomposeLayoutState subcomposeLayoutState, int i10) {
            super(2);
            this.$prefetchState = lazyLayoutPrefetchState;
            this.$itemContentFactory = lazyLayoutItemContentFactory;
            this.$subcomposeLayoutState = subcomposeLayoutState;
            this.$$changed = i10;
        }

        @Override // r7.p
        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return t0.f22605a;
        }

        public final void invoke(Composer composer, int i10) {
            LazyLayoutPrefetcher_androidKt.LazyLayoutPrefetcher(this.$prefetchState, this.$itemContentFactory, this.$subcomposeLayoutState, composer, RecomposeScopeImplKt.updateChangedFlags(this.$$changed | 1));
        }
    }

    public static final void LazyLayoutPrefetcher(LazyLayoutPrefetchState lazyLayoutPrefetchState, LazyLayoutItemContentFactory lazyLayoutItemContentFactory, SubcomposeLayoutState subcomposeLayoutState, Composer composer, int i10) {
        Composer composerStartRestartGroup = composer.startRestartGroup(1113453182);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1113453182, i10, -1, "androidx.compose.foundation.lazy.layout.LazyLayoutPrefetcher (LazyLayoutPrefetcher.android.kt:39)");
        }
        View view = (View) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalView());
        composerStartRestartGroup.startReplaceableGroup(1618982084);
        boolean zChanged = composerStartRestartGroup.changed(subcomposeLayoutState) | composerStartRestartGroup.changed(lazyLayoutPrefetchState) | composerStartRestartGroup.changed(view);
        Object objRememberedValue = composerStartRestartGroup.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            composerStartRestartGroup.updateRememberedValue(new LazyLayoutPrefetcher(lazyLayoutPrefetchState, subcomposeLayoutState, lazyLayoutItemContentFactory, view));
        }
        composerStartRestartGroup.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new AnonymousClass2(lazyLayoutPrefetchState, lazyLayoutItemContentFactory, subcomposeLayoutState, i10));
        }
    }
}

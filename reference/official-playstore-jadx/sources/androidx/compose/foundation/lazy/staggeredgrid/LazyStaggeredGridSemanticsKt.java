package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.gestures.ScrollExtensionsKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.semantics.CollectionInfo;
import d7.d;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a\u001d\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0001¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"rememberLazyStaggeredGridSemanticState", "Landroidx/compose/foundation/lazy/layout/LazyLayoutSemanticState;", "state", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "reverseScrolling", "", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;ZLandroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/lazy/layout/LazyLayoutSemanticState;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyStaggeredGridSemanticsKt {
    public static final LazyLayoutSemanticState rememberLazyStaggeredGridSemanticState(final LazyStaggeredGridState lazyStaggeredGridState, boolean z, Composer composer, int i10) {
        composer.startReplaceableGroup(1629354903);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1629354903, i10, -1, "androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridSemanticState (LazyStaggeredGridSemantics.kt:31)");
        }
        Boolean boolValueOf = Boolean.valueOf(z);
        composer.startReplaceableGroup(511388516);
        boolean zChanged = composer.changed(boolValueOf) | composer.changed(lazyStaggeredGridState);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new LazyLayoutSemanticState() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridSemanticsKt$rememberLazyStaggeredGridSemanticState$1$1
                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
                public Object animateScrollBy(float f10, d<? super t0> dVar) {
                    Object objAnimateScrollBy$default = ScrollExtensionsKt.animateScrollBy$default(lazyStaggeredGridState, f10, null, dVar, 2, null);
                    return objAnimateScrollBy$default == e7.a.f15033i ? objAnimateScrollBy$default : t0.f22605a;
                }

                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
                public CollectionInfo collectionInfo() {
                    return new CollectionInfo(-1, -1);
                }

                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
                public boolean getCanScrollForward() {
                    return lazyStaggeredGridState.getCanScrollForward();
                }

                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
                public int getFirstVisibleItemIndex() {
                    return lazyStaggeredGridState.getFirstVisibleItemIndex();
                }

                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
                public int getFirstVisibleItemScrollOffset() {
                    return lazyStaggeredGridState.getFirstVisibleItemScrollOffset();
                }

                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
                public final /* synthetic */ float pseudoMaxScrollOffset() {
                    return androidx.compose.foundation.lazy.layout.d.a(this);
                }

                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
                public final /* synthetic */ float pseudoScrollOffset() {
                    return androidx.compose.foundation.lazy.layout.d.b(this);
                }

                @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
                public Object scrollToItem(int i11, d<? super t0> dVar) {
                    Object objScrollToItem$default = LazyStaggeredGridState.scrollToItem$default(lazyStaggeredGridState, i11, 0, dVar, 2, null);
                    return objScrollToItem$default == e7.a.f15033i ? objScrollToItem$default : t0.f22605a;
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        LazyStaggeredGridSemanticsKt$rememberLazyStaggeredGridSemanticState$1$1 lazyStaggeredGridSemanticsKt$rememberLazyStaggeredGridSemanticState$1$1 = (LazyStaggeredGridSemanticsKt$rememberLazyStaggeredGridSemanticState$1$1) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return lazyStaggeredGridSemanticsKt$rememberLazyStaggeredGridSemanticState$1$1;
    }
}

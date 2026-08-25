package androidx.compose.foundation.pager;

import androidx.compose.foundation.gestures.ScrollExtensionsKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState;
import androidx.compose.ui.semantics.CollectionInfo;
import d7.d;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000¨\u0006\u0006"}, d2 = {"LazyLayoutSemanticState", "Landroidx/compose/foundation/lazy/layout/LazyLayoutSemanticState;", "state", "Landroidx/compose/foundation/pager/PagerState;", "isVertical", "", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyLayoutSemanticStateKt {
    public static final LazyLayoutSemanticState LazyLayoutSemanticState(final PagerState pagerState, final boolean z) {
        return new LazyLayoutSemanticState() { // from class: androidx.compose.foundation.pager.LazyLayoutSemanticStateKt.LazyLayoutSemanticState.1
            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
            public Object animateScrollBy(float f10, d<? super t0> dVar) {
                Object objAnimateScrollBy$default = ScrollExtensionsKt.animateScrollBy$default(pagerState, f10, null, dVar, 2, null);
                return objAnimateScrollBy$default == e7.a.f15033i ? objAnimateScrollBy$default : t0.f22605a;
            }

            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
            public CollectionInfo collectionInfo() {
                return z ? new CollectionInfo(pagerState.getPageCount(), 1) : new CollectionInfo(1, pagerState.getPageCount());
            }

            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
            public boolean getCanScrollForward() {
                return pagerState.getCanScrollForward();
            }

            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
            public int getFirstVisibleItemIndex() {
                return pagerState.getFirstVisiblePage();
            }

            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState
            public int getFirstVisibleItemScrollOffset() {
                return pagerState.getFirstVisiblePageOffset();
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
            public Object scrollToItem(int i10, d<? super t0> dVar) {
                Object objScrollToPage$default = PagerState.scrollToPage$default(pagerState, i10, 0.0f, dVar, 2, null);
                return objScrollToPage$default == e7.a.f15033i ? objScrollToPage$default : t0.f22605a;
            }
        };
    }
}

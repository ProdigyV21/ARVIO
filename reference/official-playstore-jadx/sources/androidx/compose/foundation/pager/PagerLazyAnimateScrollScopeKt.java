package androidx.compose.foundation.pager;

import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.gestures.f;
import androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope;
import d7.d;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0001¨\u0006\u0004"}, d2 = {"PagerLazyAnimateScrollScope", "Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimateScrollScope;", "state", "Landroidx/compose/foundation/pager/PagerState;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PagerLazyAnimateScrollScopeKt {
    public static final LazyLayoutAnimateScrollScope PagerLazyAnimateScrollScope(final PagerState pagerState) {
        return new LazyLayoutAnimateScrollScope() { // from class: androidx.compose.foundation.pager.PagerLazyAnimateScrollScopeKt.PagerLazyAnimateScrollScope.1
            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope
            public float calculateDistanceTo(int targetIndex, int targetItemOffset) {
                return ((targetIndex - pagerState.getCurrentPage()) * getVisibleItemsAverageSize()) + targetItemOffset;
            }

            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope
            public int getFirstVisibleItemIndex() {
                return pagerState.getFirstVisiblePage();
            }

            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope
            public int getFirstVisibleItemScrollOffset() {
                return pagerState.getFirstVisiblePageOffset();
            }

            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope
            public int getItemCount() {
                return pagerState.getPageCount();
            }

            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope
            public int getLastVisibleItemIndex() {
                return ((PageInfo) x.w0(pagerState.getLayoutInfo().getVisiblePagesInfo())).getIndex();
            }

            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope
            public int getVisibleItemScrollOffset(int index) {
                PageInfo pageInfo;
                List<PageInfo> visiblePagesInfo = pagerState.getLayoutInfo().getVisiblePagesInfo();
                int size = visiblePagesInfo.size();
                int i10 = 0;
                while (true) {
                    if (i10 >= size) {
                        pageInfo = null;
                        break;
                    }
                    pageInfo = visiblePagesInfo.get(i10);
                    if (pageInfo.getIndex() == index) {
                        break;
                    }
                    i10++;
                }
                PageInfo pageInfo2 = pageInfo;
                if (pageInfo2 != null) {
                    return pageInfo2.getOffset();
                }
                return 0;
            }

            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope
            public int getVisibleItemsAverageSize() {
                return pagerState.getPageSpacing$foundation_release() + pagerState.getPageSize$foundation_release();
            }

            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope
            public Object scroll(p<? super ScrollScope, ? super d<? super t0>, ? extends Object> pVar, d<? super t0> dVar) {
                Object objE = f.e(pagerState, null, pVar, dVar, 1, null);
                return objE == e7.a.f15033i ? objE : t0.f22605a;
            }

            @Override // androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope
            public void snapToItem(ScrollScope scrollScope, int i10, int i11) {
                pagerState.snapToItem$foundation_release(i10, i11 / pagerState.getPageSizeWithSpacing$foundation_release());
            }
        };
    }
}

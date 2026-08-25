package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.foundation.gestures.f;
import androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import d7.d;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.x;
import r7.p;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0001\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\b\u0010\tJ#\u0010\r\u001a\u00020\f*\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u001f\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J9\u0010\u0019\u001a\u00020\f2'\u0010\u0018\u001a#\b\u0001\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u0015\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0014¢\u0006\u0002\b\u0017H\u0096@¢\u0006\u0004\b\u0019\u0010\u001aR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u001bR\u0014\u0010\u001e\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010 \u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u001dR\u0014\u0010\"\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u001dR\u0014\u0010$\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\u001dR\u0014\u0010&\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010\u001d¨\u0006'"}, d2 = {"Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridAnimateScrollScope;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimateScrollScope;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "state", "<init>", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;)V", "", "index", "getVisibleItemScrollOffset", "(I)I", "Landroidx/compose/foundation/gestures/ScrollScope;", "scrollOffset", "Lx6/t0;", "snapToItem", "(Landroidx/compose/foundation/gestures/ScrollScope;II)V", "targetIndex", "targetItemOffset", "", "calculateDistanceTo", "(II)F", "Lkotlin/Function2;", "Ld7/d;", "", "Lx6/n;", "block", "scroll", "(Lr7/p;Ld7/d;)Ljava/lang/Object;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "getFirstVisibleItemIndex", "()I", "firstVisibleItemIndex", "getFirstVisibleItemScrollOffset", "firstVisibleItemScrollOffset", "getLastVisibleItemIndex", "lastVisibleItemIndex", "getItemCount", "itemCount", "getVisibleItemsAverageSize", "visibleItemsAverageSize", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LazyStaggeredGridAnimateScrollScope implements LazyLayoutAnimateScrollScope {
    public static final int $stable = 8;
    private final LazyStaggeredGridState state;

    public LazyStaggeredGridAnimateScrollScope(LazyStaggeredGridState lazyStaggeredGridState) {
        this.state = lazyStaggeredGridState;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope
    public float calculateDistanceTo(int targetIndex, int targetItemOffset) {
        int visibleItemsAverageSize = getVisibleItemsAverageSize();
        int laneCount$foundation_release = (targetIndex / this.state.getLaneCount$foundation_release()) - (getFirstVisibleItemIndex() / this.state.getLaneCount$foundation_release());
        int iMin = Math.min(Math.abs(targetItemOffset), visibleItemsAverageSize);
        if (targetItemOffset < 0) {
            iMin *= -1;
        }
        return ((visibleItemsAverageSize * laneCount$foundation_release) + iMin) - getFirstVisibleItemScrollOffset();
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope
    public int getFirstVisibleItemIndex() {
        return this.state.getFirstVisibleItemIndex();
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope
    public int getFirstVisibleItemScrollOffset() {
        return this.state.getFirstVisibleItemScrollOffset();
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope
    public int getItemCount() {
        return this.state.getLayoutInfo().getTotalItemsCount();
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope
    public int getLastVisibleItemIndex() {
        LazyStaggeredGridItemInfo lazyStaggeredGridItemInfo = (LazyStaggeredGridItemInfo) x.y0(this.state.getLayoutInfo().getVisibleItemsInfo());
        if (lazyStaggeredGridItemInfo != null) {
            return lazyStaggeredGridItemInfo.getIndex();
        }
        return 0;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope
    public int getVisibleItemScrollOffset(int index) {
        List<LazyStaggeredGridItemInfo> visibleItemsInfo = this.state.getLayoutInfo().getVisibleItemsInfo();
        long offset = this.state.getLayoutInfo().getVisibleItemsInfo().get(t7.a.b(visibleItemsInfo, visibleItemsInfo.size(), new LazyStaggeredGridAnimateScrollScope$getVisibleItemScrollOffset$searchedIndex$1(index))).getOffset();
        return this.state.getIsVertical() ? IntOffset.m5803getYimpl(offset) : IntOffset.m5802getXimpl(offset);
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope
    public int getVisibleItemsAverageSize() {
        LazyStaggeredGridLayoutInfo layoutInfo = this.state.getLayoutInfo();
        List<LazyStaggeredGridItemInfo> visibleItemsInfo = layoutInfo.getVisibleItemsInfo();
        int size = visibleItemsInfo.size();
        int iM5843getHeightimpl = 0;
        for (int i10 = 0; i10 < size; i10++) {
            LazyStaggeredGridItemInfo lazyStaggeredGridItemInfo = visibleItemsInfo.get(i10);
            iM5843getHeightimpl += this.state.getIsVertical() ? IntSize.m5843getHeightimpl(lazyStaggeredGridItemInfo.getSize()) : IntSize.m5844getWidthimpl(lazyStaggeredGridItemInfo.getSize());
        }
        return layoutInfo.getMainAxisItemSpacing() + (iM5843getHeightimpl / visibleItemsInfo.size());
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope
    public Object scroll(p<? super ScrollScope, ? super d<? super t0>, ? extends Object> pVar, d<? super t0> dVar) {
        Object objE = f.e(this.state, null, pVar, dVar, 1, null);
        return objE == e7.a.f15033i ? objE : t0.f22605a;
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutAnimateScrollScope
    public void snapToItem(ScrollScope scrollScope, int i10, int i11) {
        this.state.snapToItemInternal$foundation_release(scrollScope, i10, i11);
    }
}

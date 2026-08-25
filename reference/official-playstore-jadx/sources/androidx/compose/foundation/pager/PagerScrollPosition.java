package androidx.compose.foundation.pager;

import androidx.compose.foundation.lazy.layout.LazyLayoutItemProviderKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutNearestRangeState;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.SnapshotIntStateKt;
import kotlin.Metadata;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B#\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u001f\u0010\r\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0015\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0011\u0010\u0012J\u001d\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0004¢\u0006\u0004\b\u0015\u0010\u000eJ\u001d\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u0015\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u0004¢\u0006\u0004\b\u001b\u0010\u001cJ\r\u0010\u001d\u001a\u00020\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u0015\u0010 \u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\u0002¢\u0006\u0004\b \u0010!R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\"\u001a\u0004\b#\u0010$R+\u0010\u0003\u001a\u00020\u00022\u0006\u0010%\u001a\u00020\u00028F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b&\u0010'\u001a\u0004\b(\u0010\u001e\"\u0004\b)\u0010!R+\u0010\u0005\u001a\u00020\u00042\u0006\u0010%\u001a\u00020\u00048F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010\u001cR\u0016\u00100\u001a\u00020/8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b0\u00101R\u0018\u00102\u001a\u0004\u0018\u00010\u00018\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b2\u00103R\u0017\u00105\u001a\u0002048\u0006¢\u0006\f\n\u0004\b5\u00106\u001a\u0004\b7\u00108¨\u00069"}, d2 = {"Landroidx/compose/foundation/pager/PagerScrollPosition;", "", "", "currentPage", "", "currentPageOffsetFraction", "Landroidx/compose/foundation/pager/PagerState;", "state", "<init>", "(IFLandroidx/compose/foundation/pager/PagerState;)V", "page", "pageOffsetFraction", "Lx6/t0;", "update", "(IF)V", "Landroidx/compose/foundation/pager/PagerMeasureResult;", "measureResult", "updateFromMeasureResult", "(Landroidx/compose/foundation/pager/PagerMeasureResult;)V", "index", "scrollOffsetFraction", "requestPosition", "Landroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;", "itemProvider", "matchPageWithKey", "(Landroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;I)I", "offsetFraction", "updateCurrentPageOffsetFraction", "(F)V", "currentScrollOffset", "()I", "delta", "applyScrollDelta", "(I)V", "Landroidx/compose/foundation/pager/PagerState;", "getState", "()Landroidx/compose/foundation/pager/PagerState;", "<set-?>", "currentPage$delegate", "Landroidx/compose/runtime/MutableIntState;", "getCurrentPage", "setCurrentPage", "currentPageOffsetFraction$delegate", "Landroidx/compose/runtime/MutableFloatState;", "getCurrentPageOffsetFraction", "()F", "setCurrentPageOffsetFraction", "", "hadFirstNotEmptyLayout", "Z", "lastKnownCurrentPageKey", "Ljava/lang/Object;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutNearestRangeState;", "nearestRangeState", "Landroidx/compose/foundation/lazy/layout/LazyLayoutNearestRangeState;", "getNearestRangeState", "()Landroidx/compose/foundation/lazy/layout/LazyLayoutNearestRangeState;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PagerScrollPosition {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: currentPage$delegate, reason: from kotlin metadata */
    private final MutableIntState currentPage;

    /* JADX INFO: renamed from: currentPageOffsetFraction$delegate, reason: from kotlin metadata */
    private final MutableFloatState currentPageOffsetFraction;
    private boolean hadFirstNotEmptyLayout;
    private Object lastKnownCurrentPageKey;
    private final LazyLayoutNearestRangeState nearestRangeState;
    private final PagerState state;

    public PagerScrollPosition(int i10, float f10, PagerState pagerState) {
        this.state = pagerState;
        this.currentPage = SnapshotIntStateKt.mutableIntStateOf(i10);
        this.currentPageOffsetFraction = PrimitiveSnapshotStateKt.mutableFloatStateOf(f10);
        this.nearestRangeState = new LazyLayoutNearestRangeState(i10, 30, 100);
    }

    private final void setCurrentPage(int i10) {
        this.currentPage.setIntValue(i10);
    }

    private final void setCurrentPageOffsetFraction(float f10) {
        this.currentPageOffsetFraction.setFloatValue(f10);
    }

    private final void update(int page, float pageOffsetFraction) {
        setCurrentPage(page);
        this.nearestRangeState.update(page);
        if (Math.abs(pageOffsetFraction) == 0.0f) {
            pageOffsetFraction = 0.0f;
        }
        setCurrentPageOffsetFraction(pageOffsetFraction);
    }

    public final void applyScrollDelta(int delta) {
        setCurrentPageOffsetFraction(getCurrentPageOffsetFraction() + (this.state.getPageSizeWithSpacing$foundation_release() == 0 ? 0.0f : delta / this.state.getPageSizeWithSpacing$foundation_release()));
    }

    public final int currentScrollOffset() {
        return t7.a.M((getCurrentPageOffsetFraction() + getCurrentPage()) * this.state.getPageSizeWithSpacing$foundation_release());
    }

    public final int getCurrentPage() {
        return this.currentPage.getIntValue();
    }

    public final float getCurrentPageOffsetFraction() {
        return this.currentPageOffsetFraction.getFloatValue();
    }

    public final LazyLayoutNearestRangeState getNearestRangeState() {
        return this.nearestRangeState;
    }

    public final PagerState getState() {
        return this.state;
    }

    public final int matchPageWithKey(PagerLazyLayoutItemProvider itemProvider, int index) {
        int iFindIndexByKey = LazyLayoutItemProviderKt.findIndexByKey(itemProvider, this.lastKnownCurrentPageKey, index);
        if (index != iFindIndexByKey) {
            setCurrentPage(iFindIndexByKey);
            this.nearestRangeState.update(index);
        }
        return iFindIndexByKey;
    }

    public final void requestPosition(int index, float scrollOffsetFraction) {
        update(index, scrollOffsetFraction);
        this.lastKnownCurrentPageKey = null;
    }

    public final void updateCurrentPageOffsetFraction(float offsetFraction) {
        setCurrentPageOffsetFraction(offsetFraction);
    }

    public final void updateFromMeasureResult(PagerMeasureResult measureResult) {
        MeasuredPage currentPage = measureResult.getCurrentPage();
        this.lastKnownCurrentPageKey = currentPage != null ? currentPage.getKey() : null;
        if (this.hadFirstNotEmptyLayout || !measureResult.getVisiblePagesInfo().isEmpty()) {
            this.hadFirstNotEmptyLayout = true;
            MeasuredPage currentPage2 = measureResult.getCurrentPage();
            update(currentPage2 != null ? currentPage2.getIndex() : 0, measureResult.getCurrentPageOffsetFraction());
        }
    }

    public /* synthetic */ PagerScrollPosition(int i10, float f10, PagerState pagerState, int i11, h hVar) {
        this((i11 & 1) != 0 ? 0 : i10, (i11 & 2) != 0 ? 0.0f : f10, pagerState);
    }
}

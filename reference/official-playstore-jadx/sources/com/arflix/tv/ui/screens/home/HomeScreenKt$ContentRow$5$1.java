package com.arflix.tv.ui.screens.home;

import androidx.compose.foundation.lazy.LazyListItemInfo;
import androidx.compose.foundation.lazy.LazyListState;
import androidx.compose.runtime.MutableIntState;
import kotlin.Metadata;
import x6.t0;

/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lka/k0;", "Lx6/t0;", "<anonymous>", "(Lka/k0;)V"}, k = 3, mv = {2, 3, 0})
@f7.e(c = "com.arflix.tv.ui.screens.home.HomeScreenKt$ContentRow$5$1", f = "HomeScreen.kt", l = {3568, 3577, 3585, 3600, 3603, 3606}, m = "invokeSuspend", v = 2)
public final class HomeScreenKt$ContentRow$5$1 extends f7.j implements r7.p<ka.k0, d7.d<? super t0>, Object> {
    final /* synthetic */ int $focusedItemIndex;
    final /* synthetic */ boolean $isCurrentRow;
    final /* synthetic */ boolean $isFastScrolling;
    final /* synthetic */ boolean $isRtlLayout;
    final /* synthetic */ boolean $isScrollable;
    final /* synthetic */ float $itemSpanPx;
    final /* synthetic */ MutableIntState $lastScrollIndex$delegate;
    final /* synthetic */ MutableIntState $lastScrollOffset$delegate;
    final /* synthetic */ int $maxFirstIndex;
    final /* synthetic */ LazyListState $rowState;
    final /* synthetic */ boolean $smoothScrolling;
    final /* synthetic */ int $totalItems;
    float F$0;
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    int I$4;
    int I$5;
    int I$6;
    int I$7;
    int I$8;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HomeScreenKt$ContentRow$5$1(boolean z, int i10, int i11, LazyListState lazyListState, int i12, boolean z5, boolean z10, float f10, boolean z11, boolean z12, MutableIntState mutableIntState, MutableIntState mutableIntState2, d7.d<? super HomeScreenKt$ContentRow$5$1> dVar) {
        super(2, dVar);
        this.$isCurrentRow = z;
        this.$focusedItemIndex = i10;
        this.$totalItems = i11;
        this.$rowState = lazyListState;
        this.$maxFirstIndex = i12;
        this.$isScrollable = z5;
        this.$smoothScrolling = z10;
        this.$itemSpanPx = f10;
        this.$isFastScrolling = z11;
        this.$isRtlLayout = z12;
        this.$lastScrollIndex$delegate = mutableIntState;
        this.$lastScrollOffset$delegate = mutableIntState2;
    }

    @Override // f7.a
    public final d7.d<t0> create(Object obj, d7.d<?> dVar) {
        return new HomeScreenKt$ContentRow$5$1(this.$isCurrentRow, this.$focusedItemIndex, this.$totalItems, this.$rowState, this.$maxFirstIndex, this.$isScrollable, this.$smoothScrolling, this.$itemSpanPx, this.$isFastScrolling, this.$isRtlLayout, this.$lastScrollIndex$delegate, this.$lastScrollOffset$delegate, dVar);
    }

    @Override // f7.a
    public final Object invokeSuspend(Object obj) {
        int i10;
        int firstVisibleItemScrollOffset;
        int i11;
        int i12;
        int i13;
        int index;
        int i14;
        float f10;
        int i15;
        int i16;
        int i17;
        LazyListState lazyListState;
        int i18 = this.label;
        t0 t0Var = t0.f22605a;
        e7.a aVar = e7.a.f15033i;
        switch (i18) {
            case 0:
                k2.c.G(obj);
                if (this.$isCurrentRow && this.$focusedItemIndex >= 0 && this.$totalItems != 0) {
                    int firstVisibleItemIndex = this.$rowState.getFirstVisibleItemIndex();
                    int i19 = this.$maxFirstIndex;
                    i10 = firstVisibleItemIndex > i19 ? i19 : firstVisibleItemIndex;
                    firstVisibleItemScrollOffset = this.$rowState.getFirstVisibleItemScrollOffset();
                    if (!this.$isScrollable || this.$lastScrollIndex$delegate.getIntValue() == -1) {
                        i11 = this.$focusedItemIndex;
                        int i20 = this.$maxFirstIndex;
                        if (i11 > i20) {
                            i11 = i20;
                        }
                    } else {
                        i11 = this.$focusedItemIndex;
                        if (i11 == i10) {
                            i11 = i10;
                        } else if (i11 < 0) {
                            i11 = 0;
                        }
                    }
                    int i21 = this.$maxFirstIndex;
                    i12 = i11 > i21 ? i21 : i11;
                    if (this.$lastScrollIndex$delegate.getIntValue() == i12 && this.$lastScrollOffset$delegate.getIntValue() == 0) {
                        return t0Var;
                    }
                    i13 = this.$lastScrollIndex$delegate.getIntValue() == -1 ? 1 : 0;
                    this.$lastScrollIndex$delegate.setIntValue(i12);
                    this.$lastScrollOffset$delegate.setIntValue(0);
                    if (i13 != 0) {
                        LazyListState lazyListState2 = this.$rowState;
                        this.I$0 = i10;
                        this.I$1 = firstVisibleItemScrollOffset;
                        this.I$2 = i12;
                        this.I$3 = 0;
                        this.I$4 = i13;
                        this.label = 1;
                        if (lazyListState2.scrollToItem(i12, 0, this) != aVar) {
                            return t0Var;
                        }
                    } else {
                        LazyListItemInfo lazyListItemInfo = (LazyListItemInfo) kotlin.collections.x.y0(this.$rowState.getLayoutInfo().getVisibleItemsInfo());
                        index = lazyListItemInfo != null ? lazyListItemInfo.getIndex() : i10;
                        int i22 = this.$focusedItemIndex;
                        i14 = (i22 < i10 || i22 > index) ? 1 : 0;
                        int i23 = i12 - i10;
                        int iAbs = Math.abs(i23);
                        int i24 = 0 - firstVisibleItemScrollOffset;
                        int iAbs2 = Math.abs(i24);
                        if (iAbs > 7) {
                            LazyListState lazyListState3 = this.$rowState;
                            this.I$0 = i10;
                            this.I$1 = firstVisibleItemScrollOffset;
                            this.I$2 = i12;
                            this.I$3 = 0;
                            this.I$4 = i13;
                            this.I$5 = index;
                            this.I$6 = i14;
                            this.I$7 = iAbs;
                            this.I$8 = iAbs2;
                            this.label = 2;
                            if (lazyListState3.scrollToItem(i12, 0, this) == aVar) {
                            }
                        } else if (i12 == i10 && i14 == 0 && iAbs2 <= 1) {
                            LazyListState lazyListState4 = this.$rowState;
                            this.I$0 = i10;
                            this.I$1 = firstVisibleItemScrollOffset;
                            this.I$2 = i12;
                            this.I$3 = 0;
                            this.I$4 = i13;
                            this.I$5 = index;
                            this.I$6 = i14;
                            this.I$7 = iAbs;
                            this.I$8 = iAbs2;
                            this.label = 6;
                            if (lazyListState4.scrollToItem(i12, 0, this) == aVar) {
                            }
                        } else if (this.$smoothScrolling) {
                            f10 = (i23 * this.$itemSpanPx) + i24;
                            LazyListState lazyListState5 = this.$rowState;
                            int i25 = this.$isFastScrolling ? 115 : iAbs >= 3 ? 180 : 150;
                            boolean z = this.$isRtlLayout;
                            this.I$0 = i10;
                            this.I$1 = firstVisibleItemScrollOffset;
                            this.I$2 = i12;
                            this.I$3 = 0;
                            this.I$4 = i13;
                            this.I$5 = index;
                            this.I$6 = i14;
                            this.I$7 = iAbs;
                            this.I$8 = iAbs2;
                            this.F$0 = f10;
                            this.label = 3;
                            if (HomeScreenKt.animateHomeScrollDelta(lazyListState5, f10, i25, z, this) != aVar) {
                                i15 = iAbs;
                                i16 = iAbs2;
                                i17 = 0;
                                if (!this.$isFastScrolling && (this.$rowState.getFirstVisibleItemIndex() != i12 || Math.abs(this.$rowState.getFirstVisibleItemScrollOffset() - i17) > 6)) {
                                    lazyListState = this.$rowState;
                                    this.I$0 = i10;
                                    this.I$1 = firstVisibleItemScrollOffset;
                                    this.I$2 = i12;
                                    this.I$3 = i17;
                                    this.I$4 = i13;
                                    this.I$5 = index;
                                    this.I$6 = i14;
                                    this.I$7 = i15;
                                    this.I$8 = i16;
                                    this.F$0 = f10;
                                    this.label = 4;
                                    if (lazyListState.scrollToItem(i12, i17, this) == aVar) {
                                    }
                                }
                            }
                        } else {
                            LazyListState lazyListState6 = this.$rowState;
                            this.I$0 = i10;
                            this.I$1 = firstVisibleItemScrollOffset;
                            this.I$2 = i12;
                            this.I$3 = 0;
                            this.I$4 = i13;
                            this.I$5 = index;
                            this.I$6 = i14;
                            this.I$7 = iAbs;
                            this.I$8 = iAbs2;
                            this.label = 5;
                            if (lazyListState6.animateScrollToItem(i12, 0, this) == aVar) {
                            }
                        }
                    }
                    return aVar;
                }
                return t0Var;
            case 1:
                k2.c.G(obj);
                return t0Var;
            case 2:
            case 4:
            case 5:
            case 6:
                k2.c.G(obj);
                return t0Var;
            case 3:
                f10 = this.F$0;
                i16 = this.I$8;
                i15 = this.I$7;
                i14 = this.I$6;
                index = this.I$5;
                i13 = this.I$4;
                i17 = this.I$3;
                i12 = this.I$2;
                firstVisibleItemScrollOffset = this.I$1;
                i10 = this.I$0;
                k2.c.G(obj);
                if (!this.$isFastScrolling) {
                    lazyListState = this.$rowState;
                    this.I$0 = i10;
                    this.I$1 = firstVisibleItemScrollOffset;
                    this.I$2 = i12;
                    this.I$3 = i17;
                    this.I$4 = i13;
                    this.I$5 = index;
                    this.I$6 = i14;
                    this.I$7 = i15;
                    this.I$8 = i16;
                    this.F$0 = f10;
                    this.label = 4;
                    if (lazyListState.scrollToItem(i12, i17, this) == aVar) {
                        return aVar;
                    }
                }
                return t0Var;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    @Override // r7.p
    public final Object invoke(ka.k0 k0Var, d7.d<? super t0> dVar) {
        return ((HomeScreenKt$ContentRow$5$1) create(k0Var, dVar)).invokeSuspend(t0.f22605a);
    }
}

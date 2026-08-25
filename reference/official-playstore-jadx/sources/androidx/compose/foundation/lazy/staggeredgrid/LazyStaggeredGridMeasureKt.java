package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.runtime.snapshots.Snapshot;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.List;
import ka.k0;
import kotlin.Metadata;
import kotlin.collections.p;
import kotlin.collections.z;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0094\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b \u001a7\u0010\u0006\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u00002\u0006\u0010\u0002\u001a\u00020\u00012\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\u0002\b\u0004H\u0083\b¢\u0006\u0004\b\u0006\u0010\u0007\u001a\u001f\u0010\f\u001a\u00020\u000b*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bH\u0002¢\u0006\u0004\b\f\u0010\r\u001a\u001e\u0010\u0011\u001a\u00020\u00102\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000eH\u0082\b¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u0084\u0001\u0010,\u001a\u00020)*\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00132\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\u00162\u0006\u0010$\u001a\u00020\u00162\u0006\u0010%\u001a\u00020\u00162\u0006\u0010&\u001a\u00020\u00162\u0006\u0010(\u001a\u00020'H\u0001ø\u0001\u0000¢\u0006\u0004\b*\u0010+\u001a3\u00103\u001a\u00020)*\u00020-2\u0006\u0010.\u001a\u00020\u00162\u0006\u00100\u001a\u00020/2\u0006\u00101\u001a\u00020/2\u0006\u00102\u001a\u00020\u001eH\u0003¢\u0006\u0004\b3\u00104\u001a=\u00108\u001a\b\u0012\u0004\u0012\u00020\n0\u0015*\u00020-2\u0012\u00105\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u00106\u001a\u00020/2\u0006\u00107\u001a\u00020\u0016H\u0002¢\u0006\u0004\b8\u00109\u001aY\u0010@\u001a\b\u0012\u0004\u0012\u00020\n0\u0015*\u00020-2\u0012\u0010:\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00100\u00032!\u0010>\u001a\u001d\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b;\u0012\b\b<\u0012\u0004\b\b(=\u0012\u0004\u0012\u00020\u001e0\u00032\u0006\u0010?\u001a\u00020\u001eH\u0083\b¢\u0006\u0004\b@\u0010A\u001a>\u0010D\u001a\u00020\u0010\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00152\b\b\u0002\u0010B\u001a\u00020\u001e2\u0012\u0010C\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00100\u0003H\u0082\b¢\u0006\u0004\bD\u0010E\u001a+\u0010I\u001a\u00020\u0010*\u00020F2\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00100\u0003H\u0082\bø\u0001\u0000¢\u0006\u0004\bG\u0010H\u001a\u001b\u0010K\u001a\u00020\u0010*\u00020/2\u0006\u0010J\u001a\u00020\u0016H\u0002¢\u0006\u0004\bK\u0010L\u001a\u001e\u0010P\u001a\u00020\u0016*\u00020/2\u0006\u0010M\u001a\u00020FH\u0002ø\u0001\u0000¢\u0006\u0004\bN\u0010O\u001a\u001d\u0010R\u001a\u00020\u0016*\u00020/2\b\b\u0002\u0010Q\u001a\u00020\u0016H\u0000¢\u0006\u0004\bR\u0010S\u001a4\u0010T\u001a\u00020\u0016\"\u0004\b\u0000\u0010\u0000*\b\u0012\u0004\u0012\u00028\u00000\b2\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00160\u0003H\u0082\b¢\u0006\u0004\bT\u0010U\u001a\u0013\u0010V\u001a\u00020\u0016*\u00020/H\u0002¢\u0006\u0004\bV\u0010W\u001a(\u0010X\u001a\u00020/*\u00020/2\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00160\u0003H\u0082\b¢\u0006\u0004\bX\u0010Y\u001a#\u0010\\\u001a\u00020\u0010*\u00020-2\u0006\u0010Z\u001a\u00020/2\u0006\u0010[\u001a\u00020\u0016H\u0002¢\u0006\u0004\b\\\u0010]\u001a#\u0010`\u001a\u00020\u0016*\u00020-2\u0006\u0010^\u001a\u00020\u00162\u0006\u0010_\u001a\u00020\u0016H\u0002¢\u0006\u0004\b`\u0010a\"\u0014\u0010b\u001a\u00020\u001e8\u0002X\u0082T¢\u0006\u0006\n\u0004\bb\u0010c\"\u0014\u0010d\u001a\u00020\u00168\u0002X\u0082T¢\u0006\u0006\n\u0004\bd\u0010e\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006f"}, d2 = {"T", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "scope", "Lkotlin/Function1;", "Lx6/n;", "block", "withDebugLogging", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;Lr7/l;)Ljava/lang/Object;", "", "Lkotlin/collections/p;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasuredItem;", "", "debugRender", "([Lkotlin/collections/p;)Ljava/lang/String;", "Lkotlin/Function0;", "message", "Lx6/t0;", "debugLog", "(Lr7/a;)V", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "state", "", "", "pinnedItems", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemProvider;", "itemProvider", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSlots;", "resolvedSlots", "Landroidx/compose/ui/unit/Constraints;", "constraints", "", "isVertical", "reverseLayout", "Landroidx/compose/ui/unit/IntOffset;", "contentOffset", "mainAxisAvailableSize", "mainAxisSpacing", "beforeContentPadding", "afterContentPadding", "Lka/k0;", "coroutineScope", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "measureStaggeredGrid-sdzDtKU", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;Ljava/util/List;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemProvider;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSlots;JZZJIIIILka/k0;)Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "measureStaggeredGrid", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureContext;", "initialScrollDelta", "", "initialItemIndices", "initialItemOffsets", "canRestartMeasure", "measure", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureContext;I[I[IZ)Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "measuredItems", "itemScrollOffsets", "mainAxisLayoutSize", "calculateVisibleItems", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureContext;[Lkotlin/collections/p;[II)Ljava/util/List;", "position", "Lx6/y;", ContentDisposition.Parameters.Name, "itemIndex", "filter", "beforeVisibleBounds", "calculateExtraItems", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureContext;Lr7/l;Lr7/l;Z)Ljava/util/List;", "reverse", "action", "fastForEach", "(Ljava/util/List;ZLr7/l;)V", "Landroidx/compose/foundation/lazy/staggeredgrid/SpanRange;", "forEach-nIS5qE8", "(JLr7/l;)V", "forEach", "delta", "offsetBy", "([II)V", "indexRange", "maxInRange-jy6DScQ", "([IJ)I", "maxInRange", "minBound", "indexOfMinValue", "([II)I", "indexOfMinBy", "([Ljava/lang/Object;Lr7/l;)I", "indexOfMaxValue", "([I)I", "transform", "([ILr7/l;)[I", "indices", "itemCount", "ensureIndicesInRange", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureContext;[II)V", "item", "lane", "findPreviousItemIndex", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureContext;II)I", "DebugLoggingEnabled", "Z", "Unset", "I", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyStaggeredGridMeasureKt {
    private static final boolean DebugLoggingEnabled = false;
    private static final int Unset = Integer.MIN_VALUE;

    private static final List<LazyStaggeredGridMeasuredItem> calculateExtraItems(LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext, l<? super LazyStaggeredGridMeasuredItem, t0> lVar, l<? super Integer, Boolean> lVar2, boolean z) {
        List<Integer> pinnedItems = lazyStaggeredGridMeasureContext.getPinnedItems();
        ArrayList arrayList = null;
        if (z) {
            int size = pinnedItems.size() - 1;
            if (size >= 0) {
                while (true) {
                    int i10 = size - 1;
                    int iIntValue = pinnedItems.get(size).intValue();
                    if (((Boolean) lVar2.invoke(Integer.valueOf(iIntValue))).booleanValue()) {
                        long jM724getSpanRangelOCCd4c = lazyStaggeredGridMeasureContext.m724getSpanRangelOCCd4c(lazyStaggeredGridMeasureContext.getItemProvider(), iIntValue, 0);
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemM732getAndMeasurejy6DScQ = lazyStaggeredGridMeasureContext.getMeasuredItemProvider().m732getAndMeasurejy6DScQ(iIntValue, jM724getSpanRangelOCCd4c);
                        lVar.invoke(lazyStaggeredGridMeasuredItemM732getAndMeasurejy6DScQ);
                        arrayList.add(lazyStaggeredGridMeasuredItemM732getAndMeasurejy6DScQ);
                    }
                    if (i10 < 0) {
                        break;
                    }
                    size = i10;
                }
            }
        } else {
            int size2 = pinnedItems.size();
            for (int i11 = 0; i11 < size2; i11++) {
                int iIntValue2 = pinnedItems.get(i11).intValue();
                if (((Boolean) lVar2.invoke(Integer.valueOf(iIntValue2))).booleanValue()) {
                    long jM724getSpanRangelOCCd4c2 = lazyStaggeredGridMeasureContext.m724getSpanRangelOCCd4c(lazyStaggeredGridMeasureContext.getItemProvider(), iIntValue2, 0);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemM732getAndMeasurejy6DScQ2 = lazyStaggeredGridMeasureContext.getMeasuredItemProvider().m732getAndMeasurejy6DScQ(iIntValue2, jM724getSpanRangelOCCd4c2);
                    lVar.invoke(lazyStaggeredGridMeasuredItemM732getAndMeasurejy6DScQ2);
                    arrayList.add(lazyStaggeredGridMeasuredItemM732getAndMeasurejy6DScQ2);
                }
            }
        }
        return arrayList == null ? z.f19728i : arrayList;
    }

    private static final List<LazyStaggeredGridMeasuredItem> calculateVisibleItems(LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext, p[] pVarArr, int[] iArr, int i10) {
        int size = 0;
        for (p pVar : pVarArr) {
            size += pVar.size();
        }
        ArrayList arrayList = new ArrayList(size);
        while (true) {
            for (p pVar2 : pVarArr) {
                if (!pVar2.isEmpty()) {
                    int length = pVarArr.length;
                    int i11 = -1;
                    int i12 = Integer.MAX_VALUE;
                    for (int i13 = 0; i13 < length; i13++) {
                        LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem = (LazyStaggeredGridMeasuredItem) pVarArr[i13].c();
                        int index = lazyStaggeredGridMeasuredItem != null ? lazyStaggeredGridMeasuredItem.getIndex() : Integer.MAX_VALUE;
                        if (i12 > index) {
                            i11 = i13;
                            i12 = index;
                        }
                    }
                    LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem2 = (LazyStaggeredGridMeasuredItem) pVarArr[i11].removeFirst();
                    if (lazyStaggeredGridMeasuredItem2.getLane() == i11) {
                        long jM737constructorimpl = SpanRange.m737constructorimpl(lazyStaggeredGridMeasuredItem2.getLane(), lazyStaggeredGridMeasuredItem2.getSpan());
                        int iM727maxInRangejy6DScQ = m727maxInRangejy6DScQ(iArr, jM737constructorimpl);
                        int i14 = lazyStaggeredGridMeasureContext.getResolvedSlots().getPositions()[i11];
                        if (lazyStaggeredGridMeasuredItem2.getPlaceablesCount() != 0) {
                            lazyStaggeredGridMeasuredItem2.position(iM727maxInRangejy6DScQ, i14, i10);
                            arrayList.add(lazyStaggeredGridMeasuredItem2);
                            int i15 = (int) (jM737constructorimpl & 4294967295L);
                            for (int i16 = (int) (jM737constructorimpl >> 32); i16 < i15; i16++) {
                                iArr[i16] = lazyStaggeredGridMeasuredItem2.getSizeWithSpacings() + iM727maxInRangejy6DScQ;
                            }
                        }
                    }
                }
            }
            return arrayList;
        }
    }

    private static final String debugRender(p[] pVarArr) {
        return "";
    }

    private static final void ensureIndicesInRange(LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext, int[] iArr, int i10) {
        int length = iArr.length - 1;
        if (length < 0) {
            return;
        }
        while (true) {
            int i11 = length - 1;
            while (true) {
                if (iArr[length] < i10 && lazyStaggeredGridMeasureContext.getLaneInfo().assignedToLane(iArr[length], length)) {
                    break;
                } else {
                    iArr[length] = findPreviousItemIndex(lazyStaggeredGridMeasureContext, iArr[length], length);
                }
            }
            if (iArr[length] >= 0 && !lazyStaggeredGridMeasureContext.isFullSpan(lazyStaggeredGridMeasureContext.getItemProvider(), iArr[length])) {
                lazyStaggeredGridMeasureContext.getLaneInfo().setLane(iArr[length], length);
            }
            if (i11 < 0) {
                return;
            } else {
                length = i11;
            }
        }
    }

    private static final <T> void fastForEach(List<? extends T> list, boolean z, l<? super T, t0> lVar) {
        if (!z) {
            int size = list.size();
            for (int i10 = 0; i10 < size; i10++) {
                lVar.invoke(list.get(i10));
            }
            return;
        }
        int size2 = list.size() - 1;
        if (size2 < 0) {
            return;
        }
        while (true) {
            int i11 = size2 - 1;
            lVar.invoke(list.get(size2));
            if (i11 < 0) {
                return;
            } else {
                size2 = i11;
            }
        }
    }

    public static /* synthetic */ void fastForEach$default(List list, boolean z, l lVar, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            z = false;
        }
        if (!z) {
            int size = list.size();
            for (int i11 = 0; i11 < size; i11++) {
                lVar.invoke(list.get(i11));
            }
            return;
        }
        int size2 = list.size() - 1;
        if (size2 < 0) {
            return;
        }
        while (true) {
            int i12 = size2 - 1;
            lVar.invoke(list.get(size2));
            if (i12 < 0) {
                return;
            } else {
                size2 = i12;
            }
        }
    }

    private static final int findPreviousItemIndex(LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext, int i10, int i11) {
        return lazyStaggeredGridMeasureContext.getLaneInfo().findPreviousItemIndex(i10, i11);
    }

    /* JADX INFO: renamed from: forEach-nIS5qE8, reason: not valid java name */
    private static final void m726forEachnIS5qE8(long j10, l<? super Integer, t0> lVar) {
        int i10 = (int) (j10 & 4294967295L);
        for (int i11 = (int) (j10 >> 32); i11 < i10; i11++) {
            lVar.invoke(Integer.valueOf(i11));
        }
    }

    private static final int indexOfMaxValue(int[] iArr) {
        int length = iArr.length;
        int i10 = -1;
        int i11 = Integer.MIN_VALUE;
        for (int i12 = 0; i12 < length; i12++) {
            int i13 = iArr[i12];
            if (i11 < i13) {
                i10 = i12;
                i11 = i13;
            }
        }
        return i10;
    }

    private static final <T> int indexOfMinBy(T[] tArr, l<? super T, Integer> lVar) {
        int length = tArr.length;
        int i10 = -1;
        int i11 = Integer.MAX_VALUE;
        for (int i12 = 0; i12 < length; i12++) {
            int iIntValue = ((Number) lVar.invoke(tArr[i12])).intValue();
            if (i11 > iIntValue) {
                i10 = i12;
                i11 = iIntValue;
            }
        }
        return i10;
    }

    public static final int indexOfMinValue(int[] iArr, int i10) {
        int length = iArr.length;
        int i11 = -1;
        int i12 = Integer.MAX_VALUE;
        for (int i13 = 0; i13 < length; i13++) {
            int i14 = i10 + 1;
            int i15 = iArr[i13];
            if (i14 <= i15 && i15 < i12) {
                i11 = i13;
                i12 = i15;
            }
        }
        return i11;
    }

    public static /* synthetic */ int indexOfMinValue$default(int[] iArr, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = Integer.MIN_VALUE;
        }
        return indexOfMinValue(iArr, i10);
    }

    /* JADX INFO: renamed from: maxInRange-jy6DScQ, reason: not valid java name */
    private static final int m727maxInRangejy6DScQ(int[] iArr, long j10) {
        int i10 = (int) (j10 & 4294967295L);
        int iMax = Integer.MIN_VALUE;
        for (int i11 = (int) (j10 >> 32); i11 < i10; i11++) {
            iMax = Math.max(iMax, iArr[i11]);
        }
        return iMax;
    }

    /* JADX WARN: Code restructure failed: missing block: B:155:0x02f6, code lost:
    
        r3 = indexOfMinValue$default(r8, 0, 1, null);
        r4 = indexOfMaxValue(r29);
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x0301, code lost:
    
        if (r3 == r4) goto L161;
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x0307, code lost:
    
        if (r8[r3] != r8[r4]) goto L160;
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x0309, code lost:
    
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x030b, code lost:
    
        r0 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x030c, code lost:
    
        r4 = r29[r3];
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x030f, code lost:
    
        if (r4 != (-1)) goto L164;
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x0311, code lost:
    
        r4 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x0312, code lost:
    
        r15 = r39;
        r4 = findPreviousItemIndex(r15, r4, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x0318, code lost:
    
        if (r4 >= 0) goto L182;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x031a, code lost:
    
        r9 = r29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x031c, code lost:
    
        if (r0 != false) goto L172;
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x0322, code lost:
    
        if (measure$lambda$38$misalignedStart(r9, r15, r8, r3) == false) goto L171;
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x0325, code lost:
    
        r6 = r28;
     */
    /* JADX WARN: Code restructure failed: missing block: B:172:0x0328, code lost:
    
        if (r43 == false) goto L171;
     */
    /* JADX WARN: Code restructure failed: missing block: B:173:0x032a, code lost:
    
        r15.getLaneInfo().reset();
        r0 = r9.length;
        r1 = new int[r0];
        r2 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:174:0x0335, code lost:
    
        if (r2 >= r0) goto L440;
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x0337, code lost:
    
        r1[r2] = -1;
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:176:0x033e, code lost:
    
        r0 = r8.length;
        r2 = new int[r0];
        r4 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:177:0x0342, code lost:
    
        if (r4 >= r0) goto L441;
     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x0344, code lost:
    
        r2[r4] = r8[r3];
        r4 = r4 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:180:0x0352, code lost:
    
        return measure(r15, r28, r1, r2, false);
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x0353, code lost:
    
        r41 = r1;
        r29 = r2;
        r25 = r11;
        r11 = r14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:182:0x035c, code lost:
    
        r6 = r28;
        r9 = r29;
        r13 = r0;
        r41 = r1;
        r0 = r15.m724getSpanRangelOCCd4c(r15.getItemProvider(), r4, r3);
        r3 = r15.getLaneInfo();
        r25 = r11;
        r10 = (int) (r0 & 4294967295L);
        r28 = r13;
        r11 = r14;
        r13 = (int) (r0 >> 32);
        r14 = r10 - r13;
        r29 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x037f, code lost:
    
        if (r14 == 1) goto L185;
     */
    /* JADX WARN: Code restructure failed: missing block: B:184:0x0381, code lost:
    
        r2 = -2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:185:0x0383, code lost:
    
        r2 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x0384, code lost:
    
        r3.setLane(r4, r2);
        r2 = r15.getMeasuredItemProvider().m732getAndMeasurejy6DScQ(r4, r0);
        r0 = m727maxInRangejy6DScQ(r8, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x0394, code lost:
    
        if (r14 == 1) goto L189;
     */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x0396, code lost:
    
        r3 = r15.getLaneInfo().getGaps(r4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:189:0x039f, code lost:
    
        r3 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:190:0x03a0, code lost:
    
        if (r13 >= r10) goto L436;
     */
    /* JADX WARN: Code restructure failed: missing block: B:192:0x03a4, code lost:
    
        if (r8[r13] == r0) goto L194;
     */
    /* JADX WARN: Code restructure failed: missing block: B:193:0x03a6, code lost:
    
        r28 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:194:0x03a8, code lost:
    
        r27[r13].addFirst(r2);
        r9[r13] = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:195:0x03af, code lost:
    
        if (r3 != null) goto L197;
     */
    /* JADX WARN: Code restructure failed: missing block: B:196:0x03b1, code lost:
    
        r1 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:197:0x03b3, code lost:
    
        r1 = r3[r13];
     */
    /* JADX WARN: Code restructure failed: missing block: B:198:0x03b5, code lost:
    
        r8[r13] = (r2.getSizeWithSpacings() + r0) + r1;
        r13 = r13 + 1;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:304:0x05f0  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x061f  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x06a0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureResult measure(androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureContext r39, int r40, int[] r41, int[] r42, boolean r43) {
        /*
            Method dump skipped, instruction units count: 2024
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureKt.measure(androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureContext, int, int[], int[], boolean):androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureResult");
    }

    private static final boolean measure$lambda$38$hasSpaceBeforeFirst(int[] iArr, int[] iArr2, LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext) {
        int length = iArr.length;
        for (int i10 = 0; i10 < length; i10++) {
            int i11 = iArr[i10];
            if (iArr2[i10] < Math.max(-lazyStaggeredGridMeasureContext.getMainAxisSpacing(), 0) && i11 > 0) {
                return true;
            }
        }
        return false;
    }

    private static final boolean measure$lambda$38$misalignedStart(int[] iArr, LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext, int[] iArr2, int i10) {
        int length = iArr.length;
        for (int i11 = 0; i11 < length; i11++) {
            if (findPreviousItemIndex(lazyStaggeredGridMeasureContext, iArr[i11], i11) == -1 && iArr2[i11] != iArr2[i10]) {
                return true;
            }
        }
        int length2 = iArr.length;
        for (int i12 = 0; i12 < length2; i12++) {
            if (findPreviousItemIndex(lazyStaggeredGridMeasureContext, iArr[i12], i12) != -1 && iArr2[i12] >= iArr2[i10]) {
                return true;
            }
        }
        int lane = lazyStaggeredGridMeasureContext.getLaneInfo().getLane(0);
        return (lane == 0 || lane == -1 || lane == -2) ? false : true;
    }

    /* JADX INFO: renamed from: measureStaggeredGrid-sdzDtKU, reason: not valid java name */
    public static final LazyStaggeredGridMeasureResult m728measureStaggeredGridsdzDtKU(LazyLayoutMeasureScope lazyLayoutMeasureScope, LazyStaggeredGridState lazyStaggeredGridState, List<Integer> list, LazyStaggeredGridItemProvider lazyStaggeredGridItemProvider, LazyStaggeredGridSlots lazyStaggeredGridSlots, long j10, boolean z, boolean z5, long j11, int i10, int i11, int i12, int i13, k0 k0Var) {
        int iM727maxInRangejy6DScQ;
        LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext = new LazyStaggeredGridMeasureContext(lazyStaggeredGridState, list, lazyStaggeredGridItemProvider, lazyStaggeredGridSlots, j10, z, lazyLayoutMeasureScope, i10, j11, i12, i13, z5, i11, k0Var, null);
        Snapshot snapshotCreateNonObservableSnapshot = Snapshot.INSTANCE.createNonObservableSnapshot();
        try {
            Snapshot snapshotMakeCurrent = snapshotCreateNonObservableSnapshot.makeCurrent();
            try {
                int[] iArrUpdateScrollPositionIfTheFirstItemWasMoved$foundation_release = lazyStaggeredGridState.updateScrollPositionIfTheFirstItemWasMoved$foundation_release(lazyStaggeredGridItemProvider, lazyStaggeredGridState.getScrollPosition().getIndices());
                int[] scrollOffsets = lazyStaggeredGridState.getScrollPosition().getScrollOffsets();
                if (iArrUpdateScrollPositionIfTheFirstItemWasMoved$foundation_release.length != lazyStaggeredGridMeasureContext.getLaneCount()) {
                    lazyStaggeredGridMeasureContext.getLaneInfo().reset();
                    int laneCount = lazyStaggeredGridMeasureContext.getLaneCount();
                    int[] iArr = new int[laneCount];
                    int i14 = 0;
                    while (i14 < laneCount) {
                        if (i14 >= iArrUpdateScrollPositionIfTheFirstItemWasMoved$foundation_release.length || (iM727maxInRangejy6DScQ = iArrUpdateScrollPositionIfTheFirstItemWasMoved$foundation_release[i14]) == -1) {
                            iM727maxInRangejy6DScQ = i14 == 0 ? 0 : m727maxInRangejy6DScQ(iArr, SpanRange.m737constructorimpl(0, i14)) + 1;
                        }
                        iArr[i14] = iM727maxInRangejy6DScQ;
                        lazyStaggeredGridMeasureContext.getLaneInfo().setLane(iArr[i14], i14);
                        i14++;
                    }
                    iArrUpdateScrollPositionIfTheFirstItemWasMoved$foundation_release = iArr;
                }
                if (scrollOffsets.length != lazyStaggeredGridMeasureContext.getLaneCount()) {
                    int laneCount2 = lazyStaggeredGridMeasureContext.getLaneCount();
                    int[] iArr2 = new int[laneCount2];
                    int i15 = 0;
                    while (i15 < laneCount2) {
                        iArr2[i15] = i15 < scrollOffsets.length ? scrollOffsets[i15] : i15 == 0 ? 0 : iArr2[i15 - 1];
                        i15++;
                    }
                    scrollOffsets = iArr2;
                }
                snapshotCreateNonObservableSnapshot.dispose();
                return measure(lazyStaggeredGridMeasureContext, t7.a.M(lazyStaggeredGridState.getScrollToBeConsumed()), iArrUpdateScrollPositionIfTheFirstItemWasMoved$foundation_release, scrollOffsets, true);
            } finally {
                snapshotCreateNonObservableSnapshot.restoreCurrent(snapshotMakeCurrent);
            }
        } catch (Throwable th) {
            snapshotCreateNonObservableSnapshot.dispose();
            throw th;
        }
    }

    private static final void offsetBy(int[] iArr, int i10) {
        int length = iArr.length;
        for (int i11 = 0; i11 < length; i11++) {
            iArr[i11] = iArr[i11] + i10;
        }
    }

    private static final int[] transform(int[] iArr, l<? super Integer, Integer> lVar) {
        int length = iArr.length;
        for (int i10 = 0; i10 < length; i10++) {
            iArr[i10] = ((Number) lVar.invoke(Integer.valueOf(iArr[i10]))).intValue();
        }
        return iArr;
    }

    private static final <T> T withDebugLogging(LazyLayoutMeasureScope lazyLayoutMeasureScope, l<? super LazyLayoutMeasureScope, ? extends T> lVar) {
        return (T) lVar.invoke(lazyLayoutMeasureScope);
    }

    private static final void debugLog(r7.a<String> aVar) {
    }
}

package androidx.compose.foundation.lazy.staggeredgrid;

import a0.c;
import androidx.compose.foundation.lazy.layout.LazyLayoutAnimation;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.media3.extractor.text.ttml.TtmlNode;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u001d\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0000\u0018\u00002\u00020\u0001Bg\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\u0002\u0012\u0006\u0010\f\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000e\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014J+\u0010\u001a\u001a\u00020\u0015*\u00020\u00152\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0016H\u0082\bø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ%\u0010!\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u0002¢\u0006\u0004\b!\u0010\"J\u001d\u0010'\u001a\u00020 2\u0006\u0010$\u001a\u00020#2\u0006\u0010&\u001a\u00020%¢\u0006\u0004\b'\u0010(J\u0015\u0010*\u001a\u00020 2\u0006\u0010)\u001a\u00020\u0002¢\u0006\u0004\b*\u0010+J\u000f\u0010-\u001a\u00020,H\u0016¢\u0006\u0004\b-\u0010.R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010/\u001a\u0004\b0\u00101R\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u00102\u001a\u0004\b3\u00104R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u00105R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u00106\u001a\u0004\b\n\u00107R\u001a\u0010\f\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\f\u0010/\u001a\u0004\b8\u00101R\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010/\u001a\u0004\b9\u00101R\u0014\u0010\u000e\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010/R\u0014\u0010\u000f\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010/R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0010\u00102\u001a\u0004\b:\u00104R\u0014\u0010\u0012\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010;R\"\u0010<\u001a\u00020\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b<\u00106\u001a\u0004\b<\u00107\"\u0004\b=\u0010>R\u0017\u0010?\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b?\u0010/\u001a\u0004\b@\u00101R\u0017\u0010A\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\bA\u0010/\u001a\u0004\bB\u00101R\u0017\u0010C\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\bC\u0010/\u001a\u0004\bD\u00101R\u0016\u0010\u001f\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010/R\u0016\u0010E\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bE\u0010/R\u0016\u0010F\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bF\u0010/R\"\u0010G\u001a\u00020\t8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bG\u00106\u001a\u0004\bH\u00107\"\u0004\bI\u0010>R \u0010K\u001a\u00020J8\u0016X\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\bK\u0010L\u001a\u0004\bM\u0010NR*\u0010P\u001a\u00020\u00152\u0006\u0010O\u001a\u00020\u00158\u0016@RX\u0096\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\f\n\u0004\bP\u0010L\u001a\u0004\bQ\u0010NR\u0018\u0010\u001d\u001a\u00020\u0002*\u00020\u00158BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bR\u0010SR\u0019\u0010?\u001a\u00020\u0002*\u00020\u00078Â\u0002X\u0082\u0004¢\u0006\u0006\u001a\u0004\b@\u0010TR\u0011\u0010V\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\bU\u00101R\u0011\u0010X\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\bW\u00101R\u0011\u0010Z\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\bY\u00101\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006["}, d2 = {"Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasuredItem;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemInfo;", "", "index", "", "key", "", "Landroidx/compose/ui/layout/Placeable;", "placeables", "", "isVertical", "spacing", "lane", TtmlNode.TAG_SPAN, "beforeContentPadding", "afterContentPadding", "contentType", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemPlacementAnimator;", "animator", "<init>", "(ILjava/lang/Object;Ljava/util/List;ZIIIIILjava/lang/Object;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemPlacementAnimator;)V", "Landroidx/compose/ui/unit/IntOffset;", "Lkotlin/Function1;", "mainAxisMap", "copy-4Tuh3kE", "(JLr7/l;)J", "copy", "getParentData", "(I)Ljava/lang/Object;", "mainAxis", "crossAxis", "mainAxisLayoutSize", "Lx6/t0;", "position", "(III)V", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "scope", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureContext;", "context", "place", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureContext;)V", "delta", "applyScrollDelta", "(I)V", "", "toString", "()Ljava/lang/String;", "I", "getIndex", "()I", "Ljava/lang/Object;", "getKey", "()Ljava/lang/Object;", "Ljava/util/List;", "Z", "()Z", "getLane", "getSpan", "getContentType", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemPlacementAnimator;", "isVisible", "setVisible", "(Z)V", "mainAxisSize", "getMainAxisSize", "sizeWithSpacings", "getSizeWithSpacings", "crossAxisSize", "getCrossAxisSize", "minMainAxisOffset", "maxMainAxisOffset", "nonScrollableItem", "getNonScrollableItem", "setNonScrollableItem", "Landroidx/compose/ui/unit/IntSize;", ContentDisposition.Parameters.Size, "J", "getSize-YbymL2g", "()J", "<set-?>", "offset", "getOffset-nOcc-ac", "getMainAxis--gyyYBs", "(J)I", "(Landroidx/compose/ui/layout/Placeable;)I", "getPlaceablesCount", "placeablesCount", "getMainAxisOffset", "mainAxisOffset", "getCrossAxisOffset", "crossAxisOffset", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LazyStaggeredGridMeasuredItem implements LazyStaggeredGridItemInfo {
    public static final int $stable = 8;
    private final int afterContentPadding;
    private final LazyStaggeredGridItemPlacementAnimator animator;
    private final int beforeContentPadding;
    private final Object contentType;
    private final int crossAxisSize;
    private final int index;
    private final boolean isVertical;
    private boolean isVisible = true;
    private final Object key;
    private final int lane;
    private int mainAxisLayoutSize;
    private final int mainAxisSize;
    private int maxMainAxisOffset;
    private int minMainAxisOffset;
    private boolean nonScrollableItem;
    private long offset;
    private final List<Placeable> placeables;
    private final long size;
    private final int sizeWithSpacings;
    private final int span;

    /* JADX WARN: Multi-variable type inference failed */
    public LazyStaggeredGridMeasuredItem(int i10, Object obj, List<? extends Placeable> list, boolean z, int i11, int i12, int i13, int i14, int i15, Object obj2, LazyStaggeredGridItemPlacementAnimator lazyStaggeredGridItemPlacementAnimator) {
        Integer numValueOf;
        this.index = i10;
        this.key = obj;
        this.placeables = list;
        this.isVertical = z;
        this.lane = i12;
        this.span = i13;
        this.beforeContentPadding = i14;
        this.afterContentPadding = i15;
        this.contentType = obj2;
        this.animator = lazyStaggeredGridItemPlacementAnimator;
        int i16 = 1;
        Integer num = null;
        if (list.isEmpty()) {
            numValueOf = null;
        } else {
            Placeable placeable = (Placeable) list.get(0);
            numValueOf = Integer.valueOf(z ? placeable.getHeight() : placeable.getWidth());
            int iW = t7.a.w(list);
            if (1 <= iW) {
                int i17 = 1;
                while (true) {
                    Placeable placeable2 = (Placeable) list.get(i17);
                    Integer numValueOf2 = Integer.valueOf(this.isVertical ? placeable2.getHeight() : placeable2.getWidth());
                    numValueOf = numValueOf2.compareTo(numValueOf) > 0 ? numValueOf2 : numValueOf;
                    if (i17 == iW) {
                        break;
                    } else {
                        i17++;
                    }
                }
            }
        }
        int iIntValue = numValueOf != null ? numValueOf.intValue() : 0;
        this.mainAxisSize = iIntValue;
        int i18 = iIntValue + i11;
        this.sizeWithSpacings = i18 < 0 ? 0 : i18;
        List<Placeable> list2 = this.placeables;
        if (!list2.isEmpty()) {
            Placeable placeable3 = list2.get(0);
            Integer numValueOf3 = Integer.valueOf(this.isVertical ? placeable3.getWidth() : placeable3.getHeight());
            int iW2 = t7.a.w(list2);
            if (1 <= iW2) {
                while (true) {
                    Placeable placeable4 = list2.get(i16);
                    Integer numValueOf4 = Integer.valueOf(this.isVertical ? placeable4.getWidth() : placeable4.getHeight());
                    numValueOf3 = numValueOf4.compareTo(numValueOf3) > 0 ? numValueOf4 : numValueOf3;
                    if (i16 == iW2) {
                        break;
                    } else {
                        i16++;
                    }
                }
            }
            num = numValueOf3;
        }
        int iIntValue2 = num != null ? num.intValue() : 0;
        this.crossAxisSize = iIntValue2;
        this.mainAxisLayoutSize = -1;
        this.size = this.isVertical ? IntSizeKt.IntSize(iIntValue2, this.mainAxisSize) : IntSizeKt.IntSize(this.mainAxisSize, iIntValue2);
        this.offset = IntOffset.INSTANCE.m5812getZeronOccac();
    }

    /* JADX INFO: renamed from: copy-4Tuh3kE, reason: not valid java name */
    private final long m733copy4Tuh3kE(long j10, l<? super Integer, Integer> lVar) {
        int iM5802getXimpl = this.isVertical ? IntOffset.m5802getXimpl(j10) : ((Number) lVar.invoke(Integer.valueOf(IntOffset.m5802getXimpl(j10)))).intValue();
        boolean z = this.isVertical;
        int iM5803getYimpl = IntOffset.m5803getYimpl(j10);
        if (z) {
            iM5803getYimpl = ((Number) lVar.invoke(Integer.valueOf(iM5803getYimpl))).intValue();
        }
        return IntOffsetKt.IntOffset(iM5802getXimpl, iM5803getYimpl);
    }

    /* JADX INFO: renamed from: getMainAxis--gyyYBs, reason: not valid java name */
    private final int m734getMainAxisgyyYBs(long j10) {
        return this.isVertical ? IntOffset.m5803getYimpl(j10) : IntOffset.m5802getXimpl(j10);
    }

    public final void applyScrollDelta(int delta) {
        if (this.nonScrollableItem) {
            return;
        }
        long offset = getOffset();
        int iM5802getXimpl = this.isVertical ? IntOffset.m5802getXimpl(offset) : IntOffset.m5802getXimpl(offset) + delta;
        boolean z = this.isVertical;
        int iM5803getYimpl = IntOffset.m5803getYimpl(offset);
        if (z) {
            iM5803getYimpl += delta;
        }
        this.offset = IntOffsetKt.IntOffset(iM5802getXimpl, iM5803getYimpl);
        int placeablesCount = getPlaceablesCount();
        for (int i10 = 0; i10 < placeablesCount; i10++) {
            LazyLayoutAnimation animation = this.animator.getAnimation(getKey(), i10);
            if (animation != null) {
                long rawOffset = animation.getRawOffset();
                int iM5802getXimpl2 = this.isVertical ? IntOffset.m5802getXimpl(rawOffset) : Integer.valueOf(IntOffset.m5802getXimpl(rawOffset) + delta).intValue();
                boolean z5 = this.isVertical;
                int iM5803getYimpl2 = IntOffset.m5803getYimpl(rawOffset);
                if (z5) {
                    iM5803getYimpl2 += delta;
                }
                animation.m680setRawOffsetgyyYBs(IntOffsetKt.IntOffset(iM5802getXimpl2, iM5803getYimpl2));
            }
        }
    }

    @Override // androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemInfo
    public Object getContentType() {
        return this.contentType;
    }

    public final int getCrossAxisOffset() {
        return this.isVertical ? IntOffset.m5802getXimpl(getOffset()) : IntOffset.m5803getYimpl(getOffset());
    }

    public final int getCrossAxisSize() {
        return this.crossAxisSize;
    }

    @Override // androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemInfo
    public int getIndex() {
        return this.index;
    }

    @Override // androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemInfo
    public Object getKey() {
        return this.key;
    }

    @Override // androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemInfo
    public int getLane() {
        return this.lane;
    }

    public final int getMainAxisOffset() {
        return !this.isVertical ? IntOffset.m5802getXimpl(getOffset()) : IntOffset.m5803getYimpl(getOffset());
    }

    public final int getMainAxisSize() {
        return this.mainAxisSize;
    }

    public final boolean getNonScrollableItem() {
        return this.nonScrollableItem;
    }

    @Override // androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemInfo
    /* JADX INFO: renamed from: getOffset-nOcc-ac, reason: from getter */
    public long getOffset() {
        return this.offset;
    }

    public final Object getParentData(int index) {
        return this.placeables.get(index).getParentData();
    }

    public final int getPlaceablesCount() {
        return this.placeables.size();
    }

    @Override // androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemInfo
    /* JADX INFO: renamed from: getSize-YbymL2g, reason: from getter */
    public long getSize() {
        return this.size;
    }

    public final int getSizeWithSpacings() {
        return this.sizeWithSpacings;
    }

    public final int getSpan() {
        return this.span;
    }

    /* JADX INFO: renamed from: isVertical, reason: from getter */
    public final boolean getIsVertical() {
        return this.isVertical;
    }

    /* JADX INFO: renamed from: isVisible, reason: from getter */
    public final boolean getIsVisible() {
        return this.isVisible;
    }

    public final void place(Placeable.PlacementScope scope, LazyStaggeredGridMeasureContext context) {
        if (this.mainAxisLayoutSize == -1) {
            throw new IllegalArgumentException("position() should be called first");
        }
        List<Placeable> list = this.placeables;
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            Placeable placeable = list.get(i10);
            int height = this.minMainAxisOffset - (this.isVertical ? placeable.getHeight() : placeable.getWidth());
            int i11 = this.maxMainAxisOffset;
            long offset = getOffset();
            LazyLayoutAnimation animation = this.animator.getAnimation(getKey(), i10);
            if (animation != null) {
                long jM677getPlacementDeltanOccac = animation.m677getPlacementDeltanOccac();
                long jC = c.c(IntOffset.m5803getYimpl(offset), IntOffset.m5802getXimpl(jM677getPlacementDeltanOccac) + IntOffset.m5802getXimpl(offset), jM677getPlacementDeltanOccac);
                if ((m734getMainAxisgyyYBs(offset) <= height && m734getMainAxisgyyYBs(jC) <= height) || (m734getMainAxisgyyYBs(offset) >= i11 && m734getMainAxisgyyYBs(jC) >= i11)) {
                    animation.cancelPlacementAnimation();
                }
                offset = jC;
            }
            if (context.getReverseLayout()) {
                offset = IntOffsetKt.IntOffset(this.isVertical ? IntOffset.m5802getXimpl(offset) : (this.mainAxisLayoutSize - IntOffset.m5802getXimpl(offset)) - (this.isVertical ? placeable.getHeight() : placeable.getWidth()), this.isVertical ? (this.mainAxisLayoutSize - IntOffset.m5803getYimpl(offset)) - (this.isVertical ? placeable.getHeight() : placeable.getWidth()) : IntOffset.m5803getYimpl(offset));
            }
            long contentOffset = context.getContentOffset();
            Placeable.PlacementScope.m4671placeRelativeWithLayeraW9wM$default(scope, placeable, c.c(IntOffset.m5803getYimpl(offset), IntOffset.m5802getXimpl(contentOffset) + IntOffset.m5802getXimpl(offset), contentOffset), 0.0f, null, 6, null);
        }
    }

    public final void position(int mainAxis, int crossAxis, int mainAxisLayoutSize) {
        this.mainAxisLayoutSize = mainAxisLayoutSize;
        this.minMainAxisOffset = -this.beforeContentPadding;
        this.maxMainAxisOffset = mainAxisLayoutSize + this.afterContentPadding;
        this.offset = this.isVertical ? IntOffsetKt.IntOffset(crossAxis, mainAxis) : IntOffsetKt.IntOffset(mainAxis, crossAxis);
    }

    public final void setNonScrollableItem(boolean z) {
        this.nonScrollableItem = z;
    }

    public final void setVisible(boolean z) {
        this.isVisible = z;
    }

    public String toString() {
        return super.toString();
    }

    private final int getMainAxisSize(Placeable placeable) {
        return this.isVertical ? placeable.getHeight() : placeable.getWidth();
    }
}

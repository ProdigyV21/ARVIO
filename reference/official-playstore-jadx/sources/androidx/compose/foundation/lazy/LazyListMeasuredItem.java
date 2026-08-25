package androidx.compose.foundation.lazy;

import androidx.compose.foundation.lazy.layout.LazyLayoutAnimation;
import androidx.compose.foundation.lazy.layout.LazyLayoutAnimationKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.LayoutDirection;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\u0015\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B\u0085\u0001\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\u0007\u0012\u0006\u0010\u0010\u001a\u00020\u0002\u0012\u0006\u0010\u0011\u001a\u00020\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0002\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0015\u0012\u0006\u0010\u0019\u001a\u00020\u0018¢\u0006\u0004\b\u001a\u0010\u001bJ+\u0010 \u001a\u00020\u0013*\u00020\u00132\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u001cH\u0082\bø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010!\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b!\u0010\"J%\u0010'\u001a\u00020&2\u0006\u0010#\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u00022\u0006\u0010%\u001a\u00020\u0002¢\u0006\u0004\b'\u0010(J\u001b\u0010+\u001a\u00020\u00132\u0006\u0010\u0003\u001a\u00020\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b)\u0010*J\u001d\u0010.\u001a\u00020&2\u0006\u0010,\u001a\u00020\u00022\u0006\u0010-\u001a\u00020\u0007¢\u0006\u0004\b.\u0010/J\u001d\u00103\u001a\u00020&2\u0006\u00101\u001a\u0002002\u0006\u00102\u001a\u00020\u0007¢\u0006\u0004\b3\u00104R\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u00105\u001a\u0004\b6\u00107R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u00108R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u00109\u001a\u0004\b\b\u0010:R\u0016\u0010\n\u001a\u0004\u0018\u00010\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010;R\u0016\u0010\f\u001a\u0004\u0018\u00010\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010<R\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010=R\u0014\u0010\u000f\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u00109R\u0014\u0010\u0010\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u00105R\u0014\u0010\u0011\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u00105R\u0014\u0010\u0012\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u00105R\u001a\u0010\u0014\u001a\u00020\u00138\u0002X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\n\u0004\b\u0014\u0010>R\u001a\u0010\u0016\u001a\u00020\u00158\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0016\u0010?\u001a\u0004\b@\u0010AR\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u00158\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0017\u0010?\u001a\u0004\bB\u0010AR\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010CR$\u0010#\u001a\u00020\u00022\u0006\u0010D\u001a\u00020\u00028\u0016@RX\u0096\u000e¢\u0006\f\n\u0004\b#\u00105\u001a\u0004\b+\u00107R\u001a\u0010E\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\bE\u00105\u001a\u0004\bF\u00107R\u0017\u0010G\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\bG\u00105\u001a\u0004\bH\u00107R\u0017\u0010I\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\bI\u00105\u001a\u0004\bJ\u00107R\"\u0010K\u001a\u00020\u00078\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\bK\u00109\u001a\u0004\bL\u0010:\"\u0004\bM\u0010NR\u0016\u0010O\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bO\u00105R\u0016\u0010P\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bP\u00105R\u0016\u0010Q\u001a\u00020\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bQ\u00105R\u0014\u0010S\u001a\u00020R8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bS\u0010TR\u0018\u0010W\u001a\u00020\u0002*\u00020\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bU\u0010VR\u0018\u0010Z\u001a\u00020\u0002*\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bX\u0010YR\u0011\u0010\\\u001a\u00020\u00028F¢\u0006\u0006\u001a\u0004\b[\u00107\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006]"}, d2 = {"Landroidx/compose/foundation/lazy/LazyListMeasuredItem;", "Landroidx/compose/foundation/lazy/LazyListItemInfo;", "", "index", "", "Landroidx/compose/ui/layout/Placeable;", "placeables", "", "isVertical", "Landroidx/compose/ui/Alignment$Horizontal;", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "verticalAlignment", "Landroidx/compose/ui/unit/LayoutDirection;", "layoutDirection", "reverseLayout", "beforeContentPadding", "afterContentPadding", "spacing", "Landroidx/compose/ui/unit/IntOffset;", "visualOffset", "", "key", "contentType", "Landroidx/compose/foundation/lazy/LazyListItemAnimator;", "animator", "<init>", "(ILjava/util/List;ZLandroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/ui/unit/LayoutDirection;ZIIIJLjava/lang/Object;Ljava/lang/Object;Landroidx/compose/foundation/lazy/LazyListItemAnimator;Lkotlin/jvm/internal/h;)V", "Lkotlin/Function1;", "mainAxisMap", "copy-4Tuh3kE", "(JLr7/l;)J", "copy", "getParentData", "(I)Ljava/lang/Object;", "offset", "layoutWidth", "layoutHeight", "Lx6/t0;", "position", "(III)V", "getOffset-Bjo55l4", "(I)J", "getOffset", "delta", "updateAnimations", "applyScrollDelta", "(IZ)V", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "scope", "isLookingAhead", "place", "(Landroidx/compose/ui/layout/Placeable$PlacementScope;Z)V", "I", "getIndex", "()I", "Ljava/util/List;", "Z", "()Z", "Landroidx/compose/ui/Alignment$Horizontal;", "Landroidx/compose/ui/Alignment$Vertical;", "Landroidx/compose/ui/unit/LayoutDirection;", "J", "Ljava/lang/Object;", "getKey", "()Ljava/lang/Object;", "getContentType", "Landroidx/compose/foundation/lazy/LazyListItemAnimator;", "<set-?>", ContentDisposition.Parameters.Size, "getSize", "sizeWithSpacings", "getSizeWithSpacings", "crossAxisSize", "getCrossAxisSize", "nonScrollableItem", "getNonScrollableItem", "setNonScrollableItem", "(Z)V", "mainAxisLayoutSize", "minMainAxisOffset", "maxMainAxisOffset", "", "placeableOffsets", "[I", "getMainAxis--gyyYBs", "(J)I", "mainAxis", "getMainAxisSize", "(Landroidx/compose/ui/layout/Placeable;)I", "mainAxisSize", "getPlaceablesCount", "placeablesCount", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LazyListMeasuredItem implements LazyListItemInfo {
    public static final int $stable = 8;
    private final int afterContentPadding;
    private final LazyListItemAnimator animator;
    private final int beforeContentPadding;
    private final Object contentType;
    private final int crossAxisSize;
    private final Alignment.Horizontal horizontalAlignment;
    private final int index;
    private final boolean isVertical;
    private final Object key;
    private final LayoutDirection layoutDirection;
    private int mainAxisLayoutSize;
    private int maxMainAxisOffset;
    private int minMainAxisOffset;
    private boolean nonScrollableItem;
    private int offset;
    private final int[] placeableOffsets;
    private final List<Placeable> placeables;
    private final boolean reverseLayout;
    private final int size;
    private final int sizeWithSpacings;
    private final int spacing;
    private final Alignment.Vertical verticalAlignment;
    private final long visualOffset;

    public /* synthetic */ LazyListMeasuredItem(int i10, List list, boolean z, Alignment.Horizontal horizontal, Alignment.Vertical vertical, LayoutDirection layoutDirection, boolean z5, int i11, int i12, int i13, long j10, Object obj, Object obj2, LazyListItemAnimator lazyListItemAnimator, h hVar) {
        this(i10, list, z, horizontal, vertical, layoutDirection, z5, i11, i12, i13, j10, obj, obj2, lazyListItemAnimator);
    }

    /* JADX INFO: renamed from: copy-4Tuh3kE, reason: not valid java name */
    private final long m637copy4Tuh3kE(long j10, l<? super Integer, Integer> lVar) {
        int iM5802getXimpl = this.isVertical ? IntOffset.m5802getXimpl(j10) : ((Number) lVar.invoke(Integer.valueOf(IntOffset.m5802getXimpl(j10)))).intValue();
        boolean z = this.isVertical;
        int iM5803getYimpl = IntOffset.m5803getYimpl(j10);
        if (z) {
            iM5803getYimpl = ((Number) lVar.invoke(Integer.valueOf(iM5803getYimpl))).intValue();
        }
        return IntOffsetKt.IntOffset(iM5802getXimpl, iM5803getYimpl);
    }

    /* JADX INFO: renamed from: getMainAxis--gyyYBs, reason: not valid java name */
    private final int m638getMainAxisgyyYBs(long j10) {
        return this.isVertical ? IntOffset.m5803getYimpl(j10) : IntOffset.m5802getXimpl(j10);
    }

    private final int getMainAxisSize(Placeable placeable) {
        return this.isVertical ? placeable.getHeight() : placeable.getWidth();
    }

    public final void applyScrollDelta(int delta, boolean updateAnimations) {
        if (this.nonScrollableItem) {
            return;
        }
        this.offset = getOffset() + delta;
        int length = this.placeableOffsets.length;
        for (int i10 = 0; i10 < length; i10++) {
            boolean z = this.isVertical;
            if ((z && i10 % 2 == 1) || (!z && i10 % 2 == 0)) {
                int[] iArr = this.placeableOffsets;
                iArr[i10] = iArr[i10] + delta;
            }
        }
        if (updateAnimations) {
            int placeablesCount = getPlaceablesCount();
            for (int i11 = 0; i11 < placeablesCount; i11++) {
                LazyLayoutAnimation animation = this.animator.getAnimation(getKey(), i11);
                if (animation != null) {
                    long rawOffset = animation.getRawOffset();
                    int iM5802getXimpl = this.isVertical ? IntOffset.m5802getXimpl(rawOffset) : Integer.valueOf(IntOffset.m5802getXimpl(rawOffset) + delta).intValue();
                    boolean z5 = this.isVertical;
                    int iM5803getYimpl = IntOffset.m5803getYimpl(rawOffset);
                    if (z5) {
                        iM5803getYimpl += delta;
                    }
                    animation.m680setRawOffsetgyyYBs(IntOffsetKt.IntOffset(iM5802getXimpl, iM5803getYimpl));
                }
            }
        }
    }

    @Override // androidx.compose.foundation.lazy.LazyListItemInfo
    public Object getContentType() {
        return this.contentType;
    }

    public final int getCrossAxisSize() {
        return this.crossAxisSize;
    }

    @Override // androidx.compose.foundation.lazy.LazyListItemInfo
    public int getIndex() {
        return this.index;
    }

    @Override // androidx.compose.foundation.lazy.LazyListItemInfo
    public Object getKey() {
        return this.key;
    }

    public final boolean getNonScrollableItem() {
        return this.nonScrollableItem;
    }

    @Override // androidx.compose.foundation.lazy.LazyListItemInfo
    public int getOffset() {
        return this.offset;
    }

    /* JADX INFO: renamed from: getOffset-Bjo55l4, reason: not valid java name */
    public final long m639getOffsetBjo55l4(int index) {
        int[] iArr = this.placeableOffsets;
        int i10 = index * 2;
        return IntOffsetKt.IntOffset(iArr[i10], iArr[i10 + 1]);
    }

    public final Object getParentData(int index) {
        return this.placeables.get(index).getParentData();
    }

    public final int getPlaceablesCount() {
        return this.placeables.size();
    }

    @Override // androidx.compose.foundation.lazy.LazyListItemInfo
    public int getSize() {
        return this.size;
    }

    public final int getSizeWithSpacings() {
        return this.sizeWithSpacings;
    }

    /* JADX INFO: renamed from: isVertical, reason: from getter */
    public final boolean getIsVertical() {
        return this.isVertical;
    }

    public final void place(Placeable.PlacementScope scope, boolean isLookingAhead) {
        l<GraphicsLayerScope, t0> defaultLayerBlock;
        Placeable.PlacementScope placementScope;
        int iM5802getXimpl;
        int iM5803getYimpl;
        if (this.mainAxisLayoutSize == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("position() should be called first");
        }
        int placeablesCount = getPlaceablesCount();
        int i10 = 0;
        while (i10 < placeablesCount) {
            Placeable placeable = this.placeables.get(i10);
            int mainAxisSize = this.minMainAxisOffset - getMainAxisSize(placeable);
            int i11 = this.maxMainAxisOffset;
            long jM639getOffsetBjo55l4 = m639getOffsetBjo55l4(i10);
            LazyLayoutAnimation animation = this.animator.getAnimation(getKey(), i10);
            if (animation != null) {
                if (isLookingAhead) {
                    animation.m679setLookaheadOffsetgyyYBs(jM639getOffsetBjo55l4);
                } else {
                    if (!IntOffset.m5801equalsimpl0(animation.getLookaheadOffset(), LazyLayoutAnimation.INSTANCE.m681getNotInitializednOccac())) {
                        jM639getOffsetBjo55l4 = animation.getLookaheadOffset();
                    }
                    long jM677getPlacementDeltanOccac = animation.m677getPlacementDeltanOccac();
                    long jC = a0.c.c(IntOffset.m5803getYimpl(jM639getOffsetBjo55l4), IntOffset.m5802getXimpl(jM677getPlacementDeltanOccac) + IntOffset.m5802getXimpl(jM639getOffsetBjo55l4), jM677getPlacementDeltanOccac);
                    if ((m638getMainAxisgyyYBs(jM639getOffsetBjo55l4) <= mainAxisSize && m638getMainAxisgyyYBs(jC) <= mainAxisSize) || (m638getMainAxisgyyYBs(jM639getOffsetBjo55l4) >= i11 && m638getMainAxisgyyYBs(jC) >= i11)) {
                        animation.cancelPlacementAnimation();
                    }
                    jM639getOffsetBjo55l4 = jC;
                }
                defaultLayerBlock = animation.getLayerBlock();
            } else {
                defaultLayerBlock = LazyLayoutAnimationKt.getDefaultLayerBlock();
            }
            l<GraphicsLayerScope, t0> lVar = defaultLayerBlock;
            if (this.reverseLayout) {
                if (this.isVertical) {
                    iM5802getXimpl = IntOffset.m5802getXimpl(jM639getOffsetBjo55l4);
                } else {
                    iM5802getXimpl = (this.mainAxisLayoutSize - IntOffset.m5802getXimpl(jM639getOffsetBjo55l4)) - getMainAxisSize(placeable);
                }
                if (this.isVertical) {
                    iM5803getYimpl = (this.mainAxisLayoutSize - IntOffset.m5803getYimpl(jM639getOffsetBjo55l4)) - getMainAxisSize(placeable);
                } else {
                    iM5803getYimpl = IntOffset.m5803getYimpl(jM639getOffsetBjo55l4);
                }
                jM639getOffsetBjo55l4 = IntOffsetKt.IntOffset(iM5802getXimpl, iM5803getYimpl);
            }
            long j10 = this.visualOffset;
            long jC2 = a0.c.c(IntOffset.m5803getYimpl(jM639getOffsetBjo55l4), IntOffset.m5802getXimpl(j10) + IntOffset.m5802getXimpl(jM639getOffsetBjo55l4), j10);
            if (this.isVertical) {
                placementScope = scope;
                Placeable.PlacementScope.m4672placeWithLayeraW9wM$default(placementScope, placeable, jC2, 0.0f, lVar, 2, null);
            } else {
                placementScope = scope;
                Placeable.PlacementScope.m4671placeRelativeWithLayeraW9wM$default(placementScope, placeable, jC2, 0.0f, lVar, 2, null);
            }
            i10++;
            scope = placementScope;
        }
    }

    public final void position(int offset, int layoutWidth, int layoutHeight) {
        int width;
        this.offset = offset;
        this.mainAxisLayoutSize = this.isVertical ? layoutHeight : layoutWidth;
        List<Placeable> list = this.placeables;
        int size = list.size();
        for (int i10 = 0; i10 < size; i10++) {
            Placeable placeable = list.get(i10);
            int i11 = i10 * 2;
            if (this.isVertical) {
                int[] iArr = this.placeableOffsets;
                Alignment.Horizontal horizontal = this.horizontalAlignment;
                if (horizontal == null) {
                    throw new IllegalArgumentException("null horizontalAlignment when isVertical == true");
                }
                iArr[i11] = horizontal.align(placeable.getWidth(), layoutWidth, this.layoutDirection);
                this.placeableOffsets[i11 + 1] = offset;
                width = placeable.getHeight();
            } else {
                int[] iArr2 = this.placeableOffsets;
                iArr2[i11] = offset;
                int i12 = i11 + 1;
                Alignment.Vertical vertical = this.verticalAlignment;
                if (vertical == null) {
                    throw new IllegalArgumentException("null verticalAlignment when isVertical == false");
                }
                iArr2[i12] = vertical.align(placeable.getHeight(), layoutHeight);
                width = placeable.getWidth();
            }
            offset = width + offset;
        }
        this.minMainAxisOffset = -this.beforeContentPadding;
        this.maxMainAxisOffset = this.mainAxisLayoutSize + this.afterContentPadding;
    }

    public final void setNonScrollableItem(boolean z) {
        this.nonScrollableItem = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private LazyListMeasuredItem(int i10, List<? extends Placeable> list, boolean z, Alignment.Horizontal horizontal, Alignment.Vertical vertical, LayoutDirection layoutDirection, boolean z5, int i11, int i12, int i13, long j10, Object obj, Object obj2, LazyListItemAnimator lazyListItemAnimator) {
        this.index = i10;
        this.placeables = list;
        this.isVertical = z;
        this.horizontalAlignment = horizontal;
        this.verticalAlignment = vertical;
        this.layoutDirection = layoutDirection;
        this.reverseLayout = z5;
        this.beforeContentPadding = i11;
        this.afterContentPadding = i12;
        this.spacing = i13;
        this.visualOffset = j10;
        this.key = obj;
        this.contentType = obj2;
        this.animator = lazyListItemAnimator;
        this.mainAxisLayoutSize = Integer.MIN_VALUE;
        int size = list.size();
        int height = 0;
        int iMax = 0;
        for (int i14 = 0; i14 < size; i14++) {
            Placeable placeable = (Placeable) list.get(i14);
            height += this.isVertical ? placeable.getHeight() : placeable.getWidth();
            iMax = Math.max(iMax, !this.isVertical ? placeable.getHeight() : placeable.getWidth());
        }
        this.size = height;
        int size2 = getSize() + this.spacing;
        this.sizeWithSpacings = size2 >= 0 ? size2 : 0;
        this.crossAxisSize = iMax;
        this.placeableOffsets = new int[this.placeables.size() * 2];
    }
}

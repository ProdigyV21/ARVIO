package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.lazy.layout.LazyLayoutAnimation;
import androidx.compose.foundation.lazy.layout.LazyLayoutKeyIndexMap;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import com.google.common.util.concurrent.r0;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.r;
import kotlin.collections.w;
import kotlin.collections.x;
import t.d0;
import t.i0;
import t.k0;
import t.z;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\r\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\r\u0010\u000eJS\u0010\u001b\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00062\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u0019¢\u0006\u0004\b\u001b\u0010\u001cJ\r\u0010\u001d\u001a\u00020\n¢\u0006\u0004\b\u001d\u0010\u0003J\u001f\u0010!\u001a\u0004\u0018\u00010 2\u0006\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001f\u001a\u00020\u0006¢\u0006\u0004\b!\u0010\"R \u0010$\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\b0#8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%R\u0016\u0010'\u001a\u00020&8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b'\u0010(R\u0016\u0010)\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b)\u0010*R\u001a\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00010+8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010-R\u001a\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00040\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b.\u0010/R\u001a\u00100\u001a\b\u0012\u0004\u0012\u00020\u00040\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u0010/R\u001a\u00101\u001a\b\u0012\u0004\u0012\u00020\u00040\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u0010/R\u001a\u00102\u001a\b\u0012\u0004\u0012\u00020\u00040\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u0010/R\u0018\u00105\u001a\u00020\u0016*\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b3\u00104¨\u00066"}, d2 = {"Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemPlacementAnimator;", "", "<init>", "()V", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasuredItem;", "item", "", "mainAxisOffset", "Landroidx/compose/foundation/lazy/staggeredgrid/ItemInfo;", "itemInfo", "Lx6/t0;", "initializeAnimation", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasuredItem;ILandroidx/compose/foundation/lazy/staggeredgrid/ItemInfo;)V", "startAnimationsIfNeeded", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasuredItem;)V", "consumedScroll", "layoutWidth", "layoutHeight", "", "positionedItems", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureProvider;", "itemProvider", "", "isVertical", "laneCount", "Lka/k0;", "coroutineScope", "onMeasured", "(IIILjava/util/List;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureProvider;ZILka/k0;)V", "reset", "key", "placeableIndex", "Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimation;", "getAnimation", "(Ljava/lang/Object;I)Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimation;", "Lt/z;", "keyToItemInfoMap", "Lt/z;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutKeyIndexMap;", "keyIndexMap", "Landroidx/compose/foundation/lazy/layout/LazyLayoutKeyIndexMap;", "firstVisibleIndex", "I", "Lt/d0;", "movingAwayKeys", "Lt/d0;", "movingInFromStartBound", "Ljava/util/List;", "movingInFromEndBound", "movingAwayToStartBound", "movingAwayToEndBound", "getHasAnimations", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasuredItem;)Z", "hasAnimations", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LazyStaggeredGridItemPlacementAnimator {
    public static final int $stable = 8;
    private int firstVisibleIndex;
    private LazyLayoutKeyIndexMap keyIndexMap;
    private final z keyToItemInfoMap;
    private final d0 movingAwayKeys;
    private final List<LazyStaggeredGridMeasuredItem> movingAwayToEndBound;
    private final List<LazyStaggeredGridMeasuredItem> movingAwayToStartBound;
    private final List<LazyStaggeredGridMeasuredItem> movingInFromEndBound;
    private final List<LazyStaggeredGridMeasuredItem> movingInFromStartBound;

    public LazyStaggeredGridItemPlacementAnimator() {
        long[] jArr = i0.f21833a;
        this.keyToItemInfoMap = new z();
        this.keyIndexMap = LazyLayoutKeyIndexMap.INSTANCE;
        int i10 = k0.f21841a;
        this.movingAwayKeys = new d0();
        this.movingInFromStartBound = new ArrayList();
        this.movingInFromEndBound = new ArrayList();
        this.movingAwayToStartBound = new ArrayList();
        this.movingAwayToEndBound = new ArrayList();
    }

    private final boolean getHasAnimations(LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem) {
        int placeablesCount = lazyStaggeredGridMeasuredItem.getPlaceablesCount();
        for (int i10 = 0; i10 < placeablesCount; i10++) {
            if (LazyStaggeredGridItemPlacementAnimatorKt.getSpecs(lazyStaggeredGridMeasuredItem.getParentData(i10)) != null) {
                return true;
            }
        }
        return false;
    }

    private final void initializeAnimation(LazyStaggeredGridMeasuredItem item, int mainAxisOffset, ItemInfo itemInfo) {
        long jMo717getOffsetnOccac = item.getOffset();
        long jM5798copyiSbpLlY$default = item.getIsVertical() ? IntOffset.m5798copyiSbpLlY$default(jMo717getOffsetnOccac, 0, mainAxisOffset, 1, null) : IntOffset.m5798copyiSbpLlY$default(jMo717getOffsetnOccac, mainAxisOffset, 0, 2, null);
        for (LazyLayoutAnimation lazyLayoutAnimation : itemInfo.getAnimations()) {
            if (lazyLayoutAnimation != null) {
                long jMo717getOffsetnOccac2 = item.getOffset();
                long jIntOffset = IntOffsetKt.IntOffset(IntOffset.m5802getXimpl(jMo717getOffsetnOccac2) - IntOffset.m5802getXimpl(jMo717getOffsetnOccac), IntOffset.m5803getYimpl(jMo717getOffsetnOccac2) - IntOffset.m5803getYimpl(jMo717getOffsetnOccac));
                lazyLayoutAnimation.m680setRawOffsetgyyYBs(IntOffsetKt.IntOffset(IntOffset.m5802getXimpl(jIntOffset) + IntOffset.m5802getXimpl(jM5798copyiSbpLlY$default), IntOffset.m5803getYimpl(jIntOffset) + IntOffset.m5803getYimpl(jM5798copyiSbpLlY$default)));
            }
        }
    }

    public static /* synthetic */ void initializeAnimation$default(LazyStaggeredGridItemPlacementAnimator lazyStaggeredGridItemPlacementAnimator, LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem, int i10, ItemInfo itemInfo, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            itemInfo = (ItemInfo) lazyStaggeredGridItemPlacementAnimator.keyToItemInfoMap.e(lazyStaggeredGridMeasuredItem.getKey());
        }
        lazyStaggeredGridItemPlacementAnimator.initializeAnimation(lazyStaggeredGridMeasuredItem, i10, itemInfo);
    }

    private final void startAnimationsIfNeeded(LazyStaggeredGridMeasuredItem item) {
        for (LazyLayoutAnimation lazyLayoutAnimation : ((ItemInfo) this.keyToItemInfoMap.e(item.getKey())).getAnimations()) {
            if (lazyLayoutAnimation != null) {
                long jMo717getOffsetnOccac = item.getOffset();
                long rawOffset = lazyLayoutAnimation.getRawOffset();
                if (!IntOffset.m5801equalsimpl0(rawOffset, LazyLayoutAnimation.INSTANCE.m681getNotInitializednOccac()) && !IntOffset.m5801equalsimpl0(rawOffset, jMo717getOffsetnOccac)) {
                    lazyLayoutAnimation.m675animatePlacementDeltagyyYBs(IntOffsetKt.IntOffset(IntOffset.m5802getXimpl(jMo717getOffsetnOccac) - IntOffset.m5802getXimpl(rawOffset), IntOffset.m5803getYimpl(jMo717getOffsetnOccac) - IntOffset.m5803getYimpl(rawOffset)));
                }
                lazyLayoutAnimation.m680setRawOffsetgyyYBs(jMo717getOffsetnOccac);
            }
        }
    }

    public final LazyLayoutAnimation getAnimation(Object key, int placeableIndex) {
        ItemInfo itemInfo;
        LazyLayoutAnimation[] animations;
        if (this.keyToItemInfoMap.g() || (itemInfo = (ItemInfo) this.keyToItemInfoMap.e(key)) == null || (animations = itemInfo.getAnimations()) == null) {
            return null;
        }
        return animations[placeableIndex];
    }

    public final void onMeasured(int consumedScroll, int layoutWidth, int layoutHeight, List<LazyStaggeredGridMeasuredItem> positionedItems, LazyStaggeredGridMeasureProvider itemProvider, boolean isVertical, int laneCount, ka.k0 coroutineScope) {
        int i10;
        LazyLayoutKeyIndexMap lazyLayoutKeyIndexMap;
        long j10;
        int i11;
        int i12;
        long[] jArr;
        Object[] objArr;
        long[] jArr2;
        int i13;
        long j11;
        Object[] objArr2;
        int i14;
        long j12;
        int i15;
        LazyLayoutKeyIndexMap lazyLayoutKeyIndexMap2;
        int i16;
        int i17;
        LazyLayoutAnimation[] lazyLayoutAnimationArr;
        long[] jArr3;
        long[] jArr4;
        int i18;
        int size = positionedItems.size();
        int i19 = 0;
        int i20 = 0;
        while (true) {
            if (i20 >= size) {
                if (this.keyToItemInfoMap.g()) {
                    reset();
                    return;
                }
            } else if (getHasAnimations(positionedItems.get(i20))) {
                break;
            } else {
                i20++;
            }
        }
        int i21 = this.firstVisibleIndex;
        LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem = (LazyStaggeredGridMeasuredItem) x.o0(positionedItems);
        this.firstVisibleIndex = lazyStaggeredGridMeasuredItem != null ? lazyStaggeredGridMeasuredItem.getIndex() : 0;
        LazyLayoutKeyIndexMap lazyLayoutKeyIndexMap3 = this.keyIndexMap;
        this.keyIndexMap = itemProvider.getKeyIndexMap();
        int i22 = isVertical ? layoutHeight : layoutWidth;
        long jIntOffset = isVertical ? IntOffsetKt.IntOffset(0, consumedScroll) : IntOffsetKt.IntOffset(consumedScroll, 0);
        z zVar = this.keyToItemInfoMap;
        Object[] objArr3 = zVar.f21882b;
        long[] jArr5 = zVar.f21881a;
        int length = jArr5.length - 2;
        int i23 = 8;
        if (length >= 0) {
            int i24 = 0;
            j10 = 255;
            while (true) {
                long j13 = jArr5[i24];
                i10 = i19;
                lazyLayoutKeyIndexMap = lazyLayoutKeyIndexMap3;
                if ((((~j13) << 7) & j13 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i25 = 8 - ((~(i24 - length)) >>> 31);
                    int i26 = i10;
                    while (i26 < i25) {
                        if ((j13 & 255) < 128) {
                            i18 = i23;
                            jArr4 = jArr5;
                            this.movingAwayKeys.d(objArr3[(i24 << 3) + i26]);
                        } else {
                            jArr4 = jArr5;
                            i18 = i23;
                        }
                        j13 >>= i18;
                        i26++;
                        i23 = i18;
                        jArr5 = jArr4;
                    }
                    jArr3 = jArr5;
                    if (i25 != i23) {
                        break;
                    }
                } else {
                    jArr3 = jArr5;
                }
                if (i24 == length) {
                    break;
                }
                i24++;
                i19 = i10;
                lazyLayoutKeyIndexMap3 = lazyLayoutKeyIndexMap;
                jArr5 = jArr3;
                i23 = 8;
            }
        } else {
            i10 = 0;
            lazyLayoutKeyIndexMap = lazyLayoutKeyIndexMap3;
            j10 = 255;
        }
        int size2 = positionedItems.size();
        int i27 = i10;
        while (true) {
            i11 = -1;
            if (i27 >= size2) {
                break;
            }
            LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem2 = positionedItems.get(i27);
            this.movingAwayKeys.j(lazyStaggeredGridMeasuredItem2.getKey());
            if (getHasAnimations(lazyStaggeredGridMeasuredItem2)) {
                ItemInfo itemInfo = (ItemInfo) this.keyToItemInfoMap.e(lazyStaggeredGridMeasuredItem2.getKey());
                if (itemInfo == null) {
                    ItemInfo itemInfo2 = new ItemInfo(lazyStaggeredGridMeasuredItem2.getLane(), lazyStaggeredGridMeasuredItem2.getSpan(), lazyStaggeredGridMeasuredItem2.getCrossAxisOffset());
                    itemInfo2.updateAnimation(lazyStaggeredGridMeasuredItem2, coroutineScope);
                    this.keyToItemInfoMap.k(lazyStaggeredGridMeasuredItem2.getKey(), itemInfo2);
                    lazyLayoutKeyIndexMap2 = lazyLayoutKeyIndexMap;
                    int index = lazyLayoutKeyIndexMap2.getIndex(lazyStaggeredGridMeasuredItem2.getKey());
                    if (index == -1 || lazyStaggeredGridMeasuredItem2.getIndex() == index) {
                        long jMo717getOffsetnOccac = lazyStaggeredGridMeasuredItem2.getOffset();
                        initializeAnimation(lazyStaggeredGridMeasuredItem2, lazyStaggeredGridMeasuredItem2.getIsVertical() ? IntOffset.m5803getYimpl(jMo717getOffsetnOccac) : IntOffset.m5802getXimpl(jMo717getOffsetnOccac), itemInfo2);
                    } else if (index < i21) {
                        this.movingInFromStartBound.add(lazyStaggeredGridMeasuredItem2);
                    } else {
                        this.movingInFromEndBound.add(lazyStaggeredGridMeasuredItem2);
                    }
                    i14 = size2;
                    i16 = i21;
                    j12 = jIntOffset;
                    i15 = i27;
                } else {
                    lazyLayoutKeyIndexMap2 = lazyLayoutKeyIndexMap;
                    LazyLayoutAnimation[] animations = itemInfo.getAnimations();
                    int length2 = animations.length;
                    int i28 = i10;
                    while (i28 < length2) {
                        int i29 = size2;
                        LazyLayoutAnimation lazyLayoutAnimation = animations[i28];
                        int i30 = i21;
                        long j14 = jIntOffset;
                        if (lazyLayoutAnimation != null) {
                            i17 = i27;
                            lazyLayoutAnimationArr = animations;
                            if (!IntOffset.m5801equalsimpl0(lazyLayoutAnimation.getRawOffset(), LazyLayoutAnimation.INSTANCE.m681getNotInitializednOccac())) {
                                long rawOffset = lazyLayoutAnimation.getRawOffset();
                                lazyLayoutAnimation.m680setRawOffsetgyyYBs(IntOffsetKt.IntOffset(IntOffset.m5802getXimpl(j14) + IntOffset.m5802getXimpl(rawOffset), IntOffset.m5803getYimpl(j14) + IntOffset.m5803getYimpl(rawOffset)));
                            }
                        } else {
                            i17 = i27;
                            lazyLayoutAnimationArr = animations;
                        }
                        i28++;
                        size2 = i29;
                        i21 = i30;
                        jIntOffset = j14;
                        i27 = i17;
                        animations = lazyLayoutAnimationArr;
                    }
                    i14 = size2;
                    i16 = i21;
                    j12 = jIntOffset;
                    i15 = i27;
                    itemInfo.setLane(lazyStaggeredGridMeasuredItem2.getLane());
                    itemInfo.setSpan(lazyStaggeredGridMeasuredItem2.getSpan());
                    itemInfo.setCrossAxisOffset(lazyStaggeredGridMeasuredItem2.getCrossAxisOffset());
                    startAnimationsIfNeeded(lazyStaggeredGridMeasuredItem2);
                }
            } else {
                i14 = size2;
                j12 = jIntOffset;
                i15 = i27;
                lazyLayoutKeyIndexMap2 = lazyLayoutKeyIndexMap;
                i16 = i21;
                this.keyToItemInfoMap.h(lazyStaggeredGridMeasuredItem2.getKey());
            }
            i27 = i15 + 1;
            size2 = i14;
            i21 = i16;
            jIntOffset = j12;
            lazyLayoutKeyIndexMap = lazyLayoutKeyIndexMap2;
        }
        final LazyLayoutKeyIndexMap lazyLayoutKeyIndexMap4 = lazyLayoutKeyIndexMap;
        int[] iArr = new int[laneCount];
        for (int i31 = i10; i31 < laneCount; i31++) {
            iArr[i31] = i10;
        }
        if (!this.movingInFromStartBound.isEmpty()) {
            List<LazyStaggeredGridMeasuredItem> list = this.movingInFromStartBound;
            if (list.size() > 1) {
                w.X(new Comparator() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemPlacementAnimator$onMeasured$$inlined$sortByDescending$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t2, T t10) {
                        return r0.e(Integer.valueOf(lazyLayoutKeyIndexMap4.getIndex(((LazyStaggeredGridMeasuredItem) t10).getKey())), Integer.valueOf(lazyLayoutKeyIndexMap4.getIndex(((LazyStaggeredGridMeasuredItem) t2).getKey())));
                    }
                }, list);
            }
            List<LazyStaggeredGridMeasuredItem> list2 = this.movingInFromStartBound;
            int size3 = list2.size();
            for (int i32 = i10; i32 < size3; i32++) {
                LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem3 = list2.get(i32);
                int lane = lazyStaggeredGridMeasuredItem3.getLane();
                iArr[lane] = lazyStaggeredGridMeasuredItem3.getMainAxisSize() + iArr[lane];
                initializeAnimation$default(this, lazyStaggeredGridMeasuredItem3, 0 - iArr[lazyStaggeredGridMeasuredItem3.getLane()], null, 4, null);
                startAnimationsIfNeeded(lazyStaggeredGridMeasuredItem3);
            }
            int i33 = i10;
            r.U(iArr, i33, i33, 6);
        }
        if (!this.movingInFromEndBound.isEmpty()) {
            List<LazyStaggeredGridMeasuredItem> list3 = this.movingInFromEndBound;
            if (list3.size() > 1) {
                w.X(new Comparator() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemPlacementAnimator$onMeasured$$inlined$sortBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t2, T t10) {
                        return r0.e(Integer.valueOf(lazyLayoutKeyIndexMap4.getIndex(((LazyStaggeredGridMeasuredItem) t2).getKey())), Integer.valueOf(lazyLayoutKeyIndexMap4.getIndex(((LazyStaggeredGridMeasuredItem) t10).getKey())));
                    }
                }, list3);
            }
            List<LazyStaggeredGridMeasuredItem> list4 = this.movingInFromEndBound;
            int size4 = list4.size();
            for (int i34 = 0; i34 < size4; i34++) {
                LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem4 = list4.get(i34);
                int i35 = iArr[lazyStaggeredGridMeasuredItem4.getLane()] + i22;
                int lane2 = lazyStaggeredGridMeasuredItem4.getLane();
                iArr[lane2] = lazyStaggeredGridMeasuredItem4.getMainAxisSize() + iArr[lane2];
                initializeAnimation$default(this, lazyStaggeredGridMeasuredItem4, i35, null, 4, null);
                startAnimationsIfNeeded(lazyStaggeredGridMeasuredItem4);
            }
            r.U(iArr, 0, 0, 6);
        }
        d0 d0Var = this.movingAwayKeys;
        Object[] objArr4 = d0Var.f21836b;
        long[] jArr6 = d0Var.f21835a;
        int length3 = jArr6.length - 2;
        if (length3 >= 0) {
            int i36 = 0;
            while (true) {
                long j15 = jArr6[i36];
                if ((((~j15) << 7) & j15 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i37 = 8 - ((~(i36 - length3)) >>> 31);
                    int i38 = 0;
                    while (i38 < i37) {
                        if ((j15 & j10) < 128) {
                            Object obj = objArr4[(i36 << 3) + i38];
                            ItemInfo itemInfo3 = (ItemInfo) this.keyToItemInfoMap.e(obj);
                            jArr2 = jArr6;
                            int index2 = this.keyIndexMap.getIndex(obj);
                            if (index2 == i11) {
                                this.keyToItemInfoMap.h(obj);
                            } else {
                                objArr2 = objArr4;
                                j11 = j15;
                                LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemM732getAndMeasurejy6DScQ = itemProvider.m732getAndMeasurejy6DScQ(index2, SpanRange.m737constructorimpl(itemInfo3.getLane(), itemInfo3.getSpan()));
                                lazyStaggeredGridMeasuredItemM732getAndMeasurejy6DScQ.setNonScrollableItem(true);
                                LazyLayoutAnimation[] animations2 = itemInfo3.getAnimations();
                                int length4 = animations2.length;
                                int i39 = 0;
                                while (true) {
                                    if (i39 >= length4) {
                                        i13 = i38;
                                        if (index2 == lazyLayoutKeyIndexMap4.getIndex(obj)) {
                                            this.keyToItemInfoMap.h(obj);
                                        }
                                    } else {
                                        LazyLayoutAnimation lazyLayoutAnimation2 = animations2[i39];
                                        if (lazyLayoutAnimation2 != null) {
                                            boolean zIsPlacementAnimationInProgress = lazyLayoutAnimation2.isPlacementAnimationInProgress();
                                            i13 = i38;
                                            if (zIsPlacementAnimationInProgress) {
                                                break;
                                            }
                                        } else {
                                            i13 = i38;
                                        }
                                        i39++;
                                        i38 = i13;
                                    }
                                }
                                if (index2 < this.firstVisibleIndex) {
                                    this.movingAwayToStartBound.add(lazyStaggeredGridMeasuredItemM732getAndMeasurejy6DScQ);
                                } else {
                                    this.movingAwayToEndBound.add(lazyStaggeredGridMeasuredItemM732getAndMeasurejy6DScQ);
                                }
                                j15 = j11 >> 8;
                                i38 = i13 + 1;
                                jArr6 = jArr2;
                                objArr4 = objArr2;
                                i11 = -1;
                            }
                        } else {
                            jArr2 = jArr6;
                        }
                        objArr2 = objArr4;
                        i13 = i38;
                        j11 = j15;
                        j15 = j11 >> 8;
                        i38 = i13 + 1;
                        jArr6 = jArr2;
                        objArr4 = objArr2;
                        i11 = -1;
                    }
                    jArr = jArr6;
                    objArr = objArr4;
                    if (i37 != 8) {
                        break;
                    }
                } else {
                    jArr = jArr6;
                    objArr = objArr4;
                }
                if (i36 == length3) {
                    break;
                }
                i36++;
                jArr6 = jArr;
                objArr4 = objArr;
                i11 = -1;
            }
        }
        if (this.movingAwayToStartBound.isEmpty()) {
            i12 = 0;
        } else {
            List<LazyStaggeredGridMeasuredItem> list5 = this.movingAwayToStartBound;
            if (list5.size() > 1) {
                w.X(new Comparator() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemPlacementAnimator$onMeasured$$inlined$sortByDescending$2
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t2, T t10) {
                        return r0.e(Integer.valueOf(this.this$0.keyIndexMap.getIndex(((LazyStaggeredGridMeasuredItem) t10).getKey())), Integer.valueOf(this.this$0.keyIndexMap.getIndex(((LazyStaggeredGridMeasuredItem) t2).getKey())));
                    }
                }, list5);
            }
            List<LazyStaggeredGridMeasuredItem> list6 = this.movingAwayToStartBound;
            int size5 = list6.size();
            for (int i40 = 0; i40 < size5; i40++) {
                LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem5 = list6.get(i40);
                int lane3 = lazyStaggeredGridMeasuredItem5.getLane();
                iArr[lane3] = lazyStaggeredGridMeasuredItem5.getMainAxisSize() + iArr[lane3];
                lazyStaggeredGridMeasuredItem5.position(0 - iArr[lazyStaggeredGridMeasuredItem5.getLane()], ((ItemInfo) this.keyToItemInfoMap.e(lazyStaggeredGridMeasuredItem5.getKey())).getCrossAxisOffset(), i22);
                positionedItems.add(lazyStaggeredGridMeasuredItem5);
                startAnimationsIfNeeded(lazyStaggeredGridMeasuredItem5);
            }
            i12 = 0;
            r.U(iArr, 0, 0, 6);
        }
        if (!this.movingAwayToEndBound.isEmpty()) {
            List<LazyStaggeredGridMeasuredItem> list7 = this.movingAwayToEndBound;
            if (list7.size() > 1) {
                w.X(new Comparator() { // from class: androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemPlacementAnimator$onMeasured$$inlined$sortBy$2
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t2, T t10) {
                        return r0.e(Integer.valueOf(this.this$0.keyIndexMap.getIndex(((LazyStaggeredGridMeasuredItem) t2).getKey())), Integer.valueOf(this.this$0.keyIndexMap.getIndex(((LazyStaggeredGridMeasuredItem) t10).getKey())));
                    }
                }, list7);
            }
            List<LazyStaggeredGridMeasuredItem> list8 = this.movingAwayToEndBound;
            int size6 = list8.size();
            while (i12 < size6) {
                LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem6 = list8.get(i12);
                int i41 = iArr[lazyStaggeredGridMeasuredItem6.getLane()] + i22;
                int lane4 = lazyStaggeredGridMeasuredItem6.getLane();
                iArr[lane4] = lazyStaggeredGridMeasuredItem6.getMainAxisSize() + iArr[lane4];
                lazyStaggeredGridMeasuredItem6.position(i41, ((ItemInfo) this.keyToItemInfoMap.e(lazyStaggeredGridMeasuredItem6.getKey())).getCrossAxisOffset(), i22);
                positionedItems.add(lazyStaggeredGridMeasuredItem6);
                startAnimationsIfNeeded(lazyStaggeredGridMeasuredItem6);
                i12++;
            }
        }
        this.movingInFromStartBound.clear();
        this.movingInFromEndBound.clear();
        this.movingAwayToStartBound.clear();
        this.movingAwayToEndBound.clear();
        this.movingAwayKeys.e();
    }

    public final void reset() {
        this.keyToItemInfoMap.a();
        this.keyIndexMap = LazyLayoutKeyIndexMap.INSTANCE;
        this.firstVisibleIndex = -1;
    }
}

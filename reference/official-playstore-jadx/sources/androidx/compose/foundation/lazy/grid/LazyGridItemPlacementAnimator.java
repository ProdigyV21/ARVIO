package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.lazy.layout.LazyLayoutAnimation;
import androidx.compose.foundation.lazy.layout.LazyLayoutKeyIndexMap;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import com.google.common.util.concurrent.r0;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.w;
import kotlin.collections.x;
import t.d0;
import t.i0;
import t.k0;
import t.z;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\r\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\r\u0010\u000eJS\u0010\u001c\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00062\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001a¢\u0006\u0004\b\u001c\u0010\u001dJ\r\u0010\u001e\u001a\u00020\n¢\u0006\u0004\b\u001e\u0010\u0003J\u001f\u0010\"\u001a\u0004\u0018\u00010!2\u0006\u0010\u001f\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\u0006¢\u0006\u0004\b\"\u0010#R \u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\b0$8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010&R\u0016\u0010(\u001a\u00020'8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b(\u0010)R\u0016\u0010*\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b*\u0010+R\u001a\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00010,8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010.R\u001a\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00040\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u00100R\u001a\u00101\u001a\b\u0012\u0004\u0012\u00020\u00040\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b1\u00100R\u001a\u00102\u001a\b\u0012\u0004\u0012\u00020\u00040\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00100R\u001a\u00103\u001a\b\u0012\u0004\u0012\u00020\u00040\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u00100R\u0018\u00106\u001a\u00020\u0018*\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b4\u00105¨\u00067"}, d2 = {"Landroidx/compose/foundation/lazy/grid/LazyGridItemPlacementAnimator;", "", "<init>", "()V", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItem;", "item", "", "mainAxisOffset", "Landroidx/compose/foundation/lazy/grid/ItemInfo;", "itemInfo", "Lx6/t0;", "initializeAnimation", "(Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItem;ILandroidx/compose/foundation/lazy/grid/ItemInfo;)V", "startAnimationsIfNeeded", "(Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItem;)V", "consumedScroll", "layoutWidth", "layoutHeight", "", "positionedItems", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItemProvider;", "itemProvider", "Landroidx/compose/foundation/lazy/grid/LazyGridSpanLayoutProvider;", "spanLayoutProvider", "", "isVertical", "Lka/k0;", "coroutineScope", "onMeasured", "(IIILjava/util/List;Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItemProvider;Landroidx/compose/foundation/lazy/grid/LazyGridSpanLayoutProvider;ZLka/k0;)V", "reset", "key", "placeableIndex", "Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimation;", "getAnimation", "(Ljava/lang/Object;I)Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimation;", "Lt/z;", "keyToItemInfoMap", "Lt/z;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutKeyIndexMap;", "keyIndexMap", "Landroidx/compose/foundation/lazy/layout/LazyLayoutKeyIndexMap;", "firstVisibleIndex", "I", "Lt/d0;", "movingAwayKeys", "Lt/d0;", "movingInFromStartBound", "Ljava/util/List;", "movingInFromEndBound", "movingAwayToStartBound", "movingAwayToEndBound", "getHasAnimations", "(Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItem;)Z", "hasAnimations", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LazyGridItemPlacementAnimator {
    public static final int $stable = 8;
    private int firstVisibleIndex;
    private LazyLayoutKeyIndexMap keyIndexMap;
    private final z keyToItemInfoMap;
    private final d0 movingAwayKeys;
    private final List<LazyGridMeasuredItem> movingAwayToEndBound;
    private final List<LazyGridMeasuredItem> movingAwayToStartBound;
    private final List<LazyGridMeasuredItem> movingInFromEndBound;
    private final List<LazyGridMeasuredItem> movingInFromStartBound;

    public LazyGridItemPlacementAnimator() {
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

    private final boolean getHasAnimations(LazyGridMeasuredItem lazyGridMeasuredItem) {
        int placeablesCount = lazyGridMeasuredItem.getPlaceablesCount();
        for (int i10 = 0; i10 < placeablesCount; i10++) {
            if (LazyGridItemPlacementAnimatorKt.getSpecs(lazyGridMeasuredItem.getParentData(i10)) != null) {
                return true;
            }
        }
        return false;
    }

    private final void initializeAnimation(LazyGridMeasuredItem item, int mainAxisOffset, ItemInfo itemInfo) {
        long offset = item.getOffset();
        long jM5798copyiSbpLlY$default = item.getIsVertical() ? IntOffset.m5798copyiSbpLlY$default(offset, 0, mainAxisOffset, 1, null) : IntOffset.m5798copyiSbpLlY$default(offset, mainAxisOffset, 0, 2, null);
        for (LazyLayoutAnimation lazyLayoutAnimation : itemInfo.getAnimations()) {
            if (lazyLayoutAnimation != null) {
                long offset2 = item.getOffset();
                long jIntOffset = IntOffsetKt.IntOffset(IntOffset.m5802getXimpl(offset2) - IntOffset.m5802getXimpl(offset), IntOffset.m5803getYimpl(offset2) - IntOffset.m5803getYimpl(offset));
                lazyLayoutAnimation.m680setRawOffsetgyyYBs(IntOffsetKt.IntOffset(IntOffset.m5802getXimpl(jIntOffset) + IntOffset.m5802getXimpl(jM5798copyiSbpLlY$default), IntOffset.m5803getYimpl(jIntOffset) + IntOffset.m5803getYimpl(jM5798copyiSbpLlY$default)));
            }
        }
    }

    public static /* synthetic */ void initializeAnimation$default(LazyGridItemPlacementAnimator lazyGridItemPlacementAnimator, LazyGridMeasuredItem lazyGridMeasuredItem, int i10, ItemInfo itemInfo, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            itemInfo = (ItemInfo) lazyGridItemPlacementAnimator.keyToItemInfoMap.e(lazyGridMeasuredItem.getKey());
        }
        lazyGridItemPlacementAnimator.initializeAnimation(lazyGridMeasuredItem, i10, itemInfo);
    }

    private final void startAnimationsIfNeeded(LazyGridMeasuredItem item) {
        for (LazyLayoutAnimation lazyLayoutAnimation : ((ItemInfo) this.keyToItemInfoMap.e(item.getKey())).getAnimations()) {
            if (lazyLayoutAnimation != null) {
                long offset = item.getOffset();
                long rawOffset = lazyLayoutAnimation.getRawOffset();
                if (!IntOffset.m5801equalsimpl0(rawOffset, LazyLayoutAnimation.INSTANCE.m681getNotInitializednOccac()) && !IntOffset.m5801equalsimpl0(rawOffset, offset)) {
                    lazyLayoutAnimation.m675animatePlacementDeltagyyYBs(IntOffsetKt.IntOffset(IntOffset.m5802getXimpl(offset) - IntOffset.m5802getXimpl(rawOffset), IntOffset.m5803getYimpl(offset) - IntOffset.m5803getYimpl(rawOffset)));
                }
                lazyLayoutAnimation.m680setRawOffsetgyyYBs(offset);
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

    public final void onMeasured(int consumedScroll, int layoutWidth, int layoutHeight, List<LazyGridMeasuredItem> positionedItems, LazyGridMeasuredItemProvider itemProvider, LazyGridSpanLayoutProvider spanLayoutProvider, boolean isVertical, ka.k0 coroutineScope) {
        long j10;
        final LazyLayoutKeyIndexMap lazyLayoutKeyIndexMap;
        long j11;
        long j12;
        int i10;
        long[] jArr;
        Object[] objArr;
        long[] jArr2;
        Object[] objArr2;
        int i11;
        long jM5655fixedHeightOenEA2s;
        int i12;
        int mainAxisSize;
        int i13;
        int mainAxisSize2;
        int i14;
        int i15;
        int i16;
        LazyLayoutAnimation[] lazyLayoutAnimationArr;
        long[] jArr3;
        long[] jArr4;
        long j13;
        int size = positionedItems.size();
        int i17 = 0;
        while (true) {
            if (i17 >= size) {
                if (this.keyToItemInfoMap.g()) {
                    reset();
                    return;
                }
            } else if (getHasAnimations(positionedItems.get(i17))) {
                break;
            } else {
                i17++;
            }
        }
        int i18 = this.firstVisibleIndex;
        LazyGridMeasuredItem lazyGridMeasuredItem = (LazyGridMeasuredItem) x.o0(positionedItems);
        this.firstVisibleIndex = lazyGridMeasuredItem != null ? lazyGridMeasuredItem.getIndex() : 0;
        LazyLayoutKeyIndexMap lazyLayoutKeyIndexMap2 = this.keyIndexMap;
        this.keyIndexMap = itemProvider.getKeyIndexMap();
        int i19 = isVertical ? layoutHeight : layoutWidth;
        long jIntOffset = isVertical ? IntOffsetKt.IntOffset(0, consumedScroll) : IntOffsetKt.IntOffset(consumedScroll, 0);
        z zVar = this.keyToItemInfoMap;
        Object[] objArr3 = zVar.f21882b;
        long[] jArr5 = zVar.f21881a;
        int length = jArr5.length - 2;
        if (length >= 0) {
            int i20 = 0;
            j11 = 128;
            j12 = 255;
            while (true) {
                long j14 = jArr5[i20];
                lazyLayoutKeyIndexMap = lazyLayoutKeyIndexMap2;
                if ((((~j14) << 7) & j14 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i21 = 8 - ((~(i20 - length)) >>> 31);
                    int i22 = 0;
                    while (i22 < i21) {
                        if ((j14 & 255) < 128) {
                            jArr4 = jArr5;
                            j13 = jIntOffset;
                            this.movingAwayKeys.d(objArr3[(i20 << 3) + i22]);
                        } else {
                            jArr4 = jArr5;
                            j13 = jIntOffset;
                        }
                        j14 >>= 8;
                        i22++;
                        jArr5 = jArr4;
                        jIntOffset = j13;
                    }
                    jArr3 = jArr5;
                    j10 = jIntOffset;
                    if (i21 != 8) {
                        break;
                    }
                } else {
                    jArr3 = jArr5;
                    j10 = jIntOffset;
                }
                if (i20 == length) {
                    break;
                }
                i20++;
                lazyLayoutKeyIndexMap2 = lazyLayoutKeyIndexMap;
                jArr5 = jArr3;
                jIntOffset = j10;
            }
        } else {
            j10 = jIntOffset;
            lazyLayoutKeyIndexMap = lazyLayoutKeyIndexMap2;
            j11 = 128;
            j12 = 255;
        }
        int size2 = positionedItems.size();
        int i23 = 0;
        while (true) {
            i10 = -1;
            if (i23 >= size2) {
                break;
            }
            LazyGridMeasuredItem lazyGridMeasuredItem2 = positionedItems.get(i23);
            this.movingAwayKeys.j(lazyGridMeasuredItem2.getKey());
            if (getHasAnimations(lazyGridMeasuredItem2)) {
                ItemInfo itemInfo = (ItemInfo) this.keyToItemInfoMap.e(lazyGridMeasuredItem2.getKey());
                if (itemInfo == null) {
                    ItemInfo itemInfo2 = new ItemInfo(lazyGridMeasuredItem2.getCrossAxisSize(), lazyGridMeasuredItem2.getCrossAxisOffset());
                    itemInfo2.updateAnimation(lazyGridMeasuredItem2, coroutineScope);
                    this.keyToItemInfoMap.k(lazyGridMeasuredItem2.getKey(), itemInfo2);
                    int index = lazyLayoutKeyIndexMap.getIndex(lazyGridMeasuredItem2.getKey());
                    if (index == -1 || lazyGridMeasuredItem2.getIndex() == index) {
                        long offset = lazyGridMeasuredItem2.getOffset();
                        initializeAnimation(lazyGridMeasuredItem2, lazyGridMeasuredItem2.getIsVertical() ? IntOffset.m5803getYimpl(offset) : IntOffset.m5802getXimpl(offset), itemInfo2);
                    } else if (index < i18) {
                        this.movingInFromStartBound.add(lazyGridMeasuredItem2);
                    } else {
                        this.movingInFromEndBound.add(lazyGridMeasuredItem2);
                    }
                    i15 = size2;
                    i16 = i18;
                } else {
                    LazyLayoutAnimation[] animations = itemInfo.getAnimations();
                    int length2 = animations.length;
                    int i24 = 0;
                    while (i24 < length2) {
                        LazyLayoutAnimation lazyLayoutAnimation = animations[i24];
                        int i25 = size2;
                        int i26 = i18;
                        if (lazyLayoutAnimation != null) {
                            lazyLayoutAnimationArr = animations;
                            if (!IntOffset.m5801equalsimpl0(lazyLayoutAnimation.getRawOffset(), LazyLayoutAnimation.INSTANCE.m681getNotInitializednOccac())) {
                                long rawOffset = lazyLayoutAnimation.getRawOffset();
                                lazyLayoutAnimation.m680setRawOffsetgyyYBs(IntOffsetKt.IntOffset(IntOffset.m5802getXimpl(j10) + IntOffset.m5802getXimpl(rawOffset), IntOffset.m5803getYimpl(j10) + IntOffset.m5803getYimpl(rawOffset)));
                            }
                        } else {
                            lazyLayoutAnimationArr = animations;
                        }
                        i24++;
                        i18 = i26;
                        size2 = i25;
                        animations = lazyLayoutAnimationArr;
                    }
                    i15 = size2;
                    i16 = i18;
                    itemInfo.setCrossAxisSize(lazyGridMeasuredItem2.getCrossAxisSize());
                    itemInfo.setCrossAxisOffset(lazyGridMeasuredItem2.getCrossAxisOffset());
                    startAnimationsIfNeeded(lazyGridMeasuredItem2);
                }
            } else {
                i15 = size2;
                i16 = i18;
                this.keyToItemInfoMap.h(lazyGridMeasuredItem2.getKey());
            }
            i23++;
            i18 = i16;
            size2 = i15;
        }
        List<LazyGridMeasuredItem> list = this.movingInFromStartBound;
        if (list.size() > 1) {
            w.X(new Comparator() { // from class: androidx.compose.foundation.lazy.grid.LazyGridItemPlacementAnimator$onMeasured$$inlined$sortByDescending$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t2, T t10) {
                    return r0.e(Integer.valueOf(lazyLayoutKeyIndexMap.getIndex(((LazyGridMeasuredItem) t10).getKey())), Integer.valueOf(lazyLayoutKeyIndexMap.getIndex(((LazyGridMeasuredItem) t2).getKey())));
                }
            }, list);
        }
        List<LazyGridMeasuredItem> list2 = this.movingInFromStartBound;
        int size3 = list2.size();
        int i27 = -1;
        int i28 = 0;
        int i29 = 0;
        int i30 = 0;
        while (i30 < size3) {
            LazyGridMeasuredItem lazyGridMeasuredItem3 = list2.get(i30);
            int row = isVertical ? lazyGridMeasuredItem3.getRow() : lazyGridMeasuredItem3.getColumn();
            if (row == -1 || row != i27) {
                i29 += i28;
                mainAxisSize2 = lazyGridMeasuredItem3.getMainAxisSize();
                i14 = row;
            } else {
                i14 = i27;
                mainAxisSize2 = Math.max(i28, lazyGridMeasuredItem3.getMainAxisSize());
            }
            int i31 = i29;
            initializeAnimation$default(this, lazyGridMeasuredItem3, (0 - i31) - lazyGridMeasuredItem3.getMainAxisSize(), null, 4, null);
            startAnimationsIfNeeded(lazyGridMeasuredItem3);
            i30++;
            i27 = i14;
            i28 = mainAxisSize2;
            i29 = i31;
        }
        List<LazyGridMeasuredItem> list3 = this.movingInFromEndBound;
        if (list3.size() > 1) {
            w.X(new Comparator() { // from class: androidx.compose.foundation.lazy.grid.LazyGridItemPlacementAnimator$onMeasured$$inlined$sortBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t2, T t10) {
                    return r0.e(Integer.valueOf(lazyLayoutKeyIndexMap.getIndex(((LazyGridMeasuredItem) t2).getKey())), Integer.valueOf(lazyLayoutKeyIndexMap.getIndex(((LazyGridMeasuredItem) t10).getKey())));
                }
            }, list3);
        }
        List<LazyGridMeasuredItem> list4 = this.movingInFromEndBound;
        int size4 = list4.size();
        int i32 = -1;
        int i33 = 0;
        int i34 = 0;
        int i35 = 0;
        while (i35 < size4) {
            LazyGridMeasuredItem lazyGridMeasuredItem4 = list4.get(i35);
            int row2 = isVertical ? lazyGridMeasuredItem4.getRow() : lazyGridMeasuredItem4.getColumn();
            if (row2 == -1 || row2 != i32) {
                i34 += i33;
                mainAxisSize = lazyGridMeasuredItem4.getMainAxisSize();
                i13 = row2;
            } else {
                i13 = i32;
                mainAxisSize = Math.max(i33, lazyGridMeasuredItem4.getMainAxisSize());
            }
            int i36 = i34;
            initializeAnimation$default(this, lazyGridMeasuredItem4, i19 + i36, null, 4, null);
            startAnimationsIfNeeded(lazyGridMeasuredItem4);
            i35++;
            i32 = i13;
            i33 = mainAxisSize;
            i34 = i36;
        }
        d0 d0Var = this.movingAwayKeys;
        Object[] objArr4 = d0Var.f21836b;
        long[] jArr6 = d0Var.f21835a;
        int length3 = jArr6.length - 2;
        if (length3 >= 0) {
            int i37 = 0;
            while (true) {
                long j15 = jArr6[i37];
                if ((((~j15) << 7) & j15 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i38 = 8 - ((~(i37 - length3)) >>> 31);
                    int i39 = 0;
                    while (i39 < i38) {
                        if ((j15 & j12) < j11) {
                            Object obj = objArr4[(i37 << 3) + i39];
                            ItemInfo itemInfo3 = (ItemInfo) this.keyToItemInfoMap.e(obj);
                            jArr2 = jArr6;
                            int index2 = this.keyIndexMap.getIndex(obj);
                            if (index2 == i10) {
                                this.keyToItemInfoMap.h(obj);
                            } else {
                                if (isVertical) {
                                    i11 = index2;
                                    jM5655fixedHeightOenEA2s = Constraints.INSTANCE.m5656fixedWidthOenEA2s(itemInfo3.getCrossAxisSize());
                                } else {
                                    i11 = index2;
                                    jM5655fixedHeightOenEA2s = Constraints.INSTANCE.m5655fixedHeightOenEA2s(itemInfo3.getCrossAxisSize());
                                }
                                LazyGridMeasuredItem lazyGridMeasuredItemM668getAndMeasure3p2s80s$default = LazyGridMeasuredItemProvider.m668getAndMeasure3p2s80s$default(itemProvider, i11, 0, jM5655fixedHeightOenEA2s, 2, null);
                                objArr2 = objArr4;
                                int i40 = i11;
                                lazyGridMeasuredItemM668getAndMeasure3p2s80s$default.setNonScrollableItem(true);
                                LazyLayoutAnimation[] animations2 = itemInfo3.getAnimations();
                                int length4 = animations2.length;
                                int i41 = 0;
                                while (true) {
                                    if (i41 < length4) {
                                        LazyLayoutAnimation lazyLayoutAnimation2 = animations2[i41];
                                        int i42 = length4;
                                        if (lazyLayoutAnimation2 != null) {
                                            boolean zIsPlacementAnimationInProgress = lazyLayoutAnimation2.isPlacementAnimationInProgress();
                                            i12 = i41;
                                            if (zIsPlacementAnimationInProgress) {
                                                break;
                                            }
                                        } else {
                                            i12 = i41;
                                        }
                                        i41 = i12 + 1;
                                        length4 = i42;
                                    } else if (i40 == lazyLayoutKeyIndexMap.getIndex(obj)) {
                                        this.keyToItemInfoMap.h(obj);
                                    }
                                }
                                if (i40 < this.firstVisibleIndex) {
                                    this.movingAwayToStartBound.add(lazyGridMeasuredItemM668getAndMeasure3p2s80s$default);
                                } else {
                                    this.movingAwayToEndBound.add(lazyGridMeasuredItemM668getAndMeasure3p2s80s$default);
                                }
                                j15 >>= 8;
                                i39++;
                                jArr6 = jArr2;
                                objArr4 = objArr2;
                                i10 = -1;
                            }
                        } else {
                            jArr2 = jArr6;
                        }
                        objArr2 = objArr4;
                        j15 >>= 8;
                        i39++;
                        jArr6 = jArr2;
                        objArr4 = objArr2;
                        i10 = -1;
                    }
                    jArr = jArr6;
                    objArr = objArr4;
                    if (i38 != 8) {
                        break;
                    }
                } else {
                    jArr = jArr6;
                    objArr = objArr4;
                }
                if (i37 == length3) {
                    break;
                }
                i37++;
                jArr6 = jArr;
                objArr4 = objArr;
                i10 = -1;
            }
        }
        List<LazyGridMeasuredItem> list5 = this.movingAwayToStartBound;
        if (list5.size() > 1) {
            w.X(new Comparator() { // from class: androidx.compose.foundation.lazy.grid.LazyGridItemPlacementAnimator$onMeasured$$inlined$sortByDescending$2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t2, T t10) {
                    return r0.e(Integer.valueOf(this.this$0.keyIndexMap.getIndex(((LazyGridMeasuredItem) t10).getKey())), Integer.valueOf(this.this$0.keyIndexMap.getIndex(((LazyGridMeasuredItem) t2).getKey())));
                }
            }, list5);
        }
        List<LazyGridMeasuredItem> list6 = this.movingAwayToStartBound;
        int size5 = list6.size();
        int i43 = -1;
        int mainAxisSize3 = 0;
        int i44 = 0;
        for (int i45 = 0; i45 < size5; i45++) {
            LazyGridMeasuredItem lazyGridMeasuredItem5 = list6.get(i45);
            int lineIndexOfItem = spanLayoutProvider.getLineIndexOfItem(lazyGridMeasuredItem5.getIndex());
            if (lineIndexOfItem == -1 || lineIndexOfItem != i43) {
                i44 += mainAxisSize3;
                mainAxisSize3 = lazyGridMeasuredItem5.getMainAxisSize();
                i43 = lineIndexOfItem;
            } else {
                mainAxisSize3 = Math.max(mainAxisSize3, lazyGridMeasuredItem5.getMainAxisSize());
            }
            LazyGridMeasuredItem.position$default(lazyGridMeasuredItem5, (0 - i44) - lazyGridMeasuredItem5.getMainAxisSize(), ((ItemInfo) this.keyToItemInfoMap.e(lazyGridMeasuredItem5.getKey())).getCrossAxisOffset(), layoutWidth, layoutHeight, 0, 0, 48, null);
            positionedItems.add(lazyGridMeasuredItem5);
            startAnimationsIfNeeded(lazyGridMeasuredItem5);
        }
        List<LazyGridMeasuredItem> list7 = this.movingAwayToEndBound;
        if (list7.size() > 1) {
            w.X(new Comparator() { // from class: androidx.compose.foundation.lazy.grid.LazyGridItemPlacementAnimator$onMeasured$$inlined$sortBy$2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t2, T t10) {
                    return r0.e(Integer.valueOf(this.this$0.keyIndexMap.getIndex(((LazyGridMeasuredItem) t2).getKey())), Integer.valueOf(this.this$0.keyIndexMap.getIndex(((LazyGridMeasuredItem) t10).getKey())));
                }
            }, list7);
        }
        List<LazyGridMeasuredItem> list8 = this.movingAwayToEndBound;
        int size6 = list8.size();
        int mainAxisSize4 = 0;
        int i46 = -1;
        int i47 = 0;
        for (int i48 = 0; i48 < size6; i48++) {
            LazyGridMeasuredItem lazyGridMeasuredItem6 = list8.get(i48);
            int lineIndexOfItem2 = spanLayoutProvider.getLineIndexOfItem(lazyGridMeasuredItem6.getIndex());
            if (lineIndexOfItem2 == -1 || lineIndexOfItem2 != i46) {
                i47 += mainAxisSize4;
                mainAxisSize4 = lazyGridMeasuredItem6.getMainAxisSize();
                i46 = lineIndexOfItem2;
            } else {
                mainAxisSize4 = Math.max(mainAxisSize4, lazyGridMeasuredItem6.getMainAxisSize());
            }
            LazyGridMeasuredItem.position$default(lazyGridMeasuredItem6, i19 + i47, ((ItemInfo) this.keyToItemInfoMap.e(lazyGridMeasuredItem6.getKey())).getCrossAxisOffset(), layoutWidth, layoutHeight, 0, 0, 48, null);
            positionedItems.add(lazyGridMeasuredItem6);
            startAnimationsIfNeeded(lazyGridMeasuredItem6);
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

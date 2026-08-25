package androidx.compose.foundation.lazy;

import androidx.compose.foundation.lazy.layout.LazyLayoutAnimation;
import androidx.compose.foundation.lazy.layout.LazyLayoutAnimationSpecsNode;
import androidx.compose.foundation.lazy.layout.LazyLayoutKeyIndexMap;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import com.google.common.util.concurrent.r0;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import ka.k0;
import kotlin.Metadata;
import kotlin.collections.h0;
import kotlin.collections.w;
import kotlin.collections.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\b\u0000\u0018\u00002\u00020\u0001:\u00018B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J)\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\r\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\r\u0010\u000eJ[\u0010\u001c\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00062\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00040\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001a¢\u0006\u0004\b\u001c\u0010\u001dJ\r\u0010\u001e\u001a\u00020\n¢\u0006\u0004\b\u001e\u0010\u0003J\u001f\u0010\"\u001a\u0004\u0018\u00010!2\u0006\u0010\u001f\u001a\u00020\u00012\u0006\u0010 \u001a\u00020\u0006¢\u0006\u0004\b\"\u0010#R \u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\b0$8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010&R\u0018\u0010(\u001a\u0004\u0018\u00010'8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b(\u0010)R\u0016\u0010*\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b*\u0010+R$\u0010.\u001a\u0012\u0012\u0004\u0012\u00020\u00010,j\b\u0012\u0004\u0012\u00020\u0001`-8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b.\u0010/R\u001a\u00100\u001a\b\u0012\u0004\u0012\u00020\u00040\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u00101R\u001a\u00102\u001a\b\u0012\u0004\u0012\u00020\u00040\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00101R\u001a\u00103\u001a\b\u0012\u0004\u0012\u00020\u00040\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b3\u00101R\u001a\u00104\u001a\b\u0012\u0004\u0012\u00020\u00040\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00101R\u0018\u00107\u001a\u00020\u0016*\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b5\u00106¨\u00069"}, d2 = {"Landroidx/compose/foundation/lazy/LazyListItemAnimator;", "", "<init>", "()V", "Landroidx/compose/foundation/lazy/LazyListMeasuredItem;", "item", "", "mainAxisOffset", "Landroidx/compose/foundation/lazy/LazyListItemAnimator$ItemInfo;", "itemInfo", "Lx6/t0;", "initializeAnimation", "(Landroidx/compose/foundation/lazy/LazyListMeasuredItem;ILandroidx/compose/foundation/lazy/LazyListItemAnimator$ItemInfo;)V", "startPlacementAnimationsIfNeeded", "(Landroidx/compose/foundation/lazy/LazyListMeasuredItem;)V", "consumedScroll", "layoutWidth", "layoutHeight", "", "positionedItems", "Landroidx/compose/foundation/lazy/LazyListMeasuredItemProvider;", "itemProvider", "", "isVertical", "isLookingAhead", "hasLookaheadOccurred", "Lka/k0;", "coroutineScope", "onMeasured", "(IIILjava/util/List;Landroidx/compose/foundation/lazy/LazyListMeasuredItemProvider;ZZZLka/k0;)V", "reset", "key", "placeableIndex", "Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimation;", "getAnimation", "(Ljava/lang/Object;I)Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimation;", "", "keyToItemInfoMap", "Ljava/util/Map;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutKeyIndexMap;", "keyIndexMap", "Landroidx/compose/foundation/lazy/layout/LazyLayoutKeyIndexMap;", "firstVisibleIndex", "I", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/LinkedHashSet;", "movingAwayKeys", "Ljava/util/LinkedHashSet;", "movingInFromStartBound", "Ljava/util/List;", "movingInFromEndBound", "movingAwayToStartBound", "movingAwayToEndBound", "getHasAnimations", "(Landroidx/compose/foundation/lazy/LazyListMeasuredItem;)Z", "hasAnimations", "ItemInfo", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class LazyListItemAnimator {
    public static final int $stable = 8;
    private int firstVisibleIndex;
    private LazyLayoutKeyIndexMap keyIndexMap;
    private final Map<Object, ItemInfo> keyToItemInfoMap = new LinkedHashMap();
    private final LinkedHashSet<Object> movingAwayKeys = new LinkedHashSet<>();
    private final List<LazyListMeasuredItem> movingInFromStartBound = new ArrayList();
    private final List<LazyListMeasuredItem> movingInFromEndBound = new ArrayList();
    private final List<LazyListMeasuredItem> movingAwayToStartBound = new ArrayList();
    private final List<LazyListMeasuredItem> movingAwayToEndBound = new ArrayList();

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nR4\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b2\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b8\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Landroidx/compose/foundation/lazy/LazyListItemAnimator$ItemInfo;", "", "<init>", "()V", "Landroidx/compose/foundation/lazy/LazyListMeasuredItem;", "positionedItem", "Lka/k0;", "coroutineScope", "Lx6/t0;", "updateAnimation", "(Landroidx/compose/foundation/lazy/LazyListMeasuredItem;Lka/k0;)V", "", "Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimation;", "<set-?>", "animations", "[Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimation;", "getAnimations", "()[Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimation;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class ItemInfo {
        private LazyLayoutAnimation[] animations = LazyListItemAnimatorKt.EmptyArray;

        public final LazyLayoutAnimation[] getAnimations() {
            return this.animations;
        }

        public final void updateAnimation(LazyListMeasuredItem positionedItem, k0 coroutineScope) {
            int length = this.animations.length;
            for (int placeablesCount = positionedItem.getPlaceablesCount(); placeablesCount < length; placeablesCount++) {
                LazyLayoutAnimation lazyLayoutAnimation = this.animations[placeablesCount];
                if (lazyLayoutAnimation != null) {
                    lazyLayoutAnimation.stopAnimations();
                }
            }
            if (this.animations.length != positionedItem.getPlaceablesCount()) {
                this.animations = (LazyLayoutAnimation[]) Arrays.copyOf(this.animations, positionedItem.getPlaceablesCount());
            }
            int placeablesCount2 = positionedItem.getPlaceablesCount();
            for (int i10 = 0; i10 < placeablesCount2; i10++) {
                LazyLayoutAnimationSpecsNode specs = LazyListItemAnimatorKt.getSpecs(positionedItem.getParentData(i10));
                if (specs == null) {
                    LazyLayoutAnimation lazyLayoutAnimation2 = this.animations[i10];
                    if (lazyLayoutAnimation2 != null) {
                        lazyLayoutAnimation2.stopAnimations();
                    }
                    this.animations[i10] = null;
                } else {
                    LazyLayoutAnimation lazyLayoutAnimation3 = this.animations[i10];
                    if (lazyLayoutAnimation3 == null) {
                        lazyLayoutAnimation3 = new LazyLayoutAnimation(coroutineScope);
                        this.animations[i10] = lazyLayoutAnimation3;
                    }
                    lazyLayoutAnimation3.setAppearanceSpec(specs.getAppearanceSpec());
                    lazyLayoutAnimation3.setPlacementSpec(specs.getPlacementSpec());
                }
            }
        }
    }

    private final boolean getHasAnimations(LazyListMeasuredItem lazyListMeasuredItem) {
        int placeablesCount = lazyListMeasuredItem.getPlaceablesCount();
        for (int i10 = 0; i10 < placeablesCount; i10++) {
            if (LazyListItemAnimatorKt.getSpecs(lazyListMeasuredItem.getParentData(i10)) != null) {
                return true;
            }
        }
        return false;
    }

    private final void initializeAnimation(LazyListMeasuredItem item, int mainAxisOffset, ItemInfo itemInfo) {
        int i10 = 0;
        long jM639getOffsetBjo55l4 = item.m639getOffsetBjo55l4(0);
        long jM5798copyiSbpLlY$default = item.getIsVertical() ? IntOffset.m5798copyiSbpLlY$default(jM639getOffsetBjo55l4, 0, mainAxisOffset, 1, null) : IntOffset.m5798copyiSbpLlY$default(jM639getOffsetBjo55l4, mainAxisOffset, 0, 2, null);
        LazyLayoutAnimation[] animations = itemInfo.getAnimations();
        int length = animations.length;
        int i11 = 0;
        while (i10 < length) {
            LazyLayoutAnimation lazyLayoutAnimation = animations[i10];
            int i12 = i11 + 1;
            if (lazyLayoutAnimation != null) {
                long jM639getOffsetBjo55l42 = item.m639getOffsetBjo55l4(i11);
                long jIntOffset = IntOffsetKt.IntOffset(IntOffset.m5802getXimpl(jM639getOffsetBjo55l42) - IntOffset.m5802getXimpl(jM639getOffsetBjo55l4), IntOffset.m5803getYimpl(jM639getOffsetBjo55l42) - IntOffset.m5803getYimpl(jM639getOffsetBjo55l4));
                lazyLayoutAnimation.m680setRawOffsetgyyYBs(IntOffsetKt.IntOffset(IntOffset.m5802getXimpl(jIntOffset) + IntOffset.m5802getXimpl(jM5798copyiSbpLlY$default), IntOffset.m5803getYimpl(jIntOffset) + IntOffset.m5803getYimpl(jM5798copyiSbpLlY$default)));
            }
            i10++;
            i11 = i12;
        }
    }

    public static /* synthetic */ void initializeAnimation$default(LazyListItemAnimator lazyListItemAnimator, LazyListMeasuredItem lazyListMeasuredItem, int i10, ItemInfo itemInfo, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            itemInfo = (ItemInfo) h0.s0(lazyListItemAnimator.keyToItemInfoMap, lazyListMeasuredItem.getKey());
        }
        lazyListItemAnimator.initializeAnimation(lazyListMeasuredItem, i10, itemInfo);
    }

    private final void startPlacementAnimationsIfNeeded(LazyListMeasuredItem item) {
        LazyLayoutAnimation[] animations = ((ItemInfo) h0.s0(this.keyToItemInfoMap, item.getKey())).getAnimations();
        int length = animations.length;
        int i10 = 0;
        int i11 = 0;
        while (i10 < length) {
            LazyLayoutAnimation lazyLayoutAnimation = animations[i10];
            int i12 = i11 + 1;
            if (lazyLayoutAnimation != null) {
                long jM639getOffsetBjo55l4 = item.m639getOffsetBjo55l4(i11);
                long rawOffset = lazyLayoutAnimation.getRawOffset();
                if (!IntOffset.m5801equalsimpl0(rawOffset, LazyLayoutAnimation.INSTANCE.m681getNotInitializednOccac()) && !IntOffset.m5801equalsimpl0(rawOffset, jM639getOffsetBjo55l4)) {
                    lazyLayoutAnimation.m675animatePlacementDeltagyyYBs(IntOffsetKt.IntOffset(IntOffset.m5802getXimpl(jM639getOffsetBjo55l4) - IntOffset.m5802getXimpl(rawOffset), IntOffset.m5803getYimpl(jM639getOffsetBjo55l4) - IntOffset.m5803getYimpl(rawOffset)));
                }
                lazyLayoutAnimation.m680setRawOffsetgyyYBs(jM639getOffsetBjo55l4);
            }
            i10++;
            i11 = i12;
        }
    }

    public final LazyLayoutAnimation getAnimation(Object key, int placeableIndex) {
        LazyLayoutAnimation[] animations;
        ItemInfo itemInfo = this.keyToItemInfoMap.get(key);
        if (itemInfo == null || (animations = itemInfo.getAnimations()) == null) {
            return null;
        }
        return animations[placeableIndex];
    }

    public final void onMeasured(int consumedScroll, int layoutWidth, int layoutHeight, List<LazyListMeasuredItem> positionedItems, LazyListMeasuredItemProvider itemProvider, boolean isVertical, boolean isLookingAhead, boolean hasLookaheadOccurred, k0 coroutineScope) {
        int sizeWithSpacings;
        Iterator it;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        k0 k0Var = coroutineScope;
        final LazyLayoutKeyIndexMap lazyLayoutKeyIndexMap = this.keyIndexMap;
        final LazyLayoutKeyIndexMap keyIndexMap = itemProvider.getKeyIndexMap();
        this.keyIndexMap = keyIndexMap;
        int size = positionedItems.size();
        int i15 = 0;
        while (true) {
            if (i15 >= size) {
                if (this.keyToItemInfoMap.isEmpty()) {
                    reset();
                    return;
                }
            } else if (getHasAnimations(positionedItems.get(i15))) {
                break;
            } else {
                i15++;
            }
        }
        int i16 = this.firstVisibleIndex;
        LazyListMeasuredItem lazyListMeasuredItem = (LazyListMeasuredItem) x.o0(positionedItems);
        this.firstVisibleIndex = lazyListMeasuredItem != null ? lazyListMeasuredItem.getIndex() : 0;
        int i17 = isVertical ? layoutHeight : layoutWidth;
        long jIntOffset = isVertical ? IntOffsetKt.IntOffset(0, consumedScroll) : IntOffsetKt.IntOffset(consumedScroll, 0);
        boolean z = isLookingAhead || !hasLookaheadOccurred;
        this.movingAwayKeys.addAll(this.keyToItemInfoMap.keySet());
        int size2 = positionedItems.size();
        long j10 = jIntOffset;
        int i18 = 0;
        while (i18 < size2) {
            LazyListMeasuredItem lazyListMeasuredItem2 = positionedItems.get(i18);
            this.movingAwayKeys.remove(lazyListMeasuredItem2.getKey());
            if (getHasAnimations(lazyListMeasuredItem2)) {
                ItemInfo itemInfo = this.keyToItemInfoMap.get(lazyListMeasuredItem2.getKey());
                if (itemInfo == null) {
                    ItemInfo itemInfo2 = new ItemInfo();
                    itemInfo2.updateAnimation(lazyListMeasuredItem2, k0Var);
                    this.keyToItemInfoMap.put(lazyListMeasuredItem2.getKey(), itemInfo2);
                    int index = lazyLayoutKeyIndexMap != null ? lazyLayoutKeyIndexMap.getIndex(lazyListMeasuredItem2.getKey()) : -1;
                    if (lazyListMeasuredItem2.getIndex() == index || index == -1) {
                        long jM639getOffsetBjo55l4 = lazyListMeasuredItem2.m639getOffsetBjo55l4(0);
                        initializeAnimation(lazyListMeasuredItem2, lazyListMeasuredItem2.getIsVertical() ? IntOffset.m5803getYimpl(jM639getOffsetBjo55l4) : IntOffset.m5802getXimpl(jM639getOffsetBjo55l4), itemInfo2);
                        if (index == -1 && lazyLayoutKeyIndexMap != null) {
                            for (LazyLayoutAnimation lazyLayoutAnimation : itemInfo2.getAnimations()) {
                                if (lazyLayoutAnimation != null) {
                                    lazyLayoutAnimation.animateAppearance();
                                }
                            }
                        }
                    } else if (index < i16) {
                        this.movingInFromStartBound.add(lazyListMeasuredItem2);
                    } else {
                        this.movingInFromEndBound.add(lazyListMeasuredItem2);
                    }
                } else if (z) {
                    itemInfo.updateAnimation(lazyListMeasuredItem2, k0Var);
                    LazyLayoutAnimation[] animations = itemInfo.getAnimations();
                    int length = animations.length;
                    int i19 = 0;
                    while (i19 < length) {
                        int i20 = size2;
                        LazyLayoutAnimation lazyLayoutAnimation2 = animations[i19];
                        int i21 = i16;
                        if (lazyLayoutAnimation2 != null) {
                            i13 = length;
                            i14 = i17;
                            if (!IntOffset.m5801equalsimpl0(lazyLayoutAnimation2.getRawOffset(), LazyLayoutAnimation.INSTANCE.m681getNotInitializednOccac())) {
                                long rawOffset = lazyLayoutAnimation2.getRawOffset();
                                lazyLayoutAnimation2.m680setRawOffsetgyyYBs(IntOffsetKt.IntOffset(IntOffset.m5802getXimpl(j10) + IntOffset.m5802getXimpl(rawOffset), IntOffset.m5803getYimpl(j10) + IntOffset.m5803getYimpl(rawOffset)));
                            }
                        } else {
                            i13 = length;
                            i14 = i17;
                        }
                        i19++;
                        size2 = i20;
                        length = i13;
                        i16 = i21;
                        i17 = i14;
                    }
                    i10 = size2;
                    i11 = i16;
                    i12 = i17;
                    startPlacementAnimationsIfNeeded(lazyListMeasuredItem2);
                }
                i10 = size2;
                i11 = i16;
                i12 = i17;
            } else {
                i10 = size2;
                i11 = i16;
                i12 = i17;
                this.keyToItemInfoMap.remove(lazyListMeasuredItem2.getKey());
            }
            i18++;
            size2 = i10;
            k0Var = coroutineScope;
            i16 = i11;
            i17 = i12;
        }
        int i22 = i17;
        int i23 = -1;
        if (z && lazyLayoutKeyIndexMap != null) {
            List<LazyListMeasuredItem> list = this.movingInFromStartBound;
            if (list.size() > 1) {
                w.X(new Comparator() { // from class: androidx.compose.foundation.lazy.LazyListItemAnimator$onMeasured$$inlined$sortByDescending$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t2, T t10) {
                        return r0.e(Integer.valueOf(lazyLayoutKeyIndexMap.getIndex(((LazyListMeasuredItem) t10).getKey())), Integer.valueOf(lazyLayoutKeyIndexMap.getIndex(((LazyListMeasuredItem) t2).getKey())));
                    }
                }, list);
            }
            List<LazyListMeasuredItem> list2 = this.movingInFromStartBound;
            int size3 = list2.size();
            int i24 = 0;
            int i25 = 0;
            while (i25 < size3) {
                LazyListMeasuredItem lazyListMeasuredItem3 = list2.get(i25);
                int sizeWithSpacings2 = lazyListMeasuredItem3.getSizeWithSpacings() + i24;
                initializeAnimation$default(this, lazyListMeasuredItem3, 0 - sizeWithSpacings2, null, 4, null);
                startPlacementAnimationsIfNeeded(lazyListMeasuredItem3);
                i25++;
                i24 = sizeWithSpacings2;
            }
            List<LazyListMeasuredItem> list3 = this.movingInFromEndBound;
            if (list3.size() > 1) {
                w.X(new Comparator() { // from class: androidx.compose.foundation.lazy.LazyListItemAnimator$onMeasured$$inlined$sortBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t2, T t10) {
                        return r0.e(Integer.valueOf(lazyLayoutKeyIndexMap.getIndex(((LazyListMeasuredItem) t2).getKey())), Integer.valueOf(lazyLayoutKeyIndexMap.getIndex(((LazyListMeasuredItem) t10).getKey())));
                    }
                }, list3);
            }
            List<LazyListMeasuredItem> list4 = this.movingInFromEndBound;
            int size4 = list4.size();
            int i26 = 0;
            int i27 = 0;
            while (i27 < size4) {
                LazyListMeasuredItem lazyListMeasuredItem4 = list4.get(i27);
                int i28 = i26;
                int sizeWithSpacings3 = lazyListMeasuredItem4.getSizeWithSpacings() + i28;
                initializeAnimation$default(this, lazyListMeasuredItem4, i22 + i28, null, 4, null);
                startPlacementAnimationsIfNeeded(lazyListMeasuredItem4);
                i27++;
                i26 = sizeWithSpacings3;
            }
        }
        Iterator it2 = this.movingAwayKeys.iterator();
        while (it2.hasNext()) {
            Object next = it2.next();
            int index2 = keyIndexMap.getIndex(next);
            if (index2 == i23) {
                this.keyToItemInfoMap.remove(next);
                it = it2;
            } else {
                LazyListMeasuredItem andMeasure = itemProvider.getAndMeasure(index2);
                boolean z5 = true;
                andMeasure.setNonScrollableItem(true);
                LazyLayoutAnimation[] animations2 = ((ItemInfo) h0.s0(this.keyToItemInfoMap, next)).getAnimations();
                int length2 = animations2.length;
                int i29 = 0;
                while (true) {
                    if (i29 < length2) {
                        LazyLayoutAnimation lazyLayoutAnimation3 = animations2[i29];
                        it = it2;
                        if (lazyLayoutAnimation3 != null && lazyLayoutAnimation3.isPlacementAnimationInProgress() == z5) {
                            break;
                        }
                        i29++;
                        it2 = it;
                        z5 = true;
                    } else {
                        it = it2;
                        if (lazyLayoutKeyIndexMap == null || index2 != lazyLayoutKeyIndexMap.getIndex(next)) {
                            break;
                        } else {
                            this.keyToItemInfoMap.remove(next);
                        }
                    }
                }
                if (index2 < this.firstVisibleIndex) {
                    this.movingAwayToStartBound.add(andMeasure);
                } else {
                    this.movingAwayToEndBound.add(andMeasure);
                }
            }
            it2 = it;
            i23 = -1;
        }
        List<LazyListMeasuredItem> list5 = this.movingAwayToStartBound;
        if (list5.size() > 1) {
            w.X(new Comparator() { // from class: androidx.compose.foundation.lazy.LazyListItemAnimator$onMeasured$$inlined$sortByDescending$2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t2, T t10) {
                    return r0.e(Integer.valueOf(keyIndexMap.getIndex(((LazyListMeasuredItem) t10).getKey())), Integer.valueOf(keyIndexMap.getIndex(((LazyListMeasuredItem) t2).getKey())));
                }
            }, list5);
        }
        List<LazyListMeasuredItem> list6 = this.movingAwayToStartBound;
        int size5 = list6.size();
        int sizeWithSpacings4 = 0;
        for (int i30 = 0; i30 < size5; i30++) {
            LazyListMeasuredItem lazyListMeasuredItem5 = list6.get(i30);
            sizeWithSpacings4 += lazyListMeasuredItem5.getSizeWithSpacings();
            lazyListMeasuredItem5.position(isLookingAhead ? ((LazyListMeasuredItem) x.m0(positionedItems)).getOffset() - sizeWithSpacings4 : 0 - sizeWithSpacings4, layoutWidth, layoutHeight);
            if (z) {
                startPlacementAnimationsIfNeeded(lazyListMeasuredItem5);
            }
        }
        List<LazyListMeasuredItem> list7 = this.movingAwayToEndBound;
        if (list7.size() > 1) {
            w.X(new Comparator() { // from class: androidx.compose.foundation.lazy.LazyListItemAnimator$onMeasured$$inlined$sortBy$2
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t2, T t10) {
                    return r0.e(Integer.valueOf(keyIndexMap.getIndex(((LazyListMeasuredItem) t2).getKey())), Integer.valueOf(keyIndexMap.getIndex(((LazyListMeasuredItem) t10).getKey())));
                }
            }, list7);
        }
        List<LazyListMeasuredItem> list8 = this.movingAwayToEndBound;
        int size6 = list8.size();
        int sizeWithSpacings5 = 0;
        for (int i31 = 0; i31 < size6; i31++) {
            LazyListMeasuredItem lazyListMeasuredItem6 = list8.get(i31);
            if (isLookingAhead) {
                LazyListMeasuredItem lazyListMeasuredItem7 = (LazyListMeasuredItem) x.w0(positionedItems);
                sizeWithSpacings = lazyListMeasuredItem7.getSizeWithSpacings() + lazyListMeasuredItem7.getOffset() + sizeWithSpacings5;
            } else {
                sizeWithSpacings = i22 + sizeWithSpacings5;
            }
            sizeWithSpacings5 += lazyListMeasuredItem6.getSizeWithSpacings();
            lazyListMeasuredItem6.position(sizeWithSpacings, layoutWidth, layoutHeight);
            if (z) {
                startPlacementAnimationsIfNeeded(lazyListMeasuredItem6);
            }
        }
        List<LazyListMeasuredItem> list9 = this.movingAwayToStartBound;
        Collections.reverse(list9);
        positionedItems.addAll(0, list9);
        positionedItems.addAll(this.movingAwayToEndBound);
        this.movingInFromStartBound.clear();
        this.movingInFromEndBound.clear();
        this.movingAwayToStartBound.clear();
        this.movingAwayToEndBound.clear();
        this.movingAwayKeys.clear();
    }

    public final void reset() {
        this.keyToItemInfoMap.clear();
        this.keyIndexMap = LazyLayoutKeyIndexMap.INSTANCE;
        this.firstVisibleIndex = -1;
    }
}

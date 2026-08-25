package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.lazy.layout.LazyLayoutAnimation;
import androidx.compose.foundation.lazy.layout.LazyLayoutAnimationSpecsNode;
import androidx.media3.extractor.text.ttml.TtmlNode;
import java.util.Arrays;
import ka.k0;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u001d\u0010\r\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eR\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\"\u0010\u0004\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010\u000f\u001a\u0004\b\u0014\u0010\u0011\"\u0004\b\u0015\u0010\u0013R\"\u0010\u0005\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0005\u0010\u000f\u001a\u0004\b\u0016\u0010\u0011\"\u0004\b\u0017\u0010\u0013R4\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u00182\u000e\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u00188\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e¨\u0006\u001f"}, d2 = {"Landroidx/compose/foundation/lazy/staggeredgrid/ItemInfo;", "", "", "lane", TtmlNode.TAG_SPAN, "crossAxisOffset", "<init>", "(III)V", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasuredItem;", "positionedItem", "Lka/k0;", "coroutineScope", "Lx6/t0;", "updateAnimation", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasuredItem;Lka/k0;)V", "I", "getLane", "()I", "setLane", "(I)V", "getSpan", "setSpan", "getCrossAxisOffset", "setCrossAxisOffset", "", "Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimation;", "<set-?>", "animations", "[Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimation;", "getAnimations", "()[Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimation;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ItemInfo {
    private LazyLayoutAnimation[] animations = LazyStaggeredGridItemPlacementAnimatorKt.EmptyArray;
    private int crossAxisOffset;
    private int lane;
    private int span;

    public ItemInfo(int i10, int i11, int i12) {
        this.lane = i10;
        this.span = i11;
        this.crossAxisOffset = i12;
    }

    public final LazyLayoutAnimation[] getAnimations() {
        return this.animations;
    }

    public final int getCrossAxisOffset() {
        return this.crossAxisOffset;
    }

    public final int getLane() {
        return this.lane;
    }

    public final int getSpan() {
        return this.span;
    }

    public final void setCrossAxisOffset(int i10) {
        this.crossAxisOffset = i10;
    }

    public final void setLane(int i10) {
        this.lane = i10;
    }

    public final void setSpan(int i10) {
        this.span = i10;
    }

    public final void updateAnimation(LazyStaggeredGridMeasuredItem positionedItem, k0 coroutineScope) {
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
            LazyLayoutAnimationSpecsNode specs = LazyStaggeredGridItemPlacementAnimatorKt.getSpecs(positionedItem.getParentData(i10));
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

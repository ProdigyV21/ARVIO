package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.lazy.layout.LazyLayoutAnimation;
import androidx.compose.foundation.lazy.layout.LazyLayoutAnimationSpecsNode;
import java.util.Arrays;
import ka.k0;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u001d\u0010\f\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\f\u0010\rR\"\u0010\u0003\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\u0004\u001a\u00020\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010\u000e\u001a\u0004\b\u0013\u0010\u0010\"\u0004\b\u0014\u0010\u0012R4\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u00152\u000e\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u00158\u0006@BX\u0086\u000e¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Landroidx/compose/foundation/lazy/grid/ItemInfo;", "", "", "crossAxisSize", "crossAxisOffset", "<init>", "(II)V", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItem;", "positionedItem", "Lka/k0;", "coroutineScope", "Lx6/t0;", "updateAnimation", "(Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItem;Lka/k0;)V", "I", "getCrossAxisSize", "()I", "setCrossAxisSize", "(I)V", "getCrossAxisOffset", "setCrossAxisOffset", "", "Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimation;", "<set-?>", "animations", "[Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimation;", "getAnimations", "()[Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimation;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class ItemInfo {
    private LazyLayoutAnimation[] animations = LazyGridItemPlacementAnimatorKt.EmptyArray;
    private int crossAxisOffset;
    private int crossAxisSize;

    public ItemInfo(int i10, int i11) {
        this.crossAxisSize = i10;
        this.crossAxisOffset = i11;
    }

    public final LazyLayoutAnimation[] getAnimations() {
        return this.animations;
    }

    public final int getCrossAxisOffset() {
        return this.crossAxisOffset;
    }

    public final int getCrossAxisSize() {
        return this.crossAxisSize;
    }

    public final void setCrossAxisOffset(int i10) {
        this.crossAxisOffset = i10;
    }

    public final void setCrossAxisSize(int i10) {
        this.crossAxisSize = i10;
    }

    public final void updateAnimation(LazyGridMeasuredItem positionedItem, k0 coroutineScope) {
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
            LazyLayoutAnimationSpecsNode specs = LazyGridItemPlacementAnimatorKt.getSpecs(positionedItem.getParentData(i10));
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

package androidx.compose.foundation.lazy;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.lazy.layout.LazyLayoutAnimationSpecsNode;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.IntOffset;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B'\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\n\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u000e\u0010\u000fJ\u0013\u0010\u0011\u001a\u00020\r*\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u0018\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\u0018\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0003¢\u0006\u0004\b\u0015\u0010\u0014J4\u0010\u0016\u001a\u00020\u00002\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0001¢\u0006\u0004\b\u0016\u0010\u0017J\u0010\u0010\u0019\u001a\u00020\u0018HÖ\u0001¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001c\u001a\u00020\u001bHÖ\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\u001a\u0010!\u001a\u00020 2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001eHÖ\u0003¢\u0006\u0004\b!\u0010\"R\u001f\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006¢\u0006\f\n\u0004\b\u0005\u0010#\u001a\u0004\b$\u0010\u0014R\u001f\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00038\u0006¢\u0006\f\n\u0004\b\u0007\u0010#\u001a\u0004\b%\u0010\u0014¨\u0006&"}, d2 = {"Landroidx/compose/foundation/lazy/AnimateItemElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimationSpecsNode;", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "", "appearanceSpec", "Landroidx/compose/ui/unit/IntOffset;", "placementSpec", "<init>", "(Landroidx/compose/animation/core/FiniteAnimationSpec;Landroidx/compose/animation/core/FiniteAnimationSpec;)V", "create", "()Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimationSpecsNode;", "node", "Lx6/t0;", "update", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimationSpecsNode;)V", "Landroidx/compose/ui/platform/InspectorInfo;", "inspectableProperties", "(Landroidx/compose/ui/platform/InspectorInfo;)V", "component1", "()Landroidx/compose/animation/core/FiniteAnimationSpec;", "component2", "copy", "(Landroidx/compose/animation/core/FiniteAnimationSpec;Landroidx/compose/animation/core/FiniteAnimationSpec;)Landroidx/compose/foundation/lazy/AnimateItemElement;", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "getAppearanceSpec", "getPlacementSpec", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class AnimateItemElement extends ModifierNodeElement<LazyLayoutAnimationSpecsNode> {
    private final FiniteAnimationSpec<Float> appearanceSpec;
    private final FiniteAnimationSpec<IntOffset> placementSpec;

    public AnimateItemElement(FiniteAnimationSpec<Float> finiteAnimationSpec, FiniteAnimationSpec<IntOffset> finiteAnimationSpec2) {
        this.appearanceSpec = finiteAnimationSpec;
        this.placementSpec = finiteAnimationSpec2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AnimateItemElement copy$default(AnimateItemElement animateItemElement, FiniteAnimationSpec finiteAnimationSpec, FiniteAnimationSpec finiteAnimationSpec2, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            finiteAnimationSpec = animateItemElement.appearanceSpec;
        }
        if ((i10 & 2) != 0) {
            finiteAnimationSpec2 = animateItemElement.placementSpec;
        }
        return animateItemElement.copy(finiteAnimationSpec, finiteAnimationSpec2);
    }

    public final FiniteAnimationSpec<Float> component1() {
        return this.appearanceSpec;
    }

    public final FiniteAnimationSpec<IntOffset> component2() {
        return this.placementSpec;
    }

    public final AnimateItemElement copy(FiniteAnimationSpec<Float> appearanceSpec, FiniteAnimationSpec<IntOffset> placementSpec) {
        return new AnimateItemElement(appearanceSpec, placementSpec);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AnimateItemElement)) {
            return false;
        }
        AnimateItemElement animateItemElement = (AnimateItemElement) other;
        return p.a(this.appearanceSpec, animateItemElement.appearanceSpec) && p.a(this.placementSpec, animateItemElement.placementSpec);
    }

    public final FiniteAnimationSpec<Float> getAppearanceSpec() {
        return this.appearanceSpec;
    }

    public final FiniteAnimationSpec<IntOffset> getPlacementSpec() {
        return this.placementSpec;
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        FiniteAnimationSpec<Float> finiteAnimationSpec = this.appearanceSpec;
        int iHashCode = (finiteAnimationSpec == null ? 0 : finiteAnimationSpec.hashCode()) * 31;
        FiniteAnimationSpec<IntOffset> finiteAnimationSpec2 = this.placementSpec;
        return iHashCode + (finiteAnimationSpec2 != null ? finiteAnimationSpec2.hashCode() : 0);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo inspectorInfo) {
        inspectorInfo.setName("animateItemPlacement");
        inspectorInfo.setValue(this.placementSpec);
    }

    public String toString() {
        return "AnimateItemElement(appearanceSpec=" + this.appearanceSpec + ", placementSpec=" + this.placementSpec + ')';
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public LazyLayoutAnimationSpecsNode create() {
        return new LazyLayoutAnimationSpecsNode(this.appearanceSpec, this.placementSpec);
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(LazyLayoutAnimationSpecsNode node) {
        node.setAppearanceSpec(this.appearanceSpec);
        node.setPlacementSpec(this.placementSpec);
    }
}

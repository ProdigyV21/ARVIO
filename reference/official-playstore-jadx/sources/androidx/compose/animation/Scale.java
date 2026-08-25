package androidx.compose.animation;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.ui.graphics.TransformOrigin;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\b\u0081\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\n\u001a\u00020\u0002HÆ\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u0016\u0010\u000e\u001a\u00020\u0004HÆ\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\f\u0010\rJ\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006HÆ\u0003¢\u0006\u0004\b\u000f\u0010\u0010J7\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006HÆ\u0001ø\u0001\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0015\u001a\u00020\u0014HÖ\u0001¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u0018\u001a\u00020\u0017HÖ\u0001¢\u0006\u0004\b\u0018\u0010\u0019J\u001a\u0010\u001c\u001a\u00020\u001b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001e\u001a\u0004\b\u001f\u0010\u000bR\u001d\u0010\u0005\u001a\u00020\u00048\u0006ø\u0001\u0001ø\u0001\u0000¢\u0006\f\n\u0004\b\u0005\u0010 \u001a\u0004\b!\u0010\rR\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\"\u001a\u0004\b#\u0010\u0010\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006$"}, d2 = {"Landroidx/compose/animation/Scale;", "", "", "scale", "Landroidx/compose/ui/graphics/TransformOrigin;", "transformOrigin", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "animationSpec", "<init>", "(FJLandroidx/compose/animation/core/FiniteAnimationSpec;Lkotlin/jvm/internal/h;)V", "component1", "()F", "component2-SzJe1aQ", "()J", "component2", "component3", "()Landroidx/compose/animation/core/FiniteAnimationSpec;", "copy-bnNdC4k", "(FJLandroidx/compose/animation/core/FiniteAnimationSpec;)Landroidx/compose/animation/Scale;", "copy", "", "toString", "()Ljava/lang/String;", "", "hashCode", "()I", "other", "", "equals", "(Ljava/lang/Object;)Z", "F", "getScale", "J", "getTransformOrigin-SzJe1aQ", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "getAnimationSpec", "animation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class Scale {
    public static final int $stable = 0;
    private final FiniteAnimationSpec<Float> animationSpec;
    private final float scale;
    private final long transformOrigin;

    public /* synthetic */ Scale(float f10, long j10, FiniteAnimationSpec finiteAnimationSpec, h hVar) {
        this(f10, j10, finiteAnimationSpec);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: copy-bnNdC4k$default, reason: not valid java name */
    public static /* synthetic */ Scale m63copybnNdC4k$default(Scale scale, float f10, long j10, FiniteAnimationSpec finiteAnimationSpec, int i10, Object obj) {
        if ((i10 & 1) != 0) {
            f10 = scale.scale;
        }
        if ((i10 & 2) != 0) {
            j10 = scale.transformOrigin;
        }
        if ((i10 & 4) != 0) {
            finiteAnimationSpec = scale.animationSpec;
        }
        return scale.m65copybnNdC4k(f10, j10, finiteAnimationSpec);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final float getScale() {
        return this.scale;
    }

    /* JADX INFO: renamed from: component2-SzJe1aQ, reason: not valid java name and from getter */
    public final long getTransformOrigin() {
        return this.transformOrigin;
    }

    public final FiniteAnimationSpec<Float> component3() {
        return this.animationSpec;
    }

    /* JADX INFO: renamed from: copy-bnNdC4k, reason: not valid java name */
    public final Scale m65copybnNdC4k(float scale, long transformOrigin, FiniteAnimationSpec<Float> animationSpec) {
        return new Scale(scale, transformOrigin, animationSpec, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Scale)) {
            return false;
        }
        Scale scale = (Scale) other;
        return Float.compare(this.scale, scale.scale) == 0 && TransformOrigin.m3835equalsimpl0(this.transformOrigin, scale.transformOrigin) && p.a(this.animationSpec, scale.animationSpec);
    }

    public final FiniteAnimationSpec<Float> getAnimationSpec() {
        return this.animationSpec;
    }

    public final float getScale() {
        return this.scale;
    }

    /* JADX INFO: renamed from: getTransformOrigin-SzJe1aQ, reason: not valid java name */
    public final long m66getTransformOriginSzJe1aQ() {
        return this.transformOrigin;
    }

    public int hashCode() {
        return this.animationSpec.hashCode() + ((TransformOrigin.m3838hashCodeimpl(this.transformOrigin) + (Float.floatToIntBits(this.scale) * 31)) * 31);
    }

    public String toString() {
        return "Scale(scale=" + this.scale + ", transformOrigin=" + ((Object) TransformOrigin.m3839toStringimpl(this.transformOrigin)) + ", animationSpec=" + this.animationSpec + ')';
    }

    private Scale(float f10, long j10, FiniteAnimationSpec<Float> finiteAnimationSpec) {
        this.scale = f10;
        this.transformOrigin = j10;
        this.animationSpec = finiteAnimationSpec;
    }
}

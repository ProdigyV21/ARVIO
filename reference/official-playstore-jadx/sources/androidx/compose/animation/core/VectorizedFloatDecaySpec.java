package androidx.compose.animation.core;

import androidx.compose.animation.core.AnimationVector;
import kotlin.Metadata;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\n\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001d\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0015J\u001d\u0010\u0016\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0017J%\u0010\u0018\u001a\u00028\u00002\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001aJ%\u0010\u001b\u001a\u00028\u00002\u0006\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001aR\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0010\u0010\r\u001a\u00028\u0000X\u0082.¢\u0006\u0004\n\u0002\u0010\u000eR\u0010\u0010\u000f\u001a\u00028\u0000X\u0082.¢\u0006\u0004\n\u0002\u0010\u000eR\u0010\u0010\u0010\u001a\u00028\u0000X\u0082.¢\u0006\u0004\n\u0002\u0010\u000e¨\u0006\u001c"}, d2 = {"Landroidx/compose/animation/core/VectorizedFloatDecaySpec;", "V", "Landroidx/compose/animation/core/AnimationVector;", "Landroidx/compose/animation/core/VectorizedDecayAnimationSpec;", "floatDecaySpec", "Landroidx/compose/animation/core/FloatDecayAnimationSpec;", "(Landroidx/compose/animation/core/FloatDecayAnimationSpec;)V", "absVelocityThreshold", "", "getAbsVelocityThreshold", "()F", "getFloatDecaySpec", "()Landroidx/compose/animation/core/FloatDecayAnimationSpec;", "targetVector", "Landroidx/compose/animation/core/AnimationVector;", "valueVector", "velocityVector", "getDurationNanos", "", "initialValue", "initialVelocity", "(Landroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;)J", "getTargetValue", "(Landroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;)Landroidx/compose/animation/core/AnimationVector;", "getValueFromNanos", "playTimeNanos", "(JLandroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;)Landroidx/compose/animation/core/AnimationVector;", "getVelocityFromNanos", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class VectorizedFloatDecaySpec<V extends AnimationVector> implements VectorizedDecayAnimationSpec<V> {
    private final float absVelocityThreshold;
    private final FloatDecayAnimationSpec floatDecaySpec;
    private V targetVector;
    private V valueVector;
    private V velocityVector;

    public VectorizedFloatDecaySpec(FloatDecayAnimationSpec floatDecayAnimationSpec) {
        this.floatDecaySpec = floatDecayAnimationSpec;
        this.absVelocityThreshold = floatDecayAnimationSpec.getAbsVelocityThreshold();
    }

    @Override // androidx.compose.animation.core.VectorizedDecayAnimationSpec
    public float getAbsVelocityThreshold() {
        return this.absVelocityThreshold;
    }

    @Override // androidx.compose.animation.core.VectorizedDecayAnimationSpec
    public long getDurationNanos(V initialValue, V initialVelocity) {
        if (this.velocityVector == null) {
            this.velocityVector = (V) AnimationVectorsKt.newInstance(initialValue);
        }
        V v2 = this.velocityVector;
        if (v2 == null) {
            p.i("velocityVector");
            throw null;
        }
        int size = v2.getSize();
        long jMax = 0;
        for (int i10 = 0; i10 < size; i10++) {
            jMax = Math.max(jMax, this.floatDecaySpec.getDurationNanos(initialValue.get$animation_core_release(i10), initialVelocity.get$animation_core_release(i10)));
        }
        return jMax;
    }

    public final FloatDecayAnimationSpec getFloatDecaySpec() {
        return this.floatDecaySpec;
    }

    @Override // androidx.compose.animation.core.VectorizedDecayAnimationSpec
    public V getTargetValue(V initialValue, V initialVelocity) {
        if (this.targetVector == null) {
            this.targetVector = (V) AnimationVectorsKt.newInstance(initialValue);
        }
        V v2 = this.targetVector;
        if (v2 == null) {
            p.i("targetVector");
            throw null;
        }
        int size = v2.getSize();
        for (int i10 = 0; i10 < size; i10++) {
            V v10 = this.targetVector;
            if (v10 == null) {
                p.i("targetVector");
                throw null;
            }
            v10.set$animation_core_release(i10, this.floatDecaySpec.getTargetValue(initialValue.get$animation_core_release(i10), initialVelocity.get$animation_core_release(i10)));
        }
        V v11 = this.targetVector;
        if (v11 != null) {
            return v11;
        }
        p.i("targetVector");
        throw null;
    }

    @Override // androidx.compose.animation.core.VectorizedDecayAnimationSpec
    public V getValueFromNanos(long playTimeNanos, V initialValue, V initialVelocity) {
        if (this.valueVector == null) {
            this.valueVector = (V) AnimationVectorsKt.newInstance(initialValue);
        }
        V v2 = this.valueVector;
        if (v2 == null) {
            p.i("valueVector");
            throw null;
        }
        int size = v2.getSize();
        for (int i10 = 0; i10 < size; i10++) {
            V v10 = this.valueVector;
            if (v10 == null) {
                p.i("valueVector");
                throw null;
            }
            v10.set$animation_core_release(i10, this.floatDecaySpec.getValueFromNanos(playTimeNanos, initialValue.get$animation_core_release(i10), initialVelocity.get$animation_core_release(i10)));
        }
        V v11 = this.valueVector;
        if (v11 != null) {
            return v11;
        }
        p.i("valueVector");
        throw null;
    }

    @Override // androidx.compose.animation.core.VectorizedDecayAnimationSpec
    public V getVelocityFromNanos(long playTimeNanos, V initialValue, V initialVelocity) {
        if (this.velocityVector == null) {
            this.velocityVector = (V) AnimationVectorsKt.newInstance(initialValue);
        }
        V v2 = this.velocityVector;
        if (v2 == null) {
            p.i("velocityVector");
            throw null;
        }
        int size = v2.getSize();
        for (int i10 = 0; i10 < size; i10++) {
            V v10 = this.velocityVector;
            if (v10 == null) {
                p.i("velocityVector");
                throw null;
            }
            v10.set$animation_core_release(i10, this.floatDecaySpec.getVelocityFromNanos(playTimeNanos, initialValue.get$animation_core_release(i10), initialVelocity.get$animation_core_release(i10)));
        }
        V v11 = this.velocityVector;
        if (v11 != null) {
            return v11;
        }
        p.i("velocityVector");
        throw null;
    }
}

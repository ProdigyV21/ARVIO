package androidx.compose.animation.core;

import androidx.compose.animation.core.AnimationVector;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.h0;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;
import x6.x;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0010\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003B9\u0012\u001e\u0010\b\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\u00060\u0004\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\b\b\u0002\u0010\n\u001a\u00020\u0005¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\u000f\u0010\u0010J/\u0010\u0016\u001a\u00028\u00002\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0016\u0010\u0017J/\u0010\u0018\u001a\u00028\u00002\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00028\u00002\u0006\u0010\u0015\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u0018\u0010\u0017R,\u0010\b\u001a\u001a\u0012\u0004\u0012\u00020\u0005\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00070\u00060\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\u0019R\u001a\u0010\t\u001a\u00020\u00058\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\t\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u001a\u0010\n\u001a\u00020\u00058\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u001a\u001a\u0004\b\u001d\u0010\u001cR\u0016\u0010\u001e\u001a\u00028\u00008\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u0016\u0010 \u001a\u00028\u00008\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b \u0010\u001f¨\u0006!"}, d2 = {"Landroidx/compose/animation/core/VectorizedKeyframesSpec;", "Landroidx/compose/animation/core/AnimationVector;", "V", "Landroidx/compose/animation/core/VectorizedDurationBasedAnimationSpec;", "", "", "Lx6/x;", "Landroidx/compose/animation/core/Easing;", "keyframes", "durationMillis", "delayMillis", "<init>", "(Ljava/util/Map;II)V", "value", "Lx6/t0;", "init", "(Landroidx/compose/animation/core/AnimationVector;)V", "", "playTimeNanos", "initialValue", "targetValue", "initialVelocity", "getValueFromNanos", "(JLandroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;)Landroidx/compose/animation/core/AnimationVector;", "getVelocityFromNanos", "Ljava/util/Map;", "I", "getDurationMillis", "()I", "getDelayMillis", "valueVector", "Landroidx/compose/animation/core/AnimationVector;", "velocityVector", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class VectorizedKeyframesSpec<V extends AnimationVector> implements VectorizedDurationBasedAnimationSpec<V> {
    public static final int $stable = 8;
    private final int delayMillis;
    private final int durationMillis;
    private final Map<Integer, x> keyframes;
    private V valueVector;
    private V velocityVector;

    /* JADX WARN: Multi-variable type inference failed */
    public VectorizedKeyframesSpec(Map<Integer, ? extends x> map, int i10, int i11) {
        this.keyframes = map;
        this.durationMillis = i10;
        this.delayMillis = i11;
    }

    private final void init(V value) {
        if (this.valueVector == null) {
            this.valueVector = (V) AnimationVectorsKt.newInstance(value);
            this.velocityVector = (V) AnimationVectorsKt.newInstance(value);
        }
    }

    @Override // androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec
    public int getDelayMillis() {
        return this.delayMillis;
    }

    @Override // androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec
    public int getDurationMillis() {
        return this.durationMillis;
    }

    @Override // androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec, androidx.compose.animation.core.VectorizedAnimationSpec
    public final /* synthetic */ long getDurationNanos(AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        return f.a(this, animationVector, animationVector2, animationVector3);
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public final /* synthetic */ AnimationVector getEndVelocity(AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        return e.a(this, animationVector, animationVector2, animationVector3);
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public V getValueFromNanos(long playTimeNanos, V initialValue, V targetValue, V initialVelocity) {
        int iClampPlayTime = (int) VectorizedAnimationSpecKt.clampPlayTime(this, playTimeNanos / 1000000);
        if (this.keyframes.containsKey(Integer.valueOf(iClampPlayTime))) {
            return (V) ((x) h0.s0(this.keyframes, Integer.valueOf(iClampPlayTime))).f22608i;
        }
        if (iClampPlayTime >= getDurationMillis()) {
            return targetValue;
        }
        if (iClampPlayTime <= 0) {
            return initialValue;
        }
        int durationMillis = getDurationMillis();
        Easing linearEasing = EasingKt.getLinearEasing();
        AnimationVector animationVector = initialValue;
        int i10 = 0;
        for (Map.Entry<Integer, x> entry : this.keyframes.entrySet()) {
            int iIntValue = entry.getKey().intValue();
            x value = entry.getValue();
            if (iClampPlayTime > iIntValue && iIntValue >= i10) {
                animationVector = (AnimationVector) value.f22608i;
                linearEasing = (Easing) value.f22609l;
                i10 = iIntValue;
            } else if (iClampPlayTime < iIntValue && iIntValue <= durationMillis) {
                targetValue = (V) value.f22608i;
                durationMillis = iIntValue;
            }
        }
        float fTransform = linearEasing.transform((iClampPlayTime - i10) / (durationMillis - i10));
        init(initialValue);
        int size = animationVector.getSize();
        for (int i11 = 0; i11 < size; i11++) {
            V v2 = this.valueVector;
            if (v2 == null) {
                p.i("valueVector");
                throw null;
            }
            v2.set$animation_core_release(i11, VectorConvertersKt.lerp(animationVector.get$animation_core_release(i11), targetValue.get$animation_core_release(i11), fTransform));
        }
        V v10 = this.valueVector;
        if (v10 != null) {
            return v10;
        }
        p.i("valueVector");
        throw null;
    }

    @Override // androidx.compose.animation.core.VectorizedAnimationSpec
    public V getVelocityFromNanos(long playTimeNanos, V initialValue, V targetValue, V initialVelocity) {
        long jClampPlayTime = VectorizedAnimationSpecKt.clampPlayTime(this, playTimeNanos / 1000000);
        if (jClampPlayTime <= 0) {
            return initialVelocity;
        }
        AnimationVector valueFromMillis = VectorizedAnimationSpecKt.getValueFromMillis(this, jClampPlayTime - 1, initialValue, targetValue, initialVelocity);
        AnimationVector valueFromMillis2 = VectorizedAnimationSpecKt.getValueFromMillis(this, jClampPlayTime, initialValue, targetValue, initialVelocity);
        init(initialValue);
        int size = valueFromMillis.getSize();
        for (int i10 = 0; i10 < size; i10++) {
            V v2 = this.velocityVector;
            if (v2 == null) {
                p.i("velocityVector");
                throw null;
            }
            v2.set$animation_core_release(i10, (valueFromMillis.get$animation_core_release(i10) - valueFromMillis2.get$animation_core_release(i10)) * 1000.0f);
        }
        V v10 = this.velocityVector;
        if (v10 != null) {
            return v10;
        }
        p.i("velocityVector");
        throw null;
    }

    @Override // androidx.compose.animation.core.VectorizedFiniteAnimationSpec, androidx.compose.animation.core.VectorizedAnimationSpec
    public final /* synthetic */ boolean isInfinite() {
        return g.a(this);
    }

    public /* synthetic */ VectorizedKeyframesSpec(Map map, int i10, int i11, int i12, h hVar) {
        this(map, i10, (i12 & 4) != 0 ? 0 : i11);
    }
}

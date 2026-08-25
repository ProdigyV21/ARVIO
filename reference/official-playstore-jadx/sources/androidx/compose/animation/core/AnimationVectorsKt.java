package androidx.compose.animation.core;

import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0015\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u001d\u0010\u0003\u001a\u00020\u00062\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\u0007\u001a%\u0010\u0003\u001a\u00020\t2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\n\u001a-\u0010\u0003\u001a\u00020\f2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0000¢\u0006\u0004\b\u0003\u0010\r\u001a\u001d\u0010\u0010\u001a\u00028\u0000\"\b\b\u0000\u0010\u000f*\u00020\u000e*\u00028\u0000H\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u001a\u001d\u0010\u0012\u001a\u00028\u0000\"\b\b\u0000\u0010\u000f*\u00020\u000e*\u00028\u0000H\u0000¢\u0006\u0004\b\u0012\u0010\u0011\u001a%\u0010\u0015\u001a\u00020\u0014\"\b\b\u0000\u0010\u000f*\u00020\u000e*\u00028\u00002\u0006\u0010\u0013\u001a\u00028\u0000H\u0000¢\u0006\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"", "v1", "Landroidx/compose/animation/core/AnimationVector1D;", "AnimationVector", "(F)Landroidx/compose/animation/core/AnimationVector1D;", "v2", "Landroidx/compose/animation/core/AnimationVector2D;", "(FF)Landroidx/compose/animation/core/AnimationVector2D;", "v3", "Landroidx/compose/animation/core/AnimationVector3D;", "(FFF)Landroidx/compose/animation/core/AnimationVector3D;", "v4", "Landroidx/compose/animation/core/AnimationVector4D;", "(FFFF)Landroidx/compose/animation/core/AnimationVector4D;", "Landroidx/compose/animation/core/AnimationVector;", "T", "newInstance", "(Landroidx/compose/animation/core/AnimationVector;)Landroidx/compose/animation/core/AnimationVector;", "copy", "source", "Lx6/t0;", "copyFrom", "(Landroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;)V", "animation-core_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AnimationVectorsKt {
    public static final AnimationVector1D AnimationVector(float f10) {
        return new AnimationVector1D(f10);
    }

    public static final <T extends AnimationVector> T copy(T t2) {
        T t10 = (T) newInstance(t2);
        int size = t10.getSize();
        for (int i10 = 0; i10 < size; i10++) {
            t10.set$animation_core_release(i10, t2.get$animation_core_release(i10));
        }
        return t10;
    }

    public static final <T extends AnimationVector> void copyFrom(T t2, T t10) {
        int size = t2.getSize();
        for (int i10 = 0; i10 < size; i10++) {
            t2.set$animation_core_release(i10, t10.get$animation_core_release(i10));
        }
    }

    public static final <T extends AnimationVector> T newInstance(T t2) {
        return (T) t2.newVector$animation_core_release();
    }

    public static final AnimationVector2D AnimationVector(float f10, float f11) {
        return new AnimationVector2D(f10, f11);
    }

    public static final AnimationVector3D AnimationVector(float f10, float f11, float f12) {
        return new AnimationVector3D(f10, f11, f12);
    }

    public static final AnimationVector4D AnimationVector(float f10, float f11, float f12, float f13) {
        return new AnimationVector4D(f10, f11, f12, f13);
    }
}

package androidx.compose.animation.core;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class c {
    public static float a(FloatAnimationSpec floatAnimationSpec, float f10, float f11, float f12) {
        return floatAnimationSpec.getVelocityFromNanos(floatAnimationSpec.getDurationNanos(f10, f11, f12), f10, f11, f12);
    }

    public static VectorizedFloatAnimationSpec c(FloatAnimationSpec floatAnimationSpec, TwoWayConverter twoWayConverter) {
        return new VectorizedFloatAnimationSpec(floatAnimationSpec);
    }
}

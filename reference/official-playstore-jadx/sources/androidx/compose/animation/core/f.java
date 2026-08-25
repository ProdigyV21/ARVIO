package androidx.compose.animation.core;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class f {
    public static long a(VectorizedDurationBasedAnimationSpec vectorizedDurationBasedAnimationSpec, AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        return ((long) (vectorizedDurationBasedAnimationSpec.getDurationMillis() + vectorizedDurationBasedAnimationSpec.getDelayMillis())) * 1000000;
    }
}

package androidx.compose.animation.core;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class e {
    public static AnimationVector a(VectorizedAnimationSpec vectorizedAnimationSpec, AnimationVector animationVector, AnimationVector animationVector2, AnimationVector animationVector3) {
        return vectorizedAnimationSpec.getVelocityFromNanos(vectorizedAnimationSpec.getDurationNanos(animationVector, animationVector2, animationVector3), animationVector, animationVector2, animationVector3);
    }
}

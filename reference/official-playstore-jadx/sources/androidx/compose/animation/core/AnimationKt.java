package androidx.compose.animation.core;

import kotlin.Metadata;
import kotlin.jvm.internal.i;
import kotlin.jvm.internal.r;
import r7.l;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a,\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\t2\b\b\u0002\u0010\u000e\u001a\u00020\t\u001aa\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u0002H\u00120\u0010\"\u0004\b\u0000\u0010\u0011\"\b\b\u0001\u0010\u0012*\u00020\u00132\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00110\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u0002H\u00120\u00162\u0006\u0010\r\u001a\u0002H\u00112\u0006\u0010\u0017\u001a\u0002H\u00112\u0006\u0010\u000e\u001a\u0002H\u0011¢\u0006\u0002\u0010\u0018\u001aC\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u0012\u0012\u0004\u0012\u0002H\u00120\u0010\"\b\b\u0000\u0010\u0012*\u00020\u0013*\b\u0012\u0004\u0012\u0002H\u00120\u001a2\u0006\u0010\r\u001a\u0002H\u00122\u0006\u0010\u0017\u001a\u0002H\u00122\u0006\u0010\u000e\u001a\u0002H\u0012¢\u0006\u0002\u0010\u001b\u001a3\u0010\u001c\u001a\u0002H\u0011\"\u0004\b\u0000\u0010\u0011\"\b\b\u0001\u0010\u0012*\u00020\u0013*\u000e\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u0002H\u00120\u00042\u0006\u0010\u001d\u001a\u00020\u0001¢\u0006\u0002\u0010\u001e\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\" \u0010\u0003\u001a\u00020\u0001*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00048@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u001f"}, d2 = {"MillisToNanos", "", "SecondsToMillis", "durationMillis", "Landroidx/compose/animation/core/Animation;", "getDurationMillis", "(Landroidx/compose/animation/core/Animation;)J", "DecayAnimation", "Landroidx/compose/animation/core/DecayAnimation;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "animationSpec", "Landroidx/compose/animation/core/FloatDecayAnimationSpec;", "initialValue", "initialVelocity", "TargetBasedAnimation", "Landroidx/compose/animation/core/TargetBasedAnimation;", "T", "V", "Landroidx/compose/animation/core/AnimationVector;", "Landroidx/compose/animation/core/AnimationSpec;", "typeConverter", "Landroidx/compose/animation/core/TwoWayConverter;", "targetValue", "(Landroidx/compose/animation/core/AnimationSpec;Landroidx/compose/animation/core/TwoWayConverter;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Landroidx/compose/animation/core/TargetBasedAnimation;", "createAnimation", "Landroidx/compose/animation/core/VectorizedAnimationSpec;", "(Landroidx/compose/animation/core/VectorizedAnimationSpec;Landroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;Landroidx/compose/animation/core/AnimationVector;)Landroidx/compose/animation/core/TargetBasedAnimation;", "getVelocityFromNanos", "playTimeNanos", "(Landroidx/compose/animation/core/Animation;J)Ljava/lang/Object;", "animation-core_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AnimationKt {
    public static final long MillisToNanos = 1000000;
    public static final long SecondsToMillis = 1000;

    /* JADX INFO: Add missing generic type declarations: [V] */
    /* JADX INFO: renamed from: androidx.compose.animation.core.AnimationKt$createAnimation$1, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u0002H\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "V", "Landroidx/compose/animation/core/AnimationVector;", "it", "invoke", "(Landroidx/compose/animation/core/AnimationVector;)Landroidx/compose/animation/core/AnimationVector;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass1<V> extends r implements l<V, V> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1);
        }

        /* JADX WARN: Incorrect return type in method signature: (TV;)TV; */
        @Override // r7.l
        public final AnimationVector invoke(AnimationVector animationVector) {
            return animationVector;
        }
    }

    /* JADX INFO: Add missing generic type declarations: [V] */
    /* JADX INFO: renamed from: androidx.compose.animation.core.AnimationKt$createAnimation$2, reason: invalid class name */
    @Metadata(d1 = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u0002H\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "V", "Landroidx/compose/animation/core/AnimationVector;", "it", "invoke", "(Landroidx/compose/animation/core/AnimationVector;)Landroidx/compose/animation/core/AnimationVector;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class AnonymousClass2<V> extends r implements l<V, V> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(1);
        }

        /* JADX WARN: Incorrect return type in method signature: (TV;)TV; */
        @Override // r7.l
        public final AnimationVector invoke(AnimationVector animationVector) {
            return animationVector;
        }
    }

    public static final DecayAnimation<Float, AnimationVector1D> DecayAnimation(FloatDecayAnimationSpec floatDecayAnimationSpec, float f10, float f11) {
        return new DecayAnimation<>((DecayAnimationSpec<Float>) DecayAnimationSpecKt.generateDecayAnimationSpec(floatDecayAnimationSpec), VectorConvertersKt.getVectorConverter(i.f19743a), Float.valueOf(f10), AnimationVectorsKt.AnimationVector(f11));
    }

    public static /* synthetic */ DecayAnimation DecayAnimation$default(FloatDecayAnimationSpec floatDecayAnimationSpec, float f10, float f11, int i10, Object obj) {
        if ((i10 & 4) != 0) {
            f11 = 0.0f;
        }
        return DecayAnimation(floatDecayAnimationSpec, f10, f11);
    }

    public static final <T, V extends AnimationVector> TargetBasedAnimation<T, V> TargetBasedAnimation(AnimationSpec<T> animationSpec, TwoWayConverter<T, V> twoWayConverter, T t2, T t10, T t11) {
        return new TargetBasedAnimation<>(animationSpec, twoWayConverter, t2, t10, (AnimationVector) twoWayConverter.getConvertToVector().invoke(t11));
    }

    public static final <V extends AnimationVector> TargetBasedAnimation<V, V> createAnimation(VectorizedAnimationSpec<V> vectorizedAnimationSpec, V v2, V v10, V v11) {
        return new TargetBasedAnimation<>(vectorizedAnimationSpec, (TwoWayConverter<V, V>) VectorConvertersKt.TwoWayConverter(AnonymousClass1.INSTANCE, AnonymousClass2.INSTANCE), v2, v10, v11);
    }

    public static final long getDurationMillis(Animation<?, ?> animation) {
        return animation.getDurationNanos() / 1000000;
    }

    public static final <T, V extends AnimationVector> T getVelocityFromNanos(Animation<T, V> animation, long j10) {
        return (T) animation.getTypeConverter().getConvertFromVector().invoke(animation.getVelocityVectorFromNanos(j10));
    }
}

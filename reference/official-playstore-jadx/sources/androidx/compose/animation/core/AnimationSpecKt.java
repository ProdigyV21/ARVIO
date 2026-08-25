package androidx.compose.animation.core;

import androidx.compose.animation.core.KeyframesSpec;
import androidx.compose.animation.core.KeyframesWithSplineSpec;
import kotlin.Metadata;
import kotlin.jvm.internal.h;
import r7.l;
import x6.t0;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a;\u0010\u0005\u001a\u0004\u0018\u00018\u0001\"\u0004\b\u0000\u0010\u0000\"\b\b\u0001\u0010\u0002*\u00020\u0001*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00018\u0000H\u0002¢\u0006\u0004\b\u0005\u0010\u0006\u001a9\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\f\"\u0004\b\u0000\u0010\u00002\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\u000b\u001a\u00020\nH\u0007¢\u0006\u0004\b\r\u0010\u000e\u001a;\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013\"\u0004\b\u0000\u0010\u00002\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00018\u0000H\u0007¢\u0006\u0004\b\u0014\u0010\u0015\u001a:\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\u001b\"\u0004\b\u0000\u0010\u00002\u001d\u0010\u001a\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0017\u0012\u0004\u0012\u00020\u00180\u0016¢\u0006\u0002\b\u0019H\u0007¢\u0006\u0004\b\u001c\u0010\u001d\u001a:\u0010 \u001a\b\u0012\u0004\u0012\u00028\u00000\u001f\"\u0004\b\u0000\u0010\u00002\u001d\u0010\u001a\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001e\u0012\u0004\u0012\u00020\u00180\u0016¢\u0006\u0002\b\u0019H\u0007¢\u0006\u0004\b \u0010!\u001aH\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000)\"\u0004\b\u0000\u0010\u00002\u0006\u0010\"\u001a\u00020\u00072\f\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000#2\b\b\u0002\u0010&\u001a\u00020%2\b\b\u0002\u0010(\u001a\u00020'H\u0007ø\u0001\u0000¢\u0006\u0004\b*\u0010+\u001a;\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000)\"\u0004\b\u0000\u0010\u00002\u0006\u0010\"\u001a\u00020\u00072\f\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000#2\b\b\u0002\u0010&\u001a\u00020%H\u0007¢\u0006\u0004\b,\u0010-\u001a@\u00101\u001a\b\u0012\u0004\u0012\u00028\u00000.\"\u0004\b\u0000\u0010\u00002\f\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000#2\b\b\u0002\u0010&\u001a\u00020%2\b\b\u0002\u0010(\u001a\u00020'H\u0007ø\u0001\u0000¢\u0006\u0004\b/\u00100\u001a3\u00101\u001a\b\u0012\u0004\u0012\u00028\u00000.\"\u0004\b\u0000\u0010\u00002\f\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000#2\b\b\u0002\u0010&\u001a\u00020%H\u0007¢\u0006\u0004\b1\u00102\u001a%\u00104\u001a\b\u0012\u0004\u0012\u00028\u000003\"\u0004\b\u0000\u0010\u00002\b\b\u0002\u0010\t\u001a\u00020\u0007H\u0007¢\u0006\u0004\b4\u00105\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00066"}, d2 = {"T", "Landroidx/compose/animation/core/AnimationVector;", "V", "Landroidx/compose/animation/core/TwoWayConverter;", "data", "convert", "(Landroidx/compose/animation/core/TwoWayConverter;Ljava/lang/Object;)Landroidx/compose/animation/core/AnimationVector;", "", "durationMillis", "delayMillis", "Landroidx/compose/animation/core/Easing;", "easing", "Landroidx/compose/animation/core/TweenSpec;", "tween", "(IILandroidx/compose/animation/core/Easing;)Landroidx/compose/animation/core/TweenSpec;", "", "dampingRatio", "stiffness", "visibilityThreshold", "Landroidx/compose/animation/core/SpringSpec;", "spring", "(FFLjava/lang/Object;)Landroidx/compose/animation/core/SpringSpec;", "Lkotlin/Function1;", "Landroidx/compose/animation/core/KeyframesSpec$KeyframesSpecConfig;", "Lx6/t0;", "Lx6/n;", "init", "Landroidx/compose/animation/core/KeyframesSpec;", "keyframes", "(Lr7/l;)Landroidx/compose/animation/core/KeyframesSpec;", "Landroidx/compose/animation/core/KeyframesWithSplineSpec$KeyframesWithSplineSpecConfig;", "Landroidx/compose/animation/core/KeyframesWithSplineSpec;", "keyframesWithSpline", "(Lr7/l;)Landroidx/compose/animation/core/KeyframesWithSplineSpec;", "iterations", "Landroidx/compose/animation/core/DurationBasedAnimationSpec;", "animation", "Landroidx/compose/animation/core/RepeatMode;", "repeatMode", "Landroidx/compose/animation/core/StartOffset;", "initialStartOffset", "Landroidx/compose/animation/core/RepeatableSpec;", "repeatable-91I0pcU", "(ILandroidx/compose/animation/core/DurationBasedAnimationSpec;Landroidx/compose/animation/core/RepeatMode;J)Landroidx/compose/animation/core/RepeatableSpec;", "repeatable", "(ILandroidx/compose/animation/core/DurationBasedAnimationSpec;Landroidx/compose/animation/core/RepeatMode;)Landroidx/compose/animation/core/RepeatableSpec;", "Landroidx/compose/animation/core/InfiniteRepeatableSpec;", "infiniteRepeatable-9IiC70o", "(Landroidx/compose/animation/core/DurationBasedAnimationSpec;Landroidx/compose/animation/core/RepeatMode;J)Landroidx/compose/animation/core/InfiniteRepeatableSpec;", "infiniteRepeatable", "(Landroidx/compose/animation/core/DurationBasedAnimationSpec;Landroidx/compose/animation/core/RepeatMode;)Landroidx/compose/animation/core/InfiniteRepeatableSpec;", "Landroidx/compose/animation/core/SnapSpec;", "snap", "(I)Landroidx/compose/animation/core/SnapSpec;", "animation-core_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AnimationSpecKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final <T, V extends AnimationVector> V convert(TwoWayConverter<T, V> twoWayConverter, T t2) {
        if (t2 == null) {
            return null;
        }
        return (V) twoWayConverter.getConvertToVector().invoke(t2);
    }

    @x6.e
    public static final /* synthetic */ InfiniteRepeatableSpec infiniteRepeatable(DurationBasedAnimationSpec durationBasedAnimationSpec, RepeatMode repeatMode) {
        return new InfiniteRepeatableSpec(durationBasedAnimationSpec, repeatMode, StartOffset.m115constructorimpl$default(0, 0, 2, null), (h) null);
    }

    public static /* synthetic */ InfiniteRepeatableSpec infiniteRepeatable$default(DurationBasedAnimationSpec durationBasedAnimationSpec, RepeatMode repeatMode, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            repeatMode = RepeatMode.Restart;
        }
        return infiniteRepeatable(durationBasedAnimationSpec, repeatMode);
    }

    /* JADX INFO: renamed from: infiniteRepeatable-9IiC70o, reason: not valid java name */
    public static final <T> InfiniteRepeatableSpec<T> m92infiniteRepeatable9IiC70o(DurationBasedAnimationSpec<T> durationBasedAnimationSpec, RepeatMode repeatMode, long j10) {
        return new InfiniteRepeatableSpec<>(durationBasedAnimationSpec, repeatMode, j10, (h) null);
    }

    /* JADX INFO: renamed from: infiniteRepeatable-9IiC70o$default, reason: not valid java name */
    public static /* synthetic */ InfiniteRepeatableSpec m93infiniteRepeatable9IiC70o$default(DurationBasedAnimationSpec durationBasedAnimationSpec, RepeatMode repeatMode, long j10, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            repeatMode = RepeatMode.Restart;
        }
        if ((i10 & 4) != 0) {
            j10 = StartOffset.m115constructorimpl$default(0, 0, 2, null);
        }
        return m92infiniteRepeatable9IiC70o(durationBasedAnimationSpec, repeatMode, j10);
    }

    public static final <T> KeyframesSpec<T> keyframes(l<? super KeyframesSpec.KeyframesSpecConfig<T>, t0> lVar) {
        KeyframesSpec.KeyframesSpecConfig keyframesSpecConfig = new KeyframesSpec.KeyframesSpecConfig();
        lVar.invoke(keyframesSpecConfig);
        return new KeyframesSpec<>(keyframesSpecConfig);
    }

    public static final <T> KeyframesWithSplineSpec<T> keyframesWithSpline(l<? super KeyframesWithSplineSpec.KeyframesWithSplineSpecConfig<T>, t0> lVar) {
        KeyframesWithSplineSpec.KeyframesWithSplineSpecConfig keyframesWithSplineSpecConfig = new KeyframesWithSplineSpec.KeyframesWithSplineSpecConfig();
        lVar.invoke(keyframesWithSplineSpecConfig);
        return new KeyframesWithSplineSpec<>(keyframesWithSplineSpecConfig);
    }

    @x6.e
    public static final /* synthetic */ RepeatableSpec repeatable(int i10, DurationBasedAnimationSpec durationBasedAnimationSpec, RepeatMode repeatMode) {
        return new RepeatableSpec(i10, durationBasedAnimationSpec, repeatMode, StartOffset.m115constructorimpl$default(0, 0, 2, null), (h) null);
    }

    public static /* synthetic */ RepeatableSpec repeatable$default(int i10, DurationBasedAnimationSpec durationBasedAnimationSpec, RepeatMode repeatMode, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            repeatMode = RepeatMode.Restart;
        }
        return repeatable(i10, durationBasedAnimationSpec, repeatMode);
    }

    /* JADX INFO: renamed from: repeatable-91I0pcU, reason: not valid java name */
    public static final <T> RepeatableSpec<T> m94repeatable91I0pcU(int i10, DurationBasedAnimationSpec<T> durationBasedAnimationSpec, RepeatMode repeatMode, long j10) {
        return new RepeatableSpec<>(i10, durationBasedAnimationSpec, repeatMode, j10, (h) null);
    }

    /* JADX INFO: renamed from: repeatable-91I0pcU$default, reason: not valid java name */
    public static /* synthetic */ RepeatableSpec m95repeatable91I0pcU$default(int i10, DurationBasedAnimationSpec durationBasedAnimationSpec, RepeatMode repeatMode, long j10, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            repeatMode = RepeatMode.Restart;
        }
        if ((i11 & 8) != 0) {
            j10 = StartOffset.m115constructorimpl$default(0, 0, 2, null);
        }
        return m94repeatable91I0pcU(i10, durationBasedAnimationSpec, repeatMode, j10);
    }

    public static final <T> SnapSpec<T> snap(int i10) {
        return new SnapSpec<>(i10);
    }

    public static /* synthetic */ SnapSpec snap$default(int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            i10 = 0;
        }
        return snap(i10);
    }

    public static final <T> SpringSpec<T> spring(float f10, float f11, T t2) {
        return new SpringSpec<>(f10, f11, t2);
    }

    public static /* synthetic */ SpringSpec spring$default(float f10, float f11, Object obj, int i10, Object obj2) {
        if ((i10 & 1) != 0) {
            f10 = 1.0f;
        }
        if ((i10 & 2) != 0) {
            f11 = 1500.0f;
        }
        if ((i10 & 4) != 0) {
            obj = null;
        }
        return spring(f10, f11, obj);
    }

    public static final <T> TweenSpec<T> tween(int i10, int i11, Easing easing) {
        return new TweenSpec<>(i10, i11, easing);
    }

    public static /* synthetic */ TweenSpec tween$default(int i10, int i11, Easing easing, int i12, Object obj) {
        if ((i12 & 1) != 0) {
            i10 = 300;
        }
        if ((i12 & 2) != 0) {
            i11 = 0;
        }
        if ((i12 & 4) != 0) {
            easing = EasingKt.getFastOutSlowInEasing();
        }
        return tween(i10, i11, easing);
    }
}

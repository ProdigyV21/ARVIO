package androidx.compose.animation.core;

import androidx.compose.animation.core.KeyframesSpec;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0001\u000eB\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J,\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\b\b\u0001\u0010\n*\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H\n0\rH\u0016R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Landroidx/compose/animation/core/KeyframesWithSplineSpec;", "T", "Landroidx/compose/animation/core/DurationBasedAnimationSpec;", "config", "Landroidx/compose/animation/core/KeyframesWithSplineSpec$KeyframesWithSplineSpecConfig;", "(Landroidx/compose/animation/core/KeyframesWithSplineSpec$KeyframesWithSplineSpecConfig;)V", "getConfig", "()Landroidx/compose/animation/core/KeyframesWithSplineSpec$KeyframesWithSplineSpecConfig;", "vectorize", "Landroidx/compose/animation/core/VectorizedDurationBasedAnimationSpec;", "V", "Landroidx/compose/animation/core/AnimationVector;", "converter", "Landroidx/compose/animation/core/TwoWayConverter;", "KeyframesWithSplineSpecConfig", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class KeyframesWithSplineSpec<T> implements DurationBasedAnimationSpec<T> {
    public static final int $stable = 0;
    private final KeyframesWithSplineSpecConfig<T> config;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u0000*\u0004\b\u0001\u0010\u00012\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002B\u0005¢\u0006\u0002\u0010\u0004J\u001d\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u00032\u0006\u0010\u0006\u001a\u00028\u0001H\u0010¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Landroidx/compose/animation/core/KeyframesWithSplineSpec$KeyframesWithSplineSpecConfig;", "T", "Landroidx/compose/animation/core/KeyframesSpecBaseConfig;", "Landroidx/compose/animation/core/KeyframesSpec$KeyframeEntity;", "()V", "createEntityFor", "value", "createEntityFor$animation_core_release", "(Ljava/lang/Object;)Landroidx/compose/animation/core/KeyframesSpec$KeyframeEntity;", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class KeyframesWithSplineSpecConfig<T> extends KeyframesSpecBaseConfig<T, KeyframesSpec.KeyframeEntity<T>> {
        public static final int $stable = 0;

        public KeyframesWithSplineSpecConfig() {
            super(null);
        }

        @Override // androidx.compose.animation.core.KeyframesSpecBaseConfig
        public KeyframesSpec.KeyframeEntity<T> createEntityFor$animation_core_release(T value) {
            return new KeyframesSpec.KeyframeEntity<>(value, null, 2, null);
        }
    }

    public KeyframesWithSplineSpec(KeyframesWithSplineSpecConfig<T> keyframesWithSplineSpecConfig) {
        this.config = keyframesWithSplineSpecConfig;
    }

    public final KeyframesWithSplineSpecConfig<T> getConfig() {
        return this.config;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0064  */
    @Override // androidx.compose.animation.core.FiniteAnimationSpec, androidx.compose.animation.core.AnimationSpec
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public <V extends androidx.compose.animation.core.AnimationVector> androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec<V> vectorize(androidx.compose.animation.core.TwoWayConverter<T, V> r19) {
        /*
            r18 = this;
            r0 = r18
            t.u r1 = new t.u
            r1.<init>()
            t.v r2 = new t.v
            r2.<init>()
            androidx.compose.animation.core.KeyframesWithSplineSpec$KeyframesWithSplineSpecConfig<T> r3 = r0.config
            t.v r3 = r3.getKeyframes()
            int[] r4 = r3.f21843b
            java.lang.Object[] r5 = r3.f21844c
            long[] r3 = r3.f21842a
            int r6 = r3.length
            int r6 = r6 + (-2)
            if (r6 < 0) goto L69
            r8 = 0
        L1e:
            r9 = r3[r8]
            long r11 = ~r9
            r13 = 7
            long r11 = r11 << r13
            long r11 = r11 & r9
            r13 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r11 = r11 & r13
            int r11 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r11 == 0) goto L64
            int r11 = r8 - r6
            int r11 = ~r11
            int r11 = r11 >>> 31
            r12 = 8
            int r11 = 8 - r11
            r13 = 0
        L38:
            if (r13 >= r11) goto L62
            r14 = 255(0xff, double:1.26E-321)
            long r14 = r14 & r9
            r16 = 128(0x80, double:6.3E-322)
            int r14 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r14 >= 0) goto L5e
            int r14 = r8 << 3
            int r14 = r14 + r13
            r15 = r4[r14]
            r14 = r5[r14]
            androidx.compose.animation.core.KeyframesSpec$KeyframeEntity r14 = (androidx.compose.animation.core.KeyframesSpec.KeyframeEntity) r14
            r1.b(r15)
            r7.l r7 = r19.getConvertToVector()
            java.lang.Object r14 = r14.getValue$animation_core_release()
            java.lang.Object r7 = r7.invoke(r14)
            r2.f(r15, r7)
        L5e:
            long r9 = r9 >> r12
            int r13 = r13 + 1
            goto L38
        L62:
            if (r11 != r12) goto L69
        L64:
            if (r8 == r6) goto L69
            int r8 = r8 + 1
            goto L1e
        L69:
            int[] r3 = r1.f21839a
            int r4 = r1.f21840b
            r5 = 0
            java.util.Arrays.sort(r3, r5, r4)
            androidx.compose.animation.core.VectorizedMonoSplineKeyframesSpec r3 = new androidx.compose.animation.core.VectorizedMonoSplineKeyframesSpec
            androidx.compose.animation.core.KeyframesWithSplineSpec$KeyframesWithSplineSpecConfig<T> r4 = r0.config
            int r4 = r4.getDurationMillis()
            androidx.compose.animation.core.KeyframesWithSplineSpec$KeyframesWithSplineSpecConfig<T> r5 = r0.config
            int r5 = r5.getDelayMillis()
            r3.<init>(r1, r2, r4, r5)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.core.KeyframesWithSplineSpec.vectorize(androidx.compose.animation.core.TwoWayConverter):androidx.compose.animation.core.VectorizedDurationBasedAnimationSpec");
    }
}

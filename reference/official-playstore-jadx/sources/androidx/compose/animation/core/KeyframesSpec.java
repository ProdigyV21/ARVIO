package androidx.compose.animation.core;

import kotlin.Metadata;
import kotlin.jvm.internal.h;
import kotlin.jvm.internal.p;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0002\u000e\u000fB\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005J,\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\n0\t\"\b\b\u0001\u0010\n*\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H\n0\rH\u0016R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Landroidx/compose/animation/core/KeyframesSpec;", "T", "Landroidx/compose/animation/core/DurationBasedAnimationSpec;", "config", "Landroidx/compose/animation/core/KeyframesSpec$KeyframesSpecConfig;", "(Landroidx/compose/animation/core/KeyframesSpec$KeyframesSpecConfig;)V", "getConfig", "()Landroidx/compose/animation/core/KeyframesSpec$KeyframesSpecConfig;", "vectorize", "Landroidx/compose/animation/core/VectorizedKeyframesSpec;", "V", "Landroidx/compose/animation/core/AnimationVector;", "converter", "Landroidx/compose/animation/core/TwoWayConverter;", "KeyframeEntity", "KeyframesSpecConfig", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class KeyframesSpec<T> implements DurationBasedAnimationSpec<T> {
    public static final int $stable = 0;
    private final KeyframesSpecConfig<T> config;

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000*\u0004\b\u0001\u0010\u00012\u0014\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00030\u0002B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00010\u00032\u0006\u0010\u0006\u001a\u00028\u0001H\u0010¢\u0006\u0004\b\u0007\u0010\bJ$\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003*\u00028\u00012\b\b\u0001\u0010\u000b\u001a\u00020\nH\u0096\u0004¢\u0006\u0004\b\f\u0010\rJ\"\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003*\u00028\u00012\u0006\u0010\u000f\u001a\u00020\u000eH\u0096\u0004¢\u0006\u0004\b\u0010\u0010\u0011J\"\u0010\u0015\u001a\u00020\u0014*\b\u0012\u0004\u0012\u00028\u00010\u00032\u0006\u0010\u0013\u001a\u00020\u0012H\u0087\u0004¢\u0006\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Landroidx/compose/animation/core/KeyframesSpec$KeyframesSpecConfig;", "T", "Landroidx/compose/animation/core/KeyframesSpecBaseConfig;", "Landroidx/compose/animation/core/KeyframesSpec$KeyframeEntity;", "<init>", "()V", "value", "createEntityFor$animation_core_release", "(Ljava/lang/Object;)Landroidx/compose/animation/core/KeyframesSpec$KeyframeEntity;", "createEntityFor", "", "timeStamp", "at", "(Ljava/lang/Object;I)Landroidx/compose/animation/core/KeyframesSpec$KeyframeEntity;", "", "fraction", "atFraction", "(Ljava/lang/Object;F)Landroidx/compose/animation/core/KeyframesSpec$KeyframeEntity;", "Landroidx/compose/animation/core/Easing;", "easing", "Lx6/t0;", "with", "(Landroidx/compose/animation/core/KeyframesSpec$KeyframeEntity;Landroidx/compose/animation/core/Easing;)V", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class KeyframesSpecConfig<T> extends KeyframesSpecBaseConfig<T, KeyframeEntity<T>> {
        public static final int $stable = 0;

        public KeyframesSpecConfig() {
            super(null);
        }

        @x6.e
        public final void with(KeyframeEntity<T> keyframeEntity, Easing easing) {
            keyframeEntity.setEasing$animation_core_release(easing);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.compose.animation.core.KeyframesSpecBaseConfig
        public KeyframeEntity<T> at(T t2, int i10) {
            KeyframeEntity<T> keyframeEntity = new KeyframeEntity<>(t2, null, 2, 0 == true ? 1 : 0);
            getKeyframes().f(i10, keyframeEntity);
            return keyframeEntity;
        }

        @Override // androidx.compose.animation.core.KeyframesSpecBaseConfig
        public KeyframeEntity<T> atFraction(T t2, float f10) {
            return at((Object) t2, t7.a.M(getDurationMillis() * f10));
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.compose.animation.core.KeyframesSpecBaseConfig
        public KeyframeEntity<T> createEntityFor$animation_core_release(T value) {
            return new KeyframeEntity<>(value, null, 2, 0 == true ? 1 : 0);
        }
    }

    public KeyframesSpec(KeyframesSpecConfig<T> keyframesSpecConfig) {
        this.config = keyframesSpecConfig;
    }

    public final KeyframesSpecConfig<T> getConfig() {
        return this.config;
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u0000*\u0004\b\u0001\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0019\b\u0000\u0012\u0006\u0010\u0003\u001a\u00028\u0001\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0096\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Landroidx/compose/animation/core/KeyframesSpec$KeyframeEntity;", "T", "Landroidx/compose/animation/core/KeyframeBaseEntity;", "value", "easing", "Landroidx/compose/animation/core/Easing;", "(Ljava/lang/Object;Landroidx/compose/animation/core/Easing;)V", "equals", "", "other", "", "hashCode", "", "animation-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class KeyframeEntity<T> extends KeyframeBaseEntity<T> {
        public static final int $stable = 0;

        public /* synthetic */ KeyframeEntity(Object obj, Easing easing, int i10, h hVar) {
            this(obj, (i10 & 2) != 0 ? EasingKt.getLinearEasing() : easing);
        }

        public boolean equals(Object other) {
            if (!(other instanceof KeyframeEntity)) {
                return false;
            }
            KeyframeEntity keyframeEntity = (KeyframeEntity) other;
            return p.a(keyframeEntity.getValue$animation_core_release(), getValue$animation_core_release()) && p.a(keyframeEntity.getEasing(), getEasing());
        }

        public int hashCode() {
            T value$animation_core_release = getValue$animation_core_release();
            return getEasing().hashCode() + ((value$animation_core_release != null ? value$animation_core_release.hashCode() : 0) * 31);
        }

        public KeyframeEntity(T t2, Easing easing) {
            super(t2, easing, null);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x005d  */
    @Override // androidx.compose.animation.core.FiniteAnimationSpec, androidx.compose.animation.core.AnimationSpec
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public <V extends androidx.compose.animation.core.AnimationVector> androidx.compose.animation.core.VectorizedKeyframesSpec<V> vectorize(androidx.compose.animation.core.TwoWayConverter<T, V> r18) {
        /*
            r17 = this;
            r0 = r17
            java.util.LinkedHashMap r1 = new java.util.LinkedHashMap
            r1.<init>()
            androidx.compose.animation.core.KeyframesSpec$KeyframesSpecConfig<T> r2 = r0.config
            t.v r2 = r2.getKeyframes()
            int[] r3 = r2.f21843b
            java.lang.Object[] r4 = r2.f21844c
            long[] r2 = r2.f21842a
            int r5 = r2.length
            int r5 = r5 + (-2)
            if (r5 < 0) goto L62
            r6 = 0
            r7 = r6
        L1a:
            r8 = r2[r7]
            long r10 = ~r8
            r12 = 7
            long r10 = r10 << r12
            long r10 = r10 & r8
            r12 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r10 = r10 & r12
            int r10 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r10 == 0) goto L5d
            int r10 = r7 - r5
            int r10 = ~r10
            int r10 = r10 >>> 31
            r11 = 8
            int r10 = 8 - r10
            r12 = r6
        L34:
            if (r12 >= r10) goto L5b
            r13 = 255(0xff, double:1.26E-321)
            long r13 = r13 & r8
            r15 = 128(0x80, double:6.3E-322)
            int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r13 >= 0) goto L57
            int r13 = r7 << 3
            int r13 = r13 + r12
            r14 = r3[r13]
            r13 = r4[r13]
            androidx.compose.animation.core.KeyframesSpec$KeyframeEntity r13 = (androidx.compose.animation.core.KeyframesSpec.KeyframeEntity) r13
            java.lang.Integer r14 = java.lang.Integer.valueOf(r14)
            r7.l r15 = r18.getConvertToVector()
            x6.x r13 = r13.toPair$animation_core_release(r15)
            r1.put(r14, r13)
        L57:
            long r8 = r8 >> r11
            int r12 = r12 + 1
            goto L34
        L5b:
            if (r10 != r11) goto L62
        L5d:
            if (r7 == r5) goto L62
            int r7 = r7 + 1
            goto L1a
        L62:
            androidx.compose.animation.core.VectorizedKeyframesSpec r2 = new androidx.compose.animation.core.VectorizedKeyframesSpec
            androidx.compose.animation.core.KeyframesSpec$KeyframesSpecConfig<T> r3 = r0.config
            int r3 = r3.getDurationMillis()
            androidx.compose.animation.core.KeyframesSpec$KeyframesSpecConfig<T> r4 = r0.config
            int r4 = r4.getDelayMillis()
            r2.<init>(r1, r3, r4)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.animation.core.KeyframesSpec.vectorize(androidx.compose.animation.core.TwoWayConverter):androidx.compose.animation.core.VectorizedKeyframesSpec");
    }
}
